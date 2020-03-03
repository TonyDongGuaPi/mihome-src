package com.mi.global.bbs.ui.plate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.ForumAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.http.ParamKey;
import com.mi.global.bbs.model.SubForumItem;
import com.mi.global.bbs.model.ThreadListBean;
import com.mi.global.bbs.ui.post.PostActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.utils.StatusBarClickListener;
import com.mi.global.bbs.view.SlidingTabStripView;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.mi.global.shop.model.Tags;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlateActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnShareListener, SlidingTabStripView.OnSelectedListener {
    private static final String PLATE_ID = "id";
    private static final String PLATE_TITLE = "title";
    /* access modifiers changed from: private */
    public ForumAdapter adapter;
    @BindView(2131493155)
    ObservableRecyclerView commonRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout commonRefreshView;
    @BindView(2131493333)
    SlidingTabStripView forumItemTopStrip;
    /* access modifiers changed from: private */
    public boolean hasMore = false;
    private String id = null;
    /* access modifiers changed from: private */
    public WrapContentLinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public HashMap<String, List<ThreadListBean>> listHashMap;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    /* access modifiers changed from: private */
    public int page = 1;
    ImageView postAction;
    /* access modifiers changed from: private */
    public int scrollStripPosition = 0;
    private String title;
    /* access modifiers changed from: private */
    public int toolbarHeight = 0;
    /* access modifiers changed from: private */
    public String typeId = null;
    List<SubForumItem.DataBean.ThreadTypesBean> typeList;

    static /* synthetic */ int access$1008(PlateActivity plateActivity) {
        int i = plateActivity.page;
        plateActivity.page = i + 1;
        return i;
    }

    static /* synthetic */ int access$1010(PlateActivity plateActivity) {
        int i = plateActivity.page;
        plateActivity.page = i - 1;
        return i;
    }

    public static void jumpWithId(Context context, String str, String str2) {
        context.startActivity(new Intent(context, PlateActivity.class).putExtra("id", str).putExtra("title", str2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.contentNeedMargin = false;
        this.statusBarDark = false;
        super.onCreate(bundle);
        addActionButton();
        setCustomContentView(R.layout.bbs_activity_plate);
        ButterKnife.bind((Activity) this);
        this.listHashMap = new HashMap<>();
        adjustStatusBar();
        this.loadMoreManager = new LoadMoreManager();
        this.commonRefreshView.setOnRefreshListener(this);
        this.commonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.layoutManager = new WrapContentLinearLayoutManager(this);
        this.layoutManager.setOrientation(1);
        this.commonRecycleView.setLayoutManager(this.layoutManager);
        this.adapter = new ForumAdapter(this, this.loadMoreManager);
        this.commonRecycleView.setAdapter(this.adapter);
        this.mToolBarContainer.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_title_bar_bg)));
        this.mToolBarContainer.getBackground().setAlpha(0);
        this.mToolBarDivider.setVisibility(4);
        this.commonRecycleView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            public void onDownMotionEvent() {
            }

            public void onUpOrCancelMotionEvent(ScrollState scrollState) {
            }

            public void onScrollChanged(int i, boolean z, boolean z2) {
                int i2 = 0;
                View childAt = PlateActivity.this.commonRecycleView.getChildAt(0);
                if (childAt != null) {
                    int height = childAt.getHeight();
                    int abs = Math.abs(i);
                    if (abs <= height) {
                        PlateActivity.this.mToolBarContainer.getBackground().setAlpha((int) ((((float) abs) / ((float) height)) * 255.0f));
                    } else {
                        PlateActivity.this.mToolBarContainer.getBackground().setAlpha(255);
                    }
                    int unused = PlateActivity.this.scrollStripPosition = (height - PlateActivity.this.forumItemTopStrip.getHeight()) - PlateActivity.this.mToolBarContainer.getHeight();
                    boolean z3 = abs >= PlateActivity.this.scrollStripPosition;
                    PlateActivity.this.forumItemTopStrip.setVisibility(z3 ? 0 : 4);
                    PlateActivity.this.mTitleView.setVisibility(z3 ? 0 : 4);
                    View access$500 = PlateActivity.this.mToolBarDivider;
                    if (!z3) {
                        i2 = 4;
                    }
                    access$500.setVisibility(i2);
                    PlateActivity.this.mUpImageView.setImageResource(z3 ? R.drawable.bbs_arrow_up_black : R.drawable.bbs_arrow_up_white);
                    PlateActivity.this.postAction.setImageResource(z3 ? R.drawable.bbs_post : R.drawable.bbs_post_white);
                    PlateActivity.this.statusBarDarkMode(z3);
                    PlateActivity.this.adapter.setStripViewVisible(z3);
                }
            }
        });
        this.commonRecycleView.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (PlateActivity.this.hasMore) {
                    PlateActivity.access$1008(PlateActivity.this);
                    PlateActivity.this.getData();
                }
            }
        });
        this.forumItemTopStrip.setOnSelectedListener(this);
        this.adapter.setOnSelectedListener(this);
        this.adapter.setOnShareListener(this);
        Intent intent = getIntent();
        this.id = intent.getStringExtra("id");
        this.title = intent.getStringExtra("title");
        setTitleAndBack(this.title, this.titleBackListener);
        this.mTitleView.setVisibility(4);
        getData();
    }

    private void addActionButton() {
        this.postAction = new ImageView(this);
        this.postAction.setImageResource(R.drawable.bbs_post_white);
        this.postAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PlateActivity.this.toPost();
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        this.postAction.setPaddingRelative(0, 0, getResources().getDimensionPixelOffset(R.dimen.padding_normal), 0);
        this.menuLayout.addView(this.postAction, layoutParams);
    }

    /* access modifiers changed from: private */
    public void getData() {
        if (!TextUtils.isEmpty(this.id)) {
            this.loadMoreManager.loadStarted();
            ApiClient.getSubforumItem(this.id, this.page, this.typeId, ParamKey.typeid, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<SubForumItem>() {
                public void accept(@NonNull SubForumItem subForumItem) throws Exception {
                    PlateActivity.this.dismissProgress();
                    boolean z = true;
                    if (subForumItem == null || subForumItem.getErrno() != 0 || subForumItem.data == null) {
                        if (PlateActivity.this.page > 1) {
                            PlateActivity.access$1010(PlateActivity.this);
                        }
                        PlateActivity.this.toast(subForumItem.getErrmsg());
                        return;
                    }
                    if (PlateActivity.this.page == 1) {
                        PlateActivity.this.adapter.clear();
                        PlateActivity.this.listHashMap.put(TextUtils.isEmpty(PlateActivity.this.typeId) ? Tags.BaiduLbs.ADDRTYPE : PlateActivity.this.typeId, subForumItem.data.threadlist);
                    }
                    PlateActivity.this.adapter.setData(subForumItem.data);
                    PlateActivity plateActivity = PlateActivity.this;
                    if (subForumItem.data.threadlist == null || subForumItem.data.threadlist.size() < 30) {
                        z = false;
                    }
                    boolean unused = plateActivity.hasMore = z;
                    if (subForumItem.data.threadtypes != null && subForumItem.data.threadtypes.size() > 0) {
                        PlateActivity.this.typeList = subForumItem.data.threadtypes;
                        PlateActivity.this.forumItemTopStrip.setIndicatorColor(PlateActivity.this.getResources().getColor(R.color.main_yellow));
                        PlateActivity.this.addTopStripView(subForumItem.data.threadtypes);
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    if (PlateActivity.this.page > 1) {
                        PlateActivity.access$1010(PlateActivity.this);
                    }
                    PlateActivity.this.dismissProgress();
                }
            });
        }
    }

    private void adjustStatusBar() {
        this.mToolBarContainer.setOnClickListener(new StatusBarClickListener(new StatusBarClickListener.OnDoubleClickListener() {
            public void onDoubleClick() {
                if (PlateActivity.this.layoutManager.findLastVisibleItemPosition() > 50) {
                    PlateActivity.this.layoutManager.scrollToPosition(50);
                }
                PlateActivity.this.commonRecycleView.smoothScrollToPosition(0);
            }
        }));
        this.mContentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                PlateActivity.this.mContentLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int unused = PlateActivity.this.toolbarHeight = PlateActivity.this.mToolBarContainer.getHeight();
                ((FrameLayout.LayoutParams) PlateActivity.this.forumItemTopStrip.getLayoutParams()).topMargin = PlateActivity.this.toolbarHeight;
            }
        });
    }

    /* access modifiers changed from: private */
    public void addTopStripView(List<SubForumItem.DataBean.ThreadTypesBean> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getString(R.string.all));
        for (SubForumItem.DataBean.ThreadTypesBean threadTypesBean : list) {
            arrayList.add(threadTypesBean.name);
        }
        this.forumItemTopStrip.notifyDataSetChanged(arrayList);
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
        getData();
    }

    public void onSelected(int i) {
        this.forumItemTopStrip.setCurrentPosition(i);
        this.adapter.setStripPosition(i);
        if (i == 0) {
            this.typeId = null;
        } else {
            this.typeId = this.typeList.get(i - 1).typeid + "";
        }
        if (this.commonRecycleView.getCurrentScrollY() > 0) {
            if (this.layoutManager.findLastVisibleItemPosition() > 20) {
                this.layoutManager.scrollToPosition(20);
            }
            this.commonRecycleView.smoothScrollToPosition(0);
            delayToGetData(600);
            return;
        }
        delayToGetData(0);
    }

    private void delayToGetData(long j) {
        this.commonRecycleView.postDelayed(new Runnable() {
            public void run() {
                PlateActivity.this.adapter.clear();
                PlateActivity.this.adapter.notifyDataSetChanged();
                PlateActivity.this.loadMoreManager.resetLoadingCount();
                boolean z = true;
                int unused = PlateActivity.this.page = 1;
                List list = (List) PlateActivity.this.listHashMap.get(TextUtils.isEmpty(PlateActivity.this.typeId) ? Tags.BaiduLbs.ADDRTYPE : PlateActivity.this.typeId);
                if (list == null || list.size() <= 0) {
                    PlateActivity.this.getData();
                    return;
                }
                PlateActivity.this.adapter.add(list);
                PlateActivity plateActivity = PlateActivity.this;
                if (list.size() < 30) {
                    z = false;
                }
                boolean unused2 = plateActivity.hasMore = z;
            }
        }, j);
    }

    private void share(String str, String str2) {
        new ShareDialog(this).setShareTitle(str).setShareUrl(str2).setCallbackManager(this.callbackManager).show();
    }

    public void onShare(String str, String str2) {
        share(str, str2);
    }

    public void toPost() {
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_fORUM, "click_post", "from_" + this.title);
        PostActivity.jump(this, this.title);
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
    }
}
