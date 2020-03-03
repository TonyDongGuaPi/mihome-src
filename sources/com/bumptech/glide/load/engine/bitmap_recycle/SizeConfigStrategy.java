package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.util.Util;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

@RequiresApi(19)
public class SizeConfigStrategy implements LruPoolStrategy {

    /* renamed from: a  reason: collision with root package name */
    private static final int f4898a = 8;
    private static final Bitmap.Config[] b;
    private static final Bitmap.Config[] c = b;
    private static final Bitmap.Config[] d = {Bitmap.Config.RGB_565};
    private static final Bitmap.Config[] e = {Bitmap.Config.ARGB_4444};
    private static final Bitmap.Config[] f = {Bitmap.Config.ALPHA_8};
    private final KeyPool g = new KeyPool();
    private final GroupedLinkedMap<Key, Bitmap> h = new GroupedLinkedMap<>();
    private final Map<Bitmap.Config, NavigableMap<Integer, Integer>> i = new HashMap();

    static {
        Bitmap.Config[] configArr = {Bitmap.Config.ARGB_8888, null};
        if (Build.VERSION.SDK_INT >= 26) {
            configArr = (Bitmap.Config[]) Arrays.copyOf(configArr, configArr.length + 1);
            configArr[configArr.length - 1] = Bitmap.Config.RGBA_F16;
        }
        b = configArr;
    }

    public void a(Bitmap bitmap) {
        Key a2 = this.g.a(Util.b(bitmap), bitmap.getConfig());
        this.h.a(a2, bitmap);
        NavigableMap<Integer, Integer> a3 = a(bitmap.getConfig());
        Integer num = (Integer) a3.get(Integer.valueOf(a2.f4900a));
        Integer valueOf = Integer.valueOf(a2.f4900a);
        int i2 = 1;
        if (num != null) {
            i2 = 1 + num.intValue();
        }
        a3.put(valueOf, Integer.valueOf(i2));
    }

    @Nullable
    public Bitmap a(int i2, int i3, Bitmap.Config config) {
        Key b2 = b(Util.a(i2, i3, config), config);
        Bitmap a2 = this.h.a(b2);
        if (a2 != null) {
            a(Integer.valueOf(b2.f4900a), a2);
            a2.reconfigure(i2, i3, config);
        }
        return a2;
    }

    private Key b(int i2, Bitmap.Config config) {
        Key a2 = this.g.a(i2, config);
        Bitmap.Config[] b2 = b(config);
        int length = b2.length;
        int i3 = 0;
        while (i3 < length) {
            Bitmap.Config config2 = b2[i3];
            Integer ceilingKey = a(config2).ceilingKey(Integer.valueOf(i2));
            if (ceilingKey == null || ceilingKey.intValue() > i2 * 8) {
                i3++;
            } else {
                if (ceilingKey.intValue() == i2) {
                    if (config2 == null) {
                        if (config == null) {
                            return a2;
                        }
                    } else if (config2.equals(config)) {
                        return a2;
                    }
                }
                this.g.a(a2);
                return this.g.a(ceilingKey.intValue(), config2);
            }
        }
        return a2;
    }

    @Nullable
    public Bitmap a() {
        Bitmap a2 = this.h.a();
        if (a2 != null) {
            a(Integer.valueOf(Util.b(a2)), a2);
        }
        return a2;
    }

    private void a(Integer num, Bitmap bitmap) {
        NavigableMap<Integer, Integer> a2 = a(bitmap.getConfig());
        Integer num2 = (Integer) a2.get(num);
        if (num2 == null) {
            throw new NullPointerException("Tried to decrement empty size, size: " + num + ", removed: " + b(bitmap) + ", this: " + this);
        } else if (num2.intValue() == 1) {
            a2.remove(num);
        } else {
            a2.put(num, Integer.valueOf(num2.intValue() - 1));
        }
    }

    private NavigableMap<Integer, Integer> a(Bitmap.Config config) {
        NavigableMap<Integer, Integer> navigableMap = this.i.get(config);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.i.put(config, treeMap);
        return treeMap;
    }

    public String b(Bitmap bitmap) {
        return a(Util.b(bitmap), bitmap.getConfig());
    }

    public String b(int i2, int i3, Bitmap.Config config) {
        return a(Util.a(i2, i3, config), config);
    }

    public int c(Bitmap bitmap) {
        return Util.b(bitmap);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SizeConfigStrategy{groupedMap=");
        sb.append(this.h);
        sb.append(", sortedSizes=(");
        for (Map.Entry next : this.i.entrySet()) {
            sb.append(next.getKey());
            sb.append(Operators.ARRAY_START);
            sb.append(next.getValue());
            sb.append("], ");
        }
        if (!this.i.isEmpty()) {
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        sb.append(")}");
        return sb.toString();
    }

    @VisibleForTesting
    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        public Key a(int i, Bitmap.Config config) {
            Key key = (Key) c();
            key.a(i, config);
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
        int f4900a;
        private final KeyPool b;
        private Bitmap.Config c;

        public Key(KeyPool keyPool) {
            this.b = keyPool;
        }

        @VisibleForTesting
        Key(KeyPool keyPool, int i, Bitmap.Config config) {
            this(keyPool);
            a(i, config);
        }

        public void a(int i, Bitmap.Config config) {
            this.f4900a = i;
            this.c = config;
        }

        public void a() {
            this.b.a(this);
        }

        public String toString() {
            return SizeConfigStrategy.a(this.f4900a, this.c);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) {
                return false;
            }
            Key key = (Key) obj;
            if (this.f4900a != key.f4900a || !Util.a((Object) this.c, (Object) key.c)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.f4900a * 31) + (this.c != null ? this.c.hashCode() : 0);
        }
    }

    static String a(int i2, Bitmap.Config config) {
        return Operators.ARRAY_START_STR + i2 + "](" + config + Operators.BRACKET_END_STR;
    }

    private static Bitmap.Config[] b(Bitmap.Config config) {
        if (Build.VERSION.SDK_INT >= 26 && Bitmap.Config.RGBA_F16.equals(config)) {
            return c;
        }
        switch (AnonymousClass1.f4899a[config.ordinal()]) {
            case 1:
                return b;
            case 2:
                return d;
            case 3:
                return e;
            case 4:
                return f;
            default:
                return new Bitmap.Config[]{config};
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.bitmap_recycle.SizeConfigStrategy$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f4899a = new int[Bitmap.Config.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                android.graphics.Bitmap$Config[] r0 = android.graphics.Bitmap.Config.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f4899a = r0
                int[] r0 = f4899a     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f4899a     // Catch:{ NoSuchFieldError -> 0x001f }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.RGB_565     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f4899a     // Catch:{ NoSuchFieldError -> 0x002a }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_4444     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f4899a     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ALPHA_8     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.bitmap_recycle.SizeConfigStrategy.AnonymousClass1.<clinit>():void");
        }
    }
}
