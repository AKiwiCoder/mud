package com.arrggh.mudworld.dao;

import java.io.IOException;

public class DaoException extends RuntimeException {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, IOException cause) {
        super(message, cause);
    }
}
