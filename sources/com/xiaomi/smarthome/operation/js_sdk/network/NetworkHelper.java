package com.xiaomi.smarthome.operation.js_sdk.network;

import android.annotation.SuppressLint;
import com.xiaomi.smarthome.operation.js_sdk.base.CommonWebView;
import com.xiaomi.smarthome.operation.js_sdk.network.NetWorkObserver;
import com.xiaomi.smarthome.operation.js_sdk.utils.JsSdkUtils;
import java.lang.ref.WeakReference;

public class NetworkHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21098a = "NetworkHelper";
    private final NetWorkObserver b = new NetWorkObserver();
    private WeakReference<CommonWebView> c = null;
    private NetState d = null;
    @SuppressLint({"CheckResult"})
    private final NetWorkObserver.OnNetChangedListener e = new NetWorkObserver.OnNetChangedListener() {
        public final void onNetChanged(NetState netState) {
            NetworkHelper.this.a(netState);
        }
    };

    /* access modifiers changed from: private */
    public /* synthetic */ void a(NetState netState) {
        if (netState != this.d) {
            this.d = netState;
            JsSdkUtils.b((CommonWebView) this.c.get(), "smartHome.dispatchEvent", "networkChange", netState.c().toString());
        }
    }

    public NetworkHelper(CommonWebView commonWebView) {
        this.c = new WeakReference<>(commonWebView);
        this.b.a(this.e);
    }

    public void a() {
        this.b.b();
    }

    public NetState b() {
        return this.b.a();
    }
}
