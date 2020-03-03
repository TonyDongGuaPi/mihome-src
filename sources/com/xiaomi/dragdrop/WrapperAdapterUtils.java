package com.xiaomi.dragdrop;

import android.support.v7.widget.RecyclerView;

public class WrapperAdapterUtils {
    private WrapperAdapterUtils() {
    }

    public static <T> T a(RecyclerView.Adapter adapter, Class<T> cls) {
        if (cls.isInstance(adapter)) {
            return cls.cast(adapter);
        }
        if (adapter instanceof BaseWrapperAdapter) {
            return a(((BaseWrapperAdapter) adapter).d(), cls);
        }
        return null;
    }

    public static RecyclerView.Adapter a(RecyclerView.Adapter adapter) {
        return b(adapter);
    }

    private static RecyclerView.Adapter b(RecyclerView.Adapter adapter) {
        if (!(adapter instanceof BaseWrapperAdapter)) {
            return adapter;
        }
        BaseWrapperAdapter baseWrapperAdapter = (BaseWrapperAdapter) adapter;
        RecyclerView.Adapter d = baseWrapperAdapter.d();
        baseWrapperAdapter.b();
        return b(d);
    }
}
