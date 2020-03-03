package com.xiaomi.jr.mipay.codepay.data;

import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.mipay.common.model.MipayResponse;

public class TradeResult extends MipayResponse {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10896a = "WAIT_PAY";
    public static final String b = "TRADE_SUCCESS";
    public static final String c = "TRADE_CANCEL";
    public static final String d = "TRADE_CLOSED";
    @SerializedName("tradeStatus")
    public String e;
    @SerializedName("summary")
    public String f;
    @SerializedName("subSummary")
    public String g;
    @SerializedName("showResultPage")
    public boolean h;
    @SerializedName("returnUrl")
    public String i;
}
