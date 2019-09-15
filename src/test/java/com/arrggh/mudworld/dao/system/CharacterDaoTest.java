package com.arrggh.mudworld.dao.system;

import com.arrggh.mudworld.dao.AbstractDaoTest;
import com.arrggh.mudworld.dao.IDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.system.Character;

class CharacterDaoTest extends AbstractDaoTest<Character> {
    private long nextId = 0;

    @Override
    protected Character create(IDao<Character> dao) {
        return Character.builder().id(nextId++).build();
    }

    @Override
    protected long getId(Character object) {
        return object.getId();
    }

    @Override
    protected IDao<Character> buildDao(IFileStore directory) {
        return new CharacterDao(directory);
    }
}