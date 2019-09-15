package com.arrggh.mudworld.dao.system;

import com.arrggh.mudworld.dao.AbstractDao;
import com.arrggh.mudworld.dao.IFileStore;
import com.arrggh.mudworld.model.system.User;

public class UserDao extends AbstractDao<User> {
    public UserDao(IFileStore store) {
        super(store);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected long getId(User object) {
        return object.getId();
    }

    @Override
    protected String buildKey(long id) {
        return "system/user-" + id + ".json";
    }
}
