package cn.tongdun.android.core.q9gqqq99999qq;

import org.json.JSONException;
import org.json.JSONObject;

class qqq9gg9gqq9qgg99q extends q9gqqq99999qq {
    private int g999gqq9ggqgqq;
    private int g9q9q9g9;
    final /* synthetic */ qgg9qgg9999g9g gqg9qq9gqq9q9q;
    private int q9gqqq99999qq;
    private int q9q99gq99gggqg9qqqgg;
    private int q9qq99qg9qqgqg9gqgg9;
    private int qqq9gg9gqq9qgg99q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public qqq9gg9gqq9qgg99q(qgg9qgg9999g9g qgg9qgg9999g9g, int i, int i2, int i3, int i4, int i5, int i6) {
        super(qgg9qgg9999g9g);
        this.gqg9qq9gqq9q9q = qgg9qgg9999g9g;
        this.q9q99gq99gggqg9qqqgg = i;
        this.q9qq99qg9qqgqg9gqgg9 = i2;
        this.q9gqqq99999qq = i3;
        this.g9q9q9g9 = i4;
        this.qqq9gg9gqq9qgg99q = i5;
        this.g999gqq9ggqgqq = i6;
    }

    /* access modifiers changed from: package-private */
    public JSONObject gqg9qq9gqq9q9q() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.q9q99gq99gggqg9qqqgg != -1) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("735c60", 73), this.q9q99gq99gggqg9qqqgg);
            }
            jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("6a1676", 21), this.q9qq99qg9qqgqg9gqgg9);
            jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("7d17", 11), this.q9gqqq99999qq);
            if (this.g9q9q9g9 != 0) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("7b577643734e", 69), this.g9q9q9g9);
            }
            if (this.qqq9gg9gqq9qgg99q != 0) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("6e5d7a", 88), this.qqq9gg9gqq9qgg99q);
            }
            if (this.g999gqq9ggqgqq != Integer.MIN_VALUE) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("7a5b6b", 75), this.g999gqq9ggqgqq);
            }
            jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("6a067d13", 29), qgg9qgg9999g9g.gqg9qq9gqq9q9q("72027d", 12));
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
