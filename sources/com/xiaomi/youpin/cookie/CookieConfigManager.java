package com.xiaomi.youpin.cookie;

public class CookieConfigManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile CookieConfigManager f23305a;
    private CookieConfig b;

    private CookieConfigManager() {
    }

    public static CookieConfigManager a() {
        if (f23305a == null) {
            synchronized (CookieConfigManager.class) {
                if (f23305a == null) {
                    f23305a = new CookieConfigManager();
                }
            }
        }
        return f23305a;
    }

    public CookieConfig b() {
        return this.b;
    }

    public void a(CookieConfig cookieConfig) {
        this.b = cookieConfig;
    }
}
