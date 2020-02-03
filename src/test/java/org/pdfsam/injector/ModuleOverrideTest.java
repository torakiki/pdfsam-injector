/*
 * This file is part of the PDF Split And Merge source code
 * Copyright 2020 by Sober Lemur S.a.s di Vacondio Andrea (info@pdfsam.org).
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
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
