package com.xiaomi.smarthome.library.common.widget.autofit;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.SingleLineTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaomi.smarthome.library.R;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AutofitHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18979a = 24;
    private static final int b = 42;
    private float c = -1.0f;
    private float d = -1.0f;
    private boolean e = false;
    private TextView f;
    private final Context g;
    private TextPaint h;
    private boolean i = true;
    private int[] j = new int[0];
    private boolean k;
    private float l;
    private TextWatcher m = new AutofitTextWatcher();
    private View.OnLayoutChangeListener n = new AutofitOnLayoutChangeListener();

    AutofitHelper(TextView textView) {
        this.f = textView;
        this.g = this.f.getContext();
        this.h = new TextPaint();
        a(this.f.getTextSize());
    }

    /* access modifiers changed from: package-private */
    public void a(AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = this.g.obtainStyledAttributes(attributeSet, R.styleable.AutofitTextViewNew, i2, 0);
        if (obtainStyledAttributes.hasValue(R.styleable.AutofitTextViewNew_autoSize)) {
            this.i = obtainStyledAttributes.getBoolean(R.styleable.AutofitTextViewNew_autoSize, true);
        }
        float dimension = obtainStyledAttributes.hasValue(R.styleable.AutofitTextViewNew_minTextSize) ? obtainStyledAttributes.getDimension(R.styleable.AutofitTextViewNew_minTextSize, -1.0f) : -1.0f;
        float dimension2 = obtainStyledAttributes.hasValue(R.styleable.AutofitTextViewNew_maxTextSize) ? obtainStyledAttributes.getDimension(R.styleable.AutofitTextViewNew_maxTextSize, -1.0f) : -1.0f;
        obtainStyledAttributes.recycle();
        if (!c()) {
            this.i = false;
        } else if (this.i) {
            this.f.addTextChangedListener(this.m);
            this.f.addOnLayoutChangeListener(this.n);
            DisplayMetrics displayMetrics = this.g.getResources().getDisplayMetrics();
            if (dimension == -1.0f) {
                dimension = TypedValue.applyDimension(0, 24.0f, displayMetrics);
            }
            if (dimension2 == -1.0f) {
                dimension2 = TypedValue.applyDimension(0, 42.0f, displayMetrics);
            }
            a(dimension, dimension2);
            if (b()) {
                a();
            }
        } else {
            this.f.removeTextChangedListener(this.m);
            this.f.removeOnLayoutChangeListener(this.n);
        }
    }

    private boolean b() {
        if (!c() || !this.i) {
            this.e = false;
        } else {
            float round = (float) Math.round(this.c);
            int i2 = 1;
            while (true) {
                round += 1.0f;
                if (Math.round(round) > Math.round(this.d)) {
                    break;
                }
                i2++;
            }
            int[] iArr = new int[i2];
            float f2 = this.c;
            for (int i3 = 0; i3 < i2; i3++) {
                iArr[i3] = Math.round(f2);
                f2 += 1.0f;
            }
            this.j = a(iArr);
            this.e = true;
        }
        return this.e;
    }

    private int[] a(int[] iArr) {
        if (r0 == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            if (i2 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i2)) < 0) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        if (r0 == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr2[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        return iArr2;
    }

    private void a(float f2, float f3) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f2 + "px) is less or equal to (0px)");
        } else if (f3 > f2) {
            this.i = true;
            this.c = f2;
            this.d = f3;
        } else {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f3 + "px) is less or equal to minimum auto-size text size (" + f2 + "px)");
        }
    }

    private boolean c() {
        return !(this.f instanceof EditText);
    }

    public void a() {
        this.k = true;
        a(this.f, this.h, this.c, this.d, d());
        this.k = false;
    }

    private int d() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f.getMaxLines();
        }
        TransformationMethod transformationMethod = this.f.getTransformationMethod();
        return (transformationMethod == null || !(transformationMethod instanceof SingleLineTransformationMethod)) ? -1 : 1;
    }

    private void a(TextView textView, TextPaint textPaint, float f2, float f3, int i2) {
        float f4;
        if (i2 > 0 && i2 != Integer.MAX_VALUE) {
            int width = (textView.getWidth() - textView.getPaddingLeft()) - textView.getPaddingRight();
            PrintStream printStream = System.out;
            printStream.println("targetWidth:   " + width);
            if (width > 0) {
                CharSequence text = textView.getText();
                TransformationMethod transformationMethod = textView.getTransformationMethod();
                if (transformationMethod != null) {
                    text = transformationMethod.getTransformation(text, textView);
                }
                CharSequence charSequence = text;
                Resources system = Resources.getSystem();
                if (this.g != null) {
                    system = this.g.getResources();
                }
                DisplayMetrics displayMetrics = system.getDisplayMetrics();
                textPaint.set(textView.getPaint());
                textPaint.setTextSize(f3);
                float f5 = (float) width;
                if (textPaint.measureText(charSequence, 0, charSequence.length()) > f5 || a(charSequence, textPaint, f3, f5, displayMetrics) > i2) {
                    f4 = a(charSequence, textPaint, width, i2, displayMetrics);
                } else {
                    f4 = f3;
                }
                if (f4 < f2) {
                    f4 = f2;
                }
                PrintStream printStream2 = System.out;
                printStream2.println("autofit size in px:   " + f4);
                textView.setTextSize(0, f4);
            }
        }
    }

    private float a(CharSequence charSequence, TextPaint textPaint, int i2, int i3, DisplayMetrics displayMetrics) {
        for (int length = this.j.length - 1; length >= 0; length--) {
            textPaint.setTextSize(TypedValue.applyDimension(0, (float) this.j[length], displayMetrics));
            int lineCount = new StaticLayout(charSequence, textPaint, i2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true).getLineCount();
            System.out.println("尝试第" + length + "个字号: " + this.j[length] + " ,lineCount " + lineCount);
            if (lineCount <= 1) {
                return (float) this.j[length];
            }
        }
        return (float) this.j[0];
    }

    private static int a(CharSequence charSequence, TextPaint textPaint, float f2, float f3, DisplayMetrics displayMetrics) {
        textPaint.setTextSize(TypedValue.applyDimension(0, f2, displayMetrics));
        return new StaticLayout(charSequence, textPaint, (int) f3, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true).getLineCount();
    }

    private void a(float f2) {
        if (this.l != f2) {
            this.l = f2;
        }
    }

    public void a(int i2, float f2) {
        if (!this.k) {
            Context context = this.f.getContext();
            Resources system = Resources.getSystem();
            if (context != null) {
                system = context.getResources();
            }
            a(TypedValue.applyDimension(i2, f2, system.getDisplayMetrics()));
        }
    }

    private class AutofitTextWatcher implements TextWatcher {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        private AutofitTextWatcher() {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            AutofitHelper.this.a();
        }
    }

    private class AutofitOnLayoutChangeListener implements View.OnLayoutChangeListener {
        private AutofitOnLayoutChangeListener() {
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            AutofitHelper.this.a();
        }
    }
}
