package com.drew.metadata.xmp;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.options.SerializeOptions;
import com.drew.metadata.Metadata;
import java.io.OutputStream;

public class XmpWriter {
    public static boolean a(OutputStream outputStream, Metadata metadata) {
        XmpDirectory xmpDirectory = (XmpDirectory) metadata.b(XmpDirectory.class);
        if (xmpDirectory == null) {
            return false;
        }
        try {
            XMPMetaFactory.a(xmpDirectory.k(), outputStream, new SerializeOptions().a(true));
            return true;
        } catch (XMPException e) {
            e.printStackTrace();
            return false;
        }
    }
}
