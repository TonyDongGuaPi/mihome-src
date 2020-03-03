package com.drew.metadata.exif;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.tiff.TiffProcessingException;
import com.drew.imaging.tiff.TiffReader;
import com.drew.lang.BufferBoundsException;
import com.drew.lang.Charsets;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import com.drew.metadata.exif.makernotes.AppleMakernoteDirectory;
import com.drew.metadata.exif.makernotes.CanonMakernoteDirectory;
import com.drew.metadata.exif.makernotes.CasioType1MakernoteDirectory;
import com.drew.metadata.exif.makernotes.CasioType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.drew.metadata.exif.makernotes.KodakMakernoteDirectory;
import com.drew.metadata.exif.makernotes.KyoceraMakernoteDirectory;
import com.drew.metadata.exif.makernotes.LeicaMakernoteDirectory;
import com.drew.metadata.exif.makernotes.LeicaType5MakernoteDirectory;
import com.drew.metadata.exif.makernotes.NikonType1MakernoteDirectory;
import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusEquipmentMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusFocusInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusRawDevelopment2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusRawDevelopmentMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusRawInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PentaxMakernoteDirectory;
import com.drew.metadata.exif.makernotes.ReconyxHyperFireMakernoteDirectory;
import com.drew.metadata.exif.makernotes.ReconyxUltraFireMakernoteDirectory;
import com.drew.metadata.exif.makernotes.RicohMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SamsungType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.SanyoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SigmaMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SonyType1MakernoteDirectory;
import com.drew.metadata.exif.makernotes.SonyType6MakernoteDirectory;
import com.drew.metadata.iptc.IptcReader;
import com.drew.metadata.tiff.DirectoryTiffHandler;
import com.drew.metadata.xmp.XmpReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Set;

public class ExifTiffHandler extends DirectoryTiffHandler {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f5218a = (!ExifTiffHandler.class.desiredAssertionStatus());

    public ExifTiffHandler(@NotNull Metadata metadata, @Nullable Directory directory) {
        super(metadata, directory);
    }

    public void a(int i) throws TiffProcessingException {
        if (i != 42) {
            if (i == 85) {
                a((Class<? extends Directory>) PanasonicRawIFD0Directory.class);
                return;
            } else if (!(i == 20306 || i == 21330)) {
                throw new TiffProcessingException(String.format("Unexpected TIFF marker: 0x%X", new Object[]{Integer.valueOf(i)}));
            }
        }
        a((Class<? extends Directory>) ExifIFD0Directory.class);
    }

    public boolean b(int i) {
        if (i == 330) {
            a((Class<? extends Directory>) ExifSubIFDDirectory.class);
            return true;
        }
        if ((this.b instanceof ExifIFD0Directory) || (this.b instanceof PanasonicRawIFD0Directory)) {
            if (i == 34665) {
                a((Class<? extends Directory>) ExifSubIFDDirectory.class);
                return true;
            } else if (i == 34853) {
                a((Class<? extends Directory>) GpsDirectory.class);
                return true;
            }
        }
        if ((this.b instanceof ExifSubIFDDirectory) && i == 40965) {
            a((Class<? extends Directory>) ExifInteropDirectory.class);
            return true;
        } else if (!(this.b instanceof OlympusMakernoteDirectory)) {
            return false;
        } else {
            if (i == 8208) {
                a((Class<? extends Directory>) OlympusEquipmentMakernoteDirectory.class);
                return true;
            } else if (i == 8224) {
                a((Class<? extends Directory>) OlympusCameraSettingsMakernoteDirectory.class);
                return true;
            } else if (i == 8256) {
                a((Class<? extends Directory>) OlympusImageProcessingMakernoteDirectory.class);
                return true;
            } else if (i == 8272) {
                a((Class<? extends Directory>) OlympusFocusInfoMakernoteDirectory.class);
                return true;
            } else if (i == 12288) {
                a((Class<? extends Directory>) OlympusRawInfoMakernoteDirectory.class);
                return true;
            } else if (i != 16384) {
                switch (i) {
                    case OlympusMakernoteDirectory.bb /*8240*/:
                        a((Class<? extends Directory>) OlympusRawDevelopmentMakernoteDirectory.class);
                        return true;
                    case OlympusMakernoteDirectory.bc /*8241*/:
                        a((Class<? extends Directory>) OlympusRawDevelopment2MakernoteDirectory.class);
                        return true;
                    default:
                        return false;
                }
            } else {
                a((Class<? extends Directory>) OlympusMakernoteDirectory.class);
                return true;
            }
        }
    }

