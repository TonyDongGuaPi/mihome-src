package com.xiaomi.smarthome.mibrain.sip;

import android.content.Context;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.mibrain.sip.model.GetVoiceTxtResult;
import com.xiaomi.smarthome.mibrain.sip.model.StartResult;
import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SipApi {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f10706a = new Object();
    private static SipApi b;

    private SipApi() {
    }

    public static SipApi a() {
        if (b == null) {
            synchronized (f10706a) {
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
            jSONObject.put("source", 1);
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
                startResult.f10711a = string;
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
                    txtItem.f10710a = jSONObject2.optString("voice_txt");
                    txtItem.b = Long.parseLong(jSONObject2.optString("time"));
                    getVoiceTxtResult.f10709a.add(txtItem);
                }
                return getVoiceTxtResult;
            }
        }, Crypto.RC4, asyncCallback);
    }
}
