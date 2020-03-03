package com.xiaomi.smarthome.operation.js_sdk.lifecycle;

import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.operation.js_sdk.CommonWebViewFragment;
import com.xiaomi.smarthome.operation.js_sdk.utils.JsSdkUtils;

public class WebViewLifeCycleDispatcher {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21082a = "LifeCycleDispatcher";

    public void a(LifeCycleEvent lifeCycleEvent, CommonWebViewFragment commonWebViewFragment, String str) {
        if (commonWebViewFragment == null) {
            LogUtil.b(f21082a, "dispatchEvent: target is null");
            return;
        }
        String str2 = lifeCycleEvent.mActionName;
        if (lifeCycleEvent == LifeCycleEvent.PAGE_RESUME) {
            JsSdkUtils.b(commonWebViewFragment.getWebView(), "smartHome.dispatchEvent", str2, str);
            return;
        }
        JsSdkUtils.b(commonWebViewFragment.getWebView(), "smartHome.dispatchEvent", str2);
    }
}
