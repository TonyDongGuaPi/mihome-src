package com.mi.global.bbs.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.CallbackManager;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.MyThreadAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.MyThreadModel;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.MPopupWindow;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class MyThreadActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnShareListener {
    /* access modifiers changed from: private */
    public boolean GET_ALL_THREAD = true;
    /* access modifiers changed from: private */
    public MyThreadAdapter adapter;
    private CallbackManager callbackManager;
    private List<MyThreadModel.DataBean.ListBean> list;
    private LoadMoreManager loadMoreManager;
    @BindView(2131492947)
    ProgressBar mProgress;
    @BindView(2131493155)
    RecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    /* access modifiers changed from: private */
    public int page = 1;
    /* access modifiers changed from: private */
    public int pageSize = 10;
    MPopupWindow pop;
    int popOffset = 12;
    /* access modifiers changed from: private */
    public int total = 0;
    private String uid;

    static /* synthetic */ int access$108(MyThreadActivity myThreadActivity) {
        int i = myThreadActivity.page;
        myThreadActivity.page = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_my_favor);
        ButterKnife.bind((Activity) this);
        this.callbackManager = CallbackManager.Factory.create();
        if (!isLiteVersion()) {
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.bbs_my_thread_indicate, getTheme());
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            this.mTitleView.setCompoundDrawables((Drawable) null, (Drawable) null, drawable, (Drawable) null);
            this.mTitleView.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.common_padding));
            this.mTitleView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        }
        addClickListenerToTitle();
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this);
        wrapContentLinearLayoutManager.setOrientation(1);
        this.mRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.list = new ArrayList();
        this.loadMoreManager = new LoadMoreManager();
        this.adapter = new MyThreadAdapter(this, this.loadMoreManager, this.list);
        this.adapter.setOnShareListener(this);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.addOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (MyThreadActivity.this.total > 0 && MyThreadActivity.this.total >= MyThreadActivity.this.page * MyThreadActivity.this.pageSize) {
                    MyThreadActivity.access$108(MyThreadActivity.this);
                    MyThreadActivity.this.getData();
                }
            }
        });
        this.uid = getIntent().getStringExtra("uid");
        handleTitle();
        getData();
    }

    private void handleTitle() {
        String userId = LoginManager.getInstance().getUserId();
        if (TextUtils.isEmpty(userId) || (!TextUtils.isEmpty(this.uid) && !this.uid.equals(userId))) {
            setTitleAndBack(R.string.threads, this.titleBackListener);
        } else {
            setTitleAndBack(R.string.my_posts, this.titleBackListener);
        }
    }

    private void addClickListenerToTitle() {
        this.mTitleView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MyThreadActivity.this.showPopList();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showPopList() {
        this.popOffset = getResources().getDimensionPixelSize(R.dimen.common_padding);
        View inflate = getLayoutInflater().inflate(R.layout.bbs_my_thread_pop_layout, (ViewGroup) null);
        this.pop = new MPopupWindow.Builder(this).setView(inflate).enableBackgroundDark(true).create();
        inflate.findViewById(R.id.my_thread).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!MyThreadActivity.this.GET_ALL_THREAD) {
                    boolean unused = MyThreadActivity.this.GET_ALL_THREAD = true;
                    MyThreadActivity.this.mTitleView.setText(R.string.all_threads);
                    MyThreadActivity.this.mRefreshView.setRefreshing(true);
                    MyThreadActivity.this.onRefresh();
                }
                MyThreadActivity.this.pop.dismiss();
            }
        });
        inflate.findViewById(R.id.my_thread_in_column).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MyThreadActivity.this.GET_ALL_THREAD) {
                    boolean unused = MyThreadActivity.this.GET_ALL_THREAD = false;
                    MyThreadActivity.this.mTitleView.setText(R.string.threads_added_in_column);
                    MyThreadActivity.this.mRefreshView.setRefreshing(true);
                    MyThreadActivity.this.onRefresh();
                }
                MyThreadActivity.this.pop.dismiss();
            }
        });
        this.pop.showAsDropDown(this.mTitleView, this.popOffset, -this.popOffset);
    }

    /* access modifiers changed from: private */
    public void getData() {
        if (this.page == 1) {
            this.loadMoreManager.loadStarted();
        }
        if (this.GET_ALL_THREAD) {
            ApiClient.getMyPost(this.uid, this.page, this.pageSize, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<MyThreadModel>() {
                public void accept(@NonNull MyThreadModel myThreadModel) throws Exception {
                    MyThreadActivity.this.dismissProgress();
                    if (MyThreadActivity.this.page == 1) {
                        MyThreadActivity.this.adapter.clear();
                    }
                    if (myThreadModel != null && myThreadModel.data != null) {
                        int unused = MyThreadActivity.this.total = 0;
                        if (myThreadModel.data.list != null) {
                            int unused2 = MyThreadActivity.this.total = myThreadModel.data.list.size();
                        }
                        MyThreadActivity.this.handleData(myThreadModel.data.list);
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    MyThreadActivity.this.dismissProgress();
                }
            });
        } else {
            ApiClient.getMyColumnPost(this.uid, this.page, this.pageSize, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<MyThreadModel>() {
                public void accept(@NonNull MyThreadModel myThreadModel) throws Exception {
                    if (MyThreadActivity.this.page == 1) {
                        MyThreadActivity.this.adapter.clear();
                    }
                    if (!(myThreadModel == null || myThreadModel.data == null)) {
                        int unused = MyThreadActivity.this.total = 0;
                        if (myThreadModel.data.list != null) {
                            int unused2 = MyThreadActivity.this.total = myThreadModel.data.list.size();
                        }
                        MyThreadActivity.this.handleData(myThreadModel.data.list);
                    }
                    MyThreadActivity.this.dismissProgress();
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    MyThreadActivity.this.dismissProgress();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void handleData(List<MyThreadModel.DataBean.ListBean> list2) {
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

    public static void show(Context context, String str) {
        context.startActivity(new Intent(context, MyThreadActivity.class).putExtra("uid", str));
    }
}
