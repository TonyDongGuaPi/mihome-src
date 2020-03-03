package com.mi.global.bbs.ui.home;

import android.app.Activity;
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
import com.mi.global.bbs.adapter.HomeThreadAdapter;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.inter.StickyScrollCallBack;
import com.mi.global.bbs.model.HomeBanner;
import com.mi.global.bbs.model.HomeColumnList;
import com.mi.global.bbs.model.HomeData;
import com.mi.global.bbs.model.HomeForumBean;
import com.mi.global.bbs.model.HomeForumData;
import com.mi.global.bbs.model.HomeNotify;
import com.mi.global.bbs.model.HomePostBean;
import com.mi.global.bbs.ui.HeaderViewPagerFragment;
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
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class TrendingFragment extends HeaderViewPagerFragment implements OnShareListener {
    public static final int PER_PAGE = 10;
    private static final String TAG = "TrendingFragment";
    /* access modifiers changed from: private */
    public Activity activity;
    protected CallbackManager callbackManager;
    /* access modifiers changed from: private */
    public boolean cancelScroll = false;
    /* access modifiers changed from: private */
    public boolean hasMoreData = true;
    private List<HomeData> homeDatas = new ArrayList();
    private WrapContentLinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public LoadMoreManager loadMoreManager;
    @BindView(2131494032)
    ObservableRecyclerView mHomeRecycle;
    /* access modifiers changed from: private */
    public HomeThreadAdapter mNewThreadAdapter;
    private int pageIndex = 0;
    private String pageName;
    /* access modifiers changed from: private */
    public int preScrollY = 0;
    StickyScrollCallBack scrollCallBack;
    /* access modifiers changed from: private */
    public boolean scrollUp = false;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_common_sticky_recycle_view, viewGroup, false);
        this.pageName = "home";
        ButterKnife.bind((Object) this, inflate);
        this.callbackManager = CallbackManager.Factory.create();
        initView();
        setFirstPageData();
        return inflate;
    }

    private void initView() {
        this.pageIndex = 0;
        this.hasMoreData = true;
        this.loadMoreManager = new LoadMoreManager();
        this.layoutManager = new WrapContentLinearLayoutManager(getActivity());
        this.mHomeRecycle.setLayoutManager(this.layoutManager);
        this.mNewThreadAdapter = new HomeThreadAdapter(this, this.pageName);
        this.mNewThreadAdapter.setOnShareListener(this);
        this.mHomeRecycle.setAdapter(this.mNewThreadAdapter);
        this.mHomeRecycle.setHasFixedSize(true);
        this.mHomeRecycle.setItemViewCacheSize(20);
        this.mHomeRecycle.setDrawingCacheEnabled(true);
        this.mHomeRecycle.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            public void onScrollChanged(int i, boolean z, boolean z2) {
                if (!TrendingFragment.this.cancelScroll) {
                    int abs = Math.abs(TrendingFragment.this.preScrollY - i);
                    boolean unused = TrendingFragment.this.scrollUp = i > TrendingFragment.this.preScrollY;
                    if (TrendingFragment.this.scrollCallBack != null && abs >= 10) {
                        TrendingFragment.this.scrollCallBack.onScrollChanged(abs, TrendingFragment.this.scrollUp, z2);
                    }
                    int unused2 = TrendingFragment.this.preScrollY = i;
                }
            }

            public void onDownMotionEvent() {
                boolean unused = TrendingFragment.this.cancelScroll = false;
            }

            public void onUpOrCancelMotionEvent(ScrollState scrollState) {
                boolean unused = TrendingFragment.this.cancelScroll = true;
            }
        });
        this.mHomeRecycle.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (TrendingFragment.this.hasMoreData && !TrendingFragment.this.loadMoreManager.isDataLoading()) {
                    TrendingFragment.this.mNewThreadAdapter.setLoading(true);
                    TrendingFragment.this.mNewThreadAdapter.removeLoadMore();
                    TrendingFragment.this.mNewThreadAdapter.addLoadMoreData(1);
                    TrendingFragment.this.getHomeMoreData();
                }
            }
        });
        this.mNewThreadAdapter.setOnRecycleClickListener(new HomeThreadAdapter.onRecycleClickListener() {
            public void onClickLink(String str) {
                if (TrendingFragment.this.activity != null) {
                    TrendingFragment.this.refreshWebUrl(str);
                }
            }

            public void onClickYoutube(String str) {
                TrendingFragment.this.getContext();
            }

            public void onClickMore(int i) {
                NewsForumActivity.jump(TrendingFragment.this.activity, i);
            }
        });
    }

    public void setFirstPageData() {
        List list;
        this.homeDatas.clear();
        this.pageIndex = 0;
        try {
            String stringPref = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_HOME_DATA, "");
            if (!TextUtils.isEmpty(stringPref)) {
                JSONObject jSONObject = new JSONObject(stringPref);
                Gson gson = new Gson();
                ArrayList arrayList = (ArrayList) gson.fromJson(jSONObject.optString("announce"), new TypeToken<List<HomeNotify>>() {
                }.getType());
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        HomeData homeData = new HomeData();
                        homeData.setType(2);
                        homeData.setHomeNotify((HomeNotify) it.next());
                        this.homeDatas.add(homeData);
                    }
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("digests");
                if (!(optJSONObject == null || (list = (List) gson.fromJson(optJSONObject.optString("list"), new TypeToken<List<HomePostBean>>() {
                }.getType())) == null || list.size() <= 0)) {
                    HomeData homeData2 = new HomeData();
                    homeData2.setType(4);
                    homeData2.setHomeHots(list);
                    this.homeDatas.add(homeData2);
                }
                Iterator<HomeForumBean> it2 = new HomeForumData(jSONObject.optString("forums")).getHomeForums().iterator();
                while (it2.hasNext()) {
                    HomeData homeData3 = new HomeData();
                    homeData3.setHomeForumBean(it2.next());
                    this.homeDatas.add(homeData3);
                }
                if (this.mNewThreadAdapter != null) {
                    this.mNewThreadAdapter.refreshDatas(this.homeDatas);
                    this.mNewThreadAdapter.notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
            String str = TAG;
            LogUtil.b(str, "updateView  Exception  " + e.toString());
        }
    }

    /* access modifiers changed from: private */
    public void getHomeMoreData() {
        this.mNewThreadAdapter.setLoading(true);
        this.loadMoreManager.loadStarted();
        ApiClient.postHomeMoreData(String.valueOf(this.pageIndex), String.valueOf(10), Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_HOME_THREADS_MAX_ID, ""), bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                TrendingFragment.this.mNewThreadAdapter.removeLoadMore();
                TrendingFragment.this.loadMoreManager.loadFinished();
                TrendingFragment.this.handleHomeMoreData(jsonObject);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                TrendingFragment.this.mNewThreadAdapter.changeLoadMoreFailed();
                TrendingFragment.this.mNewThreadAdapter.setLoading(false);
                TrendingFragment.this.loadMoreManager.loadFinished();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleHomeMoreData(JsonObject jsonObject) {
        HomeColumnList homeColumnList;
        try {
            Gson gson = new Gson();
            JSONArray optJSONArray = new JSONObject(jsonObject.toString()).optJSONArray("data");
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject = optJSONArray.getJSONObject(i);
                int optInt = jSONObject.optInt("type");
                if (optInt == 1) {
                    HomeData homeData = new HomeData();
                    homeData.setType(10);
                    homeData.setHomeAdBanner((HomeBanner) gson.fromJson(jSONObject.toString(), new TypeToken<HomeBanner>() {
                    }.getType()));
                    arrayList.add(homeData);
                } else if (optInt == 0) {
                    HomeData homeData2 = new HomeData();
                    homeData2.setHomeForumBean((HomeForumBean) gson.fromJson(jSONObject.toString(), new TypeToken<HomeForumBean>() {
                    }.getType()));
                    arrayList.add(homeData2);
                } else if (optInt == 2 && (homeColumnList = (HomeColumnList) gson.fromJson(jSONObject.toString(), new TypeToken<HomeColumnList>() {
                }.getType())) != null && homeColumnList.thread != null && homeColumnList.thread.size() > 0) {
                    HomeData homeData3 = new HomeData();
                    homeData3.setType(24);
                    homeData3.setHomeColumnList(homeColumnList);
                    arrayList.add(homeData3);
                }
            }
            this.mNewThreadAdapter.addMoreUrlList(arrayList);
            this.mNewThreadAdapter.notifyDataSetChanged();
            this.mNewThreadAdapter.setLoading(false);
            if (arrayList.size() > 0) {
                this.pageIndex++;
                this.hasMoreData = true;
                return;
            }
            this.hasMoreData = false;
            toast(R.string.tip_no_more);
        } catch (Exception e) {
            LogUtil.b(TAG, "setOnloadMoreListener error" + e.toString());
        }
    }

    public void resetFirstPosition() {
        if (this.layoutManager.findLastVisibleItemPosition() > 0) {
            this.layoutManager.scrollToPosition(0);
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

    public void onDestroy() {
        super.onDestroy();
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_COLUMN_INDEX, Constants.ClickEvent.CLICK_HOME_POPULAR_NO, this.pageIndex + "");
    }

    public void onShare(String str, String str2) {
        share(str, str2);
    }

    public View getScrollableView() {
        return this.mHomeRecycle;
    }
}
