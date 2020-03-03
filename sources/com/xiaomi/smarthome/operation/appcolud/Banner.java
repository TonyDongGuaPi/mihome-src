package com.xiaomi.smarthome.operation.appcolud;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.mi.global.shop.model.Tags;
import com.xiaomi.payment.data.MibiConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Banner {

    /* renamed from: a  reason: collision with root package name */
    public String f21046a = "";
    public String b = "";
    public String c = "";
    public long d = -1;
    public long e = -1;
    public Bitmap f;
    private int g = -1;
    private int h = -1;
    private int i = -1;
    private String j = "";

    public static List<Banner> a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if ("ok".equalsIgnoreCase(jSONObject.getString("result"))) {
                JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONArray(MibiConstants.gf);
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    Banner banner = new Banner();
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    banner.j = jSONObject2.optString("shortKey");
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("banner");
                    banner.f21046a = jSONObject3.getString(Tags.PaidService.IMG_URL);
                    banner.c = jSONObject3.getString("h5Url");
                    banner.g = jSONObject3.optInt("allUid");
                    banner.b = jSONObject3.optString("name");
                    banner.d = jSONObject3.getLong(Tags.Coupon.BEGIN_TIME);
                    banner.e = jSONObject3.getLong("endTime");
                    banner.h = jSONObject3.getInt("position");
                    banner.i = jSONObject3.getInt("status");
                    arrayList.add(banner);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            arrayList.clear();
        }
        return arrayList;
    }

    public boolean a() {
        return !b() && this.f != null && !TextUtils.isEmpty(this.c);
    }

    public boolean b() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis < this.d || currentTimeMillis > this.e;
    }
}
