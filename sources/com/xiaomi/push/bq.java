package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.channel.commonutils.logger.b;
import java.util.Date;

class bq implements fx {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ bp f12655a;

    bq(bp bpVar) {
        this.f12655a = bpVar;
    }

    public void a(fu fuVar) {
        b.c("[Slim] " + this.f12655a.b.format(new Date()) + " Connection reconnected (" + this.f12655a.c.hashCode() + Operators.BRACKET_END_STR);
    }

    public void a(fu fuVar, int i, Exception exc) {
        b.c("[Slim] " + this.f12655a.b.format(new Date()) + " Connection closed (" + this.f12655a.c.hashCode() + Operators.BRACKET_END_STR);
    }

    public void a(fu fuVar, Exception exc) {
        b.c("[Slim] " + this.f12655a.b.format(new Date()) + " Reconnection failed due to an exception (" + this.f12655a.c.hashCode() + Operators.BRACKET_END_STR);
        exc.printStackTrace();
    }

    public void b(fu fuVar) {
        b.c("[Slim] " + this.f12655a.b.format(new Date()) + " Connection started (" + this.f12655a.c.hashCode() + Operators.BRACKET_END_STR);
    }
}
