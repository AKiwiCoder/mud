package com.arrggh.mudworld.dao.actor;

import com.arrggh.mudworld.dao.AbstractDaoTest;
import com.arrggh.mudworld.dao.IDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.actor.Equipment;

class EquipmentDaoTest extends AbstractDaoTest<Equipment> {
    @Override
    protected Equipment create(IDao<Equipment> dao) {
        return Equipment.builder().id(dao.getNextId("equipment")).templateId(dao.getNextId("equipment-template")).build();
    }

    @Override
    protected long getId(Equipment object) {
        return object.getId();
    }

    @Override
    protected IDao<Equipment> buildDao(IFileStore store) {
        return new EquipmentDao(store);
    }
}