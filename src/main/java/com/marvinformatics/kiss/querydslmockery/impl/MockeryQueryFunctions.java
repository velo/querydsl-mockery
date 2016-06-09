/**
 * Copyright (C) 2013 Marvin Herman Froeder (marvin@marvinformatics.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.marvinformatics.kiss.querydslmockery.impl;

import java.util.Arrays;
import java.util.Collection;

import com.querydsl.collections.CollQueryFunctions;

/**
 * <p>
 * MockeryQueryFunctions class.
 * </p>
 *
 * @author Marvin
 * @since 0.8
 */
public class MockeryQueryFunctions {

    /**
     * <p>
     * equals
     * </p>
     *
     * @since 0.8
     */
    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2)
            return true;

        if (o1 == null)
            return false;

        if (o2 == null)
            return false;

        return o1.equals(o2);
    }

    /**
     * <p>
     * toLowerCase
     * </p>
     */
    public static String toLowerCase(String text) {
        if (text == null)
            return null;

        return text.toLowerCase();
    }

    /**
     * <p>
     * like
     * </p>
     */
    public static boolean like(final String str, String like) {
        if (str == null)
            return false;

        return CollQueryFunctions.like(str, like);
    }

    /**
     * <p>
     * like
     * </p>
     */
    public static boolean like(String str, String like, char escape) {
        return like(str, like);
    }

    /**
     * <p>
     * in
     * </p>
     */
    public static <E> boolean in(Collection<E> col, E filter) {
        if (col.isEmpty())
            throw new IllegalArgumentException("IN [empty list doesnt work] ");
        if (filter == null)
            return true;

        return col.contains(filter);
    }

    public static <T> Collection<T> leftJoin(Collection<T> coll) {
        return CollQueryFunctions.leftJoin(coll);
    }

    public static <T> Collection<T> leftJoin(T coll) {
        return CollQueryFunctions.leftJoin(Arrays.asList(coll));
    }

}
