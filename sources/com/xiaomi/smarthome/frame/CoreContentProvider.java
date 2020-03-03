package com.xiaomi.smarthome.frame;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class CoreContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.xiaomi.smarthome.core_auth";
    public static final String METHOD_KEY_DID = "device_id";
    public static final String METHOD_KEY_IS_ENABLED = "enabled";
    public static final String METHOD_KEY_isDeviceUsrExpPlanEnabled = "isDeviceUsrExpPlanEnabled";
    public static final String METHOD_KEY_isUsrExpPlanEnabled = "isUsrExpPlanEnabled";
    public static final String METHOD_KEY_setDeviceUsrExpPlanEnabled = "setDeviceUsrExpPlanEnabled";
    public static final String METHOD_KEY_setUsrExpPlanEnabled = "setUsrExpPlanEnabled";

    /* renamed from: a  reason: collision with root package name */
    private static final String f15990a = "CoreContentProvider";

    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return false;
    }

    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        return null;
    }

    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @Nullable
    public Bundle call(String str, String str2, Bundle bundle) {
        if (TextUtils.equals(METHOD_KEY_isUsrExpPlanEnabled, str)) {
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("enabled", UsrExpPlanUtil.a(getContext()));
            return bundle2;
        } else if (TextUtils.equals(METHOD_KEY_setUsrExpPlanEnabled, str)) {
            if (bundle == null) {
                return null;
            }
            boolean z = bundle.getBoolean("enabled", true);
            Bundle bundle3 = new Bundle();
            bundle3.putBoolean("enabled", UsrExpPlanUtil.a(getContext(), z));
            return bundle3;
        } else if (TextUtils.equals(METHOD_KEY_isDeviceUsrExpPlanEnabled, str)) {
            if (bundle == null) {
                return null;
            }
            String string = bundle.getString("device_id", "");
            Bundle bundle4 = new Bundle();
            bundle4.putBoolean("enabled", UsrExpPlanUtil.a(getContext(), string));
            return bundle4;
        } else if (!TextUtils.equals(METHOD_KEY_setDeviceUsrExpPlanEnabled, str) || bundle == null) {
            return null;
        } else {
            boolean z2 = bundle.getBoolean("enabled", true);
            UsrExpPlanUtil.a(getContext(), bundle.getString("device_id", ""), z2);
            return null;
        }
    }

    private static class UsrExpPlanUtil {

        /* renamed from: a  reason: collision with root package name */
        public static final String f15991a = "com.xiaomi.smarthome.usr_exp_plan_change";
        public static final String b = "usr_exp_plan_value";
        public static final int c = -1;
        public static final int d = 1;
        public static final int e = 0;
        /* access modifiers changed from: private */
        public static volatile int f = -1;
        private static Map<String, Boolean> g = new ConcurrentHashMap();

        private UsrExpPlanUtil() {
        }

        public static boolean a(Context context) {
            if (f == -1) {
                f = UsrExpSPStore.b(context);
            }
            return f == 1;
        }

        public static boolean a(Context context, boolean z) {
            if (!UsrExpSPStore.a(context, z ? 1 : 0)) {
                return false;
            }
            c(context);
            return true;
        }

        public static boolean a(Context context, String str) {
            Boolean bool;
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            b(context);
            Map<String, Boolean> map = g;
            if (map.containsKey(str) && (bool = map.get(str)) != null && (bool instanceof Boolean)) {
                return bool.booleanValue();
            }
            return true;
        }

        public static void a(Context context, String str, boolean z) {
            if (!TextUtils.isEmpty(str)) {
                b(context);
                Map<String, Boolean> map = g;
                map.put(str, Boolean.valueOf(z));
                try {
                    UsrExpSPStore.a(context, a(map));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        private static JSONObject a(Map<String, Boolean> map) throws JSONException {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            if (map == null || map.isEmpty()) {
                return jSONObject;
            }
            for (Map.Entry next : map.entrySet()) {
                if (!(next == null || next.getValue() == null)) {
                    String str = (String) next.getKey();
                    if (!TextUtils.isEmpty(str)) {
                        jSONObject2.put(str, next.getValue());
                    }
                }
            }
            jSONObject.put("data", jSONObject2);
            return jSONObject;
        }

        private static void b(Context context) {
            JSONObject optJSONObject;
            if (g.isEmpty()) {
                String a2 = UsrExpSPStore.a(context);
                if (!TextUtils.isEmpty(a2)) {
                    try {
                        JSONObject jSONObject = new JSONObject(a2);
                        if (!jSONObject.isNull("data") && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
                            Iterator<String> keys = optJSONObject.keys();
                            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                if (!TextUtils.isEmpty(next)) {
                                    concurrentHashMap.put(next, Boolean.valueOf(optJSONObject.optBoolean(next, true)));
                                } else {
                                    return;
                                }
                            }
                            g = concurrentHashMap;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }

        private static void c(Context context) {
            Intent intent = new Intent("com.xiaomi.smarthome.usr_exp_plan_change");
            intent.putExtra("usr_exp_plan_value", f);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            b();
        }

        private static void b() {
            if (f == 0) {
                CrashReport.enableBugly(false);
            } else {
                CrashReport.enableBugly(true);
            }
        }

        private static class UsrExpSPStore {

            /* renamed from: a  reason: collision with root package name */
            private static final String f15992a = "usr_exp_plan";
            private static final String b = "enabled";
            private static final String c = "device_uep";

            private UsrExpSPStore() {
            }

            public static void a(Context context, JSONObject jSONObject) {
                String str;
                if (jSONObject != null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(f15992a, 0);
                    ServerBean F = CoreApi.a().F();
                    if (F == null) {
                        str = "";
                    } else {
                        str = F.f1530a;
                    }
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("device_uep_" + str, jSONObject.toString()).apply();
                }
            }

            public static String a(Context context) {
                String str;
                SharedPreferences sharedPreferences = context.getSharedPreferences(f15992a, 0);
                ServerBean F = CoreApi.a().F();
                if (F == null) {
                    str = "";
                } else {
                    str = F.f1530a;
                }
                String string = sharedPreferences.getString("device_uep_" + str, "");
                return TextUtils.isEmpty(string) ? sharedPreferences.getString(c, "") : string;
            }

            public static int b(Context context) {
                return context.getSharedPreferences(f15992a, 0).getInt("enabled", 0);
            }

            public static boolean a(Context context, int i) {
                if (UsrExpPlanUtil.f == i || (i != 1 && i != 0)) {
                    return false;
                }
                context.getSharedPreferences(f15992a, 0).edit().putInt("enabled", i).commit();
                int unused = UsrExpPlanUtil.f = i;
                return true;
            }
        }
    }
}
