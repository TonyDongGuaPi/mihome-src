package com.xiaomi.youpin.common.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineHeightSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;
import android.util.Log;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public final class SpanUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f23272a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    private static final int e = -16777217;
    private static final String f = System.getProperty("line.separator");
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private String F;
    private Typeface G;
    private Layout.Alignment H;
    private ClickableSpan I;
    private String J;
    private float K;
    private BlurMaskFilter.Blur L;
    private Shader M;
    private float N;
    private float O;
    private float P;
    private int Q;
    private Object[] R;
    private Bitmap S;
    private Drawable T;
    private Uri U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private SpannableStringBuilder Z = new SpannableStringBuilder();
    private int aa;
    private final int ab = 0;
    private final int ac = 1;
    private final int ad = 2;
    private CharSequence g = "";
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private float w;
    private float x;
    private boolean y;
    private boolean z;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Align {
    }

    public SpanUtils() {
        j();
    }

    private void j() {
        this.h = 33;
        this.i = e;
        this.j = e;
        this.k = -1;
        this.m = e;
        this.p = -1;
        this.r = e;
        this.u = -1;
        this.w = -1.0f;
        this.x = -1.0f;
        this.y = false;
        this.z = false;
        this.A = false;
        this.B = false;
        this.C = false;
        this.D = false;
        this.E = false;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = -1.0f;
        this.M = null;
        this.N = -1.0f;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = -1;
        this.X = -1;
    }

    public SpanUtils a(int i2) {
        this.h = i2;
        return this;
    }

    public SpanUtils b(@ColorInt int i2) {
        this.i = i2;
        return this;
    }

    public SpanUtils c(@ColorInt int i2) {
        this.j = i2;
        return this;
    }

    public SpanUtils d(@IntRange(from = 0) int i2) {
        return a(i2, 2);
    }

    public SpanUtils a(@IntRange(from = 0) int i2, int i3) {
        this.k = i2;
        this.l = i3;
        return this;
    }

    public SpanUtils e(@ColorInt int i2) {
        return a(i2, 2, 2);
    }

    public SpanUtils a(@ColorInt int i2, @IntRange(from = 1) int i3, @IntRange(from = 0) int i4) {
        this.m = i2;
        this.n = i3;
        this.o = i4;
        return this;
    }

    public SpanUtils b(@IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        this.p = i2;
        this.q = i3;
        return this;
    }

    public SpanUtils f(@IntRange(from = 0) int i2) {
        return b(0, 3, i2);
    }

    public SpanUtils b(@ColorInt int i2, @IntRange(from = 0) int i3, @IntRange(from = 0) int i4) {
        this.r = i2;
        this.s = i3;
        this.t = i4;
        return this;
    }

    public SpanUtils g(@IntRange(from = 0) int i2) {
        return a(i2, false);
    }

    public SpanUtils a(@IntRange(from = 0) int i2, boolean z2) {
        this.u = i2;
        this.v = z2;
        return this;
    }

    public SpanUtils a(float f2) {
        this.w = f2;
        return this;
    }

    public SpanUtils b(float f2) {
        this.x = f2;
        return this;
    }

    public SpanUtils a() {
        this.y = true;
        return this;
    }

    public SpanUtils b() {
        this.z = true;
        return this;
    }

    public SpanUtils c() {
        this.A = true;
        return this;
    }

    public SpanUtils d() {
        this.B = true;
        return this;
    }

    public SpanUtils e() {
        this.C = true;
        return this;
    }

    public SpanUtils f() {
        this.D = true;
        return this;
    }

    public SpanUtils g() {
        this.E = true;
        return this;
    }

    public SpanUtils a(@NonNull String str) {
        this.F = str;
        return this;
    }

    public SpanUtils a(@NonNull Typeface typeface) {
        this.G = typeface;
        return this;
    }

    public SpanUtils a(@NonNull Layout.Alignment alignment) {
        this.H = alignment;
        return this;
    }

    public SpanUtils a(@NonNull ClickableSpan clickableSpan) {
        this.I = clickableSpan;
        return this;
    }

    public SpanUtils b(@NonNull String str) {
        this.J = str;
        return this;
    }

    public SpanUtils a(@FloatRange(from = 0.0d, fromInclusive = false) float f2, BlurMaskFilter.Blur blur) {
        this.K = f2;
        this.L = blur;
        return this;
    }

    public SpanUtils a(@NonNull Shader shader) {
        this.M = shader;
        return this;
    }

    public SpanUtils a(@FloatRange(from = 0.0d, fromInclusive = false) float f2, float f3, float f4, int i2) {
        this.N = f2;
        this.O = f3;
        this.P = f4;
        this.Q = i2;
        return this;
    }

    public SpanUtils a(@NonNull Object... objArr) {
        if (objArr.length > 0) {
            this.R = objArr;
        }
        return this;
    }

    public SpanUtils a(@NonNull CharSequence charSequence) {
        j(0);
        this.g = charSequence;
        return this;
    }

    public SpanUtils h() {
        j(0);
        this.g = f;
        return this;
    }

    public SpanUtils b(@NonNull CharSequence charSequence) {
        j(0);
        this.g = charSequence + f;
        return this;
    }

    public SpanUtils a(@NonNull Bitmap bitmap) {
        return a(bitmap, 0);
    }

    public SpanUtils a(@NonNull Bitmap bitmap, int i2) {
        j(1);
        this.S = bitmap;
        this.W = i2;
        return this;
    }

    public SpanUtils a(@NonNull Drawable drawable) {
        return a(drawable, 0);
    }

    public SpanUtils a(@NonNull Drawable drawable, int i2) {
        j(1);
        this.T = drawable;
        this.W = i2;
        return this;
    }

    public SpanUtils a(@NonNull Uri uri) {
        return a(uri, 0);
    }

    public SpanUtils a(@NonNull Uri uri, int i2) {
        j(1);
        this.U = uri;
        this.W = i2;
        return this;
    }

    public SpanUtils h(@DrawableRes int i2) {
        return c(i2, 0);
    }

    public SpanUtils c(@DrawableRes int i2, int i3) {
        a((CharSequence) Character.toString(0));
        j(1);
        this.V = i2;
        this.W = i3;
        return this;
    }

    public SpanUtils i(@IntRange(from = 0) int i2) {
        return d(i2, 0);
    }

    public SpanUtils d(@IntRange(from = 0) int i2, @ColorInt int i3) {
        j(2);
        this.X = i2;
        this.Y = i3;
        return this;
    }

    private void j(int i2) {
        k();
        this.aa = i2;
    }

    public SpannableStringBuilder i() {
        k();
        return this.Z;
    }

    private void k() {
        if (this.aa == 0) {
            l();
        } else if (this.aa == 1) {
            m();
        } else if (this.aa == 2) {
            n();
        }
        j();
    }

    private void l() {
        if (this.g.length() != 0) {
            int length = this.Z.length();
            this.Z.append(this.g);
            int length2 = this.Z.length();
            if (this.i != e) {
                this.Z.setSpan(new ForegroundColorSpan(this.i), length, length2, this.h);
            }
            if (this.j != e) {
                this.Z.setSpan(new BackgroundColorSpan(this.j), length, length2, this.h);
            }
            if (this.p != -1) {
                this.Z.setSpan(new LeadingMarginSpan.Standard(this.p, this.q), length, length2, this.h);
            }
            if (this.m != e) {
                this.Z.setSpan(new CustomQuoteSpan(this.m, this.n, this.o), length, length2, this.h);
            }
            if (this.r != e) {
                this.Z.setSpan(new CustomBulletSpan(this.r, this.s, this.t), length, length2, this.h);
            }
            if (this.u != -1) {
                this.Z.setSpan(new AbsoluteSizeSpan(this.u, this.v), length, length2, this.h);
            }
            if (this.w != -1.0f) {
                this.Z.setSpan(new RelativeSizeSpan(this.w), length, length2, this.h);
            }
            if (this.x != -1.0f) {
                this.Z.setSpan(new ScaleXSpan(this.x), length, length2, this.h);
            }
            if (this.k != -1) {
                this.Z.setSpan(new CustomLineHeightSpan(this.k, this.l), length, length2, this.h);
            }
            if (this.y) {
                this.Z.setSpan(new StrikethroughSpan(), length, length2, this.h);
            }
            if (this.z) {
                this.Z.setSpan(new UnderlineSpan(), length, length2, this.h);
            }
            if (this.A) {
                this.Z.setSpan(new SuperscriptSpan(), length, length2, this.h);
            }
            if (this.B) {
                this.Z.setSpan(new SubscriptSpan(), length, length2, this.h);
            }
            if (this.C) {
                this.Z.setSpan(new StyleSpan(1), length, length2, this.h);
            }
            if (this.D) {
                this.Z.setSpan(new StyleSpan(2), length, length2, this.h);
            }
            if (this.E) {
                this.Z.setSpan(new StyleSpan(3), length, length2, this.h);
            }
            if (this.F != null) {
                this.Z.setSpan(new TypefaceSpan(this.F), length, length2, this.h);
            }
            if (this.G != null) {
                this.Z.setSpan(new CustomTypefaceSpan(this.G), length, length2, this.h);
            }
            if (this.H != null) {
                this.Z.setSpan(new AlignmentSpan.Standard(this.H), length, length2, this.h);
            }
            if (this.I != null) {
                this.Z.setSpan(this.I, length, length2, this.h);
            }
            if (this.J != null) {
                this.Z.setSpan(new URLSpan(this.J), length, length2, this.h);
            }
            if (this.K != -1.0f) {
                this.Z.setSpan(new MaskFilterSpan(new BlurMaskFilter(this.K, this.L)), length, length2, this.h);
            }
            if (this.M != null) {
                this.Z.setSpan(new ShaderSpan(this.M), length, length2, this.h);
            }
            if (this.N != -1.0f) {
                this.Z.setSpan(new ShadowSpan(this.N, this.O, this.P, this.Q), length, length2, this.h);
            }
            if (this.R != null) {
                for (Object span : this.R) {
                    this.Z.setSpan(span, length, length2, this.h);
                }
            }
        }
    }

    private void m() {
        int length = this.Z.length();
        this.Z.append("<img>");
        int i2 = length + 5;
        if (this.S != null) {
            this.Z.setSpan(new CustomImageSpan(this.S, this.W), length, i2, this.h);
        } else if (this.T != null) {
            this.Z.setSpan(new CustomImageSpan(this.T, this.W), length, i2, this.h);
        } else if (this.U != null) {
            this.Z.setSpan(new CustomImageSpan(this.U, this.W), length, i2, this.h);
        } else if (this.V != -1) {
            this.Z.setSpan(new CustomImageSpan(this.V, this.W), length, i2, this.h);
        }
    }

    private void n() {
        int length = this.Z.length();
        this.Z.append("< >");
        this.Z.setSpan(new SpaceSpan(this.X, this.Y), length, length + 3, this.h);
    }

    class CustomLineHeightSpan extends CharacterStyle implements LineHeightSpan {

        /* renamed from: a  reason: collision with root package name */
        static final int f23275a = 2;
        static final int b = 3;
        final int c;
        private final int e;

        public void updateDrawState(TextPaint textPaint) {
        }

        CustomLineHeightSpan(int i, int i2) {
            this.e = i;
            this.c = i2;
        }

        public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) {
            int i5 = this.e - (((fontMetricsInt.descent + i4) - fontMetricsInt.ascent) - i3);
            if (this.c == 3) {
                fontMetricsInt.descent += i5;
            } else if (this.c == 2) {
                int i6 = i5 / 2;
                fontMetricsInt.descent += i6;
                fontMetricsInt.ascent -= i6;
            } else {
                fontMetricsInt.ascent -= i5;
            }
            int i7 = this.e - (((i4 + fontMetricsInt.bottom) - fontMetricsInt.top) - i3);
            if (this.c == 3) {
                fontMetricsInt.top += i7;
            } else if (this.c == 2) {
                int i8 = i7 / 2;
                fontMetricsInt.bottom += i8;
                fontMetricsInt.top -= i8;
            } else {
                fontMetricsInt.top -= i7;
            }
        }
    }

    class SpaceSpan extends ReplacementSpan {
        private final int b;
        private final int c;

        private SpaceSpan(SpanUtils spanUtils, int i) {
            this(i, 0);
        }

        private SpaceSpan(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        public int getSize(@NonNull Paint paint, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
            return this.b;
        }

        public void draw(@NonNull Canvas canvas, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            Paint.Style style = paint.getStyle();
            int color = paint.getColor();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.c);
            canvas.drawRect(f, (float) i3, f + ((float) this.b), (float) i5, paint);
            paint.setStyle(style);
            paint.setColor(color);
        }
    }

    class CustomQuoteSpan implements LeadingMarginSpan {
        private final int b;
        private final int c;
        private final int d;

        private CustomQuoteSpan(int i, int i2, int i3) {
            this.b = i;
            this.c = i2;
            this.d = i3;
        }

        public int getLeadingMargin(boolean z) {
            return this.c + this.d;
        }

        public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            Paint paint2 = paint;
            int i8 = i;
            Paint.Style style = paint.getStyle();
            int color = paint.getColor();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.b);
            canvas.drawRect((float) i8, (float) i3, (float) (i8 + (this.c * i2)), (float) i5, paint);
            paint.setStyle(style);
            paint.setColor(color);
        }
    }

    class CustomBulletSpan implements LeadingMarginSpan {
        private final int b;
        private final int c;
        private final int d;
        private Path e;

        private CustomBulletSpan(int i, int i2, int i3) {
            this.e = null;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }

        public int getLeadingMargin(boolean z) {
            return (this.c * 2) + this.d;
        }

        public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            if (((Spanned) charSequence).getSpanStart(this) == i6) {
                Paint.Style style = paint.getStyle();
                int color = paint.getColor();
                paint.setColor(this.b);
                paint.setStyle(Paint.Style.FILL);
                if (canvas.isHardwareAccelerated()) {
                    if (this.e == null) {
                        this.e = new Path();
                        this.e.addCircle(0.0f, 0.0f, (float) this.c, Path.Direction.CW);
                    }
                    canvas.save();
                    canvas.translate((float) (i + (i2 * this.c)), ((float) (i3 + i5)) / 2.0f);
                    canvas.drawPath(this.e, paint);
                    canvas.restore();
                } else {
                    canvas.drawCircle((float) (i + (i2 * this.c)), ((float) (i3 + i5)) / 2.0f, (float) this.c, paint);
                }
                paint.setColor(color);
                paint.setStyle(style);
            }
        }
    }

    @SuppressLint({"ParcelCreator"})
    class CustomTypefaceSpan extends TypefaceSpan {
        private final Typeface b;

        private CustomTypefaceSpan(Typeface typeface) {
            super("");
            this.b = typeface;
        }

        public void updateDrawState(TextPaint textPaint) {
            a(textPaint, this.b);
        }

        public void updateMeasureState(TextPaint textPaint) {
            a(textPaint, this.b);
        }

        private void a(Paint paint, Typeface typeface) {
            int i;
            Typeface typeface2 = paint.getTypeface();
            if (typeface2 == null) {
                i = 0;
            } else {
                i = typeface2.getStyle();
            }
            int style = i & (typeface.getStyle() ^ -1);
            if ((style & 1) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((style & 2) != 0) {
                paint.setTextSkewX(-0.25f);
            }
            paint.getShader();
            paint.setTypeface(typeface);
        }
    }

    class CustomImageSpan extends CustomDynamicDrawableSpan {
        private Drawable h;
        private Uri i;
        private int j;

        private CustomImageSpan(Bitmap bitmap, int i2) {
            super(i2);
            this.h = new BitmapDrawable(Utils.a().getResources(), bitmap);
            this.h.setBounds(0, 0, this.h.getIntrinsicWidth(), this.h.getIntrinsicHeight());
        }

        private CustomImageSpan(Drawable drawable, int i2) {
            super(i2);
            this.h = drawable;
            this.h.setBounds(0, 0, this.h.getIntrinsicWidth(), this.h.getIntrinsicHeight());
        }

        private CustomImageSpan(Uri uri, int i2) {
            super(i2);
            this.i = uri;
        }

        private CustomImageSpan(int i2, @DrawableRes int i3) {
            super(i3);
            this.j = i2;
        }

        public Drawable a() {
            Drawable drawable;
            if (this.h != null) {
                return this.h;
            }
            BitmapDrawable bitmapDrawable = null;
            if (this.i != null) {
                try {
                    InputStream openInputStream = Utils.a().getContentResolver().openInputStream(this.i);
                    BitmapDrawable bitmapDrawable2 = new BitmapDrawable(Utils.a().getResources(), BitmapFactory.decodeStream(openInputStream));
                    try {
                        bitmapDrawable2.setBounds(0, 0, bitmapDrawable2.getIntrinsicWidth(), bitmapDrawable2.getIntrinsicHeight());
                        if (openInputStream != null) {
                            openInputStream.close();
                        }
                        return bitmapDrawable2;
                    } catch (Exception e) {
                        e = e;
                        bitmapDrawable = bitmapDrawable2;
                        Log.e("sms", "Failed to loaded content " + this.i, e);
                        return bitmapDrawable;
                    }
                } catch (Exception e2) {
                    e = e2;
                    Log.e("sms", "Failed to loaded content " + this.i, e);
                    return bitmapDrawable;
                }
            } else {
                try {
                    drawable = ContextCompat.getDrawable(Utils.a(), this.j);
                    try {
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    } catch (Exception unused) {
                    }
                } catch (Exception unused2) {
                    drawable = null;
                    Log.e("sms", "Unable to find resource: " + this.j);
                    return drawable;
                }
            }
        }
    }

    abstract class CustomDynamicDrawableSpan extends ReplacementSpan {

        /* renamed from: a  reason: collision with root package name */
        static final int f23274a = 0;
        static final int b = 1;
        static final int c = 2;
        static final int d = 3;
        final int e;
        private WeakReference<Drawable> g;

        public abstract Drawable a();

        private CustomDynamicDrawableSpan() {
            this.e = 0;
        }

        private CustomDynamicDrawableSpan(int i) {
            this.e = i;
        }

        public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            int i3;
            Rect bounds = b().getBounds();
            if (fontMetricsInt != null && (i3 = fontMetricsInt.bottom - fontMetricsInt.top) < bounds.height()) {
                if (this.e == 3) {
                    fontMetricsInt.top = fontMetricsInt.top;
                    fontMetricsInt.bottom = bounds.height() + fontMetricsInt.top;
                } else if (this.e == 2) {
                    int i4 = i3 / 4;
                    fontMetricsInt.top = ((-bounds.height()) / 2) - i4;
                    fontMetricsInt.bottom = (bounds.height() / 2) - i4;
                } else {
                    fontMetricsInt.top = (-bounds.height()) + fontMetricsInt.bottom;
                    fontMetricsInt.bottom = fontMetricsInt.bottom;
                }
                fontMetricsInt.ascent = fontMetricsInt.top;
                fontMetricsInt.descent = fontMetricsInt.bottom;
            }
            return bounds.right;
        }

        public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f2, int i3, int i4, int i5, @NonNull Paint paint) {
            float f3;
            Drawable b2 = b();
            Rect bounds = b2.getBounds();
            canvas.save();
            if (bounds.height() < i5 - i3) {
                if (this.e == 3) {
                    f3 = (float) i3;
                } else if (this.e == 2) {
                    f3 = (float) (((i5 + i3) - bounds.height()) / 2);
                } else if (this.e == 1) {
                    f3 = (float) (i4 - bounds.height());
                } else {
                    f3 = (float) (i5 - bounds.height());
                }
                canvas.translate(f2, f3);
            } else {
                canvas.translate(f2, (float) i3);
            }
            b2.draw(canvas);
            canvas.restore();
        }

        private Drawable b() {
            WeakReference<Drawable> weakReference = this.g;
            Drawable drawable = weakReference != null ? (Drawable) weakReference.get() : null;
            if (drawable != null) {
                return drawable;
            }
            Drawable a2 = a();
            this.g = new WeakReference<>(a2);
            return a2;
        }
    }

    class ShaderSpan extends CharacterStyle implements UpdateAppearance {
        private Shader b;

        private ShaderSpan(Shader shader) {
            this.b = shader;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setShader(this.b);
        }
    }

    class ShadowSpan extends CharacterStyle implements UpdateAppearance {
        private float b;
        private float c;
        private float d;
        private int e;

        private ShadowSpan(float f, float f2, float f3, int i) {
            this.b = f;
            this.c = f2;
            this.d = f3;
            this.e = i;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setShadowLayer(this.b, this.c, this.d, this.e);
        }
    }
}
