package com.mi.global.bbs.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.MyViewPagerAdapter;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.observer.RefreshManager;
import com.mi.global.bbs.ui.FollowingFragment;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.utils.StatusBarClickListener;
import com.mi.global.bbs.view.AbsorbNavigationLayout;
import com.mi.global.bbs.view.PagerSlidingTabStrip;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class FollowingHomeFragment extends BaseFragment implements FollowingFragment.OnShowPostListener, Observer {
    @BindView(2131493022)
    View backBtn;
    /* access modifiers changed from: private */
    public FollowingFragment followingFragment;
    @BindView(2131493277)
    PagerSlidingTabStrip followingHomeNagTab;
    @BindView(2131493278)
    AbsorbNavigationLayout followingHomeNavigationContainer;
    @BindView(2131493279)
    ViewPager followingHomePager;
    @BindView(2131493280)
    RelativeLayout followingHomeToolBar;
    @BindView(2131493874)
    RelativeLayout recommend;
    /* access modifiers changed from: private */
    public int retainHeight = 0;
    /* access modifiers changed from: private */
    public ThoughtFragment thoughtFragment;
    @BindView(2131494101)
    View toolbarAgent;

    public void onShow(boolean z) {
    }

    public void update(Observable observable, Object obj) {
    }

    public static void jump(Context context) {
        context.startActivity(new Intent(context, FollowingHomeFragment.class));
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.following_home_fragment, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        RefreshManager.init().addObserver(this);
        initView();
        return inflate;
    }

    private void initView() {
        adjustStatusBar(this.toolbarAgent);
        initTab();
        this.followingHomeNavigationContainer.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                FollowingHomeFragment.this.followingHomeNavigationContainer.removeOnLayoutChangeListener(this);
                if (FollowingHomeFragment.this.followingFragment != null) {
                    FollowingHomeFragment.this.followingFragment.setPadding(FollowingHomeFragment.this.retainHeight);
                }
                if (FollowingHomeFragment.this.thoughtFragment != null) {
                    FollowingHomeFragment.this.thoughtFragment.setPadding(FollowingHomeFragment.this.retainHeight);
                }
            }
        });
        this.recommend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DiscoverPeopleActivity.show(FollowingHomeFragment.this.getActivity());
            }
        });
        this.backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FollowingHomeFragment.this.getActivity().finish();
            }
        });
    }

    private void initTab() {
        ArrayList arrayList = new ArrayList();
        this.followingFragment = new FollowingFragment();
        this.thoughtFragment = new ThoughtFragment();
        arrayList.add(this.followingFragment);
        arrayList.add(this.thoughtFragment);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.followingHomePager.setAdapter(new MyViewPagerAdapter(getActivity().getSupportFragmentManager(), getResources().getStringArray(R.array.thought_home_tab), arrayList));
        this.followingFragment.setOnScrollListener(this);
        this.thoughtFragment.setOnScrollListener(this);
        this.followingHomeNagTab.setTabPaddingLeftRight(30);
        this.followingHomeNagTab.setViewPager(this.followingHomePager);
        this.followingHomeNagTab.setUnderlineHeight(0);
        this.followingHomeNagTab.setUnderlineColor(getResources().getColor(R.color.user_center_divider_color));
        this.followingHomeNagTab.setIndicatorColor(getResources().getColor(R.color.main_yellow));
        this.followingHomeNagTab.setIndicatorHeight((int) TypedValue.applyDimension(1, 2.0f, displayMetrics));
        this.followingHomeNagTab.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        this.followingHomeNagTab.setSelectedTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR_SELECT));
        this.followingHomeNagTab.setTextSize((int) TypedValue.applyDimension(2, 15.0f, displayMetrics));
        this.followingHomeNagTab.setDividerColor(0);
        this.followingHomeNagTab.setAllCaps(false);
        this.followingHomeNagTab.setShouldExpand(false);
        addTitleDoubleClickAction();
    }

    public void getFragmentRefresh() {
        try {
            if (this.followingHomePager == null) {
                return;
            }
            if (this.followingHomePager.getCurrentItem() == 0) {
                if (this.followingFragment != null) {
                    this.followingFragment.onRefresh();
                }
            } else if (this.thoughtFragment != null) {
                this.thoughtFragment.onRefresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTitleDoubleClickAction() {
        this.followingHomeToolBar.setOnClickListener(new StatusBarClickListener(new StatusBarClickListener.OnDoubleClickListener() {
            public void onDoubleClick() {
                if (FollowingHomeFragment.this.followingHomePager.getCurrentItem() == 0) {
                    FollowingHomeFragment.this.followingFragment.smoothToTop();
                } else {
                    FollowingHomeFragment.this.thoughtFragment.smoothToTop();
                }
            }
        }));
    }
}
