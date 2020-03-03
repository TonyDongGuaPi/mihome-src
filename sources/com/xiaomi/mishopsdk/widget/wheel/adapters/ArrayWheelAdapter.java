package com.xiaomi.mishopsdk.widget.wheel.adapters;

import android.content.Context;

public class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter {
    private T[] items;

    public ArrayWheelAdapter(Context context, T[] tArr) {
        super(context);
        this.items = tArr;
    }

    public CharSequence getItemText(int i) {
        if (i < 0 || i >= this.items.length) {
            return null;
        }
        T t = this.items[i];
        if (t instanceof CharSequence) {
            return (CharSequence) t;
        }
        return t.toString();
    }

    public int getItemsCount() {
        return this.items.length;
    }
}
