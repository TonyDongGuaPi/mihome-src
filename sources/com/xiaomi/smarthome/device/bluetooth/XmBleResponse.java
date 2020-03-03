package com.xiaomi.smarthome.device.bluetooth;

import android.os.Bundle;
import com.xiaomi.smarthome.bluetooth.Response;

public class XmBleResponse implements Response.BleResponse<Bundle> {

    /* renamed from: a  reason: collision with root package name */
    public Response.BleResponse f15116a;

    public XmBleResponse(Response.BleResponse bleResponse) {
        this.f15116a = bleResponse;
    }

    /* renamed from: a */
    public void onResponse(int i, Bundle bundle) {
        if (this.f15116a != null) {
            if (this.f15116a instanceof Response.BleConnectResponse) {
                this.f15116a.onResponse(i, bundle);
                return;
            }
            byte[] bArr = null;
            if (this.f15116a instanceof Response.BleReadResponse) {
                Response.BleResponse bleResponse = this.f15116a;
                if (bundle != null) {
                    bArr = bundle.getByteArray("key_bytes");
                }
                bleResponse.onResponse(i, bArr);
            } else if (this.f15116a instanceof Response.BleReadRssiResponse) {
                this.f15116a.onResponse(i, Integer.valueOf(bundle != null ? bundle.getInt("key_rssi") : 0));
            } else {
                this.f15116a.onResponse(i, null);
            }
        }
    }
}
