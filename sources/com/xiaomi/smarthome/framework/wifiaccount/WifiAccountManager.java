package com.xiaomi.smarthome.framework.wifiaccount;

import android.content.Context;
import com.xiaomi.smarthome.application.ApplicationLifeCycle;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.store.CredentialStore;
import com.xiaomi.smarthome.framework.store.PreferenceCredentialStore;
import com.xiaomi.smarthome.miio.WifiAccount;

public class WifiAccountManager extends ApplicationLifeCycle {

    /* renamed from: a  reason: collision with root package name */
    private static WifiAccountManager f17904a;
    private Context b = SHApplication.getAppContext();
    private CredentialStore c = new PreferenceCredentialStore(this.b);
    private WifiAccount d;

    private WifiAccountManager() {
    }

    public static WifiAccountManager a() {
        if (f17904a == null) {
            f17904a = new WifiAccountManager();
        }
        return f17904a;
    }

    public void b() {
        super.b();
    }

    public void a(WifiAccount.WifiItem wifiItem) {
        if (this.d == null) {
            this.d = new WifiAccount();
            this.d.a(this.c.c());
        }
        this.d.a(wifiItem);
        this.c.a(this.d.a());
    }

    public WifiAccount.WifiItem a(String str) {
        if (this.d == null) {
            this.d = new WifiAccount();
            this.d.a(this.c.c());
        }
        return this.d.b(str);
    }

    public void b(String str) {
        if (this.d == null) {
            this.d = new WifiAccount();
            this.d.a(this.c.c());
        }
        this.d.c(str);
    }
}
