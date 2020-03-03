package com.mi.global.bbs.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.CallbackManager;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.MyReplyAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.MyReplyModel;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class MyRepliesActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnShareListener {
    /* access modifiers changed from: private */
    public MyReplyAdapter adapter;
    private CallbackManager callbackManager;
    private List<MyReplyModel.DataBean.ListBean> list;
    private LoadMoreManager loadMoreManager;
    @BindView(2131492947)
    ProgressBar mProgress;
    @BindView(2131493155)
    RecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    /* access modifiers changed from: private */
    public int page = 1;
    private int pageSize = 10;
    /* access modifiers changed from: private */
    public int total = 0;
    private String uid;

    static /* synthetic */ int access$108(MyRepliesActivity myRepliesActivity) {
        int i = myRepliesActivity.page;
        myRepliesActivity.page = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_my_favor);
        ButterKnife.bind((Activity) this);
        this.callbackManager = CallbackManager.Factory.create();
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this);
        wrapContentLinearLayoutManager.setOrientation(1);
        this.mRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.list = new ArrayList();
        this.loadMoreManager = new LoadMoreManager();
        this.adapter = new MyReplyAdapter(this, this.loadMoreManager, this.list);
        this.adapter.setOnShareListener(this);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.addOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (MyRepliesActivity.this.total > 0) {
                    MyRepliesActivity.access$108(MyRepliesActivity.this);
                    MyRepliesActivity.this.getData();
                }
            }
        });
        this.uid = getIntent().getStringExtra("uid");
        handleTitle();
        getData();
    }

    private void handleTitle() {
        String userId = LoginManager.getInstance().getUserId();
        if (TextUtils.isEmpty(userId) || !userId.equals(this.uid)) {
            setTitleAndBack(R.string.replies, this.titleBackListener);
        } else {
            setTitleAndBack(R.string.my_reply, this.titleBackListener);
        }
    }

    public static void jump(Context context, String str) {
        context.startActivity(new Intent(context, MyRepliesActivity.class).putExtra("uid", str));
    }

    /* access modifiers changed from: private */
    public void getData() {
        this.loadMoreManager.loadStarted();
        ApiClient.getMyReply(this.uid, this.page, this.pageSize, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<MyReplyModel>() {
            public void accept(@NonNull MyReplyModel myReplyModel) throws Exception {
                int unused = MyRepliesActivity.this.total = 0;
                if (!(myReplyModel == null || myReplyModel.data == null || myReplyModel.data.list == null)) {
                    int unused2 = MyRepliesActivity.this.total = myReplyModel.data.list.size();
                    if (MyRepliesActivity.this.page == 1) {
                        MyRepliesActivity.this.adapter.clear();
                    }
                    MyRepliesActivity.this.handleData(myReplyModel.data.list);
                }
                MyRepliesActivity.this.dismissProgress();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                MyRepliesActivity.this.dismissProgress();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleData(List<MyReplyModel.DataBean.ListBean> list2) {
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

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.callbackManager.onActivityResult(i, i2, intent);
    }

    public void onShare(String str, String str2) {
        share(str, str2);
    }
}
