package com.arrggh.mudworld.dao.map;

import com.arrggh.mudworld.dao.AbstractDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.map.World;

public class WorldDao extends AbstractDao<World> {
    public WorldDao(IFileStore store) {
        super(store);
    }

    @Override
    protected Class<World> getEntityClass() {
        return World.class;
    }

    @Override
    protected long getId(World object) {
        return object.getId();
    }

    @Override
    protected String buildKey(long id) {
        return "map/world-" + id + ".json";
    }
}
