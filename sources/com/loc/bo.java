package com.loc;

import android.content.Context;

public final class bo {

    /* renamed from: a  reason: collision with root package name */
    private Context f6517a;
    private ac b;
    private String c;

    public bo(Context context, ac acVar, String str) {
        this.f6517a = context.getApplicationContext();
        this.b = acVar;
        this.c = str;
    }

    private static String a(Context context, ac acVar, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"sdkversion\":\"");
            sb.append(acVar.c());
            sb.append("\",\"product\":\"");
            sb.append(acVar.a());
            sb.append("\",\"nt\":\"");
            sb.append(x.d(context));
            sb.append("\",\"details\":");
            sb.append(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final byte[] a() {
        return ad.a(a(this.f6517a, this.b, this.c));
    }
}
