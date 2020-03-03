package com.github.ksoichiro.android.observablescrollview;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

public abstract class CacheFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private static final String STATE_PAGES = "pages";
    private static final String STATE_PAGE_INDEX_PREFIX = "pageIndex:";
    private static final String STATE_PAGE_KEY_PREFIX = "page:";
    private static final String STATE_SUPER_STATE = "superState";
    private FragmentManager mFm;
    private SparseArray<Fragment> mPages = new SparseArray<>();

    /* access modifiers changed from: protected */
    public abstract Fragment createItem(int i);

    public CacheFragmentStatePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFm = fragmentManager;
    }

    public Parcelable saveState() {
        Parcelable saveState = super.saveState();
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_SUPER_STATE, saveState);
        bundle.putInt(STATE_PAGES, this.mPages.size());
        if (this.mPages.size() > 0) {
            for (int i = 0; i < this.mPages.size(); i++) {
                int keyAt = this.mPages.keyAt(i);
                bundle.putInt(createCacheIndex(i), keyAt);
                this.mFm.putFragment(bundle, createCacheKey(keyAt), this.mPages.get(keyAt));
            }
        }
        return bundle;
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        Bundle bundle = (Bundle) parcelable;
        int i = bundle.getInt(STATE_PAGES);
        if (i > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = bundle.getInt(createCacheIndex(i2));
                this.mPages.put(i3, this.mFm.getFragment(bundle, createCacheKey(i3)));
            }
        }
        super.restoreState(bundle.getParcelable(STATE_SUPER_STATE), classLoader);
    }

    public Fragment getItem(int i) {
        Fragment createItem = createItem(i);
        this.mPages.put(i, createItem);
        return createItem;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (this.mPages.indexOfKey(i) >= 0) {
            this.mPages.remove(i);
        }
        super.destroyItem(viewGroup, i, obj);
    }

    public Fragment getItemAt(int i) {
        return this.mPages.get(i);
    }

    /* access modifiers changed from: protected */
    public String createCacheIndex(int i) {
        return STATE_PAGE_INDEX_PREFIX + i;
    }

    /* access modifiers changed from: protected */
    public String createCacheKey(int i) {
        return STATE_PAGE_KEY_PREFIX + i;
    }
}
