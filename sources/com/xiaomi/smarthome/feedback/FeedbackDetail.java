package com.xiaomi.smarthome.feedback;

import org.json.JSONObject;

public class FeedbackDetail {
    public static final int h = 0;
    public static final int i = 1;

    /* renamed from: a  reason: collision with root package name */
    public String f15949a;
    public String b;
    public boolean c;
    public String d;
    public int e;
    public String f;
    public String g;

    public static FeedbackDetail a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        FeedbackDetail feedbackDetail = new FeedbackDetail();
        feedbackDetail.f15949a = jSONObject.optString("id");
        feedbackDetail.b = jSONObject.optString("create_time");
        feedbackDetail.c = jSONObject.optBoolean("is_new");
        feedbackDetail.d = jSONObject.optString("model");
        feedbackDetail.e = jSONObject.optInt("status");
        feedbackDetail.f = jSONObject.optString("reply");
        feedbackDetail.g = jSONObject.optString("reply_time");
        return feedbackDetail;
    }
}
