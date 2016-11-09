package org.sejda.injector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sejda.injector.InjectionException;
import org.sejda.injector.Injector;
import org.sejda.injector.Provides;

public class InjectorTest {
    @Test(expected = InjectionException.class)
    public void classAsConfig() {
        Injector.addConfig(String.class);
        Injector.start();
    }

    @Test(expected = InjectionException.class)
    public void closed() {
        Injector.addConfig(new Config());
        Injector injector = Injector.start();
        assertEquals("ChuckNorris", injector.instance(Obj.class).val);
        injector.close();
        injector.instance(Obj.class);
    }

    public class Config {
        @Provides
        public Obj obj() {
            return new Obj("ChuckNorris");
        }
    }

    private static class Obj {
        final String val;

        Obj(String val) {
            this.val = val;
        }
    }
}
