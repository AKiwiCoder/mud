package com.arrggh.mudworld.dao.actor;

import com.arrggh.mudworld.dao.AbstractDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.actor.Equipment;

public class EquipmentDao extends AbstractDao<Equipment> {
    public EquipmentDao(IFileStore store) {
        super(store);
    }

    @Override
    protected Class<Equipment> getEntityClass() {
        return Equipment.class;
    }

    @Override
    protected long getId(Equipment object) {
        return object.getId();
    }

    @Override
    protected String buildKey(long id) {
        return "actor/equipment-" + id + ".json";
    }
}
