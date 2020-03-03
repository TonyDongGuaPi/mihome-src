package com.xiaomi.smarthome.notificationquickop.views;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.notificationquickop.NotiQuickOpManager;
import com.xiaomi.smarthome.shop.view.LoopViewPager;
import java.util.List;

public class LoopBannerView extends FrameLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f20988a = "LoopBannerView";
    private static final int b = 6;
    private static final int c = 3;
    private int d = 0;
    private int e = 0;
    private LoopViewPager f;
    private LoopAdapter g;
    /* access modifiers changed from: private */
    public LayoutInflater h;

    public LoopBannerView(Context context) {
        super(context);
        a(context);
    }

    public LoopBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public LoopBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.d = (int) TypedValue.applyDimension(1, 6.0f, displayMetrics);
        this.e = (int) TypedValue.applyDimension(1, 3.0f, displayMetrics);
        this.h = LayoutInflater.from(context);
        this.h.inflate(R.layout.notification_quick_op_vp, this);
        this.f = (LoopViewPager) findViewById(R.id.view_pager);
        this.g = new LoopAdapter();
        this.f.setAdapter(this.g);
        this.f.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
            }
        });
        this.g.notifyDataSetChanged();
    }

    public void setViewPagerHeight(int i) {
        ViewGroup.LayoutParams layoutParams = this.f.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = i;
            this.f.setLayoutParams(layoutParams);
        }
    }

    public void enableAutoTurn() {
        this.f.startAutoScroll();
    }

    public void enableAutoTurn(long j) {
        this.f.startAutoScroll(j);
    }

    public void disableAutoTrun() {
        this.f.stopAutoScroll();
    }

    public void update(List<NotiQuickOpManager.QuickOpItem> list, int i) {
        this.f.setOffscreenPageLimit(list.size() + 2);
        this.g.a(list);
        this.g.notifyDataSetChanged();
        this.f.setCurrentItem(i, false);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        enableAutoTurn();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        disableAutoTrun();
    }

    private class LoopAdapter extends PagerAdapter {

        /* renamed from: a  reason: collision with root package name */
        List<NotiQuickOpManager.QuickOpItem> f20990a;

        public int getItemPosition(Object obj) {
            return -2;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private LoopAdapter() {
        }

        public void a(List<NotiQuickOpManager.QuickOpItem> list) {
            this.f20990a = list;
        }

        public int getCount() {
            if (this.f20990a == null) {
                return 0;
            }
            return this.f20990a.size();
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            String access$100 = LoopBannerView.f20988a;
            Miio.h(access$100, "instantiateItem pos: " + i);
            View inflate = LoopBannerView.this.h.inflate(R.layout.notification_quick_op_item_view, (ViewGroup) null);
            NotiQuickOpManager.QuickOpItem quickOpItem = this.f20990a.get(i);
            TextView textView = (TextView) inflate.findViewById(R.id.title);
            TextView textView2 = (TextView) inflate.findViewById(R.id.sub_title);
            inflate.setTag(Integer.valueOf(i));
            viewGroup.addView(inflate, 0);
            return inflate;
        }
    }
}
