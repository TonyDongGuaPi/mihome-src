package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.XMPUtils;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.options.ParseOptions;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPAliasInfo;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.cybergarage.upnp.event.Subscription;

public class XMPNormalizer {

    /* renamed from: a  reason: collision with root package name */
    private static Map f697a;

    static {
        a();
    }

    private XMPNormalizer() {
    }

    static XMPMeta a(XMPMetaImpl xMPMetaImpl, ParseOptions parseOptions) throws XMPException {
        XMPNode f = xMPMetaImpl.f();
        a(xMPMetaImpl);
        a(f, parseOptions);
        a(f);
        e(f);
        return xMPMetaImpl;
    }

    private static void a() {
        f697a = new HashMap();
        PropertyOptions propertyOptions = new PropertyOptions();
        propertyOptions.g(true);
        f697a.put("dc:contributor", propertyOptions);
        f697a.put("dc:language", propertyOptions);
        f697a.put("dc:publisher", propertyOptions);
        f697a.put("dc:relation", propertyOptions);
        f697a.put("dc:subject", propertyOptions);
        f697a.put("dc:type", propertyOptions);
        PropertyOptions propertyOptions2 = new PropertyOptions();
        propertyOptions2.g(true);
        propertyOptions2.h(true);
        f697a.put("dc:creator", propertyOptions2);
        f697a.put("dc:date", propertyOptions2);
        PropertyOptions propertyOptions3 = new PropertyOptions();
        propertyOptions3.g(true);
        propertyOptions3.h(true);
        propertyOptions3.i(true);
        propertyOptions3.j(true);
        f697a.put("dc:description", propertyOptions3);
        f697a.put("dc:rights", propertyOptions3);
        f697a.put("dc:title", propertyOptions3);
    }

    private static void a(XMPMeta xMPMeta, XMPNode xMPNode) {
        String str;
        try {
            XMPNode a2 = XMPNodeUtils.a(((XMPMetaImpl) xMPMeta).f(), "http://purl.org/dc/elements/1.1/", true);
            String m = xMPNode.m();
            XMPNode b = XMPNodeUtils.b(a2, "dc:rights", false);
            if (b != null) {
                if (b.h()) {
                    int a3 = XMPNodeUtils.a(b, XMPConst.aj);
                    if (a3 < 0) {
                        xMPMeta.c("http://purl.org/dc/elements/1.1/", "rights", "", XMPConst.aj, b.a(1).m(), (PropertyOptions) null);
                        a3 = XMPNodeUtils.a(b, XMPConst.aj);
                    }
                    XMPNode a4 = b.a(a3);
                    String m2 = a4.m();
                    int indexOf = m2.indexOf("\n\n");
                    if (indexOf < 0) {
                        if (!m.equals(m2)) {
                            str = m2 + "\n\n" + m;
                        }
                        xMPNode.b().b(xMPNode);
                    }
                    int i = indexOf + 2;
                    if (!m2.substring(i).equals(m)) {
                        str = m2.substring(0, i) + m;
                    }
                    xMPNode.b().b(xMPNode);
                    a4.d(str);
                    xMPNode.b().b(xMPNode);
                }
            }
            xMPMeta.c("http://purl.org/dc/elements/1.1/", "rights", "", XMPConst.aj, "\n\n" + m, (PropertyOptions) null);
            xMPNode.b().b(xMPNode);
        } catch (XMPException unused) {
        }
    }

