package org.sejda.injector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import javax.inject.Provider;

import org.junit.Test;
import org.sejda.injector.Injector;
import org.sejda.injector.Prototype;
import org.sejda.injector.Provides;

public class SingletonTest {
    @Test
    public void nonSingleton() {
        Injector injector = Injector.start();
        assertNotEquals(injector.instance(ProtoPlain.class), injector.instance(ProtoPlain.class));
    }

    @Test
    public void nonSingletonConfig() {
        Injector injector = Injector.start(new Config());
        assertNotEquals(injector.instance(Plain.class), injector.instance(Plain.class));
    }

    @Test
    public void singletonByDefault() {
        Injector injector = Injector.start();
        assertEquals(injector.instance(Plain.class), injector.instance(Plain.class));
    }

    @Test
    public void singletonThroughProvider() {
        Injector injector = Injector.start();
        Provider<Plain> provider = injector.provider(Plain.class);
        assertEquals(provider.get(), provider.get());
    }

    @Prototype
    public static class ProtoPlain {

    }

    public static class Plain {

    }


    public class Config {
        @Provides
        @Prototype
        Plain plain() {
            return new Plain();
        }
    }
}
