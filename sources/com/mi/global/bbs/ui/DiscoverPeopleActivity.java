package com.mi.global.bbs.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.CallbackManager;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.DiscoveryPeopleAdapter;
import com.mi.global.bbs.adapter.OnFollowListener;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.DiscoveryUserModel;
import com.mi.global.bbs.model.FollowingUserDataModel;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class DiscoverPeopleActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnFollowListener {
    public static final int FILTER_FOLLOW_DATA = 1;
    /* access modifiers changed from: private */
    public DiscoveryPeopleAdapter adapter;
    private List<FollowingUserDataModel> list;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131492947)
    ProgressBar mProgress;
    @BindView(2131493155)
    RecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    /* access modifiers changed from: private */
    public int page = 1;
    private int pageSize = 10;
    View.OnClickListener searchClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_DISCOVER, Constants.ClickEvent.CLICK_SEARCH, Constants.ClickEvent.CLICK_SEARCH);
            DiscoverPeopleActivity.this.startActivity(new Intent(DiscoverPeopleActivity.this, SearchActivity.class));
        }
    };
    /* access modifiers changed from: private */
    public int total = 0;

    static /* synthetic */ int access$208(DiscoverPeopleActivity discoverPeopleActivity) {
        int i = discoverPeopleActivity.page;
        discoverPeopleActivity.page = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_my_favor);
        ButterKnife.bind((Activity) this);
        this.callbackManager = CallbackManager.Factory.create();
        setTitleAndBack("", this.titleBackListener);
        setTitleAndRightBack(R.string.discovery_new_people, R.drawable.bbs_ic_search, this.searchClickListener);
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this);
        wrapContentLinearLayoutManager.setOrientation(1);
        this.mRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.list = new ArrayList();
        this.loadMoreManager = new LoadMoreManager();
        this.adapter = new DiscoveryPeopleAdapter(this, this.loadMoreManager, this.list);
        this.adapter.setOnFollowListener(this);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.addOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (DiscoverPeopleActivity.this.total > 0 && !DiscoverPeopleActivity.this.loadMoreManager.isDataLoading()) {
                    DiscoverPeopleActivity.access$208(DiscoverPeopleActivity.this);
                    DiscoverPeopleActivity.this.getData();
                }
            }
        });
        getData();
    }

    /* access modifiers changed from: private */
    public void getData() {
        this.loadMoreManager.loadStarted();
        ApiClient.getDiscoveryPeople(this.page, this.pageSize, 1, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<DiscoveryUserModel>() {
            public void accept(@NonNull DiscoveryUserModel discoveryUserModel) throws Exception {
                DiscoverPeopleActivity.this.dismissProgress();
                if (DiscoverPeopleActivity.this.page == 1) {
                    DiscoverPeopleActivity.this.adapter.clear();
                }
                int unused = DiscoverPeopleActivity.this.total = 0;
                if (discoveryUserModel != null && discoveryUserModel.data != null) {
                    int unused2 = DiscoverPeopleActivity.this.total = discoveryUserModel.data.size();
                    DiscoverPeopleActivity.this.handleData(discoveryUserModel.data);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                DiscoverPeopleActivity.this.dismissProgress();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleData(List<FollowingUserDataModel> list2) {
        this.adapter.add(list2);
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        this.loadMoreManager.loadFinished();
        if (this.mRefreshView != null) {
            this.mRefreshView.setRefreshing(false);
        }
        if (this.mProgress != null) {
            this.mProgress.setVisibility(8);
        }
    }

    public void onRefresh() {
        this.page = 1;
        this.total = 0;
        getData();
    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, DiscoverPeopleActivity.class));
    }

    public void onFollow(final int i, final String str, boolean z) {
        if (z) {
            showProDialog(getString(R.string.following_ing));
            ApiClient.follow(str, true, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        DiscoverPeopleActivity.this.toast(Integer.valueOf(R.string.follow_success));
                        DiscoverPeopleActivity.this.adapter.getUsers().get(i).follow = 1;
                        DiscoverPeopleActivity.this.adapter.notifyDataSetChanged();
                    } else {
                        DiscoverPeopleActivity.this.toast(baseResult.getErrmsg());
                    }
                    DiscoverPeopleActivity.this.dismissProDialog();
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    DiscoverPeopleActivity.this.dismissProDialog();
                }
            });
            return;
        }
        DialogFactory.showAlert((Context) this, getString(R.string.unfollow_hint), getString(R.string.bbs_yes), getString(R.string.bbs_cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                DiscoverPeopleActivity.this.showProDialog(DiscoverPeopleActivity.this.getString(R.string.unfollowing_ing));
                ApiClient.follow(str, false, DiscoverPeopleActivity.this.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                    public void accept(@NonNull BaseResult baseResult) throws Exception {
                        if (baseResult.getErrno() == 0) {
                            DiscoverPeopleActivity.this.adapter.getUsers().get(i).follow = 0;
                            DiscoverPeopleActivity.this.adapter.notifyDataSetChanged();
                        } else {
                            DiscoverPeopleActivity.this.toast(baseResult.getErrmsg());
                        }
                        DiscoverPeopleActivity.this.dismissProDialog();
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                        DiscoverPeopleActivity.this.dismissProDialog();
                    }
                });
            }
        });
    }
}
