package com.xiaomi.smarthome.listcamera.operation;

import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpRpc {

    /* renamed from: a  reason: collision with root package name */
    String f19329a;
    List<Object> b = new ArrayList();
    Param c;

    OpRpc(String str, List<Object> list, Param param) {
        this.f19329a = str;
        if (list != null) {
            this.b.addAll(list);
        }
        this.c = param;
    }

    /* access modifiers changed from: package-private */
    public void a(Object obj, MiioDeviceV2 miioDeviceV2, AsyncCallback<JSONObject, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
            jSONObject.put("method", this.f19329a);
            if (this.b.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (Object put : this.b) {
                    jSONArray.put(put);
                }
                jSONObject.put("params", jSONArray);
            } else if (this.c != null) {
                this.c.b(obj);
                jSONObject.put("params", this.c.a());
            } else {
                jSONObject.put("params", "");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        miioDeviceV2.a(jSONObject, asyncCallback);
    }
}
