package com.deere.axiom;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsCollectionContaining;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;

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
}
