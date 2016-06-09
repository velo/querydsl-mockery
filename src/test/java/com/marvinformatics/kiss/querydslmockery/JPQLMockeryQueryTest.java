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
package com.marvinformatics.kiss.querydslmockery;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.h2.Driver;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.marvinformatics.kiss.querydslmockery.entity.Address;
import com.marvinformatics.kiss.querydslmockery.entity.Person;
import com.marvinformatics.kiss.querydslmockery.entity.QAddress;
import com.marvinformatics.kiss.querydslmockery.entity.QPerson;
import com.querydsl.core.NonUniqueResultException;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

public class JPQLMockeryQueryTest {

    static {
        Driver.class.getName();
    }

    protected static EntityManager em;

    protected static EntityManagerFactory factory;

    protected static final Address a1 = new Address("42", "10 5th avenue", "city");
    protected static final Person p1 = new Person("1234", "Juka");
    protected static final Person p2 = new Person("2345", "Marko", a1, p1);
    protected static final Person p3 = new Person("3456", "Pedro", p1);

    protected static final Person p4 = new Person("4567", "Barca", p2, p3);
    protected static List<Person> people;

    protected static ArrayList<Address> addresses;

    @BeforeClass
    public static void checkClasses() {
        Person.class.getClass();
        QPerson.class.getClass();
    }

    @BeforeClass
    public static void goOn() {
        factory = Persistence.createEntityManagerFactory("h2");
        em = factory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(a1);
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        t.commit();

        people = new ArrayList<Person>(Arrays.asList(p1, p2, p3, p4));
        addresses = new ArrayList<Address>(Arrays.asList(a1));
    }

    @AfterClass
    public static void tearAppart() {
        em.close();
        factory.close();
        people.clear();
    }

    QPerson p = QPerson.person;
    QAddress a = QAddress.address;

    protected <E, X> void execute(Mockery<E, X> mockeryParameters) {
        JPAQuery<X> regularQuery = new JPAQuery<>(em);
        E regularQueryResult = mockeryParameters.runQuery(regularQuery);
        mockeryParameters.matchResult((E) regularQueryResult);

        JPQLMockeryQuery<X> mockedQuery = createJPQLMockeryQuery();
        mockedQuery.bind(p, people);
        mockedQuery.bind(a, addresses);
        E mockedQueryResult = mockeryParameters.runQuery(mockedQuery);
        mockeryParameters.matchResult(mockedQueryResult);

        if (mockedQueryResult instanceof QueryResults)
            assertQueryResultEqual((QueryResults<?>) regularQueryResult, (QueryResults<?>) mockedQueryResult);
        else
            assertThat(regularQueryResult, equalTo(mockedQueryResult));
    }

    private void assertQueryResultEqual(QueryResults<?> left, QueryResults<?> right) {
        Assert.assertEquals(left.getLimit(), right.getLimit());
        Assert.assertEquals(left.getOffset(), right.getOffset());
        Assert.assertEquals(left.getTotal(), right.getTotal());
        Assert.assertEquals(left.getResults(), right.getResults());
    }

    protected <X> JPQLMockeryQuery<X> createJPQLMockeryQuery() {
        return new JPQLMockeryQuery<X>();
    }

