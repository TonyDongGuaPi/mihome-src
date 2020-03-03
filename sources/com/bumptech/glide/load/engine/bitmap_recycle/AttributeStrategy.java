package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.util.Util;
import com.taobao.weex.el.parse.Operators;

class AttributeStrategy implements LruPoolStrategy {

    /* renamed from: a  reason: collision with root package name */
    private final KeyPool f4888a = new KeyPool();
    private final GroupedLinkedMap<Key, Bitmap> b = new GroupedLinkedMap<>();

    AttributeStrategy() {
    }

    public void a(Bitmap bitmap) {
        this.b.a(this.f4888a.a(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    public Bitmap a(int i, int i2, Bitmap.Config config) {
        return this.b.a(this.f4888a.a(i, i2, config));
    }

    public Bitmap a() {
        return this.b.a();
    }

    public String b(Bitmap bitmap) {
        return d(bitmap);
    }

    public String b(int i, int i2, Bitmap.Config config) {
        return c(i, i2, config);
    }

    public int c(Bitmap bitmap) {
        return Util.b(bitmap);
    }

    public String toString() {
        return "AttributeStrategy:\n  " + this.b;
    }

    private static String d(Bitmap bitmap) {
        return c(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }

    static String c(int i, int i2, Bitmap.Config config) {
        return Operators.ARRAY_START_STR + i + "x" + i2 + "], " + config;
    }

    @VisibleForTesting
    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        /* access modifiers changed from: package-private */
        public Key a(int i, int i2, Bitmap.Config config) {
            Key key = (Key) c();
            key.a(i, i2, config);
            return key;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Key b() {
            return new Key(this);
        }
    }

    @VisibleForTesting
    static class Key implements Poolable {

        /* renamed from: a  reason: collision with root package name */
        private final KeyPool f4889a;
        private int b;
        private int c;
        private Bitmap.Config d;

        public Key(KeyPool keyPool) {
            this.f4889a = keyPool;
        }

        public void a(int i, int i2, Bitmap.Config config) {
            this.b = i;
            this.c = i2;
            this.d = config;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) {
                return false;
            }
            Key key = (Key) obj;
            if (this.b == key.b && this.c == key.c && this.d == key.d) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((this.b * 31) + this.c) * 31) + (this.d != null ? this.d.hashCode() : 0);
        }

        public String toString() {
            return AttributeStrategy.c(this.b, this.c, this.d);
        }

        public void a() {
            this.f4889a.a(this);
        }
    }
}
