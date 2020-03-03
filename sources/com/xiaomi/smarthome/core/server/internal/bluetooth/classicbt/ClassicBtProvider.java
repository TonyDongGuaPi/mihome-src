package com.xiaomi.smarthome.core.server.internal.bluetooth.classicbt;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.os.RemoteException;
import com.xiaomi.smarthome.core.server.internal.util.Logger;

public class ClassicBtProvider extends AbsClassicBTProvider {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14176a = "ClassicBtProvider";
    private IProviderNotify b = null;

    public interface IProviderNotify {
        void onConnectionStateChanged(String str, int i) throws RemoteException;

        void onReceiveData(String str, byte[] bArr) throws RemoteException;
    }

    /* access modifiers changed from: package-private */
    public void b(int i) {
    }

    /* access modifiers changed from: package-private */
    public void f() {
    }

    public ClassicBtProvider(BluetoothManager bluetoothManager) {
        super(bluetoothManager);
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        BluetoothDevice a2 = a();
        if (a2 == null) {
            Logger.a(f14176a, "onConnectionStateChanged device is null");
        } else if (this.b != null) {
            try {
                this.b.onConnectionStateChanged(a2.getAddress(), i);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Logger.c(f14176a, "onConnectionStateChanged listener is null");
        }
    }

    /* access modifiers changed from: package-private */
    public void b(byte[] bArr) {
        BluetoothDevice a2 = a();
        if (this.b == null || a2 == null) {
            Logger.a(f14176a, "onConnectionStateChanged device is null");
            return;
        }
        try {
            this.b.onReceiveData(a2.getAddress(), bArr);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void a(IProviderNotify iProviderNotify) {
        this.b = iProviderNotify;
    }
}
