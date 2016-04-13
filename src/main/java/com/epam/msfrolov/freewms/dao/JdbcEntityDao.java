package com.epam.msfrolov.freewms.dao;

import com.epam.msfrolov.freewms.model.BaseEntity;
import com.epam.msfrolov.freewms.util.Adapter;
import com.epam.msfrolov.freewms.util.AppException;
import com.epam.msfrolov.freewms.util.Common;
import com.epam.msfrolov.freewms.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.msfrolov.freewms.util.ReflectUtil.*;

public class JdbcEntityDao<T extends BaseEntity> implements Dao<T> {
    private static final Logger log = LoggerFactory.getLogger("JdbcEntityDao.class " + JdbcEntityDao.class);
    private final Class<T> clazz;
    private final List<Field> allFields;
    private final Connection connection;
    private final Adapter adapter;

    public JdbcEntityDao(Class<T> clazz, Connection connection) {
        this.clazz = clazz;
        this.connection = connection;
        allFields = ReflectUtil.getAllFields(clazz);
        adapter = new Adapter();
    }

    @Override
    public T insert(T t) {
        List<Object> listValues = new ArrayList<>();
        QueryDesigner query = new QueryDesigner();
        query.insertInto().table(t).ob();
        List<Field> allFields = this.allFields;
        allFields.remove(ReflectUtil.getField("id", clazz));
        boolean[] trigger = {false};
        allFields.forEach(field -> {
            Method getter = getGetter(field.getName(), clazz);
            Object fieldValue = invokeMethod(getter, t);
            if (fieldValue instanceof BaseEntity)
                fieldValue = ((BaseEntity) fieldValue).getId();
            if (fieldValue != null) {
                listValues.add(fieldValue);
                if (trigger[0])
                    query.comma();
                query.camelCaseToUpperCase(field.getName());
                trigger[0] = true;
            }
        });
        query.cb().values().ob();
        for (int i = 0; i < listValues.size(); i++) {
            if (i != 0)
                query.comma();
            query.question();
        }
        query.cb();
        String assembledQuery = query.toString();
        log.debug("query insert: {}", assembledQuery);
        try (PreparedStatement statement = connection.prepareStatement(assembledQuery, Statement.RETURN_GENERATED_KEYS)) {
            fillStatementParameters(listValues, statement);
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            Method setterId = ReflectUtil.getSetter("id", clazz);
            ReflectUtil.invokeMethod(setterId, t, new Object[]{generatedKeys.getInt(1)});
            return t;
        } catch (SQLException e) {
            throw new DaoException("SQLException: fail in prepare statement 'insert'", e);
        }
    }

    @Override
    public T findById(int id) {
        QueryDesigner query = new QueryDesigner();
        query.select().asterisk()
                .from().table(clazz)
                .where().id().equal().integer(id).and().deletionMark().equal().bool(false);
        String assembledQuery = query.toString();
        log.debug("query findById: {}", assembledQuery);
        try (PreparedStatement statement = connection.prepareStatement(assembledQuery)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            @SuppressWarnings("unchecked")
            T result = (T) ReflectUtil.createInstance(clazz);
            fillObject(resultSet, result);
            return result;
        } catch (SQLException e) {
            throw new DaoException("SQLException: fail in prepare statement 'findById'", e);
        }
    }

    @Override
    public List<T> findAll() {
        QueryDesigner query = new QueryDesigner();
        query.select().asterisk().from().table(clazz).where().deletionMark().equal().bool(false);
        String assembledQuery = query.toString();
        log.debug("query findAll: {}", assembledQuery);
        return genericGetAll(assembledQuery);
    }

    @Override //pagination
    public List<T> findAll(int start, int pageSize) {
        QueryDesigner query = new QueryDesigner();
        String n = "LINE_NUMBER";
        query.select().asterisk().from()
                .ob().select().rownum().as().text(n).comma().asterisk()
                .from().table(clazz).where().deletionMark().equal().bool(false)
                .orderBy().id().cb()
                .where().text(n).equalsOrGreaterThan().integer(start)
                .and().text(n).lessThan().integer(start + pageSize);
        String assembledQuery = query.toString();
        log.debug("query findAll(pagination): {}", assembledQuery);
        return genericGetAll(assembledQuery);
    }

