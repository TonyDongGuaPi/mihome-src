package com.adobe.xmp.impl;

import android.support.media.ExifInterface;
import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPSchemaRegistry;
import com.adobe.xmp.options.AliasOptions;
import com.adobe.xmp.properties.XMPAliasInfo;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public final class XMPSchemaRegistryImpl implements XMPConst, XMPSchemaRegistry {
    private Map ap = new HashMap();
    private Map aq = new HashMap();
    private Map ar = new HashMap();
    private Pattern as = Pattern.compile("[/*?\\[\\]]");

    public XMPSchemaRegistryImpl() {
        try {
            d();
            e();
        } catch (XMPException unused) {
            throw new RuntimeException("The XMPSchemaRegistry cannot be initialized!");
        }
    }

    private void d() throws XMPException {
        a(XMPConst.f678a, "xml");
        a(XMPConst.b_, "rdf");
        a("http://purl.org/dc/elements/1.1/", "dc");
        a(XMPConst.d_, "Iptc4xmpCore");
        a(XMPConst.e_, "Iptc4xmpExt");
        a(XMPConst.f_, "DICOM");
        a(XMPConst.g_, "plus");
        a(XMPConst.h_, "x");
        a(XMPConst.i_, "iX");
        a("http://ns.adobe.com/xap/1.0/", "xmp");
        a(XMPConst.k_, "xmpRights");
        a(XMPConst.l_, "xmpMM");
        a(XMPConst.m_, "xmpBJ");
        a(XMPConst.n, "xmpNote");
        a(XMPConst.o, "pdf");
        a(XMPConst.p, "pdfx");
        a(XMPConst.q, "pdfxid");
        a(XMPConst.r, "pdfaSchema");
        a(XMPConst.s, "pdfaProperty");
        a(XMPConst.t, "pdfaType");
        a(XMPConst.u, "pdfaField");
        a(XMPConst.v, "pdfaid");
        a(XMPConst.w, "pdfaExtension");
        a(XMPConst.x, "photoshop");
        a(XMPConst.y, "album");
        a("http://ns.adobe.com/exif/1.0/", "exif");
        a(XMPConst.A, "exifEX");
        a("http://ns.adobe.com/exif/1.0/aux/", "aux");
        a("http://ns.adobe.com/tiff/1.0/", "tiff");
        a(XMPConst.D, "png");
        a(XMPConst.E, "jpeg");
        a(XMPConst.F, "jp2k");
        a(XMPConst.G, "crs");
        a(XMPConst.H, "bmsp");
        a(XMPConst.I, "creatorAtom");
        a(XMPConst.J, "asf");
        a(XMPConst.K, "wav");
        a(XMPConst.L, "bext");
        a(XMPConst.M, "riffinfo");
        a(XMPConst.N, "xmpScript");
        a(XMPConst.O, "txmp");
        a(XMPConst.P, "swf");
        a(XMPConst.Q, "xmpDM");
        a(XMPConst.R, "xmpx");
        a(XMPConst.V, "xmpT");
        a(XMPConst.W, "xmpTPg");
        a(XMPConst.X, "xmpG");
        a(XMPConst.Y, "xmpGImg");
        a(XMPConst.Z, "stFnt");
        a(XMPConst.U, "stDim");
        a(XMPConst.aa, "stEvt");
        a(XMPConst.ab, "stRef");
        a(XMPConst.ac, "stVer");
        a(XMPConst.ad, "stJob");
        a(XMPConst.ae, "stMfs");
        a(XMPConst.T, "xmpidq");
    }

    private void e() throws XMPException {
        AliasOptions b = new AliasOptions().b(true);
        AliasOptions d = new AliasOptions().d(true);
        a("http://ns.adobe.com/xap/1.0/", "Author", "http://purl.org/dc/elements/1.1/", "creator", b);
        a("http://ns.adobe.com/xap/1.0/", "Authors", "http://purl.org/dc/elements/1.1/", "creator", (AliasOptions) null);
        a("http://ns.adobe.com/xap/1.0/", "Description", "http://purl.org/dc/elements/1.1/", "description", (AliasOptions) null);
        a("http://ns.adobe.com/xap/1.0/", "Format", "http://purl.org/dc/elements/1.1/", IjkMediaMeta.IJKM_KEY_FORMAT, (AliasOptions) null);
        a("http://ns.adobe.com/xap/1.0/", "Keywords", "http://purl.org/dc/elements/1.1/", "subject", (AliasOptions) null);
        a("http://ns.adobe.com/xap/1.0/", "Locale", "http://purl.org/dc/elements/1.1/", "language", (AliasOptions) null);
        a("http://ns.adobe.com/xap/1.0/", "Title", "http://purl.org/dc/elements/1.1/", "title", (AliasOptions) null);
        a(XMPConst.k_, ExifInterface.TAG_COPYRIGHT, "http://purl.org/dc/elements/1.1/", "rights", (AliasOptions) null);
        a(XMPConst.o, "Author", "http://purl.org/dc/elements/1.1/", "creator", b);
        a(XMPConst.o, "BaseURL", "http://ns.adobe.com/xap/1.0/", "BaseURL", (AliasOptions) null);
        a(XMPConst.o, "CreationDate", "http://ns.adobe.com/xap/1.0/", "CreateDate", (AliasOptions) null);
        a(XMPConst.o, "Creator", "http://ns.adobe.com/xap/1.0/", "CreatorTool", (AliasOptions) null);
        a(XMPConst.o, "ModDate", "http://ns.adobe.com/xap/1.0/", "ModifyDate", (AliasOptions) null);
        AliasOptions aliasOptions = d;
        a(XMPConst.o, "Subject", "http://purl.org/dc/elements/1.1/", "description", aliasOptions);
        a(XMPConst.o, "Title", "http://purl.org/dc/elements/1.1/", "title", aliasOptions);
        a(XMPConst.x, "Author", "http://purl.org/dc/elements/1.1/", "creator", b);
        a(XMPConst.x, "Caption", "http://purl.org/dc/elements/1.1/", "description", aliasOptions);
        a(XMPConst.x, ExifInterface.TAG_COPYRIGHT, "http://purl.org/dc/elements/1.1/", "rights", aliasOptions);
        a(XMPConst.x, "Keywords", "http://purl.org/dc/elements/1.1/", "subject", (AliasOptions) null);
        a(XMPConst.x, "Marked", XMPConst.k_, "Marked", (AliasOptions) null);
        a(XMPConst.x, "Title", "http://purl.org/dc/elements/1.1/", "title", d);
        a(XMPConst.x, "WebStatement", XMPConst.k_, "WebStatement", (AliasOptions) null);
        a("http://ns.adobe.com/tiff/1.0/", ExifInterface.TAG_ARTIST, "http://purl.org/dc/elements/1.1/", "creator", b);
        a("http://ns.adobe.com/tiff/1.0/", ExifInterface.TAG_COPYRIGHT, "http://purl.org/dc/elements/1.1/", "rights", (AliasOptions) null);
        a("http://ns.adobe.com/tiff/1.0/", ExifInterface.TAG_DATETIME, "http://ns.adobe.com/xap/1.0/", "ModifyDate", (AliasOptions) null);
        a("http://ns.adobe.com/tiff/1.0/", ExifInterface.TAG_IMAGE_DESCRIPTION, "http://purl.org/dc/elements/1.1/", "description", (AliasOptions) null);
        a("http://ns.adobe.com/tiff/1.0/", ExifInterface.TAG_SOFTWARE, "http://ns.adobe.com/xap/1.0/", "CreatorTool", (AliasOptions) null);
        a(XMPConst.D, "Author", "http://purl.org/dc/elements/1.1/", "creator", b);
        a(XMPConst.D, ExifInterface.TAG_COPYRIGHT, "http://purl.org/dc/elements/1.1/", "rights", d);
        a(XMPConst.D, "CreationTime", "http://ns.adobe.com/xap/1.0/", "CreateDate", (AliasOptions) null);
        a(XMPConst.D, "Description", "http://purl.org/dc/elements/1.1/", "description", d);
        a(XMPConst.D, "ModificationTime", "http://ns.adobe.com/xap/1.0/", "ModifyDate", (AliasOptions) null);
        a(XMPConst.D, ExifInterface.TAG_SOFTWARE, "http://ns.adobe.com/xap/1.0/", "CreatorTool", (AliasOptions) null);
        a(XMPConst.D, "Title", "http://purl.org/dc/elements/1.1/", "title", d);
    }

    public synchronized String a(String str) {
        return (String) this.ap.get(str);
    }

    public synchronized String a(String str, String str2) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.d(str2);
        if (str2.charAt(str2.length() - 1) != ':') {
            str2 = str2 + Operators.CONDITION_IF_MIDDLE;
        }
        if (Utils.e(str2.substring(0, str2.length() - 1))) {
            String str3 = (String) this.ap.get(str);
            String str4 = (String) this.aq.get(str2);
            if (str3 != null) {
                return str3;
            }
            if (str4 != null) {
                String str5 = str2;
                int i = 1;
                while (this.aq.containsKey(str5)) {
                    str5 = str2.substring(0, str2.length() - 1) + JSMethod.NOT_SET + i + "_:";
                    i++;
                }
                str2 = str5;
            }
            this.aq.put(str2, str);
            this.ap.put(str, str2);
            return str2;
        }
        throw new XMPException("The prefix is a bad XML name", 201);
    }

    public synchronized Map a() {
        return Collections.unmodifiableMap(new TreeMap(this.ap));
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(String str, String str2, String str3, String str4, AliasOptions aliasOptions) throws XMPException {
        ParameterAsserts.c(str);
        ParameterAsserts.b(str2);
        ParameterAsserts.c(str3);
        ParameterAsserts.b(str4);
        final AliasOptions aliasOptions2 = aliasOptions != null ? new AliasOptions(XMPNodeUtils.a(aliasOptions.f(), (Object) null).i()) : new AliasOptions();
        if (this.as.matcher(str2).find() || this.as.matcher(str4).find()) {
            throw new XMPException("Alias and actual property names must be simple", 102);
        }
        String a2 = a(str);
        final String a3 = a(str3);
        if (a2 == null) {
            throw new XMPException("Alias namespace is not registered", 101);
        } else if (a3 != null) {
            String str5 = a2 + str2;
            if (!this.ar.containsKey(str5)) {
                if (!this.ar.containsKey(a3 + str4)) {
                    final String str6 = str3;
                    final String str7 = str4;
                    this.ar.put(str5, new XMPAliasInfo() {
                        public String a() {
                            return str6;
                        }

                        public String b() {
                            return a3;
                        }

                        public String c() {
                            return str7;
                        }

                        public AliasOptions d() {
                            return aliasOptions2;
                        }

                        public String toString() {
                            return a3 + str7 + " NS(" + str6 + "), FORM (" + d() + Operators.BRACKET_END_STR;
                        }
                    });
                } else {
                    throw new XMPException("Actual property is already an alias, use the base property", 4);
                }
            } else {
                throw new XMPException("Alias is already existing", 4);
            }
        } else {
            throw new XMPException("Actual namespace is not registered", 101);
        }
    }

    public synchronized XMPAliasInfo b(String str, String str2) {
        String a2 = a(str);
        if (a2 == null) {
            return null;
        }
        Map map = this.ar;
        return (XMPAliasInfo) map.get(a2 + str2);
    }

    public synchronized String b(String str) {
        if (str != null) {
            try {
                if (!str.endsWith(":")) {
                    str = str + ":";
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return (String) this.aq.get(str);
    }

    public synchronized Map b() {
        return Collections.unmodifiableMap(new TreeMap(this.aq));
    }

    public synchronized Map c() {
        return Collections.unmodifiableMap(new TreeMap(this.ar));
    }

    public synchronized void c(String str) {
        String a2 = a(str);
        if (a2 != null) {
            this.ap.remove(str);
            this.aq.remove(a2);
        }
    }

    public synchronized XMPAliasInfo[] d(String str) {
        ArrayList arrayList;
        String a2 = a(str);
        arrayList = new ArrayList();
        if (a2 != null) {
            for (String str2 : this.ar.keySet()) {
                if (str2.startsWith(a2)) {
                    arrayList.add(e(str2));
                }
            }
        }
        return (XMPAliasInfo[]) arrayList.toArray(new XMPAliasInfo[arrayList.size()]);
    }

    public synchronized XMPAliasInfo e(String str) {
        return (XMPAliasInfo) this.ar.get(str);
    }
}
