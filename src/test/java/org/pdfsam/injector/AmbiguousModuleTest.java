package org.pdfsam.injector;

import org.junit.Test;
import org.pdfsam.injector.InjectionException;
import org.pdfsam.injector.Injector;
import org.pdfsam.injector.Provides;

public class AmbiguousModuleTest {
    @Test(expected = InjectionException.class)
    public void ambiguousModule() {
        Injector.start(new Module());
    }

    public static class Module {
        @Provides
        String foo() {
            return "foo";
        }

        @Provides
        String bar() {
            return "bar";
        }
    }
}
