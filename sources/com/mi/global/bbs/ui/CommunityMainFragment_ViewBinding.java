package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
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

public class CommunityMainFragment_ViewBinding implements Unbinder {
    private CommunityMainFragment target;

    @UiThread
    public CommunityMainFragment_ViewBinding(CommunityMainFragment communityMainFragment, View view) {
        this.target = communityMainFragment;
        communityMainFragment.lyTitleBack = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.home_title_back_ly, "field 'lyTitleBack'", LinearLayout.class);
        communityMainFragment.mRootView = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.root_view, "field 'mRootView'", ViewGroup.class);
        communityMainFragment.ivTitleSearch = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_home_title_search, "field 'ivTitleSearch'", ImageView.class);
        communityMainFragment.lyMesView = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ly_home_title_mes, "field 'lyMesView'", LinearLayout.class);
        communityMainFragment.mesView = (ProfileMesView) Utils.findRequiredViewAsType(view, R.id.iv_home_title_mes, "field 'mesView'", ProfileMesView.class);
        communityMainFragment.tvTitleSearch = (TextView) Utils.findRequiredViewAsType(view, R.id.home_serach_title, "field 'tvTitleSearch'", TextView.class);
        communityMainFragment.layoutSearch = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_home_search, "field 'layoutSearch'", LinearLayout.class);
        communityMainFragment.scrollableLayout = (HeaderViewPager) Utils.findRequiredViewAsType(view, R.id.scrollableLayout, "field 'scrollableLayout'", HeaderViewPager.class);
        communityMainFragment.mSwipeRefreshLayout = (MySwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.id_mainswiperefreshlayout, "field 'mSwipeRefreshLayout'", MySwipeRefreshLayout.class);
        communityMainFragment.mainTitleContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.main_title_container, "field 'mainTitleContainer'", LinearLayout.class);
        communityMainFragment.titleContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.home_title_container, "field 'titleContainer'", LinearLayout.class);
        communityMainFragment.toolbarAgent = Utils.findRequiredView(view, R.id.toolbar_agent, "field 'toolbarAgent'");
        communityMainFragment.bannerToolbarAgent = Utils.findRequiredView(view, R.id.banner_toolbar_agent, "field 'bannerToolbarAgent'");
        communityMainFragment.banner = (CBLoopViewPager) Utils.findRequiredViewAsType(view, R.id.banner, "field 'banner'", CBLoopViewPager.class);
        communityMainFragment.bannerContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.banner_container, "field 'bannerContainer'", LinearLayout.class);
        communityMainFragment.frameTitleFlipper = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.frame_flipper_title, "field 'frameTitleFlipper'", FrameLayout.class);
        communityMainFragment.myGridView = (MyGridView) Utils.findRequiredViewAsType(view, R.id.grid_recommend, "field 'myGridView'", MyGridView.class);
        communityMainFragment.newsForumNagTab = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.news_forum_nag, "field 'newsForumNagTab'", PagerSlidingTabStrip.class);
        communityMainFragment.newsForumPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.news_forum_pager, "field 'newsForumPager'", ViewPager.class);
        communityMainFragment.newsForumNagTabTop = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.news_forum_nag_top, "field 'newsForumNagTabTop'", PagerSlidingTabStrip.class);
        communityMainFragment.flipViewTitle = (FlipView) Utils.findRequiredViewAsType(view, R.id.flip_layout, "field 'flipViewTitle'", FlipView.class);
    }

    @CallSuper
    public void unbind() {
        CommunityMainFragment communityMainFragment = this.target;
        if (communityMainFragment != null) {
            this.target = null;
            communityMainFragment.lyTitleBack = null;
            communityMainFragment.mRootView = null;
            communityMainFragment.ivTitleSearch = null;
            communityMainFragment.lyMesView = null;
            communityMainFragment.mesView = null;
            communityMainFragment.tvTitleSearch = null;
            communityMainFragment.layoutSearch = null;
            communityMainFragment.scrollableLayout = null;
            communityMainFragment.mSwipeRefreshLayout = null;
            communityMainFragment.mainTitleContainer = null;
            communityMainFragment.titleContainer = null;
            communityMainFragment.toolbarAgent = null;
            communityMainFragment.bannerToolbarAgent = null;
            communityMainFragment.banner = null;
            communityMainFragment.bannerContainer = null;
            communityMainFragment.frameTitleFlipper = null;
            communityMainFragment.myGridView = null;
            communityMainFragment.newsForumNagTab = null;
            communityMainFragment.newsForumPager = null;
            communityMainFragment.newsForumNagTabTop = null;
            communityMainFragment.flipViewTitle = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
