package com.xiaomi.jr.antifraud;

import android.content.Context;
import cn.tongdun.android.shell.FMAgent;
import com.xiaomi.jr.common.AccountEnvironment;

public class Tongdun {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1385a = "Tongdun";
    private static final Object b = new Object();
    private static boolean c;

    public static void a(Context context) {
        synchronized (b) {
            if (!c && context != null) {
                FMAgent.init(context, AccountEnvironment.f1407a ? FMAgent.ENV_SANDBOX : FMAgent.ENV_PRODUCTION);
                c = true;
            }
        }
    }

    public static String b(Context context) {
        String onEvent;
        synchronized (b) {
            if (!c) {
                a(context);
            }
            onEvent = c ? FMAgent.onEvent(context) : null;
        }
        return onEvent;
    }
}
