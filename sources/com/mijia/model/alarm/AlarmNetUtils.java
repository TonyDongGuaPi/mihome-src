package com.mijia.model.alarm;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import com.mijia.debug.SDKLog;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.ICloudDataCallback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmNetUtils {
    public static final String A = "/v2/device/check_device_version";
    public static final String B = "/miot/camera/app/v1/get/fileIdMetas";
    public static final String C = "/miot/camera/app/v1/feedback";
    public static final String D = "/miot/camera/app/v1/delete/face";
    public static final String E = "/common/app/get/cloudlist";
    public static final String F = "/common/app/get/eventlist";
    public static final String G = "/common/app/get/fileIdEvents";
    public static final String H = "/common/app/markRead";
    public static final String I = "/common/app/m3u8";
    public static final String J = "/common/app/v2/delete/files";
    public static final String K = "/common/app/get/playlist";
    public static final String L = "/common/app/get/fileIdinfo";
    public static final String M = "/common/app/v1/img";
    public static final String N = "/user/del_user_device_data";
    public static final String O = "/common/app/add/figure";
    public static final String P = "/common/app/get/faceIds";
    public static final String Q = "/common/app/add/face";
    public static final String R = "/common/app/get/figures";
    public static final String S = "/common/app/delete/figure";
    public static final String T = "/common/app/get/face/img";
    public static final String U = "/common/app/faces/search";
    public static final String V = "/common/app/add/face";
    public static final String W = "/common/app/delete/face";
    public static final String X = "/common/app/get/faces";
    public static final boolean Y = true;
    private static final String Z = "AlarmNetUtils";

    /* renamed from: a  reason: collision with root package name */
    public static final String f8019a = "https://";
    private static AlarmNetUtils aa = null;
    private static boolean ab = false;
    private static boolean ac = false;
    public static final String b = "api.io.mi.com";
    public static final String c = "business.smartcamera.";
    public static final String d = "processor.smartcamera.";
    public static final String e = "GET";
    public static final String f = "POST";
    public static final int g = -90001;
    public static final int h = -90002;
    public static final int i = -90003;
    public static final String j = "/miot/camera/app/v1/vip/status";
    public static final String k = "/miot/camera/app/v1/get/alarmSwitch";
    public static final String l = "/miot/camera/app/v1/put/motionDetectionSwitch";
    public static final String m = "/miot/camera/app/v2/put/motionDetectionSwitch";
    public static final String n = "/miot/camera/app/v1/put/pushSwitch";
    public static final String o = "/miot/camera/app/v1/get/pushSwitch";
    public static final String p = "/miot/camera/app/v1/put/pedestrianDetectionPushSwitch";
    public static final String q = "/miot/camera/app/v1/get/pedestrianDetectionPushSwitch";
    public static final String r = "/miot/camera/app/v1/put/sensitive";
    public static final String s = "/miot/camera/app/v1/alarm/playlist/limit";
    public static final String t = "/miot/camera/app/v1/alarm/videoStoreId";
    public static final String u = "/miot/camera/app/v1/put/babyCryPushSwitch";
    public static final String v = "/miot/camera/app/v1/put/aiPushSwitch";
    public static final String w = "/miot/camera/app/v1/put/areaChangePushSwitch";
    public static final String x = "/miot/camera/app/v1/put/faceSwitch";
    public static final String y = "/miot/camera/app/v1/put/babyCrySwitch";
    public static final String z = "/miot/camera/app/v1/tags";

    public interface MiDownloadNewListener {
        void onComplete();

        void onError(int i, String str);

        void onProgress(int i);

        void onStart();
    }

    public static boolean b() {
        return true;
    }

    private AlarmNetUtils() {
    }

    public static AlarmNetUtils a() {
        if (aa == null) {
            synchronized (AlarmNetUtils.class) {
                if (aa == null) {
                    aa = new AlarmNetUtils();
                }
            }
        }
        return aa;
    }

    public void a(String str, String str2, final Callback<JSONObject> callback) {
        if (!TextUtils.isEmpty(str)) {
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", k, "GET", str2, new Callback<JSONObject>() {
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
            }, Parser.DEFAULT_PARSER);
        } else if (callback != null) {
            callback.onFailure(-90001, "invalid params");
        }
    }

    public void b(String str, String str2, final Callback<JSONObject> callback) {
        String str3 = m;
        if (!TextUtils.isEmpty(str)) {
            if (str.equals("chuangmi.camera.ipc009") || str.equals("chuangmi.camera.ipc019") || str.equals("mijia.camera.v3") || str.equals(DeviceConstant.MIJIA_CAMERA_V3_UPGRADE)) {
                str3 = l;
            }
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", str3, "POST", str2, new Callback<JSONObject>() {
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
            }, Parser.DEFAULT_PARSER);
        }
    }

    public void c(String str, String str2, final Callback<JSONObject> callback) {
        SDKLog.b(Z, "level:" + XmPluginHostApi.instance().getApiLevel());
        if (!TextUtils.isEmpty(str)) {
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", o, "GET", str2, new Callback<JSONObject>() {
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
            }, Parser.DEFAULT_PARSER);
        }
    }

    public void d(String str, String str2, final Callback<JSONObject> callback) {
        SDKLog.b(Z, "level:" + XmPluginHostApi.instance().getApiLevel());
        if (!TextUtils.isEmpty(str)) {
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", n, "POST", str2, new Callback<JSONObject>() {
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
            }, Parser.DEFAULT_PARSER);
        }
    }

    public void e(String str, String str2, final Callback<JSONObject> callback) {
        SDKLog.b(Z, "level:" + XmPluginHostApi.instance().getApiLevel());
        if (!TextUtils.isEmpty(str)) {
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", q, "GET", str2, new Callback<JSONObject>() {
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
            }, Parser.DEFAULT_PARSER);
        }
    }

    public void f(String str, String str2, final Callback<JSONObject> callback) {
        SDKLog.b(Z, "level:" + XmPluginHostApi.instance().getApiLevel());
        if (!TextUtils.isEmpty(str)) {
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", p, "POST", str2, new Callback<JSONObject>() {
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
            }, Parser.DEFAULT_PARSER);
        }
    }

    public void g(String str, String str2, final Callback<JSONObject> callback) {
        SDKLog.b(Z, "level:" + XmPluginHostApi.instance().getApiLevel());
        if (!TextUtils.isEmpty(str)) {
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", r, "POST", str2, new Callback<JSONObject>() {
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
            }, Parser.DEFAULT_PARSER);
        }
    }

    public void h(String str, String str2, final Callback<JSONObject> callback) {
        if (!TextUtils.isEmpty(str)) {
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", s, "GET", str2, new Callback<JSONObject>() {
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
            }, Parser.DEFAULT_PARSER);
        }
    }

    public void i(String str, String str2, final Callback<JSONObject> callback) {
        if (!TextUtils.isEmpty(str)) {
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", E, "GET", str2, new Callback<JSONObject>() {
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
            }, Parser.DEFAULT_PARSER);
        }
    }

    public void j(String str, String str2, final Callback<JSONObject> callback) {
        if (!TextUtils.isEmpty(str)) {
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", J, "POST", str2, new Callback<JSONObject>() {
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
            }, Parser.DEFAULT_PARSER);
        }
    }

    public void k(String str, String str2, final Callback<JSONObject> callback) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", t, "GET", str2, new Callback<JSONObject>() {
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
            }, Parser.DEFAULT_PARSER);
        }
    }

    public boolean a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            if (new File(context.getApplicationContext().getCacheDir() + "/" + str + ".mp4").exists()) {
                return true;
            }
        }
        return false;
    }

    public static boolean c() {
        return "cn".equals(XmPluginHostApi.instance().getGlobalSettingServer());
    }

    public static void l(String str, String str2, final Callback<JSONObject> callback) {
        if (e() && !TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("did", str);
                jSONObject.put("region", Locale.getDefault().getCountry());
                XmPluginHostApi.instance().callSmartHomeApi(str2, "business.smartcamera.", "/miot/camera/app/v1/vip/status", "GET", jSONObject.toString(), new Callback<JSONObject>() {
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
                }, Parser.DEFAULT_PARSER);
            } catch (JSONException unused) {
            }
        }
    }

    public static void a(boolean z2) {
        ab = z2;
    }

    public static boolean d() {
        return ab;
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        if (TextUtils.equals(str, str2)) {
            return true;
        }
        Pattern compile = Pattern.compile("[._]+");
        String[] split = compile.split(str);
        String[] split2 = compile.split(str2);
        int min = Math.min(split.length, split2.length);
        for (int i2 = 0; i2 < min; i2++) {
            if (split[i2].compareTo(split2[i2]) > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean e() {
        return ac;
    }

    public static void b(boolean z2) {
        ac = z2;
    }

    public String a(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put(Constants.Name.PREFIX, "business.smartcamera.");
            jSONObject.put("method", "GET");
            jSONObject.put("path", I);
            jSONObject2.put("did", str2);
            jSONObject2.put("fileId", str3);
            return XmPluginHostApi.instance().generateRequestUrl(str, jSONObject, jSONObject2);
        } catch (JSONException unused) {
            return null;
        }
    }

    public void a(final String str, String str2, String str3, final String str4, final MiDownloadNewListener miDownloadNewListener) {
        XmPluginHostApi.instance().getCloudVideoFile(str2, str3, a(str, str2, str3), new ICloudDataCallback() {
            public void onCloudDataSuccess(Object obj, Object obj2) {
                final ArrayList arrayList = (ArrayList) obj;
                new AsyncTask<Void, Void, Integer>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public Integer doInBackground(Void... voidArr) {
                        String str = "";
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            str = str + "file '" + ((String) it.next()) + "'\r\n";
                        }
                        File file = new File(AlarmNetUtils.a("/CEN") + "fileList.txt");
                        if (!file.exists()) {
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                            bufferedOutputStream.write(str.getBytes());
                            bufferedOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        String str2 = "ffmpeg -f concat -safe 0 -i " + file.getAbsolutePath() + " -c copy " + str4;
                        int videoConverter = XmPluginHostApi.instance().videoConverter(str, str2);
                        LogUtil.a("AlarmV2Activity", "cmd===" + str2 + ",resutl=" + videoConverter);
                        return Integer.valueOf(videoConverter);
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(Integer num) {
                        super.onPostExecute(num);
                        if (num.intValue() == 0) {
                            miDownloadNewListener.onComplete();
                        } else {
                            miDownloadNewListener.onError(num.intValue(), "yd ffmpeg  failure");
                        }
                    }
                }.execute(new Void[0]);
            }

            public void onCloudDataFailed(int i, String str) {
                miDownloadNewListener.onError(i, str);
            }

            public void onCloudDataProgress(int i) {
                miDownloadNewListener.onProgress(i);
            }
        });
    }

    public static String a(String str) {
        if (!f()) {
            return "";
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File file = new File(externalStorageDirectory.toString() + str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + "/";
    }

    public static boolean f() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public void m(String str, String str2, final Callback<JSONObject> callback) {
        if (!TextUtils.isEmpty(str)) {
            XmPluginHostApi.instance().callSmartHomeApi(str, "business.smartcamera.", J, "POST", str2, new Callback<JSONObject>() {
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
            }, Parser.DEFAULT_PARSER);
        }
    }
}
