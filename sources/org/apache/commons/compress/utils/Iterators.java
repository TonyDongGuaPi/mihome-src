package org.apache.commons.compress.utils;

import java.util.Collection;
import java.util.Iterator;

public class Iterators {
    public static <T> boolean a(Collection<T> collection, Iterator<? extends T> it) {
        collection.getClass();
        it.getClass();
        boolean z = false;
        while (it.hasNext()) {
            z |= collection.add(it.next());
        }
        return z;
    }

    private Iterators() {
    }
}
