package com.arrggh.mudworld.bus;

import com.arrggh.mudworld.api.bus.EventHandler;
import com.arrggh.mudworld.api.bus.IEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus implements IEventBus {
    private final Logger logger = LoggerFactory.getLogger(EventBus.class);

    private static class Entry {
        Object object;
        Method method;

        Entry(Object object, Method method) {
            this.object = object;
            this.method = method;
        }
    }

    private final Object lock = new Object();
    private final List<Object> events = new ArrayList<>();
    private final Map<Class<?>, List<Entry>> handlerMethods = new HashMap<>();

    @Override
    public long getPendingEventCount() {
        synchronized (lock) {
            return events.size();
        }
    }

    @Override
    public void postEvent(Object event) {
        logger.info("Posting event of type {}", event.getClass().getSimpleName());
        synchronized (lock) {
            events.add(event);
        }
    }

    @Override
    public void addHandler(Object handler) {
        for (Method method : handler.getClass().getMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                int parameterCount = method.getParameterCount();
                if (parameterCount != 1) {
                    logger.error("Method '{}' from class '{}' has incorrect parameter count of {}", method.getName(), handler.getClass().getSimpleName(), parameterCount);
                    throw new IllegalArgumentException("Handler " + handler.getClass() + " method " + method + " doesn't have 1 parameter");
                }
                Class<?> parameterType = method.getParameterTypes()[0];
                List<Entry> value = handlerMethods.getOrDefault(parameterType, new ArrayList<>());
                logger.info("Adding handler from class '{}' for type '{}'", handler.getClass().getSimpleName(), parameterType.getSimpleName());
                value.add(new Entry(handler, method));
                handlerMethods.put(parameterType, value);
            }
        }
    }

    @Override
    public boolean poll() {
        synchronized (lock) {
            if (events.size() > 0) {
                Object event = events.remove(0);
                List<Entry> handlers = handlerMethods.get(event.getClass());
                if (handlers == null) {
                    logger.warn("No handlers found for event type '{}'", event.getClass().getSimpleName());
                } else {
                    for (Entry handler : handlers) {
                        try {
                            handler.method.invoke(handler.object, event);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            logger.warn("Error processing class '{}' method '{}' for type '{}'",
                                    handler.object.getClass().getSimpleName(),
                                    handler.method.getName(),
                                    event.getClass().getSimpleName(),
                                    e);
                        }
                    }
                }
            }
            return events.size() > 0;
        }
    }
}
