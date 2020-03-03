package com.sina.weibo.sdk.statistic;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.sina.weibo.sdk.utils.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogBuilder {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8829a = "type";
    public static final String b = "aid";
    public static final String c = "appkey";
    public static final String d = "platform";
    public static final String e = "packagename";
    public static final String f = "key_hash";
    public static final String g = "version";
    public static final String h = "channel";
    public static final String i = "starttime";
    public static final String j = "endtime";
    public static final long k = 86400000;
    private static final String l = "page_id";
    private static final String m = "time";
    private static final String n = "duration";
    private static final String o = "event_id";
    private static final String p = "extend";
    private static final String q = "WEIBO_APPKEY";
    private static final String r = "WEIBO_CHANNEL";
    private static final int s = 500;

    private static boolean a(long j2, long j3) {
        return j2 - j3 < 86400000;
    }

    public static String a(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return null;
            }
            Object obj = applicationInfo.metaData.get(q);
            if (obj != null) {
                LogUtil.b(WBAgent.f8835a, "APPKEY: " + String.valueOf(obj));
                return String.valueOf(obj);
            }
            LogUtil.c(WBAgent.f8835a, "Could not read WEIBO_APPKEY meta-data from AndroidManifest.xml.");
            return null;
        } catch (Exception e2) {
            LogUtil.c(WBAgent.f8835a, "Could not read WEIBO_APPKEY meta-data from AndroidManifest.xml." + e2);
            return null;
        }
    }

    public static String b(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return null;
            }
            String string = applicationInfo.metaData.getString(r);
            if (string != null) {
                LogUtil.b(WBAgent.f8835a, "CHANNEL: " + string.trim());
                return string.trim();
            }
            LogUtil.c(WBAgent.f8835a, "Could not read WEIBO_CHANNEL meta-data from AndroidManifest.xml.");
            return null;
        } catch (Exception e2) {
            LogUtil.c(WBAgent.f8835a, "Could not read WEIBO_CHANNEL meta-data from AndroidManifest.xml." + e2);
            return null;
        }
    }

    public static String c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            LogUtil.b(WBAgent.f8835a, "versionName: " + packageInfo.versionName);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            LogUtil.c(WBAgent.f8835a, "Could not read versionName from AndroidManifest.xml." + e2);
            return null;
        }
    }

    public static String a(CopyOnWriteArrayList<PageLog> copyOnWriteArrayList) {
        StringBuilder sb = new StringBuilder();
        Iterator<PageLog> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            sb.append(a(it.next()).toString());
            sb.append(",");
        }
        return sb.toString();
    }

    private static JSONObject a(PageLog pageLog) {
        JSONObject jSONObject = new JSONObject();
        try {
            switch (pageLog.h()) {
                case SESSION_START:
                    jSONObject.put("type", 0);
                    jSONObject.put("time", pageLog.j() / 1000);
                    break;
                case SESSION_END:
                    jSONObject.put("type", 1);
                    jSONObject.put("time", pageLog.k() / 1000);
                    jSONObject.put("duration", pageLog.l() / 1000);
                    break;
                case FRAGMENT:
                    jSONObject.put("type", 2);
                    jSONObject.put(l, pageLog.i());
                    jSONObject.put("time", pageLog.j() / 1000);
                    jSONObject.put("duration", pageLog.l() / 1000);
                    break;
                case EVENT:
                    jSONObject.put("type", 3);
                    jSONObject.put(l, pageLog.i());
                    jSONObject.put("time", pageLog.j() / 1000);
                    a(jSONObject, (EventLog) pageLog);
                    break;
                case ACTIVITY:
                    jSONObject.put("type", 4);
                    jSONObject.put(l, pageLog.i());
                    jSONObject.put("time", pageLog.j() / 1000);
                    jSONObject.put("duration", pageLog.l() / 1000);
                    break;
                case APP_AD_START:
                    AdEventLog adEventLog = (AdEventLog) pageLog;
                    jSONObject.put("type", 0);
                    jSONObject.put(l, adEventLog.d());
                    jSONObject.put("time", adEventLog.j());
                    jSONObject.put("aid", adEventLog.a());
                    a(jSONObject, (EventLog) adEventLog);
                    break;
            }
        } catch (Exception e2) {
            LogUtil.c(WBAgent.f8835a, "get page log error." + e2);
        }
        return jSONObject;
    }

    private static JSONObject a(JSONObject jSONObject, EventLog eventLog) {
        try {
            jSONObject.put(o, eventLog.e());
            if (eventLog.g() != null) {
                Map<String, String> g2 = eventLog.g();
                StringBuilder sb = new StringBuilder();
                int i2 = 0;
                for (String next : g2.keySet()) {
                    if (i2 >= 10) {
                        break;
                    } else if (!TextUtils.isEmpty(g2.get(next))) {
                        if (sb.length() > 0) {
                            sb.append("|");
                        }
                        sb.append(next);
                        sb.append(":");
                        sb.append(g2.get(next));
                        i2++;
                    }
                }
                jSONObject.put("extend", sb.toString());
            }
        } catch (Exception e2) {
            LogUtil.c(WBAgent.f8835a, "add event log error." + e2);
        }
        return jSONObject;
    }

    public static List<JSONArray> a(String str) {
        String b2 = b(str);
        if (TextUtils.isEmpty(b2)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = new JSONArray();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONArray jSONArray2 = new JSONObject(b2).getJSONArray("applogs");
            JSONArray jSONArray3 = jSONArray;
            int i2 = 0;
            for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                JSONObject jSONObject = jSONArray2.getJSONObject(i3);
                if (a(currentTimeMillis, jSONObject.getLong("time") * 1000)) {
                    if (i2 < 500) {
                        jSONArray3.put(jSONObject);
                        i2++;
                    } else {
                        arrayList.add(jSONArray3);
                        jSONArray3 = new JSONArray();
                        i2 = 0;
                    }
                }
            }
            if (jSONArray3.length() > 0) {
                arrayList.add(jSONArray3);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    private static String b(String str) {
        String a2 = LogFileUtil.a(LogFileUtil.b(LogFileUtil.f8831a));
        if (TextUtils.isEmpty(a2) && TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{applogs:[");
        if (!TextUtils.isEmpty(a2)) {
            sb.append(a2);
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.replace(sb.length() - 1, sb.length(), "");
        }
        sb.append("]}");
        return sb.toString();
    }
}
