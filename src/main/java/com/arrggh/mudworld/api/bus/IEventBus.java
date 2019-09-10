package com.arrggh.mudworld.api.bus;

public interface IEventBus {
    /**
     * Returns the number of events on the bus awaiting processing.
     */
    long getPendingEventCount();

    /**
     * Add an event onto the bus to be processed when poll() is called.
     *
     * @param event to be processed
     */
    void postEvent(Object event);

    /**
     * Register an event handler on the bus.
     *
     * The handler class needs to have a method annotated with @see EventHandler which has a
     * single parameter of the type of event that you want to handle.
     *
     * @param handler
     */
    void addHandler(Object handler);

    /**
     * Process the next event on the queue
     *
     * @return if there are still pending events on the bus
     */
    boolean poll();
}
