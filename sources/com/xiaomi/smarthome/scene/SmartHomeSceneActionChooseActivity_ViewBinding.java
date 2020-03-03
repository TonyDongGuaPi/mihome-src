package com.xiaomi.smarthome.scene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.ExpandableItemIndicator;

public class SmartHomeSceneActionChooseActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SmartHomeSceneActionChooseActivity f21363a;
    private View b;

    @UiThread
    public SmartHomeSceneActionChooseActivity_ViewBinding(SmartHomeSceneActionChooseActivity smartHomeSceneActionChooseActivity) {
        this(smartHomeSceneActionChooseActivity, smartHomeSceneActionChooseActivity.getWindow().getDecorView());
    }

    @UiThread
    public SmartHomeSceneActionChooseActivity_ViewBinding(final SmartHomeSceneActionChooseActivity smartHomeSceneActionChooseActivity, View view) {
        this.f21363a = smartHomeSceneActionChooseActivity;
        smartHomeSceneActionChooseActivity.mModuleA3ReturnTransparentTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mModuleA3ReturnTransparentTitle'", TextView.class);
        smartHomeSceneActionChooseActivity.mContentListView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.content_list_view, "field 'mContentListView'", RecyclerView.class);
        smartHomeSceneActionChooseActivity.topFilterLayout = Utils.findRequiredView(view, R.id.top_filter_item, "field 'topFilterLayout'");
        smartHomeSceneActionChooseActivity.topDeviceTitleLayout = Utils.findRequiredView(view, R.id.top_device_title_item, "field 'topDeviceTitleLayout'");
        smartHomeSceneActionChooseActivity.mBuyView = Utils.findRequiredView(view, R.id.buy_empty_view, "field 'mBuyView'");
        smartHomeSceneActionChooseActivity.mBuyButton = (Button) Utils.findRequiredViewAsType(view, R.id.btn_see_now, "field 'mBuyButton'", Button.class);
        smartHomeSceneActionChooseActivity.mTitleBarFL = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'mTitleBarFL'", RelativeLayout.class);
        smartHomeSceneActionChooseActivity.mItemIndicator = (ExpandableItemIndicator) Utils.findRequiredViewAsType(view, R.id.btn_expand_indicator, "field 'mItemIndicator'", ExpandableItemIndicator.class);
        smartHomeSceneActionChooseActivity.mTitleTopTV = (TextView) Utils.findRequiredViewAsType(view, R.id.choose_action_title_top, "field 'mTitleTopTV'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "method 'close'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                smartHomeSceneActionChooseActivity.close();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SmartHomeSceneActionChooseActivity smartHomeSceneActionChooseActivity = this.f21363a;
        if (smartHomeSceneActionChooseActivity != null) {
            this.f21363a = null;
            smartHomeSceneActionChooseActivity.mModuleA3ReturnTransparentTitle = null;
            smartHomeSceneActionChooseActivity.mContentListView = null;
            smartHomeSceneActionChooseActivity.topFilterLayout = null;
            smartHomeSceneActionChooseActivity.topDeviceTitleLayout = null;
            smartHomeSceneActionChooseActivity.mBuyView = null;
            smartHomeSceneActionChooseActivity.mBuyButton = null;
            smartHomeSceneActionChooseActivity.mTitleBarFL = null;
            smartHomeSceneActionChooseActivity.mItemIndicator = null;
            smartHomeSceneActionChooseActivity.mTitleTopTV = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
