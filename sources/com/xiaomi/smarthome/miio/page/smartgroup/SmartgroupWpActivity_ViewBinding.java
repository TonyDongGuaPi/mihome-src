package com.xiaomi.smarthome.miio.page.smartgroup;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class SmartgroupWpActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SmartgroupWpActivity f19907a;

    @UiThread
    public SmartgroupWpActivity_ViewBinding(SmartgroupWpActivity smartgroupWpActivity) {
        this(smartgroupWpActivity, smartgroupWpActivity.getWindow().getDecorView());
    }

    @UiThread
    public SmartgroupWpActivity_ViewBinding(SmartgroupWpActivity smartgroupWpActivity, View view) {
        this.f19907a = smartgroupWpActivity;
        smartgroupWpActivity.mIvTitleReturn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mIvTitleReturn'", ImageView.class);
        smartgroupWpActivity.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTvTitle'", TextView.class);
        smartgroupWpActivity.mIvShare = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_more_more_btn, "field 'mIvShare'", ImageView.class);
        smartgroupWpActivity.mIvWapWork = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_wap_work, "field 'mIvWapWork'", ImageView.class);
        smartgroupWpActivity.mTvAddress = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_address, "field 'mTvAddress'", TextView.class);
        smartgroupWpActivity.mTvWapTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_wap_title, "field 'mTvWapTitle'", TextView.class);
        smartgroupWpActivity.mTvWpDataMax = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_wp_data_max, "field 'mTvWpDataMax'", TextView.class);
        smartgroupWpActivity.mTvWpDataMin = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_wp_data_min, "field 'mTvWpDataMin'", TextView.class);
        smartgroupWpActivity.mTvSource = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_data_source, "field 'mTvSource'", TextView.class);
        smartgroupWpActivity.mViewShare = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.view_share, "field 'mViewShare'", LinearLayout.class);
        smartgroupWpActivity.mLvItems = (FakeListView) Utils.findRequiredViewAsType(view, R.id.lv_items, "field 'mLvItems'", FakeListView.class);
    }

    @CallSuper
    public void unbind() {
        SmartgroupWpActivity smartgroupWpActivity = this.f19907a;
        if (smartgroupWpActivity != null) {
            this.f19907a = null;
            smartgroupWpActivity.mIvTitleReturn = null;
            smartgroupWpActivity.mTvTitle = null;
            smartgroupWpActivity.mIvShare = null;
            smartgroupWpActivity.mIvWapWork = null;
            smartgroupWpActivity.mTvAddress = null;
            smartgroupWpActivity.mTvWapTitle = null;
            smartgroupWpActivity.mTvWpDataMax = null;
            smartgroupWpActivity.mTvWpDataMin = null;
            smartgroupWpActivity.mTvSource = null;
            smartgroupWpActivity.mViewShare = null;
            smartgroupWpActivity.mLvItems = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
