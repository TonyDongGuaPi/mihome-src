package com.xiaomi.smarthome.device.bluetooth.receiver;

import android.content.Context;
import android.content.Intent;
import java.util.List;

public interface IBluetoothReceiver {
    List<String> a();

    boolean a(Context context, Intent intent);
}
