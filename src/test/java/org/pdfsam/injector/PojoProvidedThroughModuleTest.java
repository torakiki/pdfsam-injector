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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PojoProvidedThroughModuleTest {
    @Test
    public void pojoNotProvided() {
        assertThrows(InjectionException.class, () -> {
            try (Injector injector = Injector.start()) {
                injector.instance(Pojo.class);
            }
        });
    }

    @Test
    public void pojoProvided() {
        try (Injector injector = Injector.start(new Module())) {
            assertNotNull(injector.instance(Pojo.class));
        }
    }

    @Test
    public void dependecyInjected() {
        try (Injector injector = Injector.start(new Module())) {
            assertEquals("foo", injector.instance(String.class));
        }
    }

    public static class Module {
        @Provides
        Pojo pojo() {
            return new Pojo("foo");
        }

        @Provides
        String myString(Pojo pojo) {
            return pojo.foo;
        }
    }

    public static class Pojo {
        private final String foo;

        public Pojo(String foo) {
            this.foo = foo;
        }
    }
}
