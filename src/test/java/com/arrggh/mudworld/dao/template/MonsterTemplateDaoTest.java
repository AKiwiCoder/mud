package com.arrggh.mudworld.dao.template;

import com.arrggh.mudworld.dao.AbstractDaoTest;
import com.arrggh.mudworld.dao.IDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.template.MonsterTemplate;

class MonsterTemplateDaoTest extends AbstractDaoTest<MonsterTemplate> {
    private long nextId = 0;

    @Override
    protected MonsterTemplate create(IDao<MonsterTemplate> dao) {
        return MonsterTemplate.builder().id(nextId++).build();
    }

    @Override
    protected long getId(MonsterTemplate object) {
        return object.getId();
    }

    @Override
    protected IDao<MonsterTemplate> buildDao(IFileStore directory) {
        return new MonsterTemplateDao(directory);
    }
}