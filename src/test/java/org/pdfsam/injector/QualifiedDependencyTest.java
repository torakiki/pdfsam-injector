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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Inject;
import javax.inject.Qualifier;

import org.junit.Test;
import org.pdfsam.injector.Injector;
import org.pdfsam.injector.Key;
import org.pdfsam.injector.Provides;

public class QualifiedDependencyTest {
    @Test
    public void qualifiedInstances() {
        Injector injector = Injector.start(new Module());
        assertEquals(FooA.class, injector.instance(Key.of(Foo.class, A.class)).getClass());
        assertEquals(FooB.class, injector.instance(Key.of(Foo.class, B.class)).getClass());
    }

    @Test
    public void injectedQualified() {
        Injector injector = Injector.start(new Module());
        Dummy dummy = injector.instance(Dummy.class);
        assertEquals(FooB.class, dummy.foo.getClass());
    }

    interface Foo {

    }

    public static class FooA implements Foo {

    }

    public static class FooB implements Foo {

    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @interface A {

    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @interface B {

    }

    public static class Module {
        @Provides
        @A
        Foo a(FooA fooA) {
            return fooA;
        }

        @Provides
        @B
        Foo b(FooB fooB) {
            return fooB;
        }
    }

    public static class Dummy {
        private final Foo foo;

        @Inject
        public Dummy(@B Foo foo) {
            this.foo = foo;
        }
    }

}
