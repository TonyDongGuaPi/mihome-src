package com.mibigkoo.convenientbanner.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.mibigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.mibigkoo.convenientbanner.holder.Holder;
import com.mibigkoo.convenientbanner.view.CBLoopViewPager;
import com.xiaomi.smarthome.R;
import java.util.List;

public class CBPageAdapter<T> extends PagerAdapter {

    /* renamed from: a  reason: collision with root package name */
    protected List<T> f7618a;
    protected CBViewHolderCreator b;
    private boolean c = true;
    private CBLoopViewPager d;
    private final int e = 300;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public int a(int i) {
        int a2 = a();
        if (a2 == 0) {
            return 0;
        }
        return i % a2;
    }

    public int getCount() {
        return this.c ? a() * 300 : a();
    }

    public int a() {
        if (this.f7618a == null) {
            return 0;
        }
        return this.f7618a.size();
    }

    public T b(int i) {
        if (this.f7618a == null || this.f7618a.size() <= 0 || i < 0 || i >= a()) {
            return null;
        }
        return this.f7618a.get(i);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View a2 = a(a(i), (View) null, viewGroup);
        viewGroup.addView(a2);
        return a2;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public void finishUpdate(ViewGroup viewGroup) {
        int currentItem = this.d.getCurrentItem();
        if (currentItem == 0) {
            currentItem = this.d.getFristItem();
        } else if (currentItem == getCount() - 1) {
            currentItem = this.d.getLastItem();
        }
        try {
            this.d.setCurrentItem(currentItem, false);
        } catch (IllegalStateException unused) {
        }
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void a(CBLoopViewPager cBLoopViewPager) {
        this.d = cBLoopViewPager;
    }

    public CBPageAdapter(CBViewHolderCreator cBViewHolderCreator, List<T> list) {
        this.b = cBViewHolderCreator;
        this.f7618a = list;
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        View view2;
        if (view == null) {
            holder = (Holder) this.b.createHolder();
            view2 = holder.a(viewGroup.getContext(), viewGroup);
            view2.setTag(R.id.cb_item_tag, holder);
        } else {
            view2 = view;
            holder = (Holder) view.getTag(R.id.cb_item_tag);
        }
        if (this.f7618a != null && !this.f7618a.isEmpty()) {
            holder.a(viewGroup.getContext(), i, this.f7618a.get(i));
        }
        return view2;
    }
}
