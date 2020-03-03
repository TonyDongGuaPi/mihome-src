package com.miuipub.internal.graphics.gif;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.miuipub.internal.graphics.gif.DecodeGifImageHelper;
import miuipub.io.ResettableInputStream;

class DecodeGifFrames extends Handler {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f8245a = "DecodeGifFrames";
    private static final int d = 1;
    DecodeGifImageHelper.GifDecodeResult b;
    HandlerThread c;
    private Handler e;
    private ResettableInputStream f;
    private long g;

    public static DecodeGifFrames a(ResettableInputStream resettableInputStream, long j, Handler handler) {
        HandlerThread handlerThread = new HandlerThread("handler thread to decode GIF frames");
        handlerThread.start();
        return new DecodeGifFrames(handlerThread, resettableInputStream, j, handler);
    }

    public DecodeGifFrames(HandlerThread handlerThread, ResettableInputStream resettableInputStream, long j, Handler handler) {
        super(handlerThread.getLooper());
        this.c = handlerThread;
        this.g = j;
        this.f = resettableInputStream;
        this.e = handler;
    }

    public void a() {
        this.c.quit();
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        this.c.quit();
        super.finalize();
    }

    public void a(int i) {
        if (this.b == null) {
            this.b = new DecodeGifImageHelper.GifDecodeResult();
            sendMessage(obtainMessage(1, i, 0));
        }
    }

    public DecodeGifImageHelper.GifDecodeResult b() {
        DecodeGifImageHelper.GifDecodeResult gifDecodeResult = this.b;
        this.b = null;
        return gifDecodeResult;
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            DecodeGifImageHelper.GifDecodeResult a2 = DecodeGifImageHelper.a(this.f, this.g, message.arg1);
            this.b.f8248a = a2.f8248a;
            this.b.b = a2.b;
            this.e.sendEmptyMessage(1);
        }
    }
}
