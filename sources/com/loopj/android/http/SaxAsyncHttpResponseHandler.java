package com.loopj.android.http;

import cz.msebera.android.httpclient.Header;
import org.xml.sax.helpers.DefaultHandler;

public abstract class SaxAsyncHttpResponseHandler<T extends DefaultHandler> extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "SaxAsyncHttpRH";
    private T handler = null;

    public abstract void onFailure(int i, Header[] headerArr, T t);

    public abstract void onSuccess(int i, Header[] headerArr, T t);

    public SaxAsyncHttpResponseHandler(T t) {
        if (t != null) {
            this.handler = t;
            return;
        }
        throw new Error("null instance of <T extends DefaultHandler> passed to constructor");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
        if (r2 != null) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0059, code lost:
        if (r2 != null) goto L_0x002e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0062 A[SYNTHETIC, Splitter:B:30:0x0062] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x003c=Splitter:B:17:0x003c, B:23:0x004d=Splitter:B:23:0x004d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getResponseData(cz.msebera.android.httpclient.HttpEntity r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            if (r7 == 0) goto L_0x0066
            java.io.InputStream r7 = r7.getContent()
            if (r7 == 0) goto L_0x0066
            javax.xml.parsers.SAXParserFactory r1 = javax.xml.parsers.SAXParserFactory.newInstance()     // Catch:{ SAXException -> 0x004b, ParserConfigurationException -> 0x003a, all -> 0x0036 }
            javax.xml.parsers.SAXParser r1 = r1.newSAXParser()     // Catch:{ SAXException -> 0x004b, ParserConfigurationException -> 0x003a, all -> 0x0036 }
            org.xml.sax.XMLReader r1 = r1.getXMLReader()     // Catch:{ SAXException -> 0x004b, ParserConfigurationException -> 0x003a, all -> 0x0036 }
            T r2 = r6.handler     // Catch:{ SAXException -> 0x004b, ParserConfigurationException -> 0x003a, all -> 0x0036 }
            r1.setContentHandler(r2)     // Catch:{ SAXException -> 0x004b, ParserConfigurationException -> 0x003a, all -> 0x0036 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ SAXException -> 0x004b, ParserConfigurationException -> 0x003a, all -> 0x0036 }
            java.lang.String r3 = r6.getCharset()     // Catch:{ SAXException -> 0x004b, ParserConfigurationException -> 0x003a, all -> 0x0036 }
            r2.<init>(r7, r3)     // Catch:{ SAXException -> 0x004b, ParserConfigurationException -> 0x003a, all -> 0x0036 }
            org.xml.sax.InputSource r3 = new org.xml.sax.InputSource     // Catch:{ SAXException -> 0x0034, ParserConfigurationException -> 0x0032 }
            r3.<init>(r2)     // Catch:{ SAXException -> 0x0034, ParserConfigurationException -> 0x0032 }
            r1.parse(r3)     // Catch:{ SAXException -> 0x0034, ParserConfigurationException -> 0x0032 }
            com.loopj.android.http.AsyncHttpClient.silentCloseInputStream(r7)
        L_0x002e:
            r2.close()     // Catch:{ IOException -> 0x0066 }
            goto L_0x0066
        L_0x0032:
            r1 = move-exception
            goto L_0x003c
        L_0x0034:
            r1 = move-exception
            goto L_0x004d
        L_0x0036:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x005d
        L_0x003a:
            r1 = move-exception
            r2 = r0
        L_0x003c:
            com.loopj.android.http.LogInterface r3 = com.loopj.android.http.AsyncHttpClient.log     // Catch:{ all -> 0x005c }
            java.lang.String r4 = "SaxAsyncHttpRH"
            java.lang.String r5 = "getResponseData exception"
            r3.e(r4, r5, r1)     // Catch:{ all -> 0x005c }
            com.loopj.android.http.AsyncHttpClient.silentCloseInputStream(r7)
            if (r2 == 0) goto L_0x0066
            goto L_0x002e
        L_0x004b:
            r1 = move-exception
            r2 = r0
        L_0x004d:
            com.loopj.android.http.LogInterface r3 = com.loopj.android.http.AsyncHttpClient.log     // Catch:{ all -> 0x005c }
            java.lang.String r4 = "SaxAsyncHttpRH"
            java.lang.String r5 = "getResponseData exception"
            r3.e(r4, r5, r1)     // Catch:{ all -> 0x005c }
            com.loopj.android.http.AsyncHttpClient.silentCloseInputStream(r7)
            if (r2 == 0) goto L_0x0066
            goto L_0x002e
        L_0x005c:
            r0 = move-exception
        L_0x005d:
            com.loopj.android.http.AsyncHttpClient.silentCloseInputStream(r7)
            if (r2 == 0) goto L_0x0065
            r2.close()     // Catch:{ IOException -> 0x0065 }
        L_0x0065:
            throw r0
        L_0x0066:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loopj.android.http.SaxAsyncHttpResponseHandler.getResponseData(cz.msebera.android.httpclient.HttpEntity):byte[]");
    }

    public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        onSuccess(i, headerArr, this.handler);
    }

    public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        onFailure(i, headerArr, this.handler);
    }
}
