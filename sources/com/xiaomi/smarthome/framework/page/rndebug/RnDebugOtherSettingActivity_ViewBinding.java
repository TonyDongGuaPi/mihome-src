package com.xiaomi.smarthome.framework.page.rndebug;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;

public class RnDebugOtherSettingActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private RnDebugOtherSettingActivity f16966a;

    @UiThread
    public RnDebugOtherSettingActivity_ViewBinding(RnDebugOtherSettingActivity rnDebugOtherSettingActivity) {
        this(rnDebugOtherSettingActivity, rnDebugOtherSettingActivity.getWindow().getDecorView());
    }

    @UiThread
    public RnDebugOtherSettingActivity_ViewBinding(RnDebugOtherSettingActivity rnDebugOtherSettingActivity, View view) {
        this.f16966a = rnDebugOtherSettingActivity;
        rnDebugOtherSettingActivity.viewHeadLeft = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'viewHeadLeft'");
        rnDebugOtherSettingActivity.tvHeadTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'tvHeadTitle'", TextView.class);
        rnDebugOtherSettingActivity.mSwBtnRnPluginTimeToal = (SwitchButton) Utils.findRequiredViewAsType(view, R.id.swb_rn_plugin_time_total, "field 'mSwBtnRnPluginTimeToal'", SwitchButton.class);
        rnDebugOtherSettingActivity.mSwBtnRnPluginSdkVersionInfo = (SwitchButton) Utils.findRequiredViewAsType(view, R.id.swb_rn_plugin_sdk_version_info, "field 'mSwBtnRnPluginSdkVersionInfo'", SwitchButton.class);
        rnDebugOtherSettingActivity.mSwBtnUseOldPluginOnly = (SwitchButton) Utils.findRequiredViewAsType(view, R.id.swb_use_old_plugin_only, "field 'mSwBtnUseOldPluginOnly'", SwitchButton.class);
        rnDebugOtherSettingActivity.mSwBtnUsePreviewAppConfig = (SwitchButton) Utils.findRequiredViewAsType(view, R.id.swb_use_preview_app_config, "field 'mSwBtnUsePreviewAppConfig'", SwitchButton.class);
    }

    @CallSuper
    public void unbind() {
        RnDebugOtherSettingActivity rnDebugOtherSettingActivity = this.f16966a;
        if (rnDebugOtherSettingActivity != null) {
            this.f16966a = null;
            rnDebugOtherSettingActivity.viewHeadLeft = null;
            rnDebugOtherSettingActivity.tvHeadTitle = null;
            rnDebugOtherSettingActivity.mSwBtnRnPluginTimeToal = null;
            rnDebugOtherSettingActivity.mSwBtnRnPluginSdkVersionInfo = null;
            rnDebugOtherSettingActivity.mSwBtnUseOldPluginOnly = null;
            rnDebugOtherSettingActivity.mSwBtnUsePreviewAppConfig = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
