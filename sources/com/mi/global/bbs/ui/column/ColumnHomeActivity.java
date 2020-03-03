package com.mi.global.bbs.ui.column;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.MyViewPagerAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.ColumnHomeModel;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.ui.column.HotTopicFragment;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.AbsorbNavigationLayout;
import com.mi.global.bbs.view.PagerSlidingTabStrip;
import com.mi.log.LogUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;

public class ColumnHomeActivity extends BaseActivity implements HotTopicFragment.ColumnRefreshListener {
    private static final String TAG = "ColumnHomeActivity";
    View.OnClickListener applyClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (LoginManager.getInstance().hasLogin()) {
                WebActivity.jump(ColumnHomeActivity.this, ColumnHomeActivity.this.applyUrl);
            } else {
                ColumnHomeActivity.this.gotoAccount();
            }
        }
    };
    /* access modifiers changed from: private */
    public String applyUrl;
    @BindView(2131493108)
    PagerSlidingTabStrip columnHomeNagTab;
    @BindView(2131493109)
    TextView columnHomeNameText;
    @BindView(2131493110)
    AbsorbNavigationLayout columnHomeNavigationContainer;
    @BindView(2131493111)
    ViewPager columnHomePager;
    /* access modifiers changed from: private */
    public HotTopicFragment hotFragment;
    /* access modifiers changed from: private */
    public boolean isTopHeader = true;
    private long lastSubUpdateTime;
    /* access modifiers changed from: private */
    public boolean openApply = false;
    private int page = 1;
    private int pageSize = 10;
    /* access modifiers changed from: private */
    public int retainHeight = 0;
    private int statusBarHeight;
    /* access modifiers changed from: private */
    public MySubcriptionFragment subscriptionFragment;

    public static void jump(Context context) {
        context.startActivity(new Intent(context, ColumnHomeActivity.class));
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        this.contentNeedMargin = false;
        super.onCreate(bundle);
        translucentStatusBar();
        setCustomContentView(R.layout.bbs_column_home_activity);
        ButterKnife.bind((Activity) this);
        adjustStatusBar();
        initView();
        getData();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        resetColumnNameTextPostion();
    }

    private void adjustStatusBar() {
        this.statusBarHeight = getStatusBarHeight();
        if (this.statusBarHeight > 0) {
            this.mToolbarAgent.getLayoutParams().height = this.statusBarHeight;
            this.mToolbarAgent.requestLayout();
        }
    }

    private void resetColumnNameTextPostion() {
        ((ViewGroup.MarginLayoutParams) this.columnHomeNameText.getLayoutParams()).topMargin = this.statusBarHeight + ((this.mTitleView.getHeight() - this.columnHomeNameText.getHeight()) / 2);
        this.columnHomeNameText.requestLayout();
    }

    @RequiresApi(api = 11)
    private void initView() {
        initTab();
        LoginManager.getInstance().addLoginListener(this);
        this.columnHomeNavigationContainer.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                ColumnHomeActivity.this.columnHomeNavigationContainer.removeOnLayoutChangeListener(this);
                int unused = ColumnHomeActivity.this.retainHeight = ColumnHomeActivity.this.mToolBarContainer.getMeasuredHeight();
                ColumnHomeActivity.this.columnHomeNavigationContainer.setRetainHeight(ColumnHomeActivity.this.retainHeight);
                if (ColumnHomeActivity.this.hotFragment != null) {
                    ColumnHomeActivity.this.hotFragment.setPadding(ColumnHomeActivity.this.retainHeight);
                }
                if (ColumnHomeActivity.this.subscriptionFragment != null) {
                    ColumnHomeActivity.this.subscriptionFragment.setPadding(ColumnHomeActivity.this.retainHeight);
                }
            }
        });
        this.mTitleView.setVisibility(4);
        this.columnHomeNameText.setText(getResources().getString(R.string.str_column));
        this.mToolBarContainer.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_title_bar_bg)));
        this.mToolBarContainer.getBackground().setAlpha(0);
        this.mToolBarDivider.setVisibility(4);
        setLeftAndTitleAndRight(R.drawable.bbs_arrow_up_white, R.string.str_column, R.drawable.bbs_bbs_qa_search_icon, this.applyClickListener);
        this.columnHomeNavigationContainer.setOnScrollProgressListener(new AbsorbNavigationLayout.OnScrollProgressListener() {
            public void onScrollProgress(float f) {
                int top = (ColumnHomeActivity.this.columnHomeNameText.getTop() - ColumnHomeActivity.this.mTitleView.getTop()) - ColumnHomeActivity.this.mToolbarAgent.getHeight();
                int moveHeight = (int) (((float) ColumnHomeActivity.this.columnHomeNavigationContainer.getMoveHeight()) * f);
                boolean z = false;
                if (moveHeight < ColumnHomeActivity.this.columnHomeNavigationContainer.getMoveHeight()) {
                    ColumnHomeActivity.this.mToolBarDivider.setVisibility(4);
                } else {
                    ColumnHomeActivity.this.mToolBarDivider.setVisibility(0);
                }
                if (moveHeight >= top) {
                    ColumnHomeActivity.this.columnHomeNameText.setVisibility(4);
                    ColumnHomeActivity.this.mTitleView.setVisibility(0);
                } else {
                    ColumnHomeActivity.this.columnHomeNameText.setVisibility(0);
                    ColumnHomeActivity.this.mTitleView.setVisibility(4);
                }
                ColumnHomeActivity columnHomeActivity = ColumnHomeActivity.this;
                if (f == 0.0f) {
                    z = true;
                }
                boolean unused = columnHomeActivity.isTopHeader = z;
                ColumnHomeActivity.this.setColumnTitleAndRight(ColumnHomeActivity.this.isTopHeader, ColumnHomeActivity.this.openApply);
                ColumnHomeActivity.this.mToolBarContainer.getBackground().setAlpha((int) (f * 255.0f));
            }
        });
    }

    private void initTab() {
        ArrayList arrayList = new ArrayList();
        this.hotFragment = new HotTopicFragment();
        this.subscriptionFragment = new MySubcriptionFragment();
        arrayList.add(this.hotFragment);
        arrayList.add(this.subscriptionFragment);
        this.hotFragment.setOnRefreshColumnListener(this);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.columnHomePager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.column_tab), arrayList));
        this.columnHomeNagTab.setViewPager(this.columnHomePager);
        this.columnHomeNagTab.setUnderlineHeight((int) TypedValue.applyDimension(1, 1.0f, displayMetrics));
        this.columnHomeNagTab.setUnderlineColor(getResources().getColor(R.color.user_center_divider_color));
        this.columnHomeNagTab.setIndicatorColor(getResources().getColor(R.color.main_yellow));
        this.columnHomeNagTab.setIndicatorHeight((int) TypedValue.applyDimension(1, 2.0f, displayMetrics));
        this.columnHomeNagTab.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        this.columnHomeNagTab.setSelectedTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR_SELECT));
        this.columnHomeNagTab.setTextSize((int) TypedValue.applyDimension(2, 15.0f, displayMetrics));
        this.columnHomeNagTab.setDividerColor(0);
        this.columnHomeNagTab.setAllCaps(false);
        this.columnHomeNagTab.setShouldExpand(true);
        this.columnHomePager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int i) {
                if (i == 1) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_COLUMN_INDEX, Constants.ClickEvent.CLICK_COLUMN_MYSUB, Constants.ClickEvent.CLICK_COLUMN_MYSUB);
                    ColumnHomeActivity.this.setLastUpdateTime();
                    return;
                }
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_COLUMN_INDEX, "click_more", "click_more");
            }
        });
        this.columnHomePager.setCurrentItem(0);
    }

    private void getData() {
        ApiClient.getColumnIndex(this.page, this.pageSize, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<ColumnHomeModel>() {
            public void accept(@NonNull ColumnHomeModel columnHomeModel) throws Exception {
                if (columnHomeModel != null && columnHomeModel.data != null) {
                    ColumnHomeActivity.this.checkSubUpdateTime(columnHomeModel.data.subscribeUpdateTime);
                    ColumnHomeActivity.this.setTopData(columnHomeModel);
                    ColumnHomeActivity.this.hotFragment.setFirstPageData(columnHomeModel);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                LogUtil.a(th.toString());
            }
        });
    }

    /* access modifiers changed from: private */
    public void setTopData(ColumnHomeModel columnHomeModel) {
        ColumnHomeModel.DataBean.Config config = columnHomeModel.data.config;
        if (config != null) {
            this.openApply = config.openApply;
            this.applyUrl = config.applyUrl;
            setColumnTitleAndRight(this.isTopHeader, this.openApply);
        }
    }

    /* access modifiers changed from: private */
    public void setColumnTitleAndRight(boolean z, boolean z2) {
        if (z) {
            this.columnHomeNameText.setVisibility(0);
            if (z2) {
                setLeftAndTitleAndRightButton(R.drawable.bbs_arrow_up_white, R.string.str_column, R.layout.bbs_column_white_btn_apply, this.applyClickListener);
            } else {
                setTitleAndLeftBack(R.string.str_column, R.drawable.bbs_arrow_up_white);
            }
        } else if (z2) {
            setLeftAndTitleAndRightButton(R.drawable.bbs_arrow_up_black, R.string.str_column, R.layout.bbs_column_black_btn_apply, this.applyClickListener);
        } else {
            setTitleAndLeftBack(R.string.str_column, R.drawable.bbs_arrow_up_black);
        }
    }

    /* access modifiers changed from: private */
    public void checkSubUpdateTime(long j) {
        this.lastSubUpdateTime = j;
        if (j != Utils.Preference.getLongPref(this, Constants.Prefence.PREF_KEY_COLUMN_UPDATE, 0)) {
            this.columnHomeNagTab.showPointAt(1);
        }
    }

    /* access modifiers changed from: private */
    public void setLastUpdateTime() {
        this.columnHomeNagTab.clearPoint();
        Utils.Preference.setLongPref(this, Constants.Prefence.PREF_KEY_COLUMN_UPDATE, Long.valueOf(this.lastSubUpdateTime));
    }

    public void onLogin(String str, String str2, String str3) {
        super.onLogin(str, str2, str3);
        runOnUiThread(new Runnable() {
            public void run() {
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        LoginManager.getInstance().removeLoginListener(this);
        super.onDestroy();
    }

    public void onRefreshColumn() {
        this.page = 1;
        getData();
    }
}
