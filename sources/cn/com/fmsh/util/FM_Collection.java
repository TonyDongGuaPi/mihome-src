package cn.com.fmsh.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FM_Collection<T, E> {
    private Map<T, E> data = new HashMap();

    public void put(T t, E e) {
        this.data.put(t, e);
    }

    public E get(T t) {
        return this.data.get(t);
    }

    public Iterator<T> iterator() {
        return this.data.keySet().iterator();
    }
}
