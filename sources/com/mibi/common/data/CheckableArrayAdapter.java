package com.mibi.common.data;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;

public abstract class CheckableArrayAdapter<T> extends ArrayAdapter<T> {
    protected int c = -1;
    private OnItemSelectedListener<T> d;

    public interface OnItemSelectedListener<Type> {
        void a(Type type);
    }

    public abstract void a(View view, int i, T t, boolean z);

    public CheckableArrayAdapter(Context context) {
        super(context);
    }

    public final void a(View view, int i, T t) {
        a(view, i, t, this.c != -1 && this.c == i);
    }

    public void a(ArrayList<T> arrayList, boolean z) {
        this.c = -1;
        super.a(arrayList, z);
    }

    public void b(T t) {
        int a2 = a(t);
        if (-1 != a2) {
            a(a2);
        }
    }

    public void a(int i) {
        if (i < 0 || i >= getCount()) {
            i = -1;
        }
        this.c = i;
        notifyDataSetChanged();
        if (this.d != null) {
            this.d.a(c());
        }
    }

    public void b() {
        this.c = -1;
        notifyDataSetChanged();
    }

    public T c() {
        if (this.c == -1) {
            return null;
        }
        return getItem(this.c);
    }

    public int d() {
        return this.c;
    }

    public void a(OnItemSelectedListener<T> onItemSelectedListener) {
        this.d = onItemSelectedListener;
    }
}
