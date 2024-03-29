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

import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DependencyTest {
    @Test
    public void dependencyInstance() {
        try (Injector injector = Injector.start()) {
            assertNotNull(injector.instance(Plain.class));
        }
    }

    @Test
    public void provider() {
        try (Injector injector = Injector.start()) {
            Provider<Plain> plainProvider = injector.provider(Plain.class);
            assertNotNull(plainProvider.get());
        }
    }

    @Test
    public void unknown() {
        assertThrows(InjectionException.class, () -> {
            try (Injector injector = Injector.start()) {
                injector.instance(Unknown.class);
            }
        });
    }

    public static class Plain {

    }

    public static class Unknown {
        public Unknown(String noSuitableConstructor) {

        }
    }
}
