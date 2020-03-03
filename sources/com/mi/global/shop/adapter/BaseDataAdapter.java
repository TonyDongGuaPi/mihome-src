package com.mi.global.shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.mi.log.LogUtil;
import java.util.ArrayList;

public abstract class BaseDataAdapter<T> extends BaseAdapter {
    private static final String d = "BaseDataAdapter";

    /* renamed from: a  reason: collision with root package name */
    protected Context f5484a;
    protected ArrayList<T> b;
    protected boolean c = false;

    public abstract View a(Context context, T t, ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    @SuppressLint({"ResourceAsColor"})
    public void a(View view, int i) {
    }

    public abstract void a(View view, int i, T t);

    public BaseDataAdapter(Context context) {
        this.f5484a = context;
    }

    public void a(ArrayList<T> arrayList) {
        if (arrayList != null) {
            this.c = true;
            this.b = arrayList;
            notifyDataSetChanged();
            return;
        }
        this.c = false;
        notifyDataSetInvalidated();
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
        LogUtil.b(d, "getView: " + i);
        if (!this.c) {
            throw new IllegalStateException("this should only be called when the data is valid");
        } else if (i < 0 || i >= this.b.size()) {
            throw new IllegalStateException("couldn't get view at this position " + i);
        } else {
            T t = this.b.get(i);
            if (view == null) {
                view = a(this.f5484a, t, viewGroup, i);
            }
            a(view, i, t);
            return view;
        }
    }

    /* access modifiers changed from: protected */
    public View a(Context context, T t, ViewGroup viewGroup, int i) {
        return a(context, t, viewGroup);
    }
}
