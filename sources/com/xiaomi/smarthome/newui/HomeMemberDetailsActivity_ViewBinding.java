package com.xiaomi.smarthome.newui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;

public class HomeMemberDetailsActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private HomeMemberDetailsActivity f20312a;
    private View b;
    private View c;
    private View d;

    @UiThread
    public HomeMemberDetailsActivity_ViewBinding(HomeMemberDetailsActivity homeMemberDetailsActivity) {
        this(homeMemberDetailsActivity, homeMemberDetailsActivity.getWindow().getDecorView());
    }

    @UiThread
    public HomeMemberDetailsActivity_ViewBinding(final HomeMemberDetailsActivity homeMemberDetailsActivity, View view) {
        this.f20312a = homeMemberDetailsActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn' and method 'onBackClicked'");
        homeMemberDetailsActivity.moduleA3ReturnBtn = (ImageView) Utils.castView(findRequiredView, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                homeMemberDetailsActivity.onBackClicked();
            }
        });
        homeMemberDetailsActivity.moduleA3ReturnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'moduleA3ReturnTitle'", TextView.class);
        homeMemberDetailsActivity.moduleA3RightBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_right_btn, "field 'moduleA3RightBtn'", ImageView.class);
        homeMemberDetailsActivity.sdUserIcon = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.sd_user_icon, "field 'sdUserIcon'", SimpleDraweeView.class);
        homeMemberDetailsActivity.tvUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_user_name, "field 'tvUserName'", TextView.class);
        homeMemberDetailsActivity.tvUserId = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_user_id, "field 'tvUserId'", TextView.class);
        homeMemberDetailsActivity.tvUserIdentity = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_user_identity, "field 'tvUserIdentity'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.tv_bottom_btn, "field 'tvBottomBtn' and method 'onBottomBtnClicked'");
        homeMemberDetailsActivity.tvBottomBtn = (TextView) Utils.castView(findRequiredView2, R.id.tv_bottom_btn, "field 'tvBottomBtn'", TextView.class);
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                homeMemberDetailsActivity.onBottomBtnClicked();
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.tv_invite, "field 'tvReinviting' and method 'onReinvitingClicked'");
        homeMemberDetailsActivity.tvReinviting = (TextView) Utils.castView(findRequiredView3, R.id.tv_invite, "field 'tvReinviting'", TextView.class);
        this.d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                homeMemberDetailsActivity.onReinvitingClicked();
            }
        });
    }

    @CallSuper
    public void unbind() {
        HomeMemberDetailsActivity homeMemberDetailsActivity = this.f20312a;
        if (homeMemberDetailsActivity != null) {
            this.f20312a = null;
            homeMemberDetailsActivity.moduleA3ReturnBtn = null;
            homeMemberDetailsActivity.moduleA3ReturnTitle = null;
            homeMemberDetailsActivity.moduleA3RightBtn = null;
            homeMemberDetailsActivity.sdUserIcon = null;
            homeMemberDetailsActivity.tvUserName = null;
            homeMemberDetailsActivity.tvUserId = null;
            homeMemberDetailsActivity.tvUserIdentity = null;
            homeMemberDetailsActivity.tvBottomBtn = null;
            homeMemberDetailsActivity.tvReinviting = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            this.d.setOnClickListener((View.OnClickListener) null);
            this.d = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
