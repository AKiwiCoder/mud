package com.arrggh.mudworld.model.system;

import com.arrggh.mudworld.model.AbstractModelTest;

class UserTest extends AbstractModelTest<User> {

    @Override
    protected Class<User> getClassUnderTest() {
        return User.class;
    }
}