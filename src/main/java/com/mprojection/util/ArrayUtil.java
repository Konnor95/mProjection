package com.mprojection.util;

import java.util.Arrays;

public final class ArrayUtil {

    /**
     * Merges two arrays.
     * @param first first array
     * @param second second array
     * @param <T> array type
     * @return merged values as array
     */
    public static <T> T[] merge(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }


}
