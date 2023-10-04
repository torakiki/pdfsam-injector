/*
 * This file is part of the PDF Split And Merge source code
 * Copyright 2020 by Sober Lemur S.r.l. (info@pdfsam.org).
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

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NamedDependencyTest {
    @Test
    public void namedInstanceWithModule() {
        try (Injector injector = Injector.start(new HelloWorldModule())) {
            assertEquals("Hello!", injector.instance(Key.of(String.class, "hello")));
            assertEquals("Hi!", injector.instance(Key.of(String.class, "hi")));
            assertEquals("Hello!", injector.instance(Bean.class).s);
        }
    }

    @Test
    public void failingName() {
        assertThrows(InjectionException.class, () -> {
            try (Injector injector = Injector.start(new HelloWorldModule())) {
                injector.instance(Key.of(String.class, "ChuckNorris"));
            }
        });
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
