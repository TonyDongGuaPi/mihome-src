package com.mibi.common.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public abstract class ArrayAdapter<T> extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    protected Context f7498a;
    protected ArrayList<T> b = null;

    public abstract View a(Context context, int i, T t, ViewGroup viewGroup);

    public abstract void a(View view, int i, T t);

    public ArrayAdapter(Context context) {
        this.f7498a = context;
    }

    public void a(ArrayList<T> arrayList) {
        if (arrayList != null) {
            this.b = arrayList;
            notifyDataSetChanged();
            return;
        }
        this.b = null;
        notifyDataSetInvalidated();
    }

    public void a(ArrayList<T> arrayList, boolean z) {
        if (arrayList != null) {
            if (this.b == null) {
                this.b = arrayList;
            }
            if (!z) {
                this.b.clear();
            }
            this.b.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    public ArrayList<T> a() {
        return this.b;
    }

    public int a(T t) {
        return this.b.indexOf(t);
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

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (this.b == null) {
            throw new IllegalStateException("this should only be called when the data is valid");
        } else if (i < 0 || i >= this.b.size()) {
            throw new IllegalStateException("couldn't get view at this position " + i);
        } else {
            T t = this.b.get(i);
            if (view == null) {
                view = a(this.f7498a, i, t, viewGroup);
            }
            a(view, i, t);
            return view;
        }
    }
}
