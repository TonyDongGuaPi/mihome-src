package com.xiaomi.jr.mipay.codepay.data;

import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.mipay.common.model.MipayResponse;

public class DoPayResult extends MipayResponse {
    @SerializedName("tradeId")

    /* renamed from: a  reason: collision with root package name */
    public String f10892a;
    @SerializedName("passCanInput")
    public boolean b;
    @SerializedName("passErrTitle")
    public String c;
    @SerializedName("passErrDesc")
    public String d;
    @SerializedName("tailNo")
    public String e;
}
