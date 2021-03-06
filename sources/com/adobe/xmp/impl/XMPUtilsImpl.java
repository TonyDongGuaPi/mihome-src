package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPAliasInfo;
import com.coloros.mcssdk.mode.CommandMessage;
import com.drew.metadata.exif.makernotes.SonyType1MakernoteDirectory;
import java.util.Iterator;
import kotlin.text.Typography;

public class XMPUtilsImpl implements XMPConst {
    private static final String aA = "  ";
    static final /* synthetic */ boolean ap = (!XMPUtilsImpl.class.desiredAssertionStatus());
    private static final int aq = 0;
    private static final int ar = 1;
    private static final int as = 2;
    private static final int at = 3;
    private static final int au = 4;
    private static final int av = 5;
    private static final String aw = " 　〿";
    private static final String ax = ",，､﹐﹑、،՝";
    private static final String ay = ";；﹔؛;";
    private static final String az = "\"«»〝〞〟―‹›";

    private XMPUtilsImpl() {
    }

    private static char a(String str, char c) throws XMPException {
        char c2;
        if (a(c) == 4) {
            if (str.length() == 1) {
                c2 = c;
            } else {
                c2 = str.charAt(1);
                if (a(c2) != 4) {
                    throw new XMPException("Invalid quoting character", 4);
                }
            }
            if (c2 == b(c)) {
                return c2;
            }
            throw new XMPException("Mismatched quote pair", 4);
        }
        throw new XMPException("Invalid quoting character", 4);
    }

    private static int a(char c) {
        if (aw.indexOf(c) >= 0) {
            return 1;
        }
        if (8192 <= c && c <= 8203) {
            return 1;
        }
        if (ax.indexOf(c) >= 0) {
            return 2;
        }
        if (ay.indexOf(c) >= 0) {
            return 3;
        }
        if (az.indexOf(c) >= 0) {
            return 4;
        }
        if (12296 <= c && c <= 12303) {
            return 4;
        }
        if (8216 > c || c > 8223) {
            return (c < ' ' || aA.indexOf(c) >= 0) ? 5 : 0;
        }
        return 4;
    }

    private static XMPNode a(String str, String str2, PropertyOptions propertyOptions, XMPMetaImpl xMPMetaImpl) throws XMPException {
        PropertyOptions a2 = XMPNodeUtils.a(propertyOptions, (Object) null);
        if (a2.r()) {
            XMPPath a3 = XMPPathParser.a(str, str2);
            XMPNode a4 = XMPNodeUtils.a(xMPMetaImpl.f(), a3, false, (PropertyOptions) null);
            if (a4 != null) {
                PropertyOptions n = a4.n();
                if (!n.k() || n.m()) {
                    throw new XMPException("Named property must be non-alternate array", 102);
                } else if (a2.a(n)) {
                    throw new XMPException("Mismatch of specified and existing array form", 102);
                }
            } else {
                a4 = XMPNodeUtils.a(xMPMetaImpl.f(), a3, true, a2.g(true));
                if (a4 == null) {
                    throw new XMPException("Failed to create named array", 102);
                }
            }
            return a4;
        }
        throw new XMPException("Options can only provide array form", 103);
    }

