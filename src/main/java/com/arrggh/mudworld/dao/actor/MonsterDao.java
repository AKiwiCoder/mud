package com.arrggh.mudworld.dao.actor;

import com.arrggh.mudworld.dao.AbstractDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.actor.Monster;

public class MonsterDao extends AbstractDao<Monster> {
    public MonsterDao(IFileStore store) {
        super(store);
    }

    @Override
    protected Class<Monster> getEntityClass() {
        return Monster.class;
    }

    @Override
    protected long getId(Monster object) {
        return object.getId();
    }

    @Override
    protected String buildKey(long id) {
        return "actor/monster-" + id + ".json";
    }
}