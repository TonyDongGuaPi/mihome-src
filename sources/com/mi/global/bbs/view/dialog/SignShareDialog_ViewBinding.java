package com.mi.global.bbs.view.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.CircleImageView;

public class SignShareDialog_ViewBinding implements Unbinder {
    private SignShareDialog target;
    private View view2131493084;
    private View view2131493209;

    @UiThread
    public SignShareDialog_ViewBinding(SignShareDialog signShareDialog) {
        this(signShareDialog, signShareDialog.getWindow().getDecorView());
    }

    @UiThread
    public SignShareDialog_ViewBinding(final SignShareDialog signShareDialog, View view) {
        this.target = signShareDialog;
        View findRequiredView = Utils.findRequiredView(view, R.id.close_btn, "field 'mCloseBtn' and method 'onViewClicked'");
        signShareDialog.mCloseBtn = (ImageView) Utils.castView(findRequiredView, R.id.close_btn, "field 'mCloseBtn'", ImageView.class);
        this.view2131493084 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signShareDialog.onViewClicked(view);
            }
        });
        signShareDialog.mSignIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.sign_icon, "field 'mSignIcon'", ImageView.class);
        signShareDialog.mSignContent = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_content, "field 'mSignContent'", TextView.class);
        signShareDialog.mUserIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.user_icon, "field 'mUserIcon'", CircleImageView.class);
        signShareDialog.mUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.user_name, "field 'mUserName'", TextView.class);
        signShareDialog.mUserGroupName = (TextView) Utils.findRequiredViewAsType(view, R.id.user_group_name, "field 'mUserGroupName'", TextView.class);
        signShareDialog.mSignDay1 = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_day_1, "field 'mSignDay1'", TextView.class);
        signShareDialog.mSignDay2 = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_day_2, "field 'mSignDay2'", TextView.class);
        signShareDialog.mSignDay3 = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_day_3, "field 'mSignDay3'", TextView.class);
        signShareDialog.mSharePic = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.share_pic, "field 'mSharePic'", LinearLayout.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.download, "method 'onViewClicked'");
        this.view2131493209 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signShareDialog.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        SignShareDialog signShareDialog = this.target;
        if (signShareDialog != null) {
            this.target = null;
            signShareDialog.mCloseBtn = null;
            signShareDialog.mSignIcon = null;
            signShareDialog.mSignContent = null;
            signShareDialog.mUserIcon = null;
            signShareDialog.mUserName = null;
            signShareDialog.mUserGroupName = null;
            signShareDialog.mSignDay1 = null;
            signShareDialog.mSignDay2 = null;
            signShareDialog.mSignDay3 = null;
            signShareDialog.mSharePic = null;
            this.view2131493084.setOnClickListener((View.OnClickListener) null);
            this.view2131493084 = null;
            this.view2131493209.setOnClickListener((View.OnClickListener) null);
            this.view2131493209 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
