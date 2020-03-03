package com.horcrux.svg;

import android.graphics.Paint;
import android.graphics.Path;
import java.util.ArrayList;

class GlyphPathBag {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<Path> f5810a = new ArrayList<>();
    private final int[][] b = new int[256][];
    private final Paint c;

    GlyphPathBag(Paint paint) {
        this.c = paint;
        this.f5810a.add(new Path());
    }

    /* access modifiers changed from: package-private */
    public Path a(char c2, String str) {
        Path path;
        int a2 = a(c2);
        if (a2 != 0) {
            path = this.f5810a.get(a2);
        } else {
            Path path2 = new Path();
            this.c.getTextPath(str, 0, 1, 0.0f, 0.0f, path2);
            int i = c2 >> 8;
            int[] iArr = this.b[i];
            if (iArr == null) {
                int[] iArr2 = new int[256];
                this.b[i] = iArr2;
                iArr = iArr2;
            }
            iArr[c2 & 255] = this.f5810a.size();
            this.f5810a.add(path2);
            path = path2;
        }
        Path path3 = new Path();
        path3.addPath(path);
        return path3;
    }

    private int a(char c2) {
        int[] iArr = this.b[c2 >> 8];
        if (iArr == null) {
            return 0;
        }
        return iArr[c2 & 255];
    }
}
