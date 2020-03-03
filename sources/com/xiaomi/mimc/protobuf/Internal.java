package com.xiaomi.mimc.protobuf;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

public final class Internal {

    /* renamed from: a  reason: collision with root package name */
    static final Charset f11319a = Charset.forName("UTF-8");
    static final Charset b = Charset.forName("ISO-8859-1");
    public static final byte[] c = new byte[0];
    public static final ByteBuffer d = ByteBuffer.wrap(c);
    public static final CodedInputStream e = CodedInputStream.a(c);
    private static final int f = 4096;

    public interface BooleanList extends ProtobufList<Boolean> {
        BooleanList a(int i);

        void a(boolean z);

        boolean a(int i, boolean z);

        boolean c(int i);
    }

    public interface DoubleList extends ProtobufList<Double> {
        double a(int i, double d);

        DoubleList a(int i);

        void a(double d);

        double c(int i);
    }

    public interface EnumLite {
        int getNumber();
    }

    public interface EnumLiteMap<T extends EnumLite> {
        T b(int i);
    }

    public interface FloatList extends ProtobufList<Float> {
        float a(int i, float f);

        FloatList a(int i);

        void a(float f);

        float c(int i);
    }

    public interface IntList extends ProtobufList<Integer> {
        int a(int i, int i2);

        IntList a(int i);

        int c(int i);

        void d(int i);
    }

    public interface LongList extends ProtobufList<Long> {
        long a(int i);

        long a(int i, long j);

        void a(long j);

        LongList b(int i);
    }

    public interface ProtobufList<E> extends List<E>, RandomAccess {
        boolean a();

        void b();

        ProtobufList<E> e(int i);
    }

