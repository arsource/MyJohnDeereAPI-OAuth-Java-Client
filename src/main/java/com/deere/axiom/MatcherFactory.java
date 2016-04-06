package com.deere.axiom;


import org.hamcrest.Matcher;
import org.hamcrest.core.IsCollectionContaining;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
//import org.mockito.internal.matchers.Contains;

import java.lang.annotation.Annotation;
import java.util.Collection;

//import static com.deere.axiom.IsAnIterableInWhichItemsAppearInOrder.IsAnIterableInWhichItemsAppearInOrderBuilder;

public class MatcherFactory {
    private MatcherFactory() { }

    public static <T> IsEqual<T> isEqualTo(final T obj) {
        return new IsEqual<T>(obj);
    }

    public static IsInstanceOf isInstanceOf(final Class<?> clazz) {
        return new IsInstanceOf(clazz);
    }

    public static <T> IsNull<T> isNull() {
        return new IsNull<T>();
    }

    public static <T> IsNot<T> isNotNull() {
        return new IsNot<T>(new IsNull<T>());
    }

    public static <T> IsSame<T> isSameObjectAs(final T obj) {
        return new IsSame<T>(obj);
    }

    public static <T> IsCollectionContaining<T> isACollectionThatContains(final T value) {
        return new IsCollectionContaining<T>(new IsEqual<T>(value));
    }

    public static <T> IsCollectionContaining<T> isACollectionThatContainsSomethingThat(final Matcher<T> matcher) {
        return new IsCollectionContaining<T>(matcher);
    }

    public static IsEqual<Boolean> isTrue() {
        return new IsEqual<Boolean>(true);
    }

    public static <T> IsNot<T> isNot(final Matcher<T> matcher) {
        return new IsNot<T>(matcher);
    }

    public static IsEqual<Boolean> isFalse() {
        return new IsEqual<Boolean>(false);
    }

   /* public static Contains containsString(final String string) {
        return new Contains(string);
    }*/

    /*public static Matcher<Long> isLessThan(final Long aLong) {
        return new IsLessThan(aLong);
    }*/

    /*public static <T> Matcher<? extends Collection<? extends T>> isCollectionThatContainsType(final Class<T> clazz) {
        return new IsCollectionThatContainsType<T>(clazz);
    }

    public static <T> ContainsInItsModelSomethingThat<T> containsInItsModelSomethingThat(final Matcher<T> matcher) {
        return new ContainsInItsModelSomethingThat<T>(matcher);
    }

    public static <T> Matcher<Collection<? extends T>> isACollectionOfSize(final int size) {
        return new IsCollectionOfSize<T>(size);
    }

    public static HasAPropertyThat.HasAPropertyThatBuilder hasAPropertyNamed(final String fieldName) {
        return new HasAPropertyThat.HasAPropertyThatBuilder(fieldName);
    }*/

    /*public static IsAssignableTo isAssignableTo(final Class<?> clazz) {
        return new IsAssignableTo(clazz);
    }

    public static IsAnnotatedWith isAnnotatedWith(final Class<? extends Annotation> annotationType) {
        return new IsAnnotatedWith(annotationType);
    }

    public static <T> IsAnIterableInWhichItemsAppearInOrderBuilder<T>
    isAnIterableInWhichSomethingThat(final Matcher<T> firstItem) {
        return new IsAnIterableInWhichItemsAppearInOrderBuilder<T>(firstItem);
    }*/
}
