package com.drew.metadata.mp4;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.ArrayList;
import java.util.Arrays;

public class Mp4Descriptor<T extends Directory> extends TagDescriptor<Mp4Directory> {
    public Mp4Descriptor(@NotNull Mp4Directory mp4Directory) {
        super(mp4Directory);
    }

    public String a(int i) {
        if (i == 1) {
            return a();
        }
        if (i == 3) {
            return b();
        }
        if (i != 259) {
            return ((Mp4Directory) this.f5211a).s(i);
        }
        return c();
    }

    private String a() {
        byte[] g = ((Mp4Directory) this.f5211a).g(1);
        if (g == null) {
            return null;
        }
        return Mp4Dictionary.a(1, new String(g));
    }

    private String b() {
        String[] d = ((Mp4Directory) this.f5211a).d(3);
        if (d == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : d) {
            String a2 = Mp4Dictionary.a(1, str);
            if (a2 != null) {
                str = a2;
            }
            arrayList.add(str);
        }
        return Arrays.toString(arrayList.toArray());
    }

    private String c() {
        Long m = ((Mp4Directory) this.f5211a).m(259);
        if (m == null) {
            return null;
        }
        double longValue = (double) m.longValue();
        double pow = Math.pow(60.0d, 2.0d);
        Double.isNaN(longValue);
        Integer valueOf = Integer.valueOf((int) (longValue / pow));
        double longValue2 = (double) m.longValue();
        double pow2 = Math.pow(60.0d, 1.0d);
        Double.isNaN(longValue2);
        double d = longValue2 / pow2;
        double intValue = (double) (valueOf.intValue() * 60);
        Double.isNaN(intValue);
        Integer valueOf2 = Integer.valueOf((int) (d - intValue));
        double longValue3 = (double) m.longValue();
        double pow3 = Math.pow(60.0d, 0.0d);
        Double.isNaN(longValue3);
        double d2 = longValue3 / pow3;
        double intValue2 = (double) (valueOf2.intValue() * 60);
        Double.isNaN(intValue2);
        return String.format("%1$02d:%2$02d:%3$02d", new Object[]{valueOf, valueOf2, Integer.valueOf((int) Math.ceil(d2 - intValue2))});
    }
}
