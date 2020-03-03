package com.xiaomi.smarthome.sip.api;

import android.content.Context;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.sip.model.GetVoiceTxtResult;
import com.xiaomi.smarthome.sip.model.MicroHistoryInfo;
import com.xiaomi.smarthome.sip.model.StartResult;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SipApi {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f22220a = new Object();
    private static SipApi b;

    private SipApi() {
    }

    public static SipApi a() {
        if (b == null) {
            synchronized (f22220a) {
                if (b == null) {
                    b = new SipApi();
                }
            }
        }
        return b;
    }

    public AsyncHandle a(Context context, AsyncCallback<StartResult, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("source", 3);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/voicectrl/start").b((List<KeyValuePair>) arrayList).a(), new JsonParser<StartResult>() {
            /* renamed from: a */
            public StartResult parse(JSONObject jSONObject) throws JSONException {
                String string = jSONObject.getString(AgentOptions.i);
                String string2 = jSONObject.getString("token");
                long j = jSONObject.getLong("expire");
                StartResult startResult = new StartResult();
                startResult.f22227a = string;
                startResult.b = string2;
                startResult.c = j;
                return startResult;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, String str, int i, AsyncCallback<GetVoiceTxtResult, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AgentOptions.i, str);
            jSONObject.put("offset", i);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/voicectrl/get_voice_txt").b((List<KeyValuePair>) arrayList).a(), new JsonParser<GetVoiceTxtResult>() {
            /* renamed from: a */
            public GetVoiceTxtResult parse(JSONObject jSONObject) throws JSONException {
                GetVoiceTxtResult getVoiceTxtResult = new GetVoiceTxtResult();
                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    GetVoiceTxtResult.TxtItem txtItem = new GetVoiceTxtResult.TxtItem();
                    JSONObject jSONObject2 = (JSONObject) optJSONArray.get(0);
                    txtItem.f22225a = jSONObject2.optString("voice_txt");
                    txtItem.b = Long.parseLong(jSONObject2.optString("time"));
                    getVoiceTxtResult.f22224a.add(txtItem);
                }
                return getVoiceTxtResult;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, String str, int i, long j, long j2, int i2, AsyncCallback<List<MicroHistoryInfo>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("source", i);
            jSONObject.put(SmartConfigDataProvider.F, j);
            jSONObject.put("limit", i2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/voicectrl/get_history_record").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<MicroHistoryInfo>>() {
            /* renamed from: a */
            public List<MicroHistoryInfo> parse(JSONObject jSONObject) throws JSONException {
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    return arrayList;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    MicroHistoryInfo microHistoryInfo = new MicroHistoryInfo();
                    microHistoryInfo.d = jSONObject2.optInt("speaker");
                    microHistoryInfo.f22226a = jSONObject2.optLong("time");
                    JSONObject optJSONObject = jSONObject2.optJSONObject("content");
                    if (optJSONObject != null) {
                        microHistoryInfo.c = optJSONObject.optString("data");
                        microHistoryInfo.b = optJSONObject.optString("type");
                        microHistoryInfo.e = optJSONObject.optInt("result", -1);
                    }
                    arrayList.add(microHistoryInfo);
                }
                return arrayList;
            }
        }, Crypto.RC4, asyncCallback);
    }
}
