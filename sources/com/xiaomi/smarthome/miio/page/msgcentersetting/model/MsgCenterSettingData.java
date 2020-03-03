package com.xiaomi.smarthome.miio.page.msgcentersetting.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class MsgCenterSettingData implements Serializable {
    public static final String DEFAULT_DATA = "{\n    \"scene\": 1,\n    \"device_share\": 1,\n    \"family_share\": 1,\n    \"shop\": 1,\n    \"no_interrupt\": 0,\n    \"no_interrupt_time\": {\n        \"from\": {\n            \"hour\": 0,\n            \"min\": 0\n        },\n        \"to\": {\n            \"hour\": 0,\n            \"min\": 0\n        },\n        \"wday\": [\n            0,\n            1,\n            2,\n            3,\n            4,\n            5,\n            6\n        ]\n    }\n}";
    private Map<String, Object> additionalProperties = new HashMap();
    private String command;
    private Param param;

    public static MsgCenterSettingData parse(JSONObject jSONObject) {
        MsgCenterSettingData msgCenterSettingData = new MsgCenterSettingData();
        msgCenterSettingData.setParam(Param.parse(jSONObject));
        return msgCenterSettingData;
    }

    public JSONObject toJSON() {
        return this.param.toJSON();
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public Param getParam() {
        return this.param;
    }

    public void setParam(Param param2) {
        this.param = param2;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String str, Object obj) {
        this.additionalProperties.put(str, obj);
    }

    public void setDevicePushSwitch(int i) {
        this.param.setScene(Integer.valueOf(i));
    }

    public void setDeviceShareSwitch(int i) {
        this.param.setDeviceShare(Integer.valueOf(i));
    }

    public void setFamilyInvitationSwitch(int i) {
        this.param.setFamilyShare(Integer.valueOf(i));
    }

    public void setShopSwitch(int i) {
        this.param.setShop(Integer.valueOf(i));
    }

    public void setNointerruptSwitch(int i) {
        this.param.setNoInterrupt(Integer.valueOf(i));
    }

    public void setNointerruptTimeSpan(int i, int i2) {
        NoInterruptTime noInterruptTime = this.param.getNoInterruptTime();
        if (noInterruptTime != null) {
            From from = noInterruptTime.getFrom();
            To to = noInterruptTime.getTo();
            if (from != null && to != null) {
                from.setHour(Integer.valueOf(i));
                to.setHour(Integer.valueOf(i2));
            }
        }
    }
}
