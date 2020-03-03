package com.drew.imaging.png;

import com.drew.lang.ByteConvert;
import com.drew.lang.Charsets;
import com.drew.lang.DateUtil;
import com.drew.lang.KeyValuePair;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.RandomAccessStreamReader;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.StreamReader;
import com.drew.lang.StreamUtil;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.png.PngChromaticitiesDirectory;
import com.drew.metadata.png.PngDirectory;
import com.drew.metadata.xmp.XmpReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;

public class PngMetadataReader {

    /* renamed from: a  reason: collision with root package name */
    private static Set<PngChunkType> f5188a;
    private static Charset b = Charsets.c;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add(PngChunkType.f5186a);
        hashSet.add(PngChunkType.b);
        hashSet.add(PngChunkType.l);
        hashSet.add(PngChunkType.e);
        hashSet.add(PngChunkType.i);
        hashSet.add(PngChunkType.f);
        hashSet.add(PngChunkType.g);
        hashSet.add(PngChunkType.j);
        hashSet.add(PngChunkType.q);
        hashSet.add(PngChunkType.r);
        hashSet.add(PngChunkType.p);
        hashSet.add(PngChunkType.o);
        hashSet.add(PngChunkType.m);
        hashSet.add(PngChunkType.h);
        f5188a = Collections.unmodifiableSet(hashSet);
    }

    /* JADX INFO: finally extract failed */
    @NotNull
    public static Metadata a(@NotNull File file) throws PngProcessingException, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Metadata a2 = a((InputStream) fileInputStream);
            fileInputStream.close();
            new FileSystemMetadataReader().a(file, a2);
            return a2;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    @NotNull
    public static Metadata a(@NotNull InputStream inputStream) throws PngProcessingException, IOException {
        Iterable<PngChunk> a2 = new PngChunkReader().a(new StreamReader(inputStream), f5188a);
        Metadata metadata = new Metadata();
        for (PngChunk a3 : a2) {
            try {
                a(metadata, a3);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return metadata;
    }

    private static void a(@NotNull Metadata metadata, @NotNull PngChunk pngChunk) throws PngProcessingException, IOException {
        PngChunkType a2 = pngChunk.a();
        byte[] b2 = pngChunk.b();
        if (a2.equals(PngChunkType.f5186a)) {
            PngHeader pngHeader = new PngHeader(b2);
            PngDirectory pngDirectory = new PngDirectory(PngChunkType.f5186a);
            pngDirectory.a(1, pngHeader.a());
            pngDirectory.a(2, pngHeader.b());
            pngDirectory.a(3, (int) pngHeader.c());
            pngDirectory.a(4, pngHeader.d().getNumericValue());
            pngDirectory.a(5, (int) pngHeader.e() & 255);
            pngDirectory.a(6, (int) pngHeader.f());
            pngDirectory.a(7, (int) pngHeader.g());
            metadata.a(pngDirectory);
        } else if (a2.equals(PngChunkType.b)) {
            PngDirectory pngDirectory2 = new PngDirectory(PngChunkType.b);
            pngDirectory2.a(8, b2.length / 3);
            metadata.a(pngDirectory2);
        } else if (a2.equals(PngChunkType.l)) {
            PngDirectory pngDirectory3 = new PngDirectory(PngChunkType.l);
            pngDirectory3.a(9, 1);
            metadata.a(pngDirectory3);
        } else if (a2.equals(PngChunkType.i)) {
            byte b3 = b2[0];
            PngDirectory pngDirectory4 = new PngDirectory(PngChunkType.i);
            pngDirectory4.a(10, (int) b3);
            metadata.a(pngDirectory4);
        } else if (a2.equals(PngChunkType.e)) {
            PngChromaticities pngChromaticities = new PngChromaticities(b2);
            PngChromaticitiesDirectory pngChromaticitiesDirectory = new PngChromaticitiesDirectory();
            pngChromaticitiesDirectory.a(1, pngChromaticities.a());
            pngChromaticitiesDirectory.a(2, pngChromaticities.b());
            pngChromaticitiesDirectory.a(3, pngChromaticities.c());
            pngChromaticitiesDirectory.a(4, pngChromaticities.d());
            pngChromaticitiesDirectory.a(5, pngChromaticities.e());
            pngChromaticitiesDirectory.a(6, pngChromaticities.f());
            pngChromaticitiesDirectory.a(7, pngChromaticities.g());
            pngChromaticitiesDirectory.a(8, pngChromaticities.h());
            metadata.a(pngChromaticitiesDirectory);
        } else if (a2.equals(PngChunkType.f)) {
            int a3 = ByteConvert.a(b2);
            new SequentialByteArrayReader(b2).j();
            PngDirectory pngDirectory5 = new PngDirectory(PngChunkType.f);
            double d = (double) a3;
            Double.isNaN(d);
            pngDirectory5.a(11, d / 100000.0d);
            metadata.a(pngDirectory5);
        } else if (a2.equals(PngChunkType.g)) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(b2);
            byte[] c = sequentialByteArrayReader.c(80);
            PngDirectory pngDirectory6 = new PngDirectory(PngChunkType.g);
            pngDirectory6.a(12, new StringValue(c, b));
            if (sequentialByteArrayReader.f() == 0) {
                try {
                    InflaterInputStream inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(sequentialByteArrayReader.a(b2.length - ((c.length + 1) + 1))));
                    new IccReader().a((RandomAccessReader) new RandomAccessStreamReader(inflaterInputStream), metadata, (Directory) pngDirectory6);
                    inflaterInputStream.close();
                } catch (ZipException e) {
                    pngDirectory6.a(String.format("Exception decompressing PNG iCCP chunk : %s", new Object[]{e.getMessage()}));
                    metadata.a(pngDirectory6);
                }
            } else {
                pngDirectory6.a("Invalid compression method value");
            }
            metadata.a(pngDirectory6);
        } else if (a2.equals(PngChunkType.j)) {
            PngDirectory pngDirectory7 = new PngDirectory(PngChunkType.j);
            pngDirectory7.a(15, b2);
            metadata.a(pngDirectory7);
        } else if (a2.equals(PngChunkType.q)) {
            SequentialByteArrayReader sequentialByteArrayReader2 = new SequentialByteArrayReader(b2);
            StringValue d2 = sequentialByteArrayReader2.d(80, b);
            String stringValue = d2.toString();
            StringValue d3 = sequentialByteArrayReader2.d(b2.length - (d2.a().length + 1), b);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair(stringValue, d3));
            PngDirectory pngDirectory8 = new PngDirectory(PngChunkType.q);
            pngDirectory8.a(13, (Object) arrayList);
            metadata.a(pngDirectory8);
        } else {
            byte[] bArr = null;
            if (a2.equals(PngChunkType.r)) {
                SequentialByteArrayReader sequentialByteArrayReader3 = new SequentialByteArrayReader(b2);
                StringValue d4 = sequentialByteArrayReader3.d(80, b);
                String stringValue2 = d4.toString();
                byte f = sequentialByteArrayReader3.f();
                int length = b2.length - ((d4.a().length + 1) + 1);
                if (f == 0) {
                    try {
                        bArr = StreamUtil.a(new InflaterInputStream(new ByteArrayInputStream(b2, b2.length - length, length)));
                    } catch (ZipException e2) {
                        PngDirectory pngDirectory9 = new PngDirectory(PngChunkType.r);
                        pngDirectory9.a(String.format("Exception decompressing PNG zTXt chunk with keyword \"%s\": %s", new Object[]{stringValue2, e2.getMessage()}));
                        metadata.a(pngDirectory9);
                    }
                } else {
                    PngDirectory pngDirectory10 = new PngDirectory(PngChunkType.r);
                    pngDirectory10.a("Invalid compression method value");
                    metadata.a(pngDirectory10);
                }
                if (bArr == null) {
                    return;
                }
                if (stringValue2.equals("XML:com.adobe.xmp")) {
                    new XmpReader().a(bArr, metadata);
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new KeyValuePair(stringValue2, new StringValue(bArr, b)));
                PngDirectory pngDirectory11 = new PngDirectory(PngChunkType.r);
                pngDirectory11.a(13, (Object) arrayList2);
                metadata.a(pngDirectory11);
            } else if (a2.equals(PngChunkType.p)) {
                SequentialByteArrayReader sequentialByteArrayReader4 = new SequentialByteArrayReader(b2);
                StringValue d5 = sequentialByteArrayReader4.d(80, b);
                String stringValue3 = d5.toString();
                byte f2 = sequentialByteArrayReader4.f();
                byte f3 = sequentialByteArrayReader4.f();
                int length2 = b2.length - (((((((d5.a().length + 1) + 1) + 1) + sequentialByteArrayReader4.c(b2.length).length) + 1) + sequentialByteArrayReader4.c(b2.length).length) + 1);
                if (f2 == 0) {
                    bArr = sequentialByteArrayReader4.c(length2);
                } else if (f2 != 1) {
                    PngDirectory pngDirectory12 = new PngDirectory(PngChunkType.p);
                    pngDirectory12.a("Invalid compression flag value");
                    metadata.a(pngDirectory12);
                } else if (f3 == 0) {
                    try {
                        bArr = StreamUtil.a(new InflaterInputStream(new ByteArrayInputStream(b2, b2.length - length2, length2)));
                    } catch (ZipException e3) {
                        PngDirectory pngDirectory13 = new PngDirectory(PngChunkType.p);
                        pngDirectory13.a(String.format("Exception decompressing PNG iTXt chunk with keyword \"%s\": %s", new Object[]{stringValue3, e3.getMessage()}));
                        metadata.a(pngDirectory13);
                    }
                } else {
                    PngDirectory pngDirectory14 = new PngDirectory(PngChunkType.p);
                    pngDirectory14.a("Invalid compression method value");
                    metadata.a(pngDirectory14);
                }
                if (bArr == null) {
                    return;
                }
                if (stringValue3.equals("XML:com.adobe.xmp")) {
                    new XmpReader().a(bArr, metadata);
                    return;
                }
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(new KeyValuePair(stringValue3, new StringValue(bArr, b)));
                PngDirectory pngDirectory15 = new PngDirectory(PngChunkType.p);
                pngDirectory15.a(13, (Object) arrayList3);
                metadata.a(pngDirectory15);
            } else if (a2.equals(PngChunkType.o)) {
                SequentialByteArrayReader sequentialByteArrayReader5 = new SequentialByteArrayReader(b2);
                int g = sequentialByteArrayReader5.g();
                short e4 = sequentialByteArrayReader5.e();
                short e5 = sequentialByteArrayReader5.e();
                short e6 = sequentialByteArrayReader5.e();
                short e7 = sequentialByteArrayReader5.e();
                short e8 = sequentialByteArrayReader5.e();
                PngDirectory pngDirectory16 = new PngDirectory(PngChunkType.o);
                if (!DateUtil.a(g, e4 - 1, e5) || !DateUtil.b(e6, e7, e8)) {
                    pngDirectory16.a(String.format("PNG tIME data describes an invalid date/time: year=%d month=%d day=%d hour=%d minute=%d second=%d", new Object[]{Integer.valueOf(g), Integer.valueOf(e4), Integer.valueOf(e5), Integer.valueOf(e6), Integer.valueOf(e7), Integer.valueOf(e8)}));
                } else {
                    pngDirectory16.a(14, String.format("%04d:%02d:%02d %02d:%02d:%02d", new Object[]{Integer.valueOf(g), Integer.valueOf(e4), Integer.valueOf(e5), Integer.valueOf(e6), Integer.valueOf(e7), Integer.valueOf(e8)}));
                }
                metadata.a(pngDirectory16);
            } else if (a2.equals(PngChunkType.m)) {
                SequentialByteArrayReader sequentialByteArrayReader6 = new SequentialByteArrayReader(b2);
                int j = sequentialByteArrayReader6.j();
                int j2 = sequentialByteArrayReader6.j();
                byte f4 = sequentialByteArrayReader6.f();
                PngDirectory pngDirectory17 = new PngDirectory(PngChunkType.m);
                pngDirectory17.a(16, j);
                pngDirectory17.a(17, j2);
                pngDirectory17.a(18, (int) f4);
                metadata.a(pngDirectory17);
            } else if (a2.equals(PngChunkType.h)) {
                PngDirectory pngDirectory18 = new PngDirectory(PngChunkType.h);
                pngDirectory18.a(19, b2);
                metadata.a(pngDirectory18);
            }
        }
    }
}
