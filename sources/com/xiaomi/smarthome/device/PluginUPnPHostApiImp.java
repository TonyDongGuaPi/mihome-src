package com.xiaomi.smarthome.device;

import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.KeyValuePair;
import com.xiaomi.smarthome.frame.plugin.host.PluginUPnPHostApi;
import java.util.List;

public class PluginUPnPHostApiImp extends PluginUPnPHostApi {

    /* renamed from: a  reason: collision with root package name */
    private static final Byte[] f1513a = new Byte[0];

    public PluginUPnPHostApiImp() {
        UPnPDeviceHelper.a();
        MiWiFiDownloadApiImp.a();
    }

    public void invokeServiceAction(String str, String str2, String str3, List<KeyValuePair> list, Callback<String> callback) {
        UPnPDeviceHelper.a().a(str, str2, str3, list, callback);
    }

    public String getRootNodeValue(String str, String str2) {
        return UPnPDeviceHelper.a().a(str, str2);
    }
}
