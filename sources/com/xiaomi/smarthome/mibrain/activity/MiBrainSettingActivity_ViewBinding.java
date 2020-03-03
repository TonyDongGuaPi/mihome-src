package com.xiaomi.smarthome.mibrain.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;

public class MiBrainSettingActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private MiBrainSettingActivity f10625a;

    @UiThread
    public MiBrainSettingActivity_ViewBinding(MiBrainSettingActivity miBrainSettingActivity) {
        this(miBrainSettingActivity, miBrainSettingActivity.getWindow().getDecorView());
    }

    @UiThread
    public MiBrainSettingActivity_ViewBinding(MiBrainSettingActivity miBrainSettingActivity, View view) {
        this.f10625a = miBrainSettingActivity;
        miBrainSettingActivity.mWebView = (WebView) Utils.findRequiredViewAsType(view, R.id.web_view, "field 'mWebView'", WebView.class);
        miBrainSettingActivity.mSwitchBtn = (SwitchButton) Utils.findRequiredViewAsType(view, R.id.mi_brain_open_permission, "field 'mSwitchBtn'", SwitchButton.class);
    }

    @CallSuper
    public void unbind() {
        MiBrainSettingActivity miBrainSettingActivity = this.f10625a;
        if (miBrainSettingActivity != null) {
            this.f10625a = null;
            miBrainSettingActivity.mWebView = null;
            miBrainSettingActivity.mSwitchBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
