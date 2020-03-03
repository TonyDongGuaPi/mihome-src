package cn.com.fmsh.tsm.business;

import cn.com.fmsh.tsm.business.core.BusinessManagerImpl;

public class BusinessManagerFactory {
    private static BusinessManager handler;

    private BusinessManagerFactory() {
    }

    public static BusinessManager getBusinessManager() {
        if (handler == null) {
            businessManagerInit();
        }
        return handler;
    }

    private static synchronized void businessManagerInit() {
        synchronized (BusinessManagerFactory.class) {
            if (handler == null) {
                handler = new BusinessManagerImpl();
            }
        }
    }
}
