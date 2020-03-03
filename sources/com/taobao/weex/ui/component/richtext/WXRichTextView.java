package com.taobao.weex.ui.component.richtext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import com.taobao.weex.ui.component.richtext.span.ImgSpan;
import com.taobao.weex.ui.view.WXTextView;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXRichTextView extends WXTextView {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8928594575278207487L, "com/taobao/weex/ui/component/richtext/WXRichTextView", 45);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXRichTextView(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            boolean[] r0 = $jacocoInit()
            boolean r1 = super.onTouchEvent(r7)
            r2 = 1
            r0[r2] = r2
            boolean r3 = r6.isEnabled()
            r4 = 0
            if (r3 != 0) goto L_0x0016
            r7 = 2
            r0[r7] = r2
            goto L_0x002b
        L_0x0016:
            android.text.Layout r3 = r6.getTextLayout()
            if (r3 != 0) goto L_0x0020
            r7 = 3
            r0[r7] = r2
            goto L_0x002b
        L_0x0020:
            java.lang.CharSequence r3 = r6.getText()
            boolean r3 = r3 instanceof android.text.Spannable
            if (r3 != 0) goto L_0x002d
            r7 = 4
            r0[r7] = r2
        L_0x002b:
            r7 = 0
            goto L_0x0040
        L_0x002d:
            r3 = 5
            r0[r3] = r2
            java.lang.CharSequence r3 = r6.getText()
            android.text.Spannable r3 = (android.text.Spannable) r3
            r5 = 6
            r0[r5] = r2
            boolean r7 = r6.updateSelection(r7, r3)
            r3 = 7
            r0[r3] = r2
        L_0x0040:
            if (r7 == 0) goto L_0x0047
            r7 = 8
            r0[r7] = r2
            goto L_0x004d
        L_0x0047:
            if (r1 == 0) goto L_0x0053
            r7 = 9
            r0[r7] = r2
        L_0x004d:
            r7 = 10
            r0[r7] = r2
            r4 = 1
            goto L_0x0057
        L_0x0053:
            r7 = 11
            r0[r7] = r2
        L_0x0057:
            r7 = 12
            r0[r7] = r2
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.richtext.WXRichTextView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        boolean[] $jacocoInit = $jacocoInit();
        super.verifyDrawable(drawable);
        $jacocoInit[13] = true;
        return true;
    }

    public void setTextLayout(Layout layout) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setTextLayout(layout);
        $jacocoInit[14] = true;
        if (!(layout.getText() instanceof Spanned)) {
            $jacocoInit[15] = true;
        } else {
            $jacocoInit[16] = true;
            Spanned spanned = (Spanned) layout.getText();
            $jacocoInit[17] = true;
            int i = 0;
            ImgSpan[] imgSpanArr = (ImgSpan[]) spanned.getSpans(0, spanned.length(), ImgSpan.class);
            if (imgSpanArr == null) {
                $jacocoInit[18] = true;
            } else {
                int length = imgSpanArr.length;
                $jacocoInit[19] = true;
                while (i < length) {
                    ImgSpan imgSpan = imgSpanArr[i];
                    $jacocoInit[21] = true;
                    imgSpan.setView(this);
                    i++;
                    $jacocoInit[22] = true;
                }
                $jacocoInit[20] = true;
            }
        }
        $jacocoInit[23] = true;
    }

    private boolean updateSelection(MotionEvent motionEvent, Spannable spannable) {
        boolean[] $jacocoInit = $jacocoInit();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            $jacocoInit[24] = true;
        } else if (actionMasked != 0) {
            $jacocoInit[25] = true;
            $jacocoInit[44] = true;
            return false;
        } else {
            $jacocoInit[26] = true;
        }
        $jacocoInit[27] = true;
        $jacocoInit[28] = true;
        int x = ((int) motionEvent.getX()) - getPaddingLeft();
        $jacocoInit[29] = true;
        int y = ((int) motionEvent.getY()) - getPaddingTop();
        $jacocoInit[30] = true;
        int scrollX = x + getScrollX();
        $jacocoInit[31] = true;
        int scrollY = y + getScrollY();
        $jacocoInit[32] = true;
        Layout textLayout = getTextLayout();
        $jacocoInit[33] = true;
        int lineForVertical = textLayout.getLineForVertical(scrollY);
        $jacocoInit[34] = true;
        int offsetForHorizontal = textLayout.getOffsetForHorizontal(lineForVertical, (float) scrollX);
        $jacocoInit[35] = true;
        ClickableSpan[] clickableSpanArr = (ClickableSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);
        if (clickableSpanArr.length != 0) {
            if (actionMasked == 1) {
                $jacocoInit[36] = true;
                clickableSpanArr[0].onClick(this);
                $jacocoInit[37] = true;
            } else {
                ClickableSpan clickableSpan = clickableSpanArr[0];
                $jacocoInit[38] = true;
                int spanStart = spannable.getSpanStart(clickableSpan);
                ClickableSpan clickableSpan2 = clickableSpanArr[0];
                $jacocoInit[39] = true;
                int spanEnd = spannable.getSpanEnd(clickableSpan2);
                $jacocoInit[40] = true;
                Selection.setSelection(spannable, spanStart, spanEnd);
                $jacocoInit[41] = true;
            }
            $jacocoInit[42] = true;
            return true;
        }
        Selection.removeSelection(spannable);
        $jacocoInit[43] = true;
        $jacocoInit[44] = true;
        return false;
    }
}
