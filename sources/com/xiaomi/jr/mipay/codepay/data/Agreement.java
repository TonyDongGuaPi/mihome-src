package com.xiaomi.jr.mipay.codepay.data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Agreement implements Serializable {
    @SerializedName("title")
    public String mTitle;
    @SerializedName("url")
    public String mUrl;
}
