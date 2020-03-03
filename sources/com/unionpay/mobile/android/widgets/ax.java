package com.unionpay.mobile.android.widgets;

import android.view.View;
import com.unionpay.mobile.android.utils.k;

final class ax implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UPWidget f9780a;

    ax(UPWidget uPWidget) {
        this.f9780a = uPWidget;
    }

    public final void onClick(View view) {
        String str;
        int id = view.getId();
        int i = this.f9780a.c;
        if (id == 10) {
            k.c("kb", " [ FIN ]");
            UPWidget.b(this.f9780a);
        } else if (id == 20) {
            k.c("kb", " [ DEL ]");
            if (i > 0) {
                this.f9780a.deleteOnce(this.f9780a.p);
                UPWidget uPWidget = this.f9780a;
                uPWidget.c--;
                String substring = this.f9780a.b.b().toString().substring(0, i - 1);
                this.f9780a.b.c(substring);
                this.f9780a.b.b(substring.length());
            }
        } else if (this.f9780a.c == 6) {
            k.c("kb", " [ FIN ]");
        } else {
            this.f9780a.appendOnce(this.f9780a.p, Integer.toString(id));
            if (i == 0) {
                str = "*";
            } else {
                str = this.f9780a.b.b() + "*";
            }
            this.f9780a.b.c(str);
            this.f9780a.b.b(str.length());
            this.f9780a.c++;
        }
    }
}
