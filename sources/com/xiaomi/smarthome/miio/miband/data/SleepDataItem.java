package com.xiaomi.smarthome.miio.miband.data;

import com.xiaomi.smarthome.library.common.util.DateUtils;
import com.xiaomi.smarthome.miio.miband.utils.TimeUtils;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class SleepDataItem implements Comparable {
    private static final String h = "shallowSleepTime";
    private static final String i = "deepSleepTime";
    private static final String j = "sleepStartTime";
    private static final String k = "sleepEndTime";
    private static final String l = "wakeTime";
    private static final String m = "date";

    /* renamed from: a  reason: collision with root package name */
    public int f19464a;
    public int b;
    public int c;
    public long d;
    public long e;
    public String f;
    public long g;

    public SleepDataItem() {
        this.f19464a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = null;
        this.g = 0;
    }

    public SleepDataItem(Date date) {
        this.f19464a = 0;
        this.b = 0;
        this.c = 0;
        Date c2 = DateUtils.c(date);
        this.d = c2.getTime() / 1000;
        this.e = c2.getTime() / 1000;
        this.f = TimeUtils.a(c2, "yyyy-MM-dd");
        this.g = c2.getTime();
    }

    public static SleepDataItem a(JSONObject jSONObject) {
        String optString;
        if (jSONObject == null || (optString = jSONObject.optString("date", (String) null)) == null) {
            return null;
        }
        SleepDataItem sleepDataItem = new SleepDataItem();
        sleepDataItem.f19464a = jSONObject.optInt(h);
        sleepDataItem.b = jSONObject.optInt(i);
        sleepDataItem.c = jSONObject.optInt(l);
        sleepDataItem.d = jSONObject.optLong(j);
        sleepDataItem.e = jSONObject.optLong(k);
        sleepDataItem.f = optString;
        sleepDataItem.g = TimeUtils.a(optString, "yyyy-MM-dd");
        return sleepDataItem;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(h, this.f19464a);
            jSONObject.put(i, this.b);
            jSONObject.put(l, this.c);
            jSONObject.put(j, this.d);
            jSONObject.put(k, this.e);
            jSONObject.put("date", this.f);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public boolean a(Object obj) {
        if (obj == null || !(obj instanceof SleepDataItem)) {
            return false;
        }
        return this.f.equalsIgnoreCase(((SleepDataItem) obj).f);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SleepDataItem)) {
            return false;
        }
        return this.f.equalsIgnoreCase(((SleepDataItem) obj).f);
    }

    public int compareTo(Object obj) {
        if (obj == null || !(obj instanceof SleepDataItem)) {
            return -1;
        }
        SleepDataItem sleepDataItem = (SleepDataItem) obj;
        if (this.f.equalsIgnoreCase(sleepDataItem.f)) {
            return 0;
        }
        if (this.g < sleepDataItem.g) {
            return 1;
        }
        if (this.g > sleepDataItem.g) {
            return -1;
        }
        return 0;
    }
}
