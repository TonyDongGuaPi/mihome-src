package com.xiaomi.smarthome.shop.view;

import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.miio.Miio;

public class LoopPagerAdapterWrapper extends PagerAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22205a = "LoopPagerAdapterWrapper";
    private PagerAdapter b;

    private int c() {
        return 1;
    }

    public int b(int i) {
        return i + 1;
    }

    LoopPagerAdapterWrapper(PagerAdapter pagerAdapter) {
        this.b = pagerAdapter;
    }

    /* access modifiers changed from: package-private */
    public int a(int i) {
        int a2 = a();
        if (a2 == 0) {
            return 0;
        }
        int i2 = (i - 1) % a2;
        return i2 < 0 ? i2 + a2 : i2;
    }

    private int d() {
        return (c() + a()) - 1;
    }

    public int getCount() {
        return this.b.getCount() + 2;
    }

    public int a() {
        return this.b.getCount();
    }

    public PagerAdapter b() {
        return this.b;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        int a2 = a(i);
        String str = f22205a;
        Miio.h(str, "instantiateItem pos: " + a2);
        return this.b.instantiateItem(viewGroup, a2);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        int a2 = a(i);
        String str = f22205a;
        Miio.h(str, "destroyItem pos: " + a2);
        this.b.destroyItem(viewGroup, a2, obj);
    }

    public void finishUpdate(ViewGroup viewGroup) {
        this.b.finishUpdate(viewGroup);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return this.b.isViewFromObject(view, obj);
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        this.b.restoreState(parcelable, classLoader);
    }

    public Parcelable saveState() {
        return this.b.saveState();
    }

    public void startUpdate(ViewGroup viewGroup) {
        this.b.startUpdate(viewGroup);
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        this.b.setPrimaryItem(viewGroup, i, obj);
    }

    public int getItemPosition(Object obj) {
        return this.b.getItemPosition(obj);
    }

    public void notifyDataSetChanged() {
        this.b.notifyDataSetChanged();
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.b.registerDataSetObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.b.unregisterDataSetObserver(dataSetObserver);
    }

    public CharSequence getPageTitle(int i) {
        return this.b.getPageTitle(i);
    }

    public float getPageWidth(int i) {
        return this.b.getPageWidth(i);
    }
}
