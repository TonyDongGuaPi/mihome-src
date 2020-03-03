package com.alipay.mobile.common.nativecrash;

public class NativeCrashHandlerApi {
    public static String TAG = "NativeCrashHandlerApi";

    /* renamed from: a  reason: collision with root package name */
    private static NativeCrashApiGetter f974a;
    private static OnNativeCrashUploadListener b;

    public interface NativeCrashApiGetter {
    }

    public interface OnNativeCrashUploadListener {
        void onUpload(String str);
    }

    public static boolean addCrashHeadInfo(String str, String str2) {
        return false;
    }

    public static OnNativeCrashUploadListener getOnNativeCrashUploadListener() {
        return b;
    }

    public static void setOnNativeCrashUploadListener(OnNativeCrashUploadListener onNativeCrashUploadListener) {
        b = onNativeCrashUploadListener;
    }

    public static NativeCrashApiGetter getCrashGetter() {
        return f974a;
    }

    public static void setCrashGetter(NativeCrashApiGetter nativeCrashApiGetter) {
        f974a = nativeCrashApiGetter;
    }
}
