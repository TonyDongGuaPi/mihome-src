package com.xiaomi.smarthome.newui.card;

import android.util.Pair;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.newui.card.profile.ProfileCardType;
import com.xiaomi.smarthome.newui.card.profile.PropItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class Operation {

    /* renamed from: a  reason: collision with root package name */
    public Map<String, String> f20520a;
    public final String b;
    public final String[] c;
    public String d;
    public Param e;
    public List<Pair<String, Object>> f;
    public List<Pair<String, Object>> g;
    public String h;
    public String i;
    public String j;
    public int k;
    public String l;

    public Operation(JSONObject jSONObject, JSONArray jSONArray) {
        JSONObject optJSONObject = jSONObject.optJSONObject("button_name");
        if (optJSONObject != null) {
            this.f20520a = new HashMap();
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                this.f20520a.put(CardItemUtils.a(next), optJSONObject.optString(next));
            }
        }
        this.b = CardItemUtils.a(jSONObject.optString("prop_value"));
        JSONArray optJSONArray = jSONObject.optJSONArray("prop_values");
        if (optJSONArray != null) {
            String[] strArr = new String[optJSONArray.length()];
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                strArr[i2] = CardItemUtils.a(optJSONArray.optString(i2));
            }
            Arrays.sort(strArr);
            this.c = strArr;
        } else {
            this.c = null;
        }
        this.d = jSONObject.optString("method");
        this.e = new Param(jSONObject.optJSONArray("param"), jSONArray);
        JSONArray optJSONArray2 = jSONObject.optJSONArray("enable_status");
        if (optJSONArray2 != null) {
            this.f = new ArrayList();
            a(optJSONArray2, this.f);
        }
        JSONArray optJSONArray3 = jSONObject.optJSONArray("disable_status");
        if (optJSONArray3 != null) {
            this.g = new ArrayList();
            a(optJSONArray3, this.g);
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("button_image");
        if (optJSONObject2 != null) {
            this.h = optJSONObject2.optString("normal");
            this.i = optJSONObject2.optString("selected");
            this.j = optJSONObject2.optString("unable");
        }
        this.l = jSONObject.optString("action");
    }

    private void a(JSONArray jSONArray, List<Pair<String, Object>> list) {
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            list.add(new Pair(optJSONObject.optString("key"), optJSONObject.opt("value")));
        }
    }

    public void a(ProfileCardType profileCardType, Object obj, Device device, AsyncCallback<JSONObject, Error> asyncCallback) {
        ControlCardInfoManager.f().a(device, profileCardType, this.d, this.e, obj, asyncCallback);
    }

    public Object a(List<Operation> list, PropItem propItem) {
        if (this.e != null && this.e.b != null && this.e.b.size() == 1) {
            return this.e.b.get(0);
        }
        for (Operation next : list) {
            if (!next.a(String.valueOf(this.b))) {
                return next.b;
            }
        }
        return null;
    }

    public static Operation a(List<Operation> list, Object obj) {
        Operation operation = null;
        if (list == null || list.size() == 0) {
            return null;
        }
        String valueOf = String.valueOf(obj);
        Iterator<Operation> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Operation next = it.next();
            if (next.a(valueOf)) {
                operation = next;
                break;
            }
        }
        if (operation != null) {
            return operation;
        }
        if (list.size() > 1) {
            return list.get(1);
        }
        return list.size() == 1 ? list.get(0) : operation;
    }

    public boolean a(String str) {
        String a2 = CardItemUtils.a(str);
        return (this.b != null && this.b.equals(a2)) || (this.c != null && Arrays.binarySearch(this.c, a2) >= 0);
    }
}
