package com.xiaomi.smarthome.miio.miband.data;

public class PluginDeviceDownloadItem {

    /* renamed from: a  reason: collision with root package name */
    public float f19463a = 0.0f;
    public String b;
    public Status c;

    public enum Status {
        PENDING,
        DOWNLOADING,
        DOWNLOADING_SUCCESS,
        DOWNLOADING_FAILURE
    }
}
