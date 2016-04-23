package com.epam.msfrolov.freewms.dao;

import com.epam.msfrolov.freewms.model.BaseEntity;
import com.epam.msfrolov.freewms.model.Document;

import java.util.List;
import java.util.Map;

public interface Dao<T extends BaseEntity> {

    T insert(T t);

    T insert(T t, Document document);

    T findById(int id);

    List<T> findAll();

    List<T> findAll(int start, int pageSize); //pagination

    List<T> findByFields(Map<String, Object> map);

    boolean update(T t);

    boolean delete(T t);

    boolean delete(int id);

}
