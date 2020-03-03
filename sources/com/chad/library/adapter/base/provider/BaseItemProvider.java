package com.chad.library.adapter.base.provider;

import android.content.Context;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

public abstract class BaseItemProvider<T, V extends BaseViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public Context f5143a;
    public List<T> b;

    public abstract int a();

    public abstract void a(V v, T t, int i);

    public abstract int b();

    public boolean b(V v, T t, int i) {
        return false;
    }

    public void onClick(V v, T t, int i) {
    }
}
