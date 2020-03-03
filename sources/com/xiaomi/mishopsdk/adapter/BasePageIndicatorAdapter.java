package com.xiaomi.mishopsdk.adapter;

import android.content.Context;
import android.view.View;

public abstract class BasePageIndicatorAdapter<T> extends BasePageAdapter<T> {
    public static final int MAX_GALLERY_COUNT = 500;

    public BasePageIndicatorAdapter(Context context) {
        super(context);
    }

    public int getCount() {
        if (this.mData.size() == 1) {
            return 1;
        }
        return this.mData.size() == 0 ? 0 : 500;
    }

    public T getData(int i) {
        int reallyCount = getReallyCount();
        if (i >= 0 && i < reallyCount) {
            return this.mData.get(i);
        }
        if (reallyCount > 0) {
            return this.mData.get(i % reallyCount);
        }
        return null;
    }

    public int getReallyCount() {
        if (this.mData != null) {
            return this.mData.size();
        }
        return 0;
    }

    public int getItemPosition(Object obj) {
        if (getReallyCount() > 0) {
            return this.mData.contains(((View) obj).getTag()) ? -1 : -2;
        }
        return super.getItemPosition(obj);
    }
}
