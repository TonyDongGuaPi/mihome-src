package com.alipay.zoloz.android.phone.mrpc.core;

import java.util.List;
import org.apache.http.Header;

public class RpcParams {

    /* renamed from: a  reason: collision with root package name */
    private String f1193a;
    private List<Header> b;
    private boolean c;

    public String getGwUrl() {
        return this.f1193a;
    }

    public void setGwUrl(String str) {
        this.f1193a = str;
    }

    public List<Header> getHeaders() {
        return this.b;
    }

    public void setHeaders(List<Header> list) {
        this.b = list;
    }

    public boolean isGzip() {
        return this.c;
    }

    public void setGzip(boolean z) {
        this.c = z;
    }
}
