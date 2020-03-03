package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPDateTimeFactory;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.XMPUtils;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathSegment;
import com.adobe.xmp.options.PropertyOptions;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class XMPNodeUtils implements XMPConst {
    static final int ap = 0;
    static final int aq = 1;
    static final int ar = 2;
    static final int as = 3;
    static final int at = 4;
    static final int au = 5;
    static final /* synthetic */ boolean av = (!XMPNodeUtils.class.desiredAssertionStatus());

    private XMPNodeUtils() {
    }

    static int a(XMPNode xMPNode, String str) throws XMPException {
        if (xMPNode.n().k()) {
            for (int i = 1; i <= xMPNode.e(); i++) {
                XMPNode a2 = xMPNode.a(i);
                if (a2.j() && XMPConst.ak.equals(a2.c(1).l()) && str.equals(a2.c(1).m())) {
                    return i;
                }
            }
            return -1;
        }
        throw new XMPException("Language item must be used on array", 102);
    }

    private static int a(XMPNode xMPNode, String str, String str2, int i) throws XMPException {
        if (XMPConst.ak.equals(str)) {
            int a2 = a(xMPNode, Utils.a(str2));
            if (a2 >= 0 || (i & 4096) <= 0) {
                return a2;
            }
            XMPNode xMPNode2 = new XMPNode(XMPConst.ai, (PropertyOptions) null);
            xMPNode2.c(new XMPNode(XMPConst.ak, XMPConst.aj, (PropertyOptions) null));
            xMPNode.a(1, xMPNode2);
            return 1;
        }
        for (int i2 = 1; i2 < xMPNode.e(); i2++) {
            Iterator k = xMPNode.a(i2).k();
            while (k.hasNext()) {
                XMPNode xMPNode3 = (XMPNode) k.next();
                if (str.equals(xMPNode3.l()) && str2.equals(xMPNode3.m())) {
                    return i2;
                }
            }
        }
        return -1;
    }

    static XMPNode a(XMPNode xMPNode, XMPPath xMPPath, boolean z, PropertyOptions propertyOptions) throws XMPException {
        XMPNode xMPNode2;
        if (xMPPath == null || xMPPath.a() == 0) {
            throw new XMPException("Empty XMPPath", 102);
        }
        XMPNode a2 = a(xMPNode, xMPPath.a(0).b(), z);
        if (a2 == null) {
            return null;
        }
        if (a2.o()) {
            a2.b(false);
            xMPNode2 = a2;
        } else {
            xMPNode2 = null;
        }
        XMPNode xMPNode3 = xMPNode2;
        XMPNode xMPNode4 = a2;
        int i = 1;
        while (i < xMPPath.a()) {
            try {
                xMPNode4 = a(xMPNode4, xMPPath.a(i), z);
                if (xMPNode4 == null) {
                    if (z) {
                        a(xMPNode3);
                    }
                    return null;
                }
                if (xMPNode4.o()) {
                    xMPNode4.b(false);
                    if (i == 1 && xMPPath.a(i).c() && xMPPath.a(i).d() != 0) {
                        xMPNode4.n().a(xMPPath.a(i).d(), true);
                    } else if (i < xMPPath.a() - 1 && xMPPath.a(i).a() == 1 && !xMPNode4.n().p()) {
                        xMPNode4.n().f(true);
                    }
                    if (xMPNode3 == null) {
                        xMPNode3 = xMPNode4;
                    }
                }
                i++;
            } catch (XMPException e) {
                if (xMPNode3 != null) {
                    a(xMPNode3);
                }
                throw e;
            }
        }
        if (xMPNode3 != null) {
            xMPNode4.n().b(propertyOptions);
            xMPNode4.a(xMPNode4.n());
        }
        return xMPNode4;
    }

    private static XMPNode a(XMPNode xMPNode, XMPPathSegment xMPPathSegment, boolean z) throws XMPException {
        int i;
        int a2 = xMPPathSegment.a();
        if (a2 == 1) {
            return b(xMPNode, xMPPathSegment.b(), z);
        }
        if (a2 == 2) {
            return c(xMPNode, xMPPathSegment.b().substring(1), z);
        }
        if (xMPNode.n().k()) {
            if (a2 == 3) {
                i = d(xMPNode, xMPPathSegment.b(), z);
            } else if (a2 == 4) {
                i = xMPNode.e();
            } else if (a2 == 6) {
                String[] b = Utils.b(xMPPathSegment.b());
                i = c(xMPNode, b[0], b[1]);
            } else if (a2 == 5) {
                String[] b2 = Utils.b(xMPPathSegment.b());
                i = a(xMPNode, b2[0], b2[1], xMPPathSegment.d());
            } else {
                throw new XMPException("Unknown array indexing step in FollowXPathStep", 9);
            }
            if (1 > i || i > xMPNode.e()) {
                return null;
            }
            return xMPNode.a(i);
        }
        throw new XMPException("Indexing applied to non-array", 102);
    }

    static XMPNode a(XMPNode xMPNode, String str, String str2, boolean z) throws XMPException {
        if (av || xMPNode.b() == null) {
            XMPNode a2 = xMPNode.a(str);
            if (a2 == null && z) {
                a2 = new XMPNode(str, new PropertyOptions().k(true));
                a2.b(true);
                String a3 = XMPMetaFactory.a().a(str);
                if (a3 == null) {
                    if (str2 == null || str2.length() == 0) {
                        throw new XMPException("Unregistered schema namespace URI", 101);
                    }
                    a3 = XMPMetaFactory.a().a(str, str2);
                }
                a2.d(a3);
                xMPNode.a(a2);
            }
            return a2;
        }
        throw new AssertionError();
    }

    static XMPNode a(XMPNode xMPNode, String str, boolean z) throws XMPException {
        return a(xMPNode, str, (String) null, z);
    }

    static PropertyOptions a(PropertyOptions propertyOptions, Object obj) throws XMPException {
        if (propertyOptions == null) {
            propertyOptions = new PropertyOptions();
        }
        if (propertyOptions.n()) {
            propertyOptions.i(true);
        }
        if (propertyOptions.m()) {
            propertyOptions.h(true);
        }
        if (propertyOptions.l()) {
            propertyOptions.g(true);
        }
        if (!propertyOptions.p() || obj == null || obj.toString().length() <= 0) {
            propertyOptions.g(propertyOptions.i());
            return propertyOptions;
        }
        throw new XMPException("Structs and arrays can't have values", 103);
    }

    static String a(Object obj) {
        String str;
        XMPDateTime a2;
        if (obj == null) {
            str = null;
        } else if (obj instanceof Boolean) {
            str = XMPUtils.a(((Boolean) obj).booleanValue());
        } else if (obj instanceof Integer) {
            str = XMPUtils.a(((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            str = XMPUtils.a(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            str = XMPUtils.a(((Double) obj).doubleValue());
        } else {
            if (obj instanceof XMPDateTime) {
                a2 = (XMPDateTime) obj;
            } else if (obj instanceof GregorianCalendar) {
                a2 = XMPDateTimeFactory.a((Calendar) (GregorianCalendar) obj);
            } else {
                str = obj instanceof byte[] ? XMPUtils.a((byte[]) obj) : obj.toString();
            }
            str = XMPUtils.a(a2);
        }
        if (str != null) {
            return Utils.f(str);
        }
        return null;
    }

    static void a(XMPNode xMPNode) {
        XMPNode b = xMPNode.b();
        if (xMPNode.n().c()) {
            b.d(xMPNode);
        } else {
            b.b(xMPNode);
        }
        if (!b.h() && b.n().o()) {
            b.b().b(b);
        }
    }

    static void a(XMPNode xMPNode, Object obj) {
        String a2 = a(obj);
        if (xMPNode.n().c() && XMPConst.ak.equals(xMPNode.l())) {
            a2 = Utils.a(a2);
        }
        xMPNode.d(a2);
    }

    static void a(XMPNode xMPNode, String str, String str2) throws XMPException {
        XMPNode xMPNode2 = new XMPNode(XMPConst.ai, str2, (PropertyOptions) null);
        XMPNode xMPNode3 = new XMPNode(XMPConst.ak, str, (PropertyOptions) null);
        xMPNode2.c(xMPNode3);
        if (!XMPConst.aj.equals(xMPNode3.m())) {
            xMPNode.a(xMPNode2);
        } else {
            xMPNode.a(1, xMPNode2);
        }
    }

    static XMPNode b(XMPNode xMPNode, String str, boolean z) throws XMPException {
        if (!xMPNode.n().o() && !xMPNode.n().f()) {
            if (!xMPNode.o()) {
                throw new XMPException("Named children only allowed for schemas and structs", 102);
            } else if (xMPNode.n().k()) {
                throw new XMPException("Named children not allowed for arrays", 102);
            } else if (z) {
                xMPNode.n().f(true);
            }
        }
        XMPNode a2 = xMPNode.a(str);
        if (a2 == null && z) {
            XMPNode xMPNode2 = new XMPNode(str, new PropertyOptions());
            xMPNode2.b(true);
            xMPNode.a(xMPNode2);
            a2 = xMPNode2;
        }
        if (av || a2 != null || !z) {
            return a2;
        }
        throw new AssertionError();
    }

    static void b(XMPNode xMPNode) {
        if (xMPNode.n().n()) {
            int i = 2;
            while (i <= xMPNode.e()) {
                XMPNode a2 = xMPNode.a(i);
                if (!a2.j() || !XMPConst.aj.equals(a2.c(1).m())) {
                    i++;
                } else {
                    try {
                        xMPNode.b(i);
                        xMPNode.a(1, a2);
                    } catch (XMPException unused) {
                        if (!av) {
                            throw new AssertionError();
                        }
                    }
                    if (i == 2) {
                        xMPNode.a(2).d(a2.m());
                        return;
                    }
                    return;
                }
            }
        }
    }

    static Object[] b(XMPNode xMPNode, String str, String str2) throws XMPException {
        if (!xMPNode.n().n()) {
            throw new XMPException("Localized text array is not alt-text", 102);
        } else if (!xMPNode.h()) {
            return new Object[]{new Integer(0), null};
        } else {
            Iterator i = xMPNode.i();
            XMPNode xMPNode2 = null;
            XMPNode xMPNode3 = null;
            int i2 = 0;
            while (i.hasNext()) {
                XMPNode xMPNode4 = (XMPNode) i.next();
                if (xMPNode4.n().p()) {
                    throw new XMPException("Alt-text array item is not simple", 102);
                } else if (!xMPNode4.j() || !XMPConst.ak.equals(xMPNode4.c(1).l())) {
                    throw new XMPException("Alt-text array item has no language qualifier", 102);
                } else {
                    String m = xMPNode4.c(1).m();
                    if (str2.equals(m)) {
                        return new Object[]{new Integer(1), xMPNode4};
                    } else if (str != null && m.startsWith(str)) {
                        if (xMPNode2 == null) {
                            xMPNode2 = xMPNode4;
                        }
                        i2++;
                    } else if (XMPConst.aj.equals(m)) {
                        xMPNode3 = xMPNode4;
                    }
                }
            }
            if (i2 == 1) {
                return new Object[]{new Integer(2), xMPNode2};
            } else if (i2 > 1) {
                return new Object[]{new Integer(3), xMPNode2};
            } else if (xMPNode3 != null) {
                return new Object[]{new Integer(4), xMPNode3};
            } else {
                return new Object[]{new Integer(5), xMPNode.a(1)};
            }
        }
    }

    private static int c(XMPNode xMPNode, String str, String str2) throws XMPException {
        int i = 1;
        int i2 = -1;
        while (i <= xMPNode.e() && i2 < 0) {
            XMPNode a2 = xMPNode.a(i);
            if (a2.n().f()) {
                int i3 = 1;
                while (true) {
                    if (i3 > a2.e()) {
                        break;
                    }
                    XMPNode a3 = a2.a(i3);
                    if (str.equals(a3.l()) && str2.equals(a3.m())) {
                        i2 = i;
                        break;
                    }
                    i3++;
                }
                i++;
            } else {
                throw new XMPException("Field selector must be used on array of struct", 102);
            }
        }
        return i2;
    }

    private static XMPNode c(XMPNode xMPNode, String str, boolean z) throws XMPException {
        if (av || !str.startsWith("?")) {
            XMPNode b = xMPNode.b(str);
            if (b != null || !z) {
                return b;
            }
            XMPNode xMPNode2 = new XMPNode(str, (PropertyOptions) null);
            xMPNode2.b(true);
            xMPNode.c(xMPNode2);
            return xMPNode2;
        }
        throw new AssertionError();
    }

    static void c(XMPNode xMPNode) {
        if (xMPNode.n().m() && xMPNode.h()) {
            boolean z = false;
            Iterator i = xMPNode.i();
            while (true) {
                if (i.hasNext()) {
                    if (((XMPNode) i.next()).n().d()) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z) {
                xMPNode.n().j(true);
                b(xMPNode);
            }
        }
    }

    private static int d(XMPNode xMPNode, String str, boolean z) throws XMPException {
        try {
            int parseInt = Integer.parseInt(str.substring(1, str.length() - 1));
            if (parseInt >= 1) {
                if (z && parseInt == xMPNode.e() + 1) {
                    XMPNode xMPNode2 = new XMPNode(XMPConst.ai, (PropertyOptions) null);
                    xMPNode2.b(true);
                    xMPNode.a(xMPNode2);
                }
                return parseInt;
            }
            throw new XMPException("Array index must be larger than zero", 102);
        } catch (NumberFormatException unused) {
            throw new XMPException("Array index not digits.", 102);
        }
    }
}
