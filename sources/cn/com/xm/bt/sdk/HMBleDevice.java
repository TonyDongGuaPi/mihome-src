package cn.com.xm.bt.sdk;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Base64;
import cn.com.xm.bt.a.a;
import cn.com.xm.bt.b.d;
import cn.com.xm.bt.b.e;
import cn.com.xm.bt.c.c;
import cn.com.xm.bt.profile.a.f;
import cn.com.xm.bt.profile.nfc.ApduResponse;
import cn.com.xm.bt.profile.nfc.HMAccessInfo;
import cn.com.xm.bt.profile.nfc.HMAidInfo;
import cn.com.xm.bt.profile.nfc.HMNFCStatus;
import java.io.File;

public class HMBleDevice {
    public static final int AUTH_ERROR_CANCEL = 2;
    public static final int AUTH_ERROR_CMD_FAILED = 4;
    public static final int AUTH_ERROR_KEY_ERROR = 5;
    public static final int AUTH_ERROR_NOKNOCK = 3;
    public static final int AUTH_ERROR_NONE = 0;
    public static final int AUTH_ERROR_TIMEOUT = 1;
    public static final int AUTH_STATE_DENY = 4;
    public static final int AUTH_STATE_FAILED = 1;
    public static final int AUTH_STATE_KNOCK = 2;
    public static final int AUTH_STATE_KNOCK_SUCCESS = 3;
    public static final int AUTH_STATE_SUCCESS = 0;
    public static final int CONN_CONNECTING = 1;
    public static final int CONN_DISCONNECTED = 3;
    public static final int CONN_SUCCESS = 0;
    public static final int CONN_TIMEOUT = 2;
    private static final String TAG = "HMBleDevice";
    private static final int VERSION = 2;
    private boolean autoReconnect = true;
    private d bleDevice = null;

    public HMBleDevice(Context context, String str) {
        a.b(TAG, "connect:" + str);
        this.bleDevice = new d(context, str);
        this.bleDevice.a(new cn.com.xm.bt.profile.a.a());
    }

    public void connect(final IDeviceCallback iDeviceCallback) {
        a.b(TAG, "connect:" + iDeviceCallback);
        this.bleDevice.a((f) new f() {
            public byte[] onGetSignData(byte[] bArr, byte[] bArr2, int i) {
                a.b(HMBleDevice.TAG, "onGetSignData random:" + c.a(bArr) + ",publicKeyHash:" + c.a(bArr2) + ",algorithm:" + i);
                String encodeToString = Base64.encodeToString(bArr, 2);
                StringBuilder sb = new StringBuilder();
                sb.append(i == 0 ? "SHA1:" : "UNKNOWN:");
                sb.append(c.d(bArr2));
                String sb2 = sb.toString();
                a.a(HMBleDevice.TAG, "random:" + c.a(bArr) + ",base64Random:" + encodeToString + ",keyHash:" + sb2);
                byte[] onSignature = iDeviceCallback.onSignature(encodeToString, sb2);
                StringBuilder sb3 = new StringBuilder();
                sb3.append("signature:");
                sb3.append(c.a(onSignature));
                a.b(HMBleDevice.TAG, sb3.toString());
                return onSignature;
            }

            public void onAuthentication(cn.com.xm.bt.profile.a.c cVar) {
                a.b(HMBleDevice.TAG, "authState:" + cVar);
                if (iDeviceCallback != null) {
                    iDeviceCallback.onAuthStateChanged(cVar.a(), cVar.b());
                }
            }
        });
        this.bleDevice.a((e) new e() {
            public void onDeviceConnecting(BluetoothDevice bluetoothDevice, cn.com.xm.bt.b.c cVar) {
                a.b(HMBleDevice.TAG, "onDeviceConnecting");
                if (iDeviceCallback != null) {
                    iDeviceCallback.onConnectionStateChanged(1);
                }
            }

            public void onDeviceConnectingTimeout(BluetoothDevice bluetoothDevice, cn.com.xm.bt.b.c cVar) {
                a.b(HMBleDevice.TAG, "onDeviceConnectingTimeout");
                if (iDeviceCallback != null) {
                    iDeviceCallback.onConnectionStateChanged(2);
                }
            }

            public void onDeviceConnected(BluetoothDevice bluetoothDevice, cn.com.xm.bt.b.c cVar) {
                a.b(HMBleDevice.TAG, "onDeviceConnected");
                if (iDeviceCallback != null) {
                    iDeviceCallback.onConnectionStateChanged(0);
                }
            }

            public void onDeviceDisconnected(BluetoothDevice bluetoothDevice, cn.com.xm.bt.b.c cVar) {
                a.b(HMBleDevice.TAG, "onDeviceDisconnected");
                if (iDeviceCallback != null) {
                    iDeviceCallback.onConnectionStateChanged(3);
                }
            }
        });
        this.bleDevice.a(this.autoReconnect);
        this.bleDevice.f();
    }

