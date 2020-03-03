package com.mi.global.bbs.ui.checkin;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.global.bbs.view.ImageCheckView;

public class SignActivity_ViewBinding implements Unbinder {
    private SignActivity target;
    private View view2131493074;
    private View view2131493075;
    private View view2131493981;
    private View view2131494053;

    @UiThread
    public SignActivity_ViewBinding(SignActivity signActivity) {
        this(signActivity, signActivity.getWindow().getDecorView());
    }

    @UiThread
    public SignActivity_ViewBinding(final SignActivity signActivity, View view) {
        this.target = signActivity;
        signActivity.mSignStatTv = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_stat_tv, "field 'mSignStatTv'", TextView.class);
        signActivity.mSignView1 = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.sign_view_1, "field 'mSignView1'", LinearLayout.class);
        signActivity.mEmoIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.emo_icon, "field 'mEmoIcon'", ImageView.class);
        signActivity.mSignTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_txt, "field 'mSignTxt'", TextView.class);
        signActivity.mSignView2 = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.sign_view_2, "field 'mSignView2'", LinearLayout.class);
        signActivity.mUserIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.user_icon, "field 'mUserIcon'", CircleImageView.class);
        signActivity.mSignDay1 = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_day_1, "field 'mSignDay1'", TextView.class);
        signActivity.mSignDay2 = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_day_2, "field 'mSignDay2'", TextView.class);
        signActivity.mSignDay3 = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_day_3, "field 'mSignDay3'", TextView.class);
        signActivity.mCheckbox = (ImageCheckView) Utils.findRequiredViewAsType(view, R.id.checkbox, "field 'mCheckbox'", ImageCheckView.class);
        signActivity.mSheet = (BottomSheetLayout) Utils.findRequiredViewAsType(view, R.id.sheet, "field 'mSheet'", BottomSheetLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.check_in_calendar_bt, "method 'onViewClicked'");
        this.view2131493074 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.check_in_leaderboard_bt, "method 'onViewClicked'");
        this.view2131493075 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.sign_bt, "method 'onViewClicked'");
        this.view2131493981 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signActivity.onViewClicked(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.task_bt, "method 'onViewClicked'");
        this.view2131494053 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        SignActivity signActivity = this.target;
        if (signActivity != null) {
            this.target = null;
            signActivity.mSignStatTv = null;
            signActivity.mSignView1 = null;
            signActivity.mEmoIcon = null;
            signActivity.mSignTxt = null;
            signActivity.mSignView2 = null;
            signActivity.mUserIcon = null;
            signActivity.mSignDay1 = null;
            signActivity.mSignDay2 = null;
            signActivity.mSignDay3 = null;
            signActivity.mCheckbox = null;
            signActivity.mSheet = null;
            this.view2131493074.setOnClickListener((View.OnClickListener) null);
            this.view2131493074 = null;
            this.view2131493075.setOnClickListener((View.OnClickListener) null);
            this.view2131493075 = null;
            this.view2131493981.setOnClickListener((View.OnClickListener) null);
            this.view2131493981 = null;
            this.view2131494053.setOnClickListener((View.OnClickListener) null);
            this.view2131494053 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
