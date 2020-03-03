package com.xiaomi.miot.support.monitor.core.net.i;

import com.xiaomi.miot.support.monitor.core.net.NetInfo;

public class QOKHttp {
    public static void a(String str, int i, long j, long j2, long j3, long j4, int i2) {
        NetInfo netInfo = new NetInfo();
        netInfo.setStartTime(j3);
        netInfo.setURL(str);
        netInfo.setStatusCode(i);
        netInfo.setSendBytes(j);
        netInfo.setReceivedBytes(j2);
        netInfo.setCostTime(j4);
        netInfo.setBackType(i2);
        netInfo.end();
    }
}
