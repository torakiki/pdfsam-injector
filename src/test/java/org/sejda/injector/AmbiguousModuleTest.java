package org.sejda.injector;

import org.junit.Test;
import org.sejda.injector.InjectionException;
import org.sejda.injector.Injector;
import org.sejda.injector.Provides;

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
