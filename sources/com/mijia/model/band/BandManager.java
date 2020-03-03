package com.mijia.model.band;

import android.text.TextUtils;
import com.mijia.debug.SDKLog;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.stat.a.l;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BandManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public CameraDevice f8041a;
    /* access modifiers changed from: private */
    public String b = "band";

    public BandManager(CameraDevice cameraDevice) {
        this.f8041a = cameraDevice;
    }

    public void a(CameraBand cameraBand, final Callback<Object> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mac", cameraBand.b());
            jSONObject.put("pid", cameraBand.d());
            jSONObject.put("beaconkey", "FFFFFFFFFFFFFFFFFFFFFFFF");
            jSONObject.put(l.a.g, 8193);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.f8041a.a("evtRuleAdd", jSONObject, new Callback<Object>() {
            public void onSuccess(Object obj) {
                String a2 = BandManager.this.b;
                SDKLog.b(a2, "evtRuleAdd onSuccess" + obj);
                BandManager.this.f8041a.notifyStateChanged();
                if (callback != null) {
                    callback.onSuccess(obj);
                }
            }

            public void onFailure(int i, String str) {
                String a2 = BandManager.this.b;
                SDKLog.b(a2, "evtRuleAdd onRequestFailed" + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        });
    }

    public void b(CameraBand cameraBand, final Callback<Object> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mac", cameraBand.b());
            jSONObject.put("pid", cameraBand.d());
            jSONObject.put(l.a.g, 8193);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.f8041a.a("evtRuleDel", jSONObject, new Callback<Object>() {
            public void onSuccess(Object obj) {
                String a2 = BandManager.this.b;
                SDKLog.b(a2, "evtRuleDel onSuccess" + obj);
                BandManager.this.f8041a.notifyStateChanged();
                if (callback != null) {
                    callback.onSuccess(obj);
                }
            }

            public void onFailure(int i, String str) {
                String a2 = BandManager.this.b;
                SDKLog.b(a2, "evtRuleDel onRequestFailed" + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        });
    }

    public void a(final Callback<List<CameraBand>> callback) {
        this.f8041a.callMethod("evtRuleDump", new JSONArray(), new Callback<List<CameraBand>>() {
            /* renamed from: a */
            public void onSuccess(List<CameraBand> list) {
                String a2 = BandManager.this.b;
                SDKLog.b(a2, "get_all_bands onSuccess" + list);
                BandManager.this.f8041a.notifyStateChanged();
                if (callback != null) {
                    callback.onSuccess(list);
                }
            }

            public void onFailure(int i, String str) {
                String a2 = BandManager.this.b;
                SDKLog.b(a2, "get_all_bands onRequestFailed" + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, new Parser<List<CameraBand>>() {
            /* renamed from: a */
            public List<CameraBand> parse(String str) throws JSONException {
                JSONArray optJSONArray;
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.isNull("result") || (optJSONArray = jSONObject.optJSONArray("result")) == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject.isNull("evtid") || optJSONObject.optInt("evtid") == 8193) {
                        arrayList.add(CameraBand.a(optJSONObject));
                    }
                }
                return arrayList;
            }
        });
    }

    public void a(int i, final Callback<Object> callback) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(i);
        this.f8041a.callMethod("start_search_band", jSONArray, new Callback<Object>() {
            public void onSuccess(Object obj) {
                String a2 = BandManager.this.b;
                SDKLog.b(a2, "start_scan_band onSuccess" + obj);
                BandManager.this.f8041a.notifyStateChanged();
                if (callback != null) {
                    callback.onSuccess(obj);
                }
            }

            public void onFailure(int i, String str) {
                String a2 = BandManager.this.b;
                SDKLog.b(a2, "start_scan_band onRequestFailed" + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, (Parser) null);
    }

    public void b(final Callback<List<CameraBand>> callback) {
        this.f8041a.callMethod("get_search_band_result", new JSONArray(), new Callback<List<CameraBand>>() {
            /* renamed from: a */
            public void onSuccess(List<CameraBand> list) {
                String a2 = BandManager.this.b;
                SDKLog.b(a2, "get_scan_band_result onSuccess" + list);
                BandManager.this.f8041a.notifyStateChanged();
                if (callback != null) {
                    callback.onSuccess(list);
                }
            }

            public void onFailure(int i, String str) {
                String a2 = BandManager.this.b;
                SDKLog.b(a2, "get_scan_band_result onRequestFailed" + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, new Parser<List<CameraBand>>() {
            /* renamed from: a */
            public List<CameraBand> parse(String str) throws JSONException {
                JSONArray optJSONArray;
                JSONObject jSONObject = new JSONObject(str);
                if (TextUtils.isEmpty(str) || (optJSONArray = jSONObject.optJSONArray("result")) == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(CameraBand.a(optJSONArray.optJSONObject(i)));
                }
                return arrayList;
            }
        });
    }
}
