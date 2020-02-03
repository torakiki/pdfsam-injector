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
import org.pdfsam.injector.InjectionException;
import org.pdfsam.injector.Injector;
import org.pdfsam.injector.Key;
import org.pdfsam.injector.Provides;

public class NamedDependencyTest {
    @Test
    public void namedInstanceWithModule() {
        Injector injector = Injector.start(new HelloWorldModule());
        assertEquals("Hello!", injector.instance(Key.of(String.class, "hello")));
        assertEquals("Hi!", injector.instance(Key.of(String.class, "hi")));
        assertEquals("Hello!", injector.instance(Bean.class).s);
    }

    @Test(expected = InjectionException.class)
    public void failingName() {
        Injector injector = Injector.start(new HelloWorldModule());
        injector.instance(Key.of(String.class, "ChuckNorris"));
    }

    public static class HelloWorldModule {
        @Provides
        @Named("hello")
        String hello() {
            return "Hello!";
        }

        @Provides
        @Named("hi")
        String hi() {
            return "Hi!";
        }
    }

    public static class Bean {
        private final String s;

        @Inject
        public Bean(@Named("hello") String s) {
            this.s = s;
        }
    }

}
