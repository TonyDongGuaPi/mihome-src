package com.xiaomi.infrared.bean;

import com.xiaomi.infrared.utils.CommUtil;
import org.json.JSONObject;

public class MJBrandEntity {

    /* renamed from: a  reason: collision with root package name */
    private String f10230a;
    private String b;
    private String c;

    public void a(String str) {
        this.f10230a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public String a() {
        return this.f10230a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public static MJBrandEntity a(JSONObject jSONObject) {
        MJBrandEntity mJBrandEntity = new MJBrandEntity();
        try {
            mJBrandEntity.f10230a = CommUtil.a(jSONObject, "id");
            mJBrandEntity.c = CommUtil.a(jSONObject, "en_name");
            mJBrandEntity.c = CommUtil.a(jSONObject, "en_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mJBrandEntity;
    }
}
