package com.xiaomi.shopviews.widget.homehorizontaltab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.xiaomi.base.utils.ArrayUtils;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.smarttab.ISmartTabMediator;
import com.xiaomi.shopviews.widget.smarttab.SmartTabLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class HorizontalViewPagerSlideTab extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13263a = "HorizontalViewPagerSlideTab";
    /* access modifiers changed from: private */
    public OnSlideTabItemSelecteListener b;
    /* access modifiers changed from: private */
    public SmartTabLayout c;
    /* access modifiers changed from: private */
    public SmartTabMediator d = new SmartTabMediator();
    /* access modifiers changed from: private */
    public LinkedHashMap<String, Integer> e = new LinkedHashMap<>();
    private SparseArrayCompat<String> f = new SparseArrayCompat<>();

    public interface OnSlideTabItemSelecteListener {
        void a(int i);
    }

    public HorizontalViewPagerSlideTab(Context context) {
        super(context);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.listitem_category_slidetab, this);
        SmartTabLayout smartTabLayout = (SmartTabLayout) findViewById(R.id.listitem_category_slidetab_smarttablayout);
        this.c = smartTabLayout;
        smartTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.transparent));
    }

    public void bind(ArrayList<HomeSectionItem> arrayList) {
        if (ArrayUtils.a((ArrayList<?>) arrayList) || arrayList.size() > 4) {
            this.c.setDefaultTextMinWidth(ScreenInfo.a().e() / 4);
        } else {
            this.c.setDefaultTextMinWidth(ScreenInfo.a().e() / arrayList.size());
        }
        this.e.clear();
        this.f.clear();
        int size = arrayList.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            String str = arrayList.get(i2).mTitle;
            this.e.put(str, Integer.valueOf(i2));
            this.f.put(i, str);
            i++;
        }
        this.d.a(this.f);
    }

    public SmartTabMediator getSmartTabMediator() {
        return this.d;
    }

    public void setDoAnim(boolean z) {
        this.c.setDoStartAnim(z);
    }

    public void setOnSlideTabItemSelecteListener(OnSlideTabItemSelecteListener onSlideTabItemSelecteListener) {
        this.b = onSlideTabItemSelecteListener;
    }

    public void setSelectedItemByTitle(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (int i = 0; i < this.f.size(); i++) {
                int keyAt = this.f.keyAt(i);
                if (str.equals(this.f.get(keyAt))) {
                    this.d.c(keyAt);
                    return;
                }
            }
        }
    }

    public void setTitleColor(int i, int i2) {
        this.c.setDefaultTabTextColor(new ColorStateList(new int[][]{new int[]{16842913}, new int[0]}, new int[]{i2, i}));
    }

    public class SmartTabMediator implements ISmartTabMediator {

        /* renamed from: a  reason: collision with root package name */
        int f13264a;
        ViewPager.OnPageChangeListener b;
        public SparseArrayCompat<String> c;

        public SmartTabMediator() {
        }

        public void a(ViewPager.OnPageChangeListener onPageChangeListener) {
            this.b = onPageChangeListener;
        }

        public int a() {
            return this.c.size();
        }

        public int b() {
            return this.f13264a;
        }

        public CharSequence a(int i) {
            return this.c.get(i);
        }

        public void a(SparseArrayCompat<String> sparseArrayCompat) {
            this.c = sparseArrayCompat;
            HorizontalViewPagerSlideTab.this.c.setMediator(HorizontalViewPagerSlideTab.this.d);
        }

        public boolean c() {
            return this.c == null && this.c.size() <= 0;
        }

        public void d() {
            b(Math.min(this.f13264a + 1, this.c.size() - 1));
        }

        public void e() {
            b(Math.max(this.f13264a - 1, 0));
        }

        public void b(int i) {
            this.f13264a = i;
            if (HorizontalViewPagerSlideTab.this.b != null) {
                HorizontalViewPagerSlideTab.this.b.a(((Integer) HorizontalViewPagerSlideTab.this.e.get(this.c.get(i))).intValue());
            }
            if (this.b != null) {
                this.b.onPageSelected(i);
            }
        }

        public void c(int i) {
            this.f13264a = i;
            if (this.b != null) {
                this.b.onPageSelected(i);
            }
        }
    }
}
