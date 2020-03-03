package com.xiaomi.smarthome.homeroom.initdevice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.CommonFlowGroup;

public class InitDeviceNameActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private InitDeviceNameActivity f18280a;

    @UiThread
    public InitDeviceNameActivity_ViewBinding(InitDeviceNameActivity initDeviceNameActivity) {
        this(initDeviceNameActivity, initDeviceNameActivity.getWindow().getDecorView());
    }

    @UiThread
    public InitDeviceNameActivity_ViewBinding(InitDeviceNameActivity initDeviceNameActivity, View view) {
        this.f18280a = initDeviceNameActivity;
        initDeviceNameActivity.mTitleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'mTitleBar'");
        initDeviceNameActivity.mReturnBtn = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'mReturnBtn'");
        initDeviceNameActivity.mTitleTv = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleTv'", TextView.class);
        initDeviceNameActivity.mDeviceImg = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.device_img, "field 'mDeviceImg'", SimpleDraweeView.class);
        initDeviceNameActivity.mGoNextBtn = (Button) Utils.findRequiredViewAsType(view, R.id.go_next, "field 'mGoNextBtn'", Button.class);
        initDeviceNameActivity.mDeviceNameEt = (EditText) Utils.findRequiredViewAsType(view, R.id.device_name, "field 'mDeviceNameEt'", EditText.class);
        initDeviceNameActivity.mInputClearBtn = Utils.findRequiredView(view, R.id.input_clear, "field 'mInputClearBtn'");
        initDeviceNameActivity.mDeviceNameTips = (TextView) Utils.findRequiredViewAsType(view, R.id.device_name_tips, "field 'mDeviceNameTips'", TextView.class);
        initDeviceNameActivity.mRecommendTagFlow = (CommonFlowGroup) Utils.findRequiredViewAsType(view, R.id.recommend_name_tag, "field 'mRecommendTagFlow'", CommonFlowGroup.class);
        initDeviceNameActivity.mDivider = Utils.findRequiredView(view, R.id.divider, "field 'mDivider'");
    }

    @CallSuper
    public void unbind() {
        InitDeviceNameActivity initDeviceNameActivity = this.f18280a;
        if (initDeviceNameActivity != null) {
            this.f18280a = null;
            initDeviceNameActivity.mTitleBar = null;
            initDeviceNameActivity.mReturnBtn = null;
            initDeviceNameActivity.mTitleTv = null;
            initDeviceNameActivity.mDeviceImg = null;
            initDeviceNameActivity.mGoNextBtn = null;
            initDeviceNameActivity.mDeviceNameEt = null;
            initDeviceNameActivity.mInputClearBtn = null;
            initDeviceNameActivity.mDeviceNameTips = null;
            initDeviceNameActivity.mRecommendTagFlow = null;
            initDeviceNameActivity.mDivider = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
