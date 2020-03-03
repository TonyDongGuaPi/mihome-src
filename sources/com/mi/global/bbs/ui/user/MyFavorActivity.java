package com.mi.global.bbs.ui.user;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.CallbackManager;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.MyFavorAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.MyFavorModel;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class MyFavorActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnShareListener {
    /* access modifiers changed from: private */
    public MyFavorAdapter adapter;
    private CallbackManager callbackManager;
    private List<MyFavorModel.DataBean.ListBean> list;
    private LoadMoreManager loadMoreManager;
    @BindView(2131492947)
    ProgressBar mProgress;
    @BindView(2131493155)
    ObservableRecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    /* access modifiers changed from: private */
    public int page = 1;
    private int pageSize = 10;
    /* access modifiers changed from: private */
    public int total = 0;

    static /* synthetic */ int access$008(MyFavorActivity myFavorActivity) {
        int i = myFavorActivity.page;
        myFavorActivity.page = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_my_favor);
        ButterKnife.bind((Activity) this);
        this.callbackManager = CallbackManager.Factory.create();
        setTitleAndBack(R.string.my_favours, this.titleBackListener);
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this);
        wrapContentLinearLayoutManager.setOrientation(1);
        this.mRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.list = new ArrayList();
        this.loadMoreManager = new LoadMoreManager();
        this.adapter = new MyFavorAdapter(this, this.loadMoreManager, this.list);
        this.adapter.setOnShareListener(this);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.addOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (MyFavorActivity.this.page < MyFavorActivity.this.total) {
                    MyFavorActivity.access$008(MyFavorActivity.this);
                    MyFavorActivity.this.getData();
                }
            }
        });
        getData();
    }

    /* access modifiers changed from: private */
    public void getData() {
        this.loadMoreManager.loadStarted();
        ApiClient.getFavor(this.page, this.pageSize, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<MyFavorModel>() {
            public void accept(@NonNull MyFavorModel myFavorModel) throws Exception {
                MyFavorActivity.this.dismissProgress();
                if (myFavorModel != null && myFavorModel.data != null) {
                    int unused = MyFavorActivity.this.total = myFavorModel.data.totalpage;
                    int unused2 = MyFavorActivity.this.page = myFavorModel.data.curpage;
                    if (MyFavorActivity.this.page == 1) {
                        MyFavorActivity.this.adapter.clear();
                    }
                    MyFavorActivity.this.handleData(myFavorModel.data.list);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                MyFavorActivity.this.dismissProgress();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleData(List<MyFavorModel.DataBean.ListBean> list2) {
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
        getData();
    }

    private void share(String str, String str2) {
        new ShareDialog(this).setShareTitle(str).setShareUrl(str2).setCallbackManager(this.callbackManager).show();
    }

    public void onShare(String str, String str2) {
        share(str, str2);
    }
}
