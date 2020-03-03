package com.unionpay.mobile.android.nocard.utils;

import com.unionpay.mobile.android.callback.UPAndroidCallback;

public final class d {

    /* renamed from: a  reason: collision with root package name */
    private static UPAndroidCallback f9584a;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r8, com.unionpay.mobile.android.model.b r9) {
        /*
            java.lang.String r0 = "uppay"
            java.lang.String r1 = "exit() +++"
            com.unionpay.mobile.android.utils.k.b(r0, r1)
            java.lang.String r0 = "uppay"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "reqId="
            r1.<init>(r2)
            com.unionpay.mobile.android.plugin.c r2 = r9.I
            int r2 = r2.f9667a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.unionpay.mobile.android.utils.k.a(r0, r1)
            r0 = r8
            com.unionpay.mobile.android.plugin.BaseActivity r0 = (com.unionpay.mobile.android.plugin.BaseActivity) r0
            com.unionpay.mobile.android.plugin.c r1 = r9.I
            java.lang.String r1 = r1.f
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x020f
            java.lang.String r1 = "uppay"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "result="
            r2.<init>(r3)
            com.unionpay.mobile.android.plugin.c r3 = r9.I
            java.lang.String r3 = r3.f
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.unionpay.mobile.android.utils.k.a(r1, r2)
            com.unionpay.mobile.android.plugin.c r1 = r9.I
            int r1 = r1.f9667a
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r1 == r2) goto L_0x011c
            r3 = -1
            switch(r1) {
                case 0: goto L_0x009e;
                case 1: goto L_0x011c;
                case 2: goto L_0x009e;
                case 3: goto L_0x0050;
                case 4: goto L_0x011c;
                case 5: goto L_0x009e;
                default: goto L_0x004e;
            }
        L_0x004e:
            goto L_0x020f
        L_0x0050:
            java.lang.String r1 = "uppay"
            java.lang.String r2 = "notifyTencentJarResult() +++"
            com.unionpay.mobile.android.utils.k.b(r1, r2)
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
            java.lang.String r2 = "pay_result"
            com.unionpay.mobile.android.plugin.c r4 = r9.I
            java.lang.String r4 = r4.f
            r1.putExtra(r2, r4)
            java.lang.String r2 = "tencentWID"
            com.unionpay.mobile.android.plugin.c r4 = r9.I
            java.lang.String r4 = r4.h
            r1.putExtra(r2, r4)
            java.lang.String r2 = "tencentUID"
            com.unionpay.mobile.android.plugin.c r4 = r9.I
            java.lang.String r4 = r4.g
            r1.putExtra(r2, r4)
            java.lang.String r2 = "bankInfo"
            com.unionpay.mobile.android.plugin.c r4 = r9.I
            java.lang.String r4 = r4.j
            r1.putExtra(r2, r4)
            java.lang.String r2 = "cardType"
            com.unionpay.mobile.android.plugin.c r4 = r9.I
            java.lang.String r4 = r4.k
            r1.putExtra(r2, r4)
            java.lang.String r2 = "cardNo"
            com.unionpay.mobile.android.plugin.c r4 = r9.I
            java.lang.String r4 = r4.i
            r1.putExtra(r2, r4)
            r0.setResult(r3, r1)
            java.lang.String r1 = "uppay"
            java.lang.String r2 = "notifyTencentJarResult() ---"
        L_0x0099:
            com.unionpay.mobile.android.utils.k.b(r1, r2)
            goto L_0x020f
        L_0x009e:
            java.lang.String r1 = "uppay"
            java.lang.String r2 = "notifyAppResult() +++"
            com.unionpay.mobile.android.utils.k.b(r1, r2)
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
            java.lang.String r2 = "pay_result"
            com.unionpay.mobile.android.plugin.c r4 = r9.I
            java.lang.String r4 = r4.f
            r1.putExtra(r2, r4)
            java.lang.String r2 = "result_data"
            java.lang.String r4 = r9.bj
            r1.putExtra(r2, r4)
            java.lang.String r2 = r9.V
            if (r2 == 0) goto L_0x00e4
            java.lang.String r2 = r9.V
            int r2 = r2.length()
            if (r2 <= 0) goto L_0x00e4
            java.lang.String r2 = r9.W
            if (r2 == 0) goto L_0x00e4
            java.lang.String r2 = r9.W
            int r2 = r2.length()
            if (r2 <= 0) goto L_0x00e4
            boolean r2 = r9.U
            if (r2 != 0) goto L_0x00e4
            java.lang.String r2 = "notify_url"
            java.lang.String r4 = r9.V
            r1.putExtra(r2, r4)
            java.lang.String r2 = "notify_msg"
            java.lang.String r4 = r9.W
            r1.putExtra(r2, r4)
        L_0x00e4:
            java.lang.String r2 = r9.n
            if (r2 == 0) goto L_0x00fd
            java.lang.String r2 = "qn"
            java.lang.String r4 = r9.n
            r1.putExtra(r2, r4)
            java.lang.String r2 = "sid"
            java.lang.String r4 = r9.k
            r1.putExtra(r2, r4)
            java.lang.String r2 = "secret"
            java.lang.String r4 = r9.l
            r1.putExtra(r2, r4)
        L_0x00fd:
            com.unionpay.mobile.android.callback.UPAndroidCallback r2 = f9584a
            if (r2 == 0) goto L_0x0110
            com.unionpay.mobile.android.callback.UPAndroidCallback r2 = f9584a
            com.unionpay.mobile.android.plugin.c r4 = r9.I
            java.lang.String r4 = r4.f
            java.lang.String r5 = r9.n
            java.lang.String r6 = r9.k
            java.lang.String r7 = r9.l
            r2.UPAndroidOK(r4, r5, r6, r7)
        L_0x0110:
            r2 = r0
            com.unionpay.mobile.android.plugin.BaseActivity r2 = (com.unionpay.mobile.android.plugin.BaseActivity) r2
            r2.setResult(r3, r1)
            java.lang.String r1 = "uppay"
            java.lang.String r2 = "notifyAppResult() ---"
            goto L_0x0099
        L_0x011c:
            java.lang.String r1 = "uppay"
            java.lang.String r3 = " notifyBrowserResult() +++ "
            com.unionpay.mobile.android.utils.k.b(r1, r3)
            r1 = 0
            com.unionpay.mobile.android.plugin.c r3 = r9.I
            java.lang.String r3 = r3.f
            java.lang.String r4 = "fail"
            boolean r4 = r3.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x0133
            java.lang.String r3 = "1"
            goto L_0x0140
        L_0x0133:
            java.lang.String r4 = "cancel"
            boolean r3 = r3.equalsIgnoreCase(r4)
            if (r3 == 0) goto L_0x013e
            java.lang.String r3 = "-1"
            goto L_0x0140
        L_0x013e:
            java.lang.String r3 = "0"
        L_0x0140:
            com.unionpay.mobile.android.plugin.c r4 = r9.I
            int r4 = r4.f9667a
            r5 = 1
            if (r4 == r5) goto L_0x0170
            r5 = 4
            if (r4 == r5) goto L_0x014b
            goto L_0x0197
        L_0x014b:
            android.content.Intent r1 = new android.content.Intent
            java.lang.String r4 = "com.UCMobile.PluginApp.ActivityState"
            r1.<init>(r4)
            java.lang.String r4 = "ActivityState"
            java.lang.String r5 = "inactive"
            r1.putExtra(r4, r5)
            java.lang.String r4 = "android.intent.category.DEFAULT"
            r1.addCategory(r4)
            r0.sendBroadcast(r1)
            android.content.Intent r1 = new android.content.Intent
            java.lang.String r4 = "com.unionpay.uppay.resultURL"
            r1.<init>(r4)
            java.lang.String r4 = "uppay"
            java.lang.String r5 = " uc browser "
            com.unionpay.mobile.android.utils.k.b(r4, r5)
            goto L_0x0197
        L_0x0170:
            android.content.Intent r1 = new android.content.Intent
            com.unionpay.mobile.android.plugin.c r4 = r9.I
            java.lang.String r4 = r4.b
            r1.<init>(r4)
            java.lang.String r4 = "uppay"
            java.lang.String r5 = " other browser "
            com.unionpay.mobile.android.utils.k.b(r4, r5)
            java.lang.String r4 = "uppay"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = " result Action="
            r5.<init>(r6)
            com.unionpay.mobile.android.plugin.c r6 = r9.I
            java.lang.String r6 = r6.b
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.unionpay.mobile.android.utils.k.a(r4, r5)
        L_0x0197:
            java.lang.String r4 = r9.r
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x020f
            java.lang.String r4 = "exit"
            java.lang.String r5 = r9.r
            boolean r4 = r4.equalsIgnoreCase(r5)
            if (r4 != 0) goto L_0x020f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r9.r
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            java.lang.String r4 = "uppay"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "result URL= "
            r5.<init>(r6)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            com.unionpay.mobile.android.utils.k.a(r4, r5)
            com.unionpay.mobile.android.plugin.c r4 = r9.I     // Catch:{ Exception -> 0x0205 }
            int r4 = r4.f9667a     // Catch:{ Exception -> 0x0205 }
            if (r2 != r4) goto L_0x01e7
            android.net.Uri r1 = android.net.Uri.parse(r3)     // Catch:{ Exception -> 0x0205 }
            android.content.Intent r2 = new android.content.Intent     // Catch:{ Exception -> 0x0205 }
            java.lang.String r3 = "android.intent.action.VIEW"
            r2.<init>(r3, r1)     // Catch:{ Exception -> 0x0205 }
            java.lang.String r1 = "android.intent.category.BROWSABLE"
            r2.addCategory(r1)     // Catch:{ Exception -> 0x0205 }
            r0.startActivity(r2)     // Catch:{ Exception -> 0x0205 }
            goto L_0x0209
        L_0x01e7:
            java.lang.String r2 = "ResultURL"
            r1.putExtra(r2, r3)     // Catch:{ Exception -> 0x0205 }
            java.lang.String r2 = "browser"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0205 }
            r3.<init>()     // Catch:{ Exception -> 0x0205 }
            java.lang.String r4 = r1.toURI()     // Catch:{ Exception -> 0x0205 }
            r3.append(r4)     // Catch:{ Exception -> 0x0205 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0205 }
            com.unionpay.mobile.android.utils.k.a(r2, r3)     // Catch:{ Exception -> 0x0205 }
            r0.sendBroadcast(r1)     // Catch:{ Exception -> 0x0205 }
            goto L_0x0209
        L_0x0205:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0209:
            java.lang.String r1 = "uppay"
            java.lang.String r2 = " notifyBrowserResult() --- "
            goto L_0x0099
        L_0x020f:
            java.lang.String r1 = r9.V
            if (r1 == 0) goto L_0x023c
            java.lang.String r1 = r9.V
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x023c
            java.lang.String r1 = r9.W
            if (r1 == 0) goto L_0x023c
            java.lang.String r1 = r9.W
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x023c
            boolean r1 = r9.U
            java.lang.String r2 = r9.V
            java.lang.String r9 = r9.W
            if (r1 == 0) goto L_0x023c
            java.lang.Thread r1 = new java.lang.Thread
            com.unionpay.mobile.android.nocard.utils.e r3 = new com.unionpay.mobile.android.nocard.utils.e
            r3.<init>(r2, r9, r8)
            r1.<init>(r3)
            r1.start()
        L_0x023c:
            r0.finish()
            java.lang.String r8 = "uppay"
            java.lang.String r9 = "exit() +++"
            com.unionpay.mobile.android.utils.k.b(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.utils.d.a(android.content.Context, com.unionpay.mobile.android.model.b):void");
    }
}
