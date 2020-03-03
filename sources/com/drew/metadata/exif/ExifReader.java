package com.drew.metadata.exif;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.imaging.tiff.TiffProcessingException;
import com.drew.imaging.tiff.TiffReader;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import java.io.IOException;
import java.util.Collections;

public class ExifReader implements JpegSegmentMetadataReader {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5217a = "Exif\u0000\u0000";
    static final /* synthetic */ boolean b = (!ExifReader.class.desiredAssertionStatus());

    @NotNull
    public Iterable<JpegSegmentType> a() {
        return Collections.singletonList(JpegSegmentType.APP1);
    }

    public void a(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        if (b || jpegSegmentType == JpegSegmentType.APP1) {
            for (byte[] next : iterable) {
                if (next.length >= f5217a.length() && new String(next, 0, f5217a.length()).equals(f5217a)) {
                    a((RandomAccessReader) new ByteArrayReader(next), metadata, f5217a.length());
                }
            }
            return;
        }
        throw new AssertionError();
    }

    public void a(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata) {
        a(randomAccessReader, metadata, 0);
    }

    public void a(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata, int i) {
        a(randomAccessReader, metadata, i, (Directory) null);
    }

    public void a(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata, int i, @Nullable Directory directory) {
        ExifTiffHandler exifTiffHandler = new ExifTiffHandler(metadata, directory);
        try {
            new TiffReader().a(randomAccessReader, exifTiffHandler, i);
        } catch (TiffProcessingException e) {
            exifTiffHandler.b("Exception processing TIFF data: " + e.getMessage());
            e.printStackTrace(System.err);
        } catch (IOException e2) {
            exifTiffHandler.b("Exception processing TIFF data: " + e2.getMessage());
            e2.printStackTrace(System.err);
        }
    }
}
