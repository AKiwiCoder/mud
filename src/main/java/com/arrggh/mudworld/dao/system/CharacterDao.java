package com.arrggh.mudworld.dao.system;

import com.arrggh.mudworld.dao.AbstractDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.system.Character;

public class CharacterDao extends AbstractDao<Character> {
    public CharacterDao(IFileStore store) {
        super(store);
    }

    @Override
    protected Class<Character> getEntityClass() {
        return Character.class;
    }

    @Override
    protected long getId(Character object) {
        return object.getId();
    }

    @Override
    protected String buildKey(long id) {
        return "system/character-" + id + ".json";
    }
}
