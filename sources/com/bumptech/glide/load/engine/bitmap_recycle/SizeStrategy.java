package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.util.Util;
import com.taobao.weex.el.parse.Operators;
import java.util.NavigableMap;

@RequiresApi(19)
final class SizeStrategy implements LruPoolStrategy {

    /* renamed from: a  reason: collision with root package name */
    private static final int f4901a = 8;
    private final KeyPool b = new KeyPool();
    private final GroupedLinkedMap<Key, Bitmap> c = new GroupedLinkedMap<>();
    private final NavigableMap<Integer, Integer> d = new PrettyPrintTreeMap();

    SizeStrategy() {
    }

    public void a(Bitmap bitmap) {
        Key a2 = this.b.a(Util.b(bitmap));
        this.c.a(a2, bitmap);
        Integer num = (Integer) this.d.get(Integer.valueOf(a2.f4902a));
        NavigableMap<Integer, Integer> navigableMap = this.d;
        Integer valueOf = Integer.valueOf(a2.f4902a);
        int i = 1;
        if (num != null) {
            i = 1 + num.intValue();
        }
        navigableMap.put(valueOf, Integer.valueOf(i));
    }

    @Nullable
    public Bitmap a(int i, int i2, Bitmap.Config config) {
        int a2 = Util.a(i, i2, config);
        Key a3 = this.b.a(a2);
        Integer ceilingKey = this.d.ceilingKey(Integer.valueOf(a2));
        if (!(ceilingKey == null || ceilingKey.intValue() == a2 || ceilingKey.intValue() > a2 * 8)) {
            this.b.a(a3);
            a3 = this.b.a(ceilingKey.intValue());
        }
        Bitmap a4 = this.c.a(a3);
        if (a4 != null) {
            a4.reconfigure(i, i2, config);
            a(ceilingKey);
        }
        return a4;
    }

    @Nullable
    public Bitmap a() {
        Bitmap a2 = this.c.a();
        if (a2 != null) {
            a(Integer.valueOf(Util.b(a2)));
        }
        return a2;
    }

    private void a(Integer num) {
        Integer num2 = (Integer) this.d.get(num);
        if (num2.intValue() == 1) {
            this.d.remove(num);
        } else {
            this.d.put(num, Integer.valueOf(num2.intValue() - 1));
        }
    }

    public String b(Bitmap bitmap) {
        return d(bitmap);
    }

    public String b(int i, int i2, Bitmap.Config config) {
        return a(Util.a(i, i2, config));
    }

    public int c(Bitmap bitmap) {
        return Util.b(bitmap);
    }

    public String toString() {
        return "SizeStrategy:\n  " + this.c + "\n  SortedSizes" + this.d;
    }

    private static String d(Bitmap bitmap) {
        return a(Util.b(bitmap));
    }

    static String a(int i) {
        return Operators.ARRAY_START_STR + i + Operators.ARRAY_END_STR;
    }

    @VisibleForTesting
    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        public Key a(int i) {
            Key key = (Key) super.c();
            key.a(i);
            return key;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Key b() {
            return new Key(this);
        }
    }

    @VisibleForTesting
    static final class Key implements Poolable {

        /* renamed from: a  reason: collision with root package name */
        int f4902a;
        private final KeyPool b;

        Key(KeyPool keyPool) {
            this.b = keyPool;
        }

        public void a(int i) {
            this.f4902a = i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Key) || this.f4902a != ((Key) obj).f4902a) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.f4902a;
        }

        public String toString() {
            return SizeStrategy.a(this.f4902a);
        }

        public void a() {
            this.b.a(this);
        }
    }
}