    public boolean a() {
        if ((this.b instanceof ExifIFD0Directory) || (this.b instanceof ExifImageDirectory)) {
            if (this.b.a((int) ExifDirectoryBase.F)) {
                a((Class<? extends Directory>) ExifImageDirectory.class);
            } else {
                a((Class<? extends Directory>) ExifThumbnailDirectory.class);
            }
            return true;
        } else if (this.b instanceof ExifThumbnailDirectory) {
            return true;
        } else {
            return false;
        }
    }

    @Nullable
    public Long a(int i, int i2, long j) {
        if (i2 == 13) {
            return Long.valueOf(j * 4);
        }
        return i2 == 0 ? 0L : null;
    }

    public boolean a(int i, @NotNull Set<Integer> set, int i2, @NotNull RandomAccessReader randomAccessReader, int i3, int i4) throws IOException {
        int i5 = i;
        Set<Integer> set2 = set;
        int i6 = i2;
        RandomAccessReader randomAccessReader2 = randomAccessReader;
        int i7 = i3;
        int i8 = i4;
        if (f5218a || this.b != null) {
            if (i7 == 0) {
                if (this.b.a(i3)) {
                    return false;
                }
                if (i8 == 0) {
                    return true;
                }
            }
            if (i7 == 37500 && (this.b instanceof ExifSubIFDDirectory)) {
                return a(i, set, i2, randomAccessReader);
            }
            if (i7 != 33723 || !(this.b instanceof ExifIFD0Directory)) {
                if (i7 == 700 && (this.b instanceof ExifIFD0Directory)) {
                    new XmpReader().a(randomAccessReader.d(i, i8), this.c, this.b);
                    return true;
                } else if (a(this.b, i3)) {
                    PrintIMDirectory printIMDirectory = new PrintIMDirectory();
                    printIMDirectory.a(this.b);
                    this.c.a(printIMDirectory);
                    a(printIMDirectory, i, randomAccessReader, i8);
                    return true;
                } else {
                    if (this.b instanceof OlympusMakernoteDirectory) {
                        if (i7 == 8208) {
                            a((Class<? extends Directory>) OlympusEquipmentMakernoteDirectory.class);
                            TiffReader.a(this, randomAccessReader, set, i, i2);
                            return true;
                        } else if (i7 == 8224) {
                            a((Class<? extends Directory>) OlympusCameraSettingsMakernoteDirectory.class);
                            TiffReader.a(this, randomAccessReader, set, i, i2);
                            return true;
                        } else if (i7 == 8256) {
                            a((Class<? extends Directory>) OlympusImageProcessingMakernoteDirectory.class);
                            TiffReader.a(this, randomAccessReader, set, i, i2);
                            return true;
                        } else if (i7 == 8272) {
                            a((Class<? extends Directory>) OlympusFocusInfoMakernoteDirectory.class);
                            TiffReader.a(this, randomAccessReader, set, i, i2);
                            return true;
                        } else if (i7 == 12288) {
                            a((Class<? extends Directory>) OlympusRawInfoMakernoteDirectory.class);
                            TiffReader.a(this, randomAccessReader, set, i, i2);
                            return true;
                        } else if (i7 != 16384) {
                            switch (i7) {
                                case OlympusMakernoteDirectory.bb /*8240*/:
                                    a((Class<? extends Directory>) OlympusRawDevelopmentMakernoteDirectory.class);
                                    TiffReader.a(this, randomAccessReader, set, i, i2);
                                    return true;
                                case OlympusMakernoteDirectory.bc /*8241*/:
                                    a((Class<? extends Directory>) OlympusRawDevelopment2MakernoteDirectory.class);
                                    TiffReader.a(this, randomAccessReader, set, i, i2);
                                    return true;
                            }
                        } else {
                            a((Class<? extends Directory>) OlympusMakernoteDirectory.class);
                            TiffReader.a(this, randomAccessReader, set, i, i2);
                            return true;
                        }
                    }
                    if (this.b instanceof PanasonicRawIFD0Directory) {
                        if (i7 == 19) {
                            PanasonicRawWbInfoDirectory panasonicRawWbInfoDirectory = new PanasonicRawWbInfoDirectory();
                            panasonicRawWbInfoDirectory.a(this.b);
                            this.c.a(panasonicRawWbInfoDirectory);
                            a((Directory) panasonicRawWbInfoDirectory, i, randomAccessReader, i4, (Boolean) null, 2);
                            return true;
                        } else if (i7 == 39) {
                            PanasonicRawWbInfo2Directory panasonicRawWbInfo2Directory = new PanasonicRawWbInfo2Directory();
                            panasonicRawWbInfo2Directory.a(this.b);
                            this.c.a(panasonicRawWbInfo2Directory);
                            a((Directory) panasonicRawWbInfo2Directory, i, randomAccessReader, i4, (Boolean) null, 3);
                            return true;
                        } else if (i7 == 281) {
                            PanasonicRawDistortionDirectory panasonicRawDistortionDirectory = new PanasonicRawDistortionDirectory();
                            panasonicRawDistortionDirectory.a(this.b);
                            this.c.a(panasonicRawDistortionDirectory);
                            a((Directory) panasonicRawDistortionDirectory, i, randomAccessReader, i4, (Boolean) 1, 1);
                            return true;
                        }
                    }
                    if (i7 == 46 && (this.b instanceof PanasonicRawIFD0Directory)) {
                        try {
                            for (Directory next : JpegMetadataReader.a((InputStream) new ByteArrayInputStream(randomAccessReader.c(i, i8))).a()) {
                                next.a(this.b);
                                this.c.a(next);
                            }
                            return true;
                        } catch (JpegProcessingException e) {
                            Directory directory = this.b;
                            directory.a("Error processing JpgFromRaw: " + e.getMessage());
                        } catch (IOException e2) {
                            Directory directory2 = this.b;
                            directory2.a("Error reading JpgFromRaw: " + e2.getMessage());
                        }
                    }
                    return false;
                }
            } else if (randomAccessReader.e(i) != 28) {
                return false;
            } else {
                byte[] c = randomAccessReader.c(i, i8);
                new IptcReader().a(new SequentialByteArrayReader(c), this.c, (long) c.length, this.b);
                return true;
            }
        } else {
            throw new AssertionError();
        }
    }

