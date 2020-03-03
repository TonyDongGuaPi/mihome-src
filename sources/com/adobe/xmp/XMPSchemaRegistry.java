package com.adobe.xmp;

import com.adobe.xmp.properties.XMPAliasInfo;
import java.util.Map;

public interface XMPSchemaRegistry {
    String a(String str);

    String a(String str, String str2) throws XMPException;

    Map a();

    XMPAliasInfo b(String str, String str2);

    String b(String str);

    Map b();

    Map c();

    void c(String str);

    XMPAliasInfo[] d(String str);

    XMPAliasInfo e(String str);
}
