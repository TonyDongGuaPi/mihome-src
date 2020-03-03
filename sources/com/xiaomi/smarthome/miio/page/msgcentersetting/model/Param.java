package com.xiaomi.smarthome.miio.page.msgcentersetting.model;

import android.text.TextUtils;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Param implements Serializable {
    private Map<String, Object> additionalProperties = new HashMap();
    private int deviceShare = 1;
    private int familyShare = 1;
    private int noInterrupt = 0;
    private NoInterruptTime noInterruptTime = new NoInterruptTime();
    private int scene = 1;
    private int shop = 1;

    public static Param parse(JSONObject jSONObject) {
        Param param = new Param();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (TextUtils.equals(next, PageUrl.j)) {
                param.setScene(Integer.valueOf(jSONObject.optInt(next)));
            } else if (TextUtils.equals(next, "device_share")) {
                param.setDeviceShare(Integer.valueOf(jSONObject.optInt(next)));
            } else if (TextUtils.equals(next, "family_share")) {
                param.setFamilyShare(Integer.valueOf(jSONObject.optInt(next)));
            } else if (TextUtils.equals(next, "shop")) {
                param.setShop(Integer.valueOf(jSONObject.optInt(next)));
            } else if (TextUtils.equals(next, "no_interrupt")) {
                param.setNoInterrupt(Integer.valueOf(jSONObject.optInt(next)));
            } else if (TextUtils.equals(next, "no_interrupt_time")) {
                param.setNoInterruptTime(NoInterruptTime.parse(jSONObject.optJSONObject("no_interrupt_time")));
            } else {
                try {
                    param.additionalProperties.put(next, jSONObject.get(next));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return param;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("no_interrupt_time", this.noInterruptTime.toJSON());
            jSONObject.put(PageUrl.j, this.scene);
            jSONObject.put("device_share", this.deviceShare);
            jSONObject.put("family_share", this.familyShare);
            jSONObject.put("shop", this.shop);
            jSONObject.put("no_interrupt", this.noInterrupt);
            for (String next : this.additionalProperties.keySet()) {
                jSONObject.put(next, this.additionalProperties.get(next));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public Integer getScene() {
        return Integer.valueOf(this.scene);
    }

    public void setScene(Integer num) {
        this.scene = num.intValue();
    }

    public Integer getDeviceShare() {
        return Integer.valueOf(this.deviceShare);
    }

    public void setDeviceShare(Integer num) {
        this.deviceShare = num.intValue();
    }

    public Integer getFamilyShare() {
        return Integer.valueOf(this.familyShare);
    }

    public void setFamilyShare(Integer num) {
        this.familyShare = num.intValue();
    }

    public Integer getShop() {
        return Integer.valueOf(this.shop);
    }

    public void setShop(Integer num) {
        this.shop = num.intValue();
    }

    public Integer getNoInterrupt() {
        return Integer.valueOf(this.noInterrupt);
    }

    public void setNoInterrupt(Integer num) {
        this.noInterrupt = num.intValue();
    }

    public NoInterruptTime getNoInterruptTime() {
        return this.noInterruptTime;
    }

    public void setNoInterruptTime(NoInterruptTime noInterruptTime2) {
        this.noInterruptTime = noInterruptTime2;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String str, Object obj) {
        this.additionalProperties.put(str, obj);
    }
}
