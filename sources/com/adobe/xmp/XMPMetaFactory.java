package com.adobe.xmp;

import com.adobe.xmp.impl.XMPMetaImpl;
import com.adobe.xmp.impl.XMPMetaParser;
import com.adobe.xmp.impl.XMPSchemaRegistryImpl;
import com.adobe.xmp.impl.XMPSerializerHelper;
import com.adobe.xmp.options.ParseOptions;
import com.adobe.xmp.options.SerializeOptions;
import java.io.InputStream;
import java.io.OutputStream;

public final class XMPMetaFactory {

    /* renamed from: a  reason: collision with root package name */
    private static XMPSchemaRegistry f680a = new XMPSchemaRegistryImpl();
    private static XMPVersionInfo b = null;

    private XMPMetaFactory() {
    }

    public static XMPMeta a(InputStream inputStream) throws XMPException {
        return a(inputStream, (ParseOptions) null);
    }

    public static XMPMeta a(InputStream inputStream, ParseOptions parseOptions) throws XMPException {
        return XMPMetaParser.a((Object) inputStream, parseOptions);
    }

    public static XMPMeta a(String str) throws XMPException {
        return a(str, (ParseOptions) null);
    }

    public static XMPMeta a(String str, ParseOptions parseOptions) throws XMPException {
        return XMPMetaParser.a((Object) str, parseOptions);
    }

    public static XMPMeta a(byte[] bArr) throws XMPException {
        return a(bArr, (ParseOptions) null);
    }

    public static XMPMeta a(byte[] bArr, ParseOptions parseOptions) throws XMPException {
        return XMPMetaParser.a((Object) bArr, parseOptions);
    }

    public static XMPSchemaRegistry a() {
        return f680a;
    }

    private static void a(XMPMeta xMPMeta) {
        if (!(xMPMeta instanceof XMPMetaImpl)) {
            throw new UnsupportedOperationException("The serializing service works onlywith the XMPMeta implementation of this library");
        }
    }

    public static void a(XMPMeta xMPMeta, OutputStream outputStream) throws XMPException {
        a(xMPMeta, outputStream, (SerializeOptions) null);
    }

    public static void a(XMPMeta xMPMeta, OutputStream outputStream, SerializeOptions serializeOptions) throws XMPException {
        a(xMPMeta);
        XMPSerializerHelper.a((XMPMetaImpl) xMPMeta, outputStream, serializeOptions);
    }

    public static byte[] a(XMPMeta xMPMeta, SerializeOptions serializeOptions) throws XMPException {
        a(xMPMeta);
        return XMPSerializerHelper.b((XMPMetaImpl) xMPMeta, serializeOptions);
    }

    public static XMPMeta b() {
        return new XMPMetaImpl();
    }

    public static String b(XMPMeta xMPMeta, SerializeOptions serializeOptions) throws XMPException {
        a(xMPMeta);
        return XMPSerializerHelper.a((XMPMetaImpl) xMPMeta, serializeOptions);
    }

    public static void c() {
        f680a = new XMPSchemaRegistryImpl();
    }

    public static synchronized XMPVersionInfo d() {
        XMPVersionInfo xMPVersionInfo;
        synchronized (XMPMetaFactory.class) {
            if (b == null) {
                try {
                    b = new XMPVersionInfo() {
                        public int a() {
                            return 5;
                        }

                        public int b() {
                            return 1;
                        }

                        public int c() {
                            return 0;
                        }

                        public boolean d() {
                            return false;
                        }

                        public int e() {
                            return 3;
                        }

                        public String f() {
                            return "Adobe XMP Core 5.1.0-jc003";
                        }

                        public String toString() {
                            return "Adobe XMP Core 5.1.0-jc003";
                        }
                    };
                } catch (Throwable th) {
                    System.out.println(th);
                }
            }
            xMPVersionInfo = b;
        }
        return xMPVersionInfo;
    }
}
