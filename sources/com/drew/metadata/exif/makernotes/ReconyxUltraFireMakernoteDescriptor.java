package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.StringValue;
import com.drew.metadata.TagDescriptor;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReconyxUltraFireMakernoteDescriptor extends TagDescriptor<ReconyxUltraFireMakernoteDirectory> {
    public ReconyxUltraFireMakernoteDescriptor(@NotNull ReconyxUltraFireMakernoteDirectory reconyxUltraFireMakernoteDirectory) {
        super(reconyxUltraFireMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 0:
                return ((ReconyxUltraFireMakernoteDirectory) this.f5211a).s(i);
            case 10:
                return String.format("0x%08X", new Object[]{((ReconyxUltraFireMakernoteDirectory) this.f5211a).c(i)});
            case 14:
                return String.format("%d", new Object[]{((ReconyxUltraFireMakernoteDirectory) this.f5211a).c(i)});
            case 18:
                return String.format("0x%08X", new Object[]{((ReconyxUltraFireMakernoteDirectory) this.f5211a).c(i)});
            case 22:
                return String.format("%d", new Object[]{((ReconyxUltraFireMakernoteDirectory) this.f5211a).c(i)});
            case 24:
            case 31:
            case 38:
            case 45:
            case 52:
                return ((ReconyxUltraFireMakernoteDirectory) this.f5211a).s(i);
            case 53:
                int[] f = ((ReconyxUltraFireMakernoteDirectory) this.f5211a).f(i);
                if (f == null) {
                    return null;
                }
                return String.format("%d/%d", new Object[]{Integer.valueOf(f[0]), Integer.valueOf(f[1])});
            case 55:
                return String.format("%d", new Object[]{((ReconyxUltraFireMakernoteDirectory) this.f5211a).c(i)});
            case 59:
                String s = ((ReconyxUltraFireMakernoteDirectory) this.f5211a).s(i);
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                    return simpleDateFormat.format(simpleDateFormat.parse(s));
                } catch (ParseException unused) {
                    return null;
                }
            case 67:
                return a(i, "New", "Waxing Crescent", "First Quarter", "Waxing Gibbous", "Full", "Waning Gibbous", "Last Quarter", "Waning Crescent");
            case 68:
            case 70:
                return String.format("%d", new Object[]{((ReconyxUltraFireMakernoteDirectory) this.f5211a).c(i)});
            case 72:
                return a(i, "Off", "On");
            case 73:
                Double i2 = ((ReconyxUltraFireMakernoteDirectory) this.f5211a).i(i);
                DecimalFormat decimalFormat = new DecimalFormat("0.000");
                if (i2 == null) {
                    return null;
                }
                return decimalFormat.format(i2);
            case 75:
                StringValue t = ((ReconyxUltraFireMakernoteDirectory) this.f5211a).t(i);
                if (t == null) {
                    return null;
                }
                return t.toString();
            case 80:
                return ((ReconyxUltraFireMakernoteDirectory) this.f5211a).s(i);
            default:
                return super.a(i);
        }
    }
}
