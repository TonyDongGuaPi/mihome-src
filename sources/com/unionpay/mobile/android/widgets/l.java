package com.unionpay.mobile.android.widgets;

import android.view.View;

final class l implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ k f9796a;

    l(k kVar) {
        this.f9796a = kVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0085 A[LOOP:0: B:15:0x007f->B:17:0x0085, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r6) {
        /*
            r5 = this;
            com.unionpay.mobile.android.widgets.k r0 = r5.f9796a
            boolean r0 = r0.r
            r1 = 0
            if (r0 == 0) goto L_0x0090
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            com.unionpay.mobile.android.widgets.k r2 = r5.f9796a     // Catch:{ JSONException -> 0x006e }
            java.lang.String r2 = r2.a()     // Catch:{ JSONException -> 0x006e }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ JSONException -> 0x006e }
            r3 = 1
            if (r2 != 0) goto L_0x005b
            com.unionpay.mobile.android.widgets.k r2 = r5.f9796a     // Catch:{ JSONException -> 0x006e }
            java.lang.String r2 = r2.a()     // Catch:{ JSONException -> 0x006e }
            java.lang.String r4 = "[A-Za-z0-9]{8,32}"
            boolean r2 = r2.matches(r4)     // Catch:{ JSONException -> 0x006e }
            if (r2 == 0) goto L_0x0045
            com.unionpay.mobile.android.widgets.k r1 = r5.f9796a     // Catch:{ JSONException -> 0x006e }
            r1.a((boolean) r3)     // Catch:{ JSONException -> 0x006e }
            java.lang.String r1 = "value"
            com.unionpay.mobile.android.widgets.k r2 = r5.f9796a     // Catch:{ JSONException -> 0x006e }
            java.lang.String r2 = r2.h()     // Catch:{ JSONException -> 0x006e }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x006e }
            java.lang.String r1 = "action"
            com.unionpay.mobile.android.widgets.k r2 = r5.f9796a     // Catch:{ JSONException -> 0x006e }
            java.lang.String r2 = r2.t     // Catch:{ JSONException -> 0x006e }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x006e }
            goto L_0x0072
        L_0x0045:
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD     // Catch:{ JSONException -> 0x006e }
            java.lang.String r2 = r2.aD     // Catch:{ JSONException -> 0x006e }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ JSONException -> 0x006e }
            com.unionpay.mobile.android.languages.c r4 = com.unionpay.mobile.android.languages.c.bD     // Catch:{ JSONException -> 0x006e }
            java.lang.String r4 = r4.C     // Catch:{ JSONException -> 0x006e }
            r3[r1] = r4     // Catch:{ JSONException -> 0x006e }
            java.lang.String r1 = java.lang.String.format(r2, r3)     // Catch:{ JSONException -> 0x006e }
            java.lang.String r2 = "errMsg"
        L_0x0057:
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x006e }
            goto L_0x0072
        L_0x005b:
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD     // Catch:{ JSONException -> 0x006e }
            java.lang.String r2 = r2.aC     // Catch:{ JSONException -> 0x006e }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ JSONException -> 0x006e }
            com.unionpay.mobile.android.languages.c r4 = com.unionpay.mobile.android.languages.c.bD     // Catch:{ JSONException -> 0x006e }
            java.lang.String r4 = r4.C     // Catch:{ JSONException -> 0x006e }
            r3[r1] = r4     // Catch:{ JSONException -> 0x006e }
            java.lang.String r1 = java.lang.String.format(r2, r3)     // Catch:{ JSONException -> 0x006e }
            java.lang.String r2 = "errMsg"
            goto L_0x0057
        L_0x006e:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0072:
            r6.setTag(r0)
            com.unionpay.mobile.android.widgets.k r0 = r5.f9796a
            java.util.ArrayList r0 = r0.o
            java.util.Iterator r0 = r0.iterator()
        L_0x007f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x008f
            java.lang.Object r1 = r0.next()
            android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
            r1.onClick(r6)
            goto L_0x007f
        L_0x008f:
            return
        L_0x0090:
            com.unionpay.mobile.android.widgets.k r0 = r5.f9796a
            com.unionpay.mobile.android.widgets.u r0 = r0.b
            r0.e()
            com.unionpay.mobile.android.widgets.k r0 = r5.f9796a
            r0.a((boolean) r1)
            com.unionpay.mobile.android.widgets.k r0 = r5.f9796a
            java.util.ArrayList r0 = r0.p
            java.util.Iterator r0 = r0.iterator()
        L_0x00a6:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00b6
            java.lang.Object r1 = r0.next()
            android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
            r1.onClick(r6)
            goto L_0x00a6
        L_0x00b6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.widgets.l.onClick(android.view.View):void");
    }
}
