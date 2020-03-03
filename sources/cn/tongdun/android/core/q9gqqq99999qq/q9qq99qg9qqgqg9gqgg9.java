package cn.tongdun.android.core.q9gqqq99999qq;

import org.json.JSONException;
import org.json.JSONObject;

class q9qq99qg9qqgqg9gqgg9 extends q9gqqq99999qq {
    private int g999gqq9ggqgqq;
    private int g9q9q9g9;
    final /* synthetic */ qgg9qgg9999g9g gqg9qq9gqq9q9q;
    private int q9gqqq99999qq;
    private int q9q99gq99gggqg9qqqgg;
    private int q9qq99qg9qqgqg9gqgg9;
    private int qqq9gg9gqq9qgg99q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public q9qq99qg9qqgqg9gqgg9(qgg9qgg9999g9g qgg9qgg9999g9g, int i, int i2, int i3, int i4, int i5, int i6) {
        super(qgg9qgg9999g9g);
        this.gqg9qq9gqq9q9q = qgg9qgg9999g9g;
        this.q9qq99qg9qqgqg9gqgg9 = i;
        this.q9gqqq99999qq = i2;
        this.g9q9q9g9 = i3;
        this.q9q99gq99gggqg9qqqgg = i4;
        this.qqq9gg9gqq9qgg99q = i5;
        this.g999gqq9ggqgqq = i6;
    }

    /* access modifiers changed from: package-private */
    public JSONObject gqg9qq9gqq9q9q() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("704c63", 93), this.q9qq99qg9qqgqg9gqgg9);
            jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("6d017e", 13), this.q9gqqq99999qq);
            jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("7c0d6f", 16), this.g9q9q9g9);
            if (!(this.q9q99gq99gggqg9qqqgg == Integer.MAX_VALUE || this.qqq9gg9gqq9qgg99q == Integer.MAX_VALUE)) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("726479", 127), this.q9q99gq99gggqg9qqqgg);
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("72006d09", 21), this.qqq9gg9gqq9qgg99q);
            }
            if (this.g999gqq9ggqgqq != Integer.MIN_VALUE) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("7a2a6b", 58), this.g999gqq9ggqgqq);
            }
            jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("6a567d43", 77), qgg9qgg9999g9g.gqg9qq9gqq9q9q("7d6e6a62", 127));
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
