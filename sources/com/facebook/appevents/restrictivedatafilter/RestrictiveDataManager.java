package com.facebook.appevents.restrictivedatafilter;

import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Log;
import com.facebook.appevents.AppEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class RestrictiveDataManager {
    private static final String TAG = RestrictiveDataManager.class.getCanonicalName();
    private static boolean enabled = false;
    private static Set<String> restrictiveEvents = new HashSet();
    private static List<RestrictiveParam> restrictiveParams = new ArrayList();

    public static synchronized void enable() {
        synchronized (RestrictiveDataManager.class) {
            enabled = true;
            initialize();
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void initialize() {
        /*
            java.lang.Class<com.facebook.appevents.restrictivedatafilter.RestrictiveDataManager> r0 = com.facebook.appevents.restrictivedatafilter.RestrictiveDataManager.class
            monitor-enter(r0)
            java.lang.String r1 = com.facebook.FacebookSdk.getApplicationId()     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r2 = 0
            com.facebook.internal.FetchedAppSettings r1 = com.facebook.internal.FetchedAppSettingsManager.queryAppSettings(r1, r2)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            if (r1 != 0) goto L_0x0010
            monitor-exit(r0)
            return
        L_0x0010:
            java.lang.String r1 = r1.getRestrictiveDataSetting()     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            if (r1 != 0) goto L_0x0018
            monitor-exit(r0)
            return
        L_0x0018:
            boolean r2 = r1.isEmpty()     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            if (r2 != 0) goto L_0x0085
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            java.util.List<com.facebook.appevents.restrictivedatafilter.RestrictiveDataManager$RestrictiveParam> r1 = restrictiveParams     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r1.clear()     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            java.util.Set<java.lang.String> r1 = restrictiveEvents     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r1.clear()     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            java.util.Iterator r1 = r2.keys()     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
        L_0x0031:
            boolean r3 = r1.hasNext()     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            if (r3 == 0) goto L_0x0085
            java.lang.Object r3 = r1.next()     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            org.json.JSONObject r4 = r2.getJSONObject(r3)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            if (r4 == 0) goto L_0x0031
            java.lang.String r5 = "is_deprecated_event"
            boolean r5 = r4.optBoolean(r5)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            if (r5 == 0) goto L_0x0051
            java.util.Set<java.lang.String> r4 = restrictiveEvents     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r4.add(r3)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            goto L_0x0031
        L_0x0051:
            java.lang.String r5 = "restrictive_param"
            org.json.JSONObject r5 = r4.optJSONObject(r5)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            java.lang.String r6 = "deprecated_param"
            org.json.JSONArray r4 = r4.optJSONArray(r6)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            com.facebook.appevents.restrictivedatafilter.RestrictiveDataManager$RestrictiveParam r6 = new com.facebook.appevents.restrictivedatafilter.RestrictiveDataManager$RestrictiveParam     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r7.<init>()     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r8.<init>()     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r6.<init>(r3, r7, r8)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            if (r5 == 0) goto L_0x0074
            java.util.Map r3 = com.facebook.internal.Utility.convertJSONObjectToStringMap(r5)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r6.restrictiveParams = r3     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
        L_0x0074:
            if (r4 == 0) goto L_0x007c
            java.util.List r3 = com.facebook.internal.Utility.convertJSONArrayToList(r4)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r6.deprecatedParams = r3     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
        L_0x007c:
            java.util.List<com.facebook.appevents.restrictivedatafilter.RestrictiveDataManager$RestrictiveParam> r3 = restrictiveParams     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            r3.add(r6)     // Catch:{ Exception -> 0x0085, all -> 0x0082 }
            goto L_0x0031
        L_0x0082:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x0085:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.restrictivedatafilter.RestrictiveDataManager.initialize():void");
    }

    public static void processEvents(List<AppEvent> list) {
        if (enabled) {
            Iterator<AppEvent> it = list.iterator();
            while (it.hasNext()) {
                if (isDeprecatedEvent(it.next().getName())) {
                    it.remove();
                }
            }
        }
    }

    public static void processParameters(Map<String, String> map, String str) {
        if (enabled) {
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList(map.keySet());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                String matchedRuleType = getMatchedRuleType(str, str2);
                if (matchedRuleType != null) {
                    hashMap.put(str2, matchedRuleType);
                    map.remove(str2);
                }
            }
            for (RestrictiveParam next : restrictiveParams) {
                if (next.eventName.equals(str)) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        String str3 = (String) it2.next();
                        if (next.deprecatedParams.contains(str3)) {
                            map.remove(str3);
                        }
                    }
                }
            }
            if (hashMap.size() > 0) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    for (Map.Entry entry : hashMap.entrySet()) {
                        jSONObject.put((String) entry.getKey(), entry.getValue());
                    }
                    map.put("_restrictedParams", jSONObject.toString());
                } catch (JSONException unused) {
                }
            }
        }
    }

    private static boolean isDeprecatedEvent(String str) {
        return restrictiveEvents.contains(str);
    }

    @Nullable
    private static String getMatchedRuleType(String str, String str2) {
        try {
            Iterator it = new ArrayList(restrictiveParams).iterator();
            while (it.hasNext()) {
                RestrictiveParam restrictiveParam = (RestrictiveParam) it.next();
                if (restrictiveParam != null) {
                    if (str.equals(restrictiveParam.eventName)) {
                        for (String next : restrictiveParam.restrictiveParams.keySet()) {
                            if (str2.equals(next)) {
                                return restrictiveParam.restrictiveParams.get(next);
                            }
                        }
                        continue;
                    } else {
                        continue;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            Log.w(TAG, "getMatchedRuleType failed", e);
            return null;
        }
    }

    static class RestrictiveParam {
        List<String> deprecatedParams;
        String eventName;
        Map<String, String> restrictiveParams;

        RestrictiveParam(String str, Map<String, String> map, List<String> list) {
            this.eventName = str;
            this.restrictiveParams = map;
            this.deprecatedParams = list;
        }
    }
}