    private static void a(@NotNull Directory directory, int i, @NotNull RandomAccessReader randomAccessReader, int i2, Boolean bool, int i3) throws IOException {
        int i4 = 0;
        while (i4 < i2) {
            if (directory.w(i4)) {
                if (i4 >= i2 - 1 || !directory.w(i4 + 1)) {
                    if (bool.booleanValue()) {
                        short[] sArr = new short[i3];
                        for (int i5 = 0; i5 < sArr.length; i5++) {
                            sArr[i5] = randomAccessReader.g(((i4 + i5) * 2) + i);
                        }
                        directory.b(i4, (Object) sArr);
                    } else {
                        int[] iArr = new int[i3];
                        for (int i6 = 0; i6 < iArr.length; i6++) {
                            iArr[i6] = randomAccessReader.f(((i4 + i6) * 2) + i);
                        }
                        directory.b(i4, (Object) iArr);
                    }
                    i4 += i3 - 1;
                } else if (bool.booleanValue()) {
                    directory.a(i4, (Object) Short.valueOf(randomAccessReader.g((i4 * 2) + i)));
                } else {
                    directory.a(i4, (Object) Integer.valueOf(randomAccessReader.f((i4 * 2) + i)));
                }
            }
            i4++;
        }
    }

    @NotNull
    private static String a(@NotNull RandomAccessReader randomAccessReader, int i, int i2) throws IOException {
        try {
            return randomAccessReader.b(i, i2, Charsets.f5194a);
        } catch (BufferBoundsException unused) {
            return "";
        }
    }

