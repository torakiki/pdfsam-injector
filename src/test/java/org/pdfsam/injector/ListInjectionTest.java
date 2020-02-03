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

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.pdfsam.injector.InjectionException;
import org.pdfsam.injector.Injector;
import org.pdfsam.injector.Key;
import org.pdfsam.injector.Provides;

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
