package com.arrggh.mudworld.dao.map;

import com.arrggh.mudworld.dao.AbstractDaoTest;
import com.arrggh.mudworld.dao.IDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.map.Room;

class RoomDaoTest extends AbstractDaoTest<Room> {
    private long nextId = 0;

    @Override
    protected Room create(IDao<Room> dao) {
        return Room.builder().id(nextId++).build();
    }

    @Override
    protected long getId(Room object) {
        return object.getId();
    }

    @Override
    protected IDao<Room> buildDao(IFileStore directory) {
        return new RoomDao(directory);
    }
}