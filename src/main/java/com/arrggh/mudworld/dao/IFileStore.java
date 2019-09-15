package com.arrggh.mudworld.dao;

public interface IFileStore {
    boolean exists(String key);
    String read(String key);
    void write(String key, String content);
    void delete(String key);
}
