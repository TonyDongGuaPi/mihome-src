package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.TintContextWrapper;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactCompoundView;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import com.xiaomi.smarthome.auth.AuthCode;

public class ReactTextView extends AppCompatTextView implements ReactCompoundView {
    private static final ViewGroup.LayoutParams EMPTY_LAYOUT_PARAMS = new ViewGroup.LayoutParams(0, 0);
    private boolean mContainsImages;
    private int mDefaultGravityHorizontal = (getGravity() & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK);
    private int mDefaultGravityVertical = (getGravity() & 112);
    private TextUtils.TruncateAt mEllipsizeLocation = TextUtils.TruncateAt.END;
    private int mLinkifyMaskType = 0;
    private boolean mNotifyOnInlineViewLayout;
    private int mNumberOfLines = Integer.MAX_VALUE;
    private ReactViewBackgroundManager mReactBackgroundManager = new ReactViewBackgroundManager(this);
    private Spannable mSpanned;
    private int mTextAlign = 0;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public ReactTextView(Context context) {
        super(context);
    }

    private WritableMap inlineViewJson(int i, int i2, int i3, int i4, int i5, int i6) {
        WritableMap createMap = Arguments.createMap();
        if (i == 8) {
            createMap.putString("visibility", "gone");
            createMap.putInt("index", i2);
        } else if (i == 0) {
            createMap.putString("visibility", "visible");
            createMap.putInt("index", i2);
            createMap.putDouble("left", (double) PixelUtil.toDIPFromPixel((float) i3));
            createMap.putDouble("top", (double) PixelUtil.toDIPFromPixel((float) i4));
            createMap.putDouble("right", (double) PixelUtil.toDIPFromPixel((float) i5));
            createMap.putDouble("bottom", (double) PixelUtil.toDIPFromPixel((float) i6));
        } else {
            createMap.putString("visibility", "unknown");
            createMap.putInt("index", i2);
        }
        return createMap;
    }

