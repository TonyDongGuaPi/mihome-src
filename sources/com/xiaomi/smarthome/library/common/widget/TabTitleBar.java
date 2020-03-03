package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

public class TabTitleBar extends LinearLayout implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private FragmentActivity f18955a;
    private ViewPager b;
    private int c;
    List<TabBaseFragment> mFragments;
    List<View> mTitles;

    public TabTitleBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public TabTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTitles = new ArrayList();
        this.mFragments = new ArrayList();
        this.c = -1;
    }

    public void initialize(FragmentActivity fragmentActivity, ViewPager viewPager) {
        this.f18955a = fragmentActivity;
        this.b = viewPager;
        this.b.setAdapter(new TabPageAdapter(this.f18955a.getSupportFragmentManager()));
        this.b.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                TabTitleBar.this.setSelectedTab(i);
            }
        });
    }

    public void setSelectedTab(int i) {
        if (this.c != i) {
            View currentTab = getCurrentTab();
            if (currentTab != null) {
                currentTab.setSelected(false);
            }
            this.c = i;
            getCurrentTab().setSelected(true);
        }
    }

    public void addFragment(View view, TabBaseFragment tabBaseFragment) {
        this.mTitles.add(view);
        this.mFragments.add(tabBaseFragment);
        this.b.getAdapter().notifyDataSetChanged();
        view.setTag(Integer.valueOf(this.mTitles.size() - 1));
        view.setOnClickListener(this);
    }

    public int getTabCount() {
        return this.mFragments.size();
    }

    public int getCurrentTabIndex() {
        return this.c;
    }

    public TabBaseFragment getCurrentFragment() {
        return getFragmentAt(this.c);
    }

    public TabBaseFragment getFragmentAt(int i) {
        if (i < 0 || i >= this.mFragments.size()) {
            return null;
        }
        return this.mFragments.get(i);
    }

    public View getCurrentTab() {
        return getTabAt(this.c);
    }

    public View getTabAt(int i) {
        if (i < 0 || i >= this.mTitles.size()) {
            return null;
        }
        return this.mTitles.get(i);
    }

    public void onClick(View view) {
        Integer num = (Integer) view.getTag();
        if (num != null) {
            this.b.setCurrentItem(num.intValue(), true);
        }
    }

    class TabPageAdapter extends FragmentPagerAdapter {
        public TabPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            if (TabTitleBar.this.mFragments == null || TabTitleBar.this.mFragments.size() == 0) {
                return null;
            }
            return TabTitleBar.this.mFragments.get(i);
        }

        public int getCount() {
            if (TabTitleBar.this.mFragments == null) {
                return 0;
            }
            return TabTitleBar.this.mFragments.size();
        }
    }
}