    private static void a(XMPMetaImpl xMPMetaImpl) throws XMPException {
        XMPNode b;
        XMPNodeUtils.a(xMPMetaImpl.f(), "http://purl.org/dc/elements/1.1/", true);
        Iterator i = xMPMetaImpl.f().i();
        while (i.hasNext()) {
            XMPNode xMPNode = (XMPNode) i.next();
            if ("http://purl.org/dc/elements/1.1/".equals(xMPNode.l())) {
                b(xMPNode);
            } else {
                if ("http://ns.adobe.com/exif/1.0/".equals(xMPNode.l())) {
                    d(xMPNode);
                    b = XMPNodeUtils.b(xMPNode, "exif:UserComment", false);
                    if (b == null) {
                    }
                } else if (XMPConst.Q.equals(xMPNode.l())) {
                    XMPNode b2 = XMPNodeUtils.b(xMPNode, "xmpDM:copyright", false);
                    if (b2 != null) {
                        a((XMPMeta) xMPMetaImpl, b2);
                    }
                } else if (XMPConst.k_.equals(xMPNode.l())) {
                    b = XMPNodeUtils.b(xMPNode, "xmpRights:UsageTerms", false);
                    if (b == null) {
                    }
                }
                c(b);
            }
        }
    }

    private static void a(XMPNode xMPNode) throws XMPException {
        if (xMPNode.l() != null && xMPNode.l().length() >= 36) {
            String lowerCase = xMPNode.l().toLowerCase();
            if (lowerCase.startsWith(Subscription.UUID)) {
                lowerCase = lowerCase.substring(5);
            }
            if (Utils.c(lowerCase)) {
                XMPNode a2 = XMPNodeUtils.a(xMPNode, XMPPathParser.a(XMPConst.l_, "InstanceID"), true, (PropertyOptions) null);
                if (a2 != null) {
                    a2.a((PropertyOptions) null);
                    a2.d(Subscription.UUID + lowerCase);
                    a2.d();
                    a2.g();
                    xMPNode.c((String) null);
                    return;
                }
                throw new XMPException("Failure creating xmpMM:InstanceID", 9);
            }
        }
    }

    private static void a(XMPNode xMPNode, XMPNode xMPNode2, boolean z) throws XMPException {
        if (!xMPNode.m().equals(xMPNode2.m()) || xMPNode.e() != xMPNode2.e()) {
            throw new XMPException("Mismatch between alias and base nodes", 203);
        } else if (z || (xMPNode.l().equals(xMPNode2.l()) && xMPNode.n().equals(xMPNode2.n()) && xMPNode.f() == xMPNode2.f())) {
            Iterator i = xMPNode.i();
            Iterator i2 = xMPNode2.i();
            while (i.hasNext() && i2.hasNext()) {
                a((XMPNode) i.next(), (XMPNode) i2.next(), false);
            }
            Iterator k = xMPNode.k();
            Iterator k2 = xMPNode2.k();
            while (k.hasNext() && k2.hasNext()) {
                a((XMPNode) k.next(), (XMPNode) k2.next(), false);
            }
        } else {
            throw new XMPException("Mismatch between alias and base nodes", 203);
        }
    }

    private static void a(XMPNode xMPNode, ParseOptions parseOptions) throws XMPException {
        if (xMPNode.p()) {
            xMPNode.c(false);
            boolean b = parseOptions.b();
            for (XMPNode xMPNode2 : xMPNode.t()) {
                if (xMPNode2.p()) {
                    Iterator i = xMPNode2.i();
                    while (i.hasNext()) {
                        XMPNode xMPNode3 = (XMPNode) i.next();
                        if (xMPNode3.q()) {
                            xMPNode3.d(false);
                            XMPAliasInfo e = XMPMetaFactory.a().e(xMPNode3.l());
                            if (e != null) {
                                XMPNode xMPNode4 = null;
                                XMPNode a2 = XMPNodeUtils.a(xMPNode, e.a(), (String) null, true);
                                a2.b(false);
                                XMPNode b2 = XMPNodeUtils.b(a2, e.b() + e.c(), false);
                                if (b2 == null) {
                                    if (e.d().a()) {
                                        xMPNode3.c(e.b() + e.c());
                                        a2.a(xMPNode3);
                                    } else {
                                        XMPNode xMPNode5 = new XMPNode(e.b() + e.c(), e.d().f());
                                        a2.a(xMPNode5);
                                        a(i, xMPNode3, xMPNode5);
                                    }
                                } else if (!e.d().a()) {
                                    if (e.d().e()) {
                                        int a3 = XMPNodeUtils.a(b2, XMPConst.aj);
                                        if (a3 != -1) {
                                            xMPNode4 = b2.a(a3);
                                        }
                                    } else if (b2.h()) {
                                        xMPNode4 = b2.a(1);
                                    }
                                    if (xMPNode4 == null) {
                                        a(i, xMPNode3, b2);
                                    } else if (b) {
                                        a(xMPNode3, xMPNode4, true);
                                    }
                                } else if (b) {
                                    a(xMPNode3, b2, true);
                                }
                                i.remove();
                            }
                        }
                    }
                    xMPNode2.c(false);
                }
            }
        }
    }

