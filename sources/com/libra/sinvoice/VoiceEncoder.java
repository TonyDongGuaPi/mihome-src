package com.libra.sinvoice;

import android.content.Context;

public class VoiceEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6263a = 1;
    public static final int b = 2;
    public static final int c = 3;
    private static final String d = "VoiceEncoder";
    private Listener e;
    private long f;

    public interface Listener {
        void a(byte[] bArr, int i);
    }

    public native void create(Context context, String str, String str2);

    public native void destroy();

    public native void send(byte[] bArr);

    public native void setEffect(int i);

    public native void start(int i);

    public native void stop();

    public VoiceEncoder(Listener listener) {
        this.e = listener;
    }

    private void a(byte[] bArr, int i) {
        if (this.e != null) {
            this.e.a(bArr, i);
        }
    }
}
