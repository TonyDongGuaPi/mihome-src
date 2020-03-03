package com.xiaomi.smarthome.device.api;

public interface FaceManagerCallback<T> {
    public static final int ERROR_UNKNOWN_ERROR = -9;
    public static final int INVALID = -9999;
    public static final int SUCCESS = 0;

    void onFailure(int i, String str);

    void onSuccess(T t, T t2);
}
