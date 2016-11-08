package org.pdfsam.injector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.pdfsam.injector.Injector;
import org.pdfsam.injector.Provides;

public class ModuleOverrideTest {
    @Test
    public void dependencyOverridenByModule() {
        Injector injector = Injector.start(new PlainStubOverrideModule());
        assertEquals(PlainStub.class, injector.instance(Plain.class).getClass());
    }


    @Test
    public void moduleOverwrittenBySubClass() {
        assertEquals("foo", Injector.start(new FooModule()).instance(String.class));
        assertEquals("bar", Injector.start(new FooOverrideModule()).instance(String.class));
    }

    public static class Plain {
    }

    public static class PlainStub extends Plain {

    }

    public static class PlainStubOverrideModule {
        @Provides
        public Plain plain(PlainStub plainStub) {
            return plainStub;
        }

    }

    public static class FooModule {
        @Provides
        String foo() {
            return "foo";
        }
    }

    public static class FooOverrideModule extends FooModule {
        @Provides
        @Override
        String foo() {
            return "bar";
        }
    }




}
