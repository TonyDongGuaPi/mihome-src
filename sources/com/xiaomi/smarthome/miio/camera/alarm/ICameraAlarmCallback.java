package com.xiaomi.smarthome.miio.camera.alarm;

public interface ICameraAlarmCallback<T> {
    void onFailure(int i, String str);

    void onSuccess(T t, Object obj);
}
