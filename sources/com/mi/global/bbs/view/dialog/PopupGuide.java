package com.mi.global.bbs.view.dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.ui.column.QuestionActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.view.MyGridView;
import com.mi.global.bbs.view.dialog.DoubleGuide;
import com.yuyh.library.EasyGuide;
import com.yuyh.library.support.OnStateChangedListener;

public class PopupGuide {
    public static void showCalendarGuide(Activity activity, LinearLayout linearLayout, final Runnable runnable) {
        View findViewWithTag = linearLayout.findViewWithTag("calendar");
        if (findViewWithTag != null) {
            int[] iArr = new int[2];
            findViewWithTag.getLocationOnScreen(iArr);
            Drawable drawable = ResourcesCompat.getDrawable(activity.getResources(), R.drawable.bbs_calendar_indicate_icon, activity.getTheme());
            EasyGuide a2 = new EasyGuide.Builder(activity).a(findViewWithTag, 0).a(R.drawable.bbs_calendar_indicate_icon, (iArr[0] + (findViewWithTag.getWidth() / 2)) - drawable.getIntrinsicWidth(), iArr[1] + findViewWithTag.getHeight()).a(createTipsView(activity, R.string.calendar_guide_text), 0, iArr[1] + findViewWithTag.getHeight() + drawable.getIntrinsicHeight(), new RelativeLayout.LayoutParams(-1, -2)).a(true).a();
            a2.a((OnStateChangedListener) new OnStateChangedListener() {
                public void onHeightlightViewClick(View view) {
                }

                public void onShow() {
                }

                public void onDismiss() {
                    runnable.run();
                }
            });
            a2.a();
        }
    }

    public static void showTabFollowGuide(final Activity activity, final LinearLayout linearLayout) {
        View childAt = linearLayout.getChildAt(2);
        if (childAt != null) {
            int[] iArr = new int[2];
            childAt.getLocationOnScreen(iArr);
            Drawable drawable = ResourcesCompat.getDrawable(activity.getResources(), R.drawable.bbs_follow_indicate_icon, activity.getTheme());
            EasyGuide a2 = new EasyGuide.Builder(activity).a(childAt, 0).a(R.drawable.bbs_follow_indicate_icon, iArr[0], iArr[1] - drawable.getIntrinsicHeight()).a(createTipsView(activity, R.string.follow_guide_text), 0, (iArr[1] - (childAt.getHeight() / 2)) - drawable.getIntrinsicHeight(), new RelativeLayout.LayoutParams(-1, -2)).a(true).a();
            a2.a((OnStateChangedListener) new OnStateChangedListener() {
                public void onHeightlightViewClick(View view) {
                }

                public void onShow() {
                }

                public void onDismiss() {
                    PopupGuide.showTabAccountGuide(activity, linearLayout);
                }
            });
            a2.a();
        }
    }

    public static void showTabAccountGuide(Activity activity, LinearLayout linearLayout) {
        View childAt = linearLayout.getChildAt(3);
        if (childAt != null) {
            int[] iArr = new int[2];
            childAt.getLocationOnScreen(iArr);
            Drawable drawable = ResourcesCompat.getDrawable(activity.getResources(), R.drawable.bbs_account_indicate_icon, activity.getTheme());
            new EasyGuide.Builder(activity).a(childAt, 0).a(R.drawable.bbs_account_indicate_icon, (iArr[0] - drawable.getIntrinsicWidth()) + (childAt.getWidth() / 2), (iArr[1] - drawable.getIntrinsicHeight()) - 10).a(createTipsView(activity, R.string.mission_guide_text), 0, (iArr[1] - (childAt.getHeight() / 2)) - drawable.getIntrinsicHeight(), new RelativeLayout.LayoutParams(-1, -2)).a(true).a().a();
        }
    }

