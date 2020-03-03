package com.mi.global.bbs.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class InfiniteScrollListener extends RecyclerView.OnScrollListener {
    private static final int VISIBLE_THRESHOLD = 1;
    private final DataLoading dataLoading;
    private final LinearLayoutManager layoutManager;

    public interface DataLoading {

        public interface DataLoadingCallbacks {
            void dataFinishedLoading();

            void dataStartedLoading();
        }

        boolean isDataLoading();

        void registerCallback(DataLoadingCallbacks dataLoadingCallbacks);

        void unregisterCallback(DataLoadingCallbacks dataLoadingCallbacks);
    }

    public abstract void onLoadMore();

    public InfiniteScrollListener(@NonNull LinearLayoutManager linearLayoutManager, @NonNull DataLoading dataLoading2) {
        this.layoutManager = linearLayoutManager;
        this.dataLoading = dataLoading2;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        if (i2 >= 0 && !this.dataLoading.isDataLoading()) {
            int childCount = recyclerView.getChildCount();
            if (this.layoutManager.getItemCount() - childCount <= this.layoutManager.findFirstVisibleItemPosition() + 1) {
                onLoadMore();
            }
        }
    }
}
