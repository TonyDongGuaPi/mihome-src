package com.mi.global.bbs.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.ParamsProvider;
import com.mi.global.bbs.inter.StickyScrollCallBack;
import com.mi.global.bbs.model.HomeBanner;
import com.mi.global.bbs.model.HomeSuggest;
import com.mi.global.bbs.observer.RefreshManager;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.ui.checkin.SignActivity;
import com.mi.global.bbs.ui.column.ColumnHomeActivity;
import com.mi.global.bbs.ui.home.FroYouFragment;
import com.mi.global.bbs.ui.home.TrendingFragment;
import com.mi.global.bbs.ui.plate.PlateActivity;
import com.mi.global.bbs.ui.post.PostActivity;
import com.mi.global.bbs.ui.qa.QAHomeActivity;
import com.mi.global.bbs.ui.qa.QuestionActivity;
import com.mi.global.bbs.utils.BannerPageTransformer;
import com.mi.global.bbs.utils.ChannelUtil;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.utils.SplashUtil;
import com.mi.global.bbs.utils.TinyTimer;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.BannerHolderView;
import com.mi.global.bbs.view.MyGridView;
import com.mi.global.bbs.view.PagerSlidingTabStrip;
import com.mi.global.bbs.view.ProfileMesView;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.mi.global.bbs.view.headerviewpager.HeaderScrollHelper;
import com.mi.global.bbs.view.headerviewpager.HeaderViewPager;
import com.mi.global.bbs.view.main.ActionOptionItem;
import com.mi.global.bbs.view.main.MainActionOptionsFrame;
import com.mi.global.bbs.view.swipe.MySwipeRefreshLayout;
import com.mi.global.bbs.view.swipe.SwipeRefreshLayout;
import com.mi.log.LogUtil;
import com.mi.multi_image_selector.utils.ScreenUtils;
import com.mi.util.Coder;
import com.mi.util.Device;
import com.trello.rxlifecycle2.android.FragmentEvent;
import eu.davidea.flipview.FlipView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class CommunityMainFragment extends BaseFragment implements OnShareListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "CommunityMainFragment";
    public static boolean foryouRefreshed = false;
    /* access modifiers changed from: private */
    public Activity activity;
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
    @BindView(2131493527)
    ImageView ivTitleSearch;
    @BindView(2131493552)
    LinearLayout layoutSearch;
    @BindView(2131493599)
    LinearLayout lyMesView;
    @BindView(2131493423)
    LinearLayout lyTitleBack;
    private MainActionOptionsFrame mMainActionFrame;
    @BindView(2131493902)
    ViewGroup mRootView;
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

    /* access modifiers changed from: private */
    public void showOrHideMainTab(boolean z) {
    }

    public void onAttach(Activity activity2) {
        super.onAttach(activity2);
        this.activity = activity2;
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
        loadData();
    }

    private void loadData() {
        getSyncData();
        initViewEvent();
        refreshHomeTitle();
        updateView();
        onRefresh();
        setOptionsFrameAction();
    }

    public void reload() {
        loadData();
    }

    private void getSyncData() {
        ApiClient.getSyncInfo(ParamsProvider.getSyncParams("" + Device.r, ChannelUtil.getChannel(getContext()), ChannelUtil.getApkMD5(getContext())), bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                SplashUtil.loadInfo(jsonObject.toString());
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
            }
        }, new Action() {
            public void run() throws Exception {
            }
        });
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
        this.lyTitleBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommunityMainFragment.this.goToTask();
            }
        });
        this.layoutSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommunityMainFragment.this.startActivity(new Intent(CommunityMainFragment.this.activity, SearchActivity.class));
                CommunityMainFragment.this.activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
        this.newsForumPager.setAdapter(new MyHeadViewPagerAdapter(getChildFragmentManager(), BBSApplication.getInstance().getResources().getStringArray(R.array.home_trending_tab), this.fragments));
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
        this.tvTitleSearch.setText(BBSApplication.getInstance().getResources().getString(R.string.search_in_community));
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
        this.newsForumPager.setOffscreenPageLimit(2);
        this.newsForumNagTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
                if (CommunityMainFragment.this.scrollHeaderTop && !CommunityMainFragment.this.flipViewTitle.isFlipped()) {
                    CommunityMainFragment.this.flipViewTitle.showNext();
                }
            }

            public void onPageSelected(int i) {
                CommunityMainFragment.this.scrollableLayout.setCurrentScrollableContainer((HeaderScrollHelper.ScrollableContainer) CommunityMainFragment.this.fragments.get(i));
                int unused = CommunityMainFragment.this.pagerSelection = i;
                if (i == 0) {
                    GoogleTrackerUtil.sendRecordEvent("home", Constants.ClickEvent.CLICK_HOME_POPULAR, Constants.ClickEvent.CLICK_HOME_POPULAR);
                    return;
                }
                if (!CommunityMainFragment.foryouRefreshed) {
                    RefreshManager.init().startRefresh(LoginManager.getInstance().hasLogin());
                }
                GoogleTrackerUtil.sendRecordEvent("home", Constants.ClickEvent.CLICK_HOME_FOR_YOU, Constants.ClickEvent.CLICK_HOME_FOR_YOU);
            }
        });
        this.newsForumPager.setCurrentItem(0);
        this.scrollableLayout.setTopOffset(0);
        this.scrollableLayout.setOnScrollListener(new HeaderViewPager.OnScrollListener() {
            public void onScroll(int i, int i2) {
                boolean unused = CommunityMainFragment.this.scrollHeaderTop = i == i2;
                if (CommunityMainFragment.this.scrollableLayout.isHeadTop()) {
                    CommunityMainFragment.this.mSwipeRefreshLayout.setEnabled(true);
                } else {
                    CommunityMainFragment.this.mSwipeRefreshLayout.setEnabled(false);
                }
                if (i > CommunityMainFragment.this.preScrollY && !CommunityMainFragment.this.scrollUp) {
                    boolean unused2 = CommunityMainFragment.this.scrollUp = true;
                }
                if (i < CommunityMainFragment.this.preScrollY && CommunityMainFragment.this.scrollUp) {
                    boolean unused3 = CommunityMainFragment.this.scrollUp = false;
                }
                int unused4 = CommunityMainFragment.this.preScrollY = i;
                if (i == i2) {
                    if (CommunityMainFragment.this.scrollUp && !CommunityMainFragment.this.flipViewTitle.isFlipped()) {
                        CommunityMainFragment.this.flipViewTitle.showNext();
                    }
                } else if (!CommunityMainFragment.this.scrollUp && CommunityMainFragment.this.flipViewTitle.isFlipped()) {
                    CommunityMainFragment.this.flipViewTitle.showPrevious();
                }
                if (i < i2) {
                    CommunityMainFragment.this.resetInnerFragmentPosition();
                }
            }
        });
        this.trendingFragment.setScrollCallBack(new StickyScrollCallBack() {
            public void onScrollChanged(int i, boolean z, boolean z2) {
                if (CommunityMainFragment.this.scrollHeaderTop && CommunityMainFragment.this.activity != null) {
                    CommunityMainFragment.this.showOrHideMainTab(z);
                    CommunityMainFragment.this.showOrHideFlipView(z, z2);
                }
            }
        });
        this.foryouFragment.setScrollCallBack(new StickyScrollCallBack() {
            public void onScrollChanged(int i, boolean z, boolean z2) {
                if (CommunityMainFragment.this.scrollHeaderTop && CommunityMainFragment.this.activity != null) {
                    CommunityMainFragment.this.showOrHideMainTab(z);
                    CommunityMainFragment.this.showOrHideFlipView(z, z2);
                }
            }
        });
    }

    private void refreshHomeTitle() {
        this.mesView.setMesCount(Utils.Preference.getIntPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_HOME_NEW_MESSAGE, 0));
        this.lyMesView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MineActivity.jump(CommunityMainFragment.this.getActivity());
            }
        });
    }

    private void getHomeData() {
        ApiClient.getHomePostData(bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                CommunityMainFragment.this.handleHomeData(jsonObject);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                CommunityMainFragment.this.mSwipeRefreshLayout.setRefreshing(false);
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
                    CommunityMainFragment.this.refreshWebUrl(((HomeBanner) list.get(i)).getLink());
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
                            ColumnHomeActivity.jump(CommunityMainFragment.this.activity);
                        } else if (str.contains(Constants.WebViewURL.PAGE_QA)) {
                            QAHomeActivity.jump(CommunityMainFragment.this.activity);
                        } else if (str.contains(Constants.WebViewURL.PAGE_ACTIVITY)) {
                            ActivitiesActivity.jump(CommunityMainFragment.this.activity);
                        } else if (str.contains(Constants.WebViewURL.PAGE_MIUI)) {
                            CommunityMainFragment.this.activity.startActivity(new Intent(CommunityMainFragment.this.activity, MIUIActivity.class));
                        } else if (str.contains(Constants.WebViewURL.PAGE_SUBFORUM)) {
                            SubForumActivity.jump(CommunityMainFragment.this.activity);
                        } else if (str.contains(Constants.WebViewURL.PAGE_FEED)) {
                            FeedActivity.jump(CommunityMainFragment.this.activity);
                        } else {
                            String substring = str.substring(str.indexOf("-") + 1, str.lastIndexOf("-"));
                            if (!TextUtils.isEmpty(substring)) {
                                PlateActivity.jumpWithId(CommunityMainFragment.this.activity, substring, str2);
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            }
        });
        this.myGridView.setAdapter(homeIconGridAdapter);
        list.clear();
        list.addAll(getMiHomeGridData());
        homeIconGridAdapter.setData(list);
    }

    private List<HomeSuggest> getMiHomeGridData() {
        ArrayList arrayList = new ArrayList();
        HomeSuggest homeSuggest = new HomeSuggest();
        homeSuggest.setIcon("https://u01.appmifile.com/images/2018/03/02/79a69448-5ccd-408b-9ab3-5c0230afc1f0.png");
        homeSuggest.setFname(BBSApplication.getInstance().getResources().getString(R.string.subforums));
        homeSuggest.setLink(Constants.WebViewURL.PAGE_SUBFORUM);
        arrayList.add(homeSuggest);
        HomeSuggest homeSuggest2 = new HomeSuggest();
        homeSuggest2.setIcon("https://u01.appmifile.com/images/2017/12/01/bce467a2-5473-4449-83d5-b9bfd363f5d4.png");
        homeSuggest2.setLink(Constants.WebViewURL.PAGE_FEED);
        homeSuggest2.setFname(BBSApplication.getInstance().getResources().getString(R.string.feed));
        arrayList.add(homeSuggest2);
        return arrayList;
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

    private void setOptionsFrameAction() {
        if (getContext() != null) {
            this.mMainActionFrame = new MainActionOptionsFrame(getContext());
            this.mRootView.addView(this.mMainActionFrame, new FrameLayout.LayoutParams(-1, -1));
            this.mMainActionFrame.setOnActionOptionsClickListener(new MainActionOptionsFrame.OnActionOptionsClickListener() {
                public void onActionItemClick(ActionOptionItem actionOptionItem, int i) {
                    switch (i) {
                        case 0:
                            GoogleTrackerUtil.sendRecordEvent("home", "click_post", "click_post_thread_btn");
                            CommunityMainFragment.this.goToPost();
                            return;
                        case 1:
                            GoogleTrackerUtil.sendRecordEvent("home", "click_post", "click_post_qa_btn");
                            CommunityMainFragment.this.goToAskQuestion();
                            return;
                        default:
                            return;
                    }
                }
            });
        }
    }

    public boolean isActionMenuShown() {
        if (this.mMainActionFrame != null) {
            return this.mMainActionFrame.isExpandOptions();
        }
        return false;
    }

    public void hideActionMenu() {
        if (this.mMainActionFrame != null) {
            this.mMainActionFrame.hide();
        }
    }

    /* access modifiers changed from: private */
    public void goToTask() {
        if (!LoginManager.getInstance().hasLogin()) {
            gotoAccount();
        } else if (getActivity() != null) {
            startActivityForResult(new Intent(getActivity(), SignActivity.class), Constants.RequestCode.REQUEST_GO_POST);
        }
    }

    /* access modifiers changed from: private */
    public void goToPost() {
        if (LoginManager.getInstance().hasLogin()) {
            startActivityForResult(new Intent(getContext(), PostActivity.class), Constants.RequestCode.REQUEST_GO_POST);
        } else {
            gotoAccount();
        }
    }

    /* access modifiers changed from: private */
    public void goToAskQuestion() {
        if (LoginManager.getInstance().hasLogin()) {
            startActivityForResult(new Intent(this.activity, QuestionActivity.class).putExtra("default_type", ""), Constants.RequestCode.REQUEST_GO_POST);
        } else {
            gotoAccount();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Bundle extras;
        super.onActivityResult(i, i2, intent);
        if (i == 55555 && intent != null && (extras = intent.getExtras()) != null) {
            refreshWebUrl(extras.getString("url"));
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
