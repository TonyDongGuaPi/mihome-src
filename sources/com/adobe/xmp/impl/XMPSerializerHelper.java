package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.options.SerializeOptions;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class XMPSerializerHelper {
    public static String a(XMPMetaImpl xMPMetaImpl, SerializeOptions serializeOptions) throws XMPException {
        if (serializeOptions == null) {
            serializeOptions = new SerializeOptions();
        }
        serializeOptions.i(true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
        a(xMPMetaImpl, byteArrayOutputStream, serializeOptions);
        try {
            return byteArrayOutputStream.toString(serializeOptions.t());
        } catch (UnsupportedEncodingException unused) {
            return byteArrayOutputStream.toString();
        }
    }

    public static void a(XMPMetaImpl xMPMetaImpl, OutputStream outputStream, SerializeOptions serializeOptions) throws XMPException {
        if (serializeOptions == null) {
            serializeOptions = new SerializeOptions();
        }
        if (serializeOptions.l()) {
            xMPMetaImpl.d();
        }
        new XMPSerializerRDF().a((XMPMeta) xMPMetaImpl, outputStream, serializeOptions);
    }

    public static byte[] b(XMPMetaImpl xMPMetaImpl, SerializeOptions serializeOptions) throws XMPException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
        a(xMPMetaImpl, byteArrayOutputStream, serializeOptions);
        return byteArrayOutputStream.toByteArray();
    }
}
