package com.xiaomi.shopviews.adapter.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class BasePageAdapter<T> extends PagerAdapter {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<T> f13068a = new ArrayList<>();
    private Context b;
    private HashMap<Integer, View> c = new HashMap<>();

    public abstract View a(Context context, T t, ViewGroup viewGroup);

    public abstract void a(View view, int i, T t);

    /* access modifiers changed from: protected */
    public boolean a() {
        return true;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return obj == view;
    }

    public BasePageAdapter(Context context) {
        this.b = context;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ((ViewPager) viewGroup).removeView(this.c.get(Integer.valueOf(i)));
    }

    public int getCount() {
        return this.f13068a.size();
    }

    public T a(int i) {
        return this.f13068a.get(i);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = i < this.c.size() ? this.c.get(Integer.valueOf(i)) : null;
        Object a2 = a(i);
        if (view == null) {
            view = a(this.b, a2, viewGroup);
            this.c.put(Integer.valueOf(i), view);
        }
        viewGroup.addView(view);
        a();
        a(view, i, a2);
        view.setId(i);
        view.setTag(a2);
        return view;
    }

    public void a(ArrayList<T> arrayList) {
        if (arrayList != null) {
            this.f13068a = arrayList;
            notifyDataSetChanged();
        }
    }
}
