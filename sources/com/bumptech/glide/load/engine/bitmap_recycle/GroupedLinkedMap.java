package com.bumptech.glide.load.engine.bitmap_recycle;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.engine.bitmap_recycle.Poolable;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GroupedLinkedMap<K extends Poolable, V> {

    /* renamed from: a  reason: collision with root package name */
    private final LinkedEntry<K, V> f4892a = new LinkedEntry<>();
    private final Map<K, LinkedEntry<K, V>> b = new HashMap();

    GroupedLinkedMap() {
    }

    public void a(K k, V v) {
        LinkedEntry linkedEntry = this.b.get(k);
        if (linkedEntry == null) {
            linkedEntry = new LinkedEntry(k);
            b(linkedEntry);
            this.b.put(k, linkedEntry);
        } else {
            k.a();
        }
        linkedEntry.a(v);
    }

    @Nullable
    public V a(K k) {
        LinkedEntry linkedEntry = this.b.get(k);
        if (linkedEntry == null) {
            linkedEntry = new LinkedEntry(k);
            this.b.put(k, linkedEntry);
        } else {
            k.a();
        }
        a(linkedEntry);
        return linkedEntry.a();
    }

    @Nullable
    public V a() {
        for (LinkedEntry<K, V> linkedEntry = this.f4892a.c; !linkedEntry.equals(this.f4892a); linkedEntry = linkedEntry.c) {
            V a2 = linkedEntry.a();
            if (a2 != null) {
                return a2;
            }
            d(linkedEntry);
            this.b.remove(linkedEntry.f4893a);
            ((Poolable) linkedEntry.f4893a).a();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        boolean z = false;
        for (LinkedEntry<K, V> linkedEntry = this.f4892a.b; !linkedEntry.equals(this.f4892a); linkedEntry = linkedEntry.b) {
            z = true;
            sb.append(Operators.BLOCK_START);
            sb.append(linkedEntry.f4893a);
            sb.append(Operators.CONDITION_IF_MIDDLE);
            sb.append(linkedEntry.b());
            sb.append("}, ");
        }
        if (z) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        return sb.toString();
    }

    private void a(LinkedEntry<K, V> linkedEntry) {
        d(linkedEntry);
        linkedEntry.c = this.f4892a;
        linkedEntry.b = this.f4892a.b;
        c(linkedEntry);
    }

    private void b(LinkedEntry<K, V> linkedEntry) {
        d(linkedEntry);
        linkedEntry.c = this.f4892a.c;
        linkedEntry.b = this.f4892a;
        c(linkedEntry);
    }

    private static <K, V> void c(LinkedEntry<K, V> linkedEntry) {
        linkedEntry.b.c = linkedEntry;
        linkedEntry.c.b = linkedEntry;
    }

    private static <K, V> void d(LinkedEntry<K, V> linkedEntry) {
        linkedEntry.c.b = linkedEntry.b;
        linkedEntry.b.c = linkedEntry.c;
    }

    private static class LinkedEntry<K, V> {

        /* renamed from: a  reason: collision with root package name */
        final K f4893a;
        LinkedEntry<K, V> b;
        LinkedEntry<K, V> c;
        private List<V> d;

        LinkedEntry() {
            this((Object) null);
        }

        LinkedEntry(K k) {
            this.c = this;
            this.b = this;
            this.f4893a = k;
        }

        @Nullable
        public V a() {
            int b2 = b();
            if (b2 > 0) {
                return this.d.remove(b2 - 1);
            }
            return null;
        }

        public int b() {
            if (this.d != null) {
                return this.d.size();
            }
            return 0;
        }

        public void a(V v) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            this.d.add(v);
        }
    }
}
