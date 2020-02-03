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

import static org.mockito.Mockito.verify;

import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.pdfsam.injector.Auto;
import org.pdfsam.injector.Components;
import org.pdfsam.injector.Injector;
import org.pdfsam.injector.Provides;

public class AutoProviderTest {
    private static Consumer<String> HIT;

    @Before
    public void setUp() {
        HIT = Mockito.mock(Consumer.class);
    }

    @Test
    public void autoCreatedAnnotatedClass() {
        Injector injector = Injector.start(new Config());
        verify(HIT).accept("hit");
    }

    @Test
    public void autoCreatedAnnotatedConfig() {
        Injector injector = Injector.start(new ConfigComponent());
        verify(HIT).accept("hit");
    }

    public class Config {

        @Provides
        public AnnotatedClass sing() {
            return new AnnotatedClass();
        }
    }

    @Components({ AnnotatedClass.class })
    public class ConfigComponent {

    }

    @Auto
    public static class AnnotatedClass {
        AnnotatedClass() {
            HIT.accept("hit");
        }
    }
}
