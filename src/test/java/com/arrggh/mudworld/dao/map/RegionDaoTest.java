package com.arrggh.mudworld.dao.map;

import com.arrggh.mudworld.dao.AbstractDaoTest;
import com.arrggh.mudworld.dao.IDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.map.Region;

class RegionDaoTest extends AbstractDaoTest<Region> {
    private long nextId = 0;

    @Override
    protected Region create(IDao<Region> dao) {
        return Region.builder().id(nextId++).build();
    }

    @Override
    protected long getId(Region object) {
        return object.getId();
    }

    @Override
    protected IDao<Region> buildDao(IFileStore directory) {
        return new RegionDao(directory);
    }
}