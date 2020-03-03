package com.xiaomi.smarthome.miio.camera.cloudstorage.model;

public interface ICloudVideoCallback<T> {
    void onCloudVideoFailed(int i, String str);

    void onCloudVideoSuccess(T t, Object obj);
}
