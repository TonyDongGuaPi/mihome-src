package com.mi.global.bbs.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.InCityActivityAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.InCityActivityResult;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class InCityFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, InCityActivityAdapter.OnDividedClickListener {
    public static final int PER_PAGE = 10;
    private static final int REQUEST_TYPE_COMPLETE = 1;
    private static final int REQUEST_TYPE_UNCOMPLETE = 0;
    /* access modifiers changed from: private */
    public List<InCityActivityResult.InCityActivityBean.InCityActivity> activities = new ArrayList();
    /* access modifiers changed from: private */
    public InCityActivityAdapter adapter;
    /* access modifiers changed from: private */
    public int completePage = 1;
    /* access modifiers changed from: private */
    public int completeTotal = 0;
    @BindView(2131493379)
    RelativeLayout emptyLayout;
    private WrapContentLinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131493155)
    RecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    /* access modifiers changed from: private */
    public int page = 1;
    /* access modifiers changed from: private */
    public int total = 0;

    static /* synthetic */ int access$1210(InCityFragment inCityFragment) {
        int i = inCityFragment.completePage;
        inCityFragment.completePage = i - 1;
        return i;
    }

    static /* synthetic */ int access$510(InCityFragment inCityFragment) {
        int i = inCityFragment.page;
        inCityFragment.page = i - 1;
        return i;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_activity_list_layout, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        initView();
        getData();
        return inflate;
    }

    private void initView() {
        this.loadMoreManager = new LoadMoreManager();
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setVisibility(8);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.adapter = new InCityActivityAdapter((BaseActivity) getActivity(), this.loadMoreManager, this.mRefreshView);
        this.adapter.setOnClickAddListener(this);
        this.layoutManager = new WrapContentLinearLayoutManager(getActivity());
        this.mRecycleView.setLayoutManager(this.layoutManager);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (!InCityFragment.this.loadMoreManager.isDataLoading()) {
                    InCityFragment.this.loadMore();
                }
            }
        });
    }

    public void setPadding(int i) {
        if (this.mRecycleView != null) {
            this.mRecycleView.setPadding(0, 0, 0, i);
        }
    }

    /* access modifiers changed from: private */
    public void loadMore() {
        if (this.total > 0) {
            this.page++;
            getData();
        }
        if (this.completeTotal > 0) {
            this.completePage++;
            getComplateData();
        }
    }

    private void getData() {
        if (this.page > 1) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getInCityActivityList(this.page, 10, 0, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<InCityActivityResult>() {
            public void accept(@NonNull InCityActivityResult inCityActivityResult) throws Exception {
                InCityFragment.this.dismissProgress();
                if (inCityActivityResult == null || inCityActivityResult.getErrno() != 0 || inCityActivityResult.data == null || inCityActivityResult.data.list == null || inCityActivityResult.data.list.size() <= 0) {
                    int unused = InCityFragment.this.total = 0;
                    if (InCityFragment.this.page > 1) {
                        InCityFragment.access$510(InCityFragment.this);
                    }
                    if (InCityFragment.this.activities == null || InCityFragment.this.activities.size() <= 0) {
                        InCityFragment.this.getComplateData();
                        return;
                    }
                    InCityActivityResult.InCityActivityBean.InCityActivity inCityActivity = new InCityActivityResult.InCityActivityBean.InCityActivity();
                    inCityActivity.tid = "-1";
                    inCityActivity.fid = "-1";
                    InCityFragment.this.activities.add(inCityActivity);
                    InCityFragment.this.adapter.addData(InCityFragment.this.activities);
                    return;
                }
                InCityFragment.this.handleResponse(inCityActivityResult);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                int unused = InCityFragment.this.total = 0;
                InCityFragment.this.dismissProgress();
                InCityFragment.this.updateUI();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleResponse(InCityActivityResult inCityActivityResult) {
        this.total = inCityActivityResult.data.list.size();
        for (int i = 0; i < this.total; i++) {
            this.activities.add(inCityActivityResult.data.list.get(i));
        }
        this.adapter.addData(this.activities);
    }

    /* access modifiers changed from: private */
    public void getComplateData() {
        if (this.completePage > 1) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getInCityActivityList(this.completePage, 10, 1, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<InCityActivityResult>() {
            public void accept(@NonNull InCityActivityResult inCityActivityResult) throws Exception {
                InCityFragment.this.dismissProgress();
                if (inCityActivityResult == null || inCityActivityResult.getErrno() != 0 || inCityActivityResult.data == null || inCityActivityResult.data.list == null || inCityActivityResult.data.list.size() <= 0) {
                    int unused = InCityFragment.this.completeTotal = 0;
                    if (InCityFragment.this.completePage > 1) {
                        InCityFragment.access$1210(InCityFragment.this);
                    }
                    InCityFragment.this.updateUI();
                    return;
                }
                InCityFragment.this.handleCompulteResponse(inCityActivityResult);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                int unused = InCityFragment.this.completeTotal = 0;
                InCityFragment.this.dismissProgress();
                InCityFragment.this.updateUI();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleCompulteResponse(InCityActivityResult inCityActivityResult) {
        this.completeTotal = inCityActivityResult.data.list.size();
        for (int i = 0; i < this.completeTotal; i++) {
            this.activities.add(inCityActivityResult.data.list.get(i));
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
        if (this.mRefreshView != null) {
            this.mRefreshView.setVisibility(0);
            this.mRefreshView.setRefreshing(false);
        }
    }

    public void onRefresh() {
        this.total = 0;
        this.completeTotal = 0;
        this.page = 1;
        this.completePage = 1;
        this.activities.clear();
        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_ACTIVITIES, Constants.ClickEvent.CLICK_OFFLINE_ACTIVITY_LEAVE, this.page + "");
        super.onDestroy();
    }

    public void onDividedClick(int i) {
        this.activities.remove(i);
        getComplateData();
    }
}
