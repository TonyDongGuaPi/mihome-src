package com.xiaomi.smarthome.newui.widget.topnavi.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.MagicIndicator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.abs.IPagerNavigator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.CommonNavigator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import java.util.ArrayList;
import java.util.List;

public class PageIndicator extends MagicIndicator {

    /* renamed from: a  reason: collision with root package name */
    private MyAdapter f20953a;
    /* access modifiers changed from: private */
    public ViewPager b;
    /* access modifiers changed from: private */
    public boolean c;

    public PageIndicator(Context context) {
        this(context, (AttributeSet) null);
    }

    public PageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        CommonNavigator commonNavigator = new CommonNavigator(context);
        MyAdapter myAdapter = new MyAdapter();
        this.f20953a = myAdapter;
        commonNavigator.setAdapter(myAdapter);
        commonNavigator.setCompactMode(true);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 80;
        commonNavigator.setLayoutParams(layoutParams);
        setNavigator(commonNavigator);
        this.c = DarkModeCompat.a(context);
    }

    public void attachViewPager(ViewPager viewPager) {
        this.b = viewPager;
    }

    public void refreshData(List<Integer> list) {
        if (this.f20953a != null) {
            this.f20953a.a(list);
        }
    }

    public int getCurrentSelected() {
        if (getNavigator() instanceof CommonNavigator) {
            return ((CommonNavigator) getNavigator()).getCurrentSelected();
        }
        return 0;
    }

    public static class ColorState {

        /* renamed from: a  reason: collision with root package name */
        static final ColorState f20954a = new ColorState(-16559779, 1426280797);
        static final ColorState b = new ColorState(-1, -1996488705);
        int c;
        int d;

        ColorState(int i, int i2) {
            this.c = i;
            this.d = i2;
        }
    }

    class MyAdapter extends CommonNavigatorAdapter {
        private ArrayList<Integer> b = new ArrayList<>();

        public IPagerIndicator a(Context context) {
            return null;
        }

        MyAdapter() {
        }

        public int a() {
            if (this.b == null) {
                return 0;
            }
            return this.b.size();
        }

        public IPagerTitleView a(Context context, final int i) {
            ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
            if (PageIndicator.this.c) {
                colorTransitionPagerTitleView.setNormalColor(ColorState.b.d);
                colorTransitionPagerTitleView.setSelectedColor(ColorState.b.c);
            } else {
                colorTransitionPagerTitleView.setNormalColor(ColorState.f20954a.d);
                colorTransitionPagerTitleView.setSelectedColor(ColorState.f20954a.c);
            }
            colorTransitionPagerTitleView.setText(this.b.get(i).intValue());
            colorTransitionPagerTitleView.setTextSize(1, 16.0f);
            colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (PageIndicator.this.b != null && PageIndicator.this.b.getCurrentItem() != i) {
                        IPagerNavigator navigator = PageIndicator.this.getNavigator();
                        if (navigator instanceof CommonNavigator) {
                            ((CommonNavigator) navigator).setChangeFromClickTab();
                        }
                        PageIndicator.this.b.setCurrentItem(i);
                    }
                }
            });
            int d = DisplayUtils.d(context, 25.0f);
            colorTransitionPagerTitleView.setPadding(d, DisplayUtils.a(9.0f), d, DisplayUtils.a(4.0f));
            colorTransitionPagerTitleView.setIncludeFontPadding(false);
            return colorTransitionPagerTitleView;
        }

        public void a(List<Integer> list) {
            if (!this.b.equals(list)) {
                this.b = new ArrayList<>(list);
                b();
            }
        }
    }
}
