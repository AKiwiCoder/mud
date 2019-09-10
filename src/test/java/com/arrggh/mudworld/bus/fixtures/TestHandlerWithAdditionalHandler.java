package com.arrggh.mudworld.bus.fixtures;

import com.arrggh.mudworld.api.bus.EventHandler;

public class TestHandlerWithAdditionalHandler extends TestHandlerWithOneHandler {
    @EventHandler
    public void handle(String string) {
        incrementHandledCount();
    }
}
