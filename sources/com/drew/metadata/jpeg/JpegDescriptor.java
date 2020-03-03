package com.drew.metadata.jpeg;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class JpegDescriptor extends TagDescriptor<JpegDirectory> {
    public JpegDescriptor(@NotNull JpegDirectory jpegDirectory) {
        super(jpegDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i == -3) {
            return a();
        }
        if (i == 3) {
            return b();
        }
        switch (i) {
            case 0:
                return d();
            case 1:
                return c();
            default:
                switch (i) {
                    case 6:
                        return j(0);
                    case 7:
                        return j(1);
                    case 8:
                        return j(2);
                    case 9:
                        return j(3);
                    default:
                        return super.a(i);
                }
        }
    }

    @Nullable
    public String a() {
        return a(-3, "Baseline", "Extended sequential, Huffman", "Progressive, Huffman", "Lossless, Huffman", null, "Differential sequential, Huffman", "Differential progressive, Huffman", "Differential lossless, Huffman", "Reserved for JPEG extensions", "Extended sequential, arithmetic", "Progressive, arithmetic", "Lossless, arithmetic", null, "Differential sequential, arithmetic", "Differential progressive, arithmetic", "Differential lossless, arithmetic");
    }

    @Nullable
    public String b() {
        String s = ((JpegDirectory) this.f5211a).s(3);
        if (s == null) {
            return null;
        }
        return s + " pixels";
    }

    @Nullable
    public String c() {
        String s = ((JpegDirectory) this.f5211a).s(1);
        if (s == null) {
            return null;
        }
        return s + " pixels";
    }

    @Nullable
    public String d() {
        String s = ((JpegDirectory) this.f5211a).s(0);
        if (s == null) {
            return null;
        }
        return s + " bits";
    }

    @Nullable
    public String j(int i) {
        JpegComponent y = ((JpegDirectory) this.f5211a).y(i);
        if (y == null) {
            return null;
        }
        return y.getComponentName() + " component: " + y;
    }
}
