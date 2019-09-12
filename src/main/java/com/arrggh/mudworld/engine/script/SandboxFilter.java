package com.arrggh.mudworld.engine.script;

import groovy.lang.Closure;
import groovy.lang.Script;
import org.kohsuke.groovy.sandbox.GroovyValueFilter;

import java.util.Arrays;
import java.util.List;

public class SandboxFilter extends GroovyValueFilter {
    @Override
    public Object filter(Object o) {
        if (o == null || ALLOWED_TYPES.contains(o.getClass())) {
            return o;
        }

        if (o instanceof Script || o instanceof Closure) {
            return o; // access to properties of compiled groovy script
        }

        throw new SecurityException("Script tried to access banned class: " + o.getClass());
    }

    // I'm not adding Class, which rules out all the static method calls
    private static final List<Class<?>> ALLOWED_TYPES = Arrays.asList(String.class,
            Integer.class,
            Boolean.class,
            Float.class,
            Void.class);
}
