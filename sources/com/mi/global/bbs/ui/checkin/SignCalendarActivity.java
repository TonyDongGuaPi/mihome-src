package com.mi.global.bbs.ui.checkin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.MyViewPagerAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.utils.BasePageChangeListener;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.global.bbs.view.PagerSlidingTabStrip;
import java.util.ArrayList;

public class SignCalendarActivity extends BaseActivity {
    private static final String DAYS = "day";
    private static final String FROM = "from";
    private static final String ICON = "icon";
    @BindView(2131493765)
    ViewPager mPager;
    @BindView(2131493983)
    TextView mSignDay1;
    @BindView(2131493984)
    TextView mSignDay2;
    @BindView(2131493985)
    TextView mSignDay3;
    @BindView(2131494047)
    PagerSlidingTabStrip mTab;
    @BindView(2131494213)
    CircleImageView mUserIcon;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitleAndBack(getString(R.string.check_in), this.titleBackListener);
        setCustomContentView(R.layout.bbs_activity_sign_calendar);
        ButterKnife.bind((Activity) this);
        int intExtra = getIntent().getIntExtra("from", 0);
        inflateHead(getIntent().getStringExtra("icon"), getIntent().getIntExtra(DAYS, 0));
        initTabs(intExtra);
    }

    private void inflateHead(String str, int i) {
        ImageLoader.showHeadIcon(this.mUserIcon, str);
        if (i >= 10) {
            this.mSignDay1.setText(String.valueOf(i / 100));
            int i2 = i % 100;
            this.mSignDay2.setText(String.valueOf(i2 / 10));
            this.mSignDay3.setText(String.valueOf(i2 % 10));
            return;
        }
        this.mSignDay3.setText(String.valueOf(i));
    }

    private void initTabs(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CalendarFragment());
        arrayList.add(new RankingFragment());
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.checkInTabs), arrayList));
        this.mTab.setViewPager(this.mPager);
        this.mTab.setUnderlineHeight((int) TypedValue.applyDimension(1, 1.0f, displayMetrics));
        this.mTab.setUnderlineColor(getResources().getColor(R.color.user_center_divider_color));
        this.mTab.setIndicatorColor(getResources().getColor(R.color.main_yellow));
        this.mTab.setIndicatorHeight((int) TypedValue.applyDimension(1, 2.0f, displayMetrics));
        this.mTab.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        this.mTab.setSelectedTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR_SELECT));
        this.mTab.setTextSize((int) TypedValue.applyDimension(2, 15.0f, displayMetrics));
        this.mTab.setDividerColor(0);
        this.mTab.setAllCaps(false);
        this.mTab.setShouldExpand(true);
        this.mPager.setCurrentItem(i);
        this.mTab.setOnPageChangeListener(new BasePageChangeListener() {
            public void onPageSelected(int i) {
                if (i == 0) {
                    GoogleTrackerUtil.sendRecordEvent("checkin_board", "click_calendar", "click_calendar");
                } else {
                    GoogleTrackerUtil.sendRecordEvent("checkin_board", Constants.ClickEvent.CLICK_USER, Constants.ClickEvent.CLICK_USER);
                }
            }
        });
    }

    public static void jump(Context context, int i, String str, int i2) {
        context.startActivity(new Intent(context, SignCalendarActivity.class).putExtra("from", i).putExtra("icon", str).putExtra(DAYS, i2));
    }
}