    private static View createTipsView(Activity activity, @StringRes int i) {
        TextView textView = new TextView(activity);
        textView.setTextColor(-1);
        textView.setGravity(1);
        textView.setTextSize(2, 16.0f);
        textView.setText(i);
        int dimensionPixelOffset = activity.getResources().getDimensionPixelOffset(R.dimen.dimen_20);
        textView.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        return textView;
    }

    public static void showForYouGuide(BaseActivity baseActivity, View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        new EasyGuide.Builder(baseActivity).a(view, 1).a(R.drawable.bbs_guide_point, iArr[0] + (view.getWidth() / 2), iArr[1] + view.getHeight() + 18).a(createForYouTipsView(baseActivity), 0, iArr[1] + view.getHeight() + ResourcesCompat.getDrawable(baseActivity.getResources(), R.drawable.bbs_guide_point, baseActivity.getTheme()).getIntrinsicHeight() + 35, new RelativeLayout.LayoutParams(-1, -2)).a(true).a().a();
    }

    private static View createForYouTipsView(final BaseActivity baseActivity) {
        View inflate = LayoutInflater.from(baseActivity).inflate(R.layout.bbs_foryou_tips_layout, (ViewGroup) null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.do_it_now);
        textView.setText(baseActivity.getString(R.string.do_it_now).replace("!", ""));
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginManager.getInstance().hasLogin()) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FORU, Constants.ClickEvent.CLICK_PERSONALIZE, "click_personalize_start1");
                    baseActivity.startActivity(new Intent(baseActivity, QuestionActivity.class));
                    return;
                }
                baseActivity.gotoAccount();
            }
        });
        return inflate;
    }

    public static void showActivityAndMIUIGuide(BaseActivity baseActivity, MyGridView myGridView) {
        View childAt = ((RelativeLayout) myGridView.getChildAt(0)).getChildAt(0);
        View childAt2 = ((RelativeLayout) myGridView.getChildAt(1)).getChildAt(0);
        if (childAt != null && childAt2 != null) {
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            childAt.getLocationOnScreen(iArr);
            childAt2.getLocationOnScreen(iArr2);
            Drawable drawable = ResourcesCompat.getDrawable(baseActivity.getResources(), R.drawable.bbs_activity_down_arrow, baseActivity.getTheme());
            Drawable drawable2 = ResourcesCompat.getDrawable(baseActivity.getResources(), R.drawable.bbs_activity_up_arrow, baseActivity.getTheme());
            new DoubleGuide.Builder(baseActivity).addHightArea(childAt, 0).addHightArea(childAt2, 0).addIndicator(R.drawable.bbs_activity_down_arrow, iArr2[0] + (childAt2.getWidth() / 2), (iArr2[1] - childAt2.getHeight()) - 8).addIndicator(R.drawable.bbs_activity_up_arrow, (iArr[0] + (childAt.getWidth() / 2)) - 8, iArr[1] + childAt.getHeight() + 8).addView(createActivityAndMIUITipsView(baseActivity, R.string.miui_guide_string), 0, ((iArr2[1] - childAt2.getHeight()) - drawable.getIntrinsicHeight()) + 10, new RelativeLayout.LayoutParams(-1, -2)).addView(createActivityAndMIUITipsView(baseActivity, R.string.activity_guide_string), 0, iArr[1] + childAt.getHeight() + 8 + drawable2.getIntrinsicHeight(), new RelativeLayout.LayoutParams(-1, -2)).dismissAnyWhere(false).setPositiveButton(baseActivity.getResources().getString(R.string.guide_got_it), 16, iArr[1] + (childAt.getHeight() * 3) + drawable2.getIntrinsicHeight()).build().show();
        }
    }

    private static View createActivityAndMIUITipsView(Activity activity, @StringRes int i) {
        TextView textView = new TextView(activity);
        textView.setTextColor(-1);
        textView.setGravity(1);
        textView.setTextSize(2, 16.0f);
        textView.setText(i);
        int dimensionPixelOffset = activity.getResources().getDimensionPixelOffset(R.dimen.dimen_20);
        textView.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        return textView;
    }
}
