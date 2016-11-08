package org.pdfsam.injector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pdfsam.injector.Auto;
import org.pdfsam.injector.Injector;
import org.pdfsam.injector.Provides;

public class AutoProviderTest {

    @Test
    public void singleton() {
        Injector injector = Injector.start(new Config());
        assertEquals(injector.instance(SingletonObj.class), injector.instance(SingletonObj.class));
    }

    public static class Config {

        @Provides
        public SingletonObj sing() {
            return new SingletonObj();
        }
    }

    @Auto
    public static class SingletonObj {

    }
}
