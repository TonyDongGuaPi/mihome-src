package com.xiaomi.mistream;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MIStreamStatistic {
    private static final String TAG = "MIStreamStatistic";
    private static MIStreamStatistic _instance;
    public long latestCameraConnect = -1;
    public long latestLiveVideo = -1;

    private MIStreamStatistic() {
    }

    public static MIStreamStatistic getInstance() {
        if (_instance == null) {
            synchronized (MIStreamStatistic.class) {
                if (_instance == null) {
                    _instance = new MIStreamStatistic();
                }
            }
        }
        return _instance;
    }

    public void sendCameraConnectDuration(String str, String str2, long j) throws Exception {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && j > 0 && j <= 50000) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            String str3 = CommonApplication.getAppContext().getPackageManager().getPackageInfo(CommonApplication.getAppContext().getPackageName(), 0).versionName;
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("sdkType", "android_app");
            jSONObject2.put("model", str);
            jSONObject2.put("did", str2);
            jSONObject2.put("version", "" + str3);
            jSONObject.put(TtmlNode.TAG_HEAD, jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("gaugeType", "displayTimeDelay");
            jSONObject3.put("duration", j);
            jSONArray.put(jSONObject3);
            jSONObject.put("gauge", jSONArray);
            LogUtil.a(TAG, "sendCameraConnectDuration:" + jSONObject);
            sendStatisticRequest(str, str2, "POST", jSONObject, new Callback() {
                public void onSuccess(Object obj) {
                    MIStreamStatistic.this.latestCameraConnect = -1;
                    LogUtil.a(MIStreamStatistic.TAG, "sendCameraConnectDuration result:" + obj);
                }

                public void onFailure(int i, String str) {
                    MIStreamStatistic.this.latestCameraConnect = -1;
                    LogUtil.b(MIStreamStatistic.TAG, "sendCameraConnectDuration error:" + i + "" + str);
                }
            });
            this.latestCameraConnect = -1;
        }
    }

    public void sendCameraWatchDuration(String str, String str2, long j) throws Exception {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && j > 0) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            String str3 = CommonApplication.getAppContext().getPackageManager().getPackageInfo(CommonApplication.getAppContext().getPackageName(), 0).versionName;
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("sdkType", "android_app");
            jSONObject2.put("model", str);
            jSONObject2.put("did", str2);
            jSONObject2.put("version", str3);
            jSONObject.put(TtmlNode.TAG_HEAD, jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("gaugeType", "timePerViewer");
            jSONObject3.put("duration", j);
            jSONArray.put(jSONObject3);
            jSONObject.put("gauge", jSONArray);
            LogUtil.a(TAG, "sendCameraWatchDuration:" + jSONObject);
            sendStatisticRequest(str, str2, "POST", jSONObject, new Callback() {
                public void onSuccess(Object obj) {
                    MIStreamStatistic.this.latestLiveVideo = -1;
                    LogUtil.a(MIStreamStatistic.TAG, "sendCameraWatchDuration result:" + obj);
                }

                public void onFailure(int i, String str) {
                    MIStreamStatistic.this.latestLiveVideo = -1;
                    LogUtil.b(MIStreamStatistic.TAG, "sendCameraWatchDuration error:" + i + "" + str);
                }
            });
            this.latestLiveVideo = -1;
        }
    }

    public void sendCameraConnection(String str, String str2, String str3) throws Exception {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("sdkType", "android_app");
            jSONObject2.put("model", str);
            jSONObject2.put("did", str2);
            jSONObject.put(TtmlNode.TAG_HEAD, jSONObject2);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("retryCount", 0);
            JSONObject jSONObject4 = new JSONObject();
            for (String split : str3.split(a.b)) {
                String[] split2 = split.split("=");
                if (!split2[0].equals(MIStream.STATISTIC_CAMERA_CONNECTIONS)) {
                    if (split2[0].equals("cost")) {
                        jSONObject3.put("cost", Long.valueOf(split2[1]));
                    } else if (split2[0].equals("code")) {
                        jSONObject3.put("code", Long.valueOf(split2[1]));
                    } else if (canParseInt(split2[1])) {
                        try {
                            jSONObject4.put(split2[0], Long.valueOf(split2[1]));
                        } catch (NumberFormatException e) {
                            LogUtil.b(TAG, "NumberFormatException:" + e.getLocalizedMessage());
                        }
                    } else {
                        jSONObject4.put(split2[0], split2[1]);
                    }
                }
            }
            jSONObject3.put("common", jSONObject4);
            jSONArray.put(jSONObject3);
            jSONObject.put(MIStream.STATISTIC_CAMERA_CONNECTIONS, jSONArray);
            sendStatisticRequest(str, str2, "POST", jSONObject, new Callback() {
                public void onSuccess(Object obj) {
                    LogUtil.a(MIStreamStatistic.TAG, "sendCameraConnection result:" + obj);
                }

                public void onFailure(int i, String str) {
                    LogUtil.b(MIStreamStatistic.TAG, "sendCameraConnection error:" + i + "" + str);
                }
            });
        }
    }

    public void sendFirstFrame(String str, String str2, String str3) throws Exception {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("sdkType", "android_app");
            jSONObject2.put("model", str);
            jSONObject2.put("did", str2);
            jSONObject.put(TtmlNode.TAG_HEAD, jSONObject2);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("code", 0);
            JSONObject jSONObject4 = new JSONObject();
            for (String split : str3.split(a.b)) {
                String[] split2 = split.split("=");
                if (!split2[0].equals(MIStream.STATISTIC_FIRST_FRAMES)) {
                    if (split2[0].equals("cost")) {
                        jSONObject3.put("cost", Long.valueOf(split2[1]));
                    } else if (canParseInt(split2[1])) {
                        try {
                            jSONObject4.put(split2[0], Long.valueOf(split2[1]));
                        } catch (NumberFormatException e) {
                            LogUtil.b(TAG, "NumberFormatException:" + e.getLocalizedMessage());
                        }
                    } else {
                        jSONObject4.put(split2[0], split2[1]);
                    }
                }
            }
            jSONObject3.put("common", jSONObject4);
            jSONArray.put(jSONObject3);
            jSONObject.put(MIStream.STATISTIC_FIRST_FRAMES, jSONArray);
            sendStatisticRequest(str, str2, "POST", jSONObject, new Callback() {
                public void onSuccess(Object obj) {
                    LogUtil.a(MIStreamStatistic.TAG, "sendFirstFrame result:" + obj);
                }

                public void onFailure(int i, String str) {
                    LogUtil.b(MIStreamStatistic.TAG, "sendFirstFrame error:" + i + "" + str);
                }
            });
        }
    }

    public void sendErrorCode(String str, String str2, String str3) throws Exception {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("sdkType", "android_app");
            jSONObject2.put("model", str);
            jSONObject2.put("did", str2);
            jSONObject.put(TtmlNode.TAG_HEAD, jSONObject2);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("code", 0);
            jSONObject3.put("cost", 0);
            JSONObject jSONObject4 = new JSONObject();
            for (String split : str3.split(a.b)) {
                String[] split2 = split.split("=");
                if (!split2[0].equals("errorCode")) {
                    if (canParseInt(split2[1])) {
                        try {
                            jSONObject4.put(split2[0], Long.valueOf(split2[1]));
                        } catch (NumberFormatException e) {
                            LogUtil.b(TAG, "sendErrorCode NumberFormatException:" + e.getLocalizedMessage());
                        }
                    } else {
                        jSONObject4.put(split2[0], split2[1]);
                    }
                }
            }
            jSONObject3.put("common", jSONObject4);
            jSONArray.put(jSONObject3);
            jSONObject.put("errorCodes", jSONArray);
            sendStatisticRequest(str, str2, "POST", jSONObject, new Callback() {
                public void onSuccess(Object obj) {
                    LogUtil.a(MIStreamStatistic.TAG, "sendErrorCode result:" + obj);
                }

                public void onFailure(int i, String str) {
                    LogUtil.b(MIStreamStatistic.TAG, "sendErrorCode error:" + i + "" + str);
                }
            });
        }
    }

    public void sendStatisticRequest(String str, String str2, String str3, JSONObject jSONObject, Callback callback) {
        if (CoreApi.a().l() && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && jSONObject != null) {
            LogUtil.a(TAG, "model:" + str + " method:" + str3 + " params:" + jSONObject.toString());
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("dataType", "EventData");
                jSONObject2.put("data", jSONObject);
                jSONObject2.put("did", str2);
            } catch (JSONException unused) {
            }
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", "/perf/camera/app/v1/data", str3, jSONObject2, callback, Parser.DEFAULT_PARSER);
        } else if (callback != null) {
            callback.onFailure(-1001, "params invalid");
        }
    }

    private boolean canParseInt(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("\\d+");
    }
}
