package com.libra.sinvoice;

import android.content.Context;
import android.media.AudioRecord;
import android.os.Handler;
import android.os.Message;
import com.libra.sinvoice.Record;
import com.libra.sinvoice.VoiceRecognition;
import java.io.UnsupportedEncodingException;

public class SinVoiceReceiver implements Record.Callback, Record.Listener, VoiceRecognition.Callback, VoiceRecognition.Listener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6254a = "SinVoiceReceiver";
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 1;
    private static final int e = 2;
    private static final int f = 3;
    private static final int g = 4;
    private BufferQueue h;
    /* access modifiers changed from: private */
    public Record i;
    /* access modifiers changed from: private */
    public VoiceRecognition j;
    private Thread k;
    private Thread l;
    private int m;
    /* access modifiers changed from: private */
    public Listener n;
    private Handler o;

    public interface Listener {
        void b(String str);

        void i();

        void j();

        void k();
    }

    public void b() {
    }

    public void c() {
    }

    public SinVoiceReceiver(Context context, Listener listener) {
        this(context, listener, 3);
    }

    public SinVoiceReceiver(Context context, Listener listener, int i2) {
        this.m = 2;
        this.n = listener;
        int minBufferSize = AudioRecord.getMinBufferSize(44100, 16, 2);
        this.h = new BufferQueue(i2, minBufferSize);
        this.i = new Record(this, 44100, 16, 2, minBufferSize);
        this.i.a((Record.Listener) this);
        this.j = new VoiceRecognition(this);
        this.j.a((VoiceRecognition.Listener) this);
        this.j.a(context);
        this.o = new Handler(new Handler.Callback() {
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        if (SinVoiceReceiver.this.n == null) {
                            return false;
                        }
                        SinVoiceReceiver.this.n.i();
                        return false;
                    case 2:
                        if (SinVoiceReceiver.this.n == null) {
                            return false;
                        }
                        SinVoiceReceiver.this.n.b((String) message.obj);
                        return false;
                    case 3:
                        if (SinVoiceReceiver.this.n == null) {
                            return false;
                        }
                        SinVoiceReceiver.this.n.j();
                        return false;
                    case 4:
                        if (SinVoiceReceiver.this.n == null) {
                            return false;
                        }
                        SinVoiceReceiver.this.n.k();
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

    public void a(Listener listener) {
        this.n = listener;
    }

    public void a(boolean z) {
        this.j.a(z);
    }

    public boolean d() {
        return this.j.b();
    }

    public void a(int i2) {
        this.j.a(i2);
    }

    public void e() {
        this.j.c();
    }

    public boolean f() {
        return 1 == this.m;
    }

    public void b(final boolean z) {
        if (2 == this.m) {
            this.m = 1;
            this.h.a();
            this.l = new Thread() {
                public void run() {
                    SinVoiceReceiver.this.j.d();
                }
            };
            if (this.l != null) {
                this.l.start();
            }
            this.k = new Thread() {
                public void run() {
                    SinVoiceReceiver.this.i.a(z);
                }
            };
            if (this.k != null) {
                this.k.start();
            }
        }
    }

    public void g() {
        if (1 == this.m) {
            this.m = 2;
            this.i.b();
            this.j.e();
            this.h.b();
            if (this.k != null) {
                try {
                    this.k.join();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                } catch (Throwable th) {
                    this.k = null;
                    throw th;
                }
                this.k = null;
            }
            if (this.l != null) {
                try {
                    this.l.join();
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                } catch (Throwable th2) {
                    this.l = null;
                    throw th2;
                }
                this.l = null;
            }
        }
    }

    public BufferData a() {
        BufferData c2 = this.h.c();
        if (c2 == null) {
            LogHelper.c(f6254a, "get null empty buffer");
        }
        return c2;
    }

    public void a(BufferData bufferData) {
        if (bufferData != null && !this.h.b(bufferData)) {
            LogHelper.c(f6254a, "put full buffer failed");
        }
    }

    public BufferData h() {
        BufferData d2 = this.h.d();
        if (d2 == null) {
            LogHelper.c(f6254a, "get null full buffer");
        }
        return d2;
    }

    public void b(BufferData bufferData) {
        if (bufferData != null && !this.h.a(bufferData)) {
            LogHelper.c(f6254a, "put empty buffer failed");
        }
    }

    public void i() {
        this.o.sendEmptyMessage(3);
    }

    public void j() {
        this.o.sendEmptyMessage(1);
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            try {
                this.o.sendMessage(this.o.obtainMessage(2, new String(bArr, "UTF8")));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        } else {
            this.o.sendEmptyMessage(2);
        }
    }

    public void k() {
        this.o.sendEmptyMessage(4);
    }
}
