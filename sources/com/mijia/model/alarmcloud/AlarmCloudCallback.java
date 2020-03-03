package com.mijia.model.alarmcloud;

import com.xiaomi.smarthome.device.api.Callback;

public interface AlarmCloudCallback<T> extends Callback<T> {
    void onSuccess(T t, long j, boolean z);
}
