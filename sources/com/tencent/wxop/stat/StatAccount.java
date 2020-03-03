package com.tencent.wxop.stat;

import com.taobao.weex.el.parse.Operators;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.q;
import org.json.JSONException;
import org.json.JSONObject;

public class StatAccount {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9263a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    private String i = "";
    private int j = 0;
    private String k = "";
    private String l = "";

    public StatAccount(String str) {
        this.i = str;
    }

    public StatAccount(String str, int i2) {
        this.i = str;
        this.j = i2;
    }

    public String a() {
        JSONObject jSONObject = new JSONObject();
        if (k.c(this.i)) {
            try {
                q.a(jSONObject, "a", this.i);
                jSONObject.put("t", this.j);
                q.a(jSONObject, "e", this.k);
                q.a(jSONObject, "e1", this.l);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    public void a(int i2) {
        this.j = i2;
    }

    public void a(String str) {
        this.i = str;
    }

    public String b() {
        return this.i;
    }

    public void b(String str) {
        this.k = str;
    }

    public int c() {
        return this.j;
    }

    public void c(String str) {
        this.l = str;
    }

    public String d() {
        return this.k;
    }

    public String e() {
        return this.l;
    }

    public String toString() {
        return "StatAccount [account=" + this.i + ", accountType=" + this.j + ", ext=" + this.k + ", ext1=" + this.l + Operators.ARRAY_END_STR;
    }
}
