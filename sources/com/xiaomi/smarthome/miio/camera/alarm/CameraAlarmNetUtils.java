package com.xiaomi.smarthome.miio.camera.alarm;

import android.text.TextUtils;
import com.mijia.model.alarm.AlarmNetUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraAlarmNetUtils {
    public static final int FAIL_GENERAL = -80001;
    public static final int FAIL_IN_CODE = -80003;
    public static final int FAIL_IN_SUCCESS = -80002;
    private static CameraAlarmNetUtils instance;

    private CameraAlarmNetUtils() {
    }

    public static CameraAlarmNetUtils getInstance() {
        if (instance == null) {
            synchronized (CameraAlarmNetUtils.class) {
                if (instance == null) {
                    instance = new CameraAlarmNetUtils();
                }
            }
        }
        return instance;
    }

    public void getAlarmStatus(String str, final ICameraAlarmCallback<JSONObject> iCameraAlarmCallback) {
        if (!TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().c("business.smartcamera.").a("GET").b(AlarmNetUtils.k).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCameraAlarmCallback == null) {
                        return;
                    }
                    if (jSONObject != null) {
                        iCameraAlarmCallback.onSuccess(jSONObject, (Object) null);
                    } else {
                        iCameraAlarmCallback.onFailure(CameraAlarmNetUtils.FAIL_IN_SUCCESS, "result is null");
                    }
                }

                public void onFailure(Error error) {
                    if (iCameraAlarmCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICameraAlarmCallback iCameraAlarmCallback = iCameraAlarmCallback;
                        int a2 = error.a();
                        iCameraAlarmCallback.onFailure(a2, "" + error.b());
                        return;
                    }
                    iCameraAlarmCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "FAIL_GENERAL");
                }
            });
        } else if (iCameraAlarmCallback != null) {
            iCameraAlarmCallback.onFailure(FAIL_GENERAL, "params is empty");
        }
    }

    public void getAlarmList(String str, final ICameraAlarmCallback<JSONObject> iCameraAlarmCallback) {
        if (!TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().c("business.smartcamera.").a("GET").b(AlarmNetUtils.s).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCameraAlarmCallback == null) {
                        return;
                    }
                    if (jSONObject != null) {
                        iCameraAlarmCallback.onSuccess(jSONObject, (Object) null);
                    } else {
                        iCameraAlarmCallback.onFailure(CameraAlarmNetUtils.FAIL_IN_SUCCESS, "result is null");
                    }
                }

                public void onFailure(Error error) {
                    if (iCameraAlarmCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICameraAlarmCallback iCameraAlarmCallback = iCameraAlarmCallback;
                        int a2 = error.a();
                        iCameraAlarmCallback.onFailure(a2, "" + error.b());
                        return;
                    }
                    iCameraAlarmCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "FAIL_GENERAL");
                }
            });
        } else if (iCameraAlarmCallback != null) {
            iCameraAlarmCallback.onFailure(FAIL_GENERAL, "params is empty");
        }
    }
}
