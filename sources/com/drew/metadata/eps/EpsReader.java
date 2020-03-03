package com.drew.metadata.eps;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.drew.imaging.tiff.TiffProcessingException;
import com.drew.imaging.tiff.TiffReader;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.Charsets;
import com.drew.lang.RandomAccessStreamReader;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.photoshop.PhotoshopReader;
import com.drew.metadata.photoshop.PhotoshopTiffHandler;
import com.drew.metadata.xmp.XmpReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class EpsReader {

    /* renamed from: a  reason: collision with root package name */
    private int f5216a;

    private static int a(byte b) {
        if (b >= 48 && b <= 57) {
            return b - 48;
        }
        if (b >= 65 && b <= 70) {
            return (b - Constants.TagName.TERMINAL_BACK_CONTENT) + 10;
        }
        if (b < 97 || b > 102) {
            return -1;
        }
        return (b - 97) + 10;
    }

    public void a(@NotNull InputStream inputStream, @NotNull Metadata metadata) throws IOException {
        RandomAccessStreamReader randomAccessStreamReader = new RandomAccessStreamReader(inputStream);
        EpsDirectory epsDirectory = new EpsDirectory();
        metadata.a(epsDirectory);
        int j = randomAccessStreamReader.j(0);
        if (j == -976170042) {
            randomAccessStreamReader.a(false);
            int j2 = randomAccessStreamReader.j(4);
            int j3 = randomAccessStreamReader.j(8);
            int j4 = randomAccessStreamReader.j(12);
            int j5 = randomAccessStreamReader.j(16);
            int j6 = randomAccessStreamReader.j(20);
            int j7 = randomAccessStreamReader.j(24);
            if (j7 != 0) {
                epsDirectory.a(32, j7);
                epsDirectory.a(33, j6);
                try {
                    new TiffReader().a(new ByteArrayReader(randomAccessStreamReader.c(j6, j7)), new PhotoshopTiffHandler(metadata, (Directory) null), 0);
                } catch (TiffProcessingException e) {
                    epsDirectory.a("Unable to process TIFF data: " + e.getMessage());
                }
            } else if (j5 != 0) {
                epsDirectory.a(34, j5);
                epsDirectory.a(35, j4);
            }
            a(epsDirectory, metadata, (SequentialReader) new SequentialByteArrayReader(randomAccessStreamReader.c(j2, j3)));
        } else if (j != 622940243) {
            epsDirectory.a("File type not supported.");
        } else {
            inputStream.reset();
            a(epsDirectory, metadata, (SequentialReader) new StreamReader(inputStream));
        }
    }

    private void a(@NotNull EpsDirectory epsDirectory, @NotNull Metadata metadata, @NotNull SequentialReader sequentialReader) throws IOException {
        String str;
        StringBuilder sb = new StringBuilder();
        while (true) {
            sb.setLength(0);
            while (true) {
                char b = (char) sequentialReader.b();
                if (b != 13 && b != 10) {
                    sb.append(b);
                }
            }
            if (sb.length() == 0 || sb.charAt(0) == '%') {
                int indexOf = sb.indexOf(":");
                if (indexOf != -1) {
                    str = sb.substring(0, indexOf).trim();
                    a(epsDirectory, str, sb.substring(indexOf + 1).trim());
                } else {
                    str = sb.toString().trim();
                }
                if (str.equals("%BeginPhotoshop")) {
                    a(metadata, sequentialReader);
                } else if (str.equals("%%BeginICCProfile")) {
                    b(metadata, sequentialReader);
                } else if (str.equals("%begin_xml_packet")) {
                    c(metadata, sequentialReader);
                }
            } else {
                return;
            }
        }
    }

    private void a(@NotNull EpsDirectory epsDirectory, String str, String str2) throws IOException {
        Integer num = EpsDirectory.P.get(str);
        if (num != null) {
            int intValue = num.intValue();
            if (intValue == 8) {
                a(epsDirectory, str2);
            } else if (intValue == 36) {
                int i = this.f5216a;
                epsDirectory.a(i, epsDirectory.s(this.f5216a) + " " + str2);
            } else if (!EpsDirectory.O.containsKey(num) || epsDirectory.a(num.intValue())) {
                this.f5216a = 0;
            } else {
                epsDirectory.a(num.intValue(), str2);
                this.f5216a = num.intValue();
            }
            this.f5216a = num.intValue();
        }
    }

    private static void a(@NotNull EpsDirectory epsDirectory, String str) throws IOException {
        epsDirectory.a(8, str.trim());
        String[] split = str.split(" ");
        int parseInt = Integer.parseInt(split[0]);
        int parseInt2 = Integer.parseInt(split[1]);
        int i = 3;
        int parseInt3 = Integer.parseInt(split[3]);
        if (!epsDirectory.a(28)) {
            epsDirectory.a(28, parseInt);
        }
        if (!epsDirectory.a(29)) {
            epsDirectory.a(29, parseInt2);
        }
        if (!epsDirectory.a(30)) {
            epsDirectory.a(30, parseInt3);
        }
        if (!epsDirectory.a(31)) {
            if (parseInt3 == 1) {
                i = 1;
            } else if (!(parseInt3 == 2 || parseInt3 == 3 || parseInt3 == 4)) {
                i = 0;
            }
            if (i != 0) {
                epsDirectory.a(31, i * parseInt * parseInt2);
            }
        }
    }

    private static void a(@NotNull Metadata metadata, @NotNull SequentialReader sequentialReader) throws IOException {
        byte[] a2 = a(sequentialReader);
        if (a2 != null) {
            new PhotoshopReader().a((SequentialReader) new SequentialByteArrayReader(a2), a2.length, metadata);
        }
    }

    private static void b(@NotNull Metadata metadata, @NotNull SequentialReader sequentialReader) throws IOException {
        byte[] a2 = a(sequentialReader);
        if (a2 != null) {
            new IccReader().a(new ByteArrayReader(a2), metadata);
        }
    }

    private static void c(@NotNull Metadata metadata, @NotNull SequentialReader sequentialReader) throws IOException {
        new XmpReader().a(new String(a(sequentialReader, "<?xpacket end=\"w\"?>".getBytes()), Charsets.f5194a), metadata);
    }

    private static byte[] a(@NotNull SequentialReader sequentialReader, @NotNull byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = bArr.length;
        int i = 0;
        while (i != length) {
            byte b = sequentialReader.b();
            i = b == bArr[i] ? i + 1 : 0;
            byteArrayOutputStream.write(b);
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    @com.drew.lang.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] a(@com.drew.lang.annotations.NotNull com.drew.lang.SequentialReader r13) throws java.io.IOException {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x000a:
            r6 = 10
            if (r2 != 0) goto L_0x0051
            byte r4 = r13.b()
            r7 = 2
            r8 = 1
            r9 = -1
            r10 = 32
            r11 = 13
            r12 = 0
            switch(r3) {
                case 0: goto L_0x0044;
                case 1: goto L_0x003e;
                case 2: goto L_0x002a;
                case 3: goto L_0x001e;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x000a
        L_0x001e:
            int r3 = a((byte) r4)
            if (r3 != r9) goto L_0x0025
            return r12
        L_0x0025:
            int r3 = r3 + r5
            r0.write(r3)
            goto L_0x0042
        L_0x002a:
            int r3 = a((byte) r4)
            if (r3 == r9) goto L_0x0036
            int r3 = r3 * 16
            r5 = 3
            r5 = r3
            r3 = 3
            goto L_0x000a
        L_0x0036:
            if (r4 == r11) goto L_0x003c
            if (r4 != r6) goto L_0x003b
            goto L_0x003c
        L_0x003b:
            return r12
        L_0x003c:
            r3 = 0
            goto L_0x000a
        L_0x003e:
            if (r4 == r10) goto L_0x0042
            r2 = 1
            goto L_0x000a
        L_0x0042:
            r3 = 2
            goto L_0x000a
        L_0x0044:
            if (r4 == r6) goto L_0x000a
            if (r4 == r11) goto L_0x000a
            if (r4 == r10) goto L_0x000a
            r3 = 37
            if (r4 == r3) goto L_0x004f
            return r12
        L_0x004f:
            r3 = 1
            goto L_0x000a
        L_0x0051:
            if (r4 == r6) goto L_0x0058
            byte r4 = r13.b()
            goto L_0x0051
        L_0x0058:
            byte[] r13 = r0.toByteArray()
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.eps.EpsReader.a(com.drew.lang.SequentialReader):byte[]");
    }
}
