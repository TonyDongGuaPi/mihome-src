package com.xiaomi.smarthome.miio.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class ConsumablesActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ConsumablesActivity f11731a;

    @UiThread
    public ConsumablesActivity_ViewBinding(ConsumablesActivity consumablesActivity) {
        this(consumablesActivity, consumablesActivity.getWindow().getDecorView());
    }

    @UiThread
    public ConsumablesActivity_ViewBinding(ConsumablesActivity consumablesActivity, View view) {
        this.f11731a = consumablesActivity;
        consumablesActivity.mPullRefreshLL = (PtrFrameLayout) Utils.findRequiredViewAsType(view, R.id.pull_down_refresh, "field 'mPullRefreshLL'", PtrFrameLayout.class);
        consumablesActivity.list = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.consumables_list, "field 'list'", RecyclerView.class);
        consumablesActivity.mEmptyView = Utils.findRequiredView(view, R.id.common_white_empty_view, "field 'mEmptyView'");
        consumablesActivity.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitle'", TextView.class);
        consumablesActivity.mGroupTitle = Utils.findRequiredView(view, R.id.title_group, "field 'mGroupTitle'");
        consumablesActivity.mMenuIcom = Utils.findRequiredView(view, R.id.home_change_icon, "field 'mMenuIcom'");
        consumablesActivity.mTitleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'mTitleBar'");
        consumablesActivity.mEmptyTV = (TextView) Utils.findRequiredViewAsType(view, R.id.common_white_empty_text, "field 'mEmptyTV'", TextView.class);
        consumablesActivity.mMaskView = Utils.findRequiredView(view, R.id.mask, "field 'mMaskView'");
    }

    @CallSuper
    public void unbind() {
        ConsumablesActivity consumablesActivity = this.f11731a;
        if (consumablesActivity != null) {
            this.f11731a = null;
            consumablesActivity.mPullRefreshLL = null;
            consumablesActivity.list = null;
            consumablesActivity.mEmptyView = null;
            consumablesActivity.mTitle = null;
            consumablesActivity.mGroupTitle = null;
            consumablesActivity.mMenuIcom = null;
            consumablesActivity.mTitleBar = null;
            consumablesActivity.mEmptyTV = null;
            consumablesActivity.mMaskView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
