package com.libra.sinvoice;

import android.media.AudioTrack;

public class PcmPlayer {
    private static final String b = "PcmPlayer";
    private static final int c = 1;
    private static final int d = 2;

    /* renamed from: a  reason: collision with root package name */
    AudioTrack f6247a = new AudioTrack(3, this.i, this.j, this.k, this.l * 3, 1);
    private volatile int e = 2;
    private boolean f;
    private Listener g;
    private Callback h;
    private int i;
    private int j;
    private int k;
    private int l;

    public interface Callback {
        void b(BufferData bufferData);

        BufferData d();
    }

    public interface Listener {
        void e();

        void f();
    }

    public PcmPlayer(Callback callback, int i2, int i3, int i4, int i5) {
        this.i = i2;
        this.j = i3;
        this.k = i4;
        this.l = i5;
        this.h = callback;
    }

    public void a() {
        if (this.f6247a != null) {
            this.f6247a.stop();
            this.f6247a.release();
        }
    }

    public void a(Listener listener) {
        this.g = listener;
    }

    public void b() {
        if (2 == this.e) {
            this.f = false;
            if (this.h != null) {
                this.e = 1;
                if (this.g != null) {
                    this.g.e();
                }
                while (true) {
                    if (1 == this.e) {
                        BufferData d2 = this.h.d();
                        if (d2 != null) {
                            if (d2.f6241a == null) {
                                break;
                            }
                            int write = this.f6247a.write(d2.f6241a, 0, d2.b());
                            if (write != d2.b()) {
                                LogHelper.c(b, "PcmPlayerTime writedata, write is invalidate len:" + write + "   filledSize:" + d2.b());
                            }
                            if (!this.f) {
                                this.f = true;
                                this.f6247a.play();
                            }
                            this.h.b(d2);
                        } else {
                            LogHelper.c(b, "get null data");
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (this.f6247a != null) {
                    this.f6247a.flush();
                }
                this.e = 2;
                if (this.g != null) {
                    this.g.f();
                }
            }
        }
    }

    public void c() {
        if (1 == this.e) {
            this.e = 2;
        }
    }
}
