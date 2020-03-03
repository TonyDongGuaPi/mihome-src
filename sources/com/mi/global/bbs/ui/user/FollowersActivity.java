package com.mi.global.bbs.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.FollowersAdapter;
import com.mi.global.bbs.adapter.OnFollowListener;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.FollowersResult;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.RecycleViewDivider;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class FollowersActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnFollowListener {
    static final String EXTRA_TYPE = "from";
    public static final int TYPE_FOLLOWER = 1;
    public static final int TYPE_FOLLOWING = 2;
    private int fromType;
    private LoadMoreManager loadMoreManager;
    @BindView(2131492928)
    ProgressBar mActivityFollowersProgress;
    FollowersAdapter mAdapter;
    @BindView(2131493155)
    ObservableRecyclerView mCommonRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mCommonRefreshView;
    int page = 1;
    int total = 0;
    String uid = "";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_followers);
        ButterKnife.bind((Activity) this);
        this.mCommonRefreshView.setOnRefreshListener(this);
        this.mCommonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this);
        wrapContentLinearLayoutManager.setOrientation(1);
        this.mCommonRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.loadMoreManager = new LoadMoreManager();
        this.mAdapter = new FollowersAdapter(this, this.loadMoreManager);
        this.mCommonRecycleView.setAdapter(this.mAdapter);
        this.mAdapter.setOnFollowListener(this);
        this.mCommonRecycleView.addOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (((float) FollowersActivity.this.page) < ((float) FollowersActivity.this.total) / 20.0f) {
                    FollowersActivity.this.page++;
                    FollowersActivity.this.getFollowers();
                }
            }
        });
        this.mCommonRecycleView.addItemDecoration(new RecycleViewDivider(this, 1, getResources().getDimensionPixelSize(R.dimen.common_padding), getResources().getColor(R.color.user_center_divider_color)));
        this.uid = getIntent().getStringExtra("uid");
        this.fromType = getIntent().getIntExtra("from", 0);
        if (this.fromType == 1) {
            setTitleAndBack(R.string.follower, this.titleBackListener);
        } else if (this.fromType == 2) {
            setTitleAndBack(R.string.following, this.titleBackListener);
        } else {
            setTitleAndBack(R.string.follower, this.titleBackListener);
        }
        getFollowers();
    }

    /* access modifiers changed from: private */
    public void getFollowers() {
        if (this.fromType == 1) {
            ApiClient.getFollowers(this.uid, this.page).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<FollowersResult>() {
                public void accept(@NonNull FollowersResult followersResult) throws Exception {
                    FollowersActivity.this.mActivityFollowersProgress.setVisibility(8);
                    FollowersActivity.this.mCommonRefreshView.setRefreshing(false);
                    if (followersResult.data != null) {
                        FollowersActivity.this.total = Integer.valueOf(followersResult.data.total).intValue();
                        if (FollowersActivity.this.page == 1) {
                            FollowersActivity.this.mAdapter.clear();
                        }
                        FollowersActivity.this.mAdapter.setUsers(followersResult.data.users);
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    FollowersActivity.this.mCommonRefreshView.setRefreshing(false);
                    FollowersActivity.this.mActivityFollowersProgress.setVisibility(8);
                }
            });
        } else if (this.fromType == 2) {
            ApiClient.getFolloweing(this.uid, this.page).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<FollowersResult>() {
                public void accept(@NonNull FollowersResult followersResult) throws Exception {
                    FollowersActivity.this.mActivityFollowersProgress.setVisibility(8);
                    FollowersActivity.this.mCommonRefreshView.setRefreshing(false);
                    if (followersResult.data != null) {
                        FollowersActivity.this.total = Integer.valueOf(followersResult.data.total).intValue();
                        if (FollowersActivity.this.page == 1) {
                            FollowersActivity.this.mAdapter.clear();
                        }
                        FollowersActivity.this.mAdapter.setUsers(followersResult.data.users);
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    FollowersActivity.this.mCommonRefreshView.setRefreshing(false);
                    FollowersActivity.this.mActivityFollowersProgress.setVisibility(8);
                }
            });
        }
    }

    public void onRefresh() {
        this.page = 1;
        getFollowers();
    }

    public static void show(Context context, String str, int i) {
        context.startActivity(new Intent(context, FollowersActivity.class).putExtra("uid", str).putExtra("from", i));
    }

    public void onFollow(final int i, final String str, boolean z) {
        if (z) {
            showProDialog(getString(R.string.following_ing));
            ApiClient.follow(str, true, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        FollowersActivity.this.toast(Integer.valueOf(R.string.follow_success));
                        FollowersActivity.this.mAdapter.getUsers().get(i).follow = 1;
                        FollowersActivity.this.mAdapter.notifyDataSetChanged();
                    } else {
                        FollowersActivity.this.toast(baseResult.getErrmsg());
                    }
                    FollowersActivity.this.dismissProDialog();
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    FollowersActivity.this.dismissProDialog();
                }
            });
            return;
        }
        DialogFactory.showAlert((Context) this, getString(R.string.unfollow_hint), getString(R.string.bbs_yes), getString(R.string.bbs_cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                FollowersActivity.this.showProDialog(FollowersActivity.this.getString(R.string.unfollowing_ing));
                ApiClient.follow(str, false, FollowersActivity.this.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                    public void accept(@NonNull BaseResult baseResult) throws Exception {
                        if (baseResult.getErrno() == 0) {
                            FollowersActivity.this.mAdapter.getUsers().get(i).follow = 0;
                            FollowersActivity.this.mAdapter.notifyDataSetChanged();
                        } else {
                            FollowersActivity.this.toast(baseResult.getErrmsg());
                        }
                        FollowersActivity.this.dismissProDialog();
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                        FollowersActivity.this.dismissProDialog();
                    }
                });
            }
        });
    }
}
