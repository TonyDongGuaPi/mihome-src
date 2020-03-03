package com.mi.global.bbs.ui;

import android.animation.Animator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.CallbackManager;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.FollowingAdapter;
import com.mi.global.bbs.adapter.FollowingRecommendItemAdapter;
import com.mi.global.bbs.adapter.OnFollowListener;
import com.mi.global.bbs.adapter.OnFollowingFollowListener;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.DiscoveryUserModel;
import com.mi.global.bbs.model.FollowData;
import com.mi.global.bbs.model.FollowingFeedModel;
import com.mi.global.bbs.model.FollowingUserDataModel;
import com.mi.global.bbs.model.SubFollowItem;
import com.mi.global.bbs.model.UserInfoModel;
import com.mi.global.bbs.observer.RefreshManager;
import com.mi.global.bbs.ui.post.PostShortContentActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.CheckUtil;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DefaultAnimatorListener;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.Editor.FontTextView;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.NewShareDialog;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class FollowingFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnFollowListener, OnFollowingFollowListener, OnShareListener, Observer {
    private static final String FOLLOWING_CACHE = "following_cache";
    public static final int FOLLOWING_TOP_HIGH = 100;
    public static final int FOLLOW_STATED = 1;
    private static final String IS_FEED_EMPTY = "is_feed_empty";
    public static final int PER_PAGE = 10;
    public static final int UNFOLLOW_STATED = 0;
    private static final String USER_DATA_CACHE = "user_data_cache";
    BaseActivity activity;
    /* access modifiers changed from: private */
    public FollowingAdapter adapter;
    protected CallbackManager callbackManager;
    /* access modifiers changed from: private */
    public boolean cancelScroll = false;
    public List<FollowingFeedModel> currentFeedList;
    /* access modifiers changed from: private */
    public int followCount = 0;
    private List<FollowData> followDatas = new ArrayList();
    /* access modifiers changed from: private */
    public List<String> followIds = new ArrayList();
    /* access modifiers changed from: private */
    public FollowingRecommendItemAdapter followingRecommendItemAdapter;
    @BindView(2131493285)
    ImageView followingTopIcon;
    @BindView(2131493286)
    ImageView followingTopPost;
    @BindView(2131493819)
    RelativeLayout followingTopPostLayout;
    @BindView(2131493378)
    LinearLayout frameNoLogin;
    @BindView(2131493347)
    FrameLayout frameRecycleView;
    /* access modifiers changed from: private */
    public long lastFeedId;
    private WrapContentLinearLayoutManager layoutManager;
    @BindView(2131493282)
    ImageView leftButton;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131493268)
    FrameLayout mFollowNotifyContainer;
    @BindView(2131493269)
    FontTextView mFollowNotifyText;
    @BindView(2131493348)
    ProgressBar mProgress;
    @BindView(2131493155)
    ObservableRecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    /* access modifiers changed from: private */
    public OnShowPostListener onShowPostListener;
    /* access modifiers changed from: private */
    public int page = 1;
    /* access modifiers changed from: private */
    public int preScrollY = 0;
    /* access modifiers changed from: private */
    public int recommendPage = 1;
    /* access modifiers changed from: private */
    public int recommendTotal = 0;
    @BindView(2131493734)
    ImageView rightButton;
    public View rootView;
    @BindView(2131493903)
    RelativeLayout rvFollowingDone;
    /* access modifiers changed from: private */
    public boolean scrollUp = false;
    @BindView(2131494101)
    View toolbarAgent;
    /* access modifiers changed from: private */
    public int total = 0;
    @BindView(2131494129)
    TextView tvFollowingCount;
    private BroadcastReceiver updateFollowingData = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            FollowingFragment.this.onRefresh();
        }
    };

    public interface OnShowPostListener {
        void onShow(boolean z);
    }

    static /* synthetic */ int access$1508(FollowingFragment followingFragment) {
        int i = followingFragment.followCount;
        followingFragment.followCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$1510(FollowingFragment followingFragment) {
        int i = followingFragment.followCount;
        followingFragment.followCount = i - 1;
        return i;
    }

    static /* synthetic */ int access$810(FollowingFragment followingFragment) {
        int i = followingFragment.page;
        followingFragment.page = i - 1;
        return i;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_following, viewGroup, false);
        this.activity = (BaseActivity) getActivity();
        ButterKnife.bind((Object) this, this.rootView);
        RefreshManager.init().addObserver(this);
        this.callbackManager = CallbackManager.Factory.create();
        initView();
        checkIfHaveCache();
        checkLogin();
        registBroadcast();
        return this.rootView;
    }

    public void onResume() {
        super.onResume();
        GoogleTrackerUtil.sendPageShowEvent(getClass().getName(), "page_show");
    }

    private void initView() {
        this.loadMoreManager = new LoadMoreManager();
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setVisibility(8);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.adapter = new FollowingAdapter((BaseActivity) getActivity(), this.loadMoreManager, Constants.PageFragment.PAGE_FOLLOWING);
        this.adapter.setOnShareListener(this);
        this.adapter.setOnFollowListener(this);
        this.adapter.setOnFollowingFollowListener(this);
        this.layoutManager = new WrapContentLinearLayoutManager(getActivity());
        this.mRecycleView.setLayoutManager(this.layoutManager);
        this.mRecycleView.setAdapter(this.adapter);
        this.mRecycleView.setHasFixedSize(true);
        this.mRecycleView.setItemViewCacheSize(20);
        this.mRecycleView.setDrawingCacheEnabled(true);
        this.mRecycleView.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (!FollowingFragment.this.loadMoreManager.isDataLoading()) {
                    FollowingFragment.this.loadMore();
                }
            }
        });
        this.mRecycleView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            public void onScrollChanged(int i, boolean z, boolean z2) {
                if (i > 100) {
                    FollowingFragment.this.onShowPostListener.onShow(true);
                } else {
                    FollowingFragment.this.onShowPostListener.onShow(false);
                }
                if (!FollowingFragment.this.cancelScroll) {
                    if (i > FollowingFragment.this.preScrollY && !FollowingFragment.this.scrollUp) {
                        boolean unused = FollowingFragment.this.scrollUp = true;
                    }
                    if (i < FollowingFragment.this.preScrollY && FollowingFragment.this.scrollUp) {
                        boolean unused2 = FollowingFragment.this.scrollUp = false;
                    }
                    int unused3 = FollowingFragment.this.preScrollY = i;
                }
            }

            public void onDownMotionEvent() {
                boolean unused = FollowingFragment.this.cancelScroll = false;
            }

            public void onUpOrCancelMotionEvent(ScrollState scrollState) {
                boolean unused = FollowingFragment.this.cancelScroll = true;
            }
        });
        this.followingTopPostLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING, Constants.ClickEvent.CLICK_TOP_POST, "");
                PostShortContentActivity.jump(FollowingFragment.this.activity);
            }
        });
        this.followingTopIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginManager.getInstance().hasLogin()) {
                    UserCenterActivity.jump(FollowingFragment.this.getActivity(), LoginManager.getInstance().getUserId());
                } else {
                    FollowingFragment.this.activity.gotoAccount();
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
        if (Utils.Preference.getBooleanPref(getActivity(), IS_FEED_EMPTY, false)) {
            if (this.recommendTotal > 0) {
                this.recommendPage++;
                getRecommendData();
            }
        } else if (this.total > 0) {
            this.page++;
            GoogleTrackerUtil.sendRecordEvent("feed", "click_Feed_Following", "click_Feed_Following_" + this.page);
            getData();
        }
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, "click_feed_no", "click_feed_no_" + this.page);
    }

    private void checkIfHaveCache() {
        if (!Utils.Preference.getBooleanPref(getActivity(), IS_FEED_EMPTY, false)) {
            String stringPref = Utils.Preference.getStringPref(getActivity(), FOLLOWING_CACHE, "");
            if (!TextUtils.isEmpty(stringPref)) {
                dismissProgress();
                handleResponse((SubFollowItem) JsonParser.parse(stringPref, SubFollowItem.class));
                return;
            }
            return;
        }
        String stringPref2 = Utils.Preference.getStringPref(getActivity(), USER_DATA_CACHE, "");
        if (!TextUtils.isEmpty(stringPref2)) {
            dismissProgress();
            handleData(JsonParser.parseList(stringPref2, new TypeToken<List<FollowingUserDataModel>>() {
            }.getType()));
        }
    }

    private void checkLogin() {
        if (LoginManager.getInstance().hasLogin()) {
            UserInfoModel userInfoModel = (UserInfoModel) JsonParser.parse(Utils.Preference.getStringPref(getActivity(), "userInfo", ""), UserInfoModel.class);
            if (!(userInfoModel == null || userInfoModel.data == null || userInfoModel.data.icon == null)) {
                ImageLoader.showImage(this.followingTopIcon, userInfoModel.data.icon, (RequestOptions) new RequestOptions().a(R.drawable.icon_default_head));
            }
            this.frameNoLogin.setVisibility(8);
            this.frameRecycleView.setVisibility(0);
            onRefresh();
            return;
        }
        Glide.a(getActivity()).a(Integer.valueOf(R.drawable.icon_default_head)).a(this.followingTopIcon);
        this.frameNoLogin.setVisibility(0);
        this.frameRecycleView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void getData() {
        if (this.page > 0) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getFollowingFeed(this.page, 10, String.valueOf(this.lastFeedId), bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<SubFollowItem>() {
            public void accept(@NonNull SubFollowItem subFollowItem) throws Exception {
                FollowingFragment.this.dismissProgress();
                if (subFollowItem == null || subFollowItem.getCode() != 0 || subFollowItem.data == null) {
                    int unused = FollowingFragment.this.total = 0;
                    if (FollowingFragment.this.page > 1) {
                        FollowingFragment.access$810(FollowingFragment.this);
                    }
                    FollowingFragment.this.toast(subFollowItem.getError());
                    return;
                }
                FollowingFragment.this.saveFeedCache(new Gson().toJson((Object) subFollowItem));
                if (FollowingFragment.this.page == 1) {
                    FollowingFragment.this.handleFirstResponse(subFollowItem);
                } else {
                    FollowingFragment.this.handleResponse(subFollowItem);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                int unused = FollowingFragment.this.total = 0;
                FollowingFragment.this.dismissProgress();
                if (LoginManager.getInstance().hasLogin()) {
                    FollowingFragment.this.frameNoLogin.setVisibility(8);
                    FollowingFragment.this.frameRecycleView.setVisibility(0);
                    return;
                }
                FollowingFragment.this.frameNoLogin.setVisibility(0);
                FollowingFragment.this.frameRecycleView.setVisibility(8);
            }
        });
    }

    private void getFollowDoneData() {
        ApiClient.followingFollowDone(getFollowIds(), bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<BaseResult>() {
            public void accept(@NonNull BaseResult baseResult) throws Exception {
                FollowingFragment.this.dismissProgress();
                long unused = FollowingFragment.this.lastFeedId = 0;
                FollowingFragment.this.getData();
                FollowingFragment.this.followIds.clear();
                int unused2 = FollowingFragment.this.followCount = 0;
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                FollowingFragment.this.dismissProgress();
                if (LoginManager.getInstance().hasLogin()) {
                    FollowingFragment.this.frameNoLogin.setVisibility(8);
                    FollowingFragment.this.frameRecycleView.setVisibility(0);
                    return;
                }
                FollowingFragment.this.frameNoLogin.setVisibility(0);
                FollowingFragment.this.frameRecycleView.setVisibility(8);
            }
        });
    }

    private String getFollowIds() {
        String str = "";
        for (String checkAes : this.followIds) {
            str = str + CheckUtil.getCheckAes(checkAes) + ",";
        }
        return str.length() > 0 ? str.substring(0, str.length() - 1) : str;
    }

    /* access modifiers changed from: private */
    public void saveFeedCache(String str) {
        Utils.Preference.setStringPref(getActivity(), FOLLOWING_CACHE, str);
    }

    /* access modifiers changed from: private */
    public void saveUserCache(String str) {
        Utils.Preference.setStringPref(getActivity(), USER_DATA_CACHE, str);
    }

    /* access modifiers changed from: private */
    public void handleFirstResponse(SubFollowItem subFollowItem) {
        this.followDatas.clear();
        if (((subFollowItem == null || subFollowItem.data == null || subFollowItem.data.FeedData == null || subFollowItem.data.FeedData.size() <= 0) ? 0 : subFollowItem.data.FeedData.size()) <= 0) {
            Utils.Preference.setBooleanPref(getActivity(), IS_FEED_EMPTY, true);
            FollowData followData = new FollowData();
            followData.setType(91);
            this.followDatas.add(followData);
            getRecommendData();
        } else {
            Utils.Preference.setBooleanPref(getActivity(), IS_FEED_EMPTY, false);
            if (this.currentFeedList == null || !this.currentFeedList.equals(subFollowItem.data.FeedData)) {
                showNotifyLoadedView();
            }
            this.currentFeedList = subFollowItem.data.FeedData;
            showFeedList(subFollowItem, (FollowData) null);
        }
        showIsEmptyView();
    }

    private void showFeedList(SubFollowItem subFollowItem, FollowData followData) {
        FollowData followData2 = new FollowData();
        followData2.setType(200);
        this.followDatas.add(followData2);
        long j = Long.MAX_VALUE;
        for (int i = 0; i < subFollowItem.data.FeedData.size(); i++) {
            if (i == 5 && subFollowItem.data.UserData != null && subFollowItem.data.UserData.size() > 0) {
                FollowData followData3 = new FollowData();
                followData3.setFollowingUserDataModels(subFollowItem.data.UserData);
                this.followDatas.add(followData3);
            }
            FollowingFeedModel followingFeedModel = subFollowItem.data.FeedData.get(i);
            FollowData followData4 = new FollowData();
            followData4.setFollowingFeedModel(followingFeedModel);
            this.followDatas.add(followData4);
            if (followingFeedModel.Id < j) {
                j = followingFeedModel.Id;
            }
        }
        this.lastFeedId = j;
        this.total = 0;
        this.total = subFollowItem.data.FeedData.size();
        this.adapter.addData(this.followDatas);
        this.adapter.notifyDataSetChanged();
    }

    private void showIsEmptyView() {
        if (Utils.Preference.getBooleanPref(getActivity(), IS_FEED_EMPTY, false)) {
            updateFollowCountText();
            this.rvFollowingDone.setVisibility(0);
            return;
        }
        this.rvFollowingDone.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void handleResponse(SubFollowItem subFollowItem) {
        this.total = 0;
        if (!(subFollowItem == null || subFollowItem.data == null || subFollowItem.data.FeedData == null || subFollowItem.data.FeedData.size() <= 0)) {
            this.total = subFollowItem.data.FeedData.size();
            long j = Long.MAX_VALUE;
            for (int i = 0; i < subFollowItem.data.FeedData.size(); i++) {
                FollowingFeedModel followingFeedModel = subFollowItem.data.FeedData.get(i);
                FollowData followData = new FollowData();
                followData.setFollowingFeedModel(followingFeedModel);
                this.followDatas.add(followData);
                if (followingFeedModel.Id < j) {
                    j = followingFeedModel.Id;
                }
            }
            this.lastFeedId = j;
        }
        this.adapter.addData(this.followDatas);
        this.adapter.notifyDataSetChanged();
    }

    private void getRecommendData() {
        this.loadMoreManager.loadStarted();
        ApiClient.getDiscoveryPeople(this.recommendPage, 10, 1, bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<DiscoveryUserModel>() {
            public void accept(@NonNull DiscoveryUserModel discoveryUserModel) throws Exception {
                FollowingFragment.this.dismissProgress();
                if (FollowingFragment.this.recommendPage == 1) {
                    FollowingFragment.this.adapter.clear();
                }
                int unused = FollowingFragment.this.recommendTotal = 0;
                if (discoveryUserModel != null && discoveryUserModel.data != null) {
                    FollowingFragment.this.saveUserCache(new Gson().toJson((Object) discoveryUserModel.data));
                    int unused2 = FollowingFragment.this.recommendTotal = discoveryUserModel.data.size();
                    FollowingFragment.this.handleData(discoveryUserModel.data);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                FollowingFragment.this.dismissProgress();
                int unused = FollowingFragment.this.recommendTotal = 0;
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleData(List<FollowingUserDataModel> list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                FollowData followData = new FollowData();
                followData.setFollowingUserDataModels(list.get(i));
                this.followDatas.add(followData);
            }
        }
        this.adapter.addData(this.followDatas);
        this.adapter.notifyDataSetChanged();
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

    @OnClick({2131493267, 2131493735, 2131494130})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.follow_login_bt) {
            gotoAccount();
        } else if (id == R.id.new_follow_user_layout) {
            GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_FRIEND, Constants.ClickEvent.CLICK_FRIEND);
            DiscoverPeopleActivity.show(this.activity);
        } else if (id == R.id.tv_following_done) {
            GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_NONE, Constants.ClickEvent.CLICK_DONE, Constants.ClickEvent.CLICK_DONE);
            GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_NONE, "click_feed_no", "click_feed_no_" + this.tvFollowingCount.getText());
            getFollowDoneData();
        }
    }

    public void onRefresh() {
        this.page = 1;
        this.lastFeedId = 0;
        this.recommendPage = 1;
        this.followCount = 0;
        this.followIds.clear();
        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(this.updateFollowingData);
        RefreshManager.init().deleteObserver(this);
    }

    public void update(Observable observable, Object obj) {
        checkLogin();
    }

    public void onShare(String str, String str2) {
        share(str, str2);
    }

    private void share(String str, String str2) {
        new NewShareDialog(getActivity()).setShareTitle(str).setShareUrl(str2).setCallbackManager(this.callbackManager).show();
    }

    public void onFollow(final int i, final String str, boolean z) {
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_NONE, Constants.ClickEvent.CLICK_FOLLOW, "click_follow_" + str);
        if (z) {
            showProDialog(getString(R.string.following_ing));
            ApiClient.follow(str, true, bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        FollowingFragment.this.toast(R.string.follow_success);
                        FollowingUserDataModel followingUserDataModel = FollowingFragment.this.adapter.getFollowDatas(i).getFollowingUserDataModel();
                        followingUserDataModel.follow = 1;
                        FollowingFragment.this.adapter.notifyDataSetChanged();
                        FollowingFragment.access$1508(FollowingFragment.this);
                        FollowingFragment.this.followIds.add(followingUserDataModel.Uid);
                        FollowingFragment.this.updateFollowCountText();
                    } else {
                        FollowingFragment.this.toast(baseResult.getErrmsg());
                        FollowingFragment.this.adapter.notifyDataSetChanged();
                    }
                    FollowingFragment.this.dismissProDialog();
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    FollowingFragment.this.dismissProDialog();
                }
            });
            return;
        }
        DialogFactory.showAlert((Context) getActivity(), getString(R.string.unfollow_hint), getString(R.string.yes), getString(R.string.cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                FollowingFragment.this.unFollow(i, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void unFollow(final int i, String str) {
        showProDialog(getString(R.string.add_follow));
        ApiClient.follow(str, false, bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
            public void accept(@NonNull BaseResult baseResult) throws Exception {
                if (baseResult.getErrno() == 0) {
                    FollowingUserDataModel followingUserDataModel = FollowingFragment.this.adapter.getFollowDatas(i).getFollowingUserDataModel();
                    followingUserDataModel.follow = 0;
                    FollowingFragment.this.adapter.notifyDataSetChanged();
                    if (FollowingFragment.this.followCount > 0) {
                        FollowingFragment.access$1510(FollowingFragment.this);
                    }
                    if (FollowingFragment.this.followIds.contains(followingUserDataModel.Uid)) {
                        FollowingFragment.this.followIds.remove(followingUserDataModel.Uid);
                    }
                    FollowingFragment.this.updateFollowCountText();
                } else {
                    FollowingFragment.this.toast(baseResult.getErrmsg());
                }
                FollowingFragment.this.dismissProDialog();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                FollowingFragment.this.dismissProDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateFollowCountText() {
        TextHelper.setQuantityString((Context) getActivity(), this.tvFollowingCount, R.plurals.following_tab_follow_count, this.followCount);
    }

    public void onFolliwingFollow(final FollowingRecommendItemAdapter followingRecommendItemAdapter2, final int i, final String str, boolean z) {
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_FOLLOW, "click_follow_" + str);
        if (z) {
            showProDialog(getString(R.string.following_ing));
            ApiClient.follow(str, true, bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        FollowingFragment.this.toast(R.string.follow_success);
                        FollowingRecommendItemAdapter unused = FollowingFragment.this.followingRecommendItemAdapter = followingRecommendItemAdapter2;
                        FollowingFragment.this.followingRecommendItemAdapter.setFollow(i, 1);
                        FollowingFragment.this.followingRecommendItemAdapter.notifyDataSetChanged();
                    } else {
                        FollowingFragment.this.toast(baseResult.getErrmsg());
                        FollowingFragment.this.followingRecommendItemAdapter.notifyDataSetChanged();
                    }
                    FollowingFragment.this.dismissProDialog();
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    FollowingFragment.this.dismissProDialog();
                }
            });
            return;
        }
        DialogFactory.showAlert((Context) getActivity(), getString(R.string.unfollow_hint), getString(R.string.yes), getString(R.string.cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                FollowingFragment.this.unFollowingFollow(i, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void unFollowingFollow(final int i, String str) {
        showProDialog(getString(R.string.add_follow));
        ApiClient.follow(str, false, bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
            public void accept(@NonNull BaseResult baseResult) throws Exception {
                if (baseResult.getErrno() == 0) {
                    FollowingFragment.this.followingRecommendItemAdapter.setFollow(i, 0);
                    FollowingFragment.this.followingRecommendItemAdapter.notifyDataSetChanged();
                } else {
                    FollowingFragment.this.toast(baseResult.getErrmsg());
                }
                FollowingFragment.this.dismissProDialog();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                FollowingFragment.this.dismissProDialog();
            }
        });
    }

    private void showNotifyLoadedView() {
        if (this.mFollowNotifyContainer.getVisibility() == 0) {
            delayDismissNotifyView();
            return;
        }
        this.mFollowNotifyContainer.setVisibility(0);
        this.mFollowNotifyText.animate().scaleX(1.0f).alpha(1.0f).setListener(new DefaultAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                FollowingFragment.this.delayDismissNotifyView();
            }
        }).setDuration(300).start();
    }

    /* access modifiers changed from: private */
    public void delayDismissNotifyView() {
        this.mFollowNotifyContainer.postDelayed(new Runnable() {
            public void run() {
                FollowingFragment.this.dismissNotifyLoadedView();
            }
        }, 2000);
    }

    /* access modifiers changed from: private */
    public void dismissNotifyLoadedView() {
        this.mFollowNotifyText.animate().scaleX(0.8f).alpha(0.4f).setListener(new DefaultAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                FollowingFragment.this.mFollowNotifyContainer.setVisibility(8);
            }
        }).setDuration(300).start();
    }

    public void setPadding(int i) {
        if (this.mRecycleView != null) {
            this.mRecycleView.setPadding(0, 0, 0, i);
        }
    }

    public void setOnScrollListener(OnShowPostListener onShowPostListener2) {
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
