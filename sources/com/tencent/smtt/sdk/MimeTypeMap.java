package com.tencent.smtt.sdk;

public class MimeTypeMap {

    /* renamed from: a  reason: collision with root package name */
    private static MimeTypeMap f9079a;

    private MimeTypeMap() {
    }

    public static String getFileExtensionFromUrl(String str) {
        bt a2 = bt.a();
        return (a2 == null || !a2.b()) ? android.webkit.MimeTypeMap.getFileExtensionFromUrl(str) : a2.c().h(str);
    }

    public static synchronized MimeTypeMap getSingleton() {
        MimeTypeMap mimeTypeMap;
        synchronized (MimeTypeMap.class) {
            if (f9079a == null) {
                f9079a = new MimeTypeMap();
            }
            mimeTypeMap = f9079a;
        }
        return mimeTypeMap;
    }

    public String getExtensionFromMimeType(String str) {
        bt a2 = bt.a();
        return (a2 == null || !a2.b()) ? android.webkit.MimeTypeMap.getSingleton().getExtensionFromMimeType(str) : a2.c().l(str);
    }

    public String getMimeTypeFromExtension(String str) {
        bt a2 = bt.a();
        return (a2 == null || !a2.b()) ? android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(str) : a2.c().j(str);
    }

    public boolean hasExtension(String str) {
        bt a2 = bt.a();
        return (a2 == null || !a2.b()) ? android.webkit.MimeTypeMap.getSingleton().hasExtension(str) : a2.c().k(str);
    }

    public boolean hasMimeType(String str) {
        bt a2 = bt.a();
        return (a2 == null || !a2.b()) ? android.webkit.MimeTypeMap.getSingleton().hasMimeType(str) : a2.c().i(str);
    }
}
