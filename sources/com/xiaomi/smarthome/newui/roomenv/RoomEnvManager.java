package com.xiaomi.smarthome.newui.roomenv;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.newui.roomenv.model.RoomEnvData;
import com.xiaomi.smarthome.newui.roomenv.model.RoomEnvDataItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class RoomEnvManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20702a = "room_env_data_updated";
    private static final String b = "RoomEnvManager";
    private static RoomEnvManager d = null;
    private static final String e = "room_env_data_";
    private static final String f = "data";
    private static final long g = 259200000;
    /* access modifiers changed from: private */
    public volatile Map<String, Map<String, RoomEnvData>> c = new ConcurrentHashMap();

    private RoomEnvManager() {
        SHApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                RoomEnvManager.this.b();
            }
        });
    }

    public static RoomEnvManager a() {
        if (d == null) {
            synchronized (RoomEnvManager.class) {
                if (d == null) {
                    d = new RoomEnvManager();
                }
            }
        }
        return d;
    }

    public Map<String, RoomEnvData> a(String str) {
        return this.c.get(str);
    }

    public RoomEnvData a(String str, String str2, AsyncCallback<RoomEnvData, Error> asyncCallback) {
        RoomEnvData roomEnvData;
        Map map = this.c.get(str);
        if (map == null || (roomEnvData = (RoomEnvData) map.get(str2)) == null) {
            b(str, str2, asyncCallback);
            return null;
        }
        asyncCallback.onSuccess(roomEnvData);
        return roomEnvData;
    }

    public void b(final String str, final String str2, final AsyncCallback<RoomEnvData, Error> asyncCallback) {
        RemoteFamilyApi.a().a(str2, HomeManager.a().j(str), (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null || jSONObject.isNull("desc_list")) {
                    asyncCallback.onSuccess(null);
                    return;
                }
                RoomEnvData roomEnvData = new RoomEnvData();
                roomEnvData.b(str);
                roomEnvData.a(str2);
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("desc_list");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        RoomEnvDataItem a2 = RoomEnvDataItem.a(optJSONArray.optJSONObject(i));
                        if (a2 != null) {
                            arrayList.add(a2);
                        }
                    }
                }
                roomEnvData.a((List<RoomEnvDataItem>) arrayList);
                Map map = (Map) RoomEnvManager.this.c.get(str);
                if (map == null) {
                    map = new ConcurrentHashMap();
                    RoomEnvManager.this.c.put(str, map);
                }
                map.put(roomEnvData.b(), roomEnvData);
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(roomEnvData);
                }
                RoomEnvManager.this.c();
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public void a(final String str, final AsyncCallback<List<RoomEnvData>, Error> asyncCallback) {
        if (TextUtils.isEmpty(str)) {
            str = HomeManager.a().l();
        }
        if (!TextUtils.isEmpty(str)) {
            RemoteFamilyApi.a().i(str, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        ArrayList arrayList = new ArrayList();
                        JSONArray optJSONArray = jSONObject.optJSONArray("room_desc_list");
                        if (optJSONArray != null) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                RoomEnvData a2 = RoomEnvData.a(optJSONArray.optJSONObject(i));
                                if (a2 != null) {
                                    a2.b(str);
                                    Map map = (Map) RoomEnvManager.this.c.get(str);
                                    if (map == null) {
                                        map = new ConcurrentHashMap();
                                        RoomEnvManager.this.c.put(str, map);
                                    }
                                    map.put(a2.b(), a2);
                                    arrayList.add(a2);
                                }
                            }
                        }
                        if (asyncCallback != null) {
                            asyncCallback.onSuccess(arrayList);
                        }
                        RoomEnvManager.this.c();
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(RoomEnvManager.f20702a));
                    } else if (asyncCallback != null) {
                        asyncCallback.onFailure(new Error(ErrorCode.ERROR_UNKNOWN_ERROR.getCode(), "null result"));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        JSONArray optJSONArray;
        Context appContext = SHApplication.getAppContext();
        String string = appContext.getSharedPreferences(e + MD5Util.a(CoreApi.a().s()), 0).getString("data", (String) null);
        String str = b;
        LogUtil.a(str, "dumpFromStore:" + string);
        if (!TextUtils.isEmpty(string)) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                if (System.currentTimeMillis() - jSONObject.optLong("stored_ts") > g || (optJSONArray = jSONObject.optJSONArray("env_data")) == null) {
                    return;
                }
                if (optJSONArray.length() > 0) {
                    ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            String optString = optJSONObject.optString("home_id");
                            if (!TextUtils.isEmpty(optString)) {
                                JSONArray optJSONArray2 = jSONObject.optJSONArray("data");
                                if (optJSONArray2 != null) {
                                    if (optJSONArray2.length() != 0) {
                                        ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
                                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                            JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                                            if (optJSONObject2 != null) {
                                                RoomEnvData a2 = RoomEnvData.a(optJSONObject2);
                                                if (a2 != null) {
                                                    a2.b(optString);
                                                    concurrentHashMap2.put(a2.b(), a2);
                                                }
                                            }
                                        }
                                        if (!concurrentHashMap2.isEmpty()) {
                                            concurrentHashMap.put(optString, concurrentHashMap2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!concurrentHashMap.isEmpty()) {
                        this.c = concurrentHashMap;
                    }
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(f20702a));
                    String str2 = b;
                    LogUtil.a(str2, "dumpFromStore map size:" + concurrentHashMap.size());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry next : this.c.entrySet()) {
                if (next != null) {
                    String str = (String) next.getKey();
                    Map map = (Map) next.getValue();
                    if (map != null) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("home_id", str);
                        JSONArray jSONArray2 = new JSONArray();
                        for (Map.Entry entry : map.entrySet()) {
                            if (entry != null) {
                                RoomEnvData roomEnvData = (RoomEnvData) entry.getValue();
                                if (roomEnvData != null) {
                                    jSONArray2.put(roomEnvData.a());
                                }
                            }
                        }
                        jSONObject2.put("data", jSONArray2);
                        jSONArray.put(jSONObject2);
                    }
                }
            }
            jSONObject.put("env_data", jSONArray);
            jSONObject.put("stored_ts", System.currentTimeMillis());
            String jSONObject3 = jSONObject.toString();
            String str2 = b;
            LogUtil.a(str2, "dumpToStore:" + jSONObject3);
            Context appContext = SHApplication.getAppContext();
            PreferenceUtils.b(appContext.getSharedPreferences(e + MD5Util.a(CoreApi.a().s()), 0), "data", jSONObject3);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
