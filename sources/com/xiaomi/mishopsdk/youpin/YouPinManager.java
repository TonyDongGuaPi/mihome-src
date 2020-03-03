package com.xiaomi.mishopsdk.youpin;

public class YouPinManager {
    protected static IYouPinProxy sYouPinProxy;

    public static synchronized void setYouPinProxy(IYouPinProxy iYouPinProxy) {
        synchronized (YouPinManager.class) {
            sYouPinProxy = iYouPinProxy;
        }
    }

    public static synchronized IYouPinProxy getYouPinProxy() {
        IYouPinProxy iYouPinProxy;
        synchronized (YouPinManager.class) {
            iYouPinProxy = sYouPinProxy;
        }
        return iYouPinProxy;
    }
}
