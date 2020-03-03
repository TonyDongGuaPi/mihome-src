package com.xiaomi.infrared.bean;

import com.xiaomi.infrared.utils.CommUtil;
import org.json.JSONObject;

public class MJAreaID {

    /* renamed from: a  reason: collision with root package name */
    private String f10229a;
    private String b;

    public String a() {
        return this.b;
    }

    public String b() {
        return this.f10229a;
    }

    public static MJAreaID a(JSONObject jSONObject) {
        MJAreaID mJAreaID = new MJAreaID();
        try {
            mJAreaID.b = CommUtil.a(jSONObject, "city_id");
            mJAreaID.f10229a = CommUtil.a(jSONObject, "area_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mJAreaID;
    }
}
