package org.mp4parser.boxes.microsoft;

import com.alipay.sdk.util.i;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.taobao.weex.el.parse.Operators;
import com.tencent.smtt.sdk.TbsListener;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import org.mp4parser.support.AbstractBox;
import org.mp4parser.support.RequiresParseDetailAspect;

public class XtraBox extends AbstractBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3943a = "Xtra";
    public static final int b = 8;
    public static final int c = 19;
    public static final int d = 21;
    public static final int e = 72;
    private static final long h = 11644473600000L;
    private static final long i = 10000;
    private static final JoinPoint.StaticPart k = null;
    private static final JoinPoint.StaticPart l = null;
    private static final JoinPoint.StaticPart m = null;
    private static final JoinPoint.StaticPart n = null;
    private static final JoinPoint.StaticPart o = null;
    private static final JoinPoint.StaticPart p = null;
    private static final JoinPoint.StaticPart q = null;
    private static final JoinPoint.StaticPart u = null;
    private static final JoinPoint.StaticPart v = null;
    private static final JoinPoint.StaticPart w = null;
    private static final JoinPoint.StaticPart x = null;
    Vector<XtraTag> f = new Vector<>();
    ByteBuffer g;
    private boolean j = false;

    static {
        f();
    }

    /* access modifiers changed from: private */
    public static long d(long j2) {
        return (j2 + h) * 10000;
    }

    private static void f() {
        Factory factory = new Factory("XtraBox.java", XtraBox.class);
        k = factory.a("method-execution", (Signature) factory.a("1", "toString", "org.mp4parser.boxes.microsoft.XtraBox", "", "", "", "java.lang.String"), 131);
        l = factory.a("method-execution", (Signature) factory.a("1", "getAllTagNames", "org.mp4parser.boxes.microsoft.XtraBox", "", "", "", "[Ljava.lang.String;"), 193);
        x = factory.a("method-execution", (Signature) factory.a("1", "setTagValue", "org.mp4parser.boxes.microsoft.XtraBox", "java.lang.String:long", "name:value", "", "void"), 326);
        m = factory.a("method-execution", (Signature) factory.a("1", "getFirstStringValue", "org.mp4parser.boxes.microsoft.XtraBox", "java.lang.String", "name", "", "java.lang.String"), 208);
        n = factory.a("method-execution", (Signature) factory.a("1", "getFirstDateValue", "org.mp4parser.boxes.microsoft.XtraBox", "java.lang.String", "name", "", "java.util.Date"), 224);
        o = factory.a("method-execution", (Signature) factory.a("1", "getFirstLongValue", "org.mp4parser.boxes.microsoft.XtraBox", "java.lang.String", "name", "", "java.lang.Long"), (int) PsExtractor.VIDEO_STREAM_MASK);
        p = factory.a("method-execution", (Signature) factory.a("1", "getValues", "org.mp4parser.boxes.microsoft.XtraBox", "java.lang.String", "name", "", "[Ljava.lang.Object;"), 256);
        q = factory.a("method-execution", (Signature) factory.a("1", "removeTag", "org.mp4parser.boxes.microsoft.XtraBox", "java.lang.String", "name", "", "void"), 275);
        u = factory.a("method-execution", (Signature) factory.a("1", "setTagValues", "org.mp4parser.boxes.microsoft.XtraBox", "java.lang.String:[Ljava.lang.String;", "name:values", "", "void"), 288);
        v = factory.a("method-execution", (Signature) factory.a("1", "setTagValue", "org.mp4parser.boxes.microsoft.XtraBox", "java.lang.String:java.lang.String", "name:value", "", "void"), 303);
        w = factory.a("method-execution", (Signature) factory.a("1", "setTagValue", "org.mp4parser.boxes.microsoft.XtraBox", "java.lang.String:java.util.Date", "name:date", "", "void"), (int) TbsListener.ErrorCode.ERROR_CANLOADVIDEO_RETURN_FALSE);
    }

    public XtraBox() {
        super(f3943a);
    }

    public XtraBox(String str) {
        super(str);
    }

    /* access modifiers changed from: private */
    public static long c(long j2) {
        return (j2 / 10000) - h;
    }

    /* access modifiers changed from: private */
    public static void c(ByteBuffer byteBuffer, String str) {
        try {
            byteBuffer.put(str.getBytes("US-ASCII"));
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Shouldn't happen", e2);
        }
    }

    /* access modifiers changed from: private */
    public static String c(ByteBuffer byteBuffer, int i2) {
        byte[] bArr = new byte[i2];
        byteBuffer.get(bArr);
        try {
            return new String(bArr, "US-ASCII");
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Shouldn't happen", e2);
        }
    }

    /* access modifiers changed from: private */
    public static String d(ByteBuffer byteBuffer, int i2) {
        int i3 = (i2 / 2) - 1;
        char[] cArr = new char[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            cArr[i4] = byteBuffer.getChar();
        }
        byteBuffer.getChar();
        return new String(cArr);
    }

    /* access modifiers changed from: private */
    public static void d(ByteBuffer byteBuffer, String str) {
        char[] charArray = str.toCharArray();
        for (char putChar : charArray) {
            byteBuffer.putChar(putChar);
        }
        byteBuffer.putChar(0);
    }

    /* access modifiers changed from: protected */
    public long ai_() {
        if (this.j) {
            return (long) e();
        }
        return (long) this.g.limit();
    }

    private int e() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.f.size(); i3++) {
            i2 += this.f.elementAt(i3).a();
        }
        return i2;
    }

    public String toString() {
        RequiresParseDetailAspect.a().a(Factory.a(k, (Object) this, (Object) this));
        if (!x()) {
            w();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("XtraBox[");
        Iterator<XtraTag> it = this.f.iterator();
        while (it.hasNext()) {
            XtraTag next = it.next();
            Iterator it2 = next.c.iterator();
            while (it2.hasNext()) {
                stringBuffer.append(next.b);
                stringBuffer.append("=");
                stringBuffer.append(((XtraValue) it2.next()).toString());
                stringBuffer.append(i.b);
            }
        }
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }

    public void a(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        this.g = byteBuffer.slice();
        this.j = false;
        try {
            this.f.clear();
            while (byteBuffer.remaining() > 0) {
                XtraTag xtraTag = new XtraTag((XtraTag) null);
                xtraTag.a(byteBuffer);
                this.f.addElement(xtraTag);
            }
            int e2 = e();
            if (remaining == e2) {
                this.j = true;
                byteBuffer.order(ByteOrder.BIG_ENDIAN);
                return;
            }
            throw new RuntimeException("Improperly handled Xtra tag: Calculated sizes don't match ( " + remaining + "/" + e2 + Operators.BRACKET_END_STR);
        } catch (Exception e3) {
            this.j = false;
            PrintStream printStream = System.err;
            printStream.println("Malformed Xtra Tag detected: " + e3.toString());
            e3.printStackTrace();
            byteBuffer.position(byteBuffer.position() + byteBuffer.remaining());
        } catch (Throwable th) {
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        if (this.j) {
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                this.f.elementAt(i2).b(byteBuffer);
            }
            return;
        }
        this.g.rewind();
        byteBuffer.put(this.g);
    }

    public String[] d() {
        RequiresParseDetailAspect.a().a(Factory.a(l, (Object) this, (Object) this));
        String[] strArr = new String[this.f.size()];
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            strArr[i2] = this.f.elementAt(i2).b;
        }
        return strArr;
    }

    public String a(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(m, (Object) this, (Object) this, (Object) str));
        for (Object obj : d(str)) {
            if (obj instanceof String) {
                return (String) obj;
            }
        }
        return null;
    }

    public Date b(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(n, (Object) this, (Object) this, (Object) str));
        for (Object obj : d(str)) {
            if (obj instanceof Date) {
                return (Date) obj;
            }
        }
        return null;
    }

    public Long c(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(o, (Object) this, (Object) this, (Object) str));
        for (Object obj : d(str)) {
            if (obj instanceof Long) {
                return (Long) obj;
            }
        }
        return null;
    }

    public Object[] d(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(p, (Object) this, (Object) this, (Object) str));
        XtraTag f2 = f(str);
        if (f2 == null) {
            return new Object[0];
        }
        Object[] objArr = new Object[f2.c.size()];
        for (int i2 = 0; i2 < f2.c.size(); i2++) {
            objArr[i2] = ((XtraValue) f2.c.elementAt(i2)).a();
        }
        return objArr;
    }

    public void e(String str) {
        RequiresParseDetailAspect.a().a(Factory.a(q, (Object) this, (Object) this, (Object) str));
        XtraTag f2 = f(str);
        if (f2 != null) {
            this.f.remove(f2);
        }
    }

    public void a(String str, String[] strArr) {
        RequiresParseDetailAspect.a().a(Factory.a(u, (Object) this, (Object) this, (Object) str, (Object) strArr));
        e(str);
        XtraTag xtraTag = new XtraTag(str, (XtraTag) null);
        for (String xtraValue : strArr) {
            xtraTag.c.addElement(new XtraValue(xtraValue, (XtraValue) null));
        }
        this.f.addElement(xtraTag);
    }

    public void a(String str, String str2) {
        RequiresParseDetailAspect.a().a(Factory.a(v, (Object) this, (Object) this, (Object) str, (Object) str2));
        a(str, new String[]{str2});
    }

    public void a(String str, Date date) {
        RequiresParseDetailAspect.a().a(Factory.a(w, (Object) this, (Object) this, (Object) str, (Object) date));
        e(str);
        XtraTag xtraTag = new XtraTag(str, (XtraTag) null);
        xtraTag.c.addElement(new XtraValue(date, (XtraValue) null));
        this.f.addElement(xtraTag);
    }

    public void a(String str, long j2) {
        RequiresParseDetailAspect.a().a(Factory.a(x, (Object) this, (Object) this, (Object) str, Conversions.a(j2)));
        e(str);
        XtraTag xtraTag = new XtraTag(str, (XtraTag) null);
        xtraTag.c.addElement(new XtraValue(j2, (XtraValue) null));
        this.f.addElement(xtraTag);
    }

    private XtraTag f(String str) {
        Iterator<XtraTag> it = this.f.iterator();
        while (it.hasNext()) {
            XtraTag next = it.next();
            if (next.b.equals(str)) {
                return next;
            }
        }
        return null;
    }

    private static class XtraTag {

        /* renamed from: a  reason: collision with root package name */
        private int f3944a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public Vector<XtraValue> c;

        private XtraTag() {
            this.c = new Vector<>();
        }

        /* synthetic */ XtraTag(XtraTag xtraTag) {
            this();
        }

        /* synthetic */ XtraTag(String str, XtraTag xtraTag) {
            this(str);
        }

        private XtraTag(String str) {
            this();
            this.b = str;
        }

        /* access modifiers changed from: private */
        public void a(ByteBuffer byteBuffer) {
            this.f3944a = byteBuffer.getInt();
            this.b = XtraBox.c(byteBuffer, byteBuffer.getInt());
            int i = byteBuffer.getInt();
            for (int i2 = 0; i2 < i; i2++) {
                XtraValue xtraValue = new XtraValue((XtraValue) null);
                xtraValue.a(byteBuffer);
                this.c.addElement(xtraValue);
            }
            if (this.f3944a != a()) {
                throw new RuntimeException("Improperly handled Xtra tag: Sizes don't match ( " + this.f3944a + "/" + a() + ") on " + this.b);
            }
        }

        /* access modifiers changed from: private */
        public void b(ByteBuffer byteBuffer) {
            byteBuffer.putInt(a());
            byteBuffer.putInt(this.b.length());
            XtraBox.c(byteBuffer, this.b);
            byteBuffer.putInt(this.c.size());
            for (int i = 0; i < this.c.size(); i++) {
                this.c.elementAt(i).b(byteBuffer);
            }
        }

        /* access modifiers changed from: private */
        public int a() {
            int length = this.b.length() + 12;
            for (int i = 0; i < this.c.size(); i++) {
                length += this.c.elementAt(i).b();
            }
            return length;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.b);
            stringBuffer.append(" [");
            stringBuffer.append(this.f3944a);
            stringBuffer.append("/");
            stringBuffer.append(this.c.size());
            stringBuffer.append("]:\n");
            for (int i = 0; i < this.c.size(); i++) {
                stringBuffer.append("  ");
                stringBuffer.append(this.c.elementAt(i).toString());
                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    private static class XtraValue {

        /* renamed from: a  reason: collision with root package name */
        public int f3945a;
        public String b;
        public long c;
        public byte[] d;
        public Date e;

        private XtraValue() {
        }

        /* synthetic */ XtraValue(XtraValue xtraValue) {
            this();
        }

        private XtraValue(String str) {
            this.f3945a = 8;
            this.b = str;
        }

        /* synthetic */ XtraValue(String str, XtraValue xtraValue) {
            this(str);
        }

        private XtraValue(long j) {
            this.f3945a = 19;
            this.c = j;
        }

        /* synthetic */ XtraValue(long j, XtraValue xtraValue) {
            this(j);
        }

        private XtraValue(Date date) {
            this.f3945a = 21;
            this.e = date;
        }

        /* synthetic */ XtraValue(Date date, XtraValue xtraValue) {
            this(date);
        }

        /* access modifiers changed from: private */
        public Object a() {
            int i = this.f3945a;
            if (i == 8) {
                return this.b;
            }
            if (i == 19) {
                return new Long(this.c);
            }
            if (i != 21) {
                return this.d;
            }
            return this.e;
        }

        /* access modifiers changed from: private */
        public void a(ByteBuffer byteBuffer) {
            int i = byteBuffer.getInt() - 6;
            this.f3945a = byteBuffer.getShort();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            int i2 = this.f3945a;
            if (i2 == 8) {
                this.b = XtraBox.d(byteBuffer, i);
            } else if (i2 == 19) {
                this.c = byteBuffer.getLong();
            } else if (i2 != 21) {
                this.d = new byte[i];
                byteBuffer.get(this.d);
            } else {
                this.e = new Date(XtraBox.c(byteBuffer.getLong()));
            }
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        /* access modifiers changed from: private */
        public void b(ByteBuffer byteBuffer) {
            try {
                byteBuffer.putInt(b());
                byteBuffer.putShort((short) this.f3945a);
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                int i = this.f3945a;
                if (i == 8) {
                    XtraBox.d(byteBuffer, this.b);
                } else if (i == 19) {
                    byteBuffer.putLong(this.c);
                } else if (i != 21) {
                    byteBuffer.put(this.d);
                } else {
                    byteBuffer.putLong(XtraBox.d(this.e.getTime()));
                }
            } finally {
                byteBuffer.order(ByteOrder.BIG_ENDIAN);
            }
        }

        /* access modifiers changed from: private */
        public int b() {
            int i = this.f3945a;
            if (i == 8) {
                return (this.b.length() * 2) + 2 + 6;
            }
            if (i == 19 || i == 21) {
                return 14;
            }
            return this.d.length + 6;
        }

        public String toString() {
            int i = this.f3945a;
            if (i == 8) {
                return "[string]" + this.b;
            } else if (i == 19) {
                return "[long]" + String.valueOf(this.c);
            } else if (i != 21) {
                return "[GUID](nonParsed)";
            } else {
                return "[filetime]" + this.e.toString();
            }
        }
    }
}
