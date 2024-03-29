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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class InjectorTest {
    @Test
    public void classAsConfig() {
        assertThrows(InjectionException.class, () -> {
            Injector.addConfig(String.class);
            Injector.start();
        });
    }

    @Test
    public void closed() {
        assertThrows(InjectionException.class, () -> {
            Injector.addConfig(new Config());
            Injector injector = Injector.start();
            assertEquals("ChuckNorris", injector.instance(Obj.class).val);
            injector.close();
            injector.instance(Obj.class);
        });
    }

    public class Config {
        @Provides
        public Obj obj() {
            return new Obj("ChuckNorris");
        }
    }

    private static class Obj {
        final String val;

        Obj(String val) {
            this.val = val;
        }
    }
}
