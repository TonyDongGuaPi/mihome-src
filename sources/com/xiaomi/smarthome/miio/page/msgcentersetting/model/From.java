package com.xiaomi.smarthome.miio.page.msgcentersetting.model;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class From implements Serializable {
    private Map<String, Object> additionalProperties = new HashMap();
    private int hour = 23;
    private int min = 0;

    public static From parse(JSONObject jSONObject) {
        From from = new From();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (TextUtils.equals(next, "hour")) {
                from.setHour(Integer.valueOf(jSONObject.optInt("hour")));
            } else if (TextUtils.equals(next, "min")) {
                from.setMin(Integer.valueOf(jSONObject.optInt("min")));
            } else {
                try {
                    from.additionalProperties.put(next, jSONObject.get(next));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return from;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("hour", this.hour);
            jSONObject.put("min", this.min);
            for (String next : this.additionalProperties.keySet()) {
                jSONObject.put(next, this.additionalProperties.get(next));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public Integer getHour() {
        return Integer.valueOf(this.hour);
    }

    public void setHour(Integer num) {
        this.hour = num.intValue();
    }

    public Integer getMin() {
        return Integer.valueOf(this.min);
    }

    public void setMin(Integer num) {
        this.min = num.intValue();
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String str, Object obj) {
        this.additionalProperties.put(str, obj);
    }
}
