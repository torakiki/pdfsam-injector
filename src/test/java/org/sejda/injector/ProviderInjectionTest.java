package org.sejda.injector;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;
import javax.inject.Provider;

import org.junit.Test;
import org.sejda.injector.Injector;

public class ProviderInjectionTest {
    @Test
    public void providerInjected() {
        Injector injector = Injector.start();
        assertNotNull(injector.instance(A.class).plainProvider.get());
    }

    public static class A {
        private final Provider<B> plainProvider;

        @Inject
        public A(Provider<B> plainProvider) {
            this.plainProvider = plainProvider;
        }
    }

    public static class B {

    }
}
