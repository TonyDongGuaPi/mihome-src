package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter;

import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.sdk.R;

public class RoidmiBtFmV2Filter extends BluetoothDeviceFilter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14285a = XMStringUtils.a(CoreService.getAppContext(), R.string.roidmi_car_bluetooth_player_v2);

    public String b() {
        return DeviceFactory.u;
    }

    private RoidmiBtFmV2Filter() {
    }

    public static RoidmiBtFmV2Filter a() {
        return new RoidmiBtFmV2Filter();
    }

    public boolean a(BluetoothSearchResult bluetoothSearchResult) {
        return f14285a.equalsIgnoreCase(bluetoothSearchResult.k());
    }
}
