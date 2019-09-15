package com.arrggh.mudworld.dao.map;

import com.arrggh.mudworld.dao.AbstractDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.map.Room;

public class RoomDao extends AbstractDao<Room> {
    public RoomDao(IFileStore store) {
        super(store);
    }

    @Override
    protected Class<Room> getEntityClass() {
        return Room.class;
    }

    @Override
    protected long getId(Room object) {
        return object.getId();
    }

    @Override
    protected String buildKey(long id) {
        return "map/room-" + id + ".json";
    }
}
