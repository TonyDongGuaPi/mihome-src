package com.mi.global.bbs.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.CallbackManager;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.HomeThreadAdapter;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.inter.StickyScrollCallBack;
import com.mi.global.bbs.model.ForYouRecoomend;
import com.mi.global.bbs.model.HomeData;
import com.mi.global.bbs.model.HomeForumBean;
import com.mi.global.bbs.observer.RefreshManager;
import com.mi.global.bbs.ui.HeaderViewPagerFragment;
import com.mi.global.bbs.ui.HomeFragment;
import com.mi.global.bbs.ui.forum.NewsForumActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.mi.log.LogUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONObject;

public class FroYouFragment extends HeaderViewPagerFragment implements HomeThreadAdapter.OnClickForYouCusListener, OnShareListener, Observer {
    public static final int PER_PAGE = 10;
    private static final String TAG = "FroYouFragment";
    private final int TO_FOR_YOU_CUS_CODE = 1;
    /* access modifiers changed from: private */
    public Activity activity;
    protected CallbackManager callbackManager;
    /* access modifiers changed from: private */
    public boolean cancelScroll = false;
    /* access modifiers changed from: private */
    public boolean hasMoreData = false;
    private List<HomeData> homeDatas = new ArrayList();
    private WrapContentLinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131494032)
    ObservableRecyclerView mHomeRecycle;
    /* access modifiers changed from: private */
    public HomeThreadAdapter mNewThreadAdapter;
    /* access modifiers changed from: private */
    public int pageIndex = 1;
    private String pageName;
    /* access modifiers changed from: private */
    public int preScrollY = 0;
    StickyScrollCallBack scrollCallBack;
    /* access modifiers changed from: private */
    public boolean scrollUp = false;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        setRetainInstance(true);
        View inflate = layoutInflater.inflate(R.layout.bbs_common_sticky_recycle_view, viewGroup, false);
        this.pageName = Constants.PageFragment.PAGE_FORU;
        ButterKnife.bind((Object) this, inflate);
        this.callbackManager = CallbackManager.Factory.create();
        RefreshManager.init().addObserver(this);
        initView();
        return inflate;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        setFirstPageData();
    }

    private void initView() {
        this.pageIndex = 1;
        this.hasMoreData = false;
        this.layoutManager = new WrapContentLinearLayoutManager(getActivity());
        this.mHomeRecycle.setLayoutManager(this.layoutManager);
        this.loadMoreManager = new LoadMoreManager();
        this.mNewThreadAdapter = new HomeThreadAdapter(this, this.pageName);
        this.mNewThreadAdapter.setOnShareListener(this);
        this.mNewThreadAdapter.setOnClickForYouCusListener(this);
        this.mHomeRecycle.setAdapter(this.mNewThreadAdapter);
        this.mHomeRecycle.setHasFixedSize(true);
        this.mHomeRecycle.setItemViewCacheSize(20);
        this.mHomeRecycle.setDrawingCacheEnabled(true);
        this.mHomeRecycle.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            public void onScrollChanged(int i, boolean z, boolean z2) {
                if (!FroYouFragment.this.cancelScroll) {
                    int abs = Math.abs(FroYouFragment.this.preScrollY - i);
                    boolean unused = FroYouFragment.this.scrollUp = i > FroYouFragment.this.preScrollY;
                    if (FroYouFragment.this.scrollCallBack != null && abs >= 10) {
                        FroYouFragment.this.scrollCallBack.onScrollChanged(abs, FroYouFragment.this.scrollUp, z2);
                    }
                    int unused2 = FroYouFragment.this.preScrollY = i;
                }
            }

            public void onDownMotionEvent() {
                boolean unused = FroYouFragment.this.cancelScroll = false;
            }

            public void onUpOrCancelMotionEvent(ScrollState scrollState) {
                boolean unused = FroYouFragment.this.cancelScroll = true;
            }
        });
        this.mHomeRecycle.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (FroYouFragment.this.hasMoreData && !FroYouFragment.this.loadMoreManager.isDataLoading()) {
                    FroYouFragment.this.mNewThreadAdapter.setLoading(true);
                    FroYouFragment.this.mNewThreadAdapter.removeLoadMore();
                    FroYouFragment.this.mNewThreadAdapter.addLoadMoreData(1);
                    FroYouFragment.this.getForYouData();
                }
            }
        });
        this.mNewThreadAdapter.setOnRecycleClickListener(new HomeThreadAdapter.onRecycleClickListener() {
            public void onClickLink(String str) {
            }

            public void onClickYoutube(String str) {
            }

            public void onClickMore(int i) {
                NewsForumActivity.jump(FroYouFragment.this.activity, i);
            }
        });
    }

    public void setFirstPageData() {
        this.homeDatas.clear();
        try {
            Gson gson = new Gson();
            ArrayList arrayList = (ArrayList) gson.fromJson(Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORYOU_LIST, ""), new TypeToken<List<HomeForumBean>>() {
            }.getType());
            boolean booleanPref = Utils.Preference.getBooleanPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORYOU_FOLLOW, false);
            ArrayList arrayList2 = (ArrayList) gson.fromJson(Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORYOU_RECOMMED, ""), new TypeToken<List<ForYouRecoomend>>() {
            }.getType());
            if (!booleanPref) {
                HomeData homeData = new HomeData();
                homeData.setType(21);
                this.homeDatas.add(homeData);
            } else if (arrayList == null || arrayList.size() <= 0) {
                HomeData homeData2 = new HomeData();
                homeData2.setType(21);
                this.homeDatas.add(homeData2);
            } else {
                if (LoginManager.getInstance().hasLogin()) {
                    HomeData homeData3 = new HomeData();
                    homeData3.setType(23);
                    this.homeDatas.add(homeData3);
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i == 5 && arrayList2 != null && arrayList2.size() > 0) {
                        HomeData homeData4 = new HomeData();
                        homeData4.setForYouRecoomends(arrayList2);
                        this.homeDatas.add(homeData4);
                    }
                    HomeData homeData5 = new HomeData();
                    homeData5.setHomeForumBean((HomeForumBean) arrayList.get(i));
                    this.homeDatas.add(homeData5);
                }
                if (arrayList.size() > 0) {
                    this.hasMoreData = true;
                    this.pageIndex++;
                } else {
                    this.hasMoreData = false;
                    toast(R.string.tip_no_more);
                }
            }
            this.mNewThreadAdapter.refreshDatas(this.homeDatas);
            this.mNewThreadAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            LogUtil.b(TAG, "updateView  Exception  " + e.toString());
        }
    }

    /* access modifiers changed from: private */
    public void getForYouData() {
        this.loadMoreManager.loadStarted();
        int i = 1;
        this.mNewThreadAdapter.setLoading(true);
        HomeFragment.foryouRefreshed = true;
        if (this.pageIndex != 1) {
            i = 0;
        }
        ApiClient.getForyouData(this.pageIndex, 10, i, bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                FroYouFragment.this.mNewThreadAdapter.removeLoadMore();
                FroYouFragment.this.mNewThreadAdapter.setLoading(false);
                FroYouFragment.this.loadMoreManager.loadFinished();
                if (FroYouFragment.this.pageIndex == 1) {
                    FroYouFragment.this.handleFirstPageData(jsonObject);
                } else {
                    FroYouFragment.this.handleHomeMoreData(jsonObject);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                FroYouFragment.this.mNewThreadAdapter.changeLoadMoreFailed();
                FroYouFragment.this.mNewThreadAdapter.setLoading(false);
                FroYouFragment.this.loadMoreManager.loadFinished();
            }
        });
    }

    private void showNoLoginView() {
        this.hasMoreData = false;
        this.homeDatas.clear();
        HomeData homeData = new HomeData();
        homeData.setType(21);
        this.homeDatas.add(homeData);
        this.mNewThreadAdapter.refreshDatas(this.homeDatas);
    }

    public void resetFirstPosition() {
        if (this.layoutManager.findLastVisibleItemPosition() > 0) {
            this.layoutManager.scrollToPosition(0);
        }
    }

    /* access modifiers changed from: private */
    public void handleHomeMoreData(JsonObject jsonObject) {
        try {
            Gson gson = new Gson();
            JSONObject optJSONObject = new JSONObject(jsonObject.toString()).optJSONObject("data");
            if (optJSONObject != null) {
                this.homeDatas.clear();
                String optString = optJSONObject.optString("list");
                new HomeData();
                if (!TextUtils.isEmpty(optString)) {
                    ArrayList arrayList = (ArrayList) gson.fromJson(optString, new TypeToken<List<HomeForumBean>>() {
                    }.getType());
                    for (int i = 0; i < arrayList.size(); i++) {
                        HomeData homeData = new HomeData();
                        homeData.setHomeForumBean((HomeForumBean) arrayList.get(i));
                        this.homeDatas.add(homeData);
                    }
                    this.mNewThreadAdapter.addMoreUrlList(this.homeDatas);
                    this.mNewThreadAdapter.notifyDataSetChanged();
                    if (arrayList == null || arrayList.size() <= 0) {
                        this.hasMoreData = false;
                        toast(R.string.tip_no_more);
                    } else {
                        this.pageIndex++;
                    }
                }
            }
            this.mNewThreadAdapter.setLoading(false);
        } catch (Exception e) {
            LogUtil.b(TAG, "setOnloadMoreListener error" + e.toString());
        }
    }

    /* access modifiers changed from: private */
    public void handleFirstPageData(JsonObject jsonObject) {
        try {
            JSONObject optJSONObject = new JSONObject(jsonObject.toString()).optJSONObject("data");
            Utils.Preference.setBooleanPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORYOU_FOLLOW, optJSONObject.optBoolean("havefollow"));
            Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORYOU_LIST, optJSONObject.optString("list"));
            Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORYOU_RECOMMED, optJSONObject.optString("recommend"));
            setFirstPageData();
        } catch (Exception unused) {
        }
    }

    public void setScrollCallBack(StickyScrollCallBack stickyScrollCallBack) {
        this.scrollCallBack = stickyScrollCallBack;
    }

    private void share(String str, String str2) {
        new ShareDialog(getActivity()).setShareTitle(str).setShareUrl(str2).setCallbackManager(this.callbackManager).show();
    }

    public void onAttach(Activity activity2) {
        super.onAttach(activity2);
        this.activity = activity2;
    }

    public void onShare(String str, String str2) {
        share(str, str2);
    }

    public void onDestroy() {
        super.onDestroy();
        RefreshManager.init().deleteObserver(this);
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FORU, Constants.ClickEvent.CLICK_HOME_FOR_YOU_NO, this.pageIndex + "");
    }

    public void update(Observable observable, Object obj) {
        this.pageIndex = 1;
        if (obj != null && (obj instanceof Boolean)) {
            if (((Boolean) obj).booleanValue()) {
                getForYouData();
            } else {
                showNoLoginView();
            }
        }
    }

    public void onClickForYouCusListener() {
        startActivityForResult(new Intent(getActivity(), ForYouCustomizeActivity.class), 1);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            this.pageIndex = 1;
            getForYouData();
        }
    }

    public View getScrollableView() {
        return this.mHomeRecycle;
    }
}
