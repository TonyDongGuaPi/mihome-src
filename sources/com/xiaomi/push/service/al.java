package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.push.cx;
import com.xiaomi.push.db;
import com.xiaomi.push.dk;
import com.xiaomi.push.em;
import com.xiaomi.push.fj;
import com.xiaomi.push.fn;
import com.xiaomi.push.fv;
import com.xiaomi.push.gi;
import com.xiaomi.push.gj;
import com.xiaomi.push.gk;
import com.xiaomi.push.gl;
import com.xiaomi.push.gz;
import com.xiaomi.push.hi;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.am;
import com.xiaomi.stat.c.c;
import java.util.Date;
import org.cybergarage.http.HTTP;

public class al {

    /* renamed from: a  reason: collision with root package name */
    private XMPushService f12879a;

    al(XMPushService xMPushService) {
        this.f12879a = xMPushService;
    }

    private void a(gi giVar) {
        String c = giVar.c();
        if (!TextUtils.isEmpty(c)) {
            String[] split = c.split(i.b);
            cx a2 = db.a().a(fv.a(), false);
            if (a2 != null && split.length > 0) {
                a2.a(split);
                this.f12879a.a(20, (Exception) null);
                this.f12879a.a(true);
            }
        }
    }

    private void b(gl glVar) {
        am.b a2;
        String m = glVar.m();
        String l = glVar.l();
        if (!TextUtils.isEmpty(m) && !TextUtils.isEmpty(l) && (a2 = am.a().a(l, m)) != null) {
            gz.a(this.f12879a, a2.f290a, (long) gz.b(glVar.c()), true, true, System.currentTimeMillis());
        }
    }

    private void c(fn fnVar) {
        am.b a2;
        String j = fnVar.j();
        String num = Integer.toString(fnVar.c());
        if (!TextUtils.isEmpty(j) && !TextUtils.isEmpty(num) && (a2 = am.a().a(num, j)) != null) {
            gz.a(this.f12879a, a2.f290a, (long) fnVar.l(), true, true, System.currentTimeMillis());
        }
    }

    public void a(fn fnVar) {
        if (5 != fnVar.c()) {
            c(fnVar);
        }
        try {
            b(fnVar);
        } catch (Exception e) {
            b.a("handle Blob chid = " + fnVar.c() + " cmd = " + fnVar.a() + " packetid = " + fnVar.h() + " failure ", (Throwable) e);
        }
    }

    public void a(gl glVar) {
        if (!"5".equals(glVar.l())) {
            b(glVar);
        }
        String l = glVar.l();
        if (TextUtils.isEmpty(l)) {
            l = "1";
            glVar.l(l);
        }
        if (l.equals("0")) {
            b.a("Received wrong packet with chid = 0 : " + glVar.c());
        }
        if (glVar instanceof gj) {
            gi p = glVar.p("kick");
            if (p != null) {
                String m = glVar.m();
                String a2 = p.a("type");
                String a3 = p.a("reason");
                b.a("kicked by server, chid=" + l + " res=" + am.b.a(m) + " type=" + a2 + " reason=" + a3);
                if ("wait".equals(a2)) {
                    am.b a4 = am.a().a(l, m);
                    if (a4 != null) {
                        this.f12879a.a(a4);
                        a4.a(am.c.unbind, 3, 0, a3, a2);
                        return;
                    }
                    return;
                }
                this.f12879a.a(l, m, 3, a3, a2);
                am.a().a(l, m);
                return;
            }
        } else if (glVar instanceof gk) {
            gk gkVar = (gk) glVar;
            if ("redir".equals(gkVar.a())) {
                gi p2 = gkVar.p("hosts");
                if (p2 != null) {
                    a(p2);
                    return;
                }
                return;
            }
        }
        this.f12879a.b().a(this.f12879a, l, glVar);
    }

