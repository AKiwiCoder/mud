package com.arrggh.mudworld.dao.template;

import com.arrggh.mudworld.dao.AbstractDaoTest;
import com.arrggh.mudworld.dao.IDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.template.EquipmentTemplate;

class EquipmentTemplateDaoTest extends AbstractDaoTest<EquipmentTemplate> {
    private long nextId = 0;

    @Override
    protected EquipmentTemplate create(IDao<EquipmentTemplate> dao) {
        return EquipmentTemplate.builder().id(nextId++).build();
    }

    @Override
    protected long getId(EquipmentTemplate object) {
        return object.getId();
    }

    @Override
    protected IDao<EquipmentTemplate> buildDao(IFileStore directory) {
        return new EquipmentTemplateDao(directory);
    }
}