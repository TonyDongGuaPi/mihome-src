package com.transitionseverywhere.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Property;

@TargetApi(14)
public abstract class IntProperty<T> extends Property<T, Integer> {
    /* renamed from: a */
    public Integer get(T t) {
        return null;
    }

    public abstract void a(T t, int i);

    public IntProperty() {
        super(Integer.class, (String) null);
    }

    /* renamed from: a */
    public final void set(T t, Integer num) {
        a(t, num.intValue());
    }

    @SuppressLint({"NewApi"})
    public Property<T, Integer> a() {
        return Build.VERSION.SDK_INT > 18 ? new android.util.IntProperty<T>((String) null) {
            public void setValue(T t, int i) {
                IntProperty.this.a(t, i);
            }

            /* renamed from: a */
            public Integer get(T t) {
                return IntProperty.this.get(t);
            }
        } : this;
    }
}
