package com.adobe.xmp.impl.xpath;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.impl.Utils;
import com.adobe.xmp.properties.XMPAliasInfo;

public final class XMPPathParser {
    private XMPPathParser() {
    }

    public static XMPPath a(String str, String str2) throws XMPException {
        if (str == null || str2 == null) {
            throw new XMPException("Parameter must not be null", 4);
        }
        XMPPath xMPPath = new XMPPath();
        PathPosition pathPosition = new PathPosition();
        pathPosition.f700a = str2;
        a(str, pathPosition, xMPPath);
        while (pathPosition.e < str2.length()) {
            pathPosition.d = pathPosition.e;
            a(str2, pathPosition);
            pathPosition.e = pathPosition.d;
            XMPPathSegment a2 = str2.charAt(pathPosition.d) != '[' ? a(pathPosition) : b(pathPosition);
            if (a2.a() == 1) {
                if (a2.b().charAt(0) == '@') {
                    a2.a("?" + a2.b().substring(1));
                    if (!"?xml:lang".equals(a2.b())) {
                        throw new XMPException("Only xml:lang allowed with '@'", 102);
                    }
                }
                if (a2.b().charAt(0) == '?') {
                    pathPosition.b++;
                    a2.a(2);
                }
            } else {
                if (a2.a() != 6) {
                    continue;
                } else {
                    if (a2.b().charAt(1) == '@') {
                        a2.a("[?" + a2.b().substring(2));
                        if (!a2.b().startsWith("[?xml:lang=")) {
                            throw new XMPException("Only xml:lang allowed with '@'", 102);
                        }
                    }
                    if (a2.b().charAt(1) == '?') {
                        pathPosition.b++;
                        a2.a(5);
                    }
                }
                xMPPath.a(a2);
            }
            a(pathPosition.f700a.substring(pathPosition.b, pathPosition.c));
            xMPPath.a(a2);
        }
        return xMPPath;
    }

    private static XMPPathSegment a(PathPosition pathPosition) throws XMPException {
        pathPosition.b = pathPosition.d;
        while (pathPosition.e < pathPosition.f700a.length() && "/[*".indexOf(pathPosition.f700a.charAt(pathPosition.e)) < 0) {
            pathPosition.e++;
        }
        pathPosition.c = pathPosition.e;
        if (pathPosition.e != pathPosition.d) {
            return new XMPPathSegment(pathPosition.f700a.substring(pathPosition.d, pathPosition.e), 1);
        }
        throw new XMPException("Empty XMPPath segment", 102);
    }

