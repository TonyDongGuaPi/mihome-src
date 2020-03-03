package com.youpin.weex.app.extend.component.slider_neighbor.view;

import android.support.v4.view.PagerAdapter;

public abstract class BaseBannerPagerAdapter extends PagerAdapter {
    private boolean mAutoloop = false;

    public abstract int getActualCount();

    public void setInnerLoop(boolean z) {
        if (this.mAutoloop != z) {
            this.mAutoloop = z;
            notifyDataSetChanged();
        }
    }

    public int getActualPosition(int i) {
        int actualCount = getActualCount();
        return actualCount > 0 ? i % actualCount : i;
    }

    public int getCount() {
        int actualCount = getActualCount();
        if (!this.mAutoloop || actualCount <= 1) {
            return actualCount;
        }
        return Integer.MAX_VALUE;
    }

    public int getFirstPostion() {
        if (this.mAutoloop) {
            return 1073741823 - (1073741823 % getActualCount());
        }
        return 0;
    }

    public int getItemIndex(int i) {
        int actualCount = getActualCount();
        if (actualCount == 0) {
            return 0;
        }
        if (!this.mAutoloop) {
            return i;
        }
        if (actualCount == 1) {
            return 0;
        }
        if (actualCount == 2 || actualCount == 3) {
            return i % (actualCount * 2);
        }
        return i % actualCount;
    }
}
