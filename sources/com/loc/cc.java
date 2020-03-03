package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.miui.tsmclient.util.StringUtils;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;

public final class cc extends cf {
    private Context b;
    private boolean c;
    private int d;
    private int e;

    public cc(Context context, boolean z, int i, int i2) {
        this.b = context;
        this.c = z;
        this.d = i;
        this.e = i2;
    }

    public final void a(int i) {
        if (x.q(this.b) != 1) {
            String a2 = ad.a(System.currentTimeMillis(), StringUtils.SOURCE_DATE_FORMAT);
            String a3 = ao.a(this.b, "iKey");
            if (!TextUtils.isEmpty(a3)) {
                String[] split = a3.split(PaymentOptionsDecoder.pipeSeparator);
                if (split == null || split.length < 2) {
                    ao.b(this.b, "iKey");
                } else if (a2.equals(split[0])) {
                    i += Integer.parseInt(split[1]);
                }
            }
            Context context = this.b;
            ao.a(context, "iKey", a2 + "|" + i);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        if (x.q(this.b) == 1) {
            return true;
        }
        if (!this.c) {
            return false;
        }
        String a2 = ao.a(this.b, "iKey");
        if (TextUtils.isEmpty(a2)) {
            return true;
        }
        String[] split = a2.split(PaymentOptionsDecoder.pipeSeparator);
        if (split == null || split.length < 2) {
            ao.b(this.b, "iKey");
            return true;
        }
        return !ad.a(System.currentTimeMillis(), StringUtils.SOURCE_DATE_FORMAT).equals(split[0]) || Integer.parseInt(split[1]) < this.e;
    }

    public final int b() {
        int i = (x.q(this.b) == 1 || this.d <= 0) ? Integer.MAX_VALUE : this.d;
        return this.f6531a != null ? Math.max(i, this.f6531a.b()) : i;
    }
}
