package com.xiaomi.smarthome.miio.page.msgcentersetting.model;

import com.xiaomi.smarthome.device.Device;

public class DeviceMsgSettingChild implements Comparable<DeviceMsgSettingChild> {

    /* renamed from: a  reason: collision with root package name */
    public String f19887a;
    public String b;
    public Device c;
    public String d;
    public Integer e = -1;
    public String f;
    public String g;
    public String h;
    public String i;
    public Integer j = -1;
    public int k = -1;
    public int l = -1;
    public int m = -1;

    /* renamed from: a */
    public int compareTo(DeviceMsgSettingChild deviceMsgSettingChild) {
        if (this.k != deviceMsgSettingChild.k) {
            if (this.k < 0) {
                return 1;
            }
            if (deviceMsgSettingChild.k < 0) {
                return -1;
            }
            return this.k - deviceMsgSettingChild.k;
        } else if (this.l == deviceMsgSettingChild.l) {
            return this.m - deviceMsgSettingChild.m;
        } else {
            if (this.l < 0) {
                return 1;
            }
            if (deviceMsgSettingChild.l < 0) {
                return -1;
            }
            return this.l - deviceMsgSettingChild.l;
        }
    }
}
