package com.xiaomi.youpin.login.other.cookie;

public class CookieConfigManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile CookieConfigManager f23577a;
    private CookieConfig b;

    private CookieConfigManager() {
    }

    public static CookieConfigManager a() {
        if (f23577a == null) {
            synchronized (CookieConfigManager.class) {
                if (f23577a == null) {
                    f23577a = new CookieConfigManager();
                }
            }
        }
        return f23577a;
    }

    public CookieConfig b() {
        return this.b;
    }

    public void a(CookieConfig cookieConfig) {
        this.b = cookieConfig;
    }
}
