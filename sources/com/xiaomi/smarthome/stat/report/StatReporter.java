package com.xiaomi.smarthome.stat.report;

import com.xiaomi.smarthome.frame.AppUsrExpPlanUtil;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import org.json.JSONObject;

public class StatReporter {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22772a = "mihome";
    public static final String b = "app";
    public static final String c = "plugin";
    public static final String d = "click";
    public static final String e = "result";
    private String f;

    public static final boolean a(String str, String str2) {
        if ("app".equals(str) || "plugin".equals(str)) {
            return true;
        }
        if (AppUsrExpPlanUtil.a(FrameManager.f()) && !ServerCompact.g(FrameManager.f())) {
            return true;
        }
        return false;
    }

    public static final String a(Object obj) {
        if (obj == null) {
            return "";
        }
        String name = obj.getClass().getName();
        String packageName = FrameManager.f().getPackageName();
        return name.startsWith(packageName) ? name.replace(packageName, "") : name;
    }

    public StatReporter(String str) {
        this.f = str;
    }

    public static JSONObject a(Object... objArr) {
        String obj;
        JSONObject jSONObject = new JSONObject();
        for (int i = 1; i < objArr.length; i += 2) {
            Object obj2 = objArr[i - 1];
            if (!(obj2 == null || (obj = obj2.toString()) == null)) {
                String str = objArr[i];
                if (str == null) {
                    str = "";
                }
                a(jSONObject, obj, (Object) str);
            }
        }
        return jSONObject;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:0|1|2|3|5) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0004 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(org.json.JSONObject r0, java.lang.String r1, java.lang.Object r2) {
        /*
            r0.put(r1, r2)     // Catch:{ Exception -> 0x0004 }
            goto L_0x000b
        L_0x0004:
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x000b }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x000b }
        L_0x000b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.stat.report.StatReporter.a(org.json.JSONObject, java.lang.String, java.lang.Object):void");
    }

    public long a(String str, Object... objArr) {
        return StatLogSender.b().a(this.f, "mihome", str, a(objArr), "", true);
    }

    public long a(String str, String str2, Object... objArr) {
        StatLogSender b2 = StatLogSender.b();
        String str3 = this.f;
        if (str2 == null) {
            str2 = "mihome";
        }
        return b2.a(str3, str2, str, a(objArr), "", true);
    }

    public long a(String str, String str2, JSONObject jSONObject, String str3) {
        StatLogSender b2 = StatLogSender.b();
        String str4 = this.f;
        if (str2 == null) {
            str2 = "mihome";
        }
        return b2.a(str4, str2, str, jSONObject, str3, true);
    }

    public long b(String str, String str2, Object... objArr) {
        return StatLogSender.b().a(this.f, "mihome", str, a(objArr), str2, true);
    }
}
