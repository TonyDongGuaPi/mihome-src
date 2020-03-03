package com.mi.global.bbs.ui.checkin;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.global.bbs.view.PagerSlidingTabStrip;

public class SignCalendarActivity_ViewBinding implements Unbinder {
    private SignCalendarActivity target;

    @UiThread
    public SignCalendarActivity_ViewBinding(SignCalendarActivity signCalendarActivity) {
        this(signCalendarActivity, signCalendarActivity.getWindow().getDecorView());
    }

    @UiThread
    public SignCalendarActivity_ViewBinding(SignCalendarActivity signCalendarActivity, View view) {
        this.target = signCalendarActivity;
        signCalendarActivity.mUserIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.user_icon, "field 'mUserIcon'", CircleImageView.class);
        signCalendarActivity.mSignDay1 = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_day_1, "field 'mSignDay1'", TextView.class);
        signCalendarActivity.mSignDay2 = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_day_2, "field 'mSignDay2'", TextView.class);
        signCalendarActivity.mSignDay3 = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_day_3, "field 'mSignDay3'", TextView.class);
        signCalendarActivity.mTab = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.tab, "field 'mTab'", PagerSlidingTabStrip.class);
        signCalendarActivity.mPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.pager, "field 'mPager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        SignCalendarActivity signCalendarActivity = this.target;
        if (signCalendarActivity != null) {
            this.target = null;
            signCalendarActivity.mUserIcon = null;
            signCalendarActivity.mSignDay1 = null;
            signCalendarActivity.mSignDay2 = null;
            signCalendarActivity.mSignDay3 = null;
            signCalendarActivity.mTab = null;
            signCalendarActivity.mPager = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
