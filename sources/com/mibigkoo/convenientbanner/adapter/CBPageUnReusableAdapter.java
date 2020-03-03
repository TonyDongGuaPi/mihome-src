package com.mibigkoo.convenientbanner.adapter;

import android.view.View;
import android.view.ViewGroup;
import com.mibigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.mibigkoo.convenientbanner.holder.Holder;
import com.mibigkoo.convenientbanner.view.CBLoopViewPager;
import com.xiaomi.smarthome.R;
import java.util.ArrayList;
import java.util.List;

public class CBPageUnReusableAdapter<T> extends CBPageAdapter {
    protected List<T> c;
    protected CBViewUnReusableHolderCreator d;
    private boolean e = true;
    private CBLoopViewPager f;
    private final int g = 300;
    private List<View> h = new ArrayList();

    public interface CBViewUnReusableHolderCreator<Holder> {
        Holder a(int i);
    }

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
        return this.e ? a() * 300 : a();
    }

    public int a() {
        if (this.c == null) {
            return 0;
        }
        return this.c.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View a2 = a(a(i), (View) null, viewGroup);
        if (a2.getParent() == null) {
            viewGroup.addView(a2);
        }
        return a2;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public void finishUpdate(ViewGroup viewGroup) {
        int currentItem = this.f.getCurrentItem();
        if (currentItem == 0) {
            currentItem = this.f.getFristItem();
        } else if (currentItem == getCount() - 1) {
            currentItem = this.f.getLastItem();
        }
        try {
            this.f.setCurrentItem(currentItem, false);
        } catch (IllegalStateException unused) {
        }
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void a(CBLoopViewPager cBLoopViewPager) {
        this.f = cBLoopViewPager;
    }

    public CBPageUnReusableAdapter(CBViewUnReusableHolderCreator cBViewUnReusableHolderCreator, List<T> list) {
        super(new CBViewHolderCreator() {
            public Object createHolder() {
                return null;
            }
        }, list);
        this.d = cBViewUnReusableHolderCreator;
        this.c = list;
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        View view2;
        if (view == null) {
            holder = (Holder) this.d.a(i);
            view2 = holder.a(viewGroup.getContext(), viewGroup);
            view2.setTag(R.id.cb_item_tag, holder);
        } else {
            view2 = view;
            holder = (Holder) view.getTag(R.id.cb_item_tag);
        }
        if (this.c != null && !this.c.isEmpty()) {
            holder.a(viewGroup.getContext(), i, this.c.get(i));
        }
        return view2;
    }
}
