package com.xiaomi.smarthome.frame.plugin.host;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AppUsrExpPlanUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService;
import com.xiaomi.smarthome.plugin.service.HostService;
import com.xiaomi.smarthome.stat.PluginStatReporter;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PluginHostApi extends XmPluginHostApi {
    protected String did;
    private Context mAppContext;
    private int nonce = -1;

    public interface ParserForRawResult<T> extends Parser<T> {
    }

    public abstract void createDeviceGroup(Context context, String str);

    public abstract void ensureService(Callback callback);

    public final int getApiLevel() {
        return 100;
    }

    public abstract DeviceTagInterface getDeviceTagManager();

    public abstract String getPluginProcess(int i, String str);

    public abstract void login(Context context, int i);

    public PluginHostApi(Context context) {
        this.mAppContext = context.getApplicationContext();
        XmPluginHostApi.sXmPluginHostApi = this;
    }

    public final int generateNonce() {
        this.nonce++;
        if (this.nonce <= 0) {
            this.nonce = new Random().nextInt(10000) + 1;
        }
        return this.nonce;
    }

    public final String getChannel() {
        return HostSetting.j;
    }

    @Deprecated
    public final void loadLibrary(String str, String str2, ClassLoader classLoader) {
        loadLibrary(PluginRuntimeManager.getInstance().getXmPluginPackage(str), str2);
    }

    public final void loadLibrary(XmPluginPackage xmPluginPackage, String str) {
        throw new UnsupportedOperationException("this method is unsupported, please use System.loadLibrary");
    }

    public final void addRecord(String str, String str2, Object obj, JSONObject jSONObject) {
        if (AppUsrExpPlanUtil.a(this.mAppContext, this.did)) {
            String str3 = "plugin." + str;
            String str4 = "";
            if (obj != null) {
                str4 = obj.toString();
            }
            String str5 = str4;
            String str6 = "";
            if (jSONObject != null) {
                str6 = jSONObject.toString();
            }
            String str7 = str6;
            int indexOf = str2.indexOf(":");
            if (indexOf > 0) {
                String substring = str2.substring(0, indexOf);
                str2 = str2.substring(indexOf + 1);
                PluginStatReporter.a(str3, substring, str2, str5, jSONObject);
            }
            CoreApi.a().a(StatType.PLUGIN, str3, str2, str5, str7, false);
        }
    }

    public final void addRecordV2(XmPluginPackage xmPluginPackage, String str, String str2, Object obj, JSONObject jSONObject) {
        if (AppUsrExpPlanUtil.a(this.mAppContext, this.did)) {
            addRecord(xmPluginPackage, str2, obj, jSONObject);
            HashMap hashMap = new HashMap();
            if (obj != null) {
                hashMap.put("value", obj.toString());
            }
            if (jSONObject != null) {
                hashMap.put("extra", jSONObject.toString());
            }
            if (str != null) {
                hashMap.put("model", str);
            }
            MobclickAgent.a(this.mAppContext, str2, (Map<String, String>) hashMap);
        }
    }

    public final void addRecord(XmPluginPackage xmPluginPackage, String str, Object obj, JSONObject jSONObject) {
        if (AppUsrExpPlanUtil.a(this.mAppContext, this.did) && xmPluginPackage != null) {
            String str2 = "plugin." + xmPluginPackage.getPluginId() + "." + xmPluginPackage.getPackageId();
            String str3 = "";
            if (obj != null) {
                str3 = obj.toString();
            }
            String str4 = str3;
            String str5 = "";
            if (jSONObject != null) {
                str5 = jSONObject.toString();
            }
            String str6 = str5;
            int indexOf = str.indexOf(":");
            if (indexOf > 0) {
                String substring = str.substring(0, indexOf);
                str = str.substring(indexOf + 1);
                PluginStatReporter.a(str2, substring, str, str4, jSONObject);
            }
            CoreApi.a().a(StatType.PLUGIN, str2, str, str4, str6, false);
        }
    }

    public final String getAccountId() {
        return CoreApi.a().s();
    }

    /* access modifiers changed from: package-private */
    public Looper getLooper() {
        Looper myLooper = Looper.myLooper();
        return myLooper == null ? Looper.getMainLooper() : myLooper;
    }

    public final <T> void callRouterRemoteApi(String str, String str2, String str3, boolean z, List<NameValuePair> list, final Callback<T> callback, final Parser<T> parser) {
        final Looper looper = getLooper();
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (NameValuePair next : list) {
                arrayList.add(new KeyValuePair(next.getName(), next.getValue()));
            }
        }
        String str4 = str;
        boolean z2 = z;
        CoreApi.a().a(this.mAppContext, new NetRequest.Builder().a(str3).b(str2).b((List<KeyValuePair>) arrayList).a(), str4, z2, new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.toString();
            }
        }, new AsyncCallback<String, Error>() {
            public void onSuccess(String str) {
                if (parser != null) {
                    try {
                        PluginHostApi.this.handlerSuccess(callback, parser.parse(str), looper);
                    } catch (Exception e) {
                        PluginHostApi.this.handlerFailed(callback, ErrorCode.ERROR_JSON_PARSER_EXCEPTION.getCode(), e.toString(), looper);
                    }
                } else {
                    PluginHostApi.this.handlerSuccess(callback, null, looper);
                }
            }

            public void onFailure(Error error) {
                PluginHostApi.this.handlerFailed(callback, error.a(), "", looper);
            }
        });
    }

    public final <T> void callRouterRemoteApiV13(String str, String str2, String str3, boolean z, List<com.xiaomi.smarthome.device.api.KeyValuePair> list, final Callback<T> callback, final Parser<T> parser) {
        final Looper looper = getLooper();
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (com.xiaomi.smarthome.device.api.KeyValuePair next : list) {
                arrayList.add(new KeyValuePair(next.getKey(), next.getValue()));
            }
        }
        String str4 = str;
        boolean z2 = z;
        CoreApi.a().a(this.mAppContext, new NetRequest.Builder().a(str3).b(str2).b((List<KeyValuePair>) arrayList).a(), str4, z2, new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.toString();
            }
        }, new AsyncCallback<String, Error>() {
            public void onSuccess(String str) {
                if (parser != null) {
                    try {
                        PluginHostApi.this.handlerSuccess(callback, parser.parse(str), looper);
                    } catch (Exception e) {
                        PluginHostApi.this.handlerFailed(callback, ErrorCode.ERROR_JSON_PARSER_EXCEPTION.getCode(), e.toString(), looper);
                    }
                } else {
                    PluginHostApi.this.handlerSuccess(callback, null, looper);
                }
            }

            public void onFailure(Error error) {
                PluginHostApi.this.handlerFailed(callback, error.a(), "", looper);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public <T> void handlerSuccess(final Callback<T> callback, final T t, Looper looper) {
        if (callback != null) {
            if (looper == null) {
                looper = Looper.getMainLooper();
            }
            new Handler(looper).post(new Runnable() {
                public void run() {
                    if (callback != null) {
                        callback.onSuccess(t);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public <T> void handlerFailed(final Callback<T> callback, final int i, final String str, Looper looper) {
        if (callback != null) {
            if (looper == null) {
                looper = Looper.getMainLooper();
            }
            new Handler(looper).post(new Runnable() {
                public void run() {
                    if (callback != null) {
                        callback.onFailure(i, str);
                    }
                }
            });
        }
    }

    public <T> void callSmartHomeApi(String str, String str2, JSONObject jSONObject, Callback<T> callback, Parser<T> parser) {
        callSmartHomeApi(str, str2, jSONObject.toString(), callback, parser);
    }

    public <T> void callSmartHomeApi(String str, String str2, String str3, String str4, JSONObject jSONObject, Callback<T> callback, Parser<T> parser) {
        callSmartHomeApi(str, str2, str3, str4, jSONObject.toString(), callback, parser);
    }

    public final <T> void callSmartHomeApi(String str, String str2, String str3, final Callback<T> callback, final Parser<T> parser) {
        boolean z;
        final Looper looper = getLooper();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", str3));
        NetRequest a2 = new NetRequest.Builder().a("POST").b(str2).b((List<KeyValuePair>) arrayList).a();
        if (parser == null) {
            z = false;
        } else {
            z = parser instanceof ParserForRawResult;
        }
        CoreApi.a().a((Context) null, a2, new JsonParser<T>() {
            public T parse(JSONObject jSONObject) throws JSONException {
                if (parser != null) {
                    return parser.parse(jSONObject.toString());
                }
                return null;
            }
        }, Crypto.RC4, new AsyncCallback<T, Error>() {
            public void onSuccess(T t) {
                PluginHostApi.this.handlerSuccess(callback, t, looper);
            }

            public void onFailure(Error error) {
                if (error != null) {
                    PluginHostApi.this.handlerFailed(callback, error.a(), error.b(), looper);
                } else {
                    PluginHostApi.this.handlerFailed(callback, -1, "", looper);
                }
            }
        }, z);
    }

    public <T> void callSmartHomeApi(String str, String str2, String str3, String str4, String str5, final Callback<T> callback, final Parser<T> parser) {
        boolean z;
        final Looper looper = getLooper();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", str5));
        if (TextUtils.isEmpty(str4) || (!str4.toLowerCase().equals("get") && !str4.toLowerCase().equals("post"))) {
            str4 = "GET";
        }
        if (parser == null) {
            z = false;
        } else {
            z = parser instanceof ParserForRawResult;
        }
        CoreApi.a().a((Context) null, new NetRequest.Builder().c(str2).a(str4).b(str3).b((List<KeyValuePair>) arrayList).a(), new JsonParser<T>() {
            public T parse(JSONObject jSONObject) throws JSONException {
                if (parser != null) {
                    return parser.parse(jSONObject.toString());
                }
                return null;
            }
        }, Crypto.RC4, new AsyncCallback<T, Error>() {
            public void onSuccess(T t) {
                PluginHostApi.this.handlerSuccess(callback, t, looper);
            }

            public void onFailure(Error error) {
                if (error != null) {
                    PluginHostApi.this.handlerFailed(callback, error.a(), error.b(), looper);
                } else {
                    PluginHostApi.this.handlerFailed(callback, -1, "", looper);
                }
            }
        }, z);
    }

    public <T> void callMethod(String str, String str2, JSONArray jSONArray, Callback<T> callback, Parser<T> parser) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", generateNonce());
            jSONObject.put("method", str2);
            jSONObject.put("params", jSONArray);
        } catch (JSONException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.ERROR_PARAM_JSON_ERROR.getCode(), e.toString());
            }
        }
        request(str, jSONObject.toString(), false, callback, parser);
    }

    public <T> void callMethodFromCloud(String str, String str2, Object obj, Callback<T> callback, Parser<T> parser) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", generateNonce());
            jSONObject.put("method", str2);
            jSONObject.put("params", obj);
        } catch (JSONException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.ERROR_PARAM_JSON_ERROR.getCode(), e.toString());
            }
        }
        request(str, jSONObject.toString(), true, callback, parser);
    }

    public <T> void callMethod(String str, String str2, JSONObject jSONObject, Callback<T> callback, Parser<T> parser) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("id", generateNonce());
            jSONObject2.put("method", str2);
            jSONObject2.put("params", jSONObject);
        } catch (JSONException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.ERROR_PARAM_JSON_ERROR.getCode(), e.toString());
            }
        }
        request(str, jSONObject2.toString(), false, callback, parser);
    }

    public final <T> void callMethod(String str, String str2, Object[] objArr, Callback<T> callback, Parser<T> parser) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", generateNonce());
            jSONObject.put("method", str2);
            JSONArray jSONArray = new JSONArray();
            if (objArr != null) {
                for (int i = 0; i < objArr.length; i++) {
                    if (objArr[i] != null) {
                        jSONArray.put(objArr[i]);
                    }
                }
            }
            jSONObject.put("params", jSONArray);
        } catch (JSONException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.ERROR_PARAM_JSON_ERROR.getCode(), e.toString());
            }
        }
        request(str, jSONObject.toString(), false, callback, parser);
    }

    public final <T> void request(String str, String str2, boolean z, final Callback<T> callback, final Parser<T> parser) {
        final Looper looper = getLooper();
        DeviceStat deviceByDid = getDeviceByDid(str);
        if (deviceByDid == null) {
            handlerFailed(callback, -1, "device not found", looper);
            return;
        }
        final String str3 = deviceByDid.token;
        final AnonymousClass11 r7 = new AsyncCallback<JSONObject, Error>() {
            public void onSuccess(JSONObject jSONObject) {
                if (parser != null) {
                    try {
                        PluginHostApi.this.handlerSuccess(callback, parser.parse(jSONObject.toString()), looper);
                    } catch (Exception e) {
                        PluginHostApi.this.handlerFailed(callback, -1, e.getMessage(), looper);
                    }
                } else {
                    PluginHostApi.this.handlerSuccess(callback, null, looper);
                }
            }

            public void onFailure(Error error) {
                PluginHostApi.this.handlerFailed(callback, error.a(), error.b(), looper);
            }
        };
        final boolean z2 = z;
        final String str4 = str;
        final String str5 = str2;
        final Parser<T> parser2 = parser;
        CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                if (z2) {
                    CoreApi.a().a(str4, str3, str5, (AsyncCallback<JSONObject, Error>) r7, parser2 != null && (parser2 instanceof ParserForRawResult));
                } else {
                    CoreApi.a().b(str4, str3, str5, r7, parser2 != null && (parser2 instanceof ParserForRawResult));
                }
            }
        });
    }

    public final <T> void requestFromLocal(String str, String str2, final Callback<T> callback, final Parser<T> parser) {
        final Looper looper = getLooper();
        DeviceStat deviceByDid = getDeviceByDid(str);
        if (deviceByDid == null) {
            handlerFailed(callback, -1, "device not found", looper);
            return;
        }
        final String str3 = deviceByDid.token;
        final AnonymousClass13 r6 = new AsyncCallback<JSONObject, Error>() {
            public void onSuccess(JSONObject jSONObject) {
                if (parser != null) {
                    try {
                        PluginHostApi.this.handlerSuccess(callback, parser.parse(jSONObject.toString()), looper);
                    } catch (Exception e) {
                        PluginHostApi.this.handlerFailed(callback, -1, e.getMessage(), looper);
                    }
                } else {
                    PluginHostApi.this.handlerSuccess(callback, null, looper);
                }
            }

            public void onFailure(Error error) {
                PluginHostApi.this.handlerFailed(callback, error.a(), error.b(), looper);
            }
        };
        final String str4 = str;
        final String str5 = str2;
        final Parser<T> parser2 = parser;
        CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                CoreApi.a().c(str4, str3, str5, r6, parser2 != null && (parser2 instanceof ParserForRawResult));
            }
        });
    }

    public RunningProcess pluginRunningProcess() {
        return PluginBridgeService.process;
    }

    public final void startActivity(Context context, XmPluginPackage xmPluginPackage, Intent intent, String str, Class cls) {
        RunningProcess runningProcess;
        Class pluginActivityClass;
        if (xmPluginPackage != null) {
            String str2 = "";
            DeviceStat deviceStat = null;
            if (!TextUtils.isEmpty(str)) {
                deviceStat = XmPluginHostApi.instance().getDeviceByDid(str);
            }
            if (deviceStat == null) {
                List<String> modelList = xmPluginPackage.getModelList();
                if (modelList.size() > 0) {
                    str2 = modelList.get(0);
                }
                runningProcess = PluginBridgeService.process;
            } else {
                str2 = deviceStat.model;
                runningProcess = pluginRunningProcess();
            }
            if (runningProcess == null) {
                runningProcess = RunningProcess.PLUGIN0;
            }
            if (!TextUtils.isEmpty(str2) && (pluginActivityClass = PluginRuntimeManager.getInstance().getPluginActivityClass(runningProcess)) != null) {
                Intent intent2 = new Intent(this.mAppContext, pluginActivityClass);
                if (!TextUtils.isEmpty(str)) {
                    intent2.addCategory("did:" + str);
                }
                intent2.addCategory("model:" + str2);
                if (intent != null) {
                    intent2.putExtras(intent);
                    intent2.addFlags(intent.getFlags());
                }
                intent2.putExtra("extra_package", xmPluginPackage.packageName);
                intent2.putExtra("extra_class", cls.getName());
                if (!TextUtils.isEmpty(str)) {
                    intent2.putExtra("extra_device_did", str);
                }
                intent2.putExtra("extra_device_model", str2);
                if (!(context instanceof Activity)) {
                    intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                }
                context.startActivity(intent2);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004b A[LOOP:0: B:14:0x004b->B:17:0x005f, LOOP_START, PHI: r6 
      PHI: (r6v4 com.xiaomi.smarthome.frame.plugin.RunningProcess) = (r6v3 com.xiaomi.smarthome.frame.plugin.RunningProcess), (r6v8 com.xiaomi.smarthome.frame.plugin.RunningProcess) binds: [B:13:0x0042, B:17:0x005f] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void startService(android.content.Context r20, com.xiaomi.plugin.core.XmPluginPackage r21, com.xiaomi.smarthome.plugin.service.HostService r22, android.content.Intent r23, java.lang.Class r24, com.xiaomi.smarthome.device.api.Callback<android.os.Bundle> r25) {
        /*
            r19 = this;
            r0 = r19
            r1 = r25
            r2 = -1
            if (r21 != 0) goto L_0x000f
            if (r1 == 0) goto L_0x000e
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x000e:
            return
        L_0x000f:
            com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager r4 = com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager.getInstance()
            r5 = r22
            java.lang.Class r4 = r4.getPluginHostServiceClass(r5)
            if (r4 != 0) goto L_0x002b
            java.lang.String r3 = "PluginHostApi"
            java.lang.String r4 = "startService: can not find PluginHostService"
            android.util.Log.d(r3, r4)
            if (r1 == 0) goto L_0x002a
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x002a:
            return
        L_0x002b:
            android.content.Intent r5 = new android.content.Intent
            android.content.Context r6 = r0.mAppContext
            r5.<init>(r6, r4)
            android.content.pm.PackageManager r6 = r20.getPackageManager()
            r7 = 0
            android.content.pm.ResolveInfo r5 = r6.resolveService(r5, r7)
            if (r5 == 0) goto L_0x00af
            android.content.pm.ServiceInfo r6 = r5.serviceInfo
            if (r6 != 0) goto L_0x0042
            goto L_0x00af
        L_0x0042:
            r6 = 0
            java.util.List r8 = r21.getModelList()
            java.util.Iterator r8 = r8.iterator()
        L_0x004b:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0061
            java.lang.Object r6 = r8.next()
            java.lang.String r6 = (java.lang.String) r6
            com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager r9 = com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager.getInstance()
            com.xiaomi.smarthome.frame.plugin.RunningProcess r6 = r9.getPluginProcess(r7, r6)
            if (r6 == 0) goto L_0x004b
        L_0x0061:
            r10 = r6
            if (r10 != 0) goto L_0x0074
            java.lang.String r3 = "PluginHostApi"
            java.lang.String r4 = "startService: can not find targetPluginProcess"
            android.util.Log.d(r3, r4)
            if (r1 == 0) goto L_0x0073
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x0073:
            return
        L_0x0074:
            android.content.pm.ServiceInfo r5 = r5.serviceInfo
            java.lang.String r5 = r5.processName
            com.xiaomi.smarthome.frame.plugin.RunningProcess r5 = com.xiaomi.smarthome.frame.plugin.RunningProcess.getByProcessName(r5)
            if (r10 == r5) goto L_0x008e
            java.lang.String r3 = "PluginHostApi"
            java.lang.String r4 = "startService: targetPluginProcess and serviceProcess not match"
            android.util.Log.d(r3, r4)
            if (r1 == 0) goto L_0x008d
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x008d:
            return
        L_0x008e:
            com.xiaomi.smarthome.frame.plugin.PluginApi r9 = com.xiaomi.smarthome.frame.plugin.PluginApi.getInstance()
            java.lang.String r11 = r4.getName()
            long r12 = r21.getPluginId()
            long r14 = r21.getPackageId()
            java.lang.String r17 = r24.getName()
            com.xiaomi.smarthome.frame.plugin.host.PluginHostApi$15 r2 = new com.xiaomi.smarthome.frame.plugin.host.PluginHostApi$15
            r2.<init>(r1)
            r16 = r23
            r18 = r2
            r9.startService(r10, r11, r12, r14, r16, r17, r18)
            return
        L_0x00af:
            java.lang.String r3 = "PluginHostApi"
            java.lang.String r4 = "startService: can not resolve PluginHostService"
            android.util.Log.d(r3, r4)
            if (r1 == 0) goto L_0x00be
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x00be:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.plugin.host.PluginHostApi.startService(android.content.Context, com.xiaomi.plugin.core.XmPluginPackage, com.xiaomi.smarthome.plugin.service.HostService, android.content.Intent, java.lang.Class, com.xiaomi.smarthome.device.api.Callback):void");
    }

    public final void stopService(Context context, XmPluginPackage xmPluginPackage, HostService hostService, Intent intent, Class cls, Callback<Bundle> callback) {
        if (xmPluginPackage != null) {
            Class pluginHostServiceClass = PluginRuntimeManager.getInstance().getPluginHostServiceClass(hostService);
            if (pluginHostServiceClass == null) {
                Log.d("PluginHostApi", "startService: can not find PluginHostService");
                if (callback != null) {
                    callback.onFailure(-1, "");
                    return;
                }
                return;
            }
            ResolveInfo resolveService = context.getPackageManager().resolveService(new Intent(this.mAppContext, pluginHostServiceClass), 0);
            if (resolveService == null || resolveService.serviceInfo == null) {
                Log.d("PluginHostApi", "startService: can not resolve PluginHostService");
                if (callback != null) {
                    callback.onFailure(-1, "");
                    return;
                }
                return;
            }
            RunningProcess runningProcess = null;
            for (String next : xmPluginPackage.getModelList()) {
                runningProcess = pluginRunningProcess();
                if (runningProcess != null) {
                    break;
                }
            }
            if (runningProcess == null) {
                Log.d("PluginHostApi", "startService: can not find targetPluginProcess");
                if (callback != null) {
                    callback.onFailure(-1, "");
                }
            } else if (runningProcess != RunningProcess.getByProcessName(resolveService.serviceInfo.processName)) {
                Log.d("PluginHostApi", "startService: targetPluginProcess and serviceProcess not match");
                if (callback != null) {
                    callback.onFailure(-1, "");
                }
            } else {
                if (this.mAppContext.stopService(new Intent(this.mAppContext, pluginHostServiceClass))) {
                    if (callback != null) {
                        callback.onSuccess(new Bundle());
                    }
                } else if (callback != null) {
                    callback.onFailure(-1, "");
                }
            }
        } else if (callback != null) {
            callback.onFailure(-1, "");
        }
    }

    public void localPing(final String str, final Callback<Void> callback) {
        final Looper looper = getLooper();
        if (getDeviceByDid(str) == null) {
            handlerFailed(callback, -1, "device not found", looper);
            return;
        }
        final AnonymousClass16 r1 = new AsyncCallback<Void, Error>() {
            public void onSuccess(Void voidR) {
                PluginHostApi.this.handlerSuccess(callback, null, looper);
            }

            public void onFailure(Error error) {
                PluginHostApi.this.handlerFailed(callback, error.a(), error.b(), looper);
            }
        };
        CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                CoreApi.a().a(str, (AsyncCallback<Void, Error>) r1);
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004b A[LOOP:0: B:14:0x004b->B:17:0x005f, LOOP_START, PHI: r6 
      PHI: (r6v4 com.xiaomi.smarthome.frame.plugin.RunningProcess) = (r6v3 com.xiaomi.smarthome.frame.plugin.RunningProcess), (r6v8 com.xiaomi.smarthome.frame.plugin.RunningProcess) binds: [B:13:0x0042, B:17:0x005f] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void bindService(android.content.Context r21, com.xiaomi.plugin.core.XmPluginPackage r22, com.xiaomi.smarthome.plugin.service.HostService r23, java.lang.Class r24, android.content.ServiceConnection r25, int r26, com.xiaomi.smarthome.device.api.Callback<android.os.Bundle> r27) {
        /*
            r20 = this;
            r0 = r20
            r1 = r27
            r2 = -1
            if (r22 != 0) goto L_0x000f
            if (r1 == 0) goto L_0x000e
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x000e:
            return
        L_0x000f:
            com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager r4 = com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager.getInstance()
            r5 = r23
            java.lang.Class r4 = r4.getPluginHostServiceClass(r5)
            if (r4 != 0) goto L_0x002b
            java.lang.String r3 = "PluginHostApi"
            java.lang.String r4 = "startService: can not find PluginHostService"
            android.util.Log.d(r3, r4)
            if (r1 == 0) goto L_0x002a
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x002a:
            return
        L_0x002b:
            android.content.Intent r5 = new android.content.Intent
            android.content.Context r6 = r0.mAppContext
            r5.<init>(r6, r4)
            android.content.pm.PackageManager r6 = r21.getPackageManager()
            r7 = 0
            android.content.pm.ResolveInfo r5 = r6.resolveService(r5, r7)
            if (r5 == 0) goto L_0x00b1
            android.content.pm.ServiceInfo r6 = r5.serviceInfo
            if (r6 != 0) goto L_0x0042
            goto L_0x00b1
        L_0x0042:
            r6 = 0
            java.util.List r8 = r22.getModelList()
            java.util.Iterator r8 = r8.iterator()
        L_0x004b:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0061
            java.lang.Object r6 = r8.next()
            java.lang.String r6 = (java.lang.String) r6
            com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager r9 = com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager.getInstance()
            com.xiaomi.smarthome.frame.plugin.RunningProcess r6 = r9.getPluginProcess(r7, r6)
            if (r6 == 0) goto L_0x004b
        L_0x0061:
            r10 = r6
            if (r10 != 0) goto L_0x0074
            java.lang.String r3 = "PluginHostApi"
            java.lang.String r4 = "startService: can not find targetPluginProcess"
            android.util.Log.d(r3, r4)
            if (r1 == 0) goto L_0x0073
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x0073:
            return
        L_0x0074:
            android.content.pm.ServiceInfo r5 = r5.serviceInfo
            java.lang.String r5 = r5.processName
            com.xiaomi.smarthome.frame.plugin.RunningProcess r5 = com.xiaomi.smarthome.frame.plugin.RunningProcess.getByProcessName(r5)
            if (r10 == r5) goto L_0x008e
            java.lang.String r3 = "PluginHostApi"
            java.lang.String r4 = "startService: targetPluginProcess and serviceProcess not match"
            android.util.Log.d(r3, r4)
            if (r1 == 0) goto L_0x008d
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x008d:
            return
        L_0x008e:
            com.xiaomi.smarthome.frame.plugin.PluginApi r9 = com.xiaomi.smarthome.frame.plugin.PluginApi.getInstance()
            java.lang.String r11 = r4.getName()
            long r12 = r22.getPluginId()
            long r14 = r22.getPackageId()
            java.lang.String r16 = r24.getName()
            com.xiaomi.smarthome.frame.plugin.host.PluginHostApi$18 r2 = new com.xiaomi.smarthome.frame.plugin.host.PluginHostApi$18
            r2.<init>(r1)
            r17 = r25
            r18 = r26
            r19 = r2
            r9.bindService(r10, r11, r12, r14, r16, r17, r18, r19)
            return
        L_0x00b1:
            java.lang.String r3 = "PluginHostApi"
            java.lang.String r4 = "startService: can not resolve PluginHostService"
            android.util.Log.d(r3, r4)
            if (r1 == 0) goto L_0x00c0
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x00c0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.plugin.host.PluginHostApi.bindService(android.content.Context, com.xiaomi.plugin.core.XmPluginPackage, com.xiaomi.smarthome.plugin.service.HostService, java.lang.Class, android.content.ServiceConnection, int, com.xiaomi.smarthome.device.api.Callback):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004b A[LOOP:0: B:14:0x004b->B:17:0x005f, LOOP_START, PHI: r6 
      PHI: (r6v4 com.xiaomi.smarthome.frame.plugin.RunningProcess) = (r6v3 com.xiaomi.smarthome.frame.plugin.RunningProcess), (r6v8 com.xiaomi.smarthome.frame.plugin.RunningProcess) binds: [B:13:0x0042, B:17:0x005f] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unbindService(android.content.Context r20, com.xiaomi.plugin.core.XmPluginPackage r21, com.xiaomi.smarthome.plugin.service.HostService r22, java.lang.Class r23, android.content.ServiceConnection r24, com.xiaomi.smarthome.device.api.Callback<android.os.Bundle> r25) {
        /*
            r19 = this;
            r0 = r19
            r1 = r25
            r2 = -1
            if (r21 != 0) goto L_0x000f
            if (r1 == 0) goto L_0x000e
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x000e:
            return
        L_0x000f:
            com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager r4 = com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager.getInstance()
            r5 = r22
            java.lang.Class r4 = r4.getPluginHostServiceClass(r5)
            if (r4 != 0) goto L_0x002b
            java.lang.String r3 = "PluginHostApi"
            java.lang.String r4 = "startService: can not find PluginHostService"
            android.util.Log.d(r3, r4)
            if (r1 == 0) goto L_0x002a
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x002a:
            return
        L_0x002b:
            android.content.Intent r5 = new android.content.Intent
            android.content.Context r6 = r0.mAppContext
            r5.<init>(r6, r4)
            android.content.pm.PackageManager r6 = r20.getPackageManager()
            r7 = 0
            android.content.pm.ResolveInfo r5 = r6.resolveService(r5, r7)
            if (r5 == 0) goto L_0x00af
            android.content.pm.ServiceInfo r6 = r5.serviceInfo
            if (r6 != 0) goto L_0x0042
            goto L_0x00af
        L_0x0042:
            r6 = 0
            java.util.List r8 = r21.getModelList()
            java.util.Iterator r8 = r8.iterator()
        L_0x004b:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0061
            java.lang.Object r6 = r8.next()
            java.lang.String r6 = (java.lang.String) r6
            com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager r9 = com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager.getInstance()
            com.xiaomi.smarthome.frame.plugin.RunningProcess r6 = r9.getPluginProcess(r7, r6)
            if (r6 == 0) goto L_0x004b
        L_0x0061:
            r10 = r6
            if (r10 != 0) goto L_0x0074
            java.lang.String r3 = "PluginHostApi"
            java.lang.String r4 = "startService: can not find targetPluginProcess"
            android.util.Log.d(r3, r4)
            if (r1 == 0) goto L_0x0073
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x0073:
            return
        L_0x0074:
            android.content.pm.ServiceInfo r5 = r5.serviceInfo
            java.lang.String r5 = r5.processName
            com.xiaomi.smarthome.frame.plugin.RunningProcess r5 = com.xiaomi.smarthome.frame.plugin.RunningProcess.getByProcessName(r5)
            if (r10 == r5) goto L_0x008e
            java.lang.String r3 = "PluginHostApi"
            java.lang.String r4 = "startService: targetPluginProcess and serviceProcess not match"
            android.util.Log.d(r3, r4)
            if (r1 == 0) goto L_0x008d
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x008d:
            return
        L_0x008e:
            com.xiaomi.smarthome.frame.plugin.PluginApi r9 = com.xiaomi.smarthome.frame.plugin.PluginApi.getInstance()
            java.lang.String r11 = r4.getName()
            long r12 = r21.getPluginId()
            long r14 = r21.getPackageId()
            java.lang.String r16 = r23.getName()
            com.xiaomi.smarthome.frame.plugin.host.PluginHostApi$19 r2 = new com.xiaomi.smarthome.frame.plugin.host.PluginHostApi$19
            r2.<init>(r1)
            r17 = r24
            r18 = r2
            r9.unbindService(r10, r11, r12, r14, r16, r17, r18)
            return
        L_0x00af:
            java.lang.String r3 = "PluginHostApi"
            java.lang.String r4 = "startService: can not resolve PluginHostService"
            android.util.Log.d(r3, r4)
            if (r1 == 0) goto L_0x00be
            java.lang.String r3 = ""
            r1.onFailure(r2, r3)
        L_0x00be:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.plugin.host.PluginHostApi.unbindService(android.content.Context, com.xiaomi.plugin.core.XmPluginPackage, com.xiaomi.smarthome.plugin.service.HostService, java.lang.Class, android.content.ServiceConnection, com.xiaomi.smarthome.device.api.Callback):void");
    }

    public long getUTCTimeInMillis() {
        MiServiceTokenInfo a2 = CoreApi.a().a("xiaomiio");
        if (a2 != null) {
            return System.currentTimeMillis() + a2.e;
        }
        return System.currentTimeMillis();
    }

    public void setCurrentDid(String str) {
        this.did = str;
    }

    public void setUserDeviceData(String str, String str2, String str3, String str4, long j, Object obj, Callback<JSONArray> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str2);
            jSONObject.put("type", str3);
            jSONObject.put("key", str4);
            jSONObject.put("time", j);
            jSONObject.put("value", obj);
        } catch (JSONException e) {
            if (callback != null) {
                callback.onFailure(-1, e.toString());
                return;
            }
        }
        callSmartHomeApi(str, "/user/set_user_device_data", jSONObject, callback, new Parser<JSONArray>() {
            public JSONArray parse(String str) throws JSONException {
                return new JSONObject(str).getJSONArray("result");
            }
        });
    }
}
