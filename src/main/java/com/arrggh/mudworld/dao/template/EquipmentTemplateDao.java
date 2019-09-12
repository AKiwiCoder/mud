package com.arrggh.mudworld.dao.template;

import com.arrggh.mudworld.dao.AbstractDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.template.EquipmentTemplate;

public class EquipmentTemplateDao extends AbstractDao<EquipmentTemplate> {
    public EquipmentTemplateDao(IFileStore store) {
        super(store);
    }

    @Override
    protected Class<EquipmentTemplate> getEntityClass() {
        return EquipmentTemplate.class;
    }

    @Override
    protected long getId(EquipmentTemplate object) {
        return object.getId();
    }

    @Override
    protected String buildKey(long id) {
        return "template/equipment-template-" + id + ".json";
    }
}
