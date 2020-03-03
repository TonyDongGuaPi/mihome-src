package com.mijia.model.alarmcloud;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mi.global.shop.model.Tags;
import com.mijia.debug.SDKLog;
import com.mijia.model.alarm.AlarmNetUtils;
import com.taobao.weex.common.Constants;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmCloudManager {
    public static final String AI = "AI";
    public static final String BABY_CRY = "BabyCry";
    public static final String DEFAULT = "Default";
    public static final String FACE = "Face";
    public static final String KNOWN_FACE = "KnownFace";
    public static final String OBJECT_MOTION = "ObjectMotion";
    public static final String PEOPLE_MOTION = "PeopleMotion";
    private static final String TAG = "AlarmCloudManager";
    public static Gson gGson = new Gson();
    /* access modifiers changed from: private */
    public CameraDevice mCameraDevice;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EventType {
    }

    public String getVideoSnapshotUrl(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put(Constants.Name.PREFIX, "processor.smartcamera.");
            jSONObject.put("method", "GET");
            jSONObject.put("path", AlarmNetUtils.M);
            jSONObject2.put("did", str2);
            jSONObject2.put("stoId", str3);
            return XmPluginHostApi.instance().generateRequestUrl(str, jSONObject, jSONObject2);
        } catch (JSONException unused) {
            return null;
        }
    }

    public AlarmCloudManager(CameraDevice cameraDevice) {
        this.mCameraDevice = cameraDevice;
    }

    public void getEventList(String str, long j, long j2, AlarmCloudCallback<ArrayList<AlarmVideo>> alarmCloudCallback) {
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("model", this.mCameraDevice.getModel());
            String str2 = str;
            jSONObject.put("eventType", str);
            long j3 = j;
            jSONObject.put(Tags.Coupon.BEGIN_TIME, j);
            jSONObject.put("endTime", j2);
            jSONObject.put("limit", 40);
            jSONObject.put("needMerge", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final AlarmCloudCallback<ArrayList<AlarmVideo>> alarmCloudCallback2 = alarmCloudCallback;
        XmPluginHostApi.instance().callSmartHomeApi(this.mCameraDevice.getModel(), "business.smartcamera.", AlarmNetUtils.F, "GET", jSONObject, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                try {
                    LogUtil.a(AlarmCloudManager.TAG, "getEventList success " + jSONObject.toString());
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    final boolean z = jSONObject2.getBoolean("isContinue");
                    final long j = jSONObject2.getLong("nextTime");
                    final ArrayList arrayList = (ArrayList) AlarmCloudManager.gGson.fromJson(jSONObject2.getJSONArray("thirdPartPlayUnits").toString(), new TypeToken<ArrayList<AlarmVideo>>() {
                    }.getType());
                    ArrayList arrayList2 = new ArrayList();
                    for (int i = 0; i < arrayList.size(); i++) {
                        AlarmVideo alarmVideo = (AlarmVideo) arrayList.get(i);
                        alarmVideo.imgStoreUrl = AlarmCloudManager.this.getVideoSnapshotUrl(AlarmCloudManager.this.mCameraDevice.getModel(), AlarmCloudManager.this.mCameraDevice.getDid(), alarmVideo.imgStoreId);
                        if (!arrayList2.contains(alarmVideo.fileId)) {
                            arrayList2.add(alarmVideo.fileId);
                        }
                    }
                    AlarmCloudManager.this.getFaceIdsByFiledId(new Callback() {
                        public void onSuccess(Object obj) {
                            JSONObject jSONObject = (JSONObject) obj;
                            LogUtil.a(AlarmCloudManager.TAG, "getFaceIdsByFiledId Success " + jSONObject.toString());
                            try {
                                ArrayList arrayList = (ArrayList) AlarmCloudManager.gGson.fromJson(jSONObject.getJSONObject("data").getJSONArray("fileIdMetaResults").toString(), new TypeToken<ArrayList<FileIdMetaResult>>() {
                                }.getType());
                                if (arrayList.size() > 0) {
                                    for (int i = 0; i < arrayList.size(); i++) {
                                        FileIdMetaResult fileIdMetaResult = (FileIdMetaResult) arrayList.get(i);
                                        for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                            AlarmVideo alarmVideo = (AlarmVideo) arrayList.get(i2);
                                            if (alarmVideo.fileId.equals(fileIdMetaResult.fileId) && alarmVideo.offset == fileIdMetaResult.offset) {
                                                alarmVideo.fileIdMetaResult = fileIdMetaResult;
                                            }
                                        }
                                    }
                                }
                                if (alarmCloudCallback2 != null) {
                                    alarmCloudCallback2.onSuccess(arrayList, j, z);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                if (alarmCloudCallback2 != null) {
                                    alarmCloudCallback2.onSuccess(arrayList, j, z);
                                }
                            }
                        }

                        public void onFailure(int i, String str) {
                            LogUtil.a(AlarmCloudManager.TAG, "getFaceIdsByFiledId fail errorCode = " + i + "errorMsg = " + str);
                            if (alarmCloudCallback2 != null) {
                                alarmCloudCallback2.onSuccess(arrayList, j, z);
                            }
                        }
                    }, arrayList2.toArray());
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (alarmCloudCallback2 != null) {
                        alarmCloudCallback2.onFailure(-1, "json error");
                    }
                }
            }

            public void onFailure(int i, String str) {
                LogUtil.a(AlarmCloudManager.TAG, "getEventList fail errorCode = " + i + "errorMsg = " + str);
                alarmCloudCallback2.onFailure(i, str);
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void getEventListByFileId(final String str, boolean z, final Callback<ArrayList<AlarmVideo>> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("model", this.mCameraDevice.getModel());
            jSONObject.put("fileId", str);
            jSONObject.put("isAlarm", z);
            jSONObject.put("limit", 40);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        XmPluginHostApi.instance().callSmartHomeApi(this.mCameraDevice.getModel(), "business.smartcamera.", AlarmNetUtils.G, "GET", jSONObject, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                try {
                    LogUtil.a(AlarmCloudManager.TAG, "getEventListByFileId success " + jSONObject.toString());
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    jSONObject2.getBoolean("isContinue");
                    jSONObject2.getLong("nextTime");
                    final ArrayList arrayList = (ArrayList) AlarmCloudManager.gGson.fromJson(jSONObject2.getJSONArray("thirdPartPlayUnits").toString(), new TypeToken<ArrayList<AlarmVideo>>() {
                    }.getType());
                    AlarmCloudManager.this.getFaceIdsByFiledId(new Callback() {
                        public void onSuccess(Object obj) {
                            JSONObject jSONObject = (JSONObject) obj;
                            LogUtil.a(AlarmCloudManager.TAG, "getFaceIdsByFiledId Success " + jSONObject.toString());
                            try {
                                ArrayList arrayList = (ArrayList) AlarmCloudManager.gGson.fromJson(jSONObject.getJSONObject("data").getJSONArray("fileIdMetaResults").toString(), new TypeToken<ArrayList<FileIdMetaResult>>() {
                                }.getType());
                                if (arrayList.size() > 0) {
                                    for (int i = 0; i < arrayList.size(); i++) {
                                        FileIdMetaResult fileIdMetaResult = (FileIdMetaResult) arrayList.get(i);
                                        for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                            AlarmVideo alarmVideo = (AlarmVideo) arrayList.get(i2);
                                            if (alarmVideo.offset == fileIdMetaResult.offset) {
                                                alarmVideo.fileIdMetaResult = fileIdMetaResult;
                                            }
                                        }
                                    }
                                }
                                if (callback != null) {
                                    callback.onSuccess(arrayList);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                if (callback != null) {
                                    callback.onSuccess(arrayList);
                                }
                            }
                        }

                        public void onFailure(int i, String str) {
                            LogUtil.a(AlarmCloudManager.TAG, "getFaceIdsByFiledId fail errorCode = " + i + "errorMsg = " + str);
                            if (callback != null) {
                                callback.onSuccess(arrayList);
                            }
                        }
                    }, str);
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFailure(-1, "json error");
                    }
                }
            }

            public void onFailure(int i, String str) {
                LogUtil.a(AlarmCloudManager.TAG, "getEventListByFileId onFailure " + i);
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void markEvent(String str, int i, final Callback<Boolean> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("fileId", str);
            jSONObject.put("offset", i);
        } catch (Exception e) {
            SDKLog.d(TAG, e.toString());
        }
        XmPluginHostApi.instance().callSmartHomeApi(this.mCameraDevice.getModel(), "business.smartcamera.", AlarmNetUtils.H, "POST", jSONObject.toString(), new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                try {
                    LogUtil.a(AlarmCloudManager.TAG, "mark event read success " + jSONObject.toString());
                    String string = jSONObject.getString("result");
                    if (TextUtils.isEmpty(string)) {
                        return;
                    }
                    if (string.equals("ok")) {
                        if (callback != null) {
                            callback.onSuccess(true);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(int i, String str) {
                LogUtil.a(AlarmCloudManager.TAG, "getEventListByFileId onFailure " + i);
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void getFaceIdsByFiledId(final Callback callback, Object... objArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("model", this.mCameraDevice.getModel());
            JSONArray jSONArray = new JSONArray();
            for (Object put : objArr) {
                jSONArray.put(put);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("fileIds", jSONArray);
            jSONObject.put("fileIds", jSONObject2);
        } catch (Exception e) {
            SDKLog.d(TAG, e.toString());
        }
        String jSONObject3 = jSONObject.toString();
        SDKLog.c(TAG, "getFaceIdsByFiledId request params=" + jSONObject3);
        XmPluginHostApi.instance().callSmartHomeApi(this.mCameraDevice.getModel(), "business.smartcamera.", AlarmNetUtils.B, "GET", jSONObject3, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                SDKLog.c(AlarmCloudManager.TAG, "onSuccess_getFaceIdsByFiledId=" + jSONObject.toString());
                if (callback != null) {
                    callback.onSuccess(jSONObject);
                }
            }

            public void onFailure(int i, String str) {
                SDKLog.d(AlarmCloudManager.TAG, "onFailure_getFaceIdsByFiledId code=" + i + " msg=" + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void deleteFiles(final Callback callback, boolean z, Object... objArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("model", this.mCameraDevice.getModel());
            JSONArray jSONArray = new JSONArray();
            for (Object put : objArr) {
                jSONArray.put(put);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("fileIds", jSONArray);
            jSONObject.put("fileIds", jSONObject2);
        } catch (Exception e) {
            LogUtil.b(TAG, e.toString());
        }
        String jSONObject3 = jSONObject.toString();
        LogUtil.a(TAG, "deleteFiles request params=" + jSONObject3);
        XmPluginHostApi.instance().callSmartHomeApi(this.mCameraDevice.getModel(), "business.smartcamera.", AlarmNetUtils.J, "POST", jSONObject3, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                SDKLog.c(AlarmCloudManager.TAG, "onSuccess_deleteFiles=" + jSONObject.toString());
                if (callback != null) {
                    callback.onSuccess(jSONObject);
                }
            }

            public void onFailure(int i, String str) {
                SDKLog.d(AlarmCloudManager.TAG, "onFailure_deleteFiles code=" + i + " msg=" + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void feedBack(String str, boolean z, final Callback callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("model", this.mCameraDevice.getModel());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("fileId", str);
            jSONObject.put("isVisible", z);
            jSONObject.put("type", "face");
        } catch (Exception e) {
            SDKLog.d(TAG, e.toString());
        }
        String jSONObject2 = jSONObject.toString();
        SDKLog.c(TAG, "feedBack request params=" + jSONObject2);
        XmPluginHostApi.instance().callSmartHomeApi(this.mCameraDevice.getModel(), "business.smartcamera.", AlarmNetUtils.C, "POST", jSONObject2, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                SDKLog.c(AlarmCloudManager.TAG, "onSuccess_feedBack=" + jSONObject.toString());
                if (callback != null) {
                    callback.onSuccess(jSONObject);
                }
            }

            public void onFailure(int i, String str) {
                SDKLog.d(AlarmCloudManager.TAG, "onFailure_feedBack code=" + i + " msg=" + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }
}
