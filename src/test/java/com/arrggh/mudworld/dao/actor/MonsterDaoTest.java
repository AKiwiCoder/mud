package com.arrggh.mudworld.dao.actor;

import com.arrggh.mudworld.dao.AbstractDaoTest;
import com.arrggh.mudworld.dao.IDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.actor.Monster;

class MonsterDaoTest extends AbstractDaoTest<Monster> {
    private long nextId = 0;

    @Override
    protected Monster create(IDao<Monster> dao) {

        return Monster.builder().id(dao.getNextId("monster")).templateId(dao.getNextId("monster-template")).build();
    }

    @Override
    protected long getId(Monster object) {
        return object.getId();
    }

    @Override
    protected IDao<Monster> buildDao(IFileStore store) {
        return new MonsterDao(store);
    }
}