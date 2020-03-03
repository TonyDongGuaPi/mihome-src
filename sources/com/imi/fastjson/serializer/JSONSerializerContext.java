package com.imi.fastjson.serializer;

public final class JSONSerializerContext {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6166a = 128;
    private final Entry[] b;
    private final int c;

    public JSONSerializerContext() {
        this(128);
    }

    public JSONSerializerContext(int i) {
        this.c = i - 1;
        this.b = new Entry[i];
    }

    public final boolean a(Object obj) {
        int identityHashCode = System.identityHashCode(obj);
        int i = this.c & identityHashCode;
        for (Entry entry = this.b[i]; entry != null; entry = entry.c) {
            if (obj == entry.b) {
                return true;
            }
        }
        this.b[i] = new Entry(obj, identityHashCode, this.b[i]);
        return false;
    }

    protected static final class Entry {

        /* renamed from: a  reason: collision with root package name */
        public final int f6167a;
        public final Object b;
        public Entry c;

        public Entry(Object obj, int i, Entry entry) {
            this.b = obj;
            this.c = entry;
            this.f6167a = i;
        }
    }
}
