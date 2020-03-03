package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.channel.commonutils.logger.b;
import java.text.SimpleDateFormat;
import java.util.Date;

public class bp implements gg {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f12653a;
    /* access modifiers changed from: private */
    public SimpleDateFormat b = new SimpleDateFormat("hh:mm:ss aaa");
    /* access modifiers changed from: private */
    public fu c = null;
    private a d = null;
    private a e = null;
    private fx f = null;
    private final String g = "[Slim] ";

    class a implements fz, gh {

        /* renamed from: a  reason: collision with root package name */
        String f12654a;

        a(boolean z) {
            this.f12654a = z ? " RCV " : " Sent ";
        }

        public void a(fn fnVar) {
            StringBuilder sb;
            String str;
            if (bp.f12653a) {
                sb = new StringBuilder();
                sb.append("[Slim] ");
                sb.append(bp.this.b.format(new Date()));
                sb.append(this.f12654a);
                str = fnVar.toString();
            } else {
                sb = new StringBuilder();
                sb.append("[Slim] ");
                sb.append(bp.this.b.format(new Date()));
                sb.append(this.f12654a);
                sb.append(" Blob [");
                sb.append(fnVar.a());
                sb.append(",");
                sb.append(fnVar.c());
                sb.append(",");
                sb.append(fnVar.h());
                str = Operators.ARRAY_END_STR;
            }
            sb.append(str);
            b.c(sb.toString());
        }

        public void a(gl glVar) {
            StringBuilder sb;
            String str;
            if (bp.f12653a) {
                sb = new StringBuilder();
                sb.append("[Slim] ");
                sb.append(bp.this.b.format(new Date()));
                sb.append(this.f12654a);
                sb.append(" PKT ");
                str = glVar.c();
            } else {
                sb = new StringBuilder();
                sb.append("[Slim] ");
                sb.append(bp.this.b.format(new Date()));
                sb.append(this.f12654a);
                sb.append(" PKT [");
                sb.append(glVar.l());
                sb.append(",");
                sb.append(glVar.k());
                str = Operators.ARRAY_END_STR;
            }
            sb.append(str);
            b.c(sb.toString());
        }

        /* renamed from: a  reason: collision with other method in class */
        public boolean m108a(gl glVar) {
            return true;
        }
    }

    static {
        boolean z = true;
        if (t.b() != 1) {
            z = false;
        }
        f12653a = z;
    }

    public bp(fu fuVar) {
        this.c = fuVar;
        a();
    }

    private void a() {
        this.d = new a(true);
        this.e = new a(false);
        this.c.a((fz) this.d, (gh) this.d);
        this.c.b((fz) this.e, (gh) this.e);
        this.f = new bq(this);
    }
}
