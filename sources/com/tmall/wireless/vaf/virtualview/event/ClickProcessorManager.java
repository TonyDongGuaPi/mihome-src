package com.tmall.wireless.vaf.virtualview.event;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import org.json.JSONObject;

public class ClickProcessorManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9393a = "CliProManager_TMTEST";
    private ArrayMap<String, IClickProcessor> b = new ArrayMap<>();
    private IClickProcessor c;

    public void a(String str, IClickProcessor iClickProcessor) {
        if (!TextUtils.isEmpty(str) && iClickProcessor != null) {
            this.b.put(str, iClickProcessor);
        }
    }

    public void a(IClickProcessor iClickProcessor) {
        if (iClickProcessor != null) {
            this.c = iClickProcessor;
        }
    }

    public void a(String str) {
        this.b.remove(str);
    }

    public void a() {
        this.c = null;
    }

    public boolean a(EventData eventData) {
        JSONObject jSONObject;
        if (!(eventData == null || (jSONObject = (JSONObject) eventData.b.l().b()) == null)) {
            IClickProcessor iClickProcessor = this.b.get(jSONObject.optString("type"));
            if (iClickProcessor != null) {
                return iClickProcessor.a(eventData);
            }
            if (this.c != null) {
                return this.c.a(eventData);
            }
        }
        return false;
    }
}
