package com.xiaomi.mishopsdk.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class BasePageAdapter<T> extends PagerAdapter {
    private Context mContext;
    public ArrayList<T> mData = new ArrayList<>();
    private HashMap<Integer, View> mViews = new HashMap<>();

    public abstract void bindView(View view, int i, T t);

    public abstract View newView(Context context, T t, ViewGroup viewGroup);

    public BasePageAdapter(Context context) {
        this.mContext = context;
    }

    public void updateData(ArrayList<T> arrayList) {
        if (arrayList != null) {
            this.mData = arrayList;
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        return this.mData.size();
    }

    public T getData(int i) {
        return this.mData.get(i);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return ((View) obj) == view;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = i < this.mViews.size() ? this.mViews.get(Integer.valueOf(i)) : null;
        Object data = getData(i);
        if (view == null) {
            view = newView(this.mContext, data, viewGroup);
            this.mViews.put(Integer.valueOf(i), view);
        }
        viewGroup.addView(view);
        bindView(view, i, data);
        view.setId(i);
        view.setTag(data);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ((ViewPager) viewGroup).removeView(this.mViews.get(Integer.valueOf(i)));
    }
}
