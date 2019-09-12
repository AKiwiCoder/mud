package com.arrggh.mudworld.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;

public class FileStore implements IFileStore {
    private final Logger logger = LoggerFactory.getLogger(FileStore.class);
    private final File directory;

    public FileStore(File directory) {
        this.directory = directory;
    }

    @Override
    public boolean exists(String key) {
        File file = new File(directory, key);
        logger.info("Reading file " + file);
        return file.exists();
    }

    @Override
    public String read(String key) {
        File file = new File(directory, key);
        logger.info("Reading file " + file);
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            throw new DaoException("FileStore cannot read " + key, e);
        }
    }

    @Override
    public void write(String key, String content) {
        File file = new File(directory, key);
        logger.info("Writing file " + file);
        file.getParentFile().mkdirs();
        try (Writer writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            throw new DaoException("FileStore cannot write " + key, e);
        }
    }

    @Override
    public void delete(String key) {
        File file = new File(directory, key);
        logger.info("Deleting file " + file);
        file.delete();
    }
}
