package com.arrggh.mudworld.dao.map;

import com.arrggh.mudworld.dao.AbstractDaoTest;
import com.arrggh.mudworld.dao.IDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.map.World;

class WorldDaoTest extends AbstractDaoTest<World> {
    private long nextId = 0;

    @Override
    protected World create(IDao<World> dao) {
        return World.builder().id(nextId++).build();
    }

    @Override
    protected long getId(World object) {
        return object.getId();
    }

    @Override
    protected IDao<World> buildDao(IFileStore directory) {
        return new WorldDao(directory);
    }
}