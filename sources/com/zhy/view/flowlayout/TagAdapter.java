package com.zhy.view.flowlayout;

import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class TagAdapter<T> {

    /* renamed from: a  reason: collision with root package name */
    private List<T> f2570a;
    private OnDataChangedListener b;
    private HashSet<Integer> c = new HashSet<>();

    interface OnDataChangedListener {
        void onChanged();
    }

    public abstract View a(FlowLayout flowLayout, int i, T t);

    public boolean a(int i, T t) {
        return false;
    }

    public TagAdapter(List<T> list) {
        this.f2570a = list;
    }

    public TagAdapter(T[] tArr) {
        this.f2570a = new ArrayList(Arrays.asList(tArr));
    }

    /* access modifiers changed from: package-private */
    public void a(OnDataChangedListener onDataChangedListener) {
        this.b = onDataChangedListener;
    }

    public void a(int... iArr) {
        for (int valueOf : iArr) {
            this.c.add(Integer.valueOf(valueOf));
        }
        c();
    }

    public void a(Set<Integer> set) {
        this.c.clear();
        if (set != null) {
            this.c.addAll(set);
        }
        c();
    }

    /* access modifiers changed from: package-private */
    public HashSet<Integer> a() {
        return this.c;
    }

    public int b() {
        if (this.f2570a == null) {
            return 0;
        }
        return this.f2570a.size();
    }

    public void c() {
        this.b.onChanged();
    }

    public T a(int i) {
        return this.f2570a.get(i);
    }
}
