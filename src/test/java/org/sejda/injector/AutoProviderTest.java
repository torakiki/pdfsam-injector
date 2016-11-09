package org.sejda.injector;

import static org.mockito.Mockito.verify;

import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.sejda.injector.Auto;
import org.sejda.injector.Components;
import org.sejda.injector.Injector;
import org.sejda.injector.Provides;

public class AutoProviderTest {
    private static Consumer<String> HIT;

    @Before
    public void setUp() {
        HIT = Mockito.mock(Consumer.class);
    }

    @Test
    public void autoCreatedAnnotatedClass() {
        Injector injector = Injector.start(new Config());
        verify(HIT).accept("hit");
    }

    @Test
    public void autoCreatedAnnotatedConfig() {
        Injector injector = Injector.start(new ConfigComponent());
        verify(HIT).accept("hit");
    }

    public class Config {

        @Provides
        public AnnotatedClass sing() {
            return new AnnotatedClass();
        }
    }

    @Components({ AnnotatedClass.class })
    public class ConfigComponent {

    }

    @Auto
    public static class AnnotatedClass {
        AnnotatedClass() {
            HIT.accept("hit");
        }
    }
}
