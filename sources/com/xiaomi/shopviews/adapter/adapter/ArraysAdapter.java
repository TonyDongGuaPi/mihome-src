package com.xiaomi.shopviews.adapter.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import com.xiaomi.base.utils.ExposeHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SuppressLint({"WrongConstant"})
public abstract class ArraysAdapter<T> extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    private Context f13065a;
    private ArraysAdapter<T>.ArrayFilter b;
    private LayoutInflater c;
    private final Object d = new Object();
    private boolean e = true;
    /* access modifiers changed from: private */
    public List<T> f = new ArrayList();
    private ArrayList<T> g;

    /* access modifiers changed from: protected */
    public abstract View a(int i, T t, ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    public abstract void a(View view, int i, T t);

    /* access modifiers changed from: protected */
    public boolean d() {
        return true;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public ArraysAdapter(Context context) {
        this.f13065a = context;
        this.c = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public void a(T t) {
        synchronized (this.d) {
            if (this.g != null) {
                this.g.add(t);
            } else {
                this.f.add(t);
            }
            if (this.e) {
                notifyDataSetChanged();
            }
        }
    }

    public void a(Collection<? extends T> collection) {
        synchronized (this.d) {
            if (this.g != null) {
                this.g.addAll(collection);
            } else {
                this.f.addAll(collection);
            }
            if (this.e) {
                notifyDataSetChanged();
            }
        }
    }

    public void a(T... tArr) {
        synchronized (this.d) {
            if (this.g != null) {
                Collections.addAll(this.g, tArr);
            } else {
                Collections.addAll(this.f, tArr);
            }
            if (this.e) {
                notifyDataSetChanged();
            }
        }
    }

    public void a() {
        synchronized (this.d) {
            if (this.g != null) {
                this.g.clear();
            } else {
                this.f.clear();
            }
            if (this.e) {
                notifyDataSetChanged();
            }
        }
    }

    public Context b() {
        return this.f13065a;
    }

    public int getCount() {
        return this.f.size();
    }

    public Filter c() {
        if (this.b == null) {
            this.b = new ArrayFilter();
        }
        return this.b;
    }

    public T getItem(int i) {
        return this.f.get(i);
    }

    public int b(T t) {
        return this.f.indexOf(t);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i < 0 || i >= this.f.size()) {
            throw new IllegalStateException("couldn't get view at this position " + i);
        }
        T t = this.f.get(i);
        if (view == null) {
            view = a(i, t, viewGroup);
        }
        if (d()) {
            ExposeHelper.a().a(t, i);
        }
        a(view, i, t);
        return view;
    }

    public void a(T t, int i) {
        synchronized (this.d) {
            if (this.g != null) {
                this.g.add(i, t);
            } else {
                this.f.add(i, t);
            }
            if (this.e) {
                notifyDataSetChanged();
            }
        }
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.e = true;
    }

    public void c(T t) {
        synchronized (this.d) {
            if (this.g != null) {
                this.g.remove(t);
            } else {
                this.f.remove(t);
            }
            if (this.e) {
                notifyDataSetChanged();
            }
        }
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void a(Comparator<? super T> comparator) {
        synchronized (this.d) {
            if (this.g != null) {
                Collections.sort(this.g, comparator);
            } else {
                Collections.sort(this.f, comparator);
            }
            if (this.e) {
                notifyDataSetChanged();
            }
        }
    }

    private class ArrayFilter extends Filter {
        private ArrayFilter() {
        }

        /* access modifiers changed from: protected */
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            List unused = ArraysAdapter.this.f = (List) filterResults.values;
            if (filterResults.count > 0) {
                ArraysAdapter.this.notifyDataSetChanged();
            } else {
                ArraysAdapter.this.notifyDataSetInvalidated();
            }
        }
    }
}
