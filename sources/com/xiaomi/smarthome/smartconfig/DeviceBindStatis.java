package com.xiaomi.smarthome.smartconfig;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceBindStatis {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22270a = "DeviceBindStatis";
    private static final String b = "devicebind_statis_";
    private static final String c = "_start";
    private static final String d = "_end";
    private static final String e = "_ssid";
    private static final String f = "nossid";
    private static final long g = 1800000;

    public static void a(final Context context, final String str, final String str2) {
        STAT.i.d(str, (int) (System.currentTimeMillis() / 1000));
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                JSONObject jSONObject;
                DeviceBindStatis.a(context);
                Context context = context;
                SharedPreferences sharedPreferences = context.getSharedPreferences(DeviceBindStatis.b + CoreApi.a().s(), 0);
                String string = sharedPreferences.getString(str + DeviceBindStatis.c, (String) null);
                if (string == null) {
                    jSONObject = new JSONObject();
                } else {
                    try {
                        jSONObject = new JSONObject(string);
                    } catch (JSONException e) {
                        LogUtil.b(DeviceBindStatis.f22270a, Log.getStackTraceString(e));
                        jSONObject = new JSONObject();
                    }
                }
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        long optLong = jSONObject.optLong(str2);
                        if (optLong <= 0) {
                            optLong = jSONObject.optLong(DeviceBindStatis.f);
                            if (optLong > 0) {
                                jSONObject.remove(DeviceBindStatis.f);
                                jSONObject.put(str2, optLong);
                                SharedPreferences.Editor edit = sharedPreferences.edit();
                                edit.putString(str + DeviceBindStatis.c, jSONObject.toString()).apply();
                            }
                        }
                        if (optLong <= 0) {
                            jSONObject.put(str2, System.currentTimeMillis());
                            SharedPreferences.Editor edit2 = sharedPreferences.edit();
                            edit2.putString(str + DeviceBindStatis.c, jSONObject.toString()).apply();
                        }
                    } else if (jSONObject.optLong(DeviceBindStatis.f) <= 0) {
                        jSONObject.put(DeviceBindStatis.f, System.currentTimeMillis());
                        SharedPreferences.Editor edit3 = sharedPreferences.edit();
                        edit3.putString(str + DeviceBindStatis.c, jSONObject.toString()).apply();
                    }
                } catch (JSONException e2) {
                    LogUtil.b(DeviceBindStatis.f22270a, Log.getStackTraceString(e2));
                }
            }
        });
    }

    private static void c(final Context context, final String str, final String str2) {
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                JSONObject jSONObject;
                Context context = context;
                SharedPreferences sharedPreferences = context.getSharedPreferences(DeviceBindStatis.b + CoreApi.a().s(), 0);
                String string = sharedPreferences.getString(str + DeviceBindStatis.d, (String) null);
                if (string == null) {
                    jSONObject = new JSONObject();
                } else {
                    try {
                        jSONObject = new JSONObject(string);
                    } catch (JSONException e) {
                        LogUtil.b(DeviceBindStatis.f22270a, Log.getStackTraceString(e));
                        jSONObject = new JSONObject();
                    }
                }
                try {
                    jSONObject.put(TextUtils.isEmpty(str2) ? DeviceBindStatis.f : str2, System.currentTimeMillis());
                } catch (JSONException e2) {
                    LogUtil.b(DeviceBindStatis.f22270a, Log.getStackTraceString(e2));
                }
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(str + DeviceBindStatis.d, jSONObject.toString()).apply();
            }
        });
    }

    public static void b(Context context, String str, String str2) {
        c(context, str, str2);
        a(context);
    }

    public static void a(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(b + CoreApi.a().s(), 0);
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                HashMap hashMap = new HashMap(sharedPreferences.getAll());
                SharedPreferences.Editor edit = sharedPreferences.edit();
                if (hashMap.size() != 0) {
                    HashMap hashMap2 = new HashMap();
                    HashMap hashMap3 = new HashMap();
                    for (Map.Entry entry : hashMap.entrySet()) {
                        String str = (String) entry.getKey();
                        if (str.endsWith(DeviceBindStatis.c)) {
                            try {
                                hashMap2.put(str.replace(DeviceBindStatis.c, ""), new JSONObject(entry.getValue().toString()));
                            } catch (JSONException e) {
                                LogUtil.b(DeviceBindStatis.f22270a, Log.getStackTraceString(e));
                            }
                        } else if (str.endsWith(DeviceBindStatis.d)) {
                            try {
                                hashMap3.put(str.replace(DeviceBindStatis.d, ""), new JSONObject(entry.getValue().toString()));
                                edit.remove(str);
                            } catch (JSONException e2) {
                                LogUtil.b(DeviceBindStatis.f22270a, Log.getStackTraceString(e2));
                            }
                        }
                    }
                    for (Map.Entry entry2 : hashMap3.entrySet()) {
                        String str2 = (String) entry2.getKey();
                        JSONObject jSONObject = (JSONObject) hashMap2.get(str2);
                        JSONObject jSONObject2 = (JSONObject) entry2.getValue();
                        if (jSONObject2 == null) {
                            LogUtil.b(DeviceBindStatis.f22270a, str2 + " endValue is null");
                            edit.remove(str2 + DeviceBindStatis.d);
                        } else if (jSONObject == null) {
                            LogUtil.b(DeviceBindStatis.f22270a, str2 + " startTimeJson is null");
                            edit.remove(str2 + DeviceBindStatis.c);
                        } else if (jSONObject.length() == 1 && jSONObject2.length() == 1) {
                            DeviceBindStatis.b(str2, jSONObject.optLong(jSONObject.keys().next()), jSONObject2.optLong(jSONObject2.keys().next()));
                            edit.remove(str2 + DeviceBindStatis.c);
                        } else {
                            Iterator<String> keys = jSONObject2.keys();
                            ArrayList arrayList = new ArrayList();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                DeviceBindStatis.b(str2, jSONObject.optLong(next), jSONObject2.optLong(next));
                                arrayList.add(next);
                            }
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                jSONObject.remove((String) it.next());
                            }
                            if (jSONObject.length() == 0) {
                                edit.remove(str2 + DeviceBindStatis.c);
                            }
                        }
                    }
                    for (Map.Entry entry3 : hashMap2.entrySet()) {
                        String str3 = (String) entry3.getKey();
                        JSONObject jSONObject3 = (JSONObject) entry3.getValue();
                        if (jSONObject3 == null) {
                            LogUtil.b(DeviceBindStatis.f22270a, str3 + " startValue is null");
                            edit.remove(str3 + DeviceBindStatis.c);
                        } else {
                            Iterator<String> keys2 = jSONObject3.keys();
                            ArrayList arrayList2 = new ArrayList();
                            while (keys2.hasNext()) {
                                String next2 = keys2.next();
                                if (System.currentTimeMillis() - jSONObject3.optLong(next2) > DeviceBindStatis.g) {
                                    STAT.i.b(str3, 2);
                                    LogUtil.c(DeviceBindStatis.f22270a, "adddevice_link_time_30min fail");
                                    arrayList2.add(next2);
                                }
                            }
                            Iterator it2 = arrayList2.iterator();
                            while (it2.hasNext()) {
                                jSONObject3.remove((String) it2.next());
                            }
                            if (jSONObject3.length() == 0) {
                                edit.remove(str3 + DeviceBindStatis.c);
                            }
                        }
                    }
                    edit.apply();
                    return;
                }
                LogUtil.c(DeviceBindStatis.f22270a, "no connect statis collection");
            }
        });
    }

    /* access modifiers changed from: private */
    public static void b(String str, long j, long j2) {
        if (j2 == 0 || j == 0 || j2 <= j) {
            LogUtil.b(f22270a, "startTime:" + j + " endTime:" + j2);
            return;
        }
        long j3 = j2 - j;
        if (j3 < g) {
            STAT.i.b(str, 1);
            LogUtil.c(f22270a, "adddevice_link_time_30min success");
        } else {
            STAT.i.b(str, 2);
            LogUtil.c(f22270a, "adddevice_link_time_30min timeout");
        }
        STAT.i.c(str, (int) (j3 / 1000));
    }
}