    private ReactContext getReactContext() {
        Context context = getContext();
        if (context instanceof TintContextWrapper) {
            context = ((TintContextWrapper) context).getBaseContext();
        }
        return (ReactContext) context;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0161 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r22, int r23, int r24, int r25, int r26) {
        /*
            r21 = this;
            r7 = r21
            java.lang.CharSequence r0 = r21.getText()
            boolean r0 = r0 instanceof android.text.Spanned
            if (r0 != 0) goto L_0x000b
            return
        L_0x000b:
            com.facebook.react.bridge.ReactContext r0 = r21.getReactContext()
            boolean r0 = r0.hasCatalystInstance()
            if (r0 != 0) goto L_0x0016
            return
        L_0x0016:
            com.facebook.react.bridge.ReactContext r0 = r21.getReactContext()
            java.lang.Class<com.facebook.react.uimanager.UIManagerModule> r1 = com.facebook.react.uimanager.UIManagerModule.class
            com.facebook.react.bridge.NativeModule r0 = r0.getNativeModule(r1)
            r8 = r0
            com.facebook.react.uimanager.UIManagerModule r8 = (com.facebook.react.uimanager.UIManagerModule) r8
            java.lang.CharSequence r0 = r21.getText()
            r9 = r0
            android.text.Spanned r9 = (android.text.Spanned) r9
            android.text.Layout r10 = r21.getLayout()
            int r0 = r9.length()
            java.lang.Class<com.facebook.react.views.text.TextInlineViewPlaceholderSpan> r1 = com.facebook.react.views.text.TextInlineViewPlaceholderSpan.class
            r11 = 0
            java.lang.Object[] r0 = r9.getSpans(r11, r0, r1)
            r12 = r0
            com.facebook.react.views.text.TextInlineViewPlaceholderSpan[] r12 = (com.facebook.react.views.text.TextInlineViewPlaceholderSpan[]) r12
            boolean r0 = r7.mNotifyOnInlineViewLayout
            if (r0 == 0) goto L_0x0048
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r12.length
            r0.<init>(r1)
        L_0x0046:
            r13 = r0
            goto L_0x004a
        L_0x0048:
            r0 = 0
            goto L_0x0046
        L_0x004a:
            int r6 = r25 - r23
            int r5 = r26 - r24
            int r4 = r12.length
            r3 = 0
        L_0x0050:
            if (r3 >= r4) goto L_0x016d
            r0 = r12[r3]
            int r1 = r0.getReactTag()
            android.view.View r1 = r8.resolveView(r1)
            int r2 = r9.getSpanStart(r0)
            int r11 = r10.getLineForOffset(r2)
            int r14 = r10.getEllipsisCount(r11)
            r15 = 1
            if (r14 <= 0) goto L_0x006f
            r16 = r3
            r14 = 1
            goto L_0x0072
        L_0x006f:
            r16 = r3
            r14 = 0
        L_0x0072:
            if (r14 == 0) goto L_0x008e
            int r14 = r10.getLineStart(r11)
            int r17 = r10.getEllipsisStart(r11)
            int r3 = r14 + r17
            if (r2 >= r3) goto L_0x0081
            goto L_0x008e
        L_0x0081:
            r15 = r4
            r11 = r5
            r19 = r8
            r20 = r12
            r8 = r16
            r0 = 8
            r12 = r6
            goto L_0x014b
        L_0x008e:
            int r3 = r7.mNumberOfLines
            if (r11 >= r3) goto L_0x0140
            int r3 = r10.getLineEnd(r11)
            if (r2 < r3) goto L_0x0099
            goto L_0x0081
        L_0x0099:
            int r3 = r0.getWidth()
            int r0 = r0.getHeight()
            r18 = r4
            boolean r4 = r10.isRtlCharAt(r2)
            r19 = r8
            int r8 = r10.getParagraphDirection(r11)
            r20 = r12
            r12 = -1
            if (r8 != r12) goto L_0x00b4
            r8 = 1
            goto L_0x00b5
        L_0x00b4:
            r8 = 0
        L_0x00b5:
            int r12 = r9.length()
            int r12 = r12 - r15
            if (r2 != r12) goto L_0x00cd
            if (r8 == 0) goto L_0x00c6
            float r8 = r10.getLineWidth(r11)
            int r8 = (int) r8
            int r8 = r6 - r8
            goto L_0x00ee
        L_0x00c6:
            float r8 = r10.getLineRight(r11)
            int r8 = (int) r8
            int r8 = r8 - r3
            goto L_0x00ee
        L_0x00cd:
            if (r8 != r4) goto L_0x00d1
            r12 = 1
            goto L_0x00d2
        L_0x00d1:
            r12 = 0
        L_0x00d2:
            if (r12 == 0) goto L_0x00da
            float r12 = r10.getPrimaryHorizontal(r2)
            int r12 = (int) r12
            goto L_0x00df
        L_0x00da:
            float r12 = r10.getSecondaryHorizontal(r2)
            int r12 = (int) r12
        L_0x00df:
            if (r8 == 0) goto L_0x00ea
            float r8 = r10.getLineRight(r11)
            int r8 = (int) r8
            int r8 = r8 - r12
            int r8 = r6 - r8
            goto L_0x00eb
        L_0x00ea:
            r8 = r12
        L_0x00eb:
            if (r4 == 0) goto L_0x00ee
            int r8 = r8 - r3
        L_0x00ee:
            if (r4 == 0) goto L_0x00f6
            int r4 = r21.getTotalPaddingRight()
            int r8 = r8 + r4
            goto L_0x00fb
        L_0x00f6:
            int r4 = r21.getTotalPaddingLeft()
            int r8 = r8 + r4
        L_0x00fb:
            int r4 = r23 + r8
            int r12 = r21.getTotalPaddingTop()
            int r11 = r10.getLineBaseline(r11)
            int r12 = r12 + r11
            int r12 = r12 - r0
            int r11 = r24 + r12
            if (r6 <= r8) goto L_0x010f
            if (r5 > r12) goto L_0x010e
            goto L_0x010f
        L_0x010e:
            r15 = 0
        L_0x010f:
            if (r15 == 0) goto L_0x0114
            r8 = 8
            goto L_0x0115
        L_0x0114:
            r8 = 0
        L_0x0115:
            int r12 = r4 + r3
            int r3 = r11 + r0
            r1.setVisibility(r8)
            r1.layout(r4, r11, r12, r3)
            boolean r0 = r7.mNotifyOnInlineViewLayout
            if (r0 == 0) goto L_0x0139
            r0 = r21
            r1 = r8
            r14 = r3
            r8 = r16
            r3 = r4
            r15 = r18
            r4 = r11
            r11 = r5
            r5 = r12
            r12 = r6
            r6 = r14
            com.facebook.react.bridge.WritableMap r0 = r0.inlineViewJson(r1, r2, r3, r4, r5, r6)
            r13.add(r0)
            goto L_0x0161
        L_0x0139:
            r11 = r5
            r12 = r6
            r8 = r16
            r15 = r18
            goto L_0x0161
        L_0x0140:
            r15 = r4
            r11 = r5
            r19 = r8
            r20 = r12
            r8 = r16
            r12 = r6
            r0 = 8
        L_0x014b:
            r1.setVisibility(r0)
            boolean r0 = r7.mNotifyOnInlineViewLayout
            if (r0 == 0) goto L_0x0161
            r1 = 8
            r3 = -1
            r4 = -1
            r5 = -1
            r6 = -1
            r0 = r21
            com.facebook.react.bridge.WritableMap r0 = r0.inlineViewJson(r1, r2, r3, r4, r5, r6)
            r13.add(r0)
        L_0x0161:
            int r3 = r8 + 1
            r5 = r11
            r6 = r12
            r4 = r15
            r8 = r19
            r12 = r20
            r11 = 0
            goto L_0x0050
        L_0x016d:
            boolean r0 = r7.mNotifyOnInlineViewLayout
            if (r0 == 0) goto L_0x01b0
            com.facebook.react.views.text.ReactTextView$1 r0 = new com.facebook.react.views.text.ReactTextView$1
            r0.<init>()
            java.util.Collections.sort(r13, r0)
            com.facebook.react.bridge.WritableArray r0 = com.facebook.react.bridge.Arguments.createArray()
            java.util.Iterator r1 = r13.iterator()
        L_0x0181:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0191
            java.lang.Object r2 = r1.next()
            com.facebook.react.bridge.WritableMap r2 = (com.facebook.react.bridge.WritableMap) r2
            r0.pushMap(r2)
            goto L_0x0181
        L_0x0191:
            com.facebook.react.bridge.WritableMap r1 = com.facebook.react.bridge.Arguments.createMap()
            java.lang.String r2 = "inlineViews"
            r1.putArray(r2, r0)
            com.facebook.react.bridge.ReactContext r0 = r21.getReactContext()
            java.lang.Class<com.facebook.react.uimanager.events.RCTEventEmitter> r2 = com.facebook.react.uimanager.events.RCTEventEmitter.class
            com.facebook.react.bridge.JavaScriptModule r0 = r0.getJSModule(r2)
            com.facebook.react.uimanager.events.RCTEventEmitter r0 = (com.facebook.react.uimanager.events.RCTEventEmitter) r0
            int r2 = r21.getId()
            java.lang.String r3 = "topInlineViewLayout"
            r0.receiveEvent(r2, r3, r1)
        L_0x01b0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextView.onLayout(boolean, int, int, int, int):void");
    }

    public void setText(ReactTextUpdate reactTextUpdate) {
        this.mContainsImages = reactTextUpdate.containsImages();
        if (getLayoutParams() == null) {
            setLayoutParams(EMPTY_LAYOUT_PARAMS);
        }
        Spannable text = reactTextUpdate.getText();
        if (this.mLinkifyMaskType > 0) {
            Linkify.addLinks(text, this.mLinkifyMaskType);
            setMovementMethod(LinkMovementMethod.getInstance());
        }
        setText(text);
        setPadding((int) Math.floor((double) reactTextUpdate.getPaddingLeft()), (int) Math.floor((double) reactTextUpdate.getPaddingTop()), (int) Math.floor((double) reactTextUpdate.getPaddingRight()), (int) Math.floor((double) reactTextUpdate.getPaddingBottom()));
        int textAlign = reactTextUpdate.getTextAlign();
        if (this.mTextAlign != textAlign) {
            this.mTextAlign = textAlign;
        }
        setGravityHorizontal(this.mTextAlign);
        if (Build.VERSION.SDK_INT >= 23 && getBreakStrategy() != reactTextUpdate.getTextBreakStrategy()) {
            setBreakStrategy(reactTextUpdate.getTextBreakStrategy());
        }
        if (Build.VERSION.SDK_INT >= 26 && getJustificationMode() != reactTextUpdate.getJustificationMode()) {
            setJustificationMode(reactTextUpdate.getJustificationMode());
        }
        requestLayout();
    }

    public int reactTagForTouch(float f, float f2) {
        int i;
        CharSequence text = getText();
        int id = getId();
        int i2 = (int) f;
        int i3 = (int) f2;
        Layout layout = getLayout();
        if (layout == null) {
            return id;
        }
        int lineForVertical = layout.getLineForVertical(i3);
        int lineLeft = (int) layout.getLineLeft(lineForVertical);
        int lineRight = (int) layout.getLineRight(lineForVertical);
        if ((text instanceof Spanned) && i2 >= lineLeft && i2 <= lineRight) {
            Spanned spanned = (Spanned) text;
            try {
                int offsetForHorizontal = layout.getOffsetForHorizontal(lineForVertical, (float) i2);
                ReactTagSpan[] reactTagSpanArr = (ReactTagSpan[]) spanned.getSpans(offsetForHorizontal, offsetForHorizontal, ReactTagSpan.class);
                if (reactTagSpanArr != null) {
                    int length = text.length();
                    for (int i4 = 0; i4 < reactTagSpanArr.length; i4++) {
                        int spanStart = spanned.getSpanStart(reactTagSpanArr[i4]);
                        int spanEnd = spanned.getSpanEnd(reactTagSpanArr[i4]);
                        if (spanEnd > offsetForHorizontal && (i = spanEnd - spanStart) <= length) {
                            id = reactTagSpanArr[i4].getReactTag();
                            length = i;
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                FLog.e(ReactConstants.TAG, "Crash in HorizontalMeasurementProvider: " + e.getMessage());
                return id;
            }
        }
        return id;
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan drawable2 : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                if (drawable2.getDrawable() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan drawable2 : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                if (drawable2.getDrawable() == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan onDetachedFromWindow : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                onDetachedFromWindow.onDetachedFromWindow();
            }
        }
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan onStartTemporaryDetach : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                onStartTemporaryDetach.onStartTemporaryDetach();
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan onAttachedToWindow : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                onAttachedToWindow.onAttachedToWindow();
            }
        }
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan onFinishTemporaryDetach : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                onFinishTemporaryDetach.onFinishTemporaryDetach();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setGravityHorizontal(int i) {
        if (i == 0) {
            i = this.mDefaultGravityHorizontal;
        }
        setGravity(i | (getGravity() & -8 & -8388616));
    }

    /* access modifiers changed from: package-private */
    public void setGravityVertical(int i) {
        if (i == 0) {
            i = this.mDefaultGravityVertical;
        }
        setGravity(i | (getGravity() & AuthCode.n));
    }

    public void setNumberOfLines(int i) {
        if (i == 0) {
            i = Integer.MAX_VALUE;
        }
        this.mNumberOfLines = i;
        boolean z = true;
        if (this.mNumberOfLines != 1) {
            z = false;
        }
        setSingleLine(z);
        setMaxLines(this.mNumberOfLines);
    }

    public void setEllipsizeLocation(TextUtils.TruncateAt truncateAt) {
        this.mEllipsizeLocation = truncateAt;
    }

    public void setNotifyOnInlineViewLayout(boolean z) {
        this.mNotifyOnInlineViewLayout = z;
    }

    public void updateView() {
        setEllipsize(this.mNumberOfLines == Integer.MAX_VALUE ? null : this.mEllipsizeLocation);
    }

    public void setBackgroundColor(int i) {
        this.mReactBackgroundManager.setBackgroundColor(i);
    }

    public void setBorderWidth(int i, float f) {
        this.mReactBackgroundManager.setBorderWidth(i, f);
    }

    public void setBorderColor(int i, float f, float f2) {
        this.mReactBackgroundManager.setBorderColor(i, f, f2);
    }

    public void setBorderRadius(float f) {
        this.mReactBackgroundManager.setBorderRadius(f);
    }

    public void setBorderRadius(float f, int i) {
        this.mReactBackgroundManager.setBorderRadius(f, i);
    }

    public void setBorderStyle(@Nullable String str) {
        this.mReactBackgroundManager.setBorderStyle(str);
    }

    public void setSpanned(Spannable spannable) {
        this.mSpanned = spannable;
    }

    public Spannable getSpanned() {
        return this.mSpanned;
    }

    public void setLinkifyMask(int i) {
        this.mLinkifyMaskType = i;
    }
}
