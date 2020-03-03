package com.drew.metadata.photoshop;

import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.iptc.IptcReader;
import com.drew.metadata.xmp.XmpReader;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class PhotoshopReader implements JpegSegmentMetadataReader {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private static final String f5263a = "Photoshop 3.0";

    @NotNull
    public Iterable<JpegSegmentType> a() {
        return Collections.singletonList(JpegSegmentType.APPD);
    }

    public void a(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        int length = f5263a.length();
        for (byte[] next : iterable) {
            int i = length + 1;
            if (next.length >= i && f5263a.equals(new String(next, 0, length))) {
                a((SequentialReader) new SequentialByteArrayReader(next, i), (next.length - length) - 1, metadata);
            }
        }
    }

    public void a(@NotNull SequentialReader sequentialReader, int i, @NotNull Metadata metadata) {
        int i2;
        SequentialReader sequentialReader2 = sequentialReader;
        int i3 = i;
        Metadata metadata2 = metadata;
        PhotoshopDirectory photoshopDirectory = new PhotoshopDirectory();
        metadata2.a(photoshopDirectory);
        int i4 = 0;
        int i5 = 0;
        while (i4 < i3) {
            try {
                String b = sequentialReader2.b(4);
                int g = sequentialReader.g();
                short e = sequentialReader.e();
                int i6 = i4 + 4 + 2 + 1;
                if (e < 0 || (i2 = e + i6) > i3) {
                    throw new ImageProcessingException("Invalid string length");
                }
                StringBuilder sb = new StringBuilder();
                short s = (short) i2;
                while (i6 < s) {
                    sb.append((char) sequentialReader.e());
                    i6++;
                }
                if (i6 % 2 != 0) {
                    sequentialReader2.a(1);
                    i6++;
                }
                int j = sequentialReader.j();
                byte[] a2 = sequentialReader2.a(j);
                int i7 = i6 + 4 + j;
                if (i7 % 2 != 0) {
                    sequentialReader2.a(1);
                    i7++;
                }
                int i8 = i7;
                if (b.equals("8BIM")) {
                    if (g == 1028) {
                        new IptcReader().a(new SequentialByteArrayReader(a2), metadata, (long) a2.length, photoshopDirectory);
                    } else if (g == 1039) {
                        new IccReader().a((RandomAccessReader) new ByteArrayReader(a2), metadata2, (Directory) photoshopDirectory);
                    } else {
                        if (g != 1058) {
                            if (g != 1059) {
                                if (g == 1060) {
                                    new XmpReader().a(a2, metadata2, (Directory) photoshopDirectory);
                                } else if (g < 2000 || g > 2998) {
                                    photoshopDirectory.a(g, a2);
                                } else {
                                    i5++;
                                    byte[] copyOf = Arrays.copyOf(a2, a2.length + sb.length() + 1);
                                    for (int length = (copyOf.length - sb.length()) - 1; length < copyOf.length; length++) {
                                        if (length % (((copyOf.length - sb.length()) - 1) + sb.length()) == 0) {
                                            copyOf[length] = (byte) sb.length();
                                        } else {
                                            copyOf[length] = (byte) sb.charAt(length - ((copyOf.length - sb.length()) - 1));
                                        }
                                    }
                                    HashMap<Integer, String> hashMap = PhotoshopDirectory.aM;
                                    int i9 = i5 + 1999;
                                    Integer valueOf = Integer.valueOf(i9);
                                    hashMap.put(valueOf, "Path Info " + i5);
                                    photoshopDirectory.a(i9, copyOf);
                                }
                            }
                        }
                        new ExifReader().a(new ByteArrayReader(a2), metadata2, 0, photoshopDirectory);
                    }
                    if (g >= 4000 && g <= 4999) {
                        PhotoshopDirectory.aM.put(Integer.valueOf(g), String.format("Plug-in %d Data", new Object[]{Integer.valueOf(g + LoginErrorCode.D + 1)}));
                    }
                }
                i4 = i8;
            } catch (Exception e2) {
                photoshopDirectory.a(e2.getMessage());
                return;
            }
        }
    }
}
