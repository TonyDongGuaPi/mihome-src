package com.alipay.mobile.security.bio.service;

import java.util.HashMap;

public class BioSysBehavior {

    /* renamed from: a  reason: collision with root package name */
    private BioSysBehaviorType f1005a = BioSysBehaviorType.EVENT;
    private HashMap<String, String> b = new HashMap<>();
    private String c = "";

    public void addExt(String str, String str2) {
        this.b.put(str, str2);
    }

    public BioSysBehaviorType getType() {
        return this.f1005a;
    }

    public void setType(BioSysBehaviorType bioSysBehaviorType) {
        this.f1005a = bioSysBehaviorType;
    }

    public String getMsg() {
        return this.c;
    }

    public void setMsg(String str) {
        this.c = str;
    }

    public HashMap<String, String> getExt() {
        return this.b;
    }
}
