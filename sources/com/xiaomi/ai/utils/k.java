package com.xiaomi.ai.utils;

import android.text.TextUtils;
import com.alipay.sdk.app.statistic.c;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase;
import com.mi.global.shop.model.Tags;
import com.xiaomi.ai.HTTPCallback;
import com.xiaomi.ai.TrackInfo;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class k {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9955a = "https://api.ai.xiaomi.com/track/v2";
    public static final String b = "https://api-preview.ai.xiaomi.com/track/v2";
    public static final String c = "https://api.ai.xiaomi.com/track/authmiot";
    String d;
    String e;
    HTTPCallback f;
    int g;

    public k(String str, String str2) {
        this.d = str;
        this.e = str2;
    }

    private String a(int i, int i2) {
        if (i == 0) {
            return f9955a;
        }
        if (i == 1) {
            return b;
        }
        return null;
    }

    public int a(HTTPCallback hTTPCallback, TrackInfo trackInfo, String str, String str2, int i) {
        String a2 = a(trackInfo, str);
        if (TextUtils.isEmpty(a2)) {
            Log.g("Track", "generate Data failed");
            return -1;
        } else if (TextUtils.isEmpty(str2)) {
            Log.g("Track", "Token is null");
            return -2;
        } else {
            Log.f("UploadTrack", a2);
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", "application/json");
            hashMap.put("Date", f.a());
            hashMap.put("Authorization", str2);
            new Thread(new l(this, hTTPCallback, a(this.g, i), hashMap, a2)).start();
            return 0;
        }
    }

    public HTTPCallback a() {
        return this.f;
    }

    public String a(TrackInfo trackInfo, String str) {
        JSONObject a2 = a(trackInfo);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("app_id", this.d);
            jSONObject.put("did", this.e);
            jSONObject.put("uid", str);
            if (trackInfo.a() == null) {
                jSONObject.put("data", a2);
            } else {
                jSONObject.put("data", trackInfo.a());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public JSONObject a(TrackInfo trackInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", trackInfo.b());
            jSONObject.put("request_id", trackInfo.c());
            jSONObject.put(MediaVariationsIndexDatabase.IndexEntry.COLUMN_NAME_RESOURCE_ID, trackInfo.d());
            jSONObject.put(c.c, trackInfo.e());
            jSONObject.put("load_time", trackInfo.f());
            jSONObject.put(SmartConfigDataProvider.F, trackInfo.g());
            jSONObject.put(Tags.ReserveOrder.END_TIME, trackInfo.h());
            jSONObject.put("position", trackInfo.i());
            jSONObject.put("action_type", trackInfo.j());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void a(int i) {
        this.g = i;
    }

    public void a(HTTPCallback hTTPCallback) {
        this.f = hTTPCallback;
    }
}
