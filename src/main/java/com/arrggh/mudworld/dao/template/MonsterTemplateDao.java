package com.arrggh.mudworld.dao.template;

import com.arrggh.mudworld.dao.AbstractDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.template.MonsterTemplate;

public class MonsterTemplateDao extends AbstractDao<MonsterTemplate> {
    public MonsterTemplateDao(IFileStore store) {
        super(store);
    }

    @Override
    protected Class<MonsterTemplate> getEntityClass() {
        return MonsterTemplate.class;
    }

    @Override
    protected long getId(MonsterTemplate object) {
        return object.getId();
    }

    @Override
    protected String buildKey(long id) {
        return "template/monster-template-" + id + ".json";
    }
}
