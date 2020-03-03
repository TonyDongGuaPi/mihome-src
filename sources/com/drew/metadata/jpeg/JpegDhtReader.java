package com.drew.metadata.jpeg;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.jpeg.HuffmanTablesDirectory;
import java.io.IOException;
import java.util.Collections;

public class JpegDhtReader implements JpegSegmentMetadataReader {
    @NotNull
    public Iterable<JpegSegmentType> a() {
        return Collections.singletonList(JpegSegmentType.DHT);
    }

    public void a(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        for (byte[] sequentialByteArrayReader : iterable) {
            a((SequentialReader) new SequentialByteArrayReader(sequentialByteArrayReader), metadata);
        }
    }

    public void a(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        HuffmanTablesDirectory huffmanTablesDirectory = (HuffmanTablesDirectory) metadata.b(HuffmanTablesDirectory.class);
        if (huffmanTablesDirectory == null) {
            huffmanTablesDirectory = new HuffmanTablesDirectory();
            metadata.a(huffmanTablesDirectory);
        }
        while (sequentialReader.c() > 0) {
            try {
                byte b = sequentialReader.b();
                HuffmanTablesDirectory.HuffmanTable.HuffmanTableClass typeOf = HuffmanTablesDirectory.HuffmanTable.HuffmanTableClass.typeOf((b & 240) >> 4);
                byte b2 = b & 15;
                byte[] a2 = a(sequentialReader, 16);
                int i = 0;
                for (byte b3 : a2) {
                    i += b3 & 255;
                }
                huffmanTablesDirectory.k().add(new HuffmanTablesDirectory.HuffmanTable(typeOf, b2, a2, a(sequentialReader, i)));
            } catch (IOException e) {
                huffmanTablesDirectory.a(e.getMessage());
            }
        }
        huffmanTablesDirectory.a(1, huffmanTablesDirectory.k().size());
    }

    private byte[] a(@NotNull SequentialReader sequentialReader, int i) throws IOException {
        byte b;
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            byte b2 = sequentialReader.b();
            if ((b2 & 255) != 255 || (b = sequentialReader.b()) == 0) {
                bArr[i2] = b2;
                i2++;
            } else {
                throw new IOException("Marker " + JpegSegmentType.fromByte(b) + " found inside DHT segment");
            }
        }
        return bArr;
    }
}
