package com.arrggh.mudworld.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.EqualsVerifierApi;
import org.junit.jupiter.api.Test;

public abstract class AbstractModelTest<T> {
    @Test
    public void equalsAndHashcode() {
        EqualsVerifier.forClass(getClassUnderTest()).verify();
    }

    protected abstract Class<T> getClassUnderTest();
}
