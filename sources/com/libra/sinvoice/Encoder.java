package com.libra.sinvoice;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.libra.sinvoice.VoiceEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Encoder implements VoiceEncoder.Listener {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6245a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    private static final String f = "Encoder";
    private static final int g = 1;
    private static final int h = 2;
    private Listener i;
    private FileOutputStream j;
    private volatile int k = 2;
    private VoiceEncoder l;
    private Callback m;
    private Context n;
    private int o;
    private int p;
    private byte[] q;
    private int r = 0;
    private boolean s;

    public interface Callback {
        BufferData a();

        void a(BufferData bufferData);
    }

    public interface Listener {
        void b();

        void c();
    }

    public Encoder(Callback callback) {
        this.m = callback;
        this.s = false;
        this.l = new VoiceEncoder(this);
    }

    public void a(Listener listener) {
        this.i = listener;
    }

    public void a(Context context) {
        this.n = context;
        this.l.create(this.n, Common.c, Common.d);
    }

    public void a() {
        this.l.destroy();
    }

    public final boolean b() {
        return 2 == this.k;
    }

    public void a(int i2) {
        this.o = i2;
        this.l.setEffect(i2);
    }

    public void b(int i2) {
        if (3 == this.o) {
            if (i2 < 0) {
                this.p = 0;
            } else {
                this.p = i2;
            }
            a(String.format("sound_effect/mask%d.pcm", new Object[]{Integer.valueOf(this.p)}));
        }
    }

    public void a(boolean z) {
        this.s = z;
    }

    public boolean c() {
        return this.s;
    }

    public void a(int i2, byte[] bArr, int i3, boolean z) {
        if (2 == this.k) {
            this.k = 1;
            this.l.start(i2);
            if (this.s) {
                try {
                    String path = Environment.getExternalStorageDirectory().getPath();
                    if (!TextUtils.isEmpty(path)) {
                        this.j = new FileOutputStream(String.format("%s/player.pcm", new Object[]{path}));
                    }
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
            this.r = 0;
            do {
                b(bArr, i3);
                if (!z || 1 != this.k) {
                    d();
                    this.l.stop();
                }
                b(bArr, i3);
                break;
            } while (1 != this.k);
            d();
            this.l.stop();
            if (this.s && this.j != null) {
                try {
                    this.j.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    private void b(byte[] bArr, int i2) {
        if (1 == this.k) {
            if (this.i != null) {
                this.i.b();
            }
            this.l.send(bArr);
            if (i2 > 0) {
                int i3 = 0;
                while (1 == this.k && i3 < i2) {
                    i3 += 20;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (this.i != null) {
                this.i.c();
            }
        }
    }

    public void d() {
        if (1 == this.k) {
            this.k = 2;
            this.m.a((BufferData) null);
        }
    }

    public void a(byte[] bArr, int i2) {
        c(bArr, i2);
        if (this.m != null) {
            if (this.s && this.j != null) {
                try {
                    this.j.write(bArr, 0, i2);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            BufferData a2 = this.m.a();
            if (a2 != null) {
                a2.a(bArr, i2);
                this.m.a(a2);
            }
        }
    }

    private void c(byte[] bArr, int i2) {
        if (this.q != null && this.p > -1 && 3 == this.o) {
            int i3 = 0;
            while (i3 < i2 - 1) {
                if (this.r >= this.q.length) {
                    this.r = 0;
                }
                int i4 = i3 + 1;
                short s2 = (short) ((((short) (((short) (((short) bArr[i3]) & 255)) | ((short) (((short) bArr[i4]) << 8)))) + ((short) (((short) (((short) this.q[this.r]) & 255)) | ((short) (((short) this.q[this.r + 1]) << 8))))) / 2);
                bArr[i3] = (byte) (s2 & 255);
                bArr[i4] = (byte) ((s2 >> 8) & 255);
                this.r += 2;
                i3 = i4 + 1;
            }
        }
    }

    private void a(String str) {
        try {
            InputStream open = this.n.getResources().getAssets().open(str);
            if (open != null) {
                int available = open.available();
                if (available > 0) {
                    this.q = new byte[available];
                    open.read(this.q);
                    LogHelper.a(f, "mask len:" + this.q.length);
                } else {
                    this.q = null;
                }
                open.close();
                return;
            }
            this.q = null;
        } catch (Exception e2) {
            this.q = null;
            e2.printStackTrace();
        }
    }
}
