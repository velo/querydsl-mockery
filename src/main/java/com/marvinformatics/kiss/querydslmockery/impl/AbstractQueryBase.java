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

import java.util.Collection;

import com.marvinformatics.kiss.querydslmockery.JPQLMockeryQuery;
import com.querydsl.core.QueryModifiers;
import com.querydsl.core.support.FetchableQueryBase;
import com.querydsl.core.support.QueryMixin;
import com.querydsl.core.types.CollectionExpression;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.MapExpression;
import com.querydsl.core.types.ParamExpression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Visitor;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.jpa.JPQLQuery;

/**
 *
 * This class hosts no real code, it is only here to make
 * {@link JPQLMockeryQuery} cleaner and more pleasant to read.
 *
 * As thumb rule shall not be changed!
 *
 * @author Marvin
 * @since 0.8
 */
public abstract class AbstractQueryBase<T, Q extends FetchableQueryBase<T, Q> & JPQLQuery<T>>
        extends FetchableQueryBase<T, Q>implements JPQLQuery<T> {

    private static final long serialVersionUID = 331427590935411125L;

    /**
     * <p>Constructor for AbstractQueryBase.</p>
     */
    public AbstractQueryBase(QueryMixin<Q> queryMixin) {
        super(queryMixin);
    }

    @Override
    public Q restrict(QueryModifiers modifiers) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <U> Q set(ParamExpression<U> param, U value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Q distinct() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public long fetchCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Q groupBy(Expression<?>... o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Q having(Predicate... o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression eq(Expression<? extends T> expr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression eq(T constant) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression ne(Expression<? extends T> expr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression ne(T constant) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression contains(Expression<? extends T> right) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression contains(T constant) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression exists() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression notExists() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression lt(Expression<? extends T> expr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression lt(T constant) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression gt(Expression<? extends T> expr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression gt(T constant) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression loe(Expression<? extends T> expr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression loe(T constant) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression goe(Expression<? extends T> expr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression goe(T constant) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanOperation isNull() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanOperation isNotNull() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression in(Collection<? extends T> right) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public BooleanExpression in(@SuppressWarnings("unchecked") T... right) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <R, C> R accept(Visitor<R, C> v, C context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<? extends T> getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Q from(EntityPath<?>... sources) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q from(CollectionExpression<?, P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q innerJoin(EntityPath<P> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q innerJoin(EntityPath<P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q innerJoin(CollectionExpression<?, P> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q innerJoin(CollectionExpression<?, P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q innerJoin(MapExpression<?, P> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q innerJoin(MapExpression<?, P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q join(EntityPath<P> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q join(EntityPath<P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q join(CollectionExpression<?, P> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q join(CollectionExpression<?, P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q join(MapExpression<?, P> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q join(MapExpression<?, P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q leftJoin(EntityPath<P> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q leftJoin(EntityPath<P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q leftJoin(CollectionExpression<?, P> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q leftJoin(CollectionExpression<?, P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q leftJoin(MapExpression<?, P> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q leftJoin(MapExpression<?, P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q rightJoin(EntityPath<P> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q rightJoin(EntityPath<P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q rightJoin(CollectionExpression<?, P> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q rightJoin(CollectionExpression<?, P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q rightJoin(MapExpression<?, P> target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public <P> Q rightJoin(MapExpression<?, P> target, Path<P> alias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Q on(Predicate... condition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Q fetchJoin() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Q fetchAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