    public void b(fn fnVar) {
        StringBuilder sb;
        String f;
        String str;
        am.c cVar;
        int i;
        int i2;
        String a2 = fnVar.a();
        if (fnVar.c() != 0) {
            String num = Integer.toString(fnVar.c());
            if (MIMCConstant.p.equals(fnVar.a())) {
                if (!fnVar.d()) {
                    this.f12879a.b().a(this.f12879a, num, fnVar);
                    return;
                }
                sb = new StringBuilder();
                sb.append("Recv SECMSG errCode = ");
                sb.append(fnVar.e());
                sb.append(" errStr = ");
                f = fnVar.f();
            } else if (MIMCConstant.m.equals(a2)) {
                em.d b = em.d.b(fnVar.k());
                String j = fnVar.j();
                am.b a3 = am.a().a(num, j);
                if (a3 != null) {
                    if (b.d()) {
                        b.a("SMACK: channel bind succeeded, chid=" + fnVar.c());
                        a3.a(am.c.binded, 1, 0, (String) null, (String) null);
                        return;
                    }
                    String f2 = b.f();
                    if ("auth".equals(f2)) {
                        if ("invalid-sig".equals(b.h())) {
                            b.a("SMACK: bind error invalid-sig token = " + a3.c + " sec = " + a3.h);
                            hi.a(0, fj.BIND_INVALID_SIG.a(), 1, (String) null, 0);
                        }
                        cVar = am.c.unbind;
                        i = 1;
                        i2 = 5;
                    } else if ("cancel".equals(f2)) {
                        cVar = am.c.unbind;
                        i = 1;
                        i2 = 7;
                    } else {
                        if ("wait".equals(f2)) {
                            this.f12879a.a(a3);
                            a3.a(am.c.unbind, 1, 7, b.h(), f2);
                        }
                        str = "SMACK: channel bind failed, chid=" + num + " reason=" + b.h();
                        b.a(str);
                    }
                    a3.a(cVar, i, i2, b.h(), f2);
                    am.a().a(num, j);
                    str = "SMACK: channel bind failed, chid=" + num + " reason=" + b.h();
                    b.a(str);
                }
                return;
            } else if (MIMCConstant.q.equals(a2)) {
                em.g b2 = em.g.b(fnVar.k());
                String j2 = fnVar.j();
                String d = b2.d();
                String f3 = b2.f();
                b.a("kicked by server, chid=" + num + " res= " + am.b.a(j2) + " type=" + d + " reason=" + f3);
                if ("wait".equals(d)) {
                    am.b a4 = am.a().a(num, j2);
                    if (a4 != null) {
                        this.f12879a.a(a4);
                        a4.a(am.c.unbind, 3, 0, f3, d);
                        return;
                    }
                    return;
                }
                this.f12879a.a(num, j2, 3, f3, d);
                am.a().a(num, j2);
                return;
            } else {
                return;
            }
        } else if (MIMCConstant.n.equals(a2)) {
            byte[] k = fnVar.k();
            if (k != null && k.length > 0) {
                em.j b3 = em.j.b(k);
                if (b3.f()) {
                    bb.a().a(b3.g());
                }
            }
            if (!c.f23036a.equals(this.f12879a.getPackageName())) {
                this.f12879a.a();
            }
            if ("1".equals(fnVar.h())) {
                b.a("received a server ping");
            } else {
                hi.b();
            }
            this.f12879a.b();
            return;
        } else if ("SYNC".equals(a2)) {
            if ("CONF".equals(fnVar.b())) {
                bb.a().a(em.b.b(fnVar.k()));
                return;
            } else if (TextUtils.equals("U", fnVar.b())) {
                em.k b4 = em.k.b(fnVar.k());
                dk.a((Context) this.f12879a).a(b4.d(), b4.f(), new Date(b4.h()), new Date(b4.j()), b4.n() * 1024, b4.l());
                fn fnVar2 = new fn();
                fnVar2.a(0);
                fnVar2.a(fnVar.a(), "UCA");
                fnVar2.a(fnVar.h());
                this.f12879a.a((XMPushService.i) new ax(this.f12879a, fnVar2));
                return;
            } else if (TextUtils.equals("P", fnVar.b())) {
                em.i b5 = em.i.b(fnVar.k());
                fn fnVar3 = new fn();
                fnVar3.a(0);
                fnVar3.a(fnVar.a(), "PCA");
                fnVar3.a(fnVar.h());
                em.i iVar = new em.i();
                if (b5.e()) {
                    iVar.a(b5.d());
                }
                fnVar3.a(iVar.c(), (String) null);
                this.f12879a.a((XMPushService.i) new ax(this.f12879a, fnVar3));
                sb = new StringBuilder();
                sb.append("ACK msgP: id = ");
                f = fnVar.h();
            } else {
                return;
            }
        } else if (HTTP.NOTIFY.equals(fnVar.a())) {
            em.h b6 = em.h.b(fnVar.k());
            sb = new StringBuilder();
            sb.append("notify by server err = ");
            sb.append(b6.d());
            sb.append(" desc = ");
            f = b6.f();
        } else {
            return;
        }
        sb.append(f);
        str = sb.toString();
        b.a(str);
    }
}
