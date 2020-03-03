package com.xiaomi.mishopsdk.widget;

import android.content.Context;
import android.widget.AbsListView;
import com.xiaomi.mishopsdk.util.PicUtil;

public class PageController implements AbsListView.OnScrollListener {
    public static final int DEFAULT_PAGE_SIZE = 20;
    private Runnable mCallback;
    private Context mContext;
    private boolean mIsLastItem;
    public boolean mNeedNextPage = false;
    private boolean mOnlyOnePage;
    public int mPageIndex = 1;
    public int mPageSize = 20;

    public PageController(Runnable runnable, Context context) {
        this.mCallback = runnable;
        this.mIsLastItem = false;
        this.mOnlyOnePage = false;
        this.mContext = context;
    }

    public void nextPage() {
        if (this.mNeedNextPage) {
            this.mPageIndex++;
        }
    }

    public void resetPage() {
        this.mPageIndex = 1;
    }

    public int getPageIndex() {
        return this.mPageIndex;
    }

    public boolean isFirstPage() {
        return this.mPageIndex == 1;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        boolean z = false;
        this.mIsLastItem = i3 == i + i2;
        if (i3 == i2) {
            z = true;
        }
        this.mOnlyOnePage = z;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0) {
            if (this.mIsLastItem && this.mCallback != null && !this.mOnlyOnePage && this.mNeedNextPage) {
                this.mCallback.run();
            }
            if (i == 2) {
                PicUtil.getInstance().getPicasso().pauseTag(this.mContext);
            } else {
                PicUtil.getInstance().getPicasso().resumeTag(this.mContext);
            }
        }
    }
}
