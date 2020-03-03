package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.clientreport.data.a;
import com.xiaomi.clientreport.processor.d;

public class bg implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private Context f12647a;
    private a b;
    private d c;

    public bg(Context context, a aVar, d dVar) {
        this.f12647a = context;
        this.b = aVar;
        this.c = dVar;
    }

    public void run() {
        if (this.c != null) {
            this.c.b(this.b);
        }
    }
}
