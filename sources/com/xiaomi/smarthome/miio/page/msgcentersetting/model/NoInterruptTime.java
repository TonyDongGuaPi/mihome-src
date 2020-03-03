package com.xiaomi.smarthome.miio.page.msgcentersetting.model;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NoInterruptTime implements Serializable {
    private Map<String, Object> additionalProperties = new HashMap();
    private From from = new From();
    private To to = new To();
    private List<Integer> wday = new ArrayList();

    public static NoInterruptTime parse(JSONObject jSONObject) {
        NoInterruptTime noInterruptTime = new NoInterruptTime();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (TextUtils.equals(next, "from")) {
                noInterruptTime.setFrom(From.parse(jSONObject.optJSONObject("from")));
            } else if (TextUtils.equals(next, "to")) {
                noInterruptTime.setTo(To.parse(jSONObject.optJSONObject("to")));
            } else if (TextUtils.equals(next, "wday")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("wday");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        noInterruptTime.wday.add(Integer.valueOf(optJSONArray.optInt(i)));
                    }
                }
            } else {
                try {
                    noInterruptTime.additionalProperties.put(next, jSONObject.get(next));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return noInterruptTime;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("from", this.from.toJSON());
            jSONObject.put("to", this.to.toJSON());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.wday.size(); i++) {
                jSONArray.put(this.wday.get(i).intValue());
            }
            jSONObject.put("wday", jSONArray);
            for (String next : this.additionalProperties.keySet()) {
                jSONObject.put(next, this.additionalProperties.get(next));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public From getFrom() {
        return this.from;
    }

    public void setFrom(From from2) {
        this.from = from2;
    }

    public To getTo() {
        return this.to;
    }

    public void setTo(To to2) {
        this.to = to2;
    }

    public List<Integer> getWday() {
        return this.wday;
    }

    public void setWday(List<Integer> list) {
        this.wday = list;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String str, Object obj) {
        this.additionalProperties.put(str, obj);
    }
}
