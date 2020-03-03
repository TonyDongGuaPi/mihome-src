package com.xiaomi.smarthome.device;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.choosedevice.ScanDeviceProgressBar;

public class ScanChooseBluetoothDevice_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ScanChooseBluetoothDevice f14972a;

    @UiThread
    public ScanChooseBluetoothDevice_ViewBinding(ScanChooseBluetoothDevice scanChooseBluetoothDevice) {
        this(scanChooseBluetoothDevice, scanChooseBluetoothDevice.getWindow().getDecorView());
    }

    @UiThread
    public ScanChooseBluetoothDevice_ViewBinding(ScanChooseBluetoothDevice scanChooseBluetoothDevice, View view) {
        this.f14972a = scanChooseBluetoothDevice;
        scanChooseBluetoothDevice.mScanFailView = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.scan_fail_view, "field 'mScanFailView'", RelativeLayout.class);
        scanChooseBluetoothDevice.mCannotFindDeviceTx = (TextView) Utils.findRequiredViewAsType(view, R.id.cannot_find_device, "field 'mCannotFindDeviceTx'", TextView.class);
        scanChooseBluetoothDevice.mRescanBt = (Button) Utils.findRequiredViewAsType(view, R.id.scan_again, "field 'mRescanBt'", Button.class);
        scanChooseBluetoothDevice.mScanDescText = (TextView) Utils.findRequiredViewAsType(view, R.id.scan_desc, "field 'mScanDescText'", TextView.class);
        scanChooseBluetoothDevice.mProgressBar = (ScanDeviceProgressBar) Utils.findRequiredViewAsType(view, R.id.progress_bar, "field 'mProgressBar'", ScanDeviceProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        ScanChooseBluetoothDevice scanChooseBluetoothDevice = this.f14972a;
        if (scanChooseBluetoothDevice != null) {
            this.f14972a = null;
            scanChooseBluetoothDevice.mScanFailView = null;
            scanChooseBluetoothDevice.mCannotFindDeviceTx = null;
            scanChooseBluetoothDevice.mRescanBt = null;
            scanChooseBluetoothDevice.mScanDescText = null;
            scanChooseBluetoothDevice.mProgressBar = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
