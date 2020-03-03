package com.xiaomi.push;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.push.em;
import com.xiaomi.push.fu;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.am;
import com.xiaomi.push.service.av;
import com.xiaomi.push.service.bb;

public class fs extends gb {
    private Thread v;
    /* access modifiers changed from: private */
    public fo w;
    private fp x;
    private byte[] y;

    public fs(XMPushService xMPushService, fv fvVar) {
        super(xMPushService, fvVar);
    }

    private fn c(boolean z) {
        fr frVar = new fr();
        if (z) {
            frVar.a("1");
        }
        byte[] c = hi.c();
        if (c != null) {
            em.j jVar = new em.j();
            jVar.a(a.a(c));
            frVar.a(jVar.c(), (String) null);
        }
        return frVar;
    }

    private void v() {
        try {
            this.w = new fo(this.p.getInputStream(), this);
            this.x = new fp(this.p.getOutputStream(), this);
            this.v = new ft(this, "Blob Reader (" + this.k + Operators.BRACKET_END_STR);
            this.v.start();
        } catch (Exception e) {
            throw new gf("Error to init reader and writer", e);
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void a(int i, Exception exc) {
        if (this.w != null) {
            this.w.b();
            this.w = null;
        }
        if (this.x != null) {
            try {
                this.x.b();
            } catch (Exception e) {
                b.a((Throwable) e);
            }
            this.x = null;
        }
        this.y = null;
        super.a(i, exc);
    }

    /* access modifiers changed from: package-private */
    public void a(fn fnVar) {
        if (fnVar != null) {
            if (fnVar.d()) {
                b.a("[Slim] RCV blob chid=" + fnVar.c() + "; id=" + fnVar.h() + "; errCode=" + fnVar.e() + "; err=" + fnVar.f());
            }
            if (fnVar.c() == 0) {
                if (MIMCConstant.n.equals(fnVar.a())) {
                    b.a("[Slim] RCV ping id=" + fnVar.h());
                    u();
                } else if ("CLOSE".equals(fnVar.a())) {
                    c(13, (Exception) null);
                }
            }
            for (fu.a a2 : this.f.values()) {
                a2.a(fnVar);
            }
        }
    }

    @Deprecated
    public void a(gl glVar) {
        b(fn.a(glVar, (String) null));
    }

    public synchronized void a(am.b bVar) {
        fm.a(bVar, q(), (fu) this);
    }

    public synchronized void a(String str, String str2) {
        fm.a(str, str2, (fu) this);
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        if (this.x != null) {
            fn c = c(z);
            b.a("[Slim] SND ping id=" + c.h());
            b(c);
            t();
            return;
        }
        throw new gf("The BlobWriter is null.");
    }

    public void a(fn[] fnVarArr) {
        for (fn b : fnVarArr) {
            b(b);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized byte[] a() {
        if (this.y == null && !TextUtils.isEmpty(this.i)) {
            String a2 = bb.a();
            this.y = av.a(this.i.getBytes(), (this.i.substring(this.i.length() / 2) + a2.substring(a2.length() / 2)).getBytes());
        }
        return this.y;
    }

    public void b(fn fnVar) {
        if (this.x != null) {
            try {
                int a2 = this.x.a(fnVar);
                this.n = System.currentTimeMillis();
                String i = fnVar.i();
                if (!TextUtils.isEmpty(i)) {
                    gz.a(this.m, i, (long) a2, false, true, System.currentTimeMillis());
                }
                for (fu.a a3 : this.g.values()) {
                    a3.a(fnVar);
                }
            } catch (Exception e) {
                throw new gf((Throwable) e);
            }
        } else {
            throw new gf("the writer is null.");
        }
    }

    /* access modifiers changed from: package-private */
    public void b(gl glVar) {
        if (glVar != null) {
            for (fu.a a2 : this.f.values()) {
                a2.a(glVar);
            }
        }
    }

    public boolean b() {
        return true;
    }

    /* access modifiers changed from: protected */
    public synchronized void c() {
        v();
        this.x.a();
    }
}
