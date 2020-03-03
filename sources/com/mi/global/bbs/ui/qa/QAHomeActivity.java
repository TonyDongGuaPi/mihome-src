package com.mi.global.bbs.ui.qa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.MyViewPagerAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.ui.qa.QARequestFragment;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.view.AbsorbNavigationLayout;
import com.mi.global.bbs.view.PagerSlidingTabStrip;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import java.util.ArrayList;

public class QAHomeActivity extends BaseActivity implements QARequestFragment.OnLoginClickListener {
    @BindView(2131493247)
    ImageView fabImageView;
    private boolean mFabIsShown;
    @BindView(2131493848)
    TextView qaHomeDes;
    @BindView(2131493849)
    PagerSlidingTabStrip qaHomeNagTab;
    @BindView(2131493850)
    TextView qaHomeNameText;
    @BindView(2131493851)
    AbsorbNavigationLayout qaHomeNavigationContainer;
    @BindView(2131493852)
    ViewPager qaHomePager;
    @BindView(2131493854)
    ImageView qaHomeTopBg;
    @BindView(2131493855)
    LinearLayout qaHomeTopLayout;
    /* access modifiers changed from: private */
    public QANewFragment qaNewFragment;
    /* access modifiers changed from: private */
    public QARequestFragment qaRequestFragment;
    /* access modifiers changed from: private */
    public QATrendingFragment qaTrendingFragment;
    /* access modifiers changed from: private */
    public int retainHeight = 0;
    View.OnClickListener searchClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            QAHomeActivity.this.startActivity(new Intent(QAHomeActivity.this, QASearchActivity.class));
        }
    };

    public static void jump(Context context) {
        context.startActivity(new Intent(context, QAHomeActivity.class));
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        this.contentNeedMargin = false;
        super.onCreate(bundle);
        translucentStatusBar();
        setCustomContentView(R.layout.bbs_qa_home_activity);
        ButterKnife.bind((Activity) this);
        initView();
        adjustStatusBar();
    }

    private void adjustStatusBar() {
        int statusBarHeight = getStatusBarHeight();
        if (statusBarHeight > 0) {
            ((ViewGroup.MarginLayoutParams) this.qaHomeTopLayout.getLayoutParams()).topMargin = statusBarHeight;
            this.qaHomeTopLayout.requestLayout();
            this.mToolbarAgent.getLayoutParams().height = statusBarHeight;
            this.mToolbarAgent.requestLayout();
        }
    }

    private void initView() {
        initTab();
        LoginManager.getInstance().addLoginListener(this);
        this.qaHomeNavigationContainer.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                QAHomeActivity.this.qaHomeNavigationContainer.removeOnLayoutChangeListener(this);
                int unused = QAHomeActivity.this.retainHeight = QAHomeActivity.this.mToolBarContainer.getMeasuredHeight();
                QAHomeActivity.this.qaHomeNavigationContainer.setRetainHeight(QAHomeActivity.this.retainHeight);
                if (QAHomeActivity.this.qaTrendingFragment != null) {
                    QAHomeActivity.this.qaTrendingFragment.setPadding(QAHomeActivity.this.retainHeight);
                }
                if (QAHomeActivity.this.qaNewFragment != null) {
                    QAHomeActivity.this.qaNewFragment.setPadding(QAHomeActivity.this.retainHeight);
                }
                if (QAHomeActivity.this.qaRequestFragment != null) {
                    QAHomeActivity.this.qaRequestFragment.setPadding(QAHomeActivity.this.retainHeight);
                }
            }
        });
        this.mTitleView.setVisibility(4);
        this.qaHomeNameText.setText(getResources().getString(R.string.qa_home_title));
        this.qaHomeDes.setText(getResources().getString(R.string.qa_home_des));
        this.qaHomeTopBg.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bbs_qa_home_top_empty, getTheme()));
        this.mToolBarContainer.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_title_bar_bg)));
        this.mToolBarContainer.getBackground().setAlpha(0);
        this.mToolBarDivider.setVisibility(4);
        setLeftAndTitleAndRight(R.drawable.bbs_arrow_up_white, R.string.qa_home_title, R.drawable.bbs_bbs_qa_search_icon, this.searchClickListener);
        this.qaHomeNavigationContainer.setOnScrollProgressListener(new AbsorbNavigationLayout.OnScrollProgressListener() {
            public void onScrollProgress(float f) {
                if (QAHomeActivity.this.qaHomeNavigationContainer.getUpState()) {
                    QAHomeActivity.this.hideFab(true);
                } else {
                    QAHomeActivity.this.showFab(true);
                }
                int moveHeight = (int) (((float) QAHomeActivity.this.qaHomeNavigationContainer.getMoveHeight()) * f);
                if (moveHeight < QAHomeActivity.this.qaHomeNavigationContainer.getMoveHeight()) {
                    QAHomeActivity.this.mToolBarDivider.setVisibility(4);
                } else {
                    QAHomeActivity.this.mToolBarDivider.setVisibility(0);
                }
                if (moveHeight > 0) {
                    QAHomeActivity.this.qaHomeNameText.setVisibility(4);
                    QAHomeActivity.this.mTitleView.setVisibility(0);
                    QAHomeActivity.this.setLeftAndTitleAndRight(R.drawable.bbs_arrow_up_black, R.string.qa_home_title, R.drawable.bbs_qa_search_icon_black, QAHomeActivity.this.searchClickListener);
                } else {
                    QAHomeActivity.this.qaHomeNameText.setVisibility(0);
                    QAHomeActivity.this.mTitleView.setVisibility(4);
                    QAHomeActivity.this.setLeftAndTitleAndRight(R.drawable.bbs_arrow_up_white, R.string.qa_home_title, R.drawable.bbs_bbs_qa_search_icon, QAHomeActivity.this.searchClickListener);
                }
                QAHomeActivity.this.mToolBarContainer.getBackground().setAlpha((int) (f * 255.0f));
            }
        });
        this.fabImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (QAHomeActivity.this.qaHomePager.getCurrentItem() == 0) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_QA, Constants.ClickEvent.QA_CLICK_POST, "trending");
                } else if (QAHomeActivity.this.qaHomePager.getCurrentItem() == 1) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_QA, Constants.ClickEvent.QA_CLICK_POST, "new");
                } else {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_QA, Constants.ClickEvent.QA_CLICK_POST, "request");
                }
                QuestionActivity.jump(QAHomeActivity.this, "");
            }
        });
    }

    private void initTab() {
        ArrayList arrayList = new ArrayList();
        this.qaTrendingFragment = new QATrendingFragment();
        this.qaNewFragment = new QANewFragment();
        this.qaRequestFragment = new QARequestFragment();
        this.qaRequestFragment.setOnLoginClickListener(this);
        arrayList.add(this.qaTrendingFragment);
        arrayList.add(this.qaNewFragment);
        arrayList.add(this.qaRequestFragment);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.qaHomePager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.qa_home_tab), arrayList));
        this.qaHomeNagTab.setViewPager(this.qaHomePager);
        this.qaHomeNagTab.setUnderlineHeight((int) TypedValue.applyDimension(1, 1.0f, displayMetrics));
        this.qaHomeNagTab.setUnderlineColor(getResources().getColor(R.color.user_center_divider_color));
        this.qaHomeNagTab.setIndicatorColor(getResources().getColor(R.color.main_yellow));
        this.qaHomeNagTab.setIndicatorHeight((int) TypedValue.applyDimension(1, 2.0f, displayMetrics));
        this.qaHomeNagTab.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        this.qaHomeNagTab.setSelectedTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR_SELECT));
        this.qaHomeNagTab.setTextSize((int) TypedValue.applyDimension(2, 15.0f, displayMetrics));
        this.qaHomeNagTab.setDividerColor(0);
        this.qaHomeNagTab.setAllCaps(false);
        this.qaHomeNagTab.setTabPaddingLeftRight(19);
        this.qaHomeNagTab.setShouldExpand(true);
    }

    /* access modifiers changed from: private */
    public void showFab(boolean z) {
        if (this.fabImageView != null) {
            this.fabImageView.setClickable(true);
            if (!this.mFabIsShown) {
                if (z) {
                    ViewPropertyAnimator.animate(this.fabImageView).cancel();
                    ViewPropertyAnimator.animate(this.fabImageView).scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                } else {
                    ViewHelper.setScaleX(this.fabImageView, 1.0f);
                    ViewHelper.setScaleY(this.fabImageView, 1.0f);
                }
                this.mFabIsShown = true;
                return;
            }
            ViewHelper.setScaleX(this.fabImageView, 1.0f);
            ViewHelper.setScaleY(this.fabImageView, 1.0f);
        }
    }

    /* access modifiers changed from: private */
    public void hideFab(boolean z) {
        if (this.fabImageView != null) {
            this.fabImageView.setClickable(false);
            if (this.mFabIsShown) {
                if (z) {
                    ViewPropertyAnimator.animate(this.fabImageView).cancel();
                    ViewPropertyAnimator.animate(this.fabImageView).scaleX(0.0f).scaleY(0.0f).setDuration(300).start();
                } else {
                    ViewHelper.setScaleX(this.fabImageView, 0.0f);
                    ViewHelper.setScaleY(this.fabImageView, 0.0f);
                }
                this.mFabIsShown = false;
                return;
            }
            ViewHelper.setScaleX(this.fabImageView, 0.0f);
            ViewHelper.setScaleY(this.fabImageView, 0.0f);
        }
    }

    public void onLogin(String str, String str2, String str3) {
        super.onLogin(str, str2, str3);
        runOnUiThread(new Runnable() {
            public void run() {
                QAHomeActivity.this.qaRequestFragment.getData();
            }
        });
    }

    public void onClick() {
        gotoAccount();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        LoginManager.getInstance().removeLoginListener(this);
        super.onDestroy();
    }
}
