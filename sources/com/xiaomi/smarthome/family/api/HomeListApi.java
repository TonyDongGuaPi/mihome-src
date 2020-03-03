package com.xiaomi.smarthome.family.api;

import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeListApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15779a = "RemoteFamilyApi";
    public static final int b = 300;

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject a(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject b(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    public AsyncHandle a(final AsyncCallback<JSONObject, Error> asyncCallback) {
        boolean z;
        try {
            JSONObject jSONObject = new JSONObject();
            try {
                z = SHApplication.getApplication().isAppForeground();
            } catch (Exception unused) {
                z = true;
            }
            jSONObject.put("fg", z);
            jSONObject.put("fetch_share", true);
            jSONObject.put("limit", 300);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            if (GlobalSetting.q) {
                LogUtilGrey.a("RemoteFamilyApi", "getHomeFromServer " + jSONObject.toString());
            }
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/homeroom/gethome").b((List<KeyValuePair>) arrayList).a();
            final AsyncHandle.AsyncHandleWrap asyncHandleWrap = new AsyncHandle.AsyncHandleWrap();
            asyncHandleWrap.a(CoreApi.a().a(SHApplication.getAppContext(), a2, $$Lambda$HomeListApi$8zsNb5u6sfk2pdBQX350ntzs3w.INSTANCE, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    String optString = jSONObject.optString("max_id");
                    if (jSONObject.optBoolean("has_more")) {
                        if (GlobalSetting.q) {
                            LogUtilGrey.a("RemoteFamilyApi", "getHomeFromServer onSuccess hasmore max_id:" + optString);
                        }
                        HomeListApi.this.a(asyncHandleWrap, jSONObject, HomeListApi.this.a(jSONObject.optJSONArray("homelist")), optString, asyncCallback);
                        return;
                    }
                    if (GlobalSetting.q) {
                        LogUtilGrey.a("RemoteFamilyApi", "getHomeFromServer onSuccess onepage");
                    }
                    asyncCallback.onSuccess(jSONObject);
                }

                public void onFailure(Error error) {
                    asyncCallback.onFailure(error);
                    LogUtilGrey.a("RemoteFamilyApi", "getMoreHomeFromServer onFailure");
                }
            }));
            return asyncHandleWrap;
        } catch (Exception unused2) {
            LogUtilGrey.a("RemoteFamilyApi", "getMoreHomeFromServer onFailure");
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x008b A[SYNTHETIC] */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, org.json.JSONArray>> a(org.json.JSONArray r11) {
        /*
            r10 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            if (r11 == 0) goto L_0x008f
            r1 = 0
            r2 = 0
        L_0x0009:
            int r3 = r11.length()
            if (r2 >= r3) goto L_0x008f
            org.json.JSONObject r3 = r11.optJSONObject(r2)
            if (r3 == 0) goto L_0x008b
            java.lang.String r4 = "id"
            java.lang.String r4 = r3.optString(r4)
            java.util.HashMap r5 = new java.util.HashMap
            r5.<init>()
            r0.put(r4, r5)
            java.lang.String r4 = "dids"
            org.json.JSONArray r4 = r3.optJSONArray(r4)
            if (r4 != 0) goto L_0x0045
            java.lang.String r6 = "dids"
            org.json.JSONArray r7 = new org.json.JSONArray     // Catch:{ JSONException -> 0x003b }
            r7.<init>()     // Catch:{ JSONException -> 0x003b }
            r3.put(r6, r7)     // Catch:{ JSONException -> 0x0037 }
            r4 = r7
            goto L_0x0045
        L_0x0037:
            r4 = move-exception
            r6 = r4
            r4 = r7
            goto L_0x003c
        L_0x003b:
            r6 = move-exception
        L_0x003c:
            java.lang.String r7 = "RemoteFamilyApi"
            java.lang.String r6 = android.util.Log.getStackTraceString(r6)
            com.xiaomi.smarthome.framework.log.LogUtil.b(r7, r6)
        L_0x0045:
            java.lang.String r6 = ""
            r5.put(r6, r4)
            java.lang.String r4 = "roomlist"
            org.json.JSONArray r3 = r3.optJSONArray(r4)
            if (r3 == 0) goto L_0x008b
            r4 = 0
        L_0x0053:
            int r6 = r3.length()
            if (r4 >= r6) goto L_0x008b
            org.json.JSONObject r6 = r3.optJSONObject(r4)
            java.lang.String r7 = "dids"
            org.json.JSONArray r7 = r6.optJSONArray(r7)
            if (r7 != 0) goto L_0x007f
            java.lang.String r8 = "dids"
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0075 }
            r9.<init>()     // Catch:{ JSONException -> 0x0075 }
            r6.put(r8, r9)     // Catch:{ JSONException -> 0x0071 }
            r7 = r9
            goto L_0x007f
        L_0x0071:
            r7 = move-exception
            r8 = r7
            r7 = r9
            goto L_0x0076
        L_0x0075:
            r8 = move-exception
        L_0x0076:
            java.lang.String r9 = "RemoteFamilyApi"
            java.lang.String r8 = android.util.Log.getStackTraceString(r8)
            com.xiaomi.smarthome.framework.log.LogUtil.b(r9, r8)
        L_0x007f:
            java.lang.String r8 = "id"
            java.lang.String r6 = r6.optString(r8)
            r5.put(r6, r7)
            int r4 = r4 + 1
            goto L_0x0053
        L_0x008b:
            int r2 = r2 + 1
            goto L_0x0009
        L_0x008f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.family.api.HomeListApi.a(org.json.JSONArray):java.util.HashMap");
    }

    /* access modifiers changed from: private */
    public void a(AsyncHandle.AsyncHandleWrap asyncHandleWrap, JSONObject jSONObject, HashMap<String, HashMap<String, JSONArray>> hashMap, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            if (asyncHandleWrap.a()) {
                asyncCallback.onFailure(new Error(-1, "canel"));
                return;
            }
            AsyncCallback<JSONObject, Error> asyncCallback2 = asyncCallback;
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("start_id", str);
            jSONObject2.put("limit", 300);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject2.toString()));
            if (GlobalSetting.q) {
                LogUtilGrey.a("RemoteFamilyApi", "getMoreHomeFromServer request");
            }
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/homeroom/get_dev_room_page").b((List<KeyValuePair>) arrayList).a();
            final HashMap<String, HashMap<String, JSONArray>> hashMap2 = hashMap;
            final AsyncHandle.AsyncHandleWrap asyncHandleWrap2 = asyncHandleWrap;
            final JSONObject jSONObject3 = jSONObject;
            final AsyncCallback<JSONObject, Error> asyncCallback3 = asyncCallback;
            AsyncHandle.AsyncHandleWrap asyncHandleWrap3 = asyncHandleWrap;
            asyncHandleWrap.a(CoreApi.a().a(SHApplication.getAppContext(), a2, $$Lambda$HomeListApi$kq4VL2eqZoDv4YMzeCCM5zc4HB0.INSTANCE, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("info");
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                String optString = optJSONObject.optString("id");
                                HashMap hashMap = (HashMap) hashMap2.get(optString);
                                if (hashMap != null) {
                                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("dids");
                                    if (optJSONArray2 != null) {
                                        JSONArray jSONArray = (JSONArray) hashMap.get("");
                                        if (jSONArray == null) {
                                            LogUtil.b("RemoteFamilyApi", "getMoreHomeFromServer firstDefaultRoomlist is null");
                                        } else {
                                            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                                jSONArray.put(optJSONArray2.optString(i2));
                                            }
                                        }
                                    }
                                    JSONArray optJSONArray3 = optJSONObject.optJSONArray("roomlist");
                                    if (optJSONArray3 != null) {
                                        for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                                            JSONObject optJSONObject2 = optJSONArray3.optJSONObject(i3);
                                            if (optJSONObject2 != null) {
                                                JSONArray optJSONArray4 = optJSONObject2.optJSONArray("dids");
                                                if (optJSONArray4 != null) {
                                                    String optString2 = optJSONObject2.optString("id");
                                                    JSONArray jSONArray2 = (JSONArray) hashMap.get(optString2);
                                                    if (jSONArray2 == null) {
                                                        LogUtil.b("RemoteFamilyApi", "getMoreHomeFromServer firstroomitem is null roomId:" + optString2);
                                                    } else {
                                                        for (int i4 = 0; i4 < optJSONArray4.length(); i4++) {
                                                            jSONArray2.put(optJSONArray4.optString(i4));
                                                        }
                                                    }
                                                }
                                            } else {
                                                LogUtil.b("RemoteFamilyApi", "getMoreHomeFromServer roomitem is null");
                                            }
                                        }
                                    }
                                } else {
                                    LogUtil.b("RemoteFamilyApi", "getMoreHomeFromServer nodata homeId:" + optString);
                                }
                            } else {
                                LogUtil.b("RemoteFamilyApi", "getMoreHomeFromServer info contain null homedata");
                            }
                        }
                    }
                    if (jSONObject.optBoolean("has_more")) {
                        String optString3 = jSONObject.optString("max_id");
                        if (GlobalSetting.q) {
                            LogUtilGrey.a("RemoteFamilyApi", "getMoreHomeFromServer onSuccess hasmore max_id:" + optString3);
                        }
                        HomeListApi.this.a(asyncHandleWrap2, jSONObject3, hashMap2, optString3, asyncCallback3);
                        return;
                    }
                    if (GlobalSetting.q) {
                        LogUtilGrey.a("RemoteFamilyApi", "getMoreHomeFromServer onSuccess");
                    }
                    asyncCallback3.onSuccess(jSONObject3);
                }

                public void onFailure(Error error) {
                    asyncCallback3.onFailure(error);
                    LogUtilGrey.a("RemoteFamilyApi", "getMoreHomeFromServer onFailure");
                }
            }));
        } catch (Exception e) {
            LogUtil.b("RemoteFamilyApi", Log.getStackTraceString(e));
        }
    }
}
