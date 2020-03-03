package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import com.miui.tsmclient.util.StringUtils;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;

public class dz extends ec {
    private Context b;
    private boolean c;
    private int d;
    private int e;

    public dz(Context context, boolean z, int i, int i2) {
        this.b = context;
        this.c = z;
        this.d = i;
        this.e = i2;
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        if (bt.q(this.b) == 1) {
            return true;
        }
        if (!this.c) {
            return false;
        }
        String a2 = cj.a(this.b, "iKey");
        if (TextUtils.isEmpty(a2)) {
            return true;
        }
        String[] split = a2.split(PaymentOptionsDecoder.pipeSeparator);
        if (split == null || split.length < 2) {
            cj.b(this.b, "iKey");
            return true;
        }
        if (!bz.a(System.currentTimeMillis(), StringUtils.SOURCE_DATE_FORMAT).equals(split[0]) || Integer.parseInt(split[1]) < this.e) {
            return true;
        }
        return false;
    }

    public int b() {
        int i = (bt.q(this.b) == 1 || this.d <= 0) ? Integer.MAX_VALUE : this.d;
        return this.f4419a != null ? Math.max(i, this.f4419a.b()) : i;
    }

    public void a(int i) {
        if (bt.q(this.b) != 1) {
            String a2 = bz.a(System.currentTimeMillis(), StringUtils.SOURCE_DATE_FORMAT);
            String a3 = cj.a(this.b, "iKey");
            if (!TextUtils.isEmpty(a3)) {
                String[] split = a3.split(PaymentOptionsDecoder.pipeSeparator);
                if (split == null || split.length < 2) {
                    cj.b(this.b, "iKey");
                } else if (a2.equals(split[0])) {
                    i += Integer.parseInt(split[1]);
                }
            }
            Context context = this.b;
            cj.a(context, "iKey", a2 + "|" + i);
        }
    }
}
