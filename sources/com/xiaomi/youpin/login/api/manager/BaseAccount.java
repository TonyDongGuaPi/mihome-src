package com.xiaomi.youpin.login.api.manager;

import com.xiaomi.accountsdk.account.data.AccountInfo;

public class BaseAccount {

    /* renamed from: a  reason: collision with root package name */
    public String f23409a;
    public String b;
    public String c;
    public String d;
    public String e;
    public long f;

    public void a(AccountInfo accountInfo, long j) {
        this.f23409a = accountInfo.getUserId();
        this.c = accountInfo.getServiceToken();
        this.d = accountInfo.getSecurity();
        this.e = accountInfo.getPassToken();
        this.b = accountInfo.getEncryptedUserId();
        this.f = j;
    }
}
