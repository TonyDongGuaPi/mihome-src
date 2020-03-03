package com.xiaomi.smarthome.device.bluetooth.connect.combo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.device.bluetooth.BLEBytesWriter;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.bluetooth.BleFastConnector;
import com.xiaomi.smarthome.device.bluetooth.ComboDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothConstants;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.util.Task;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BleComboConnector extends BaseBleComboConnector {
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    public static final int i = 4;
    public static final int j = 5;
    private BleFastConnector k;
    private NotifyReceiver l;
    private BleConnectOptions m = new BleConnectOptions.Builder().a(1).c(35000).b(1).d(10000).a();

    public BleComboConnector(ComboConnectResponse comboConnectResponse) {
        super(comboConnectResponse);
    }

    public void a(ScanResult scanResult) {
        super.a(scanResult);
        BluetoothMyLogger.f("BleComboConnector.connectCombo");
        Log.i("stopScan", "BCC stop");
        CoreApi.a().W();
        final Future<String> a2 = ComboDeviceManager.a(scanResult);
        if (a2 == null) {
            a((String) null);
        } else {
            Task.a((Task) new Task() {
                public void a() {
                    String str = "";
                    try {
                        str = (String) a2.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e2) {
                        e2.printStackTrace();
                    }
                    if (!TextUtils.isEmpty(str)) {
                        BleComboConnector.this.c = str;
                    }
                    BleComboConnector.this.a(str);
                }
            }, AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    public void d() {
        super.d();
        b();
        XmBluetoothManager.getInstance().disconnect(this.c);
    }

    public void a(String str, String str2) {
        super.a(str, str2);
        String b = SmartConfigDataProvider.a().b();
        String d = SmartConfigDataProvider.a().d();
        LogUtilGrey.a("BleComboStep", String.format("BleComboConnector.sendSSIDAndPassWd ssid = %s, passwd = %s, bindKey = %s, configType = %s", new Object[]{b, d, str, str2}));
        if (this.k == null) {
            this.k = new BleFastConnector(this.c, b, d, str, str2);
        }
        BleCacheUtils.x(this.c);
        this.k.a(this.m, (BleFastConnector.BleFastConnectResponse) new BleFastConnector.BleFastConnectResponse() {
            /* renamed from: a */
            public void onResponse(int i, Bundle bundle) {
                BluetoothLog.e(String.format("mBleFastConnector onResponse = %s", new Object[]{Code.a(i)}));
                String str = "";
                if (bundle != null) {
                    str = bundle.getString("key_version");
                }
                BleComboConnector.this.a(i, str);
            }
        });
    }

    public void a() {
        super.a();
        e();
        XmBluetoothManager.getInstance().notify(this.c, BluetoothConstants.w, BluetoothConstants.D, new Response.BleNotifyResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
            }
        });
    }

    public void b() {
        super.b();
        f();
        XmBluetoothManager.getInstance().unnotify(this.c, BluetoothConstants.w, BluetoothConstants.D);
    }

    private void e() {
        if (this.l == null) {
            this.l = new NotifyReceiver();
            IntentFilter intentFilter = new IntentFilter("com.xiaomi.smarthome.bluetooth.character_changed");
            intentFilter.addAction("com.xiaomi.smarthome.bluetooth.connect_status_changed");
            BluetoothUtils.a((BroadcastReceiver) this.l, intentFilter);
        }
    }

    private void f() {
        if (this.l != null) {
            BluetoothUtils.a((BroadcastReceiver) this.l);
            this.l = null;
        }
    }

    private class NotifyReceiver extends BroadcastReceiver {
        private NotifyReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String stringExtra = intent.getStringExtra("key_device_address");
                if (!TextUtils.isEmpty(stringExtra) && stringExtra.equalsIgnoreCase(BleComboConnector.this.c)) {
                    String action = intent.getAction();
                    if ("com.xiaomi.smarthome.bluetooth.character_changed".equalsIgnoreCase(action)) {
                        UUID uuid = (UUID) intent.getSerializableExtra("key_character_uuid");
                        byte[] byteArrayExtra = intent.getByteArrayExtra("key_character_value");
                        if (((UUID) intent.getSerializableExtra("key_service_uuid")).equals(BluetoothConstants.w) && uuid.equals(BluetoothConstants.D) && !ByteUtils.e(byteArrayExtra)) {
                            BleComboConnector.this.a((int) byteArrayExtra[0]);
                        }
                    } else if ("com.xiaomi.smarthome.bluetooth.connect_status_changed".equalsIgnoreCase(action)) {
                        intent.getIntExtra("key_connect_status", 5);
                    }
                }
            }
        }
    }

    public void c() {
        super.c();
        this.k.a((BLEBytesWriter.BLEBytesWriteResponse) new BLEBytesWriter.BLEBytesWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
            }
        });
    }

    public void a(final ComboRestoreResponse comboRestoreResponse) {
        super.a(comboRestoreResponse);
        if (this.k != null) {
            this.k.b((BLEBytesWriter.BLEBytesWriteResponse) new BLEBytesWriter.BLEBytesWriteResponse() {
                /* renamed from: a */
                public void onResponse(int i, Void voidR) {
                    if (comboRestoreResponse != null) {
                        comboRestoreResponse.a(i);
                    }
                }
            });
        }
    }
}
