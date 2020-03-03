package com.xiaomi.smarthome.device.renderer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ViewUtils;

public class BleDeviceRenderer extends DeviceRenderer {
    public void a() {
    }

    /* access modifiers changed from: protected */
    public void a(Context context, MLAlertDialog mLAlertDialog, String str) {
        mLAlertDialog.setView(ViewUtils.b(context, mLAlertDialog));
    }

    public Intent a(Device device, Context context, Bundle bundle, boolean z, DeviceRenderer.LoadingCallback loadingCallback) {
        Log.i("stopScan", "render stop");
        BluetoothHelper.b();
        BluetoothHelper.b(device.mac);
        BleDevice d = BLEDeviceManager.d(device.mac);
        if (d != null) {
            d.b(false);
        }
        if (TextUtils.isEmpty(device.ownerId)) {
            device.ownerId = BleCacheUtils.k(device.mac);
        }
        if (!device.isOnline) {
            return super.a(device, context, bundle, z, (DeviceRenderer.LoadingCallback) null);
        }
        if (CoreApi.a().c(device.model)) {
            return super.a(device, context, bundle, z, (DeviceRenderer.LoadingCallback) null);
        }
        return super.a(device, context, bundle, z, (DeviceRenderer.LoadingCallback) null);
    }

    /* access modifiers changed from: protected */
    public boolean a(Context context, Device device, Handler handler) {
        return super.a(context, device, handler);
    }
}