    @Test
    public void count() {
        execute(new Mockery<Long, Person>() {
            @Override
            public Long runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p)
                        .from(p)
                        .where(p.child.size().eq(2))
                        .fetchCount();
            }

            @Override
            public void matchResult(Long result) {
                assertThat(result, equalTo(1L));
            }
        });
    }

    @Test
    public void from() {
        execute(new Mockery<List<Person>, Person>() {
            @Override
            public List<Person> runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p)
                        .from(p)
                        .fetch();
            }

            @Override
            public void matchResult(List<Person> result) {
                assertThat(result, hasSize(4));
            }
        });
    }

    @Test
    public void join() {
        execute(new Mockery<List<Person>, Person>() {
            QPerson c = new QPerson("child");

            @Override
            public List<Person> runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p)
                        .from(p)
                        .join(p.child, c)
                        .where(c.name.eq("Juka"))
                        .fetch();
            }

            @Override
            public void matchResult(List<Person> result) {
                assertThat(result, hasSize(2));
            }
        });
    }

    @Test
    public void innerJoin() {
        execute(new Mockery<List<Person>, Person>() {
            QPerson c = new QPerson("child");

            @Override
            public List<Person> runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p)
                        .from(p)
                        .innerJoin(p.child, c)
                        .where(c.name.eq("Juka"))
                        .fetch();
            }

            @Override
            public void matchResult(List<Person> result) {
                assertThat(result, hasSize(2));
            }
        });
    }

    @Test
    public void noMatchCondition() {
        execute(new Mockery<List<Person>, Person>() {
            @Override
            public List<Person> runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p)
                        .from(p)
                        .where(p.name.eq("bananas"))
                        .fetch();
            }

            @Override
            public void matchResult(List<Person> result) {
                assertThat(result, hasSize(0));
            }
        });
    }

    @Test
    public void notExists() {
        execute(new Mockery<Boolean, Person>() {
            @Override
            public Boolean runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p)
                        .from(p)
                        .where(p.age.eq(1L))
                        .fetchFirst() == null;
            }

            @Override
            public void matchResult(Boolean result) {
                assertThat(result, equalTo(true));
            }
        });
    }

    @Test
    public void orderBy() {
        execute(new Mockery<List<String>, Person>() {
            @Override
            public List<String> runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p.id)
                        .from(p)
                        .orderBy(p.name.asc())
                        .fetch();
            }

            @Override
            public void matchResult(List<String> result) {
                assertThat(result, hasSize(4));
            }
        });
    }

    @Test
    public void singleResult() {
        execute(new Mockery<String, Person>() {
            @Override
            public String runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p.name)
                        .from(p)
                        .where(p.id.eq("3456"))
                        .fetchFirst();
            }

            @Override
            public void matchResult(String result) {
                assertThat(result, equalTo("Pedro"));
            }
        });
    }

    @Test
    public void fetchResults() {
        execute(new Mockery<QueryResults<String>, Person>() {
            @Override
            public QueryResults<String> runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p.name)
                        .from(p)
                        .offset(2)
                        .limit(1)
                        .fetchResults();
            }

            @Override
            public void matchResult(QueryResults<String> result) {
                assertThat(result.getTotal(), equalTo(4L));
                assertThat(result.getLimit(), equalTo(1L));
                assertThat(result.getOffset(), equalTo(2L));
            }
        });
    }

    @Test
    public void fetchNoResults() {
        execute(new Mockery<QueryResults<String>, Person>() {
            @Override
            public QueryResults<String> runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p.name)
                        .from(p)
                        .where(p.name.eq("name is not found"))
                        .fetchResults();
            }

            @Override
            public void matchResult(QueryResults<String> result) {
                assertThat(result.getTotal(), equalTo(0L));
                assertThat(result.getOffset(), equalTo(0L));
            }
        });
    }

    @Test(expected = NonUniqueResultException.class)
    public void singleResultNonUniqueResult() {
        execute(new Mockery<String, Person>() {
            @Override
            public String runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p.name)
                        .from(p)
                        .fetchOne();
            }

            @Override
            public void matchResult(String result) {
                assertThat(result, equalTo("Juka"));
            }
        });
    }

    @Test
    public void singleResultNoResult() {
        execute(new Mockery<Person, Person>() {
            @Override
            public Person runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p)
                        .from(p)
                        .where(p.id.eq("42L"))
                        .fetchOne();
            }

            @Override
            public void matchResult(Person result) {
                assertThat(result, Matchers.nullValue());
            }
        });
    }

    @Test(expected = NonUniqueResultException.class)
    public void uniqueResultNonUniqueResult() {
        JPQLMockeryQuery<Person> mq = new JPQLMockeryQuery<>();
        mq.bind(p, people);
        mq.select(p).from(p).fetchOne();
    }

    @Test
    public void where() {
        execute(new Mockery<List<Person>, Person>() {
            @Override
            public List<Person> runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p)
                        .from(p)
                        .where(p.name.like("%a%"))
                        .fetch();
            }

            @Override
            public void matchResult(List<Person> result) {
                assertThat(result, hasSize(3));
            }
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void inEmptyListShouldFail() throws Exception {
        execute(new Mockery<List<Person>, Person>() {

            @Override
            public List<Person> runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p)
                        .from(p)
                        .where(p.id.in(new ArrayList<String>()))
                        .fetch();
            }

            @Override
            public void matchResult(List<Person> result) {
            }
        });
    }

    @Test
    public void leftJoin() {
        final QAddress otherA = new QAddress("another_A");

        execute(new Mockery<List<Person>, Person>() {
            @Override
            public List<Person> runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p)
                        .from(p)
                        .leftJoin(p.address, otherA)
                        .orderBy(p.id.asc())
                        .fetch();
            }

            @Override
            public void matchResult(List<Person> result) {
                assertThat(result, hasSize(4));
                assertThat(result.get(1).getAddress(), Matchers.notNullValue());
                assertThat(result.get(1).getAddress(), equalTo(a1));
            }
        });
    }

    @Test
    public void singleResultTuple() {
        execute(new Mockery<Tuple, Person>() {
            @Override
            public Tuple runQuery(JPQLQuery<Person> query) {
                return query
                        .select(p.name, p.child.size())
                        .from(p)
                        .where(p.id.eq("3456"))
                        .fetchOne();
            }

            @Override
            public void matchResult(Tuple result) {
                assertThat(result, notNullValue());
                assertThat(result.get(p.name), equalTo("Pedro"));
                assertThat(result.get(p.child.size()), equalTo(1));
            }
        });
    }

}
