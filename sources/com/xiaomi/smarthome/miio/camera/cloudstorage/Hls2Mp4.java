package com.xiaomi.smarthome.miio.camera.cloudstorage;

import com.xiaomi.smarthome.framework.log.LogUtil;

public class Hls2Mp4 {
    public static final int ERR_OPEN_INPUT_SOURCE = -104;
    public static final int ERR_OPEN_OUTPUT_FILE = -106;
    public static final int ERR_OUT_OF_MEMORY = -102;
    public static final int ERR_PREVIOUS_TASK_IN_PROGRESS = -101;
    public static final int ERR_STREAM_FORMAT = -105;
    public static final int ERR_THREAD = -103;
    public static final int ERR_THREAD_BEING_CANCELLED = -108;
    public static final int ERR_THREAD_NOT_RUNNING = -107;
    public static final int ERR_UNKNOWN = -100;
    private static final int INFO_CONVERT_CANCELLED = 101;
    private static final int INFO_CONVERT_COMPLETE = 102;
    public static final int INFO_CONVERT_ERROR_DATA = 103;
    private static final int INFO_CONVERT_PROGRESS = 201;
    private static final int INFO_CONVERT_SIZE = 202;
    private static final int INFO_CONVERT_START = 100;
    private static final String TAG = "Hls2Mp4";
    private boolean mIsRunning;
    private OnInfoListener mListener;
    private long mNativeContext = 0;

    public interface OnInfoListener {
        void onCancelled();

        void onComplete();

        void onError(int i);

        void onInfo(int i);

        void onProgress(int i);

        void onSize(int i);

        void onStart();
    }

    private native int cancelConvertNative();

    private native int startConvertNative(String str, String str2);

    private native int startConvertNativeWithCookie(String str, String str2, String str3);

    public void setInfoListener(OnInfoListener onInfoListener) {
        this.mListener = onInfoListener;
    }

    public void removeInfoListener() {
        this.mListener = null;
    }

    public void start(String str, String str2) {
        this.mIsRunning = true;
        startConvertNative(str, str2);
    }

    public void start(String str, String str2, String str3) {
        this.mIsRunning = true;
        startConvertNativeWithCookie(str, str2, str3);
    }

    public int cancel() {
        return cancelConvertNative();
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    private void notifyFromNative(int i, int i2) {
        if (i != 202) {
            String str = TAG;
            LogUtil.a(str, "callback from native:" + i + "-" + i2);
        }
        switch (i) {
            case 100:
                this.mIsRunning = true;
                if (this.mListener != null) {
                    this.mListener.onStart();
                    return;
                }
                return;
            case 101:
                this.mIsRunning = false;
                if (this.mListener != null) {
                    this.mListener.onCancelled();
                    return;
                }
                return;
            case 102:
                this.mIsRunning = false;
                if (this.mListener != null) {
                    this.mListener.onComplete();
                    return;
                }
                return;
            default:
                switch (i) {
                    case 201:
                        if (this.mListener != null && i2 <= 100) {
                            this.mListener.onProgress(i2);
                            return;
                        }
                        return;
                    case 202:
                        if (this.mListener != null) {
                            this.mListener.onSize(i2);
                            return;
                        }
                        return;
                    default:
                        if (i < 0 || i == 103) {
                            this.mIsRunning = false;
                            if (this.mListener != null && i2 <= 100) {
                                this.mListener.onError(i);
                                return;
                            }
                            return;
                        } else if (this.mListener != null && i2 <= 100) {
                            this.mListener.onInfo(i);
                            return;
                        } else {
                            return;
                        }
                }
        }
    }

    static {
        System.loadLibrary("mjmp4mux");
    }
}
