package com.xiaomi.smarthome.core.server.internal.bluetooth.channel;

import com.xiaomi.smarthome.core.server.internal.bluetooth.channel.ChannelManager;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.channel.ChannelCallback;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.List;

public class MeshDfuChannelManager extends ChannelManager {
    private static MeshDfuChannelManager e;

    public boolean a() {
        return false;
    }

    private MeshDfuChannelManager() {
    }

    public static MeshDfuChannelManager e() {
        if (e == null) {
            synchronized (MeshDfuChannelManager.class) {
                if (e == null) {
                    e = new MeshDfuChannelManager();
                }
            }
        }
        return e;
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
        ChannelManager.BleChannel f = e().f(str);
        if (f != null) {
            f.f();
        }
    }

    public void a(String str, byte[] bArr, ChannelCallback channelCallback, boolean z) {
        final ChannelCallback channelCallback2 = channelCallback;
        if (z) {
            BleConnectManager.a().c(str, BluetoothConstants.i, BluetoothConstants.U, bArr, new BleWriteResponse() {
                public void a(int i, Void voidR) {
                    BluetoothLog.d("writeble onResponse");
                    if (channelCallback2 != null) {
                        channelCallback2.a(i);
                    }
                }
            });
            return;
        }
        BleConnectManager.a().b(str, BluetoothConstants.i, BluetoothConstants.U, bArr, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                if (channelCallback2 != null) {
                    channelCallback2.a(i);
                }
            }
        });
    }

    public void a(String str, List<byte[]> list, final ChannelCallback channelCallback) {
        BleConnectManager.a().a(str, BluetoothConstants.i, BluetoothConstants.U, list, (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothLog.d("writeBatchBleData onResponse");
                if (channelCallback != null) {
                    channelCallback.a(i);
                }
            }
        });
    }
}
