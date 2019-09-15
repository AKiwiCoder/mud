package com.arrggh.mudworld.dao;

public interface IDao<T> {
    T read(long id);

    void update(T object);

    void delete(long id);

    long getNextId(String key);
}
