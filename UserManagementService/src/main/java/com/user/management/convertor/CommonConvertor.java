package com.user.management.convertor;

/**
 * Common convertor for
 * @param <T1>
 * @param <T2>
 */
public interface CommonConvertor<T1, T2> {
    T1 convert(T2 t2);
    T2 convertBack(T1 t1);
}
