package com.xiaomi.smarthome.newui.widget.cards;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.xiaomi.smarthome.newui.widget.cards.ViewUpdater;

public class VerticalViewUpdater extends ViewUpdater {
    private static final float ALPHA_ACTIVE = 1.0f;
    private static final float ALPHA_BOTTOM = 0.0f;
    private static final float ALPHA_TOP = 0.9f;
    private static final float SCALE_BOTTOM = 0.85f;
    private static final float SCALE_CENTER = 1.0f;
    private static final float SCALE_CENTER_TO_BOTTOM = 0.14999998f;
    public static final float SCALE_CENTER_TO_TOP = 0.35000002f;
    public static final float SCALE_TOP = 0.65f;
    private static final String TAG = "VerticalViewUpdater";
    private static final int Z_CENTER_1 = 12;
    private static final int Z_CENTER_2 = 16;
    private static final int Z_RIGHT = 8;
    private int activeCardBottom;
    private int activeCardCenter;
    private int activeCardTop;
    private int cardHeight;
    private float cardsGap;
    private int curY0;
    private ViewUpdater.OnActiveCardChangeListener mActCardListener;
    private UpdateInterceptor mInterceptor;
    private float transitionBottom2Center;
    private int transitionDistance;
    private int transitionEnd;

    public VerticalViewUpdater(CardSliderLayoutManager cardSliderLayoutManager) {
        super(cardSliderLayoutManager);
    }

    public void setUpdateInterceptor(UpdateInterceptor updateInterceptor) {
        this.mInterceptor = updateInterceptor;
    }

    public void onLayoutManagerInitialized() {
        this.cardHeight = this.lm.getCardHeight();
        this.activeCardTop = this.lm.getActiveCardTop();
        this.activeCardBottom = this.lm.getActiveCardBottom();
        this.activeCardCenter = this.lm.getActiveCardCenter();
        this.cardsGap = this.lm.getCardsGap();
        this.curY0 = this.lm.getCurY0();
        this.transitionEnd = this.activeCardCenter;
        this.transitionDistance = this.activeCardBottom - this.transitionEnd;
        this.transitionBottom2Center = ((((float) this.activeCardBottom) + ((((float) this.cardHeight) - (((float) this.cardHeight) * 1.0f)) / 2.0f)) - (((float) this.activeCardBottom) - ((((float) this.cardHeight) - (((float) this.cardHeight) * SCALE_BOTTOM)) / 2.0f))) - this.cardsGap;
    }

