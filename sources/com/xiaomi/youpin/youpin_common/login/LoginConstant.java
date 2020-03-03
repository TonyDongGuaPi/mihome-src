package com.xiaomi.youpin.youpin_common.login;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LoginConstant {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23797a = "com.xiaomi";
    public static final String b = "xiaomiio";
    public static final String c = "xiaoqiang";
    public static final String d = "passportapi";
    public static final String e = "xiaomihome";
    public static final String f = "miotstore";
    public static final String g = "recharge-web";
    public static final String h = "mi_eshopm_go";
    public static final String i = "mi_huodong";
    public static final String j = "eshopmobile";
    public static final String k = "ypsupport2";
    public static final String l = ".io.mi.com";
    public static final String m = "api.gorouter.info";
    public static final String n = "account.xiaomi.com";
    public static final String o = ".home.mi.com";
    public static final String p = "shopapi.io.mi.com";
    public static final String q = "web.recharge.pay.xiaomi.com";
    public static final String r = "m.mi.com";
    public static final String s = ".huodong.mi.com";
    public static final String t = "api.m.mi.com";
    public static final String u = ".youpin.mi.com";
    public static final String v = ".m.youpin.mi.com";
    public static final String w = ".xiaomiyoupin.com";
    public static final String x = "ypsupport2.kefu.mi.com";

    @Retention(RetentionPolicy.SOURCE)
    public @interface DomainType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SidType {
    }

    public static class WXLoginIntentConstant {

        /* renamed from: a  reason: collision with root package name */
        public static final String f23798a = "action.wx.login.start";
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
                case -1498652355: goto L_0x0064;
                case -1419722781: goto L_0x005a;
                case -513303352: goto L_0x0050;
                case -360749757: goto L_0x0046;
                case -242974118: goto L_0x003b;
                case 265338657: goto L_0x0031;
                case 1587352602: goto L_0x0027;
                case 1781001842: goto L_0x001d;
                case 2026863872: goto L_0x0013;
                case 2144015150: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x006f
        L_0x0009:
            java.lang.String r0 = "recharge-web"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x006f
            r1 = 5
            goto L_0x0070
        L_0x0013:
            java.lang.String r0 = "miotstore"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x006f
            r1 = 4
            goto L_0x0070
        L_0x001d:
            java.lang.String r0 = "mi_eshopm_go"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x006f
            r1 = 6
            goto L_0x0070
        L_0x0027:
            java.lang.String r0 = "xiaomihome"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x006f
            r1 = 3
            goto L_0x0070
        L_0x0031:
            java.lang.String r0 = "xiaomiio"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x006f
            r1 = 0
            goto L_0x0070
        L_0x003b:
            java.lang.String r0 = "ypsupport2"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x006f
            r1 = 9
            goto L_0x0070
        L_0x0046:
            java.lang.String r0 = "xiaoqiang"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x006f
            r1 = 1
            goto L_0x0070
        L_0x0050:
            java.lang.String r0 = "passportapi"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x006f
            r1 = 2
            goto L_0x0070
        L_0x005a:
            java.lang.String r0 = "mi_huodong"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x006f
            r1 = 7
            goto L_0x0070
        L_0x0064:
            java.lang.String r0 = "eshopmobile"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x006f
            r1 = 8
            goto L_0x0070
        L_0x006f:
            r1 = -1
        L_0x0070:
            switch(r1) {
                case 0: goto L_0x0091;
                case 1: goto L_0x008e;
                case 2: goto L_0x008b;
                case 3: goto L_0x0088;
                case 4: goto L_0x0085;
                case 5: goto L_0x0082;
                case 6: goto L_0x007f;
                case 7: goto L_0x007c;
                case 8: goto L_0x0079;
                case 9: goto L_0x0076;
                default: goto L_0x0073;
            }
        L_0x0073:
            java.lang.String r1 = ""
            return r1
        L_0x0076:
            java.lang.String r1 = "ypsupport2.kefu.mi.com"
            return r1
        L_0x0079:
            java.lang.String r1 = "api.m.mi.com"
            return r1
        L_0x007c:
            java.lang.String r1 = ".huodong.mi.com"
            return r1
        L_0x007f:
            java.lang.String r1 = "m.mi.com"
            return r1
        L_0x0082:
            java.lang.String r1 = "web.recharge.pay.xiaomi.com"
            return r1
        L_0x0085:
            java.lang.String r1 = "shopapi.io.mi.com"
            return r1
        L_0x0088:
            java.lang.String r1 = ".home.mi.com"
            return r1
        L_0x008b:
            java.lang.String r1 = "account.xiaomi.com"
            return r1
        L_0x008e:
            java.lang.String r1 = "api.gorouter.info"
            return r1
        L_0x0091:
            java.lang.String r1 = ".io.mi.com"
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.youpin_common.login.LoginConstant.a(java.lang.String):java.lang.String");
    }
}