    private static void a(String str) throws XMPException {
        int indexOf = str.indexOf(58);
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            if (Utils.e(substring)) {
                if (XMPMetaFactory.a().b(substring) == null) {
                    throw new XMPException("Unknown namespace prefix for qualified name", 102);
                }
                return;
            }
        }
        throw new XMPException("Ill-formed qualified name", 102);
    }

    private static void a(String str, PathPosition pathPosition) throws XMPException {
        if (str.charAt(pathPosition.d) == '/') {
            pathPosition.d++;
            if (pathPosition.d >= str.length()) {
                throw new XMPException("Empty XMPPath segment", 102);
            }
        }
        if (str.charAt(pathPosition.d) == '*') {
            pathPosition.d++;
            if (pathPosition.d >= str.length() || str.charAt(pathPosition.d) != '[') {
                throw new XMPException("Missing '[' after '*'", 102);
            }
        }
    }

    private static void a(String str, PathPosition pathPosition, XMPPath xMPPath) throws XMPException {
        XMPPathSegment xMPPathSegment;
        while (pathPosition.e < pathPosition.f700a.length() && "/[*".indexOf(pathPosition.f700a.charAt(pathPosition.e)) < 0) {
            pathPosition.e++;
        }
        if (pathPosition.e != pathPosition.d) {
            String b = b(str, pathPosition.f700a.substring(pathPosition.d, pathPosition.e));
            XMPAliasInfo e = XMPMetaFactory.a().e(b);
            if (e == null) {
                xMPPath.a(new XMPPathSegment(str, Integer.MIN_VALUE));
                xMPPathSegment = new XMPPathSegment(b, 1);
            } else {
                xMPPath.a(new XMPPathSegment(e.a(), Integer.MIN_VALUE));
                XMPPathSegment xMPPathSegment2 = new XMPPathSegment(b(e.a(), e.c()), 1);
                xMPPathSegment2.a(true);
                xMPPathSegment2.b(e.d().i());
                xMPPath.a(xMPPathSegment2);
                if (e.d().e()) {
                    xMPPathSegment = new XMPPathSegment("[?xml:lang='x-default']", 5);
                } else if (e.d().b()) {
                    xMPPathSegment = new XMPPathSegment("[1]", 3);
                } else {
                    return;
                }
                xMPPathSegment.a(true);
                xMPPathSegment.b(e.d().i());
            }
            xMPPath.a(xMPPathSegment);
            return;
        }
        throw new XMPException("Empty initial XMPPath step", 102);
    }

    private static XMPPathSegment b(PathPosition pathPosition) throws XMPException {
        XMPPathSegment xMPPathSegment;
        pathPosition.e++;
        if ('0' > pathPosition.f700a.charAt(pathPosition.e) || pathPosition.f700a.charAt(pathPosition.e) > '9') {
            while (pathPosition.e < pathPosition.f700a.length() && pathPosition.f700a.charAt(pathPosition.e) != ']' && pathPosition.f700a.charAt(pathPosition.e) != '=') {
                pathPosition.e++;
            }
            if (pathPosition.e >= pathPosition.f700a.length()) {
                throw new XMPException("Missing ']' or '=' for array index", 102);
            } else if (pathPosition.f700a.charAt(pathPosition.e) != ']') {
                pathPosition.b = pathPosition.d + 1;
                pathPosition.c = pathPosition.e;
                pathPosition.e++;
                char charAt = pathPosition.f700a.charAt(pathPosition.e);
                if (charAt == '\'' || charAt == '\"') {
                    while (true) {
                        pathPosition.e++;
                        if (pathPosition.e < pathPosition.f700a.length()) {
                            if (pathPosition.f700a.charAt(pathPosition.e) == charAt) {
                                if (pathPosition.e + 1 >= pathPosition.f700a.length() || pathPosition.f700a.charAt(pathPosition.e + 1) != charAt) {
                                    break;
                                }
                                pathPosition.e++;
                            }
                        } else {
                            break;
                        }
                    }
                    if (pathPosition.e < pathPosition.f700a.length()) {
                        pathPosition.e++;
                        xMPPathSegment = new XMPPathSegment((String) null, 6);
                    } else {
                        throw new XMPException("No terminating quote for array selector", 102);
                    }
                } else {
                    throw new XMPException("Invalid quote in array selector", 102);
                }
            } else if ("[last()".equals(pathPosition.f700a.substring(pathPosition.d, pathPosition.e))) {
                xMPPathSegment = new XMPPathSegment((String) null, 4);
            } else {
                throw new XMPException("Invalid non-numeric array index", 102);
            }
        } else {
            while (pathPosition.e < pathPosition.f700a.length() && '0' <= pathPosition.f700a.charAt(pathPosition.e) && pathPosition.f700a.charAt(pathPosition.e) <= '9') {
                pathPosition.e++;
            }
            xMPPathSegment = new XMPPathSegment((String) null, 3);
        }
        if (pathPosition.e >= pathPosition.f700a.length() || pathPosition.f700a.charAt(pathPosition.e) != ']') {
            throw new XMPException("Missing ']' for array index", 102);
        }
        pathPosition.e++;
        xMPPathSegment.a(pathPosition.f700a.substring(pathPosition.d, pathPosition.e));
        return xMPPathSegment;
    }

    private static String b(String str, String str2) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Schema namespace URI is required", 101);
        } else if (str2.charAt(0) == '?' || str2.charAt(0) == '@') {
            throw new XMPException("Top level name must not be a qualifier", 102);
        } else if (str2.indexOf(47) >= 0 || str2.indexOf(91) >= 0) {
            throw new XMPException("Top level name must be simple", 102);
        } else {
            String a2 = XMPMetaFactory.a().a(str);
            if (a2 != null) {
                int indexOf = str2.indexOf(58);
                if (indexOf < 0) {
                    b(str2);
                    return a2 + str2;
                }
                b(str2.substring(0, indexOf));
                b(str2.substring(indexOf));
                String substring = str2.substring(0, indexOf + 1);
                String a3 = XMPMetaFactory.a().a(str);
                if (a3 == null) {
                    throw new XMPException("Unknown schema namespace prefix", 101);
                } else if (substring.equals(a3)) {
                    return str2;
                } else {
                    throw new XMPException("Schema namespace URI and prefix mismatch", 101);
                }
            } else {
                throw new XMPException("Unregistered schema namespace URI", 101);
            }
        }
    }

    private static void b(String str) throws XMPException {
        if (!Utils.d(str)) {
            throw new XMPException("Bad XML name", 102);
        }
    }
}
