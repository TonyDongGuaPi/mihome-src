package com.xiaomi.smarthome.miniprogram.model;

import com.xiaomi.smarthome.device.Device;

public class MyMiniProgramDevice<T extends Device> {

    /* renamed from: a  reason: collision with root package name */
    public boolean f20051a = false;
    public int b = -1;
    public T c;

    public MyMiniProgramDevice(T t) {
        this.c = t;
    }
}
