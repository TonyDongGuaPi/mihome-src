package miui.bluetooth.ble;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import java.util.UUID;
import miui.bluetooth.ble.IBluetoothMiBle;
import miui.bluetooth.ble.IBluetoothMiBleCallback;
import miui.bluetooth.ble.IBluetoothMiBlePropertyCallback;

public class MiBleProfile {
    public static final String ACTION_MIBLE_SERVICE = "miui.bluetooth.mible.Service";
    public static final String ACTION_SELECT_DEVICE = "miui.bluetooth.action.PICK_DEVICE";
    protected static final boolean DBG = true;
    public static final String EXTRA_MIBLE_PROPERTY = "miui.bluetooth.extra.MIBLE_PROPERTY";
    private static final int MSG_PROPERTY = 2;
    private static final int MSG_STATUS = 1;
    public static final int PROPERTY_ALARM_CLOCK = 106;
    public static final int PROPERTY_BATTERY = 6;
    public static final int PROPERTY_DEVICE_CONTROL = 2;
    public static final int PROPERTY_DEVICE_INFO = 101;
    public static final int PROPERTY_FIRMWARE = 3;
    public static final int PROPERTY_IMMEDIATE_ALERT = 5;
    public static final int PROPERTY_LINK_LOSS = 7;
    public static final int PROPERTY_MI_BAND_EVENT = 108;
    public static final int PROPERTY_MI_KEY = 107;
    public static final int PROPERTY_PAY = 4;
    public static final int PROPERTY_SPORT_ACTIVITIES = 104;
    public static final int PROPERTY_SPORT_STEPS = 103;
    public static final int PROPERTY_THEME_COLOR = 105;
    public static final int PROPERTY_UNDEFINED = 0;
    public static final int PROPERTY_UNLOCK = 1;
    public static final int PROPERTY_USER_INFO = 102;
    public static final int SERVICE_VERSION_UNKNOWN = -1;
    public static final int STATUS_CONNECTED = 2;
    public static final int STATUS_CONNECTING = 1;
    public static final int STATUS_DISCONNECTED = 0;
    public static final int STATUS_DISCONNECTING = 3;
    public static final int STATUS_ERROR = -1;
    public static final int STATUS_READY = 4;
    protected static final String TAG = "MiBleProfile";
    protected IProfileStateChangeCallback mCallback;
    protected final ParcelUuid mClientId;
    /* access modifiers changed from: private */
    public boolean mConnectWhenBind;
    protected Context mContext;
    protected String mDevice;
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public int mProfileState;
    private IBluetoothMiBlePropertyCallback mPropertyCallback;
    /* access modifiers changed from: private */
    public SparseArray<IPropertyNotifyCallback> mPropertyCallbacks;
    protected IBluetoothMiBle mService;
    /* access modifiers changed from: private */
    public IBluetoothMiBleCallback mServiceCallback;
    private ServiceConnection mServiceConnection;
    /* access modifiers changed from: private */
    public final IBinder mToken;

    public interface IProfileStateChangeCallback {
        void onState(int i);
    }

    public interface IPropertyNotifyCallback {
        void notifyProperty(int i, byte[] bArr);
    }

    public MiBleProfile(Context context, String str) {
        this(context, str, (IProfileStateChangeCallback) null);
    }

