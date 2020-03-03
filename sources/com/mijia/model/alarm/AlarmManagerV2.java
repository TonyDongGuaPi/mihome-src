package com.mijia.model.alarm;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.Utils.TimeZoneUtils;
import com.coloros.mcssdk.mode.CommandMessage;
import com.mi.global.shop.model.Tags;
import com.mijia.debug.SDKLog;
import com.mijia.model.alarm.AlarmNetUtils;
import com.taobao.weex.common.Constants;
import com.xiaomi.CameraDevice;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.ICloudDataCallback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmManagerV2 {
    private static final String b = "AlarmManagerV2";
    private static final int f = 20;
    private static final int h = 50;
    private static int l = 20;

    /* renamed from: a  reason: collision with root package name */
    protected CameraDevice f8002a;
    /* access modifiers changed from: private */
    public AlarmConfigV2 c = new AlarmConfigV2();
    private long d = -1;
    /* access modifiers changed from: private */
    public long e = -1;
    /* access modifiers changed from: private */
    public List<AlarmItemV2> g = new ArrayList();
    private long i = (d() / 1000);
    private long j = (System.currentTimeMillis() / 1000);
    /* access modifiers changed from: private */
    public boolean k = false;
    private AlarmConfig m = null;

    public interface IAlarmCallback<T> {
        void onFailure(int i, String str);

        void onSuccess(T t, Object obj);
    }

    public String a(String str, String str2, String str3) {
        return AlarmNetUtils.a().a(str, str2, str3);
    }

    public void a(String str, String str2, String str3, String str4, AlarmNetUtils.MiDownloadNewListener miDownloadNewListener) {
        AlarmNetUtils.a().a(str, str2, str3, str4, miDownloadNewListener);
    }

    public AlarmConfigV2 a() {
        return this.c;
    }

    public void a(String str, JSONObject jSONObject, final IAlarmCallback iAlarmCallback) {
        AlarmNetUtils.a().a(str, jSONObject.toString(), (Callback<JSONObject>) new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    SDKLog.e(AlarmManagerV2.b, "getAlarmConfig:" + jSONObject.toString());
                    if (jSONObject.optInt("code") == 0) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optJSONObject != null) {
                            JSONObject optJSONObject2 = optJSONObject.optJSONObject("motionDetectionSwitch");
                            if (optJSONObject2 != null) {
                                AlarmManagerV2.this.c.f7966a = optJSONObject2.optBoolean("detectionSwitch", false);
                                AlarmManagerV2.this.c.e = optJSONObject2.optInt(Constants.Name.INTERVAL, 5);
                                AlarmManagerV2.this.c.i = optJSONObject2.optBoolean("trackSwitch", false);
                                String optString = optJSONObject2.optString("startTime");
                                if (!TextUtils.isEmpty(optString)) {
                                    String b2 = TimeZoneUtils.a().b(optString);
                                    if (!TextUtils.isEmpty(b2)) {
                                        AlarmManagerV2.this.c.b = b2;
                                    } else {
                                        AlarmManagerV2.this.c.b = optString;
                                    }
                                } else {
                                    AlarmManagerV2.this.c.b = "00:00:00";
                                }
                                String optString2 = optJSONObject2.optString("endTime");
                                if (!TextUtils.isEmpty(optString2)) {
                                    String b3 = TimeZoneUtils.a().b(optString2);
                                    if (!TextUtils.isEmpty(b3)) {
                                        AlarmManagerV2.this.c.c = b3;
                                    } else {
                                        AlarmManagerV2.this.c.c = optString2;
                                    }
                                } else {
                                    AlarmManagerV2.this.c.c = "23:59:59";
                                }
                            }
                            AlarmManagerV2.this.c.f = optJSONObject.optBoolean("pedestrianDetectionPushSwitch", false);
                            AlarmManagerV2.this.c.d = optJSONObject.optBoolean("pushSwitch", false);
                            AlarmManagerV2.this.c.g = AlarmManagerV2.this.a(optJSONObject.optJSONArray("sensitive"));
                            AlarmManagerV2.this.c.j = optJSONObject.optBoolean("babyCrySwitch", false);
                            AlarmManagerV2.this.c.k = optJSONObject.optBoolean("faceSwitch", false);
                            AlarmManagerV2.this.c.n = optJSONObject.optBoolean("babyPush", false);
                            AlarmManagerV2.this.c.o = optJSONObject.optBoolean("facePush", false);
                            AlarmManagerV2.this.c.m = optJSONObject.optBoolean("aiPush", false);
                            AlarmManagerV2.this.c.l = optJSONObject.optBoolean("areaPush", false);
                            AlarmManagerV2.this.c.p = optJSONObject.optBoolean("pedestrianDetectionPushSwitch", false);
                            if (iAlarmCallback != null) {
                                iAlarmCallback.onSuccess(null, (Object) null);
                            }
                        } else if (iAlarmCallback != null) {
                            iAlarmCallback.onFailure(-90002, "data is null");
                        }
                    } else if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(-90002, "code is not 0");
                    }
                } else if (iAlarmCallback != null) {
                    iAlarmCallback.onFailure(-90002, "jsonObject is null");
                }
            }

            public void onFailure(int i, String str) {
                SDKLog.d(AlarmManagerV2.b, "getAlarmConfig i:" + i + " s:" + str);
                if (iAlarmCallback != null) {
                    iAlarmCallback.onFailure(i, str);
                }
            }
        });
    }

    public void b(String str, final JSONObject jSONObject, final IAlarmCallback iAlarmCallback) {
        if (!TextUtils.isEmpty(str) && jSONObject != null) {
            AlarmNetUtils.a().b(str, jSONObject.toString(), new Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        if (jSONObject.optInt("code") == 0) {
                            AlarmManagerV2.this.c.f7966a = jSONObject.optBoolean("open");
                            AlarmManagerV2.this.c.e = jSONObject.optInt(Constants.Name.INTERVAL);
                            AlarmManagerV2.this.c.i = jSONObject.optBoolean("trackSwitch");
                            String optString = jSONObject.optString("startTime");
                            if (!TextUtils.isEmpty(optString)) {
                                String b2 = TimeZoneUtils.a().b(optString);
                                if (!TextUtils.isEmpty(b2)) {
                                    AlarmManagerV2.this.c.b = b2;
                                } else {
                                    AlarmManagerV2.this.c.b = optString;
                                }
                            } else {
                                AlarmManagerV2.this.c.b = "00:00:00";
                            }
                            String optString2 = jSONObject.optString("endTime");
                            if (!TextUtils.isEmpty(optString2)) {
                                String b3 = TimeZoneUtils.a().b(optString2);
                                if (!TextUtils.isEmpty(b3)) {
                                    AlarmManagerV2.this.c.c = b3;
                                } else {
                                    AlarmManagerV2.this.c.c = optString2;
                                }
                            } else {
                                AlarmManagerV2.this.c.c = "23:59:59";
                            }
                            if (iAlarmCallback != null) {
                                iAlarmCallback.onSuccess(null, (Object) null);
                            }
                        } else if (iAlarmCallback != null) {
                            iAlarmCallback.onFailure(-90002, "code is not 0");
                        }
                    } else if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(-90002, "jsonObject is null");
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.d(AlarmManagerV2.b, "setAlarmConfig i:" + i + " s:" + str);
                    if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(i, str);
                    }
                }
            });
        }
    }

    public void c(String str, JSONObject jSONObject, final IAlarmCallback iAlarmCallback) {
        if (!TextUtils.isEmpty(str) && jSONObject != null) {
            AlarmNetUtils.a().c(str, jSONObject.toString(), new Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        SDKLog.b(AlarmManagerV2.b, "jsonObject:" + jSONObject.toString());
                        if (jSONObject.optInt("code") == 0) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("data");
                            if (optJSONObject != null) {
                                AlarmManagerV2.this.c.d = optJSONObject.optBoolean("status");
                                if (iAlarmCallback != null) {
                                    iAlarmCallback.onSuccess(null, (Object) null);
                                }
                            } else if (iAlarmCallback != null) {
                                iAlarmCallback.onFailure(-90002, "data is null");
                            }
                        } else if (iAlarmCallback != null) {
                            iAlarmCallback.onFailure(-90002, "code is not 0");
                        }
                    } else if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(-90002, "jsonObject is null");
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.d(AlarmManagerV2.b, "getPush i:" + i + " s:" + str);
                    if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(i, str);
                    }
                }
            });
        }
    }

    public void d(String str, final JSONObject jSONObject, final IAlarmCallback iAlarmCallback) {
        if (!TextUtils.isEmpty(str) && jSONObject != null) {
            AlarmNetUtils.a().d(str, jSONObject.toString(), new Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        if (jSONObject.optInt("code") == 0) {
                            AlarmManagerV2.this.c.d = jSONObject.optBoolean("open");
                            if (iAlarmCallback != null) {
                                iAlarmCallback.onSuccess(null, (Object) null);
                            }
                        } else if (iAlarmCallback != null) {
                            iAlarmCallback.onFailure(-90002, "code is not 0");
                        }
                    } else if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(-90002, "jsonObject is null");
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.d(AlarmManagerV2.b, "putPush i:" + i + " s:" + str);
                    if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(i, str);
                    }
                }
            });
        }
    }

    public void e(String str, JSONObject jSONObject, final IAlarmCallback iAlarmCallback) {
        if (!TextUtils.isEmpty(str) && jSONObject != null) {
            AlarmNetUtils.a().e(str, jSONObject.toString(), new Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        SDKLog.b(AlarmManagerV2.b, "jsonObject:" + jSONObject.toString());
                        if (jSONObject.optInt("code") == 0) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("data");
                            if (optJSONObject != null) {
                                AlarmManagerV2.this.c.f = optJSONObject.optBoolean("status");
                                if (iAlarmCallback != null) {
                                    iAlarmCallback.onSuccess(null, (Object) null);
                                }
                            } else if (iAlarmCallback != null) {
                                iAlarmCallback.onFailure(-90002, "data is null");
                            }
                        } else if (iAlarmCallback != null) {
                            iAlarmCallback.onFailure(-90002, "code is not 0");
                        }
                    } else if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(-90002, "jsonObject is null");
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.d(AlarmManagerV2.b, "getHumanDetectionPush i:" + i + " s:" + str);
                    if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(i, str);
                    }
                }
            });
        }
    }

    public void f(String str, final JSONObject jSONObject, final IAlarmCallback iAlarmCallback) {
        if (!TextUtils.isEmpty(str) && jSONObject != null) {
            AlarmNetUtils.a().f(str, jSONObject.toString(), new Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        SDKLog.b(AlarmManagerV2.b, "jsonObject:" + jSONObject.toString());
                        if (jSONObject.optInt("code") == 0) {
                            AlarmManagerV2.this.c.f = jSONObject.optBoolean("open");
                            if (iAlarmCallback != null) {
                                iAlarmCallback.onSuccess(null, (Object) null);
                            }
                        } else if (iAlarmCallback != null) {
                            iAlarmCallback.onFailure(-90002, "code is not 0");
                        }
                    } else if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(-90002, "jsonObject is null");
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.d(AlarmManagerV2.b, "putHumanDetectionPush i:" + i + " s:" + str);
                    if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(i, str);
                    }
                }
            });
        }
    }

    public void a(final boolean z, final IAlarmCallback iAlarmCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.f8002a.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("open", z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        XmPluginHostApi.instance().callSmartHomeApi(this.f8002a.getModel(), "business.smartcamera.", AlarmNetUtils.w, "POST", jSONObject.toString(), new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                AlarmManagerV2.this.c.l = z;
                if (iAlarmCallback != null) {
                    iAlarmCallback.onSuccess(jSONObject, (Object) null);
                }
            }

            public void onFailure(int i, String str) {
                if (iAlarmCallback != null) {
                    iAlarmCallback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void b(final boolean z, final IAlarmCallback iAlarmCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.f8002a.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("open", z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        XmPluginHostApi.instance().callSmartHomeApi(this.f8002a.getModel(), "business.smartcamera.", AlarmNetUtils.p, "POST", jSONObject.toString(), new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                AlarmManagerV2.this.c.p = z;
                if (iAlarmCallback != null) {
                    iAlarmCallback.onSuccess(jSONObject, (Object) null);
                }
            }

            public void onFailure(int i, String str) {
                if (iAlarmCallback != null) {
                    iAlarmCallback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void c(final boolean z, final IAlarmCallback iAlarmCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.f8002a.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("open", z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        XmPluginHostApi.instance().callSmartHomeApi(this.f8002a.getModel(), "business.smartcamera.", AlarmNetUtils.v, "POST", jSONObject.toString(), new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                AlarmManagerV2.this.c.m = z;
                if (iAlarmCallback != null) {
                    iAlarmCallback.onSuccess(jSONObject, (Object) null);
                }
            }

            public void onFailure(int i, String str) {
                if (iAlarmCallback != null) {
                    iAlarmCallback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void d(final boolean z, final IAlarmCallback iAlarmCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.f8002a.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("open", z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        XmPluginHostApi.instance().callSmartHomeApi(this.f8002a.getModel(), "business.smartcamera.", AlarmNetUtils.u, "POST", jSONObject.toString(), new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                AlarmManagerV2.this.c.n = z;
                if (iAlarmCallback != null) {
                    iAlarmCallback.onSuccess(jSONObject, (Object) null);
                }
            }

            public void onFailure(int i, String str) {
                if (iAlarmCallback != null) {
                    iAlarmCallback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void e(final boolean z, final IAlarmCallback iAlarmCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.f8002a.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("open", z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        XmPluginHostApi.instance().callSmartHomeApi(this.f8002a.getModel(), "business.smartcamera.", AlarmNetUtils.x, "POST", jSONObject.toString(), new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                AlarmManagerV2.this.c.k = z;
                if (iAlarmCallback != null) {
                    iAlarmCallback.onSuccess(jSONObject, (Object) null);
                }
            }

            public void onFailure(int i, String str) {
                if (iAlarmCallback != null) {
                    iAlarmCallback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void f(final boolean z, final IAlarmCallback iAlarmCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.f8002a.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("open", z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        XmPluginHostApi.instance().callSmartHomeApi(this.f8002a.getModel(), "business.smartcamera.", AlarmNetUtils.y, "POST", jSONObject.toString(), new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                AlarmManagerV2.this.c.j = z;
                if (iAlarmCallback != null) {
                    iAlarmCallback.onSuccess(jSONObject, (Object) null);
                }
            }

            public void onFailure(int i, String str) {
                if (iAlarmCallback != null) {
                    iAlarmCallback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public boolean b() {
        return this.k;
    }

    public void a(final Callback<Void> callback, final boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.f8002a.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("language", Locale.getDefault().getLanguage());
            if (z) {
                jSONObject.put(Tags.Coupon.BEGIN_TIME, d());
                jSONObject.put("endTime", System.currentTimeMillis());
            } else {
                jSONObject.put(Tags.Coupon.BEGIN_TIME, e());
                jSONObject.put("endTime", this.e);
            }
            jSONObject.put("limit", 20);
            AlarmNetUtils.a().i(this.f8002a.getModel(), jSONObject.toString(), new Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    Log.d("zz", "loadDataNew:" + jSONObject.toString());
                    if (jSONObject != null && jSONObject.optInt("code") == 0 && jSONObject.optJSONObject("data") != null) {
                        JSONArray optJSONArray = jSONObject.optJSONObject("data").optJSONArray("playUnits");
                        List arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                arrayList.add(AlarmManagerV2.this.a(optJSONObject));
                            }
                        }
                        if (z) {
                            arrayList = AlarmManagerV2.this.a((List<AlarmItemV2>) arrayList);
                            List unused = AlarmManagerV2.this.g = arrayList;
                        } else {
                            List unused2 = AlarmManagerV2.this.g = AlarmManagerV2.this.a((List<AlarmItemV2>) AlarmManagerV2.this.g, (List<AlarmItemV2>) arrayList);
                        }
                        if (arrayList.size() == 20) {
                            long unused3 = AlarmManagerV2.this.e = ((AlarmItemV2) arrayList.get(arrayList.size() - 1)).c - 1;
                            boolean unused4 = AlarmManagerV2.this.k = true;
                            if (callback != null) {
                                callback.onSuccess(null);
                                return;
                            }
                            return;
                        }
                        boolean unused5 = AlarmManagerV2.this.k = false;
                        if (callback != null) {
                            callback.onSuccess(null);
                        }
                    } else if (callback != null) {
                        callback.onFailure(-90002, "jsonObject is null");
                    }
                }

                public void onFailure(int i, String str) {
                    if (callback != null) {
                        callback.onFailure(i, str);
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public AlarmItemV2 a(JSONObject jSONObject) {
        AlarmItemV2 alarmItemV2 = new AlarmItemV2();
        alarmItemV2.f7969a = jSONObject.optLong("duration");
        alarmItemV2.b = jSONObject.optLong(MibiConstants.eP);
        alarmItemV2.c = jSONObject.optLong("createTime");
        alarmItemV2.e = jSONObject.optString("imgStoreId");
        alarmItemV2.g = jSONObject.optString("fileId");
        alarmItemV2.i = jSONObject.optString("desc");
        alarmItemV2.j = jSONObject.optString(CommandMessage.TYPE_TAGS);
        alarmItemV2.d = jSONObject.optInt("offset");
        alarmItemV2.h = jSONObject.optString("videoStoreId");
        alarmItemV2.f = XmPluginHostApi.instance().getCloudImageUrl(this.f8002a.getDid(), alarmItemV2.g, alarmItemV2.e);
        return alarmItemV2;
    }

    private long e() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.d);
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    private long f() {
        return e() + 86400000;
    }

    /* access modifiers changed from: private */
    public List<AlarmItemV2> a(List<AlarmItemV2> list) {
        if (list.size() == 0) {
            return list;
        }
        this.d = list.get(0).c;
        if (a(list.get(list.size() - 1).c)) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            AlarmItemV2 alarmItemV2 = list.get(i2);
            if (a(alarmItemV2.c)) {
                arrayList.add(alarmItemV2);
            }
        }
        return arrayList;
    }

    public boolean a(long j2) {
        return j2 >= e() && j2 <= f();
    }

    public void a(long j2, long j3, boolean z, final IAlarmCallback<List<AlarmItemV2>> iAlarmCallback) {
        try {
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.f8002a.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("language", Locale.getDefault().getLanguage());
            jSONObject.put("limit", 20);
            jSONObject.put(Tags.Coupon.BEGIN_TIME, j2);
            jSONObject.put("endTime", j3);
            AlarmNetUtils.a().i(this.f8002a.getModel(), jSONObject.toString(), new Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        if (jSONObject.optInt("code") == 0) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("data");
                            if (optJSONObject != null) {
                                JSONArray optJSONArray = optJSONObject.optJSONArray("playUnits");
                                ArrayList arrayList = new ArrayList();
                                for (int i = 0; i < optJSONArray.length(); i++) {
                                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                                    if (optJSONObject2 != null) {
                                        AlarmItemV2 alarmItemV2 = new AlarmItemV2();
                                        alarmItemV2.f7969a = optJSONObject2.optLong("duration");
                                        alarmItemV2.b = optJSONObject2.optLong(MibiConstants.eP);
                                        alarmItemV2.c = optJSONObject2.optLong("createTime");
                                        alarmItemV2.e = optJSONObject2.optString("imgStoreId");
                                        alarmItemV2.g = optJSONObject2.optString("fileId");
                                        alarmItemV2.i = optJSONObject2.optString("desc");
                                        alarmItemV2.j = optJSONObject2.optString(CommandMessage.TYPE_TAGS);
                                        alarmItemV2.d = optJSONObject2.optInt("offset");
                                        alarmItemV2.h = optJSONObject2.optString("videoStoreId");
                                        alarmItemV2.f = XmPluginHostApi.instance().getCloudImageUrl(AlarmManagerV2.this.f8002a.getDid(), alarmItemV2.g, alarmItemV2.e);
                                        arrayList.add(alarmItemV2);
                                    }
                                }
                                if (iAlarmCallback != null) {
                                    iAlarmCallback.onSuccess(arrayList, (Object) null);
                                }
                            } else if (iAlarmCallback != null) {
                                iAlarmCallback.onFailure(-90002, "data is null");
                            }
                        } else if (iAlarmCallback != null) {
                            iAlarmCallback.onFailure(-90002, "code is not 0");
                        }
                    } else if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(-90002, "jsonObject is null");
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.d(AlarmManagerV2.b, "i:" + i + " s:" + str);
                    if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(i, str);
                    }
                }
            });
        } catch (JSONException e2) {
            if (iAlarmCallback != null) {
                iAlarmCallback.onFailure(-90001, e2.getLocalizedMessage());
            }
        }
    }

    public void b(long j2, long j3, boolean z, final IAlarmCallback<List<AlarmItemV2>> iAlarmCallback) {
        try {
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.f8002a.getDid());
            jSONObject.put("model", this.f8002a.getModel());
            jSONObject.put("limit", 20);
            jSONObject.put(Tags.Coupon.BEGIN_TIME, j2);
            jSONObject.put("endTime", j3);
            AlarmNetUtils.a().i(this.f8002a.getModel(), jSONObject.toString(), new Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        if (jSONObject.optInt("code") == 0) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("data");
                            if (optJSONObject != null) {
                                JSONArray optJSONArray = optJSONObject.optJSONArray("thirdPartPlayUnits");
                                ArrayList arrayList = new ArrayList();
                                for (int i = 0; i < optJSONArray.length(); i++) {
                                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                                    if (optJSONObject2 != null) {
                                        AlarmItemV2 alarmItemV2 = new AlarmItemV2();
                                        alarmItemV2.f7969a = optJSONObject2.optLong("duration");
                                        alarmItemV2.b = optJSONObject2.optLong(MibiConstants.eP);
                                        alarmItemV2.c = optJSONObject2.optLong("createTime");
                                        alarmItemV2.e = optJSONObject2.optString("imgStoreId");
                                        alarmItemV2.g = optJSONObject2.optString("fileId");
                                        alarmItemV2.i = optJSONObject2.optString("desc");
                                        alarmItemV2.j = optJSONObject2.optString(CommandMessage.TYPE_TAGS);
                                        alarmItemV2.d = optJSONObject2.optInt("offset");
                                        alarmItemV2.h = optJSONObject2.optString("videoStoreId");
                                        alarmItemV2.f = XmPluginHostApi.instance().getCloudImageUrl(AlarmManagerV2.this.f8002a.getDid(), alarmItemV2.g, alarmItemV2.e);
                                        arrayList.add(alarmItemV2);
                                    }
                                }
                                if (iAlarmCallback != null) {
                                    iAlarmCallback.onSuccess(arrayList, (Object) null);
                                }
                            } else if (iAlarmCallback != null) {
                                iAlarmCallback.onFailure(-90002, "data is null");
                            }
                        } else if (iAlarmCallback != null) {
                            iAlarmCallback.onFailure(-90002, "code is not 0");
                        }
                    } else if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(-90002, "jsonObject is null");
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.d(AlarmManagerV2.b, "i:" + i + " s:" + str);
                    if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(i, str);
                    }
                }
            });
        } catch (JSONException e2) {
            if (iAlarmCallback != null) {
                iAlarmCallback.onFailure(-90001, e2.getLocalizedMessage());
            }
        }
    }

    public void a(Context context, AlarmItemV2 alarmItemV2, final IAlarmCallback<String> iAlarmCallback) {
        if (context != null && alarmItemV2 != null && !TextUtils.isEmpty(alarmItemV2.g) && !TextUtils.isEmpty(alarmItemV2.h)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("did", this.f8002a.getDid());
                jSONObject.put("fileId", alarmItemV2.g);
                jSONObject.put("stoId", alarmItemV2.h);
                XmPluginHostApi.instance().getCloudVideoFile(context, jSONObject.toString(), new ICloudDataCallback<String>() {
                    public void onCloudDataProgress(int i) {
                    }

                    /* renamed from: a */
                    public void onCloudDataSuccess(String str, Object obj) {
                        if (iAlarmCallback != null) {
                            iAlarmCallback.onSuccess(str, obj);
                        }
                    }

                    public void onCloudDataFailed(int i, String str) {
                        SDKLog.d(AlarmManagerV2.b, "getAlarmFile i:" + i + " s:" + str);
                        if (iAlarmCallback != null) {
                            iAlarmCallback.onFailure(i, str);
                        }
                    }
                });
            } catch (JSONException e2) {
                if (iAlarmCallback != null) {
                    iAlarmCallback.onFailure(-90001, e2.getLocalizedMessage());
                }
            }
        } else if (iAlarmCallback != null) {
            iAlarmCallback.onFailure(-90001, "params is invalid");
        }
    }

    /* access modifiers changed from: private */
    public int[] a(JSONArray jSONArray) {
        int[] iArr = new int[jSONArray.length()];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = jSONArray.optInt(i2);
        }
        return iArr;
    }

    public AlarmManagerV2(CameraDevice cameraDevice) {
        this.f8002a = cameraDevice;
    }

    public AlarmItemV2 b(long j2) {
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            if (this.g.get(i2).c == j2) {
                return this.g.get(i2);
            }
        }
        return null;
    }

    public AlarmItemV2 a(String str) {
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            if (this.g.get(i2) != null && !TextUtils.isEmpty(this.g.get(i2).g) && !TextUtils.isEmpty(str) && this.g.get(i2).g.equals(str)) {
                return this.g.get(i2);
            }
        }
        return null;
    }

    public List<AlarmItemV2> c() {
        return this.g;
    }

    /* access modifiers changed from: private */
    public List<AlarmItemV2> a(List<AlarmItemV2> list, List<AlarmItemV2> list2) {
        ArrayList arrayList = new ArrayList();
        if (list == null && list2 == null) {
            return arrayList;
        }
        if (list2 == null || list2.size() == 0) {
            if (list != null) {
                arrayList.addAll(list);
            }
            return arrayList;
        } else if (list == null || list.size() == 0) {
            arrayList.addAll(list2);
            return arrayList;
        } else {
            int i2 = 0;
            long j2 = list2.get(0).c;
            while (i2 < list.size() && list.get(i2).c > j2) {
                arrayList.add(list.get(i2));
                i2++;
            }
            arrayList.addAll(list2);
            return arrayList;
        }
    }

    public long d() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis() - 518400000;
    }
}
