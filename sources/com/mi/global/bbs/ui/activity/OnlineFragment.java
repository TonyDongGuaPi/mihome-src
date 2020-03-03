package com.mi.global.bbs.ui.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.OnlineActivityAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.OnlineActivityResult;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.RvGirdScrollListener;
import com.mi.global.bbs.view.WrapContentGridLayoutManager;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class OnlineFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final int COLUMN_COUNT_TWO = 2;
    public static final int GRID_LAYOUT_COLUMN_PADDING_VALUE = 5;
    public static final int PER_PAGE = 10;
    private List<OnlineActivityResult.OnlineActivityBean.OnlineActivity> activities = new ArrayList();
    /* access modifiers changed from: private */
    public OnlineActivityAdapter adapter;
    @BindView(2131493380)
    RelativeLayout emptyLayout;
    private WrapContentGridLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131493155)
    RecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    /* access modifiers changed from: private */
    public int page = 1;
    @BindView(2131493842)
    ProgressBar progressView;
    /* access modifiers changed from: private */
    public int total = 0;

    static /* synthetic */ int access$610(OnlineFragment onlineFragment) {
        int i = onlineFragment.page;
        onlineFragment.page = i - 1;
        return i;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_online_layout, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        initView();
        getData();
        return inflate;
    }

    private void initView() {
        this.loadMoreManager = new LoadMoreManager();
        this.mRefreshView.setVisibility(8);
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.adapter = new OnlineActivityAdapter((BaseActivity) getActivity(), this.loadMoreManager);
        this.layoutManager = new WrapContentGridLayoutManager(getActivity(), 2);
        this.layoutManager.setSpanSizeLookup(getSpanSizeLookUp());
        this.mRecycleView.setLayoutManager(this.layoutManager);
        this.mRecycleView.addItemDecoration(new SpacesItemDecoration(5));
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.addOnScrollListener(new RvGirdScrollListener() {
            public void onLoadMore() {
                if (!OnlineFragment.this.loadMoreManager.isDataLoading()) {
                    OnlineFragment.this.loadMore();
                }
            }
        });
    }

    public void setPadding(int i) {
        if (this.mRecycleView != null) {
            this.mRecycleView.setPadding(0, 0, 0, i);
        }
    }

    private GridLayoutManager.SpanSizeLookup getSpanSizeLookUp() {
        return new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int i) {
                return OnlineFragment.this.adapter.getItemColumnSpan(i);
            }
        };
    }

    /* access modifiers changed from: private */
    public void loadMore() {
        if (this.total > 0) {
            this.page++;
            getData();
        }
    }

    private void getData() {
        if (this.page > 1) {
            this.loadMoreManager.loadStarted();
        }
        this.progressView.setVisibility(0);
        ApiClient.getOnlineActivityList(this.page, 10, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<OnlineActivityResult>() {
            public void accept(@NonNull OnlineActivityResult onlineActivityResult) throws Exception {
                OnlineFragment.this.dismissProgress();
                if (onlineActivityResult == null || onlineActivityResult.getErrno() != 0 || onlineActivityResult.data == null) {
                    int unused = OnlineFragment.this.total = 0;
                    if (OnlineFragment.this.page > 1) {
                        OnlineFragment.access$610(OnlineFragment.this);
                    }
                    OnlineFragment.this.updateUI();
                    return;
                }
                OnlineFragment.this.handleResponse(onlineActivityResult);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                int unused = OnlineFragment.this.total = 0;
                OnlineFragment.this.dismissProgress();
                OnlineFragment.this.updateUI();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleResponse(OnlineActivityResult onlineActivityResult) {
        if (onlineActivityResult == null || onlineActivityResult.data == null || onlineActivityResult.data.list == null || onlineActivityResult.data.list.size() <= 0) {
            this.total = 0;
            updateUI();
            return;
        }
        this.total = onlineActivityResult.data.list.size();
        for (int i = 0; i < this.total; i++) {
            this.activities.add(onlineActivityResult.data.list.get(i));
        }
        this.adapter.addData(this.activities);
        updateUI();
    }

    /* access modifiers changed from: private */
    public void updateUI() {
        if (this.adapter.getDataItemCount() > 0) {
            this.mRefreshView.setVisibility(0);
            this.emptyLayout.setVisibility(8);
            return;
        }
        this.mRefreshView.setVisibility(8);
        this.emptyLayout.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        if (this.loadMoreManager.isDataLoading()) {
            this.loadMoreManager.loadFinished();
        }
        this.progressView.setVisibility(8);
        if (this.mRefreshView != null) {
            this.mRefreshView.setVisibility(0);
            this.mRefreshView.setRefreshing(false);
        }
    }

    public void onRefresh() {
        this.page = 1;
        this.activities.clear();
        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_ACTIVITIES, Constants.ClickEvent.CLICK_ONLINE_ACTIVITY_LEAVE, this.page + "");
        super.onDestroy();
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int i) {
            this.space = i;
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (recyclerView.getChildAdapterPosition(view) == 0) {
                return;
            }
            if (recyclerView.getChildLayoutPosition(view) % 2 == 1) {
                rect.right = OnlineFragment.dip2px(OnlineFragment.this.getContext(), (float) this.space);
            } else if (recyclerView.getChildLayoutPosition(view) % 2 == 0) {
                rect.left = OnlineFragment.dip2px(OnlineFragment.this.getContext(), (float) this.space);
            }
        }
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
