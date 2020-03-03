package com.alipay.mobile.security.bio.service;

import java.util.HashMap;
import java.util.Map;

public class BioServiceDescription {

    /* renamed from: a  reason: collision with root package name */
    Class<?> f1003a = null;
    String b = "";
    boolean c = false;
    boolean d = false;
    protected Map<String, String> e = new HashMap();

    public Class<?> getClazz() {
        return this.f1003a;
    }

    public void setClazz(Class<?> cls) {
        this.f1003a = cls;
    }

    public String getInterfaceName() {
        return this.b;
    }

    public void setInterfaceName(String str) {
        this.b = str;
    }

    public boolean isLazy() {
        return this.c;
    }

    public void setLazy(boolean z) {
        this.c = z;
    }

    public boolean isAutoDownloadRes() {
        return this.d;
    }

    public void setAutoDownloadRes(boolean z) {
        this.d = z;
    }

    public Map<String, String> getExtMetaInfo() {
        return this.e;
    }

    public void setExtMetaInfo(Map<String, String> map) {
        this.e = map;
    }
}
