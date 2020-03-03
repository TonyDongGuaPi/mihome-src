package com.taobao.weex.ui.view.border;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class BorderDrawable extends Drawable {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int BORDER_BOTTOM_LEFT_RADIUS = 3;
    public static final int BORDER_BOTTOM_RIGHT_RADIUS = 2;
    public static final int BORDER_RADIUS_ALL = 5;
    public static final int BORDER_TOP_LEFT_RADIUS = 0;
    public static final int BORDER_TOP_RIGHT_RADIUS = 1;
    static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final BorderStyle DEFAULT_BORDER_STYLE = BorderStyle.SOLID;
    static final float DEFAULT_BORDER_WIDTH = 0.0f;
    private static final String TAG = "Border";
    private static BorderStyle[] sBorderStyle = BorderStyle.values();
    private int mAlpha = 255;
    @Nullable
    private SparseIntArray mBorderColor;
    private final BorderEdge mBorderEdge;
    @Nullable
    private CSSShorthand<CSSShorthand.CORNER> mBorderRadius;
    @Nullable
    private SparseIntArray mBorderStyle;
    @Nullable
    private CSSShorthand<CSSShorthand.EDGE> mBorderWidth;
    private BottomLeftCorner mBottomLeftCorner;
    private BottomRightCorner mBottomRightCorner;
    private int mColor = 0;
    private boolean mNeedUpdatePath = false;
    @Nullable
    private CSSShorthand<CSSShorthand.CORNER> mOverlappingBorderRadius;
    private final Paint mPaint = new Paint(1);
    @Nullable
    private Path mPathForBorderOutline;
    private RectF mRectBounds;
    private Shader mShader = null;
    private TopLeftCorner mTopLeftCorner;
    private TopRightCorner mTopRightCorner;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1063462553045468782L, "com/taobao/weex/ui/view/border/BorderDrawable", 238);
        $jacocoData = a2;
        return a2;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[236] = true;
        $jacocoInit[237] = true;
    }

    public BorderDrawable() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.mBorderEdge = new BorderEdge();
        $jacocoInit[2] = true;
    }

    public void draw(@NonNull Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        canvas.save();
        $jacocoInit[3] = true;
        updateBorderOutline();
        $jacocoInit[4] = true;
        this.mPaint.setAlpha(255);
        if (this.mPathForBorderOutline == null) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            int multiplyColorAlpha = WXViewUtils.multiplyColorAlpha(this.mColor, this.mAlpha);
            if (this.mShader != null) {
                $jacocoInit[7] = true;
                this.mPaint.setShader(this.mShader);
                $jacocoInit[8] = true;
                this.mPaint.setStyle(Paint.Style.FILL);
                $jacocoInit[9] = true;
                canvas.drawPath(this.mPathForBorderOutline, this.mPaint);
                $jacocoInit[10] = true;
                this.mPaint.setShader((Shader) null);
                $jacocoInit[11] = true;
            } else if ((multiplyColorAlpha >>> 24) == 0) {
                $jacocoInit[12] = true;
            } else {
                $jacocoInit[13] = true;
                this.mPaint.setColor(multiplyColorAlpha);
                $jacocoInit[14] = true;
                this.mPaint.setStyle(Paint.Style.FILL);
                $jacocoInit[15] = true;
                canvas.drawPath(this.mPathForBorderOutline, this.mPaint);
                $jacocoInit[16] = true;
                this.mPaint.setShader((Shader) null);
                $jacocoInit[17] = true;
            }
        }
        this.mPaint.setStyle(Paint.Style.STROKE);
        $jacocoInit[18] = true;
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        $jacocoInit[19] = true;
        drawBorders(canvas);
        $jacocoInit[20] = true;
        this.mPaint.setShader((Shader) null);
        $jacocoInit[21] = true;
        canvas.restore();
        $jacocoInit[22] = true;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onBoundsChange(rect);
        this.mNeedUpdatePath = true;
        $jacocoInit[23] = true;
    }

    public void setAlpha(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i == this.mAlpha) {
            $jacocoInit[24] = true;
        } else {
            this.mAlpha = i;
            $jacocoInit[25] = true;
            invalidateSelf();
            $jacocoInit[26] = true;
        }
        $jacocoInit[27] = true;
    }

    public int getAlpha() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mAlpha;
        $jacocoInit[28] = true;
        return i;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        $jacocoInit()[29] = true;
    }

    public int getOpacity() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mShader != null) {
            i = -1;
            $jacocoInit[30] = true;
        } else {
            int i2 = this.mColor;
            int i3 = this.mAlpha;
            $jacocoInit[31] = true;
            i = WXViewUtils.getOpacityFromColor(WXViewUtils.multiplyColorAlpha(i2, i3));
            $jacocoInit[32] = true;
        }
        $jacocoInit[33] = true;
        return i;
    }

    public void getOutline(@NonNull Outline outline) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT < 21) {
            $jacocoInit[34] = true;
        } else {
            if (this.mPathForBorderOutline != null) {
                $jacocoInit[35] = true;
            } else {
                this.mNeedUpdatePath = true;
                $jacocoInit[36] = true;
            }
            updateBorderOutline();
            $jacocoInit[37] = true;
            outline.setConvexPath(this.mPathForBorderOutline);
            $jacocoInit[38] = true;
        }
        $jacocoInit[39] = true;
    }

    public void setBorderWidth(CSSShorthand.EDGE edge, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mBorderWidth != null) {
            $jacocoInit[40] = true;
        } else {
            $jacocoInit[41] = true;
            this.mBorderWidth = new CSSShorthand<>();
            $jacocoInit[42] = true;
        }
        if (this.mBorderWidth.get(edge) == f) {
            $jacocoInit[43] = true;
        } else {
            $jacocoInit[44] = true;
            this.mBorderWidth.set(edge, f);
            this.mNeedUpdatePath = true;
            $jacocoInit[45] = true;
            invalidateSelf();
            $jacocoInit[46] = true;
        }
        $jacocoInit[47] = true;
    }

    /* access modifiers changed from: package-private */
    public float getBorderWidth(CSSShorthand.EDGE edge) {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mBorderWidth.get(edge);
        $jacocoInit[48] = true;
        return f;
    }

    public void setBorderRadius(CSSShorthand.CORNER corner, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mBorderRadius != null) {
            $jacocoInit[49] = true;
        } else {
            $jacocoInit[50] = true;
            this.mBorderRadius = new CSSShorthand<>();
            $jacocoInit[51] = true;
        }
        if (this.mBorderRadius.get(corner) != f) {
            $jacocoInit[52] = true;
        } else {
            if (corner != CSSShorthand.CORNER.ALL) {
                $jacocoInit[53] = true;
            } else {
                CSSShorthand<CSSShorthand.CORNER> cSSShorthand = this.mBorderRadius;
                CSSShorthand.CORNER corner2 = CSSShorthand.CORNER.BORDER_TOP_LEFT;
                $jacocoInit[54] = true;
                if (f != cSSShorthand.get(corner2)) {
                    $jacocoInit[55] = true;
                } else {
                    CSSShorthand<CSSShorthand.CORNER> cSSShorthand2 = this.mBorderRadius;
                    CSSShorthand.CORNER corner3 = CSSShorthand.CORNER.BORDER_TOP_RIGHT;
                    $jacocoInit[56] = true;
                    if (f != cSSShorthand2.get(corner3)) {
                        $jacocoInit[57] = true;
                    } else {
                        CSSShorthand<CSSShorthand.CORNER> cSSShorthand3 = this.mBorderRadius;
                        CSSShorthand.CORNER corner4 = CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT;
                        $jacocoInit[58] = true;
                        if (f != cSSShorthand3.get(corner4)) {
                            $jacocoInit[59] = true;
                        } else {
                            CSSShorthand<CSSShorthand.CORNER> cSSShorthand4 = this.mBorderRadius;
                            CSSShorthand.CORNER corner5 = CSSShorthand.CORNER.BORDER_BOTTOM_LEFT;
                            $jacocoInit[60] = true;
                            if (f == cSSShorthand4.get(corner5)) {
                                $jacocoInit[61] = true;
                            } else {
                                $jacocoInit[62] = true;
                            }
                        }
                    }
                }
            }
            $jacocoInit[65] = true;
        }
        this.mBorderRadius.set(corner, f);
        this.mNeedUpdatePath = true;
        $jacocoInit[63] = true;
        invalidateSelf();
        $jacocoInit[64] = true;
        $jacocoInit[65] = true;
    }

    @NonNull
    public float[] getBorderRadius(RectF rectF) {
        boolean[] $jacocoInit = $jacocoInit();
        prepareBorderRadius(rectF);
        if (this.mOverlappingBorderRadius != null) {
            $jacocoInit[66] = true;
        } else {
            $jacocoInit[67] = true;
            this.mOverlappingBorderRadius = new CSSShorthand<>();
            $jacocoInit[68] = true;
        }
        float f = this.mOverlappingBorderRadius.get(CSSShorthand.CORNER.BORDER_TOP_LEFT);
        $jacocoInit[69] = true;
        float f2 = this.mOverlappingBorderRadius.get(CSSShorthand.CORNER.BORDER_TOP_RIGHT);
        $jacocoInit[70] = true;
        float f3 = this.mOverlappingBorderRadius.get(CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT);
        $jacocoInit[71] = true;
        float f4 = this.mOverlappingBorderRadius.get(CSSShorthand.CORNER.BORDER_BOTTOM_LEFT);
        float[] fArr = {f, f, f2, f2, f3, f3, f4, f4};
        $jacocoInit[72] = true;
        return fArr;
    }

    @NonNull
    public float[] getBorderInnerRadius(RectF rectF) {
        boolean[] $jacocoInit = $jacocoInit();
        prepareBorderRadius(rectF);
        if (this.mOverlappingBorderRadius != null) {
            $jacocoInit[73] = true;
        } else {
            $jacocoInit[74] = true;
            this.mOverlappingBorderRadius = new CSSShorthand<>();
            $jacocoInit[75] = true;
        }
        float f = this.mOverlappingBorderRadius.get(CSSShorthand.CORNER.BORDER_TOP_LEFT);
        $jacocoInit[76] = true;
        float f2 = this.mOverlappingBorderRadius.get(CSSShorthand.CORNER.BORDER_TOP_RIGHT);
        $jacocoInit[77] = true;
        float f3 = this.mOverlappingBorderRadius.get(CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT);
        $jacocoInit[78] = true;
        float f4 = this.mOverlappingBorderRadius.get(CSSShorthand.CORNER.BORDER_BOTTOM_LEFT);
        if (this.mBorderWidth == null) {
            $jacocoInit[79] = true;
        } else {
            $jacocoInit[80] = true;
            f = Math.max(f - this.mBorderWidth.get(CSSShorthand.EDGE.TOP), 0.0f);
            $jacocoInit[81] = true;
            f2 = Math.max(f2 - this.mBorderWidth.get(CSSShorthand.EDGE.TOP), 0.0f);
            $jacocoInit[82] = true;
            f3 = Math.max(f3 - this.mBorderWidth.get(CSSShorthand.EDGE.BOTTOM), 0.0f);
            $jacocoInit[83] = true;
            f4 = Math.max(f4 - this.mBorderWidth.get(CSSShorthand.EDGE.BOTTOM), 0.0f);
            $jacocoInit[84] = true;
        }
        float[] fArr = {f, f, f2, f2, f3, f3, f4, f4};
        $jacocoInit[85] = true;
        return fArr;
    }

    public void setBorderColor(CSSShorthand.EDGE edge, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mBorderColor != null) {
            $jacocoInit[86] = true;
        } else {
            $jacocoInit[87] = true;
            this.mBorderColor = new SparseIntArray(5);
            $jacocoInit[88] = true;
            this.mBorderColor.put(CSSShorthand.EDGE.ALL.ordinal(), -16777216);
            $jacocoInit[89] = true;
        }
        if (getBorderColor(edge) == i) {
            $jacocoInit[90] = true;
        } else {
            $jacocoInit[91] = true;
            BorderUtil.updateSparseArray(this.mBorderColor, edge.ordinal(), i);
            $jacocoInit[92] = true;
            invalidateSelf();
            $jacocoInit[93] = true;
        }
        $jacocoInit[94] = true;
    }

    /* access modifiers changed from: package-private */
    public int getBorderColor(CSSShorthand.EDGE edge) {
        boolean[] $jacocoInit = $jacocoInit();
        int fetchFromSparseArray = BorderUtil.fetchFromSparseArray(this.mBorderColor, edge.ordinal(), -16777216);
        $jacocoInit[95] = true;
        return fetchFromSparseArray;
    }

    public void setBorderStyle(CSSShorthand.EDGE edge, @NonNull String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mBorderStyle != null) {
            $jacocoInit[96] = true;
        } else {
            $jacocoInit[97] = true;
            this.mBorderStyle = new SparseIntArray(5);
            $jacocoInit[98] = true;
            this.mBorderStyle.put(CSSShorthand.EDGE.ALL.ordinal(), DEFAULT_BORDER_STYLE.ordinal());
            try {
                $jacocoInit[99] = true;
            } catch (IllegalArgumentException e) {
                $jacocoInit[106] = true;
                WXLogUtils.e(TAG, WXLogUtils.getStackTrace(e));
                $jacocoInit[107] = true;
            }
        }
        int ordinal = BorderStyle.valueOf(str.toUpperCase(Locale.US)).ordinal();
        $jacocoInit[100] = true;
        if (getBorderStyle(edge) == ordinal) {
            $jacocoInit[101] = true;
        } else {
            $jacocoInit[102] = true;
            BorderUtil.updateSparseArray(this.mBorderStyle, edge.ordinal(), ordinal);
            $jacocoInit[103] = true;
            invalidateSelf();
            $jacocoInit[104] = true;
        }
        $jacocoInit[105] = true;
        $jacocoInit[108] = true;
    }

    /* access modifiers changed from: package-private */
    public int getBorderStyle(CSSShorthand.EDGE edge) {
        boolean[] $jacocoInit = $jacocoInit();
        int fetchFromSparseArray = BorderUtil.fetchFromSparseArray(this.mBorderStyle, edge.ordinal(), BorderStyle.SOLID.ordinal());
        $jacocoInit[109] = true;
        return fetchFromSparseArray;
    }

    public int getColor() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mColor;
        $jacocoInit[110] = true;
        return i;
    }

    public void setColor(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mColor = i;
        $jacocoInit[111] = true;
        invalidateSelf();
        $jacocoInit[112] = true;
    }

    public void setImage(Shader shader) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mShader = shader;
        $jacocoInit[113] = true;
        invalidateSelf();
        $jacocoInit[114] = true;
    }

    public boolean hasImage() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mShader != null) {
            $jacocoInit[115] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[116] = true;
        }
        $jacocoInit[117] = true;
        return z;
    }

    public boolean isRounded() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mBorderRadius == null) {
            $jacocoInit[118] = true;
        } else {
            CSSShorthand<CSSShorthand.CORNER> cSSShorthand = this.mBorderRadius;
            CSSShorthand.CORNER corner = CSSShorthand.CORNER.BORDER_TOP_LEFT;
            $jacocoInit[119] = true;
            if (cSSShorthand.get(corner) != 0.0f) {
                $jacocoInit[120] = true;
            } else {
                CSSShorthand<CSSShorthand.CORNER> cSSShorthand2 = this.mBorderRadius;
                CSSShorthand.CORNER corner2 = CSSShorthand.CORNER.BORDER_TOP_RIGHT;
                $jacocoInit[121] = true;
                if (cSSShorthand2.get(corner2) != 0.0f) {
                    $jacocoInit[122] = true;
                } else {
                    CSSShorthand<CSSShorthand.CORNER> cSSShorthand3 = this.mBorderRadius;
                    CSSShorthand.CORNER corner3 = CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT;
                    $jacocoInit[123] = true;
                    if (cSSShorthand3.get(corner3) != 0.0f) {
                        $jacocoInit[124] = true;
                    } else {
                        CSSShorthand<CSSShorthand.CORNER> cSSShorthand4 = this.mBorderRadius;
                        CSSShorthand.CORNER corner4 = CSSShorthand.CORNER.BORDER_BOTTOM_LEFT;
                        $jacocoInit[125] = true;
                        if (cSSShorthand4.get(corner4) == 0.0f) {
                            $jacocoInit[126] = true;
                        } else {
                            $jacocoInit[127] = true;
                        }
                    }
                }
            }
            $jacocoInit[128] = true;
            z = true;
            $jacocoInit[130] = true;
            return z;
        }
        z = false;
        $jacocoInit[129] = true;
        $jacocoInit[130] = true;
        return z;
    }

    @NonNull
    public Path getContentPath(@NonNull RectF rectF) {
        boolean[] $jacocoInit = $jacocoInit();
        Path path = new Path();
        $jacocoInit[131] = true;
        prepareBorderPath(0, 0, 0, 0, rectF, path);
        $jacocoInit[132] = true;
        return path;
    }

    private void updateBorderOutline() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mNeedUpdatePath) {
            $jacocoInit[133] = true;
        } else {
            this.mNeedUpdatePath = false;
            if (this.mPathForBorderOutline != null) {
                $jacocoInit[134] = true;
            } else {
                $jacocoInit[135] = true;
                this.mPathForBorderOutline = new Path();
                $jacocoInit[136] = true;
            }
            this.mPathForBorderOutline.reset();
            $jacocoInit[137] = true;
            prepareBorderPath(0, 0, 0, 0, new RectF(getBounds()), this.mPathForBorderOutline);
            $jacocoInit[138] = true;
        }
        $jacocoInit[139] = true;
    }

    private void prepareBorderPath(int i, int i2, int i3, int i4, @NonNull RectF rectF, @NonNull Path path) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mBorderRadius != null) {
            $jacocoInit[140] = true;
            prepareBorderRadius(rectF);
            if (this.mOverlappingBorderRadius != null) {
                $jacocoInit[141] = true;
            } else {
                $jacocoInit[142] = true;
                this.mOverlappingBorderRadius = new CSSShorthand<>();
                $jacocoInit[143] = true;
            }
            float f = this.mOverlappingBorderRadius.get(CSSShorthand.CORNER.BORDER_TOP_LEFT);
            $jacocoInit[144] = true;
            float f2 = this.mOverlappingBorderRadius.get(CSSShorthand.CORNER.BORDER_TOP_RIGHT);
            $jacocoInit[145] = true;
            float f3 = this.mOverlappingBorderRadius.get(CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT);
            $jacocoInit[146] = true;
            float f4 = this.mOverlappingBorderRadius.get(CSSShorthand.CORNER.BORDER_BOTTOM_LEFT);
            $jacocoInit[147] = true;
            float f5 = (float) i4;
            float f6 = (float) i;
            float f7 = (float) i2;
            float f8 = (float) i3;
            path.addRoundRect(rectF, new float[]{f - f5, f - f6, f2 - f7, f2 - f6, f3 - f7, f3 - f8, f4 - f5, f4 - f8}, Path.Direction.CW);
            $jacocoInit[148] = true;
        } else {
            path.addRect(rectF, Path.Direction.CW);
            $jacocoInit[149] = true;
        }
        $jacocoInit[150] = true;
    }

    private void prepareBorderRadius(@NonNull RectF rectF) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mBorderRadius == null) {
            $jacocoInit[151] = true;
        } else {
            $jacocoInit[152] = true;
            float scaleFactor = getScaleFactor(rectF);
            if (this.mOverlappingBorderRadius != null) {
                $jacocoInit[153] = true;
            } else {
                $jacocoInit[154] = true;
                this.mOverlappingBorderRadius = new CSSShorthand<>();
                $jacocoInit[155] = true;
            }
            if (Float.isNaN(scaleFactor)) {
                $jacocoInit[156] = true;
            } else if (scaleFactor >= 1.0f) {
                $jacocoInit[157] = true;
            } else {
                CSSShorthand<CSSShorthand.CORNER> cSSShorthand = this.mOverlappingBorderRadius;
                CSSShorthand.CORNER corner = CSSShorthand.CORNER.BORDER_TOP_LEFT;
                CSSShorthand<CSSShorthand.CORNER> cSSShorthand2 = this.mBorderRadius;
                CSSShorthand.CORNER corner2 = CSSShorthand.CORNER.BORDER_TOP_LEFT;
                $jacocoInit[158] = true;
                $jacocoInit[159] = true;
                cSSShorthand.set(corner, cSSShorthand2.get(corner2) * scaleFactor);
                CSSShorthand<CSSShorthand.CORNER> cSSShorthand3 = this.mOverlappingBorderRadius;
                CSSShorthand.CORNER corner3 = CSSShorthand.CORNER.BORDER_TOP_RIGHT;
                CSSShorthand<CSSShorthand.CORNER> cSSShorthand4 = this.mBorderRadius;
                CSSShorthand.CORNER corner4 = CSSShorthand.CORNER.BORDER_TOP_RIGHT;
                $jacocoInit[160] = true;
                $jacocoInit[161] = true;
                cSSShorthand3.set(corner3, cSSShorthand4.get(corner4) * scaleFactor);
                CSSShorthand<CSSShorthand.CORNER> cSSShorthand5 = this.mOverlappingBorderRadius;
                CSSShorthand.CORNER corner5 = CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT;
                CSSShorthand<CSSShorthand.CORNER> cSSShorthand6 = this.mBorderRadius;
                CSSShorthand.CORNER corner6 = CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT;
                $jacocoInit[162] = true;
                $jacocoInit[163] = true;
                cSSShorthand5.set(corner5, cSSShorthand6.get(corner6) * scaleFactor);
                CSSShorthand<CSSShorthand.CORNER> cSSShorthand7 = this.mOverlappingBorderRadius;
                CSSShorthand.CORNER corner7 = CSSShorthand.CORNER.BORDER_BOTTOM_LEFT;
                CSSShorthand<CSSShorthand.CORNER> cSSShorthand8 = this.mBorderRadius;
                CSSShorthand.CORNER corner8 = CSSShorthand.CORNER.BORDER_BOTTOM_LEFT;
                $jacocoInit[164] = true;
                $jacocoInit[165] = true;
                cSSShorthand7.set(corner7, cSSShorthand8.get(corner8) * scaleFactor);
                $jacocoInit[166] = true;
            }
            CSSShorthand<CSSShorthand.CORNER> cSSShorthand9 = this.mOverlappingBorderRadius;
            CSSShorthand.CORNER corner9 = CSSShorthand.CORNER.BORDER_TOP_LEFT;
            CSSShorthand<CSSShorthand.CORNER> cSSShorthand10 = this.mBorderRadius;
            CSSShorthand.CORNER corner10 = CSSShorthand.CORNER.BORDER_TOP_LEFT;
            $jacocoInit[167] = true;
            float f = cSSShorthand10.get(corner10);
            $jacocoInit[168] = true;
            cSSShorthand9.set(corner9, f);
            CSSShorthand<CSSShorthand.CORNER> cSSShorthand11 = this.mOverlappingBorderRadius;
            CSSShorthand.CORNER corner11 = CSSShorthand.CORNER.BORDER_TOP_RIGHT;
            CSSShorthand<CSSShorthand.CORNER> cSSShorthand12 = this.mBorderRadius;
            CSSShorthand.CORNER corner12 = CSSShorthand.CORNER.BORDER_TOP_RIGHT;
            $jacocoInit[169] = true;
            float f2 = cSSShorthand12.get(corner12);
            $jacocoInit[170] = true;
            cSSShorthand11.set(corner11, f2);
            CSSShorthand<CSSShorthand.CORNER> cSSShorthand13 = this.mOverlappingBorderRadius;
            CSSShorthand.CORNER corner13 = CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT;
            CSSShorthand<CSSShorthand.CORNER> cSSShorthand14 = this.mBorderRadius;
            CSSShorthand.CORNER corner14 = CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT;
            $jacocoInit[171] = true;
            float f3 = cSSShorthand14.get(corner14);
            $jacocoInit[172] = true;
            cSSShorthand13.set(corner13, f3);
            CSSShorthand<CSSShorthand.CORNER> cSSShorthand15 = this.mOverlappingBorderRadius;
            CSSShorthand.CORNER corner15 = CSSShorthand.CORNER.BORDER_BOTTOM_LEFT;
            CSSShorthand<CSSShorthand.CORNER> cSSShorthand16 = this.mBorderRadius;
            CSSShorthand.CORNER corner16 = CSSShorthand.CORNER.BORDER_BOTTOM_LEFT;
            $jacocoInit[173] = true;
            float f4 = cSSShorthand16.get(corner16);
            $jacocoInit[174] = true;
            cSSShorthand15.set(corner15, f4);
            $jacocoInit[175] = true;
        }
        $jacocoInit[176] = true;
    }

    private float getScaleFactor(@NonNull RectF rectF) {
        float f;
        boolean[] $jacocoInit = $jacocoInit();
        float f2 = this.mBorderRadius.get(CSSShorthand.CORNER.BORDER_TOP_LEFT);
        CSSShorthand<CSSShorthand.CORNER> cSSShorthand = this.mBorderRadius;
        CSSShorthand.CORNER corner = CSSShorthand.CORNER.BORDER_TOP_RIGHT;
        $jacocoInit[177] = true;
        float f3 = f2 + cSSShorthand.get(corner);
        $jacocoInit[178] = true;
        float f4 = this.mBorderRadius.get(CSSShorthand.CORNER.BORDER_TOP_RIGHT);
        CSSShorthand<CSSShorthand.CORNER> cSSShorthand2 = this.mBorderRadius;
        CSSShorthand.CORNER corner2 = CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT;
        $jacocoInit[179] = true;
        float f5 = f4 + cSSShorthand2.get(corner2);
        $jacocoInit[180] = true;
        float f6 = this.mBorderRadius.get(CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT);
        CSSShorthand<CSSShorthand.CORNER> cSSShorthand3 = this.mBorderRadius;
        CSSShorthand.CORNER corner3 = CSSShorthand.CORNER.BORDER_BOTTOM_LEFT;
        $jacocoInit[181] = true;
        float f7 = f6 + cSSShorthand3.get(corner3);
        $jacocoInit[182] = true;
        float f8 = this.mBorderRadius.get(CSSShorthand.CORNER.BORDER_BOTTOM_LEFT);
        CSSShorthand<CSSShorthand.CORNER> cSSShorthand4 = this.mBorderRadius;
        CSSShorthand.CORNER corner4 = CSSShorthand.CORNER.BORDER_TOP_LEFT;
        $jacocoInit[183] = true;
        float f9 = f8 + cSSShorthand4.get(corner4);
        $jacocoInit[184] = true;
        ArrayList arrayList = new ArrayList(4);
        $jacocoInit[185] = true;
        updateFactor(arrayList, rectF.width(), f3);
        $jacocoInit[186] = true;
        updateFactor(arrayList, rectF.height(), f5);
        $jacocoInit[187] = true;
        updateFactor(arrayList, rectF.width(), f7);
        $jacocoInit[188] = true;
        updateFactor(arrayList, rectF.height(), f9);
        $jacocoInit[189] = true;
        if (arrayList.isEmpty()) {
            f = Float.NaN;
            $jacocoInit[190] = true;
        } else {
            f = ((Float) Collections.min(arrayList)).floatValue();
            $jacocoInit[191] = true;
        }
        $jacocoInit[192] = true;
        return f;
    }

    private void updateFactor(@NonNull List<Float> list, float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (f2 == 0.0f) {
            $jacocoInit[193] = true;
        } else {
            $jacocoInit[194] = true;
            list.add(Float.valueOf(f / f2));
            $jacocoInit[195] = true;
        }
        $jacocoInit[196] = true;
    }

    private void drawBorders(Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRectBounds == null) {
            $jacocoInit[197] = true;
            this.mRectBounds = new RectF(getBounds());
            $jacocoInit[198] = true;
        } else {
            this.mRectBounds.set(getBounds());
            $jacocoInit[199] = true;
        }
        if (this.mBorderWidth == null) {
            $jacocoInit[200] = true;
            return;
        }
        float f = this.mBorderWidth.get(CSSShorthand.EDGE.LEFT);
        $jacocoInit[201] = true;
        float f2 = this.mBorderWidth.get(CSSShorthand.EDGE.TOP);
        $jacocoInit[202] = true;
        float f3 = this.mBorderWidth.get(CSSShorthand.EDGE.BOTTOM);
        $jacocoInit[203] = true;
        float f4 = this.mBorderWidth.get(CSSShorthand.EDGE.RIGHT);
        if (this.mTopLeftCorner != null) {
            $jacocoInit[204] = true;
        } else {
            $jacocoInit[205] = true;
            this.mTopLeftCorner = new TopLeftCorner();
            $jacocoInit[206] = true;
        }
        this.mTopLeftCorner.set(getBorderRadius(CSSShorthand.CORNER.BORDER_TOP_LEFT), f, f2, this.mRectBounds);
        if (this.mTopRightCorner != null) {
            $jacocoInit[207] = true;
        } else {
            $jacocoInit[208] = true;
            this.mTopRightCorner = new TopRightCorner();
            $jacocoInit[209] = true;
        }
        this.mTopRightCorner.set(getBorderRadius(CSSShorthand.CORNER.BORDER_TOP_RIGHT), f2, f4, this.mRectBounds);
        if (this.mBottomRightCorner != null) {
            $jacocoInit[210] = true;
        } else {
            $jacocoInit[211] = true;
            this.mBottomRightCorner = new BottomRightCorner();
            $jacocoInit[212] = true;
        }
        this.mBottomRightCorner.set(getBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_RIGHT), f4, f3, this.mRectBounds);
        if (this.mBottomLeftCorner != null) {
            $jacocoInit[213] = true;
        } else {
            $jacocoInit[214] = true;
            this.mBottomLeftCorner = new BottomLeftCorner();
            $jacocoInit[215] = true;
        }
        this.mBottomLeftCorner.set(getBorderRadius(CSSShorthand.CORNER.BORDER_BOTTOM_LEFT), f3, f, this.mRectBounds);
        $jacocoInit[216] = true;
        drawOneSide(canvas, this.mBorderEdge.set(this.mTopLeftCorner, this.mTopRightCorner, f2, CSSShorthand.EDGE.TOP));
        $jacocoInit[217] = true;
        drawOneSide(canvas, this.mBorderEdge.set(this.mTopRightCorner, this.mBottomRightCorner, f4, CSSShorthand.EDGE.RIGHT));
        $jacocoInit[218] = true;
        drawOneSide(canvas, this.mBorderEdge.set(this.mBottomRightCorner, this.mBottomLeftCorner, f3, CSSShorthand.EDGE.BOTTOM));
        $jacocoInit[219] = true;
        drawOneSide(canvas, this.mBorderEdge.set(this.mBottomLeftCorner, this.mTopLeftCorner, f, CSSShorthand.EDGE.LEFT));
        $jacocoInit[220] = true;
    }

    private float getBorderRadius(CSSShorthand.CORNER corner) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mOverlappingBorderRadius != null) {
            $jacocoInit[221] = true;
            float f = this.mOverlappingBorderRadius.get(corner);
            $jacocoInit[222] = true;
            return f;
        }
        $jacocoInit[223] = true;
        return 0.0f;
    }

    private void drawOneSide(Canvas canvas, @NonNull BorderEdge borderEdge) {
        boolean[] $jacocoInit = $jacocoInit();
        if (0.0f == borderEdge.getBorderWidth()) {
            $jacocoInit[224] = true;
        } else {
            $jacocoInit[225] = true;
            preparePaint(borderEdge.getEdge());
            $jacocoInit[226] = true;
            borderEdge.drawEdge(canvas, this.mPaint);
            $jacocoInit[227] = true;
        }
        $jacocoInit[228] = true;
    }

    private void preparePaint(CSSShorthand.EDGE edge) {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mBorderWidth.get(edge);
        $jacocoInit[229] = true;
        int multiplyColorAlpha = WXViewUtils.multiplyColorAlpha(getBorderColor(edge), this.mAlpha);
        $jacocoInit[230] = true;
        BorderStyle borderStyle = sBorderStyle[getBorderStyle(edge)];
        $jacocoInit[231] = true;
        Shader lineShader = borderStyle.getLineShader(f, multiplyColorAlpha, edge);
        $jacocoInit[232] = true;
        this.mPaint.setShader(lineShader);
        $jacocoInit[233] = true;
        this.mPaint.setColor(multiplyColorAlpha);
        $jacocoInit[234] = true;
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        $jacocoInit[235] = true;
    }
}
