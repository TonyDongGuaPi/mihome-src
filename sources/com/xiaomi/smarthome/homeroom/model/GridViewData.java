package com.xiaomi.smarthome.homeroom.model;

import com.xiaomi.smarthome.device.Device;

public class GridViewData {

    /* renamed from: a  reason: collision with root package name */
    public GridType f18311a;
    public Device b;
    public String c;

    public enum GridType {
        TYPE_NORMAL,
        TYPE_IR,
        TYPE_ADD_TO_COMMON,
        TYPE_TIPS,
        TYPE_CAMERA
    }

    public GridViewData() {
    }

    public GridViewData(GridType gridType) {
        this.f18311a = gridType;
    }
}
