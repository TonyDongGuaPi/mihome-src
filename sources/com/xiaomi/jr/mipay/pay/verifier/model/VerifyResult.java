package com.xiaomi.jr.mipay.pay.verifier.model;

import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.mipay.common.model.MipayResponse;

public class VerifyResult extends MipayResponse {
    @SerializedName("passCanInput")

    /* renamed from: a  reason: collision with root package name */
    public boolean f10971a;
    @SerializedName("passErrTitle")
    public String b;
    @SerializedName("passErrDesc")
    public String c;
}
