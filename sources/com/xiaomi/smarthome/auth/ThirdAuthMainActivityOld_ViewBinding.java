package com.xiaomi.smarthome.auth;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.ListViewWithFixedHeight;

public class ThirdAuthMainActivityOld_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ThirdAuthMainActivityOld f13865a;

    @UiThread
    public ThirdAuthMainActivityOld_ViewBinding(ThirdAuthMainActivityOld thirdAuthMainActivityOld) {
        this(thirdAuthMainActivityOld, thirdAuthMainActivityOld.getWindow().getDecorView());
    }

    @UiThread
    public ThirdAuthMainActivityOld_ViewBinding(ThirdAuthMainActivityOld thirdAuthMainActivityOld, View view) {
        this.f13865a = thirdAuthMainActivityOld;
        thirdAuthMainActivityOld.mAppIconIV = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.app_icon, "field 'mAppIconIV'", SimpleDraweeView.class);
        thirdAuthMainActivityOld.mAppNameTV = (TextView) Utils.findRequiredViewAsType(view, R.id.app_name, "field 'mAppNameTV'", TextView.class);
        thirdAuthMainActivityOld.mAppDescTV = (TextView) Utils.findRequiredViewAsType(view, R.id.app_desc, "field 'mAppDescTV'", TextView.class);
        thirdAuthMainActivityOld.mAuthDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.auth_desc, "field 'mAuthDesc'", TextView.class);
        thirdAuthMainActivityOld.mListView = (ListViewWithFixedHeight) Utils.findRequiredViewAsType(view, R.id.auto_lv, "field 'mListView'", ListViewWithFixedHeight.class);
        thirdAuthMainActivityOld.mAuthCancelTV = (TextView) Utils.findRequiredViewAsType(view, R.id.auth_cancel, "field 'mAuthCancelTV'", TextView.class);
        thirdAuthMainActivityOld.mAuthAgreeTV = (TextView) Utils.findRequiredViewAsType(view, R.id.auth_agree, "field 'mAuthAgreeTV'", TextView.class);
        thirdAuthMainActivityOld.mTitleTV = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleTV'", TextView.class);
        thirdAuthMainActivityOld.mBackIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mBackIV'", ImageView.class);
        thirdAuthMainActivityOld.mSelectAll = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ckb_select_all, "field 'mSelectAll'", CheckBox.class);
    }

    @CallSuper
    public void unbind() {
        ThirdAuthMainActivityOld thirdAuthMainActivityOld = this.f13865a;
        if (thirdAuthMainActivityOld != null) {
            this.f13865a = null;
            thirdAuthMainActivityOld.mAppIconIV = null;
            thirdAuthMainActivityOld.mAppNameTV = null;
            thirdAuthMainActivityOld.mAppDescTV = null;
            thirdAuthMainActivityOld.mAuthDesc = null;
            thirdAuthMainActivityOld.mListView = null;
            thirdAuthMainActivityOld.mAuthCancelTV = null;
            thirdAuthMainActivityOld.mAuthAgreeTV = null;
            thirdAuthMainActivityOld.mTitleTV = null;
            thirdAuthMainActivityOld.mBackIV = null;
            thirdAuthMainActivityOld.mSelectAll = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
