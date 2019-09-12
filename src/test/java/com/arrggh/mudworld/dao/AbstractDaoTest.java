package com.arrggh.mudworld.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractDaoTest<T> {

    @Test
    public void checkOperations(@TempDir File directory) {
        IFileStore store = new FileStore(directory);
        IDao<T> dao = buildDao(store);

        T object = create(dao);
        dao.update(object);
        long id = getId(object);
        T object2 = dao.read(id);

        assertEquals(object, object2);

        dao.delete(id);

        Assertions.assertThrows(DaoException.class, () -> {
            dao.read(id);
        });
    }

    protected abstract T create(IDao<T> dao);
    protected abstract long getId(T object);
    protected abstract IDao<T> buildDao(IFileStore directory);
}
