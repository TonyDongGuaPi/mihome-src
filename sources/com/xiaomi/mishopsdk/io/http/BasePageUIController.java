package com.xiaomi.mishopsdk.io.http;

import android.os.Handler;
import android.widget.AbsListView;
import com.mishopsdk.volley.VolleyError;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.widget.LoadingView;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BasePageUIController<T> implements AbsListView.OnScrollListener {
    private static final boolean DEBUG = true;
    public static final int PAGEINDEX_START = 1;
    public static final int PAGESIZE_DEFAULT = 20;
    private static final String TAG = "BasePageUIController";
    private UIControllerAdapter mAdapter;
    protected boolean mHasMoreData = true;
    private boolean mIsFirstPageReuqest;
    protected boolean mIsRefreshing;
    protected boolean mLastItemVisible;
    private LoadingView mLoadingView;
    protected AtomicInteger mPageIndex = new AtomicInteger(1);
    protected int mPageSize = 20;

    public interface UIControllerAdapter {
        void clear();

        int getCount();
    }

    /* access modifiers changed from: protected */
    public abstract void getListData();

    /* access modifiers changed from: protected */
    public boolean hasCustomEmptyDataTip() {
        return false;
    }

    public BasePageUIController(LoadingView loadingView, UIControllerAdapter uIControllerAdapter) {
        this.mLoadingView = loadingView;
        this.mAdapter = uIControllerAdapter;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0 && this.mLastItemVisible && !this.mIsRefreshing && this.mHasMoreData) {
            getListData();
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        boolean z = true;
        if (i3 <= 0 || i + i2 < i3 - 1) {
            z = false;
        }
        this.mLastItemVisible = z;
    }

    public void onStart() {
        Log.d(TAG, "onStart");
        this.mIsRefreshing = true;
        boolean z = this.mAdapter.getCount() == 0;
        if ((!isFirstPageReuqest() || z) && this.mLoadingView != null) {
            this.mLoadingView.startLoading(true, z);
        }
    }

    public void onSuccess(ArrayList<T> arrayList) {
        Log.d(TAG, "onSuccess >>");
        if (isFirstPage() || isFirstPageReuqest()) {
            this.mAdapter.clear();
            if (this.mLoadingView != null) {
                this.mLoadingView.loadingSuccess();
            }
            if ((arrayList == null || arrayList.isEmpty()) && !hasCustomEmptyDataTip() && this.mLoadingView != null) {
                this.mLoadingView.showErrorWithActionButton(ShopApp.instance.getString(R.string.mishopsdk_request_empty_data), "", (Handler.Callback) null);
            }
        }
        if (dataOver(arrayList)) {
            this.mHasMoreData = false;
        } else if (isFirstPageReuqest()) {
            this.mPageIndex.set(2);
        } else {
            this.mPageIndex.incrementAndGet();
        }
    }

    /* access modifiers changed from: protected */
    public boolean dataOver(ArrayList<T> arrayList) {
        return arrayList == null || arrayList.size() < this.mPageSize;
    }

    public void onError(VolleyError volleyError) {
        Log.d(TAG, "onError >> " + volleyError.toString());
        ShopApiError.toastError(volleyError);
    }

    public void onFinish() {
        Log.d(TAG, "onFinish >> ");
        this.mIsRefreshing = false;
        if (this.mLoadingView != null) {
            this.mLoadingView.stopLoading();
        }
    }

    public void reset() {
        this.mPageIndex.set(1);
        this.mIsRefreshing = false;
        this.mHasMoreData = true;
    }

    public boolean isFirstPage() {
        return this.mPageIndex.get() == 1;
    }

    public String getPageIndex() {
        return String.valueOf(this.mPageIndex.get());
    }

    public int getPageIndexVal() {
        return this.mPageIndex.get();
    }

    public String getPageSize() {
        return String.valueOf(this.mPageSize);
    }

    public void setPageSize(int i) {
        this.mPageSize = i;
    }

    public void setIsFirstRequest(boolean z) {
        this.mIsFirstPageReuqest = z;
    }

    public boolean isFirstPageReuqest() {
        return this.mIsFirstPageReuqest;
    }
}
