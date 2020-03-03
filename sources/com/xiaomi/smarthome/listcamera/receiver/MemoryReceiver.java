package com.xiaomi.smarthome.listcamera.receiver;

import com.xiaomi.smarthome.camera.VideoFrame;
import java.util.concurrent.LinkedBlockingDeque;

public class MemoryReceiver extends Receiver {

    /* renamed from: a  reason: collision with root package name */
    LinkedBlockingDeque<VideoFrame> f19346a = new LinkedBlockingDeque<>();
    private String b;

    public MemoryReceiver(String str) {
        this.b = str;
    }

    /* access modifiers changed from: protected */
    public void a() {
        MessageDispatcher.a(this.b, this);
    }

    /* access modifiers changed from: protected */
    public void c() {
        try {
            VideoFrame take = this.f19346a.take();
            if (take != null && take.data != null && this.b != null && this.e != null) {
                this.e.a(this.b, take);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        MessageDispatcher.b(this.b, this);
    }

    /* access modifiers changed from: protected */
    public void e() {
        MessageDispatcher.a(this.b, new VideoFrame((byte[]) null, 0, 0, 0, 0, 0, false));
    }
}
