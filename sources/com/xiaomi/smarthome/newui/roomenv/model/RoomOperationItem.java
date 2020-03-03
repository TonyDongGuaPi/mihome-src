package com.xiaomi.smarthome.newui.roomenv.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class RoomOperationItem {

    /* renamed from: a  reason: collision with root package name */
    private String f20708a;
    private String b;
    private List<String> c;
    private JSONObject d;

    public static RoomOperationItem a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        RoomOperationItem roomOperationItem = new RoomOperationItem();
        roomOperationItem.b(jSONObject.optString("option_type"));
        roomOperationItem.b(jSONObject.optJSONObject("param"));
        roomOperationItem.a(jSONObject.optString("option_des"));
        JSONArray optJSONArray = jSONObject.optJSONArray("dids");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                String optString = optJSONArray.optString(i);
                if (!TextUtils.isEmpty(optString)) {
                    arrayList.add(optString);
                }
            }
            roomOperationItem.a((List<String>) arrayList);
        }
        return roomOperationItem;
    }

    public String a() {
        return this.f20708a;
    }

    public void a(String str) {
        this.f20708a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public List<String> c() {
        return this.c;
    }

    public void a(List<String> list) {
        this.c = list;
    }

    public JSONObject d() {
        return this.d;
    }

    public void b(JSONObject jSONObject) {
        this.d = jSONObject;
    }
}
