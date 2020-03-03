package com.mi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class ArrayAdapter<T> extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    protected Context f6732a;
    protected ArrayList<T> b = new ArrayList<>();
    protected boolean c;

    public abstract View a(Context context, int i, T t, ViewGroup viewGroup);

    public abstract void a(View view, int i, T t);

    public ArrayAdapter(Context context) {
        this.f6732a = context;
        this.c = false;
    }

    public void a(ArrayList<T> arrayList) {
        if (arrayList != null) {
            this.c = true;
            this.b.addAll(arrayList);
            notifyDataSetChanged();
            return;
        }
        this.c = false;
        notifyDataSetInvalidated();
    }

    public void a(List<T> list) {
        if (list != null) {
            this.c = true;
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
            return;
        }
        this.c = false;
        notifyDataSetInvalidated();
    }

    public void b(ArrayList<T> arrayList) {
        this.b.addAll(arrayList);
        notifyDataSetChanged();
    }

    public ArrayList<T> a() {
        ArrayList<T> arrayList = new ArrayList<>();
        if (this.b != null) {
            arrayList.addAll(this.b);
        }
        return arrayList;
    }

    public int getCount() {
        if (!this.c || this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public Object getItem(int i) {
        if (!this.c || this.b == null) {
            return null;
        }
        return this.b.get(i);
    }

    public long getItemId(int i) {
        if (!this.c || this.b == null) {
            return 0;
        }
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.c) {
            throw new IllegalStateException("this should only be called when the data is valid");
        } else if (i < 0 || i >= this.b.size()) {
            throw new IllegalStateException("couldn't get view at this position " + i);
        } else {
            T t = this.b.get(i);
            if (view == null) {
                view = a(this.f6732a, i, t, viewGroup);
            }
            a(view, i, t);
            return view;
        }
    }

    public Context b() {
        return this.f6732a;
    }

    public void c() {
        if (this.b != null) {
            this.b.clear();
        }
    }
}
