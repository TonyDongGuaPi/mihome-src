package com.xiaomi.smarthome.mitsmsdk;

import android.content.Context;
import com.miui.tsmclient.util.IDeviceInfo;
import java.util.Collections;
import java.util.List;

public class DeviceInfoImpl implements IDeviceInfo {
    public boolean isESEEnabled(Context context) {
        return true;
    }

    public String getDeviceId(Context context) {
        return NfcChannelManager.a().c();
    }

    public String getDeviceModel() {
        return NfcChannelManager.a().b();
    }

    public List<String> getSIMNumber() {
        return Collections.emptyList();
    }
}
