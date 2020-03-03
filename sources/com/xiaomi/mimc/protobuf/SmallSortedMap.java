package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.FieldSet;
import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class SmallSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private final int f11342a;
    /* access modifiers changed from: private */
    public List<SmallSortedMap<K, V>.Entry> b;
    /* access modifiers changed from: private */
    public Map<K, V> c;
    private boolean d;
    private volatile SmallSortedMap<K, V>.EntrySet e;

    static <FieldDescriptorType extends FieldSet.FieldDescriptorLite<FieldDescriptorType>> SmallSortedMap<FieldDescriptorType, Object> a(int i) {
        return new SmallSortedMap<FieldDescriptorType, Object>(i) {
            public /* synthetic */ Object put(Object obj, Object obj2) {
                return SmallSortedMap.super.put((FieldSet.FieldDescriptorLite) obj, obj2);
            }

            public void a() {
                if (!b()) {
                    for (int i = 0; i < c(); i++) {
                        Map.Entry c = c(i);
                        if (((FieldSet.FieldDescriptorLite) c.getKey()).d()) {
                            c.setValue(Collections.unmodifiableList((List) c.getValue()));
                        }
                    }
                    for (Map.Entry entry : e()) {
                        if (((FieldSet.FieldDescriptorLite) entry.getKey()).d()) {
                            entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                        }
                    }
                }
                SmallSortedMap.super.a();
            }
        };
    }

    static <K extends Comparable<K>, V> SmallSortedMap<K, V> b(int i) {
        return new SmallSortedMap<>(i);
    }

    private SmallSortedMap(int i) {
        this.f11342a = i;
        this.b = Collections.emptyList();
        this.c = Collections.emptyMap();
    }

    public void a() {
        Map<K, V> map;
        if (!this.d) {
            if (this.c.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.c);
            }
            this.c = map;
            this.d = true;
        }
    }

    public boolean b() {
        return this.d;
    }

    public int c() {
        return this.b.size();
    }

    public Map.Entry<K, V> c(int i) {
        return this.b.get(i);
    }

    public int d() {
        return this.c.size();
    }

    public Iterable<Map.Entry<K, V>> e() {
        if (this.c.isEmpty()) {
            return EmptySet.a();
        }
        return this.c.entrySet();
    }

    public int size() {
        return this.b.size() + this.c.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return a(comparable) >= 0 || this.c.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int a2 = a(comparable);
        if (a2 >= 0) {
            return this.b.get(a2).getValue();
        }
        return this.c.get(comparable);
    }

    /* renamed from: a */
    public V put(K k, V v) {
        f();
        int a2 = a(k);
        if (a2 >= 0) {
            return this.b.get(a2).setValue(v);
        }
        h();
        int i = -(a2 + 1);
        if (i >= this.f11342a) {
            return g().put(k, v);
        }
        if (this.b.size() == this.f11342a) {
            Entry remove = this.b.remove(this.f11342a - 1);
            g().put(remove.getKey(), remove.getValue());
        }
        this.b.add(i, new Entry(k, v));
        return null;
    }

    public void clear() {
        f();
        if (!this.b.isEmpty()) {
            this.b.clear();
        }
        if (!this.c.isEmpty()) {
            this.c.clear();
        }
    }

    public V remove(Object obj) {
        f();
        Comparable comparable = (Comparable) obj;
        int a2 = a(comparable);
        if (a2 >= 0) {
            return d(a2);
        }
        if (this.c.isEmpty()) {
            return null;
        }
        return this.c.remove(comparable);
    }

    /* access modifiers changed from: private */
    public V d(int i) {
        f();
        V value = this.b.remove(i).getValue();
        if (!this.c.isEmpty()) {
            Iterator it = g().entrySet().iterator();
            this.b.add(new Entry(this, (Map.Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private int a(K k) {
        int size = this.b.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo(this.b.get(size).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo(this.b.get(i2).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.e == null) {
            this.e = new EntrySet();
        }
        return this.e;
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.d) {
            throw new UnsupportedOperationException();
        }
    }

    private SortedMap<K, V> g() {
        f();
        if (this.c.isEmpty() && !(this.c instanceof TreeMap)) {
            this.c = new TreeMap();
        }
        return (SortedMap) this.c;
    }

    private void h() {
        f();
        if (this.b.isEmpty() && !(this.b instanceof ArrayList)) {
            this.b = new ArrayList(this.f11342a);
        }
    }

    private class Entry implements Comparable<SmallSortedMap<K, V>.Entry>, Map.Entry<K, V> {
        private final K b;
        private V c;

        Entry(SmallSortedMap smallSortedMap, Map.Entry<K, V> entry) {
            this((Comparable) entry.getKey(), entry.getValue());
        }

        Entry(K k, V v) {
            this.b = k;
            this.c = v;
        }

        /* renamed from: a */
        public K getKey() {
            return this.b;
        }

        public V getValue() {
            return this.c;
        }

        /* renamed from: a */
        public int compareTo(SmallSortedMap<K, V>.Entry entry) {
            return getKey().compareTo(entry.getKey());
        }

        public V setValue(V v) {
            SmallSortedMap.this.f();
            V v2 = this.c;
            this.c = v;
            return v2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!a(this.b, entry.getKey()) || !a(this.c, entry.getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.b == null ? 0 : this.b.hashCode();
            if (this.c != null) {
                i = this.c.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return this.b + "=" + this.c;
        }

        private boolean a(Object obj, Object obj2) {
            if (obj == null) {
                return obj2 == null;
            }
            return obj.equals(obj2);
        }
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        public int size() {
            return SmallSortedMap.this.size();
        }

        public boolean contains(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = SmallSortedMap.this.get(entry.getKey());
            Object value = entry.getValue();
            return obj2 == value || (obj2 != null && obj2.equals(value));
        }

        /* renamed from: a */
        public boolean add(Map.Entry<K, V> entry) {
            if (contains(entry)) {
                return false;
            }
            SmallSortedMap.this.put((Comparable) entry.getKey(), entry.getValue());
            return true;
        }

        public boolean remove(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            if (!contains(entry)) {
                return false;
            }
            SmallSortedMap.this.remove(entry.getKey());
            return true;
        }

        public void clear() {
            SmallSortedMap.this.clear();
        }
    }

    private class EntryIterator implements Iterator<Map.Entry<K, V>> {
        private int b;
        private boolean c;
        private Iterator<Map.Entry<K, V>> d;

        private EntryIterator() {
            this.b = -1;
        }

        public boolean hasNext() {
            if (this.b + 1 < SmallSortedMap.this.b.size() || b().hasNext()) {
                return true;
            }
            return false;
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            this.c = true;
            int i = this.b + 1;
            this.b = i;
            if (i < SmallSortedMap.this.b.size()) {
                return (Map.Entry) SmallSortedMap.this.b.get(this.b);
            }
            return (Map.Entry) b().next();
        }

        public void remove() {
            if (this.c) {
                this.c = false;
                SmallSortedMap.this.f();
                if (this.b < SmallSortedMap.this.b.size()) {
                    SmallSortedMap smallSortedMap = SmallSortedMap.this;
                    int i = this.b;
                    this.b = i - 1;
                    Object unused = smallSortedMap.d(i);
                    return;
                }
                b().remove();
                return;
            }
            throw new IllegalStateException("remove() was called before next()");
        }

        private Iterator<Map.Entry<K, V>> b() {
            if (this.d == null) {
                this.d = SmallSortedMap.this.c.entrySet().iterator();
            }
            return this.d;
        }
    }

    private static class EmptySet {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final Iterator<Object> f11343a = new Iterator<Object>() {
            public boolean hasNext() {
                return false;
            }

            public Object next() {
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        private static final Iterable<Object> b = new Iterable<Object>() {
            public Iterator<Object> iterator() {
                return EmptySet.f11343a;
            }
        };

        private EmptySet() {
        }

        static <T> Iterable<T> a() {
            return b;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SmallSortedMap)) {
            return super.equals(obj);
        }
        SmallSortedMap smallSortedMap = (SmallSortedMap) obj;
        int size = size();
        if (size != smallSortedMap.size()) {
            return false;
        }
        int c2 = c();
        if (c2 != smallSortedMap.c()) {
            return entrySet().equals(smallSortedMap.entrySet());
        }
        for (int i = 0; i < c2; i++) {
            if (!c(i).equals(smallSortedMap.c(i))) {
                return false;
            }
        }
        if (c2 != size) {
            return this.c.equals(smallSortedMap.c);
        }
        return true;
    }

    public int hashCode() {
        int c2 = c();
        int i = 0;
        for (int i2 = 0; i2 < c2; i2++) {
            i += this.b.get(i2).hashCode();
        }
        return d() > 0 ? i + this.c.hashCode() : i;
    }
}
