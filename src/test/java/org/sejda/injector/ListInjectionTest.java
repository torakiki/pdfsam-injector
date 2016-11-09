package org.sejda.injector;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.sejda.injector.InjectionException;
import org.sejda.injector.Injector;
import org.sejda.injector.Key;
import org.sejda.injector.Provides;

public class ListInjectionTest {
    @Test
    public void listOfInjected() {
        Injector injector = Injector.start(new Config());
        assertEquals(2, injector.instance(A.class).list.size());
    }

    @Test
    public void instancesOfType() {
        Injector injector = Injector.start(new Config());
        assertEquals(2, injector.instancesOfType(Base.class).size());
    }

    @Test(expected = InjectionException.class)
    public void invalidType() {
        Injector injector = Injector.start(new Config());
        injector.instance(B.class).list.size();
    }

    @Test
    public void qualifiedListOfInjected() {
        Injector injector = Injector.start(new Config());
        assertEquals(4, injector.instance(Key.of(List.class, "aList")).size());
    }

    public static class A {
        private final List<Base> list;

        @Inject
        public A(List<Base> list) {
            this.list = list;
        }
    }

    public static class B {
        private final List<? extends Base> list;

        @Inject
        public B(List<? extends Base> list) {
            this.list = list;
        }
    }

    public static class Config {

        @Provides
        Sub1 sub1() {
            return new Sub1();
        }

        @Provides
        Sub2 sub2() {
            return new Sub2();
        }

        @Provides
        @Named("aList")
        List<String> qualifiedList() {
            return Arrays.asList("a", "b", "c", "d");
        }
    }

    public static class Base {

    }

    public static class Sub1 extends Base {

    }

    public static class Sub2 extends Base {

    }
}
