package com.xiaomi.youpin.login.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xiaomi.passport.interfaces.AuthenticatorComponentNameInterface;
import com.xiaomi.youpin.login.LoginDependencyApi;
import com.xiaomi.youpin.login.api.stat.LoginStatInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoginConfig {

    /* renamed from: a  reason: collision with root package name */
    private Context f1583a;
    private int b;
    private boolean c;
    private String d;
    private IWXAPI e;
    private String f;
    private LoginStatInterface g;
    @Deprecated
    private boolean h;
    private boolean i;
    private String j;
    private List<String> k;
    private List<String> l;
    private LoginDependencyApi m;
    private boolean n;
    private AuthenticatorComponentNameInterface o;
    private String p;

    private LoginConfig(Builder builder) {
        this.k = new ArrayList();
        this.l = new ArrayList();
        this.f1583a = builder.f1584a;
        this.b = builder.n;
        this.d = builder.b;
        this.e = builder.c;
        this.g = builder.e;
        this.h = builder.f;
        this.i = builder.g;
        this.j = builder.h;
        this.k = builder.i;
        this.l = builder.j;
        this.f = builder.d;
        this.m = builder.k;
        this.c = builder.o;
        this.n = builder.l;
        this.o = builder.m;
        this.p = builder.p;
    }

    public void a(Context context, LoginStatInterface loginStatInterface) {
        this.f1583a = context.getApplicationContext();
        this.g = loginStatInterface;
    }

    public boolean a() {
        return this.h;
    }

    public boolean b() {
        return !TextUtils.isEmpty(this.d) && this.e != null;
    }

    public boolean c() {
        return !TextUtils.isEmpty(this.f);
    }

    public String d() {
        return this.j;
    }

    public List<String> e() {
        return this.k;
    }

    public String f() {
        return this.f;
    }

    public int g() {
        return this.b;
    }

    public LoginDependencyApi h() {
        return this.m;
    }

    public boolean i() {
        return this.c;
    }

    public boolean j() {
        return this.n;
    }

    public AuthenticatorComponentNameInterface k() {
        return this.o;
    }

    public String l() {
        return this.d;
    }

    public LoginStatInterface m() {
        return this.g;
    }

    public IWXAPI n() {
        return this.e;
    }

    public Context o() {
        return this.f1583a;
    }

    public boolean p() {
        return this.i;
    }

    public String q() {
        return this.p;
    }

    public List<String> r() {
        return this.l;
    }

    public void a(List<String> list) {
        this.l = list;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        Context f1584a;
        String b;
        IWXAPI c;
        String d;
        LoginStatInterface e;
        @Deprecated
        boolean f;
        boolean g;
        String h;
        List<String> i = new ArrayList();
        List<String> j = new ArrayList();
        LoginDependencyApi k;
        boolean l;
        AuthenticatorComponentNameInterface m;
        /* access modifiers changed from: private */
        public int n = 1;
        /* access modifiers changed from: private */
        public boolean o;
        /* access modifiers changed from: private */
        public String p;

        public Builder a(Context context) {
            this.f1584a = context.getApplicationContext();
            return this;
        }

        public Builder a(int i2) {
            this.n = i2;
            return this;
        }

        public Builder a(boolean z) {
            this.o = z;
            return this;
        }

        public Builder a(LoginStatInterface loginStatInterface) {
            this.e = loginStatInterface;
            return this;
        }

        public Builder a(@NonNull String str, @NonNull IWXAPI iwxapi) {
            this.b = str;
            this.c = iwxapi;
            return this;
        }

        public Builder a() {
            this.f = true;
            return this;
        }

        public Builder b() {
            this.g = true;
            return this;
        }

        public Builder a(String str) {
            this.h = str;
            return this;
        }

        public Builder a(String... strArr) {
            Collections.addAll(this.i, strArr);
            return this;
        }

        public Builder b(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.i.add(str);
            }
            return this;
        }

        public Builder b(String... strArr) {
            Collections.addAll(this.j, strArr);
            return this;
        }

        public Builder c(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.j.add(str);
            }
            return this;
        }

        public Builder d(@NonNull String str) {
            this.d = str;
            return this;
        }

        public Builder a(LoginDependencyApi loginDependencyApi) {
            this.k = loginDependencyApi;
            return this;
        }

        public Builder b(boolean z) {
            this.l = z;
            return this;
        }

        public Builder a(AuthenticatorComponentNameInterface authenticatorComponentNameInterface) {
            this.m = authenticatorComponentNameInterface;
            return this;
        }

        public Builder e(String str) {
            this.p = str;
            return this;
        }

        public LoginConfig c() {
            return new LoginConfig(this);
        }
    }
}
