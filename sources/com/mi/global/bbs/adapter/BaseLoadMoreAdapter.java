package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.mi.global.bbs.R;
import com.mi.global.bbs.utils.InfiniteScrollListener;

public class BaseLoadMoreAdapter extends RecyclerView.Adapter implements InfiniteScrollListener.DataLoading.DataLoadingCallbacks {
    protected static final int TYPE_LOADING_MORE = -1;
    protected static final int TYPE_NORMAL = 0;
    protected InfiniteScrollListener.DataLoading dataLoading;
    protected final LayoutInflater layoutInflater;
    protected boolean showLoadingMore = false;

    public int getDataItemCount() {
        return 0;
    }

    public int getNormalViewType(int i) {
        return 0;
    }

    public BaseLoadMoreAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading2) {
        this.layoutInflater = LayoutInflater.from(activity);
        this.dataLoading = dataLoading2;
        dataLoading2.registerCallback(this);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != -1) {
            return null;
        }
        return new LoadingMoreHolder(this.layoutInflater.inflate(R.layout.bbs_infinite_loading, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == -1) {
            bindLoadingViewHolder((LoadingMoreHolder) viewHolder, i);
        }
    }

    private void bindLoadingViewHolder(LoadingMoreHolder loadingMoreHolder, int i) {
        loadingMoreHolder.progress.setVisibility((i <= 0 || !this.dataLoading.isDataLoading()) ? 4 : 0);
    }

    public int getItemViewType(int i) {
        if (i >= getDataItemCount() || getDataItemCount() <= 0) {
            return -1;
        }
        return getNormalViewType(i);
    }

    public int getItemCount() {
        return getDataItemCount() + (this.showLoadingMore ? 1 : 0);
    }

    /* access modifiers changed from: protected */
    public int getLoadingMoreItemPosition() {
        if (this.showLoadingMore) {
            return getItemCount() - 1;
        }
        return -1;
    }

    public void dataStartedLoading() {
        if (!this.showLoadingMore) {
            this.showLoadingMore = true;
            notifyDataSetChanged();
        }
    }

    public void dataFinishedLoading() {
        if (this.showLoadingMore) {
            this.showLoadingMore = false;
            notifyDataSetChanged();
        }
    }

    static class LoadingMoreHolder extends RecyclerView.ViewHolder {
        ProgressBar progress;

        LoadingMoreHolder(View view) {
            super(view);
            this.progress = (ProgressBar) view;
        }
    }
}
