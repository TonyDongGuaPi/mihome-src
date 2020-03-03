package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzae[] zzev;
    private List<Line> zzew;
    private String zzex;
    private Rect zzey;

    TextBlock(SparseArray<zzae> sparseArray) {
        this.zzev = new zzae[sparseArray.size()];
        for (int i = 0; i < this.zzev.length; i++) {
            this.zzev[i] = sparseArray.valueAt(i);
        }
    }

    public String getLanguage() {
        if (this.zzex != null) {
            return this.zzex;
        }
        HashMap hashMap = new HashMap();
        for (zzae zzae : this.zzev) {
            hashMap.put(zzae.zzex, Integer.valueOf((hashMap.containsKey(zzae.zzex) ? ((Integer) hashMap.get(zzae.zzex)).intValue() : 0) + 1));
        }
        this.zzex = (String) ((Map.Entry) Collections.max(hashMap.entrySet(), new zza(this))).getKey();
        if (this.zzex == null || this.zzex.isEmpty()) {
            this.zzex = C.LANGUAGE_UNDETERMINED;
        }
        return this.zzex;
    }

    public String getValue() {
        if (this.zzev.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(this.zzev[0].zzfg);
        for (int i = 1; i < this.zzev.length; i++) {
            sb.append("\n");
            sb.append(this.zzev[i].zzfg);
        }
        return sb.toString();
    }

    public Point[] getCornerPoints() {
        if (this.cornerPoints == null) {
            char c = 0;
            if (this.zzev.length == 0) {
                this.cornerPoints = new Point[0];
            } else {
                int i = Integer.MAX_VALUE;
                int i2 = 0;
                int i3 = Integer.MAX_VALUE;
                int i4 = Integer.MIN_VALUE;
                int i5 = Integer.MIN_VALUE;
                while (i2 < this.zzev.length) {
                    zzy zzy = this.zzev[i2].zzfd;
                    zzy zzy2 = this.zzev[c].zzfd;
                    double sin = Math.sin(Math.toRadians((double) zzy2.zzfb));
                    double cos = Math.cos(Math.toRadians((double) zzy2.zzfb));
                    Point[] pointArr = new Point[4];
                    int i6 = i5;
                    pointArr[0] = new Point(zzy.left, zzy.top);
                    pointArr[0].offset(-zzy2.left, -zzy2.top);
                    double d = (double) pointArr[0].x;
                    Double.isNaN(d);
                    double d2 = (double) pointArr[0].y;
                    Double.isNaN(d2);
                    int i7 = (int) ((d * cos) + (d2 * sin));
                    double d3 = (double) (-pointArr[0].x);
                    Double.isNaN(d3);
                    double d4 = d3 * sin;
                    double d5 = (double) pointArr[0].y;
                    Double.isNaN(d5);
                    int i8 = (int) (d4 + (d5 * cos));
                    pointArr[0].x = i7;
                    pointArr[0].y = i8;
                    pointArr[1] = new Point(zzy.width + i7, i8);
                    pointArr[2] = new Point(zzy.width + i7, zzy.height + i8);
                    pointArr[3] = new Point(i7, i8 + zzy.height);
                    i5 = i6;
                    i = i;
                    for (int i9 = 0; i9 < 4; i9++) {
                        Point point = pointArr[i9];
                        i = Math.min(i, point.x);
                        i4 = Math.max(i4, point.x);
                        i3 = Math.min(i3, point.y);
                        i5 = Math.max(i5, point.y);
                    }
                    i2++;
                    c = 0;
                }
                int i10 = i;
                int i11 = i5;
                zzy zzy3 = this.zzev[0].zzfd;
                int i12 = zzy3.left;
                int i13 = zzy3.top;
                double sin2 = Math.sin(Math.toRadians((double) zzy3.zzfb));
                double cos2 = Math.cos(Math.toRadians((double) zzy3.zzfb));
                int i14 = i10;
                int i15 = i11;
                Point[] pointArr2 = {new Point(i14, i3), new Point(i4, i3), new Point(i4, i15), new Point(i14, i15)};
                for (int i16 = 0; i16 < 4; i16++) {
                    double d6 = (double) pointArr2[i16].x;
                    Double.isNaN(d6);
                    double d7 = (double) pointArr2[i16].y;
                    Double.isNaN(d7);
                    double d8 = (double) pointArr2[i16].x;
                    Double.isNaN(d8);
                    double d9 = (double) pointArr2[i16].y;
                    Double.isNaN(d9);
                    pointArr2[i16].x = (int) ((d6 * cos2) - (d7 * sin2));
                    pointArr2[i16].y = (int) ((d8 * sin2) + (d9 * cos2));
                    i12 = i12;
                    pointArr2[i16].offset(i12, i13);
                }
                this.cornerPoints = pointArr2;
            }
        }
        return this.cornerPoints;
    }

    public List<? extends Text> getComponents() {
        if (this.zzev.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzew == null) {
            this.zzew = new ArrayList(this.zzev.length);
            for (zzae line : this.zzev) {
                this.zzew.add(new Line(line));
            }
        }
        return this.zzew;
    }

    public Rect getBoundingBox() {
        if (this.zzey == null) {
            this.zzey = zzc.zza((Text) this);
        }
        return this.zzey;
    }
}
