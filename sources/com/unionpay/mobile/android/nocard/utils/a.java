package com.unionpay.mobile.android.nocard.utils;

import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.plugin.c;
import com.unionpay.mobile.android.utils.k;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public final class a {
    /* JADX WARNING: Removed duplicated region for block: B:10:0x001f A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(android.os.Bundle r1) {
        /*
            if (r1 == 0) goto L_0x001c
            java.lang.String r0 = "paydata"
            boolean r0 = r1.containsKey(r0)
            if (r0 == 0) goto L_0x0011
            java.lang.String r0 = "paydata"
        L_0x000c:
            java.lang.String r1 = r1.getString(r0)
            goto L_0x001d
        L_0x0011:
            java.lang.String r0 = "tn"
            boolean r0 = r1.containsKey(r0)
            if (r0 == 0) goto L_0x001c
            java.lang.String r0 = "tn"
            goto L_0x000c
        L_0x001c:
            r1 = 0
        L_0x001d:
            if (r1 != 0) goto L_0x0021
            java.lang.String r1 = ""
        L_0x0021:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.utils.a.a(android.os.Bundle):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031 A[SYNTHETIC, Splitter:B:12:0x0031] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String r4) {
        /*
            java.lang.String r0 = "uppay"
            java.lang.String r1 = "decodePayInfo +++ \n"
            com.unionpay.mobile.android.utils.k.a(r0, r1)
            r0 = 0
            if (r4 == 0) goto L_0x000f
            java.lang.String r4 = java.net.URLDecoder.decode(r4)
            goto L_0x0010
        L_0x000f:
            r4 = r0
        L_0x0010:
            java.lang.String r1 = "uppay"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "url deocode result:"
            r2.<init>(r3)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            com.unionpay.mobile.android.utils.k.b(r1, r2)
            if (r4 == 0) goto L_0x002e
            byte[] r4 = com.unionpay.mobile.android.utils.a.a(r4)     // Catch:{ IOException -> 0x002a }
            goto L_0x002f
        L_0x002a:
            r4 = move-exception
            r4.printStackTrace()
        L_0x002e:
            r4 = r0
        L_0x002f:
            if (r4 == 0) goto L_0x003e
            java.lang.String r1 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x003a }
            java.lang.String r2 = "UTF-8"
            r1.<init>(r4, r2)     // Catch:{ UnsupportedEncodingException -> 0x003a }
            r0 = r1
            goto L_0x003e
        L_0x003a:
            r4 = move-exception
            r4.printStackTrace()
        L_0x003e:
            java.lang.String r4 = "uppay"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "order info:"
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r2 = "\n"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.unionpay.mobile.android.utils.k.a(r4, r1)
            java.lang.String r4 = "uppay"
            java.lang.String r1 = "decodePayInfo --- \n"
            com.unionpay.mobile.android.utils.k.a(r4, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.utils.a.a(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0143, code lost:
        if (android.text.TextUtils.isEmpty(r8.d) == false) goto L_0x01b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x01ae, code lost:
        if (r8.I.h.trim().length() > 0) goto L_0x01b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x01b0, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x01de, code lost:
        r0 = r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Intent r7, com.unionpay.mobile.android.model.b r8) {
        /*
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "uppay"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = " ===>"
            r2.<init>(r3)
            java.lang.String r3 = r7.toString()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.unionpay.mobile.android.utils.k.c(r1, r2)
            java.lang.String r1 = r7.getDataString()
            android.os.Bundle r7 = r7.getExtras()
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r7 == 0) goto L_0x0072
            java.lang.String r3 = "reqOriginalId"
            boolean r3 = r7.containsKey(r3)
            if (r3 == 0) goto L_0x0072
            java.lang.String r3 = "uppay"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = " bundle===>"
            r4.<init>(r5)
            java.lang.String r5 = r7.toString()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.unionpay.mobile.android.utils.k.c(r3, r4)
            com.unionpay.mobile.android.plugin.c r3 = r8.I
            java.lang.String r4 = "reqOriginalId"
            int r4 = r7.getInt(r4)
            r3.f9667a = r4
            java.lang.String r3 = "uppay"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "reqID:"
            r4.<init>(r5)
            com.unionpay.mobile.android.plugin.c r5 = r8.I
            int r5 = r5.f9667a
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.unionpay.mobile.android.utils.k.b(r3, r4)
            com.unionpay.mobile.android.plugin.c r3 = r8.I
            java.lang.String r4 = "invokedbyplugin"
            boolean r4 = r7.getBoolean(r4)
            r3.e = r4
            goto L_0x0091
        L_0x0072:
            if (r1 == 0) goto L_0x02d9
            int r3 = r1.length()
            if (r3 <= 0) goto L_0x02d9
            com.unionpay.mobile.android.plugin.c r3 = r8.I
            r3.f9667a = r2
            java.lang.String r3 = "uppay"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "nativeBrowser data:"
            r4.<init>(r5)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.unionpay.mobile.android.utils.k.b(r3, r4)
        L_0x0091:
            r3 = -13268007(0xffffffffff358bd9, float:-2.413164E38)
            r4 = -10705958(0xffffffffff5ca3da, float:-2.9328093E38)
            if (r7 == 0) goto L_0x00de
            java.lang.String r5 = "dlgstyle"
            boolean r5 = r7.getBoolean(r5, r0)
            r8.aM = r5
            java.lang.String r5 = "url_index"
            java.lang.String r5 = r7.getString(r5)
            if (r5 == 0) goto L_0x00bf
            java.lang.String r6 = "[^0-9]+"
            java.util.regex.Pattern r6 = java.util.regex.Pattern.compile(r6)
            java.util.regex.Matcher r6 = r6.matcher(r5)
            boolean r6 = r6.matches()
            if (r6 == 0) goto L_0x00bf
            int r5 = java.lang.Integer.parseInt(r5)
            r8.aO = r5
        L_0x00bf:
            java.lang.String r5 = "server"
            java.lang.String r5 = r7.getString(r5)
            if (r5 == 0) goto L_0x00cf
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x00cf
            r8.bk = r5
        L_0x00cf:
            java.lang.String r5 = "navbargb"
            int r4 = r7.getInt(r5, r4)
            com.unionpay.mobile.android.global.a.M = r4
            java.lang.String r4 = "divlinecolor"
            int r3 = r7.getInt(r4, r3)
            goto L_0x00e2
        L_0x00de:
            r8.aM = r0
            com.unionpay.mobile.android.global.a.M = r4
        L_0x00e2:
            com.unionpay.mobile.android.global.a.N = r3
            com.unionpay.mobile.android.plugin.c r3 = r8.I
            int r3 = r3.f9667a
            r4 = 1
            if (r3 == r2) goto L_0x029d
            switch(r3) {
                case 0: goto L_0x01e1;
                case 1: goto L_0x01b3;
                case 2: goto L_0x01e1;
                case 3: goto L_0x0168;
                case 4: goto L_0x0146;
                case 5: goto L_0x00f0;
                default: goto L_0x00ee;
            }
        L_0x00ee:
            goto L_0x02a1
        L_0x00f0:
            com.unionpay.mobile.android.plugin.c r1 = r8.I
            java.lang.String r2 = "ex_mode"
            java.lang.String r2 = r7.getString(r2)
            r1.c = r2
            java.lang.String r1 = "uppay"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "mServerIdentifier = "
            r2.<init>(r3)
            com.unionpay.mobile.android.plugin.c r3 = r8.I
            java.lang.String r3 = r3.c
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.unionpay.mobile.android.utils.k.a(r1, r2)
            java.lang.String r1 = "appID"
            java.lang.String r1 = r7.getString(r1)
            r8.g = r1
            java.lang.String r1 = r8.g
            if (r1 != 0) goto L_0x0121
            java.lang.String r1 = ""
            r8.g = r1
        L_0x0121:
            java.lang.String r1 = "amt"
            java.lang.String r1 = r7.getString(r1)
            r8.e = r1
            java.lang.String r1 = "aid"
            java.lang.String r7 = r7.getString(r1)
            r8.d = r7
            r8.f = r4
            r8.c = r4
            java.lang.String r7 = r8.e
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x02a1
            java.lang.String r7 = r8.d
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x02a1
            goto L_0x01b0
        L_0x0146:
            java.lang.String r0 = "paydata"
            java.lang.String r7 = r7.getString(r0)
            java.lang.String r0 = "PluginEx"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = " paydata="
            r1.<init>(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            com.unionpay.mobile.android.utils.k.b(r0, r1)
            java.lang.String r7 = a((java.lang.String) r7)
            boolean r7 = b(r7, r8)
            goto L_0x01de
        L_0x0168:
            com.unionpay.mobile.android.plugin.c r1 = r8.I
            java.lang.String r2 = "ex_mode"
            java.lang.String r2 = r7.getString(r2)
            r1.c = r2
            com.unionpay.mobile.android.plugin.c r1 = r8.I
            java.lang.String r2 = "tencentUID"
            java.lang.String r2 = r7.getString(r2)
            r1.g = r2
            com.unionpay.mobile.android.plugin.c r1 = r8.I
            java.lang.String r2 = "tencentWID"
            java.lang.String r2 = r7.getString(r2)
            r1.h = r2
            java.lang.String r7 = a((android.os.Bundle) r7)
            r8.b = r7
            java.lang.String r7 = r8.b
            if (r7 == 0) goto L_0x02a1
            java.lang.String r7 = r8.b
            java.lang.String r7 = r7.trim()
            int r7 = r7.length()
            if (r7 <= 0) goto L_0x02a1
            com.unionpay.mobile.android.plugin.c r7 = r8.I
            java.lang.String r7 = r7.h
            if (r7 == 0) goto L_0x02a1
            com.unionpay.mobile.android.plugin.c r7 = r8.I
            java.lang.String r7 = r7.h
            java.lang.String r7 = r7.trim()
            int r7 = r7.length()
            if (r7 <= 0) goto L_0x02a1
        L_0x01b0:
            r0 = 1
            goto L_0x02a1
        L_0x01b3:
            java.lang.String r0 = "uppayuri"
            java.lang.String r0 = r7.getString(r0)
            com.unionpay.mobile.android.plugin.c r1 = r8.I
            java.lang.String r2 = "resultIntentAction"
            java.lang.String r7 = r7.getString(r2)
            r1.b = r7
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = " result Action="
            r1.<init>(r2)
            com.unionpay.mobile.android.plugin.c r2 = r8.I
            java.lang.String r2 = r2.b
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.unionpay.mobile.android.utils.k.a(r7, r1)
            boolean r7 = a((java.lang.String) r0, (com.unionpay.mobile.android.model.b) r8)
        L_0x01de:
            r0 = r7
            goto L_0x02a1
        L_0x01e1:
            java.lang.String r1 = "ex_mode"
            java.lang.String r1 = r7.getString(r1)
            if (r1 == 0) goto L_0x01ed
            com.unionpay.mobile.android.plugin.c r2 = r8.I
            r2.c = r1
        L_0x01ed:
            java.lang.String r1 = "uppay"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "mServerIdentifier = "
            r2.<init>(r3)
            com.unionpay.mobile.android.plugin.c r3 = r8.I
            java.lang.String r3 = r3.c
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.unionpay.mobile.android.utils.k.a(r1, r2)
            java.lang.String r1 = a((android.os.Bundle) r7)
            r8.b = r1
            java.lang.String r1 = "appID"
            java.lang.String r1 = r7.getString(r1)
            r8.g = r1
            java.lang.String r1 = r8.g
            if (r1 != 0) goto L_0x021a
            java.lang.String r1 = ""
            r8.g = r1
        L_0x021a:
            java.lang.String r1 = "source"
            java.lang.String r1 = r7.getString(r1)
            if (r1 == 0) goto L_0x022a
            java.lang.String r2 = "samsung_out"
            boolean r1 = r1.equals(r2)
            com.unionpay.mobile.android.model.b.bm = r1
        L_0x022a:
            java.lang.String r1 = "frontNotifyByPlugin"
            java.lang.String r1 = r7.getString(r1)
            if (r1 == 0) goto L_0x023b
            int r1 = r1.length()
            if (r1 != 0) goto L_0x0239
            goto L_0x023b
        L_0x0239:
            r1 = 0
            goto L_0x023c
        L_0x023b:
            r1 = 1
        L_0x023c:
            r8.U = r1
            boolean r1 = r8.f
            if (r1 != 0) goto L_0x027f
            java.lang.String r1 = r8.b
            if (r1 == 0) goto L_0x027f
            java.lang.String r1 = r8.b
            java.lang.String r1 = r1.trim()
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x027f
            java.lang.String r0 = "uppay"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "tn from bundle:"
            r1.<init>(r2)
            java.lang.String r2 = r8.b
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.unionpay.mobile.android.utils.k.a(r0, r1)
            r8.c = r4
            java.lang.String r0 = "uppay"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "dw.isNewTypeTn="
            r1.<init>(r2)
            boolean r2 = r8.c
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.unionpay.mobile.android.utils.k.a(r0, r1)
            r0 = 1
        L_0x027f:
            java.lang.String r1 = "ResultURL"
            java.lang.String r7 = r7.getString(r1)
            r8.r = r7
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "result URL:"
            r1.<init>(r2)
            java.lang.String r2 = r8.r
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.unionpay.mobile.android.utils.k.a(r7, r1)
            goto L_0x02a1
        L_0x029d:
            boolean r0 = a((java.lang.String) r1, (com.unionpay.mobile.android.model.b) r8)
        L_0x02a1:
            boolean r7 = r8.aM
            if (r7 == 0) goto L_0x02b5
            boolean r7 = com.unionpay.mobile.android.model.b.bm
            if (r7 != 0) goto L_0x02b5
            r7 = -1
            com.unionpay.mobile.android.global.b.b = r7
            r7 = -2513882(0xffffffffffd9a426, float:NaN)
            com.unionpay.mobile.android.global.b.c = r7
            r7 = -6745(0xffffffffffffe5a7, float:NaN)
            com.unionpay.mobile.android.global.b.d = r7
        L_0x02b5:
            com.unionpay.mobile.android.plugin.c r7 = r8.I
            int r7 = r7.f9667a
            r1 = 2
            if (r7 == r1) goto L_0x02ca
            com.unionpay.mobile.android.plugin.c r7 = r8.I
            int r7 = r7.f9667a
            r1 = 5
            if (r7 == r1) goto L_0x02ca
            com.unionpay.mobile.android.plugin.c r7 = r8.I
            int r7 = r7.f9667a
            r1 = 3
            if (r7 != r1) goto L_0x02ce
        L_0x02ca:
            com.unionpay.mobile.android.plugin.c r7 = r8.I
            r7.d = r4
        L_0x02ce:
            boolean r7 = r8.c
            if (r7 == 0) goto L_0x02d5
            java.lang.String r7 = "1.9"
            goto L_0x02d7
        L_0x02d5:
            java.lang.String r7 = "1.4"
        L_0x02d7:
            r8.f9576a = r7
        L_0x02d9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.utils.a.a(android.content.Intent, com.unionpay.mobile.android.model.b):boolean");
    }

    private static boolean a(String str, b bVar) {
        boolean z = false;
        if (str != null) {
            String[] split = str.split("\\?");
            if (split.length < 2) {
                k.c("uppay", "uppay protocol params error!");
            } else {
                String str2 = split[1];
                k.a("uppay", "parseUPPayURIParams() +++ ");
                k.a("uppay", str2);
                String str3 = null;
                String str4 = null;
                for (String split2 : str2.split(com.alipay.sdk.sys.a.b)) {
                    String[] split3 = split2.split("=");
                    if (split3.length >= 2) {
                        if (split3[0].equalsIgnoreCase("style")) {
                            str3 = split3[1];
                        } else if (split3[0].equalsIgnoreCase("paydata")) {
                            str4 = split3[1];
                        }
                    }
                }
                if (!(str3 == null || !str3.equalsIgnoreCase("token") || str4 == null)) {
                    k.a("uppay", "paydata=" + str4);
                    z = b(a(str4), bVar);
                }
                k.a("uppay", "parseUPPayURIParams() ---");
            }
        }
        return z;
    }

    private static boolean b(String str, b bVar) {
        c cVar;
        String str2;
        if (str == null || str.length() == 0) {
            return false;
        }
        bVar.I.c = "00";
        for (String trim : str.split(",")) {
            String[] split = trim.trim().split("=");
            if (split.length >= 2) {
                if (split[0].trim().equalsIgnoreCase(BaseInfo.KEY_THREAD_NAME)) {
                    bVar.b = split[1].trim();
                } else if (split[0].trim().equalsIgnoreCase("usetestmode")) {
                    if (split[1].trim().equalsIgnoreCase("true")) {
                        cVar = bVar.I;
                        str2 = "01";
                    } else if (split[1].trim().equalsIgnoreCase("test")) {
                        cVar = bVar.I;
                        str2 = "02";
                    } else if (split[1].trim().equalsIgnoreCase("inner")) {
                        cVar = bVar.I;
                        str2 = "98";
                    }
                    cVar.c = str2;
                } else if (split[0].trim().equalsIgnoreCase("ResultURL")) {
                    try {
                        bVar.r = URLDecoder.decode(split[1].trim(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bVar.b != null && bVar.b.length() > 0;
    }
}
