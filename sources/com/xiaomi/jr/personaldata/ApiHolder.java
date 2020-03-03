package com.xiaomi.jr.personaldata;

import com.google.gson.annotations.SerializedName;

public class ApiHolder {

    /* renamed from: a  reason: collision with root package name */
    private static IApi f10998a;

    public interface IApi {
        long a(int i);

        TypeListResult a();

        boolean a(int i, String str, long j);

        boolean a(int[] iArr);

        boolean b();
    }

    public static class TypeListResult {
        @SerializedName("types")

        /* renamed from: a  reason: collision with root package name */
        public int[] f10999a;
        @SerializedName("inquire")
        public boolean b;
    }

    public static void a(IApi iApi) {
        f10998a = iApi;
    }

    public static IApi a() {
        return f10998a;
    }
}
