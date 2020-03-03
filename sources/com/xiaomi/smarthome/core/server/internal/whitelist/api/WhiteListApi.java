package com.xiaomi.smarthome.core.server.internal.whitelist.api;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.internal.CoreAsyncCallback;
import com.xiaomi.smarthome.core.server.internal.CoreAsyncHandle;
import com.xiaomi.smarthome.core.server.internal.CoreError;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.apiparser.CoreJsonParser;
import com.xiaomi.smarthome.core.server.internal.apiparser.CoreSmartHomeApiParser;
import com.xiaomi.smarthome.core.server.internal.whitelist.entity.WhiteListResult;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WhiteListApi {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f14736a = new Object();
    private static WhiteListApi b;

    private WhiteListApi() {
    }

    public static WhiteListApi a() {
        if (b == null) {
            synchronized (f14736a) {
                if (b == null) {
                    b = new WhiteListApi();
                }
            }
        }
        return b;
    }

    public CoreAsyncHandle a(Context context, long j, int i, final CoreAsyncCallback<WhiteListResult, CoreError> coreAsyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("last_modify", j);
            jSONObject.put("app_version", i);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/service/whitelist").b((List<KeyValuePair>) arrayList).a();
            final AnonymousClass1 r4 = new CoreJsonParser<WhiteListResult>() {
                /* renamed from: b */
                public WhiteListResult a(JSONObject jSONObject) throws JSONException {
                    WhiteListResult whiteListResult = new WhiteListResult();
                    JSONObject optJSONObject = jSONObject.optJSONObject("whitelist");
                    whiteListResult.f14740a = optJSONObject.optString("type");
                    whiteListResult.b = optJSONObject.optLong("last_modify");
                    JSONArray optJSONArray = optJSONObject.optJSONArray("mod_list");
                    if (optJSONArray != null) {
                        int length = optJSONArray.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                            String optString = jSONObject2.optString("packageName");
                            String optString2 = jSONObject2.optString("cert");
                            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                                WhiteListResult.WhiteListItemResult whiteListItemResult = new WhiteListResult.WhiteListItemResult();
                                whiteListItemResult.f14741a = jSONObject2.optString("packageName");
                                whiteListItemResult.b = jSONObject2.optString("cert");
                                whiteListResult.c.add(whiteListItemResult);
                            }
                        }
                    }
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("del_list");
                    if (optJSONArray != null) {
                        int length2 = optJSONArray2.length();
                        for (int i2 = 0; i2 < length2; i2++) {
                            String str = (String) optJSONArray2.get(i2);
                            if (!TextUtils.isEmpty(str)) {
                                whiteListResult.d.add(str);
                            }
                        }
                    }
                    return whiteListResult;
                }
            };
            return new CoreAsyncHandle(SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                /* renamed from: a */
                public void b(NetResult netResult) {
                }

                /* renamed from: b */
                public void a(NetResult netResult) {
                    CoreSmartHomeApiParser.a().a(netResult, r4, coreAsyncCallback);
                }

                public void a(NetError netError) {
                    coreAsyncCallback.b(new CoreError(netError.a(), netError.b()));
                }
            }));
        } catch (JSONException unused) {
            if (coreAsyncCallback != null) {
                coreAsyncCallback.b(new CoreError(ErrorCode.INVALID.getCode(), ""));
            }
            return new CoreAsyncHandle(null);
        }
    }

    public void a(final Callback<Boolean> callback) {
        SmartHomeRc4Api.a().a(new NetRequest.Builder().a("POST").b("/v2/developer/check_developer").a(), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
                StringBuilder sb = new StringBuilder();
                sb.append("check developer execute, onCache ");
                sb.append(netResult != null ? netResult.toString() : "result is empty!");
                LogUtilGrey.a("WhiteListApi", sb.toString());
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                StringBuilder sb = new StringBuilder();
                sb.append("check developer execute, onSuccess ");
                sb.append(netResult != null ? netResult.toString() : "result is empty!");
                LogUtilGrey.a("WhiteListApi", sb.toString());
                if (netResult.f13981a == 0) {
                    try {
                        boolean optBoolean = new JSONObject(netResult.c).optBoolean("result", false);
                        if (callback != null) {
                            callback.onSuccess(Boolean.valueOf(optBoolean));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (callback != null) {
                    callback.onSuccess(false);
                }
            }

            public void a(NetError netError) {
                StringBuilder sb = new StringBuilder();
                sb.append("check developer execute, onFail ");
                sb.append(netError != null ? netError.b() : "result is empty!");
                LogUtilGrey.a("WhiteListApi", sb.toString());
                if (callback != null) {
                    callback.onSuccess(false);
                }
            }
        });
    }
}
