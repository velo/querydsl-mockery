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

import com.querydsl.collections.CollQueryTemplates;
import com.querydsl.core.types.Ops;

/**
 * <p>MockeryQueryTemplates class.</p>
 *
 * @author Marvin
 * @since 0.8
 */
public class MockeryQueryTemplates extends CollQueryTemplates {

    /** Constant <code>DEFAULT</code> */
    public static final MockeryQueryTemplates DEFAULT = new MockeryQueryTemplates();

    protected MockeryQueryTemplates() {
        super();

        String functions = MockeryQueryFunctions.class.getName();
        add(Ops.EQ, functions + ".equals({0}, {1})");
        add(Ops.NE, "!" + functions + ".equals({0}, {1})");
        add(Ops.LOWER, functions + ".toLowerCase({0})");
        add(Ops.LIKE, functions + ".like({0},{1})");
        add(Ops.IN, functions + ".in({1},{0})");
    }

}
