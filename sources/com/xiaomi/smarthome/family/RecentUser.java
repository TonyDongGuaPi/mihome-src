package com.xiaomi.smarthome.family;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecentUser {

    /* renamed from: a  reason: collision with root package name */
    public Long f15716a;
    public long b;

    public static List<RecentUser> a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("result");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            RecentUser recentUser = new RecentUser();
            try {
                recentUser.f15716a = Long.valueOf(optJSONArray.getJSONObject(i).optLong("receiver", -1));
                recentUser.b = optJSONArray.getJSONObject(i).optLong("last_modify");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            arrayList.add(recentUser);
        }
        return arrayList;
    }

    public static List<RecentUser> b(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            RecentUser recentUser = new RecentUser();
            try {
                recentUser.f15716a = Long.valueOf(optJSONArray.getJSONObject(i).optLong("uid", -1));
                recentUser.b = optJSONArray.getJSONObject(i).optLong("time_stamp");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            arrayList.add(recentUser);
        }
        return arrayList;
    }
}
