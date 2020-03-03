package com.xiaomi.smarthome.newui.wallpaper;

import com.facebook.react.modules.appstate.AppStateModule;
import com.taobao.weex.common.Constants;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.xiaomi.stat.d;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class LibAnimationConfig {

    /* renamed from: a  reason: collision with root package name */
    final int f20796a;
    final int b;
    final String c;
    final String d;
    final int e;
    final int f;
    final List<Layer> g = new ArrayList();

    public LibAnimationConfig(JSONObject jSONObject) {
        this.d = jSONObject.optString(AppStateModule.APP_STATE_BACKGROUND, "");
        String optString = jSONObject.optString(SharePatchInfo.g, "");
        if (optString.isEmpty()) {
            optString = "";
        } else if (!optString.endsWith("/")) {
            optString = optString + "/";
        }
        this.c = optString;
        this.f20796a = jSONObject.optInt("start", 0);
        this.b = jSONObject.optInt("stop", 0);
        JSONArray optJSONArray = jSONObject.optJSONArray("size");
        this.e = optJSONArray.optInt(0);
        this.f = optJSONArray.optInt(1);
        JSONArray optJSONArray2 = jSONObject.optJSONArray("layers");
        if (optJSONArray2 != null) {
            int length = optJSONArray2.length();
            for (int i = 0; i < length; i++) {
                this.g.add(new Layer(optJSONArray2.optJSONObject(i)));
            }
        }
    }

    static class Layer {

        /* renamed from: a  reason: collision with root package name */
        final int f20799a;
        final int b;
        final String c;
        final float d;
        final float e;
        final float f;
        final float g;
        final float h;
        final int i;
        final int j;
        final int k;
        final List<Layer> l;
        final List<Action> m;

        private Layer(JSONObject jSONObject) {
            this.l = new ArrayList();
            this.m = new ArrayList();
            this.c = jSONObject.optString("picture", "");
            this.f20799a = jSONObject.optInt("start", 0);
            this.b = jSONObject.optInt("stop", 0);
            this.i = jSONObject.optInt("start", 0);
            this.j = jSONObject.optInt("stop", 0);
            this.d = (float) jSONObject.optDouble("x", 0.0d);
            this.e = (float) jSONObject.optDouble(Constants.Name.Y, 0.0d);
            this.f = (float) jSONObject.optDouble("w", 0.0d);
            this.g = (float) jSONObject.optDouble("h", 0.0d);
            this.h = (float) jSONObject.optDouble("a", 0.0d);
            this.k = jSONObject.optInt("loop", 1);
            JSONArray optJSONArray = jSONObject.optJSONArray("layers");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    this.l.add(new Layer(optJSONArray.optJSONObject(i2)));
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("actions");
            if (optJSONArray2 != null) {
                int length2 = optJSONArray2.length();
                for (int i3 = 0; i3 < length2; i3++) {
                    this.m.add(new Action(optJSONArray2.optJSONObject(i3)));
                }
            }
        }
    }

    static class Action {

        /* renamed from: a  reason: collision with root package name */
        final int f20797a;
        final int b;
        final int c;
        final List<Animations> d;

        private Action(JSONObject jSONObject) {
            this.d = new ArrayList();
            this.f20797a = jSONObject.optInt("begin", 0);
            this.b = jSONObject.optInt("end", 0);
            this.c = jSONObject.optInt("loop", 1);
            JSONArray optJSONArray = jSONObject.optJSONArray("animations");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    this.d.add(new Animations(optJSONArray.optJSONObject(i)));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static float[] b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() < 1) {
            return null;
        }
        float[] fArr = new float[jSONArray.length()];
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            fArr[i] = (float) jSONArray.optDouble(i);
        }
        return fArr;
    }

    static class Animations {
        final float[] A;

        /* renamed from: a  reason: collision with root package name */
        final int f20798a;
        final int b;
        final int c;
        final int d;
        final String e;
        final String f;
        final String g;
        final String h;
        final String i;
        final String j;
        final String k;
        final String l;
        final String m;
        final String n;
        final String o;
        final String p;
        final float[] q;
        final float[] r;
        final float[] s;
        final float[] t;
        final float[] u;
        final float[] v;
        final float[] w;
        final float[] x;
        final float[] y;
        final float[] z;

        private Animations(JSONObject jSONObject) {
            this.c = jSONObject.optInt("begin", 0);
            this.d = jSONObject.optInt("end", 0);
            this.f20798a = jSONObject.optInt("loop", 1);
            this.b = jSONObject.optInt("duration", 0);
            this.q = LibAnimationConfig.b(jSONObject.optJSONArray("x"));
            this.r = LibAnimationConfig.b(jSONObject.optJSONArray(Constants.Name.Y));
            this.s = LibAnimationConfig.b(jSONObject.optJSONArray("w"));
            this.t = LibAnimationConfig.b(jSONObject.optJSONArray("h"));
            this.u = LibAnimationConfig.b(jSONObject.optJSONArray("a"));
            this.v = LibAnimationConfig.b(jSONObject.optJSONArray("tx"));
            this.w = LibAnimationConfig.b(jSONObject.optJSONArray("ty"));
            this.x = LibAnimationConfig.b(jSONObject.optJSONArray(d.o));
            this.y = LibAnimationConfig.b(jSONObject.optJSONArray("r"));
            this.z = LibAnimationConfig.b(jSONObject.optJSONArray("rx"));
            this.A = LibAnimationConfig.b(jSONObject.optJSONArray("ry"));
            this.e = jSONObject.optString("func", "");
            this.f = jSONObject.optString("fx", this.e);
            this.g = jSONObject.optString("fy", this.e);
            this.h = jSONObject.optString("fw", this.e);
            this.i = jSONObject.optString("fh", this.e);
            this.j = jSONObject.optString("fa", this.e);
            this.k = jSONObject.optString("ftx", this.e);
            this.l = jSONObject.optString("fty", this.e);
            this.m = jSONObject.optString("ftz", this.e);
            this.n = jSONObject.optString("fr", this.e);
            this.o = jSONObject.optString("frx", this.e);
            this.p = jSONObject.optString("fry", this.e);
        }
    }
}
