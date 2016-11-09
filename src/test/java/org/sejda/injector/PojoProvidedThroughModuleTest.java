package org.sejda.injector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.sejda.injector.InjectionException;
import org.sejda.injector.Injector;
import org.sejda.injector.Provides;

public class PojoProvidedThroughModuleTest {
    @Test(expected = InjectionException.class)
    public void pojoNotProvided() {
        Injector injector = Injector.start();
        injector.instance(Pojo.class);
    }

    @Test
    public void pojoProvided() {
        Injector injector = Injector.start(new Module());
        assertNotNull(injector.instance(Pojo.class));
    }

    @Test
    public void dependecyInjected() {
        Injector injector = Injector.start(new Module());
        assertEquals("foo", injector.instance(String.class));
    }

    public static class Module {
        @Provides
        Pojo pojo() {
            return new Pojo("foo");
        }

        @Provides
        String myString(Pojo pojo) {
            return pojo.foo;
        }
    }

    public static class Pojo {
        private final String foo;

        public Pojo(String foo) {
            this.foo = foo;
        }
    }
}