    private boolean a(int i, @NotNull Set<Integer> set, int i2, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        String str;
        int i3 = i;
        Set<Integer> set2 = set;
        int i4 = i2;
        RandomAccessReader randomAccessReader2 = randomAccessReader;
        if (f5218a || this.b != null) {
            Directory b = this.c.b(ExifIFD0Directory.class);
            if (b == null) {
                str = null;
            } else {
                str = b.s(271);
            }
            String a2 = a(randomAccessReader2, i3, 2);
            String a3 = a(randomAccessReader2, i3, 3);
            String a4 = a(randomAccessReader2, i3, 4);
            String a5 = a(randomAccessReader2, i3, 5);
            String a6 = a(randomAccessReader2, i3, 6);
            String a7 = a(randomAccessReader2, i3, 7);
            String a8 = a(randomAccessReader2, i3, 8);
            String a9 = a(randomAccessReader2, i3, 9);
            String a10 = a(randomAccessReader2, i3, 10);
            String str2 = a9;
            String a11 = a(randomAccessReader2, i3, 12);
            boolean b2 = randomAccessReader.b();
            String str3 = a2;
            if ("OLYMP\u0000".equals(a6) || "EPSON".equals(a5) || "AGFA".equals(a4)) {
                a((Class<? extends Directory>) OlympusMakernoteDirectory.class);
                TiffReader.a(this, randomAccessReader2, set2, i3 + 8, i4);
            } else if ("OLYMPUS\u0000II".equals(a10)) {
                a((Class<? extends Directory>) OlympusMakernoteDirectory.class);
                TiffReader.a(this, randomAccessReader2, set2, i3 + 12, i3);
            } else if (str != null && str.toUpperCase().startsWith("MINOLTA")) {
                a((Class<? extends Directory>) OlympusMakernoteDirectory.class);
                TiffReader.a(this, randomAccessReader2, set2, i3, i4);
            } else if (str == null || !str.trim().toUpperCase().startsWith("NIKON")) {
                if ("SONY CAM".equals(a8) || "SONY DSC".equals(a8)) {
                    a((Class<? extends Directory>) SonyType1MakernoteDirectory.class);
                    TiffReader.a(this, randomAccessReader2, set2, i3 + 12, i4);
                } else if (str != null && str.startsWith("SONY") && !Arrays.equals(randomAccessReader2.c(i3, 2), new byte[]{1, 0})) {
                    a((Class<? extends Directory>) SonyType1MakernoteDirectory.class);
                    TiffReader.a(this, randomAccessReader2, set2, i3, i4);
                } else if ("SEMC MS\u0000\u0000\u0000\u0000\u0000".equals(a11)) {
                    randomAccessReader2.a(true);
                    a((Class<? extends Directory>) SonyType6MakernoteDirectory.class);
                    TiffReader.a(this, randomAccessReader2, set2, i3 + 20, i4);
                } else if ("SIGMA\u0000\u0000\u0000".equals(a8) || "FOVEON\u0000\u0000".equals(a8)) {
                    a((Class<? extends Directory>) SigmaMakernoteDirectory.class);
                    TiffReader.a(this, randomAccessReader2, set2, i3 + 10, i4);
                } else if ("KDK".equals(a3)) {
                    randomAccessReader2.a(a7.equals("KDK INFO"));
                    KodakMakernoteDirectory kodakMakernoteDirectory = new KodakMakernoteDirectory();
                    this.c.a(kodakMakernoteDirectory);
                    a(kodakMakernoteDirectory, i3, randomAccessReader2);
                } else if ("Canon".equalsIgnoreCase(str)) {
                    a((Class<? extends Directory>) CanonMakernoteDirectory.class);
                    TiffReader.a(this, randomAccessReader2, set2, i3, i4);
                } else if (str == null || !str.toUpperCase().startsWith("CASIO")) {
                    if ("FUJIFILM".equals(a8) || "Fujifilm".equalsIgnoreCase(str)) {
                        randomAccessReader2.a(false);
                        a((Class<? extends Directory>) FujifilmMakernoteDirectory.class);
                        TiffReader.a(this, randomAccessReader2, set2, randomAccessReader2.j(i3 + 8) + i3, i3);
                    } else if ("KYOCERA".equals(a7)) {
                        a((Class<? extends Directory>) KyoceraMakernoteDirectory.class);
                        TiffReader.a(this, randomAccessReader2, set2, i3 + 22, i4);
                    } else if ("LEICA".equals(a5)) {
                        randomAccessReader2.a(false);
                        if ("LEICA\u0000\u0001\u0000".equals(a8) || "LEICA\u0000\u0004\u0000".equals(a8) || "LEICA\u0000\u0005\u0000".equals(a8) || "LEICA\u0000\u0006\u0000".equals(a8) || "LEICA\u0000\u0007\u0000".equals(a8)) {
                            a((Class<? extends Directory>) LeicaType5MakernoteDirectory.class);
                            TiffReader.a(this, randomAccessReader2, set2, i3 + 8, i3);
                        } else if ("Leica Camera AG".equals(str)) {
                            a((Class<? extends Directory>) LeicaMakernoteDirectory.class);
                            TiffReader.a(this, randomAccessReader2, set2, i3 + 8, i4);
                        } else if (!"LEICA".equals(str)) {
                            return false;
                        } else {
                            a((Class<? extends Directory>) PanasonicMakernoteDirectory.class);
                            TiffReader.a(this, randomAccessReader2, set2, i3 + 8, i4);
                        }
                    } else if ("Panasonic\u0000\u0000\u0000".equals(a11)) {
                        a((Class<? extends Directory>) PanasonicMakernoteDirectory.class);
                        TiffReader.a(this, randomAccessReader2, set2, i3 + 12, i4);
                    } else if ("AOC\u0000".equals(a4)) {
                        a((Class<? extends Directory>) CasioType2MakernoteDirectory.class);
                        TiffReader.a(this, randomAccessReader2, set2, i3 + 6, i3);
                    } else if (str != null && (str.toUpperCase().startsWith("PENTAX") || str.toUpperCase().startsWith("ASAHI"))) {
                        a((Class<? extends Directory>) PentaxMakernoteDirectory.class);
                        TiffReader.a(this, randomAccessReader2, set2, i3, i3);
                    } else if ("SANYO\u0000\u0001\u0000".equals(a8)) {
                        a((Class<? extends Directory>) SanyoMakernoteDirectory.class);
                        TiffReader.a(this, randomAccessReader2, set2, i3 + 8, i3);
                    } else if (str == null || !str.toLowerCase().startsWith("ricoh")) {
                        if (a10.equals("Apple iOS\u0000")) {
                            boolean b3 = randomAccessReader.b();
                            randomAccessReader2.a(true);
                            a((Class<? extends Directory>) AppleMakernoteDirectory.class);
                            TiffReader.a(this, randomAccessReader2, set2, i3 + 14, i3);
                            randomAccessReader2.a(b3);
                        } else if (randomAccessReader2.f(i3) == 61697) {
                            ReconyxHyperFireMakernoteDirectory reconyxHyperFireMakernoteDirectory = new ReconyxHyperFireMakernoteDirectory();
                            this.c.a(reconyxHyperFireMakernoteDirectory);
                            a(reconyxHyperFireMakernoteDirectory, i3, randomAccessReader2);
                        } else if (str2.equalsIgnoreCase("RECONYXUF")) {
                            ReconyxUltraFireMakernoteDirectory reconyxUltraFireMakernoteDirectory = new ReconyxUltraFireMakernoteDirectory();
                            this.c.a(reconyxUltraFireMakernoteDirectory);
                            a(reconyxUltraFireMakernoteDirectory, i3, randomAccessReader2);
                        } else if (!"SAMSUNG".equals(str)) {
                            return false;
                        } else {
                            a((Class<? extends Directory>) SamsungType2MakernoteDirectory.class);
                            TiffReader.a(this, randomAccessReader2, set2, i3, i4);
                        }
                    } else if (str3.equals("Rv") || a3.equals("Rev")) {
                        return false;
                    } else {
                        if (a5.equalsIgnoreCase("Ricoh")) {
                            randomAccessReader2.a(true);
                            a((Class<? extends Directory>) RicohMakernoteDirectory.class);
                            TiffReader.a(this, randomAccessReader2, set2, i3 + 8, i3);
                        }
                    }
                } else if ("QVC\u0000\u0000\u0000".equals(a6)) {
                    a((Class<? extends Directory>) CasioType2MakernoteDirectory.class);
                    TiffReader.a(this, randomAccessReader2, set2, i3 + 6, i4);
                } else {
                    a((Class<? extends Directory>) CasioType1MakernoteDirectory.class);
                    TiffReader.a(this, randomAccessReader2, set2, i3, i4);
                }
            } else if ("Nikon".equals(a5)) {
                switch (randomAccessReader2.d(i3 + 6)) {
                    case 1:
                        a((Class<? extends Directory>) NikonType1MakernoteDirectory.class);
                        TiffReader.a(this, randomAccessReader2, set2, i3 + 8, i4);
                        break;
                    case 2:
                        a((Class<? extends Directory>) NikonType2MakernoteDirectory.class);
                        TiffReader.a(this, randomAccessReader2, set2, i3 + 18, i3 + 10);
                        break;
                    default:
                        this.b.a("Unsupported Nikon makernote data ignored.");
                        break;
                }
            } else {
                a((Class<? extends Directory>) NikonType2MakernoteDirectory.class);
                TiffReader.a(this, randomAccessReader2, set2, i3, i4);
            }
            randomAccessReader2.a(b2);
            return true;
        }
        throw new AssertionError();
    }

