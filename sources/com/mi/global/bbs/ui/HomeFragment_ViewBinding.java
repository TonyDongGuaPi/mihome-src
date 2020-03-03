package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bigkoo.convenientbanner.view.CBLoopViewPager;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.MyGridView;
import com.mi.global.bbs.view.PagerSlidingTabStrip;
import com.mi.global.bbs.view.ProfileMesView;
import com.mi.global.bbs.view.headerviewpager.HeaderViewPager;
import com.mi.global.bbs.view.swipe.MySwipeRefreshLayout;
import eu.davidea.flipview.FlipView;

public class HomeFragment_ViewBinding implements Unbinder {
    private HomeFragment target;

    @UiThread
    public HomeFragment_ViewBinding(HomeFragment homeFragment, View view) {
        this.target = homeFragment;
        homeFragment.lyTitleBack = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.home_title_back_ly, "field 'lyTitleBack'", LinearLayout.class);
        homeFragment.ivTitleSearch = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_home_title_search, "field 'ivTitleSearch'", ImageView.class);
        homeFragment.lyMesView = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ly_home_title_mes, "field 'lyMesView'", LinearLayout.class);
        homeFragment.mesView = (ProfileMesView) Utils.findRequiredViewAsType(view, R.id.iv_home_title_mes, "field 'mesView'", ProfileMesView.class);
        homeFragment.tvTitleSearch = (TextView) Utils.findRequiredViewAsType(view, R.id.home_serach_title, "field 'tvTitleSearch'", TextView.class);
        homeFragment.layoutSearch = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_home_search, "field 'layoutSearch'", LinearLayout.class);
        homeFragment.scrollableLayout = (HeaderViewPager) Utils.findRequiredViewAsType(view, R.id.scrollableLayout, "field 'scrollableLayout'", HeaderViewPager.class);
        homeFragment.mSwipeRefreshLayout = (MySwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.id_mainswiperefreshlayout, "field 'mSwipeRefreshLayout'", MySwipeRefreshLayout.class);
        homeFragment.mainTitleContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.main_title_container, "field 'mainTitleContainer'", LinearLayout.class);
        homeFragment.titleContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.home_title_container, "field 'titleContainer'", LinearLayout.class);
        homeFragment.toolbarAgent = Utils.findRequiredView(view, R.id.toolbar_agent, "field 'toolbarAgent'");
        homeFragment.bannerToolbarAgent = Utils.findRequiredView(view, R.id.banner_toolbar_agent, "field 'bannerToolbarAgent'");
        homeFragment.banner = (CBLoopViewPager) Utils.findRequiredViewAsType(view, R.id.banner, "field 'banner'", CBLoopViewPager.class);
        homeFragment.bannerContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.banner_container, "field 'bannerContainer'", LinearLayout.class);
        homeFragment.frameTitleFlipper = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.frame_flipper_title, "field 'frameTitleFlipper'", FrameLayout.class);
        homeFragment.myGridView = (MyGridView) Utils.findRequiredViewAsType(view, R.id.grid_recommend, "field 'myGridView'", MyGridView.class);
        homeFragment.newsForumNagTab = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.news_forum_nag, "field 'newsForumNagTab'", PagerSlidingTabStrip.class);
        homeFragment.newsForumPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.news_forum_pager, "field 'newsForumPager'", ViewPager.class);
        homeFragment.newsForumNagTabTop = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.news_forum_nag_top, "field 'newsForumNagTabTop'", PagerSlidingTabStrip.class);
        homeFragment.flipViewTitle = (FlipView) Utils.findRequiredViewAsType(view, R.id.flip_layout, "field 'flipViewTitle'", FlipView.class);
        homeFragment.headerBackBtnImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_header_back_btn, "field 'headerBackBtnImg'", ImageView.class);
        homeFragment.headerCheckinBtnImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_home_title_back, "field 'headerCheckinBtnImg'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        HomeFragment homeFragment = this.target;
        if (homeFragment != null) {
            this.target = null;
            homeFragment.lyTitleBack = null;
            homeFragment.ivTitleSearch = null;
            homeFragment.lyMesView = null;
            homeFragment.mesView = null;
            homeFragment.tvTitleSearch = null;
            homeFragment.layoutSearch = null;
            homeFragment.scrollableLayout = null;
            homeFragment.mSwipeRefreshLayout = null;
            homeFragment.mainTitleContainer = null;
            homeFragment.titleContainer = null;
            homeFragment.toolbarAgent = null;
            homeFragment.bannerToolbarAgent = null;
            homeFragment.banner = null;
            homeFragment.bannerContainer = null;
            homeFragment.frameTitleFlipper = null;
            homeFragment.myGridView = null;
            homeFragment.newsForumNagTab = null;
            homeFragment.newsForumPager = null;
            homeFragment.newsForumNagTabTop = null;
            homeFragment.flipViewTitle = null;
            homeFragment.headerBackBtnImg = null;
            homeFragment.headerCheckinBtnImg = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
