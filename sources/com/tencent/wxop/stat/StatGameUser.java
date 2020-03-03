package com.tencent.wxop.stat;

import com.taobao.weex.el.parse.Operators;

public class StatGameUser implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private String f9266a = "";
    private String b = "";
    private String c = "";

    public StatGameUser() {
    }

    public StatGameUser(String str, String str2, String str3) {
        this.b = str;
        this.f9266a = str2;
        this.c = str3;
    }

    public String a() {
        return this.f9266a;
    }

    public void a(String str) {
        this.f9266a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    /* renamed from: d */
    public StatGameUser clone() {
        try {
            return (StatGameUser) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public String toString() {
        return "StatGameUser [worldName=" + this.f9266a + ", account=" + this.b + ", level=" + this.c + Operators.ARRAY_END_STR;
    }
}
