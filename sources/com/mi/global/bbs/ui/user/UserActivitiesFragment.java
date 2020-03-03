package com.mi.global.bbs.ui.user;

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
import com.mi.global.bbs.adapter.UserActivitiesAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.UserActivitiesModel;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.RecycleViewDivider;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class UserActivitiesFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    /* access modifiers changed from: private */
    public UserActivitiesAdapter adapter;
    /* access modifiers changed from: private */
    public boolean canLoadMore = false;
    @BindView(2131493155)
    ObservableRecyclerView commonRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout commonRefreshView;
    private LoadMoreManager loadMoreManager;
    private List<UserActivitiesModel.UserActivity> mList;
    @BindView(2131493154)
    ProgressBar mProgress;
    /* access modifiers changed from: private */
    public int page = 0;
    /* access modifiers changed from: private */
    public int pageSize = 10;
    private String uid;
    private String userName = "";

    static /* synthetic */ int access$008(UserActivitiesFragment userActivitiesFragment) {
        int i = userActivitiesFragment.page;
        userActivitiesFragment.page = i + 1;
        return i;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_common_refresh_loading_layout, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        initView();
        getData();
        return inflate;
    }

    /* access modifiers changed from: private */
    public void getData() {
        if (this.page > 0) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getUserActivity(this.uid, this.page * this.pageSize, this.pageSize, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<UserActivitiesModel>() {
            public void accept(@NonNull UserActivitiesModel userActivitiesModel) throws Exception {
                if (!(userActivitiesModel == null || userActivitiesModel.getErrno() != 0 || userActivitiesModel.data == null)) {
                    if (UserActivitiesFragment.this.page == 0) {
                        UserActivitiesFragment.this.adapter.clear();
                    }
                    boolean unused = UserActivitiesFragment.this.canLoadMore = userActivitiesModel.data.size() == UserActivitiesFragment.this.pageSize;
                    UserActivitiesFragment.this.adapter.add(userActivitiesModel.data);
                }
                UserActivitiesFragment.this.dismissProgress();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                UserActivitiesFragment.this.dismissProgress();
            }
        });
    }

    private void initView() {
        this.commonRefreshView.setOnRefreshListener(this);
        this.commonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(getActivity());
        wrapContentLinearLayoutManager.setOrientation(1);
        this.commonRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.commonRecycleView.addItemDecoration(new RecycleViewDivider(getActivity(), 1, getResources().getDimensionPixelSize(R.dimen.common_padding), getResources().getColor(R.color.user_center_divider_color)));
        this.mList = new ArrayList();
        this.loadMoreManager = new LoadMoreManager();
        this.adapter = new UserActivitiesAdapter((BaseActivity) getActivity(), this.loadMoreManager, this.mList, this.userName);
        this.commonRecycleView.setAdapter(this.adapter);
        this.commonRecycleView.setOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (UserActivitiesFragment.this.checkIfReachLoadMore()) {
                    UserActivitiesFragment.access$008(UserActivitiesFragment.this);
                    UserActivitiesFragment.this.getData();
                }
            }
        });
    }

    public void setPadding(int i) {
        if (this.commonRecycleView != null) {
            this.commonRecycleView.setPadding(0, 0, 0, i);
        }
    }

    /* access modifiers changed from: private */
    public boolean checkIfReachLoadMore() {
        if (!this.canLoadMore && this.mList.size() < (this.page + 1) * this.pageSize) {
            return false;
        }
        return true;
    }

    public void onRefresh() {
        this.page = 0;
        getData();
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        if (this.commonRefreshView != null) {
            this.commonRefreshView.setRefreshing(false);
        }
        if (this.mProgress != null && this.mProgress.getVisibility() == 0) {
            this.mProgress.setVisibility(8);
        }
        if (this.page > 0) {
            this.loadMoreManager.loadFinished();
        }
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public void setUserName(String str) {
        this.userName = str;
        if (this.adapter != null) {
            this.adapter.setUserName(str);
            this.adapter.notifyDataSetChanged();
        }
    }
}