    public static String a(XMPMeta xMPMeta, String str, String str2, String str3, String str4, boolean z) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.a(str2);
        ParameterAsserts.a(xMPMeta);
        if (str3 == null || str3.length() == 0) {
            str3 = "; ";
        }
        if (str4 == null || str4.length() == 0) {
            str4 = "\"";
        }
        XMPNode a2 = XMPNodeUtils.a(((XMPMetaImpl) xMPMeta).f(), XMPPathParser.a(str, str2), false, (PropertyOptions) null);
        if (a2 == null) {
            return "";
        }
        if (!a2.n().k() || a2.n().m()) {
            throw new XMPException("Named property must be non-alternate array", 4);
        }
        a(str3);
        char charAt = str4.charAt(0);
        char a3 = a(str4, charAt);
        StringBuffer stringBuffer = new StringBuffer();
        Iterator i = a2.i();
        while (i.hasNext()) {
            XMPNode xMPNode = (XMPNode) i.next();
            if (!xMPNode.n().p()) {
                stringBuffer.append(a(xMPNode.m(), charAt, a3, z));
                if (i.hasNext()) {
                    stringBuffer.append(str3);
                }
            } else {
                throw new XMPException("Array items must be simple", 4);
            }
        }
        return stringBuffer.toString();
    }

    private static String a(String str, char c, char c2, boolean z) {
        if (str == null) {
            str = "";
        }
        int i = 0;
        boolean z2 = false;
        while (i < str.length()) {
            int a2 = a(str.charAt(i));
            if (i == 0 && a2 == 4) {
                break;
            }
            if (a2 != 1) {
                if (a2 == 3 || a2 == 5 || (a2 == 2 && !z)) {
                    break;
                }
                z2 = false;
            } else if (z2) {
                break;
            } else {
                z2 = true;
            }
            i++;
        }
        if (i >= str.length()) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 2);
        int i2 = 0;
        while (i2 <= i && a(str.charAt(i)) != 4) {
            i2++;
        }
        stringBuffer.append(c);
        stringBuffer.append(str.substring(0, i2));
        while (i2 < str.length()) {
            stringBuffer.append(str.charAt(i2));
            if (a(str.charAt(i2)) == 4 && a(str.charAt(i2), c, c2)) {
                stringBuffer.append(str.charAt(i2));
            }
            i2++;
        }
        stringBuffer.append(c2);
        return stringBuffer.toString();
    }

    public static void a(XMPMeta xMPMeta, XMPMeta xMPMeta2, boolean z, boolean z2, boolean z3) throws XMPException {
        ParameterAsserts.a(xMPMeta);
        ParameterAsserts.a(xMPMeta2);
        XMPMetaImpl xMPMetaImpl = (XMPMetaImpl) xMPMeta2;
        Iterator i = ((XMPMetaImpl) xMPMeta).f().i();
        while (i.hasNext()) {
            XMPNode xMPNode = (XMPNode) i.next();
            XMPNode a2 = XMPNodeUtils.a(xMPMetaImpl.f(), xMPNode.l(), false);
            boolean z4 = true;
            if (a2 == null) {
                a2 = new XMPNode(xMPNode.l(), xMPNode.m(), new PropertyOptions().k(true));
                xMPMetaImpl.f().a(a2);
            } else {
                z4 = false;
            }
            Iterator i2 = xMPNode.i();
            while (i2.hasNext()) {
                XMPNode xMPNode2 = (XMPNode) i2.next();
                if (z || !Utils.a(xMPNode.l(), xMPNode2.l())) {
                    a(xMPMetaImpl, xMPNode2, a2, z2, z3);
                }
            }
            if (!a2.h() && (z4 || z3)) {
                xMPMetaImpl.f().b(a2);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        r3 = r11.charAt(r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(com.adobe.xmp.XMPMeta r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, com.adobe.xmp.options.PropertyOptions r12, boolean r13) throws com.adobe.xmp.XMPException {
        /*
            com.adobe.xmp.impl.ParameterAsserts.c(r9)
            com.adobe.xmp.impl.ParameterAsserts.a((java.lang.String) r10)
            r0 = 4
            if (r11 == 0) goto L_0x00f8
            com.adobe.xmp.impl.ParameterAsserts.a((com.adobe.xmp.XMPMeta) r8)
            com.adobe.xmp.impl.XMPMetaImpl r8 = (com.adobe.xmp.impl.XMPMetaImpl) r8
            com.adobe.xmp.impl.XMPNode r8 = a((java.lang.String) r9, (java.lang.String) r10, (com.adobe.xmp.options.PropertyOptions) r12, (com.adobe.xmp.impl.XMPMetaImpl) r8)
            int r9 = r11.length()
            r10 = 0
            r12 = 0
            r1 = 0
        L_0x0019:
            if (r10 >= r9) goto L_0x00f7
        L_0x001b:
            if (r10 >= r9) goto L_0x002d
            char r1 = r11.charAt(r10)
            int r12 = a((char) r1)
            if (r12 == 0) goto L_0x002d
            if (r12 != r0) goto L_0x002a
            goto L_0x002d
        L_0x002a:
            int r10 = r10 + 1
            goto L_0x001b
        L_0x002d:
            if (r10 < r9) goto L_0x0031
            goto L_0x00f7
        L_0x0031:
            r2 = 1
            if (r12 == r0) goto L_0x006f
            r3 = r1
            r1 = r12
            r12 = r10
        L_0x0037:
            if (r12 >= r9) goto L_0x0065
            char r3 = r11.charAt(r12)
            int r1 = a((char) r3)
            if (r1 == 0) goto L_0x0062
            if (r1 == r0) goto L_0x0062
            r4 = 2
            if (r1 != r4) goto L_0x004b
            if (r13 == 0) goto L_0x004b
            goto L_0x0062
        L_0x004b:
            if (r1 == r2) goto L_0x004e
            goto L_0x0065
        L_0x004e:
            int r5 = r12 + 1
            if (r5 >= r9) goto L_0x0065
            char r3 = r11.charAt(r5)
            int r5 = a((char) r3)
            if (r5 == 0) goto L_0x0062
            if (r5 == r0) goto L_0x0062
            if (r5 != r4) goto L_0x0065
            if (r13 == 0) goto L_0x0065
        L_0x0062:
            int r12 = r12 + 1
            goto L_0x0037
        L_0x0065:
            java.lang.String r10 = r11.substring(r10, r12)
            r4 = r10
            r10 = r12
            r12 = r1
            r1 = r3
            goto L_0x00ce
        L_0x006f:
            char r3 = b(r1)
            int r10 = r10 + 1
            java.lang.String r4 = ""
            r5 = r1
        L_0x0078:
            if (r10 >= r9) goto L_0x00cd
            char r5 = r11.charAt(r10)
            int r12 = a((char) r5)
            if (r12 != r0) goto L_0x00bc
            boolean r6 = a(r5, r1, r3)
            if (r6 != 0) goto L_0x008b
            goto L_0x00bc
        L_0x008b:
            int r6 = r10 + 1
            if (r6 >= r9) goto L_0x0097
            char r7 = r11.charAt(r6)
            a((char) r7)
            goto L_0x0099
        L_0x0097:
            r7 = 59
        L_0x0099:
            if (r5 != r7) goto L_0x00ad
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r4)
            r10.append(r5)
            java.lang.String r10 = r10.toString()
            r4 = r10
            r10 = r6
            goto L_0x00cb
        L_0x00ad:
            boolean r7 = b(r5, r1, r3)
            if (r7 != 0) goto L_0x00b9
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            goto L_0x00c1
        L_0x00b9:
            r1 = r5
            r10 = r6
            goto L_0x00ce
        L_0x00bc:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
        L_0x00c1:
            r6.append(r4)
            r6.append(r5)
            java.lang.String r4 = r6.toString()
        L_0x00cb:
            int r10 = r10 + r2
            goto L_0x0078
        L_0x00cd:
            r1 = r5
        L_0x00ce:
            r3 = -1
        L_0x00cf:
            int r5 = r8.e()
            if (r2 > r5) goto L_0x00e7
            com.adobe.xmp.impl.XMPNode r5 = r8.a((int) r2)
            java.lang.String r5 = r5.m()
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x00e4
            goto L_0x00e8
        L_0x00e4:
            int r2 = r2 + 1
            goto L_0x00cf
        L_0x00e7:
            r2 = -1
        L_0x00e8:
            if (r2 >= 0) goto L_0x0019
            com.adobe.xmp.impl.XMPNode r2 = new com.adobe.xmp.impl.XMPNode
            java.lang.String r3 = "[]"
            r5 = 0
            r2.<init>(r3, r4, r5)
            r8.a((com.adobe.xmp.impl.XMPNode) r2)
            goto L_0x0019
        L_0x00f7:
            return
        L_0x00f8:
            com.adobe.xmp.XMPException r8 = new com.adobe.xmp.XMPException
            java.lang.String r9 = "Parameter must not be null"
            r8.<init>(r9, r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPUtilsImpl.a(com.adobe.xmp.XMPMeta, java.lang.String, java.lang.String, java.lang.String, com.adobe.xmp.options.PropertyOptions, boolean):void");
    }

    public static void a(XMPMeta xMPMeta, String str, String str2, boolean z, boolean z2) throws XMPException {
        ParameterAsserts.a(xMPMeta);
        XMPMetaImpl xMPMetaImpl = (XMPMetaImpl) xMPMeta;
        if (str2 == null || str2.length() <= 0) {
            if (str == null || str.length() <= 0) {
                Iterator i = xMPMetaImpl.f().i();
                while (i.hasNext()) {
                    if (a((XMPNode) i.next(), z)) {
                        i.remove();
                    }
                }
                return;
            }
            XMPNode a2 = XMPNodeUtils.a(xMPMetaImpl.f(), str, false);
            if (a2 != null && a(a2, z)) {
                xMPMetaImpl.f().b(a2);
            }
            if (z2) {
                XMPAliasInfo[] d = XMPMetaFactory.a().d(str);
                for (XMPAliasInfo xMPAliasInfo : d) {
                    XMPNode a3 = XMPNodeUtils.a(xMPMetaImpl.f(), XMPPathParser.a(xMPAliasInfo.a(), xMPAliasInfo.c()), false, (PropertyOptions) null);
                    if (a3 != null) {
                        a3.b().b(a3);
                    }
                }
            }
        } else if (str == null || str.length() == 0) {
            throw new XMPException("Property name requires schema namespace", 4);
        } else {
            XMPPath a4 = XMPPathParser.a(str, str2);
            XMPNode a5 = XMPNodeUtils.a(xMPMetaImpl.f(), a4, false, (PropertyOptions) null);
            if (a5 == null) {
                return;
            }
            if (z || !Utils.a(a4.a(0).b(), a4.a(1).b())) {
                XMPNode b = a5.b();
                b.b(a5);
                if (b.n().o() && !b.h()) {
                    b.b().b(b);
                }
            }
        }
    }

    private static void a(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, XMPNode xMPNode2, boolean z, boolean z2) throws XMPException {
        XMPNode b = XMPNodeUtils.b(xMPNode2, xMPNode.l(), false);
        boolean z3 = z2 && (!xMPNode.n().q() ? !xMPNode.h() : xMPNode.m() == null || xMPNode.m().length() == 0);
        if (!z2 || !z3) {
            if (b != null) {
                if (z) {
                    xMPMetaImpl.a(b, (Object) xMPNode.m(), xMPNode.n(), true);
                    xMPNode2.b(b);
                } else {
                    PropertyOptions n = xMPNode.n();
                    if (n == b.n()) {
                        if (n.f()) {
                            Iterator i = xMPNode.i();
                            while (i.hasNext()) {
                                a(xMPMetaImpl, (XMPNode) i.next(), b, z, z2);
                                if (z2 && !b.h()) {
                                    xMPNode2.b(b);
                                }
                            }
                            return;
                        } else if (n.n()) {
                            Iterator i2 = xMPNode.i();
                            while (i2.hasNext()) {
                                XMPNode xMPNode3 = (XMPNode) i2.next();
                                if (xMPNode3.j() && XMPConst.ak.equals(xMPNode3.c(1).l())) {
                                    int a2 = XMPNodeUtils.a(b, xMPNode3.c(1).m());
                                    if (!z2 || !(xMPNode3.m() == null || xMPNode3.m().length() == 0)) {
                                        if (a2 == -1) {
                                            if (!XMPConst.aj.equals(xMPNode3.c(1).m()) || !b.h()) {
                                                xMPNode3.e(b);
                                            } else {
                                                XMPNode xMPNode4 = new XMPNode(xMPNode3.l(), xMPNode3.m(), xMPNode3.n());
                                                xMPNode3.e(xMPNode4);
                                                b.a(1, xMPNode4);
                                            }
                                        }
                                    } else if (a2 != -1) {
                                        b.b(a2);
                                        if (!b.h()) {
                                            xMPNode2.b(b);
                                        }
                                    }
                                }
                            }
                            return;
                        } else if (n.k()) {
                            Iterator i3 = xMPNode.i();
                            while (i3.hasNext()) {
                                XMPNode xMPNode5 = (XMPNode) i3.next();
                                Iterator i4 = b.i();
                                boolean z4 = false;
                                while (i4.hasNext()) {
                                    if (a(xMPNode5, (XMPNode) i4.next())) {
                                        z4 = true;
                                    }
                                }
                                if (!z4) {
                                    XMPNode xMPNode6 = (XMPNode) xMPNode5.clone();
                                    xMPNode2.a(xMPNode6);
                                    b = xMPNode6;
                                }
                            }
                            return;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
            xMPNode2.a((XMPNode) xMPNode.clone());
        } else if (b != null) {
            xMPNode2.b(b);
        }
    }

    private static void a(String str) throws XMPException {
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            int a2 = a(str.charAt(i));
            if (a2 == 3) {
                if (!z) {
                    z = true;
                } else {
                    throw new XMPException("Separator can have only one semicolon", 4);
                }
            } else if (a2 != 1) {
                throw new XMPException("Separator can have only spaces and one semicolon", 4);
            }
        }
        if (!z) {
            throw new XMPException("Separator must have one semicolon", 4);
        }
    }

    private static boolean a(char c, char c2, char c3) {
        return c == c2 || b(c, c2, c3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(com.adobe.xmp.impl.XMPNode r5, com.adobe.xmp.impl.XMPNode r6) throws com.adobe.xmp.XMPException {
        /*
            com.adobe.xmp.options.PropertyOptions r0 = r5.n()
            com.adobe.xmp.options.PropertyOptions r1 = r6.n()
            boolean r1 = r0.equals(r1)
            r2 = 0
            if (r1 == 0) goto L_0x0010
            return r2
        L_0x0010:
            int r1 = r0.i()
            r3 = 1
            if (r1 != 0) goto L_0x005a
            java.lang.String r0 = r5.m()
            java.lang.String r1 = r6.m()
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0026
            return r2
        L_0x0026:
            com.adobe.xmp.options.PropertyOptions r0 = r5.n()
            boolean r0 = r0.d()
            com.adobe.xmp.options.PropertyOptions r1 = r6.n()
            boolean r1 = r1.d()
            if (r0 == r1) goto L_0x0039
            return r2
        L_0x0039:
            com.adobe.xmp.options.PropertyOptions r0 = r5.n()
            boolean r0 = r0.d()
            if (r0 == 0) goto L_0x00c9
            com.adobe.xmp.impl.XMPNode r5 = r5.c((int) r3)
            java.lang.String r5 = r5.m()
            com.adobe.xmp.impl.XMPNode r6 = r6.c((int) r3)
            java.lang.String r6 = r6.m()
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x00c9
            return r2
        L_0x005a:
            boolean r1 = r0.f()
            if (r1 == 0) goto L_0x008c
            int r0 = r5.e()
            int r1 = r6.e()
            if (r0 == r1) goto L_0x006b
            return r2
        L_0x006b:
            java.util.Iterator r5 = r5.i()
        L_0x006f:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x00c9
            java.lang.Object r0 = r5.next()
            com.adobe.xmp.impl.XMPNode r0 = (com.adobe.xmp.impl.XMPNode) r0
            java.lang.String r1 = r0.l()
            com.adobe.xmp.impl.XMPNode r1 = com.adobe.xmp.impl.XMPNodeUtils.b((com.adobe.xmp.impl.XMPNode) r6, (java.lang.String) r1, (boolean) r2)
            if (r1 == 0) goto L_0x008b
            boolean r0 = a((com.adobe.xmp.impl.XMPNode) r0, (com.adobe.xmp.impl.XMPNode) r1)
            if (r0 != 0) goto L_0x006f
        L_0x008b:
            return r2
        L_0x008c:
            boolean r1 = ap
            if (r1 != 0) goto L_0x009d
            boolean r0 = r0.k()
            if (r0 == 0) goto L_0x0097
            goto L_0x009d
        L_0x0097:
            java.lang.AssertionError r5 = new java.lang.AssertionError
            r5.<init>()
            throw r5
        L_0x009d:
            java.util.Iterator r5 = r5.i()
        L_0x00a1:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x00c9
            java.lang.Object r0 = r5.next()
            com.adobe.xmp.impl.XMPNode r0 = (com.adobe.xmp.impl.XMPNode) r0
            java.util.Iterator r1 = r6.i()
        L_0x00b1:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x00c5
            java.lang.Object r4 = r1.next()
            com.adobe.xmp.impl.XMPNode r4 = (com.adobe.xmp.impl.XMPNode) r4
            boolean r4 = a((com.adobe.xmp.impl.XMPNode) r0, (com.adobe.xmp.impl.XMPNode) r4)
            if (r4 == 0) goto L_0x00b1
            r0 = 1
            goto L_0x00c6
        L_0x00c5:
            r0 = 0
        L_0x00c6:
            if (r0 != 0) goto L_0x00a1
            return r2
        L_0x00c9:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPUtilsImpl.a(com.adobe.xmp.impl.XMPNode, com.adobe.xmp.impl.XMPNode):boolean");
    }

    private static boolean a(XMPNode xMPNode, boolean z) {
        Iterator i = xMPNode.i();
        while (i.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) i.next();
            if (z || !Utils.a(xMPNode.l(), xMPNode2.l())) {
                i.remove();
            }
        }
        return !xMPNode.h();
    }

    private static char b(char c) {
        switch (c) {
            case '\"':
                return '\"';
            case 171:
                return Typography.m;
            case 187:
                return Typography.l;
            case 8213:
                return 8213;
            case 8216:
                return Typography.w;
            case 8218:
                return 8219;
            case 8220:
                return Typography.z;
            case SonyType1MakernoteDirectory.K /*8222*/:
                return 8223;
            case 8249:
                return 8250;
            case 8250:
                return 8249;
            case CommandMessage.COMMAND_GET_TAGS /*12296*/:
                return 12297;
            case CommandMessage.COMMAND_SET_PUSH_TIME /*12298*/:
                return 12299;
            case CommandMessage.COMMAND_RESUME_PUSH /*12300*/:
                return 12301;
            case CommandMessage.COMMAND_GET_ACCOUNTS /*12302*/:
                return 12303;
            case 12317:
                return 12319;
            default:
                return 0;
        }
    }

    private static boolean b(char c, char c2, char c3) {
        return c == c3 || (c2 == 12317 && c == 12318) || c == 12319;
    }
}
