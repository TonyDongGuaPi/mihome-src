package com.xiaomi.smarthome.light.group;

import android.text.TextUtils;
import android.util.SparseArray;
import com.mi.global.bbs.utils.Constants;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.BleMeshFirmwareUpdateInfoV2;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LightGroupManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19145a = "1";
    public static final String b = "3";
    public static final String c = "0";
    public static final String[] d = {"1", "3", "0"};
    public static final String e = "on";
    public static final String f = "brightness";
    public static final String g = "color-temperature";
    public static final String h = "color";
    public static final String[] i = {"on", f, g, "color"};
    public static final String[] j = {"mijia.light.group1", "mijia.light.group2", "mijia.light.group3", "mijia.light.group4"};
    private static LightGroupManager k;
    /* access modifiers changed from: private */
    public Map<String, List<String>> l = new ConcurrentHashMap();

    private LightGroupManager() {
    }

    public static LightGroupManager a() {
        if (k == null) {
            synchronized (LightGroupManager.class) {
                if (k == null) {
                    k = new LightGroupManager();
                }
            }
        }
        return k;
    }

    public void b() {
        DevicelibApi.defLightGroup(CommonApplication.getAppContext(), new AsyncCallback<JSONObject, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                try {
                    LightGroupManager.this.l.clear();
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        JSONArray optJSONArray = jSONObject.optJSONArray(next);
                        if (optJSONArray.length() > 0) {
                            ArrayList arrayList = new ArrayList();
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                arrayList.add(optJSONArray.optString(i));
                            }
                            LightGroupManager.this.l.put(next, arrayList);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void a(Set<String> set, AsyncCallback<SparseArray<List<String>>, Error> asyncCallback) {
        DevicelibApi.queryModelFunction(CommonApplication.getAppContext(), i, set, asyncCallback);
    }

    public boolean a(Device device) {
        if (device == null || TextUtils.isEmpty(device.model) || !device.isOwner()) {
            return false;
        }
        for (List<String> contains : this.l.values()) {
            if (contains.contains(device.model)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(Device device, String str) {
        List list;
        if (device == null || TextUtils.isEmpty(str) || !device.isOwner() || (list = this.l.get(str)) == null || list.size() <= 0) {
            return false;
        }
        return list.contains(device.model);
    }

    public List<String> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.l.get(str);
    }

    public boolean b(Device device) {
        return device != null && device.pid == Device.PID_VIRTUAL_GROUP;
    }

    public String b(String str) {
        for (Map.Entry next : this.l.entrySet()) {
            if (((List) next.getValue()).contains(str)) {
                return (String) next.getKey();
            }
        }
        return "";
    }

    public void a(String str, List<String> list, Callback<List<BleMeshFirmwareUpdateInfoV2>> callback) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            for (String put : list) {
                jSONArray.put(put);
            }
            jSONObject.put("dids", jSONArray);
            PluginHostApi.instance().callSmartHomeApi(str, "/v2/device/batch_get_latest_ver", jSONObject, callback, $$Lambda$LightGroupManager$CygNgwkSsiOIYbLZRVWAsax4F_c.INSTANCE);
        } catch (JSONException e2) {
            if (callback != null) {
                callback.onFailure(-1, e2.toString());
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List c(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has("list")) {
            JSONObject optJSONObject = jSONObject.optJSONObject("list");
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                BleMeshFirmwareUpdateInfoV2 bleMeshFirmwareUpdateInfoV2 = new BleMeshFirmwareUpdateInfoV2();
                bleMeshFirmwareUpdateInfoV2.did = keys.next();
                JSONObject optJSONObject2 = optJSONObject.optJSONObject(bleMeshFirmwareUpdateInfoV2.did);
                if (!optJSONObject2.optBoolean("isLatest")) {
                    bleMeshFirmwareUpdateInfoV2.currentVersion = optJSONObject2.optString("current");
                    String optString = optJSONObject2.optString(Constants.PageFragment.PAGE_LATEST);
                    if (jSONObject.has("detail")) {
                        JSONObject optJSONObject3 = jSONObject.optJSONObject("detail");
                        if (optJSONObject3.has("info")) {
                            JSONObject optJSONObject4 = optJSONObject3.optJSONObject("info");
                            if (optJSONObject4.has(optString)) {
                                JSONObject optJSONObject5 = optJSONObject4.optJSONObject(optString);
                                bleMeshFirmwareUpdateInfoV2.version = optJSONObject5.optString("version");
                                bleMeshFirmwareUpdateInfoV2.url = optJSONObject5.optString("url");
                                bleMeshFirmwareUpdateInfoV2.safeUrl = optJSONObject5.optString("safe_url");
                                bleMeshFirmwareUpdateInfoV2.changeLog = optJSONObject5.optString("changeLog");
                                bleMeshFirmwareUpdateInfoV2.md5 = optJSONObject5.optString("md5");
                                bleMeshFirmwareUpdateInfoV2.uploadTime = optJSONObject5.optString("upload_time");
                                arrayList.add(bleMeshFirmwareUpdateInfoV2);
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }
}
