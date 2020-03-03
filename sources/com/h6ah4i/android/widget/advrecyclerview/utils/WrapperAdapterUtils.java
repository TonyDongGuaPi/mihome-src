package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.support.v7.widget.RecyclerView;

public class WrapperAdapterUtils {
    private WrapperAdapterUtils() {
    }

    public static <T> T a(RecyclerView.Adapter adapter, Class<T> cls) {
        if (cls.isInstance(adapter)) {
            return cls.cast(adapter);
        }
        if (adapter instanceof BaseWrapperAdapter) {
            return a(((BaseWrapperAdapter) adapter).m(), cls);
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
        RecyclerView.Adapter m = baseWrapperAdapter.m();
        baseWrapperAdapter.l();
        return b(m);
    }
}
