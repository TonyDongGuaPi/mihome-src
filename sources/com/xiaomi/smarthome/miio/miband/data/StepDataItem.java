package com.xiaomi.smarthome.miio.miband.data;

import com.xiaomi.smarthome.library.common.util.DateUtils;
import com.xiaomi.smarthome.miio.miband.utils.TimeUtils;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class StepDataItem implements Comparable {
    private static final String j = "walkTime";
    private static final String k = "walkDistance";
    private static final String l = "runTime";
    private static final String m = "runDistance";
    private static final String n = "runCalorie";
    private static final String o = "calorie";
    private static final String p = "step";
    private static final String q = "date";

    /* renamed from: a  reason: collision with root package name */
    public int f19465a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public String h;
    public long i;

    public static StepDataItem a(JSONObject jSONObject) {
        String optString;
        if (jSONObject == null || (optString = jSONObject.optString("date", (String) null)) == null) {
            return null;
        }
        StepDataItem stepDataItem = new StepDataItem();
        stepDataItem.f19465a = jSONObject.optInt(j, 0);
        stepDataItem.b = jSONObject.optInt(k, 0);
        stepDataItem.c = jSONObject.optInt(l, 0);
        stepDataItem.d = jSONObject.optInt(m, 0);
        stepDataItem.e = jSONObject.optInt(n, 0);
        stepDataItem.f = jSONObject.optInt(o, 0);
        stepDataItem.g = jSONObject.optInt(p, 0);
        stepDataItem.h = optString;
        stepDataItem.i = TimeUtils.a(optString, "yyyy-MM-dd");
        return stepDataItem;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(j, this.f19465a);
            jSONObject.put(k, this.b);
            jSONObject.put(l, this.c);
            jSONObject.put(m, this.d);
            jSONObject.put(n, this.e);
            jSONObject.put(o, this.f);
            jSONObject.put(p, this.g);
            jSONObject.put("date", this.h);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public boolean a(Object obj) {
        if (obj == null || !(obj instanceof StepDataItem)) {
            return false;
        }
        return this.h.equalsIgnoreCase(((StepDataItem) obj).h);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof StepDataItem)) {
            return false;
        }
        return this.h.equalsIgnoreCase(((StepDataItem) obj).h);
    }

    public StepDataItem() {
        this.f19465a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = null;
        this.i = 0;
    }

    public StepDataItem(Date date) {
        this.f19465a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        Date c2 = DateUtils.c(date);
        this.h = TimeUtils.a(c2, "yyyy-MM-dd");
        this.i = c2.getTime();
    }

    public int compareTo(Object obj) {
        if (obj == null || !(obj instanceof StepDataItem)) {
            return -1;
        }
        StepDataItem stepDataItem = (StepDataItem) obj;
        if (this.h.equalsIgnoreCase(stepDataItem.h)) {
            return 0;
        }
        if (this.i < stepDataItem.i) {
            return 1;
        }
        if (this.i > stepDataItem.i) {
            return -1;
        }
        return 0;
    }
}
