package com.amap.location.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class d {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.io.ByteArrayOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.io.ByteArrayOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.io.ByteArrayOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.io.ByteArrayOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.io.ByteArrayOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.io.ByteArrayOutputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(byte[] r3) throws java.lang.Exception {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0029, all -> 0x0026 }
            r1.<init>()     // Catch:{ Exception -> 0x0029, all -> 0x0026 }
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch:{ Exception -> 0x0022, all -> 0x0020 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0022, all -> 0x0020 }
            r2.write(r3)     // Catch:{ Exception -> 0x001e, all -> 0x001c }
            r2.finish()     // Catch:{ Exception -> 0x001e, all -> 0x001c }
            byte[] r3 = r1.toByteArray()     // Catch:{ Exception -> 0x001e, all -> 0x001c }
            com.amap.location.common.util.e.a((java.io.Closeable) r1)
            com.amap.location.common.util.e.a((java.io.Closeable) r2)
            return r3
        L_0x001c:
            r3 = move-exception
            goto L_0x002e
        L_0x001e:
            r3 = move-exception
            goto L_0x0024
        L_0x0020:
            r3 = move-exception
            goto L_0x002f
        L_0x0022:
            r3 = move-exception
            r2 = r0
        L_0x0024:
            r0 = r1
            goto L_0x002b
        L_0x0026:
            r3 = move-exception
            r1 = r0
            goto L_0x002f
        L_0x0029:
            r3 = move-exception
            r2 = r0
        L_0x002b:
            throw r3     // Catch:{ all -> 0x002c }
        L_0x002c:
            r3 = move-exception
            r1 = r0
        L_0x002e:
            r0 = r2
        L_0x002f:
            com.amap.location.common.util.e.a((java.io.Closeable) r1)
            com.amap.location.common.util.e.a((java.io.Closeable) r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.location.common.util.d.a(byte[]):byte[]");
    }

    public static byte[] b(byte[] bArr) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream;
        InputStream inputStream2;
        InputStream gZIPInputStream;
        InputStream inputStream3;
        InputStream inputStream4 = null;
        try {
            inputStream = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream = new GZIPInputStream(inputStream);
            } catch (Exception e) {
                e = e;
                byteArrayOutputStream = null;
                inputStream4 = inputStream;
                inputStream2 = null;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    InputStream inputStream5 = inputStream2;
                    inputStream = inputStream4;
                    inputStream4 = inputStream5;
                }
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
                e.a((Closeable) inputStream);
                e.a((Closeable) inputStream4);
                e.a((Closeable) byteArrayOutputStream);
                throw th;
            }
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e2) {
                inputStream3 = inputStream;
                inputStream2 = gZIPInputStream;
                e = e2;
                byteArrayOutputStream = null;
                inputStream4 = inputStream3;
                throw e;
            } catch (Throwable th3) {
                inputStream4 = gZIPInputStream;
                th = th3;
                byteArrayOutputStream = null;
                e.a((Closeable) inputStream);
                e.a((Closeable) inputStream4);
                e.a((Closeable) byteArrayOutputStream);
                throw th;
            }
            try {
                e.a(gZIPInputStream, byteArrayOutputStream);
                byteArrayOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                e.a((Closeable) inputStream);
                e.a((Closeable) gZIPInputStream);
                e.a((Closeable) byteArrayOutputStream);
                return byteArray;
            } catch (Exception e3) {
                inputStream3 = inputStream;
                inputStream2 = gZIPInputStream;
                e = e3;
                inputStream4 = inputStream3;
                throw e;
            } catch (Throwable th4) {
                Throwable th5 = th4;
                inputStream4 = gZIPInputStream;
                th = th5;
                e.a((Closeable) inputStream);
                e.a((Closeable) inputStream4);
                e.a((Closeable) byteArrayOutputStream);
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            inputStream2 = null;
            byteArrayOutputStream = null;
            throw e;
        } catch (Throwable th6) {
            th = th6;
            inputStream = null;
            byteArrayOutputStream = null;
            e.a((Closeable) inputStream);
            e.a((Closeable) inputStream4);
            e.a((Closeable) byteArrayOutputStream);
            throw th;
        }
    }
}
