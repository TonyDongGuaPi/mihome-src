package com.xiaomi.mishopsdk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class FragmentPagerAdapter extends PagerAdapter implements View.OnClickListener {
    private FragmentTransaction mCurrentTransaction;
    private FragmentManager mFragmentManager;
    private final List<Fragment> mFragments = new ArrayList();
    private TabChangedListener mTabChangedListener;
    private final List<View> mTabs = new ArrayList();

    public interface TabChangedListener {
        void onTabChanged(int i);
    }

    public void setTabChangedListener(TabChangedListener tabChangedListener) {
        this.mTabChangedListener = tabChangedListener;
    }

    public FragmentPagerAdapter(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    public int getCount() {
        return this.mFragments.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    public Fragment getItem(int i) {
        if (i < 0 || i >= this.mFragments.size()) {
            return null;
        }
        return this.mFragments.get(i);
    }

    public void addFragment(View view, Fragment fragment) {
        this.mFragments.add(fragment);
        this.mTabs.add(view);
        view.setOnClickListener(this);
    }

    public void finishUpdate(ViewGroup viewGroup) {
        if (this.mCurrentTransaction != null) {
            this.mCurrentTransaction.commitAllowingStateLoss();
            this.mCurrentTransaction = null;
            this.mFragmentManager.executePendingTransactions();
        }
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (this.mCurrentTransaction == null) {
            this.mCurrentTransaction = this.mFragmentManager.beginTransaction();
        }
        if (obj != null) {
            this.mCurrentTransaction.detach((Fragment) obj);
        }
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (this.mCurrentTransaction == null) {
            this.mCurrentTransaction = this.mFragmentManager.beginTransaction();
        }
        Fragment item = getItem(i);
        if (item != null) {
            this.mCurrentTransaction.attach(item);
        }
        return item;
    }

    public String getTagByPosition(int i) {
        if (i < 0 || i > this.mFragments.size()) {
            return null;
        }
        return this.mFragments.get(i).getTag();
    }

    public int getPositionByTag(String str) {
        for (int i = 0; i < this.mFragments.size(); i++) {
            if (TextUtils.equals(str, this.mFragments.get(i).getTag())) {
                return i;
            }
        }
        return 0;
    }

    public View getTab(int i) {
        if (i < 0 || i >= this.mTabs.size()) {
            return null;
        }
        return this.mTabs.get(i);
    }

    public int getTabIndex(View view) {
        return this.mTabs.indexOf(view);
    }

    public void selectTab(int i) {
        for (int i2 = 0; i2 < this.mTabs.size(); i2++) {
            if (i == i2) {
                this.mTabs.get(i2).setSelected(true);
            } else {
                this.mTabs.get(i2).setSelected(false);
            }
        }
    }

    public void onClick(View view) {
        int i = 0;
        while (true) {
            if (i >= this.mTabs.size()) {
                break;
            } else if (this.mTabs.get(i) != view) {
                i++;
            } else if (this.mTabChangedListener != null) {
                this.mTabChangedListener.onTabChanged(i);
            }
        }
        selectTab(i);
    }

    public int getItemPosition(Object obj) {
        return getFragmentPosition((Fragment) obj) != -2 ? -1 : -2;
    }

    public int getFragmentPosition(Fragment fragment) {
        for (int i = 0; i < this.mFragments.size(); i++) {
            if (TextUtils.equals(fragment.getTag(), this.mFragments.get(i).getTag())) {
                return i;
            }
        }
        return -2;
    }

    public void replaceFragment(ViewPager viewPager, Fragment fragment, Fragment fragment2, String str) {
        startUpdate((ViewGroup) viewPager);
        if (this.mCurrentTransaction == null) {
            this.mCurrentTransaction = this.mFragmentManager.beginTransaction();
        }
        this.mFragments.set(getFragmentPosition(fragment), fragment2);
        if (fragment != null) {
            this.mCurrentTransaction.remove(fragment);
        }
        if (fragment2 != null) {
            this.mCurrentTransaction.add(viewPager.getId(), fragment2, str);
        }
        finishUpdate(viewPager);
        this.mCurrentTransaction = null;
        notifyDataSetChanged();
    }
}
