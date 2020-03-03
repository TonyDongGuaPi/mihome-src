package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.support.api.push.PushReceiver;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ai;
import com.xiaomi.push.az;
import com.xiaomi.push.r;
import com.xiaomi.push.service.ah;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class h {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<String, String> f11553a = new HashMap<>();

    public static MiPushMessage a(String str) {
        MiPushMessage miPushMessage = new MiPushMessage();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("messageId")) {
                    miPushMessage.setMessageId(jSONObject.getString("messageId"));
                }
                if (jSONObject.has("description")) {
                    miPushMessage.setDescription(jSONObject.getString("description"));
                }
                if (jSONObject.has("title")) {
                    miPushMessage.setTitle(jSONObject.getString("title"));
                }
                if (jSONObject.has("content")) {
                    miPushMessage.setContent(jSONObject.getString("content"));
                }
                if (jSONObject.has("passThrough")) {
                    miPushMessage.setPassThrough(jSONObject.getInt("passThrough"));
                }
                if (jSONObject.has("notifyType")) {
                    miPushMessage.setNotifyType(jSONObject.getInt("notifyType"));
                }
                if (jSONObject.has("messageType")) {
                    miPushMessage.setMessageType(jSONObject.getInt("messageType"));
                }
                if (jSONObject.has("alias")) {
                    miPushMessage.setAlias(jSONObject.getString("alias"));
                }
                if (jSONObject.has(UrlConstants.topic)) {
                    miPushMessage.setTopic(jSONObject.getString(UrlConstants.topic));
                }
                if (jSONObject.has("user_account")) {
                    miPushMessage.setUserAccount(jSONObject.getString("user_account"));
                }
                if (jSONObject.has("notifyId")) {
                    miPushMessage.setNotifyId(jSONObject.getInt("notifyId"));
                }
                if (jSONObject.has("category")) {
                    miPushMessage.setCategory(jSONObject.getString("category"));
                }
                if (jSONObject.has("isNotified")) {
                    miPushMessage.setNotified(jSONObject.getBoolean("isNotified"));
                }
                if (jSONObject.has("extra")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("extra");
                    Iterator<String> keys = jSONObject2.keys();
                    HashMap hashMap = new HashMap();
                    while (keys != null && keys.hasNext()) {
                        String next = keys.next();
                        hashMap.put(next, jSONObject2.getString(next));
                    }
                    if (hashMap.size() > 0) {
                        miPushMessage.setExtra(hashMap);
                    }
                }
            } catch (Exception e) {
                b.d(e.toString());
            }
        }
        return miPushMessage;
    }

    protected static synchronized String a(Context context, String str) {
        String str2;
        synchronized (h.class) {
            str2 = f11553a.get(str);
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
        }
        return str2;
    }

    public static String a(d dVar) {
        switch (j.f11555a[dVar.ordinal()]) {
            case 1:
                return "hms_push_token";
            case 2:
                return "fcm_push_token";
            case 3:
                return "cos_push_token";
            case 4:
                return "ftos_push_token";
            default:
                return null;
        }
    }

    static void a(Context context) {
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        String a2 = a(d.ASSEMBLE_PUSH_HUAWEI);
        String a3 = a(d.ASSEMBLE_PUSH_FCM);
        if (!TextUtils.isEmpty(sharedPreferences.getString(a2, "")) && TextUtils.isEmpty(sharedPreferences.getString(a3, ""))) {
            z = true;
        }
        if (z) {
            aw.a(context).a(2, a2);
        }
    }

    public static void a(Context context, d dVar) {
        String a2 = a(dVar);
        if (!TextUtils.isEmpty(a2)) {
            r.a(context.getSharedPreferences("mipush_extra", 0).edit().putString(a2, ""));
        }
    }

    public static void a(Context context, d dVar, String str) {
        if (!TextUtils.isEmpty(str)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
            String a2 = a(dVar);
            if (TextUtils.isEmpty(a2)) {
                b.a("ASSEMBLE_PUSH : can not find the key of token used in sp file");
                return;
            }
            String string = sharedPreferences.getString(a2, "");
            if (TextUtils.isEmpty(string) || !str.equals(string)) {
                b.a("ASSEMBLE_PUSH : send token upload");
                a(dVar, str);
                bb c = k.c(dVar);
                if (c != null) {
                    aw.a(context).a((String) null, c, dVar);
                    return;
                }
                return;
            }
            b.a("ASSEMBLE_PUSH : do not need to send token");
        }
    }

    public static void a(Intent intent) {
        Bundle extras;
        if (intent != null && (extras = intent.getExtras()) != null && extras.containsKey(PushReceiver.BOUND_KEY.pushMsgKey)) {
            intent.putExtra(PushMessageHelper.j, a(extras.getString(PushReceiver.BOUND_KEY.pushMsgKey)));
        }
    }

    private static synchronized void a(d dVar, String str) {
        synchronized (h.class) {
            String a2 = a(dVar);
            if (TextUtils.isEmpty(a2)) {
                b.a("ASSEMBLE_PUSH : can not find the key of token used in sp file");
            } else if (TextUtils.isEmpty(str)) {
                b.a("ASSEMBLE_PUSH : token is null");
            } else {
                f11553a.put(a2, str);
            }
        }
    }

    public static void a(String str, int i) {
        MiTinyDataClient.a("hms_push_error", str, 1, "error code = " + i);
    }

    public static String b(d dVar) {
        switch (j.f11555a[dVar.ordinal()]) {
            case 1:
                return "hms_push_error";
            case 2:
                return "fcm_push_error";
            case 3:
                return "cos_push_error";
            case 4:
                return "ftos_push_error";
            default:
                return null;
        }
    }

    public static void b(Context context, d dVar, String str) {
        ai.a(context).a((Runnable) new i(str, context, dVar));
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        return az.c(context);
    }

    public static boolean b(Context context, d dVar) {
        if (k.b(dVar) != null) {
            return ah.a(context).a(k.b(dVar).a(), true);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041 A[Catch:{ Exception -> 0x0051 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0050 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.xiaomi.mipush.sdk.PushMessageReceiver c(android.content.Context r5) {
        /*
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.xiaomi.mipush.RECEIVE_MESSAGE"
            r0.<init>(r1)
            java.lang.String r1 = r5.getPackageName()
            r0.setPackage(r1)
            android.content.pm.PackageManager r1 = r5.getPackageManager()
            r2 = 32
            r3 = 0
            java.util.List r0 = r1.queryBroadcastReceivers(r0, r2)     // Catch:{ Exception -> 0x0051 }
            if (r0 == 0) goto L_0x003e
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x0051 }
        L_0x001f:
            boolean r1 = r0.hasNext()     // Catch:{ Exception -> 0x0051 }
            if (r1 == 0) goto L_0x003e
            java.lang.Object r1 = r0.next()     // Catch:{ Exception -> 0x0051 }
            android.content.pm.ResolveInfo r1 = (android.content.pm.ResolveInfo) r1     // Catch:{ Exception -> 0x0051 }
            android.content.pm.ActivityInfo r2 = r1.activityInfo     // Catch:{ Exception -> 0x0051 }
            if (r2 == 0) goto L_0x001f
            android.content.pm.ActivityInfo r2 = r1.activityInfo     // Catch:{ Exception -> 0x0051 }
            java.lang.String r2 = r2.packageName     // Catch:{ Exception -> 0x0051 }
            java.lang.String r4 = r5.getPackageName()     // Catch:{ Exception -> 0x0051 }
            boolean r2 = r2.equals(r4)     // Catch:{ Exception -> 0x0051 }
            if (r2 == 0) goto L_0x001f
            goto L_0x003f
        L_0x003e:
            r1 = r3
        L_0x003f:
            if (r1 == 0) goto L_0x0050
            android.content.pm.ActivityInfo r0 = r1.activityInfo     // Catch:{ Exception -> 0x0051 }
            java.lang.String r0 = r0.name     // Catch:{ Exception -> 0x0051 }
            java.lang.Class r5 = com.xiaomi.push.t.a(r5, r0)     // Catch:{ Exception -> 0x0051 }
            java.lang.Object r5 = r5.newInstance()     // Catch:{ Exception -> 0x0051 }
            com.xiaomi.mipush.sdk.PushMessageReceiver r5 = (com.xiaomi.mipush.sdk.PushMessageReceiver) r5     // Catch:{ Exception -> 0x0051 }
            return r5
        L_0x0050:
            return r3
        L_0x0051:
            r5 = move-exception
            java.lang.String r5 = r5.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.h.c(android.content.Context):com.xiaomi.mipush.sdk.PushMessageReceiver");
    }

    public static HashMap<String, String> c(Context context, d dVar) {
        ApplicationInfo applicationInfo;
        StringBuilder sb;
        an anVar;
        HashMap<String, String> hashMap = new HashMap<>();
        String a2 = a(dVar);
        if (TextUtils.isEmpty(a2)) {
            return hashMap;
        }
        String str = null;
        switch (j.f11555a[dVar.ordinal()]) {
            case 1:
                try {
                    applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                } catch (Exception e) {
                    b.d(e.toString());
                    applicationInfo = null;
                }
                int i = -1;
                if (applicationInfo != null) {
                    i = applicationInfo.metaData.getInt(Constants.K);
                }
                sb = new StringBuilder();
                sb.append("brand:");
                sb.append(m.a(context).name());
                sb.append(Constants.J);
                sb.append("token");
                sb.append(":");
                sb.append(a(context, a2));
                sb.append(Constants.J);
                sb.append("package_name");
                sb.append(":");
                sb.append(context.getPackageName());
                sb.append(Constants.J);
                sb.append("app_id");
                sb.append(":");
                sb.append(i);
                break;
            case 2:
                sb = new StringBuilder();
                sb.append("brand:");
                anVar = an.c;
                break;
            case 3:
                sb = new StringBuilder();
                sb.append("brand:");
                anVar = an.OPPO;
                break;
            case 4:
                sb = new StringBuilder();
                sb.append("brand:");
                anVar = an.VIVO;
                break;
            default:
                hashMap.put(Constants.D, str);
                return hashMap;
        }
        sb.append(anVar.name());
        sb.append(Constants.J);
        sb.append("token");
        sb.append(":");
        sb.append(a(context, a2));
        sb.append(Constants.J);
        sb.append("package_name");
        sb.append(":");
        sb.append(context.getPackageName());
        str = sb.toString();
        hashMap.put(Constants.D, str);
        return hashMap;
    }

    public static void d(Context context) {
        e.a(context).a();
    }

    /* access modifiers changed from: private */
    public static synchronized void d(Context context, d dVar, String str) {
        synchronized (h.class) {
            String a2 = a(dVar);
            if (TextUtils.isEmpty(a2)) {
                b.a("ASSEMBLE_PUSH : can not find the key of token used in sp file");
                return;
            }
            r.a(context.getSharedPreferences("mipush_extra", 0).edit().putString(a2, str));
            b.a("ASSEMBLE_PUSH : update sp file success!  " + str);
        }
    }

    public static void e(Context context) {
        e.a(context).b();
    }
}
