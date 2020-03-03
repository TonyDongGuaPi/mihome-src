package com.xiaomi.jr.account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.xiaomi.jr.common.utils.Utils;

public class XiaomiAccountInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f10279a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    Bundle g;
    boolean h;

    @NonNull
    public static XiaomiAccountInfo a(Bundle bundle) {
        Utils.b();
        XiaomiAccountInfo xiaomiAccountInfo = new XiaomiAccountInfo();
        xiaomiAccountInfo.f10279a = bundle.getString("sid");
        xiaomiAccountInfo.c = bundle.getString("serviceToken");
        xiaomiAccountInfo.d = bundle.getString("security");
        xiaomiAccountInfo.b = bundle.getString("cUserId");
        xiaomiAccountInfo.e = bundle.getString("ph");
        xiaomiAccountInfo.f = bundle.getString(IAccountProvider.d);
        xiaomiAccountInfo.g = bundle;
        return xiaomiAccountInfo;
    }
}
