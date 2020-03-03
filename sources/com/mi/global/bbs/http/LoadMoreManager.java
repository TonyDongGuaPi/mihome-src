package com.mi.global.bbs.http;

import com.mi.global.bbs.utils.InfiniteScrollListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoadMoreManager implements InfiniteScrollListener.DataLoading {
    private List<InfiniteScrollListener.DataLoading.DataLoadingCallbacks> loadingCallbacks;
    private final AtomicBoolean loadingCount = new AtomicBoolean(false);

    public boolean isDataLoading() {
        return this.loadingCount.get();
    }

    public void registerCallback(InfiniteScrollListener.DataLoading.DataLoadingCallbacks dataLoadingCallbacks) {
        if (this.loadingCallbacks == null) {
            this.loadingCallbacks = new ArrayList(1);
        }
        this.loadingCallbacks.add(dataLoadingCallbacks);
    }

    public void unregisterCallback(InfiniteScrollListener.DataLoading.DataLoadingCallbacks dataLoadingCallbacks) {
        if (this.loadingCallbacks != null && this.loadingCallbacks.contains(dataLoadingCallbacks)) {
            this.loadingCallbacks.remove(dataLoadingCallbacks);
        }
    }

    public void loadStarted() {
        this.loadingCount.set(true);
        dispatchLoadingStartedCallbacks();
    }

    public void loadFinished() {
        this.loadingCount.set(false);
        dispatchLoadingFinishedCallbacks();
    }

    /* access modifiers changed from: protected */
    public void dispatchLoadingStartedCallbacks() {
        if (this.loadingCallbacks != null && !this.loadingCallbacks.isEmpty()) {
            for (InfiniteScrollListener.DataLoading.DataLoadingCallbacks dataStartedLoading : this.loadingCallbacks) {
                dataStartedLoading.dataStartedLoading();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchLoadingFinishedCallbacks() {
        if (this.loadingCallbacks != null && !this.loadingCallbacks.isEmpty()) {
            for (InfiniteScrollListener.DataLoading.DataLoadingCallbacks dataFinishedLoading : this.loadingCallbacks) {
                dataFinishedLoading.dataFinishedLoading();
            }
        }
    }

    public void resetLoadingCount() {
        this.loadingCount.set(false);
    }
}
