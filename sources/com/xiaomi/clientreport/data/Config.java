package com.xiaomi.clientreport.data;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.push.bl;

public class Config {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f10075a = true;
    public static final boolean b = false;
    public static final boolean c = false;
    public static final long d = 1048576;
    public static final long e = 86400;
    public static final long f = 86400;
    private String g;
    private boolean h;
    private boolean i;
    private boolean j;
    private long k;
    private long l;
    private long m;

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f10076a = -1;
        /* access modifiers changed from: private */
        public int b = -1;
        /* access modifiers changed from: private */
        public int c = -1;
        /* access modifiers changed from: private */
        public String d = null;
        /* access modifiers changed from: private */
        public long e = -1;
        /* access modifiers changed from: private */
        public long f = -1;
        /* access modifiers changed from: private */
        public long g = -1;

        public Builder a(long j) {
            this.e = j;
            return this;
        }

        public Builder a(String str) {
            this.d = str;
            return this;
        }

        public Builder a(boolean z) {
            this.f10076a = z ? 1 : 0;
            return this;
        }

        public Config a(Context context) {
            return new Config(context, this);
        }

        public Builder b(long j) {
            this.f = j;
            return this;
        }

        public Builder b(boolean z) {
            this.b = z ? 1 : 0;
            return this;
        }

        public Builder c(long j) {
            this.g = j;
            return this;
        }

        public Builder c(boolean z) {
            this.c = z ? 1 : 0;
            return this;
        }
    }

    private Config() {
        this.h = true;
        this.i = false;
        this.j = false;
        this.k = 1048576;
        this.l = 86400;
        this.m = 86400;
    }

    private Config(Context context, Builder builder) {
        this.h = true;
        this.i = false;
        this.j = false;
        long j2 = 1048576;
        this.k = 1048576;
        this.l = 86400;
        this.m = 86400;
        if (builder.f10076a == 0) {
            this.h = false;
        } else {
            int a2 = builder.f10076a;
            this.h = true;
        }
        this.g = !TextUtils.isEmpty(builder.d) ? builder.d : bl.a(context);
        this.k = builder.e > -1 ? builder.e : j2;
        if (builder.f > -1) {
            this.l = builder.f;
        } else {
            this.l = 86400;
        }
        if (builder.g > -1) {
            this.m = builder.g;
        } else {
            this.m = 86400;
        }
        if (builder.b != 0 && builder.b == 1) {
            this.i = true;
        } else {
            this.i = false;
        }
        if (builder.c != 0 && builder.c == 1) {
            this.j = true;
        } else {
            this.j = false;
        }
    }

    public static Builder a() {
        return new Builder();
    }

    public static Config a(Context context) {
        return a().a(true).a(bl.a(context)).a(1048576).b(false).b(86400).c(false).c(86400).a(context);
    }

    public boolean b() {
        return this.h;
    }

    public boolean c() {
        return this.i;
    }

    public boolean d() {
        return this.j;
    }

    public long e() {
        return this.k;
    }

    public long f() {
        return this.l;
    }

    public long g() {
        return this.m;
    }

    public String toString() {
        return "Config{mEventEncrypted=" + this.h + ", mAESKey='" + this.g + Operators.SINGLE_QUOTE + ", mMaxFileLength=" + this.k + ", mEventUploadSwitchOpen=" + this.i + ", mPerfUploadSwitchOpen=" + this.j + ", mEventUploadFrequency=" + this.l + ", mPerfUploadFrequency=" + this.m + Operators.BLOCK_END;
    }
}