    private static boolean a(@NotNull Directory directory, int i) {
        if (i == 50341) {
            return true;
        }
        if (i == 3584) {
            return (directory instanceof CasioType2MakernoteDirectory) || (directory instanceof KyoceraMakernoteDirectory) || (directory instanceof NikonType2MakernoteDirectory) || (directory instanceof OlympusMakernoteDirectory) || (directory instanceof PanasonicMakernoteDirectory) || (directory instanceof PentaxMakernoteDirectory) || (directory instanceof RicohMakernoteDirectory) || (directory instanceof SanyoMakernoteDirectory) || (directory instanceof SonyType1MakernoteDirectory);
        }
        return false;
    }

    private static void a(@NotNull PrintIMDirectory printIMDirectory, int i, @NotNull RandomAccessReader randomAccessReader, int i2) throws IOException {
        Boolean bool;
        int i3;
        if (i2 == 0) {
            printIMDirectory.a("Empty PrintIM data");
        } else if (i2 <= 15) {
            printIMDirectory.a("Bad PrintIM data");
        } else {
            String b = randomAccessReader.b(i, 12, Charsets.f5194a);
            if (!b.startsWith("PrintIM")) {
                printIMDirectory.a("Invalid PrintIM header");
                return;
            }
            int i4 = i + 14;
            int f = randomAccessReader.f(i4);
            if (i2 < (f * 6) + 16) {
                Boolean valueOf = Boolean.valueOf(randomAccessReader.b());
                randomAccessReader.a(!randomAccessReader.b());
                i3 = randomAccessReader.f(i4);
                if (i2 < (i3 * 6) + 16) {
                    printIMDirectory.a("Bad PrintIM size");
                    return;
                }
                bool = valueOf;
            } else {
                bool = null;
                i3 = f;
            }
            printIMDirectory.a(0, (Object) b.substring(8, 12));
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = i + 16 + (i5 * 6);
                printIMDirectory.a(randomAccessReader.f(i6), (Object) Long.valueOf(randomAccessReader.i(i6 + 2)));
            }
            if (bool != null) {
                randomAccessReader.a(bool.booleanValue());
            }
        }
    }

    private static void a(@NotNull KodakMakernoteDirectory kodakMakernoteDirectory, int i, @NotNull RandomAccessReader randomAccessReader) {
        int i2 = i + 8;
        try {
            kodakMakernoteDirectory.a(0, randomAccessReader.a(i2, 8, Charsets.f5194a));
            kodakMakernoteDirectory.a(9, (int) randomAccessReader.d(i2 + 9));
            kodakMakernoteDirectory.a(10, (int) randomAccessReader.d(i2 + 10));
            kodakMakernoteDirectory.a(12, randomAccessReader.f(i2 + 12));
            kodakMakernoteDirectory.a(14, randomAccessReader.f(i2 + 14));
            kodakMakernoteDirectory.a(16, randomAccessReader.f(i2 + 16));
            kodakMakernoteDirectory.a(18, randomAccessReader.c(i2 + 18, 2));
            kodakMakernoteDirectory.a(20, randomAccessReader.c(i2 + 20, 4));
            kodakMakernoteDirectory.a(24, randomAccessReader.f(i2 + 24));
            kodakMakernoteDirectory.a(27, (int) randomAccessReader.d(i2 + 27));
            kodakMakernoteDirectory.a(28, (int) randomAccessReader.d(i2 + 28));
            kodakMakernoteDirectory.a(29, (int) randomAccessReader.d(i2 + 29));
            kodakMakernoteDirectory.a(30, randomAccessReader.f(i2 + 30));
            kodakMakernoteDirectory.a(32, randomAccessReader.i(i2 + 32));
            kodakMakernoteDirectory.a(36, (int) randomAccessReader.g(i2 + 36));
            kodakMakernoteDirectory.a(56, (int) randomAccessReader.d(i2 + 56));
            kodakMakernoteDirectory.a(64, (int) randomAccessReader.d(i2 + 64));
            kodakMakernoteDirectory.a(92, (int) randomAccessReader.d(i2 + 92));
            kodakMakernoteDirectory.a(93, (int) randomAccessReader.d(i2 + 93));
            kodakMakernoteDirectory.a(94, randomAccessReader.f(i2 + 94));
            kodakMakernoteDirectory.a(96, randomAccessReader.f(i2 + 96));
            kodakMakernoteDirectory.a(98, randomAccessReader.f(i2 + 98));
            kodakMakernoteDirectory.a(100, randomAccessReader.f(i2 + 100));
            kodakMakernoteDirectory.a(102, randomAccessReader.f(i2 + 102));
            kodakMakernoteDirectory.a(104, randomAccessReader.f(i2 + 104));
            kodakMakernoteDirectory.a(107, (int) randomAccessReader.e(i2 + 107));
        } catch (IOException e) {
            kodakMakernoteDirectory.a("Error processing Kodak makernote data: " + e.getMessage());
        }
    }

    private static void a(@NotNull ReconyxHyperFireMakernoteDirectory reconyxHyperFireMakernoteDirectory, int i, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        Integer num;
        reconyxHyperFireMakernoteDirectory.a(0, (Object) Integer.valueOf(randomAccessReader.f(i)));
        int i2 = i + 2;
        int f = randomAccessReader.f(i2);
        int f2 = randomAccessReader.f(i2 + 2);
        int f3 = randomAccessReader.f(i2 + 4);
        String str = String.format("%04X", new Object[]{Integer.valueOf(randomAccessReader.f(i2 + 6))}) + String.format("%04X", new Object[]{Integer.valueOf(randomAccessReader.f(i2 + 8))});
        try {
            num = Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            num = null;
        }
        if (num != null) {
            reconyxHyperFireMakernoteDirectory.a(2, String.format("%d.%d.%d.%s", new Object[]{Integer.valueOf(f), Integer.valueOf(f2), Integer.valueOf(f3), num}));
        } else {
            reconyxHyperFireMakernoteDirectory.a(2, String.format("%d.%d.%d", new Object[]{Integer.valueOf(f), Integer.valueOf(f2), Integer.valueOf(f3)}));
            reconyxHyperFireMakernoteDirectory.a("Error processing Reconyx HyperFire makernote data: build '" + str + "' is not in the expected format and will be omitted from Firmware Version.");
        }
        reconyxHyperFireMakernoteDirectory.a(12, String.valueOf((char) randomAccessReader.f(i + 12)));
        int i3 = i + 14;
        reconyxHyperFireMakernoteDirectory.a(14, new int[]{randomAccessReader.f(i3), randomAccessReader.f(i3 + 2)});
        int i4 = i + 18;
        reconyxHyperFireMakernoteDirectory.a(18, (randomAccessReader.f(i4) << 16) + randomAccessReader.f(i4 + 2));
        int i5 = i + 22;
        int f4 = randomAccessReader.f(i5);
        int f5 = randomAccessReader.f(i5 + 2);
        int f6 = randomAccessReader.f(i5 + 4);
        int f7 = randomAccessReader.f(i5 + 6);
        int f8 = randomAccessReader.f(i5 + 8);
        int f9 = randomAccessReader.f(i5 + 10);
        if (f4 < 0 || f4 >= 60 || f5 < 0 || f5 >= 60 || f6 < 0 || f6 >= 24 || f7 < 1 || f7 >= 13 || f8 < 1 || f8 >= 32 || f9 < 1 || f9 > 9999) {
            reconyxHyperFireMakernoteDirectory.a("Error processing Reconyx HyperFire makernote data: Date/Time Original " + f9 + "-" + f7 + "-" + f8 + " " + f6 + ":" + f5 + ":" + f4 + " is not a valid date/time.");
        } else {
            reconyxHyperFireMakernoteDirectory.a(22, String.format("%4d:%2d:%2d %2d:%2d:%2d", new Object[]{Integer.valueOf(f9), Integer.valueOf(f7), Integer.valueOf(f8), Integer.valueOf(f6), Integer.valueOf(f5), Integer.valueOf(f4)}));
        }
        reconyxHyperFireMakernoteDirectory.a(36, randomAccessReader.f(i + 36));
        reconyxHyperFireMakernoteDirectory.a(38, (int) randomAccessReader.g(i + 38));
        reconyxHyperFireMakernoteDirectory.a(40, (int) randomAccessReader.g(i + 40));
        reconyxHyperFireMakernoteDirectory.a(42, new StringValue(randomAccessReader.c(i + 42, 28), Charsets.f));
        reconyxHyperFireMakernoteDirectory.a(72, randomAccessReader.f(i + 72));
        reconyxHyperFireMakernoteDirectory.a(74, randomAccessReader.f(i + 74));
        reconyxHyperFireMakernoteDirectory.a(76, randomAccessReader.f(i + 76));
        reconyxHyperFireMakernoteDirectory.a(78, randomAccessReader.f(i + 78));
        reconyxHyperFireMakernoteDirectory.a(80, randomAccessReader.f(i + 80));
        reconyxHyperFireMakernoteDirectory.a(82, randomAccessReader.f(i + 82));
        double f10 = (double) randomAccessReader.f(i + 84);
        Double.isNaN(f10);
        reconyxHyperFireMakernoteDirectory.a(84, f10 / 1000.0d);
        reconyxHyperFireMakernoteDirectory.a(86, randomAccessReader.c(i + 86, 44, Charsets.f5194a));
    }

    private static void a(@NotNull ReconyxUltraFireMakernoteDirectory reconyxUltraFireMakernoteDirectory, int i, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        reconyxUltraFireMakernoteDirectory.a(0, randomAccessReader.b(i, 9, Charsets.f5194a));
        reconyxUltraFireMakernoteDirectory.a(52, randomAccessReader.b(i + 52, 1, Charsets.f5194a));
        int i2 = i + 53;
        reconyxUltraFireMakernoteDirectory.a(53, new int[]{randomAccessReader.b(i2), randomAccessReader.b(i2 + 1)});
        int i3 = i + 59;
        randomAccessReader.b(i3);
        randomAccessReader.b(i3 + 1);
        randomAccessReader.b(i3 + 2);
        randomAccessReader.b(i3 + 3);
        randomAccessReader.b(i3 + 4);
        reconyxUltraFireMakernoteDirectory.a(67, (int) randomAccessReader.b(i + 67));
        reconyxUltraFireMakernoteDirectory.a(72, (int) randomAccessReader.b(i + 72));
        reconyxUltraFireMakernoteDirectory.a(75, new StringValue(randomAccessReader.c(i + 75, 14), Charsets.f5194a));
        reconyxUltraFireMakernoteDirectory.a(80, randomAccessReader.c(i + 80, 20, Charsets.f5194a));
    }
}
