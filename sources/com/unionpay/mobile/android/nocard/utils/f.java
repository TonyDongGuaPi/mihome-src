package com.unionpay.mobile.android.nocard.utils;

import android.text.TextUtils;
import com.unionpay.mobile.android.model.a;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.smarthome.family.ShareDeviceActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import miuipub.content.ExtraIntent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class f {
    public static int a(b bVar, JSONObject jSONObject) {
        bVar.bt = j.a(jSONObject, "promotion_change_info");
        bVar.z = j.d(jSONObject, "rules");
        bVar.ad = j.d(jSONObject, "available_area_codes");
        bVar.D = j.d(jSONObject, "entrust_rules");
        bVar.E = j.a(jSONObject, "pre_cmd");
        bVar.A = j.a(jSONObject, "title");
        bVar.C = j.c(jSONObject, "rules_button");
        bVar.al = j.c(jSONObject, "service_checkbox");
        bVar.am = j.c(jSONObject, "bind_card_checkbox");
        String a2 = j.a(jSONObject, "timeout_msg");
        if (a2 != null && !TextUtils.isEmpty(a2)) {
            bVar.ak = a2;
        }
        bVar.p = new HashMap<>();
        JSONObject c = j.c(jSONObject, "f55");
        String a3 = j.a(c, "order_amount");
        HashMap<String, String> hashMap = bVar.p;
        if (a3 == null || a3.length() <= 0) {
            a3 = Constant.DEFAULT_BALANCE;
        }
        hashMap.put("trans_amt", a3);
        String a4 = j.a(c, "order_currency");
        HashMap<String, String> hashMap2 = bVar.p;
        if (a4 == null || a4.length() <= 0) {
            a4 = "0156";
        }
        hashMap2.put("trans currcy code", a4);
        String a5 = j.a(c, "trans_type");
        HashMap<String, String> hashMap3 = bVar.p;
        if (a5 == null || a5.length() <= 0) {
            a5 = "00";
        }
        hashMap3.put("trans_type", a5);
        String a6 = j.a(c, "mer_name");
        HashMap<String, String> hashMap4 = bVar.p;
        if (a6 == null || a6.length() <= 0) {
            a6 = "";
        }
        hashMap4.put("mer_name", a6);
        bVar.aq = j.a(jSONObject, "pan");
        bVar.bf = j.a(jSONObject, "encrypt_key");
        bVar.bg = j.a(jSONObject, "auth_id");
        String a7 = j.a(jSONObject, "fail_continue");
        if (a7 != null && a7.equalsIgnoreCase("0")) {
            bVar.F = true;
        }
        return ((bVar.z == null || bVar.z.length() <= 0) && (bVar.D == null || bVar.D.length() <= 0)) ? 2 : 0;
    }

    public static int a(b bVar, JSONObject jSONObject, boolean z) {
        if (!z) {
            bVar.G = jSONObject;
        }
        return a(bVar, jSONObject);
    }

    public static e a(JSONObject jSONObject) {
        com.unionpay.mobile.android.model.f fVar = new com.unionpay.mobile.android.model.f();
        fVar.a("promotion", j.c(jSONObject, "promotion"));
        fVar.a("instalment", j.c(jSONObject, "instalment"));
        fVar.a("promotion_instalment_msgbox", j.c(jSONObject, "promotion_instalment_msgbox"));
        return fVar;
    }

    public static boolean a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return false;
        }
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String a2 = j.a(jSONObject, "type");
                String a3 = j.a(jSONObject, ShareDeviceActivity.KEY_SHARE_TYPE_READONLY);
                if ("pan".equalsIgnoreCase(a2)) {
                    return "true".equalsIgnoreCase(a3);
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static int b(b bVar, JSONObject jSONObject) {
        int i = jSONObject == null ? 2 : 0;
        if (bVar.ab == null) {
            bVar.ab = new ArrayList();
        }
        bVar.ab.clear();
        List<JSONArray> e = j.e(jSONObject, "user_cards");
        if (e.size() > 0) {
            for (int i2 = 0; i2 < e.size(); i2++) {
                bVar.ab.add(new a(j.a(e.get(i2), 0), j.a(e.get(i2), 1), j.a(e.get(i2), 2), (byte) 0));
            }
        }
        bVar.ad = j.d(jSONObject, "available_area_codes");
        bVar.bt = j.a(jSONObject, "promotion_change_info");
        bVar.ac = j.d(jSONObject, "user_info");
        bVar.z = j.d(jSONObject, "rules");
        bVar.Z = j.c(jSONObject, ExtraIntent.ay);
        bVar.aa = j.c(jSONObject, "bind_url");
        bVar.ae = j.a(jSONObject, "empty_info");
        bVar.aY = j.a(jSONObject, "add_card_tip");
        bVar.aq = j.a(jSONObject, "pan");
        return i;
    }

    public static boolean c(b bVar, JSONObject jSONObject) {
        bVar.aF = null;
        bVar.aF = j.c(jSONObject, "cardExpireMsgBox");
        if (bVar.aF == null) {
            bVar.aF = j.c(jSONObject, "openByCounterMsgBox");
        }
        if (bVar.aF == null) {
            bVar.aF = j.c(jSONObject, "restrictPayMsgBox");
        }
        if (bVar.aF == null) {
            return false;
        }
        bVar.aG = j.a(bVar.aF, "title");
        bVar.aH = j.a(bVar.aF, "text");
        JSONObject c = j.c(bVar.aF, "func");
        JSONObject c2 = j.c(bVar.aF, "cancel");
        bVar.aK = j.a(c, "label");
        bVar.aL = j.a(c, "action");
        bVar.aI = j.a(c2, "label");
        bVar.aJ = j.a(c2, "action");
        return true;
    }
}
