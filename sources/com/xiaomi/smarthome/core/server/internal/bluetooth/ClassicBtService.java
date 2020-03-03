package com.xiaomi.smarthome.core.server.internal.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.IntentFilter;
import android.os.RemoteException;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.bluetooth.IClassicBtRequest;
import com.xiaomi.smarthome.core.server.bluetooth.IClassicBtResponse;
import com.xiaomi.smarthome.core.server.bluetooth.IProfileProxyPrepareCallback;
import com.xiaomi.smarthome.core.server.internal.bluetooth.classicbt.BondStateReceiver;
import com.xiaomi.smarthome.core.server.internal.bluetooth.classicbt.ClassicBtProvider;
import com.xiaomi.smarthome.core.server.internal.util.ClassicBtUtil;
import com.xiaomi.smarthome.core.server.internal.util.Logger;
import com.xiaomi.smarthome.device.BleDevice;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClassicBtService extends IClassicBtRequest.Stub implements BondStateReceiver.BondStateListener, ClassicBtProvider.IProviderNotify {
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothManager mBluetoothManager;
    private BondStateReceiver mBondStateReceiver;
    private ClassicBtProvider mBtProvider;
    private Context mContext;
    /* access modifiers changed from: private */
    public Map<Integer, BluetoothProfile> mProfileMap;
    private boolean mRegisterReceiver;
    private IClassicBtResponse mResponse;

    static final class Holder {

        /* renamed from: a  reason: collision with root package name */
        public static final ClassicBtService f14142a = new ClassicBtService();

        Holder() {
        }
    }

    private ClassicBtService() {
        this.mResponse = null;
        this.mBondStateReceiver = new BondStateReceiver(this);
        this.mBluetoothManager = null;
        this.mBluetoothAdapter = null;
        this.mBtProvider = null;
        this.mProfileMap = new HashMap();
        this.mContext = CoreService.getAppContext();
        this.mRegisterReceiver = false;
    }

    public static ClassicBtService getInstance() {
        return Holder.f14142a;
    }

    public void setClassicBtResponse(IClassicBtResponse iClassicBtResponse) {
        this.mResponse = iClassicBtResponse;
    }

    public void createClassicBTService() throws RemoteException {
        initBT();
        initBondStateReceiver();
    }

    /* access modifiers changed from: package-private */
    public void initBT() {
        this.mBluetoothManager = (BluetoothManager) this.mContext.getSystemService(BleDevice.f14751a);
        if (this.mBtProvider != null) {
            this.mBtProvider.c();
            this.mBtProvider = null;
        }
        this.mBtProvider = new ClassicBtProvider(this.mBluetoothManager);
        this.mBluetoothAdapter = this.mBluetoothManager.getAdapter();
        this.mBtProvider.a((ClassicBtProvider.IProviderNotify) this);
    }

    /* access modifiers changed from: package-private */
    public void initBondStateReceiver() {
        if (!this.mRegisterReceiver) {
            IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
            this.mContext.registerReceiver(this.mBondStateReceiver, intentFilter);
            this.mRegisterReceiver = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void checkBondedState(String str) {
        BluetoothDevice remoteDevice;
        if (this.mBluetoothAdapter != null && (remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str)) != null) {
            try {
                onBondStateChange(remoteDevice, remoteDevice.getBondState());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean connectClassicBTSocket(String str, String str2) throws RemoteException {
        if (this.mBtProvider != null) {
            boolean a2 = this.mBtProvider.a(str, UUID.fromString(str2));
            checkBondedState(str);
            return a2;
        }
        Logger.c("ClassBtProvider is null!!!");
        return false;
    }

    public void disconnectClassicBtSocket() throws RemoteException {
        if (this.mBtProvider == null) {
            Logger.c("disconnect socket ,but bt provider is null");
        } else {
            this.mBtProvider.c();
        }
    }

    public boolean write(byte[] bArr) throws RemoteException {
        if (this.mBtProvider != null) {
            return this.mBtProvider.a(bArr);
        }
        return false;
    }

    public void prepareBluetoothProfile(int i, final IProfileProxyPrepareCallback iProfileProxyPrepareCallback) throws RemoteException {
        if (this.mBluetoothAdapter == null) {
            Logger.c("mBluetoothAdapter is null");
        } else {
            this.mBluetoothAdapter.getProfileProxy(this.mContext, new BluetoothProfile.ServiceListener() {
                public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                    ClassicBtService.this.mProfileMap.put(Integer.valueOf(i), bluetoothProfile);
                    if (iProfileProxyPrepareCallback != null) {
                        try {
                            iProfileProxyPrepareCallback.onServiceConnected(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onServiceDisconnected(int i) {
                    BluetoothProfile bluetoothProfile = (BluetoothProfile) ClassicBtService.this.mProfileMap.remove(Integer.valueOf(i));
                    if (iProfileProxyPrepareCallback != null) {
                        try {
                            iProfileProxyPrepareCallback.onServiceDisconnected(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, i);
        }
    }

    public boolean connectBluetoothProfile(String str, int i) throws RemoteException {
        if (this.mBluetoothAdapter == null) {
            Logger.c("mBluetoothAdapter is null");
            return false;
        } else if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            Logger.b(str + " is not a valid Bluetooth address");
            return false;
        } else {
            BluetoothProfile bluetoothProfile = this.mProfileMap.get(Integer.valueOf(i));
            if (bluetoothProfile != null) {
                return ClassicBtUtil.a(this.mBluetoothAdapter.getRemoteDevice(str), bluetoothProfile);
            }
            Logger.c(" connectBluetoothProfile ,but profile is null");
            return false;
        }
    }

    public boolean disconnectBluetoothProfile(String str, int i) throws RemoteException {
        if (this.mBluetoothAdapter == null) {
            Logger.c("mBluetoothAdapter is null");
            return false;
        } else if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            Logger.b(str + " is not a valid Bluetooth address");
            return false;
        } else {
            BluetoothProfile bluetoothProfile = this.mProfileMap.get(Integer.valueOf(i));
            if (bluetoothProfile != null) {
                return ClassicBtUtil.b(this.mBluetoothAdapter.getRemoteDevice(str), bluetoothProfile);
            }
            Logger.c("disconnectBluetoothProfile , but profile is null,profile is " + i);
            return false;
        }
    }

    public int getBluetoothProfileState(String str, int i) throws RemoteException {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            Logger.c("getBluetoothProfileState fail ,address invalid,address is " + str + ",profile is " + i);
            return 0;
        }
        BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str);
        if (remoteDevice == null) {
            Logger.c("getBluetoothProfileState fail ,device is null,address is " + str + ",profile is " + i);
            return 0;
        }
        BluetoothProfile bluetoothProfile = this.mProfileMap.get(Integer.valueOf(i));
        if (bluetoothProfile != null) {
            return bluetoothProfile.getConnectionState(remoteDevice);
        }
        Logger.c("getBluetoothProfileState fail , because proxy is null,address is " + str + ",profile is " + i);
        return 0;
    }

    public void destroy() throws RemoteException {
        if (this.mBondStateReceiver != null && this.mRegisterReceiver) {
            this.mContext.unregisterReceiver(this.mBondStateReceiver);
            this.mRegisterReceiver = false;
        }
        if (this.mBtProvider != null) {
            this.mBtProvider.c();
        }
        this.mResponse = null;
    }

    public void onBondStateChange(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
        if (bluetoothDevice == null) {
            Logger.c("onBondStateChange device is null");
        } else if (this.mResponse != null) {
            Logger.a("onBondStateChange ,mac address=" + bluetoothDevice.getAddress() + ", state =" + i);
            this.mResponse.onBondStateChange(bluetoothDevice.getAddress(), i);
        } else {
            Logger.c("onBondStateChange response is null");
        }
    }

    public void onConnectionStateChanged(String str, int i) throws RemoteException {
        if (this.mResponse != null) {
            Logger.a("onConnectionStateChanged macAddress =" + str + ", state =" + i);
            this.mResponse.onConnectionStateChanged(str, i);
            return;
        }
        Logger.c("onConnectionStateChanged response is null");
    }

    public void onReceiveData(String str, byte[] bArr) throws RemoteException {
        if (bArr == null || bArr.length <= 0) {
            Logger.c("onReceiveData data is empty");
        } else if (this.mResponse != null) {
            Logger.a("onReceiveData address =" + str + ",data str =" + new String(bArr));
            this.mResponse.onReceiveData(str, bArr);
        } else {
            Logger.c("onReceiveData response is null");
        }
    }
}
