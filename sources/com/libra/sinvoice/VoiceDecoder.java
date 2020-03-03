package com.libra.sinvoice;

import android.content.Context;

public class VoiceDecoder {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6262a = 1;
    public static final int b = 2;
    public static final int c = 3;
    private Callback d;
    private long e = 0;

    public interface Callback {
        void a();

        void a(byte[] bArr);
    }

    public native void create(Context context, String str, String str2);

    public native void destroy();

    public native void putData(byte[] bArr, int i);

    public native void setEffect(int i);

    public native void start();

    public native void stop();

    public VoiceDecoder(Callback callback) {
        this.d = callback;
    }

    private void a() {
        if (this.d != null) {
            this.d.a();
        }
    }

    private void a(byte[] bArr) {
        if (this.d != null) {
            this.d.a(bArr);
        }
    }
}
