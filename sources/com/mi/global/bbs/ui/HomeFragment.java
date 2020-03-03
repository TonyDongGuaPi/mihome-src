package com.mi.global.bbs.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bigkoo.convenientbanner.adapter.CBPageAdapter;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bigkoo.convenientbanner.view.CBLoopViewPager;
import com.facebook.CallbackManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.HomeIconGridAdapter;
import com.mi.global.bbs.adapter.MyHeadViewPagerAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.inter.StickyScrollCallBack;
import com.mi.global.bbs.model.HomeBanner;
import com.mi.global.bbs.model.HomeSuggest;
import com.mi.global.bbs.observer.RefreshManager;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.ui.column.ColumnHomeActivity;
import com.mi.global.bbs.ui.home.FroYouFragment;
import com.mi.global.bbs.ui.home.TrendingFragment;
import com.mi.global.bbs.ui.plate.PlateActivity;
import com.mi.global.bbs.ui.qa.QAHomeActivity;
import com.mi.global.bbs.utils.BannerPageTransformer;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DeviceUtils;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.utils.TinyTimer;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.BannerHolderView;
import com.mi.global.bbs.view.MyGridView;
import com.mi.global.bbs.view.PagerSlidingTabStrip;
import com.mi.global.bbs.view.ProfileMesView;
import com.mi.global.bbs.view.dialog.PopupGuide;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.mi.global.bbs.view.headerviewpager.HeaderScrollHelper;
import com.mi.global.bbs.view.headerviewpager.HeaderViewPager;
import com.mi.global.bbs.view.swipe.MySwipeRefreshLayout;
import com.mi.global.bbs.view.swipe.SwipeRefreshLayout;
import com.mi.log.LogUtil;
import com.mi.multi_image_selector.utils.ScreenUtils;
import com.mi.util.Coder;
import com.trello.rxlifecycle2.android.FragmentEvent;
import eu.davidea.flipview.FlipView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class HomeFragment extends BaseFragment implements OnShareListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "HomeFragment";
    public static boolean foryouRefreshed = false;
    /* access modifiers changed from: private */
    public BaseActivity activity;
    @BindView(2131493004)
    CBLoopViewPager banner;
    @BindView(2131493006)
    LinearLayout bannerContainer;
    @BindView(2131493008)
    View bannerToolbarAgent;
    private CallbackManager callbackManager;
    @BindView(2131493265)
    FlipView flipViewTitle;
    private FroYouFragment foryouFragment;
    /* access modifiers changed from: private */
    public List<HeaderViewPagerFragment> fragments;
    @BindView(2131493377)
    FrameLayout frameTitleFlipper;
    @BindView(2131493523)
    ImageView headerBackBtnImg;
    @BindView(2131493525)
    ImageView headerCheckinBtnImg;
    @BindView(2131493527)
    ImageView ivTitleSearch;
    @BindView(2131493552)
    LinearLayout layoutSearch;
    @BindView(2131493599)
    LinearLayout lyMesView;
    @BindView(2131493423)
    LinearLayout lyTitleBack;
    @BindView(2131493437)
    MySwipeRefreshLayout mSwipeRefreshLayout;
    private TinyTimer mTinyTimer;
    @BindView(2131493619)
    LinearLayout mainTitleContainer;
    @BindView(2131493526)
    ProfileMesView mesView;
    @BindView(2131493393)
    MyGridView myGridView;
    @BindView(2131493741)
    PagerSlidingTabStrip newsForumNagTab;
    @BindView(2131493742)
    PagerSlidingTabStrip newsForumNagTabTop;
    @BindView(2131493743)
    ViewPager newsForumPager;
    /* access modifiers changed from: private */
    public int pagerSelection;
    /* access modifiers changed from: private */
    public int preScrollY = 0;
    /* access modifiers changed from: private */
    public boolean scrollHeaderTop = false;
    /* access modifiers changed from: private */
    public boolean scrollUp = false;
    @BindView(2131493918)
    HeaderViewPager scrollableLayout;
    @BindView(2131493424)
    LinearLayout titleContainer;
    @BindView(2131494101)
    View toolbarAgent;
    private TrendingFragment trendingFragment;
    @BindView(2131493422)
    TextView tvTitleSearch;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(new Bundle());
        return homeFragment;
    }

    public static HomeFragment newInstance(String str) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    public void onAttach(Activity activity2) {
        super.onAttach(activity2);
        if (activity2 instanceof BaseActivity) {
            this.activity = (BaseActivity) activity2;
        }
    }

    private void adjustStatusBar() {
        int statusBarHeight = getStatusBarHeight();
        if (statusBarHeight > 0) {
            this.toolbarAgent.getLayoutParams().height = statusBarHeight;
            this.toolbarAgent.requestLayout();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_fragment_home_main_new, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        adjustStatusBar();
        return inflate;
    }

    @RequiresApi(api = 23)
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        adjustStatusBar(this.toolbarAgent);
        this.mTinyTimer = new TinyTimer();
        this.callbackManager = CallbackManager.Factory.create();
        initViewEvent();
        refreshHomeTitle();
        updateView();
        onRefresh();
    }

    public void onRefresh() {
        getHomeData();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mTinyTimer.release();
    }

    public void onShare(String str, String str2) {
        share(str, str2);
    }

    private void initViewEvent() {
        this.mSwipeRefreshLayout.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.headerCheckinBtnImg.setVisibility(8);
        this.headerBackBtnImg.setVisibility(0);
        this.lyTitleBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FragmentActivity activity = HomeFragment.this.getActivity();
                if (activity != null) {
                    activity.finish();
                }
            }
        });
        this.layoutSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeFragment.this.startActivity(new Intent(HomeFragment.this.activity, SearchActivity.class));
                HomeFragment.this.activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.bannerToolbarAgent.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        this.bannerToolbarAgent.setLayoutParams(layoutParams);
        this.fragments = new ArrayList();
        this.trendingFragment = new TrendingFragment();
        this.foryouFragment = new FroYouFragment();
        this.fragments.add(this.trendingFragment);
        this.fragments.add(this.foryouFragment);
        this.scrollableLayout.setCurrentScrollableContainer(this.fragments.get(0));
        DisplayMetrics displayMetrics = this.activity.getResources().getDisplayMetrics();
        this.newsForumPager.setAdapter(new MyHeadViewPagerAdapter(getChildFragmentManager(), this.activity.getResources().getStringArray(R.array.home_trending_tab), this.fragments));
        this.newsForumNagTabTop.setViewPager(this.newsForumPager);
        this.newsForumNagTabTop.setUnderlineHeight(0);
        this.newsForumNagTabTop.setIndicatorColor(this.activity.getResources().getColor(R.color.main_yellow));
        this.newsForumNagTabTop.setIndicatorHeight((int) TypedValue.applyDimension(1, 2.0f, displayMetrics));
        this.newsForumNagTabTop.setTextColor(Color.parseColor("#666666"));
        this.newsForumNagTabTop.setSelectedTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR_SELECT));
        this.newsForumNagTabTop.setTextSize((int) TypedValue.applyDimension(2, 15.0f, displayMetrics));
        this.newsForumNagTabTop.setDividerColor(0);
        this.newsForumNagTabTop.setAllCaps(false);
        this.newsForumNagTabTop.setShouldExpand(false);
        this.newsForumNagTab.setViewPager(this.newsForumPager);
        this.newsForumNagTab.setUnderlineHeight(0);
        this.newsForumNagTab.setIndicatorColor(this.activity.getResources().getColor(R.color.main_yellow));
        this.newsForumNagTab.setIndicatorHeight((int) TypedValue.applyDimension(1, 2.0f, displayMetrics));
        this.newsForumNagTab.setTextColor(Color.parseColor("#666666"));
        this.newsForumNagTab.setSelectedTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR_SELECT));
        this.newsForumNagTab.setTextSize((int) TypedValue.applyDimension(2, 15.0f, displayMetrics));
        this.newsForumNagTab.setDividerColor(0);
        this.newsForumNagTab.setAllCaps(false);
        this.newsForumNagTab.setShouldExpand(false);
        this.newsForumNagTab.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                HomeFragment.this.newsForumNagTab.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                boolean booleanPref = Utils.Preference.getBooleanPref(BBSApplication.getInstance(), Constants.App.FOR_YOU_IS_FIRST, true);
                int versionCode = DeviceUtils.getVersionCode(HomeFragment.this.activity);
                if (booleanPref && versionCode < 20600) {
                    Utils.Preference.setBooleanPref(BBSApplication.getInstance(), Constants.App.FOR_YOU_IS_FIRST, false);
                    PopupGuide.showForYouGuide(HomeFragment.this.activity, HomeFragment.this.newsForumNagTab.getTabViewAt(1));
                }
            }
        });
        this.newsForumPager.setOffscreenPageLimit(2);
        this.newsForumNagTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
                if (HomeFragment.this.scrollHeaderTop && !HomeFragment.this.flipViewTitle.isFlipped()) {
                    HomeFragment.this.flipViewTitle.showNext();
                }
            }

            public void onPageSelected(int i) {
                HomeFragment.this.scrollableLayout.setCurrentScrollableContainer((HeaderScrollHelper.ScrollableContainer) HomeFragment.this.fragments.get(i));
                int unused = HomeFragment.this.pagerSelection = i;
                if (i == 0) {
                    GoogleTrackerUtil.sendRecordEvent("home", Constants.ClickEvent.CLICK_HOME_POPULAR, Constants.ClickEvent.CLICK_HOME_POPULAR);
                    return;
                }
                if (!HomeFragment.foryouRefreshed) {
                    RefreshManager.init().startRefresh(LoginManager.getInstance().hasLogin());
                }
                GoogleTrackerUtil.sendRecordEvent("home", Constants.ClickEvent.CLICK_HOME_FOR_YOU, Constants.ClickEvent.CLICK_HOME_FOR_YOU);
            }
        });
        this.newsForumPager.setCurrentItem(0);
        this.scrollableLayout.setTopOffset(0);
        this.scrollableLayout.setOnScrollListener(new HeaderViewPager.OnScrollListener() {
            public void onScroll(int i, int i2) {
                boolean unused = HomeFragment.this.scrollHeaderTop = i == i2;
                if (HomeFragment.this.scrollableLayout.isHeadTop()) {
                    HomeFragment.this.mSwipeRefreshLayout.setEnabled(true);
                } else {
                    HomeFragment.this.mSwipeRefreshLayout.setEnabled(false);
                }
                if (i > HomeFragment.this.preScrollY && !HomeFragment.this.scrollUp) {
                    boolean unused2 = HomeFragment.this.scrollUp = true;
                }
                if (i < HomeFragment.this.preScrollY && HomeFragment.this.scrollUp) {
                    boolean unused3 = HomeFragment.this.scrollUp = false;
                }
                int unused4 = HomeFragment.this.preScrollY = i;
                if (i == i2) {
                    if (HomeFragment.this.scrollUp && !HomeFragment.this.flipViewTitle.isFlipped()) {
                        HomeFragment.this.flipViewTitle.showNext();
                    }
                } else if (!HomeFragment.this.scrollUp && HomeFragment.this.flipViewTitle.isFlipped()) {
                    HomeFragment.this.flipViewTitle.showPrevious();
                }
                if (i < i2) {
                    HomeFragment.this.resetInnerFragmentPosition();
                }
            }
        });
        this.trendingFragment.setScrollCallBack(new StickyScrollCallBack() {
            public void onScrollChanged(int i, boolean z, boolean z2) {
                if (HomeFragment.this.scrollHeaderTop && HomeFragment.this.activity != null) {
                    HomeFragment.this.showOrHideFlipView(z, z2);
                }
            }
        });
        this.foryouFragment.setScrollCallBack(new StickyScrollCallBack() {
            public void onScrollChanged(int i, boolean z, boolean z2) {
                if (HomeFragment.this.scrollHeaderTop && HomeFragment.this.activity != null) {
                    HomeFragment.this.showOrHideFlipView(z, z2);
                }
            }
        });
    }

    private void refreshHomeTitle() {
        this.mesView.setMesCount(Utils.Preference.getIntPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_HOME_NEW_MESSAGE, 0));
        this.lyMesView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MineActivity.jump(HomeFragment.this.getActivity());
            }
        });
    }

    private void getHomeData() {
        ApiClient.getHomePostData(bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                HomeFragment.this.handleHomeData(jsonObject);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                HomeFragment.this.mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleHomeData(JsonObject jsonObject) {
        try {
            JSONObject optJSONObject = new JSONObject(jsonObject.toString()).optJSONObject("data");
            Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_HOME_DATA, optJSONObject.toString());
            Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_HOME_THREADS_MAX_ID, optJSONObject.optJSONObject("threads").optString("max_id"));
            Utils.Preference.setIntPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_HOME_NEW_MESSAGE, optJSONObject.optInt("xmring"));
            updateView();
        } catch (Exception e) {
            String str = TAG;
            LogUtil.a(str, "getHomeData  erro " + e.toString());
        }
    }

    private void updateView() {
        this.mSwipeRefreshLayout.setRefreshing(false);
        try {
            String stringPref = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_HOME_DATA, "");
            if (!TextUtils.isEmpty(stringPref)) {
                JSONObject jSONObject = new JSONObject(stringPref);
                Gson gson = new Gson();
                bindBannerHolder((ArrayList) gson.fromJson(jSONObject.optString("banner"), new TypeToken<List<HomeBanner>>() {
                }.getType()));
                bindRecommendIconHolder((ArrayList) gson.fromJson(jSONObject.optString("recommend"), new TypeToken<List<HomeSuggest>>() {
                }.getType()));
                refreshHomeTitle();
                this.trendingFragment.setFirstPageData();
                if (this.pagerSelection == 1) {
                    RefreshManager.init().startRefresh(LoginManager.getInstance().hasLogin());
                }
            }
        } catch (Exception e) {
            String str = TAG;
            LogUtil.b(str, "updateView  error  " + e.toString());
        }
    }

    private void bindBannerHolder(final List<HomeBanner> list) {
        if (list.size() > 0) {
            this.bannerContainer.setVisibility(0);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.banner.getLayoutParams();
            int a2 = ScreenUtils.a(this.activity).x - Coder.a(32.0f);
            layoutParams.width = a2;
            layoutParams.height = a2 / 2;
            this.banner.setLayoutParams(layoutParams);
            this.banner.requestLayout();
            this.banner.setAdapter(new CBPageAdapter(new CBViewHolderCreator<BannerHolderView>() {
                public BannerHolderView createHolder() {
                    return new BannerHolderView();
                }
            }, list), true);
            this.banner.setOffscreenPageLimit(list.size());
            this.banner.setPageMargin(-Coder.a(5.0f));
            this.banner.setPageTransformer(true, new BannerPageTransformer());
            this.banner.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(int i) {
                    GoogleTrackerUtil.sendRecordEvent("home", Constants.WebView.CLICK_TOP_BANNER, ((HomeBanner) list.get(i)).getLink());
                    HomeFragment.this.activity.refreshWebUrl(((HomeBanner) list.get(i)).getLink());
                }
            });
            this.mTinyTimer.setPager(this.banner);
        }
    }

    private void bindRecommendIconHolder(List<HomeSuggest> list) {
        HomeIconGridAdapter homeIconGridAdapter = new HomeIconGridAdapter(this.activity, 2);
        homeIconGridAdapter.setOnGridClick(new HomeIconGridAdapter.onGridClick() {
            public void onClickLink(String str, String str2) {
                GoogleTrackerUtil.sendRecordEvent("home", Constants.WebView.CLICK_ICON_RECOMMEND, str2);
                try {
                    if (!TextUtils.isEmpty(str)) {
                        if (str.contains(Constants.WebViewURL.PAGE_COLUMN)) {
                            ColumnHomeActivity.jump(HomeFragment.this.activity);
                        } else if (str.contains(Constants.WebViewURL.PAGE_QA)) {
                            QAHomeActivity.jump(HomeFragment.this.activity);
                        } else if (str.contains(Constants.WebViewURL.PAGE_ACTIVITY)) {
                            ActivitiesActivity.jump(HomeFragment.this.activity);
                        } else if (str.contains(Constants.WebViewURL.PAGE_MIUI)) {
                            HomeFragment.this.activity.startActivity(new Intent(HomeFragment.this.activity, MIUIActivity.class));
                        } else if (str.contains(Constants.WebViewURL.PAGE_SUBFORUM)) {
                            SubForumActivity.jump(HomeFragment.this.activity);
                        } else {
                            String substring = str.substring(str.indexOf("-") + 1, str.lastIndexOf("-"));
                            if (!TextUtils.isEmpty(substring)) {
                                PlateActivity.jumpWithId(HomeFragment.this.activity, substring, str2);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.myGridView.setAdapter(homeIconGridAdapter);
        homeIconGridAdapter.setData(list);
    }

    /* access modifiers changed from: private */
    public void showOrHideFlipView(boolean z, boolean z2) {
        if (z) {
            if (!this.flipViewTitle.isFlipped()) {
                this.flipViewTitle.showNext();
            }
        } else if (this.flipViewTitle.isFlipped()) {
            this.flipViewTitle.showPrevious();
        }
    }

    /* access modifiers changed from: private */
    public void resetInnerFragmentPosition() {
        this.trendingFragment.resetFirstPosition();
        this.foryouFragment.resetFirstPosition();
    }

    private void share(String str, String str2) {
        new ShareDialog(getActivity()).setShareTitle(str).setShareUrl(str2).setCallbackManager(this.callbackManager).show();
    }
}
