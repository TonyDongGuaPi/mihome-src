package com.mipay.common.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public abstract class a<T> extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    protected Context f8124a;
    protected ArrayList<T> b = null;

    public a(Context context) {
        this.f8124a = context;
    }

    public abstract void bindView(View view, int i, T t);

    public int getCount() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public ArrayList<T> getData() {
        return this.b;
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
                view = newView(this.f8124a, i, t, viewGroup);
            }
            bindView(view, i, t);
            return view;
        }
    }

    public abstract View newView(Context context, int i, T t, ViewGroup viewGroup);

    public void updateData(ArrayList<T> arrayList) {
        if (arrayList != null) {
            this.b = arrayList;
            notifyDataSetChanged();
            return;
        }
        this.b = null;
        notifyDataSetInvalidated();
    }

    public void updateData(ArrayList<T> arrayList, boolean z) {
        if (arrayList != null) {
            if (this.b == null) {
                this.b = arrayList;
            } else {
                this.b.addAll(arrayList);
            }
            notifyDataSetChanged();
        }
    }
}
