package com.arrggh.mudworld.dao.system;

import com.arrggh.mudworld.dao.AbstractDaoTest;
import com.arrggh.mudworld.dao.IDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.system.User;

class UserDaoTest extends AbstractDaoTest<User> {
    private long nextId = 0;

    @Override
    protected User create(IDao<User> dao) {
        return User.builder().id(nextId++).build();
    }

    @Override
    protected long getId(User object) {
        return object.getId();
    }

    @Override
    protected IDao<User> buildDao(IFileStore directory) {
        return new UserDao(directory);
    }
}