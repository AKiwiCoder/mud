package com.arrggh.mudworld.bus.fixtures;

import com.arrggh.mudworld.api.bus.EventHandler;

public class TestHandlerWithNoArgsHandler extends BaseHandler {
    @EventHandler
    public void handle() {
        incrementHandledCount();
        ;
    }
}
