package com.xiaomi.smarthome.library.common.wheel;

public class ArrayWheelAdapter<T> implements WheelAdapter {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18754a = -1;
    private T[] b;
    private int c;

    public ArrayWheelAdapter(T[] tArr, int i) {
        this.b = tArr;
        this.c = i;
    }

    public ArrayWheelAdapter(T[] tArr) {
        this(tArr, -1);
    }

    public String a(int i) {
        if (i < 0 || i >= this.b.length) {
            return null;
        }
        return this.b[i].toString();
    }

    public int a() {
        return this.b.length;
    }

    public int b() {
        return this.c;
    }
}
