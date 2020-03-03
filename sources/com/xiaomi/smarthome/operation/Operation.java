package com.xiaomi.smarthome.operation;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.mi.global.shop.model.Tags;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Operation {

    /* renamed from: a  reason: collision with root package name */
    public String f21043a = "-1";
    public String b = "";
    public String c = "";
    public String d = "";
    public int e = -1;
    public long f = -1;
    public long g = -1;
    @Deprecated
    public int h = 1;
    public Bitmap i;
    @Deprecated
    public String[] j = new String[0];
    public String k = "";
    public String l = "";
    public String m = "";
    public int n = 2;
    public String o = "";
    public boolean p = true;
    private String q = "";

    public static List<Operation> a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if ("ok".equalsIgnoreCase(jSONObject.getString("result"))) {
                JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONArray("list");
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    Operation operation = new Operation();
                    arrayList.add(operation);
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    operation.q = str;
                    operation.c = jSONObject2.optString("name");
                    operation.b = jSONObject2.getString(Tags.PaidService.IMG_URL);
                    operation.d = jSONObject2.getString("h5Url");
                    operation.e = jSONObject2.getInt("jump");
                    operation.f = jSONObject2.getLong(Tags.Coupon.BEGIN_TIME);
                    operation.g = jSONObject2.getLong("endTime");
                    operation.n = jSONObject2.optInt("showTitleType", 2);
                    operation.k = jSONObject2.optString("shareTitle", "");
                    operation.l = jSONObject2.optString("shareDesc", "");
                    operation.m = jSONObject2.optString("shareImgUrl", "");
                    operation.f21043a = jSONObject2.optString("type");
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            arrayList.clear();
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return !b() && this.i != null && this.e != -1 && !TextUtils.isEmpty(this.d);
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis < this.f || currentTimeMillis > this.g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Operation operation = (Operation) obj;
        if (this.e == operation.e && this.f == operation.f && this.g == operation.g && this.n == operation.n && this.b.equals(operation.b) && this.c.equals(operation.c) && this.d.equals(operation.d) && this.k.equals(operation.k) && this.l.equals(operation.l)) {
            return this.m.equals(operation.m);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((this.b.hashCode() * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e) * 31) + ((int) (this.f ^ (this.f >>> 32)))) * 31) + ((int) (this.g ^ (this.g >>> 32)))) * 31) + this.k.hashCode()) * 31) + this.l.hashCode()) * 31) + this.m.hashCode()) * 31) + this.n;
    }
}
