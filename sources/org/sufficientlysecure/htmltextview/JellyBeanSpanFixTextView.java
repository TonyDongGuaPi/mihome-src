package org.sufficientlysecure.htmltextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class JellyBeanSpanFixTextView extends TextView {

    private static class FixingResult {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f4190a;
        public final List<Object> b;
        public final List<Object> c;

        public static FixingResult a(List<Object> list, List<Object> list2) {
            return new FixingResult(true, list, list2);
        }

        public static FixingResult a() {
            return new FixingResult(false, (List<Object>) null, (List<Object>) null);
        }

        private FixingResult(boolean z, List<Object> list, List<Object> list2) {
            this.f4190a = z;
            this.b = list;
            this.c = list2;
        }
    }

    public JellyBeanSpanFixTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public JellyBeanSpanFixTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JellyBeanSpanFixTextView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        try {
            super.onMeasure(i, i2);
        } catch (IndexOutOfBoundsException unused) {
            fixOnMeasure(i, i2);
        }
    }

    private void fixOnMeasure(int i, int i2) {
        CharSequence text = getText();
        if (text instanceof Spanned) {
            fixSpannedWithSpaces(new SpannableStringBuilder(text), i, i2);
        } else {
            fallbackToString(i, i2);
        }
    }

    private void fixSpannedWithSpaces(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
        System.currentTimeMillis();
        FixingResult addSpacesAroundSpansUntilFixed = addSpacesAroundSpansUntilFixed(spannableStringBuilder, i, i2);
        if (addSpacesAroundSpansUntilFixed.f4190a) {
            removeUnneededSpaces(i, i2, spannableStringBuilder, addSpacesAroundSpansUntilFixed);
        } else {
            fallbackToString(i, i2);
        }
    }

    private FixingResult addSpacesAroundSpansUntilFixed(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
        int i3 = 0;
        Object[] spans = spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), Object.class);
        ArrayList arrayList = new ArrayList(spans.length);
        ArrayList arrayList2 = new ArrayList(spans.length);
        int length = spans.length;
        while (i3 < length) {
            Object obj = spans[i3];
            int spanStart = spannableStringBuilder.getSpanStart(obj);
            if (isNotSpace(spannableStringBuilder, spanStart - 1)) {
                spannableStringBuilder.insert(spanStart, " ");
                arrayList.add(obj);
            }
            int spanEnd = spannableStringBuilder.getSpanEnd(obj);
            if (isNotSpace(spannableStringBuilder, spanEnd)) {
                spannableStringBuilder.insert(spanEnd, " ");
                arrayList2.add(obj);
            }
            try {
                setTextAndMeasure(spannableStringBuilder, i, i2);
                return FixingResult.a(arrayList, arrayList2);
            } catch (IndexOutOfBoundsException unused) {
                i3++;
            }
        }
        return FixingResult.a();
    }

    private boolean isNotSpace(CharSequence charSequence, int i) {
        return i < 0 || i >= charSequence.length() || charSequence.charAt(i) != ' ';
    }

    @SuppressLint({"WrongCall"})
    private void setTextAndMeasure(CharSequence charSequence, int i, int i2) {
        setText(charSequence);
        super.onMeasure(i, i2);
    }

    @SuppressLint({"WrongCall"})
    private void removeUnneededSpaces(int i, int i2, SpannableStringBuilder spannableStringBuilder, FixingResult fixingResult) {
        boolean z;
        for (Object spanEnd : fixingResult.c) {
            int spanEnd2 = spannableStringBuilder.getSpanEnd(spanEnd);
            spannableStringBuilder.delete(spanEnd2, spanEnd2 + 1);
            try {
                setTextAndMeasure(spannableStringBuilder, i, i2);
            } catch (IndexOutOfBoundsException unused) {
                spannableStringBuilder.insert(spanEnd2, " ");
            }
        }
        loop1:
        while (true) {
            z = true;
            for (Object spanStart : fixingResult.b) {
                int spanStart2 = spannableStringBuilder.getSpanStart(spanStart);
                int i3 = spanStart2 - 1;
                spannableStringBuilder.delete(i3, spanStart2);
                try {
                    setTextAndMeasure(spannableStringBuilder, i, i2);
                    z = false;
                } catch (IndexOutOfBoundsException unused2) {
                    spannableStringBuilder.insert(i3, " ");
                }
            }
            break loop1;
        }
        if (z) {
            setText(spannableStringBuilder);
            super.onMeasure(i, i2);
        }
    }

    private void fallbackToString(int i, int i2) {
        setTextAndMeasure(getText().toString(), i, i2);
    }
}
