package com.xiaomi.jr.mipay.codepay.component;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public abstract class ArrayAdapter<T> extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    protected Context f10890a;
    protected List<T> b = null;

    public abstract View a(Context context, int i, T t, ViewGroup viewGroup);

    public abstract void a(View view, int i, T t);

    public ArrayAdapter(Context context) {
        this.f10890a = context;
    }

    public void a(List<T> list) {
        if (list != null) {
            this.b = list;
            notifyDataSetChanged();
            return;
        }
        this.b = null;
        notifyDataSetInvalidated();
    }

    public void a(List<T> list, boolean z) {
        if (list != null) {
            if (this.b == null) {
                this.b = list;
            } else {
                this.b.addAll(list);
            }
            notifyDataSetChanged();
        }
    }

    public List<T> a() {
        return this.b;
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (i < 0 || i >= getCount()) {
            throw new IllegalStateException("couldn't get view at this position " + i);
        } else if (this.b != null) {
            return this.b.get(i);
        } else {
            return null;
        }
    }

    public long getItemId(int i) {
        if (this.b != null) {
            return (long) i;
        }
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.b == null) {
            throw new IllegalStateException("this should only be called when the data is valid");
        } else if (i < 0 || i >= this.b.size()) {
            throw new IllegalStateException("couldn't get view at this position " + i);
        } else {
            T t = this.b.get(i);
            if (view == null) {
                view = a(this.f10890a, i, t, viewGroup);
            }
            a(view, i, t);
            return view;
        }
    }
}
