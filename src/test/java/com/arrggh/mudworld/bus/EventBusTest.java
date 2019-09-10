package com.arrggh.mudworld.bus;

import com.arrggh.mudworld.api.bus.IEventBus;
import com.arrggh.mudworld.bus.fixtures.TestHandlerWithAdditionalHandler;
import com.arrggh.mudworld.bus.fixtures.TestHandlerWithNoArgsHandler;
import com.arrggh.mudworld.bus.fixtures.TestHandlerWithOneHandler;
import com.arrggh.mudworld.bus.fixtures.TestHandlerWithTwoArgsHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventBusTest {
    @Test
    public void testHasNoEventsOnCreation() {
        IEventBus bus = new EventBus();
        assertEquals(0, bus.getPendingEventCount());
    }

    @Test
    public void testAddedEventWithNoListenersHasOneEvent() {
        IEventBus bus = new EventBus();
        bus.postEvent(new Object());
        assertEquals(1, bus.getPendingEventCount());
    }

    @Test
    public void testPollWithNoEventsReturnsFalse() {
        IEventBus bus = new EventBus();
        assertEquals(0, bus.getPendingEventCount());
        assertFalse(bus.poll());
    }

    @Test
    public void testPollWithPendingEventsReturnsTrue() {
        IEventBus bus = new EventBus();
        bus.postEvent(new Object());
        bus.postEvent(new Object());
        assertEquals(2, bus.getPendingEventCount());
        assertTrue(bus.poll());
        assertFalse(bus.poll());
    }

    @Test
    public void testAddedEventWithMultipleMethodHasNoEventAfterPoll() {
        IEventBus bus = new EventBus();
        TestHandlerWithAdditionalHandler handler = new TestHandlerWithAdditionalHandler();
        bus.addHandler(handler);
        bus.postEvent(Integer.valueOf(1));
        bus.postEvent(new String(""));
        assertEquals(2, bus.getPendingEventCount());
        assertEquals(0, handler.getHandledCount());
        bus.poll();
        assertEquals(1, bus.getPendingEventCount());
        assertEquals(1, handler.getHandledCount());
        bus.poll();
        assertEquals(0, bus.getPendingEventCount());
        assertEquals(2, handler.getHandledCount());
    }

    @Test
    public void testAddedEventWithListenersHasNoEventAfterPoll() {
        IEventBus bus = new EventBus();
        TestHandlerWithOneHandler handler = new TestHandlerWithOneHandler();
        bus.addHandler(handler);
        bus.postEvent(Integer.valueOf(1));
        assertEquals(1, bus.getPendingEventCount());
        assertEquals(0, handler.getHandledCount());
        bus.poll();
        assertEquals(0, bus.getPendingEventCount());
        assertEquals(1, handler.getHandledCount());
    }


    @Test
    public void testAddedEventWithValidListenersHasNoEventAfterPoll() {
        IEventBus bus = new EventBus();
        TestHandlerWithOneHandler handler = new TestHandlerWithOneHandler();
        bus.addHandler(handler);
        bus.postEvent(Boolean.TRUE);
        assertEquals(1, bus.getPendingEventCount());
        assertEquals(0, handler.getHandledCount());
        bus.poll();
        assertEquals(0, bus.getPendingEventCount());
        assertEquals(0, handler.getHandledCount());
    }

    @Test
    public void testAddedEventWithMultipleListenersEachGetEventAfterPoll() {
        IEventBus bus = new EventBus();
        TestHandlerWithOneHandler handler1 = new TestHandlerWithOneHandler();
        TestHandlerWithOneHandler handler2 = new TestHandlerWithOneHandler();
        bus.addHandler(handler1);
        bus.addHandler(handler2);
        bus.postEvent(Integer.valueOf(1));
        assertEquals(1, bus.getPendingEventCount());
        assertEquals(0, handler1.getHandledCount());
        assertEquals(0, handler2.getHandledCount());
        bus.poll();
        assertEquals(0, bus.getPendingEventCount());
        assertEquals(1, handler1.getHandledCount());
        assertEquals(1, handler2.getHandledCount());
    }

    @Test
    public void testThrowsExceptionWithNoParameterHandler() {
        IEventBus bus = new EventBus();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bus.addHandler(new TestHandlerWithNoArgsHandler());
        });
    }

    @Test
    public void testThrowsExceptionWithTwoParameterHandler() {
        IEventBus bus = new EventBus();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bus.addHandler(new TestHandlerWithTwoArgsHandler());
        });
    }
}