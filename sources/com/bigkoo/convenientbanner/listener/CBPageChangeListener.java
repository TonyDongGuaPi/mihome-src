package com.bigkoo.convenientbanner.listener;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import java.util.ArrayList;

public class CBPageChangeListener implements ViewPager.OnPageChangeListener {

    /* renamed from: a  reason: collision with root package name */
    private ArrayList<ImageView> f4792a;
    private int[] b;
    private ViewPager.OnPageChangeListener c;

    public CBPageChangeListener(ArrayList<ImageView> arrayList, int[] iArr) {
        this.f4792a = arrayList;
        this.b = iArr;
    }

    public void onPageScrollStateChanged(int i) {
        if (this.c != null) {
            this.c.onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.c != null) {
            this.c.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        for (int i2 = 0; i2 < this.f4792a.size(); i2++) {
            this.f4792a.get(i).setImageResource(this.b[1]);
            if (i != i2) {
                this.f4792a.get(i2).setImageResource(this.b[0]);
            }
        }
        if (this.c != null) {
            this.c.onPageSelected(i);
        }
    }

    public void a(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.c = onPageChangeListener;
    }
}
