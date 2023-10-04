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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransitiveDependencyTest {
    @Test
    public void transitive() {
        Injector injector = Injector.start();
        A a = injector.instance(A.class);
        assertNotNull(a.b.c);
    }

    public static class A {
        private final B b;

        @Inject
        public A(B b) {
            this.b = b;
        }
    }

    public static class B {
        private final C c;

        @Inject
        public B(C c) {
            this.c = c;
        }
    }

    public static class C {

    }

}
