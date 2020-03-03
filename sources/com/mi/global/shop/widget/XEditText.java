package com.mi.global.shop.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.google.code.microlog4android.format.PatternFormatter;
import com.mi.global.shop.util.FontUtil;

public class XEditText extends CustomEditTextView {
    FinishedEditListener finishedEditListener;
    /* access modifiers changed from: private */
    public boolean hasFocused;
    private boolean hasNoSeparator;
    /* access modifiers changed from: private */
    public int inputLength;
    /* access modifiers changed from: private */
    public int[] intervals;
    /* access modifiers changed from: private */
    public int mMaxLength;
    /* access modifiers changed from: private */
    public int mPreLength;
    /* access modifiers changed from: private */
    public String mSeparator;
    /* access modifiers changed from: private */
    public String mSeparatorString;
    public SeparatorTextWatcher mTextWatcher;
    /* access modifiers changed from: private */
    public OnXTextChangeListener mXTextChangeListener;
    /* access modifiers changed from: private */
    public int[] pattern;

    public interface FinishedEditListener {
        void a(boolean z);
    }

    public interface OnXTextChangeListener {
        void a(Editable editable);

        void a(CharSequence charSequence, int i, int i2, int i3);

        void b(CharSequence charSequence, int i, int i2, int i3);
    }

