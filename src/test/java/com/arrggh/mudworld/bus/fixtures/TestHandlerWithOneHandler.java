package com.arrggh.mudworld.bus.fixtures;

import com.arrggh.mudworld.api.bus.EventHandler;

public class TestHandlerWithOneHandler extends TestHandlerWithNoHandlers {
    @EventHandler
    public void handle(Integer integer) {
        incrementHandledCount();
    }
}
