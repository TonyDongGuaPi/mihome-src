package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.XMPSchemaRegistry;
import com.adobe.xmp.options.SerializeOptions;
import com.google.code.microlog4android.format.PatternFormatter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class XMPSerializerRDF {

    /* renamed from: a  reason: collision with root package name */
    static final Set f699a = new HashSet(Arrays.asList(new String[]{XMPConst.ak, "rdf:resource", "rdf:ID", "rdf:bagID", "rdf:nodeID"}));
    private static final int b = 2048;
    private static final String c = "<?xpacket begin=\"﻿\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?>";
    private static final String d = "<?xpacket end=\"";
    private static final String e = "\"?>";
    private static final String f = "<x:xmpmeta xmlns:x=\"adobe:ns:meta/\" x:xmptk=\"";
    private static final String g = "</x:xmpmeta>";
    private static final String h = "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">";
    private static final String i = "</rdf:RDF>";
    private static final String j = "<rdf:Description rdf:about=";
    private static final String k = "</rdf:Description>";
    private static final String l = "<rdf:Description";
    private static final String m = "</rdf:Description>";
    private static final String n = "<rdf:Description/>";
    private XMPMetaImpl o;
    private CountOutputStream p;
    private OutputStreamWriter q;
    private SerializeOptions r;
    private int s = 1;
    private int t;

    private void a(int i2) throws XMPException, IOException {
        if (this.r.k()) {
            int a2 = this.p.a() + (i2 * this.s);
            if (a2 <= this.t) {
                this.t -= a2;
            } else {
                throw new XMPException("Can't fit into specified packet size", 107);
            }
        }
        this.t /= this.s;
        int length = this.r.q().length();
        if (this.t >= length) {
            int i3 = this.t - length;
            while (true) {
                this.t = i3;
                int i4 = length + 100;
                if (this.t >= i4) {
                    a(100, ' ');
                    d();
                    i3 = this.t - i4;
                } else {
                    a(this.t, ' ');
                    d();
                    return;
                }
            }
        } else {
            a(this.t, ' ');
        }
    }

    private void a(int i2, char c2) throws IOException {
        while (i2 > 0) {
            this.q.write(c2);
            i2--;
        }
    }

    private void a(int i2, XMPNode xMPNode) throws IOException, XMPException {
        a(" rdf:parseType=\"Resource\">");
        d();
        int i3 = i2 + 1;
        a(xMPNode, false, true, i3);
        Iterator k2 = xMPNode.k();
        while (k2.hasNext()) {
            a((XMPNode) k2.next(), false, false, i3);
        }
    }

    private void a(XMPNode xMPNode, Set set, int i2) throws IOException {
        if (xMPNode.n().o()) {
            a(xMPNode.m().substring(0, xMPNode.m().length() - 1), xMPNode.l(), set, i2);
        } else if (xMPNode.n().f()) {
            Iterator i3 = xMPNode.i();
            while (i3.hasNext()) {
                a(((XMPNode) i3.next()).l(), (String) null, set, i2);
            }
        }
        Iterator i4 = xMPNode.i();
        while (i4.hasNext()) {
            a((XMPNode) i4.next(), set, i2);
        }
        Iterator k2 = xMPNode.k();
        while (k2.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) k2.next();
            a(xMPNode2.l(), (String) null, set, i2);
            a(xMPNode2, set, i2);
        }
    }

    private void a(XMPNode xMPNode, boolean z, int i2) throws IOException {
        if (z || xMPNode.h()) {
            e(i2);
            a(z ? "<rdf:" : "</rdf:");
            a(xMPNode.n().m() ? "Alt" : xMPNode.n().l() ? "Seq" : "Bag");
            a((!z || xMPNode.h()) ? ">" : "/>");
            d();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b9, code lost:
        if (r13 != false) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01bc, code lost:
        if (r13 != false) goto L_0x00bb;
     */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0202  */
    /* JADX WARNING: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.adobe.xmp.impl.XMPNode r12, boolean r13, boolean r14, int r15) throws java.io.IOException, com.adobe.xmp.XMPException {
        /*
            r11 = this;
            java.lang.String r0 = r12.l()
            if (r14 == 0) goto L_0x0009
            java.lang.String r0 = "rdf:value"
            goto L_0x0013
        L_0x0009:
            java.lang.String r1 = "[]"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0013
            java.lang.String r0 = "rdf:li"
        L_0x0013:
            r11.e(r15)
            r1 = 60
            r11.f(r1)
            r11.a((java.lang.String) r0)
            java.util.Iterator r1 = r12.k()
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0025:
            boolean r5 = r1.hasNext()
            r6 = 34
            r7 = 32
            r8 = 1
            if (r5 == 0) goto L_0x006a
            java.lang.Object r5 = r1.next()
            com.adobe.xmp.impl.XMPNode r5 = (com.adobe.xmp.impl.XMPNode) r5
            java.util.Set r9 = f699a
            java.lang.String r10 = r5.l()
            boolean r9 = r9.contains(r10)
            if (r9 != 0) goto L_0x0044
            r3 = 1
            goto L_0x0025
        L_0x0044:
            java.lang.String r4 = "rdf:resource"
            java.lang.String r9 = r5.l()
            boolean r4 = r4.equals(r9)
            if (r14 != 0) goto L_0x0025
            r11.f(r7)
            java.lang.String r7 = r5.l()
            r11.a((java.lang.String) r7)
            java.lang.String r7 = "=\""
            r11.a((java.lang.String) r7)
            java.lang.String r5 = r5.m()
            r11.a((java.lang.String) r5, (boolean) r8)
            r11.f(r6)
            goto L_0x0025
        L_0x006a:
            r1 = 202(0xca, float:2.83E-43)
            r5 = 62
            if (r3 == 0) goto L_0x00d2
            if (r14 != 0) goto L_0x00d2
            if (r4 != 0) goto L_0x00ca
            if (r13 == 0) goto L_0x008e
            java.lang.String r14 = ">"
            r11.a((java.lang.String) r14)
            r11.d()
            int r15 = r15 + 1
            r11.e(r15)
            java.lang.String r14 = "<rdf:Description"
            r11.a((java.lang.String) r14)
            java.lang.String r14 = ">"
        L_0x008a:
            r11.a((java.lang.String) r14)
            goto L_0x0091
        L_0x008e:
            java.lang.String r14 = " rdf:parseType=\"Resource\">"
            goto L_0x008a
        L_0x0091:
            r11.d()
            int r14 = r15 + 1
            r11.a((com.adobe.xmp.impl.XMPNode) r12, (boolean) r13, (boolean) r8, (int) r14)
            java.util.Iterator r12 = r12.k()
        L_0x009d:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x00b9
            java.lang.Object r1 = r12.next()
            com.adobe.xmp.impl.XMPNode r1 = (com.adobe.xmp.impl.XMPNode) r1
            java.util.Set r3 = f699a
            java.lang.String r4 = r1.l()
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x009d
            r11.a((com.adobe.xmp.impl.XMPNode) r1, (boolean) r13, (boolean) r2, (int) r14)
            goto L_0x009d
        L_0x00b9:
            if (r13 == 0) goto L_0x015b
        L_0x00bb:
            r11.e(r15)
            java.lang.String r12 = "</rdf:Description>"
            r11.a((java.lang.String) r12)
            r11.d()
            int r15 = r15 + -1
            goto L_0x015b
        L_0x00ca:
            com.adobe.xmp.XMPException r12 = new com.adobe.xmp.XMPException
            java.lang.String r13 = "Can't mix rdf:resource and general qualifiers"
            r12.<init>(r13, r1)
            throw r12
        L_0x00d2:
            com.adobe.xmp.options.PropertyOptions r14 = r12.n()
            boolean r14 = r14.p()
            if (r14 != 0) goto L_0x0120
            com.adobe.xmp.options.PropertyOptions r13 = r12.n()
            boolean r13 = r13.a()
            if (r13 == 0) goto L_0x00fe
            java.lang.String r13 = " rdf:resource=\""
            r11.a((java.lang.String) r13)
            java.lang.String r12 = r12.m()
            r11.a((java.lang.String) r12, (boolean) r8)
            java.lang.String r12 = "\"/>"
        L_0x00f4:
            r11.a((java.lang.String) r12)
            r11.d()
            r2 = 1
            r8 = 0
            goto L_0x0200
        L_0x00fe:
            java.lang.String r13 = r12.m()
            if (r13 == 0) goto L_0x011d
            java.lang.String r13 = ""
            java.lang.String r14 = r12.m()
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0111
            goto L_0x011d
        L_0x0111:
            r11.f(r5)
            java.lang.String r12 = r12.m()
            r11.a((java.lang.String) r12, (boolean) r2)
            goto L_0x0200
        L_0x011d:
            java.lang.String r12 = "/>"
            goto L_0x00f4
        L_0x0120:
            com.adobe.xmp.options.PropertyOptions r14 = r12.n()
            boolean r14 = r14.k()
            if (r14 == 0) goto L_0x015e
            r11.f(r5)
            r11.d()
            int r14 = r15 + 1
            r11.a((com.adobe.xmp.impl.XMPNode) r12, (boolean) r8, (int) r14)
            com.adobe.xmp.options.PropertyOptions r1 = r12.n()
            boolean r1 = r1.n()
            if (r1 == 0) goto L_0x0142
            com.adobe.xmp.impl.XMPNodeUtils.b(r12)
        L_0x0142:
            java.util.Iterator r1 = r12.i()
        L_0x0146:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0158
            java.lang.Object r3 = r1.next()
            com.adobe.xmp.impl.XMPNode r3 = (com.adobe.xmp.impl.XMPNode) r3
            int r4 = r15 + 2
            r11.a((com.adobe.xmp.impl.XMPNode) r3, (boolean) r13, (boolean) r2, (int) r4)
            goto L_0x0146
        L_0x0158:
            r11.a((com.adobe.xmp.impl.XMPNode) r12, (boolean) r2, (int) r14)
        L_0x015b:
            r2 = 1
            goto L_0x0200
        L_0x015e:
            if (r4 != 0) goto L_0x01c0
            boolean r14 = r12.h()
            if (r14 != 0) goto L_0x0186
            if (r13 == 0) goto L_0x017c
            java.lang.String r12 = ">"
            r11.a((java.lang.String) r12)
            r11.d()
            int r12 = r15 + 1
            r11.e(r12)
            java.lang.String r12 = "<rdf:Description/>"
            r11.a((java.lang.String) r12)
            r2 = 1
            goto L_0x0181
        L_0x017c:
            java.lang.String r12 = " rdf:parseType=\"Resource\"/>"
            r11.a((java.lang.String) r12)
        L_0x0181:
            r11.d()
            r8 = r2
            goto L_0x015b
        L_0x0186:
            if (r13 == 0) goto L_0x01a0
            java.lang.String r14 = ">"
            r11.a((java.lang.String) r14)
            r11.d()
            int r15 = r15 + 1
            r11.e(r15)
            java.lang.String r14 = "<rdf:Description"
            r11.a((java.lang.String) r14)
            java.lang.String r14 = ">"
        L_0x019c:
            r11.a((java.lang.String) r14)
            goto L_0x01a3
        L_0x01a0:
            java.lang.String r14 = " rdf:parseType=\"Resource\">"
            goto L_0x019c
        L_0x01a3:
            r11.d()
            java.util.Iterator r12 = r12.i()
        L_0x01aa:
            boolean r14 = r12.hasNext()
            if (r14 == 0) goto L_0x01bc
            java.lang.Object r14 = r12.next()
            com.adobe.xmp.impl.XMPNode r14 = (com.adobe.xmp.impl.XMPNode) r14
            int r1 = r15 + 1
            r11.a((com.adobe.xmp.impl.XMPNode) r14, (boolean) r13, (boolean) r2, (int) r1)
            goto L_0x01aa
        L_0x01bc:
            if (r13 == 0) goto L_0x015b
            goto L_0x00bb
        L_0x01c0:
            java.util.Iterator r12 = r12.i()
        L_0x01c4:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x011d
            java.lang.Object r13 = r12.next()
            com.adobe.xmp.impl.XMPNode r13 = (com.adobe.xmp.impl.XMPNode) r13
            boolean r14 = r11.b((com.adobe.xmp.impl.XMPNode) r13)
            if (r14 == 0) goto L_0x01f8
            r11.d()
            int r14 = r15 + 1
            r11.e(r14)
            r11.f(r7)
            java.lang.String r14 = r13.l()
            r11.a((java.lang.String) r14)
            java.lang.String r14 = "=\""
            r11.a((java.lang.String) r14)
            java.lang.String r13 = r13.m()
            r11.a((java.lang.String) r13, (boolean) r8)
            r11.f(r6)
            goto L_0x01c4
        L_0x01f8:
            com.adobe.xmp.XMPException r12 = new com.adobe.xmp.XMPException
            java.lang.String r13 = "Can't mix rdf:resource and complex fields"
            r12.<init>(r13, r1)
            throw r12
        L_0x0200:
            if (r8 == 0) goto L_0x0215
            if (r2 == 0) goto L_0x0207
            r11.e(r15)
        L_0x0207:
            java.lang.String r12 = "</"
            r11.a((java.lang.String) r12)
            r11.a((java.lang.String) r0)
            r11.f(r5)
            r11.d()
        L_0x0215:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPSerializerRDF.a(com.adobe.xmp.impl.XMPNode, boolean, boolean, int):void");
    }

    private void a(String str) throws IOException {
        this.q.write(str);
    }

    private void a(String str, String str2, Set set, int i2) throws IOException {
        if (str2 == null) {
            QName qName = new QName(str);
            if (qName.a()) {
                str = qName.c();
                XMPSchemaRegistry a2 = XMPMetaFactory.a();
                str2 = a2.b(str + ":");
                a(str, str2, set, i2);
            } else {
                return;
            }
        }
        if (!set.contains(str)) {
            d();
            e(i2);
            a("xmlns:");
            a(str);
            a("=\"");
            a(str2);
            f(34);
            set.add(str);
        }
    }

    private void a(String str, boolean z) throws IOException {
        if (str == null) {
            str = "";
        }
        a(Utils.a(str, z, true));
    }

    private boolean a(XMPNode xMPNode, int i2) throws IOException {
        Iterator i3 = xMPNode.i();
        boolean z = true;
        while (i3.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) i3.next();
            if (b(xMPNode2)) {
                d();
                e(i2);
                a(xMPNode2.l());
                a("=\"");
                a(xMPNode2.m(), true);
                f(34);
            } else {
                z = false;
            }
        }
        return z;
    }

    private boolean a(XMPNode xMPNode, int i2, boolean z) throws XMPException, IOException {
        String str;
        Iterator i3 = xMPNode.i();
        boolean z2 = false;
        boolean z3 = false;
        while (i3.hasNext()) {
            if (b((XMPNode) i3.next())) {
                z2 = true;
            } else {
                z3 = true;
            }
            if (z2 && z3) {
                break;
            }
        }
        if (!z || !z3) {
            if (!xMPNode.h()) {
                str = " rdf:parseType=\"Resource\"/>";
            } else if (!z3) {
                a(xMPNode, i2 + 1);
                str = "/>";
            } else {
                if (!z2) {
                    a(" rdf:parseType=\"Resource\">");
                    d();
                    b(xMPNode, i2 + 1);
                } else {
                    f(62);
                    d();
                    int i4 = i2 + 1;
                    e(i4);
                    a(l);
                    a(xMPNode, i2 + 2);
                    a(">");
                    d();
                    b(xMPNode, i4);
                    e(i4);
                    a("</rdf:Description>");
                    d();
                }
                return true;
            }
            a(str);
            d();
            return false;
        }
        throw new XMPException("Can't mix rdf:resource qualifier and element fields", 202);
    }

    private Object[] a(XMPNode xMPNode) throws IOException {
        String str;
        Boolean bool = Boolean.TRUE;
        Boolean bool2 = Boolean.TRUE;
        if (xMPNode.n().a()) {
            a(" rdf:resource=\"");
            a(xMPNode.m(), true);
            str = "\"/>";
        } else if (xMPNode.m() == null || xMPNode.m().length() == 0) {
            str = "/>";
        } else {
            f(62);
            a(xMPNode.m(), false);
            bool2 = Boolean.FALSE;
            return new Object[]{bool, bool2};
        }
        a(str);
        d();
        bool = Boolean.FALSE;
        return new Object[]{bool, bool2};
    }

    private String b() throws IOException, XMPException {
        int i2 = 0;
        if (!this.r.a()) {
            e(0);
            a(c);
            d();
        }
        if (!this.r.b()) {
            e(0);
            a(f);
            if (!this.r.s()) {
                a(XMPMetaFactory.d().f());
            }
            a("\">");
            d();
            i2 = 1;
        }
        e(i2);
        a(h);
        d();
        if (this.r.e()) {
            b(i2);
        } else {
            c(i2);
        }
        e(i2);
        a(i);
        d();
        if (!this.r.b()) {
            e(i2 - 1);
            a(g);
            d();
        }
        String str = "";
        if (this.r.a()) {
            return str;
        }
        for (int o2 = this.r.o(); o2 > 0; o2--) {
            str = str + this.r.p();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str + d);
        sb.append(this.r.c() ? PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR : 'w');
        return sb.toString() + e;
    }

    private void b(int i2) throws IOException, XMPException {
        if (this.o.f().e() > 0) {
            e(this.o.f(), i2);
            Iterator i3 = this.o.f().i();
            while (i3.hasNext()) {
                d((XMPNode) i3.next(), i2);
            }
            d(i2);
            return;
        }
        e(i2 + 1);
        a(j);
        c();
        a("/>");
        d();
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0004 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(com.adobe.xmp.impl.XMPNode r11, int r12) throws java.io.IOException, com.adobe.xmp.XMPException {
        /*
            r10 = this;
            java.util.Iterator r11 = r11.i()
        L_0x0004:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x00cc
            java.lang.Object r0 = r11.next()
            com.adobe.xmp.impl.XMPNode r0 = (com.adobe.xmp.impl.XMPNode) r0
            boolean r1 = r10.b((com.adobe.xmp.impl.XMPNode) r0)
            if (r1 == 0) goto L_0x0017
            goto L_0x0004
        L_0x0017:
            java.lang.String r1 = r0.l()
            java.lang.String r2 = "[]"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0025
            java.lang.String r1 = "rdf:li"
        L_0x0025:
            r10.e(r12)
            r2 = 60
            r10.f(r2)
            r10.a((java.lang.String) r1)
            java.util.Iterator r2 = r0.k()
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x0037:
            boolean r6 = r2.hasNext()
            r7 = 1
            if (r6 == 0) goto L_0x007a
            java.lang.Object r6 = r2.next()
            com.adobe.xmp.impl.XMPNode r6 = (com.adobe.xmp.impl.XMPNode) r6
            java.util.Set r8 = f699a
            java.lang.String r9 = r6.l()
            boolean r8 = r8.contains(r9)
            if (r8 != 0) goto L_0x0052
            r4 = 1
            goto L_0x0037
        L_0x0052:
            java.lang.String r5 = "rdf:resource"
            java.lang.String r8 = r6.l()
            boolean r5 = r5.equals(r8)
            r8 = 32
            r10.f(r8)
            java.lang.String r8 = r6.l()
            r10.a((java.lang.String) r8)
            java.lang.String r8 = "=\""
            r10.a((java.lang.String) r8)
            java.lang.String r6 = r6.m()
            r10.a((java.lang.String) r6, (boolean) r7)
            r6 = 34
            r10.f(r6)
            goto L_0x0037
        L_0x007a:
            if (r4 == 0) goto L_0x0080
            r10.a((int) r12, (com.adobe.xmp.impl.XMPNode) r0)
            goto L_0x00ad
        L_0x0080:
            com.adobe.xmp.options.PropertyOptions r2 = r0.n()
            boolean r2 = r2.p()
            if (r2 != 0) goto L_0x00a0
            java.lang.Object[] r0 = r10.a((com.adobe.xmp.impl.XMPNode) r0)
            r2 = r0[r3]
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            r0 = r0[r7]
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r7 = r0.booleanValue()
            r0 = r2
            goto L_0x00b3
        L_0x00a0:
            com.adobe.xmp.options.PropertyOptions r2 = r0.n()
            boolean r2 = r2.k()
            if (r2 == 0) goto L_0x00af
            r10.c(r0, r12)
        L_0x00ad:
            r0 = 1
            goto L_0x00b3
        L_0x00af:
            boolean r0 = r10.a((com.adobe.xmp.impl.XMPNode) r0, (int) r12, (boolean) r5)
        L_0x00b3:
            if (r0 == 0) goto L_0x0004
            if (r7 == 0) goto L_0x00ba
            r10.e(r12)
        L_0x00ba:
            java.lang.String r0 = "</"
            r10.a((java.lang.String) r0)
            r10.a((java.lang.String) r1)
            r0 = 62
            r10.f(r0)
            r10.d()
            goto L_0x0004
        L_0x00cc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPSerializerRDF.b(com.adobe.xmp.impl.XMPNode, int):void");
    }

    private boolean b(XMPNode xMPNode) {
        return !xMPNode.j() && !xMPNode.n().a() && !xMPNode.n().p() && !XMPConst.ai.equals(xMPNode.l());
    }

    private void c() throws IOException {
        f(34);
        String l2 = this.o.f().l();
        if (l2 != null) {
            a(l2, true);
        }
        f(34);
    }

    private void c(int i2) throws IOException, XMPException {
        String str;
        int i3 = i2 + 1;
        e(i3);
        a(j);
        c();
        HashSet hashSet = new HashSet();
        hashSet.add("xml");
        hashSet.add("rdf");
        Iterator i4 = this.o.f().i();
        while (i4.hasNext()) {
            a((XMPNode) i4.next(), (Set) hashSet, i2 + 3);
        }
        Iterator i5 = this.o.f().i();
        boolean z = true;
        while (i5.hasNext()) {
            z &= a((XMPNode) i5.next(), i2 + 2);
        }
        if (!z) {
            f(62);
            d();
            Iterator i6 = this.o.f().i();
            while (i6.hasNext()) {
                b((XMPNode) i6.next(), i2 + 2);
            }
            e(i3);
            str = "</rdf:Description>";
        } else {
            str = "/>";
        }
        a(str);
        d();
    }

    private void c(XMPNode xMPNode, int i2) throws IOException, XMPException {
        f(62);
        d();
        int i3 = i2 + 1;
        a(xMPNode, true, i3);
        if (xMPNode.n().n()) {
            XMPNodeUtils.b(xMPNode);
        }
        b(xMPNode, i2 + 2);
        a(xMPNode, false, i3);
    }

    private void d() throws IOException {
        this.q.write(this.r.q());
    }

    private void d(int i2) throws IOException {
        e(i2 + 1);
        a("</rdf:Description>");
        d();
    }

    private void d(XMPNode xMPNode, int i2) throws IOException, XMPException {
        Iterator i3 = xMPNode.i();
        while (i3.hasNext()) {
            a((XMPNode) i3.next(), this.r.e(), false, i2 + 2);
        }
    }

    private void e(int i2) throws IOException {
        for (int o2 = this.r.o() + i2; o2 > 0; o2--) {
            this.q.write(this.r.p());
        }
    }

    private void e(XMPNode xMPNode, int i2) throws IOException {
        e(i2 + 1);
        a(j);
        c();
        HashSet hashSet = new HashSet();
        hashSet.add("xml");
        hashSet.add("rdf");
        a(xMPNode, (Set) hashSet, i2 + 3);
        f(62);
        d();
    }

    private void f(int i2) throws IOException {
        this.q.write(i2);
    }

    /* access modifiers changed from: protected */
    public void a() throws XMPException {
        if (this.r.m() || this.r.n()) {
            this.s = 2;
        }
        if (!this.r.k()) {
            if (this.r.c()) {
                if (this.r.a() || this.r.f()) {
                    throw new XMPException("Inconsistent options for read-only packet", 103);
                }
            } else if (!this.r.a()) {
                if (this.t == 0) {
                    this.t = this.s * 2048;
                }
                if (this.r.f() && !this.o.d("http://ns.adobe.com/xap/1.0/", "Thumbnails")) {
                    this.t += this.s * 10000;
                    return;
                }
                return;
            } else if (this.r.f()) {
                throw new XMPException("Inconsistent options for non-packet serialize", 103);
            }
            this.t = 0;
        } else if (this.r.a() || this.r.f()) {
            throw new XMPException("Inconsistent options for exact size serialize", 103);
        } else if ((this.r.r() & (this.s - 1)) != 0) {
            throw new XMPException("Exact size must be a multiple of the Unicode element", 103);
        }
    }

    public void a(XMPMeta xMPMeta, OutputStream outputStream, SerializeOptions serializeOptions) throws XMPException {
        try {
            this.p = new CountOutputStream(outputStream);
            this.q = new OutputStreamWriter(this.p, serializeOptions.t());
            this.o = (XMPMetaImpl) xMPMeta;
            this.r = serializeOptions;
            this.t = serializeOptions.r();
            this.q = new OutputStreamWriter(this.p, serializeOptions.t());
            a();
            String b2 = b();
            this.q.flush();
            a(b2.length());
            a(b2);
            this.q.flush();
            this.p.close();
        } catch (IOException unused) {
            throw new XMPException("Error writing to the OutputStream", 0);
        }
    }
}
