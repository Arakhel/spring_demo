package com.nicolaus.codecamp.spring.dao;

import java.util.List;

public interface BaseDAO<T> {

    T save(T entity);
    T delete(T entity);
    T update(T entity);
    List<T> find(T entity, int offset, int limit);
    T findById(int id);
    int count(T entity);
}
