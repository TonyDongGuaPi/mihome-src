package com.libra.sinvoice;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.libra.sinvoice.VoiceDecoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class VoiceRecognition implements VoiceDecoder.Callback {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6264a = "Recognition";
    private static final int b = 1;
    private static final int c = 2;
    private volatile int d = 2;
    private Listener e;
    private Callback f;
    private FileOutputStream g;
    private VoiceDecoder h;
    private Context i;
    private boolean j;

    public interface Callback {
        void b(BufferData bufferData);

        BufferData h();
    }

    public interface Listener {
        void a(byte[] bArr);

        void i();

        void j();

        void k();
    }

    public VoiceRecognition(Callback callback) {
        this.f = callback;
        this.j = false;
        this.h = new VoiceDecoder(this);
    }

    public void a(Listener listener) {
        this.e = listener;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public boolean b() {
        return this.j;
    }

    public void a(Context context) {
        this.i = context;
        this.h.create(this.i, Common.c, Common.d);
    }

    public void c() {
        this.h.destroy();
    }

    public void a(int i2) {
        this.h.setEffect(i2);
    }

    public void d() {
        BufferData h2;
        if (2 == this.d && this.f != null) {
            this.d = 1;
            if (this.e != null) {
                this.e.i();
            }
            if (this.j) {
                try {
                    String path = Environment.getExternalStorageDirectory().getPath();
                    if (!TextUtils.isEmpty(path)) {
                        this.g = new FileOutputStream(String.format("%s/record.pcm", new Object[]{path}));
                    }
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
            this.h.start();
            while (1 == this.d && (h2 = this.f.h()) != null && h2.f6241a != null) {
                this.h.putData(h2.f6241a, h2.b());
                this.f.b(h2);
                if (this.j && this.g != null) {
                    try {
                        this.g.write(h2.f6241a);
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            this.h.stop();
            if (this.j && this.g != null) {
                try {
                    this.g.close();
                    this.g = null;
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            this.d = 2;
            if (this.e != null) {
                this.e.k();
            }
        }
    }

    public void e() {
        if (1 == this.d) {
            this.d = 2;
        }
    }

    public void a() {
        if (this.e != null) {
            this.e.j();
        }
    }

    public void a(byte[] bArr) {
        if (this.e != null) {
            this.e.a(bArr);
        }
    }
}
