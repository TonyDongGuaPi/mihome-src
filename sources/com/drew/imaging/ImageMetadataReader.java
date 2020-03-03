package com.drew.imaging;

import com.drew.imaging.avi.AviMetadataReader;
import com.drew.imaging.bmp.BmpMetadataReader;
import com.drew.imaging.eps.EpsMetadataReader;
import com.drew.imaging.gif.GifMetadataReader;
import com.drew.imaging.ico.IcoMetadataReader;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.mp4.Mp4MetadataReader;
import com.drew.imaging.pcx.PcxMetadataReader;
import com.drew.imaging.png.PngMetadataReader;
import com.drew.imaging.psd.PsdMetadataReader;
import com.drew.imaging.quicktime.QuickTimeMetadataReader;
import com.drew.imaging.raf.RafMetadataReader;
import com.drew.imaging.tiff.TiffMetadataReader;
import com.drew.imaging.wav.WavMetadataReader;
import com.drew.imaging.webp.WebpMetadataReader;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.RandomAccessStreamReader;
import com.drew.lang.StringUtil;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.file.FileTypeDirectory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageMetadataReader {
    @NotNull
    public static Metadata a(@NotNull InputStream inputStream) throws ImageProcessingException, IOException {
        return a(inputStream, -1);
    }

    @NotNull
    public static Metadata a(@NotNull InputStream inputStream, long j) throws ImageProcessingException, IOException {
        BufferedInputStream bufferedInputStream = inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
        FileType a2 = FileTypeDetector.a(bufferedInputStream);
        Metadata a3 = a(bufferedInputStream, j, a2);
        a3.a(new FileTypeDirectory(a2));
        return a3;
    }

    @NotNull
    public static Metadata a(@NotNull InputStream inputStream, long j, FileType fileType) throws IOException, ImageProcessingException {
        switch (fileType) {
            case Jpeg:
                return JpegMetadataReader.a(inputStream);
            case Tiff:
            case Arw:
            case Cr2:
            case Nef:
            case Orf:
            case Rw2:
                return TiffMetadataReader.a((RandomAccessReader) new RandomAccessStreamReader(inputStream, 2048, j));
            case Psd:
                return PsdMetadataReader.a(inputStream);
            case Png:
                return PngMetadataReader.a(inputStream);
            case Bmp:
                return BmpMetadataReader.a(inputStream);
            case Gif:
                return GifMetadataReader.a(inputStream);
            case Ico:
                return IcoMetadataReader.a(inputStream);
            case Pcx:
                return PcxMetadataReader.a(inputStream);
            case WebP:
                return WebpMetadataReader.a(inputStream);
            case Raf:
                return RafMetadataReader.a(inputStream);
            case Avi:
                return AviMetadataReader.a(inputStream);
            case Wav:
                return WavMetadataReader.a(inputStream);
            case Mov:
                return QuickTimeMetadataReader.a(inputStream);
            case Mp4:
                return Mp4MetadataReader.a(inputStream);
            case Eps:
                return EpsMetadataReader.a(inputStream);
            case Unknown:
                throw new ImageProcessingException("File format could not be determined");
            default:
                return new Metadata();
        }
    }

    /* JADX INFO: finally extract failed */
    @NotNull
    public static Metadata a(@NotNull File file) throws ImageProcessingException, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Metadata a2 = a(fileInputStream, file.length());
            fileInputStream.close();
            new FileSystemMetadataReader().a(file, a2);
            return a2;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    private ImageMetadataReader() throws Exception {
        throw new Exception("Not intended for instantiation");
    }

    public static void a(@NotNull String[] strArr) throws MetadataException, IOException {
        Metadata metadata;
        String str;
        String str2;
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(strArr));
        boolean remove = arrayList.remove("-markdown");
        boolean remove2 = arrayList.remove("-hex");
        if (arrayList.size() < 1) {
            String implementationVersion = ImageMetadataReader.class.getPackage().getImplementationVersion();
            System.out.println("metadata-extractor version " + implementationVersion);
            System.out.println();
            PrintStream printStream = System.out;
            Object[] objArr = new Object[1];
            if (implementationVersion == null) {
                implementationVersion = "a.b.c";
            }
            objArr[0] = implementationVersion;
            printStream.println(String.format("Usage: java -jar metadata-extractor-%s.jar <filename> [<filename>] [-thumb] [-markdown] [-hex]", objArr));
            System.exit(1);
        }
        for (String str3 : arrayList) {
            long nanoTime = System.nanoTime();
            File file = new File(str3);
            if (!remove && arrayList.size() > 1) {
                System.out.printf("\n***** PROCESSING: %s%n%n", new Object[]{str3});
            }
            try {
                metadata = a(file);
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.exit(1);
                metadata = null;
            }
            long nanoTime2 = System.nanoTime() - nanoTime;
            if (!remove) {
                double length = (double) file.length();
                Double.isNaN(length);
                double d = (double) nanoTime2;
                Double.isNaN(d);
                System.out.printf("Processed %.3f MB file in %.2f ms%n%n", new Object[]{Double.valueOf(length / 1048576.0d), Double.valueOf(d / 1000000.0d)});
            }
            if (remove) {
                String name = file.getName();
                String a2 = StringUtil.a(str3);
                ExifIFD0Directory exifIFD0Directory = (ExifIFD0Directory) metadata.b(ExifIFD0Directory.class);
                if (exifIFD0Directory == null) {
                    str = "";
                } else {
                    str = exifIFD0Directory.s(271);
                }
                if (exifIFD0Directory == null) {
                    str2 = "";
                } else {
                    str2 = exifIFD0Directory.s(272);
                }
                System.out.println();
                System.out.println("---");
                System.out.println();
                System.out.printf("# %s - %s%n", new Object[]{str, str2});
                System.out.println();
                System.out.printf("<a href=\"https://raw.githubusercontent.com/drewnoakes/metadata-extractor-images/master/%s\">%n", new Object[]{a2});
                System.out.printf("<img src=\"https://raw.githubusercontent.com/drewnoakes/metadata-extractor-images/master/%s\" width=\"300\"/><br/>%n", new Object[]{a2});
                System.out.println(name);
                System.out.println("</a>");
                System.out.println();
                System.out.println("Directory | Tag Id | Tag Name | Extracted Value");
                System.out.println(":--------:|-------:|----------|----------------");
            }
            for (Directory next : metadata.a()) {
                String a3 = next.a();
                for (Tag next2 : next.d()) {
                    String e2 = next2.e();
                    String c = next2.c();
                    if (c != null && c.length() > 1024) {
                        c = c.substring(0, 1024) + "...";
                    }
                    if (remove) {
                        System.out.printf("%s|0x%s|%s|%s%n", new Object[]{a3, Integer.toHexString(next2.a()), e2, c});
                    } else if (remove2) {
                        System.out.printf("[%s - %s] %s = %s%n", new Object[]{a3, next2.b(), e2, c});
                    } else {
                        System.out.printf("[%s] %s = %s%n", new Object[]{a3, e2, c});
                    }
                }
                for (String str4 : next.g()) {
                    System.err.println("ERROR: " + str4);
                }
            }
        }
    }
}
