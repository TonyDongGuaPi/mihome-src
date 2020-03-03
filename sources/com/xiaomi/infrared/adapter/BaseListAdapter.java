package com.xiaomi.infrared.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public abstract class BaseListAdapter<T> extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    protected Context f10217a;
    protected List<T> b;

    public abstract int a(int i);

    public abstract void a(ViewHolder viewHolder, T t, int i);

    public long getItemId(int i) {
        return (long) i;
    }

    public BaseListAdapter(Context context, List<T> list) {
        this.f10217a = context;
        this.b = list;
    }

    public int getCount() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public T getItem(int i) {
        if (i < this.b.size()) {
            return this.b.get(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = a(getItemViewType(i), viewGroup);
            viewHolder = ViewHolder.a(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        a(viewHolder, getItem(i), i);
        return view;
    }

    public View a(int i, ViewGroup viewGroup) {
        return LayoutInflater.from(this.f10217a).inflate(a(i), viewGroup, false);
    }

    public void a(List<T> list) {
        if (list != null) {
            this.b = list;
            notifyDataSetChanged();
        }
    }
}