    public static int a(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int a(boolean z) {
        return z ? 1231 : 1237;
    }

    private Internal() {
    }

    public static String a(String str) {
        return new String(str.getBytes(b), f11319a);
    }

    public static ByteString b(String str) {
        return ByteString.copyFrom(str.getBytes(b));
    }

    public static byte[] c(String str) {
        return str.getBytes(b);
    }

    public static ByteBuffer d(String str) {
        return ByteBuffer.wrap(c(str));
    }

    public static ByteBuffer a(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.clear();
        ByteBuffer allocate = ByteBuffer.allocate(duplicate.capacity());
        allocate.put(duplicate);
        allocate.clear();
        return allocate;
    }

    public static boolean a(ByteString byteString) {
        return byteString.isValidUtf8();
    }

    public static boolean a(byte[] bArr) {
        return Utf8.a(bArr);
    }

    public static byte[] e(String str) {
        return str.getBytes(f11319a);
    }

    public static String b(byte[] bArr) {
        return new String(bArr, f11319a);
    }

    public static int a(EnumLite enumLite) {
        return enumLite.getNumber();
    }

    public static int a(List<? extends EnumLite> list) {
        int i = 1;
        for (EnumLite a2 : list) {
            i = (i * 31) + a(a2);
        }
        return i;
    }

    public static boolean a(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static int b(List<byte[]> list) {
        int i = 1;
        for (byte[] c2 : list) {
            i = (i * 31) + c(c2);
        }
        return i;
    }

    public static int c(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    static int a(byte[] bArr, int i, int i2) {
        int a2 = a(i2, bArr, i, i2);
        if (a2 == 0) {
            return 1;
        }
        return a2;
    }

    static int a(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }

    public static boolean a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        if (byteBuffer.capacity() != byteBuffer2.capacity()) {
            return false;
        }
        return byteBuffer.duplicate().clear().equals(byteBuffer2.duplicate().clear());
    }

    public static boolean b(List<ByteBuffer> list, List<ByteBuffer> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!a(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static int c(List<ByteBuffer> list) {
        int i = 1;
        for (ByteBuffer b2 : list) {
            i = (i * 31) + b(b2);
        }
        return i;
    }

    public static int b(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            int a2 = a(byteBuffer.capacity(), byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
            if (a2 == 0) {
                return 1;
            }
            return a2;
        }
        int i = 4096;
        if (byteBuffer.capacity() <= 4096) {
            i = byteBuffer.capacity();
        }
        byte[] bArr = new byte[i];
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.clear();
        int capacity = byteBuffer.capacity();
        while (duplicate.remaining() > 0) {
            int remaining = duplicate.remaining() <= i ? duplicate.remaining() : i;
            duplicate.get(bArr, 0, remaining);
            capacity = a(capacity, bArr, 0, remaining);
        }
        if (capacity == 0) {
            return 1;
        }
        return capacity;
    }

    public static <T extends MessageLite> T a(Class<T> cls) {
        try {
            Method method = cls.getMethod("getDefaultInstance", new Class[0]);
            return (MessageLite) method.invoke(method, new Object[0]);
        } catch (Exception e2) {
            throw new RuntimeException("Failed to get default instance for " + cls, e2);
        }
    }

    public static class ListAdapter<F, T> extends AbstractList<T> {

        /* renamed from: a  reason: collision with root package name */
        private final List<F> f11320a;
        private final Converter<F, T> b;

        public interface Converter<F, T> {
            T a(F f);
        }

        public ListAdapter(List<F> list, Converter<F, T> converter) {
            this.f11320a = list;
            this.b = converter;
        }

        public T get(int i) {
            return this.b.a(this.f11320a.get(i));
        }

        public int size() {
            return this.f11320a.size();
        }
    }

    public static class MapAdapter<K, V, RealValue> extends AbstractMap<K, V> {

        /* renamed from: a  reason: collision with root package name */
        private final Map<K, RealValue> f11321a;
        /* access modifiers changed from: private */
        public final Converter<RealValue, V> b;

        public interface Converter<A, B> {
            A a(B b);

            B b(A a2);
        }

        public static <T extends EnumLite> Converter<Integer, T> a(final EnumLiteMap<T> enumLiteMap, final T t) {
            return new Converter<Integer, T>() {
                /* renamed from: a */
                public T b(Integer num) {
                    T b2 = enumLiteMap.b(num.intValue());
                    return b2 == null ? t : b2;
                }

                public Integer a(T t) {
                    return Integer.valueOf(t.getNumber());
                }
            };
        }

        public MapAdapter(Map<K, RealValue> map, Converter<RealValue, V> converter) {
            this.f11321a = map;
            this.b = converter;
        }

        public V get(Object obj) {
            RealValue realvalue = this.f11321a.get(obj);
            if (realvalue == null) {
                return null;
            }
            return this.b.b(realvalue);
        }

        public V put(K k, V v) {
            RealValue put = this.f11321a.put(k, this.b.a(v));
            if (put == null) {
                return null;
            }
            return this.b.b(put);
        }

        public Set<Map.Entry<K, V>> entrySet() {
            return new SetAdapter(this.f11321a.entrySet());
        }

        private class SetAdapter extends AbstractSet<Map.Entry<K, V>> {
            private final Set<Map.Entry<K, RealValue>> b;

            public SetAdapter(Set<Map.Entry<K, RealValue>> set) {
                this.b = set;
            }

            public Iterator<Map.Entry<K, V>> iterator() {
                return new IteratorAdapter(this.b.iterator());
            }

            public int size() {
                return this.b.size();
            }
        }

        private class IteratorAdapter implements Iterator<Map.Entry<K, V>> {
            private final Iterator<Map.Entry<K, RealValue>> b;

            public IteratorAdapter(Iterator<Map.Entry<K, RealValue>> it) {
                this.b = it;
            }

            public boolean hasNext() {
                return this.b.hasNext();
            }

            /* renamed from: a */
            public Map.Entry<K, V> next() {
                return new EntryAdapter(this.b.next());
            }

            public void remove() {
                this.b.remove();
            }
        }

        private class EntryAdapter implements Map.Entry<K, V> {
            private final Map.Entry<K, RealValue> b;

            public EntryAdapter(Map.Entry<K, RealValue> entry) {
                this.b = entry;
            }

            public K getKey() {
                return this.b.getKey();
            }

            public V getValue() {
                return MapAdapter.this.b.b(this.b.getValue());
            }

            public V setValue(V v) {
                RealValue value = this.b.setValue(MapAdapter.this.b.a(v));
                if (value == null) {
                    return null;
                }
                return MapAdapter.this.b.b(value);
            }
        }
    }
}
