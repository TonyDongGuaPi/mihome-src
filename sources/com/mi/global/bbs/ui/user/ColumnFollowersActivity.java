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
import com.mi.global.bbs.adapter.ColumnFollowersAdapter;
import com.mi.global.bbs.adapter.OnFollowListener;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.ColumnFollowers;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.RecycleViewDivider;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class ColumnFollowersActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnFollowListener {
    String cid = "";
    /* access modifiers changed from: private */
    public boolean hasMore;
    private LoadMoreManager loadMoreManager;
    @BindView(2131492928)
    ProgressBar mActivityFollowersProgress;
    ColumnFollowersAdapter mAdapter;
    @BindView(2131493155)
    ObservableRecyclerView mCommonRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mCommonRefreshView;
    int page = 1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitleAndBack(R.string.subscribers, this.titleBackListener);
        setCustomContentView(R.layout.bbs_activity_followers);
        ButterKnife.bind((Activity) this);
        this.mCommonRefreshView.setOnRefreshListener(this);
        this.mCommonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this);
        wrapContentLinearLayoutManager.setOrientation(1);
        this.mCommonRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.loadMoreManager = new LoadMoreManager();
        this.mAdapter = new ColumnFollowersAdapter(this, this.loadMoreManager);
        this.mCommonRecycleView.setAdapter(this.mAdapter);
        this.mAdapter.setOnFollowListener(this);
        this.mCommonRecycleView.addOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (ColumnFollowersActivity.this.hasMore) {
                    ColumnFollowersActivity.this.page++;
                    ColumnFollowersActivity.this.getFollowers();
                }
            }
        });
        this.mCommonRecycleView.addItemDecoration(new RecycleViewDivider(this, 1, getResources().getDimensionPixelSize(R.dimen.common_padding), getResources().getColor(R.color.user_center_divider_color)));
        this.cid = getIntent().getStringExtra("cid");
        getFollowers();
    }

    /* access modifiers changed from: private */
    public void getFollowers() {
        ApiClient.getColumnFollowers(this.cid, this.page).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<ColumnFollowers>() {
            public void accept(@NonNull ColumnFollowers columnFollowers) throws Exception {
                ColumnFollowersActivity.this.mActivityFollowersProgress.setVisibility(8);
                ColumnFollowersActivity.this.mCommonRefreshView.setRefreshing(false);
                if (columnFollowers.data != null) {
                    if (ColumnFollowersActivity.this.page == 1) {
                        ColumnFollowersActivity.this.mAdapter.clear();
                    }
                    if (columnFollowers.data.list.size() > 0) {
                        boolean unused = ColumnFollowersActivity.this.hasMore = true;
                    } else {
                        boolean unused2 = ColumnFollowersActivity.this.hasMore = false;
                    }
                    ColumnFollowersActivity.this.mAdapter.setUsers(columnFollowers.data.list);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                ColumnFollowersActivity.this.mCommonRefreshView.setRefreshing(false);
                ColumnFollowersActivity.this.mActivityFollowersProgress.setVisibility(8);
            }
        });
    }

    public void onRefresh() {
        this.page = 1;
        getFollowers();
    }

    public static void show(Context context, String str) {
        context.startActivity(new Intent(context, ColumnFollowersActivity.class).putExtra("cid", str));
    }

    public void onFollow(final int i, final String str, boolean z) {
        if (!z) {
            showProDialog(getString(R.string.following_ing));
            ApiClient.follow(str, true, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        ColumnFollowersActivity.this.toast(Integer.valueOf(R.string.follow_success));
                        ColumnFollowersActivity.this.mAdapter.getUsers().get(i).following = true;
                        ColumnFollowersActivity.this.mAdapter.notifyDataSetChanged();
                    } else {
                        ColumnFollowersActivity.this.toast(baseResult.getErrmsg());
                    }
                    ColumnFollowersActivity.this.dismissProDialog();
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    ColumnFollowersActivity.this.dismissProDialog();
                }
            });
            return;
        }
        DialogFactory.showAlert((Context) this, getString(R.string.unfollow_hint), getString(R.string.bbs_yes), getString(R.string.bbs_cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                ColumnFollowersActivity.this.showProDialog(ColumnFollowersActivity.this.getString(R.string.unfollowing_ing));
                ApiClient.follow(str, false, ColumnFollowersActivity.this.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                    public void accept(@NonNull BaseResult baseResult) throws Exception {
                        if (baseResult.getErrno() == 0) {
                            ColumnFollowersActivity.this.mAdapter.getUsers().get(i).following = false;
                            ColumnFollowersActivity.this.mAdapter.notifyDataSetChanged();
                        } else {
                            ColumnFollowersActivity.this.toast(baseResult.getErrmsg());
                        }
                        ColumnFollowersActivity.this.dismissProDialog();
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                        ColumnFollowersActivity.this.dismissProDialog();
                    }
                });
            }
        });
    }
}