    private static void a(Iterator it, XMPNode xMPNode, XMPNode xMPNode2) throws XMPException {
        if (xMPNode2.n().n()) {
            if (!xMPNode.n().d()) {
                xMPNode.c(new XMPNode(XMPConst.ak, XMPConst.aj, (PropertyOptions) null));
            } else {
                throw new XMPException("Alias to x-default already has a language qualifier", 203);
            }
        }
        it.remove();
        xMPNode.c(XMPConst.ai);
        xMPNode2.a(xMPNode);
    }

    private static void b(XMPNode xMPNode) throws XMPException {
        for (int i = 1; i <= xMPNode.e(); i++) {
            XMPNode a2 = xMPNode.a(i);
            PropertyOptions propertyOptions = (PropertyOptions) f697a.get(a2.l());
            if (propertyOptions != null) {
                if (a2.n().q()) {
                    XMPNode xMPNode2 = new XMPNode(a2.l(), propertyOptions);
                    a2.c(XMPConst.ai);
                    xMPNode2.a(a2);
                    xMPNode.b(i, xMPNode2);
                    if (propertyOptions.n() && !a2.n().d()) {
                        a2.c(new XMPNode(XMPConst.ak, XMPConst.aj, (PropertyOptions) null));
                    }
                } else {
                    a2.n().a(7680, false);
                    a2.n().b(propertyOptions);
                    if (propertyOptions.n()) {
                        c(a2);
                    }
                }
            }
        }
    }

    private static void c(XMPNode xMPNode) throws XMPException {
        if (xMPNode != null && xMPNode.n().k()) {
            xMPNode.n().h(true).i(true).j(true);
            Iterator i = xMPNode.i();
            while (i.hasNext()) {
                XMPNode xMPNode2 = (XMPNode) i.next();
                if (!xMPNode2.n().p()) {
                    if (!xMPNode2.n().d()) {
                        String m = xMPNode2.m();
                        if (!(m == null || m.length() == 0)) {
                            xMPNode2.c(new XMPNode(XMPConst.ak, "x-repair", (PropertyOptions) null));
                        }
                    }
                }
                i.remove();
            }
        }
    }

    private static void d(XMPNode xMPNode) throws XMPException {
        XMPNode b = XMPNodeUtils.b(xMPNode, "exif:GPSTimeStamp", false);
        if (b != null) {
            try {
                XMPDateTime e = XMPUtils.e(b.m());
                if (e.a() != 0 || e.b() != 0) {
                    return;
                }
                if (e.c() == 0) {
                    XMPNode b2 = XMPNodeUtils.b(xMPNode, "exif:DateTimeOriginal", false);
                    if (b2 == null) {
                        b2 = XMPNodeUtils.b(xMPNode, "exif:DateTimeDigitized", false);
                    }
                    XMPDateTime e2 = XMPUtils.e(b2.m());
                    Calendar l = e.l();
                    l.set(1, e2.a());
                    l.set(2, e2.b());
                    l.set(5, e2.c());
                    b.d(XMPUtils.a((XMPDateTime) new XMPDateTimeImpl(l)));
                }
            } catch (XMPException unused) {
            }
        }
    }

    private static void e(XMPNode xMPNode) {
        Iterator i = xMPNode.i();
        while (i.hasNext()) {
            if (!((XMPNode) i.next()).h()) {
                i.remove();
            }
        }
    }
}
