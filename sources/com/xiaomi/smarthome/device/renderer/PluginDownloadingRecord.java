package com.xiaomi.smarthome.device.renderer;

import com.xiaomi.smarthome.frame.plugin.PluginApi;

public class PluginDownloadingRecord {

    /* renamed from: a  reason: collision with root package name */
    public String f15409a;
    public Status b;
    public float c;
    public PluginApi.SendMessageHandle d;
    public long e;

    public enum Status {
        PENDING,
        DOWNLOADING,
        DOWNLOADING_SUCCESS,
        DOWNLOADING_FAILURE
    }
}
