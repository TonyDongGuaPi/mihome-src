package com.mi.global.bbs.ui;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.MIUIForumAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.MIUIContentModel;
import com.mi.global.bbs.model.MIUIForumModel;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.utils.StatusBarClickListener;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MIUIActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnShareListener {
    private final String MIUI = "MIUI";
    /* access modifiers changed from: private */
    public MIUIForumAdapter adapter;
    /* access modifiers changed from: private */
    public WrapContentLinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131493155)
    ObservableRecyclerView mCommonRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mCommonRefreshView;
    /* access modifiers changed from: private */
    public int page = 1;
    /* access modifiers changed from: private */
    public int pageSize = 10;

    static /* synthetic */ int access$1008(MIUIActivity mIUIActivity) {
        int i = mIUIActivity.page;
        mIUIActivity.page = i + 1;
        return i;
    }

    static /* synthetic */ int access$1010(MIUIActivity mIUIActivity) {
        int i = mIUIActivity.page;
        mIUIActivity.page = i - 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.contentNeedMargin = false;
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_common_refresh_layout);
        ButterKnife.bind((Activity) this);
        addTitleDoubleClickAction();
        setTitleAndBack("", this.titleBackListener);
        this.loadMoreManager = new LoadMoreManager();
        this.mCommonRefreshView.setOnRefreshListener(this);
        this.mCommonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.layoutManager = new WrapContentLinearLayoutManager(this);
        this.layoutManager.setOrientation(1);
        this.mCommonRecycleView.setLayoutManager(this.layoutManager);
        this.mToolBarContainer.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_title_bar_bg)));
        this.mToolBarContainer.getBackground().setAlpha(0);
        this.mToolBarDivider.setVisibility(4);
        this.mCommonRecycleView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            public void onDownMotionEvent() {
            }

            public void onUpOrCancelMotionEvent(ScrollState scrollState) {
            }

            public void onScrollChanged(int i, boolean z, boolean z2) {
                int height = MIUIActivity.this.mToolBarContainer.getHeight();
                int abs = Math.abs(i);
                if (abs <= height) {
                    MIUIActivity.this.mToolBarContainer.getBackground().setAlpha((int) ((((float) abs) / ((float) height)) * 255.0f));
                    MIUIActivity.this.mToolBarDivider.setVisibility(4);
                    MIUIActivity.this.mTitleView.setVisibility(4);
                    MIUIActivity.this.mUpImageView.setImageResource(R.drawable.bbs_arrow_up_white);
                    return;
                }
                MIUIActivity.this.mToolBarDivider.setVisibility(0);
                MIUIActivity.this.mToolBarContainer.getBackground().setAlpha(255);
                MIUIActivity.this.mTitleView.setVisibility(0);
                MIUIActivity.this.mUpImageView.setImageResource(R.drawable.bbs_arrow_up_black);
            }
        });
        this.adapter = new MIUIForumAdapter(this, this.loadMoreManager);
        this.adapter.setOnShareListener(this);
        this.mCommonRecycleView.setAdapter(this.adapter);
        this.mCommonRecycleView.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (MIUIActivity.this.adapter.forumSize() >= MIUIActivity.this.page * MIUIActivity.this.pageSize) {
                    MIUIActivity.access$1008(MIUIActivity.this);
                    MIUIActivity.this.getMIUIForumList();
                }
            }
        });
        this.mCommonRefreshView.setRefreshing(true);
        getMIUIContentAndForumList();
    }

    /* access modifiers changed from: private */
    public void getMIUIForumList() {
        this.loadMoreManager.loadStarted();
        ApiClient.getApiService().getMIUIForumList("MIUI", this.page, this.pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<MIUIForumModel>() {
            public void accept(MIUIForumModel mIUIForumModel) throws Exception {
                MIUIActivity.this.loadMoreManager.loadFinished();
                if (mIUIForumModel.getErrno() == 0 && mIUIForumModel != null && mIUIForumModel.data != null && mIUIForumModel.data.list != null) {
                    MIUIActivity.this.adapter.addForumList(mIUIForumModel.data.list);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                MIUIActivity.access$1010(MIUIActivity.this);
                MIUIActivity.this.loadMoreManager.loadFinished();
            }
        });
    }

    private void getMIUIContentAndForumList() {
        ApiClient.getApiService().getMIUIContent().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<MIUIContentModel>() {
            public void accept(MIUIContentModel mIUIContentModel) throws Exception {
                MIUIActivity.this.mCommonRefreshView.setRefreshing(false);
                MIUIActivity.this.loadMoreManager.loadStarted();
                if (mIUIContentModel.getErrno() != 0 || mIUIContentModel.data == null) {
                    MIUIActivity.this.toast(mIUIContentModel.getErrmsg());
                } else if (mIUIContentModel.data.banner != null) {
                    MIUIActivity.this.adapter.setContentModel(mIUIContentModel);
                    MIUIActivity.this.setTitle((CharSequence) mIUIContentModel.data.banner.name);
                }
            }
        }).observeOn(Schedulers.io()).flatMap(new Function<MIUIContentModel, ObservableSource<MIUIForumModel>>() {
            public ObservableSource<MIUIForumModel> apply(@NonNull MIUIContentModel mIUIContentModel) throws Exception {
                return ApiClient.getApiService().getMIUIForumList("MIUI", MIUIActivity.this.page, MIUIActivity.this.pageSize);
            }
        }).observeOn(AndroidSchedulers.mainThread()).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<MIUIForumModel>() {
            public void accept(MIUIForumModel mIUIForumModel) throws Exception {
                MIUIActivity.this.loadMoreManager.loadFinished();
                if (mIUIForumModel.getErrno() == 0 && mIUIForumModel != null && mIUIForumModel.data != null && mIUIForumModel.data.list != null) {
                    if (MIUIActivity.this.page == 1) {
                        MIUIActivity.this.adapter.clear();
                    }
                    MIUIActivity.this.adapter.addForumList(mIUIForumModel.data.list);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                MIUIActivity.this.loadMoreManager.loadFinished();
                MIUIActivity.this.mCommonRefreshView.setRefreshing(false);
            }
        });
    }

    private void addTitleDoubleClickAction() {
        this.mToolBarContainer.setOnClickListener(new StatusBarClickListener(new StatusBarClickListener.OnDoubleClickListener() {
            public void onDoubleClick() {
                if (MIUIActivity.this.layoutManager.findLastVisibleItemPosition() > 50) {
                    MIUIActivity.this.layoutManager.scrollToPosition(50);
                }
                MIUIActivity.this.mCommonRecycleView.smoothScrollToPosition(0);
            }
        }));
    }

    public void onRefresh() {
        this.page = 1;
        getMIUIContentAndForumList();
    }

    public void onShare(String str, String str2) {
        share(str, str2);
    }

    private void share(String str, String str2) {
        new ShareDialog(this).setShareTitle(str).setShareUrl(str2).setCallbackManager(this.callbackManager).show();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        GoogleTrackerUtil.sendRecordEvent("miui", "click_miui_no", "click_miui_no_" + this.page);
    }
}
