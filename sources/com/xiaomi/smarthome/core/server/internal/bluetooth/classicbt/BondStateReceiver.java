package com.xiaomi.smarthome.core.server.internal.bluetooth.classicbt;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;

public class BondStateReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private final BondStateListener f14175a;

    public interface BondStateListener {
        void onBondStateChange(BluetoothDevice bluetoothDevice, int i) throws RemoteException;
    }

    public BondStateReceiver(BondStateListener bondStateListener) {
        this.f14175a = bondStateListener;
    }

    public void onReceive(Context context, Intent intent) {
        BluetoothDevice bluetoothDevice;
        if (intent.getAction().equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
            BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
            if (bluetoothDevice2 != null && intExtra > -1) {
                try {
                    if (this.f14175a != null) {
                        this.f14175a.onBondStateChange(bluetoothDevice2, intExtra);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else if (intent.getAction().equals("android.bluetooth.device.action.PAIRING_REQUEST") && (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) != null) {
            try {
                if (this.f14175a != null) {
                    this.f14175a.onBondStateChange(bluetoothDevice, 11);
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }
}
