package com.arrggh.mudworld.dao.map;

import com.arrggh.mudworld.dao.AbstractDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.map.Path;

public class PathDao extends AbstractDao<Path> {
    public PathDao(IFileStore store) {
        super(store);
    }

    @Override
    protected Class<Path> getEntityClass() {
        return Path.class;
    }

    @Override
    protected long getId(Path object) {
        return object.getId();
    }

    @Override
    protected String buildKey(long id) {
        return "map/path-" + id + ".json";
    }
}
