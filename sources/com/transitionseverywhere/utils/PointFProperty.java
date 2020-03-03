package com.transitionseverywhere.utils;

import android.annotation.TargetApi;
import android.graphics.PointF;
import android.util.Property;

@TargetApi(14)
public abstract class PointFProperty<T> extends Property<T, PointF> {
    /* renamed from: a */
    public PointF get(T t) {
        return null;
    }

    public PointFProperty() {
        super(PointF.class, (String) null);
    }
}
