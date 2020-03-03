package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

final class SubtitlePainter {
    private static final float INNER_PADDING_RATIO = 0.125f;
    private static final String TAG = "SubtitlePainter";
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private int backgroundColor;
    private Rect bitmapRect;
    private float bottomPaddingFraction;
    private Bitmap cueBitmap;
    private float cueBitmapHeight;
    private float cueLine;
    private int cueLineAnchor;
    private int cueLineType;
    private float cuePosition;
    private int cuePositionAnchor;
    private float cueSize;
    private CharSequence cueText;
    private Layout.Alignment cueTextAlignment;
    private float cueTextSizePx;
    private float defaultTextSizePx;
    private int edgeColor;
    private int edgeType;
    private int foregroundColor;
    private final float outlineWidth;
    private final Paint paint;
    private int parentBottom;
    private int parentLeft;
    private int parentRight;
    private int parentTop;
    private final float shadowOffset;
    private final float shadowRadius;
    private final float spacingAdd;
    private final float spacingMult;
    private StaticLayout textLayout;
    private int textLeft;
    private int textPaddingX;
    private final TextPaint textPaint = new TextPaint();
    private int textTop;
    private int windowColor;

    public SubtitlePainter(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, new int[]{16843287, 16843288}, 0, 0);
        this.spacingAdd = (float) obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.spacingMult = obtainStyledAttributes.getFloat(1, 1.0f);
        obtainStyledAttributes.recycle();
        float round = (float) Math.round((((float) context.getResources().getDisplayMetrics().densityDpi) * 2.0f) / 160.0f);
        this.outlineWidth = round;
        this.shadowRadius = round;
        this.shadowOffset = round;
        this.textPaint.setAntiAlias(true);
        this.textPaint.setSubpixelText(true);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.FILL);
    }

    public void draw(Cue cue, boolean z, boolean z2, CaptionStyleCompat captionStyleCompat, float f, float f2, float f3, Canvas canvas, int i, int i2, int i3, int i4) {
        Cue cue2 = cue;
        boolean z3 = z;
        boolean z4 = z2;
        CaptionStyleCompat captionStyleCompat2 = captionStyleCompat;
        float f4 = f;
        float f5 = f2;
        float f6 = f3;
        Canvas canvas2 = canvas;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        boolean z5 = cue2.bitmap == null;
        int i9 = -16777216;
        if (z5) {
            if (!TextUtils.isEmpty(cue2.text)) {
                i9 = (!cue2.windowColorSet || !z3) ? captionStyleCompat2.windowColor : cue2.windowColor;
            } else {
                return;
            }
        }
        if (areCharSequencesEqual(this.cueText, cue2.text) && Util.areEqual(this.cueTextAlignment, cue2.textAlignment) && this.cueBitmap == cue2.bitmap && this.cueLine == cue2.line && this.cueLineType == cue2.lineType && Util.areEqual(Integer.valueOf(this.cueLineAnchor), Integer.valueOf(cue2.lineAnchor)) && this.cuePosition == cue2.position && Util.areEqual(Integer.valueOf(this.cuePositionAnchor), Integer.valueOf(cue2.positionAnchor)) && this.cueSize == cue2.size && this.cueBitmapHeight == cue2.bitmapHeight && this.applyEmbeddedStyles == z3 && this.applyEmbeddedFontSizes == z4 && this.foregroundColor == captionStyleCompat2.foregroundColor && this.backgroundColor == captionStyleCompat2.backgroundColor && this.windowColor == i9 && this.edgeType == captionStyleCompat2.edgeType && this.edgeColor == captionStyleCompat2.edgeColor && Util.areEqual(this.textPaint.getTypeface(), captionStyleCompat2.typeface) && this.defaultTextSizePx == f4 && this.cueTextSizePx == f5 && this.bottomPaddingFraction == f6 && this.parentLeft == i5 && this.parentTop == i6 && this.parentRight == i7 && this.parentBottom == i8) {
            drawLayout(canvas, z5);
            return;
        }
        Canvas canvas3 = canvas;
        this.cueText = cue2.text;
        this.cueTextAlignment = cue2.textAlignment;
        this.cueBitmap = cue2.bitmap;
        this.cueLine = cue2.line;
        this.cueLineType = cue2.lineType;
        this.cueLineAnchor = cue2.lineAnchor;
        this.cuePosition = cue2.position;
        this.cuePositionAnchor = cue2.positionAnchor;
        this.cueSize = cue2.size;
        this.cueBitmapHeight = cue2.bitmapHeight;
        this.applyEmbeddedStyles = z3;
        this.applyEmbeddedFontSizes = z4;
        this.foregroundColor = captionStyleCompat2.foregroundColor;
        this.backgroundColor = captionStyleCompat2.backgroundColor;
        this.windowColor = i9;
        this.edgeType = captionStyleCompat2.edgeType;
        this.edgeColor = captionStyleCompat2.edgeColor;
        this.textPaint.setTypeface(captionStyleCompat2.typeface);
        this.defaultTextSizePx = f4;
        this.cueTextSizePx = f5;
        this.bottomPaddingFraction = f6;
        this.parentLeft = i5;
        this.parentTop = i6;
        this.parentRight = i7;
        this.parentBottom = i8;
        if (z5) {
            setupTextLayout();
        } else {
            setupBitmapLayout();
        }
        drawLayout(canvas3, z5);
    }

    private void setupTextLayout() {
        SpannableStringBuilder spannableStringBuilder;
        int i;
        int i2;
        int i3;
        SpannableStringBuilder spannableStringBuilder2;
        int i4 = this.parentRight - this.parentLeft;
        int i5 = this.parentBottom - this.parentTop;
        this.textPaint.setTextSize(this.defaultTextSizePx);
        int i6 = (int) ((this.defaultTextSizePx * INNER_PADDING_RATIO) + 0.5f);
        int i7 = i6 * 2;
        int i8 = i4 - i7;
        if (this.cueSize != Float.MIN_VALUE) {
            i8 = (int) (((float) i8) * this.cueSize);
        }
        if (i8 <= 0) {
            Log.w(TAG, "Skipped drawing subtitle cue (insufficient space)");
            return;
        }
        CharSequence charSequence = this.cueText;
        if (!this.applyEmbeddedStyles) {
            charSequence = charSequence.toString();
        } else {
            if (!this.applyEmbeddedFontSizes) {
                spannableStringBuilder2 = new SpannableStringBuilder(charSequence);
                int length = spannableStringBuilder2.length();
                AbsoluteSizeSpan[] absoluteSizeSpanArr = (AbsoluteSizeSpan[]) spannableStringBuilder2.getSpans(0, length, AbsoluteSizeSpan.class);
                RelativeSizeSpan[] relativeSizeSpanArr = (RelativeSizeSpan[]) spannableStringBuilder2.getSpans(0, length, RelativeSizeSpan.class);
                for (AbsoluteSizeSpan removeSpan : absoluteSizeSpanArr) {
                    spannableStringBuilder2.removeSpan(removeSpan);
                }
                for (RelativeSizeSpan removeSpan2 : relativeSizeSpanArr) {
                    spannableStringBuilder2.removeSpan(removeSpan2);
                }
            } else if (this.cueTextSizePx > 0.0f) {
                spannableStringBuilder2 = new SpannableStringBuilder(charSequence);
                spannableStringBuilder2.setSpan(new AbsoluteSizeSpan((int) this.cueTextSizePx), 0, spannableStringBuilder2.length(), 16711680);
            }
            charSequence = spannableStringBuilder2;
        }
        if (Color.alpha(this.backgroundColor) > 0) {
            SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(charSequence);
            spannableStringBuilder3.setSpan(new BackgroundColorSpan(this.backgroundColor), 0, spannableStringBuilder3.length(), 16711680);
            spannableStringBuilder = spannableStringBuilder3;
        } else {
            spannableStringBuilder = charSequence;
        }
        Layout.Alignment alignment = this.cueTextAlignment == null ? Layout.Alignment.ALIGN_CENTER : this.cueTextAlignment;
        this.textLayout = new StaticLayout(spannableStringBuilder, this.textPaint, i8, alignment, this.spacingMult, this.spacingAdd, true);
        int height = this.textLayout.getHeight();
        int lineCount = this.textLayout.getLineCount();
        int i9 = 0;
        for (int i10 = 0; i10 < lineCount; i10++) {
            i9 = Math.max((int) Math.ceil((double) this.textLayout.getLineWidth(i10)), i9);
        }
        if (this.cueSize == Float.MIN_VALUE || i9 >= i8) {
            i8 = i9;
        }
        int i11 = i8 + i7;
        if (this.cuePosition != Float.MIN_VALUE) {
            int round = Math.round(((float) i4) * this.cuePosition) + this.parentLeft;
            if (this.cuePositionAnchor == 2) {
                round -= i11;
            } else if (this.cuePositionAnchor == 1) {
                round = ((round * 2) - i11) / 2;
            }
            i2 = Math.max(round, this.parentLeft);
            i = Math.min(i11 + i2, this.parentRight);
        } else {
            i2 = ((i4 - i11) / 2) + this.parentLeft;
            i = i2 + i11;
        }
        int i12 = i - i2;
        if (i12 <= 0) {
            Log.w(TAG, "Skipped drawing subtitle cue (invalid horizontal positioning)");
            return;
        }
        if (this.cueLine != Float.MIN_VALUE) {
            if (this.cueLineType == 0) {
                i3 = Math.round(((float) i5) * this.cueLine) + this.parentTop;
            } else {
                int lineBottom = this.textLayout.getLineBottom(0) - this.textLayout.getLineTop(0);
                if (this.cueLine >= 0.0f) {
                    i3 = Math.round(this.cueLine * ((float) lineBottom)) + this.parentTop;
                } else {
                    i3 = Math.round((this.cueLine + 1.0f) * ((float) lineBottom)) + this.parentBottom;
                }
            }
            if (this.cueLineAnchor == 2) {
                i3 -= height;
            } else if (this.cueLineAnchor == 1) {
                i3 = ((i3 * 2) - height) / 2;
            }
            if (i3 + height > this.parentBottom) {
                i3 = this.parentBottom - height;
            } else if (i3 < this.parentTop) {
                i3 = this.parentTop;
            }
        } else {
            i3 = (this.parentBottom - height) - ((int) (((float) i5) * this.bottomPaddingFraction));
        }
        this.textLayout = new StaticLayout(spannableStringBuilder, this.textPaint, i12, alignment, this.spacingMult, this.spacingAdd, true);
        this.textLeft = i2;
        this.textTop = i3;
        this.textPaddingX = i6;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupBitmapLayout() {
        /*
            r7 = this;
            int r0 = r7.parentRight
            int r1 = r7.parentLeft
            int r0 = r0 - r1
            int r1 = r7.parentBottom
            int r2 = r7.parentTop
            int r1 = r1 - r2
            int r2 = r7.parentLeft
            float r2 = (float) r2
            float r0 = (float) r0
            float r3 = r7.cuePosition
            float r3 = r3 * r0
            float r2 = r2 + r3
            int r3 = r7.parentTop
            float r3 = (float) r3
            float r1 = (float) r1
            float r4 = r7.cueLine
            float r4 = r4 * r1
            float r3 = r3 + r4
            float r4 = r7.cueSize
            float r0 = r0 * r4
            int r0 = java.lang.Math.round(r0)
            float r4 = r7.cueBitmapHeight
            r5 = 1
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 == 0) goto L_0x0034
            float r4 = r7.cueBitmapHeight
            float r1 = r1 * r4
            int r1 = java.lang.Math.round(r1)
            goto L_0x004a
        L_0x0034:
            float r1 = (float) r0
            android.graphics.Bitmap r4 = r7.cueBitmap
            int r4 = r4.getHeight()
            float r4 = (float) r4
            android.graphics.Bitmap r5 = r7.cueBitmap
            int r5 = r5.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            float r1 = r1 * r4
            int r1 = java.lang.Math.round(r1)
        L_0x004a:
            int r4 = r7.cueLineAnchor
            r5 = 1
            r6 = 2
            if (r4 != r6) goto L_0x0053
            float r4 = (float) r0
        L_0x0051:
            float r2 = r2 - r4
            goto L_0x005b
        L_0x0053:
            int r4 = r7.cueLineAnchor
            if (r4 != r5) goto L_0x005b
            int r4 = r0 / 2
            float r4 = (float) r4
            goto L_0x0051
        L_0x005b:
            int r2 = java.lang.Math.round(r2)
            int r4 = r7.cuePositionAnchor
            if (r4 != r6) goto L_0x0066
            float r4 = (float) r1
        L_0x0064:
            float r3 = r3 - r4
            goto L_0x006e
        L_0x0066:
            int r4 = r7.cuePositionAnchor
            if (r4 != r5) goto L_0x006e
            int r4 = r1 / 2
            float r4 = (float) r4
            goto L_0x0064
        L_0x006e:
            int r3 = java.lang.Math.round(r3)
            android.graphics.Rect r4 = new android.graphics.Rect
            int r0 = r0 + r2
            int r1 = r1 + r3
            r4.<init>(r2, r3, r0, r1)
            r7.bitmapRect = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.SubtitlePainter.setupBitmapLayout():void");
    }

    private void drawLayout(Canvas canvas, boolean z) {
        if (z) {
            drawTextLayout(canvas);
        } else {
            drawBitmapLayout(canvas);
        }
    }

    private void drawTextLayout(Canvas canvas) {
        int i;
        StaticLayout staticLayout = this.textLayout;
        if (staticLayout != null) {
            int save = canvas.save();
            canvas.translate((float) this.textLeft, (float) this.textTop);
            if (Color.alpha(this.windowColor) > 0) {
                this.paint.setColor(this.windowColor);
                canvas.drawRect((float) (-this.textPaddingX), 0.0f, (float) (staticLayout.getWidth() + this.textPaddingX), (float) staticLayout.getHeight(), this.paint);
            }
            boolean z = true;
            if (this.edgeType == 1) {
                this.textPaint.setStrokeJoin(Paint.Join.ROUND);
                this.textPaint.setStrokeWidth(this.outlineWidth);
                this.textPaint.setColor(this.edgeColor);
                this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                staticLayout.draw(canvas);
            } else if (this.edgeType == 2) {
                this.textPaint.setShadowLayer(this.shadowRadius, this.shadowOffset, this.shadowOffset, this.edgeColor);
            } else if (this.edgeType == 3 || this.edgeType == 4) {
                if (this.edgeType != 3) {
                    z = false;
                }
                int i2 = -1;
                if (z) {
                    i = -1;
                } else {
                    i = this.edgeColor;
                }
                if (z) {
                    i2 = this.edgeColor;
                }
                float f = this.shadowRadius / 2.0f;
                this.textPaint.setColor(this.foregroundColor);
                this.textPaint.setStyle(Paint.Style.FILL);
                float f2 = -f;
                this.textPaint.setShadowLayer(this.shadowRadius, f2, f2, i);
                staticLayout.draw(canvas);
                this.textPaint.setShadowLayer(this.shadowRadius, f, f, i2);
            }
            this.textPaint.setColor(this.foregroundColor);
            this.textPaint.setStyle(Paint.Style.FILL);
            staticLayout.draw(canvas);
            this.textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            canvas.restoreToCount(save);
        }
    }

    private void drawBitmapLayout(Canvas canvas) {
        canvas.drawBitmap(this.cueBitmap, (Rect) null, this.bitmapRect, (Paint) null);
    }

    private static boolean areCharSequencesEqual(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence == charSequence2 || (charSequence != null && charSequence.equals(charSequence2));
    }
}
