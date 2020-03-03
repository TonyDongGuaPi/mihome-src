package com.xiaomi.smarthome.operation.js_sdk.base;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadActivity;

public class CloudVideoJavaScriptInterface extends BaseJSInterface {
    public CloudVideoJavaScriptInterface(Activity activity, CommonWebView commonWebView) {
        super(activity, commonWebView);
    }

    @JavascriptInterface
    public void openDownloadList() {
        Activity activity = (Activity) this.f21058a.get();
        CommonWebView commonWebView = (CommonWebView) this.b.get();
        if (activity != null && commonWebView != null) {
            Intent intent = new Intent(activity, CloudVideoDownloadActivity.class);
            String did = commonWebView.getDid();
            intent.putExtra("did", did);
            Device b = SmartHomeDeviceManager.a().b(did);
            if (b != null) {
                intent.putExtra("uid", b.userId);
                activity.startActivity(intent);
            }
        }
    }
}
