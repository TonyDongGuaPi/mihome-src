package com.tencent.smtt.sdk;

import android.os.HandlerThread;

class al extends HandlerThread {

    /* renamed from: a  reason: collision with root package name */
    private static al f9141a;

    public al(String str) {
        super(str);
    }

    public static synchronized al a() {
        al alVar;
        synchronized (al.class) {
            if (f9141a == null) {
                f9141a = new al("TbsHandlerThread");
                f9141a.start();
            }
            alVar = f9141a;
        }
        return alVar;
    }
}
