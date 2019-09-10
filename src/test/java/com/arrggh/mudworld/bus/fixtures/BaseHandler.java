package com.arrggh.mudworld.bus.fixtures;

public class BaseHandler {
    private int handled = 0;

    public void incrementHandledCount() {
        handled++;
    }

    public int getHandledCount() {
        return handled;
    }
}
