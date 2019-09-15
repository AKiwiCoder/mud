package com.arrggh.mudworld.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDao<T> implements IDao<T> {
    public static final String IDS_FILE_KEY = "ids.json";
    private final Logger logger = LoggerFactory.getLogger(AbstractDao.class);

    private final ObjectMapper mapper = new ObjectMapper();
    private final IFileStore store;
    private final Map<String, Long> nextIdMap = new HashMap<>();

    public AbstractDao(IFileStore store) {
        this.store = store;

        if (store.exists(IDS_FILE_KEY)) {
            store.read(IDS_FILE_KEY);
        }

    }

    @Override
    public long getNextId(String key) {
        long result = nextIdMap.getOrDefault(key, 0l);
        nextIdMap.put(key, result + 1);
        try {
            store.write(IDS_FILE_KEY, mapper.writeValueAsString(nextIdMap));
        } catch (JsonProcessingException e) {
            throw new DaoException("Cannot save next id map", e);
        }
        logger.info("getNextId for key '" + key + "' returning " + result);
        return result;
    }

    @Override
    public T read(long id) {
        try {
            logger.info("Reading '{} with id '{}'", getEntityClass().getSimpleName(), id);
            return mapper.readValue(store.read(buildKey(id)), getEntityClass());
        } catch (IOException e) {
            throw new DaoException("Cannot read entity with id " + buildKey(id), e);
        }
    }

    @Override
    public void update(T object) {
        try {
            String content = mapper.writeValueAsString(object);
            store.write(buildKey(getId(object)), content);
        } catch (JsonProcessingException e) {
            throw new DaoException("Cannot write entity with id " + buildKey(getId(object)), e);
        }
    }

    @Override
    public void delete(long id) {
        store.delete(buildKey(id));
    }

    protected abstract Class<T> getEntityClass();

    protected abstract long getId(T object);

    protected abstract String buildKey(long id);
}
