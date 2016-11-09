package org.sejda.injector;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.sejda.injector.InjectionException;
import org.sejda.injector.Injector;
import org.sejda.injector.Key;
import org.sejda.injector.Provides;

public class NamedDependencyTest {
    @Test
    public void namedInstanceWithModule() {
        Injector injector = Injector.start(new HelloWorldModule());
        assertEquals("Hello!", injector.instance(Key.of(String.class, "hello")));
        assertEquals("Hi!", injector.instance(Key.of(String.class, "hi")));
        assertEquals("Hello!", injector.instance(Bean.class).s);
    }

    @Test(expected = InjectionException.class)
    public void failingName() {
        Injector injector = Injector.start(new HelloWorldModule());
        injector.instance(Key.of(String.class, "ChuckNorris"));
    }

    public static class HelloWorldModule {
        @Provides
        @Named("hello")
        String hello() {
            return "Hello!";
        }

        @Provides
        @Named("hi")
        String hi() {
            return "Hi!";
        }
    }

    public static class Bean {
        private final String s;

        @Inject
        public Bean(@Named("hello") String s) {
            this.s = s;
        }
    }

}
