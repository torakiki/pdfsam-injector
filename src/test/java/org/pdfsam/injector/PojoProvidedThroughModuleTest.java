package org.pdfsam.injector;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.pdfsam.injector.InjectionException;
import org.pdfsam.injector.Injector;
import org.pdfsam.injector.Provides;

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

    public static class Module {
        @Provides
        Pojo pojo() {
            return new Pojo("foo");
        }
    }

    public static class Pojo {
        private final String foo;

        public Pojo(String foo) {
            this.foo = foo;
        }
    }
}
