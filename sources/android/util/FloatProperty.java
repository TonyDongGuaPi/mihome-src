package android.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;

@TargetApi(14)
public abstract class FloatProperty<T> extends Property<T, Float> {
    public abstract void setValue(T t, float f);

    public FloatProperty(String str) {
        super(Float.class, str);
    }

    @SuppressLint({"NewApi"})
    public final void set(T t, Float f) {
        setValue(t, f.floatValue());
    }
}
