package com.xiaomi.smarthome.listcamera.receiver;

import com.xiaomi.smarthome.camera.VideoFrame;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MessageDispatcher {

    /* renamed from: a  reason: collision with root package name */
    private static ConcurrentMap<String, MemoryReceiver> f19347a = new ConcurrentHashMap();

    public static void a(String str, MemoryReceiver memoryReceiver) {
        f19347a.put(str, memoryReceiver);
    }

    public static void b(String str, MemoryReceiver memoryReceiver) {
        f19347a.remove(str);
    }

    public static void a(String str, VideoFrame videoFrame) {
        MemoryReceiver memoryReceiver;
        if (f19347a.containsKey(str) && (memoryReceiver = (MemoryReceiver) f19347a.get(str)) != null) {
            try {
                memoryReceiver.f19346a.put(videoFrame);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
