package com.ximalaya.ting.android.sdkdownloader.downloadutil.viewholder;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import java.util.List;

public abstract class AbstractAdapter<T> extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    protected Context f2351a;
    protected List<T> b;
    protected LayoutInflater c;

    public long getItemId(int i) {
        return (long) i;
    }

    public AbstractAdapter(Context context, List<T> list) {
        this.f2351a = context;
        this.b = list;
        this.c = LayoutInflater.from(context);
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.b == null || getCount() <= 0 || i >= this.b.size()) {
            return null;
        }
        return this.b.get(i);
    }

    public void a(T t) {
        int indexOf;
        if (this.b != null && (indexOf = this.b.indexOf(t)) >= 0) {
            this.b.set(indexOf, t);
        }
    }

    public int b(T t) {
        if (this.b != null) {
            return this.b.indexOf(t);
        }
        return -1;
    }

    public boolean c(T t) {
        if (this.b == null) {
            return false;
        }
        return this.b.contains(t);
    }

    public List<T> a() {
        return this.b;
    }

    public void a(List<T> list) {
        this.b = list;
    }

    public void b(List<T> list) {
        if (this.b == null) {
            this.b = list;
        } else {
            this.b.addAll(list);
        }
    }

    public void c(List<T> list) {
        if (this.b == null) {
            this.b = list;
        } else {
            this.b.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void a(int i) {
        if (this.b != null && this.b.size() > i) {
            this.b.remove(i);
            notifyDataSetChanged();
        }
    }

    public void d(T t) {
        if (this.b != null) {
            this.b.remove(t);
            notifyDataSetChanged();
        }
    }

    public void d(List<T> list) {
        if (this.b != null) {
            this.b.removeAll(list);
            notifyDataSetChanged();
        }
    }

    public void b() {
        if (this.b != null) {
            this.b.clear();
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        if (a("notifyDataSetChanged")) {
            super.notifyDataSetChanged();
        }
    }

    public void notifyDataSetInvalidated() {
        if (a("notifyDataSetInvalidated")) {
            super.notifyDataSetInvalidated();
        }
    }

    private boolean a(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return true;
        }
        Log.e("ERROR", "不能在线程中调用" + str + "方法" + getClass().getName());
        return false;
    }
}
