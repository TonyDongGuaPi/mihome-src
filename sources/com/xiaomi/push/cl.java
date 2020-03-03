package com.xiaomi.push;

import android.os.Bundle;
import com.mi.global.shop.model.Tags;
import org.json.JSONObject;

public class cl {

    /* renamed from: a  reason: collision with root package name */
    public long f12675a;
    public int b;
    public String c;
    public int d;
    public int e;
    public long f;
    public int g;
    public int h;
    private String i;

    public cl() {
    }

    public cl(cl clVar) {
        this.f12675a = clVar.f12675a;
        this.b = clVar.b;
        this.c = clVar.c;
        this.d = clVar.d;
        this.e = clVar.e;
        this.f = clVar.f;
        this.g = clVar.g;
        this.i = clVar.i;
        this.h = clVar.h;
    }

    public String a() {
        return this.i;
    }

    public void a(String str) {
        this.i = str;
    }

    public void a(JSONObject jSONObject) {
        this.f12675a = jSONObject.optLong("id");
        this.b = jSONObject.optInt(Tags.ShoppingCartList.SHOWTYPE);
        this.d = jSONObject.optInt("nonsense");
        this.e = jSONObject.optInt("receiveUpperBound");
        this.f = jSONObject.optLong("lastShowTime");
        this.h = jSONObject.optInt("multi");
    }

    public Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putLong("id", this.f12675a);
        bundle.putInt(Tags.ShoppingCartList.SHOWTYPE, this.b);
        bundle.putInt("nonsense", this.d);
        bundle.putInt("receiveUpperBound", this.e);
        bundle.putLong("lastShowTime", this.f);
        bundle.putInt("multi", this.h);
        return bundle;
    }

    public String toString() {
        return "";
    }
}
