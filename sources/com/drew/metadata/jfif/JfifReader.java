package com.drew.metadata.jfif;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataReader;
import java.io.IOException;
import java.util.Collections;

public class JfifReader implements JpegSegmentMetadataReader, MetadataReader {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5231a = "JFIF";

    @NotNull
    public Iterable<JpegSegmentType> a() {
        return Collections.singletonList(JpegSegmentType.APP0);
    }

    public void a(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        for (byte[] next : iterable) {
            if (next.length >= f5231a.length() && f5231a.equals(new String(next, 0, f5231a.length()))) {
                a(new ByteArrayReader(next), metadata);
            }
        }
    }

    public void a(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata) {
        JfifDirectory jfifDirectory = new JfifDirectory();
        metadata.a(jfifDirectory);
        try {
            jfifDirectory.a(5, randomAccessReader.f(5));
            jfifDirectory.a(7, (int) randomAccessReader.d(7));
            jfifDirectory.a(8, randomAccessReader.f(8));
            jfifDirectory.a(10, randomAccessReader.f(10));
            jfifDirectory.a(12, (int) randomAccessReader.d(12));
            jfifDirectory.a(13, (int) randomAccessReader.d(13));
        } catch (IOException e) {
            jfifDirectory.a(e.getMessage());
        }
    }
}
