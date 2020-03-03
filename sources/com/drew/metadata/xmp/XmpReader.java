package com.drew.metadata.xmp;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPIterator;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.options.IteratorOptions;
import com.adobe.xmp.properties.XMPPropertyInfo;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import java.io.IOException;
import java.util.Collections;

public class XmpReader implements JpegSegmentMetadataReader {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private static final String f5269a = "http://ns.adobe.com/xap/1.0/\u0000";
    @NotNull
    private static final String b = "http://ns.adobe.com/xmp/extension/\u0000";
    @NotNull
    private static final String c = "http://ns.adobe.com/xmp/note/";
    @NotNull
    private static final String d = "xmpNote:HasExtendedXMP";
    private static final int e = 32;
    private static final int f = 4;

    @NotNull
    public Iterable<JpegSegmentType> a() {
        return Collections.singletonList(JpegSegmentType.APP1);
    }

    public void a(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        int length = f5269a.length();
        int length2 = b.length();
        byte[] bArr = null;
        String str = null;
        for (byte[] next : iterable) {
            if (next.length >= length && (f5269a.equalsIgnoreCase(new String(next, 0, length)) || "XMP".equalsIgnoreCase(new String(next, 0, 3)))) {
                byte[] bArr2 = new byte[(next.length - length)];
                System.arraycopy(next, length, bArr2, 0, bArr2.length);
                a(bArr2, metadata);
                str = a(metadata);
            } else if (str != null && next.length >= length2 && b.equalsIgnoreCase(new String(next, 0, length2))) {
                bArr = a(metadata, next, str, bArr);
            }
        }
        if (bArr != null) {
            a(bArr, metadata);
        }
    }

    public void a(@NotNull byte[] bArr, @NotNull Metadata metadata) {
        a(bArr, metadata, (Directory) null);
    }

    public void a(@NotNull byte[] bArr, @NotNull Metadata metadata, @Nullable Directory directory) {
        a(bArr, 0, bArr.length, metadata, directory);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(@com.drew.lang.annotations.NotNull byte[] r2, int r3, int r4, @com.drew.lang.annotations.NotNull com.drew.metadata.Metadata r5, @com.drew.lang.annotations.Nullable com.drew.metadata.Directory r6) {
        /*
            r1 = this;
            com.drew.metadata.xmp.XmpDirectory r0 = new com.drew.metadata.xmp.XmpDirectory
            r0.<init>()
            if (r6 == 0) goto L_0x000a
            r0.a((com.drew.metadata.Directory) r6)
        L_0x000a:
            if (r3 != 0) goto L_0x0016
            int r6 = r2.length     // Catch:{ XMPException -> 0x0014 }
            if (r4 != r6) goto L_0x0016
            com.adobe.xmp.XMPMeta r2 = com.adobe.xmp.XMPMetaFactory.a((byte[]) r2)     // Catch:{ XMPException -> 0x0014 }
            goto L_0x0023
        L_0x0014:
            r2 = move-exception
            goto L_0x0027
        L_0x0016:
            com.adobe.xmp.impl.ByteBuffer r6 = new com.adobe.xmp.impl.ByteBuffer     // Catch:{ XMPException -> 0x0014 }
            r6.<init>(r2, r3, r4)     // Catch:{ XMPException -> 0x0014 }
            java.io.InputStream r2 = r6.a()     // Catch:{ XMPException -> 0x0014 }
            com.adobe.xmp.XMPMeta r2 = com.adobe.xmp.XMPMetaFactory.a((java.io.InputStream) r2)     // Catch:{ XMPException -> 0x0014 }
        L_0x0023:
            r0.a(r2)     // Catch:{ XMPException -> 0x0014 }
            goto L_0x003f
        L_0x0027:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Error processing XMP data: "
            r3.append(r4)
            java.lang.String r2 = r2.getMessage()
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r0.a((java.lang.String) r2)
        L_0x003f:
            boolean r2 = r0.c()
            if (r2 != 0) goto L_0x0048
            r5.a(r0)
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.xmp.XmpReader.a(byte[], int, int, com.drew.metadata.Metadata, com.drew.metadata.Directory):void");
    }

    public void a(@NotNull String str, @NotNull Metadata metadata) {
        a(str, metadata, (Directory) null);
    }

    public void a(@NotNull StringValue stringValue, @NotNull Metadata metadata) {
        a(stringValue.a(), metadata, (Directory) null);
    }

    public void a(@NotNull String str, @NotNull Metadata metadata, @Nullable Directory directory) {
        XmpDirectory xmpDirectory = new XmpDirectory();
        if (directory != null) {
            xmpDirectory.a(directory);
        }
        try {
            xmpDirectory.a(XMPMetaFactory.a(str));
        } catch (XMPException e2) {
            xmpDirectory.a("Error processing XMP data: " + e2.getMessage());
        }
        if (!xmpDirectory.c()) {
            metadata.a(xmpDirectory);
        }
    }

    @Nullable
    private static String a(@NotNull Metadata metadata) {
        for (XmpDirectory k : metadata.a(XmpDirectory.class)) {
            try {
                XMPIterator a2 = k.k().a("http://ns.adobe.com/xmp/note/", (String) null, (IteratorOptions) null);
                if (a2 != null) {
                    while (a2.hasNext()) {
                        XMPPropertyInfo xMPPropertyInfo = (XMPPropertyInfo) a2.next();
                        if (d.equals(xMPPropertyInfo.b())) {
                            return xMPPropertyInfo.c();
                        }
                    }
                    continue;
                }
            } catch (XMPException unused) {
            }
        }
        return null;
    }

    @Nullable
    private static byte[] a(@NotNull Metadata metadata, @NotNull byte[] bArr, @NotNull String str, @Nullable byte[] bArr2) {
        int length = b.length();
        int length2 = bArr.length;
        int i = length + 32 + 4 + 4;
        if (length2 >= i) {
            try {
                SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
                sequentialByteArrayReader.a((long) length);
                if (str.equals(sequentialByteArrayReader.b(32))) {
                    int i2 = (int) sequentialByteArrayReader.i();
                    int i3 = (int) sequentialByteArrayReader.i();
                    if (bArr2 == null) {
                        bArr2 = new byte[i2];
                    }
                    if (bArr2.length == i2) {
                        System.arraycopy(bArr, i, bArr2, i3, length2 - i);
                    } else {
                        XmpDirectory xmpDirectory = new XmpDirectory();
                        xmpDirectory.a(String.format("Inconsistent length for the Extended XMP buffer: %d instead of %d", new Object[]{Integer.valueOf(i2), Integer.valueOf(bArr2.length)}));
                        metadata.a(xmpDirectory);
                    }
                }
            } catch (IOException e2) {
                XmpDirectory xmpDirectory2 = new XmpDirectory();
                xmpDirectory2.a(e2.getMessage());
                metadata.a(xmpDirectory2);
            }
        }
        return bArr2;
    }
}
