package com.xiaomi.smarthome.mibrain.anothernamesetting;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class AnotherNameDevListActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private AnotherNameDevListActivity f10633a;

    @UiThread
    public AnotherNameDevListActivity_ViewBinding(AnotherNameDevListActivity anotherNameDevListActivity) {
        this(anotherNameDevListActivity, anotherNameDevListActivity.getWindow().getDecorView());
    }

    @UiThread
    public AnotherNameDevListActivity_ViewBinding(AnotherNameDevListActivity anotherNameDevListActivity, View view) {
        this.f10633a = anotherNameDevListActivity;
        anotherNameDevListActivity.mReturn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mReturn'", ImageView.class);
        anotherNameDevListActivity.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitle'", TextView.class);
        anotherNameDevListActivity.mTitleRightIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_right_iv_setting_btn, "field 'mTitleRightIcon'", ImageView.class);
        anotherNameDevListActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerview, "field 'mRecyclerView'", RecyclerView.class);
        anotherNameDevListActivity.mIntroduction = (TextView) Utils.findRequiredViewAsType(view, R.id.id_introduction, "field 'mIntroduction'", TextView.class);
        anotherNameDevListActivity.mGuide = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.guide, "field 'mGuide'", LinearLayout.class);
        anotherNameDevListActivity.mGuideCancel = (ImageView) Utils.findRequiredViewAsType(view, R.id.guide_cancel, "field 'mGuideCancel'", ImageView.class);
        anotherNameDevListActivity.mEmpty = (TextView) Utils.findRequiredViewAsType(view, R.id.empty, "field 'mEmpty'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        AnotherNameDevListActivity anotherNameDevListActivity = this.f10633a;
        if (anotherNameDevListActivity != null) {
            this.f10633a = null;
            anotherNameDevListActivity.mReturn = null;
            anotherNameDevListActivity.mTitle = null;
            anotherNameDevListActivity.mTitleRightIcon = null;
            anotherNameDevListActivity.mRecyclerView = null;
            anotherNameDevListActivity.mIntroduction = null;
            anotherNameDevListActivity.mGuide = null;
            anotherNameDevListActivity.mGuideCancel = null;
            anotherNameDevListActivity.mEmpty = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
