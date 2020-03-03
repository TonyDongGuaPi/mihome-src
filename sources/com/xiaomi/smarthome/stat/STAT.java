package com.xiaomi.smarthome.stat;

import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.stat.report.StatReporter;

public final class STAT {

    /* renamed from: a  reason: collision with root package name */
    public static final StatApp f22748a = new StatApp() {
    };
    public static final StatPage b = new StatPage() {
    };
    public static final StatPagev2 c = new StatPagev2() {
    };
    public static final StatClick d = new StatClick() {
    };
    public static final StatPopUp e = new StatPopUp() {
    };
    public static final StatReq f = new StatReq() {
    };
    public static final StatMonitor g = new StatMonitor() {
    };
    public static final StatException h = new StatException() {
    };
    public static final StatResult i = new StatResult() {
    };

    public static final class Scroll {

        /* renamed from: a  reason: collision with root package name */
        private static StatReporter f22749a = new StatReporter("slip");

        public static void a(String str) {
            if (!CoreApi.a().D()) {
                StatReporter statReporter = f22749a;
                Object[] objArr = new Object[2];
                objArr[0] = "name";
                if (str == null) {
                    str = "null";
                }
                objArr[1] = str;
                statReporter.a("home_function_slip", objArr);
            }
        }
    }
}
