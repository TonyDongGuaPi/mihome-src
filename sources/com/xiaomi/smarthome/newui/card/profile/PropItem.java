package com.xiaomi.smarthome.newui.card.profile;

import android.text.TextUtils;
import com.mi.global.shop.base.request.HostManager;
import com.xiaomi.smarthome.MainPageOpManager;
import com.xiaomi.smarthome.newui.card.CardItemUtils;
import com.xiaomi.smarthome.newui.card.Param;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class PropItem {

    /* renamed from: a  reason: collision with root package name */
    public String f20557a;
    public Map<String, PropItem> b;
    public Map<String, String> c;
    public Map<String, Integer> d = new HashMap();
    public double e = 1.0d;
    public String f = MainPageOpManager.f13390a;
    public String g;
    public List<Integer> h;
    public List<PropExtraItem> i;
    public List<Object> j;
    public Param k;
    public Map<String, String> l;
    public String m;
    public TreeMap<String, String> n;

    public PropItem() {
    }

    public PropItem(JSONObject jSONObject) {
        if (jSONObject != null) {
            if (jSONObject.optJSONArray("subProps") != null) {
                this.f20557a = jSONObject.optString("prop_key");
                this.b = new HashMap();
                JSONArray optJSONArray = jSONObject.optJSONArray("subProps");
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    PropItem a2 = a(optJSONArray.optJSONObject(i2));
                    this.b.put(a2.f20557a, a2);
                }
            } else {
                PropItem a3 = a(jSONObject);
                this.f20557a = a3.f20557a;
                this.c = a3.c;
                this.e = a3.e;
                this.f = a3.f;
                this.g = a3.g;
                this.i = a3.i;
                this.j = a3.j;
                this.k = a3.k;
                this.l = a3.l;
                this.m = a3.m;
                this.n = a3.n;
                this.d = a3.d;
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("supportType");
            if (optJSONArray2 != null) {
                this.h = new ArrayList();
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    this.h.add(Integer.valueOf(optJSONArray2.optInt(i3)));
                }
            }
        }
    }

    private PropItem a(JSONObject jSONObject) {
        PropItem propItem = new PropItem();
        propItem.f20557a = jSONObject.optString("prop_key");
        JSONObject optJSONObject = jSONObject.optJSONObject("prop_name");
        if (optJSONObject != null) {
            propItem.c = new HashMap();
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                propItem.c.put(CardItemUtils.a(next), optJSONObject.optString(next));
            }
        }
        propItem.e = jSONObject.optDouble(HostManager.Parameters.Keys.DEVICE_RATIO, 1.0d);
        propItem.f = jSONObject.optString(IjkMediaMeta.IJKM_KEY_FORMAT);
        propItem.g = jSONObject.optString("prop_unit");
        JSONArray optJSONArray = jSONObject.optJSONArray("prop_extra");
        int i2 = 0;
        if (optJSONArray != null) {
            propItem.i = new ArrayList();
            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                propItem.i.add(new PropExtraItem(optJSONArray.optJSONObject(i3)));
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("switchStatus");
        if (optJSONArray2 != null) {
            propItem.j = new ArrayList();
            while (i2 < optJSONArray2.length()) {
                try {
                    propItem.j.add(optJSONArray2.opt(i2));
                    i2++;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        propItem.k = new Param((JSONArray) null, jSONObject.optJSONArray("prop_value_type"));
        JSONObject optJSONObject2 = jSONObject.optJSONObject("prop_status_name");
        if (optJSONObject2 != null) {
            propItem.l = new HashMap();
            Iterator<String> keys2 = optJSONObject2.keys();
            while (keys2.hasNext()) {
                String next2 = keys2.next();
                propItem.l.put(next2, optJSONObject2.optString(next2));
            }
        }
        propItem.m = jSONObject.optString("show_type");
        JSONObject optJSONObject3 = jSONObject.optJSONObject("event_list");
        if (optJSONObject3 != null) {
            Iterator<String> keys3 = optJSONObject3.keys();
            while (keys3.hasNext()) {
                String next3 = keys3.next();
                propItem.d.put(next3, Integer.valueOf(optJSONObject3.optInt(next3)));
            }
        }
        return propItem;
    }

    public boolean a() {
        return TextUtils.equals(this.m, "date");
    }

    public boolean b() {
        return !Double.isNaN(this.e);
    }

    public static class PropExtraItem {

        /* renamed from: a  reason: collision with root package name */
        public Object f20558a;
        public Map<String, String> b;
        public double c;
        public double d;
        public String e;
        public String f;

        public PropExtraItem(JSONObject jSONObject) {
            this.f20558a = jSONObject.opt("value");
            this.e = jSONObject.optString("text_color");
            this.f = jSONObject.optString("image");
            JSONObject optJSONObject = jSONObject.optJSONObject("param_range");
            if (optJSONObject != null) {
                this.c = optJSONObject.optDouble("min");
                this.d = optJSONObject.optDouble("max");
            }
            this.b = new HashMap();
            JSONObject optJSONObject2 = jSONObject.optJSONObject("desc");
            if (optJSONObject2 != null) {
                Iterator<String> keys = optJSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    this.b.put(CardItemUtils.a(next), optJSONObject2.optString(next));
                }
            }
        }
    }
}
