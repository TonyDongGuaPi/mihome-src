package com.xiaomi.smarthome.auth;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.ListViewWithFixedHeight;

public class AuthManagerDetailActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private AuthManagerDetailActivity f13822a;

    @UiThread
    public AuthManagerDetailActivity_ViewBinding(AuthManagerDetailActivity authManagerDetailActivity) {
        this(authManagerDetailActivity, authManagerDetailActivity.getWindow().getDecorView());
    }

    @UiThread
    public AuthManagerDetailActivity_ViewBinding(AuthManagerDetailActivity authManagerDetailActivity, View view) {
        this.f13822a = authManagerDetailActivity;
        authManagerDetailActivity.mTitleTV = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleTV'", TextView.class);
        authManagerDetailActivity.mBackIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mBackIV'", ImageView.class);
        authManagerDetailActivity.mListView = (ListViewWithFixedHeight) Utils.findRequiredViewAsType(view, R.id.auth_manager_list_lv, "field 'mListView'", ListViewWithFixedHeight.class);
        authManagerDetailActivity.mDeleteAuth = (TextView) Utils.findRequiredViewAsType(view, R.id.delete_auth, "field 'mDeleteAuth'", TextView.class);
        authManagerDetailActivity.mAppNameTV = (TextView) Utils.findRequiredViewAsType(view, R.id.name, "field 'mAppNameTV'", TextView.class);
        authManagerDetailActivity.mAppAuthTimeTV = (TextView) Utils.findRequiredViewAsType(view, R.id.auth_time, "field 'mAppAuthTimeTV'", TextView.class);
        authManagerDetailActivity.mAppIcon = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.image, "field 'mAppIcon'", SimpleDraweeView.class);
    }

    @CallSuper
    public void unbind() {
        AuthManagerDetailActivity authManagerDetailActivity = this.f13822a;
        if (authManagerDetailActivity != null) {
            this.f13822a = null;
            authManagerDetailActivity.mTitleTV = null;
            authManagerDetailActivity.mBackIV = null;
            authManagerDetailActivity.mListView = null;
            authManagerDetailActivity.mDeleteAuth = null;
            authManagerDetailActivity.mAppNameTV = null;
            authManagerDetailActivity.mAppAuthTimeTV = null;
            authManagerDetailActivity.mAppIcon = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
