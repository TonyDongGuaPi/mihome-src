package com.xiaomi.youpin.login.entity.account;

import android.text.TextUtils;

public class AccountParam {

    /* renamed from: a  reason: collision with root package name */
    public String f23511a;
    public String b;
    public String c;
    public String d;

    public AccountParam() {
    }

    public AccountParam(String str, String str2, String str3, String str4) {
        this.f23511a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public boolean a() {
        return !TextUtils.isEmpty(this.f23511a) && !TextUtils.isEmpty(this.b) && !TextUtils.isEmpty(this.c) && !TextUtils.isEmpty(this.d);
    }
}
