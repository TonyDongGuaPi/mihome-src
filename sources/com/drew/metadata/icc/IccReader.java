package com.drew.metadata.icc;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.DateUtil;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataReader;
import java.io.IOException;
import java.util.Collections;

public class IccReader implements JpegSegmentMetadataReader, MetadataReader {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5228a = "ICC_PROFILE";

    @NotNull
    public Iterable<JpegSegmentType> a() {
        return Collections.singletonList(JpegSegmentType.APP2);
    }

    public void a(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        int length = f5228a.length();
        byte[] bArr = null;
        for (byte[] next : iterable) {
            if (next.length >= length && f5228a.equalsIgnoreCase(new String(next, 0, length))) {
                if (bArr == null) {
                    bArr = new byte[(next.length - 14)];
                    System.arraycopy(next, 14, bArr, 0, next.length - 14);
                } else {
                    byte[] bArr2 = new byte[((bArr.length + next.length) - 14)];
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    System.arraycopy(next, 14, bArr2, bArr.length, next.length - 14);
                    bArr = bArr2;
                }
            }
        }
        if (bArr != null) {
            a(new ByteArrayReader(bArr), metadata);
        }
    }

    public void a(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata) {
        a(randomAccessReader, metadata, (Directory) null);
    }

    public void a(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata, @Nullable Directory directory) {
        IccDirectory iccDirectory = new IccDirectory();
        if (directory != null) {
            iccDirectory.a(directory);
        }
        try {
            iccDirectory.a(0, randomAccessReader.j(0));
            a((Directory) iccDirectory, 4, randomAccessReader);
            b(iccDirectory, 8, randomAccessReader);
            a((Directory) iccDirectory, 12, randomAccessReader);
            a((Directory) iccDirectory, 16, randomAccessReader);
            a((Directory) iccDirectory, 20, randomAccessReader);
            a(iccDirectory, 24, randomAccessReader);
            a((Directory) iccDirectory, 36, randomAccessReader);
            a((Directory) iccDirectory, 40, randomAccessReader);
            b(iccDirectory, 44, randomAccessReader);
            a((Directory) iccDirectory, 48, randomAccessReader);
            int j = randomAccessReader.j(52);
            if (j != 0) {
                if (j <= 538976288) {
                    iccDirectory.a(52, j);
                } else {
                    iccDirectory.a(52, a(j));
                }
            }
            b(iccDirectory, 64, randomAccessReader);
            c(iccDirectory, 56, randomAccessReader);
            iccDirectory.a(68, (Object) new float[]{randomAccessReader.l(68), randomAccessReader.l(72), randomAccessReader.l(76)});
            int j2 = randomAccessReader.j(128);
            iccDirectory.a(128, j2);
            for (int i = 0; i < j2; i++) {
                int i2 = (i * 12) + 132;
                iccDirectory.a(randomAccessReader.j(i2), randomAccessReader.c(randomAccessReader.j(i2 + 4), randomAccessReader.j(i2 + 8)));
            }
        } catch (IOException e) {
            iccDirectory.a("Exception reading ICC profile: " + e.getMessage());
        }
        metadata.a(iccDirectory);
    }

    private void a(@NotNull Directory directory, int i, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        int j = randomAccessReader.j(i);
        if (j != 0) {
            directory.a(i, a(j));
        }
    }

    private void b(@NotNull Directory directory, int i, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        int j = randomAccessReader.j(i);
        if (j != 0) {
            directory.a(i, j);
        }
    }

    private void c(@NotNull Directory directory, int i, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        long k = randomAccessReader.k(i);
        if (k != 0) {
            directory.a(i, k);
        }
    }

    private void a(@NotNull IccDirectory iccDirectory, int i, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        IccDirectory iccDirectory2 = iccDirectory;
        int i2 = i;
        RandomAccessReader randomAccessReader2 = randomAccessReader;
        int f = randomAccessReader2.f(i2);
        int f2 = randomAccessReader2.f(i2 + 2);
        int f3 = randomAccessReader2.f(i2 + 4);
        int f4 = randomAccessReader2.f(i2 + 6);
        int f5 = randomAccessReader2.f(i2 + 8);
        int f6 = randomAccessReader2.f(i2 + 10);
        if (!DateUtil.a(f, f2 - 1, f3) || !DateUtil.b(f4, f5, f6)) {
            iccDirectory2.a(String.format("ICC data describes an invalid date/time: year=%d month=%d day=%d hour=%d minute=%d second=%d", new Object[]{Integer.valueOf(f), Integer.valueOf(f2), Integer.valueOf(f3), Integer.valueOf(f4), Integer.valueOf(f5), Integer.valueOf(f6)}));
            return;
        }
        iccDirectory2.a(i2, String.format("%04d:%02d:%02d %02d:%02d:%02d", new Object[]{Integer.valueOf(f), Integer.valueOf(f2), Integer.valueOf(f3), Integer.valueOf(f4), Integer.valueOf(f5), Integer.valueOf(f6)}));
    }

    @NotNull
    public static String a(int i) {
        return new String(new byte[]{(byte) ((-16777216 & i) >> 24), (byte) ((16711680 & i) >> 16), (byte) ((65280 & i) >> 8), (byte) (i & 255)});
    }
}
