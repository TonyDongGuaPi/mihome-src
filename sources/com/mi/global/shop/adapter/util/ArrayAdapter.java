package com.mi.global.shop.adapter.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class ArrayAdapter<T> extends BaseAdapter {
    /* access modifiers changed from: protected */
    public Context d;
    protected ArrayList<T> e = new ArrayList<>();
    protected boolean f;

    public abstract View a(Context context, int i, T t, ViewGroup viewGroup);

    public abstract void a(View view, int i, T t);

    public ArrayAdapter(Context context) {
        this.d = context;
        this.f = false;
    }

    public void a(ArrayList<T> arrayList) {
        if (arrayList != null) {
            this.f = true;
            this.e.addAll(arrayList);
            notifyDataSetChanged();
            return;
        }
        this.f = false;
        notifyDataSetInvalidated();
    }

    public void a(List<T> list) {
        if (list != null) {
            this.f = true;
            this.e.clear();
            this.e.addAll(list);
            notifyDataSetChanged();
            return;
        }
        this.f = false;
        notifyDataSetInvalidated();
    }

    public void c(ArrayList<T> arrayList) {
        this.e.addAll(arrayList);
        notifyDataSetChanged();
    }

    public ArrayList<T> a() {
        ArrayList<T> arrayList = new ArrayList<>();
        if (this.e != null) {
            arrayList.addAll(this.e);
        }
        return arrayList;
    }

    public int getCount() {
        if (!this.f || this.e == null) {
            return 0;
        }
        return this.e.size();
    }

    public Object getItem(int i) {
        if (!this.f || this.e == null) {
            return null;
        }
        return this.e.get(i);
    }

    public long getItemId(int i) {
        if (!this.f || this.e == null) {
            return 0;
        }
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f) {
            throw new IllegalStateException("this should only be called when the data is valid");
        } else if (i < 0 || i >= this.e.size()) {
            throw new IllegalStateException("couldn't get view at this position " + i);
        } else {
            T t = this.e.get(i);
            if (view == null) {
                view = a(this.d, i, t, viewGroup);
            }
            a(view, i, t);
            return view;
        }
    }

    public Context b() {
        return this.d;
    }

    public void c() {
        if (this.e != null) {
            this.e.clear();
        }
    }
}
