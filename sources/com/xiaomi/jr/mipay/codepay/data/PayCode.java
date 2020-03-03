package com.xiaomi.jr.mipay.codepay.data;

import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.mipay.common.model.MipayResponse;
import java.util.List;

public class PayCode extends MipayResponse {
    @SerializedName("codePayUuid")

    /* renamed from: a  reason: collision with root package name */
    public String f10893a;
    @SerializedName("needToBindCard")
    public boolean b;
    @SerializedName("defaultPayTypeId")
    public int c = -1;
    @SerializedName("payTypeList")
    public List<PayType> d;
}