    public void setPair(boolean z) {
        a.b(TAG, "setPair:" + z);
        if (this.bleDevice != null) {
            this.bleDevice.d().a(z);
        }
    }

    public void setAutoConnect(boolean z) {
        a.b(TAG, "setAutoConnect:" + z);
        this.autoReconnect = z;
        if (this.bleDevice != null) {
            this.bleDevice.a(z);
        }
    }

    public void setKey(String str) {
        a.b(TAG, "setKey:" + str);
        if (this.bleDevice != null) {
            this.bleDevice.d().a(str);
        }
    }

    public String getKey() {
        String a2 = this.bleDevice != null ? this.bleDevice.d().a() : null;
        a.b(TAG, "getKey:" + a2);
        return a2;
    }

    public int getRealtimeStep() {
        a.b(TAG, "getRealtimeStep");
        cn.com.xm.bt.d.e h = this.bleDevice != null ? this.bleDevice.h() : null;
        if (h == null) {
            return -1;
        }
        return h.a();
    }

    public HMNFCStatus openApduChannel() {
        a.b(TAG, "openApduChannel");
        if (this.bleDevice != null) {
            return this.bleDevice.a((cn.com.xm.bt.profile.nfc.d) $$Lambda$HMBleDevice$QbK4Bi8wrg2LXl6ehbrfcKpl70M.INSTANCE);
        }
        return null;
    }

    public ApduResponse sendApduData(byte[] bArr, int i, boolean z) {
        a.b(TAG, "sendApduData:" + c.a(bArr) + ",apduLen:" + i + ",encrypt:" + z);
        if (this.bleDevice != null) {
            return this.bleDevice.a(new cn.com.xm.bt.profile.nfc.a(bArr, i, z));
        }
        return null;
    }

    public boolean setAidInfoSync(HMAidInfo hMAidInfo) {
        a.b(TAG, "setAidInfoSync:" + hMAidInfo);
        return this.bleDevice != null && this.bleDevice.a(hMAidInfo);
    }

    public boolean setAccessInfoSync(HMAccessInfo hMAccessInfo) {
        a.b(TAG, "setAccessInfoSync:" + hMAccessInfo);
        return this.bleDevice != null && this.bleDevice.a(hMAccessInfo);
    }

    public HMNFCStatus closeApduChannel() {
        a.b(TAG, "closeApduChannel");
        if (this.bleDevice != null) {
            return this.bleDevice.i();
        }
        return null;
    }

    public void disconnect() {
        a.b(TAG, "disconnect");
        if (this.bleDevice != null) {
            this.bleDevice.g();
            this.bleDevice = null;
        }
    }

    public static int getVersion() {
        a.b(TAG, "getVersion");
        return 2;
    }

    public static void enableLog(boolean z) {
        a.b(TAG, "enableLog:" + z);
        a.a(z);
    }

    public static void setLogFile(File file) {
        a.b(TAG, "setLogFile:" + file);
        a.a(file);
    }
}
