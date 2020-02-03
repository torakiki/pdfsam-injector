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

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.pdfsam.injector.Injector;
import org.pdfsam.injector.Key;
import org.pdfsam.injector.Provides;

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
