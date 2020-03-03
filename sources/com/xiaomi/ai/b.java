package com.xiaomi.ai;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import com.xiaomi.ai.utils.Log;
import java.util.List;

class b implements BluetoothProfile.ServiceListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BluetoothManager f9914a;

    b(BluetoothManager bluetoothManager) {
        this.f9914a = bluetoothManager;
    }

    public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
        BluetoothHeadset unused = this.f9914a.e = (BluetoothHeadset) bluetoothProfile;
        Log.f("MiSpeechSDK:BluetoothManager", "onServiceConnected profile" + i);
        List<BluetoothDevice> connectedDevices = this.f9914a.e.getConnectedDevices();
        if (connectedDevices.size() > 0) {
            Log.f("MiSpeechSDK:BluetoothManager", "l.size(): " + connectedDevices.size());
            BluetoothDevice unused2 = this.f9914a.d = connectedDevices.get(0);
            boolean unused3 = this.f9914a.g = true;
            if (this.f9914a.h) {
                Log.f("MiSpeechSDK:BluetoothManager", "mHasPendingRequest, set sco on");
                this.f9914a.a(true);
                boolean unused4 = this.f9914a.h = false;
                return;
            }
            return;
        }
        Log.f("MiSpeechSDK:BluetoothManager", "onServiceConnected size" + connectedDevices.size());
    }

    public void onServiceDisconnected(int i) {
        Log.f("MiSpeechSDK:BluetoothManager", "onServiceDisconnected profile" + i);
        if (i == 1) {
            Log.f("MiSpeechSDK:BluetoothManager", "onServiceDisconnected");
            boolean unused = this.f9914a.g = false;
            BluetoothHeadset unused2 = this.f9914a.e = null;
        }
    }
}
