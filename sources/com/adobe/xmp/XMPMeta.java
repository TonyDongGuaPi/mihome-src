package com.adobe.xmp;

import com.adobe.xmp.options.IteratorOptions;
import com.adobe.xmp.options.ParseOptions;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPProperty;
import java.util.Calendar;

public interface XMPMeta extends Cloneable {
    XMPIterator a() throws XMPException;

    XMPIterator a(IteratorOptions iteratorOptions) throws XMPException;

    XMPIterator a(String str, String str2, IteratorOptions iteratorOptions) throws XMPException;

    XMPProperty a(String str, String str2) throws XMPException;

    XMPProperty a(String str, String str2, int i) throws XMPException;

    XMPProperty a(String str, String str2, String str3, String str4) throws XMPException;

    void a(ParseOptions parseOptions) throws XMPException;

    void a(String str);

    void a(String str, String str2, double d) throws XMPException;

    void a(String str, String str2, double d, PropertyOptions propertyOptions) throws XMPException;

    void a(String str, String str2, int i, PropertyOptions propertyOptions) throws XMPException;

    void a(String str, String str2, int i, String str3) throws XMPException;

    void a(String str, String str2, int i, String str3, PropertyOptions propertyOptions) throws XMPException;

    void a(String str, String str2, long j) throws XMPException;

    void a(String str, String str2, long j, PropertyOptions propertyOptions) throws XMPException;

    void a(String str, String str2, XMPDateTime xMPDateTime) throws XMPException;

    void a(String str, String str2, XMPDateTime xMPDateTime, PropertyOptions propertyOptions) throws XMPException;

    void a(String str, String str2, PropertyOptions propertyOptions, String str3, PropertyOptions propertyOptions2) throws XMPException;

    void a(String str, String str2, Object obj) throws XMPException;

    void a(String str, String str2, Object obj, PropertyOptions propertyOptions) throws XMPException;

    void a(String str, String str2, String str3) throws XMPException;

    void a(String str, String str2, String str3, String str4, String str5) throws XMPException;

    void a(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) throws XMPException;

    void a(String str, String str2, Calendar calendar) throws XMPException;

    void a(String str, String str2, Calendar calendar, PropertyOptions propertyOptions) throws XMPException;

    void a(String str, String str2, boolean z) throws XMPException;

    void a(String str, String str2, boolean z, PropertyOptions propertyOptions) throws XMPException;

    void a(String str, String str2, byte[] bArr) throws XMPException;

    void a(String str, String str2, byte[] bArr, PropertyOptions propertyOptions) throws XMPException;

    int b(String str, String str2) throws XMPException;

    XMPProperty b(String str, String str2, String str3, String str4) throws XMPException;

    String b();

    void b(String str, String str2, int i);

    void b(String str, String str2, int i, String str3) throws XMPException;

    void b(String str, String str2, int i, String str3, PropertyOptions propertyOptions) throws XMPException;

    void b(String str, String str2, String str3, String str4, String str5) throws XMPException;

    void b(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) throws XMPException;

    String c();

    void c(String str, String str2);

    void c(String str, String str2, String str3, String str4);

    void c(String str, String str2, String str3, String str4, String str5) throws XMPException;

    void c(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) throws XMPException;

    boolean c(String str, String str2, int i);

    Object clone();

    void d();

    void d(String str, String str2, int i) throws XMPException;

    void d(String str, String str2, String str3, String str4);

    boolean d(String str, String str2);

    Boolean e(String str, String str2) throws XMPException;

    String e();

    boolean e(String str, String str2, String str3, String str4);

    Integer f(String str, String str2) throws XMPException;

    boolean f(String str, String str2, String str3, String str4);

    XMPProperty g(String str, String str2, String str3, String str4) throws XMPException;

    Long g(String str, String str2) throws XMPException;

    Double h(String str, String str2) throws XMPException;

    XMPDateTime i(String str, String str2) throws XMPException;

    Calendar j(String str, String str2) throws XMPException;

    byte[] k(String str, String str2) throws XMPException;

    String l(String str, String str2) throws XMPException;
}
