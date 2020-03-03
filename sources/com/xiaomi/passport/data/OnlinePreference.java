package com.xiaomi.passport.data;

import org.json.JSONObject;

public class OnlinePreference {
    public String dataCenterZone;
    public String diagnosisDomain;

    public static OnlinePreference parse(JSONObject jSONObject) {
        OnlinePreference onlinePreference = new OnlinePreference();
        onlinePreference.diagnosisDomain = jSONObject.optString("diagnosisDomain", (String) null);
        onlinePreference.dataCenterZone = jSONObject.optString("dataCenterZone", (String) null);
        return onlinePreference;
    }
}
