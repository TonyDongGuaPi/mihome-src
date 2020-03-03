package com.mi.global.bbs.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.MyViewPagerAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.view.AbsorbNavigationLayout;
import com.mi.global.bbs.view.PagerSlidingTabStrip;
import java.util.ArrayList;
import java.util.List;

public class ActivitiesActivity extends BaseActivity {
    public static final int INCITY_ACTIVITY_TAB = 1;
    public static final int ONLINE_ACTIVITY_TAB = 0;
    public static final int ONLINE_ACTIVITY_TAB_INDICATE_HIGH = 2;
    public static final String ONLINE_ACTIVITY_TAB_TEXT_COLOR = "#999999";
    public static final String ONLINE_ACTIVITY_TAB_TEXT_COLOR_SELECT = "#ff6700";
    public static final int ONLINE_ACTIVITY_TAB_TEXT_SIZE = 15;
    public static final int ONLINE_ACTIVITY_TAB_UNDER_LINE_HIGH = 1;
    @BindView(2131492929)
    TextView activityHomeDes;
    @BindView(2131492930)
    TextView activityHomeNameText;
    @BindView(2131492931)
    AbsorbNavigationLayout activityHomeNavigationContainer;
    @BindView(2131492933)
    ImageView activityHomeTopBg;
    @BindView(2131492934)
    LinearLayout activityHomeTopLayout;
    public List<Fragment> fragments;
    /* access modifiers changed from: private */
    public InCityFragment inCityFragment;
    @BindView(2131493343)
    PagerSlidingTabStrip newsForumNagTab;
    @BindView(2131493344)
    ViewPager newsForumPager;
    /* access modifiers changed from: private */
    public OnlineFragment onlineFragment;
    /* access modifiers changed from: private */
    public int retainHeight = 0;

    public static void jump(Context context) {
        context.startActivity(new Intent(context, ActivitiesActivity.class));
    }

    public void onCreate(@Nullable Bundle bundle) {
        this.contentNeedMargin = false;
        super.onCreate(bundle);
        translucentStatusBar();
        if (bundle != null) {
            finish();
        }
        setCustomContentView(R.layout.bbs_activity_fragment_home);
        ButterKnife.bind((Activity) this);
        initView();
        adjustStatusBar();
    }

    private void adjustStatusBar() {
        int statusBarHeight = getStatusBarHeight();
        if (statusBarHeight > 0) {
            ((ViewGroup.MarginLayoutParams) this.activityHomeTopLayout.getLayoutParams()).topMargin = statusBarHeight;
            this.activityHomeTopLayout.requestLayout();
            this.mToolbarAgent.getLayoutParams().height = statusBarHeight;
            this.mToolbarAgent.requestLayout();
        }
    }

    private void initView() {
        initTab();
        this.activityHomeNavigationContainer.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                ActivitiesActivity.this.activityHomeNavigationContainer.removeOnLayoutChangeListener(this);
                int unused = ActivitiesActivity.this.retainHeight = ActivitiesActivity.this.mToolBarContainer.getMeasuredHeight();
                ActivitiesActivity.this.activityHomeNavigationContainer.setRetainHeight(ActivitiesActivity.this.retainHeight);
                if (ActivitiesActivity.this.onlineFragment != null) {
                    ActivitiesActivity.this.onlineFragment.setPadding(ActivitiesActivity.this.retainHeight);
                }
                if (ActivitiesActivity.this.inCityFragment != null) {
                    ActivitiesActivity.this.inCityFragment.setPadding(ActivitiesActivity.this.retainHeight);
                }
            }
        });
        this.mTitleView.setVisibility(4);
        this.activityHomeNameText.setText(getResources().getString(R.string.activity_home_title));
        this.activityHomeDes.setText(getResources().getString(R.string.activity_home_des));
        this.activityHomeTopBg.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bbs_activity_home_top_bg, getTheme()));
        this.mToolBarContainer.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_title_bar_bg)));
        this.mToolBarContainer.getBackground().setAlpha(0);
        this.mToolBarDivider.setVisibility(4);
        setTitleAndLeftBack(R.string.activity_home_title, R.drawable.bbs_arrow_up_white);
        this.activityHomeNavigationContainer.setOnScrollProgressListener(new AbsorbNavigationLayout.OnScrollProgressListener() {
            public void onScrollProgress(float f) {
                int moveHeight = (int) (((float) ActivitiesActivity.this.activityHomeNavigationContainer.getMoveHeight()) * f);
                if (moveHeight < ActivitiesActivity.this.activityHomeNavigationContainer.getMoveHeight()) {
                    ActivitiesActivity.this.mToolBarDivider.setVisibility(4);
                } else {
                    ActivitiesActivity.this.mToolBarDivider.setVisibility(0);
                }
                if (moveHeight > 0) {
                    ActivitiesActivity.this.activityHomeNameText.setVisibility(4);
                    ActivitiesActivity.this.mTitleView.setVisibility(0);
                    ActivitiesActivity.this.setTitleAndLeftBack(R.string.activity_home_title, R.drawable.bbs_arrow_up_black);
                } else {
                    ActivitiesActivity.this.activityHomeNameText.setVisibility(0);
                    ActivitiesActivity.this.mTitleView.setVisibility(4);
                    ActivitiesActivity.this.setTitleAndLeftBack(R.string.activity_home_title, R.drawable.bbs_arrow_up_white);
                }
                ActivitiesActivity.this.mToolBarContainer.getBackground().setAlpha((int) (f * 255.0f));
            }
        });
    }

    private void initTab() {
        this.fragments = new ArrayList();
        this.onlineFragment = new OnlineFragment();
        this.inCityFragment = new InCityFragment();
        this.fragments.add(this.onlineFragment);
        this.fragments.add(this.inCityFragment);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.newsForumPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.activity_tab), this.fragments));
        this.newsForumPager.setCurrentItem(1);
        this.newsForumNagTab.setViewPager(this.newsForumPager);
        this.newsForumNagTab.setUnderlineHeight((int) TypedValue.applyDimension(1, 1.0f, displayMetrics));
        this.newsForumNagTab.setUnderlineColor(getResources().getColor(R.color.user_center_divider_color));
        this.newsForumNagTab.setIndicatorColor(getResources().getColor(R.color.main_yellow));
        this.newsForumNagTab.setIndicatorHeight((int) TypedValue.applyDimension(1, 2.0f, displayMetrics));
        this.newsForumNagTab.setTextColor(Color.parseColor(ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        this.newsForumNagTab.setSelectedTextColor(Color.parseColor(ONLINE_ACTIVITY_TAB_TEXT_COLOR_SELECT));
        this.newsForumNagTab.setTextSize((int) TypedValue.applyDimension(2, 15.0f, displayMetrics));
        this.newsForumNagTab.setDividerColor(0);
        this.newsForumNagTab.setAllCaps(false);
        this.newsForumNagTab.setShouldExpand(true);
    }
}
