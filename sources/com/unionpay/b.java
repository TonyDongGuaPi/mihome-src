package com.unionpay;

import android.os.Handler;

final class b implements Handler.Callback {
    b() {
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:5|6|7|8|9|10|11|(1:13)|14|(1:16)|17|(4:21|(1:23)|24|(1:26))) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0024 */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003e A[Catch:{ Exception -> 0x0115 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0053 A[Catch:{ Exception -> 0x0115 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e4 A[Catch:{ Exception -> 0x0115 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0103 A[Catch:{ Exception -> 0x0115 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r8) {
        /*
            r7 = this;
            int r0 = r8.what
            switch(r0) {
                case 1001: goto L_0x0120;
                case 1002: goto L_0x0007;
                default: goto L_0x0005;
            }
        L_0x0005:
            goto L_0x0132
        L_0x0007:
            java.lang.Object r0 = r8.obj     // Catch:{ Exception -> 0x0115 }
            if (r0 == 0) goto L_0x0119
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0115 }
            java.lang.Object r1 = r8.obj     // Catch:{ Exception -> 0x0115 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0115 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r1 = "sign"
            java.lang.String r1 = com.unionpay.utils.i.a((org.json.JSONObject) r0, (java.lang.String) r1)     // Catch:{ Exception -> 0x0115 }
            r2 = 0
            java.lang.String r3 = com.unionpay.UPPayAssistEx.K     // Catch:{ NumberFormatException -> 0x0024 }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x0024 }
            r2 = r3
        L_0x0024:
            java.lang.String r3 = new java.lang.String     // Catch:{ Exception -> 0x0115 }
            java.lang.String r4 = "configs"
            java.lang.String r4 = r0.getString(r4)     // Catch:{ Exception -> 0x0115 }
            r5 = 2
            byte[] r4 = android.util.Base64.decode(r4, r5)     // Catch:{ Exception -> 0x0115 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r4 = ""
            java.lang.String r6 = "sePayConf"
            boolean r6 = r0.has(r6)     // Catch:{ Exception -> 0x0115 }
            if (r6 == 0) goto L_0x004d
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x0115 }
            java.lang.String r6 = "sePayConf"
            java.lang.String r0 = r0.getString(r6)     // Catch:{ Exception -> 0x0115 }
            byte[] r0 = android.util.Base64.decode(r0, r5)     // Catch:{ Exception -> 0x0115 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x0115 }
        L_0x004d:
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Exception -> 0x0115 }
            if (r0 == 0) goto L_0x0055
            java.lang.String r4 = ""
        L_0x0055:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0115 }
            r0.<init>()     // Catch:{ Exception -> 0x0115 }
            r0.append(r3)     // Catch:{ Exception -> 0x0115 }
            r0.append(r4)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r5 = com.unionpay.UPPayAssistEx.H     // Catch:{ Exception -> 0x0115 }
            r0.append(r5)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0115 }
            java.lang.String r0 = com.unionpay.utils.UPUtils.a(r0)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r0 = com.unionpay.utils.b.a((java.lang.String) r0)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r1 = com.unionpay.utils.UPUtils.forConfig(r2, r1)     // Catch:{ Exception -> 0x0115 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0115 }
            if (r2 != 0) goto L_0x0119
            boolean r0 = r1.equals(r0)     // Catch:{ Exception -> 0x0115 }
            if (r0 == 0) goto L_0x0119
            android.content.Context r0 = com.unionpay.UPPayAssistEx.G     // Catch:{ Exception -> 0x0115 }
            java.lang.Object r8 = r8.obj     // Catch:{ Exception -> 0x0115 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x0115 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0115 }
            java.lang.String r2 = "configs"
            r1.<init>(r2)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r2 = com.unionpay.UPPayAssistEx.C     // Catch:{ Exception -> 0x0115 }
            r1.append(r2)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0115 }
            com.unionpay.utils.UPUtils.a((android.content.Context) r0, (java.lang.String) r8, (java.lang.String) r1)     // Catch:{ Exception -> 0x0115 }
            android.content.Context r8 = com.unionpay.UPPayAssistEx.G     // Catch:{ Exception -> 0x0115 }
            java.lang.String r0 = com.unionpay.UPPayAssistEx.K     // Catch:{ Exception -> 0x0115 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0115 }
            java.lang.String r2 = "mode"
            r1.<init>(r2)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r2 = com.unionpay.UPPayAssistEx.C     // Catch:{ Exception -> 0x0115 }
            r1.append(r2)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0115 }
            com.unionpay.utils.UPUtils.a((android.content.Context) r8, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ Exception -> 0x0115 }
            android.content.Context r8 = com.unionpay.UPPayAssistEx.G     // Catch:{ Exception -> 0x0115 }
            java.lang.String r0 = com.unionpay.UPPayAssistEx.H     // Catch:{ Exception -> 0x0115 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0115 }
            java.lang.String r2 = "or"
            r1.<init>(r2)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r2 = com.unionpay.UPPayAssistEx.C     // Catch:{ Exception -> 0x0115 }
            r1.append(r2)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0115 }
            com.unionpay.utils.UPUtils.a((android.content.Context) r8, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r8 = com.unionpay.UPPayAssistEx.A     // Catch:{ Exception -> 0x0115 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Exception -> 0x0115 }
            if (r8 != 0) goto L_0x00fd
            android.content.Context r8 = com.unionpay.UPPayAssistEx.G     // Catch:{ Exception -> 0x0115 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0115 }
            java.lang.String r1 = "se_configs"
            r0.<init>(r1)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r1 = com.unionpay.UPPayAssistEx.A     // Catch:{ Exception -> 0x0115 }
            r0.append(r1)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0115 }
            com.unionpay.utils.UPUtils.a((android.content.Context) r8, (java.lang.String) r4, (java.lang.String) r0)     // Catch:{ Exception -> 0x0115 }
        L_0x00fd:
            boolean r8 = com.unionpay.UPPayAssistEx.P     // Catch:{ Exception -> 0x0115 }
            if (r8 != 0) goto L_0x0119
            org.json.JSONArray r8 = new org.json.JSONArray     // Catch:{ Exception -> 0x0115 }
            r8.<init>(r3)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r0 = "sort"
            org.json.JSONArray r8 = com.unionpay.UPPayAssistEx.b(r8, r0)     // Catch:{ Exception -> 0x0115 }
            org.json.JSONArray unused = com.unionpay.UPPayAssistEx.W = r8     // Catch:{ Exception -> 0x0115 }
            com.unionpay.UPPayAssistEx.d(r4)     // Catch:{ Exception -> 0x0115 }
            goto L_0x0119
        L_0x0115:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0119:
            boolean r8 = com.unionpay.UPPayAssistEx.P
            if (r8 != 0) goto L_0x0132
            goto L_0x0123
        L_0x0120:
            boolean unused = com.unionpay.UPPayAssistEx.P = true
        L_0x0123:
            android.content.Context r8 = com.unionpay.UPPayAssistEx.G
            org.json.JSONArray r0 = com.unionpay.UPPayAssistEx.W
            int r1 = com.unionpay.UPPayAssistEx.O
            com.unionpay.UPPayAssistEx.a((android.content.Context) r8, (org.json.JSONArray) r0, (int) r1)
        L_0x0132:
            r8 = 1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.b.handleMessage(android.os.Message):boolean");
    }
}
