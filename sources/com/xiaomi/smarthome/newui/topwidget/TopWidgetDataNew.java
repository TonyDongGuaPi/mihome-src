package com.xiaomi.smarthome.newui.topwidget;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.mi.global.shop.model.Tags;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TopWidgetDataNew {

    /* renamed from: a  reason: collision with root package name */
    public List<Detail> f20739a = new ArrayList();
    public int b;
    public int c;

    public static TopWidgetDataNew a(JSONObject jSONObject) {
        TopWidgetDataNew topWidgetDataNew = new TopWidgetDataNew();
        if (jSONObject == null) {
            return null;
        }
        topWidgetDataNew.b = jSONObject.optInt("oldest_time");
        topWidgetDataNew.c = jSONObject.optInt("latest_time");
        JSONArray optJSONArray = jSONObject.optJSONArray("description_list");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                List<Detail> a2 = Detail.a(optJSONArray.optJSONObject(i));
                if (a2 != null && a2.size() > 0) {
                    topWidgetDataNew.f20739a.addAll(a2);
                }
            }
        }
        return topWidgetDataNew;
    }

    private static String a(Detail detail) {
        return detail.b + detail.d;
    }

    public void a() {
        if (this.f20739a != null && this.f20739a.size() > 0) {
            b(this.f20739a);
            Collections.sort(this.f20739a);
            a(this.f20739a);
        }
    }

    private List<Detail> a(List<Detail> list) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).e != null && list.get(size).e.d > 0 && currentTimeMillis - ((long) list.get(size).c) > list.get(size).e.d) {
                list.remove(size);
            }
        }
        return list;
    }

    private List<Detail> b(List<Detail> list) {
        HashMap hashMap = new HashMap();
        this.b = list.get(0).c;
        this.c = this.b;
        for (int size = list.size() - 1; size >= 0; size--) {
            String a2 = a(list.get(size));
            Detail detail = list.get(size);
            if (detail.c > this.c) {
                this.c = detail.c;
            }
            if (detail.c < this.b) {
                this.b = detail.c;
            }
            if (hashMap.containsKey(a2)) {
                if (TextUtils.isEmpty(detail.f20740a)) {
                    Detail detail2 = (Detail) hashMap.remove(a2);
                    if (detail2 != null) {
                        list.remove(detail2);
                    }
                    list.remove(size);
                } else if (list.get(size).c > ((Detail) hashMap.get(a2)).c) {
                    Detail detail3 = (Detail) hashMap.remove(a2);
                    if (detail3 != null) {
                        list.remove(detail3);
                    }
                    hashMap.put(a2, detail);
                } else {
                    list.remove(size);
                }
            } else if (TextUtils.isEmpty(detail.f20740a)) {
                list.remove(size);
            } else {
                hashMap.put(a2, detail);
            }
        }
        return list;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("oldest_time", this.b);
            jSONObject.put("latest_time", this.c);
            JSONArray jSONArray = new JSONArray();
            if (this.f20739a != null) {
                for (int i = 0; i < this.f20739a.size(); i++) {
                    jSONArray.put(this.f20739a.get(i).a());
                }
            }
            jSONObject.put("description_list", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static class Detail implements Comparable<Detail> {

        /* renamed from: a  reason: collision with root package name */
        public String f20740a;
        public String b;
        public int c;
        public String d;
        public ExtraInfo e;
        public String f;
        public String g;
        public String h;

        public static List<Detail> a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            String optString = jSONObject.optString("did");
            String optString2 = jSONObject.optString("room_id");
            JSONArray optJSONArray = jSONObject.optJSONArray(Tags.MiPhoneDetails.DETAILS);
            ArrayList arrayList = new ArrayList();
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    Detail detail = new Detail();
                    detail.b = optString;
                    detail.f20740a = optJSONArray.optJSONObject(i).optString("description");
                    if (!TextUtils.isEmpty(detail.f20740a)) {
                        detail.f20740a = detail.f20740a.trim();
                    }
                    detail.c = optJSONArray.optJSONObject(i).optInt("timestamp");
                    detail.d = optJSONArray.optJSONObject(i).optString(XmBluetoothRecord.TYPE_PROP);
                    detail.e = ExtraInfo.a(optJSONArray.optJSONObject(i).optString("extra"));
                    if (TextUtils.isEmpty(optJSONArray.optJSONObject(i).optString("value"))) {
                        detail.f = "";
                    } else {
                        detail.f = String.valueOf((int) (optJSONArray.optJSONObject(i).optDouble("value") + 0.5d));
                    }
                    detail.g = optJSONArray.optJSONObject(i).optString("attr_type");
                    detail.h = optString2;
                    arrayList.add(detail);
                }
            }
            return arrayList;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("did", this.b);
                jSONObject.put("room_id", this.h);
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("description", this.f20740a);
                jSONObject2.put("timestamp", this.c);
                jSONObject2.put(XmBluetoothRecord.TYPE_PROP, this.d);
                jSONObject2.put("extra", this.e.a());
                jSONObject2.put("value", this.f);
                jSONObject2.put("attr_type", this.g);
                jSONArray.put(jSONObject2);
                jSONObject.put(Tags.MiPhoneDetails.DETAILS, jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }

        /* renamed from: a */
        public int compareTo(@NonNull Detail detail) {
            if (detail.c != this.c) {
                return detail.c - this.c;
            }
            if (!TextUtils.equals(detail.b, this.b)) {
                if (detail.b != null) {
                    return detail.b.compareTo(this.b);
                }
                if (this.b != null) {
                    return this.b.compareTo(detail.b);
                }
            }
            if (TextUtils.equals(detail.d, this.d)) {
                return 0;
            }
            if (detail.d != null) {
                return detail.d.compareTo(this.d);
            }
            if (this.d != null) {
                return this.d.compareTo(detail.d);
            }
            return 0;
        }
    }

    public static class ExtraInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f20741a;
        public String b;
        public String c;
        public long d = 0;

        public static ExtraInfo a(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                ExtraInfo extraInfo = new ExtraInfo();
                extraInfo.f20741a = jSONObject.optString("home_icon");
                extraInfo.b = jSONObject.optString("item_icon");
                extraInfo.c = jSONObject.optString("item_colour");
                extraInfo.d = jSONObject.optLong("expireSeconds");
                return extraInfo;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("home_icon", this.f20741a);
                jSONObject.put("item_icon", this.b);
                jSONObject.put("item_colour", this.c);
                jSONObject.put("expireSeconds", this.d);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }
}
