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

import java.lang.annotation.Annotation;
import java.util.Objects;

import jakarta.inject.Named;

public class Key<T> {
    final Class<T> type;
    final Class<? extends Annotation> qualifier;
    final String name;

    private Key(Class<T> type, Class<? extends Annotation> qualifier, String name) {
        this.type = type;
        this.qualifier = qualifier;
        this.name = name;
    }

    /**
     * @return Key for a given type
     */
    public static <T> Key<T> of(Class<T> type) {
        return new Key<>(type, null, null);
    }

    /**
     * @return Key for a given type and qualifier annotation type
     */
    public static <T> Key<T> of(Class<T> type, Class<? extends Annotation> qualifier) {
        return new Key<>(type, qualifier, null);
    }

    /**
     * @return Key for a given type and name (@Named value)
     */
    public static <T> Key<T> of(Class<T> type, String name) {
        return new Key<>(type, Named.class, name);
    }

    static <T> Key<T> of(Class<T> type, Annotation qualifier) {
        if (qualifier == null) {
            return Key.of(type);
        }
        return qualifier.annotationType().equals(Named.class) ? Key.of(type, ((Named) qualifier).value())
                : Key.of(type, qualifier.annotationType());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Key<?> key = (Key<?>) o;

        if (!type.equals(key.type))
            return false;
        if (!Objects.equals(qualifier, key.qualifier))
            return false;
        return Objects.equals(name, key.name);

    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (qualifier != null ? qualifier.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String suffix = name != null ? "@\"" + name + "\"" : qualifier != null ? "@" + qualifier.getSimpleName() : "";
        return type.getName() + suffix;
    }

}