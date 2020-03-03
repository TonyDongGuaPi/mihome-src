package com.franmontiel.persistentcookiejar.persistence;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import okhttp3.Cookie;

public class SerializableCookie implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5310a = "SerializableCookie";
    private static long c = -1;
    private static final long serialVersionUID = -8594045714036645534L;
    private transient Cookie b;

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0035 A[SYNTHETIC, Splitter:B:19:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0045 A[SYNTHETIC, Splitter:B:26:0x0045] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String encode(okhttp3.Cookie r5) {
        /*
            r4 = this;
            r4.b = r5
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream
            r5.<init>()
            r0 = 0
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x002a, all -> 0x0027 }
            r1.<init>(r5)     // Catch:{ IOException -> 0x002a, all -> 0x0027 }
            r1.writeObject(r4)     // Catch:{ IOException -> 0x0025 }
            r1.close()     // Catch:{ IOException -> 0x0014 }
            goto L_0x001c
        L_0x0014:
            r0 = move-exception
            java.lang.String r1 = f5310a
            java.lang.String r2 = "Stream not closed in encodeCookie"
            android.util.Log.d(r1, r2, r0)
        L_0x001c:
            byte[] r5 = r5.toByteArray()
            java.lang.String r5 = a((byte[]) r5)
            return r5
        L_0x0025:
            r5 = move-exception
            goto L_0x002c
        L_0x0027:
            r5 = move-exception
            r1 = r0
            goto L_0x0043
        L_0x002a:
            r5 = move-exception
            r1 = r0
        L_0x002c:
            java.lang.String r2 = f5310a     // Catch:{ all -> 0x0042 }
            java.lang.String r3 = "IOException in encodeCookie"
            android.util.Log.d(r2, r3, r5)     // Catch:{ all -> 0x0042 }
            if (r1 == 0) goto L_0x0041
            r1.close()     // Catch:{ IOException -> 0x0039 }
            goto L_0x0041
        L_0x0039:
            r5 = move-exception
            java.lang.String r1 = f5310a
            java.lang.String r2 = "Stream not closed in encodeCookie"
            android.util.Log.d(r1, r2, r5)
        L_0x0041:
            return r0
        L_0x0042:
            r5 = move-exception
        L_0x0043:
            if (r1 == 0) goto L_0x0051
            r1.close()     // Catch:{ IOException -> 0x0049 }
            goto L_0x0051
        L_0x0049:
            r0 = move-exception
            java.lang.String r1 = f5310a
            java.lang.String r2 = "Stream not closed in encodeCookie"
            android.util.Log.d(r1, r2, r0)
        L_0x0051:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.franmontiel.persistentcookiejar.persistence.SerializableCookie.encode(okhttp3.Cookie):java.lang.String");
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            byte b3 = b2 & 255;
            if (b3 < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(b3));
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0038 A[SYNTHETIC, Splitter:B:19:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0047 A[SYNTHETIC, Splitter:B:26:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0057 A[SYNTHETIC, Splitter:B:32:0x0057] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x003e=Splitter:B:23:0x003e, B:16:0x002f=Splitter:B:16:0x002f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Cookie decode(java.lang.String r5) {
        /*
            r4 = this;
            byte[] r5 = a((java.lang.String) r5)
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r5)
            r5 = 0
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x003c, ClassNotFoundException -> 0x002d, all -> 0x0029 }
            r1.<init>(r0)     // Catch:{ IOException -> 0x003c, ClassNotFoundException -> 0x002d, all -> 0x0029 }
            java.lang.Object r0 = r1.readObject()     // Catch:{ IOException -> 0x0027, ClassNotFoundException -> 0x0025 }
            com.franmontiel.persistentcookiejar.persistence.SerializableCookie r0 = (com.franmontiel.persistentcookiejar.persistence.SerializableCookie) r0     // Catch:{ IOException -> 0x0027, ClassNotFoundException -> 0x0025 }
            okhttp3.Cookie r0 = r0.b     // Catch:{ IOException -> 0x0027, ClassNotFoundException -> 0x0025 }
            r1.close()     // Catch:{ IOException -> 0x001b }
            goto L_0x0023
        L_0x001b:
            r5 = move-exception
            java.lang.String r1 = f5310a
            java.lang.String r2 = "Stream not closed in decodeCookie"
            android.util.Log.d(r1, r2, r5)
        L_0x0023:
            r5 = r0
            goto L_0x0053
        L_0x0025:
            r0 = move-exception
            goto L_0x002f
        L_0x0027:
            r0 = move-exception
            goto L_0x003e
        L_0x0029:
            r0 = move-exception
            r1 = r5
            r5 = r0
            goto L_0x0055
        L_0x002d:
            r0 = move-exception
            r1 = r5
        L_0x002f:
            java.lang.String r2 = f5310a     // Catch:{ all -> 0x0054 }
            java.lang.String r3 = "ClassNotFoundException in decodeCookie"
            android.util.Log.d(r2, r3, r0)     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0053
            r1.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x0053
        L_0x003c:
            r0 = move-exception
            r1 = r5
        L_0x003e:
            java.lang.String r2 = f5310a     // Catch:{ all -> 0x0054 }
            java.lang.String r3 = "IOException in decodeCookie"
            android.util.Log.d(r2, r3, r0)     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0053
            r1.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x0053
        L_0x004b:
            r0 = move-exception
            java.lang.String r1 = f5310a
            java.lang.String r2 = "Stream not closed in decodeCookie"
            android.util.Log.d(r1, r2, r0)
        L_0x0053:
            return r5
        L_0x0054:
            r5 = move-exception
        L_0x0055:
            if (r1 == 0) goto L_0x0063
            r1.close()     // Catch:{ IOException -> 0x005b }
            goto L_0x0063
        L_0x005b:
            r0 = move-exception
            java.lang.String r1 = f5310a
            java.lang.String r2 = "Stream not closed in decodeCookie"
            android.util.Log.d(r1, r2, r0)
        L_0x0063:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.franmontiel.persistentcookiejar.persistence.SerializableCookie.decode(java.lang.String):okhttp3.Cookie");
    }

    private static byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.b.name());
        objectOutputStream.writeObject(this.b.value());
        objectOutputStream.writeLong(this.b.persistent() ? this.b.expiresAt() : c);
        objectOutputStream.writeObject(this.b.domain());
        objectOutputStream.writeObject(this.b.path());
        objectOutputStream.writeBoolean(this.b.secure());
        objectOutputStream.writeBoolean(this.b.httpOnly());
        objectOutputStream.writeBoolean(this.b.hostOnly());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Cookie.Builder builder = new Cookie.Builder();
        builder.name((String) objectInputStream.readObject());
        builder.value((String) objectInputStream.readObject());
        long readLong = objectInputStream.readLong();
        if (readLong != c) {
            builder.expiresAt(readLong);
        }
        String str = (String) objectInputStream.readObject();
        builder.domain(str);
        builder.path((String) objectInputStream.readObject());
        if (objectInputStream.readBoolean()) {
            builder.secure();
        }
        if (objectInputStream.readBoolean()) {
            builder.httpOnly();
        }
        if (objectInputStream.readBoolean()) {
            builder.hostOnlyDomain(str);
        }
        this.b = builder.build();
    }
}
