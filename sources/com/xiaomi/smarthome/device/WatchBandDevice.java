package com.xiaomi.smarthome.device;

public class WatchBandDevice extends MiioDeviceV2 {
    public boolean canBeDeleted() {
        return false;
    }

    public boolean canBeShared() {
        return false;
    }

    public boolean canRename() {
        return false;
    }
}
