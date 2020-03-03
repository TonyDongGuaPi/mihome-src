package com.drew.metadata.png;

import android.support.media.ExifInterface;
import com.drew.imaging.png.PngColorType;
import com.drew.lang.KeyValuePair;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.xiaomi.zxing.integration.android.IntentIntegrator;
import java.io.IOException;
import java.util.List;

public class PngDescriptor extends TagDescriptor<PngDirectory> {
    public PngDescriptor(@NotNull PngDirectory pngDirectory) {
        super(pngDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 4:
                return a();
            case 5:
                return b();
            case 6:
                return c();
            case 7:
                return d();
            case 9:
                return e();
            case 10:
                return f();
            case 13:
                return h();
            case 15:
                return i();
            case 18:
                return g();
            default:
                return super.a(i);
        }
    }

    @Nullable
    public String a() {
        PngColorType fromNumericValue;
        Integer c = ((PngDirectory) this.f5211a).c(4);
        if (c == null || (fromNumericValue = PngColorType.fromNumericValue(c.intValue())) == null) {
            return null;
        }
        return fromNumericValue.getDescription();
    }

    @Nullable
    public String b() {
        return a(5, "Deflate");
    }

    @Nullable
    public String c() {
        return a(6, "Adaptive");
    }

    @Nullable
    public String d() {
        return a(7, "No Interlace", "Adam7 Interlace");
    }

    @Nullable
    public String e() {
        return a(9, null, IntentIntegrator.d);
    }

    @Nullable
    public String f() {
        return a(10, "Perceptual", "Relative Colorimetric", ExifInterface.TAG_SATURATION, "Absolute Colorimetric");
    }

    @Nullable
    public String g() {
        return a(18, "Unspecified", "Metres");
    }

    @Nullable
    public String h() {
        Object u = ((PngDirectory) this.f5211a).u(13);
        if (u == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (KeyValuePair keyValuePair : (List) u) {
            if (sb.length() != 0) {
                sb.append(10);
            }
            sb.append(String.format("%s: %s", new Object[]{keyValuePair.a(), keyValuePair.b()}));
        }
        return sb.toString();
    }

    @Nullable
    public String i() {
        byte[] g = ((PngDirectory) this.f5211a).g(15);
        Integer c = ((PngDirectory) this.f5211a).c(4);
        if (g == null || c == null) {
            return null;
        }
        SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(g);
        try {
            int intValue = c.intValue();
            if (intValue != 0) {
                if (intValue != 6) {
                    switch (intValue) {
                        case 2:
                            break;
                        case 3:
                            return String.format("Palette Index %d", new Object[]{Short.valueOf(sequentialByteArrayReader.e())});
                        case 4:
                            break;
                        default:
                            return null;
                    }
                }
                return String.format("R %d, G %d, B %d", new Object[]{Integer.valueOf(sequentialByteArrayReader.g()), Integer.valueOf(sequentialByteArrayReader.g()), Integer.valueOf(sequentialByteArrayReader.g())});
            }
            return String.format("Greyscale Level %d", new Object[]{Integer.valueOf(sequentialByteArrayReader.g())});
        } catch (IOException unused) {
            return null;
        }
    }
}
