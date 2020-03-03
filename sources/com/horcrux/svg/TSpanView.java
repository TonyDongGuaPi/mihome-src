package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewParent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.horcrux.svg.TextProperties;
import com.xiaomi.stat.a.j;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SuppressLint({"ViewConstructor"})
class TSpanView extends TextView {
    private static final double f = 6.283185307179586d;
    private static final double g = 57.29577951308232d;
    private static final String h = "fonts/";
    private static final String i = ".otf";
    private static final String j = ".ttf";
    @Nullable
    String b;
    private Path k;
    private TextPathView l;
    private final ArrayList<String> m = new ArrayList<>();
    private final ArrayList<Matrix> n = new ArrayList<>();

    public TSpanView(ReactContext reactContext) {
        super(reactContext);
    }

    @ReactProp(name = "content")
    public void setContent(@Nullable String str) {
        this.b = str;
        invalidate();
    }

    public void invalidate() {
        this.k = null;
        super.invalidate();
    }

    /* access modifiers changed from: package-private */
    public void clearCache() {
        this.k = null;
        super.clearCache();
    }

    /* access modifiers changed from: package-private */
    public void draw(Canvas canvas, Paint paint, float f2) {
        if (this.b != null) {
            int size = this.m.size();
            if (size > 0) {
                a(paint, b().a());
                for (int i2 = 0; i2 < size; i2++) {
                    canvas.save();
                    canvas.concat(this.n.get(i2));
                    canvas.drawText(this.m.get(i2), 0.0f, 0.0f, paint);
                    canvas.restore();
                }
            }
            b(canvas, paint, f2);
            return;
        }
        clip(canvas, paint);
        a(canvas, paint, f2);
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        if (this.k != null) {
            return this.k;
        }
        if (this.b == null) {
            this.k = b(canvas, paint);
            return this.k;
        }
        e();
        c();
        this.k = a(this.b, paint, canvas);
        d();
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public double a(Paint paint) {
        if (!Double.isNaN(this.e)) {
            return this.e;
        }
        int i2 = 0;
        double d = 0.0d;
        if (this.b == null) {
            while (i2 < getChildCount()) {
                View childAt = getChildAt(i2);
                if (childAt instanceof TextView) {
                    d += ((TextView) childAt).a(paint);
                }
                i2++;
            }
            this.e = d;
            return d;
        }
        String str = this.b;
        if (str.length() == 0) {
            this.e = 0.0d;
            return 0.0d;
        }
        FontData a2 = b().a();
        a(paint, a2);
        double d2 = a2.l;
        if (d2 == 0.0d && a2.h == TextProperties.FontVariantLigatures.normal) {
            i2 = 1;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            String str2 = "'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk'," + "'kern', ";
            if (i2 != 0) {
                paint.setFontFeatureSettings(str2 + "'hlig', 'cala', " + a2.g);
            } else {
                paint.setFontFeatureSettings(str2 + "'liga' 0, 'clig' 0, 'dlig' 0, 'hlig' 0, 'cala' 0, " + a2.g);
            }
            double d3 = a2.b;
            double d4 = (double) this.mScale;
            Double.isNaN(d4);
            paint.setLetterSpacing((float) (d2 / (d3 * d4)));
        }
        this.e = (double) paint.measureText(str);
        return this.e;
    }

    private Path a(String str, Paint paint, Canvas canvas) {
        double d;
        boolean z;
        PathMeasure pathMeasure;
        PathMeasure pathMeasure2;
        GlyphContext glyphContext;
        GlyphPathBag glyphPathBag;
        boolean[] zArr;
        double d2;
        double d3;
        boolean z2;
        int i2;
        double d4;
        double d5;
        int i3;
        boolean z3;
        String str2;
        int i4;
        char c;
        double d6;
        Matrix matrix;
        double d7;
        int i5;
        GlyphContext glyphContext2;
        double d8;
        int i6;
        Matrix matrix2;
        int i7;
        float[] fArr;
        Paint paint2;
        TSpanView tSpanView;
        Path path;
        float[] fArr2;
        PathMeasure pathMeasure3;
        Matrix matrix3;
        GlyphPathBag glyphPathBag2;
        Matrix matrix4;
        GlyphContext glyphContext3;
        float[] fArr3;
        float[] fArr4;
        Matrix matrix5;
        GlyphPathBag glyphPathBag3;
        PathMeasure pathMeasure4;
        double d9;
        double d10;
        int i8;
        int i9;
        int i10;
        double d11;
        String str3;
        double d12;
        GlyphContext glyphContext4;
        double d13;
        Path path2;
        String str4;
        Path path3;
        boolean z4;
        double d14;
        int i11;
        double d15;
        int i12;
        TSpanView tSpanView2 = this;
        Paint paint3 = paint;
        Canvas canvas2 = canvas;
        int length = str.length();
        Path path4 = new Path();
        tSpanView2.m.clear();
        tSpanView2.n.clear();
        if (length == 0) {
            return path4;
        }
        boolean z5 = tSpanView2.l != null;
        if (z5) {
            PathMeasure pathMeasure5 = new PathMeasure(tSpanView2.l.a(canvas2, paint3), false);
            double length2 = (double) pathMeasure5.getLength();
            boolean isClosed = pathMeasure5.isClosed();
            if (length2 == 0.0d) {
                return path4;
            }
            pathMeasure = pathMeasure5;
            d = length2;
            z = isClosed;
        } else {
            pathMeasure = null;
            d = 0.0d;
            z = false;
        }
        GlyphContext b2 = b();
        FontData a2 = b2.a();
        tSpanView2.a(paint3, a2);
        GlyphPathBag glyphPathBag4 = new GlyphPathBag(paint3);
        boolean[] zArr2 = new boolean[length];
        char[] charArray = str.toCharArray();
        Path path5 = path4;
        double d16 = a2.j;
        double d17 = a2.k;
        double d18 = a2.l;
        boolean z6 = !a2.m;
        boolean z7 = d18 == 0.0d && a2.h == TextProperties.FontVariantLigatures.normal;
        GlyphPathBag glyphPathBag5 = glyphPathBag4;
        if (Build.VERSION.SDK_INT >= 21) {
            String str5 = "'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk'," + "'kern', ";
            if (z7) {
                paint3.setFontFeatureSettings(str5 + "'hlig', 'cala', " + a2.g);
            } else {
                paint3.setFontFeatureSettings(str5 + "'liga' 0, 'clig' 0, 'dlig' 0, 'hlig' 0, 'cala' 0, " + a2.g);
            }
        }
        ReadableMap readableMap = a2.e;
        float[] fArr5 = new float[length];
        paint3.getTextWidths(str, fArr5);
        TextProperties.TextAnchor textAnchor = a2.i;
        float[] fArr6 = fArr5;
        double d19 = d18;
        double a3 = l().a(paint3);
        double a4 = tSpanView2.a(textAnchor, a3);
        tSpanView2.a(paint3, a2);
        double c2 = b2.c();
        char c3 = 65535;
        if (z5) {
            boolean z8 = tSpanView2.l.h() == TextProperties.TextPathMidLine.sharp;
            int i13 = tSpanView2.l.g() == TextProperties.TextPathSide.right ? -1 : 1;
            TextProperties.TextAnchor textAnchor2 = textAnchor;
            glyphPathBag = glyphPathBag5;
            zArr = zArr2;
            pathMeasure2 = pathMeasure;
            glyphContext = b2;
            double a5 = a(tSpanView2.l.i(), d, c2);
            a4 += a5;
            if (z) {
                double d20 = a5 + (textAnchor2 == TextProperties.TextAnchor.middle ? -(d / 2.0d) : 0.0d);
                d3 = d20 + d;
                d2 = d20;
            } else {
                d3 = d;
                d2 = 0.0d;
            }
            z2 = z8;
            i2 = i13;
        } else {
            pathMeasure2 = pathMeasure;
            glyphContext = b2;
            glyphPathBag = glyphPathBag5;
            zArr = zArr2;
            d3 = d;
            d2 = 0.0d;
            i2 = 1;
            z2 = false;
        }
        double d21 = 1.0d;
        if (tSpanView2.c != null) {
            d5 = d;
            d4 = d2;
            double a6 = PropHelper.a(tSpanView2.c, (double) canvas.getWidth(), 0.0d, (double) tSpanView2.mScale, c2);
            if (a6 < 0.0d) {
                throw new IllegalArgumentException("Negative textLength value");
            } else if (AnonymousClass1.f5831a[tSpanView2.d.ordinal()] != 2) {
                double d22 = (double) (length - 1);
                Double.isNaN(d22);
                d19 += (a6 - a3) / d22;
            } else {
                d21 = a6 / a3;
            }
        } else {
            d4 = d2;
            d5 = d;
        }
        double d23 = (double) i2;
        Double.isNaN(d23);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        double d24 = (double) fontMetrics.descent;
        double d25 = d21 * d23;
        double d26 = (double) fontMetrics.leading;
        Double.isNaN(d24);
        Double.isNaN(d26);
        double d27 = d26 + d24;
        int i14 = i2;
        boolean z9 = z2;
        double d28 = (double) ((-fontMetrics.ascent) + fontMetrics.leading);
        double d29 = d3;
        double d30 = (double) (-fontMetrics.top);
        Double.isNaN(d30);
        double d31 = d30 + d27;
        String k2 = k();
        TextProperties.AlignmentBaseline j2 = j();
        if (j2 != null) {
            switch (j2) {
                case textBottom:
                case afterEdge:
                case textAfterEdge:
                    Double.isNaN(d24);
                    d28 = -d24;
                    break;
                case ideographic:
                    Double.isNaN(d24);
                    d28 = -d24;
                    break;
                case middle:
                    Rect rect = new Rect();
                    paint3.getTextBounds("x", 0, 1, rect);
                    double height = (double) rect.height();
                    Double.isNaN(height);
                    d28 = height / 2.0d;
                    break;
                case central:
                    Double.isNaN(d28);
                    Double.isNaN(d24);
                    d28 = (d28 - d24) / 2.0d;
                    break;
                case mathematical:
                    Double.isNaN(d28);
                    d28 *= 0.5d;
                    break;
                case hanging:
                    Double.isNaN(d28);
                    d28 *= 0.8d;
                    break;
                case textTop:
                case beforeEdge:
                case textBeforeEdge:
                    break;
                case bottom:
                    d28 = d27;
                    break;
                case center:
                    d28 = d31 / 2.0d;
                    break;
                case top:
                    d28 = d30;
                    break;
            }
        }
        d28 = 0.0d;
        if (!(k2 == null || k2.isEmpty() || (i12 = AnonymousClass1.b[j2.ordinal()]) == 14 || i12 == 16)) {
            int hashCode = k2.hashCode();
            if (hashCode != -1720785339) {
                if (hashCode != 114240) {
                    if (hashCode == 109801339 && k2.equals("super")) {
                        c3 = 1;
                    }
                } else if (k2.equals(j.i)) {
                    c3 = 0;
                }
            } else if (k2.equals("baseline")) {
                c3 = 2;
            }
            switch (c3) {
                case 0:
                    if (readableMap != null && readableMap.hasKey("tables") && readableMap.hasKey("unitsPerEm")) {
                        int i15 = readableMap.getInt("unitsPerEm");
                        ReadableMap map = readableMap.getMap("tables");
                        if (map.hasKey("os2")) {
                            ReadableMap map2 = map.getMap("os2");
                            if (map2.hasKey("ySubscriptYOffset")) {
                                double d32 = map2.getDouble("ySubscriptYOffset");
                                double d33 = (double) tSpanView2.mScale;
                                Double.isNaN(d33);
                                double d34 = d33 * c2 * d32;
                                double d35 = (double) i15;
                                Double.isNaN(d35);
                                d28 += d34 / d35;
                                break;
                            }
                        }
                    }
                    break;
                case 1:
                    if (readableMap != null && readableMap.hasKey("tables") && readableMap.hasKey("unitsPerEm")) {
                        int i16 = readableMap.getInt("unitsPerEm");
                        ReadableMap map3 = readableMap.getMap("tables");
                        if (map3.hasKey("os2")) {
                            ReadableMap map4 = map3.getMap("os2");
                            if (map4.hasKey("ySuperscriptYOffset")) {
                                double d36 = map4.getDouble("ySuperscriptYOffset");
                                double d37 = (double) tSpanView2.mScale;
                                Double.isNaN(d37);
                                double d38 = d37 * c2 * d36;
                                double d39 = (double) i16;
                                Double.isNaN(d39);
                                d28 -= d38 / d39;
                                break;
                            }
                        }
                    }
                    break;
                case 2:
                    break;
                default:
                    double d40 = (double) tSpanView2.mScale;
                    Double.isNaN(d40);
                    d28 -= PropHelper.a(k2, d40 * c2, (double) tSpanView2.mScale, c2);
                    break;
            }
        }
        Matrix matrix6 = new Matrix();
        Matrix matrix7 = new Matrix();
        Matrix matrix8 = new Matrix();
        float[] fArr7 = new float[9];
        float[] fArr8 = new float[9];
        int i17 = 0;
        while (i17 < length) {
            char c4 = charArray[i17];
            String valueOf = String.valueOf(c4);
            boolean z10 = zArr[i17];
            float f2 = 0.0f;
            if (z10) {
                str2 = "";
                i3 = length;
                z3 = false;
            } else {
                String str6 = valueOf;
                int i18 = i17;
                z3 = false;
                while (true) {
                    i18++;
                    if (i18 >= length || fArr6[i18] > f2) {
                        i3 = length;
                        str2 = str6;
                    } else {
                        str6 = str6 + charArray[i18];
                        zArr[i18] = true;
                        length = length;
                        f2 = 0.0f;
                        z3 = true;
                    }
                }
                i3 = length;
                str2 = str6;
            }
            double measureText = (double) paint3.measureText(str2);
            Double.isNaN(measureText);
            double d41 = measureText * d21;
            if (z6) {
                i4 = i17;
                double d42 = (double) fArr6[i17];
                Double.isNaN(d42);
                d16 = (d42 * d21) - d41;
            } else {
                i4 = i17;
            }
            boolean z11 = c4 == ' ';
            double d43 = d41 + (z11 ? d17 : 0.0d) + d19;
            if (z10) {
                c = c4;
                d6 = 0.0d;
            } else {
                c = c4;
                d6 = d16 + d43;
            }
            GlyphContext glyphContext5 = glyphContext;
            double a7 = glyphContext5.a(d6);
            double d44 = d28;
            double d45 = glyphContext5.d();
            double e = glyphContext5.e();
            double f3 = glyphContext5.f();
            double d46 = d45;
            double g2 = glyphContext5.g();
            if (z10 || z11) {
                matrix4 = matrix6;
                glyphContext3 = glyphContext5;
                fArr3 = fArr8;
                fArr4 = fArr7;
                matrix5 = matrix7;
                path = path5;
                glyphPathBag3 = glyphPathBag;
                pathMeasure4 = pathMeasure2;
                d9 = d5;
                d10 = d25;
                i8 = i14;
                i9 = i3;
                i10 = i4;
            } else {
                Double.isNaN(d23);
                Double.isNaN(d23);
                double d47 = d41 * d23;
                Double.isNaN(d23);
                double d48 = (a4 + ((a7 + e) * d23)) - (d43 * d23);
                if (z5) {
                    glyphContext4 = glyphContext5;
                    double d49 = d48 + d47;
                    double d50 = d47 / 2.0d;
                    d12 = g2;
                    double d51 = d48 + d50;
                    if (d51 <= d29 && d51 >= d4) {
                        str3 = str2;
                        if (z9) {
                            PathMeasure pathMeasure6 = pathMeasure2;
                            pathMeasure6.getMatrix((float) d51, matrix7, 3);
                            pathMeasure3 = pathMeasure6;
                            matrix3 = matrix7;
                            d11 = d5;
                        } else {
                            pathMeasure3 = pathMeasure2;
                            if (d48 < 0.0d) {
                                d14 = d50;
                                pathMeasure3.getMatrix(0.0f, matrix6, 3);
                                matrix6.preTranslate((float) d48, 0.0f);
                                i11 = 1;
                            } else {
                                d14 = d50;
                                i11 = 1;
                                pathMeasure3.getMatrix((float) d48, matrix6, 1);
                            }
                            pathMeasure3.getMatrix((float) d51, matrix7, i11);
                            if (d49 > d5) {
                                d15 = d5;
                                pathMeasure3.getMatrix((float) d15, matrix8, 3);
                                matrix8.preTranslate((float) (d49 - d15), 0.0f);
                            } else {
                                d15 = d5;
                                pathMeasure3.getMatrix((float) d49, matrix8, i11);
                            }
                            matrix6.getValues(fArr7);
                            matrix8.getValues(fArr8);
                            double d52 = (double) fArr7[2];
                            double d53 = (double) fArr7[5];
                            double d54 = (double) fArr8[2];
                            d11 = d15;
                            double d55 = (double) fArr8[5];
                            Double.isNaN(d54);
                            Double.isNaN(d52);
                            Double.isNaN(d55);
                            Double.isNaN(d53);
                            double atan2 = Math.atan2(d55 - d53, d54 - d52) * g;
                            Double.isNaN(d23);
                            matrix3 = matrix7;
                            matrix3.preRotate((float) (atan2 * d23));
                            d50 = d14;
                        }
                        matrix3.preTranslate((float) (-d50), (float) (f3 + d44));
                        d13 = d25;
                        i7 = i14;
                        matrix3.preScale((float) d13, (float) i7);
                        matrix3.postTranslate(0.0f, (float) d46);
                    } else {
                        matrix4 = matrix6;
                        fArr3 = fArr8;
                        fArr4 = fArr7;
                        matrix5 = matrix7;
                        path = path5;
                        glyphPathBag3 = glyphPathBag;
                        pathMeasure4 = pathMeasure2;
                        d9 = d5;
                        d10 = d25;
                        i8 = i14;
                        i9 = i3;
                        i10 = i4;
                        glyphContext3 = glyphContext4;
                    }
                } else {
                    d12 = g2;
                    str3 = str2;
                    glyphContext4 = glyphContext5;
                    matrix3 = matrix7;
                    pathMeasure3 = pathMeasure2;
                    d11 = d5;
                    d13 = d25;
                    i7 = i14;
                    matrix3.setTranslate((float) d48, (float) (d46 + f3 + d44));
                }
                matrix3.preRotate((float) d12);
                if (z3) {
                    Path path6 = new Path();
                    d8 = d11;
                    i5 = i4;
                    glyphContext2 = glyphContext4;
                    d7 = d13;
                    fArr = fArr8;
                    fArr2 = fArr7;
                    path2 = path5;
                    matrix2 = matrix8;
                    matrix = matrix6;
                    i6 = i3;
                    z4 = true;
                    paint.getTextPath(str3, 0, str3.length(), 0.0f, 0.0f, path6);
                    path3 = path6;
                    glyphPathBag2 = glyphPathBag;
                    str4 = str3;
                } else {
                    matrix = matrix6;
                    fArr2 = fArr7;
                    d7 = d13;
                    path2 = path5;
                    glyphPathBag2 = glyphPathBag;
                    i6 = i3;
                    i5 = i4;
                    glyphContext2 = glyphContext4;
                    str4 = str3;
                    d8 = d11;
                    z4 = true;
                    fArr = fArr8;
                    matrix2 = matrix8;
                    path3 = glyphPathBag2.a(c, str4);
                }
                RectF rectF = new RectF();
                path3.computeBounds(rectF, z4);
                if (rectF.width() == 0.0f) {
                    canvas.save();
                    Canvas canvas3 = canvas;
                    canvas3.concat(matrix3);
                    tSpanView = this;
                    tSpanView.m.add(str4);
                    tSpanView.n.add(new Matrix(matrix3));
                    paint2 = paint;
                    canvas3.drawText(str4, 0.0f, 0.0f, paint2);
                    canvas.restore();
                    path = path2;
                } else {
                    Canvas canvas4 = canvas;
                    tSpanView = this;
                    paint2 = paint;
                    path3.transform(matrix3);
                    path = path2;
                    path.addPath(path3);
                }
                glyphPathBag = glyphPathBag2;
                pathMeasure2 = pathMeasure3;
                i14 = i7;
                matrix8 = matrix2;
                length = i6;
                d5 = d8;
                glyphContext = glyphContext2;
                d25 = d7;
                matrix6 = matrix;
                matrix7 = matrix3;
                path5 = path;
                d28 = d44;
                i17 = i5 + 1;
                Paint paint4 = paint2;
                fArr7 = fArr2;
                tSpanView2 = tSpanView;
                fArr8 = fArr;
                paint3 = paint4;
            }
            Canvas canvas5 = canvas;
            tSpanView = this;
            paint2 = paint;
            matrix2 = matrix8;
            glyphPathBag = glyphPathBag2;
            pathMeasure2 = pathMeasure3;
            i14 = i7;
            matrix8 = matrix2;
            length = i6;
            d5 = d8;
            glyphContext = glyphContext2;
            d25 = d7;
            matrix6 = matrix;
            matrix7 = matrix3;
            path5 = path;
            d28 = d44;
            i17 = i5 + 1;
            Paint paint42 = paint2;
            fArr7 = fArr2;
            tSpanView2 = tSpanView;
            fArr8 = fArr;
            paint3 = paint42;
        }
        TSpanView tSpanView3 = tSpanView2;
        return path5;
    }

    private double a(SVGLength sVGLength, double d, double d2) {
        return PropHelper.a(sVGLength, d, 0.0d, (double) this.mScale, d2);
    }

    /* renamed from: com.horcrux.svg.TSpanView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f5831a = new int[TextProperties.TextLengthAdjust.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(43:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|(3:49|50|52)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(45:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|52) */
        /* JADX WARNING: Can't wrap try/catch for region: R(46:0|(2:1|2)|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|52) */
        /* JADX WARNING: Can't wrap try/catch for region: R(47:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|47|48|49|50|52) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0047 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0051 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0067 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0072 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0089 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0095 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00a1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00ad */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00b9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00c5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00d1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00dd */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00fc */
        static {
            /*
                com.horcrux.svg.TextProperties$TextAnchor[] r0 = com.horcrux.svg.TextProperties.TextAnchor.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                c = r0
                r0 = 1
                int[] r1 = c     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.horcrux.svg.TextProperties$TextAnchor r2 = com.horcrux.svg.TextProperties.TextAnchor.start     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = c     // Catch:{ NoSuchFieldError -> 0x001f }
                com.horcrux.svg.TextProperties$TextAnchor r3 = com.horcrux.svg.TextProperties.TextAnchor.middle     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = c     // Catch:{ NoSuchFieldError -> 0x002a }
                com.horcrux.svg.TextProperties$TextAnchor r4 = com.horcrux.svg.TextProperties.TextAnchor.end     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                com.horcrux.svg.TextProperties$AlignmentBaseline[] r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                b = r3
                int[] r3 = b     // Catch:{ NoSuchFieldError -> 0x003d }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties.AlignmentBaseline.baseline     // Catch:{ NoSuchFieldError -> 0x003d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                int[] r3 = b     // Catch:{ NoSuchFieldError -> 0x0047 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties.AlignmentBaseline.textBottom     // Catch:{ NoSuchFieldError -> 0x0047 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0047 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0047 }
            L_0x0047:
                int[] r3 = b     // Catch:{ NoSuchFieldError -> 0x0051 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties.AlignmentBaseline.afterEdge     // Catch:{ NoSuchFieldError -> 0x0051 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0051 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0051 }
            L_0x0051:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x005c }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.textAfterEdge     // Catch:{ NoSuchFieldError -> 0x005c }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r4 = 4
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x0067 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.alphabetic     // Catch:{ NoSuchFieldError -> 0x0067 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0067 }
                r4 = 5
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0067 }
            L_0x0067:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x0072 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.ideographic     // Catch:{ NoSuchFieldError -> 0x0072 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0072 }
                r4 = 6
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0072 }
            L_0x0072:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x007d }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.middle     // Catch:{ NoSuchFieldError -> 0x007d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x007d }
                r4 = 7
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x007d }
            L_0x007d:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.central     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r4 = 8
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x0095 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.mathematical     // Catch:{ NoSuchFieldError -> 0x0095 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0095 }
                r4 = 9
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0095 }
            L_0x0095:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x00a1 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.hanging     // Catch:{ NoSuchFieldError -> 0x00a1 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a1 }
                r4 = 10
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x00a1 }
            L_0x00a1:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x00ad }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.textTop     // Catch:{ NoSuchFieldError -> 0x00ad }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ad }
                r4 = 11
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x00ad }
            L_0x00ad:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x00b9 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.beforeEdge     // Catch:{ NoSuchFieldError -> 0x00b9 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b9 }
                r4 = 12
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x00b9 }
            L_0x00b9:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x00c5 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.textBeforeEdge     // Catch:{ NoSuchFieldError -> 0x00c5 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c5 }
                r4 = 13
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x00c5 }
            L_0x00c5:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x00d1 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.bottom     // Catch:{ NoSuchFieldError -> 0x00d1 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d1 }
                r4 = 14
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x00d1 }
            L_0x00d1:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x00dd }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.center     // Catch:{ NoSuchFieldError -> 0x00dd }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00dd }
                r4 = 15
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x00dd }
            L_0x00dd:
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x00e9 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties.AlignmentBaseline.top     // Catch:{ NoSuchFieldError -> 0x00e9 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e9 }
                r4 = 16
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x00e9 }
            L_0x00e9:
                com.horcrux.svg.TextProperties$TextLengthAdjust[] r2 = com.horcrux.svg.TextProperties.TextLengthAdjust.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                f5831a = r2
                int[] r2 = f5831a     // Catch:{ NoSuchFieldError -> 0x00fc }
                com.horcrux.svg.TextProperties$TextLengthAdjust r3 = com.horcrux.svg.TextProperties.TextLengthAdjust.spacing     // Catch:{ NoSuchFieldError -> 0x00fc }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fc }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x00fc }
            L_0x00fc:
                int[] r0 = f5831a     // Catch:{ NoSuchFieldError -> 0x0106 }
                com.horcrux.svg.TextProperties$TextLengthAdjust r2 = com.horcrux.svg.TextProperties.TextLengthAdjust.spacingAndGlyphs     // Catch:{ NoSuchFieldError -> 0x0106 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0106 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0106 }
            L_0x0106:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TSpanView.AnonymousClass1.<clinit>():void");
        }
    }

    private double a(TextProperties.TextAnchor textAnchor, double d) {
        switch (textAnchor) {
            case middle:
                return (-d) / 2.0d;
            case end:
                return -d;
            default:
                return 0.0d;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:18|19) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r4 = android.graphics.Typeface.createFromAsset(r0, h + r10 + j);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0076, code lost:
        r4 = com.facebook.react.views.text.ReactFontManager.getInstance().getTypeface(r10, r5, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0078, code lost:
        r4 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0053 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x006e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.graphics.Paint r9, com.horcrux.svg.FontData r10) {
        /*
            r8 = this;
            com.facebook.react.bridge.ReactContext r0 = r8.mContext
            android.content.res.Resources r0 = r0.getResources()
            android.content.res.AssetManager r0 = r0.getAssets()
            double r1 = r10.b
            float r3 = r8.mScale
            double r3 = (double) r3
            java.lang.Double.isNaN(r3)
            double r1 = r1 * r3
            com.horcrux.svg.TextProperties$FontWeight r3 = r10.f
            com.horcrux.svg.TextProperties$FontWeight r4 = com.horcrux.svg.TextProperties.FontWeight.Bold
            r5 = 0
            r6 = 1
            if (r3 != r4) goto L_0x001e
            r3 = 1
            goto L_0x001f
        L_0x001e:
            r3 = 0
        L_0x001f:
            com.horcrux.svg.TextProperties$FontStyle r4 = r10.d
            com.horcrux.svg.TextProperties$FontStyle r7 = com.horcrux.svg.TextProperties.FontStyle.italic
            if (r4 != r7) goto L_0x0027
            r4 = 1
            goto L_0x0028
        L_0x0027:
            r4 = 0
        L_0x0028:
            if (r3 == 0) goto L_0x002e
            if (r4 == 0) goto L_0x002e
            r5 = 3
            goto L_0x0035
        L_0x002e:
            if (r3 == 0) goto L_0x0032
            r5 = 1
            goto L_0x0035
        L_0x0032:
            if (r4 == 0) goto L_0x0035
            r5 = 2
        L_0x0035:
            r3 = 0
            java.lang.String r10 = r10.c
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0053 }
            r4.<init>()     // Catch:{ Exception -> 0x0053 }
            java.lang.String r6 = "fonts/"
            r4.append(r6)     // Catch:{ Exception -> 0x0053 }
            r4.append(r10)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r6 = ".otf"
            r4.append(r6)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0053 }
            android.graphics.Typeface r4 = android.graphics.Typeface.createFromAsset(r0, r4)     // Catch:{ Exception -> 0x0053 }
            goto L_0x0079
        L_0x0053:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x006e }
            r4.<init>()     // Catch:{ Exception -> 0x006e }
            java.lang.String r6 = "fonts/"
            r4.append(r6)     // Catch:{ Exception -> 0x006e }
            r4.append(r10)     // Catch:{ Exception -> 0x006e }
            java.lang.String r6 = ".ttf"
            r4.append(r6)     // Catch:{ Exception -> 0x006e }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x006e }
            android.graphics.Typeface r4 = android.graphics.Typeface.createFromAsset(r0, r4)     // Catch:{ Exception -> 0x006e }
            goto L_0x0079
        L_0x006e:
            com.facebook.react.views.text.ReactFontManager r4 = com.facebook.react.views.text.ReactFontManager.getInstance()     // Catch:{ Exception -> 0x0078 }
            android.graphics.Typeface r10 = r4.getTypeface(r10, r5, r0)     // Catch:{ Exception -> 0x0078 }
            r4 = r10
            goto L_0x0079
        L_0x0078:
            r4 = r3
        L_0x0079:
            r9.setTypeface(r4)
            float r10 = (float) r1
            r9.setTextSize(r10)
            android.graphics.Paint$Align r10 = android.graphics.Paint.Align.LEFT
            r9.setTextAlign(r10)
            int r10 = android.os.Build.VERSION.SDK_INT
            r0 = 21
            if (r10 < r0) goto L_0x008f
            r10 = 0
            r9.setLetterSpacing(r10)
        L_0x008f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TSpanView.a(android.graphics.Paint, com.horcrux.svg.FontData):void");
    }

    private void e() {
        ViewParent parent = getParent();
        while (parent != null) {
            if (parent.getClass() == TextPathView.class) {
                this.l = (TextPathView) parent;
                return;
            } else if (parent instanceof TextView) {
                parent = parent.getParent();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int hitTest(float[] fArr) {
        if (this.b == null) {
            return super.hitTest(fArr);
        }
        if (this.mPath == null || !this.mInvertible || !this.mTransformInvertible) {
            return -1;
        }
        float[] fArr2 = new float[2];
        this.mInvMatrix.mapPoints(fArr2, fArr);
        this.mInvTransform.mapPoints(fArr2);
        int round = Math.round(fArr2[0]);
        int round2 = Math.round(fArr2[1]);
        if (this.mRegion == null && this.mFillPath != null) {
            this.mRegion = getRegion(this.mFillPath);
        }
        if (this.mRegion == null && this.mPath != null) {
            this.mRegion = getRegion(this.mPath);
        }
        if (this.mStrokeRegion == null && this.mStrokePath != null) {
            this.mStrokeRegion = getRegion(this.mStrokePath);
        }
        if ((this.mRegion == null || !this.mRegion.contains(round, round2)) && (this.mStrokeRegion == null || !this.mStrokeRegion.contains(round, round2))) {
            return -1;
        }
        Path clipPath = getClipPath();
        if (clipPath != null) {
            if (this.mClipRegionPath != clipPath) {
                this.mClipRegionPath = clipPath;
                this.mClipRegion = getRegion(clipPath);
            }
            if (!this.mClipRegion.contains(round, round2)) {
                return -1;
            }
        }
        return getId();
    }
}
