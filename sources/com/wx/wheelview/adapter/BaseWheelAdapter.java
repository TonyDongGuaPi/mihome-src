package com.wx.wheelview.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.wx.wheelview.util.WheelUtils;
import java.util.List;

public abstract class BaseWheelAdapter<T> extends BaseAdapter {
    protected boolean mClickable = false;
    private int mCurrentPositon = -1;
    protected List<T> mList = null;
    protected boolean mLoop = false;
    protected int mWheelSize = 3;

    /* access modifiers changed from: protected */
    public abstract View bindView(int i, View view, ViewGroup viewGroup);

    public final void setCurrentPosition(int i) {
        this.mCurrentPositon = i;
    }

    public final int getCount() {
        if (this.mLoop) {
            return Integer.MAX_VALUE;
        }
        if (!WheelUtils.a(this.mList)) {
            return (this.mList.size() + this.mWheelSize) - 1;
        }
        return 0;
    }

    public final long getItemId(int i) {
        if (!WheelUtils.a(this.mList)) {
            i %= this.mList.size();
        }
        return (long) i;
    }

    public final Object getItem(int i) {
        if (!WheelUtils.a(this.mList)) {
            return this.mList.get(i % this.mList.size());
        }
        return null;
    }

    public boolean areAllItemsEnabled() {
        return !this.mClickable;
    }

    public boolean isEnabled(int i) {
        if (!this.mClickable) {
            return false;
        }
        if (this.mLoop) {
            if (i % this.mList.size() == this.mCurrentPositon) {
                return true;
            }
            return false;
        } else if (i == this.mCurrentPositon + (this.mWheelSize / 2)) {
            return true;
        } else {
            return false;
        }
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        int i2;
        View view2;
        if (this.mLoop) {
            i2 = i % this.mList.size();
        } else if (i >= this.mWheelSize / 2 && i < (this.mWheelSize / 2) + this.mList.size()) {
            i2 = i - (this.mWheelSize / 2);
        } else {
            i2 = -1;
        }
        if (i2 == -1) {
            view2 = bindView(0, view, viewGroup);
        } else {
            view2 = bindView(i2, view, viewGroup);
        }
        if (!this.mLoop) {
            if (i2 == -1) {
                view2.setVisibility(4);
            } else {
                view2.setVisibility(0);
            }
        }
        return view2;
    }

    public final BaseWheelAdapter setClickable(boolean z) {
        if (z != this.mClickable) {
            this.mClickable = z;
            super.notifyDataSetChanged();
        }
        return this;
    }

    public final BaseWheelAdapter setLoop(boolean z) {
        if (z != this.mLoop) {
            this.mLoop = z;
            super.notifyDataSetChanged();
        }
        return this;
    }

    public final BaseWheelAdapter setWheelSize(int i) {
        this.mWheelSize = i;
        super.notifyDataSetChanged();
        return this;
    }

    public final BaseWheelAdapter setData(List<T> list) {
        this.mList = list;
        super.notifyDataSetChanged();
        return this;
    }

    @Deprecated
    public final void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Deprecated
    public final void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }
}
