package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter;

import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.sdk.R;

public class RoidmiBtFmFilter extends BluetoothDeviceFilter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14284a = XMStringUtils.a(CoreService.getAppContext(), R.string.roidmi_car_bluetooth_player);

    public String b() {
        return DeviceFactory.t;
    }

    private RoidmiBtFmFilter() {
    }

    public static RoidmiBtFmFilter a() {
        return new RoidmiBtFmFilter();
    }

    public boolean a(BluetoothSearchResult bluetoothSearchResult) {
        return f14284a.equalsIgnoreCase(bluetoothSearchResult.k());
    }
}
