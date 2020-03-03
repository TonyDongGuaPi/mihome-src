package com.xiaomi.ai.streamplayer;

import com.xiaomi.ai.mibrain.MibrainMp3Decoder;
import com.xiaomi.ai.streamplayer.a;
import com.xiaomi.ai.utils.Log;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class f extends a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9940a = "Mp3SoftDecoder";
    /* access modifiers changed from: private */
    public MibrainMp3Decoder b = new MibrainMp3Decoder(new g(this));
    /* access modifiers changed from: private */
    public PipedInputStream c = new PipedInputStream();
    private PipedOutputStream d;
    /* access modifiers changed from: private */
    public BlockingQueue<a.C0083a> e = new LinkedBlockingDeque();
    /* access modifiers changed from: private */
    public boolean f;
    private Thread g = new Thread(new h(this));

    f() {
        try {
            this.d = new PipedOutputStream(this.c);
        } catch (IOException e2) {
            Log.a(f9940a, "PIPE IO ", e2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.b.init();
        this.g.start();
    }

    public void a(byte[] bArr, int i) {
        if (bArr != null) {
            try {
                this.d.write(bArr, 0, i);
            } catch (IOException e2) {
                Log.a(f9940a, "write ", e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(byte[] bArr, int i, int i2) {
        try {
            this.d.write(bArr, 0, i2);
        } catch (IOException e2) {
            Log.a(f9940a, "putEncodedBuffe ", e2);
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        synchronized (this) {
            if (!this.f) {
                this.b.cancel();
                this.g.interrupt();
            }
        }
    }

    public void c() {
        synchronized (this) {
            if (!this.f) {
                this.b.cancel();
                this.g.interrupt();
            }
        }
    }

    public void d() {
        try {
            this.d.close();
        } catch (IOException e2) {
            Log.a(f9940a, "end ", e2);
        }
    }

    /* access modifiers changed from: package-private */
    public a.C0083a e() {
        try {
            return this.e.take();
        } catch (InterruptedException e2) {
            Log.a(f9940a, "getDecodedBuffer ", e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void f() {
        this.b.start();
        try {
            this.c.close();
        } catch (IOException unused) {
        }
        a.C0083a aVar = new a.C0083a();
        aVar.d = true;
        try {
            this.e.put(aVar);
        } catch (InterruptedException e2) {
            Log.a(f9940a, "e", e2);
        }
    }
}
