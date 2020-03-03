package com.xiaomi.smarthome.framework.plugin.mpk;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Parcel;
import android.util.Log;
import com.xiaomi.smarthome.listcamera.receiver.LocalSocketReceiver;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class LocalSocketSender implements FrameSender {
    private HashMap<String, LocalSocket> mSocketMap = new HashMap<>();

    public void initCameraFrame(String str) {
        if (this.mSocketMap.containsKey(str)) {
            closeCameraFrame(str);
        }
        LocalSocket localSocket = new LocalSocket();
        try {
            localSocket.connect(new LocalSocketAddress(LocalSocketReceiver.f19345a + str));
            localSocket.setSoTimeout(1000);
            this.mSocketMap.put(str, localSocket);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("device_rpc", "init camera frame error");
        }
        Log.e("device_rpc", "init camera frame");
    }

    public void sendCameraFrame(String str, byte[] bArr, long j, int i, long j2, int i2, boolean z, int i3, int i4) {
        if (this.mSocketMap.containsKey(str)) {
            LocalSocket localSocket = this.mSocketMap.get(str);
            try {
                Parcel obtain = Parcel.obtain();
                obtain.writeString(str);
                obtain.writeInt(i3);
                obtain.writeInt(i4);
                obtain.writeLong(j);
                obtain.writeInt(i);
                obtain.writeLong(j2);
                obtain.writeInt(i2);
                obtain.writeInt(z ? 1 : 0);
                obtain.writeByteArray(bArr);
                OutputStream outputStream = localSocket.getOutputStream();
                byte[] marshall = obtain.marshall();
                outputStream.write(new byte[]{(byte) (marshall.length & 255), (byte) ((marshall.length >> 8) & 255), (byte) ((marshall.length >> 16) & 255), (byte) ((marshall.length >> 24) & 255)});
                outputStream.write(marshall);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            initCameraFrame(str);
        }
    }

    public void closeCameraFrame(String str) {
        if (this.mSocketMap.containsKey(str)) {
            try {
                this.mSocketMap.get(str).close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Throwable th) {
                this.mSocketMap.remove(str);
                throw th;
            }
            this.mSocketMap.remove(str);
        }
        Log.e("device_rpc", "close camera frame");
    }
}
