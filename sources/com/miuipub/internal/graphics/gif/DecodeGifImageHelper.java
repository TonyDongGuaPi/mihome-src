package com.miuipub.internal.graphics.gif;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import miuipub.io.ResettableInputStream;

public class DecodeGifImageHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8246a = 1;
    public long b = 1048576;
    public List<GifFrame> c = new ArrayList();
    public int d;
    public boolean e;
    public Handler f;
    public ResettableInputStream g;
    private int h;
    /* access modifiers changed from: private */
    public DecodeGifFrames i;

    public static class GifDecodeResult {

        /* renamed from: a  reason: collision with root package name */
        public GifDecoder f8248a;
        public boolean b;
    }

    public static class GifFrame {

        /* renamed from: a  reason: collision with root package name */
        public Bitmap f8249a;
        public int b;
        public int c;

        public GifFrame(Bitmap bitmap, int i, int i2) {
            this.f8249a = bitmap;
            this.b = i;
            this.c = i2;
        }
    }

    private int d() {
        return this.c.get(this.c.size() - 1).c;
    }

    private int b(int i2) {
        if (this.d == 0) {
            return i2;
        }
        return i2 % this.d;
    }

    public void a() {
        int size = this.c.size();
        boolean z = false;
        if (this.h > 3 ? size <= this.h / 2 : size <= 2) {
            z = true;
        }
        if (z) {
            this.i.a(b(d() + 1));
        }
    }

    public boolean a(GifDecodeResult gifDecodeResult) {
        if (!gifDecodeResult.b || gifDecodeResult.f8248a == null) {
            return false;
        }
        GifDecoder gifDecoder = gifDecodeResult.f8248a;
        Log.d("dumpFrameIndex", String.format("Thread#%d: decoded %d frames [%s] [%d]", new Object[]{Long.valueOf(Thread.currentThread().getId()), Integer.valueOf(gifDecodeResult.f8248a.c()), Boolean.valueOf(gifDecodeResult.b), Integer.valueOf(this.d)}));
        if (gifDecoder.a()) {
            this.d = gifDecoder.m();
        }
        int c2 = gifDecoder.c();
        if (c2 > 0) {
            int d2 = d();
            for (int i2 = 0; i2 < c2; i2++) {
                this.c.add(new GifFrame(gifDecoder.c(i2), gifDecoder.b(i2), b(d2 + 1 + i2)));
            }
        }
        return true;
    }

    public GifDecodeResult a(int i2) {
        return a(this.g, this.b, i2);
    }

    public static GifDecodeResult a(ResettableInputStream resettableInputStream, long j, int i2) {
        GifDecodeResult gifDecodeResult = new GifDecodeResult();
        gifDecodeResult.f8248a = null;
        boolean z = false;
        gifDecodeResult.b = false;
        try {
            resettableInputStream.reset();
            gifDecodeResult.f8248a = new GifDecoder();
            GifDecoder gifDecoder = gifDecodeResult.f8248a;
            gifDecoder.a(i2);
            gifDecoder.a(j);
            if (gifDecoder.c((InputStream) resettableInputStream) == 0) {
                z = true;
            }
            gifDecodeResult.b = z;
            try {
                resettableInputStream.close();
            } catch (IOException unused) {
            }
            return gifDecodeResult;
        } catch (IOException unused2) {
            return gifDecodeResult;
        }
    }

    public void b() {
        this.f = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                if (message.what == 1 && DecodeGifImageHelper.this.a(DecodeGifImageHelper.this.i.b())) {
                    DecodeGifImageHelper.this.a();
                }
            }
        };
        this.i = DecodeGifFrames.a(this.g, this.b, this.f);
        this.h = this.c.size();
        a();
    }

    public void c() {
        if (this.i != null) {
            this.i.a();
        }
    }
}
