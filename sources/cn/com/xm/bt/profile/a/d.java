package cn.com.xm.bt.profile.a;

import android.text.TextUtils;
import cn.com.xm.bt.a.a;
import cn.com.xm.bt.c.b;
import cn.com.xm.bt.c.c;

public class d {

    /* renamed from: a  reason: collision with root package name */
    private e f578a = null;

    public d(b bVar) {
        this.f578a = new e(bVar);
    }

    public boolean a(a aVar, f fVar) {
        boolean z;
        if (aVar == null || fVar == null) {
            return false;
        }
        this.f578a.a(aVar.c());
        if (!this.f578a.a()) {
            fVar.onAuthentication(new c(1, 4));
            return false;
        }
        b f = this.f578a.f();
        a.b("HMProAuthController", "AuthProfileInfo:" + f);
        if (f == null || f.a() < 1) {
            z = b(aVar, fVar);
        } else {
            this.f578a.b(Byte.MIN_VALUE);
            z = a(aVar, f, fVar);
        }
        this.f578a.b();
        return z;
    }

    private boolean b(a aVar, f fVar) {
        String a2 = aVar.a();
        if (TextUtils.isEmpty(a2)) {
            fVar.onAuthentication(new c(1, 4));
            return false;
        }
        boolean z = !aVar.b();
        if (aVar.b()) {
            if (this.f578a.a(a2)) {
                fVar.onAuthentication(new c(2));
                int d = this.f578a.d();
                a.a("HMProAuthController", "response code:" + d);
                if (d == 1) {
                    fVar.onAuthentication(new c(3, 0, d));
                    z = true;
                } else if (d == 2) {
                    fVar.onAuthentication(new c(4, 0, d));
                } else {
                    fVar.onAuthentication(new c(1, 3, d));
                }
            } else {
                fVar.onAuthentication(new c(1, 4));
            }
        }
        if (!z) {
            return false;
        }
        int b = this.f578a.b(a2);
        a.a("HMProAuthController", "response code:" + b);
        if (b == 1) {
            c cVar = new c(0, 0, b);
            cVar.a(this.f578a.e());
            fVar.onAuthentication(cVar);
            return true;
        }
        fVar.onAuthentication(new c(1, 4, b));
        return false;
    }

    private boolean a(a aVar, b bVar, f fVar) {
        byte[] bArr;
        int i = 4;
        if (aVar.b()) {
            byte[] c = this.f578a.c();
            a.b("HMProAuthController", "randomValue:" + c.a(c));
            if (c == null || c.length != 16) {
                fVar.onAuthentication(new c(1, 4));
                this.f578a.b();
                return false;
            }
            byte[] a2 = e.a(this.f578a.g().getAddress(), c);
            a.b("HMProAuthController", "newRandom:" + c.a(a2));
            if (a2 == null) {
                fVar.onAuthentication(new c(1, 4));
                this.f578a.b();
                return false;
            }
            byte[] b = e.b(a2);
            a.b("HMProAuthController", "sha256:" + c.a(b));
            if (b == null || b.length != 32) {
                fVar.onAuthentication(new c(1, 4));
                this.f578a.b();
                return false;
            }
            byte[] onGetSignData = fVar.onGetSignData(b, bVar.c(), bVar.b());
            StringBuilder sb = new StringBuilder();
            sb.append("signData:");
            sb.append(c.a(onGetSignData));
            sb.append(",len:");
            sb.append(onGetSignData != null ? onGetSignData.length : 0);
            a.b("HMProAuthController", sb.toString());
            if (onGetSignData == null) {
                fVar.onAuthentication(new c(1, 4));
                this.f578a.b();
                return false;
            } else if (this.f578a.a(onGetSignData, new byte[]{0, 0, 0, 0})) {
                fVar.onAuthentication(new c(2));
                int d = this.f578a.d();
                if (d == 1) {
                    fVar.onAuthentication(new c(3));
                    bArr = new byte[16];
                    System.arraycopy(b, 0, bArr, 0, 16);
                    aVar.a(c.d(bArr));
                } else if (d == 2) {
                    fVar.onAuthentication(new c(4));
                    this.f578a.b();
                    return false;
                } else {
                    fVar.onAuthentication(new c(1, 3));
                    this.f578a.b();
                    return false;
                }
            } else {
                fVar.onAuthentication(new c(1, 4));
                this.f578a.b();
                return false;
            }
        } else {
            bArr = c.c(aVar.a());
            if (bArr == null || bArr.length != 16) {
                fVar.onAuthentication(new c(1, 4));
                return false;
            }
        }
        int a3 = this.f578a.a(bArr);
        if (a3 == 1) {
            c cVar = new c(0);
            cVar.a(this.f578a.e());
            fVar.onAuthentication(cVar);
            return true;
        }
        if (a3 == 7 || a3 == 8) {
            i = 5;
        }
        fVar.onAuthentication(new c(1, i));
        return false;
    }
}
