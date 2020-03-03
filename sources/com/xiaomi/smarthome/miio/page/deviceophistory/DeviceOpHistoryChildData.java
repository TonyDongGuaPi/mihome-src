package com.xiaomi.smarthome.miio.page.deviceophistory;

import java.util.GregorianCalendar;

public class DeviceOpHistoryChildData {

    /* renamed from: a  reason: collision with root package name */
    public long f19738a;
    public String b;
    public String c;
    public String d;
    public String e;

    public static DeviceOpHistoryChildData a(long j, String str, String str2, String str3) {
        DeviceOpHistoryChildData deviceOpHistoryChildData = new DeviceOpHistoryChildData();
        deviceOpHistoryChildData.f19738a = j;
        deviceOpHistoryChildData.c = str;
        deviceOpHistoryChildData.d = str2;
        deviceOpHistoryChildData.e = str3;
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(j);
        deviceOpHistoryChildData.b = "" + gregorianCalendar.get(11) + ":" + gregorianCalendar.get(12);
        return deviceOpHistoryChildData;
    }
}