    @Override
    public List<T> findByFields(Map<String, Object> map) {
        map.forEach((k, v) -> {
            if (!ReflectUtil.presenceField(k, clazz)) throw new AppException("missing field in the object");
            if (!(adapter.checkType(v) || Common.isEntity(v))) throw new AppException("wrong type");
        });
        QueryDesigner query = new QueryDesigner();
        query.select().asterisk().from().table(clazz).where();
        boolean[] trigger = {false};
        map.forEach((k, v) -> {
            if (trigger[0])
                query.and();
            query.camelCaseToUpperCase(k).equal().question();
            trigger[0] = true;
        });
        query.and().deletionMark().equal().bool(false);
        String assembledQuery = query.toString();
        log.debug("query findByFields: {}", assembledQuery);
        try (PreparedStatement statement = connection.prepareStatement(assembledQuery)) {
            int[] counter = {1};
            map.forEach((k, v) -> {
                try {
                    statement.setString(counter[0]++, Common.isEntity(v) ? String.valueOf(((BaseEntity) v).getId()) : adapter.serialize(v));
                } catch (SQLException e) {
                    throw new DaoException("SQLException: fail when setting parameters in prepareStatement 'findByFields'", e);
                }
            });
            ResultSet resultSet = statement.executeQuery();
            List<T> resultList = new ArrayList<>();
            while (resultSet.next()) {
                @SuppressWarnings("unchecked")
                T result = (T) ReflectUtil.createInstance(clazz);
                fillObject(resultSet, result);
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException e) {
            throw new DaoException("SQLException: fail in prepare statement 'findByFields'", e);
        }
    }

    @Override
    public boolean update(T t) {
        List<Object> listValues = new ArrayList<>();
        QueryDesigner query = new QueryDesigner();
        query.update().table(clazz).set();
        List<Field> allFields = this.allFields;
        allFields.remove(ReflectUtil.getField("id", clazz));
        allFields.forEach(field -> {
            Method getter = getGetter(field.getName(), clazz);
            Object fieldValue = invokeMethod(getter, t);
            if (fieldValue instanceof BaseEntity)
                fieldValue = ((BaseEntity) fieldValue).getId();
            if (fieldValue != null) {
                listValues.add(fieldValue);
                if (allFields.indexOf(field) != 0)
                    query.comma();
                query.camelCaseToUpperCase(field.getName()).equal().question();
            }
        });
        query.where().id().equal().integer(t.getId()).and().deletionMark().equal().bool(false);
        String assembledQuery = query.toString();
        log.debug("query update: {}", assembledQuery);
        try (PreparedStatement statement = connection.prepareStatement(assembledQuery)) {
            fillStatementParameters(listValues, statement);
            int numberOfChanges = statement.executeUpdate();
            return numberOfChanges > 0;
        } catch (SQLException e) {
            throw new DaoException("SQLException: fail in prepare statement 'insert'", e);
        }
    }

    @Override
    public boolean delete(T t) {
        String deletionMark = "DELETION_MARK";
        QueryDesigner query = new QueryDesigner();
        query.update().table(clazz)
                .set().text(deletionMark).equal().question()
                .where().id().equal().integer(t.getId());
        String assembledQuery = query.toString();
        log.debug("query update: {}", assembledQuery);
        try (PreparedStatement statement = connection.prepareStatement(assembledQuery)) {
            statement.setBoolean(1, true);
            int numberOfChanges = statement.executeUpdate();
            return numberOfChanges > 0;
        } catch (SQLException e) {
            throw new DaoException("SQLException: fail in prepare statement 'delete'", e);
        }
    }

    private void fillObject(ResultSet resultSet, T result) throws SQLException {
        for (Field field : allFields) {
            Class fieldClass = (Class) field.getType();
            String dbFieldName = Common.camelCaseToUpperCase(field.getName());
            if (checkIsSubclass(fieldClass, BaseEntity.class)) {
                Integer valueId = (Integer) resultSet.getObject(dbFieldName);
                if (valueId != null) {
                    Object fieldValue = Dao.createDaoEntity(fieldClass, connection).findById(valueId);
                    Method setter = ReflectUtil.getSetter(field.getName(), clazz);
                    ReflectUtil.invokeMethod(setter, result, new Object[]{fieldValue});
                }
            } else if (adapter.checkClass(fieldClass)) {
                Method setter = ReflectUtil.getSetter(field.getName(), clazz);
                Object deserialize = adapter.deserialize(resultSet.getString(dbFieldName), fieldClass);
                if (deserialize != null)
                    ReflectUtil.invokeMethod(setter, result, new Object[]{deserialize});
            } else throw new AppException("condition was not provided");
        }
    }

    private List<T> genericGetAll(String assembledQuery) {
        try (PreparedStatement statement = connection.prepareStatement(assembledQuery)) {
            ResultSet resultSet = statement.executeQuery();
            List resultList = new ArrayList<>();
            while (resultSet.next()) {
                @SuppressWarnings("unchecked")
                T result = (T) ReflectUtil.createInstance(clazz);
                fillObject(resultSet, result);
                //noinspection unchecked
                resultList.add(result);
            }
            //noinspection unchecked
            return resultList;
        } catch (SQLException e) {
            throw new DaoException("SQLException: fail in prepare statement 'findById'", e);
        }
    }

    private void fillStatementParameters(List<Object> listValues, PreparedStatement statement) throws SQLException {
        for (int i = 0; i < listValues.size(); i++) {
            Object o = listValues.get(i);
            if (adapter.checkType(o)) statement.setString(i + 1, adapter.serialize(o));
            else throw new AppException("condition was not provided");
        }
    }


}
