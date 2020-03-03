package com.bumptech.glide.load.engine.bitmap_recycle;

public interface ArrayPool {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4887a = 65536;

    <T> T a(int i, Class<T> cls);

    void a();

    void a(int i);

    <T> void a(T t);

    @Deprecated
    <T> void a(T t, Class<T> cls);

    <T> T b(int i, Class<T> cls);
}
