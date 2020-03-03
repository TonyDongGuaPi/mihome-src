package com.drew.metadata.bmp;

import android.support.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class BmpHeaderDirectory extends Directory {
    public static final int A = 21;
    @NotNull
    protected static final HashMap<Integer, String> B = new HashMap<>();
    public static final int e = -2;
    public static final int f = -1;
    public static final int g = 1;
    public static final int h = 2;
    public static final int i = 3;
    public static final int j = 4;
    public static final int k = 5;
    public static final int l = 6;
    public static final int m = 7;
    public static final int n = 8;
    public static final int o = 9;
    public static final int p = 10;
    public static final int q = 11;
    public static final int r = 12;
    public static final int s = 13;
    public static final int t = 14;
    public static final int u = 15;
    public static final int v = 16;
    public static final int w = 17;
    public static final int x = 18;
    public static final int y = 19;
    public static final int z = 20;

    @NotNull
    public String a() {
        return "BMP Header";
    }

    static {
        B.put(-2, "Bitmap type");
        B.put(-1, "Header Size");
        B.put(1, "Image Height");
        B.put(2, "Image Width");
        B.put(3, "Planes");
        B.put(4, "Bits Per Pixel");
        B.put(5, ExifInterface.TAG_COMPRESSION);
        B.put(6, "X Pixels per Meter");
        B.put(7, "Y Pixels per Meter");
        B.put(8, "Palette Colour Count");
        B.put(9, "Important Colour Count");
        B.put(10, "Rendering");
        B.put(11, "Color Encoding");
        B.put(12, "Red Mask");
        B.put(13, "Green Mask");
        B.put(14, "Blue Mask");
        B.put(15, "Alpha Mask");
        B.put(16, "Color Space Type");
        B.put(17, "Red Gamma Curve");
        B.put(18, "Green Gamma Curve");
        B.put(19, "Blue Gamma Curve");
        B.put(20, "Rendering Intent");
        B.put(21, "Linked Profile File Name");
    }

    public BmpHeaderDirectory() {
        a((TagDescriptor) new BmpHeaderDescriptor(this));
    }

    @Nullable
    public BitmapType j() {
        Integer c = c(-2);
        if (c == null) {
            return null;
        }
        return BitmapType.typeOf(c.intValue());
    }

    @Nullable
    public Compression k() {
        return Compression.typeOf(this);
    }

    @Nullable
    public RenderingHalftoningAlgorithm l() {
        Integer c = c(10);
        if (c == null) {
            return null;
        }
        return RenderingHalftoningAlgorithm.typeOf(c.intValue());
    }

    @Nullable
    public ColorEncoding m() {
        Integer c = c(11);
        if (c == null) {
            return null;
        }
        return ColorEncoding.typeOf(c.intValue());
    }

    @Nullable
    public ColorSpaceType n() {
        Long m2 = m(16);
        if (m2 == null) {
            return null;
        }
        return ColorSpaceType.typeOf(m2.longValue());
    }

    @Nullable
    public RenderingIntent o() {
        Integer c = c(20);
        if (c == null) {
            return null;
        }
        return RenderingIntent.typeOf((long) c.intValue());
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return B;
    }

    public enum BitmapType {
        BITMAP(BmpReader.f5215a),
        OS2_BITMAP_ARRAY(BmpReader.b),
        OS2_ICON(BmpReader.c),
        OS2_COLOR_ICON(BmpReader.d),
        OS2_COLOR_POINTER(BmpReader.e),
        OS2_POINTER(BmpReader.f);
        
        private final int value;

        private BitmapType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        @Nullable
        public static BitmapType typeOf(int i) {
            for (BitmapType bitmapType : values()) {
                if (bitmapType.value == i) {
                    return bitmapType;
                }
            }
            return null;
        }

        @NotNull
        public String toString() {
            switch (this) {
                case BITMAP:
                    return "Standard";
                case OS2_BITMAP_ARRAY:
                    return "Bitmap Array";
                case OS2_COLOR_ICON:
                    return "Color Icon";
                case OS2_COLOR_POINTER:
                    return "Color Pointer";
                case OS2_ICON:
                    return "Monochrome Icon";
                case OS2_POINTER:
                    return "Monochrome Pointer";
                default:
                    throw new IllegalStateException("Unimplemented bitmap type " + super.toString());
            }
        }
    }

    public enum Compression {
        BI_RGB(0),
        BI_RLE8(1),
        BI_RLE4(2),
        BI_BITFIELDS(3),
        BI_HUFFMAN_1D(3),
        BI_JPEG(4),
        BI_RLE24(4),
        BI_PNG(5),
        BI_ALPHABITFIELDS(6),
        BI_CMYK(11),
        BI_CMYKRLE8(12),
        BI_CMYKRLE4(13);
        
        private final int value;

        private Compression(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        @Nullable
        public static Compression typeOf(@NotNull BmpHeaderDirectory bmpHeaderDirectory) {
            Integer c;
            Integer c2 = bmpHeaderDirectory.c(5);
            if (c2 == null || (c = bmpHeaderDirectory.c(-1)) == null) {
                return null;
            }
            return typeOf(c2.intValue(), c.intValue());
        }

        @Nullable
        public static Compression typeOf(int i, int i2) {
            switch (i) {
                case 0:
                    return BI_RGB;
                case 1:
                    return BI_RLE8;
                case 2:
                    return BI_RLE4;
                case 3:
                    return i2 == 64 ? BI_HUFFMAN_1D : BI_BITFIELDS;
                case 4:
                    return i2 == 64 ? BI_RLE24 : BI_JPEG;
                case 5:
                    return BI_PNG;
                case 6:
                    return BI_ALPHABITFIELDS;
                default:
                    switch (i) {
                        case 11:
                            return BI_CMYK;
                        case 12:
                            return BI_CMYKRLE8;
                        case 13:
                            return BI_CMYKRLE4;
                        default:
                            return null;
                    }
            }
        }

        @NotNull
        public String toString() {
            switch (this) {
                case BI_RGB:
                    return "None";
                case BI_RLE8:
                    return "RLE 8-bit/pixel";
                case BI_RLE4:
                    return "RLE 4-bit/pixel";
                case BI_BITFIELDS:
                    return "Bit Fields";
                case BI_HUFFMAN_1D:
                    return "Huffman 1D";
                case BI_JPEG:
                    return "JPEG";
                case BI_RLE24:
                    return "RLE 24-bit/pixel";
                case BI_PNG:
                    return "PNG";
                case BI_ALPHABITFIELDS:
                    return "RGBA Bit Fields";
                case BI_CMYK:
                    return "CMYK Uncompressed";
                case BI_CMYKRLE8:
                    return "CMYK RLE-8";
                case BI_CMYKRLE4:
                    return "CMYK RLE-4";
                default:
                    throw new IllegalStateException("Unimplemented compression type " + super.toString());
            }
        }
    }

    public enum RenderingHalftoningAlgorithm {
        NONE(0),
        ERROR_DIFFUSION(1),
        PANDA(2),
        SUPER_CIRCLE(3);
        
        private final int value;

        private RenderingHalftoningAlgorithm(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        @Nullable
        public static RenderingHalftoningAlgorithm typeOf(int i) {
            for (RenderingHalftoningAlgorithm renderingHalftoningAlgorithm : values()) {
                if (renderingHalftoningAlgorithm.value == i) {
                    return renderingHalftoningAlgorithm;
                }
            }
            return null;
        }

        @NotNull
        public String toString() {
            switch (this) {
                case NONE:
                    return "No Halftoning Algorithm";
                case ERROR_DIFFUSION:
                    return "Error Diffusion Halftoning";
                case PANDA:
                    return "Processing Algorithm for Noncoded Document Acquisition";
                case SUPER_CIRCLE:
                    return "Super-circle Halftoning";
                default:
                    throw new IllegalStateException("Unimplemented rendering halftoning algorithm type " + super.toString());
            }
        }
    }

    public enum ColorEncoding {
        RGB(0);
        
        private final int value;

        private ColorEncoding(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        @Nullable
        public static ColorEncoding typeOf(int i) {
            if (i == 0) {
                return RGB;
            }
            return null;
        }
    }

    public enum ColorSpaceType {
        LCS_CALIBRATED_RGB(0),
        LCS_sRGB(1934772034),
        LCS_WINDOWS_COLOR_SPACE(1466527264),
        PROFILE_LINKED(1279872587),
        PROFILE_EMBEDDED(1296188740);
        
        private final long value;

        private ColorSpaceType(long j) {
            this.value = j;
        }

        public long getValue() {
            return this.value;
        }

        @Nullable
        public static ColorSpaceType typeOf(long j) {
            for (ColorSpaceType colorSpaceType : values()) {
                if (colorSpaceType.value == j) {
                    return colorSpaceType;
                }
            }
            return null;
        }

        @NotNull
        public String toString() {
            switch (this) {
                case LCS_CALIBRATED_RGB:
                    return "Calibrated RGB";
                case LCS_sRGB:
                    return "sRGB Color Space";
                case LCS_WINDOWS_COLOR_SPACE:
                    return "System Default Color Space, sRGB";
                case PROFILE_LINKED:
                    return "Linked Profile";
                case PROFILE_EMBEDDED:
                    return "Embedded Profile";
                default:
                    throw new IllegalStateException("Unimplemented color space type " + super.toString());
            }
        }
    }

    public enum RenderingIntent {
        LCS_GM_BUSINESS(1),
        LCS_GM_GRAPHICS(2),
        LCS_GM_IMAGES(4),
        LCS_GM_ABS_COLORIMETRIC(8);
        
        private final int value;

        private RenderingIntent(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        @Nullable
        public static RenderingIntent typeOf(long j) {
            for (RenderingIntent renderingIntent : values()) {
                if (((long) renderingIntent.value) == j) {
                    return renderingIntent;
                }
            }
            return null;
        }

        @NotNull
        public String toString() {
            switch (this) {
                case LCS_GM_BUSINESS:
                    return "Graphic, Saturation";
                case LCS_GM_GRAPHICS:
                    return "Proof, Relative Colorimetric";
                case LCS_GM_IMAGES:
                    return "Picture, Perceptual";
                case LCS_GM_ABS_COLORIMETRIC:
                    return "Match, Absolute Colorimetric";
                default:
                    throw new IllegalStateException("Unimplemented rendering intent " + super.toString());
            }
        }
    }
}
