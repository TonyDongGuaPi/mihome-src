package com.alipay.mobile.common.logging.api.monitor;

import java.util.HashMap;
import java.util.Map;

public enum ExceptionID {
    MONITORPOINT_CRASH("MonitorPoint_Crash"),
    MONITORPOINT_CLIENTSERR("MonitorPoint_ClientsErr"),
    MONITORPOINT_CONNECTERR("MonitorPoint_ConnectErr");
    

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, ExceptionID> f950a = null;
    private String desc;

    static {
        int i;
        f950a = new HashMap();
        for (ExceptionID exceptionID : values()) {
            f950a.put(exceptionID.desc, exceptionID);
        }
    }

    private ExceptionID(String str) {
        this.desc = str;
    }

    public String getDes() {
        return this.desc;
    }

    public static ExceptionID fromString(String str) {
        return f950a.get(str);
    }
}
