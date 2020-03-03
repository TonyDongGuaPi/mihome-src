package com.drew.metadata.xmp;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPIterator;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.impl.XMPMetaImpl;
import com.adobe.xmp.properties.XMPPropertyInfo;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class XmpDirectory extends Directory {
    public static final int e = 65535;
    @NotNull
    protected static final HashMap<Integer, String> f = new HashMap<>();
    @Nullable
    private XMPMeta g;

    @NotNull
    public String a() {
        return "XMP";
    }

    static {
        f.put(65535, "XMP Value Count");
    }

    public XmpDirectory() {
        a((TagDescriptor) new XmpDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return f;
    }

    @NotNull
    public Map<String, String> j() {
        HashMap hashMap = new HashMap();
        if (this.g != null) {
            try {
                XMPIterator a2 = this.g.a();
                while (a2.hasNext()) {
                    XMPPropertyInfo xMPPropertyInfo = (XMPPropertyInfo) a2.next();
                    String b = xMPPropertyInfo.b();
                    String c = xMPPropertyInfo.c();
                    if (!(b == null || c == null)) {
                        hashMap.put(b, c);
                    }
                }
            } catch (XMPException unused) {
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public void a(@NotNull XMPMeta xMPMeta) {
        this.g = xMPMeta;
        int i = 0;
        try {
            XMPIterator a2 = this.g.a();
            while (a2.hasNext()) {
                if (((XMPPropertyInfo) a2.next()).b() != null) {
                    i++;
                }
            }
            a(65535, i);
        } catch (XMPException unused) {
        }
    }

    @NotNull
    public XMPMeta k() {
        if (this.g == null) {
            this.g = new XMPMetaImpl();
        }
        return this.g;
    }
}
