package com.mipay.ucashier.pay.alipay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.alipay.sdk.util.i;
import com.mipay.common.base.l;
import com.mipay.ucashier.pay.IPayEntry;
import java.lang.ref.SoftReference;
import org.json.JSONObject;

public class AlipayEntry implements IPayEntry {

    /* renamed from: a  reason: collision with root package name */
    private static final int f8181a = 1;
    /* access modifiers changed from: private */
    public SoftReference<l> b;

    private class a extends Handler {
        private a() {
        }

        /* synthetic */ a(AlipayEntry alipayEntry, a aVar) {
            this();
        }

        private Bundle a(int i, String str, String str2) {
            Bundle bundle = new Bundle();
            bundle.putInt("errcode", i);
            bundle.putString("errDesc", str);
            bundle.putString("result", str2);
            return bundle;
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0076  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r9) {
            /*
                r8 = this;
                int r0 = r9.what
                r1 = 1
                if (r0 != r1) goto L_0x007c
                com.mipay.ucashier.pay.alipay.AlipayEntry r0 = com.mipay.ucashier.pay.alipay.AlipayEntry.this
                java.lang.ref.SoftReference r0 = r0.b
                java.lang.Object r0 = r0.get()
                if (r0 != 0) goto L_0x0012
                return
            L_0x0012:
                com.mipay.ucashier.pay.alipay.AlipayEntry r0 = com.mipay.ucashier.pay.alipay.AlipayEntry.this
                java.lang.ref.SoftReference r0 = r0.b
                java.lang.Object r0 = r0.get()
                com.mipay.common.base.l r0 = (com.mipay.common.base.l) r0
                java.lang.Object r9 = r9.obj
                java.lang.String r9 = (java.lang.String) r9
                int r2 = com.mipay.ucashier.ui.BaseUCashierFragment.RESULT_ERROR
                r3 = 0
                r4 = 0
                com.mipay.ucashier.pay.alipay.AlipayEntry$b r5 = new com.mipay.ucashier.pay.alipay.AlipayEntry$b     // Catch:{ Exception -> 0x0063 }
                r5.<init>(r9)     // Catch:{ Exception -> 0x0063 }
                java.lang.String r6 = r5.a()     // Catch:{ Exception -> 0x0063 }
                java.lang.String r5 = r5.b()     // Catch:{ Exception -> 0x0063 }
                java.lang.String r7 = "{9000}"
                boolean r7 = android.text.TextUtils.equals(r6, r7)     // Catch:{ Exception -> 0x0063 }
                if (r7 == 0) goto L_0x004f
                java.lang.String r7 = "true"
                boolean r5 = android.text.TextUtils.equals(r5, r7)     // Catch:{ Exception -> 0x0063 }
                if (r5 == 0) goto L_0x004f
                int r2 = com.mipay.ucashier.ui.BaseUCashierFragment.RESULT_OK     // Catch:{ Exception -> 0x0063 }
                java.lang.String r3 = "success"
                android.os.Bundle r3 = r8.a(r4, r3, r9)     // Catch:{ Exception -> 0x0063 }
                goto L_0x006b
            L_0x004f:
                java.lang.String r5 = "{6001}"
                boolean r5 = android.text.TextUtils.equals(r6, r5)     // Catch:{ Exception -> 0x0063 }
                if (r5 == 0) goto L_0x006b
                int r2 = com.mipay.ucashier.ui.BaseUCashierFragment.RESULT_CANCELED     // Catch:{ Exception -> 0x0063 }
                r3 = 2
                java.lang.String r5 = "user canceled"
                android.os.Bundle r3 = r8.a(r3, r5, r9)     // Catch:{ Exception -> 0x0063 }
                goto L_0x006b
            L_0x0063:
                int r2 = com.mipay.ucashier.ui.BaseUCashierFragment.RESULT_ERROR
                java.lang.String r3 = "exception"
                android.os.Bundle r3 = r8.a(r1, r3, r9)
            L_0x006b:
                r0.setResult(r2, r3)
                int r9 = com.mipay.ucashier.ui.BaseUCashierFragment.RESULT_CANCELED
                if (r2 != r9) goto L_0x0076
                r0.finish()
                goto L_0x007c
            L_0x0076:
                java.lang.String r9 = "trade"
                r0.finish(r9, r4)
            L_0x007c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mipay.ucashier.pay.alipay.AlipayEntry.a.handleMessage(android.os.Message):void");
        }
    }

    private static class b {

        /* renamed from: a  reason: collision with root package name */
        private String f8183a;

        public b(String str) {
            this.f8183a = str;
        }

        private String a(String str, String str2, String str3) {
            String str4;
            int indexOf = str.indexOf(str2) + str2.length();
            if (str3 != null) {
                try {
                    str4 = str.substring(indexOf, str.indexOf(str3));
                } catch (Exception e) {
                    e.printStackTrace();
                    return str;
                }
            } else {
                str4 = str.substring(indexOf);
            }
            return str4;
        }

        private JSONObject a(String str, String str2) {
            JSONObject jSONObject = new JSONObject();
            try {
                String[] split = str.split(str2);
                for (int i = 0; i < split.length; i++) {
                    String[] split2 = split[i].split("=");
                    jSONObject.put(split2[0], split[i].substring(split2[0].length() + 1));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jSONObject;
        }

        public String a() {
            return a(this.f8183a, "resultStatus=", ";memo=");
        }

        public String b() {
            String str = null;
            try {
                String string = a(this.f8183a, i.b).getString("result");
                String string2 = a(string.substring(1, string.length() - 1), com.alipay.sdk.sys.a.b).getString("success");
                try {
                    return string2.replace("\"", "");
                } catch (Exception e) {
                    String str2 = string2;
                    e = e;
                    str = str2;
                    e.printStackTrace();
                    return str;
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return str;
            }
        }
    }

    public void handleActivityResult(int i, int i2, Intent intent) {
    }

    public void pay(l lVar, String str) {
        a aVar = new a(this, (a) null);
        this.b = new SoftReference<>(lVar);
        new a(this, lVar, str, aVar).start();
    }
}
