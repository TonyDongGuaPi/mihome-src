package com.xiaomi.smarthome.scene.geofence.manager;

import android.os.Build;
import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.AppConfigHelper;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.http.Error;
import java.util.HashSet;
import java.util.Set;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MIUIGeoFenceConfigHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21541a = "{\"strategy\":1,\"phone_list\":[],\"start_version\":62987}";
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static volatile int e = 1;
    private static volatile int f;
    private static Set<String> g = new HashSet();
    private static Set<String> h = new HashSet();
    /* access modifiers changed from: private */
    public static volatile boolean i = false;
    /* access modifiers changed from: private */
    public static volatile boolean j = false;

    public static boolean a() {
        if (!i) {
            b();
        }
        if (f > GlobalSetting.n) {
            return false;
        }
        if (e == 1) {
            return true;
        }
        if (e == 2) {
            if (g.contains(c())) {
                return true;
            }
            return false;
        } else if (e != 3 || h.contains(c())) {
            return false;
        } else {
            return true;
        }
    }

    public static void b() {
        if (!j) {
            j = true;
            new AppConfigHelper(SHApplication.getAppContext()).a("miui_geofence_config", "1", "zh_CN", (AppConfigHelper.OnCacheHandler<JSONObject>) new AppConfigHelper.OnCacheHandler<JSONObject>() {
                public boolean b(JSONObject jSONObject) throws Exception {
                    return false;
                }

                public boolean a(JSONObject jSONObject) throws Exception {
                    return MIUIGeoFenceConfigHelper.b(true, jSONObject);
                }
            }, (AppConfigHelper.JsonAsyncHandler) new AppConfigHelper.JsonAsyncHandler() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject, Response response) {
                    try {
                        if (jSONObject.optJSONObject("result") != null && jSONObject.has("content")) {
                            boolean unused = MIUIGeoFenceConfigHelper.b(false, jSONObject);
                        }
                        boolean unused2 = MIUIGeoFenceConfigHelper.i = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    boolean unused3 = MIUIGeoFenceConfigHelper.j = false;
                }

                public void onFailure(Error error, Exception exc, Response response) {
                    String str;
                    boolean unused = MIUIGeoFenceConfigHelper.i = false;
                    StringBuilder sb = new StringBuilder();
                    sb.append("updateConfig onFailure ");
                    if (error == null) {
                        str = null;
                    } else {
                        str = error.a() + "," + error.b();
                    }
                    sb.append(str);
                    LogUtilGrey.a("MIUIGeoFenceConfigHelper", sb.toString());
                    boolean unused2 = MIUIGeoFenceConfigHelper.j = false;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static boolean b(boolean z, JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject;
        int i2;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("result")) == null) {
            return false;
        }
        String optString = optJSONObject.optString("content");
        if (TextUtils.isEmpty(optString)) {
            GrayLogDelegate.a("AppConfig geofence_config", "isFromCache:" + z + ", content==null");
            if (z) {
                return false;
            }
            optString = f21541a;
        }
        JSONObject jSONObject2 = new JSONObject(optString);
        GrayLogDelegate.a("AppConfig geofence_config", "isFromCache:" + z + ",parse content" + jSONObject2);
        int optInt = jSONObject2.optInt(Constants.Name.STRATEGY, 1);
        int optInt2 = jSONObject2.optInt("start_version", 0);
        JSONArray optJSONArray = jSONObject2.optJSONArray("phone_list");
        HashSet hashSet = new HashSet();
        int i3 = 0;
        while (true) {
            if (optJSONArray == null) {
                i2 = 0;
            } else {
                i2 = optJSONArray.length();
            }
            if (i3 >= i2) {
                break;
            }
            String optString2 = optJSONArray.optString(i3);
            if (!TextUtils.isEmpty(optString2)) {
                hashSet.add(optString2);
            }
            i3++;
        }
        if (optInt == 2) {
            g = hashSet;
        } else if (optInt == 3) {
            h = hashSet;
        }
        f = optInt2;
        e = optInt;
        return true;
    }

    public static String c() {
        LogUtilGrey.a("MIUIGeoFenceConfigHelper", "getPhoneModel:MODEL=" + Build.MODEL + ",BRAND=" + Build.BRAND + ",BOARD=" + Build.BOARD + ",DEVICE=" + Build.DEVICE + ",DISPLAY=" + Build.DISPLAY + ",HARDWARE=" + Build.HARDWARE + ",PRODUCT=" + Build.PRODUCT + ",MANUFACTURER=" + Build.MANUFACTURER);
        return Build.DEVICE;
    }
}
