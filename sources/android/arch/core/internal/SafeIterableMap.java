package android.arch.core.internal;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import com.taobao.weex.el.parse.Operators;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SafeIterableMap<K, V> implements Iterable<Map.Entry<K, V>> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Entry<K, V> f425a;
    private Entry<K, V> b;
    private WeakHashMap<SupportRemove<K, V>, Boolean> c = new WeakHashMap<>();
    private int d = 0;

    interface SupportRemove<K, V> {
        void a_(@NonNull Entry<K, V> entry);
    }

    /* access modifiers changed from: protected */
    public Entry<K, V> a(K k) {
        Entry<K, V> entry = this.f425a;
        while (entry != null && !entry.f426a.equals(k)) {
            entry = entry.c;
        }
        return entry;
    }

    public V a(@NonNull K k, @NonNull V v) {
        Entry a2 = a(k);
        if (a2 != null) {
            return a2.b;
        }
        b(k, v);
        return null;
    }

    /* access modifiers changed from: protected */
    public Entry<K, V> b(@NonNull K k, @NonNull V v) {
        Entry<K, V> entry = new Entry<>(k, v);
        this.d++;
        if (this.b == null) {
            this.f425a = entry;
            this.b = this.f425a;
            return entry;
        }
        this.b.c = entry;
        entry.d = this.b;
        this.b = entry;
        return entry;
    }

    public V b(@NonNull K k) {
        Entry a2 = a(k);
        if (a2 == null) {
            return null;
        }
        this.d--;
        if (!this.c.isEmpty()) {
            for (SupportRemove<K, V> a_ : this.c.keySet()) {
                a_.a_(a2);
            }
        }
        if (a2.d != null) {
            a2.d.c = a2.c;
        } else {
            this.f425a = a2.c;
        }
        if (a2.c != null) {
            a2.c.d = a2.d;
        } else {
            this.b = a2.d;
        }
        a2.c = null;
        a2.d = null;
        return a2.b;
    }

    public int a() {
        return this.d;
    }

    @NonNull
    public Iterator<Map.Entry<K, V>> iterator() {
        AscendingIterator ascendingIterator = new AscendingIterator(this.f425a, this.b);
        this.c.put(ascendingIterator, false);
        return ascendingIterator;
    }

    public Iterator<Map.Entry<K, V>> b() {
        DescendingIterator descendingIterator = new DescendingIterator(this.b, this.f425a);
        this.c.put(descendingIterator, false);
        return descendingIterator;
    }

    public SafeIterableMap<K, V>.IteratorWithAdditions c() {
        SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions = new IteratorWithAdditions();
        this.c.put(iteratorWithAdditions, false);
        return iteratorWithAdditions;
    }

    public Map.Entry<K, V> d() {
        return this.f425a;
    }

    public Map.Entry<K, V> e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SafeIterableMap)) {
            return false;
        }
        SafeIterableMap safeIterableMap = (SafeIterableMap) obj;
        if (a() != safeIterableMap.a()) {
            return false;
        }
        Iterator it = iterator();
        Iterator it2 = safeIterableMap.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object next = it2.next();
            if ((entry == null && next != null) || (entry != null && !entry.equals(next))) {
                return false;
            }
        }
        if (it.hasNext() || it2.hasNext()) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.ARRAY_START_STR);
        Iterator it = iterator();
        while (it.hasNext()) {
            sb.append(((Map.Entry) it.next()).toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    private static abstract class ListIterator<K, V> implements SupportRemove<K, V>, Iterator<Map.Entry<K, V>> {

        /* renamed from: a  reason: collision with root package name */
        Entry<K, V> f428a;
        Entry<K, V> b;

        /* access modifiers changed from: package-private */
        public abstract Entry<K, V> a(Entry<K, V> entry);

        /* access modifiers changed from: package-private */
        public abstract Entry<K, V> b(Entry<K, V> entry);

        ListIterator(Entry<K, V> entry, Entry<K, V> entry2) {
            this.f428a = entry2;
            this.b = entry;
        }

        public boolean hasNext() {
            return this.b != null;
        }

        public void a_(@NonNull Entry<K, V> entry) {
            if (this.f428a == entry && entry == this.b) {
                this.b = null;
                this.f428a = null;
            }
            if (this.f428a == entry) {
                this.f428a = b(this.f428a);
            }
            if (this.b == entry) {
                this.b = b();
            }
        }

        private Entry<K, V> b() {
            if (this.b == this.f428a || this.f428a == null) {
                return null;
            }
            return a(this.b);
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            Entry<K, V> entry = this.b;
            this.b = b();
            return entry;
        }
    }

    static class AscendingIterator<K, V> extends ListIterator<K, V> {
        AscendingIterator(Entry<K, V> entry, Entry<K, V> entry2) {
            super(entry, entry2);
        }

        /* access modifiers changed from: package-private */
        public Entry<K, V> a(Entry<K, V> entry) {
            return entry.c;
        }

        /* access modifiers changed from: package-private */
        public Entry<K, V> b(Entry<K, V> entry) {
            return entry.d;
        }
    }

    private static class DescendingIterator<K, V> extends ListIterator<K, V> {
        DescendingIterator(Entry<K, V> entry, Entry<K, V> entry2) {
            super(entry, entry2);
        }

        /* access modifiers changed from: package-private */
        public Entry<K, V> a(Entry<K, V> entry) {
            return entry.d;
        }

        /* access modifiers changed from: package-private */
        public Entry<K, V> b(Entry<K, V> entry) {
            return entry.c;
        }
    }

    private class IteratorWithAdditions implements SupportRemove<K, V>, Iterator<Map.Entry<K, V>> {
        private Entry<K, V> b;
        private boolean c;

        private IteratorWithAdditions() {
            this.c = true;
        }

        public void a_(@NonNull Entry<K, V> entry) {
            if (entry == this.b) {
                this.b = this.b.d;
                this.c = this.b == null;
            }
        }

        public boolean hasNext() {
            if (this.c) {
                if (SafeIterableMap.this.f425a != null) {
                    return true;
                }
                return false;
            } else if (this.b == null || this.b.c == null) {
                return false;
            } else {
                return true;
            }
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            if (this.c) {
                this.c = false;
                this.b = SafeIterableMap.this.f425a;
            } else {
                this.b = this.b != null ? this.b.c : null;
            }
            return this.b;
        }
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        final K f426a;
        @NonNull
        final V b;
        Entry<K, V> c;
        Entry<K, V> d;

        Entry(@NonNull K k, @NonNull V v) {
            this.f426a = k;
            this.b = v;
        }

        @NonNull
        public K getKey() {
            return this.f426a;
        }

        @NonNull
        public V getValue() {
            return this.b;
        }

        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f426a + "=" + this.b;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (!this.f426a.equals(entry.f426a) || !this.b.equals(entry.b)) {
                return false;
            }
            return true;
        }
    }
}