    public MiBleProfile(Context context, String str, IProfileStateChangeCallback iProfileStateChangeCallback) {
        this.mClientId = new ParcelUuid(UUID.randomUUID());
        this.mToken = new Binder();
        this.mConnectWhenBind = false;
        this.mPropertyCallbacks = new SparseArray<>();
        this.mProfileState = 0;
        this.mServiceConnection = new ServiceConnection() {
            public void onServiceDisconnected(ComponentName componentName) {
                MiBleProfile.this.mService = null;
                int unused = MiBleProfile.this.mProfileState = 0;
                MiBleProfile.this.mHandler.sendMessage(MiBleProfile.this.mHandler.obtainMessage(1, 0, 0));
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(MiBleProfile.TAG, "onServiceConnected()");
                MiBleProfile.this.mService = IBluetoothMiBle.Stub.asInterface(iBinder);
                MiBleProfile.this.mHandler.sendMessage(MiBleProfile.this.mHandler.obtainMessage(1, 2, 0));
                try {
                    MiBleProfile.this.mService.registerClient(MiBleProfile.this.mToken, MiBleProfile.this.mDevice, MiBleProfile.this.mClientId, MiBleProfile.this.mServiceCallback);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    MiBleProfile.this.mHandler.sendMessage(MiBleProfile.this.mHandler.obtainMessage(1, -1, 0));
                }
                if (MiBleProfile.this.mConnectWhenBind) {
                    boolean unused = MiBleProfile.this.mConnectWhenBind = false;
                    MiBleProfile.this.connect();
                }
            }
        };
        this.mServiceCallback = new IBluetoothMiBleCallback.Stub() {
            public void onConnectionState(ParcelUuid parcelUuid, int i) throws RemoteException {
                Log.d(MiBleProfile.TAG, "onConnectionState() sate=" + i);
                if (MiBleProfile.this.mClientId.equals(parcelUuid)) {
                    int unused = MiBleProfile.this.mProfileState = i;
                    MiBleProfile.this.mHandler.sendMessage(MiBleProfile.this.mHandler.obtainMessage(1, i, 0));
                }
            }
        };
        this.mPropertyCallback = new IBluetoothMiBlePropertyCallback.Stub() {
            public void notifyProperty(ParcelUuid parcelUuid, int i, byte[] bArr) throws RemoteException {
                Log.d(MiBleProfile.TAG, "mPropertyCallback() property=" + i);
                if (MiBleProfile.this.mClientId.equals(parcelUuid)) {
                    Message obtainMessage = MiBleProfile.this.mHandler.obtainMessage(2);
                    obtainMessage.arg1 = i;
                    obtainMessage.obj = bArr;
                    MiBleProfile.this.mHandler.sendMessage(obtainMessage);
                }
            }
        };
        this.mDevice = str;
        this.mContext = context;
        this.mCallback = iProfileStateChangeCallback;
        try {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        this.mHandler = new Handler(new Handler.Callback() {
            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    if (MiBleProfile.this.mCallback != null) {
                        MiBleProfile.this.mCallback.onState(message.arg1);
                    }
                    return true;
                } else if (i != 2) {
                    return false;
                } else {
                    int i2 = message.arg1;
                    IPropertyNotifyCallback iPropertyNotifyCallback = (IPropertyNotifyCallback) MiBleProfile.this.mPropertyCallbacks.get(i2);
                    if (iPropertyNotifyCallback != null) {
                        iPropertyNotifyCallback.notifyProperty(i2, (byte[]) message.obj);
                    }
                    return true;
                }
            }
        });
    }

    public int getServiceVersion() {
        if (this.mService == null) {
            return -1;
        }
        try {
            return this.mService.getServiceVersion();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void setProfileStateChangeCallback(IProfileStateChangeCallback iProfileStateChangeCallback) {
        this.mCallback = iProfileStateChangeCallback;
    }

    public String getDeviceAddress() {
        return this.mDevice;
    }

    public void connect() {
        if (this.mService == null) {
            this.mConnectWhenBind = true;
            Intent intent = new Intent(ACTION_MIBLE_SERVICE);
            intent.setClassName("com.android.bluetooth", "com.android.bluetooth.ble.BluetoothMiBleService");
            intent.setPackage("com.android.bluetooth");
            if (!this.mContext.bindService(intent, this.mServiceConnection, 1)) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, -1, 0));
                return;
            }
            return;
        }
        try {
            this.mService.connect(this.mDevice, this.mClientId);
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, -1, 0));
        }
    }

    public void disconnect() {
        if (this.mService != null) {
            try {
                this.mService.unregisterClient(this.mToken, this.mDevice, this.mClientId);
                this.mContext.unbindService(this.mServiceConnection);
            } catch (RemoteException e) {
                e.printStackTrace();
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, -1, 0));
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, -1, 0));
            }
        }
    }

    public boolean isReady() {
        return this.mProfileState == 4;
    }

    public boolean isSupportProperty(int i) {
        try {
            return this.mService != null && this.mService.supportProperty(this.mDevice, i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public byte[] getProperty(int i) {
        if (this.mService == null) {
            return null;
        }
        try {
            return this.mService.getProperty(this.mDevice, this.mClientId, i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean setProperty(int i, byte[] bArr) {
        if (this.mService == null) {
            return false;
        }
        try {
            return this.mService.setProperty(this.mDevice, this.mClientId, i, bArr);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0024 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean registerPropertyNotifyCallback(int r5, miui.bluetooth.ble.MiBleProfile.IPropertyNotifyCallback r6) {
        /*
            r4 = this;
            android.util.SparseArray<miui.bluetooth.ble.MiBleProfile$IPropertyNotifyCallback> r0 = r4.mPropertyCallbacks
            r0.put(r5, r6)
            miui.bluetooth.ble.IBluetoothMiBle r6 = r4.mService
            r0 = 0
            if (r6 == 0) goto L_0x001b
            miui.bluetooth.ble.IBluetoothMiBle r6 = r4.mService     // Catch:{ RemoteException -> 0x0017 }
            java.lang.String r1 = r4.mDevice     // Catch:{ RemoteException -> 0x0017 }
            android.os.ParcelUuid r2 = r4.mClientId     // Catch:{ RemoteException -> 0x0017 }
            miui.bluetooth.ble.IBluetoothMiBlePropertyCallback r3 = r4.mPropertyCallback     // Catch:{ RemoteException -> 0x0017 }
            boolean r6 = r6.registerPropertyCallback(r1, r2, r5, r3)     // Catch:{ RemoteException -> 0x0017 }
            goto L_0x001c
        L_0x0017:
            r6 = move-exception
            r6.printStackTrace()
        L_0x001b:
            r6 = 0
        L_0x001c:
            if (r6 != 0) goto L_0x0024
            android.util.SparseArray<miui.bluetooth.ble.MiBleProfile$IPropertyNotifyCallback> r6 = r4.mPropertyCallbacks
            r6.remove(r5)
            return r0
        L_0x0024:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.bluetooth.ble.MiBleProfile.registerPropertyNotifyCallback(int, miui.bluetooth.ble.MiBleProfile$IPropertyNotifyCallback):boolean");
    }

    public boolean unregisterPropertyNotifyCallback(int i) {
        this.mPropertyCallbacks.remove(i);
        if (this.mPropertyCallbacks.get(i) != null) {
            return true;
        }
        try {
            if (this.mService != null) {
                return this.mService.unregisterPropertyCallback(this.mDevice, this.mClientId, i, this.mPropertyCallback);
            }
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getRssi() {
        if (!isReady()) {
            return Integer.MIN_VALUE;
        }
        try {
            return this.mService.getRssi(this.mDevice, this.mClientId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }
}
