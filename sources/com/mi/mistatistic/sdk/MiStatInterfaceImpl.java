package com.mi.mistatistic.sdk;

public class MiStatInterfaceImpl extends MiStatInterface {
    private static MiStatInterfaceImpl i;

    public static synchronized MiStatInterfaceImpl d() {
        MiStatInterfaceImpl miStatInterfaceImpl;
        synchronized (MiStatInterfaceImpl.class) {
            if (i == null) {
                i = new MiStatInterfaceImpl();
            }
            miStatInterfaceImpl = i;
        }
        return miStatInterfaceImpl;
    }
}
