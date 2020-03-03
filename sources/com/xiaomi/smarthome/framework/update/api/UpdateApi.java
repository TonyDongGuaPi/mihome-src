package com.xiaomi.smarthome.framework.update.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.mi.global.bbs.utils.Constants;
import com.xiaomi.market.sdk.Constants;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.update.api.entity.AppGrayUpdateResult;
import com.xiaomi.smarthome.framework.update.api.entity.AppInnerUpdateResult;
import com.xiaomi.smarthome.framework.update.api.entity.AppReleaseUpdateResult;
import com.xiaomi.smarthome.framework.update.api.entity.FirmwareUpdateBatchResult;
import com.xiaomi.smarthome.framework.update.api.entity.FirmwareUpdateResult;
import com.xiaomi.smarthome.miio.update.ModelUpdateInfo;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateApi {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17753a = 300;
    private static final Object b = new Object();
    private static final String c = "UpdateApi";
    private static UpdateApi d;

    private UpdateApi() {
    }

    public static UpdateApi a() {
        if (d == null) {
            synchronized (b) {
                if (d == null) {
                    d = new UpdateApi();
                }
            }
        }
        return d;
    }

    public AsyncHandle a(Context context, List<String> list, AsyncCallback<FirmwareUpdateBatchResult, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    jSONArray.put(list.get(i));
                }
            }
            jSONObject.put("dids", jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/multi_checkversion").b((List<KeyValuePair>) arrayList).a(), new JsonParser<FirmwareUpdateBatchResult>() {
            /* renamed from: a */
            public FirmwareUpdateBatchResult parse(JSONObject jSONObject) throws JSONException {
                Log.d("ABC", "multi check version result " + jSONObject);
                FirmwareUpdateBatchResult firmwareUpdateBatchResult = new FirmwareUpdateBatchResult();
                JSONArray jSONArray = jSONObject.getJSONArray("list");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    FirmwareUpdateBatchResult.UpdateResultItem updateResultItem = new FirmwareUpdateBatchResult.UpdateResultItem();
                    updateResultItem.e = jSONObject2.optBoolean("isLatest");
                    updateResultItem.f17778a = jSONObject2.optString("did");
                    updateResultItem.b = jSONObject2.optBoolean("updating");
                    updateResultItem.c = jSONObject2.optString("curr");
                    updateResultItem.d = jSONObject2.optString(Constants.PageFragment.PAGE_LATEST);
                    updateResultItem.f = jSONObject2.optString("description");
                    updateResultItem.g = jSONObject2.optInt("ota_progress");
                    updateResultItem.h = jSONObject2.optString("ota_status");
                    updateResultItem.i = jSONObject2.optInt("timeout_time");
                    updateResultItem.j = jSONObject2.optLong("upload_time");
                    firmwareUpdateBatchResult.f17777a.add(updateResultItem);
                }
                return firmwareUpdateBatchResult;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle b(Context context, List<KeyValuePair> list, final AsyncCallback<FirmwareUpdateBatchResult, Error> asyncCallback) {
        JSONArray jSONArray = new JSONArray();
        final ArrayList arrayList = new ArrayList();
        if (list != null) {
            try {
                if (list.size() > 0) {
                    for (KeyValuePair next : list) {
                        String a2 = next.a();
                        String b2 = next.b();
                        PluginRecord d2 = CoreApi.a().d(b2);
                        if (!(d2 == null || d2.h() == null)) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("did", a2);
                            if (TextUtils.isEmpty(b2) || (!b2.equals("chuangmi.camera.ipc009") && !b2.equals("chuangmi.camera.ipc019") && !b2.equals(DeviceConstant.CHUANGMI_CAMERA_021))) {
                                jSONObject.put("plugin_level", "" + d2.h().g());
                            } else {
                                jSONObject.put("plugin_level", "96");
                            }
                            jSONArray.put(jSONObject);
                            if (jSONArray.length() >= 300) {
                                arrayList.add(jSONArray);
                                jSONArray = new JSONArray();
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                LogUtil.b(c, Log.getStackTraceString(e));
            }
        }
        if (jSONArray.length() > 0) {
            arrayList.add(jSONArray);
        }
        AnonymousClass2 r11 = new JsonParser<List<FirmwareUpdateBatchResult.UpdateResultItem>>() {
            /* renamed from: a */
            public List<FirmwareUpdateBatchResult.UpdateResultItem> parse(JSONObject jSONObject) throws JSONException {
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray = jSONObject.getJSONArray("list");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    FirmwareUpdateBatchResult.UpdateResultItem updateResultItem = new FirmwareUpdateBatchResult.UpdateResultItem();
                    updateResultItem.e = jSONObject2.optBoolean("isLatest");
                    updateResultItem.f17778a = jSONObject2.optString("did");
                    updateResultItem.b = jSONObject2.optBoolean("updating");
                    updateResultItem.c = jSONObject2.optString("curr");
                    updateResultItem.d = jSONObject2.optString(Constants.PageFragment.PAGE_LATEST);
                    updateResultItem.f = jSONObject2.optString("description");
                    updateResultItem.g = jSONObject2.optInt("ota_progress");
                    updateResultItem.h = jSONObject2.optString("ota_status");
                    updateResultItem.i = jSONObject2.optInt("timeout_time");
                    updateResultItem.j = jSONObject2.optLong("upload_time");
                    arrayList.add(updateResultItem);
                }
                return arrayList;
            }
        };
        AnonymousClass3 r0 = new AsyncCallback<List<FirmwareUpdateBatchResult.UpdateResultItem>, Error>() {

            /* renamed from: a  reason: collision with root package name */
            FirmwareUpdateBatchResult f17767a = new FirmwareUpdateBatchResult();
            AtomicInteger b = new AtomicInteger(arrayList.size());
            Error c;

            /* renamed from: a */
            public void onSuccess(List<FirmwareUpdateBatchResult.UpdateResultItem> list) {
                this.f17767a.f17777a.addAll(list);
                onFailure((Error) null);
            }

            public void onFailure(Error error) {
                if (error != null) {
                    this.c = error;
                }
                int decrementAndGet = this.b.decrementAndGet();
                LogUtil.c(UpdateApi.c, "checkFirmwareUpdateBatch response times " + decrementAndGet + " size:" + this.f17767a.f17777a.size());
                if (decrementAndGet == 0 && asyncCallback != null) {
                    if (this.c == null) {
                        asyncCallback.onSuccess(this.f17767a);
                    } else {
                        asyncCallback.onFailure(this.c);
                    }
                }
            }
        };
        final ArrayList arrayList2 = new ArrayList();
        if (arrayList.size() != 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                JSONArray jSONArray2 = (JSONArray) it.next();
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("platform", "android");
                    jSONObject2.put("check_reqs", jSONArray2);
                    if (context != null) {
                        try {
                            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            jSONObject2.put("app_level", "" + packageInfo.versionCode);
                        } catch (PackageManager.NameNotFoundException e2) {
                            LogUtil.b(c, Log.getStackTraceString(e2));
                        }
                    }
                } catch (JSONException e3) {
                    LogUtil.b(c, Log.getStackTraceString(e3));
                }
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(new KeyValuePair("data", jSONObject2.toString()));
                Context context2 = context;
                arrayList2.add(CoreApi.a().a(context2, new NetRequest.Builder().a("POST").b("/v2/device/multi_check_device_version").b((List<KeyValuePair>) arrayList3).a(), r11, Crypto.RC4, r0));
            }
        } else if (asyncCallback != null) {
            asyncCallback.onSuccess(new FirmwareUpdateBatchResult());
        }
        return new AsyncHandle<FirmwareUpdateBatchResult>((FirmwareUpdateBatchResult) null) {
            public void cancel() {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((AsyncHandle) it.next()).cancel();
                }
            }
        };
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:1|2|(2:4|5)|6|7|(2:11|(2:20|(1:24))(1:19))|25|26) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0042 */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0078 A[Catch:{ JSONException -> 0x00e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0080 A[Catch:{ JSONException -> 0x00e5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.frame.AsyncHandle a(android.content.Context r7, java.lang.String r8, int r9, com.xiaomi.smarthome.frame.AsyncCallback<java.lang.Void, com.xiaomi.smarthome.frame.Error> r10) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r2 = "did"
            r1.put(r2, r8)     // Catch:{ JSONException -> 0x00e5 }
            java.lang.String r2 = "pid"
            r1.put(r2, r9)     // Catch:{ JSONException -> 0x00e5 }
            java.lang.String r9 = "platform"
            java.lang.String r2 = "android"
            r1.put(r9, r2)     // Catch:{ JSONException -> 0x00e5 }
            if (r7 == 0) goto L_0x0042
            android.content.pm.PackageManager r9 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0042 }
            java.lang.String r2 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0042 }
            r3 = 0
            android.content.pm.PackageInfo r9 = r9.getPackageInfo(r2, r3)     // Catch:{ NameNotFoundException -> 0x0042 }
            java.lang.String r2 = "app_level"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x0042 }
            r3.<init>()     // Catch:{ NameNotFoundException -> 0x0042 }
            java.lang.String r4 = ""
            r3.append(r4)     // Catch:{ NameNotFoundException -> 0x0042 }
            int r9 = r9.versionCode     // Catch:{ NameNotFoundException -> 0x0042 }
            r3.append(r9)     // Catch:{ NameNotFoundException -> 0x0042 }
            java.lang.String r9 = r3.toString()     // Catch:{ NameNotFoundException -> 0x0042 }
            r1.put(r2, r9)     // Catch:{ NameNotFoundException -> 0x0042 }
        L_0x0042:
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ JSONException -> 0x00e5 }
            if (r9 != 0) goto L_0x00b0
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r9 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ JSONException -> 0x00e5 }
            com.xiaomi.smarthome.device.Device r8 = r9.b((java.lang.String) r8)     // Catch:{ JSONException -> 0x00e5 }
            if (r8 == 0) goto L_0x00b0
            java.lang.String r9 = r8.model     // Catch:{ JSONException -> 0x00e5 }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ JSONException -> 0x00e5 }
            if (r9 != 0) goto L_0x0080
            java.lang.String r9 = r8.model     // Catch:{ JSONException -> 0x00e5 }
            java.lang.String r2 = "chuangmi.camera.ipc009"
            boolean r9 = r9.equals(r2)     // Catch:{ JSONException -> 0x00e5 }
            if (r9 != 0) goto L_0x0078
            java.lang.String r9 = r8.model     // Catch:{ JSONException -> 0x00e5 }
            java.lang.String r2 = "chuangmi.camera.ipc019"
            boolean r9 = r9.equals(r2)     // Catch:{ JSONException -> 0x00e5 }
            if (r9 != 0) goto L_0x0078
            java.lang.String r9 = r8.model     // Catch:{ JSONException -> 0x00e5 }
            java.lang.String r2 = "chuangmi.camera.ipc021"
            boolean r9 = r9.equals(r2)     // Catch:{ JSONException -> 0x00e5 }
            if (r9 == 0) goto L_0x0080
        L_0x0078:
            java.lang.String r8 = "plugin_level"
            java.lang.String r9 = "96"
            r1.put(r8, r9)     // Catch:{ JSONException -> 0x00e5 }
            goto L_0x00b0
        L_0x0080:
            com.xiaomi.smarthome.frame.core.CoreApi r9 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x00e5 }
            java.lang.String r8 = r8.model     // Catch:{ JSONException -> 0x00e5 }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r8 = r9.d((java.lang.String) r8)     // Catch:{ JSONException -> 0x00e5 }
            if (r8 == 0) goto L_0x00b0
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r9 = r8.h()     // Catch:{ JSONException -> 0x00e5 }
            if (r9 == 0) goto L_0x00b0
            java.lang.String r9 = "plugin_level"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00e5 }
            r2.<init>()     // Catch:{ JSONException -> 0x00e5 }
            java.lang.String r3 = ""
            r2.append(r3)     // Catch:{ JSONException -> 0x00e5 }
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r8 = r8.h()     // Catch:{ JSONException -> 0x00e5 }
            int r8 = r8.g()     // Catch:{ JSONException -> 0x00e5 }
            r2.append(r8)     // Catch:{ JSONException -> 0x00e5 }
            java.lang.String r8 = r2.toString()     // Catch:{ JSONException -> 0x00e5 }
            r1.put(r9, r8)     // Catch:{ JSONException -> 0x00e5 }
        L_0x00b0:
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r8 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r9 = "data"
            java.lang.String r1 = r1.toString()
            r8.<init>(r9, r1)
            r0.add(r8)
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = new com.xiaomi.smarthome.core.entity.net.NetRequest$Builder
            r8.<init>()
            java.lang.String r9 = "POST"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = r8.a((java.lang.String) r9)
            java.lang.String r9 = "/home/devupgrade"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = r8.b((java.lang.String) r9)
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = r8.b((java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r0)
            com.xiaomi.smarthome.core.entity.net.NetRequest r2 = r8.a()
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            r3 = 0
            com.xiaomi.smarthome.core.entity.net.Crypto r4 = com.xiaomi.smarthome.core.entity.net.Crypto.RC4
            r1 = r7
            r5 = r10
            com.xiaomi.smarthome.frame.AsyncHandle r7 = r0.a((android.content.Context) r1, (com.xiaomi.smarthome.core.entity.net.NetRequest) r2, r3, (com.xiaomi.smarthome.core.entity.net.Crypto) r4, r5)
            return r7
        L_0x00e5:
            if (r10 == 0) goto L_0x00f7
            com.xiaomi.smarthome.frame.Error r7 = new com.xiaomi.smarthome.frame.Error
            com.xiaomi.smarthome.frame.ErrorCode r8 = com.xiaomi.smarthome.frame.ErrorCode.INVALID
            int r8 = r8.getCode()
            java.lang.String r9 = ""
            r7.<init>(r8, r9)
            r10.sendFailureMessage(r7)
        L_0x00f7:
            com.xiaomi.smarthome.frame.AsyncHandle r7 = new com.xiaomi.smarthome.frame.AsyncHandle
            r8 = 0
            r7.<init>(r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.update.api.UpdateApi.a(android.content.Context, java.lang.String, int, com.xiaomi.smarthome.frame.AsyncCallback):com.xiaomi.smarthome.frame.AsyncHandle");
    }

    public AsyncHandle b(Context context, String str, int i, AsyncCallback<FirmwareUpdateResult, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("pid", i);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/checkversion").b((List<KeyValuePair>) arrayList).a(), new JsonParser<FirmwareUpdateResult>() {
                /* renamed from: a */
                public FirmwareUpdateResult parse(JSONObject jSONObject) throws JSONException {
                    Log.d("ABC", "update api result " + jSONObject);
                    FirmwareUpdateResult firmwareUpdateResult = new FirmwareUpdateResult();
                    firmwareUpdateResult.d = jSONObject.optBoolean("isLatest");
                    firmwareUpdateResult.f17779a = jSONObject.optBoolean("updating");
                    firmwareUpdateResult.b = jSONObject.optString("curr");
                    firmwareUpdateResult.c = jSONObject.optString(Constants.PageFragment.PAGE_LATEST);
                    firmwareUpdateResult.e = jSONObject.optString("description");
                    firmwareUpdateResult.f = jSONObject.optInt("ota_progress");
                    firmwareUpdateResult.g = jSONObject.optString("ota_status");
                    firmwareUpdateResult.h = jSONObject.optInt("timeout_time");
                    long currentTimeMillis = (System.currentTimeMillis() / 1000) - jSONObject.optLong("ota_start_time");
                    int i = (int) (currentTimeMillis / 2);
                    if (firmwareUpdateResult.h > 0) {
                        i = (int) ((currentTimeMillis / ((long) firmwareUpdateResult.h)) * 90);
                    }
                    if (i > 90) {
                        i = 90;
                    }
                    if (i > firmwareUpdateResult.f) {
                        firmwareUpdateResult.f = i;
                    }
                    return firmwareUpdateResult;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:1|2|(2:4|5)|6|7|(2:11|(2:20|(1:24))(1:19))|25|26) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x003d */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0073 A[Catch:{ JSONException -> 0x00e4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007b A[Catch:{ JSONException -> 0x00e4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.frame.AsyncHandle c(android.content.Context r7, java.lang.String r8, int r9, com.xiaomi.smarthome.frame.AsyncCallback<com.xiaomi.smarthome.framework.update.api.entity.FirmwareUpdateResult, com.xiaomi.smarthome.frame.Error> r10) {
        /*
            r6 = this;
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "did"
            r0.put(r1, r8)     // Catch:{ JSONException -> 0x00e4 }
            java.lang.String r1 = "platform"
            java.lang.String r2 = "android"
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00e4 }
            if (r7 == 0) goto L_0x003d
            android.content.pm.PackageManager r1 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x003d }
            java.lang.String r2 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x003d }
            r3 = 0
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r2, r3)     // Catch:{ NameNotFoundException -> 0x003d }
            java.lang.String r2 = "app_level"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x003d }
            r3.<init>()     // Catch:{ NameNotFoundException -> 0x003d }
            java.lang.String r4 = ""
            r3.append(r4)     // Catch:{ NameNotFoundException -> 0x003d }
            int r1 = r1.versionCode     // Catch:{ NameNotFoundException -> 0x003d }
            r3.append(r1)     // Catch:{ NameNotFoundException -> 0x003d }
            java.lang.String r1 = r3.toString()     // Catch:{ NameNotFoundException -> 0x003d }
            r0.put(r2, r1)     // Catch:{ NameNotFoundException -> 0x003d }
        L_0x003d:
            boolean r1 = android.text.TextUtils.isEmpty(r8)     // Catch:{ JSONException -> 0x00e4 }
            if (r1 != 0) goto L_0x00ab
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r1 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ JSONException -> 0x00e4 }
            com.xiaomi.smarthome.device.Device r8 = r1.b((java.lang.String) r8)     // Catch:{ JSONException -> 0x00e4 }
            if (r8 == 0) goto L_0x00ab
            java.lang.String r1 = r8.model     // Catch:{ JSONException -> 0x00e4 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ JSONException -> 0x00e4 }
            if (r1 != 0) goto L_0x007b
            java.lang.String r1 = r8.model     // Catch:{ JSONException -> 0x00e4 }
            java.lang.String r2 = "chuangmi.camera.ipc009"
            boolean r1 = r1.equals(r2)     // Catch:{ JSONException -> 0x00e4 }
            if (r1 != 0) goto L_0x0073
            java.lang.String r1 = r8.model     // Catch:{ JSONException -> 0x00e4 }
            java.lang.String r2 = "chuangmi.camera.ipc019"
            boolean r1 = r1.equals(r2)     // Catch:{ JSONException -> 0x00e4 }
            if (r1 != 0) goto L_0x0073
            java.lang.String r1 = r8.model     // Catch:{ JSONException -> 0x00e4 }
            java.lang.String r2 = "chuangmi.camera.ipc021"
            boolean r1 = r1.equals(r2)     // Catch:{ JSONException -> 0x00e4 }
            if (r1 == 0) goto L_0x007b
        L_0x0073:
            java.lang.String r8 = "plugin_level"
            java.lang.String r1 = "96"
            r0.put(r8, r1)     // Catch:{ JSONException -> 0x00e4 }
            goto L_0x00ab
        L_0x007b:
            com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x00e4 }
            java.lang.String r8 = r8.model     // Catch:{ JSONException -> 0x00e4 }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r8 = r1.d((java.lang.String) r8)     // Catch:{ JSONException -> 0x00e4 }
            if (r8 == 0) goto L_0x00ab
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r1 = r8.h()     // Catch:{ JSONException -> 0x00e4 }
            if (r1 == 0) goto L_0x00ab
            java.lang.String r1 = "plugin_level"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00e4 }
            r2.<init>()     // Catch:{ JSONException -> 0x00e4 }
            java.lang.String r3 = ""
            r2.append(r3)     // Catch:{ JSONException -> 0x00e4 }
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r8 = r8.h()     // Catch:{ JSONException -> 0x00e4 }
            int r8 = r8.g()     // Catch:{ JSONException -> 0x00e4 }
            r2.append(r8)     // Catch:{ JSONException -> 0x00e4 }
            java.lang.String r8 = r2.toString()     // Catch:{ JSONException -> 0x00e4 }
            r0.put(r1, r8)     // Catch:{ JSONException -> 0x00e4 }
        L_0x00ab:
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r8 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r1 = "data"
            java.lang.String r0 = r0.toString()
            r8.<init>(r1, r0)
            r9.add(r8)
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = new com.xiaomi.smarthome.core.entity.net.NetRequest$Builder
            r8.<init>()
            java.lang.String r0 = "POST"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = r8.a((java.lang.String) r0)
            java.lang.String r0 = "/v2/device/check_device_version"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = r8.b((java.lang.String) r0)
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = r8.b((java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r9)
            com.xiaomi.smarthome.core.entity.net.NetRequest r2 = r8.a()
            com.xiaomi.smarthome.framework.update.api.UpdateApi$6 r3 = new com.xiaomi.smarthome.framework.update.api.UpdateApi$6
            r3.<init>()
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.smarthome.core.entity.net.Crypto r4 = com.xiaomi.smarthome.core.entity.net.Crypto.RC4
            r1 = r7
            r5 = r10
            com.xiaomi.smarthome.frame.AsyncHandle r7 = r0.a((android.content.Context) r1, (com.xiaomi.smarthome.core.entity.net.NetRequest) r2, r3, (com.xiaomi.smarthome.core.entity.net.Crypto) r4, r5)
            return r7
        L_0x00e4:
            if (r10 == 0) goto L_0x00f6
            com.xiaomi.smarthome.frame.Error r7 = new com.xiaomi.smarthome.frame.Error
            com.xiaomi.smarthome.frame.ErrorCode r8 = com.xiaomi.smarthome.frame.ErrorCode.INVALID
            int r8 = r8.getCode()
            java.lang.String r9 = ""
            r7.<init>(r8, r9)
            r10.sendFailureMessage(r7)
        L_0x00f6:
            com.xiaomi.smarthome.frame.AsyncHandle r7 = new com.xiaomi.smarthome.frame.AsyncHandle
            r8 = 0
            r7.<init>(r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.update.api.UpdateApi.c(android.content.Context, java.lang.String, int, com.xiaomi.smarthome.frame.AsyncCallback):com.xiaomi.smarthome.frame.AsyncHandle");
    }

    public AsyncHandle a(Context context, String str, String str2, int i, AsyncCallback<AppGrayUpdateResult, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("package_name", str);
            jSONObject.put("version_name", str2);
            jSONObject.put(Constants.Update.e, i);
            jSONObject.put("api_level", 2);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/version/check_gray").b((List<KeyValuePair>) arrayList).a(), new JsonParser<AppGrayUpdateResult>() {
                /* renamed from: a */
                public AppGrayUpdateResult parse(JSONObject jSONObject) throws JSONException {
                    AppGrayUpdateResult appGrayUpdateResult = new AppGrayUpdateResult();
                    appGrayUpdateResult.f17774a = jSONObject.optInt("new_version");
                    appGrayUpdateResult.b = jSONObject.optInt("new_version_code");
                    appGrayUpdateResult.c = jSONObject.optString("new_version_name");
                    appGrayUpdateResult.d = jSONObject.optString("download_url");
                    appGrayUpdateResult.e = jSONObject.optString("change_log");
                    return appGrayUpdateResult;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle d(Context context, String str, int i, AsyncCallback<AppReleaseUpdateResult, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version_name", str);
            jSONObject.put(Constants.Update.e, i);
            jSONObject.put("api_level", 2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/version/online_version").b((List<KeyValuePair>) arrayList).a(), new JsonParser<AppReleaseUpdateResult>() {
            /* renamed from: a */
            public AppReleaseUpdateResult parse(JSONObject jSONObject) throws JSONException {
                AppReleaseUpdateResult appReleaseUpdateResult = new AppReleaseUpdateResult();
                appReleaseUpdateResult.f17776a = jSONObject.optInt("new_version");
                appReleaseUpdateResult.b = jSONObject.optInt("force");
                appReleaseUpdateResult.c = jSONObject.optInt("new_version_code");
                appReleaseUpdateResult.d = jSONObject.optString("new_version_name");
                appReleaseUpdateResult.e = jSONObject.optString("download_url");
                appReleaseUpdateResult.f = jSONObject.optString("change_log");
                return appReleaseUpdateResult;
            }
        }, Crypto.HTTPS, asyncCallback);
    }

    public AsyncHandle b(Context context, String str, String str2, int i, AsyncCallback<AppInnerUpdateResult, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", new JSONObject().toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/service/getdailybuild").b((List<KeyValuePair>) arrayList).a(), new JsonParser<AppInnerUpdateResult>() {
            /* renamed from: a */
            public AppInnerUpdateResult parse(JSONObject jSONObject) throws JSONException {
                AppInnerUpdateResult appInnerUpdateResult = new AppInnerUpdateResult();
                JSONObject jSONObject2 = jSONObject.getJSONObject("debug");
                String string = jSONObject2.getString("version");
                String string2 = jSONObject2.getString("url");
                String string3 = jSONObject.getJSONObject("sdk").getString("version");
                String string4 = jSONObject2.getString("url");
                JSONObject jSONObject3 = jSONObject.getJSONObject("release");
                String string5 = jSONObject3.getString("version");
                String string6 = jSONObject3.getString("url");
                appInnerUpdateResult.f17775a = string;
                appInnerUpdateResult.b = string2;
                appInnerUpdateResult.c = string3;
                appInnerUpdateResult.d = string4;
                appInnerUpdateResult.e = string5;
                appInnerUpdateResult.f = string6;
                return appInnerUpdateResult;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle e(Context context, String str, int i, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("pid", i);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/checkversion").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:1|2|(2:4|5)|6|7|(2:11|(2:20|(1:24))(1:19))) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x003d */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0073 A[Catch:{ JSONException -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007b A[Catch:{ JSONException -> 0x00ab }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.frame.AsyncHandle f(android.content.Context r7, java.lang.String r8, int r9, com.xiaomi.smarthome.frame.AsyncCallback<org.json.JSONObject, com.xiaomi.smarthome.frame.Error> r10) {
        /*
            r6 = this;
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "did"
            r0.put(r1, r8)     // Catch:{ JSONException -> 0x00ab }
            java.lang.String r1 = "platform"
            java.lang.String r2 = "android"
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00ab }
            if (r7 == 0) goto L_0x003d
            android.content.pm.PackageManager r1 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x003d }
            java.lang.String r2 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x003d }
            r3 = 0
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r2, r3)     // Catch:{ NameNotFoundException -> 0x003d }
            java.lang.String r2 = "app_level"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x003d }
            r3.<init>()     // Catch:{ NameNotFoundException -> 0x003d }
            java.lang.String r4 = ""
            r3.append(r4)     // Catch:{ NameNotFoundException -> 0x003d }
            int r1 = r1.versionCode     // Catch:{ NameNotFoundException -> 0x003d }
            r3.append(r1)     // Catch:{ NameNotFoundException -> 0x003d }
            java.lang.String r1 = r3.toString()     // Catch:{ NameNotFoundException -> 0x003d }
            r0.put(r2, r1)     // Catch:{ NameNotFoundException -> 0x003d }
        L_0x003d:
            boolean r1 = android.text.TextUtils.isEmpty(r8)     // Catch:{ JSONException -> 0x00ab }
            if (r1 != 0) goto L_0x00ab
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r1 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ JSONException -> 0x00ab }
            com.xiaomi.smarthome.device.Device r8 = r1.b((java.lang.String) r8)     // Catch:{ JSONException -> 0x00ab }
            if (r8 == 0) goto L_0x00ab
            java.lang.String r1 = r8.model     // Catch:{ JSONException -> 0x00ab }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ JSONException -> 0x00ab }
            if (r1 != 0) goto L_0x007b
            java.lang.String r1 = r8.model     // Catch:{ JSONException -> 0x00ab }
            java.lang.String r2 = "chuangmi.camera.ipc009"
            boolean r1 = r1.equals(r2)     // Catch:{ JSONException -> 0x00ab }
            if (r1 != 0) goto L_0x0073
            java.lang.String r1 = r8.model     // Catch:{ JSONException -> 0x00ab }
            java.lang.String r2 = "chuangmi.camera.ipc019"
            boolean r1 = r1.equals(r2)     // Catch:{ JSONException -> 0x00ab }
            if (r1 != 0) goto L_0x0073
            java.lang.String r1 = r8.model     // Catch:{ JSONException -> 0x00ab }
            java.lang.String r2 = "chuangmi.camera.ipc021"
            boolean r1 = r1.equals(r2)     // Catch:{ JSONException -> 0x00ab }
            if (r1 == 0) goto L_0x007b
        L_0x0073:
            java.lang.String r8 = "plugin_level"
            java.lang.String r1 = "96"
            r0.put(r8, r1)     // Catch:{ JSONException -> 0x00ab }
            goto L_0x00ab
        L_0x007b:
            com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x00ab }
            java.lang.String r8 = r8.model     // Catch:{ JSONException -> 0x00ab }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r8 = r1.d((java.lang.String) r8)     // Catch:{ JSONException -> 0x00ab }
            if (r8 == 0) goto L_0x00ab
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r1 = r8.h()     // Catch:{ JSONException -> 0x00ab }
            if (r1 == 0) goto L_0x00ab
            java.lang.String r1 = "plugin_level"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00ab }
            r2.<init>()     // Catch:{ JSONException -> 0x00ab }
            java.lang.String r3 = ""
            r2.append(r3)     // Catch:{ JSONException -> 0x00ab }
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r8 = r8.h()     // Catch:{ JSONException -> 0x00ab }
            int r8 = r8.g()     // Catch:{ JSONException -> 0x00ab }
            r2.append(r8)     // Catch:{ JSONException -> 0x00ab }
            java.lang.String r8 = r2.toString()     // Catch:{ JSONException -> 0x00ab }
            r0.put(r1, r8)     // Catch:{ JSONException -> 0x00ab }
        L_0x00ab:
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r8 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r1 = "data"
            java.lang.String r0 = r0.toString()
            r8.<init>(r1, r0)
            r9.add(r8)
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = new com.xiaomi.smarthome.core.entity.net.NetRequest$Builder
            r8.<init>()
            java.lang.String r0 = "POST"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = r8.a((java.lang.String) r0)
            java.lang.String r0 = "/v2/device/check_device_version"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = r8.b((java.lang.String) r0)
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = r8.b((java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r9)
            com.xiaomi.smarthome.core.entity.net.NetRequest r2 = r8.a()
            com.xiaomi.smarthome.framework.update.api.UpdateApi$11 r3 = new com.xiaomi.smarthome.framework.update.api.UpdateApi$11
            r3.<init>()
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.smarthome.core.entity.net.Crypto r4 = com.xiaomi.smarthome.core.entity.net.Crypto.RC4
            r1 = r7
            r5 = r10
            com.xiaomi.smarthome.frame.AsyncHandle r7 = r0.a((android.content.Context) r1, (com.xiaomi.smarthome.core.entity.net.NetRequest) r2, r3, (com.xiaomi.smarthome.core.entity.net.Crypto) r4, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.update.api.UpdateApi.f(android.content.Context, java.lang.String, int, com.xiaomi.smarthome.frame.AsyncCallback):com.xiaomi.smarthome.frame.AsyncHandle");
    }

    public AsyncHandle c(Context context, List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    jSONArray.put(list.get(i));
                }
            }
            jSONObject.put("dids", jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/multi_checkversion").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle d(Context context, List<String> list, final AsyncCallback<List<ModelUpdateInfo>, Error> asyncCallback) {
        PluginRecord d2;
        JSONArray jSONArray = new JSONArray();
        final ArrayList arrayList = new ArrayList();
        try {
            List<Device> d3 = SmartHomeDeviceManager.a().d();
            if (d3 != null && d3.size() > 0) {
                for (Device next : d3) {
                    if (!(next == null || (d2 = CoreApi.a().d(next.model)) == null || d2.h() == null)) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("did", next.did);
                        if (next != null) {
                            if (TextUtils.isEmpty(next.model) || (!next.model.equals("chuangmi.camera.ipc009") && !next.model.equals("chuangmi.camera.ipc019") && !next.model.equals(DeviceConstant.CHUANGMI_CAMERA_021))) {
                                jSONObject.put("plugin_level", "" + d2.h().g());
                            } else {
                                jSONObject.put("plugin_level", "96");
                            }
                        }
                        jSONArray.put(jSONObject);
                        if (jSONArray.length() >= 300) {
                            arrayList.add(jSONArray);
                            jSONArray = new JSONArray();
                        }
                    }
                }
            }
            if (jSONArray.length() > 0) {
                arrayList.add(jSONArray);
            }
        } catch (JSONException e) {
            LogUtil.b(c, Log.getStackTraceString(e));
        }
        final ArrayList arrayList2 = new ArrayList();
        AnonymousClass13 r7 = new JsonParser<List<ModelUpdateInfo>>() {
            /* renamed from: a */
            public List<ModelUpdateInfo> parse(JSONObject jSONObject) throws JSONException {
                ArrayList arrayList = new ArrayList();
                try {
                    JSONArray jSONArray = jSONObject.getJSONArray("list");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        ModelUpdateInfo modelUpdateInfo = new ModelUpdateInfo();
                        modelUpdateInfo.h = jSONObject2.getBoolean("isLatest");
                        if (!modelUpdateInfo.h) {
                            modelUpdateInfo.b = jSONObject2.getString("did");
                            try {
                                Device b = SmartHomeDeviceManager.a().b(modelUpdateInfo.b);
                                if (b != null) {
                                    if (b.isOnline) {
                                        modelUpdateInfo.c = b.pid;
                                        modelUpdateInfo.e = jSONObject2.getBoolean("updating");
                                        modelUpdateInfo.f = jSONObject2.getString("curr");
                                        modelUpdateInfo.g = jSONObject2.getString(Constants.PageFragment.PAGE_LATEST);
                                        modelUpdateInfo.i = jSONObject2.getString("description");
                                        modelUpdateInfo.j = 0;
                                        modelUpdateInfo.k = jSONObject2.getString("ota_status");
                                        arrayList.add(modelUpdateInfo);
                                    }
                                }
                            } catch (Exception unused) {
                            }
                        }
                    }
                } catch (JSONException unused2) {
                }
                return arrayList;
            }
        };
        new AsyncCallback<List<ModelUpdateInfo>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<ModelUpdateInfo> list) {
            }

            public void onFailure(Error error) {
            }
        };
        AnonymousClass15 r8 = new AsyncCallback<List<ModelUpdateInfo>, Error>() {

            /* renamed from: a  reason: collision with root package name */
            List<ModelUpdateInfo> f17760a = new ArrayList();
            AtomicInteger b = new AtomicInteger(arrayList.size());
            Error c;

            /* renamed from: a */
            public void onSuccess(List<ModelUpdateInfo> list) {
                this.f17760a.addAll(list);
                onFailure((Error) null);
            }

            public void onFailure(Error error) {
                if (error != null) {
                    this.c = error;
                }
                int decrementAndGet = this.b.decrementAndGet();
                LogUtil.c(UpdateApi.c, "getBatchUpdateInfo response times " + decrementAndGet + " size:" + this.f17760a.size());
                if (decrementAndGet == 0 && asyncCallback != null) {
                    if (this.c == null) {
                        asyncCallback.onSuccess(this.f17760a);
                    } else {
                        asyncCallback.onFailure(this.c);
                    }
                }
            }
        };
        if (arrayList.size() != 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                JSONArray jSONArray2 = (JSONArray) it.next();
                ArrayList arrayList3 = new ArrayList();
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("platform", "android");
                    jSONObject2.put("check_reqs", jSONArray2);
                    if (context != null) {
                        try {
                            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            jSONObject2.put("app_level", "" + packageInfo.versionCode);
                        } catch (PackageManager.NameNotFoundException e2) {
                            LogUtil.b(c, Log.getStackTraceString(e2));
                        }
                    }
                } catch (JSONException e3) {
                    LogUtil.b(c, Log.getStackTraceString(e3));
                }
                arrayList3.add(new KeyValuePair("data", jSONObject2.toString()));
                Context context2 = context;
                arrayList2.add(CoreApi.a().a(context2, new NetRequest.Builder().a("POST").b("/v2/device/multi_check_device_version").b((List<KeyValuePair>) arrayList3).a(), r7, Crypto.RC4, r8));
            }
        } else if (asyncCallback != null) {
            asyncCallback.onSuccess(new ArrayList());
        }
        return new AsyncHandle<FirmwareUpdateBatchResult>((FirmwareUpdateBatchResult) null) {
            public void cancel() {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((AsyncHandle) it.next()).cancel();
                }
            }
        };
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:1|2|(2:4|5)|6|7|(2:11|(2:20|(1:24))(1:19))) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0042 */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0078 A[Catch:{ JSONException -> 0x00b0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0080 A[Catch:{ JSONException -> 0x00b0 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.frame.AsyncHandle g(android.content.Context r7, java.lang.String r8, int r9, com.xiaomi.smarthome.frame.AsyncCallback<org.json.JSONObject, com.xiaomi.smarthome.frame.Error> r10) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r2 = "did"
            r1.put(r2, r8)     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r2 = "pid"
            r1.put(r2, r9)     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r9 = "platform"
            java.lang.String r2 = "android"
            r1.put(r9, r2)     // Catch:{ JSONException -> 0x00b0 }
            if (r7 == 0) goto L_0x0042
            android.content.pm.PackageManager r9 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0042 }
            java.lang.String r2 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0042 }
            r3 = 0
            android.content.pm.PackageInfo r9 = r9.getPackageInfo(r2, r3)     // Catch:{ NameNotFoundException -> 0x0042 }
            java.lang.String r2 = "app_level"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x0042 }
            r3.<init>()     // Catch:{ NameNotFoundException -> 0x0042 }
            java.lang.String r4 = ""
            r3.append(r4)     // Catch:{ NameNotFoundException -> 0x0042 }
            int r9 = r9.versionCode     // Catch:{ NameNotFoundException -> 0x0042 }
            r3.append(r9)     // Catch:{ NameNotFoundException -> 0x0042 }
            java.lang.String r9 = r3.toString()     // Catch:{ NameNotFoundException -> 0x0042 }
            r1.put(r2, r9)     // Catch:{ NameNotFoundException -> 0x0042 }
        L_0x0042:
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ JSONException -> 0x00b0 }
            if (r9 != 0) goto L_0x00b0
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r9 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ JSONException -> 0x00b0 }
            com.xiaomi.smarthome.device.Device r8 = r9.b((java.lang.String) r8)     // Catch:{ JSONException -> 0x00b0 }
            if (r8 == 0) goto L_0x00b0
            java.lang.String r9 = r8.model     // Catch:{ JSONException -> 0x00b0 }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ JSONException -> 0x00b0 }
            if (r9 != 0) goto L_0x0080
            java.lang.String r9 = r8.model     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r2 = "chuangmi.camera.ipc009"
            boolean r9 = r9.equals(r2)     // Catch:{ JSONException -> 0x00b0 }
            if (r9 != 0) goto L_0x0078
            java.lang.String r9 = r8.model     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r2 = "chuangmi.camera.ipc019"
            boolean r9 = r9.equals(r2)     // Catch:{ JSONException -> 0x00b0 }
            if (r9 != 0) goto L_0x0078
            java.lang.String r9 = r8.model     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r2 = "chuangmi.camera.ipc021"
            boolean r9 = r9.equals(r2)     // Catch:{ JSONException -> 0x00b0 }
            if (r9 == 0) goto L_0x0080
        L_0x0078:
            java.lang.String r8 = "plugin_level"
            java.lang.String r9 = "96"
            r1.put(r8, r9)     // Catch:{ JSONException -> 0x00b0 }
            goto L_0x00b0
        L_0x0080:
            com.xiaomi.smarthome.frame.core.CoreApi r9 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r8 = r8.model     // Catch:{ JSONException -> 0x00b0 }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r8 = r9.d((java.lang.String) r8)     // Catch:{ JSONException -> 0x00b0 }
            if (r8 == 0) goto L_0x00b0
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r9 = r8.h()     // Catch:{ JSONException -> 0x00b0 }
            if (r9 == 0) goto L_0x00b0
            java.lang.String r9 = "plugin_level"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00b0 }
            r2.<init>()     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r3 = ""
            r2.append(r3)     // Catch:{ JSONException -> 0x00b0 }
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r8 = r8.h()     // Catch:{ JSONException -> 0x00b0 }
            int r8 = r8.g()     // Catch:{ JSONException -> 0x00b0 }
            r2.append(r8)     // Catch:{ JSONException -> 0x00b0 }
            java.lang.String r8 = r2.toString()     // Catch:{ JSONException -> 0x00b0 }
            r1.put(r9, r8)     // Catch:{ JSONException -> 0x00b0 }
        L_0x00b0:
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r8 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r9 = "data"
            java.lang.String r1 = r1.toString()
            r8.<init>(r9, r1)
            r0.add(r8)
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = new com.xiaomi.smarthome.core.entity.net.NetRequest$Builder
            r8.<init>()
            java.lang.String r9 = "POST"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = r8.a((java.lang.String) r9)
            java.lang.String r9 = "/home/devupgrade"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = r8.b((java.lang.String) r9)
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r8 = r8.b((java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r0)
            com.xiaomi.smarthome.core.entity.net.NetRequest r2 = r8.a()
            com.xiaomi.smarthome.framework.update.api.UpdateApi$17 r3 = new com.xiaomi.smarthome.framework.update.api.UpdateApi$17
            r3.<init>()
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.smarthome.core.entity.net.Crypto r4 = com.xiaomi.smarthome.core.entity.net.Crypto.RC4
            r1 = r7
            r5 = r10
            com.xiaomi.smarthome.frame.AsyncHandle r7 = r0.a((android.content.Context) r1, (com.xiaomi.smarthome.core.entity.net.NetRequest) r2, r3, (com.xiaomi.smarthome.core.entity.net.Crypto) r4, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.update.api.UpdateApi.g(android.content.Context, java.lang.String, int, com.xiaomi.smarthome.frame.AsyncCallback):com.xiaomi.smarthome.frame.AsyncHandle");
    }

    public AsyncHandle c(Context context, String str, String str2, int i, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("package_name", str);
            jSONObject.put("version_name", str2);
            jSONObject.put(Constants.Update.e, i);
            jSONObject.put("api_level", 2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/version/check_gray").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle d(Context context, String str, String str2, int i, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("package_name", str);
            jSONObject.put("version_name", str2);
            jSONObject.put(Constants.Update.e, i);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/version/check_version").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.NONE, asyncCallback);
    }

    public AsyncHandle h(Context context, String str, int i, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version_name", str);
            jSONObject.put(Constants.Update.e, i);
            jSONObject.put("api_level", 2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/version/online_version").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.HTTPS, asyncCallback);
    }
}
