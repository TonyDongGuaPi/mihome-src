package com.mi.mistatistic.sdk.data;

import android.annotation.SuppressLint;
import android.view.View;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class ClickableView {

    /* renamed from: a  reason: collision with root package name */
    private int f7356a;
    private int b;
    private int c;
    private int d;

    public ClickableView(int i, int i2, int i3, int i4) {
        this.f7356a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public ClickableView(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.f7356a = iArr[0];
        this.b = iArr[1];
        this.c = view.getWidth();
        this.d = view.getHeight();
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("left", this.f7356a);
        jSONObject.put("top", this.b);
        jSONObject.put("width", this.c);
        jSONObject.put("height", this.d);
        return jSONObject;
    }

    public String toString() {
        try {
            return a().toString();
        } catch (JSONException unused) {
            return "";
        }
    }
}
