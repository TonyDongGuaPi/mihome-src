package com.xiaomi.smarthome.core.server.internal.device;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalDeviceListResponse;
import com.xiaomi.miio.MiioLocalDeviceListResult;
import com.xiaomi.miio.MiioLocalRpcResult;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.Location;
import com.xiaomi.smarthome.core.entity.device.MiioDevice;
import com.xiaomi.smarthome.frame.ErrorCode;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class MiioManager {

    public static abstract class AsyncResponseCallback<T> {
        public abstract void a(int i);

        public abstract void a(int i, Object obj);

        public abstract void a(T t);
    }

    public static void a(final Context context, final AsyncResponseCallback<List<Device>> asyncResponseCallback) {
        new Handler(Looper.getMainLooper()) {

            /* renamed from: a  reason: collision with root package name */
            int f14543a;

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        MiioLocalDeviceListResult miioLocalDeviceListResult = (MiioLocalDeviceListResult) message.obj;
                        ArrayList arrayList = new ArrayList();
                        if (miioLocalDeviceListResult.b != null) {
                            for (MiioLocalRpcResult next : miioLocalDeviceListResult.b) {
                                MiioDevice miioDevice = new MiioDevice(String.valueOf(next.c), "");
                                miioDevice.f(next.f);
                                miioDevice.a(Location.LOCAL);
                                miioDevice.U();
                                arrayList.add(miioDevice);
                            }
                        }
                        asyncResponseCallback.a(arrayList);
                        return;
                    case 2:
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(ErrorCode.INVALID.getCode());
                            break;
                        }
                        break;
                    case 3:
                        break;
                    default:
                        return;
                }
                try {
                    InetAddress a2 = MiioManager.b(context);
                    if (a2 != null) {
                        MiioLocalAPI.a(a2, (MiioLocalDeviceListResponse) new MiioLocalDeviceListResponse() {
                            public void a(MiioLocalDeviceListResult miioLocalDeviceListResult) {
                                AnonymousClass1.this.obtainMessage(1, miioLocalDeviceListResult).sendToTarget();
                            }
                        });
                    }
                } catch (IOException unused) {
                    int i = this.f14543a + 1;
                    this.f14543a = i;
                    if (i < 4) {
                        sendEmptyMessage(3);
                    }
                }
            }
        }.sendEmptyMessage(3);
    }

    /* access modifiers changed from: private */
    public static InetAddress b(Context context) throws IOException {
        DhcpInfo dhcpInfo = ((WifiManager) context.getSystemService("wifi")).getDhcpInfo();
        if (dhcpInfo == null) {
            return null;
        }
        int i = (dhcpInfo.netmask ^ -1) | (dhcpInfo.ipAddress & dhcpInfo.netmask);
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return InetAddress.getByAddress(bArr);
    }
}
