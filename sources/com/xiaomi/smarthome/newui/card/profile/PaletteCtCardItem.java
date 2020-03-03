package com.xiaomi.smarthome.newui.card.profile;

import android.view.ViewGroup;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PaletteCtCardItem extends ProfileCardItem {
    public void a(String str, Object obj) {
    }

    public PaletteCtCardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        ProfileCard profileCard = (ProfileCard) l();
    }

    public void i() {
        super.i();
        this.E = null;
    }

    public Object a() {
        return ((ProfileCardType) this.G).b(k(), this.w);
    }

    public void a(Object obj, Device device, AsyncCallback<JSONObject, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
            jSONObject.put("method", "set_ct_abx");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(0, obj);
            jSONArray.put(1, "smooth");
            jSONArray.put(2, 500);
            jSONObject.put("params", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().b(device.did, device.token, jSONObject.toString(), asyncCallback);
    }
}
