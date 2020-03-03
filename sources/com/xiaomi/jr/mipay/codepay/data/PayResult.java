package com.xiaomi.jr.mipay.codepay.data;

import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.mipay.common.model.MipayResponse;

public class PayResult<T> extends MipayResponse {
    public static final int c = 1;
    public static final int d = 2;
    public static final String e = "WAIT_PAY";
    public static final String f = "TRADE_SUCCESS";
    public static final String g = "TRADE_CANCEL";
    public static final String h = "TRADE_CLOSED";
    @SerializedName("control")

    /* renamed from: a  reason: collision with root package name */
    public int f10894a;
    @SerializedName("data")
    public T b;
}
