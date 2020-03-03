package cn.tongdun.android.core.q9gqqq99999qq;

import org.json.JSONException;
import org.json.JSONObject;

class g9q9q9g9 extends q9gqqq99999qq {
    private int g999gqq9ggqgqq;
    private int g9q9q9g9;
    final /* synthetic */ qgg9qgg9999g9g gqg9qq9gqq9q9q;
    private int q9gqqq99999qq;
    private int q9q99gq99gggqg9qqqgg;
    private int q9qq99qg9qqgqg9gqgg9;
    private int qqq9gg9gqq9qgg99q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g9q9q9g9(qgg9qgg9999g9g qgg9qgg9999g9g, int i, int i2, int i3, int i4, int i5, int i6) {
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
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("731b60", 14), this.q9q99gq99gggqg9qqqgg);
            }
            jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("723f6e", 36), this.q9qq99qg9qqgqg9gqgg9);
            jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("7d156e", 9), this.q9gqqq99999qq);
            if (this.g9q9q9g9 != 0) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("7f53755678", 86), this.g9q9q9g9);
            }
            if (this.qqq9gg9gqq9qgg99q != 0) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("7c637869", 100), this.qqq9gg9gqq9qgg99q);
            }
            if (this.g999gqq9ggqgqq != Integer.MIN_VALUE) {
                jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("7a506b", 64), this.g999gqq9ggqgqq);
            }
            jSONObject.put(qgg9qgg9999g9g.gqg9qq9gqq9q9q("6a627d77", 121), qgg9qgg9999g9g.gqg9qq9gqq9q9q("794379", 65));
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
