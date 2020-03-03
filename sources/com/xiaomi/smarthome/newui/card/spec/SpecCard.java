package com.xiaomi.smarthome.newui.card.spec;

import android.util.Pair;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecDevice;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class SpecCard extends Card<SpecCardType> {
    public static final String d = "mijia-card";
    private static final int f = 5;
    public String[] e;
    private int g;

    public SpecCard(JSONObject jSONObject, Map<String, SpecCardType> map) {
        if (jSONObject != null && map != null) {
            this.g = jSONObject.optInt("instance_type");
            this.b = jSONObject.optInt("layout_type");
            JSONArray optJSONArray = jSONObject.optJSONArray("card_layout");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("disable_type");
            if (optJSONArray2 != null) {
                int length = optJSONArray2.length();
                this.e = new String[length];
                for (int i = 0; i < length; i++) {
                    this.e[i] = optJSONArray2.optString(i);
                }
                Arrays.sort(this.e);
            }
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    String optString = optJSONArray.optString(i2);
                    SpecCardType specCardType = map.get(optString);
                    if (specCardType == null) {
                        LogUtilGrey.a("mijia-card", "SpecCard not match card_layout:" + optString);
                        this.c.clear();
                        return;
                    }
                    this.c.add(specCardType);
                }
            }
        }
    }

    public ArrayList<CardItem> a(Device device) {
        MiotSpecCardManager f2 = MiotSpecCardManager.f();
        SpecDevice a2 = f2.a(device);
        SpecCardSelector a3 = f2.a(a2);
        ArrayList<CardItem> arrayList = new ArrayList<>();
        if (a3 == null) {
            LogUtilGrey.a("mijia-card", "createCardItem specCard null" + device);
            LogUtilGrey.a("mijia-card", "createCardItem specCard null" + this.c);
            return arrayList;
        }
        Pair[][] e2 = a3.e(a2);
        List list = this.c;
        int size = list.size();
        if (e2 == null) {
            LogUtilGrey.a("mijia-card", "createCardItem pairs null" + device);
            LogUtilGrey.a("mijia-card", "createCardItem pairs null" + this.c);
        } else if (size != e2.length) {
            LogUtilGrey.a("mijia-card", "createCardItem pairSize:" + e2.length + "  typeSize:" + size + "  " + device);
            LogUtilGrey.a("mijia-card", "createCardItem pairSize:" + e2.length + "  typeSize:" + size + "  " + this.c);
        } else {
            for (int i = 0; i < size; i++) {
                arrayList.add(((SpecCardType) list.get(i)).a((Pair<SpecService, ? extends Spec.SpecItem>[]) e2[i]));
            }
        }
        return arrayList;
    }

    public List<SpecCardType> a() {
        if (this.g == 5) {
            return this.c;
        }
        return null;
    }

    public List<SpecCardType> b() {
        if (this.g != 5) {
            return this.c;
        }
        return null;
    }
}
