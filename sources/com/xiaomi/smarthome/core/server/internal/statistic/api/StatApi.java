package com.xiaomi.smarthome.core.server.internal.statistic.api;

import android.content.Context;
import com.taobao.weex.common.Constants;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.internal.CoreAsyncCallback;
import com.xiaomi.smarthome.core.server.internal.CoreAsyncHandle;
import com.xiaomi.smarthome.core.server.internal.CoreError;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeHttpsApi;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.apiparser.CoreJsonParser;
import com.xiaomi.smarthome.core.server.internal.apiparser.CoreSmartHomeApiParser;
import com.xiaomi.smarthome.core.server.internal.statistic.entity.StatInfoResult;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.stat.report.StatLogUploader;
import com.xiaomi.stat.d;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StatApi {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f14715a = new Object();
    private static StatApi b;

    private StatApi() {
    }

    public static StatApi a() {
        if (b == null) {
            synchronized (f14715a) {
                if (b == null) {
                    b = new StatApi();
                }
            }
        }
        return b;
    }

    public CoreAsyncHandle a(Context context, JSONArray jSONArray, final CoreAsyncCallback<StatInfoResult, CoreError> coreAsyncCallback) {
        ArrayList arrayList = new ArrayList();
        boolean g = ServerCompact.g(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(DeviceTagInterface.m, "v2");
            jSONObject.put("cid", SystemApi.a().a(context, g));
            jSONObject.put("msid", MiStatInterface.b(context));
            jSONObject.put("mc", SystemApi.a().j(context));
            jSONObject.put("av", SystemApi.a().d(context));
            jSONObject.put(d.R, SystemApi.a().d() + "-" + SystemApi.a().f() + "-" + SystemApi.a().j());
            jSONObject.put(d.G, SystemApi.a().i());
            jSONObject.put("am", SystemApi.a().k());
            jSONObject.put("ch", HostSetting.j);
            jSONObject.put(d.Z, jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/stat/stat_info_p").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass1 r7 = new CoreJsonParser<StatInfoResult>() {
            /* renamed from: b */
            public StatInfoResult a(JSONObject jSONObject) throws JSONException {
                StatInfoResult statInfoResult = new StatInfoResult();
                statInfoResult.f14722a = jSONObject.optInt(Constants.Name.INTERVAL);
                statInfoResult.b = jSONObject.optInt("max_number");
                return statInfoResult;
            }
        };
        return new CoreAsyncHandle(SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                CoreSmartHomeApiParser.a().a(netResult, r7, coreAsyncCallback);
            }

            public void a(NetError netError) {
                coreAsyncCallback.b(new CoreError(netError.a(), netError.b()));
            }
        }));
    }

    public CoreAsyncHandle a(Context context, String str, final CoreAsyncCallback<StatInfoResult, CoreError> coreAsyncCallback) {
        ArrayList arrayList = new ArrayList();
        boolean g = ServerCompact.g(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(DeviceTagInterface.m, "v3");
            jSONObject.put("cid", SystemApi.a().a(context, g));
            jSONObject.put("msid", MiStatInterface.b(context));
            jSONObject.put("mc", SystemApi.a().j(context));
            jSONObject.put("av", SystemApi.a().d(context));
            jSONObject.put(d.R, SystemApi.a().d() + "-" + SystemApi.a().f() + "-" + SystemApi.a().j());
            jSONObject.put(d.G, SystemApi.a().i());
            jSONObject.put("am", SystemApi.a().k());
            jSONObject.put("ch", HostSetting.j);
        } catch (JSONException unused) {
        }
        String jSONObject2 = jSONObject.toString();
        int lastIndexOf = jSONObject2.lastIndexOf(125);
        String str2 = jSONObject2.substring(0, lastIndexOf) + ",\"rd\":[" + str + "]}";
        StatLogUploader.a("STAT-UPLOAD", str2);
        arrayList.add(new KeyValuePair("data", str2));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/stat/stat_info").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass3 r7 = new CoreJsonParser<StatInfoResult>() {
            /* renamed from: b */
            public StatInfoResult a(JSONObject jSONObject) throws JSONException {
                StatInfoResult statInfoResult = new StatInfoResult();
                statInfoResult.f14722a = jSONObject.optInt(Constants.Name.INTERVAL);
                statInfoResult.b = jSONObject.optInt("max_number");
                return statInfoResult;
            }
        };
        return new CoreAsyncHandle(SmartHomeHttpsApi.a().a(a2, new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                CoreSmartHomeApiParser.a().a(netResult, r7, coreAsyncCallback);
            }

            public void a(NetError netError) {
                coreAsyncCallback.b(new CoreError(netError.a(), netError.b()));
            }
        }));
    }

    public CoreAsyncHandle b(Context context, JSONArray jSONArray, final CoreAsyncCallback<StatInfoResult, CoreError> coreAsyncCallback) {
        ArrayList arrayList = new ArrayList();
        boolean g = ServerCompact.g(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(DeviceTagInterface.m, "v2");
            jSONObject.put("cid", SystemApi.a().a(context, g));
            jSONObject.put("msid", MiStatInterface.b(context));
            jSONObject.put("mc", SystemApi.a().j(context));
            jSONObject.put("av", SystemApi.a().d(context));
            jSONObject.put(d.R, SystemApi.a().d() + "-" + SystemApi.a().f() + "-" + SystemApi.a().j());
            jSONObject.put(d.G, SystemApi.a().i());
            jSONObject.put("am", SystemApi.a().k());
            jSONObject.put("ch", HostSetting.j);
            jSONObject.put(d.Z, jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/stat/stat_info").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass5 r7 = new CoreJsonParser<StatInfoResult>() {
            /* renamed from: b */
            public StatInfoResult a(JSONObject jSONObject) throws JSONException {
                StatInfoResult statInfoResult = new StatInfoResult();
                statInfoResult.f14722a = jSONObject.optInt(Constants.Name.INTERVAL);
                statInfoResult.b = jSONObject.optInt("max_number");
                return statInfoResult;
            }
        };
        return new CoreAsyncHandle(SmartHomeHttpsApi.a().a(a2, new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                CoreSmartHomeApiParser.a().a(netResult, r7, coreAsyncCallback);
            }

            public void a(NetError netError) {
                coreAsyncCallback.b(new CoreError(netError.a(), netError.b()));
            }
        }));
    }
}
