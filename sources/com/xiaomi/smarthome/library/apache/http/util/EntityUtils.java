package com.xiaomi.smarthome.library.apache.http.util;

import com.xiaomi.smarthome.library.apache.http.HeaderElement;
import com.xiaomi.smarthome.library.apache.http.HttpEntity;
import com.xiaomi.smarthome.library.apache.http.NameValuePair;
import com.xiaomi.smarthome.library.apache.http.ParseException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.cybergarage.http.HTTP;

public final class EntityUtils {
    private EntityUtils() {
    }

    /* JADX INFO: finally extract failed */
    public static byte[] a(HttpEntity httpEntity) throws IOException {
        if (httpEntity != null) {
            InputStream f = httpEntity.f();
            if (f == null) {
                return new byte[0];
            }
            if (httpEntity.c() <= 2147483647L) {
                int c = (int) httpEntity.c();
                if (c < 0) {
                    c = 4096;
                }
                ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(c);
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = f.read(bArr);
                        if (read != -1) {
                            byteArrayBuffer.a(bArr, 0, read);
                        } else {
                            f.close();
                            return byteArrayBuffer.b();
                        }
                    }
                } catch (Throwable th) {
                    f.close();
                    throw th;
                }
            } else {
                throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
            }
        } else {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
    }

    public static String b(HttpEntity httpEntity) throws ParseException {
        NameValuePair a2;
        if (httpEntity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        } else if (httpEntity.d() == null) {
            return null;
        } else {
            HeaderElement[] c = httpEntity.d().c();
            if (c.length <= 0 || (a2 = c[0].a((String) HTTP.CHARSET)) == null) {
                return null;
            }
            return a2.b();
        }
    }

    /* JADX INFO: finally extract failed */
    public static String a(HttpEntity httpEntity, String str) throws IOException, ParseException {
        if (httpEntity != null) {
            InputStream f = httpEntity.f();
            if (f == null) {
                return "";
            }
            if (httpEntity.c() <= 2147483647L) {
                int c = (int) httpEntity.c();
                if (c < 0) {
                    c = 4096;
                }
                String b = b(httpEntity);
                if (b == null) {
                    b = str;
                }
                if (b == null) {
                    b = "ISO-8859-1";
                }
                InputStreamReader inputStreamReader = new InputStreamReader(f, b);
                CharArrayBuffer charArrayBuffer = new CharArrayBuffer(c);
                try {
                    char[] cArr = new char[1024];
                    while (true) {
                        int read = inputStreamReader.read(cArr);
                        if (read != -1) {
                            charArrayBuffer.a(cArr, 0, read);
                        } else {
                            inputStreamReader.close();
                            return charArrayBuffer.toString();
                        }
                    }
                } catch (Throwable th) {
                    inputStreamReader.close();
                    throw th;
                }
            } else {
                throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
            }
        } else {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
    }

    public static String c(HttpEntity httpEntity) throws IOException, ParseException {
        return a(httpEntity, (String) null);
    }
}
