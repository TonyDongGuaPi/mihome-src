package com.xiaomi.jr.mipay.common.model;

import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.http.model.RawResponse;

public class MipayResponse extends RawResponse {
    @SerializedName("errcode")
    public int j;
    @SerializedName("errDesc")
    public String k;
    @SerializedName("announcement")
    public String l;
}
