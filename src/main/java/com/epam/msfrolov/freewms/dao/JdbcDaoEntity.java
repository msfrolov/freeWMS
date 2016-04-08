package com.epam.msfrolov.freewms.dao;

import com.epam.msfrolov.freewms.model.BaseEntity;
import com.epam.msfrolov.freewms.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.msfrolov.freewms.util.ReflectUtil.*;

public class JdbcDaoEntity<T> implements Dao<T> {
    private static final Logger log = LoggerFactory.getLogger(JdbcDaoEntity.class);
    private final Class<T> clazz;
    private List<Field> allFields;
    private Connection connection;
    private Adapter adapter;

    public JdbcDaoEntity(Class<T> clazz, Connection connection) {
        this.clazz = clazz;
        this.connection = connection;
        allFields = ReflectUtil.getAllFields(clazz);
        allFields.remove(ReflectUtil.getField("id", clazz));
        adapter = new Adapter();
    }

    @Override
    public T insert(T t) throws DaoException {
        List<Object> listValues = new ArrayList<>();
        QueryDesigner query = new QueryDesigner();
        query.insertInto().table(t).ob();
        for (Field field : allFields) {
            Method getter = getGetter(field.getName(), clazz);
            Object fieldValue = invokeMethod(getter, t);
            if (fieldValue instanceof BaseEntity)
                fieldValue = ((BaseEntity) fieldValue).getId();
            if (fieldValue != null) {
                listValues.add(fieldValue);
                if (allFields.indexOf(field) != 0)
                    query.comma();
                query.camelCaseToUpperCase(field.getName());
            }
        }
        query.cb().values().ob();
        for (int i = 0; i < listValues.size(); i++) {
            if (i != 0)
                query.comma();
            query.question();
        }
        query.cb();
        String assembledQuery = query.getQuery();
        log.debug(assembledQuery);
        try (PreparedStatement statement = connection.prepareStatement(assembledQuery, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < listValues.size(); i++) {
                Object o = listValues.get(i);
                if (adapter.checkType(o)) statement.setString(i + 1, adapter.serialize(o));
                else throw new AppException("condition was not provided");
            }
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
    public T findById(int id) throws DaoException {
        QueryDesigner query = new QueryDesigner();
        query.select().asterisk()
                .from().table(clazz)
                .where().id().equal().integer(id);
        String assembledQuery = query.toString();
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
    public List<T> findAll() throws DaoException {
        QueryDesigner query = new QueryDesigner();
        query.select().asterisk().from().table(clazz);
        String assembledQuery = query.toString();
        return genericGetAll(assembledQuery);
    }

    @Override //pagination
    public List<T> findAll(int start, int pageSize) throws DaoException {
        QueryDesigner query = new QueryDesigner();
        String n = "LINE_NUMBER";
        query.select().asterisk().from()
                .ob().select().rownum().as().text(n).comma().asterisk()
                .from().table(clazz)
                .orderBy().text(n).cb()
                .where().text(n).equalsOrGreaterThan().integer(start)
                .and().text(n).lessThan().integer(start + pageSize);
        String assembledQuery = query.toString();
        return genericGetAll(assembledQuery);
    }


    private List<T> genericGetAll(String assembledQuery) throws DaoException {
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

    private void fillObject(ResultSet resultSet, T result) throws SQLException, DaoException {
        for (Field field : allFields) {
            log.debug(field.getName());
            Class fieldClass = (Class) field.getType();
            boolean check = checkIsSubclass(fieldClass, BaseEntity.class);
            if (check) {
                Integer valueId = (Integer) resultSet.getObject(field.getName());
                if (valueId != null) {
                    Object fieldValue = DaoFactory.newInstance().createDaoEntity(fieldClass).findById(valueId);
                    Method setter = ReflectUtil.getSetter(field.getName(), clazz);
                    ReflectUtil.invokeMethod(setter, result, new Object[]{fieldValue});
                }
            } else if (adapter.checkType(fieldClass)) {
                Method setter = ReflectUtil.getSetter(field.getName(), clazz);
                Object deserialize = adapter.deserialize(resultSet.getString(field.getName()), fieldClass);
                ReflectUtil.invokeMethod(setter, result, new Object[]{deserialize});
            } else throw new AppException("condition was not provided");
        }
    }

    @Override
    public List<T> findByFields(Map<String, Object> map) throws DaoException {
        String[] keys = new String[map.size()];
        Object[] values = new Object[map.size()];
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();
            if (!ReflectUtil.presenceField(fieldName, value.getClass()))
                throw new AppException("missing field in the object");
            if (!adapter.checkType(value) && !Common.isEntity(value)) throw new AppException("wrong type");
        }
        QueryDesigner query = new QueryDesigner();
        query.select().asterisk()
                .from().table(clazz)
                //.where().text(Common.camelCaseToUpperCase(fieldName)).equal().text(value);
                .
        String assembledQuery = query.toString();
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
    public T update(T t) {
        return null;
    }

    @Override
    public boolean delete(T t) {
        return false;
    }

    // insert
}