    public int getActiveCardPosition() {
        int i;
        int childCount = this.lm.getChildCount();
        View view = null;
        float f = 0.0f;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.lm.getChildAt(i2);
            int decoratedTop = this.lm.getDecoratedTop(childAt);
            if (decoratedTop < this.activeCardBottom) {
                float scaleY = ViewCompat.getScaleY(childAt);
                if (f < scaleY && decoratedTop < this.activeCardCenter) {
                    view = childAt;
                    f = scaleY;
                }
            }
        }
        int i3 = -1;
        if (view != null) {
            int position = this.lm.getPosition(view);
            int viewPositionInAdapter = this.lm.getViewPositionInAdapter(view);
            i = position;
            i3 = viewPositionInAdapter;
        } else {
            i = -1;
        }
        if (this.mActCardListener != null) {
            this.mActCardListener.onActiveCardChange(i3, this.lm.getItemCount());
        }
        return i;
    }

    @Nullable
    public View getTopView() {
        View view = null;
        if (this.lm.getChildCount() == 0) {
            return null;
        }
        float f = (float) this.cardHeight;
        int childCount = this.lm.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.lm.getChildAt(i);
            if (this.lm.getDecoratedTop(childAt) < this.activeCardBottom) {
                float decoratedTop = (float) (this.activeCardBottom - this.lm.getDecoratedTop(childAt));
                if (decoratedTop < f) {
                    view = childAt;
                    f = decoratedTop;
                }
            }
        }
        return view;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x014e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateView() {
        /*
            r17 = this;
            r0 = r17
            com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r1 = r0.lm
            int r1 = r1.getChildCount()
            r3 = 0
            r4 = r3
            r3 = 0
        L_0x000b:
            if (r3 >= r1) goto L_0x0153
            com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r5 = r0.lm
            android.view.View r5 = r5.getChildAt(r3)
            com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r6 = r0.lm
            int r6 = r6.getViewPositionInAdapter(r5)
            com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r7 = r0.lm
            int r7 = r7.getDecoratedTop(r5)
            r8 = 2130839276(0x7f0206ec, float:1.7283558E38)
            float r9 = (float) r7
            int r10 = r0.activeCardTop
            float r10 = (float) r10
            com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r11 = r0.lm
            float r11 = r11.getCardGap2to3()
            float r10 = r10 - r11
            int r10 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            r11 = 1062836634(0x3f59999a, float:0.85)
            r12 = 1051931444(0x3eb33334, float:0.35000002)
            r13 = 1059481190(0x3f266666, float:0.65)
            r14 = 1094713344(0x41400000, float:12.0)
            r15 = 0
            r16 = 1065353216(0x3f800000, float:1.0)
            if (r10 >= 0) goto L_0x0088
            com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r4 = r0.lm
            int r8 = r3 + 3
            android.view.View r4 = r4.getChildAt(r8)
            r8 = 1063675494(0x3f666666, float:0.9)
            if (r4 == 0) goto L_0x0070
            com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r9 = r0.lm
            int r9 = r9.getDecoratedTop(r4)
            int r10 = r0.activeCardBottom
            if (r9 > r10) goto L_0x0070
            com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r9 = r0.lm
            int r4 = r9.getDecoratedTop(r4)
            int r9 = r0.activeCardCenter
            int r4 = r4 - r9
            int r4 = java.lang.Math.abs(r4)
            float r4 = (float) r4
            int r9 = r0.activeCardBottom
            int r10 = r0.activeCardCenter
            int r9 = r9 - r10
            float r9 = (float) r9
            float r4 = r4 / r9
            float r4 = r4 * r8
            r16 = r4
            goto L_0x0073
        L_0x0070:
            r16 = 1063675494(0x3f666666, float:0.9)
        L_0x0073:
            int r4 = r0.curY0
            int r7 = r7 - r4
            float r4 = (float) r7
            int r7 = r0.activeCardTop
            int r8 = r0.curY0
            int r7 = r7 - r8
            float r7 = (float) r7
            float r4 = r4 / r7
            float r12 = r12 * r4
            float r11 = r12 + r13
            float r14 = r14 * r4
            r8 = 2130839277(0x7f0206ed, float:1.728356E38)
            goto L_0x00b0
        L_0x0088:
            int r10 = r0.activeCardTop
            if (r7 >= r10) goto L_0x00b4
            int r4 = r0.curY0
            int r4 = r7 - r4
            float r4 = (float) r4
            int r9 = r0.activeCardTop
            int r10 = r0.curY0
            int r9 = r9 - r10
            float r9 = (float) r9
            float r4 = r4 / r9
            float r12 = r12 * r4
            float r11 = r12 + r13
            int r9 = r0.activeCardTop
            int r9 = r9 - r7
            float r7 = (float) r9
            com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r9 = r0.lm
            float r9 = r9.getCardGap2to3()
            float r7 = r7 / r9
            r9 = 1036831952(0x3dccccd0, float:0.100000024)
            float r7 = r7 * r9
            float r16 = r16 - r7
            float r14 = r14 * r4
        L_0x00b0:
            r7 = r16
            goto L_0x013d
        L_0x00b4:
            int r10 = r0.activeCardCenter
            if (r7 >= r10) goto L_0x00be
            r7 = 1065353216(0x3f800000, float:1.0)
            r11 = 1065353216(0x3f800000, float:1.0)
            goto L_0x013d
        L_0x00be:
            int r10 = r0.activeCardBottom
            if (r7 >= r10) goto L_0x00f7
            int r4 = r0.activeCardCenter
            int r4 = r7 - r4
            float r4 = (float) r4
            int r9 = r0.activeCardBottom
            int r10 = r0.activeCardCenter
            int r9 = r9 - r10
            float r9 = (float) r9
            float r4 = r4 / r9
            r9 = 1041865112(0x3e199998, float:0.14999998)
            float r4 = r4 * r9
            float r11 = r16 - r4
            r14 = 1098907648(0x41800000, float:16.0)
            float r4 = r0.transitionBottom2Center
            int r9 = r0.transitionEnd
            int r7 = r7 - r9
            float r7 = (float) r7
            float r4 = r4 * r7
            int r7 = r0.transitionDistance
            float r7 = (float) r7
            float r4 = r4 / r7
            float r7 = r0.transitionBottom2Center
            float r7 = java.lang.Math.abs(r7)
            float r9 = java.lang.Math.abs(r4)
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 >= 0) goto L_0x00f3
            float r4 = r0.transitionBottom2Center
        L_0x00f3:
            float r15 = -r4
            r7 = 1065353216(0x3f800000, float:1.0)
            goto L_0x013d
        L_0x00f7:
            r7 = 1056964608(0x3f000000, float:0.5)
            r14 = 1090519040(0x41000000, float:8.0)
            if (r4 == 0) goto L_0x013d
            com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r10 = r0.lm
            int r10 = r10.getDecoratedBottom(r4)
            int r12 = r0.activeCardBottom
            if (r10 > r12) goto L_0x0109
            r10 = 1
            goto L_0x010a
        L_0x0109:
            r10 = 0
        L_0x010a:
            if (r10 == 0) goto L_0x010f
            int r4 = r0.activeCardBottom
            goto L_0x011e
        L_0x010f:
            float r16 = android.support.v4.view.ViewCompat.getScaleY(r4)
            com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r10 = r0.lm
            int r10 = r10.getDecoratedBottom(r4)
            float r15 = android.support.v4.view.ViewCompat.getTranslationY(r4)
            r4 = r10
        L_0x011e:
            int r10 = r0.cardHeight
            float r10 = (float) r10
            int r12 = r0.cardHeight
            float r12 = (float) r12
            float r12 = r12 * r16
            float r10 = r10 - r12
            r12 = 1073741824(0x40000000, float:2.0)
            float r10 = r10 / r12
            int r13 = r0.cardHeight
            float r13 = (float) r13
            int r2 = r0.cardHeight
            float r2 = (float) r2
            float r2 = r2 * r11
            float r13 = r13 - r2
            float r13 = r13 / r12
            float r9 = r9 + r13
            float r2 = (float) r4
            float r2 = r2 - r10
            float r2 = r2 + r15
            float r9 = r9 - r2
            float r2 = r0.cardsGap
            float r9 = r9 - r2
            float r15 = -r9
        L_0x013d:
            if (r6 < 0) goto L_0x014e
            r0.onUpdateViewScale(r5, r6, r11)
            r0.onUpdateViewTransitionY(r5, r6, r15)
            r0.onUpdateViewZ(r5, r6, r14)
            r0.onUpdateViewAlpha(r5, r6, r7)
            r0.onUpdateViewBackgroud(r5, r6, r8)
        L_0x014e:
            int r3 = r3 + 1
            r4 = r5
            goto L_0x000b
        L_0x0153:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.widget.cards.VerticalViewUpdater.updateView():void");
    }

    public void setOnActiveCardChangeListener(ViewUpdater.OnActiveCardChangeListener onActiveCardChangeListener) {
        this.mActCardListener = onActiveCardChangeListener;
    }

    /* access modifiers changed from: protected */
    public void onUpdateViewAlpha(@NonNull View view, int i, float f) {
        if (this.mInterceptor != null) {
            this.mInterceptor.onUpdateViewAlpha(view, i, f);
        } else if (ViewCompat.getAlpha(view) != f) {
            ViewCompat.setAlpha(view, f);
        }
    }

    /* access modifiers changed from: protected */
    public void onUpdateViewScale(@NonNull View view, int i, float f) {
        if (this.mInterceptor != null) {
            this.mInterceptor.onUpdateViewScale(view, i, f);
        } else if (ViewCompat.getScaleY(view) != f) {
            ViewCompat.setScaleX(view, f);
            ViewCompat.setScaleY(view, f);
        }
    }

    /* access modifiers changed from: protected */
    public void onUpdateViewZ(@NonNull View view, int i, float f) {
        if (this.mInterceptor != null) {
            this.mInterceptor.onUpdateViewZ(view, i, f);
        } else if (ViewCompat.getZ(view) != f) {
            ViewCompat.setZ(view, f);
        }
    }

    /* access modifiers changed from: protected */
    public void onUpdateViewTransitionY(@NonNull View view, int i, float f) {
        if (this.mInterceptor != null) {
            this.mInterceptor.onUpdateViewTransitionY(view, i, f);
        } else if (ViewCompat.getTranslationY(view) != f) {
            ViewCompat.setTranslationY(view, f);
        }
    }

    /* access modifiers changed from: protected */
    public void onUpdateViewBackgroud(@NonNull View view, int i, int i2) {
        if (this.mInterceptor != null) {
            this.mInterceptor.onUpdateViewBackgroud(view, i, i2);
        } else {
            view.setBackgroundResource(i2);
        }
    }
}
