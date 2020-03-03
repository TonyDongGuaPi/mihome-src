package cn.tongdun.android.core.q9gqqq99999qq;

import org.json.JSONException;
import org.json.JSONObject;

class q9q99gq99gggqg9qqqgg extends q9gqqq99999qq {
    private int g999gqq9ggqgqq;
    private int g9q9q9g9;
    final /* synthetic */ qgg9qgg9999g9g gqg9qq9gqq9q9q;
    private int q9gqqq99999qq;
    private int q9q99gq99gggqg9qqqgg;
    private int q9qq99qg9qqgqg9gqgg9;
    private int qqq9gg9gqq9qgg99q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public q9q99gq99gggqg9qqqgg(qgg9qgg9999g9g qgg9qgg9999g9g, int i, int i2, int i3, int i4, int i5, int i6) {
        super(qgg9qgg9999g9g);
        this.gqg9qq9gqq9q9q = qgg9qgg9999g9g;
        this.q9gqqq99999qq = i;
        this.q9qq99qg9qqgqg9gqgg9 = i2;
        this.g9q9q9g9 = i3;
        this.q9q99gq99gggqg9qqqgg = i4;
        this.qqq9gg9gqq9qgg99q = i5;
        this.g999gqq9ggqgqq = i6;
    }

    /* access modifiers changed from: package-private */
    public JSONObject gqg9qq9gqq9q9q() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.q9gqqq99999qq != -1) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("732f60", 58), this.q9gqqq99999qq);
            }
            if (this.q9qq99qg9qqgqg9gqgg9 != Integer.MAX_VALUE) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("724a6e", 81), this.q9qq99qg9qqgqg9gqgg9);
            }
            if (this.g9q9q9g9 != Integer.MAX_VALUE) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("7d4c6e", 80), this.g9q9q9g9);
            }
            if (this.q9q99gq99gggqg9qqqgg != 0) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("6b266632633f", 36), this.q9q99gq99gggqg9qqqgg);
            }
            if (this.g999gqq9ggqgqq != Integer.MIN_VALUE) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("7a4f6b", 95), this.g999gqq9ggqgqq);
            }
            jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("6a0c7d19", 23), qgg9qgg9999g9g.gqg9qq9gqq9q9q("696070697c", 98));
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
