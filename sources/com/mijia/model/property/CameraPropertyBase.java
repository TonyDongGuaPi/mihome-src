package com.mijia.model.property;

import android.text.TextUtils;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class CameraPropertyBase {
    public static final String e = "CameraProperties";
    public static final String f = "prop.";
    public static final String g = "light";
    public static final String h = "motion_record";
    public static final String i = "flip";
    public static final String j = "watermark";
    public static final String k = "sdcard_status";
    public static final String l = "power";
    public static final String m = "wdr";
    public static final String n = "night_mode";
    public static final String o = "full_color";
    public static final String p = "max_client";
    public static final String q = "on";
    public static final String r = "off";
    public static final String s = "stop";
    public static final String t = "track";
    public static final String u = "rect";

    /* renamed from: a  reason: collision with root package name */
    private PropertyManger f8059a;
    public CameraDevice v;
    public HashMap<String, Object> w = new HashMap<>();
    public ICameraPropertyListener x;

    public interface ICameraPropertyListener {
        void onCameraPropertyChanged(String str);
    }

    public abstract List<String> a();

    public abstract List<String> b();

    public CameraPropertyBase(CameraDevice cameraDevice) {
        this.v = cameraDevice;
        this.f8059a = new PropertyManger(this.v);
    }

    public void a(final String[] strArr, final Callback<Void> callback) {
        if (strArr != null && strArr.length != 0) {
            JSONArray jSONArray = new JSONArray();
            for (String put : strArr) {
                jSONArray.put(put);
            }
            LogUtil.a(e, "request params:" + jSONArray.toString());
            this.v.callMethod("get_prop", jSONArray, new Callback<JSONArray>() {
                /* renamed from: a */
                public void onSuccess(JSONArray jSONArray) {
                    if (jSONArray != null) {
                        SDKLog.b(CameraPropertyBase.e, " update prop success:" + jSONArray);
                    }
                    if (jSONArray != null && jSONArray.length() != 0) {
                        int i = 0;
                        while (i < jSONArray.length() && i < strArr.length) {
                            try {
                                CameraPropertyBase.this.w.put(strArr[i], jSONArray.get(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            i++;
                        }
                        CameraPropertyBase.this.v.notifyStateChanged();
                        if (callback != null) {
                            callback.onSuccess(null);
                        }
                    } else if (callback != null) {
                        callback.onFailure(-1, "");
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.b(CameraPropertyBase.e, " update prop fail  " + str);
                    if (callback != null) {
                        callback.onFailure(i, str);
                    }
                }
            }, new Parser<JSONArray>() {
                /* renamed from: a */
                public JSONArray parse(String str) throws JSONException {
                    return new JSONObject(str).optJSONArray("result");
                }
            });
        }
    }

    public boolean c() {
        return a(l, true);
    }

    public int a(String str) {
        int intValue;
        Object obj = this.w.get(str);
        if (obj == null) {
            return 0;
        }
        try {
            if (obj instanceof String) {
                intValue = Integer.valueOf((String) obj).intValue();
            } else if (!(obj instanceof Integer)) {
                return 0;
            } else {
                intValue = ((Integer) obj).intValue();
            }
            return intValue;
        } catch (Exception e2) {
            SDKLog.e(Tag.d, "getIntProperty", e2);
            return 0;
        }
    }

    public boolean a(String str, boolean z) {
        Object obj = this.w.get(str);
        if (obj == null) {
            return z;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        if ("on".equals(obj)) {
            return true;
        }
        if ("off".equals(obj)) {
            return false;
        }
        return z;
    }

    public String d() {
        Object obj = this.w.get(h);
        return obj instanceof String ? (String) obj : "";
    }

    public boolean e() {
        return a(p) == 1;
    }

    public boolean b(String str) {
        return a(str, false);
    }

    public void a(String str, boolean z, Callback<Void> callback) {
        if (this.x != null && !TextUtils.isEmpty(str)) {
            this.x.onCameraPropertyChanged(str);
        }
        a(str, (Object) z ? "on" : "off", callback);
    }

    public void a(String str, Object obj) {
        this.w.put(str, obj);
    }

    public void a(final String str, final Object obj, final Callback<Void> callback) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(obj);
        CameraDevice cameraDevice = this.v;
        cameraDevice.callMethod("set_" + str, jSONArray, new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                SDKLog.d(CameraPropertyBase.e, "set_" + str + "  success");
                CameraPropertyBase.this.a(str, obj);
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }

            public void onFailure(int i, String str) {
                SDKLog.e(CameraPropertyBase.e, "set_" + str + "  fail " + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void b(String str, Object obj) {
        this.w.put(str, obj);
    }

    public void f() {
        this.f8059a.a();
    }

    public boolean g() {
        return a("track", false);
    }

    public void a(boolean z, int i2, final Callback<Void> callback) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(z ? "on" : "off");
        jSONArray.put(i2 + "");
        SDKLog.b(e, "setAutoBitrate params=" + jSONArray.toString());
        this.v.callMethod("SetAutoBitrate", jSONArray, new Callback<Object>() {
            public void onSuccess(Object obj) {
                SDKLog.b(CameraPropertyBase.e, "setAutoBitrate result=" + obj);
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, (Parser) null);
    }

    public void a(final Callback<JSONArray> callback) {
        this.v.callMethod("GetAutoBitrate", new JSONArray(), new Callback<JSONArray>() {
            /* renamed from: a */
            public void onSuccess(JSONArray jSONArray) {
                if (callback != null) {
                    callback.onSuccess(jSONArray);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, new Parser<JSONArray>() {
            /* renamed from: a */
            public JSONArray parse(String str) throws JSONException {
                SDKLog.b(CameraPropertyBase.e, "getAutoBitrate result=" + str);
                return new JSONObject(str).optJSONArray("result");
            }
        });
    }

    public void b(final Callback<JSONObject> callback) {
        this.v.callMethod("getCustomVoiceInfo", new JSONArray(), new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (callback != null) {
                    callback.onSuccess(jSONObject);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, new Parser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(String str) throws JSONException {
                SDKLog.b(CameraPropertyBase.e, "getCustomVoiceInfo result=" + str);
                return new JSONObject(str);
            }
        });
    }
}
