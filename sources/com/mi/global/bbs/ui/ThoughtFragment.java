package com.mi.global.bbs.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.CallbackManager;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.OnFollowListener;
import com.mi.global.bbs.adapter.ThoughtAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.AllThoughtModel;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.ui.FollowingFragment;
import com.mi.global.bbs.ui.post.PostShortContentActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.NewShareDialog;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class ThoughtFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnFollowListener, OnShareListener {
    public static final int PER_PAGE = 10;
    BaseActivity activity;
    /* access modifiers changed from: private */
    public ThoughtAdapter adapter;
    protected CallbackManager callbackManager;
    /* access modifiers changed from: private */
    public boolean cancelScroll = false;
    /* access modifiers changed from: private */
    public List<AllThoughtModel.ListBean> followDatas = new ArrayList();
    @BindView(2131493285)
    ImageView followingTopIcon;
    @BindView(2131493286)
    ImageView followingTopPost;
    @BindView(2131493819)
    RelativeLayout followingTopPostLayout;
    @BindView(2131493347)
    FrameLayout frameRecycleView;
    private WrapContentLinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131493348)
    ProgressBar mProgress;
    @BindView(2131493155)
    ObservableRecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    /* access modifiers changed from: private */
    public FollowingFragment.OnShowPostListener onShowPostListener;
    /* access modifiers changed from: private */
    public int page = 1;
    /* access modifiers changed from: private */
    public int preScrollY = 0;
    public View rootView;
    /* access modifiers changed from: private */
    public boolean scrollUp = false;
    /* access modifiers changed from: private */
    public int total = 0;
    private BroadcastReceiver updateFollowingData = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            ThoughtFragment.this.onRefresh();
        }
    };

    static /* synthetic */ int access$710(ThoughtFragment thoughtFragment) {
        int i = thoughtFragment.page;
        thoughtFragment.page = i - 1;
        return i;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_thought, viewGroup, false);
        this.activity = (BaseActivity) getActivity();
        ButterKnife.bind((Object) this, this.rootView);
        this.callbackManager = CallbackManager.Factory.create();
        initView();
        getData();
        registBroadcast();
        return this.rootView;
    }

    public void onResume() {
        super.onResume();
    }

    private void initView() {
        this.loadMoreManager = new LoadMoreManager();
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setVisibility(8);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.adapter = new ThoughtAdapter((BaseActivity) getActivity(), this.loadMoreManager);
        this.adapter.setOnFollowListener(this);
        this.layoutManager = new WrapContentLinearLayoutManager(getActivity());
        this.mRecycleView.setLayoutManager(this.layoutManager);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.setHasFixedSize(true);
        this.mRecycleView.setItemViewCacheSize(20);
        this.mRecycleView.setDrawingCacheEnabled(true);
        this.mRecycleView.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (!ThoughtFragment.this.loadMoreManager.isDataLoading()) {
                    ThoughtFragment.this.loadMore();
                }
            }
        });
        this.mRecycleView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            public void onScrollChanged(int i, boolean z, boolean z2) {
                if (i > 100) {
                    ThoughtFragment.this.onShowPostListener.onShow(true);
                } else {
                    ThoughtFragment.this.onShowPostListener.onShow(false);
                }
                if (!ThoughtFragment.this.cancelScroll) {
                    if (i > ThoughtFragment.this.preScrollY && !ThoughtFragment.this.scrollUp) {
                        boolean unused = ThoughtFragment.this.scrollUp = true;
                    }
                    if (i < ThoughtFragment.this.preScrollY && ThoughtFragment.this.scrollUp) {
                        boolean unused2 = ThoughtFragment.this.scrollUp = false;
                    }
                    int unused3 = ThoughtFragment.this.preScrollY = i;
                }
            }

            public void onDownMotionEvent() {
                boolean unused = ThoughtFragment.this.cancelScroll = false;
            }

            public void onUpOrCancelMotionEvent(ScrollState scrollState) {
                boolean unused = ThoughtFragment.this.cancelScroll = true;
            }
        });
        this.followingTopPostLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING, Constants.ClickEvent.CLICK_TOP_POST, "");
                PostShortContentActivity.jump(ThoughtFragment.this.activity);
            }
        });
        this.followingTopIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginManager.getInstance().hasLogin()) {
                    UserCenterActivity.jump(ThoughtFragment.this.getActivity(), LoginManager.getInstance().getUserId());
                } else {
                    ThoughtFragment.this.activity.gotoAccount();
                }
            }
        });
    }

    private void registBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PostShortContentActivity.UPDATE_FOLLOWING_ACTION);
        getActivity().registerReceiver(this.updateFollowingData, intentFilter);
    }

    /* access modifiers changed from: private */
    public void loadMore() {
        if (this.total > 0) {
            this.page++;
            GoogleTrackerUtil.sendRecordEvent("feed", "click_Feed_Thoughts", "click_Feed_Thoughts_" + this.page);
            getData();
        }
    }

    private void getData() {
        if (this.page > 0) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getAllThoughtList(this.page, 10, bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<AllThoughtModel>() {
            public void accept(@NonNull AllThoughtModel allThoughtModel) throws Exception {
                ThoughtFragment.this.dismissProgress();
                if (allThoughtModel == null || allThoughtModel.data == null) {
                    int unused = ThoughtFragment.this.total = 0;
                    if (ThoughtFragment.this.page > 1) {
                        ThoughtFragment.access$710(ThoughtFragment.this);
                    }
                } else if (ThoughtFragment.this.page == 1) {
                    ThoughtFragment.this.handleFirstResponse(allThoughtModel);
                } else {
                    ThoughtFragment.this.handleResponse(allThoughtModel);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                if (ThoughtFragment.this.page == 1) {
                    ThoughtFragment.this.followDatas.clear();
                    AllThoughtModel.ListBean listBean = new AllThoughtModel.ListBean();
                    listBean.type_id = 200;
                    ThoughtFragment.this.followDatas.add(listBean);
                    ThoughtFragment.this.adapter.addData(ThoughtFragment.this.followDatas);
                }
                int unused = ThoughtFragment.this.total = 0;
                ThoughtFragment.this.dismissProgress();
                if (LoginManager.getInstance().hasLogin()) {
                    ThoughtFragment.this.frameRecycleView.setVisibility(0);
                } else {
                    ThoughtFragment.this.frameRecycleView.setVisibility(8);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleFirstResponse(AllThoughtModel allThoughtModel) {
        this.followDatas.clear();
        this.total = 0;
        AllThoughtModel.ListBean listBean = new AllThoughtModel.ListBean();
        listBean.type_id = 200;
        this.followDatas.add(listBean);
        if (!(allThoughtModel == null || allThoughtModel.data == null || allThoughtModel.data.size() <= 0)) {
            this.followDatas.addAll(allThoughtModel.data);
            this.total = allThoughtModel.data.size();
        }
        this.adapter.addData(this.followDatas);
    }

    /* access modifiers changed from: private */
    public void handleResponse(AllThoughtModel allThoughtModel) {
        this.total = 0;
        if (allThoughtModel != null) {
            boolean z = true;
            boolean z2 = allThoughtModel.data != null;
            if (allThoughtModel.data.size() <= 0) {
                z = false;
            }
            if (z2 && z) {
                this.total = allThoughtModel.data.size();
                for (int i = 0; i < allThoughtModel.data.size(); i++) {
                    this.followDatas.add(allThoughtModel.data.get(i));
                }
            }
        }
        this.adapter.addData(this.followDatas);
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        this.loadMoreManager.loadFinished();
        if (this.mProgress != null && this.mProgress.getVisibility() == 0) {
            this.mProgress.setVisibility(8);
        }
        if (this.mRefreshView != null) {
            this.mRefreshView.setVisibility(0);
            this.mRefreshView.setRefreshing(false);
        }
    }

    public void onRefresh() {
        this.page = 1;
        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(this.updateFollowingData);
    }

    public void onShare(String str, String str2) {
        share(str, str2);
    }

    private void share(String str, String str2) {
        new NewShareDialog(getActivity()).setShareTitle(str).setShareUrl(str2).setCallbackManager(this.callbackManager).show();
    }

    public void setPadding(int i) {
        if (this.mRecycleView != null) {
            this.mRecycleView.setPadding(0, 0, 0, i);
        }
    }

    public void onFollow(final int i, final String str, boolean z) {
        if (z) {
            showProDialog(getString(R.string.following_ing));
            ApiClient.follow(str, true, bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        ThoughtFragment.this.toast(R.string.follow_success);
                        ThoughtFragment.this.adapter.getFollowDatas(i).followed = 1;
                        ThoughtFragment.this.adapter.notifyDataSetChanged();
                    } else {
                        ThoughtFragment.this.toast(baseResult.getErrmsg());
                        ThoughtFragment.this.adapter.notifyDataSetChanged();
                    }
                    ThoughtFragment.this.dismissProDialog();
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    ThoughtFragment.this.dismissProDialog();
                }
            });
            return;
        }
        DialogFactory.showAlert((Context) getActivity(), getString(R.string.unfollow_hint), getString(R.string.yes), getString(R.string.cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                ThoughtFragment.this.unFollow(i, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void unFollow(final int i, String str) {
        showProDialog(getString(R.string.add_follow));
        ApiClient.follow(str, false, bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
            public void accept(@NonNull BaseResult baseResult) throws Exception {
                if (baseResult.getErrno() == 0) {
                    ThoughtFragment.this.adapter.getFollowDatas(i).followed = 0;
                    ThoughtFragment.this.adapter.notifyDataSetChanged();
                } else {
                    ThoughtFragment.this.toast(baseResult.getErrmsg());
                }
                ThoughtFragment.this.dismissProDialog();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                ThoughtFragment.this.dismissProDialog();
            }
        });
    }

    public void setOnScrollListener(FollowingFragment.OnShowPostListener onShowPostListener2) {
        this.onShowPostListener = onShowPostListener2;
    }

    public void smoothToTop() {
        if (this.layoutManager != null && this.mRecycleView != null) {
            if (this.layoutManager.findLastVisibleItemPosition() > 50) {
                this.layoutManager.scrollToPosition(50);
            }
            this.mRecycleView.smoothScrollToPosition(0);
        }
    }
}
