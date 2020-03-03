package com.ximalaya.ting.android.opensdk.model.pay;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class PayInfo extends XimalayaResponse {
    @SerializedName("composed_price_type")

    /* renamed from: a  reason: collision with root package name */
    private int f2106a;
    @SerializedName("price_type_detail")
    private List<PriceTypeDetailBean> b;

    public int a() {
        return this.f2106a;
    }

    public void a(int i) {
        this.f2106a = i;
    }

    public List<PriceTypeDetailBean> b() {
        return this.b;
    }

    public void a(List<PriceTypeDetailBean> list) {
        this.b = list;
    }

    public static class PriceTypeDetailBean {

        /* renamed from: a  reason: collision with root package name */
        private float f2107a;
        @SerializedName("price_type")
        private int b;
        @SerializedName("discounted_price")
        private float c;
        @SerializedName("price_unit")
        private String d;

        public String a() {
            return this.d;
        }

        public void a(String str) {
            this.d = str;
        }

        public float b() {
            return this.f2107a;
        }

        public void a(float f) {
            this.f2107a = f;
        }

        public int c() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        public float d() {
            return this.c;
        }

        public void b(float f) {
            this.c = f;
        }
    }
}
