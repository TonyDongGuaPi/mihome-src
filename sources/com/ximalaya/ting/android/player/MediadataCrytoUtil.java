package com.ximalaya.ting.android.player;

public class MediadataCrytoUtil {

    /* renamed from: a  reason: collision with root package name */
    public static int f2276a = 1024;
    private static MediadataCrytoUtil b;
    private long c = initLogistic();

    private native byte[] decryptData(long j, byte[] bArr, int i);

    private native void destroyEncryptCtx(long j);

    private native byte[] encryptData(long j, byte[] bArr, int i);

    private native long initLogistic();

    private MediadataCrytoUtil() {
    }

    public static synchronized MediadataCrytoUtil a() {
        MediadataCrytoUtil mediadataCrytoUtil;
        synchronized (MediadataCrytoUtil.class) {
            if (b == null) {
                b = new MediadataCrytoUtil();
            }
            mediadataCrytoUtil = b;
        }
        return mediadataCrytoUtil;
    }

    public byte[] a(byte[] bArr) {
        return encryptData(this.c, bArr, bArr.length);
    }

    public byte[] b(byte[] bArr) {
        return decryptData(this.c, bArr, bArr.length);
    }

    public static void b() {
        if (b != null) {
            b.destroyEncryptCtx(b.c);
            b = null;
        }
    }

    static {
        try {
            System.loadLibrary("mediadatacryto");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
