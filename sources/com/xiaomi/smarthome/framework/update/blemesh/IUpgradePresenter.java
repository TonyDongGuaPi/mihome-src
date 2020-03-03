package com.xiaomi.smarthome.framework.update.blemesh;

import android.text.TextUtils;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;

public abstract class IUpgradePresenter {
    protected static final int b = 2;
    /* access modifiers changed from: protected */

    /* renamed from: a  reason: collision with root package name */
    public int f17781a;

    public abstract String a();

    public abstract String b();

    public abstract String c();

    public abstract String d();

    public abstract boolean e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    /* access modifiers changed from: protected */
    public void a(final String str, final Response.FirmwareUpgradeResponse firmwareUpgradeResponse) {
        XmPluginHostApi.instance().downloadFirmware(str, new Response.FirmwareUpgradeResponse() {
            public void onResponse(int i, String str, String str2) {
                if (i == 0 && !TextUtils.isEmpty(str)) {
                    firmwareUpgradeResponse.onResponse(i, str, str2);
                    IUpgradePresenter.this.f17781a = 0;
                } else if (IUpgradePresenter.this.f17781a == 2) {
                    firmwareUpgradeResponse.onResponse(i, str, str2);
                    IUpgradePresenter.this.f17781a = 0;
                } else {
                    IUpgradePresenter.this.f17781a++;
                    IUpgradePresenter.this.a(str, firmwareUpgradeResponse);
                }
            }

            public void onProgress(int i) {
                firmwareUpgradeResponse.onProgress(i);
            }
        });
    }
}
