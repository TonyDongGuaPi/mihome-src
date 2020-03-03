package com.xiaomi.smarthome.core.server.internal.bluetooth.channel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelReader;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter;
import com.xiaomi.smarthome.core.server.internal.bluetooth.channel.ChannelManager;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.channel.ChannelCallback;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleRequestMtuResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.util.List;
import java.util.UUID;
import util.VersionUtils;

public class StandardAuthChannelManager extends ChannelManager {
    private static StandardAuthChannelManager e;

    public boolean a() {
        return false;
    }

    private StandardAuthChannelManager() {
    }

    public static StandardAuthChannelManager e() {
        if (e == null) {
            synchronized (StandardAuthChannelManager.class) {
                if (e == null) {
                    e = new StandardAuthChannelManager();
                }
            }
        }
        return e;
    }

    public synchronized IBleChannelWriter b(String str, IBleChannelReader iBleChannelReader) {
        return a(str, true, iBleChannelReader, (BleRequestMtuResponse) null);
    }

    public void f() {
        BluetoothLog.d("StandChannelManager init");
        IntentFilter intentFilter = new IntentFilter("com.xiaomi.smarthome.bluetooth.character_changed");
        intentFilter.addAction("action.online.status.changed");
        BluetoothUtils.a((BroadcastReceiver) new NotifyReceiver(this), intentFilter);
    }

    public void a(String str, byte[] bArr, final ChannelCallback channelCallback, boolean z) {
        BleConnectManager.a().b(str, BluetoothConstants.i, BluetoothConstants.V, bArr, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                if (channelCallback != null) {
                    channelCallback.a(i);
                }
            }
        });
    }

    public void a(String str, List<byte[]> list, final ChannelCallback channelCallback) {
        BleConnectManager.a().a(str, BluetoothConstants.i, BluetoothConstants.V, list, (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
                if (channelCallback != null) {
                    channelCallback.a(i);
                }
            }
        });
    }

    private static class NotifyReceiver extends BroadcastReceiver {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public ChannelManager f14170a;

        public NotifyReceiver(ChannelManager channelManager) {
            this.f14170a = channelManager;
        }

        public void onReceive(Context context, Intent intent) {
            ChannelManager.BleChannel f;
            String action = intent.getAction();
            if ("com.xiaomi.smarthome.bluetooth.character_changed".equals(action)) {
                String stringExtra = intent.getStringExtra("key_device_address");
                UUID uuid = (UUID) intent.getSerializableExtra("key_character_uuid");
                byte[] byteArrayExtra = intent.getByteArrayExtra("key_character_value");
                if (BluetoothConstants.i.equals((UUID) intent.getSerializableExtra("key_service_uuid")) && BluetoothConstants.V.equals(uuid) && (f = this.f14170a.f(stringExtra)) != null) {
                    f.a(byteArrayExtra);
                }
            } else if (TextUtils.equals(action, "action.online.status.changed")) {
                final String stringExtra2 = intent.getStringExtra("extra_mac");
                int intExtra = intent.getIntExtra("extra_online_status", -1);
                BluetoothLog.a("%s,channel Manager receive mac %s, status %s", "standardAuthManager", stringExtra2, String.valueOf(intExtra));
                if (intent.getBooleanExtra("IS_STANDARD_AUTH_DEVICE", false) && intExtra == 80) {
                    StandardAuthChannelManager.a(stringExtra2, (BleResponse<String>) new BleResponse<String>() {
                        public void a(int i, String str) {
                            if (i == 0) {
                                boolean a2 = VersionUtils.a(4, str);
                                BluetoothLog.a("Standard check support A4, current version %s, support is %s", str, String.valueOf(a2));
                                if (a2) {
                                    NotifyReceiver.this.f14170a.c(stringExtra2);
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    static void a(String str, final BleResponse<String> bleResponse) {
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

    public static int a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return -1;
        }
        String[] split = str.split("[._]");
        String[] split2 = str2.split("[._]");
        int min = Math.min(split.length, split2.length);
        int i = 0;
        while (i < min) {
            try {
                int parseInt = Integer.parseInt(split[i]);
                int parseInt2 = Integer.parseInt(split2[i]);
                if (parseInt != parseInt2) {
                    return parseInt - parseInt2;
                }
                i++;
            } catch (Exception e2) {
                BluetoothLog.a((Throwable) e2);
                return 0;
            }
        }
        return 0;
    }
}
