package com.xiaomi.smarthome.framework.plugin.web.command;

import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import org.json.JSONException;
import org.json.JSONObject;

public class RpcCommand extends BaseCommand {
    public void c() {
        try {
            JSONObject jSONObject = new JSONObject(a());
            DeviceApi.getInstance().rpcAsyncRemote(SHApplication.getAppContext(), jSONObject.optString("did"), jSONObject.optJSONObject("param").toString(), new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(final JSONObject jSONObject) {
                    RpcCommand.this.e.post(new Runnable() {
                        public void run() {
                            RpcCommand.this.a((Object) jSONObject);
                        }
                    });
                }

                public void onFailure(Error error) {
                    RpcCommand.this.e.post(new Runnable() {
                        public void run() {
                            RpcCommand.this.d();
                        }
                    });
                }
            });
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error", -1);
            a((Object) jSONObject);
        } catch (JSONException unused) {
        }
    }
}
