package com.xiaomi.infrared.bean;

import com.xiaomi.infrared.utils.CommUtil;
import org.json.JSONObject;

public class MJKeyEntity {

    /* renamed from: a  reason: collision with root package name */
    private String f10232a;
    private String b;
    private String c;
    private String d;

    public void a(String str) {
        this.b = str;
    }

    public void b(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.f10232a;
    }

    public void d(String str) {
        this.f10232a = str;
    }

    public void e(String str) {
        this.f10232a = str;
    }

    public String e() {
        return this.f10232a;
    }

    public static MJKeyEntity a(JSONObject jSONObject) {
        MJKeyEntity mJKeyEntity = new MJKeyEntity();
        try {
            mJKeyEntity.f10232a = CommUtil.a(jSONObject, "ac_key");
            mJKeyEntity.b = CommUtil.a(jSONObject, "id");
            mJKeyEntity.c = CommUtil.a(jSONObject, "name");
            mJKeyEntity.d = CommUtil.a(jSONObject, "display_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mJKeyEntity;
    }
}
