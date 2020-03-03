package com.xiaomi.mishopsdk.plugin.lib;

import android.content.Context;

public class MediaRecorderJni {
    private static final String SO_LIBFFMPEG = "ffmpeg";
    private static final String SO_LIBFFMPEGJNI = "ffmpegjni";
    private static final String TAG = "MediaRecorderJni";
    private static MediaRecorderJni sMediaRecorderJni;

    public static void initializeRecorderJni(Context context) {
    }

    public native synchronized int endRecord();

    public native synchronized int initialize();

    public native synchronized int startRecord(String str, int i, int i2, int i3, int i4, int i5);

    public native synchronized int writeRawAudio(short[] sArr, int i);

    public native synchronized int writeRawVideo(byte[] bArr, int i, int i2, int i3);

    private MediaRecorderJni() {
    }

    public static synchronized MediaRecorderJni getInstance() {
        MediaRecorderJni mediaRecorderJni;
        synchronized (MediaRecorderJni.class) {
            if (sMediaRecorderJni == null) {
                System.loadLibrary(SO_LIBFFMPEG);
                System.loadLibrary(SO_LIBFFMPEGJNI);
                sMediaRecorderJni = new MediaRecorderJni();
            }
            sMediaRecorderJni.initialize();
            mediaRecorderJni = sMediaRecorderJni;
        }
        return mediaRecorderJni;
    }
}
