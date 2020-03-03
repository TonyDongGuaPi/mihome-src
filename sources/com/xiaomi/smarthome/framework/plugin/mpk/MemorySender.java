package com.xiaomi.smarthome.framework.plugin.mpk;

import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.listcamera.receiver.MessageDispatcher;

public class MemorySender implements FrameSender {
    public void closeCameraFrame(String str) {
    }

    public void initCameraFrame(String str) {
    }

    public void sendCameraFrame(String str, byte[] bArr, long j, int i, long j2, int i2, boolean z, int i3, int i4) {
        String str2 = str;
        MessageDispatcher.a(str, new VideoFrame(bArr, j, i, i3, i4, j2, i2, z));
    }
}
