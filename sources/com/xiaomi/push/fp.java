package com.xiaomi.push;

import android.os.Build;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.push.em;
import com.xiaomi.push.service.av;
import com.xiaomi.push.service.bb;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.Adler32;

public class fp {

    /* renamed from: a  reason: collision with root package name */
    ByteBuffer f12740a = ByteBuffer.allocate(2048);
    private ByteBuffer b = ByteBuffer.allocate(4);
    private Adler32 c = new Adler32();
    private fs d;
    private OutputStream e;
    private int f;
    private int g;
    private byte[] h;

    fp(OutputStream outputStream, fs fsVar) {
        this.e = new BufferedOutputStream(outputStream);
        this.d = fsVar;
        TimeZone timeZone = TimeZone.getDefault();
        this.f = timeZone.getRawOffset() / 3600000;
        this.g = timeZone.useDaylightTime() ? 1 : 0;
    }

    public int a(fn fnVar) {
        int l = fnVar.l();
        if (l > 32768) {
            b.a("Blob size=" + l + " should be less than " + 32768 + " Drop blob chid=" + fnVar.c() + " id=" + fnVar.h());
            return 0;
        }
        this.f12740a.clear();
        int i = l + 8 + 4;
        if (i > this.f12740a.capacity() || this.f12740a.capacity() > 4096) {
            this.f12740a = ByteBuffer.allocate(i);
        }
        this.f12740a.putShort(-15618);
        this.f12740a.putShort(5);
        this.f12740a.putInt(l);
        int position = this.f12740a.position();
        this.f12740a = fnVar.a(this.f12740a);
        if (!MIMCConstant.l.equals(fnVar.a())) {
            if (this.h == null) {
                this.h = this.d.a();
            }
            av.a(this.h, this.f12740a.array(), true, position, l);
        }
        this.c.reset();
        this.c.update(this.f12740a.array(), 0, this.f12740a.position());
        this.b.putInt(0, (int) this.c.getValue());
        this.e.write(this.f12740a.array(), 0, this.f12740a.position());
        this.e.write(this.b.array(), 0, 4);
        this.e.flush();
        int position2 = this.f12740a.position() + 4;
        b.c("[Slim] Wrote {cmd=" + fnVar.a() + ";chid=" + fnVar.c() + ";len=" + position2 + "}");
        return position2;
    }

    public void a() {
        em.e eVar = new em.e();
        eVar.a(106);
        eVar.a(Build.MODEL);
        eVar.b(t.e());
        eVar.c(bb.a());
        eVar.b(39);
        eVar.d(this.d.f());
        eVar.e(this.d.e());
        eVar.f(Locale.getDefault().toString());
        eVar.c(Build.VERSION.SDK_INT);
        byte[] a2 = this.d.d().a();
        if (a2 != null) {
            eVar.a(em.b.b(a2));
        }
        fn fnVar = new fn();
        fnVar.a(0);
        fnVar.a(MIMCConstant.l, (String) null);
        fnVar.a(0, "xiaomi.com", (String) null);
        fnVar.a(eVar.c(), (String) null);
        a(fnVar);
        b.a("[slim] open conn: andver=" + Build.VERSION.SDK_INT + " sdk=" + 39 + " hash=" + bb.a() + " tz=" + this.f + ":" + this.g + " Model=" + Build.MODEL + " os=" + Build.VERSION.INCREMENTAL);
    }

    public void b() {
        fn fnVar = new fn();
        fnVar.a("CLOSE", (String) null);
        a(fnVar);
        this.e.close();
    }
}
