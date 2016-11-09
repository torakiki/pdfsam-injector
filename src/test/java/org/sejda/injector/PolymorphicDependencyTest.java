package org.sejda.injector;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.sejda.injector.Injector;
import org.sejda.injector.Key;
import org.sejda.injector.Provides;

public class PolymorphicDependencyTest {
    @Test
    public void multipleImplementations() {
        Injector injector = Injector.start(new Module());
        assertEquals(FooA.class, injector.instance(Key.of(Foo.class, "A")).getClass());
        assertEquals(FooB.class, injector.instance(Key.of(Foo.class, "B")).getClass());
    }

    public static class Module {
        @Provides @Named("A")
        Foo a(FooA fooA) {
            return fooA;
        }

        @Provides @Named("B")
        Foo a(FooB fooB) {
            return fooB;
        }
    }

    interface Foo {

    }

    public static class FooA implements Foo {
        @Inject
        public FooA() {
        }
    }

    public static class FooB implements Foo {
        @Inject
        public FooB() {
        }

    }
}
