package com.xiaomi.smarthome.miio.camera.cloudstorage.utils;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudVideoEventLogger {
    public static final String CloudServiceInfoNumber = "plg.cld.qbe.pwt";
    public static final String CloudServiceNumber = "plg.cld.m3m.xbb";
    public static final String CloudVideo_AudioNumber = "plg.4gw.qte.g2w";
    public static final String CloudVideo_DeleteNumber = "plg.4gw.zv6.o3c";
    public static final String CloudVideo_DownloadNumber = "plg.4gw.zv6.m1p";
    public static final String CloudVideo_FullScreenNumber = "plg.4gw.ohr.iqo";
    public static final String CloudVideo_ScreenshotNumber = "plg.4gw.zv6.f98";
    public static final String CloudVideo_ViewTime = "plg.4gw.4fn.53o";
    public static final String DownloadListNumber = "plg.cld.061.6i6";
    public static final String RenewNumber = "plg.cld.i1k.u97";
    private static final String TAG = "CloudVideoEventLogger";
    private static final String TYPE_CLICK = "click:";
    private static final String TYPE_DURATION = "result:";

    public static void EventClick(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            EventLogger(str, TYPE_CLICK + str2, (String) null, str3, false);
        }
    }

    public static void EventDuration(String str, String str2, long j, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String str4 = TYPE_DURATION + str2;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("time", j);
                EventLogger(str, str4, jSONObject.toString(), str3, false);
            } catch (JSONException unused) {
            }
        }
    }

    public static void EventType(String str, String str2, int i, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String str4 = TYPE_CLICK + str2;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", i);
                EventLogger(str, str4, jSONObject.toString(), str3, false);
            } catch (JSONException unused) {
            }
        }
    }

    private static void EventLogger(String str, String str2, String str3, String str4, boolean z) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                PluginRecord d = CoreApi.a().d(str);
                String str5 = "plugin." + d.d() + "." + d.e();
                LogUtil.a(TAG, "am:" + str5);
                CoreApi.a().a(StatType.PLUGIN, str5, str2, str3, str4, z);
            } catch (Exception unused) {
            }
        }
    }
}
