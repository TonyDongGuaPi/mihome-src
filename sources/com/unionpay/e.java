package com.unionpay;

import android.content.Context;
import android.content.DialogInterface;

final class e implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9549a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;

    e(Context context, String str, String str2, String str3, String str4, String str5) {
        this.f9549a = context;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        UPPayAssistEx.a(this.f9549a, this.b, this.c, this.d, this.e, this.f);
    }
}
