package com.arrggh.mudworld.api.bus;

import java.lang.annotation.*;

/**
 * Annotation for the method that will be called by the event bus when a event of the
 * appropriate type is queued.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
}
