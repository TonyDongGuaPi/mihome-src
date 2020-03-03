package com.xiaomi.smarthome.feedback;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedbackList {

    /* renamed from: a  reason: collision with root package name */
    public int f15969a;
    public int b;
    public ArrayList<FeedbackItem> c;

    public static class FeedbackItem {

        /* renamed from: a  reason: collision with root package name */
        public String f15970a;
        public String b;
        public boolean c;
        public String d;
        public int e;
        public String f;

        public static FeedbackItem a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            FeedbackItem feedbackItem = new FeedbackItem();
            feedbackItem.f15970a = jSONObject.optString("id");
            feedbackItem.b = jSONObject.optString("create_time");
            feedbackItem.c = jSONObject.optBoolean("is_new");
            feedbackItem.d = jSONObject.optString("model");
            feedbackItem.e = jSONObject.optInt("status");
            feedbackItem.f = jSONObject.optString("content");
            return feedbackItem;
        }
    }

    public static FeedbackList a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        FeedbackList feedbackList = new FeedbackList();
        feedbackList.f15969a = jSONObject.optInt("page");
        feedbackList.b = jSONObject.optInt("totalPage");
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        feedbackList.c = new ArrayList<>();
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                FeedbackItem a2 = FeedbackItem.a(optJSONArray.optJSONObject(i));
                if (a2 != null) {
                    feedbackList.c.add(a2);
                }
            }
        }
        return feedbackList;
    }
}
