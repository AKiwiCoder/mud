package com.arrggh.mudworld.dao.map;

import com.arrggh.mudworld.dao.AbstractDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.map.Region;

public class RegionDao extends AbstractDao<Region> {
    public RegionDao(IFileStore store) {
        super(store);
    }

    @Override
    protected Class<Region> getEntityClass() {
        return Region.class;
    }

    @Override
    protected long getId(Region object) {
        return object.getId();
    }

    @Override
    protected String buildKey(long id) {
        return "map/region-" + id + ".json";
    }
}
