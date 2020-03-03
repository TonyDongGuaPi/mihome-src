package com.xiaomi.youpin.login.entity.passtoken;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;

public class GetMiServerSignResult {
    @SerializedName("code")

    /* renamed from: a  reason: collision with root package name */
    public int f23520a;
    @SerializedName("location")
    public String b;
    @SerializedName("captchaUrl")
    public String c;
    @SerializedName("callback")
    public String d;
    @SerializedName("qs")
    public String e;
    @SerializedName("_sign")
    public String f;

    public void a() {
        this.b = "null".equals(this.b) ? "" : this.b;
        this.c = "null".equals(this.c) ? "" : this.c;
        this.d = "null".equals(this.d) ? "" : this.d;
        this.e = "null".equals(this.e) ? "" : this.e;
        this.f = "null".equals(this.f) ? "" : this.f;
        if (!TextUtils.isEmpty(this.c) && this.c.startsWith("/")) {
            this.c += "https://account.xiaomi.com";
        }
    }
}