    public XEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    public XEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842862);
    }

    public XEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMaxLength = Integer.MAX_VALUE;
        FontUtil.a(this, context);
        if (this.mSeparator == null) {
            this.mSeparator = "";
            this.mSeparatorString = "";
        }
        this.mTextWatcher = new SeparatorTextWatcher();
        addTextChangedListener(this.mTextWatcher);
        setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                boolean unused = XEditText.this.hasFocused = z;
            }
        });
    }

    public void setInputLength(int i) {
        this.inputLength = i;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public void setFinishedEditListener(FinishedEditListener finishedEditListener2) {
        this.finishedEditListener = finishedEditListener2;
    }

    private class SeparatorTextWatcher implements TextWatcher {
        private SeparatorTextWatcher() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            int unused = XEditText.this.mPreLength = charSequence.length();
            if (XEditText.this.mXTextChangeListener != null) {
                XEditText.this.mXTextChangeListener.a(charSequence, i, i2, i3);
            }
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (XEditText.this.mXTextChangeListener != null) {
                XEditText.this.mXTextChangeListener.b(charSequence, i, i2, i3);
            }
            if (charSequence.length() <= XEditText.this.mPreLength) {
                return;
            }
            if (charSequence.toString().contains(XEditText.this.mSeparator) || charSequence.length() > XEditText.this.inputLength) {
                XEditText.this.removeTextChangedListener(XEditText.this.mTextWatcher);
                String replaceAll = charSequence.toString().replaceAll(XEditText.this.mSeparator, "");
                if (replaceAll.length() > XEditText.this.inputLength) {
                    replaceAll = replaceAll.substring(0, XEditText.this.inputLength);
                }
                XEditText.this.setText(replaceAll);
                XEditText.this.addTextChangedListener(XEditText.this.mTextWatcher);
            }
        }

        public void afterTextChanged(Editable editable) {
            if (XEditText.this.mXTextChangeListener != null) {
                XEditText.this.mXTextChangeListener.a(editable);
            }
            int length = editable.length();
            if (TextUtils.isEmpty(XEditText.this.mSeparatorString)) {
                int unused = XEditText.this.mMaxLength = length;
            }
            if (length > XEditText.this.mMaxLength) {
                XEditText.this.getText().delete(XEditText.this.mMaxLength, length);
                if (XEditText.this.finishedEditListener == null) {
                    return;
                }
                if (XEditText.this.getText().toString().trim().length() >= XEditText.this.mMaxLength) {
                    XEditText.this.finishedEditListener.a(true);
                } else {
                    XEditText.this.finishedEditListener.a(false);
                }
            } else if (XEditText.this.pattern != null && editable.toString().trim().length() != 0) {
                XEditText.this.removeTextChangedListener(XEditText.this.mTextWatcher);
                if (length > XEditText.this.mPreLength) {
                    for (int i = 0; i < XEditText.this.pattern.length - 1; i++) {
                        if (length > XEditText.this.intervals[i]) {
                            XEditText.this.getText().insert(XEditText.this.intervals[i], XEditText.this.mSeparatorString);
                        } else if (i == 0) {
                            break;
                        }
                    }
                    if (editable.length() > 0) {
                        XEditText.this.setSelection(XEditText.this.getText().length());
                    }
                } else {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= XEditText.this.pattern.length) {
                            break;
                        } else if (length - 1 == XEditText.this.intervals[i2]) {
                            XEditText.this.getText().delete(length - 2, length);
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                if (XEditText.this.finishedEditListener != null) {
                    if (XEditText.this.getText().toString().trim().length() >= XEditText.this.mMaxLength) {
                        XEditText.this.finishedEditListener.a(true);
                    } else {
                        XEditText.this.finishedEditListener.a(false);
                    }
                }
                XEditText.this.addTextChangedListener(XEditText.this.mTextWatcher);
            } else if (XEditText.this.finishedEditListener != null) {
                XEditText.this.finishedEditListener.a(false);
            }
        }
    }

    private boolean isTextEmpty() {
        return getText().toString().trim().length() == 0;
    }

    private int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, Resources.getSystem().getDisplayMetrics());
    }

    public void setSeparator(@NonNull String str, int i) {
        this.mSeparator = str;
        this.hasNoSeparator = false;
        this.mSeparatorString = str;
        while (true) {
            i--;
            if (i > 0) {
                this.mSeparatorString += str;
            } else {
                return;
            }
        }
    }

    public void setPattern(@NonNull int[] iArr, @NonNull String str, int i) {
        setSeparator(str, i);
        setPattern(iArr);
    }

    public void setPattern(@NonNull int[] iArr) {
        this.pattern = iArr;
        this.intervals = new int[iArr.length];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            i += iArr[i3];
            this.intervals[i3] = i + i2;
            if (i3 < iArr.length - 1) {
                i2 += this.mSeparatorString.length();
            }
        }
        this.mMaxLength = this.intervals[this.intervals.length - 1];
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.mMaxLength)});
    }

    @Deprecated
    public String getNonSeparatorText() {
        return getText().toString().replaceAll(this.mSeparator, "");
    }

    public String getTrimmedString() {
        if (this.hasNoSeparator) {
            return getText().toString().trim();
        }
        return getText().toString().replaceAll(this.mSeparator, "").trim();
    }

    public boolean hasNoSeparator() {
        return this.hasNoSeparator;
    }

    public void setHasNoSeparator(boolean z) {
        this.hasNoSeparator = z;
        if (z) {
            this.mSeparator = "";
            this.mSeparatorString = "";
        }
    }

    public void setOnXTextChangeListener(OnXTextChangeListener onXTextChangeListener) {
        this.mXTextChangeListener = onXTextChangeListener;
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("save_instance", super.onSaveInstanceState());
        bundle.putString("separator", this.mSeparator);
        bundle.putString("mSeparatorString", this.mSeparatorString);
        bundle.putIntArray(PatternFormatter.PATTERN_PROPERTY, this.pattern);
        bundle.putBoolean("hasNoSeparator", this.hasNoSeparator);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mSeparator = bundle.getString("separator");
            this.mSeparatorString = bundle.getString("mSeparatorString");
            this.pattern = bundle.getIntArray(PatternFormatter.PATTERN_PROPERTY);
            this.hasNoSeparator = bundle.getBoolean("hasNoSeparator");
            if (this.pattern != null) {
                setPattern(this.pattern);
            }
            super.onRestoreInstanceState(bundle.getParcelable("save_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
