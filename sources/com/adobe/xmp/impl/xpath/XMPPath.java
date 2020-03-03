package com.adobe.xmp.impl.xpath;

import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.util.ArrayList;
import java.util.List;

public class XMPPath {

    /* renamed from: a  reason: collision with root package name */
    public static final int f701a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = Integer.MIN_VALUE;
    public static final int h = 0;
    public static final int i = 1;
    private List j = new ArrayList(5);

    public int a() {
        return this.j.size();
    }

    public XMPPathSegment a(int i2) {
        return (XMPPathSegment) this.j.get(i2);
    }

    public void a(XMPPathSegment xMPPathSegment) {
        this.j.add(xMPPathSegment);
    }

    public String toString() {
        int a2;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 1; i2 < a(); i2++) {
            stringBuffer.append(a(i2));
            if (i2 < a() - 1 && ((a2 = a(i2 + 1).a()) == 1 || a2 == 2)) {
                stringBuffer.append(IOUtils.f15883a);
            }
        }
        return stringBuffer.toString();
    }
}
