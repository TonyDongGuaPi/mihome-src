package com.xiaomi.smarthome.core.server.internal.bluetooth.channel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.RemoteException;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BleMessageParser;
import com.xiaomi.smarthome.core.server.internal.bluetooth.channel.ChannelManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.device.bluetooth.security.BLECipher;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.channel.ChannelCallback;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IPCChannelManager extends ChannelManager {
    private static IPCChannelManager e;

    public boolean a() {
        return true;
    }

    private IPCChannelManager() {
    }

    public static IPCChannelManager e() {
        if (e == null) {
            synchronized (IPCChannelManager.class) {
                if (e == null) {
                    e = new IPCChannelManager();
                }
            }
        }
        return e;
    }

    public void f() {
        BluetoothUtils.a((BroadcastReceiver) new NotifyReceiver(this), new IntentFilter("com.xiaomi.smarthome.bluetooth.character_changed"));
    }

    public void a(String str, byte[] bArr, final ChannelCallback channelCallback, boolean z) {
        byte[] a2 = BLECipher.a(BluetoothCache.p(str), bArr);
        if (z) {
            BleConnectManager.a().a(str, BluetoothConstants.i, BluetoothConstants.R, a2, (BleWriteResponse) new BleWriteResponse() {
                public void a(int i, Void voidR) {
                }
            });
            if (channelCallback != null) {
                channelCallback.a(0);
                return;
            }
            return;
        }
        BleConnectManager.a().a(str, BluetoothConstants.i, BluetoothConstants.R, a2, (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
                if (channelCallback != null) {
                    channelCallback.a(i);
                }
            }
        });
    }

    public void a(String str, List<byte[]> list, final ChannelCallback channelCallback) {
        byte[] p = BluetoothCache.p(str);
        ArrayList arrayList = new ArrayList();
        for (byte[] a2 : list) {
            arrayList.add(BLECipher.a(p, a2));
        }
        BleConnectManager.a().a(str, BluetoothConstants.i, BluetoothConstants.R, (List<byte[]>) arrayList, (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
                if (channelCallback != null) {
                    channelCallback.a(i);
                }
            }
        });
    }

    private static class NotifyReceiver extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        private ChannelManager f14161a;

        public NotifyReceiver(ChannelManager channelManager) {
            this.f14161a = channelManager;
        }

        public void onReceive(Context context, Intent intent) {
            ChannelManager.BleChannel f;
            if ("com.xiaomi.smarthome.bluetooth.character_changed".equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra("key_device_address");
                UUID uuid = (UUID) intent.getSerializableExtra("key_character_uuid");
                byte[] byteArrayExtra = intent.getByteArrayExtra("key_character_value");
                if (BluetoothConstants.i.equals((UUID) intent.getSerializableExtra("key_service_uuid")) && BluetoothConstants.R.equals(uuid) && (f = this.f14161a.f(stringExtra)) != null) {
                    f.a(BLECipher.a(BluetoothCache.p(stringExtra), byteArrayExtra));
                }
            }
        }
    }

    public synchronized void a(String str, byte[] bArr, final IBleResponse iBleResponse) {
        ChannelManager.BleChannel f = f(str);
        if (f != null) {
            f.a(BleMessageParser.a(bArr), 0, (ChannelCallback) new ChannelCallback() {
                public void a(int i) {
                    if (iBleResponse != null) {
                        try {
                            iBleResponse.onResponse(i, (Bundle) null);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else if (iBleResponse != null) {
            try {
                iBleResponse.onResponse(-1, (Bundle) null);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        return;
    }
}
