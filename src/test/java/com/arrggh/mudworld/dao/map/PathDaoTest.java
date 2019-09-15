package com.arrggh.mudworld.dao.map;

import com.arrggh.mudworld.dao.AbstractDaoTest;
import com.arrggh.mudworld.dao.IDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.map.Path;

class PathDaoTest extends AbstractDaoTest<Path> {
    private long nextId = 0;

    @Override
    protected Path create(IDao<Path> dao) {
        return Path.builder().id(nextId++).build();
    }

    @Override
    protected long getId(Path object) {
        return object.getId();
    }

    @Override
    protected IDao<Path> buildDao(IFileStore directory) {
        return new PathDao(directory);
    }
}