package com.xiaomi.smarthome.frame.plugin.runtime.activity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.server.internal.bluetooth.IBluetoothService;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class LockSecurePinUtil {
    public static void checkSecurePinChanged(Activity activity, String str, final String str2) {
        getShowSecurePin(activity.getApplicationContext(), str, new AsyncCallback<Boolean, Error>() {
            public void onFailure(Error error) {
            }

            public void onSuccess(Boolean bool) {
                LockSecurePinUtil.setPropShowPincode(str2, bool.booleanValue());
            }
        });
    }

    public static void checkLtmkChanged(final Activity activity, final String str, final String str2, final boolean z) {
        getEncryptLtmk(activity.getApplicationContext(), str, new AsyncCallback<JSONObject, Error>() {
            public void onFailure(Error error) {
            }

            public void onSuccess(JSONObject jSONObject) {
                int i;
                String str = "";
                if (jSONObject != null) {
                    str = jSONObject.optString("key");
                    i = jSONObject.optInt("encrypt_type");
                } else {
                    i = 0;
                }
                if (!TextUtils.isEmpty(str) && i != 0 && !TextUtils.equals(LockSecurePinUtil.getPropLtmk(str2), str)) {
                    LockSecurePinUtil.setPropLtmk(str2, str);
                    LockSecurePinUtil.setPropLtmkEncryptType(str2, i);
                    LockSecurePinUtil.setPropPincode(str2, "");
                    if (!activity.isFinishing() && !z) {
                        Bundle bundle = new Bundle();
                        bundle.putString("extra_device_did", str);
                        FrameManager.b().k().openVerfyPincode(activity, bundle, 9999);
                    }
                }
            }
        });
    }

    private static void getShowSecurePin(Context context, String str, AsyncCallback<Boolean, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(LtmkEncryptUtil.f14732a);
            jSONObject.put(QuickTimeAtomTypes.h, jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        BluetoothLog.c(String.format("getShowSecurePin: [%s]", new Object[]{jSONObject}));
        Context context2 = context;
        CoreApi.a().a(context2, new NetRequest.Builder().a("POST").b("/v2/device/get_extra_data").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Boolean>() {
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                String str = "1";
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                if (optJSONObject != null) {
                    str = optJSONObject.optString(LtmkEncryptUtil.f14732a, "1");
                }
                return Boolean.valueOf(TextUtils.equals(str, "1"));
            }
        }, Crypto.RC4, asyncCallback);
    }

    private static void getEncryptLtmk(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "own");
            jSONObject.put("did", str);
            jSONObject.put("keyid", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        BluetoothLog.c(String.format("getEncryptLtmk: [%s]", new Object[]{jSONObject}));
        Context context2 = context;
        CoreApi.a().a(context2, new NetRequest.Builder().a("POST").b("/share/askbluetoothkey").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                BluetoothLog.c(String.format("Http Response: [%S]", new Object[]{jSONObject}));
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static String getPropLtmk(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 19, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void setPropLtmk(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("setPropLtmk mac null", new Object[0]));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.V, str2);
        CoreApi.a().b(str, 19, bundle);
    }

    public static String getPropPincode(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 21, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void setPropPincode(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("setPropPincode mac null", new Object[0]));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.V, str2);
        CoreApi.a().b(str, 21, bundle);
    }

    public static void setPropLtmkEncryptType(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("setPropLtmkEncryptType mac null", new Object[0]));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(IBluetoothService.V, i);
        CoreApi.a().b(str, 22, bundle);
    }

    public static boolean getPropShowPincode(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 23, bundle);
        return bundle.getBoolean("extra.result", true);
    }

    public static void setPropShowPincode(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("setPropShowPincode mac null", new Object[0]));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(IBluetoothService.V, z);
        CoreApi.a().b(str, 23, bundle);
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
