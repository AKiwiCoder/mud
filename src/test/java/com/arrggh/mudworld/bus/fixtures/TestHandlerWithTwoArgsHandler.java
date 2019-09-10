package com.arrggh.mudworld.bus.fixtures;

import com.arrggh.mudworld.api.bus.EventHandler;

public class TestHandlerWithTwoArgsHandler extends BaseHandler {
    @EventHandler
    public void handle(Integer one, Integer two) {
    }
}
