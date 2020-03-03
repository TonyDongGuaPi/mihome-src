package com.drew.metadata.mov;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import java.util.ArrayList;
import java.util.Arrays;

public class QuickTimeDescriptor extends TagDescriptor<QuickTimeDirectory> {
    public QuickTimeDescriptor(@NotNull QuickTimeDirectory quickTimeDirectory) {
        super(quickTimeDirectory);
    }

    public String a(int i) {
        if (i == 259) {
            return c();
        }
        if (i == 4096) {
            return a();
        }
        if (i != 4098) {
            return super.a(i);
        }
        return b();
    }

    private String a() {
        byte[] g = ((QuickTimeDirectory) this.f5211a).g(4096);
        if (g == null) {
            return null;
        }
        return QuickTimeDictionary.a(4096, new String(g));
    }

    private String b() {
        String[] d = ((QuickTimeDirectory) this.f5211a).d(4098);
        if (d == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : d) {
            String a2 = QuickTimeDictionary.a(4096, str);
            if (a2 != null) {
                str = a2;
            }
            arrayList.add(str);
        }
        return Arrays.toString(arrayList.toArray());
    }

    private String c() {
        Long m = ((QuickTimeDirectory) this.f5211a).m(259);
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
