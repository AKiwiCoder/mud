package com.arrggh.mudworld.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileStoreTest {

    public static final String KEY = "test/t.key";
    public static final String MISSING_KEY = "test/missing.key";
    public static final String CONTENT = "test content";

    @Test
    public void canReadWriteFile(@TempDir File tempDir) {
        IFileStore store = new FileStore(tempDir);
        store.write(KEY, CONTENT);
        assertEquals(CONTENT, store.read(KEY));
    }

    @Test
    public void canDeleteFile(@TempDir File tempDir) {
        IFileStore store = new FileStore(tempDir);
        assertFalse(store.exists(KEY));
        store.write(KEY, CONTENT);
        assertTrue(store.exists(KEY));
        store.delete(KEY);
        assertFalse(store.exists(KEY));
    }

    @Test
    public void exceptionIsThrownWhenReadingMissingFile(@TempDir File tempDir) {
        IFileStore store = new FileStore(tempDir);
        Assertions.assertThrows(DaoException.class, () -> {
            store.read(MISSING_KEY);
        });
    }

    @Test
    public void exceptionIsThrownWhenWritingFails(@TempDir File tempDir) throws IOException {
        IFileStore store = new FileStore(tempDir);
        File temp = new File(tempDir, "test");

        // This will stop the write operation creating the directory that will then cause
        // the following write to fail, as you cannot create a directory with the same name
        // as an existing file.
        temp.createNewFile();

        Assertions.assertThrows(DaoException.class, () -> {
            store.write(MISSING_KEY, CONTENT);
        });
    }
}