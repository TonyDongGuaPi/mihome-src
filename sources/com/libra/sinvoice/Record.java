package com.libra.sinvoice;

import android.media.AudioRecord;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Record {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6249a = "Record";
    private static final int b = 1;
    private static final int c = 2;
    private static final String d = "/sdcard/sinvoice.pcm";
    private volatile int e = 2;
    private int f = 44100;
    private int g;
    private int h = 16;
    private int i = 2;
    private Listener j;
    private Callback k;

    public interface Callback {
        BufferData a();

        void a(BufferData bufferData);
    }

    public interface Listener {
        void b();

        void c();
    }

    public Record(Callback callback, int i2, int i3, int i4, int i5) {
        this.k = callback;
        this.f = i2;
        this.g = i5;
        this.h = i3;
        this.i = i4;
    }

    public void a(Listener listener) {
        this.j = listener;
    }

    private void c() {
        BufferData a2;
        if (this.g > 0) {
            AudioRecord audioRecord = new AudioRecord(1, this.f, this.h, this.i, this.g * 5);
            try {
                this.e = 1;
                audioRecord.startRecording();
                if (this.k != null) {
                    if (this.j != null) {
                        this.j.b();
                    }
                    while (1 == this.e && (a2 = this.k.a()) != null && a2.f6241a != null) {
                        a2.a(audioRecord.read(a2.f6241a, 0, this.g));
                        this.k.a(a2);
                    }
                    if (this.j != null) {
                        this.j.c();
                    }
                }
                audioRecord.stop();
                audioRecord.release();
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
                LogHelper.c(f6249a, "startListen record error");
            }
            this.e = 2;
            return;
        }
        LogHelper.c(f6249a, "bufferSize is too small");
    }

    private void d() {
        BufferData a2;
        int read;
        this.e = 1;
        if (this.k != null) {
            if (this.j != null) {
                this.j.b();
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(d));
                while (1 == this.e && (a2 = this.k.a()) != null && a2.f6241a != null && (read = fileInputStream.read(a2.f6241a)) >= 0) {
                    a2.a(read);
                    this.k.a(a2);
                }
                fileInputStream.close();
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            if (this.j != null) {
                this.j.c();
            }
        }
    }

    public void a(boolean z) {
        if (2 != this.e) {
            return;
        }
        if (z) {
            d();
        } else {
            c();
        }
    }

    public int a() {
        return this.e;
    }

    public void b() {
        if (1 == this.e) {
            this.e = 2;
        }
    }
}
