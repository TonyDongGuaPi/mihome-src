package com.loc;

import com.amap.api.services.core.AMapException;
import com.facebook.internal.NativeProtocol;

public final class t extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private String f6636a;
    private String b;
    private String c;
    private String d;
    private int e;

    public t(String str) {
        super(str);
        this.f6636a = "未知的错误";
        this.b = "";
        this.c = "1900";
        this.d = NativeProtocol.ERROR_UNKNOWN_ERROR;
        this.e = -1;
        this.f6636a = str;
        if (AMapException.AMAP_CLIENT_IO_EXCEPTION.equals(str)) {
            this.e = 21;
            this.c = "1902";
            this.d = "IOException";
        } else if ("socket 连接异常 - SocketException".equals(str)) {
            this.e = 22;
        } else if (AMapException.AMAP_CLIENT_SOCKET_TIMEOUT_EXCEPTION.equals(str)) {
            this.e = 23;
            this.c = "1802";
            this.d = "SocketTimeoutException";
        } else if (AMapException.AMAP_CLIENT_INVALID_PARAMETER.equals(str)) {
            this.e = 24;
            this.c = "1901";
            this.d = "IllegalArgumentException";
        } else if (AMapException.AMAP_CLIENT_NULLPOINT_EXCEPTION.equals(str)) {
            this.e = 25;
            this.c = "1903";
            this.d = "NullPointException";
        } else if (AMapException.AMAP_CLIENT_URL_EXCEPTION.equals(str)) {
            this.e = 26;
            this.c = "1803";
            this.d = "MalformedURLException";
        } else if (AMapException.AMAP_CLIENT_UNKNOWHOST_EXCEPTION.equals(str)) {
            this.e = 27;
            this.c = "1804";
            this.d = "UnknownHostException";
        } else if ("服务器连接失败 - UnknownServiceException".equals(str)) {
            this.e = 28;
            this.c = "1805";
            this.d = "CannotConnectToHostException";
        } else if (AMapException.AMAP_CLIENT_ERROR_PROTOCOL.equals(str)) {
            this.e = 29;
            this.c = "1801";
            this.d = "ProtocolException";
        } else if ("http连接失败 - ConnectionException".equals(str)) {
            this.e = 30;
            this.c = "1806";
            this.d = "ConnectionException";
        } else if ("未知的错误".equals(str)) {
            this.e = 31;
        } else if ("key鉴权失败".equals(str)) {
            this.e = 32;
        } else if ("requeust is null".equals(str)) {
            this.e = 1;
        } else if ("request url is empty".equals(str)) {
            this.e = 2;
        } else if ("response is null".equals(str)) {
            this.e = 3;
        } else if ("thread pool has exception".equals(str)) {
            this.e = 4;
        } else if ("sdk name is invalid".equals(str)) {
            this.e = 5;
        } else if ("sdk info is null".equals(str)) {
            this.e = 6;
        } else if ("sdk packages is null".equals(str)) {
            this.e = 7;
        } else if ("线程池为空".equals(str)) {
            this.e = 8;
        } else if ("获取对象错误".equals(str)) {
            this.e = 101;
        } else {
            this.e = -1;
        }
    }

    public t(String str, String str2) {
        this(str);
        this.b = str2;
    }

    public final String a() {
        return this.f6636a;
    }

    public final void a(int i) {
        this.e = i;
    }

    public final String b() {
        return this.c;
    }

    public final String c() {
        return this.d;
    }

    public final String d() {
        return this.b;
    }

    public final int e() {
        return this.e;
    }
}
