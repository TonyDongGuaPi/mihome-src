package com.hianalytics.android.v1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.alipay.sdk.util.i;
import com.hianalytics.android.b.a.a;
import com.hianalytics.android.b.a.c;
import com.mi.global.bbs.utils.Constants;
import com.xiaomi.stat.a.j;
import kotlin.text.Typography;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private Context f5801a;
    private int b;
    private long c;

    public d(Context context, int i, long j) {
        this.f5801a = context;
        this.b = i;
        this.c = j;
    }

    private void a(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong("last_millis", this.c);
        edit.commit();
    }

    private static void a(SharedPreferences sharedPreferences, long j) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String valueOf = String.valueOf(j);
        edit.remove("session_id");
        edit.remove("refer_id");
        edit.putString("session_id", valueOf);
        edit.putString("refer_id", "");
        edit.putLong("end_millis", j);
        edit.commit();
    }

    private void b(SharedPreferences sharedPreferences) {
        String str;
        boolean z;
        JSONObject jSONObject = new JSONObject();
        Context context = this.f5801a;
        StringBuffer stringBuffer = new StringBuffer("");
        SharedPreferences a2 = c.a(context, "sessioncontext");
        String string = a2.getString("session_id", "");
        if ("".equals(string)) {
            long currentTimeMillis = System.currentTimeMillis();
            String valueOf = String.valueOf(currentTimeMillis);
            SharedPreferences.Editor edit = a2.edit();
            edit.putString("session_id", valueOf);
            edit.putLong("end_millis", currentTimeMillis);
            edit.commit();
            string = valueOf;
        }
        String string2 = a2.getString("refer_id", "");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            a.h();
            str = null;
        } else {
            stringBuffer.append(a.c(context)[0]);
            stringBuffer.append(",");
            stringBuffer.append(telephonyManager.getNetworkOperatorName().replace(',', Typography.c));
            stringBuffer.append(",");
            stringBuffer.append(string);
            stringBuffer.append(",");
            stringBuffer.append(string2);
            str = stringBuffer.toString();
        }
        if (str != null) {
            try {
                if (sharedPreferences.getString(Constants.PageFragment.PAGE_ACTIVITIES, "").trim().length() > 0) {
                    String[] split = sharedPreferences.getString(Constants.PageFragment.PAGE_ACTIVITIES, "").split(i.b);
                    JSONArray jSONArray = new JSONArray();
                    for (String put : split) {
                        jSONArray.put(put);
                    }
                    jSONObject.put("b", jSONArray);
                    z = false;
                } else {
                    z = true;
                }
                if (sharedPreferences.getString(j.b, "").trim().length() > 0) {
                    String[] split2 = sharedPreferences.getString(j.b, "").split(i.b);
                    JSONArray jSONArray2 = new JSONArray();
                    for (String put2 : split2) {
                        jSONArray2.put(put2);
                    }
                    jSONObject.put("e", jSONArray2);
                    z = false;
                }
                jSONObject.put("h", str);
                jSONObject.put("type", "termination");
                Handler f = a.f();
                if (f != null) {
                    f.post(new c(this.f5801a, jSONObject, z));
                }
                a.h();
            } catch (JSONException e) {
                Log.e("HiAnalytics", "onTerminate: JSONException.", e);
                e.printStackTrace();
            }
        }
        SharedPreferences.Editor edit2 = sharedPreferences.edit();
        edit2.putString(Constants.PageFragment.PAGE_ACTIVITIES, "");
        edit2.remove(j.b);
        edit2.commit();
    }

    private boolean c(SharedPreferences sharedPreferences) {
        return this.c - sharedPreferences.getLong("last_millis", -1) > a.a().longValue() * 1000;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x004a A[Catch:{ Exception -> 0x013b }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x010a A[Catch:{ Exception -> 0x013b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r15 = this;
            android.content.Context r0 = r15.f5801a     // Catch:{ Exception -> 0x013b }
            long r1 = r15.c     // Catch:{ Exception -> 0x013b }
            java.lang.String r3 = "sessioncontext"
            android.content.SharedPreferences r0 = com.hianalytics.android.b.a.c.a(r0, r3)     // Catch:{ Exception -> 0x013b }
            java.lang.String r3 = "session_id"
            java.lang.String r4 = ""
            java.lang.String r3 = r0.getString(r3, r4)     // Catch:{ Exception -> 0x013b }
            java.lang.String r4 = ""
            boolean r3 = r4.equals(r3)     // Catch:{ Exception -> 0x013b }
            r4 = 1000(0x3e8, double:4.94E-321)
            r6 = 0
            if (r3 == 0) goto L_0x0022
        L_0x001e:
            a(r0, r1)     // Catch:{ Exception -> 0x013b }
            goto L_0x0046
        L_0x0022:
            java.lang.String r3 = "end_millis"
            long r8 = r0.getLong(r3, r6)     // Catch:{ Exception -> 0x013b }
            r3 = 0
            long r8 = r1 - r8
            java.lang.Long r3 = com.hianalytics.android.b.a.a.c()     // Catch:{ Exception -> 0x013b }
            long r10 = r3.longValue()     // Catch:{ Exception -> 0x013b }
            long r10 = r10 * r4
            int r3 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r3 <= 0) goto L_0x003a
            goto L_0x001e
        L_0x003a:
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Exception -> 0x013b }
            java.lang.String r3 = "end_millis"
            r0.putLong(r3, r1)     // Catch:{ Exception -> 0x013b }
            r0.commit()     // Catch:{ Exception -> 0x013b }
        L_0x0046:
            int r0 = r15.b     // Catch:{ Exception -> 0x013b }
            if (r0 != 0) goto L_0x010a
            android.content.Context r0 = r15.f5801a     // Catch:{ Exception -> 0x013b }
            android.content.Context r1 = r15.f5801a     // Catch:{ Exception -> 0x013b }
            if (r1 == r0) goto L_0x0054
            com.hianalytics.android.b.a.a.h()     // Catch:{ Exception -> 0x013b }
            return
        L_0x0054:
            r15.f5801a = r0     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "state"
            android.content.SharedPreferences r1 = com.hianalytics.android.b.a.c.a(r0, r1)     // Catch:{ Exception -> 0x013b }
            if (r1 == 0) goto L_0x0109
            java.lang.String r2 = "last_millis"
            r8 = -1
            long r2 = r1.getLong(r2, r8)     // Catch:{ Exception -> 0x013b }
            int r10 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x006f
            com.hianalytics.android.b.a.a.h()     // Catch:{ Exception -> 0x013b }
            goto L_0x00f0
        L_0x006f:
            long r8 = r15.c     // Catch:{ Exception -> 0x013b }
            r10 = 0
            long r8 = r8 - r2
            java.lang.String r10 = "duration"
            long r6 = r1.getLong(r10, r6)     // Catch:{ Exception -> 0x013b }
            android.content.SharedPreferences$Editor r10 = r1.edit()     // Catch:{ Exception -> 0x013b }
            java.lang.String r11 = "activities"
            java.lang.String r12 = ""
            java.lang.String r11 = r1.getString(r11, r12)     // Catch:{ Exception -> 0x013b }
            java.lang.Class r12 = r0.getClass()     // Catch:{ Exception -> 0x013b }
            java.lang.String r12 = r12.getName()     // Catch:{ Exception -> 0x013b }
            java.lang.String r13 = ""
            boolean r13 = r13.equals(r11)     // Catch:{ Exception -> 0x013b }
            if (r13 != 0) goto L_0x00a7
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013b }
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x013b }
            r13.<init>(r11)     // Catch:{ Exception -> 0x013b }
            java.lang.String r11 = ";"
            r13.append(r11)     // Catch:{ Exception -> 0x013b }
            java.lang.String r11 = r13.toString()     // Catch:{ Exception -> 0x013b }
        L_0x00a7:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013b }
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x013b }
            r13.<init>(r11)     // Catch:{ Exception -> 0x013b }
            r13.append(r12)     // Catch:{ Exception -> 0x013b }
            java.lang.String r11 = ","
            r13.append(r11)     // Catch:{ Exception -> 0x013b }
            java.text.SimpleDateFormat r11 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x013b }
            java.lang.String r12 = "yyyyMMddHHmmssSSS"
            java.util.Locale r14 = java.util.Locale.US     // Catch:{ Exception -> 0x013b }
            r11.<init>(r12, r14)     // Catch:{ Exception -> 0x013b }
            java.util.Date r12 = new java.util.Date     // Catch:{ Exception -> 0x013b }
            r12.<init>(r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r2 = r11.format(r12)     // Catch:{ Exception -> 0x013b }
            r13.append(r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r2 = ","
            r13.append(r2)     // Catch:{ Exception -> 0x013b }
            long r2 = r8 / r4
            r13.append(r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r2 = r13.toString()     // Catch:{ Exception -> 0x013b }
            java.lang.String r3 = "activities"
            r10.remove(r3)     // Catch:{ Exception -> 0x013b }
            java.lang.String r3 = "activities"
            r10.putString(r3, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r2 = "duration"
            r3 = 0
            long r6 = r6 + r8
            r10.putLong(r2, r6)     // Catch:{ Exception -> 0x013b }
            r10.commit()     // Catch:{ Exception -> 0x013b }
        L_0x00f0:
            boolean r2 = r15.c(r1)     // Catch:{ Exception -> 0x013b }
            if (r2 == 0) goto L_0x00fd
            r15.b(r1)     // Catch:{ Exception -> 0x013b }
            r15.a(r1)     // Catch:{ Exception -> 0x013b }
            return
        L_0x00fd:
            boolean r0 = com.hianalytics.android.b.a.a.d((android.content.Context) r0)     // Catch:{ Exception -> 0x013b }
            if (r0 == 0) goto L_0x0109
            r15.b(r1)     // Catch:{ Exception -> 0x013b }
            r15.a(r1)     // Catch:{ Exception -> 0x013b }
        L_0x0109:
            return
        L_0x010a:
            int r0 = r15.b     // Catch:{ Exception -> 0x013b }
            r1 = 1
            if (r0 != r1) goto L_0x0128
            android.content.Context r0 = r15.f5801a     // Catch:{ Exception -> 0x013b }
            r15.f5801a = r0     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "state"
            android.content.SharedPreferences r0 = com.hianalytics.android.b.a.c.a(r0, r1)     // Catch:{ Exception -> 0x013b }
            if (r0 == 0) goto L_0x0127
            boolean r1 = r15.c(r0)     // Catch:{ Exception -> 0x013b }
            if (r1 == 0) goto L_0x0127
            r15.b(r0)     // Catch:{ Exception -> 0x013b }
            r15.a(r0)     // Catch:{ Exception -> 0x013b }
        L_0x0127:
            return
        L_0x0128:
            int r0 = r15.b     // Catch:{ Exception -> 0x013b }
            r1 = 2
            if (r0 != r1) goto L_0x0145
            android.content.Context r0 = r15.f5801a     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "state"
            android.content.SharedPreferences r0 = com.hianalytics.android.b.a.c.a(r0, r1)     // Catch:{ Exception -> 0x013b }
            if (r0 == 0) goto L_0x013a
            r15.b(r0)     // Catch:{ Exception -> 0x013b }
        L_0x013a:
            return
        L_0x013b:
            r0 = move-exception
            r0.getMessage()
            com.hianalytics.android.b.a.a.h()
            r0.printStackTrace()
        L_0x0145:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hianalytics.android.v1.d.run():void");
    }
}
