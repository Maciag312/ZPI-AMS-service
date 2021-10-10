package com.zpi.infrastructure.common;

import org.apache.commons.lang3.tuple.MutablePair;

public class CompositeKey<T, U> extends MutablePair<T, U> {
    /**
     * Create a new pair instance.
     *
     * @param left  the left value, may be null
     * @param right the right value, may be null
     */
    public CompositeKey(T left, U right) {
        super(left, right);
    }
}
