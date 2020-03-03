package android.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;

@TargetApi(14)
public abstract class IntProperty<T> extends Property<T, Integer> {
    public abstract void setValue(T t, int i);

    public IntProperty(String str) {
        super(Integer.class, str);
    }

    @SuppressLint({"NewApi"})
    public final void set(T t, Integer num) {
        setValue(t, num.intValue());
    }
}
