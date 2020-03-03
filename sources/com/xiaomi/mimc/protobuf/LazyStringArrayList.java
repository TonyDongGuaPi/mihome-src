package com.xiaomi.mimc.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public class LazyStringArrayList extends AbstractProtobufList<String> implements LazyStringList, RandomAccess {
    public static final LazyStringList b = c;
    private static final LazyStringArrayList c = new LazyStringArrayList();
    private final List<Object> d;

    public /* bridge */ /* synthetic */ boolean a() {
        return super.a();
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    static {
        c.b();
    }

    static LazyStringArrayList d() {
        return c;
    }

    public LazyStringArrayList() {
        this(10);
    }

    public LazyStringArrayList(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    public LazyStringArrayList(LazyStringList lazyStringList) {
        this.d = new ArrayList(lazyStringList.size());
        addAll(lazyStringList);
    }

    public LazyStringArrayList(List<String> list) {
        this((ArrayList<Object>) new ArrayList(list));
    }

    private LazyStringArrayList(ArrayList<Object> arrayList) {
        this.d = arrayList;
    }

    /* renamed from: a */
    public LazyStringArrayList e(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.d);
            return new LazyStringArrayList((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: b */
    public String get(int i) {
        Object obj = this.d.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.d.set(i, stringUtf8);
            }
            return stringUtf8;
        }
        byte[] bArr = (byte[]) obj;
        String b2 = Internal.b(bArr);
        if (Internal.a(bArr)) {
            this.d.set(i, b2);
        }
        return b2;
    }

    public int size() {
        return this.d.size();
    }

    /* renamed from: a */
    public String set(int i, String str) {
        c();
        return c(this.d.set(i, str));
    }

    /* renamed from: b */
    public void add(int i, String str) {
        c();
        this.d.add(i, str);
        this.modCount++;
    }

    /* access modifiers changed from: private */
    public void b(int i, ByteString byteString) {
        c();
        this.d.add(i, byteString);
        this.modCount++;
    }

    /* access modifiers changed from: private */
    public void b(int i, byte[] bArr) {
        c();
        this.d.add(i, bArr);
        this.modCount++;
    }

    public boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public boolean addAll(int i, Collection<? extends String> collection) {
        c();
        if (collection instanceof LazyStringList) {
            collection = ((LazyStringList) collection).e();
        }
        boolean addAll = this.d.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public boolean a(Collection<? extends ByteString> collection) {
        c();
        boolean addAll = this.d.addAll(collection);
        this.modCount++;
        return addAll;
    }

    public boolean b(Collection<byte[]> collection) {
        c();
        boolean addAll = this.d.addAll(collection);
        this.modCount++;
        return addAll;
    }

    /* renamed from: c */
    public String remove(int i) {
        c();
        Object remove = this.d.remove(i);
        this.modCount++;
        return c(remove);
    }

    public void clear() {
        c();
        this.d.clear();
        this.modCount++;
    }

    public void a(ByteString byteString) {
        c();
        this.d.add(byteString);
        this.modCount++;
    }

    public void a(byte[] bArr) {
        c();
        this.d.add(bArr);
        this.modCount++;
    }

    public Object d(int i) {
        return this.d.get(i);
    }

    public ByteString f(int i) {
        Object obj = this.d.get(i);
        ByteString d2 = d(obj);
        if (d2 != obj) {
            this.d.set(i, d2);
        }
        return d2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.util.List<java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] g(int r3) {
        /*
            r2 = this;
            java.util.List<java.lang.Object> r0 = r2.d
            java.lang.Object r0 = r0.get(r3)
            byte[] r1 = e((java.lang.Object) r0)
            if (r1 == r0) goto L_0x0011
            java.util.List<java.lang.Object> r0 = r2.d
            r0.set(r3, r1)
        L_0x0011:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.LazyStringArrayList.g(int):byte[]");
    }

    public void a(int i, ByteString byteString) {
        c(i, byteString);
    }

    /* access modifiers changed from: private */
    public Object c(int i, ByteString byteString) {
        c();
        return this.d.set(i, byteString);
    }

    public void a(int i, byte[] bArr) {
        c(i, bArr);
    }

    /* access modifiers changed from: private */
    public Object c(int i, byte[] bArr) {
        c();
        return this.d.set(i, bArr);
    }

    private static String c(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ByteString) {
            return ((ByteString) obj).toStringUtf8();
        }
        return Internal.b((byte[]) obj);
    }

    /* access modifiers changed from: private */
    public static ByteString d(Object obj) {
        if (obj instanceof ByteString) {
            return (ByteString) obj;
        }
        if (obj instanceof String) {
            return ByteString.copyFromUtf8((String) obj);
        }
        return ByteString.copyFrom((byte[]) obj);
    }

    /* access modifiers changed from: private */
    public static byte[] e(Object obj) {
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        if (obj instanceof String) {
            return Internal.e((String) obj);
        }
        return ((ByteString) obj).toByteArray();
    }

    public List<?> e() {
        return Collections.unmodifiableList(this.d);
    }

    public void a(LazyStringList lazyStringList) {
        c();
        for (Object next : lazyStringList.e()) {
            if (next instanceof byte[]) {
                byte[] bArr = (byte[]) next;
                this.d.add(Arrays.copyOf(bArr, bArr.length));
            } else {
                this.d.add(next);
            }
        }
    }

    private static class ByteArrayListView extends AbstractList<byte[]> implements RandomAccess {

        /* renamed from: a  reason: collision with root package name */
        private final LazyStringArrayList f11329a;

        ByteArrayListView(LazyStringArrayList lazyStringArrayList) {
            this.f11329a = lazyStringArrayList;
        }

        /* renamed from: a */
        public byte[] get(int i) {
            return this.f11329a.g(i);
        }

        public int size() {
            return this.f11329a.size();
        }

        /* renamed from: a */
        public byte[] set(int i, byte[] bArr) {
            Object a2 = this.f11329a.c(i, bArr);
            this.modCount++;
            return LazyStringArrayList.e(a2);
        }

        /* renamed from: b */
        public void add(int i, byte[] bArr) {
            this.f11329a.b(i, bArr);
            this.modCount++;
        }

        /* renamed from: b */
        public byte[] remove(int i) {
            String c = this.f11329a.remove(i);
            this.modCount++;
            return LazyStringArrayList.e((Object) c);
        }
    }

    public List<byte[]> f() {
        return new ByteArrayListView(this);
    }

    private static class ByteStringListView extends AbstractList<ByteString> implements RandomAccess {

        /* renamed from: a  reason: collision with root package name */
        private final LazyStringArrayList f11330a;

        ByteStringListView(LazyStringArrayList lazyStringArrayList) {
            this.f11330a = lazyStringArrayList;
        }

        /* renamed from: a */
        public ByteString get(int i) {
            return this.f11330a.f(i);
        }

        public int size() {
            return this.f11330a.size();
        }

        /* renamed from: a */
        public ByteString set(int i, ByteString byteString) {
            Object a2 = this.f11330a.c(i, byteString);
            this.modCount++;
            return LazyStringArrayList.d(a2);
        }

        /* renamed from: b */
        public void add(int i, ByteString byteString) {
            this.f11330a.b(i, byteString);
            this.modCount++;
        }

        /* renamed from: b */
        public ByteString remove(int i) {
            String c = this.f11330a.remove(i);
            this.modCount++;
            return LazyStringArrayList.d((Object) c);
        }
    }

    public List<ByteString> g() {
        return new ByteStringListView(this);
    }

    public LazyStringList h() {
        return a() ? new UnmodifiableLazyStringList(this) : this;
    }
}
