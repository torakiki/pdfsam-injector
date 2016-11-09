package org.sejda.injector;

import static org.junit.Assert.assertNotNull;

import javax.inject.Provider;

import org.junit.Test;
import org.sejda.injector.InjectionException;
import org.sejda.injector.Injector;

public class DependencyTest {
    @Test
    public void dependencyInstance() {
        Injector injector = Injector.start();
        assertNotNull(injector.instance(Plain.class));
    }

    @Test
    public void provider() {
        Injector injector = Injector.start();
        Provider<Plain> plainProvider = injector.provider(Plain.class);
        assertNotNull(plainProvider.get());
    }

    @Test(expected = InjectionException.class)
    public void unknown() {
        Injector injector = Injector.start();
        injector.instance(Unknown.class);
    }

    public static class Plain {

    }

    public static class Unknown {
        public Unknown(String noSuitableConstructor) {

        }
    }
}


