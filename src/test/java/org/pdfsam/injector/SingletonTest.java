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
import static org.junit.Assert.assertNotEquals;

import javax.inject.Provider;

import org.junit.Test;
import org.pdfsam.injector.Injector;
import org.pdfsam.injector.Prototype;
import org.pdfsam.injector.Provides;

public class SingletonTest {
    @Test
    public void nonSingleton() {
        Injector injector = Injector.start();
        assertNotEquals(injector.instance(ProtoPlain.class), injector.instance(ProtoPlain.class));
    }

    @Test
    public void nonSingletonConfig() {
        Injector injector = Injector.start(new Config());
        assertNotEquals(injector.instance(Plain.class), injector.instance(Plain.class));
    }

    @Test
    public void singletonByDefault() {
        Injector injector = Injector.start();
        assertEquals(injector.instance(Plain.class), injector.instance(Plain.class));
    }

    @Test
    public void singletonThroughProvider() {
        Injector injector = Injector.start();
        Provider<Plain> provider = injector.provider(Plain.class);
        assertEquals(provider.get(), provider.get());
    }

    @Prototype
    public static class ProtoPlain {

    }

    public static class Plain {

    }


    public class Config {
        @Provides
        @Prototype
        Plain plain() {
            return new Plain();
        }
    }
}
