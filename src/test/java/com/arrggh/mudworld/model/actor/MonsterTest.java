package com.arrggh.mudworld.model.actor;

import com.arrggh.mudworld.model.AbstractModelTest;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

class MonsterTest extends AbstractModelTest<Monster> {
    @Override
    protected Class<Monster> getClassUnderTest() {
        return Monster.class;
    }
}