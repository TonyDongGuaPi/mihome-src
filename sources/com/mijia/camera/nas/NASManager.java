package com.mijia.camera.nas;

import android.text.TextUtils;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NASManager {

    /* renamed from: a  reason: collision with root package name */
    public static int f7933a = -30001;
    public static int b = -30002;
    private CameraDevice c;
    /* access modifiers changed from: private */
    public NASInfo d = null;

    public NASManager(CameraDevice cameraDevice) {
        this.c = cameraDevice;
    }

    public NASInfo a() {
        return this.d;
    }

    public void a(final Callback<List<NASServer>> callback) {
        this.c.callMethod("nas_scan", new JSONArray(), new Callback<List<NASServer>>() {
            /* renamed from: a */
            public void onSuccess(List<NASServer> list) {
                if (callback != null) {
                    callback.onSuccess(list);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, new Parser<List<NASServer>>() {
            /* renamed from: a */
            public List<NASServer> parse(String str) throws JSONException {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.isNull("result")) {
                    return null;
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(NASServer.a(optJSONArray.optJSONObject(i)));
                }
                return arrayList;
            }
        });
    }

    public void a(final NASNode nASNode, final Callback<List<NASNode>> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("share", nASNode.e());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.c.callMethod("nas_list_dir", jSONObject, new Callback<List<NASNode>>() {
            /* renamed from: a */
            public void onSuccess(List<NASNode> list) {
                if (callback != null) {
                    callback.onSuccess(list);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, new Parser<List<NASNode>>() {
            /* renamed from: a */
            public List<NASNode> parse(String str) throws JSONException {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.isNull("result")) {
                    return null;
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(new NASNode(nASNode, optJSONArray.optString(i)));
                }
                nASNode.a((ArrayList<NASNode>) arrayList);
                return arrayList;
            }
        });
    }

    public void b(final Callback<NASInfo> callback) {
        this.c.callMethod("nas_get_config", new JSONObject(), new Callback<NASInfo>() {
            /* renamed from: a */
            public void onSuccess(NASInfo nASInfo) {
                NASInfo unused = NASManager.this.d = nASInfo;
                if (callback != null) {
                    callback.onSuccess(nASInfo);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, new Parser<NASInfo>() {
            /* renamed from: a */
            public NASInfo parse(String str) throws JSONException {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull("result")) {
                    jSONObject = jSONObject.getJSONObject("result");
                }
                return NASInfo.a(jSONObject);
            }
        });
    }

    public void a(NASInfo nASInfo, final Callback<Object> callback) {
        this.c.callMethod("nas_set_config", nASInfo.h(), new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void c(final Callback<Object> callback) {
        this.c.a("nas_reset", new JSONArray(), new Callback<Object>() {
            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }

            public void onSuccess(Object obj) {
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }
        });
    }

    public void d(final Callback<Object> callback) {
        this.c.a("nas_clear_dir", new JSONArray(), new Callback<Object>() {
            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }

            public void onSuccess(Object obj) {
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }
        });
    }

    public void a(boolean z, final Callback<Object> callback) {
        this.c.a(z ? "nas_sync_stop" : "nas_sync_start", new JSONArray(), new Callback<Object>() {
            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }

            public void onSuccess(Object obj) {
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }
        });
    }

    public void a(NASMakeDirInfo nASMakeDirInfo, final Callback<Object> callback) {
        if (nASMakeDirInfo != null && !TextUtils.isEmpty(nASMakeDirInfo.e)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("share", nASMakeDirInfo.a());
                this.c.callMethod("nas_make_dir", jSONObject, new Callback<Object>() {
                    public void onSuccess(Object obj) {
                        if (callback != null) {
                            callback.onSuccess(obj);
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (callback != null) {
                            callback.onFailure(i, str);
                        }
                    }
                }, (Parser) null);
            } catch (JSONException unused) {
                if (callback != null) {
                    callback.onFailure(b, "JSONException");
                }
            }
        } else if (callback != null) {
            callback.onFailure(f7933a, "invalid smbInfo");
        }
    }
}
