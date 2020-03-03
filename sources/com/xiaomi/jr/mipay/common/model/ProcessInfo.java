package com.xiaomi.jr.mipay.common.model;

import com.google.gson.annotations.SerializedName;

public class ProcessInfo extends MipayResponse {
    @SerializedName("processType")

    /* renamed from: a  reason: collision with root package name */
    public String f10950a;
    @SerializedName("processId")
    public String b;
    @SerializedName("isPassSet")
    public boolean c;
    @SerializedName("forgetType")
    public String d;
    @SerializedName("pubKey")
    public String e;
}
