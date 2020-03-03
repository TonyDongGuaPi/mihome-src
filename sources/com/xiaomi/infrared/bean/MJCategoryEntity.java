package com.xiaomi.infrared.bean;

import com.xiaomi.infrared.utils.CommUtil;
import org.json.JSONObject;

public class MJCategoryEntity {

    /* renamed from: a  reason: collision with root package name */
    private String f10231a;
    private String b;

    public void a(String str) {
        this.f10231a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public String a() {
        return this.f10231a;
    }

    public String b() {
        return this.b;
    }

    public static MJCategoryEntity a(JSONObject jSONObject) {
        MJCategoryEntity mJCategoryEntity = new MJCategoryEntity();
        try {
            mJCategoryEntity.f10231a = CommUtil.a(jSONObject, "id");
            mJCategoryEntity.b = CommUtil.a(jSONObject, "name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mJCategoryEntity;
    }
}
