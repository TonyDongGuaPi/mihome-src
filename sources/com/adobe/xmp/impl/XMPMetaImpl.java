package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPIterator;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPPathFactory;
import com.adobe.xmp.XMPUtils;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.options.IteratorOptions;
import com.adobe.xmp.options.ParseOptions;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPProperty;
import java.util.Calendar;

public class XMPMetaImpl implements XMPConst, XMPMeta {
    static final /* synthetic */ boolean ap = (!XMPMetaImpl.class.desiredAssertionStatus());
    private static final int aq = 0;
    private static final int ar = 1;
    private static final int as = 2;
    private static final int at = 3;
    private static final int au = 4;
    private static final int av = 5;
    private static final int aw = 6;
    private static final int ax = 7;
    private XMPNode ay;
    private String az;

    public XMPMetaImpl() {
        this.az = null;
        this.ay = new XMPNode((String) null, (String) null, (PropertyOptions) null);
    }

    public XMPMetaImpl(XMPNode xMPNode) {
        this.az = null;
        this.ay = xMPNode;
    }

    private Object a(int i, XMPNode xMPNode) throws XMPException {
        String m = xMPNode.m();
        switch (i) {
            case 1:
                return new Boolean(XMPUtils.a(m));
            case 2:
                return new Integer(XMPUtils.b(m));
            case 3:
                return new Long(XMPUtils.c(m));
            case 4:
                return new Double(XMPUtils.d(m));
            case 5:
                return XMPUtils.e(m);
            case 6:
                return XMPUtils.e(m).l();
            case 7:
                return XMPUtils.f(m);
            default:
                if (m == null && !xMPNode.n().p()) {
                    m = "";
                }
                return m;
        }
    }

    private void a(XMPNode xMPNode, int i, String str, PropertyOptions propertyOptions, boolean z) throws XMPException {
        XMPNode xMPNode2 = new XMPNode(XMPConst.ai, (PropertyOptions) null);
        PropertyOptions a2 = XMPNodeUtils.a(propertyOptions, (Object) str);
        int e = z ? xMPNode.e() + 1 : xMPNode.e();
        if (i == -1) {
            i = e;
        }
        if (1 > i || i > e) {
            throw new XMPException("Array index out of bounds", 104);
        }
        if (!z) {
            xMPNode.b(i);
        }
        xMPNode.a(i, xMPNode2);
        a(xMPNode2, (Object) str, a2, false);
    }

    public XMPIterator a() throws XMPException {
        return a((String) null, (String) null, (IteratorOptions) null);
    }

    public XMPIterator a(IteratorOptions iteratorOptions) throws XMPException {
        return a((String) null, (String) null, iteratorOptions);
    }

    public XMPIterator a(String str, String str2, IteratorOptions iteratorOptions) throws XMPException {
        return new XMPIteratorImpl(this, str, str2, iteratorOptions);
    }

    public XMPProperty a(String str, String str2) throws XMPException {
        return e(str, str2, 0);
    }

