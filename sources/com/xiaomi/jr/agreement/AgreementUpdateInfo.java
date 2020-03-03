package com.xiaomi.jr.agreement;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class AgreementUpdateInfo {
    @SerializedName("firstVisitFlag")

    /* renamed from: a  reason: collision with root package name */
    private boolean f10286a;
    @SerializedName("updateFlag")
    private boolean b;
    @SerializedName("privacyProtocolConfigs")
    private ArrayList<AgreementInfo> c;
    @SerializedName("extraInfo")
    private String d;

    public boolean a() {
        return this.f10286a;
    }

    public boolean b() {
        return this.b;
    }

    public ArrayList<AgreementInfo> c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }
}
