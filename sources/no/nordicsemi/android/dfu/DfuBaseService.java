package no.nordicsemi.android.dfu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.coloros.mcssdk.PushManager;
import com.google.android.exoplayer2.C;
import com.libra.Color;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.stats.SensorsDataManager;
import com.xiaomi.smarthome.device.BleDevice;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Locale;
import no.nordicsemi.android.dfu.DfuProgressInfo;
import no.nordicsemi.android.dfu.internal.ArchiveInputStream;
import no.nordicsemi.android.dfu.internal.HexInputStream;

public abstract class DfuBaseService extends IntentService implements DfuProgressInfo.ProgressListener {
    public static final int ACTION_ABORT = 2;
    public static final int ACTION_PAUSE = 0;
    public static final int ACTION_RESUME = 1;
    public static final String BROADCAST_ACTION = "no.nordicsemi.android.dfu.broadcast.BROADCAST_ACTION";
    public static final String BROADCAST_ERROR = "no.nordicsemi.android.dfu.broadcast.BROADCAST_ERROR";
    public static final String BROADCAST_LOG = "no.nordicsemi.android.dfu.broadcast.BROADCAST_LOG";
    public static final String BROADCAST_PROGRESS = "no.nordicsemi.android.dfu.broadcast.BROADCAST_PROGRESS";
    static boolean DEBUG = false;
    public static final int ERROR_BLUETOOTH_DISABLED = 4106;
    public static final int ERROR_CONNECTION_MASK = 16384;
    public static final int ERROR_CONNECTION_STATE_MASK = 32768;
    public static final int ERROR_CRC_ERROR = 4109;
    public static final int ERROR_DEVICE_DISCONNECTED = 4096;
    public static final int ERROR_DEVICE_NOT_BONDED = 4110;
    public static final int ERROR_FILE_ERROR = 4098;
    public static final int ERROR_FILE_INVALID = 4099;
    public static final int ERROR_FILE_IO_EXCEPTION = 4100;
    public static final int ERROR_FILE_NOT_FOUND = 4097;
    public static final int ERROR_FILE_SIZE_INVALID = 4108;
    public static final int ERROR_FILE_TYPE_UNSUPPORTED = 4105;
    public static final int ERROR_INIT_PACKET_REQUIRED = 4107;
    public static final int ERROR_INVALID_RESPONSE = 4104;
    public static final int ERROR_MASK = 4096;
    public static final int ERROR_REMOTE_MASK = 8192;
    public static final int ERROR_REMOTE_TYPE_LEGACY = 256;
    public static final int ERROR_REMOTE_TYPE_SECURE = 512;
    public static final int ERROR_REMOTE_TYPE_SECURE_BUTTONLESS = 2048;
    public static final int ERROR_REMOTE_TYPE_SECURE_EXTENDED = 1024;
    public static final int ERROR_SERVICE_DISCOVERY_NOT_STARTED = 4101;
    public static final int ERROR_SERVICE_NOT_FOUND = 4102;
    public static final int ERROR_TYPE_COMMUNICATION = 2;
    public static final int ERROR_TYPE_COMMUNICATION_STATE = 1;
    public static final int ERROR_TYPE_DFU_REMOTE = 3;
    public static final int ERROR_TYPE_OTHER = 0;
    public static final String EXTRA_ACTION = "no.nordicsemi.android.dfu.extra.EXTRA_ACTION";
    private static final String EXTRA_ATTEMPT = "no.nordicsemi.android.dfu.extra.EXTRA_ATTEMPT";
    public static final String EXTRA_AVG_SPEED_B_PER_MS = "no.nordicsemi.android.dfu.extra.EXTRA_AVG_SPEED_B_PER_MS";
    public static final String EXTRA_CURRENT_MTU = "no.nordicsemi.android.dfu.extra.EXTRA_CURRENT_MTU";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU";
    public static final String EXTRA_DATA = "no.nordicsemi.android.dfu.extra.EXTRA_DATA";
    public static final String EXTRA_DEVICE_ADDRESS = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS";
    public static final String EXTRA_DEVICE_NAME = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_NAME";
    public static final String EXTRA_DISABLE_NOTIFICATION = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_NOTIFICATION";
    public static final String EXTRA_DISABLE_RESUME = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_RESUME";
    public static final String EXTRA_ERROR_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_ERROR_TYPE";
    public static final String EXTRA_FILE_MIME_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_MIME_TYPE";
    public static final String EXTRA_FILE_PATH = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_PATH";
    public static final String EXTRA_FILE_RES_ID = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_RES_ID";
    public static final String EXTRA_FILE_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_TYPE";
    public static final String EXTRA_FILE_URI = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_URI";
    public static final String EXTRA_FORCE_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_FORCE_DFU";
    public static final String EXTRA_FOREGROUND_SERVICE = "no.nordicsemi.android.dfu.extra.EXTRA_FOREGROUND_SERVICE";
    public static final String EXTRA_INIT_FILE_PATH = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_PATH";
    public static final String EXTRA_INIT_FILE_RES_ID = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_RES_ID";
    public static final String EXTRA_INIT_FILE_URI = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_URI";
    public static final String EXTRA_KEEP_BOND = "no.nordicsemi.android.dfu.extra.EXTRA_KEEP_BOND";
    public static final String EXTRA_LOG_LEVEL = "no.nordicsemi.android.dfu.extra.EXTRA_LOG_LEVEL";
    public static final String EXTRA_LOG_MESSAGE = "no.nordicsemi.android.dfu.extra.EXTRA_LOG_INFO";
    public static final String EXTRA_MTU = "no.nordicsemi.android.dfu.extra.EXTRA_MTU";
    public static final String EXTRA_PACKET_RECEIPT_NOTIFICATIONS_ENABLED = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_ENABLED";
    public static final String EXTRA_PACKET_RECEIPT_NOTIFICATIONS_VALUE = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_VALUE";
    public static final String EXTRA_PARTS_TOTAL = "no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL";
    public static final String EXTRA_PART_CURRENT = "no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT";
    public static final String EXTRA_PROGRESS = "no.nordicsemi.android.dfu.extra.EXTRA_PROGRESS";
    public static final String EXTRA_RESTORE_BOND = "no.nordicsemi.android.dfu.extra.EXTRA_RESTORE_BOND";
    public static final String EXTRA_SPEED_B_PER_MS = "no.nordicsemi.android.dfu.extra.EXTRA_SPEED_B_PER_MS";
    public static final String EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU";
    public static final int LOG_LEVEL_APPLICATION = 10;
    public static final int LOG_LEVEL_DEBUG = 0;
    public static final int LOG_LEVEL_ERROR = 20;
    public static final int LOG_LEVEL_INFO = 5;
    public static final int LOG_LEVEL_VERBOSE = 1;
    public static final int LOG_LEVEL_WARNING = 15;
    public static final String MIME_TYPE_OCTET_STREAM = "application/octet-stream";
    public static final String MIME_TYPE_ZIP = "application/zip";
    public static final String NOTIFICATION_CHANNEL_DFU = "dfu";
    public static final int NOTIFICATION_ID = 283;
    public static final int PROGRESS_ABORTED = -7;
    public static final int PROGRESS_COMPLETED = -6;
    public static final int PROGRESS_CONNECTING = -1;
    public static final int PROGRESS_DISCONNECTING = -5;
    public static final int PROGRESS_ENABLING_DFU_MODE = -3;
    public static final int PROGRESS_STARTING = -2;
    public static final int PROGRESS_VALIDATING = -4;
    protected static final int STATE_CLOSED = -5;
    protected static final int STATE_CONNECTED = -2;
    protected static final int STATE_CONNECTED_AND_READY = -3;
    protected static final int STATE_CONNECTING = -1;
    protected static final int STATE_DISCONNECTED = 0;
    protected static final int STATE_DISCONNECTING = -4;
    private static final String TAG = "DfuBaseService";
    public static final int TYPE_APPLICATION = 4;
    public static final int TYPE_AUTO = 0;
    public static final int TYPE_BOOTLOADER = 2;
    public static final int TYPE_SOFT_DEVICE = 1;
    /* access modifiers changed from: private */
    public boolean mAborted;
    private BluetoothAdapter mBluetoothAdapter;
    private final BroadcastReceiver mBluetoothStateBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
            int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 12);
            DfuBaseService dfuBaseService = DfuBaseService.this;
            dfuBaseService.logw("Action received: android.bluetooth.adapter.action.STATE_CHANGED [state: " + intExtra + ", previous state: " + intExtra2 + Operators.ARRAY_END_STR);
            if (intExtra2 != 12) {
                return;
            }
            if (intExtra == 13 || intExtra == 10) {
                DfuBaseService.this.sendLogBroadcast(15, "Bluetooth adapter disabled");
                DfuBaseService.this.mConnectionState = 0;
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDisconnected();
                }
            }
        }
    };
    private final BroadcastReceiver mBondStateBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int intExtra;
            if (((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(DfuBaseService.this.mDeviceAddress) && (intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1)) != 11 && DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.onBondStateChanged(intExtra);
            }
        }
    };
    protected int mConnectionState;
    private final BroadcastReceiver mConnectionStateBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(DfuBaseService.this.mDeviceAddress)) {
                String action = intent.getAction();
                DfuBaseService dfuBaseService = DfuBaseService.this;
                dfuBaseService.logi("Action received: " + action);
                DfuBaseService dfuBaseService2 = DfuBaseService.this;
                dfuBaseService2.sendLogBroadcast(0, "[Broadcast] Action received: " + action);
            }
        }
    };
    /* access modifiers changed from: private */
    public String mDeviceAddress;
    private String mDeviceName;
    private final BroadcastReceiver mDfuActionReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra(DfuBaseService.EXTRA_ACTION, 0);
            DfuBaseService dfuBaseService = DfuBaseService.this;
            dfuBaseService.logi("User action received: " + intExtra);
            switch (intExtra) {
                case 0:
                    DfuBaseService.this.sendLogBroadcast(15, "[Broadcast] Pause action received");
                    if (DfuBaseService.this.mDfuServiceImpl != null) {
                        DfuBaseService.this.mDfuServiceImpl.pause();
                        return;
                    }
                    return;
                case 1:
                    DfuBaseService.this.sendLogBroadcast(15, "[Broadcast] Resume action received");
                    if (DfuBaseService.this.mDfuServiceImpl != null) {
                        DfuBaseService.this.mDfuServiceImpl.resume();
                        return;
                    }
                    return;
                case 2:
                    DfuBaseService.this.sendLogBroadcast(15, "[Broadcast] Abort action received");
                    boolean unused = DfuBaseService.this.mAborted = true;
                    if (DfuBaseService.this.mDfuServiceImpl != null) {
                        DfuBaseService.this.mDfuServiceImpl.abort();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public DfuCallback mDfuServiceImpl;
    private boolean mDisableNotification;
    /* access modifiers changed from: private */
    public int mError;
    private InputStream mFirmwareInputStream;
    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i != 0) {
                if (i == 8 || i == 19) {
                    DfuBaseService dfuBaseService = DfuBaseService.this;
                    dfuBaseService.logw("Target device disconnected with status: " + i);
                } else {
                    DfuBaseService dfuBaseService2 = DfuBaseService.this;
                    dfuBaseService2.loge("Connection state change error: " + i + " newState: " + i2);
                }
                int unused = DfuBaseService.this.mError = i | 32768;
                if (i2 == 0) {
                    DfuBaseService.this.mConnectionState = 0;
                    if (DfuBaseService.this.mDfuServiceImpl != null) {
                        DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDisconnected();
                    }
                }
            } else if (i2 == 2) {
                DfuBaseService.this.logi("Connected to GATT server");
                DfuBaseService dfuBaseService3 = DfuBaseService.this;
                dfuBaseService3.sendLogBroadcast(5, "Connected to " + DfuBaseService.this.mDeviceAddress);
                DfuBaseService.this.mConnectionState = -2;
                if (bluetoothGatt.getDevice().getBondState() == 12) {
                    DfuBaseService.this.logi("Waiting 1600 ms for a possible Service Changed indication...");
                    DfuBaseService.this.waitFor(1600);
                }
                DfuBaseService.this.sendLogBroadcast(1, "Discovering services...");
                DfuBaseService.this.sendLogBroadcast(0, "gatt.discoverServices()");
                boolean discoverServices = bluetoothGatt.discoverServices();
                DfuBaseService dfuBaseService4 = DfuBaseService.this;
                StringBuilder sb = new StringBuilder();
                sb.append("Attempting to start service discovery... ");
                sb.append(discoverServices ? SensorsDataManager.r : "failed");
                dfuBaseService4.logi(sb.toString());
                if (!discoverServices) {
                    int unused2 = DfuBaseService.this.mError = 4101;
                } else {
                    return;
                }
            } else if (i2 == 0) {
                DfuBaseService.this.logi("Disconnected from GATT server");
                DfuBaseService.this.mConnectionState = 0;
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDisconnected();
                }
            }
            synchronized (DfuBaseService.this.mLock) {
                DfuBaseService.this.mLock.notifyAll();
            }
        }

        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            if (i == 0) {
                DfuBaseService.this.logi("Services discovered");
                DfuBaseService.this.mConnectionState = -3;
            } else {
                DfuBaseService dfuBaseService = DfuBaseService.this;
                dfuBaseService.loge("Service discovery error: " + i);
                int unused = DfuBaseService.this.mError = i | 16384;
            }
            synchronized (DfuBaseService.this.mLock) {
                DfuBaseService.this.mLock.notifyAll();
            }
        }

        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        }

        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            }
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            }
        }

        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            }
        }

        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            }
        }

        @SuppressLint({"NewApi"})
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onMtuChanged(bluetoothGatt, i, i2);
            }
        }

        @SuppressLint({"NewApi"})
        public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            if (DfuBaseService.this.mDfuServiceImpl != null) {
                DfuBaseService.this.mDfuServiceImpl.getGattCallback().onPhyUpdate(bluetoothGatt, i, i2, i3);
            }
        }
    };
    private InputStream mInitFileInputStream;
    private long mLastNotificationTime;
    private int mLastProgress = -1;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    DfuProgressInfo mProgressInfo;

    /* access modifiers changed from: protected */
    public abstract Class<? extends Activity> getNotificationTarget();

    /* access modifiers changed from: protected */
    public boolean isDebug() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void updateErrorNotification(NotificationCompat.Builder builder) {
    }

    /* access modifiers changed from: protected */
    public void updateForegroundNotification(NotificationCompat.Builder builder) {
    }

    public DfuBaseService() {
        super(TAG);
    }

    private static IntentFilter makeDfuActionIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION);
        return intentFilter;
    }

    public void onCreate() {
        super.onCreate();
        DEBUG = isDebug();
        logi("DFU service created. Version: 1.8.0");
        initialize();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        IntentFilter makeDfuActionIntentFilter = makeDfuActionIntentFilter();
        instance.registerReceiver(this.mDfuActionReceiver, makeDfuActionIntentFilter);
        registerReceiver(this.mDfuActionReceiver, makeDfuActionIntentFilter);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        registerReceiver(this.mConnectionStateBroadcastReceiver, intentFilter);
        registerReceiver(this.mBondStateBroadcastReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
        registerReceiver(this.mBluetoothStateBroadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        ((NotificationManager) getSystemService(PushManager.MESSAGE_TYPE_NOTI)).cancel(283);
        stopSelf();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mDfuServiceImpl != null) {
            this.mDfuServiceImpl.abort();
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mDfuActionReceiver);
        unregisterReceiver(this.mDfuActionReceiver);
        unregisterReceiver(this.mConnectionStateBroadcastReceiver);
        unregisterReceiver(this.mBondStateBroadcastReceiver);
        unregisterReceiver(this.mBluetoothStateBroadcastReceiver);
        try {
            if (this.mFirmwareInputStream != null) {
                this.mFirmwareInputStream.close();
            }
            if (this.mInitFileInputStream != null) {
                this.mInitFileInputStream.close();
            }
        } catch (IOException unused) {
        } catch (Throwable th) {
            this.mFirmwareInputStream = null;
            this.mInitFileInputStream = null;
            throw th;
        }
        this.mFirmwareInputStream = null;
        this.mInitFileInputStream = null;
        logi("DFU service destroyed");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x0460, code lost:
        if (r4 != null) goto L_0x0462;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:?, code lost:
        r4.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x047f, code lost:
        if (r4 != null) goto L_0x0462;
     */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x0414 A[Catch:{ all -> 0x0482 }] */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x0433 A[Catch:{ all -> 0x0482 }] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x049c A[SYNTHETIC, Splitter:B:255:0x049c] */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x04a1  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x04a7 A[SYNTHETIC, Splitter:B:260:0x04a7] */
    /* JADX WARNING: Removed duplicated region for block: B:308:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0154 A[Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e, all -> 0x0544 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0156 A[Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e, all -> 0x0544 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onHandleIntent(android.content.Intent r21) {
        /*
            r20 = this;
            r1 = r20
            r8 = r21
            java.lang.String r2 = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS"
            java.lang.String r2 = r8.getStringExtra(r2)
            java.lang.String r3 = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_NAME"
            java.lang.String r3 = r8.getStringExtra(r3)
            java.lang.String r4 = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_NOTIFICATION"
            r9 = 0
            boolean r10 = r8.getBooleanExtra(r4, r9)
            java.lang.String r4 = "no.nordicsemi.android.dfu.extra.EXTRA_FOREGROUND_SERVICE"
            r11 = 1
            boolean r12 = r8.getBooleanExtra(r4, r11)
            java.lang.String r4 = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_PATH"
            java.lang.String r4 = r8.getStringExtra(r4)
            java.lang.String r5 = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_URI"
            android.os.Parcelable r5 = r8.getParcelableExtra(r5)
            android.net.Uri r5 = (android.net.Uri) r5
            java.lang.String r6 = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_RES_ID"
            int r6 = r8.getIntExtra(r6, r9)
            java.lang.String r7 = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_PATH"
            java.lang.String r7 = r8.getStringExtra(r7)
            java.lang.String r13 = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_URI"
            android.os.Parcelable r13 = r8.getParcelableExtra(r13)
            android.net.Uri r13 = (android.net.Uri) r13
            java.lang.String r14 = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_RES_ID"
            int r14 = r8.getIntExtra(r14, r9)
            java.lang.String r15 = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_TYPE"
            int r15 = r8.getIntExtra(r15, r9)
            if (r4 == 0) goto L_0x0061
            if (r15 != 0) goto L_0x0061
            java.util.Locale r15 = java.util.Locale.US
            java.lang.String r15 = r4.toLowerCase(r15)
            java.lang.String r9 = "zip"
            boolean r9 = r15.endsWith(r9)
            if (r9 == 0) goto L_0x0060
            r15 = 0
            goto L_0x0061
        L_0x0060:
            r15 = 4
        L_0x0061:
            java.lang.String r9 = "no.nordicsemi.android.dfu.extra.EXTRA_MIME_TYPE"
            java.lang.String r9 = r8.getStringExtra(r9)
            if (r9 == 0) goto L_0x006a
            goto L_0x0071
        L_0x006a:
            if (r15 != 0) goto L_0x006f
            java.lang.String r9 = "application/zip"
            goto L_0x0071
        L_0x006f:
            java.lang.String r9 = "application/octet-stream"
        L_0x0071:
            r16 = r15 & -8
            if (r16 > 0) goto L_0x054c
            java.lang.String r11 = "application/zip"
            boolean r11 = r11.equals(r9)
            if (r11 != 0) goto L_0x0087
            java.lang.String r11 = "application/octet-stream"
            boolean r11 = r11.equals(r9)
            if (r11 != 0) goto L_0x0087
            goto L_0x054c
        L_0x0087:
            java.lang.String r11 = "application/octet-stream"
            boolean r11 = r11.equals(r9)
            r8 = 2
            if (r11 == 0) goto L_0x00aa
            r11 = 1
            if (r15 == r11) goto L_0x00aa
            if (r15 == r8) goto L_0x00aa
            r11 = 4
            if (r15 == r11) goto L_0x00aa
            java.lang.String r2 = "Unable to determine file type"
            r1.logw(r2)
            java.lang.String r2 = "Unable to determine file type"
            r3 = 15
            r1.sendLogBroadcast(r3, r2)
            r2 = 4105(0x1009, float:5.752E-42)
            r1.report(r2)
            return
        L_0x00aa:
            if (r10 != 0) goto L_0x00bb
            java.lang.Class r11 = r20.getNotificationTarget()
            if (r11 == 0) goto L_0x00b3
            goto L_0x00bb
        L_0x00b3:
            java.lang.NullPointerException r2 = new java.lang.NullPointerException
            java.lang.String r3 = "getNotificationTarget() must not return null if notifications are enabled"
            r2.<init>(r3)
            throw r2
        L_0x00bb:
            if (r12 != 0) goto L_0x00c8
            int r11 = android.os.Build.VERSION.SDK_INT
            r8 = 26
            if (r11 < r8) goto L_0x00c8
            java.lang.String r8 = "Foreground service disabled. Android Oreo or newer may kill a background service few moments after user closes the application.\nConsider enabling foreground service using DfuServiceInitiator#setForeground(boolean)"
            r1.logw(r8)
        L_0x00c8:
            no.nordicsemi.android.dfu.UuidHelper.assignCustomUuids(r21)
            r1.mDeviceAddress = r2
            r1.mDeviceName = r3
            r1.mDisableNotification = r10
            r3 = 0
            r1.mConnectionState = r3
            r1.mError = r3
            android.content.SharedPreferences r3 = android.preference.PreferenceManager.getDefaultSharedPreferences(r20)
            java.lang.String r8 = "settings_mbr_size"
            r11 = 4096(0x1000, float:5.74E-42)
            r17 = r2
            java.lang.String r2 = java.lang.String.valueOf(r11)
            java.lang.String r2 = r3.getString(r8, r2)
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x00f0 }
            if (r2 >= 0) goto L_0x00f2
            r2 = 0
            goto L_0x00f2
        L_0x00f0:
            r2 = 4096(0x1000, float:5.74E-42)
        L_0x00f2:
            if (r12 == 0) goto L_0x00f7
            r20.startForeground()
        L_0x00f7:
            java.lang.String r3 = "DFU service started"
            r8 = 1
            r1.sendLogBroadcast(r8, r3)
            java.io.InputStream r3 = r1.mFirmwareInputStream
            java.io.InputStream r8 = r1.mInitFileInputStream
            java.io.InputStream r11 = r1.mFirmwareInputStream     // Catch:{ all -> 0x0544 }
            if (r11 != 0) goto L_0x0107
            r11 = 1
            goto L_0x0108
        L_0x0107:
            r11 = 0
        L_0x0108:
            r18 = r3
            r19 = r8
            if (r11 == 0) goto L_0x0172
            java.lang.String r3 = "Opening file..."
            r8 = 1
            r1.sendLogBroadcast(r8, r3)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            if (r5 == 0) goto L_0x011b
            java.io.InputStream r3 = r1.openInputStream((android.net.Uri) r5, (java.lang.String) r9, (int) r2, (int) r15)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            goto L_0x012b
        L_0x011b:
            if (r4 == 0) goto L_0x0122
            java.io.InputStream r3 = r1.openInputStream((java.lang.String) r4, (java.lang.String) r9, (int) r2, (int) r15)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            goto L_0x012b
        L_0x0122:
            if (r6 <= 0) goto L_0x0129
            java.io.InputStream r3 = r1.openInputStream((int) r6, (java.lang.String) r9, (int) r2, (int) r15)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            goto L_0x012b
        L_0x0129:
            r3 = r18
        L_0x012b:
            if (r13 == 0) goto L_0x0137
            android.content.ContentResolver r2 = r20.getContentResolver()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            java.io.InputStream r2 = r2.openInputStream(r13)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
        L_0x0135:
            r8 = r2
            goto L_0x014c
        L_0x0137:
            if (r7 == 0) goto L_0x013f
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            r2.<init>(r7)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            goto L_0x0135
        L_0x013f:
            if (r14 <= 0) goto L_0x014a
            android.content.res.Resources r2 = r20.getResources()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            java.io.InputStream r2 = r2.openRawResource(r14)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            goto L_0x0135
        L_0x014a:
            r8 = r19
        L_0x014c:
            int r2 = r3.available()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            r4 = 4
            int r2 = r2 % r4
            if (r2 != 0) goto L_0x0156
            r6 = r3
            goto L_0x0176
        L_0x0156:
            no.nordicsemi.android.dfu.internal.exception.SizeValidationException r2 = new no.nordicsemi.android.dfu.internal.exception.SizeValidationException     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            java.lang.String r3 = "The new firmware is not word-aligned."
            r2.<init>(r3)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            throw r2     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
        L_0x015e:
            r0 = move-exception
            r2 = r0
            goto L_0x04ab
        L_0x0162:
            r0 = move-exception
            r2 = r0
            goto L_0x04d5
        L_0x0166:
            r0 = move-exception
            r2 = r0
            goto L_0x04ff
        L_0x016a:
            r0 = move-exception
            r2 = r0
            goto L_0x0516
        L_0x016e:
            r0 = move-exception
            r2 = r0
            goto L_0x052d
        L_0x0172:
            r6 = r18
            r8 = r19
        L_0x0176:
            java.lang.String r2 = "application/zip"
            boolean r2 = r2.equals(r9)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            if (r2 == 0) goto L_0x01f3
            r2 = r6
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r2 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r2     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            if (r15 != 0) goto L_0x0188
            int r3 = r2.getContentType()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            goto L_0x018c
        L_0x0188:
            int r3 = r2.setContentType(r15)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
        L_0x018c:
            r4 = r3 & 4
            if (r4 <= 0) goto L_0x01a1
            int r4 = r2.applicationImageSize()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            r5 = 4
            int r4 = r4 % r5
            if (r4 != 0) goto L_0x0199
            goto L_0x01a1
        L_0x0199:
            no.nordicsemi.android.dfu.internal.exception.SizeValidationException r2 = new no.nordicsemi.android.dfu.internal.exception.SizeValidationException     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            java.lang.String r3 = "Application firmware is not word-aligned."
            r2.<init>(r3)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            throw r2     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
        L_0x01a1:
            r4 = r3 & 2
            if (r4 <= 0) goto L_0x01b6
            int r4 = r2.bootloaderImageSize()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            r5 = 4
            int r4 = r4 % r5
            if (r4 != 0) goto L_0x01ae
            goto L_0x01b6
        L_0x01ae:
            no.nordicsemi.android.dfu.internal.exception.SizeValidationException r2 = new no.nordicsemi.android.dfu.internal.exception.SizeValidationException     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            java.lang.String r3 = "Bootloader firmware is not word-aligned."
            r2.<init>(r3)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            throw r2     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
        L_0x01b6:
            r4 = r3 & 1
            if (r4 <= 0) goto L_0x01cb
            int r4 = r2.softDeviceImageSize()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            r5 = 4
            int r4 = r4 % r5
            if (r4 != 0) goto L_0x01c3
            goto L_0x01cb
        L_0x01c3:
            no.nordicsemi.android.dfu.internal.exception.SizeValidationException r2 = new no.nordicsemi.android.dfu.internal.exception.SizeValidationException     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            java.lang.String r3 = "Soft Device firmware is not word-aligned."
            r2.<init>(r3)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            throw r2     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
        L_0x01cb:
            r4 = 4
            if (r3 != r4) goto L_0x01e0
            byte[] r4 = r2.getApplicationInit()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            if (r4 == 0) goto L_0x01f0
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            byte[] r2 = r2.getApplicationInit()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            r4.<init>(r2)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
        L_0x01dd:
            r5 = r3
            r7 = r4
            goto L_0x01f5
        L_0x01e0:
            byte[] r4 = r2.getSystemInit()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            if (r4 == 0) goto L_0x01f0
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            byte[] r2 = r2.getSystemInit()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            r4.<init>(r2)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            goto L_0x01dd
        L_0x01f0:
            r5 = r3
            r7 = r8
            goto L_0x01f5
        L_0x01f3:
            r7 = r8
            r5 = r15
        L_0x01f5:
            if (r11 == 0) goto L_0x0207
            int r2 = r6.available()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            r6.mark(r2)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            if (r7 == 0) goto L_0x0207
            int r2 = r7.available()     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            r7.mark(r2)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
        L_0x0207:
            r1.mFirmwareInputStream = r6     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            r1.mInitFileInputStream = r7     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            java.lang.String r2 = "Firmware file opened successfully"
            r3 = 5
            r1.sendLogBroadcast(r3, r2)     // Catch:{ SecurityException -> 0x016e, FileNotFoundException -> 0x016a, SizeValidationException -> 0x0166, IOException -> 0x0162, Exception -> 0x015e }
            if (r11 != 0) goto L_0x021b
            r2 = 1000(0x3e8, float:1.401E-42)
            r1.waitFor(r2)     // Catch:{ all -> 0x0544 }
            r1.waitFor(r2)     // Catch:{ all -> 0x0544 }
        L_0x021b:
            no.nordicsemi.android.dfu.DfuProgressInfo r2 = new no.nordicsemi.android.dfu.DfuProgressInfo     // Catch:{ all -> 0x0544 }
            r2.<init>(r1)     // Catch:{ all -> 0x0544 }
            r1.mProgressInfo = r2     // Catch:{ all -> 0x0544 }
            boolean r2 = r1.mAborted     // Catch:{ all -> 0x0544 }
            r8 = -7
            if (r2 == 0) goto L_0x023e
            java.lang.String r2 = "Upload aborted"
            r1.logw(r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = "Upload aborted"
            r3 = 15
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
            no.nordicsemi.android.dfu.DfuProgressInfo r2 = r1.mProgressInfo     // Catch:{ all -> 0x0544 }
            r2.setProgress(r8)     // Catch:{ all -> 0x0544 }
            if (r12 == 0) goto L_0x023d
            r1.stopForeground(r10)
        L_0x023d:
            return
        L_0x023e:
            java.lang.String r2 = "Connecting to DFU target..."
            r4 = 1
            r1.sendLogBroadcast(r4, r2)     // Catch:{ all -> 0x0544 }
            no.nordicsemi.android.dfu.DfuProgressInfo r2 = r1.mProgressInfo     // Catch:{ all -> 0x0544 }
            r4 = -1
            r2.setProgress(r4)     // Catch:{ all -> 0x0544 }
            r2 = r17
            android.bluetooth.BluetoothGatt r9 = r1.connect(r2)     // Catch:{ all -> 0x0544 }
            if (r9 != 0) goto L_0x0269
            java.lang.String r2 = "Bluetooth adapter disabled"
            r1.loge(r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = "Bluetooth adapter disabled"
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
            r2 = 4106(0x100a, float:5.754E-42)
            r1.report(r2)     // Catch:{ all -> 0x0544 }
            if (r12 == 0) goto L_0x0268
            r1.stopForeground(r10)
        L_0x0268:
            return
        L_0x0269:
            int r4 = r1.mConnectionState     // Catch:{ all -> 0x0544 }
            if (r4 != 0) goto L_0x02ac
            int r3 = r1.mError     // Catch:{ all -> 0x0544 }
            r4 = 32901(0x8085, float:4.6104E-41)
            if (r3 != r4) goto L_0x0295
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0544 }
            r3.<init>()     // Catch:{ all -> 0x0544 }
            java.lang.String r4 = "Device not reachable. Check if the device with address "
            r3.append(r4)     // Catch:{ all -> 0x0544 }
            r3.append(r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = " is in range, is advertising and is connectable"
            r3.append(r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0544 }
            r1.loge(r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = "Error 133: Connection timeout"
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
            goto L_0x02a1
        L_0x0295:
            java.lang.String r2 = "Device got disconnected before service discovery finished"
            r1.loge(r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = "Disconnected"
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
        L_0x02a1:
            r2 = 4096(0x1000, float:5.74E-42)
            r1.terminateConnection(r9, r2)     // Catch:{ all -> 0x0544 }
            if (r12 == 0) goto L_0x02ab
            r1.stopForeground(r10)
        L_0x02ab:
            return
        L_0x02ac:
            int r2 = r1.mError     // Catch:{ all -> 0x0544 }
            r11 = -32769(0xffffffffffff7fff, float:NaN)
            r13 = 32768(0x8000, float:4.5918E-41)
            if (r2 <= 0) goto L_0x0370
            int r2 = r1.mError     // Catch:{ all -> 0x0544 }
            r2 = r2 & r13
            if (r2 <= 0) goto L_0x02f1
            int r2 = r1.mError     // Catch:{ all -> 0x0544 }
            r2 = r2 & r11
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0544 }
            r3.<init>()     // Catch:{ all -> 0x0544 }
            java.lang.String r4 = "An error occurred while connecting to the device:"
            r3.append(r4)     // Catch:{ all -> 0x0544 }
            r3.append(r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0544 }
            r1.loge(r3)     // Catch:{ all -> 0x0544 }
            java.util.Locale r3 = java.util.Locale.US     // Catch:{ all -> 0x0544 }
            java.lang.String r4 = "Connection failed (0x%02X): %s"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x0544 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0544 }
            r7 = 0
            r5[r7] = r6     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = no.nordicsemi.android.error.GattError.a(r2)     // Catch:{ all -> 0x0544 }
            r6 = 1
            r5[r6] = r2     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = java.lang.String.format(r3, r4, r5)     // Catch:{ all -> 0x0544 }
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
            goto L_0x0327
        L_0x02f1:
            int r2 = r1.mError     // Catch:{ all -> 0x0544 }
            r2 = r2 & -16385(0xffffffffffffbfff, float:NaN)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0544 }
            r3.<init>()     // Catch:{ all -> 0x0544 }
            java.lang.String r4 = "An error occurred during discovering services:"
            r3.append(r4)     // Catch:{ all -> 0x0544 }
            r3.append(r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0544 }
            r1.loge(r3)     // Catch:{ all -> 0x0544 }
            java.util.Locale r3 = java.util.Locale.US     // Catch:{ all -> 0x0544 }
            java.lang.String r4 = "Connection failed (0x%02X): %s"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x0544 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0544 }
            r7 = 0
            r5[r7] = r6     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = no.nordicsemi.android.error.GattError.b(r2)     // Catch:{ all -> 0x0544 }
            r6 = 1
            r5[r6] = r2     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = java.lang.String.format(r3, r4, r5)     // Catch:{ all -> 0x0544 }
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
        L_0x0327:
            java.lang.String r2 = "no.nordicsemi.android.dfu.extra.EXTRA_ATTEMPT"
            r3 = 0
            r14 = r21
            int r2 = r14.getIntExtra(r2, r3)     // Catch:{ all -> 0x0544 }
            if (r2 != 0) goto L_0x0365
            java.lang.String r2 = "Retrying..."
            r3 = 15
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
            int r2 = r1.mConnectionState     // Catch:{ all -> 0x0544 }
            if (r2 == 0) goto L_0x0340
            r1.disconnect(r9)     // Catch:{ all -> 0x0544 }
        L_0x0340:
            r2 = 1
            r1.refreshDeviceCache(r9, r2)     // Catch:{ all -> 0x0544 }
            r1.close(r9)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = "Restarting the service"
            r1.logi(r2)     // Catch:{ all -> 0x0544 }
            android.content.Intent r2 = new android.content.Intent     // Catch:{ all -> 0x0544 }
            r2.<init>()     // Catch:{ all -> 0x0544 }
            r3 = 24
            r2.fillIn(r14, r3)     // Catch:{ all -> 0x0544 }
            java.lang.String r3 = "no.nordicsemi.android.dfu.extra.EXTRA_ATTEMPT"
            r4 = 1
            r2.putExtra(r3, r4)     // Catch:{ all -> 0x0544 }
            r1.startService(r2)     // Catch:{ all -> 0x0544 }
            if (r12 == 0) goto L_0x0364
            r1.stopForeground(r10)
        L_0x0364:
            return
        L_0x0365:
            int r2 = r1.mError     // Catch:{ all -> 0x0544 }
            r1.terminateConnection(r9, r2)     // Catch:{ all -> 0x0544 }
            if (r12 == 0) goto L_0x036f
            r1.stopForeground(r10)
        L_0x036f:
            return
        L_0x0370:
            r14 = r21
            r15 = 2
            boolean r2 = r1.mAborted     // Catch:{ all -> 0x0544 }
            if (r2 == 0) goto L_0x0392
            java.lang.String r2 = "Upload aborted"
            r1.logw(r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = "Upload aborted"
            r3 = 15
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
            r2 = 0
            r1.terminateConnection(r9, r2)     // Catch:{ all -> 0x0544 }
            no.nordicsemi.android.dfu.DfuProgressInfo r2 = r1.mProgressInfo     // Catch:{ all -> 0x0544 }
            r2.setProgress(r8)     // Catch:{ all -> 0x0544 }
            if (r12 == 0) goto L_0x0391
            r1.stopForeground(r10)
        L_0x0391:
            return
        L_0x0392:
            java.lang.String r2 = "Services discovered"
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = "no.nordicsemi.android.dfu.extra.EXTRA_ATTEMPT"
            r3 = 0
            r14.putExtra(r2, r3)     // Catch:{ all -> 0x0544 }
            r2 = 0
            no.nordicsemi.android.dfu.DfuServiceProvider r3 = new no.nordicsemi.android.dfu.DfuServiceProvider     // Catch:{ UploadAbortedException -> 0x0484, DeviceDisconnectedException -> 0x0466, DfuException -> 0x0409 }
            r3.<init>()     // Catch:{ UploadAbortedException -> 0x0484, DeviceDisconnectedException -> 0x0466, DfuException -> 0x0409 }
            r1.mDfuServiceImpl = r3     // Catch:{ UploadAbortedException -> 0x0484, DeviceDisconnectedException -> 0x0466, DfuException -> 0x0409 }
            no.nordicsemi.android.dfu.DfuService r4 = r3.getServiceImpl(r14, r1, r9)     // Catch:{ UploadAbortedException -> 0x0484, DeviceDisconnectedException -> 0x0466, DfuException -> 0x0409 }
            r1.mDfuServiceImpl = r4     // Catch:{ UploadAbortedException -> 0x0400, DeviceDisconnectedException -> 0x03fc, DfuException -> 0x03f9, all -> 0x03f6 }
            if (r4 != 0) goto L_0x03d3
            java.lang.String r2 = "DfuBaseService"
            java.lang.String r3 = "DFU Service not found."
            android.util.Log.w(r2, r3)     // Catch:{ UploadAbortedException -> 0x03d0, DeviceDisconnectedException -> 0x03cd, DfuException -> 0x03cb }
            java.lang.String r2 = "DFU Service not found"
            r3 = 15
            r1.sendLogBroadcast(r3, r2)     // Catch:{ UploadAbortedException -> 0x03d0, DeviceDisconnectedException -> 0x03cd, DfuException -> 0x03cb }
            r2 = 4102(0x1006, float:5.748E-42)
            r1.terminateConnection(r9, r2)     // Catch:{ UploadAbortedException -> 0x03d0, DeviceDisconnectedException -> 0x03cd, DfuException -> 0x03cb }
            if (r4 == 0) goto L_0x03c5
            r4.release()     // Catch:{ all -> 0x0544 }
        L_0x03c5:
            if (r12 == 0) goto L_0x03ca
            r1.stopForeground(r10)
        L_0x03ca:
            return
        L_0x03cb:
            r0 = move-exception
            goto L_0x040b
        L_0x03cd:
            r0 = move-exception
            goto L_0x0468
        L_0x03d0:
            r2 = r4
            goto L_0x0484
        L_0x03d3:
            r2 = r4
            r3 = r21
            r8 = r4
            r4 = r9
            boolean r2 = r2.initialize(r3, r4, r5, r6, r7)     // Catch:{ UploadAbortedException -> 0x0401, DeviceDisconnectedException -> 0x03f1, DfuException -> 0x03ed, all -> 0x03e8 }
            if (r2 == 0) goto L_0x03e1
            r8.performDfu(r14)     // Catch:{ UploadAbortedException -> 0x0401, DeviceDisconnectedException -> 0x03f1, DfuException -> 0x03ed, all -> 0x03e8 }
        L_0x03e1:
            if (r8 == 0) goto L_0x049f
            r8.release()     // Catch:{ all -> 0x0544 }
            goto L_0x049f
        L_0x03e8:
            r0 = move-exception
            r2 = r0
            r4 = r8
            goto L_0x04a5
        L_0x03ed:
            r0 = move-exception
            r2 = r0
            r4 = r8
            goto L_0x040c
        L_0x03f1:
            r0 = move-exception
            r2 = r0
            r4 = r8
            goto L_0x0469
        L_0x03f6:
            r0 = move-exception
            r8 = r4
            goto L_0x0406
        L_0x03f9:
            r0 = move-exception
            r8 = r4
            goto L_0x040b
        L_0x03fc:
            r0 = move-exception
            r8 = r4
            goto L_0x0468
        L_0x0400:
            r8 = r4
        L_0x0401:
            r2 = r8
            goto L_0x0484
        L_0x0404:
            r0 = move-exception
            r4 = r2
        L_0x0406:
            r2 = r0
            goto L_0x04a5
        L_0x0409:
            r0 = move-exception
            r4 = r2
        L_0x040b:
            r2 = r0
        L_0x040c:
            int r3 = r2.getErrorNumber()     // Catch:{ all -> 0x0482 }
            r5 = r3 & r13
            if (r5 <= 0) goto L_0x0433
            r3 = r3 & r11
            java.util.Locale r5 = java.util.Locale.US     // Catch:{ all -> 0x0482 }
            java.lang.String r6 = "Error (0x%02X): %s"
            java.lang.Object[] r7 = new java.lang.Object[r15]     // Catch:{ all -> 0x0482 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0482 }
            r11 = 0
            r7[r11] = r8     // Catch:{ all -> 0x0482 }
            java.lang.String r3 = no.nordicsemi.android.error.GattError.a(r3)     // Catch:{ all -> 0x0482 }
            r8 = 1
            r7[r8] = r3     // Catch:{ all -> 0x0482 }
            java.lang.String r3 = java.lang.String.format(r5, r6, r7)     // Catch:{ all -> 0x0482 }
            r5 = 20
            r1.sendLogBroadcast(r5, r3)     // Catch:{ all -> 0x0482 }
            goto L_0x0452
        L_0x0433:
            r3 = r3 & -16385(0xffffffffffffbfff, float:NaN)
            java.util.Locale r5 = java.util.Locale.US     // Catch:{ all -> 0x0482 }
            java.lang.String r6 = "Error (0x%02X): %s"
            java.lang.Object[] r7 = new java.lang.Object[r15]     // Catch:{ all -> 0x0482 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0482 }
            r11 = 0
            r7[r11] = r8     // Catch:{ all -> 0x0482 }
            java.lang.String r3 = no.nordicsemi.android.error.GattError.b(r3)     // Catch:{ all -> 0x0482 }
            r8 = 1
            r7[r8] = r3     // Catch:{ all -> 0x0482 }
            java.lang.String r3 = java.lang.String.format(r5, r6, r7)     // Catch:{ all -> 0x0482 }
            r5 = 20
            r1.sendLogBroadcast(r5, r3)     // Catch:{ all -> 0x0482 }
        L_0x0452:
            java.lang.String r3 = r2.getMessage()     // Catch:{ all -> 0x0482 }
            r1.loge(r3)     // Catch:{ all -> 0x0482 }
            int r2 = r2.getErrorNumber()     // Catch:{ all -> 0x0482 }
            r1.terminateConnection(r9, r2)     // Catch:{ all -> 0x0482 }
            if (r4 == 0) goto L_0x049f
        L_0x0462:
            r4.release()     // Catch:{ all -> 0x0544 }
            goto L_0x049f
        L_0x0466:
            r0 = move-exception
            r4 = r2
        L_0x0468:
            r2 = r0
        L_0x0469:
            java.lang.String r3 = "Device has disconnected"
            r5 = 20
            r1.sendLogBroadcast(r5, r3)     // Catch:{ all -> 0x0482 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0482 }
            r1.loge(r2)     // Catch:{ all -> 0x0482 }
            r1.close(r9)     // Catch:{ all -> 0x0482 }
            r2 = 4096(0x1000, float:5.74E-42)
            r1.report(r2)     // Catch:{ all -> 0x0482 }
            if (r4 == 0) goto L_0x049f
            goto L_0x0462
        L_0x0482:
            r0 = move-exception
            goto L_0x0406
        L_0x0484:
            java.lang.String r3 = "Upload aborted"
            r1.logw(r3)     // Catch:{ all -> 0x0404 }
            java.lang.String r3 = "Upload aborted"
            r4 = 15
            r1.sendLogBroadcast(r4, r3)     // Catch:{ all -> 0x0404 }
            r3 = 0
            r1.terminateConnection(r9, r3)     // Catch:{ all -> 0x0404 }
            no.nordicsemi.android.dfu.DfuProgressInfo r3 = r1.mProgressInfo     // Catch:{ all -> 0x0404 }
            r4 = -7
            r3.setProgress(r4)     // Catch:{ all -> 0x0404 }
            if (r2 == 0) goto L_0x049f
            r2.release()     // Catch:{ all -> 0x0544 }
        L_0x049f:
            if (r12 == 0) goto L_0x04a4
            r1.stopForeground(r10)
        L_0x04a4:
            return
        L_0x04a5:
            if (r4 == 0) goto L_0x04aa
            r4.release()     // Catch:{ all -> 0x0544 }
        L_0x04aa:
            throw r2     // Catch:{ all -> 0x0544 }
        L_0x04ab:
            java.lang.String r3 = "An exception occurred while opening files. Did you set the firmware file?"
            r1.loge(r3, r2)     // Catch:{ all -> 0x0544 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0544 }
            r3.<init>()     // Catch:{ all -> 0x0544 }
            java.lang.String r4 = "Opening file failed: "
            r3.append(r4)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = r2.getLocalizedMessage()     // Catch:{ all -> 0x0544 }
            r3.append(r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0544 }
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
            r2 = 4098(0x1002, float:5.743E-42)
            r1.report(r2)     // Catch:{ all -> 0x0544 }
            if (r12 == 0) goto L_0x04d4
            r1.stopForeground(r10)
        L_0x04d4:
            return
        L_0x04d5:
            java.lang.String r3 = "An exception occurred while calculating file size"
            r1.loge(r3, r2)     // Catch:{ all -> 0x0544 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0544 }
            r3.<init>()     // Catch:{ all -> 0x0544 }
            java.lang.String r4 = "Opening file failed: "
            r3.append(r4)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = r2.getLocalizedMessage()     // Catch:{ all -> 0x0544 }
            r3.append(r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0544 }
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
            r2 = 4098(0x1002, float:5.743E-42)
            r1.report(r2)     // Catch:{ all -> 0x0544 }
            if (r12 == 0) goto L_0x04fe
            r1.stopForeground(r10)
        L_0x04fe:
            return
        L_0x04ff:
            java.lang.String r3 = "Firmware not word-aligned"
            r1.loge(r3, r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = "Opening file failed: Firmware size must be word-aligned"
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
            r2 = 4108(0x100c, float:5.757E-42)
            r1.report(r2)     // Catch:{ all -> 0x0544 }
            if (r12 == 0) goto L_0x0515
            r1.stopForeground(r10)
        L_0x0515:
            return
        L_0x0516:
            java.lang.String r3 = "An exception occurred while opening file"
            r1.loge(r3, r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = "Opening file failed: File not found"
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
            r2 = 4097(0x1001, float:5.741E-42)
            r1.report(r2)     // Catch:{ all -> 0x0544 }
            if (r12 == 0) goto L_0x052c
            r1.stopForeground(r10)
        L_0x052c:
            return
        L_0x052d:
            java.lang.String r3 = "A security exception occurred while opening file"
            r1.loge(r3, r2)     // Catch:{ all -> 0x0544 }
            java.lang.String r2 = "Opening file failed: Permission required"
            r3 = 20
            r1.sendLogBroadcast(r3, r2)     // Catch:{ all -> 0x0544 }
            r2 = 4097(0x1001, float:5.741E-42)
            r1.report(r2)     // Catch:{ all -> 0x0544 }
            if (r12 == 0) goto L_0x0543
            r1.stopForeground(r10)
        L_0x0543:
            return
        L_0x0544:
            r0 = move-exception
            r2 = r0
            if (r12 == 0) goto L_0x054b
            r1.stopForeground(r10)
        L_0x054b:
            throw r2
        L_0x054c:
            java.lang.String r2 = "File type or file mime-type not supported"
            r1.logw(r2)
            java.lang.String r2 = "File type or file mime-type not supported"
            r3 = 15
            r1.sendLogBroadcast(r3, r2)
            r2 = 4105(0x1009, float:5.752E-42)
            r1.report(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.DfuBaseService.onHandleIntent(android.content.Intent):void");
    }

    private InputStream openInputStream(String str, String str2, int i, int i2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        if (MIME_TYPE_ZIP.equals(str2)) {
            return new ArchiveInputStream(fileInputStream, i, i2);
        }
        return str.toLowerCase(Locale.US).endsWith("hex") ? new HexInputStream((InputStream) fileInputStream, i) : fileInputStream;
    }

    private InputStream openInputStream(Uri uri, String str, int i, int i2) throws IOException {
        InputStream openInputStream = getContentResolver().openInputStream(uri);
        if (MIME_TYPE_ZIP.equals(str)) {
            return new ArchiveInputStream(openInputStream, i, i2);
        }
        Cursor query = getContentResolver().query(uri, new String[]{"_display_name"}, (String) null, (String[]) null, (String) null);
        try {
            if (query.moveToNext() && query.getString(0).toLowerCase(Locale.US).endsWith("hex")) {
                return new HexInputStream(openInputStream, i);
            }
            query.close();
            return openInputStream;
        } finally {
            query.close();
        }
    }

    private InputStream openInputStream(int i, String str, int i2, int i3) throws IOException {
        InputStream openRawResource = getResources().openRawResource(i);
        if (MIME_TYPE_ZIP.equals(str)) {
            return new ArchiveInputStream(openRawResource, i2, i3);
        }
        openRawResource.mark(2);
        int read = openRawResource.read();
        openRawResource.reset();
        return read == 58 ? new HexInputStream(openRawResource, i2) : openRawResource;
    }

    /* access modifiers changed from: protected */
    public BluetoothGatt connect(String str) {
        if (!this.mBluetoothAdapter.isEnabled()) {
            return null;
        }
        this.mConnectionState = -1;
        logi("Connecting to the device...");
        BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str);
        sendLogBroadcast(0, "gatt = device.connectGatt(autoConnect = false)");
        BluetoothGatt connectGatt = remoteDevice.connectGatt(this, false, this.mGattCallback);
        try {
            synchronized (this.mLock) {
                while (true) {
                    if ((this.mConnectionState == -1 || this.mConnectionState == -2) && this.mError == 0) {
                        this.mLock.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
        return connectGatt;
    }

    /* access modifiers changed from: protected */
    public void terminateConnection(BluetoothGatt bluetoothGatt, int i) {
        if (this.mConnectionState != 0) {
            disconnect(bluetoothGatt);
        }
        refreshDeviceCache(bluetoothGatt, false);
        close(bluetoothGatt);
        waitFor(600);
        if (i != 0) {
            report(i);
        }
    }

    /* access modifiers changed from: protected */
    public void disconnect(BluetoothGatt bluetoothGatt) {
        if (this.mConnectionState != 0) {
            sendLogBroadcast(1, "Disconnecting...");
            this.mProgressInfo.setProgress(-5);
            this.mConnectionState = -4;
            logi("Disconnecting from the device...");
            sendLogBroadcast(0, "gatt.disconnect()");
            bluetoothGatt.disconnect();
            waitUntilDisconnected();
            sendLogBroadcast(5, "Disconnected");
        }
    }

    /* access modifiers changed from: protected */
    public void waitUntilDisconnected() {
        try {
            synchronized (this.mLock) {
                while (this.mConnectionState != 0 && this.mError == 0) {
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    /* access modifiers changed from: protected */
    public void waitFor(int i) {
        synchronized (this.mLock) {
            try {
                sendLogBroadcast(0, "wait(" + i + Operators.BRACKET_END_STR);
                this.mLock.wait((long) i);
            } catch (InterruptedException e) {
                loge("Sleeping interrupted", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void close(BluetoothGatt bluetoothGatt) {
        logi("Cleaning up...");
        sendLogBroadcast(0, "gatt.close()");
        bluetoothGatt.close();
        this.mConnectionState = -5;
    }

    /* access modifiers changed from: protected */
    public void refreshDeviceCache(BluetoothGatt bluetoothGatt, boolean z) {
        if (z || bluetoothGatt.getDevice().getBondState() == 10) {
            sendLogBroadcast(0, "gatt.refresh() (hidden)");
            try {
                Method method = bluetoothGatt.getClass().getMethod("refresh", new Class[0]);
                if (method != null) {
                    boolean booleanValue = ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
                    logi("Refreshing result: " + booleanValue);
                }
            } catch (Exception e) {
                loge("An exception occurred while refreshing device", e);
                sendLogBroadcast(15, "Refreshing failed");
            }
        }
    }

    public void updateProgressNotification() {
        String str;
        DfuProgressInfo dfuProgressInfo = this.mProgressInfo;
        int progress = dfuProgressInfo.getProgress();
        if (this.mLastProgress != progress) {
            this.mLastProgress = progress;
            sendProgressBroadcast(dfuProgressInfo);
            if (!this.mDisableNotification) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (elapsedRealtime - this.mLastNotificationTime >= 250 || -6 == progress || -7 == progress) {
                    this.mLastNotificationTime = elapsedRealtime;
                    String str2 = this.mDeviceAddress;
                    String string = this.mDeviceName != null ? this.mDeviceName : getString(R.string.dfu_unknown_name);
                    NotificationCompat.Builder onlyAlertOnce = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_DFU).setSmallIcon(17301640).setOnlyAlertOnce(true);
                    onlyAlertOnce.setColor(Color.c);
                    switch (progress) {
                        case -7:
                            onlyAlertOnce.setOngoing(false).setContentTitle(getString(R.string.dfu_status_aborted)).setSmallIcon(17301641).setContentText(getString(R.string.dfu_status_aborted_msg)).setAutoCancel(true);
                            break;
                        case -6:
                            onlyAlertOnce.setOngoing(false).setContentTitle(getString(R.string.dfu_status_completed)).setSmallIcon(17301641).setContentText(getString(R.string.dfu_status_completed_msg)).setAutoCancel(true).setColor(-16730086);
                            break;
                        case -5:
                            onlyAlertOnce.setOngoing(true).setContentTitle(getString(R.string.dfu_status_disconnecting)).setContentText(getString(R.string.dfu_status_disconnecting_msg, new Object[]{string})).setProgress(100, 0, true);
                            break;
                        case -4:
                            onlyAlertOnce.setOngoing(true).setContentTitle(getString(R.string.dfu_status_validating)).setContentText(getString(R.string.dfu_status_validating_msg)).setProgress(100, 0, true);
                            break;
                        case -3:
                            onlyAlertOnce.setOngoing(true).setContentTitle(getString(R.string.dfu_status_switching_to_dfu)).setContentText(getString(R.string.dfu_status_switching_to_dfu_msg)).setProgress(100, 0, true);
                            break;
                        case -2:
                            onlyAlertOnce.setOngoing(true).setContentTitle(getString(R.string.dfu_status_starting)).setContentText(getString(R.string.dfu_status_starting_msg)).setProgress(100, 0, true);
                            break;
                        case -1:
                            onlyAlertOnce.setOngoing(true).setContentTitle(getString(R.string.dfu_status_connecting)).setContentText(getString(R.string.dfu_status_connecting_msg, new Object[]{string})).setProgress(100, 0, true);
                            break;
                        default:
                            if (dfuProgressInfo.getTotalParts() == 1) {
                                str = getString(R.string.dfu_status_uploading);
                            } else {
                                str = getString(R.string.dfu_status_uploading_part, new Object[]{Integer.valueOf(dfuProgressInfo.getCurrentPart()), Integer.valueOf(dfuProgressInfo.getTotalParts())});
                            }
                            onlyAlertOnce.setOngoing(true).setContentTitle(str).setContentText(getString(R.string.dfu_status_uploading_msg, new Object[]{string})).setProgress(100, progress, false);
                            break;
                    }
                    Intent intent = new Intent(this, getNotificationTarget());
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.putExtra(EXTRA_DEVICE_ADDRESS, str2);
                    intent.putExtra(EXTRA_DEVICE_NAME, string);
                    intent.putExtra(EXTRA_PROGRESS, progress);
                    onlyAlertOnce.setContentIntent(PendingIntent.getActivity(this, 0, intent, 134217728));
                    updateProgressNotification(onlyAlertOnce, progress);
                    ((NotificationManager) getSystemService(PushManager.MESSAGE_TYPE_NOTI)).notify(283, onlyAlertOnce.build());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateProgressNotification(NotificationCompat.Builder builder, int i) {
        if (i != -7 && i != -6) {
            Intent intent = new Intent(BROADCAST_ACTION);
            intent.putExtra(EXTRA_ACTION, 2);
            builder.addAction(R.drawable.ic_action_notify_cancel, getString(R.string.dfu_action_abort), PendingIntent.getBroadcast(this, 1, intent, 134217728));
        }
    }

    private void report(int i) {
        sendErrorBroadcast(i);
        if (!this.mDisableNotification) {
            String str = this.mDeviceAddress;
            String string = this.mDeviceName != null ? this.mDeviceName : getString(R.string.dfu_unknown_name);
            NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_DFU).setSmallIcon(17301640).setOnlyAlertOnce(true).setColor(-65536).setOngoing(false).setContentTitle(getString(R.string.dfu_status_error)).setSmallIcon(17301641).setContentText(getString(R.string.dfu_status_error_msg)).setAutoCancel(true);
            Intent intent = new Intent(this, getNotificationTarget());
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            intent.putExtra(EXTRA_DEVICE_ADDRESS, str);
            intent.putExtra(EXTRA_DEVICE_NAME, string);
            intent.putExtra(EXTRA_PROGRESS, i);
            autoCancel.setContentIntent(PendingIntent.getActivity(this, 0, intent, 134217728));
            updateErrorNotification(autoCancel);
            ((NotificationManager) getSystemService(PushManager.MESSAGE_TYPE_NOTI)).notify(283, autoCancel.build());
        }
    }

    private void startForeground() {
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_DFU).setSmallIcon(17301640).setContentTitle(getString(R.string.dfu_status_foreground_title)).setContentText(getString(R.string.dfu_status_foreground_content)).setColor(Color.c).setPriority(-1).setOngoing(true);
        Class<? extends Activity> notificationTarget = getNotificationTarget();
        if (notificationTarget != null) {
            Intent intent = new Intent(this, notificationTarget);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
            intent.putExtra(EXTRA_DEVICE_NAME, this.mDeviceName);
            ongoing.setContentIntent(PendingIntent.getActivity(this, 0, intent, 134217728));
        } else {
            logw("getNotificationTarget() should not return null if the service is to be started as a foreground service");
        }
        updateForegroundNotification(ongoing);
        startForeground(283, ongoing.build());
    }

    private void sendProgressBroadcast(DfuProgressInfo dfuProgressInfo) {
        Intent intent = new Intent(BROADCAST_PROGRESS);
        intent.putExtra(EXTRA_DATA, dfuProgressInfo.getProgress());
        intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
        intent.putExtra(EXTRA_PART_CURRENT, dfuProgressInfo.getCurrentPart());
        intent.putExtra(EXTRA_PARTS_TOTAL, dfuProgressInfo.getTotalParts());
        intent.putExtra(EXTRA_SPEED_B_PER_MS, dfuProgressInfo.getSpeed());
        intent.putExtra(EXTRA_AVG_SPEED_B_PER_MS, dfuProgressInfo.getAverageSpeed());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void sendErrorBroadcast(int i) {
        Intent intent = new Intent(BROADCAST_ERROR);
        if ((i & 16384) > 0) {
            intent.putExtra(EXTRA_DATA, i & -16385);
            intent.putExtra(EXTRA_ERROR_TYPE, 2);
        } else if ((32768 & i) > 0) {
            intent.putExtra(EXTRA_DATA, i & -32769);
            intent.putExtra(EXTRA_ERROR_TYPE, 1);
        } else if ((i & 8192) > 0) {
            intent.putExtra(EXTRA_DATA, i & -8193);
            intent.putExtra(EXTRA_ERROR_TYPE, 3);
        } else {
            intent.putExtra(EXTRA_DATA, i);
            intent.putExtra(EXTRA_ERROR_TYPE, 0);
        }
        intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* access modifiers changed from: package-private */
    public void sendLogBroadcast(int i, String str) {
        Intent intent = new Intent(BROADCAST_LOG);
        intent.putExtra(EXTRA_LOG_MESSAGE, "[DFU] " + str);
        intent.putExtra(EXTRA_LOG_LEVEL, i);
        intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private boolean initialize() {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BleDevice.f14751a);
        if (bluetoothManager == null) {
            loge("Unable to initialize BluetoothManager.");
            return false;
        }
        this.mBluetoothAdapter = bluetoothManager.getAdapter();
        if (this.mBluetoothAdapter != null) {
            return true;
        }
        loge("Unable to obtain a BluetoothAdapter.");
        return false;
    }

    /* access modifiers changed from: private */
    public void loge(String str) {
        Log.e(TAG, str);
    }

    private void loge(String str, Throwable th) {
        Log.e(TAG, str, th);
    }

    /* access modifiers changed from: private */
    public void logw(String str) {
        if (DEBUG) {
            Log.w(TAG, str);
        }
    }

    /* access modifiers changed from: private */
    public void logi(String str) {
        if (DEBUG) {
            Log.i(TAG, str);
        }
    }
}
