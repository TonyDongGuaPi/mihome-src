package com.mi.global.bbs.ui.column;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.ColumnListAdapter;
import com.mi.global.bbs.adapter.OnFollowListener;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.ColumnDetailData;
import com.mi.global.bbs.model.ColumnListModel;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class ColumnAllActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnFollowListener {
    /* access modifiers changed from: private */
    public ColumnListAdapter adapter;
    @BindView(2131493155)
    ObservableRecyclerView commonRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout commonRefreshView;
    List<ColumnDetailData> detailDatas;
    /* access modifiers changed from: private */
    public boolean hasMore = false;
    private WrapContentLinearLayoutManager layoutManager;
    private LoadMoreManager loadMoreManager;
    /* access modifiers changed from: private */
    public int page = 1;
    private String pageName;
    private int pageSize = 10;

    static /* synthetic */ int access$108(ColumnAllActivity columnAllActivity) {
        int i = columnAllActivity.page;
        columnAllActivity.page = i + 1;
        return i;
    }

    static /* synthetic */ int access$110(ColumnAllActivity columnAllActivity) {
        int i = columnAllActivity.page;
        columnAllActivity.page = i - 1;
        return i;
    }

    public static void jump(Context context) {
        context.startActivity(new Intent(context, ColumnAllActivity.class));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_common_refresh_loading_layout);
        ButterKnife.bind((Activity) this);
        setTitleAndBack(R.string.str_column, this.titleBackListener);
        this.pageName = Constants.PageFragment.PAGE_COLUMN_LIST;
        ButterKnife.bind((Activity) this);
        this.detailDatas = new ArrayList();
        this.loadMoreManager = new LoadMoreManager();
        this.commonRefreshView.setOnRefreshListener(this);
        this.commonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.layoutManager = new WrapContentLinearLayoutManager(this);
        this.layoutManager.setOrientation(1);
        this.commonRecycleView.setLayoutManager(this.layoutManager);
        this.adapter = new ColumnListAdapter(this, this.loadMoreManager, this.pageName);
        this.commonRecycleView.setAdapter(this.adapter);
        this.adapter.setOnFollowListener(this);
        this.commonRecycleView.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (ColumnAllActivity.this.hasMore) {
                    ColumnAllActivity.access$108(ColumnAllActivity.this);
                    ColumnAllActivity.this.getData();
                }
            }
        });
        getData();
    }

    /* access modifiers changed from: private */
    public void getData() {
        if (this.page > 0) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getColumnList(this.page, this.pageSize, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<ColumnListModel>() {
            public void accept(@NonNull ColumnListModel columnListModel) throws Exception {
                ColumnAllActivity.this.dismissProgress();
                if (columnListModel == null || columnListModel.data == null) {
                    if (ColumnAllActivity.this.page > 1) {
                        ColumnAllActivity.access$110(ColumnAllActivity.this);
                    }
                    ColumnAllActivity.this.toast(columnListModel.getErrmsg());
                    return;
                }
                if (ColumnAllActivity.this.page == 1) {
                    ColumnAllActivity.this.adapter.clear();
                }
                ColumnAllActivity.this.adapter.setData(columnListModel.data.list);
                if (columnListModel.data.list == null || columnListModel.data.list.size() <= 0) {
                    boolean unused = ColumnAllActivity.this.hasMore = false;
                } else {
                    boolean unused2 = ColumnAllActivity.this.hasMore = true;
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                ColumnAllActivity.this.dismissProgress();
            }
        });
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        this.loadMoreManager.loadFinished();
        if (this.commonRefreshView != null) {
            this.commonRefreshView.setRefreshing(false);
        }
    }

    public void onRefresh() {
        this.page = 1;
        this.hasMore = false;
        getData();
    }

    public void onFollow(final int i, final String str, boolean z) {
        if (!z) {
            showProDialog(getString(R.string.following_ing));
            ApiClient.followColumn(str, 1, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        ColumnAllActivity.this.toast(Integer.valueOf(R.string.follow_success));
                        if (ColumnAllActivity.this.adapter.getThreadlist() != null && i < ColumnAllActivity.this.adapter.getThreadlist().size()) {
                            ColumnAllActivity.this.adapter.getThreadlist().get(i).subscribeStatus = true;
                            ColumnAllActivity.this.adapter.notifyDataSetChanged();
                        }
                    } else {
                        ColumnAllActivity.this.toast(baseResult.getErrmsg());
                    }
                    ColumnAllActivity.this.dismissProDialog();
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    ColumnAllActivity.this.dismissProDialog();
                }
            });
            return;
        }
        DialogFactory.showAlert((Context) this, getString(R.string.unfollow_column_tip), getString(R.string.bbs_yes), getString(R.string.bbs_cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                ColumnAllActivity.this.showProDialog(ColumnAllActivity.this.getString(R.string.unfollowing_ing));
                ApiClient.followColumn(str, 0, ColumnAllActivity.this.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                    public void accept(@NonNull BaseResult baseResult) throws Exception {
                        if (baseResult.getErrno() != 0) {
                            ColumnAllActivity.this.toast(baseResult.getErrmsg());
                        } else if (ColumnAllActivity.this.adapter.getThreadlist() != null && i < ColumnAllActivity.this.adapter.getThreadlist().size()) {
                            ColumnAllActivity.this.adapter.getThreadlist().get(i).subscribeStatus = false;
                            ColumnAllActivity.this.adapter.notifyDataSetChanged();
                        }
                        ColumnAllActivity.this.dismissProDialog();
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                        ColumnAllActivity.this.dismissProDialog();
                    }
                });
            }
        });
    }
}
