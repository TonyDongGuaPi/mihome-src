package com.xiaomi.smarthome.homeroom;

import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeviceInitChecker {

    /* renamed from: a  reason: collision with root package name */
    public static List<String> f17939a = new ArrayList();

    public static String a() {
        try {
            return SHApplication.getAppContext().getResources().getQuantityString(R.plurals.device_init_bubble_tips, f17939a.size(), new Object[]{Integer.valueOf(f17939a.size())});
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int b() {
        List<String> list = f17939a;
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (SmartHomeDeviceManager.a().b(list.get(i2)) != null) {
                i++;
            }
        }
        return i;
    }

    public static Device a(String str) {
        Device b;
        List<String> list = f17939a;
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            String str2 = list.get(i);
            if (!TextUtils.equals(str2, str) && (b = SmartHomeDeviceManager.a().b(str2)) != null) {
                return b;
            }
        }
        return null;
    }

    public static void a(final Callback callback) {
        RemoteFamilyApi.a().d((AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    try {
                        if (!jSONObject.isNull("Dids")) {
                            ArrayList arrayList = new ArrayList();
                            Object obj = jSONObject.get("Dids");
                            if (obj != null && (obj instanceof JSONArray)) {
                                for (int i = 0; i < ((JSONArray) obj).length(); i++) {
                                    arrayList.add(((JSONArray) obj).getString(i));
                                }
                                DeviceInitChecker.f17939a = arrayList;
                            }
                            if (callback != null) {
                                callback.onSuccess(null);
                                return;
                            }
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }

            public void onFailure(Error error) {
                if (callback != null) {
                    callback.onFailure(0, (String) null);
                }
            }
        });
    }

    public static void a(AsyncCallback asyncCallback) {
        ArrayList arrayList = new ArrayList(f17939a);
        final WeakReference weakReference = new WeakReference(asyncCallback);
        RemoteFamilyApi.a().c((List<String>) arrayList, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                DeviceInitChecker.f17939a = new ArrayList();
                AsyncCallback asyncCallback = (AsyncCallback) weakReference.get();
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(null);
                }
            }

            public void onFailure(Error error) {
                AsyncCallback asyncCallback = (AsyncCallback) weakReference.get();
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public static void a(final List<String> list, AsyncCallback asyncCallback) {
        if (list != null) {
            final WeakReference weakReference = new WeakReference(asyncCallback);
            RemoteFamilyApi.a().c(list, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    DeviceInitChecker.f17939a.removeAll(list);
                    AsyncCallback asyncCallback = (AsyncCallback) weakReference.get();
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(null);
                    }
                }

                public void onFailure(Error error) {
                    AsyncCallback asyncCallback = (AsyncCallback) weakReference.get();
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(error);
                    }
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-9999, "dids == null"));
        }
    }
}
