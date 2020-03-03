package org.apache.commons.compress.utils;

import java.util.ArrayList;
import java.util.Iterator;

public class Lists {
    public static <E> ArrayList<E> a() {
        return new ArrayList<>();
    }

    public static <E> ArrayList<E> a(Iterator<? extends E> it) {
        ArrayList<E> a2 = a();
        Iterators.a(a2, it);
        return a2;
    }

    private Lists() {
    }
}
