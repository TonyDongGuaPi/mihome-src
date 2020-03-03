package com.taobao.weex.layout.measurefunc;

import android.graphics.Canvas;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.WorkerThread;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.layout.MeasureMode;
import com.taobao.weex.ui.WXRenderManager;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXTextDecoration;
import com.taobao.weex.utils.WXDomUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXResourceUtils;
import com.taobao.weex.utils.WXUtils;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class TextContentBoxMeasurement extends ContentBoxMeasurement {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final Canvas DUMMY_CANVAS = new Canvas();
    private static final String ELLIPSIS = "…";
    private AtomicReference<Layout> atomicReference;
    private boolean hasBeenMeasured = false;
    @Nullable
    private Layout layout;
    private Layout.Alignment mAlignment;
    private int mColor;
    private String mFontFamily = null;
    private int mFontSize = -1;
    private int mFontStyle = -1;
    private int mFontWeight = -1;
    private boolean mIsColorSet = false;
    private int mLineHeight = -1;
    private int mNumberOfLines = -1;
    private String mText = null;
    private WXTextDecoration mTextDecoration = WXTextDecoration.NONE;
    private TextPaint mTextPaint;
    private float previousWidth = Float.NaN;
    @Nullable
    private Spanned spanned;
    private TextUtils.TruncateAt textOverflow;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4837378773713195078L, "com/taobao/weex/layout/measurefunc/TextContentBoxMeasurement", 188);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ AtomicReference access$000(TextContentBoxMeasurement textContentBoxMeasurement) {
        boolean[] $jacocoInit = $jacocoInit();
        AtomicReference<Layout> atomicReference2 = textContentBoxMeasurement.atomicReference;
        $jacocoInit[185] = true;
        return atomicReference2;
    }

    static /* synthetic */ WXComponent access$100(TextContentBoxMeasurement textContentBoxMeasurement) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = textContentBoxMeasurement.mComponent;
        $jacocoInit[186] = true;
        return wXComponent;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[187] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TextContentBoxMeasurement(WXComponent wXComponent) {
        super(wXComponent);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.atomicReference = new AtomicReference<>();
        $jacocoInit[1] = true;
    }

    class SetSpanOperation {
        private static transient /* synthetic */ boolean[] $jacocoData;
        protected final int end;
        protected final int flag;
        protected final int start;
        final /* synthetic */ TextContentBoxMeasurement this$0;
        protected final Object what;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-7764508995450289563L, "com/taobao/weex/layout/measurefunc/TextContentBoxMeasurement$SetSpanOperation", 3);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        SetSpanOperation(TextContentBoxMeasurement textContentBoxMeasurement, int i, int i2, Object obj) {
            this(textContentBoxMeasurement, i, i2, obj, 17);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
        }

        SetSpanOperation(TextContentBoxMeasurement textContentBoxMeasurement, int i, int i2, Object obj, int i3) {
            boolean[] $jacocoInit = $jacocoInit();
            this.this$0 = textContentBoxMeasurement;
            this.start = i;
            this.end = i2;
            this.what = obj;
            this.flag = i3;
            $jacocoInit[1] = true;
        }

        public void execute(Spannable spannable) {
            boolean[] $jacocoInit = $jacocoInit();
            spannable.setSpan(this.what, this.start, this.end, this.flag);
            $jacocoInit[2] = true;
        }
    }

    public void layoutBefore() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mTextPaint = new TextPaint(1);
        this.hasBeenMeasured = false;
        $jacocoInit[2] = true;
        updateStyleAndText();
        $jacocoInit[3] = true;
        this.spanned = createSpanned(this.mText);
        $jacocoInit[4] = true;
    }

    public void measureInternal(float f, float f2, int i, int i2) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        this.hasBeenMeasured = true;
        $jacocoInit[5] = true;
        TextPaint textPaint = this.mTextPaint;
        if (i == MeasureMode.EXACTLY) {
            $jacocoInit[6] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[7] = true;
        }
        float textWidth = getTextWidth(textPaint, f, z);
        if (textWidth <= 0.0f) {
            $jacocoInit[8] = true;
        } else if (this.mText == null) {
            $jacocoInit[9] = true;
        } else {
            $jacocoInit[10] = true;
            this.layout = createLayout(textWidth, (Layout) null);
            $jacocoInit[11] = true;
            this.previousWidth = (float) this.layout.getWidth();
            $jacocoInit[12] = true;
            if (Float.isNaN(f)) {
                $jacocoInit[13] = true;
                r6 = (float) this.layout.getWidth();
                $jacocoInit[14] = true;
            } else {
                r6 = Math.min((float) this.layout.getWidth(), f);
                $jacocoInit[15] = true;
            }
            if (!Float.isNaN(f2)) {
                $jacocoInit[16] = true;
            } else {
                $jacocoInit[17] = true;
                f2 = (float) this.layout.getHeight();
                $jacocoInit[18] = true;
            }
            this.mMeasureWidth = r6;
            this.mMeasureHeight = f2;
            $jacocoInit[23] = true;
        }
        if (i != MeasureMode.UNSPECIFIED) {
            $jacocoInit[19] = true;
        } else {
            $jacocoInit[20] = true;
            f = 0.0f;
        }
        if (i2 != MeasureMode.UNSPECIFIED) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            f2 = 0.0f;
        }
        this.mMeasureWidth = r6;
        this.mMeasureHeight = f2;
        $jacocoInit[23] = true;
    }

    public void layoutAfter(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mComponent == null) {
            $jacocoInit[24] = true;
        } else {
            if (!this.hasBeenMeasured) {
                updateStyleAndText();
                $jacocoInit[30] = true;
                recalculateLayout(f);
                $jacocoInit[31] = true;
            } else if (this.layout == null) {
                $jacocoInit[25] = true;
            } else {
                WXComponent wXComponent = this.mComponent;
                $jacocoInit[26] = true;
                if (WXDomUtils.getContentWidth(wXComponent.getPadding(), this.mComponent.getBorder(), f) == this.previousWidth) {
                    $jacocoInit[27] = true;
                } else {
                    $jacocoInit[28] = true;
                    recalculateLayout(f);
                    $jacocoInit[29] = true;
                }
            }
            this.hasBeenMeasured = false;
            $jacocoInit[32] = true;
            if (this.layout == null) {
                $jacocoInit[33] = true;
            } else if (this.layout.equals(this.atomicReference.get())) {
                $jacocoInit[34] = true;
            } else if (Build.VERSION.SDK_INT < 19) {
                $jacocoInit[35] = true;
            } else {
                $jacocoInit[36] = true;
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    $jacocoInit[37] = true;
                } else {
                    $jacocoInit[38] = true;
                    warmUpTextLayoutCache(this.layout);
                    $jacocoInit[39] = true;
                }
            }
            swap();
            $jacocoInit[40] = true;
            WXRenderManager wXRenderManager = WXSDKManager.getInstance().getWXRenderManager();
            AnonymousClass1 r0 = new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ TextContentBoxMeasurement this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-6130233243148654709L, "com/taobao/weex/layout/measurefunc/TextContentBoxMeasurement$1", 2);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r3;
                    $jacocoInit[0] = true;
                }

                public void run() {
                    boolean[] $jacocoInit = $jacocoInit();
                    TextContentBoxMeasurement.access$100(this.this$0).updateExtra(TextContentBoxMeasurement.access$000(this.this$0).get());
                    $jacocoInit[1] = true;
                }
            };
            WXComponent wXComponent2 = this.mComponent;
            $jacocoInit[41] = true;
            String instanceId = wXComponent2.getInstanceId();
            $jacocoInit[42] = true;
            wXRenderManager.postOnUiThread((Runnable) r0, instanceId);
            $jacocoInit[43] = true;
        }
        $jacocoInit[44] = true;
    }

    private void updateStyleAndText() {
        boolean[] $jacocoInit = $jacocoInit();
        updateStyleImp(this.mComponent.getStyles());
        $jacocoInit[45] = true;
        this.mText = WXAttr.getValue(this.mComponent.getAttrs());
        $jacocoInit[46] = true;
    }

    @WorkerThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void forceRelayout() {
        boolean[] $jacocoInit = $jacocoInit();
        layoutBefore();
        $jacocoInit[47] = true;
        measure(this.previousWidth, Float.NaN, MeasureMode.EXACTLY, MeasureMode.UNSPECIFIED);
        $jacocoInit[48] = true;
        layoutAfter(this.previousWidth, Float.NaN);
        $jacocoInit[49] = true;
    }

    private void updateStyleImp(Map<String, Object> map) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[50] = true;
        } else {
            $jacocoInit[51] = true;
            if (!map.containsKey(Constants.Name.LINES)) {
                $jacocoInit[52] = true;
            } else {
                $jacocoInit[53] = true;
                int lines = WXStyle.getLines(map);
                if (lines > 0) {
                    $jacocoInit[54] = true;
                } else {
                    $jacocoInit[55] = true;
                    lines = -1;
                }
                this.mNumberOfLines = lines;
                $jacocoInit[56] = true;
            }
            if (!map.containsKey("fontSize")) {
                $jacocoInit[57] = true;
            } else {
                $jacocoInit[58] = true;
                this.mFontSize = WXStyle.getFontSize(map, this.mComponent.getViewPortWidth());
                $jacocoInit[59] = true;
            }
            if (!map.containsKey("fontWeight")) {
                $jacocoInit[60] = true;
            } else {
                $jacocoInit[61] = true;
                this.mFontWeight = WXStyle.getFontWeight(map);
                $jacocoInit[62] = true;
            }
            if (!map.containsKey("fontStyle")) {
                $jacocoInit[63] = true;
            } else {
                $jacocoInit[64] = true;
                this.mFontStyle = WXStyle.getFontStyle(map);
                $jacocoInit[65] = true;
            }
            if (!map.containsKey("color")) {
                $jacocoInit[66] = true;
            } else {
                $jacocoInit[67] = true;
                this.mColor = WXResourceUtils.getColor(WXStyle.getTextColor(map));
                if (this.mColor != Integer.MIN_VALUE) {
                    $jacocoInit[68] = true;
                    z = true;
                } else {
                    z = false;
                    $jacocoInit[69] = true;
                }
                this.mIsColorSet = z;
                $jacocoInit[70] = true;
            }
            if (!map.containsKey("textDecoration")) {
                $jacocoInit[71] = true;
            } else {
                $jacocoInit[72] = true;
                this.mTextDecoration = WXStyle.getTextDecoration(map);
                $jacocoInit[73] = true;
            }
            if (!map.containsKey("fontFamily")) {
                $jacocoInit[74] = true;
            } else {
                $jacocoInit[75] = true;
                this.mFontFamily = WXStyle.getFontFamily(map);
                $jacocoInit[76] = true;
            }
            this.mAlignment = WXStyle.getTextAlignment(map, this.mComponent.isLayoutRTL());
            $jacocoInit[77] = true;
            this.textOverflow = WXStyle.getTextOverflow(map);
            $jacocoInit[78] = true;
            int lineHeight = WXStyle.getLineHeight(map, this.mComponent.getViewPortWidth());
            if (lineHeight == -1) {
                $jacocoInit[79] = true;
            } else {
                this.mLineHeight = lineHeight;
                $jacocoInit[80] = true;
            }
        }
        $jacocoInit[81] = true;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public Spanned createSpanned(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(str)) {
            $jacocoInit[82] = true;
            SpannableString spannableString = new SpannableString(str);
            $jacocoInit[83] = true;
            updateSpannable(spannableString, 17);
            $jacocoInit[84] = true;
            return spannableString;
        }
        SpannableString spannableString2 = new SpannableString("");
        $jacocoInit[85] = true;
        return spannableString2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateSpannable(android.text.Spannable r11, int r12) {
        /*
            r10 = this;
            boolean[] r0 = $jacocoInit()
            int r7 = r11.length()
            int r1 = r10.mFontSize
            r8 = -1
            r9 = 1
            if (r1 != r8) goto L_0x001e
            r1 = 86
            r0[r1] = r9
            android.text.TextPaint r1 = r10.mTextPaint
            r2 = 1107296256(0x42000000, float:32.0)
            r1.setTextSize(r2)
            r1 = 87
            r0[r1] = r9
            goto L_0x002a
        L_0x001e:
            android.text.TextPaint r1 = r10.mTextPaint
            int r2 = r10.mFontSize
            float r2 = (float) r2
            r1.setTextSize(r2)
            r1 = 88
            r0[r1] = r9
        L_0x002a:
            int r1 = r10.mLineHeight
            if (r1 != r8) goto L_0x0033
            r1 = 89
            r0[r1] = r9
            goto L_0x004a
        L_0x0033:
            r1 = 90
            r0[r1] = r9
            com.taobao.weex.dom.WXLineHeightSpan r3 = new com.taobao.weex.dom.WXLineHeightSpan
            int r1 = r10.mLineHeight
            r3.<init>(r1)
            r4 = 0
            r1 = r10
            r2 = r11
            r5 = r7
            r6 = r12
            r1.setSpan(r2, r3, r4, r5, r6)
            r1 = 91
            r0[r1] = r9
        L_0x004a:
            android.text.style.AlignmentSpan$Standard r3 = new android.text.style.AlignmentSpan$Standard
            android.text.Layout$Alignment r1 = r10.mAlignment
            r3.<init>(r1)
            r4 = 0
            r1 = r10
            r2 = r11
            r5 = r7
            r6 = r12
            r1.setSpan(r2, r3, r4, r5, r6)
            int r1 = r10.mFontStyle
            if (r1 == r8) goto L_0x0062
            r1 = 92
            r0[r1] = r9
            goto L_0x0078
        L_0x0062:
            int r1 = r10.mFontWeight
            if (r1 == r8) goto L_0x006b
            r1 = 93
            r0[r1] = r9
            goto L_0x0078
        L_0x006b:
            java.lang.String r1 = r10.mFontFamily
            if (r1 != 0) goto L_0x0074
            r1 = 94
            r0[r1] = r9
            goto L_0x008f
        L_0x0074:
            r1 = 95
            r0[r1] = r9
        L_0x0078:
            com.taobao.weex.dom.WXCustomStyleSpan r3 = new com.taobao.weex.dom.WXCustomStyleSpan
            int r1 = r10.mFontStyle
            int r2 = r10.mFontWeight
            java.lang.String r4 = r10.mFontFamily
            r3.<init>(r1, r2, r4)
            r4 = 0
            r1 = r10
            r2 = r11
            r5 = r7
            r6 = r12
            r1.setSpan(r2, r3, r4, r5, r6)
            r1 = 96
            r0[r1] = r9
        L_0x008f:
            boolean r1 = r10.mIsColorSet
            if (r1 != 0) goto L_0x0098
            r1 = 97
            r0[r1] = r9
            goto L_0x00a7
        L_0x0098:
            r1 = 98
            r0[r1] = r9
            android.text.TextPaint r1 = r10.mTextPaint
            int r2 = r10.mColor
            r1.setColor(r2)
            r1 = 99
            r0[r1] = r9
        L_0x00a7:
            com.taobao.weex.ui.component.WXTextDecoration r1 = r10.mTextDecoration
            com.taobao.weex.ui.component.WXTextDecoration r2 = com.taobao.weex.ui.component.WXTextDecoration.UNDERLINE
            if (r1 != r2) goto L_0x00b2
            r1 = 100
            r0[r1] = r9
            goto L_0x00c1
        L_0x00b2:
            com.taobao.weex.ui.component.WXTextDecoration r1 = r10.mTextDecoration
            com.taobao.weex.ui.component.WXTextDecoration r2 = com.taobao.weex.ui.component.WXTextDecoration.LINETHROUGH
            if (r1 == r2) goto L_0x00bd
            r11 = 101(0x65, float:1.42E-43)
            r0[r11] = r9
            goto L_0x00d4
        L_0x00bd:
            r1 = 102(0x66, float:1.43E-43)
            r0[r1] = r9
        L_0x00c1:
            com.taobao.weex.dom.TextDecorationSpan r3 = new com.taobao.weex.dom.TextDecorationSpan
            com.taobao.weex.ui.component.WXTextDecoration r1 = r10.mTextDecoration
            r3.<init>(r1)
            r4 = 0
            r1 = r10
            r2 = r11
            r5 = r7
            r6 = r12
            r1.setSpan(r2, r3, r4, r5, r6)
            r11 = 103(0x67, float:1.44E-43)
            r0[r11] = r9
        L_0x00d4:
            r11 = 104(0x68, float:1.46E-43)
            r0[r11] = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.layout.measurefunc.TextContentBoxMeasurement.updateSpannable(android.text.Spannable, int):void");
    }

    private void setSpan(Spannable spannable, Object obj, int i, int i2, int i3) {
        boolean[] $jacocoInit = $jacocoInit();
        spannable.setSpan(obj, i, i2, i3);
        $jacocoInit[105] = true;
    }

    private float getTextWidth(TextPaint textPaint, float f, boolean z) {
        float f2;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mText != null) {
            if (z) {
                $jacocoInit[108] = true;
            } else {
                f2 = Layout.getDesiredWidth(this.spanned, textPaint);
                $jacocoInit[109] = true;
                if (WXUtils.isUndefined(f)) {
                    $jacocoInit[110] = true;
                } else if (f2 < f) {
                    $jacocoInit[111] = true;
                } else {
                    $jacocoInit[113] = true;
                }
                $jacocoInit[112] = true;
                $jacocoInit[114] = true;
                return f2;
            }
            f2 = f;
            $jacocoInit[114] = true;
            return f2;
        } else if (z) {
            $jacocoInit[106] = true;
            return f;
        } else {
            $jacocoInit[107] = true;
            return 0.0f;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.text.StaticLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: android.text.StaticLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: android.text.StaticLayout} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0040  */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.text.Layout createLayout(float r19, @android.support.annotation.Nullable android.text.Layout r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            boolean[] r2 = $jacocoInit()
            float r3 = r0.previousWidth
            int r3 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            r4 = 1
            if (r3 == 0) goto L_0x0014
            r3 = 115(0x73, float:1.61E-43)
            r2[r3] = r4
            goto L_0x001a
        L_0x0014:
            if (r20 != 0) goto L_0x0035
            r3 = 116(0x74, float:1.63E-43)
            r2[r3] = r4
        L_0x001a:
            android.text.StaticLayout r3 = new android.text.StaticLayout
            android.text.Spanned r6 = r0.spanned
            android.text.TextPaint r7 = r0.mTextPaint
            double r8 = (double) r1
            double r8 = java.lang.Math.ceil(r8)
            int r8 = (int) r8
            android.text.Layout$Alignment r9 = android.text.Layout.Alignment.ALIGN_NORMAL
            r10 = 1065353216(0x3f800000, float:1.0)
            r11 = 0
            r12 = 0
            r5 = r3
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)
            r5 = 117(0x75, float:1.64E-43)
            r2[r5] = r4
            goto L_0x003b
        L_0x0035:
            r5 = 118(0x76, float:1.65E-43)
            r2[r5] = r4
            r3 = r20
        L_0x003b:
            int r5 = r0.mNumberOfLines
            r6 = -1
            if (r5 != r6) goto L_0x0045
            r1 = 119(0x77, float:1.67E-43)
            r2[r1] = r4
            goto L_0x0077
        L_0x0045:
            int r5 = r0.mNumberOfLines
            if (r5 > 0) goto L_0x004e
            r1 = 120(0x78, float:1.68E-43)
            r2[r1] = r4
            goto L_0x0077
        L_0x004e:
            int r5 = r0.mNumberOfLines
            int r6 = r3.getLineCount()
            if (r5 < r6) goto L_0x005b
            r1 = 121(0x79, float:1.7E-43)
            r2[r1] = r4
            goto L_0x0077
        L_0x005b:
            r5 = 122(0x7a, float:1.71E-43)
            r2[r5] = r4
            int r5 = r0.mNumberOfLines
            int r5 = r5 - r4
            int r5 = r3.getLineStart(r5)
            r6 = 123(0x7b, float:1.72E-43)
            r2[r6] = r4
            int r6 = r0.mNumberOfLines
            int r6 = r6 - r4
            int r6 = r3.getLineEnd(r6)
            if (r5 < r6) goto L_0x007c
            r1 = 124(0x7c, float:1.74E-43)
            r2[r1] = r4
        L_0x0077:
            r1 = 132(0x84, float:1.85E-43)
            r2[r1] = r4
            return r3
        L_0x007c:
            if (r5 <= 0) goto L_0x0093
            r3 = 125(0x7d, float:1.75E-43)
            r2[r3] = r4
            android.text.SpannableStringBuilder r3 = new android.text.SpannableStringBuilder
            android.text.Spanned r7 = r0.spanned
            r8 = 0
            java.lang.CharSequence r7 = r7.subSequence(r8, r5)
            r3.<init>(r7)
            r7 = 126(0x7e, float:1.77E-43)
            r2[r7] = r4
            goto L_0x009c
        L_0x0093:
            android.text.SpannableStringBuilder r3 = new android.text.SpannableStringBuilder
            r3.<init>()
            r7 = 127(0x7f, float:1.78E-43)
            r2[r7] = r4
        L_0x009c:
            android.text.SpannableStringBuilder r7 = new android.text.SpannableStringBuilder
            android.text.Spanned r8 = r0.spanned
            java.lang.CharSequence r5 = r8.subSequence(r5, r6)
            r7.<init>(r5)
            r5 = 128(0x80, float:1.794E-43)
            r2[r5] = r4
            android.text.TextPaint r5 = r0.mTextPaint
            double r8 = (double) r1
            double r10 = java.lang.Math.ceil(r8)
            int r1 = (int) r10
            android.text.TextUtils$TruncateAt r6 = r0.textOverflow
            android.text.Spanned r1 = r0.truncate(r7, r5, r1, r6)
            r3.append(r1)
            r1 = 129(0x81, float:1.81E-43)
            r2[r1] = r4
            android.text.Spanned r1 = r0.spanned
            r0.adjustSpansRange(r1, r3)
            r0.spanned = r3
            r1 = 130(0x82, float:1.82E-43)
            r2[r1] = r4
            android.text.StaticLayout r1 = new android.text.StaticLayout
            android.text.Spanned r11 = r0.spanned
            android.text.TextPaint r12 = r0.mTextPaint
            double r5 = java.lang.Math.ceil(r8)
            int r13 = (int) r5
            android.text.Layout$Alignment r14 = android.text.Layout.Alignment.ALIGN_NORMAL
            r15 = 1065353216(0x3f800000, float:1.0)
            r16 = 0
            r17 = 0
            r10 = r1
            r10.<init>(r11, r12, r13, r14, r15, r16, r17)
            r3 = 131(0x83, float:1.84E-43)
            r2[r3] = r4
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.layout.measurefunc.TextContentBoxMeasurement.createLayout(float, android.text.Layout):android.text.Layout");
    }

    @NonNull
    private Spanned truncate(@Nullable Editable editable, @NonNull TextPaint textPaint, int i, @Nullable TextUtils.TruncateAt truncateAt) {
        Editable editable2 = editable;
        boolean[] $jacocoInit = $jacocoInit();
        Spanned spannedString = new SpannedString("");
        $jacocoInit[133] = true;
        if (!TextUtils.isEmpty(editable)) {
            if (editable.length() > 0) {
                if (truncateAt == null) {
                    $jacocoInit[136] = true;
                } else {
                    $jacocoInit[137] = true;
                    editable.append(ELLIPSIS);
                    $jacocoInit[138] = true;
                    Object[] spans = editable.getSpans(0, editable.length(), Object.class);
                    int length = spans.length;
                    $jacocoInit[139] = true;
                    int i2 = 0;
                    while (i2 < length) {
                        Object obj = spans[i2];
                        $jacocoInit[141] = true;
                        int spanStart = editable.getSpanStart(obj);
                        $jacocoInit[142] = true;
                        int spanEnd = editable.getSpanEnd(obj);
                        $jacocoInit[143] = true;
                        if (spanStart != 0) {
                            $jacocoInit[144] = true;
                        } else if (spanEnd != editable.length() - 1) {
                            $jacocoInit[145] = true;
                        } else {
                            $jacocoInit[146] = true;
                            editable.removeSpan(obj);
                            $jacocoInit[147] = true;
                            editable.setSpan(obj, 0, editable.length(), editable.getSpanFlags(obj));
                            $jacocoInit[148] = true;
                        }
                        i2++;
                        $jacocoInit[149] = true;
                    }
                    $jacocoInit[140] = true;
                }
                while (true) {
                    if (editable.length() > 1) {
                        $jacocoInit[151] = true;
                        int length2 = editable.length() - 1;
                        if (truncateAt == null) {
                            $jacocoInit[152] = true;
                        } else {
                            length2--;
                            $jacocoInit[153] = true;
                        }
                        editable.delete(length2, length2 + 1);
                        $jacocoInit[154] = true;
                        StaticLayout staticLayout = new StaticLayout(editable, textPaint, i, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                        $jacocoInit[155] = true;
                        if (staticLayout.getLineCount() <= 1) {
                            $jacocoInit[157] = true;
                            spannedString = editable2;
                            break;
                        }
                        $jacocoInit[156] = true;
                    } else {
                        $jacocoInit[150] = true;
                        break;
                    }
                }
            } else {
                $jacocoInit[135] = true;
            }
        } else {
            $jacocoInit[134] = true;
        }
        $jacocoInit[158] = true;
        return spannedString;
    }

    private void adjustSpansRange(@NonNull Spanned spanned2, @NonNull Spannable spannable) {
        boolean[] $jacocoInit = $jacocoInit();
        Object[] spans = spanned2.getSpans(0, spanned2.length(), Object.class);
        int length = spans.length;
        $jacocoInit[159] = true;
        int i = 0;
        while (i < length) {
            Object obj = spans[i];
            $jacocoInit[160] = true;
            int spanStart = spanned2.getSpanStart(obj);
            $jacocoInit[161] = true;
            int spanEnd = spanned2.getSpanEnd(obj);
            $jacocoInit[162] = true;
            if (spanStart != 0) {
                $jacocoInit[163] = true;
            } else if (spanEnd != spanned2.length()) {
                $jacocoInit[164] = true;
            } else {
                $jacocoInit[165] = true;
                spannable.removeSpan(obj);
                $jacocoInit[166] = true;
                spannable.setSpan(obj, 0, spannable.length(), spanned2.getSpanFlags(obj));
                $jacocoInit[167] = true;
            }
            i++;
            $jacocoInit[168] = true;
        }
        $jacocoInit[169] = true;
    }

    private void recalculateLayout(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        float contentWidth = WXDomUtils.getContentWidth(this.mComponent.getPadding(), this.mComponent.getBorder(), f);
        if (contentWidth <= 0.0f) {
            $jacocoInit[170] = true;
        } else {
            $jacocoInit[171] = true;
            this.spanned = createSpanned(this.mText);
            if (this.mText != null) {
                $jacocoInit[172] = true;
                this.layout = createLayout(contentWidth, this.layout);
                $jacocoInit[173] = true;
                this.previousWidth = (float) this.layout.getWidth();
                $jacocoInit[174] = true;
            } else {
                this.previousWidth = 0.0f;
                $jacocoInit[175] = true;
            }
        }
        $jacocoInit[176] = true;
    }

    private boolean warmUpTextLayoutCache(Layout layout2) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            layout2.draw(DUMMY_CANVAS);
            $jacocoInit[177] = true;
            z = true;
        } catch (Exception e) {
            $jacocoInit[178] = true;
            WXLogUtils.eTag("TextWarmUp", e);
            z = false;
            $jacocoInit[179] = true;
        }
        $jacocoInit[180] = true;
        return z;
    }

    private void swap() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.layout == null) {
            $jacocoInit[181] = true;
        } else {
            $jacocoInit[182] = true;
            this.atomicReference.set(this.layout);
            this.layout = null;
            $jacocoInit[183] = true;
        }
        this.hasBeenMeasured = false;
        $jacocoInit[184] = true;
    }
}
