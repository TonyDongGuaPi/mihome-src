package com.xiaomi.mishopsdk.utils;

import com.alibaba.fastjson.JSONObject;
import com.mishopsdk.volley.Response;
import com.mishopsdk.volley.VolleyError;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.io.http.HostManager;
import com.xiaomi.mishopsdk.io.http.RequestQueueManager;
import com.xiaomi.mishopsdk.io.http.ShopJSONRequest;
import com.xiaomi.mishopsdk.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.atomic.AtomicReference;

public class MiHomeInfoManager {
    private static final String MIHOME_INFO_FILE_NAME = "com.xiaomi.mishopsdk.mihomeinfo.cfg";
    private static final String TAG = "MiHomeInfoManager";
    private static final String URL_MIHOME = (HostManager.FORMAL_DOMAIN_APP_SHOPAPI + "ishop/gallery");
    private static MiHomeInfoManager sInstance;
    private AtomicReference<JSONObject> mMiHomeJsonInfo = new AtomicReference<>();

    private MiHomeInfoManager() {
    }

    public static synchronized MiHomeInfoManager getInstance() {
        MiHomeInfoManager miHomeInfoManager;
        synchronized (MiHomeInfoManager.class) {
            if (sInstance == null) {
                sInstance = new MiHomeInfoManager();
            }
            miHomeInfoManager = sInstance;
        }
        return miHomeInfoManager;
    }

    public void updateMiHomeInfo() {
        RequestQueueManager.getInstance().addRequest(((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ShopJSONRequest.builder().setTag(this)).setUrl(URL_MIHOME)).setShouldCache(false)).addParams("city", "")).setClass(JSONObject.class).setListner(new Response.Listener<JSONObject>() {
            public void onFinish() {
            }

            public void onStart() {
            }

            public void onSuccess(JSONObject jSONObject, boolean z) {
                Log.e(MiHomeInfoManager.TAG, "updateMiHomeInfo success.");
                boolean unused = MiHomeInfoManager.this.storeMiHomeInfo(jSONObject);
            }

            public void onError(VolleyError volleyError) {
                Log.e(MiHomeInfoManager.TAG, "updateMiHomeInfo failed.", (Object) volleyError.getCause());
            }
        })).build());
    }

    public JSONObject getMiHomeInfo() {
        JSONObject jSONObject = this.mMiHomeJsonInfo.get();
        if (jSONObject != null) {
            return jSONObject;
        }
        JSONObject valueFromSerialize = getValueFromSerialize();
        this.mMiHomeJsonInfo.set(valueFromSerialize);
        return valueFromSerialize;
    }

    /* access modifiers changed from: private */
    public boolean storeMiHomeInfo(JSONObject jSONObject) {
        if (jSONObject == null) {
            Log.e(TAG, "storeMiHomeInfo get an null jsonInfo.");
            return false;
        }
        boolean serializeToFile = serializeToFile(jSONObject);
        if (serializeToFile) {
            this.mMiHomeJsonInfo.set(jSONObject);
        }
        return serializeToFile;
    }

    private synchronized boolean serializeToFile(JSONObject jSONObject) {
        try {
            new ObjectOutputStream(new FileOutputStream(new File(ShopApp.instance.getCacheDir(), MIHOME_INFO_FILE_NAME))).writeObject(jSONObject);
        } catch (Exception e) {
            Log.e(TAG, "serializeToFile get an exception, the file name:%s", MIHOME_INFO_FILE_NAME, e);
            return false;
        }
        return true;
    }

    private synchronized JSONObject getValueFromSerialize() {
        try {
        } catch (Exception e) {
            Log.e(TAG, "getValueFromSerialize get an exception, the file name:%s", MIHOME_INFO_FILE_NAME, e);
            return null;
        }
        return (JSONObject) new ObjectInputStream(new FileInputStream(new File(ShopApp.instance.getCacheDir(), MIHOME_INFO_FILE_NAME))).readObject();
    }
}
