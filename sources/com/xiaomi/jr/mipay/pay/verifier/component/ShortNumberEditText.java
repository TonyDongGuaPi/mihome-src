package com.xiaomi.jr.mipay.pay.verifier.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.jr.mipay.pay.verifier.R;

public class ShortNumberEditText extends LinearLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public int f10969a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    /* access modifiers changed from: private */
    public InputListener h;
    /* access modifiers changed from: private */
    public int i;
    /* access modifiers changed from: private */
    public StringBuilder j;
    private Context k;

    public interface InputListener {
        void a(String str, int i);
    }

    public boolean onCheckIsTextEditor() {
        return true;
    }

    static /* synthetic */ int access$008(ShortNumberEditText shortNumberEditText) {
        int i2 = shortNumberEditText.i;
        shortNumberEditText.i = i2 + 1;
        return i2;
    }

    static /* synthetic */ int access$010(ShortNumberEditText shortNumberEditText) {
        int i2 = shortNumberEditText.i;
        shortNumberEditText.i = i2 - 1;
        return i2;
    }

    public ShortNumberEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    public ShortNumberEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShortNumberEditText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.i = 0;
        this.j = new StringBuilder();
        this.k = context.getApplicationContext();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.Jr_Mipay_ShortNumberEditText, i2, 0);
        this.f10969a = obtainStyledAttributes.getInt(R.styleable.Jr_Mipay_ShortNumberEditText_numberLength, 4);
        this.b = obtainStyledAttributes.getBoolean(R.styleable.Jr_Mipay_ShortNumberEditText_numberVisibility, true);
        this.c = obtainStyledAttributes.getResourceId(R.styleable.Jr_Mipay_ShortNumberEditText_numberBackground, 0);
        this.d = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Jr_Mipay_ShortNumberEditText_numberWidth, 120);
        this.e = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Jr_Mipay_ShortNumberEditText_numberHeight, 120);
        this.f = obtainStyledAttributes.getColor(R.styleable.Jr_Mipay_ShortNumberEditText_numberColor, 0);
        this.g = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Jr_Mipay_ShortNumberEditText_numberSize, 15);
        obtainStyledAttributes.recycle();
        setOrientation(0);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClickable(true);
        a();
    }

    private void a() {
        if (this.f10969a < getChildCount()) {
            removeViews(this.f10969a, getChildCount() - this.f10969a);
        } else {
            for (int childCount = getChildCount(); childCount < this.f10969a; childCount++) {
                ShortNumberEditTextItem shortNumberEditTextItem = new ShortNumberEditTextItem(getContext());
                shortNumberEditTextItem.setSingleLine();
                shortNumberEditTextItem.setMaxEms(1);
                shortNumberEditTextItem.setTextColor(this.f);
                shortNumberEditTextItem.setTextSize(0, (float) this.g);
                shortNumberEditTextItem.setGravity(17);
                shortNumberEditTextItem.setInputType(1 | (this.b ? 144 : 128));
                addView(shortNumberEditTextItem, new LinearLayout.LayoutParams(this.d, this.e));
            }
        }
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof ShortNumberEditTextItem) {
                ((ShortNumberEditTextItem) childAt).setBackground(this.c, i2, this.f10969a);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void clearAll() {
        for (int i2 = 0; i2 < this.f10969a; i2++) {
            ((TextView) getChildAt(i2)).setText("");
        }
        this.i = 0;
        this.j.setLength(0);
        if (this.h != null) {
            this.h.a(this.j.toString(), this.i);
        }
    }

    public void setNumberLength(int i2) {
        this.f10969a = i2;
        a();
    }

    public void setNumberVisibility(boolean z) {
        this.b = z;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            ((TextView) getChildAt(i2)).setInputType((this.b ? 144 : 128) | 1);
        }
    }

    public void setItemBackground(int i2) {
        if (i2 != 0) {
            this.c = i2;
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                ((TextView) getChildAt(i3)).setBackgroundResource(this.c);
            }
        }
    }

    public void setItemSize(int i2, int i3) {
        this.d = i2;
        this.e = i3;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            TextView textView = (TextView) getChildAt(i4);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LinearLayout.LayoutParams(this.d, this.e);
            } else {
                layoutParams.weight = (float) this.d;
                layoutParams.height = this.e;
            }
            textView.setLayoutParams(layoutParams);
        }
    }

    public String getNumbers() {
        return this.j.toString();
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return new ShortNumberInputConnection(this, false);
    }

    private class ShortNumberInputConnection extends BaseInputConnection {
        public ShortNumberInputConnection(View view, boolean z) {
            super(view, z);
        }

        public boolean commitText(CharSequence charSequence, int i) {
            if (charSequence.length() != 1) {
                return true;
            }
            a(charSequence.charAt(0));
            return true;
        }

        public boolean sendKeyEvent(KeyEvent keyEvent) {
            if (keyEvent.getAction() == 1) {
                int keyCode = keyEvent.getKeyCode();
                if (keyCode == 67) {
                    a();
                    return true;
                } else if (keyCode >= 7 && keyCode <= 16) {
                    a((char) ((keyCode + 48) - 7));
                    return true;
                }
            }
            return super.sendKeyEvent(keyEvent);
        }

        private void a(char c) {
            if (ShortNumberEditText.this.i != ShortNumberEditText.this.f10969a && Character.isDigit(c)) {
                ((TextView) ShortNumberEditText.this.getChildAt(ShortNumberEditText.this.i)).setText(c + "");
                ShortNumberEditText.access$008(ShortNumberEditText.this);
                ShortNumberEditText.this.j.append(c);
                if (ShortNumberEditText.this.h != null) {
                    ShortNumberEditText.this.h.a(ShortNumberEditText.this.j.toString(), ShortNumberEditText.this.i);
                }
            }
        }

        private void a() {
            if (ShortNumberEditText.this.i != 0) {
                ShortNumberEditText.access$010(ShortNumberEditText.this);
                ShortNumberEditText.this.j.deleteCharAt(ShortNumberEditText.this.i);
                ((TextView) ShortNumberEditText.this.getChildAt(ShortNumberEditText.this.i)).setText("");
                if (ShortNumberEditText.this.h != null) {
                    ShortNumberEditText.this.h.a(ShortNumberEditText.this.j.toString(), ShortNumberEditText.this.i);
                }
            }
        }
    }

    public void setInputListener(InputListener inputListener) {
        this.h = inputListener;
    }
}
