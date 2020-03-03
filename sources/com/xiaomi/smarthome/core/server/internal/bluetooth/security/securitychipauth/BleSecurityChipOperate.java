package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class BleSecurityChipOperate {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14373a = "0000%04x-0065-6C62-2E74-6F696D2E696D";
    private static final int b = 12;
    private static UUID c = a(4096);
    private static UUID d = a(4097);
    private static UUID e = a(4098);
    private static UUID f = a(4099);
    private static byte[] g = {0};
    private static byte[] h = {1};
    private static byte[] i = {2};
    /* access modifiers changed from: private */
    public static Map<String, LastOperator> j = new ConcurrentHashMap();
    private static BroadcastReceiver k = null;
    /* access modifiers changed from: private */
    public static ScheduledExecutorService l = Executors.newScheduledThreadPool(3);

    private static UUID a(int i2) {
        return UUID.fromString(String.format(f14373a, new Object[]{Integer.valueOf(i2)}));
    }

    public static synchronized void a(String str, int i2, BleReadResponse bleReadResponse) {
        synchronized (BleSecurityChipOperate.class) {
            if (k == null) {
                k = new BluetoothChangeReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.xiaomi.smarthome.bluetooth.connect_status_changed");
                intentFilter.addAction("com.xiaomi.smarthome.bluetooth.character_changed");
                BluetoothUtils.a(k, intentFilter);
            }
            switch (i2) {
                case 0:
                    a(str, g, bleReadResponse);
                    break;
                case 1:
                    a(str, h, bleReadResponse);
                    break;
                case 2:
                    a(str, i, bleReadResponse);
                    break;
                default:
                    if (bleReadResponse != null) {
                        BluetoothMyLogger.d(String.format("operator(%d) is invalid", new Object[]{Integer.valueOf(i2)}));
                        bleReadResponse.a(-1, null);
                        break;
                    }
                    break;
            }
        }
    }

    private static void a(final String str, final byte[] bArr, final BleReadResponse bleReadResponse) {
        if (j.get(str) != null) {
            BluetoothMyLogger.d("有上一次开锁操作正在进行，等待操作完成...");
            if (bleReadResponse != null) {
                bleReadResponse.a(-13, null);
                return;
            }
            return;
        }
        BleConnectManager.a().a(str, c, f, (BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
            }
        });
        BleConnectManager.a().a(str, c, e, (BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("sendOperator notify code = " + i);
                if (i == 0) {
                    BleSecurityChipEncrypt.a(str, bArr, (BleReadResponse) new BleReadResponse() {
                        public void a(int i, byte[] bArr) {
                            if (i == 0) {
                                BluetoothMyLogger.d("operator encrypt success  code=" + i);
                                LastOperator lastOperator = new LastOperator();
                                lastOperator.f14381a = bleReadResponse;
                                BleSecurityChipOperate.j.put(str, lastOperator);
                                lastOperator.b = BleSecurityChipOperate.l.schedule(new OperatorTimeoutRunnable(str), 12, TimeUnit.SECONDS);
                                BleSecurityChipOperate.d(str, bArr);
                                return;
                            }
                            BluetoothMyLogger.d("operator encrypt failed  code=" + i + "  mac=" + str + "  operator=" + bArr);
                            if (bleReadResponse != null) {
                                bleReadResponse.a(-1, null);
                            }
                        }
                    });
                } else if (bleReadResponse != null) {
                    BluetoothMyLogger.d("Code.REQUEST_NOTIFY_FAILED code = " + i);
                    bleReadResponse.a(-27, null);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void d(final String str, byte[] bArr) {
        BleConnectManager.a().a(str, c, d, bArr, (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
                if (i != 0) {
                    BleSecurityChipOperate.b(str, -1, (byte[]) null);
                }
            }
        });
    }

    private static class BluetoothChangeReceiver extends BroadcastReceiver {
        private BluetoothChangeReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.hasExtra("key_device_address")) {
                String stringExtra = intent.getStringExtra("key_device_address");
                String action = intent.getAction();
                if (TextUtils.equals(action, "com.xiaomi.smarthome.bluetooth.connect_status_changed")) {
                    if (intent.getIntExtra("key_connect_status", 0) == 32) {
                        BleSecurityChipOperate.b(stringExtra, -1, (byte[]) null);
                    }
                } else if (TextUtils.equals(action, "com.xiaomi.smarthome.bluetooth.character_changed")) {
                    BleSecurityChipOperate.b(stringExtra, intent);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(final String str, Intent intent) {
        UUID uuid = (UUID) intent.getSerializableExtra("key_character_uuid");
        byte[] byteArrayExtra = intent.getByteArrayExtra("key_character_value");
        if (!c.equals((UUID) intent.getSerializableExtra("key_service_uuid"))) {
            return;
        }
        if (e.equals(uuid)) {
            BleSecurityChipEncrypt.b(str, byteArrayExtra, new BleReadResponse() {
                public void a(int i, byte[] bArr) {
                    BluetoothMyLogger.e(String.format("decrypt lock state: code = %d, data = %s", new Object[]{Integer.valueOf(i), ByteUtils.d(bArr)}));
                    if (i == 0) {
                        BleSecurityChipOperate.b(str, 0, bArr);
                        return;
                    }
                    BluetoothMyLogger.d("operator decrypt failed");
                    BleSecurityChipOperate.b(str, -1, (byte[]) null);
                }
            });
        } else if (f.equals(uuid)) {
            BleSecurityChipEncrypt.b(str, byteArrayExtra, new BleReadResponse() {
                public void a(int i, byte[] bArr) {
                    BluetoothMyLogger.e(String.format("decrypt lock log: code = %d, data = %s", new Object[]{Integer.valueOf(i), ByteUtils.d(bArr)}));
                    if (i == 0) {
                        BleSecurityChipOperate.e(str, bArr);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void e(final String str, final byte[] bArr) {
        a(str, (BleResponse<String>) new BleResponse<String>() {
            public void a(int i, String str) {
                if (i != 0 || TextUtils.isEmpty(str)) {
                    BluetoothMyLogger.c("reportLockEvent, get ble firmware version failed");
                    return;
                }
                BluetoothMyLogger.e("reportLockEvent, get ble firmware version = " + str);
                String f = BluetoothCache.f(str);
                String[] split = str.split("[._]");
                if (split.length >= 3) {
                    try {
                        int intValue = Integer.valueOf(split[0]).intValue();
                        int intValue2 = Integer.valueOf(split[1]).intValue();
                        int intValue3 = Integer.valueOf(split[2]).intValue();
                        if (intValue < 2) {
                            BleSecurityChipOperate.b(f, "5", ByteUtils.d(bArr));
                        } else if (intValue != 2) {
                            BleSecurityChipOperate.f(f, bArr);
                        } else if (intValue2 < 1) {
                            BleSecurityChipOperate.b(f, "5", ByteUtils.d(bArr));
                        } else if (intValue2 == 1) {
                            if (intValue3 < 7) {
                                BleSecurityChipOperate.b(f, "5", ByteUtils.d(bArr));
                            } else if (intValue3 < 11) {
                                BleSecurityChipOperate.b(f, "11", ByteUtils.d(bArr));
                            } else {
                                BleSecurityChipOperate.f(f, bArr);
                            }
                        } else if (intValue2 != 2) {
                            BleSecurityChipOperate.f(f, bArr);
                        } else if (intValue3 < 1) {
                            BleSecurityChipOperate.b(f, "5", ByteUtils.d(bArr));
                        } else if (intValue3 < 5) {
                            BleSecurityChipOperate.b(f, "11", ByteUtils.d(bArr));
                        } else {
                            BleSecurityChipOperate.f(f, bArr);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    BluetoothMyLogger.c("reportLockEvent, firmware version illegal format");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void f(String str, byte[] bArr) {
        if (bArr.length > 2) {
            byte[] bArr2 = new byte[2];
            byte[] bArr3 = new byte[(bArr.length - 2)];
            System.arraycopy(bArr, 0, bArr2, 0, 2);
            System.arraycopy(bArr, 2, bArr3, 0, bArr3.length);
            b(str, String.valueOf(ByteBuffer.wrap(bArr2).order(ByteOrder.LITTLE_ENDIAN).getShort()), ByteUtils.d(bArr3));
            return;
        }
        BluetoothMyLogger.c("parseLockEvent data is wrong");
    }

    /* access modifiers changed from: private */
    public static void b(String str, String str2, String str3) {
        DeviceApi.c(str, "event", str2, str3, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
            }

            public void onFailure(Error error) {
            }
        });
    }

    private static void a(String str, final BleResponse<String> bleResponse) {
        BleConnectManager.a().a(str, BluetoothConstants.i, BluetoothConstants.J, (BleReadResponse) new BleReadResponse() {
            public void a(int i, byte[] bArr) {
                if (i != 0 || ByteUtils.e(bArr)) {
                    bleResponse.a(-1, "");
                    return;
                }
                int i2 = 0;
                int i3 = 0;
                while (i2 < bArr.length && bArr[i2] != 0) {
                    i3++;
                    i2++;
                }
                if (i3 == 0) {
                    bleResponse.a(-1, "");
                    return;
                }
                byte[] bArr2 = new byte[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    bArr2[i4] = bArr[i4];
                }
                bleResponse.a(0, new String(bArr2));
            }
        });
    }

    /* access modifiers changed from: private */
    public static void b(String str, int i2, byte[] bArr) {
        LastOperator lastOperator = j.get(str);
        if (lastOperator != null) {
            if (lastOperator.b != null) {
                lastOperator.b.cancel(true);
                lastOperator.b = null;
            }
            if (lastOperator.f14381a != null) {
                lastOperator.f14381a.a(i2, bArr);
                lastOperator.f14381a = null;
            }
            j.remove(str);
        }
    }

    private static class OperatorTimeoutRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private String f14382a;

        public OperatorTimeoutRunnable(String str) {
            this.f14382a = str;
        }

        public void run() {
            LastOperator lastOperator = (LastOperator) BleSecurityChipOperate.j.get(this.f14382a);
            if (lastOperator != null) {
                lastOperator.b = null;
                BluetoothMyLogger.e("BleSecurityChipOperate timeout, mac = " + BluetoothMyLogger.a(this.f14382a));
                BleConnectManager.a().a(this.f14382a);
                BleSecurityChipOperate.b(this.f14382a, -7, (byte[]) null);
            }
        }
    }

    private static class LastOperator {

        /* renamed from: a  reason: collision with root package name */
        BleReadResponse f14381a;
        ScheduledFuture b;

        private LastOperator() {
        }
    }
}
