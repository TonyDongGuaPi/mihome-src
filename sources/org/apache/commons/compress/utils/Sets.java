package org.apache.commons.compress.utils;

import java.util.Collections;
import java.util.HashSet;

public class Sets {
    private Sets() {
    }

    public static <E> HashSet<E> a(E... eArr) {
        HashSet<E> hashSet = new HashSet<>(eArr.length);
        Collections.addAll(hashSet, eArr);
        return hashSet;
    }
}
