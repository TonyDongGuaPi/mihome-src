package com.p2p.play;

import com.xiaomi.smarthome.camera.XmCameraP2p;
import com.xiaomi.smarthome.camera.XmP2PInfo;
import com.xiaomi.smarthome.device.api.BaseDevice;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;

public class P2pCameraDevice extends BaseDevice {

    /* renamed from: a  reason: collision with root package name */
    private XmCameraP2p f8523a = null;
    /* access modifiers changed from: private */
    public XmP2PInfo b = null;
    /* access modifiers changed from: private */
    public String c;

    public P2pCameraDevice(DeviceStat deviceStat) {
        super(deviceStat);
    }

    public XmCameraP2p a() {
        if (this.f8523a == null) {
            this.f8523a = XmPluginHostApi.instance().createCameraP2p(this.b, 1);
        }
        return this.f8523a;
    }

    public XmP2PInfo b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.b != null ? this.b.getPwd() : "";
    }

    public void a(final Callback<Void> callback) {
        XmPluginHostApi.instance().updateP2pPwd(deviceStat(), 1, new Callback<XmP2PInfo>() {
            /* renamed from: a */
            public void onSuccess(XmP2PInfo xmP2PInfo) {
                XmP2PInfo unused = P2pCameraDevice.this.b = xmP2PInfo;
                String unused2 = P2pCameraDevice.this.c = P2pCameraDevice.this.b.getUid();
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        });
    }
}
