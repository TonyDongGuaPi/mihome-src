package com.xiaomi.infrared.bean;

import com.xiaomi.infrared.utils.CommUtil;
import org.json.JSONObject;

public class MJSetResultBean {

    /* renamed from: a  reason: collision with root package name */
    private String f10235a;
    private int b;
    private String c;
    private String d;
    private int e;
    private String f;

    public void a(String str) {
        this.f10235a = str;
    }

    public void a(int i) {
        this.b = i;
    }

    public void b(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public void b(int i) {
        this.e = i;
    }

    public void d(String str) {
        this.f = str;
    }

    public String a() {
        return this.f10235a;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public static MJSetResultBean a(JSONObject jSONObject) {
        MJSetResultBean mJSetResultBean = new MJSetResultBean();
        try {
            mJSetResultBean.f10235a = CommUtil.a(jSONObject, "did");
            mJSetResultBean.b = jSONObject.optInt("category");
            mJSetResultBean.c = CommUtil.a(jSONObject, "name");
            mJSetResultBean.d = CommUtil.a(jSONObject, "model");
            mJSetResultBean.e = jSONObject.optInt("pdid");
            mJSetResultBean.f = CommUtil.a(jSONObject, "parent_id");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return mJSetResultBean;
    }
}
