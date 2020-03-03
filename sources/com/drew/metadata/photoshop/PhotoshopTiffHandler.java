package com.drew.metadata.photoshop;

import com.drew.lang.ByteArrayReader;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifTiffHandler;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.xmp.XmpReader;
import java.io.IOException;
import java.util.Set;

public class PhotoshopTiffHandler extends ExifTiffHandler {
    private static final int d = 330;
    private static final int e = 437;
    private static final int f = 700;
    private static final int g = 33723;
    private static final int h = 34377;
    private static final int i = 34665;
    private static final int j = 34675;
    private static final int k = 34853;
    private static final int l = 37724;
    private static final int m = 50255;

    public PhotoshopTiffHandler(Metadata metadata, Directory directory) {
        super(metadata, directory);
    }

    public boolean a(int i2, @NotNull Set<Integer> set, int i3, @NotNull RandomAccessReader randomAccessReader, int i4, int i5) throws IOException {
        if (i4 == 700) {
            new XmpReader().a(randomAccessReader.c(i2, i5), this.c);
            return true;
        } else if (i4 == h) {
            new PhotoshopReader().a((SequentialReader) new SequentialByteArrayReader(randomAccessReader.c(i2, i5)), i5, this.c);
            return true;
        } else if (i4 != 34675) {
            return super.a(i2, set, i3, randomAccessReader, i4, i5);
        } else {
            new IccReader().a(new ByteArrayReader(randomAccessReader.c(i2, i5)), this.c);
            return true;
        }
    }
}