    public XMPProperty a(String str, String str2, int i) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.a(str2);
        return a(str, XMPPathFactory.a(str2, i));
    }

    public XMPProperty a(String str, String str2, String str3, String str4) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.f(str2);
        return a(str, str2 + XMPPathFactory.a(str3, str4));
    }

    /* access modifiers changed from: package-private */
    public void a(XMPNode xMPNode, Object obj, PropertyOptions propertyOptions, boolean z) throws XMPException {
        if (z) {
            xMPNode.a();
        }
        xMPNode.n().b(propertyOptions);
        if (!xMPNode.n().p()) {
            XMPNodeUtils.a(xMPNode, obj);
        } else if (obj == null || obj.toString().length() <= 0) {
            xMPNode.d();
        } else {
            throw new XMPException("Composite nodes can't have values", 102);
        }
    }

    public void a(ParseOptions parseOptions) throws XMPException {
        if (parseOptions == null) {
            parseOptions = new ParseOptions();
        }
        XMPNormalizer.a(this, parseOptions);
    }

    public void a(String str) {
        this.ay.c(str);
    }

    public void a(String str, String str2, double d) throws XMPException {
        a(str, str2, (Object) new Double(d), (PropertyOptions) null);
    }

    public void a(String str, String str2, double d, PropertyOptions propertyOptions) throws XMPException {
        a(str, str2, (Object) new Double(d), propertyOptions);
    }

    public void a(String str, String str2, int i, PropertyOptions propertyOptions) throws XMPException {
        a(str, str2, (Object) new Integer(i), propertyOptions);
    }

    public void a(String str, String str2, int i, String str3) throws XMPException {
        a(str, str2, i, str3, (PropertyOptions) null);
    }

    public void a(String str, String str2, int i, String str3, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.a(str2);
        XMPNode a2 = XMPNodeUtils.a(this.ay, XMPPathParser.a(str, str2), false, (PropertyOptions) null);
        if (a2 != null) {
            a(a2, i, str3, propertyOptions, false);
            return;
        }
        throw new XMPException("Specified array does not exist", 102);
    }

    public void a(String str, String str2, long j) throws XMPException {
        a(str, str2, (Object) new Long(j), (PropertyOptions) null);
    }

    public void a(String str, String str2, long j, PropertyOptions propertyOptions) throws XMPException {
        a(str, str2, (Object) new Long(j), propertyOptions);
    }

    public void a(String str, String str2, XMPDateTime xMPDateTime) throws XMPException {
        a(str, str2, (Object) xMPDateTime, (PropertyOptions) null);
    }

    public void a(String str, String str2, XMPDateTime xMPDateTime, PropertyOptions propertyOptions) throws XMPException {
        a(str, str2, (Object) xMPDateTime, propertyOptions);
    }

    public void a(String str, String str2, PropertyOptions propertyOptions, String str3, PropertyOptions propertyOptions2) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.a(str2);
        if (propertyOptions == null) {
            propertyOptions = new PropertyOptions();
        }
        if (propertyOptions.r()) {
            PropertyOptions a2 = XMPNodeUtils.a(propertyOptions, (Object) null);
            XMPPath a3 = XMPPathParser.a(str, str2);
            XMPNode a4 = XMPNodeUtils.a(this.ay, a3, false, (PropertyOptions) null);
            if (a4 != null) {
                if (!a4.n().k()) {
                    throw new XMPException("The named property is not an array", 102);
                }
            } else if (a2.k()) {
                a4 = XMPNodeUtils.a(this.ay, a3, true, a2);
                if (a4 == null) {
                    throw new XMPException("Failure creating array node", 102);
                }
            } else {
                throw new XMPException("Explicit arrayOptions required to create new array", 103);
            }
            a(a4, -1, str3, propertyOptions2, true);
            return;
        }
        throw new XMPException("Only array form flags allowed for arrayOptions", 103);
    }

    public void a(String str, String str2, Object obj) throws XMPException {
        a(str, str2, obj, (PropertyOptions) null);
    }

    public void a(String str, String str2, Object obj, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.b(str2);
        PropertyOptions a2 = XMPNodeUtils.a(propertyOptions, obj);
        XMPNode a3 = XMPNodeUtils.a(this.ay, XMPPathParser.a(str, str2), true, a2);
        if (a3 != null) {
            a(a3, obj, a2, false);
            return;
        }
        throw new XMPException("Specified property does not exist", 102);
    }

    public void a(String str, String str2, String str3) throws XMPException {
        a(str, str2, (PropertyOptions) null, str3, (PropertyOptions) null);
    }

    public void a(String str, String str2, String str3, String str4, String str5) throws XMPException {
        a(str, str2, str3, str4, str5, (PropertyOptions) null);
    }

    public void a(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.f(str2);
        a(str, str2 + XMPPathFactory.a(str3, str4), (Object) str5, propertyOptions);
    }

    public void a(String str, String str2, Calendar calendar) throws XMPException {
        a(str, str2, (Object) calendar, (PropertyOptions) null);
    }

    public void a(String str, String str2, Calendar calendar, PropertyOptions propertyOptions) throws XMPException {
        a(str, str2, (Object) calendar, propertyOptions);
    }

    public void a(String str, String str2, boolean z) throws XMPException {
        a(str, str2, (Object) z ? XMPConst.af : XMPConst.ag, (PropertyOptions) null);
    }

    public void a(String str, String str2, boolean z, PropertyOptions propertyOptions) throws XMPException {
        a(str, str2, (Object) z ? XMPConst.af : XMPConst.ag, propertyOptions);
    }

    public void a(String str, String str2, byte[] bArr) throws XMPException {
        a(str, str2, (Object) bArr, (PropertyOptions) null);
    }

    public void a(String str, String str2, byte[] bArr, PropertyOptions propertyOptions) throws XMPException {
        a(str, str2, (Object) bArr, propertyOptions);
    }

    public int b(String str, String str2) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.a(str2);
        XMPNode a2 = XMPNodeUtils.a(this.ay, XMPPathParser.a(str, str2), false, (PropertyOptions) null);
        if (a2 == null) {
            return 0;
        }
        if (a2.n().k()) {
            return a2.e();
        }
        throw new XMPException("The named property is not an array", 102);
    }

    public XMPProperty b(String str, String str2, String str3, String str4) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.b(str2);
        return a(str, str2 + XMPPathFactory.b(str3, str4));
    }

    public String b() {
        return this.ay.l() != null ? this.ay.l() : "";
    }

    public void b(String str) {
        this.az = str;
    }

    public void b(String str, String str2, int i) {
        try {
            ParameterAsserts.c(str);
            ParameterAsserts.a(str2);
            c(str, XMPPathFactory.a(str2, i));
        } catch (XMPException unused) {
        }
    }

    public void b(String str, String str2, int i, String str3) throws XMPException {
        b(str, str2, i, str3, (PropertyOptions) null);
    }

    public void b(String str, String str2, int i, String str3, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.a(str2);
        XMPNode a2 = XMPNodeUtils.a(this.ay, XMPPathParser.a(str, str2), false, (PropertyOptions) null);
        if (a2 != null) {
            a(a2, i, str3, propertyOptions, true);
            return;
        }
        throw new XMPException("Specified array does not exist", 102);
    }

    public void b(String str, String str2, String str3, String str4, String str5) throws XMPException {
        b(str, str2, str3, str4, str5, (PropertyOptions) null);
    }

    public void b(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.b(str2);
        if (d(str, str2)) {
            a(str, str2 + XMPPathFactory.b(str3, str4), (Object) str5, propertyOptions);
            return;
        }
        throw new XMPException("Specified property does not exist!", 102);
    }

    public String c() {
        return this.az;
    }

    public void c(String str, String str2) {
        try {
            ParameterAsserts.c(str);
            ParameterAsserts.b(str2);
            XMPNode a2 = XMPNodeUtils.a(this.ay, XMPPathParser.a(str, str2), false, (PropertyOptions) null);
            if (a2 != null) {
                XMPNodeUtils.a(a2);
            }
        } catch (XMPException unused) {
        }
    }

    public void c(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.c(str);
            ParameterAsserts.f(str2);
            c(str, str2 + XMPPathFactory.a(str3, str4));
        } catch (XMPException unused) {
        }
    }

    public void c(String str, String str2, String str3, String str4, String str5) throws XMPException {
        c(str, str2, str3, str4, str5, (PropertyOptions) null);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0098, code lost:
        throw new com.adobe.xmp.XMPException("Language qualifier must be first", 102);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d0, code lost:
        if (r3 != false) goto L_0x0166;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e7, code lost:
        if (r3 != false) goto L_0x0166;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00fd, code lost:
        if (r2.m().equals(r9.m()) != false) goto L_0x00ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ff, code lost:
        r2.d(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0102, code lost:
        r9.d(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x011a, code lost:
        if (r2.m().equals(r9.m()) != false) goto L_0x00ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0166, code lost:
        r8 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0167, code lost:
        if (r8 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x016d, code lost:
        if (r7.e() != 1) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x016f, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.a(r7, com.adobe.xmp.XMPConst.aj, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, com.adobe.xmp.options.PropertyOptions r12) throws com.adobe.xmp.XMPException {
        /*
            r6 = this;
            com.adobe.xmp.impl.ParameterAsserts.c(r7)
            com.adobe.xmp.impl.ParameterAsserts.a((java.lang.String) r8)
            com.adobe.xmp.impl.ParameterAsserts.e(r10)
            r12 = 0
            if (r9 == 0) goto L_0x0011
            java.lang.String r9 = com.adobe.xmp.impl.Utils.a((java.lang.String) r9)
            goto L_0x0012
        L_0x0011:
            r9 = r12
        L_0x0012:
            java.lang.String r10 = com.adobe.xmp.impl.Utils.a((java.lang.String) r10)
            com.adobe.xmp.impl.xpath.XMPPath r7 = com.adobe.xmp.impl.xpath.XMPPathParser.a((java.lang.String) r7, (java.lang.String) r8)
            com.adobe.xmp.impl.XMPNode r8 = r6.ay
            com.adobe.xmp.options.PropertyOptions r0 = new com.adobe.xmp.options.PropertyOptions
            r1 = 7680(0x1e00, float:1.0762E-41)
            r0.<init>(r1)
            r1 = 1
            com.adobe.xmp.impl.XMPNode r7 = com.adobe.xmp.impl.XMPNodeUtils.a((com.adobe.xmp.impl.XMPNode) r8, (com.adobe.xmp.impl.xpath.XMPPath) r7, (boolean) r1, (com.adobe.xmp.options.PropertyOptions) r0)
            r8 = 102(0x66, float:1.43E-43)
            if (r7 == 0) goto L_0x0176
            com.adobe.xmp.options.PropertyOptions r0 = r7.n()
            boolean r0 = r0.n()
            if (r0 != 0) goto L_0x0056
            boolean r0 = r7.h()
            if (r0 != 0) goto L_0x004e
            com.adobe.xmp.options.PropertyOptions r0 = r7.n()
            boolean r0 = r0.m()
            if (r0 == 0) goto L_0x004e
            com.adobe.xmp.options.PropertyOptions r0 = r7.n()
            r0.j(r1)
            goto L_0x0056
        L_0x004e:
            com.adobe.xmp.XMPException r7 = new com.adobe.xmp.XMPException
            java.lang.String r9 = "Specified property is no alt-text array"
            r7.<init>(r9, r8)
            throw r7
        L_0x0056:
            java.util.Iterator r0 = r7.i()
        L_0x005a:
            boolean r2 = r0.hasNext()
            r3 = 0
            if (r2 == 0) goto L_0x0099
            java.lang.Object r2 = r0.next()
            com.adobe.xmp.impl.XMPNode r2 = (com.adobe.xmp.impl.XMPNode) r2
            boolean r4 = r2.j()
            if (r4 == 0) goto L_0x0091
            java.lang.String r4 = "xml:lang"
            com.adobe.xmp.impl.XMPNode r5 = r2.c((int) r1)
            java.lang.String r5 = r5.l()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0091
            java.lang.String r4 = "x-default"
            com.adobe.xmp.impl.XMPNode r5 = r2.c((int) r1)
            java.lang.String r5 = r5.m()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x005a
            r8 = 1
            goto L_0x009b
        L_0x0091:
            com.adobe.xmp.XMPException r7 = new com.adobe.xmp.XMPException
            java.lang.String r9 = "Language qualifier must be first"
            r7.<init>(r9, r8)
            throw r7
        L_0x0099:
            r2 = r12
            r8 = 0
        L_0x009b:
            if (r2 == 0) goto L_0x00a9
            int r0 = r7.e()
            if (r0 <= r1) goto L_0x00a9
            r7.b((com.adobe.xmp.impl.XMPNode) r2)
            r7.a((int) r1, (com.adobe.xmp.impl.XMPNode) r2)
        L_0x00a9:
            java.lang.Object[] r9 = com.adobe.xmp.impl.XMPNodeUtils.b((com.adobe.xmp.impl.XMPNode) r7, (java.lang.String) r9, (java.lang.String) r10)
            r0 = r9[r3]
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r9 = r9[r1]
            com.adobe.xmp.impl.XMPNode r9 = (com.adobe.xmp.impl.XMPNode) r9
            java.lang.String r3 = "x-default"
            boolean r3 = r3.equals(r10)
            switch(r0) {
                case 0: goto L_0x015b;
                case 1: goto L_0x0106;
                case 2: goto L_0x00eb;
                case 3: goto L_0x00e4;
                case 4: goto L_0x00d4;
                case 5: goto L_0x00cd;
                default: goto L_0x00c3;
            }
        L_0x00c3:
            com.adobe.xmp.XMPException r7 = new com.adobe.xmp.XMPException
            r8 = 9
            java.lang.String r9 = "Unexpected result from ChooseLocalizedText"
            r7.<init>(r9, r8)
            throw r7
        L_0x00cd:
            com.adobe.xmp.impl.XMPNodeUtils.a((com.adobe.xmp.impl.XMPNode) r7, (java.lang.String) r10, (java.lang.String) r11)
            if (r3 == 0) goto L_0x0167
            goto L_0x0166
        L_0x00d4:
            if (r2 == 0) goto L_0x00df
            int r9 = r7.e()
            if (r9 != r1) goto L_0x00df
            r2.d((java.lang.String) r11)
        L_0x00df:
            com.adobe.xmp.impl.XMPNodeUtils.a((com.adobe.xmp.impl.XMPNode) r7, (java.lang.String) r10, (java.lang.String) r11)
            goto L_0x0167
        L_0x00e4:
            com.adobe.xmp.impl.XMPNodeUtils.a((com.adobe.xmp.impl.XMPNode) r7, (java.lang.String) r10, (java.lang.String) r11)
            if (r3 == 0) goto L_0x0167
            goto L_0x0166
        L_0x00eb:
            if (r8 == 0) goto L_0x0102
            if (r2 == r9) goto L_0x0102
            if (r2 == 0) goto L_0x0102
            java.lang.String r10 = r2.m()
            java.lang.String r12 = r9.m()
            boolean r10 = r10.equals(r12)
            if (r10 == 0) goto L_0x0102
        L_0x00ff:
            r2.d((java.lang.String) r11)
        L_0x0102:
            r9.d((java.lang.String) r11)
            goto L_0x0167
        L_0x0106:
            if (r3 != 0) goto L_0x011d
            if (r8 == 0) goto L_0x0102
            if (r2 == r9) goto L_0x0102
            if (r2 == 0) goto L_0x0102
            java.lang.String r10 = r2.m()
            java.lang.String r12 = r9.m()
            boolean r10 = r10.equals(r12)
            if (r10 == 0) goto L_0x0102
            goto L_0x00ff
        L_0x011d:
            boolean r10 = ap
            if (r10 != 0) goto L_0x012c
            if (r8 == 0) goto L_0x0126
            if (r2 != r9) goto L_0x0126
            goto L_0x012c
        L_0x0126:
            java.lang.AssertionError r7 = new java.lang.AssertionError
            r7.<init>()
            throw r7
        L_0x012c:
            java.util.Iterator r9 = r7.i()
        L_0x0130:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0155
            java.lang.Object r10 = r9.next()
            com.adobe.xmp.impl.XMPNode r10 = (com.adobe.xmp.impl.XMPNode) r10
            if (r10 == r2) goto L_0x0130
            java.lang.String r0 = r10.m()
            if (r2 == 0) goto L_0x0149
            java.lang.String r3 = r2.m()
            goto L_0x014a
        L_0x0149:
            r3 = r12
        L_0x014a:
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0151
            goto L_0x0130
        L_0x0151:
            r10.d((java.lang.String) r11)
            goto L_0x0130
        L_0x0155:
            if (r2 == 0) goto L_0x0167
            r2.d((java.lang.String) r11)
            goto L_0x0167
        L_0x015b:
            java.lang.String r8 = "x-default"
            com.adobe.xmp.impl.XMPNodeUtils.a((com.adobe.xmp.impl.XMPNode) r7, (java.lang.String) r8, (java.lang.String) r11)
            if (r3 != 0) goto L_0x0166
            com.adobe.xmp.impl.XMPNodeUtils.a((com.adobe.xmp.impl.XMPNode) r7, (java.lang.String) r10, (java.lang.String) r11)
        L_0x0166:
            r8 = 1
        L_0x0167:
            if (r8 != 0) goto L_0x0175
            int r8 = r7.e()
            if (r8 != r1) goto L_0x0175
            java.lang.String r8 = "x-default"
            com.adobe.xmp.impl.XMPNodeUtils.a((com.adobe.xmp.impl.XMPNode) r7, (java.lang.String) r8, (java.lang.String) r11)
        L_0x0175:
            return
        L_0x0176:
            com.adobe.xmp.XMPException r7 = new com.adobe.xmp.XMPException
            java.lang.String r9 = "Failed to find or create array node"
            r7.<init>(r9, r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPMetaImpl.c(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.adobe.xmp.options.PropertyOptions):void");
    }

    public boolean c(String str, String str2, int i) {
        try {
            ParameterAsserts.c(str);
            ParameterAsserts.a(str2);
            return d(str, XMPPathFactory.a(str2, i));
        } catch (XMPException unused) {
            return false;
        }
    }

    public Object clone() {
        return new XMPMetaImpl((XMPNode) this.ay.clone());
    }

    public void d() {
        this.ay.s();
    }

    public void d(String str, String str2, int i) throws XMPException {
        a(str, str2, (Object) new Integer(i), (PropertyOptions) null);
    }

    public void d(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.c(str);
            ParameterAsserts.b(str2);
            c(str, str2 + XMPPathFactory.b(str3, str4));
        } catch (XMPException unused) {
        }
    }

    public boolean d(String str, String str2) {
        try {
            ParameterAsserts.c(str);
            ParameterAsserts.b(str2);
            return XMPNodeUtils.a(this.ay, XMPPathParser.a(str, str2), false, (PropertyOptions) null) != null;
        } catch (XMPException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public XMPProperty e(String str, String str2, int i) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.b(str2);
        final XMPNode a2 = XMPNodeUtils.a(this.ay, XMPPathParser.a(str, str2), false, (PropertyOptions) null);
        if (a2 == null) {
            return null;
        }
        if (i == 0 || !a2.n().p()) {
            final Object a3 = a(i, a2);
            return new XMPProperty() {
                public String c() {
                    if (a3 != null) {
                        return a3.toString();
                    }
                    return null;
                }

                public PropertyOptions d() {
                    return a2.n();
                }

                public String e() {
                    return null;
                }

                public String toString() {
                    return a3.toString();
                }
            };
        }
        throw new XMPException("Property must be simple when a value type is requested", 102);
    }

    public Boolean e(String str, String str2) throws XMPException {
        return (Boolean) f(str, str2, 1);
    }

    public String e() {
        return f().a(true);
    }

    public boolean e(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.c(str);
            ParameterAsserts.f(str2);
            String a2 = XMPPathFactory.a(str3, str4);
            return d(str, str2 + a2);
        } catch (XMPException unused) {
            return false;
        }
    }

    public XMPNode f() {
        return this.ay;
    }

    public Integer f(String str, String str2) throws XMPException {
        return (Integer) f(str, str2, 2);
    }

    /* access modifiers changed from: protected */
    public Object f(String str, String str2, int i) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.b(str2);
        XMPNode a2 = XMPNodeUtils.a(this.ay, XMPPathParser.a(str, str2), false, (PropertyOptions) null);
        if (a2 == null) {
            return null;
        }
        if (i == 0 || !a2.n().p()) {
            return a(i, a2);
        }
        throw new XMPException("Property must be simple when a value type is requested", 102);
    }

    public boolean f(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.c(str);
            ParameterAsserts.b(str2);
            String b = XMPPathFactory.b(str3, str4);
            return d(str, str2 + b);
        } catch (XMPException unused) {
            return false;
        }
    }

    public XMPProperty g(String str, String str2, String str3, String str4) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.a(str2);
        ParameterAsserts.e(str4);
        String a2 = str3 != null ? Utils.a(str3) : null;
        String a3 = Utils.a(str4);
        XMPNode a4 = XMPNodeUtils.a(this.ay, XMPPathParser.a(str, str2), false, (PropertyOptions) null);
        if (a4 == null) {
            return null;
        }
        Object[] b = XMPNodeUtils.b(a4, a2, a3);
        int intValue = ((Integer) b[0]).intValue();
        final XMPNode xMPNode = (XMPNode) b[1];
        if (intValue != 0) {
            return new XMPProperty() {
                public String c() {
                    return xMPNode.m();
                }

                public PropertyOptions d() {
                    return xMPNode.n();
                }

                public String e() {
                    return xMPNode.c(1).m();
                }

                public String toString() {
                    return xMPNode.m().toString();
                }
            };
        }
        return null;
    }

    public Long g(String str, String str2) throws XMPException {
        return (Long) f(str, str2, 3);
    }

    public Double h(String str, String str2) throws XMPException {
        return (Double) f(str, str2, 4);
    }

    public XMPDateTime i(String str, String str2) throws XMPException {
        return (XMPDateTime) f(str, str2, 5);
    }

    public Calendar j(String str, String str2) throws XMPException {
        return (Calendar) f(str, str2, 6);
    }

    public byte[] k(String str, String str2) throws XMPException {
        return (byte[]) f(str, str2, 7);
    }

    public String l(String str, String str2) throws XMPException {
        return (String) f(str, str2, 0);
    }
}
