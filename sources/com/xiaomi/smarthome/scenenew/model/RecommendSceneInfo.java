package com.xiaomi.smarthome.scenenew.model;

import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.scene.action.AutoSceneAction;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecommendSceneInfo {

    /* renamed from: a  reason: collision with root package name */
    public JSONObject f21988a = new JSONObject();
    public JSONObject b = new JSONObject();
    public List<RecommendSceneItem> c = new ArrayList();

    public static class RecommendSceneItem {

        /* renamed from: a  reason: collision with root package name */
        public String f21990a;
        public String b;
        public int c;
        public int d;
        public int e;
        public int f;
        public String g;
        public String h;
        List<ConditionActionItem> i = new ArrayList();
        List<ConditionActionItem> j = new ArrayList();
    }

    private static boolean a(int i) {
        return i == 0 || i == 1;
    }

    public static RecommendSceneInfo a(JSONObject jSONObject) {
        RecommendSceneInfo recommendSceneInfo = new RecommendSceneInfo();
        JSONArray optJSONArray = jSONObject.optJSONArray("userRecom");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null) {
            return recommendSceneInfo;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            RecommendSceneItem recommendSceneItem = new RecommendSceneItem();
            recommendSceneItem.f = optJSONObject.optInt("ua", -1);
            if (a(recommendSceneItem.f)) {
                recommendSceneItem.f21990a = optJSONObject.optString("sr_id");
                recommendSceneItem.b = optJSONObject.optString("intro");
                recommendSceneItem.c = optJSONObject.optInt("st_id");
                recommendSceneItem.d = optJSONObject.optInt(AutoSceneAction.f21495a);
                recommendSceneItem.e = optJSONObject.optInt("enable_push");
                recommendSceneItem.g = optJSONObject.optString("jpg", "");
                recommendSceneItem.h = optJSONObject.optString("gif");
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("launch");
                if (optJSONArray2 != null) {
                    recommendSceneItem.i.addAll(ConditionActionItem.a(optJSONArray2));
                }
                JSONArray optJSONArray3 = optJSONObject.optJSONArray("action");
                if (optJSONArray3 != null) {
                    recommendSceneItem.j.addAll(ConditionActionItem.a(optJSONArray3));
                }
                arrayList.add(recommendSceneItem);
            }
        }
        recommendSceneInfo.c.addAll(arrayList);
        recommendSceneInfo.b = jSONObject.optJSONObject("sc_ids");
        recommendSceneInfo.f21988a = jSONObject.optJSONObject("sa_ids");
        return recommendSceneInfo;
    }

    public static class ConditionActionItem {

        /* renamed from: a  reason: collision with root package name */
        JSONObject f21989a = new JSONObject();
        String b;
        JSONArray c = new JSONArray();
        String d;
        String e;
        int f;

        public static List<ConditionActionItem> a(JSONArray jSONArray) {
            ArrayList arrayList = new ArrayList();
            if (jSONArray == null || jSONArray.length() == 0) {
                return arrayList;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                ConditionActionItem conditionActionItem = new ConditionActionItem();
                conditionActionItem.b = optJSONObject.optString("name", "");
                conditionActionItem.f21989a = optJSONObject.optJSONObject("model_list");
                conditionActionItem.c = optJSONObject.optJSONArray(ApiConst.j);
                conditionActionItem.f = optJSONObject.optInt("type", 0);
                conditionActionItem.d = optJSONObject.optString("src", "");
                conditionActionItem.e = optJSONObject.optString("key", "");
                arrayList.add(conditionActionItem);
            }
            return arrayList;
        }
    }
}
