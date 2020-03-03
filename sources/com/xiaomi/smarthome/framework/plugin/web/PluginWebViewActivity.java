package com.xiaomi.smarthome.framework.plugin.web;

import android.content.Context;
import android.os.Bundle;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginWebViewActivity extends BaseActivity implements Device.StateChangedListener {
    public static final String ARGS_KEY_DID = "did";

    /* renamed from: a  reason: collision with root package name */
    private static final String f17650a = "file://";
    Context mContext;
    Device mDevice;
    PluginWebView mPluginWebView;

    public void onStateChanged(Device device) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        Device b = SmartHomeDeviceManager.a().b(getIntent().getStringExtra("did"));
        if (b == null) {
            finish();
            return;
        }
        this.mDevice = b;
        if (CoreApi.a().d(this.mDevice.model) == null) {
            finish();
            return;
        }
        setContentView(R.layout.plugin_webview_activity);
        this.mPluginWebView = (PluginWebView) findViewById(R.id.plugin_webview);
        try {
            new JSONObject().put("device", this.mDevice.toJSON());
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mDevice.addStateChangedListener(this);
        if (this.mPluginWebView != null) {
            this.mPluginWebView.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mDevice.removeStateChangedListener(this);
        if (this.mPluginWebView != null) {
            this.mPluginWebView.onPause();
        }
    }
}
