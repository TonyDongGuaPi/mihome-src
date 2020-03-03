package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.StringValue;
import com.drew.metadata.TagDescriptor;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReconyxHyperFireMakernoteDescriptor extends TagDescriptor<ReconyxHyperFireMakernoteDirectory> {
    public ReconyxHyperFireMakernoteDescriptor(@NotNull ReconyxHyperFireMakernoteDirectory reconyxHyperFireMakernoteDirectory) {
        super(reconyxHyperFireMakernoteDirectory);
    }

    @Nullable
    public String a(int i) {
        switch (i) {
            case 0:
                return String.format("%d", new Object[]{((ReconyxHyperFireMakernoteDirectory) this.f5211a).c(i)});
            case 2:
                return ((ReconyxHyperFireMakernoteDirectory) this.f5211a).s(i);
            case 12:
                return ((ReconyxHyperFireMakernoteDirectory) this.f5211a).s(i);
            case 14:
                int[] f = ((ReconyxHyperFireMakernoteDirectory) this.f5211a).f(i);
                if (f == null) {
                    return null;
                }
                return String.format("%d/%d", new Object[]{Integer.valueOf(f[0]), Integer.valueOf(f[1])});
            case 18:
                return String.format("%d", new Object[]{((ReconyxHyperFireMakernoteDirectory) this.f5211a).c(i)});
            case 22:
                String s = ((ReconyxHyperFireMakernoteDirectory) this.f5211a).s(i);
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                    return simpleDateFormat.format(simpleDateFormat.parse(s));
                } catch (ParseException unused) {
                    return null;
                }
            case 36:
                return a(i, "New", "Waxing Crescent", "First Quarter", "Waxing Gibbous", "Full", "Waning Gibbous", "Last Quarter", "Waning Crescent");
            case 38:
            case 40:
                return String.format("%d", new Object[]{((ReconyxHyperFireMakernoteDirectory) this.f5211a).c(i)});
            case 42:
                StringValue t = ((ReconyxHyperFireMakernoteDirectory) this.f5211a).t(i);
                if (t == null) {
                    return null;
                }
                return t.toString();
            case 72:
            case 74:
            case 76:
            case 78:
                return String.format("%d", new Object[]{((ReconyxHyperFireMakernoteDirectory) this.f5211a).c(i)});
            case 80:
                return a(i, "Off", "On");
            case 82:
                return String.format("%d", new Object[]{((ReconyxHyperFireMakernoteDirectory) this.f5211a).c(i)});
            case 84:
                Double i2 = ((ReconyxHyperFireMakernoteDirectory) this.f5211a).i(i);
                DecimalFormat decimalFormat = new DecimalFormat("0.000");
                if (i2 == null) {
                    return null;
                }
                return decimalFormat.format(i2);
            case 86:
                return ((ReconyxHyperFireMakernoteDirectory) this.f5211a).s(i);
            default:
                return super.a(i);
        }
    }
}
