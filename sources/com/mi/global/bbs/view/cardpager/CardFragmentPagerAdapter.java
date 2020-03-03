package com.mi.global.bbs.view.cardpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class CardFragmentPagerAdapter<T> extends FragmentStatePagerAdapter implements CardAdapter {
    private List<CardFragment> mCardFragments = new ArrayList();
    private List<T> mData = new ArrayList();
    private float mElevation;
    private float mFloatElevation;
    private List<ViewHolder<T>> mViewHolders = new ArrayList();
    private int realSize;
    private int size;

    public CardFragmentPagerAdapter(FragmentManager fragmentManager, List<ViewHolder<T>> list, List<T> list2, int i, float f, float f2, boolean z) {
        super(fragmentManager);
        if (list.size() != list2.size()) {
            new Exception("view holders and datas size not same").printStackTrace();
        }
        this.realSize = list2.size();
        if (!z || this.realSize <= 1) {
            this.mViewHolders = list;
            this.mData = list2;
            this.size = this.realSize;
        } else {
            this.mViewHolders.add(list.get(this.realSize - 2));
            this.mViewHolders.add(list.get(this.realSize - 1));
            this.mViewHolders.addAll(list);
            this.mViewHolders.add(list.get(0));
            this.mViewHolders.add(list.get(1));
            T t = list2.get(this.realSize - 2);
            T t2 = list2.get(this.realSize - 1);
            T t3 = list2.get(0);
            T t4 = list2.get(1);
            this.mData.add(0, t);
            this.mData.add(1, t2);
            this.mData.addAll(list2);
            this.mData.add(this.realSize + 2, t3);
            this.mData.add(t4);
            this.size = this.realSize + 4;
        }
        this.mElevation = f;
        this.mFloatElevation = f2;
        for (int i2 = 0; i2 < this.size; i2++) {
            CardFragment newInstance = CardFragment.newInstance();
            newInstance.setCardContentView(this.mViewHolders.get(i2), this.mData.get(i2));
            newInstance.setCardMargin(i);
            this.mCardFragments.add(newInstance);
        }
    }

    public Fragment getItem(int i) {
        return this.mCardFragments.get(i);
    }

    public int getCount() {
        return this.size;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        return super.instantiateItem(viewGroup, i);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyItem(viewGroup, i, obj);
    }

    public float getBaseElevation() {
        return this.mElevation;
    }

    public float getFloatElevation() {
        return this.mFloatElevation;
    }

    public CardView getCardView(int i) {
        return this.mCardFragments.get(i).getCardView();
    }
}
