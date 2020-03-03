package com.xiaomi.youpin.login.setting;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LoginConstant {
    public static final String A = "tsmapi.pay.xiaomi.com";

    /* renamed from: a  reason: collision with root package name */
    public static final String f23596a = "com.xiaomi";
    public static final String b = "xiaomiio";
    public static final String c = "xiaoqiang";
    public static final String d = "passportapi";
    public static final String e = "xiaomihome";
    public static final String f = "miotstore";
    public static final String g = "mipaycom";
    public static final String h = "mi_eshopm_go";
    public static final String i = "mi_huodong";
    public static final String j = "eshopmobile";
    public static final String k = "i.ai.mi.com";
    public static final String l = "miot-third-test";
    public static final String m = "ypsupport2";
    public static final String n = "tsm-auth";
    public static final String o = ".io.mi.com";
    public static final String p = "api.gorouter.info";
    public static final String q = "account.xiaomi.com";
    public static final String r = ".home.mi.com";
    public static final String s = "shopapi.io.mi.com";
    public static final String t = "web.recharge.pay.xiaomi.com";
    public static final String u = "m.mi.com";
    public static final String v = ".huodong.mi.com";
    public static final String w = "api.m.mi.com";
    public static final String x = ".youpin.mi.com";
    public static final String y = ".m.youpin.mi.com";
    public static final String z = "ypsupport2.kefu.mi.com";

    @Retention(RetentionPolicy.SOURCE)
    public @interface DomainType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SidType {
    }

    public static class WXLoginIntentConstant {

        /* renamed from: a  reason: collision with root package name */
        public static final String f23597a = "action.wx.login.start";
        public static final String b = "action.wx.login.complete";
        public static final String c = "login_success";
        public static final String d = "auth_code";
        public static final String e = "error_code";
        public static final String f = "state";
        public static final String g = "transcation";
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r1) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case -1843581369: goto L_0x007c;
                case -1498652355: goto L_0x0071;
                case -1419722781: goto L_0x0067;
                case -1308845963: goto L_0x005d;
                case -1104067068: goto L_0x0052;
                case -513303352: goto L_0x0048;
                case -360749757: goto L_0x003e;
                case -242974118: goto L_0x0033;
                case 265338657: goto L_0x0029;
                case 1587352602: goto L_0x001f;
                case 1781001842: goto L_0x0014;
                case 2026863872: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0087
        L_0x0009:
            java.lang.String r0 = "miotstore"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0087
            r1 = 4
            goto L_0x0088
        L_0x0014:
            java.lang.String r0 = "mi_eshopm_go"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0087
            r1 = 6
            goto L_0x0088
        L_0x001f:
            java.lang.String r0 = "xiaomihome"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0087
            r1 = 3
            goto L_0x0088
        L_0x0029:
            java.lang.String r0 = "xiaomiio"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0087
            r1 = 0
            goto L_0x0088
        L_0x0033:
            java.lang.String r0 = "ypsupport2"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0087
            r1 = 10
            goto L_0x0088
        L_0x003e:
            java.lang.String r0 = "xiaoqiang"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0087
            r1 = 1
            goto L_0x0088
        L_0x0048:
            java.lang.String r0 = "passportapi"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0087
            r1 = 2
            goto L_0x0088
        L_0x0052:
            java.lang.String r0 = "miot-third-test"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0087
            r1 = 9
            goto L_0x0088
        L_0x005d:
            java.lang.String r0 = "mipaycom"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0087
            r1 = 5
            goto L_0x0088
        L_0x0067:
            java.lang.String r0 = "mi_huodong"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0087
            r1 = 7
            goto L_0x0088
        L_0x0071:
            java.lang.String r0 = "eshopmobile"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0087
            r1 = 8
            goto L_0x0088
        L_0x007c:
            java.lang.String r0 = "tsm-auth"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0087
            r1 = 11
            goto L_0x0088
        L_0x0087:
            r1 = -1
        L_0x0088:
            switch(r1) {
                case 0: goto L_0x00af;
                case 1: goto L_0x00ac;
                case 2: goto L_0x00a9;
                case 3: goto L_0x00a6;
                case 4: goto L_0x00a3;
                case 5: goto L_0x00a0;
                case 6: goto L_0x009d;
                case 7: goto L_0x009a;
                case 8: goto L_0x0097;
                case 9: goto L_0x0094;
                case 10: goto L_0x0091;
                case 11: goto L_0x008e;
                default: goto L_0x008b;
            }
        L_0x008b:
            java.lang.String r1 = ""
            return r1
        L_0x008e:
            java.lang.String r1 = "tsmapi.pay.xiaomi.com"
            return r1
        L_0x0091:
            java.lang.String r1 = "ypsupport2.kefu.mi.com"
            return r1
        L_0x0094:
            java.lang.String r1 = ".io.mi.com"
            return r1
        L_0x0097:
            java.lang.String r1 = "api.m.mi.com"
            return r1
        L_0x009a:
            java.lang.String r1 = ".huodong.mi.com"
            return r1
        L_0x009d:
            java.lang.String r1 = "m.mi.com"
            return r1
        L_0x00a0:
            java.lang.String r1 = "web.recharge.pay.xiaomi.com"
            return r1
        L_0x00a3:
            java.lang.String r1 = "shopapi.io.mi.com"
            return r1
        L_0x00a6:
            java.lang.String r1 = ".home.mi.com"
            return r1
        L_0x00a9:
            java.lang.String r1 = "account.xiaomi.com"
            return r1
        L_0x00ac:
            java.lang.String r1 = "api.gorouter.info"
            return r1
        L_0x00af:
            java.lang.String r1 = ".io.mi.com"
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.setting.LoginConstant.a(java.lang.String):java.lang.String");
    }
}
