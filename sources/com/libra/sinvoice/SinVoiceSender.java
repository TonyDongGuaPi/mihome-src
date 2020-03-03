package com.libra.sinvoice;

import android.content.Context;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Message;
import com.libra.sinvoice.Encoder;
import com.libra.sinvoice.PcmPlayer;
import java.io.UnsupportedEncodingException;

public class SinVoiceSender implements Encoder.Callback, Encoder.Listener, PcmPlayer.Callback, PcmPlayer.Listener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6258a = "SinVoiceSender";
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 0;
    private static final int n = 2;
    /* access modifiers changed from: private */
    public Encoder e;
    /* access modifiers changed from: private */
    public PcmPlayer f;
    private BufferQueue g;
    private volatile int h;
    private Listener i;
    private Thread j;
    private Thread k;
    /* access modifiers changed from: private */
    public int l;
    private Handler m;

    public interface Listener {
        void g();

        void h();
    }

    public void b() {
    }

    public void c() {
    }

    public void e() {
    }

    public SinVoiceSender(Context context, Listener listener) {
        this(context, listener, 44100, 3);
    }

    private SinVoiceSender(Context context, Listener listener, int i2, int i3) {
        this.h = 2;
        this.i = listener;
        int minBufferSize = AudioTrack.getMinBufferSize(i2, 4, 2);
        this.g = new BufferQueue(i3, minBufferSize);
        this.l = minBufferSize;
        this.e = new Encoder(this);
        this.e.a((Encoder.Listener) this);
        this.e.a(context);
        this.f = new PcmPlayer(this, i2, 4, 2, minBufferSize);
        this.f.a(this);
        this.m = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 2) {
                    SinVoiceSender.this.j();
                }
            }
        };
    }

    public boolean g() {
        return 1 == this.h;
    }

    public void a(int i2) {
        this.e.b(i2);
    }

    public void b(int i2) {
        this.e.a(i2);
    }

    public void a(boolean z) {
        this.e.a(z);
    }

    public boolean h() {
        return this.e.c();
    }

    public void i() {
        this.e.a();
        this.f.a();
    }

    public void a(Listener listener) {
        this.i = listener;
    }

    public void a(String str) {
        a(str, false, 0);
    }

    public void a(String str, boolean z, int i2) {
        byte[] bArr;
        if (str != null) {
            try {
                bArr = str.getBytes("UTF8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                bArr = null;
            }
            if (bArr != null) {
                a(bArr, z, i2);
            }
        }
    }

    private void a(final byte[] bArr, final boolean z, final int i2) {
        if (2 == this.h) {
            this.h = 1;
            this.g.a();
            this.j = new Thread() {
                public void run() {
                    SinVoiceSender.this.f.b();
                }
            };
            if (this.j != null) {
                this.j.start();
            }
            this.k = new Thread() {
                public void run() {
                    SinVoiceSender.this.e.a(SinVoiceSender.this.l, bArr, i2, z);
                }
            };
            if (this.k != null) {
                this.k.start();
            }
            if (this.i != null) {
                this.i.g();
            }
        }
    }

    public void j() {
        if (1 == this.h) {
            this.h = 2;
            this.e.d();
            this.f.c();
            this.g.b();
            if (this.j != null) {
                try {
                    this.j.join();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                this.j = null;
            }
            if (this.k != null) {
                try {
                    this.k.join();
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
                this.k = null;
            }
            if (this.i != null) {
                this.i.h();
            }
        }
    }

    public void a(BufferData bufferData) {
        this.g.b(bufferData);
    }

    public BufferData a() {
        return this.g.c();
    }

    public BufferData d() {
        return this.g.d();
    }

    public void b(BufferData bufferData) {
        this.g.a(bufferData);
    }

    public void f() {
        this.m.sendEmptyMessage(2);
    }
}
