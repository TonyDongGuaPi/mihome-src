package com.mijia.model.alarm;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.Utils.TimeZoneUtils;
import com.coloros.mcssdk.mode.CommandMessage;
import com.mi.blockcanary.internal.BlockInfo;
import com.mi.global.shop.model.Tags;
import com.mijia.debug.SDKLog;
import com.taobao.weex.common.Constants;
import com.xiaomi.CameraDevice;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.ICloudDataCallback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmManager {
    private static final String b = "AlarmManager";
    private static final int c = 50;
    /* access modifiers changed from: private */
    public static int g = 20;
    private static final int l = 20;

    /* renamed from: a  reason: collision with root package name */
    protected CameraDevice f7970a;
    private long d = (e() / 1000);
    /* access modifiers changed from: private */
    public long e = (System.currentTimeMillis() / 1000);
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    public List<AlarmItem> h = new ArrayList();
    /* access modifiers changed from: private */
    public AlarmConfig i = null;
    /* access modifiers changed from: private */
    public AlarmConfigV2 j = new AlarmConfigV2();
    /* access modifiers changed from: private */
    public long k = -1;
    private boolean m = false;
    private long n = -1;
    /* access modifiers changed from: private */
    public long o = -1;

    public interface IAlarmCallback<T> {
        void onFailure(int i, String str);

        void onSuccess(T t, Object obj);
    }

    public AlarmManager(CameraDevice cameraDevice) {
        this.f7970a = cameraDevice;
    }

    public AlarmItem a(long j2) {
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            if (this.h.get(i2).b == j2) {
                return this.h.get(i2);
            }
        }
        return null;
    }

    public void a() {
        this.e = System.currentTimeMillis() / 1000;
        this.d = e() / 1000;
    }

    public boolean b() {
        return this.f;
    }

    public List<AlarmItem> c() {
        return this.h;
    }

    public void a(Callback<Void> callback, boolean z) {
        if (z || this.h.isEmpty()) {
            a();
            a(callback);
            return;
        }
        callback.onSuccess(null);
    }

    public void a(final Callback<Void> callback) {
        a(this.d, this.e, 50, new Callback<JSONArray>() {
            /* renamed from: a */
            public void onSuccess(JSONArray jSONArray) {
                ArrayList arrayList = new ArrayList();
                if (jSONArray == null || jSONArray.length() <= 0) {
                    boolean unused = AlarmManager.this.f = false;
                } else {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(AlarmItem.a(jSONArray.optJSONObject(i)));
                    }
                    if (jSONArray.length() == 50) {
                        long unused2 = AlarmManager.this.e = (((AlarmItem) arrayList.get(arrayList.size() - 1)).b / 1000) - 1;
                        boolean unused3 = AlarmManager.this.f = true;
                    } else {
                        boolean unused4 = AlarmManager.this.f = false;
                    }
                    List unused5 = AlarmManager.this.h = arrayList;
                }
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }

            public void onFailure(int i, String str) {
                Log.d(AlarmManager.b, "getUserDeviceData failed");
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public List<AlarmItem> a(List<AlarmItem> list, List<AlarmItem> list2) {
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
            long j2 = list2.get(0).b;
            while (i2 < list.size() && list.get(i2).b > j2) {
                arrayList.add(list.get(i2));
                i2++;
            }
            arrayList.addAll(list2);
            return arrayList;
        }
    }

    public void a(final List<AlarmItem> list, final Callback<Void> callback) {
        if (list != null && list.size() != 0) {
            final ArrayList arrayList = new ArrayList();
            new Thread(new Runnable() {
                public void run() {
                    int size = list.size();
                    int h = AlarmManager.g;
                    int i = size < h ? size : h + 0;
                    int i2 = 0;
                    while (i2 < size) {
                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                        final List subList = list.subList(i2, i);
                        JSONArray jSONArray = new JSONArray();
                        int i3 = 0;
                        while (i3 < subList.size()) {
                            try {
                                AlarmItem alarmItem = (AlarmItem) subList.get(i3);
                                if (alarmItem != null) {
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("did", alarmItem.c);
                                    jSONObject.put("type", alarmItem.d);
                                    jSONObject.put("key", alarmItem.e);
                                    jSONObject.put("time", alarmItem.b / 1000);
                                    jSONArray.put(jSONObject);
                                }
                                i3++;
                            } catch (JSONException unused) {
                                callback.onFailure(-1001, (String) null);
                                return;
                            }
                        }
                        AlarmManager.this.a(jSONArray.toString(), (Callback<JSONObject>) new Callback<JSONObject>() {
                            /* renamed from: a */
                            public void onSuccess(JSONObject jSONObject) {
                                try {
                                    AlarmManager.this.h.removeAll(subList);
                                    arrayList.add(true);
                                } catch (Exception e) {
                                    LogUtil.b(AlarmManager.b, "delete alarm failed:" + e.getLocalizedMessage());
                                }
                                countDownLatch.countDown();
                                LogUtil.a("alarm", "delete alarm success " + subList.size());
                            }

                            public void onFailure(int i, String str) {
                                try {
                                    arrayList.add(false);
                                } catch (Exception e) {
                                    LogUtil.b(AlarmManager.b, "delete alarm failed2:" + e.getLocalizedMessage());
                                }
                                countDownLatch.countDown();
                                SDKLog.e("alarm", "delete alarm fail " + str);
                            }
                        });
                        try {
                            countDownLatch.await();
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int i4 = i + h;
                        if (i4 >= size) {
                            i4 = size;
                        }
                        if (arrayList.isEmpty() || ((Boolean) arrayList.remove(0)).booleanValue()) {
                            int i5 = i;
                            i = i4;
                            i2 = i5;
                        } else if (callback != null) {
                            callback.onFailure(-1, "");
                            return;
                        } else {
                            return;
                        }
                    }
                    if (callback != null) {
                        callback.onSuccess(null);
                    }
                }
            }).start();
        } else if (callback != null) {
            callback.onFailure(-1, "");
        }
    }

    public void a(long j2, final Callback<AlarmItem> callback) {
        a(0, j2 / 1000, 1, new Callback<JSONArray>() {
            /* renamed from: a */
            public void onSuccess(JSONArray jSONArray) {
                callback.onSuccess(jSONArray.length() > 0 ? AlarmItem.a(jSONArray.optJSONObject(0)) : null);
            }

            public void onFailure(int i, String str) {
                callback.onFailure(i, str);
                SDKLog.e("alarm", "get alarm fail " + str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(long j2, long j3, int i2, Callback<JSONArray> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.f7970a.getDid());
            jSONObject.put("type", "event");
            jSONObject.put("key", "motion");
            jSONObject.put(BlockInfo.k, j2);
            jSONObject.put(BlockInfo.l, j3);
            jSONObject.put("limit", i2);
            jSONObject.put("fetchAlertVideo", true);
        } catch (JSONException e2) {
            if (callback != null) {
                callback.onFailure(-1, e2.toString());
                return;
            }
        }
        XmPluginHostApi.instance().callSmartHomeApi(this.f7970a.getModel(), "/user/get_user_device_data", jSONObject, callback, new Parser<JSONArray>() {
            /* renamed from: a */
            public JSONArray parse(String str) throws JSONException {
                return new JSONObject(str).getJSONArray("result");
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, Callback<JSONObject> callback) {
        XmPluginHostApi.instance().callSmartHomeApi(this.f7970a.getModel(), "/user/del_user_device_data_batch", str, callback, Parser.DEFAULT_PARSER);
    }

    public void a(final Callback<Void> callback, int[][] iArr) {
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < 4; i2++) {
            for (int i3 = 0; i3 < 8; i3++) {
                jSONArray.put(iArr[i2][i3]);
            }
        }
        this.f7970a.callMethod("set_motion_region", jSONArray, new Callback<Object>() {
            public void onSuccess(Object obj) {
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

    public void b(final Callback<int[][]> callback, boolean z) {
        this.f7970a.callMethod("get_motion_region", new JSONArray(), new Callback<Integer[][]>() {
            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }

            /* renamed from: a */
            public void onSuccess(Integer[][] numArr) {
                if (callback == null) {
                    return;
                }
                if (numArr == null) {
                    callback.onFailure(-1, "返回数目不对不是32");
                    return;
                }
                int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{4, 8});
                for (int i = 0; i < 4; i++) {
                    for (int i2 = 0; i2 < 8; i2++) {
                        iArr[i][i2] = numArr[i][i2].intValue();
                    }
                }
                callback.onSuccess(iArr);
            }
        }, new Parser<Integer[][]>() {
            /* renamed from: a */
            public Integer[][] parse(String str) throws JSONException {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.isNull("result")) {
                    return null;
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                Integer[][] numArr = (Integer[][]) Array.newInstance(Integer.class, new int[]{4, 8});
                if (optJSONArray.length() != 32) {
                    return null;
                }
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    numArr[i / 8][i % 8] = Integer.valueOf(optJSONArray.getInt(i));
                }
                return numArr;
            }
        });
    }

    public void b(final Callback<AlarmConfig> callback) {
        this.f7970a.callMethod("getAlarmConfig", new JSONArray(), new Callback<AlarmConfig>() {
            /* renamed from: a */
            public void onSuccess(AlarmConfig alarmConfig) {
                if (callback != null) {
                    AlarmConfig unused = AlarmManager.this.i = alarmConfig;
                    callback.onSuccess(alarmConfig);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, new Parser<AlarmConfig>() {
            /* renamed from: a */
            public AlarmConfig parse(String str) throws JSONException {
                return AlarmConfig.a(new JSONObject(str).optJSONArray("result"));
            }
        });
    }

    public void a(final AlarmConfig alarmConfig, final Callback<JSONObject> callback) {
        this.f7970a.callMethod("setAlarmConfig", alarmConfig.a(), new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (callback != null) {
                    AlarmConfig unused = AlarmManager.this.i = alarmConfig;
                    callback.onSuccess(jSONObject);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public AlarmConfig d() {
        return this.i;
    }

    public void a(long j2, long j3, Callback<List<AlarmItem>> callback) {
        final long j4 = j3;
        final long j5 = j2;
        final Callback<List<AlarmItem>> callback2 = callback;
        new Thread(new Runnable() {
            public void run() {
                long[] jArr = {j4};
                jArr[0] = j4;
                int[] iArr = {0};
                ArrayList arrayList = new ArrayList();
                do {
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    final long[] jArr2 = jArr;
                    final int[] iArr2 = iArr;
                    final ArrayList arrayList2 = arrayList;
                    final CountDownLatch countDownLatch2 = countDownLatch;
                    AlarmManager.this.a(j5, jArr[0], 50, new Callback<JSONArray>() {
                        /* renamed from: a */
                        public void onSuccess(JSONArray jSONArray) {
                            ArrayList arrayList = new ArrayList();
                            if (jSONArray == null || jSONArray.length() <= 0) {
                                iArr2[0] = 0;
                            } else {
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    arrayList.add(AlarmItem.a(jSONArray.optJSONObject(i)));
                                }
                                if (jSONArray.length() == 50) {
                                    jArr2[0] = (((AlarmItem) arrayList.get(arrayList.size() - 1)).b / 1000) - 1;
                                    iArr2[0] = 50;
                                } else {
                                    iArr2[0] = jSONArray.length();
                                }
                                arrayList2.addAll(arrayList);
                            }
                            countDownLatch2.countDown();
                        }

                        public void onFailure(int i, String str) {
                            iArr2[0] = -1;
                            countDownLatch2.countDown();
                        }
                    });
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (iArr[0] == 50);
                if (callback2 == null) {
                    return;
                }
                if (iArr[0] != -1 || !arrayList.isEmpty()) {
                    callback2.onSuccess(arrayList);
                } else {
                    callback2.onFailure(-1, "");
                }
            }
        }).start();
    }

    public long e() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis() - 518400000;
    }

    public AlarmConfigV2 f() {
        return this.j;
    }

    public void a(String str, JSONObject jSONObject, final IAlarmCallback iAlarmCallback) {
        AlarmNetUtils.a().a(str, jSONObject.toString(), (Callback<JSONObject>) new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    SDKLog.b(AlarmManager.b, "getAlarmConfig:" + jSONObject.toString());
                    if (jSONObject.optInt("code") == 0) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optJSONObject != null) {
                            JSONObject optJSONObject2 = optJSONObject.optJSONObject("motionDetectionSwitch");
                            if (optJSONObject2 != null) {
                                AlarmManager.this.j.f7966a = optJSONObject2.optBoolean("detectionSwitch", false);
                                AlarmManager.this.j.e = optJSONObject2.optInt(Constants.Name.INTERVAL, 5);
                                AlarmManager.this.j.i = optJSONObject2.optBoolean("trackSwitch", false);
                                String optString = optJSONObject2.optString("startTime");
                                if (!TextUtils.isEmpty(optString)) {
                                    String b2 = TimeZoneUtils.a().b(optString);
                                    if (!TextUtils.isEmpty(b2)) {
                                        AlarmManager.this.j.b = b2;
                                    } else {
                                        AlarmManager.this.j.b = optString;
                                    }
                                } else {
                                    AlarmManager.this.j.b = "00:00:00";
                                }
                                String optString2 = optJSONObject2.optString("endTime");
                                if (!TextUtils.isEmpty(optString2)) {
                                    String b3 = TimeZoneUtils.a().b(optString2);
                                    if (!TextUtils.isEmpty(b3)) {
                                        AlarmManager.this.j.c = b3;
                                    } else {
                                        AlarmManager.this.j.c = optString2;
                                    }
                                } else {
                                    AlarmManager.this.j.c = "23:59:59";
                                }
                            }
                            AlarmManager.this.j.f = optJSONObject.optBoolean("pedestrianDetectionPushSwitch", false);
                            AlarmManager.this.j.d = optJSONObject.optBoolean("pushSwitch", false);
                            AlarmManager.this.j.g = AlarmManager.this.a(optJSONObject.optJSONArray("sensitive"));
                            AlarmManager.this.j.h = optJSONObject.optLong("upgradeTime", -1);
                            AlarmManager.this.b(String.valueOf(AlarmManager.this.j.h).length() == 10 ? AlarmManager.this.j.h * 1000 : AlarmManager.this.j.h);
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
                PrintStream printStream = System.out;
                printStream.println("mytest:getAlarmConfig i:" + i + " s:" + str);
                SDKLog.d(AlarmManager.b, "getAlarmConfig i:" + i + " s:" + str);
                if (iAlarmCallback != null) {
                    iAlarmCallback.onFailure(i, str);
                }
            }
        });
    }

    public void b(String str, final JSONObject jSONObject, final IAlarmCallback iAlarmCallback) {
        if (!TextUtils.isEmpty(str) && jSONObject != null) {
            LogUtil.a(b, "params 1:" + jSONObject.toString());
            AlarmNetUtils.a().b(str, jSONObject.toString(), new Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        LogUtil.a(AlarmManager.b, "params 2:" + jSONObject.toString());
                        if (jSONObject.optInt("code") == 0) {
                            AlarmManager.this.j.f7966a = jSONObject.optBoolean("open");
                            AlarmManager.this.j.e = jSONObject.optInt(Constants.Name.INTERVAL);
                            AlarmManager.this.j.i = jSONObject.optBoolean("trackSwitch");
                            String optString = jSONObject.optString("startTime");
                            if (!TextUtils.isEmpty(optString)) {
                                String b2 = TimeZoneUtils.a().b(optString);
                                if (!TextUtils.isEmpty(b2)) {
                                    AlarmManager.this.j.b = b2;
                                } else {
                                    AlarmManager.this.j.b = optString;
                                }
                            } else {
                                AlarmManager.this.j.b = "00:00:00";
                            }
                            String optString2 = jSONObject.optString("endTime");
                            if (!TextUtils.isEmpty(optString2)) {
                                String b3 = TimeZoneUtils.a().b(optString2);
                                if (!TextUtils.isEmpty(b3)) {
                                    AlarmManager.this.j.c = b3;
                                } else {
                                    AlarmManager.this.j.c = optString2;
                                }
                            } else {
                                AlarmManager.this.j.c = "23:59:59";
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
                    PrintStream printStream = System.out;
                    printStream.println("mytest:setAlarmConfig i:" + i + " s:" + str);
                    SDKLog.d(AlarmManager.b, "setAlarmConfig i:" + i + " s:" + str);
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
                        SDKLog.b(AlarmManager.b, "jsonObject:" + jSONObject.toString());
                        if (jSONObject.optInt("code") == 0) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("data");
                            if (optJSONObject != null) {
                                AlarmManager.this.j.d = optJSONObject.optBoolean("status");
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
                    SDKLog.d(AlarmManager.b, "getPush i:" + i + " s:" + str);
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
                            AlarmManager.this.j.d = jSONObject.optBoolean("open");
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
                    SDKLog.d(AlarmManager.b, "putPush i:" + i + " s:" + str);
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
                        SDKLog.b(AlarmManager.b, "jsonObject:" + jSONObject.toString());
                        if (jSONObject.optInt("code") == 0) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("data");
                            if (optJSONObject != null) {
                                AlarmManager.this.j.f = optJSONObject.optBoolean("status");
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
                    SDKLog.d(AlarmManager.b, "getHumanDetectionPush i:" + i + " s:" + str);
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
                        SDKLog.b(AlarmManager.b, "jsonObject:" + jSONObject.toString());
                        if (jSONObject.optInt("code") == 0) {
                            AlarmManager.this.j.f = jSONObject.optBoolean("open");
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
                    SDKLog.d(AlarmManager.b, "putHumanDetectionPush i:" + i + " s:" + str);
                    if (iAlarmCallback != null) {
                        iAlarmCallback.onFailure(i, str);
                    }
                }
            });
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

    public void b(long j2) {
        this.k = j2;
    }

    public long g() {
        return this.k;
    }

    /* access modifiers changed from: private */
    public AlarmItem a(JSONObject jSONObject) {
        AlarmItem alarmItem = new AlarmItem();
        alarmItem.m = jSONObject.optLong("duration");
        alarmItem.n = jSONObject.optLong(MibiConstants.eP);
        alarmItem.o = jSONObject.optLong("createTime");
        alarmItem.q = jSONObject.optString("imgStoreId");
        alarmItem.s = jSONObject.optString("fileId");
        alarmItem.u = jSONObject.optString("desc");
        alarmItem.v = jSONObject.optString(CommandMessage.TYPE_TAGS);
        alarmItem.p = jSONObject.optInt("offset");
        alarmItem.t = jSONObject.optString("videoStoreId");
        alarmItem.r = XmPluginHostApi.instance().getCloudImageUrl(this.f7970a.getDid(), alarmItem.s, alarmItem.q);
        alarmItem.l = true;
        alarmItem.b = alarmItem.o;
        return alarmItem;
    }

    private long i() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.n);
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    private long j() {
        return i() + 86400000;
    }

    /* access modifiers changed from: private */
    public List<AlarmItem> a(List<AlarmItem> list) {
        if (list.size() == 0) {
            return list;
        }
        this.n = list.get(0).b;
        if (c(list.get(list.size() - 1).b)) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            AlarmItem alarmItem = list.get(i2);
            if (c(alarmItem.b)) {
                arrayList.add(alarmItem);
            }
        }
        return arrayList;
    }

    public void c(final Callback<Void> callback, final boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.f7970a.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("language", Locale.getDefault().getLanguage());
            if (z) {
                jSONObject.put(Tags.Coupon.BEGIN_TIME, e());
                jSONObject.put("endTime", System.currentTimeMillis());
            } else {
                jSONObject.put(Tags.Coupon.BEGIN_TIME, i());
                jSONObject.put("endTime", this.o);
            }
            jSONObject.put("limit", 20);
            AlarmNetUtils.a().h(this.f7970a.getModel(), jSONObject.toString(), new Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    Log.d("zz", "loadDataNew:" + jSONObject.toString());
                    if (jSONObject != null && jSONObject.optInt("code") == 0 && jSONObject.optJSONObject("data") != null) {
                        JSONArray optJSONArray = jSONObject.optJSONObject("data").optJSONArray("playUnits");
                        List arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                arrayList.add(AlarmManager.this.a(optJSONObject));
                            }
                        }
                        if (z) {
                            arrayList = AlarmManager.this.a((List<AlarmItem>) arrayList);
                            List unused = AlarmManager.this.h = arrayList;
                        } else {
                            List unused2 = AlarmManager.this.h = AlarmManager.this.a((List<AlarmItem>) AlarmManager.this.h, (List<AlarmItem>) arrayList);
                        }
                        if (arrayList.size() == 20) {
                            long unused3 = AlarmManager.this.o = ((AlarmItem) arrayList.get(arrayList.size() - 1)).b - 1;
                            boolean unused4 = AlarmManager.this.f = true;
                            if (callback != null) {
                                callback.onSuccess(null);
                                return;
                            }
                            return;
                        }
                        boolean unused5 = AlarmManager.this.f = false;
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

    public boolean c(long j2) {
        return j2 >= i() && j2 <= j();
    }

    public void a(Context context, AlarmItem alarmItem, final IAlarmCallback<String> iAlarmCallback) {
        if (context != null && alarmItem != null && !TextUtils.isEmpty(alarmItem.s) && !TextUtils.isEmpty(alarmItem.t)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("did", this.f7970a.getDid());
                jSONObject.put("fileId", alarmItem.s);
                jSONObject.put("stoId", alarmItem.t);
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
                        SDKLog.d(AlarmManager.b, "getAlarmFile i:" + i + " s:" + str);
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

    public void b(long j2, long j3, Callback<List<AlarmItem>> callback) {
        if (this.k / 1000 > j3) {
            a(j2, j3, callback);
        } else if (this.k / 1000 < j2) {
            final long j4 = j3;
            final long j5 = j2;
            final Callback<List<AlarmItem>> callback2 = callback;
            new Thread(new Runnable() {
                public void run() {
                    int i = 1;
                    long[] jArr = {j4};
                    jArr[0] = j4;
                    int[] iArr = {0};
                    ArrayList arrayList = new ArrayList();
                    while (true) {
                        CountDownLatch countDownLatch = new CountDownLatch(i);
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("did", AlarmManager.this.f7970a.getDid());
                            jSONObject.put("region", Locale.getDefault().getCountry());
                            jSONObject.put("language", Locale.getDefault().getLanguage());
                            jSONObject.put(Tags.Coupon.BEGIN_TIME, j5 * 1000);
                            jSONObject.put("endTime", jArr[0] * 1000);
                            jSONObject.put("limit", 20);
                            PrintStream printStream = System.out;
                            printStream.println("mytest:getAlarmPlaylistLimit:" + (j5 * 1000) + " " + (jArr[0] * 1000));
                            AlarmNetUtils a2 = AlarmNetUtils.a();
                            String model = AlarmManager.this.f7970a.getModel();
                            final long[] jArr2 = jArr;
                            final int[] iArr2 = iArr;
                            AnonymousClass1 r8 = r1;
                            final ArrayList arrayList2 = arrayList;
                            String jSONObject2 = jSONObject.toString();
                            final CountDownLatch countDownLatch2 = countDownLatch;
                            AnonymousClass1 r1 = new Callback<JSONObject>() {
                                /* renamed from: a */
                                public void onSuccess(JSONObject jSONObject) {
                                    if (jSONObject == null || jSONObject.optInt("code") != 0 || jSONObject.optJSONObject("data") == null) {
                                        iArr2[0] = 0;
                                    } else {
                                        JSONArray optJSONArray = jSONObject.optJSONObject("data").optJSONArray("playUnits");
                                        PrintStream printStream = System.out;
                                        printStream.println("mytest:getAlarmPlaylistLimit:" + optJSONArray.length());
                                        ArrayList arrayList = new ArrayList();
                                        for (int i = 0; i < optJSONArray.length(); i++) {
                                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                            if (optJSONObject != null) {
                                                AlarmItem alarmItem = new AlarmItem();
                                                alarmItem.m = optJSONObject.optLong("duration");
                                                alarmItem.n = optJSONObject.optLong(MibiConstants.eP);
                                                alarmItem.o = optJSONObject.optLong("createTime");
                                                alarmItem.q = optJSONObject.optString("imgStoreId");
                                                alarmItem.s = optJSONObject.optString("fileId");
                                                alarmItem.u = optJSONObject.optString("desc");
                                                alarmItem.v = optJSONObject.optString(CommandMessage.TYPE_TAGS);
                                                alarmItem.p = optJSONObject.optInt("offset");
                                                alarmItem.t = optJSONObject.optString("videoStoreId");
                                                alarmItem.r = XmPluginHostApi.instance().getCloudImageUrl(AlarmManager.this.f7970a.getDid(), alarmItem.s, alarmItem.q);
                                                alarmItem.l = true;
                                                alarmItem.b = alarmItem.o;
                                                arrayList.add(alarmItem);
                                            }
                                        }
                                        if (arrayList.size() == 20) {
                                            jArr2[0] = (((AlarmItem) arrayList.get(arrayList.size() - 1)).b / 1000) - 1;
                                            iArr2[0] = 20;
                                        } else {
                                            iArr2[0] = arrayList.size();
                                        }
                                        arrayList2.addAll(arrayList);
                                    }
                                    countDownLatch2.countDown();
                                }

                                public void onFailure(int i, String str) {
                                    iArr2[0] = -1;
                                    countDownLatch2.countDown();
                                }
                            };
                            a2.h(model, jSONObject2, r8);
                        } catch (JSONException unused) {
                            iArr[0] = -1;
                            countDownLatch.countDown();
                        }
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (iArr[0] != 20) {
                            break;
                        }
                        i = 1;
                    }
                    if (callback2 == null) {
                        return;
                    }
                    if (iArr[0] != -1 || !arrayList.isEmpty()) {
                        callback2.onSuccess(arrayList);
                    } else {
                        callback2.onFailure(-1, "");
                    }
                }
            }).start();
        } else {
            final long j6 = j3;
            final long j7 = j2;
            final Callback<List<AlarmItem>> callback3 = callback;
            new Thread(new Runnable() {
                public void run() {
                    int i = 1;
                    long[] jArr = {j6};
                    jArr[0] = j6;
                    int[] iArr = {0};
                    final ArrayList arrayList = new ArrayList();
                    while (true) {
                        CountDownLatch countDownLatch = new CountDownLatch(i);
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("did", AlarmManager.this.f7970a.getDid());
                            jSONObject.put("region", Locale.getDefault().getCountry());
                            jSONObject.put("language", Locale.getDefault().getLanguage());
                            jSONObject.put(Tags.Coupon.BEGIN_TIME, AlarmManager.this.k);
                            jSONObject.put("endTime", jArr[0] * 1000);
                            jSONObject.put("limit", 20);
                            PrintStream printStream = System.out;
                            printStream.println("mytest:getAlarmPlaylistLimit:" + (j7 * 1000) + " " + (jArr[0] * 1000));
                            AlarmNetUtils a2 = AlarmNetUtils.a();
                            String model = AlarmManager.this.f7970a.getModel();
                            final long[] jArr2 = jArr;
                            final int[] iArr2 = iArr;
                            AnonymousClass1 r8 = r1;
                            final ArrayList arrayList2 = arrayList;
                            String jSONObject2 = jSONObject.toString();
                            final CountDownLatch countDownLatch2 = countDownLatch;
                            AnonymousClass1 r1 = new Callback<JSONObject>() {
                                /* renamed from: a */
                                public void onSuccess(JSONObject jSONObject) {
                                    if (jSONObject == null || jSONObject.optInt("code") != 0 || jSONObject.optJSONObject("data") == null) {
                                        iArr2[0] = 0;
                                    } else {
                                        JSONArray optJSONArray = jSONObject.optJSONObject("data").optJSONArray("playUnits");
                                        PrintStream printStream = System.out;
                                        printStream.println("mytest:getAlarmPlaylistLimit:" + optJSONArray.length());
                                        ArrayList arrayList = new ArrayList();
                                        for (int i = 0; i < optJSONArray.length(); i++) {
                                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                            if (optJSONObject != null) {
                                                AlarmItem alarmItem = new AlarmItem();
                                                alarmItem.m = optJSONObject.optLong("duration");
                                                alarmItem.n = optJSONObject.optLong(MibiConstants.eP);
                                                alarmItem.o = optJSONObject.optLong("createTime");
                                                alarmItem.q = optJSONObject.optString("imgStoreId");
                                                alarmItem.s = optJSONObject.optString("fileId");
                                                alarmItem.u = optJSONObject.optString("desc");
                                                alarmItem.v = optJSONObject.optString(CommandMessage.TYPE_TAGS);
                                                alarmItem.p = optJSONObject.optInt("offset");
                                                alarmItem.t = optJSONObject.optString("videoStoreId");
                                                alarmItem.r = XmPluginHostApi.instance().getCloudImageUrl(AlarmManager.this.f7970a.getDid(), alarmItem.s, alarmItem.q);
                                                alarmItem.l = true;
                                                alarmItem.b = alarmItem.o;
                                                arrayList.add(alarmItem);
                                            }
                                        }
                                        if (arrayList.size() == 20) {
                                            jArr2[0] = (((AlarmItem) arrayList.get(arrayList.size() - 1)).b / 1000) - 1;
                                            iArr2[0] = 20;
                                        } else {
                                            iArr2[0] = arrayList.size();
                                        }
                                        arrayList2.addAll(arrayList);
                                    }
                                    countDownLatch2.countDown();
                                }

                                public void onFailure(int i, String str) {
                                    iArr2[0] = -1;
                                    countDownLatch2.countDown();
                                }
                            };
                            a2.h(model, jSONObject2, r8);
                        } catch (JSONException unused) {
                            iArr[0] = -1;
                            countDownLatch.countDown();
                        }
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (iArr[0] != 20) {
                            new Thread(new Runnable() {
                                public void run() {
                                    final long[] jArr = {j6};
                                    jArr[0] = AlarmManager.this.k;
                                    final int[] iArr = {0};
                                    do {
                                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                                        AlarmManager.this.a(j7, jArr[0], 50, new Callback<JSONArray>() {
                                            /* renamed from: a */
                                            public void onSuccess(JSONArray jSONArray) {
                                                ArrayList arrayList = new ArrayList();
                                                if (jSONArray == null || jSONArray.length() <= 0) {
                                                    iArr[0] = 0;
                                                } else {
                                                    for (int i = 0; i < jSONArray.length(); i++) {
                                                        arrayList.add(AlarmItem.a(jSONArray.optJSONObject(i)));
                                                    }
                                                    if (jSONArray.length() == 50) {
                                                        jArr[0] = (((AlarmItem) arrayList.get(arrayList.size() - 1)).b / 1000) - 1;
                                                        iArr[0] = 50;
                                                    } else {
                                                        iArr[0] = jSONArray.length();
                                                    }
                                                    arrayList.addAll(arrayList);
                                                }
                                                countDownLatch.countDown();
                                            }

                                            public void onFailure(int i, String str) {
                                                iArr[0] = -1;
                                                countDownLatch.countDown();
                                            }
                                        });
                                        try {
                                            countDownLatch.await();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    } while (iArr[0] == 50);
                                    if (callback3 == null) {
                                        return;
                                    }
                                    if (arrayList.isEmpty()) {
                                        callback3.onFailure(-1, "");
                                    } else {
                                        callback3.onSuccess(arrayList);
                                    }
                                }
                            }).start();
                            return;
                        }
                        i = 1;
                    }
                }
            }).start();
        }
    }

    public void c(final Callback<JSONArray> callback) {
        this.f7970a.callMethod("getAlarmSensitivity", new JSONArray(), new Callback<JSONArray>() {
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
                return new JSONObject(str).optJSONArray("result");
            }
        });
    }

    public void a(final Callback<Void> callback, int i2) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(i2);
        LogUtil.a(b, "setAlarmSelectSensitivity array=" + jSONArray.toString());
        this.f7970a.callMethod("setAlarmSensitivity", jSONArray, new Callback<Object>() {
            public void onSuccess(Object obj) {
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

    public void b(final Callback<Void> callback, int i2) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(i2);
        LogUtil.a(b, "setAlarmSelectSensitivity array=" + jSONArray.toString());
        this.f7970a.callMethod("upload_voice", jSONArray, new Callback<Object>() {
            public void onSuccess(Object obj) {
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
}
