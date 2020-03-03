package com.alipay.mobile.security.bio.service;

import com.alipay.mobile.security.bio.api.BioCallback;
import com.alipay.mobile.security.bio.utils.SignHelper;
import com.taobao.weex.annotation.JSMethod;
import java.util.HashMap;
import java.util.UUID;

public class BioAppManager extends BioService {

    /* renamed from: a  reason: collision with root package name */
    private HashMap<String, BioAppDescription> f1002a;
    private HashMap<String, BioCallback> b;

    public void onCreate(BioServiceManager bioServiceManager) {
        super.onCreate(bioServiceManager);
        this.f1002a = new HashMap<>();
        this.b = new HashMap<>();
    }

    private static String a() {
        UUID randomUUID = UUID.randomUUID();
        return SignHelper.MD5(System.currentTimeMillis() + JSMethod.NOT_SET + (Math.random() * 10000.0d) + randomUUID.toString());
    }

    public String put(BioAppDescription bioAppDescription, BioCallback bioCallback) {
        String a2 = a();
        bioAppDescription.setTag(a2);
        this.f1002a.put(a2, bioAppDescription);
        this.b.put(a2, bioCallback);
        return a2;
    }

    public BioAppDescription getBioAppDescription(String str) {
        return this.f1002a.get(str);
    }

    public BioCallback getBioCallback(String str) {
        return this.b.get(str);
    }

    public void onDestroy() {
        this.f1002a.clear();
        this.b.clear();
        super.onDestroy();
    }
}
