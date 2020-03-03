package com.xiaomi.smarthome.core.server.internal.bluetooth.channel;

import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelReader;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.channel.ChannelCallback;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleRequestMtuResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import java.util.List;

public class SecureAuthChannelManager extends ChannelManager {
    private static SecureAuthChannelManager e;

    public boolean a() {
        return false;
    }

    private SecureAuthChannelManager() {
    }

    public static SecureAuthChannelManager e() {
        if (e == null) {
            synchronized (SecureAuthChannelManager.class) {
                if (e == null) {
                    e = new SecureAuthChannelManager();
                }
            }
        }
        return e;
    }

    public synchronized IBleChannelWriter b(String str, IBleChannelReader iBleChannelReader) {
        return a(str, true, iBleChannelReader, (BleRequestMtuResponse) null);
    }

    public void a(String str, byte[] bArr, final ChannelCallback channelCallback, boolean z) {
        BleConnectManager.a().b(str, BluetoothConstants.i, BluetoothConstants.S, bArr, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                if (channelCallback != null) {
                    channelCallback.a(i);
                }
            }
        });
    }

    public void a(String str, List<byte[]> list, final ChannelCallback channelCallback) {
        BleConnectManager.a().a(str, BluetoothConstants.i, BluetoothConstants.S, list, (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
                if (channelCallback != null) {
                    channelCallback.a(i);
                }
            }
        });
    }
}
