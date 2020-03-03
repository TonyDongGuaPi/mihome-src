package org.mp4parser.boxes.microsoft;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractFullBox;
import org.mp4parser.support.RequiresParseDetailAspect;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class TfrfBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart b = null;
    private static final JoinPoint.StaticPart c = null;
    private static final JoinPoint.StaticPart d = null;

    /* renamed from: a  reason: collision with root package name */
    public List<Entry> f3939a = new ArrayList();

    static {
        g();
    }

    private static void g() {
        Factory factory = new Factory("TfrfBox.java", TfrfBox.class);
        b = factory.a("method-execution", (Signature) factory.a("1", "getFragmentCount", "org.mp4parser.boxes.microsoft.TfrfBox", "", "", "", "long"), 91);
        c = factory.a("method-execution", (Signature) factory.a("1", "getEntries", "org.mp4parser.boxes.microsoft.TfrfBox", "", "", "", "java.util.List"), 95);
        d = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.microsoft.TfrfBox", "", "", "", "java.lang.String"), 100);
    }

    public TfrfBox() {
        super("uuid");
    }

    public byte[] af_() {
        return new byte[]{-44, Byte.MIN_VALUE, Constants.TagName.ELECTRONIC_OUT_SERIAL, -14, -54, ScriptToolsConst.TagName.TagApdu, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.PREDEPOSIT_TYPE, Constants.TagName.URL_TYPE, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.QUERY_RECORD_COUNT, -53, Constants.TagName.INVOICE_TOKEN_OBJECT_LIST, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.OPERATION_STEP, -97};
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        return (long) ((this.f3939a.size() * (ag_() == 1 ? 16 : 8)) + 5);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
        IsoTypeWriter.d(byteBuffer, this.f3939a.size());
        for (Entry next : this.f3939a) {
            if (ag_() == 1) {
                IsoTypeWriter.a(byteBuffer, next.f3940a);
                IsoTypeWriter.a(byteBuffer, next.b);
            } else {
                IsoTypeWriter.b(byteBuffer, next.f3940a);
                IsoTypeWriter.b(byteBuffer, next.b);
            }
        }
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
        int f = IsoTypeReader.f(byteBuffer);
        for (int i = 0; i < f; i++) {
            Entry entry = new Entry();
            if (ag_() == 1) {
                entry.f3940a = IsoTypeReader.h(byteBuffer);
                entry.b = IsoTypeReader.h(byteBuffer);
            } else {
                entry.f3940a = IsoTypeReader.b(byteBuffer);
                entry.b = IsoTypeReader.b(byteBuffer);
            }
            this.f3939a.add(entry);
        }
    }

    public long e() {
        RequiresParseDetailAspect.a().a(Factory.a(b, (Object) this, (Object) this));
        return (long) this.f3939a.size();
    }

    public List<Entry> f() {
        RequiresParseDetailAspect.a().a(Factory.a(c, (Object) this, (Object) this));
        return this.f3939a;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(d, (Object) this, (Object) this));
        return "TfrfBox" + "{entries=" + this.f3939a + Operators.BLOCK_END;
    }

    public class Entry {

        /* renamed from: a  reason: collision with root package name */
        long f3940a;
        long b;

        public Entry() {
        }

        public long a() {
            return this.f3940a;
        }

        public long b() {
            return this.b;
        }

        public String toString() {
            return "Entry" + "{fragmentAbsoluteTime=" + this.f3940a + ", fragmentAbsoluteDuration=" + this.b + Operators.BLOCK_END;
        }
    }
}
