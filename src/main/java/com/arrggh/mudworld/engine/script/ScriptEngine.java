package com.arrggh.mudworld.engine.script;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.kohsuke.groovy.sandbox.SandboxTransformer;

public class ScriptEngine {
    public void executeScript() {
        CompilerConfiguration cc = new CompilerConfiguration();
        cc.addCompilationCustomizers(new SandboxTransformer());
        Binding binding = new Binding();
        GroovyShell sh = new GroovyShell(binding, cc);

        SandboxFilter filter = new SandboxFilter();
        filter.register();

        sh.evaluate("");
    }

    public static void main(String[] args) {
        new ScriptEngine().executeScript();
    }
}
