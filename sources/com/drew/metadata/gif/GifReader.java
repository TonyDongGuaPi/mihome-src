package com.drew.metadata.gif;

import com.drew.lang.ByteArrayReader;
import com.drew.lang.Charsets;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.ErrorDirectory;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import com.drew.metadata.gif.GifControlDirectory;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.xmp.XmpReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GifReader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5227a = "87a";
    private static final String b = "89a";

    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r5.a(new com.drew.metadata.ErrorDirectory("GIF did not had hasGlobalColorTable bit."));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0063, code lost:
        r5.a(new com.drew.metadata.ErrorDirectory("IOException processing GIF data"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006d, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0021 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(@com.drew.lang.annotations.NotNull com.drew.lang.SequentialReader r4, @com.drew.lang.annotations.NotNull com.drew.metadata.Metadata r5) {
        /*
            r3 = this;
            r0 = 0
            r4.a((boolean) r0)
            com.drew.metadata.gif.GifHeaderDirectory r0 = a(r4)     // Catch:{ IOException -> 0x006e }
            r5.a(r0)     // Catch:{ IOException -> 0x006e }
            boolean r1 = r0.f()
            if (r1 == 0) goto L_0x0012
            return
        L_0x0012:
            r1 = 0
            r2 = 7
            boolean r2 = r0.n(r2)     // Catch:{ MetadataException -> 0x0021 }
            if (r2 == 0) goto L_0x002b
            r2 = 4
            java.lang.Integer r0 = r0.c(r2)     // Catch:{ MetadataException -> 0x0021 }
            r1 = r0
            goto L_0x002b
        L_0x0021:
            com.drew.metadata.ErrorDirectory r0 = new com.drew.metadata.ErrorDirectory     // Catch:{ IOException -> 0x0063 }
            java.lang.String r2 = "GIF did not had hasGlobalColorTable bit."
            r0.<init>(r2)     // Catch:{ IOException -> 0x0063 }
            r5.a(r0)     // Catch:{ IOException -> 0x0063 }
        L_0x002b:
            if (r1 == 0) goto L_0x0037
            int r0 = r1.intValue()     // Catch:{ IOException -> 0x0063 }
            int r0 = r0 * 3
            long r0 = (long) r0     // Catch:{ IOException -> 0x0063 }
            r4.a((long) r0)     // Catch:{ IOException -> 0x0063 }
        L_0x0037:
            byte r0 = r4.f()     // Catch:{ IOException -> 0x0062 }
            r1 = 33
            if (r0 == r1) goto L_0x005e
            r1 = 44
            if (r0 == r1) goto L_0x0053
            r4 = 59
            if (r0 == r4) goto L_0x0052
            com.drew.metadata.ErrorDirectory r4 = new com.drew.metadata.ErrorDirectory     // Catch:{ IOException -> 0x0063 }
            java.lang.String r0 = "Unknown gif block marker found."
            r4.<init>(r0)     // Catch:{ IOException -> 0x0063 }
            r5.a(r4)     // Catch:{ IOException -> 0x0063 }
            return
        L_0x0052:
            return
        L_0x0053:
            com.drew.metadata.gif.GifImageDirectory r0 = b(r4)     // Catch:{ IOException -> 0x0063 }
            r5.a(r0)     // Catch:{ IOException -> 0x0063 }
            d(r4)     // Catch:{ IOException -> 0x0063 }
            goto L_0x0037
        L_0x005e:
            b((com.drew.lang.SequentialReader) r4, (com.drew.metadata.Metadata) r5)     // Catch:{ IOException -> 0x0063 }
            goto L_0x0037
        L_0x0062:
            return
        L_0x0063:
            com.drew.metadata.ErrorDirectory r4 = new com.drew.metadata.ErrorDirectory
            java.lang.String r0 = "IOException processing GIF data"
            r4.<init>(r0)
            r5.a(r4)
            return
        L_0x006e:
            com.drew.metadata.ErrorDirectory r4 = new com.drew.metadata.ErrorDirectory
            java.lang.String r0 = "IOException processing GIF data"
            r4.<init>(r0)
            r5.a(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.gif.GifReader.a(com.drew.lang.SequentialReader, com.drew.metadata.Metadata):void");
    }

    private static GifHeaderDirectory a(@NotNull SequentialReader sequentialReader) throws IOException {
        GifHeaderDirectory gifHeaderDirectory = new GifHeaderDirectory();
        if (!sequentialReader.b(3).equals("GIF")) {
            gifHeaderDirectory.a("Invalid GIF file signature");
            return gifHeaderDirectory;
        }
        String b2 = sequentialReader.b(3);
        if (b2.equals(f5227a) || b2.equals(b)) {
            boolean z = true;
            gifHeaderDirectory.a(1, b2);
            gifHeaderDirectory.a(2, sequentialReader.g());
            gifHeaderDirectory.a(3, sequentialReader.g());
            short e = sequentialReader.e();
            int i = 1 << ((e & 7) + 1);
            int i2 = ((e & 112) >> 4) + 1;
            boolean z2 = (e >> 7) != 0;
            gifHeaderDirectory.a(4, i);
            if (b2.equals(b)) {
                if ((e & 8) == 0) {
                    z = false;
                }
                gifHeaderDirectory.a(5, z);
            }
            gifHeaderDirectory.a(6, i2);
            gifHeaderDirectory.a(7, z2);
            gifHeaderDirectory.a(8, (int) sequentialReader.e());
            short e2 = sequentialReader.e();
            if (e2 != 0) {
                double d = (double) e2;
                Double.isNaN(d);
                gifHeaderDirectory.a(9, (float) ((d + 15.0d) / 64.0d));
            }
            return gifHeaderDirectory;
        }
        gifHeaderDirectory.a("Unexpected GIF version");
        return gifHeaderDirectory;
    }

    private static void b(SequentialReader sequentialReader, Metadata metadata) throws IOException {
        byte f = sequentialReader.f();
        short e = sequentialReader.e();
        long a2 = sequentialReader.a();
        if (f == -7) {
            metadata.a(c(sequentialReader, e));
        } else if (f != 1) {
            switch (f) {
                case -2:
                    metadata.a(b(sequentialReader, (int) e));
                    break;
                case -1:
                    a(sequentialReader, e, metadata);
                    break;
                default:
                    metadata.a(new ErrorDirectory(String.format("Unsupported GIF extension block with type 0x%02X.", new Object[]{Byte.valueOf(f)})));
                    break;
            }
        } else {
            Directory a3 = a(sequentialReader, (int) e);
            if (a3 != null) {
                metadata.a(a3);
            }
        }
        long a4 = (a2 + ((long) e)) - sequentialReader.a();
        if (a4 > 0) {
            sequentialReader.a(a4);
        }
    }

    @Nullable
    private static Directory a(SequentialReader sequentialReader, int i) throws IOException {
        if (i != 12) {
            return new ErrorDirectory(String.format("Invalid GIF plain text block size. Expected 12, got %d.", new Object[]{Integer.valueOf(i)}));
        }
        sequentialReader.a(12);
        d(sequentialReader);
        return null;
    }

    private static GifCommentDirectory b(SequentialReader sequentialReader, int i) throws IOException {
        return new GifCommentDirectory(new StringValue(d(sequentialReader, i), Charsets.d));
    }

    private static void a(SequentialReader sequentialReader, int i, Metadata metadata) throws IOException {
        if (i != 11) {
            metadata.a(new ErrorDirectory(String.format("Invalid GIF application extension block size. Expected 11, got %d.", new Object[]{Integer.valueOf(i)})));
            return;
        }
        String a2 = sequentialReader.a(i, Charsets.f5194a);
        if (a2.equals("XMP DataXMP")) {
            byte[] c = c(sequentialReader);
            new XmpReader().a(c, 0, c.length - 257, metadata, (Directory) null);
        } else if (a2.equals("ICCRGBG1012")) {
            byte[] d = d(sequentialReader, sequentialReader.b() & 255);
            if (d.length != 0) {
                new IccReader().a(new ByteArrayReader(d), metadata);
            }
        } else if (a2.equals("NETSCAPE2.0")) {
            sequentialReader.a(2);
            int g = sequentialReader.g();
            sequentialReader.a(1);
            GifAnimationDirectory gifAnimationDirectory = new GifAnimationDirectory();
            gifAnimationDirectory.a(1, g);
            metadata.a(gifAnimationDirectory);
        } else {
            d(sequentialReader);
        }
    }

    private static GifControlDirectory c(SequentialReader sequentialReader, int i) throws IOException {
        GifControlDirectory gifControlDirectory = new GifControlDirectory();
        short e = sequentialReader.e();
        gifControlDirectory.a(2, (Object) GifControlDirectory.DisposalMethod.typeOf((e >> 2) & 7));
        boolean z = false;
        gifControlDirectory.a(3, ((e & 2) >> 1) == 1);
        if ((e & 1) == 1) {
            z = true;
        }
        gifControlDirectory.a(4, z);
        gifControlDirectory.a(1, sequentialReader.g());
        gifControlDirectory.a(5, (int) sequentialReader.e());
        sequentialReader.a(1);
        return gifControlDirectory;
    }

    private static GifImageDirectory b(SequentialReader sequentialReader) throws IOException {
        GifImageDirectory gifImageDirectory = new GifImageDirectory();
        boolean z = true;
        gifImageDirectory.a(1, sequentialReader.g());
        gifImageDirectory.a(2, sequentialReader.g());
        gifImageDirectory.a(3, sequentialReader.g());
        gifImageDirectory.a(4, sequentialReader.g());
        byte b2 = sequentialReader.b();
        boolean z2 = (b2 >> 7) != 0;
        boolean z3 = (b2 & 64) != 0;
        gifImageDirectory.a(5, z2);
        gifImageDirectory.a(6, z3);
        if (z2) {
            if ((b2 & 32) == 0) {
                z = false;
            }
            gifImageDirectory.a(7, z);
            byte b3 = b2 & 7;
            gifImageDirectory.a(8, b3 + 1);
            sequentialReader.a((long) ((2 << b3) * 3));
        }
        sequentialReader.b();
        return gifImageDirectory;
    }

    private static byte[] c(SequentialReader sequentialReader) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[257];
        while (true) {
            byte b2 = sequentialReader.b();
            if (b2 == 0) {
                return byteArrayOutputStream.toByteArray();
            }
            byte b3 = b2 & 255;
            bArr[0] = b2;
            sequentialReader.a(bArr, 1, b3);
            byteArrayOutputStream.write(bArr, 0, b3 + 1);
        }
    }

    private static byte[] d(SequentialReader sequentialReader, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (i > 0) {
            byteArrayOutputStream.write(sequentialReader.a(i), 0, i);
            i = sequentialReader.b() & 255;
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static void d(SequentialReader sequentialReader) throws IOException {
        while (true) {
            short e = sequentialReader.e();
            if (e != 0) {
                sequentialReader.a((long) e);
            } else {
                return;
            }
        }
    }
}
