package com.mi.global.shop.buy.model;

import com.brentvatne.react.ReactVideoViewManager;
import com.mi.global.bbs.http.ParamKey;
import com.mi.global.shop.buy.BFLVerifyOTPFragment;
import com.mi.global.shop.buy.payu.Cards;
import com.mi.global.shop.buy.payu.PayU;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.scene.action.AutoSceneAction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PayModelUtil {
    public static List<EMIBank> a(JSONArray jSONArray, double d) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                EMIBank eMIBank = new EMIBank();
                eMIBank.b = jSONObject.optString("key");
                eMIBank.d = Boolean.valueOf(jSONObject.optBoolean(AutoSceneAction.f21495a));
                eMIBank.h = jSONObject.optString(BFLVerifyOTPFragment.d);
                eMIBank.c = jSONObject.optString("img");
                eMIBank.f6884a = Float.parseFloat(jSONObject.optString("min"));
                eMIBank.g = jSONObject.optString("tips");
                eMIBank.i = jSONObject.optString("reason");
                arrayList.add(eMIBank);
                JSONArray jSONArray2 = jSONObject.getJSONArray(ReactVideoViewManager.PROP_RATE);
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                    EMIPlan eMIPlan = new EMIPlan();
                    eMIBank.e.add(eMIPlan);
                    eMIPlan.f = jSONObject2.optString("desc");
                    eMIPlan.d = jSONObject2.get("monthPay").toString();
                    eMIPlan.b = jSONObject2.get(ParamKey.interest).toString() + Operators.MOD;
                    eMIPlan.f6885a = jSONObject2.optString("code");
                    eMIPlan.h = jSONObject2.optString("tips");
                    eMIPlan.c = jSONObject2.optString("interest_desc");
                    eMIPlan.e = jSONObject2.optString("months");
                }
            }
        }
        return arrayList;
    }

    public static void a(JSONArray jSONArray) throws JSONException {
        if (jSONArray != null && jSONArray.length() > 0) {
            HashSet hashSet = new HashSet();
            hashSet.addAll(Cards.m);
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.remove(jSONArray.get(i));
            }
            if (hashSet.size() != Cards.m.size()) {
                PayU.aw = hashSet;
            }
        }
    }
}
