package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter;

import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.sdk.R;

public class RoidmiBtFmV3Filter extends BluetoothDeviceFilter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14286a = XMStringUtils.a(CoreService.getAppContext(), R.string.roidmi_car_bluetooth_player_v3);

    public String b() {
        return DeviceFactory.u;
    }

    private RoidmiBtFmV3Filter() {
    }

    public static RoidmiBtFmV3Filter a() {
        return new RoidmiBtFmV3Filter();
    }

    public boolean a(BluetoothSearchResult bluetoothSearchResult) {
        return f14286a.equalsIgnoreCase(bluetoothSearchResult.k());
    }
}
