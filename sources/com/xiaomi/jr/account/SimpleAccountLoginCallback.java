package com.xiaomi.jr.account;

import com.xiaomi.jr.QualityMonitor;
import com.xiaomi.jr.account.AccountNotifier;

public class SimpleAccountLoginCallback implements AccountNotifier.AccountLoginCallback {
    public void a() {
    }

    public void a(int i) {
        a(i == -1);
        if (i != -1 && i != 4) {
            QualityMonitor.a(Constants.i, "login_failure", "code", String.valueOf(i));
        }
    }

    public void a(boolean z) {
        if (z) {
            a();
        }
    }
}
