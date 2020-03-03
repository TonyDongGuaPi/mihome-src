package com.mi.global.bbs.ui.checkin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.RankingAdapter;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.LeaderBoardBean;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.RecycleViewDivider;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RankingFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    int PAGE_SIZE = 10;
    /* access modifiers changed from: private */
    public boolean hasMore = false;
    private LoadMoreManager loadMoreManager;
    /* access modifiers changed from: private */
    public RankingAdapter mAdapter;
    @BindView(2131493154)
    ProgressBar mCommonProgressView;
    @BindView(2131493155)
    ObservableRecyclerView mCommonRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mCommonRefreshView;
    int page = 1;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_common_refresh_loading_layout, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(getActivity());
        wrapContentLinearLayoutManager.setOrientation(1);
        this.mCommonRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.loadMoreManager = new LoadMoreManager();
        this.mAdapter = new RankingAdapter(getActivity(), this.loadMoreManager);
        this.mCommonRecycleView.setAdapter(this.mAdapter);
        this.mCommonRecycleView.addOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (RankingFragment.this.hasMore) {
                    RankingFragment.this.page++;
                    RankingFragment.this.obtainRankingList();
                }
            }
        });
        this.mCommonRecycleView.addItemDecoration(new RecycleViewDivider(getActivity(), 1, getResources().getDimensionPixelSize(R.dimen.divide_height2), getResources().getColor(R.color.user_center_divider_color)));
        this.mCommonRefreshView.setOnRefreshListener(this);
        this.mCommonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        obtainRankingList();
    }

    /* access modifiers changed from: private */
    public void obtainRankingList() {
        if (this.page > 1) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getApiService().getSignBoard(this.page, this.PAGE_SIZE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<LeaderBoardBean>() {
            public void accept(LeaderBoardBean leaderBoardBean) throws Exception {
                if (leaderBoardBean != null) {
                    boolean z = false;
                    if (RankingFragment.this.page == 1) {
                        RankingFragment.this.mAdapter.setLeaderBoard(leaderBoardBean.data);
                        if (leaderBoardBean.data.ranklist != null) {
                            RankingFragment rankingFragment = RankingFragment.this;
                            if (leaderBoardBean.data.ranklist.size() >= RankingFragment.this.PAGE_SIZE && RankingFragment.this.page <= 5) {
                                z = true;
                            }
                            boolean unused = rankingFragment.hasMore = z;
                        }
                    } else {
                        RankingFragment.this.mAdapter.setRankingList(leaderBoardBean.data.ranklist);
                        if (leaderBoardBean.data.ranklist != null) {
                            RankingFragment rankingFragment2 = RankingFragment.this;
                            if (leaderBoardBean.data.ranklist.size() >= RankingFragment.this.PAGE_SIZE && RankingFragment.this.page <= 5) {
                                z = true;
                            }
                            boolean unused2 = rankingFragment2.hasMore = z;
                        }
                    }
                }
                RankingFragment.this.loadComplete();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                RankingFragment.this.loadComplete();
            }
        });
    }

    /* access modifiers changed from: private */
    public void loadComplete() {
        this.mCommonRefreshView.setRefreshing(false);
        this.mCommonProgressView.setVisibility(8);
        this.loadMoreManager.loadFinished();
    }

    public void onRefresh() {
        this.page = 1;
        obtainRankingList();
    }
}
