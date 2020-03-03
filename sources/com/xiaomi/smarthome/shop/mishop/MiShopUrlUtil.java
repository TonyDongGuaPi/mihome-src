package com.xiaomi.smarthome.shop.mishop;

import android.text.TextUtils;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.youpin.common.util.UrlUtils;
import java.util.Map;

public class MiShopUrlUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final int f22176a = 101;
    private static final String b = "20118.00007";

    public static String a(String str) {
        int i;
        int i2;
        if (!ProductIdMapDataManager.a().c()) {
            return str;
        }
        Map<String, String> c = UrlUtils.c(str);
        String str2 = c.containsKey("spmref") ? c.get("spmref") : "";
        if (c.containsKey("pid")) {
            try {
                i2 = Integer.parseInt(c.get("pid"));
            } catch (Exception e) {
                e.printStackTrace();
                i2 = -1;
            }
            String b2 = ProductIdMapDataManager.a().b(i2);
            if (!TextUtils.isEmpty(b2)) {
                return b(b2, str2);
            }
        }
        if (c.containsKey(ApiConst.j)) {
            try {
                i = Integer.parseInt(c.get(ApiConst.j));
            } catch (Exception e2) {
                e2.printStackTrace();
                i = -1;
            }
            String a2 = ProductIdMapDataManager.a().a(i);
            if (!TextUtils.isEmpty(a2)) {
                return b(a2, str2);
            }
        }
        return c.containsKey("goodsId") ? b(c.get("goodsId"), str2) : str;
    }

    private static String b(String str, String str2) {
        String str3 = "http://m.mi.com/sdk?pid=101&commodityId=" + str + "&cid=" + b;
        if (TextUtils.isEmpty(str2)) {
            return str3;
        }
        return str3 + "&spmref=" + str2;
    }

    public static String a(String str, String str2) {
        Map<String, String> c = UrlUtils.c(str2);
        String str3 = c.containsKey("spmref") ? c.get("spmref") : "";
        String str4 = c.get("extra_url");
        String str5 = c.get("extra_ver");
        String str6 = "";
        if (!TextUtils.isEmpty(str4)) {
            str6 = str6 + "&extra_url=" + str4;
        }
        if (!TextUtils.isEmpty(str5)) {
            str6 = str6 + "&extra_ver=" + str5;
        }
        if (!TextUtils.isEmpty(str3)) {
            str6 = str6 + "&spmref=" + str3;
        }
        return str + str6;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(java.lang.String r6) {
        /*
            java.util.Map r0 = com.xiaomi.youpin.common.util.UrlUtils.c(r6)
            java.lang.String r1 = "spmref"
            boolean r1 = r0.containsKey(r1)
            if (r1 == 0) goto L_0x0015
            java.lang.String r1 = "spmref"
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0017
        L_0x0015:
            java.lang.String r0 = ""
        L_0x0017:
            android.content.Context r1 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            java.lang.String r3 = ""
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 != 0) goto L_0x0053
            java.lang.String r4 = "UTF-8"
            byte[] r0 = r0.getBytes(r4)     // Catch:{ UnsupportedEncodingException -> 0x004f }
            r4 = 0
            byte[] r0 = android.util.Base64.encode(r0, r4)     // Catch:{ UnsupportedEncodingException -> 0x004f }
            java.lang.String r4 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x004f }
            java.lang.String r5 = "UTF-8"
            r4.<init>(r0, r5)     // Catch:{ UnsupportedEncodingException -> 0x004f }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x004c }
            r0.<init>()     // Catch:{ UnsupportedEncodingException -> 0x004c }
            java.lang.String r3 = "20118.00007\u0001YouPin_"
            r0.append(r3)     // Catch:{ UnsupportedEncodingException -> 0x004c }
            r0.append(r4)     // Catch:{ UnsupportedEncodingException -> 0x004c }
            java.lang.String r3 = r0.toString()     // Catch:{ UnsupportedEncodingException -> 0x004c }
            goto L_0x0053
        L_0x004c:
            r0 = move-exception
            r3 = r4
            goto L_0x0050
        L_0x004f:
            r0 = move-exception
        L_0x0050:
            r0.printStackTrace()
        L_0x0053:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x006d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r6)
            java.lang.String r6 = "&pluginPrevious="
            r0.append(r6)
            r0.append(r3)
            java.lang.String r6 = r0.toString()
        L_0x006d:
            android.net.Uri r6 = android.net.Uri.parse(r6)
            r2.setData(r6)
            java.lang.String r6 = r1.getPackageName()
            r2.setPackage(r6)
            r6 = 268435456(0x10000000, float:2.5243549E-29)
            r2.setFlags(r6)
            boolean r6 = android.text.TextUtils.isEmpty(r3)
            if (r6 != 0) goto L_0x008b
            java.lang.String r6 = "pluginPrevious"
            r2.putExtra(r6, r3)
        L_0x008b:
            r1.startActivity(r2)     // Catch:{ Exception -> 0x008f }
            goto L_0x0093
        L_0x008f:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0093:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.shop.mishop.MiShopUrlUtil.b(java.lang.String):void");
    }
}
