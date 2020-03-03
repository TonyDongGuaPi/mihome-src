package com.xiaomi.smarthome.frame.login.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import com.xiaomi.smarthome.R;

class VerifyCodeInputItem extends AppCompatEditText {
    private static final int MAX_LENGTH = 1;
    /* access modifiers changed from: private */
    public OnCodeChangeListener mOnCodeChangeListener;

    public interface OnCodeChangeListener {
        void a();

        void a(int i);
    }

    public VerifyCodeInputItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.verify_code_input_item);
        setGravity(17);
        setTextSize(1, 20.0f);
        setTextColor(Color.parseColor("#333333"));
        setFocusableInTouchMode(true);
        setFocusable(true);
        setInputType(2);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
        addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                String obj = editable.toString();
                if (!TextUtils.isEmpty(obj)) {
                    VerifyCodeInputItem.this.mOnCodeChangeListener.a(Integer.valueOf(obj).intValue());
                }
            }
        });
        setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 67) {
                    return false;
                }
                VerifyCodeInputItem.this.mOnCodeChangeListener.a();
                return true;
            }
        });
    }

    public void showCode(int i) {
        setText(String.valueOf(i));
        setSelection(String.valueOf(i).length());
    }

    public void clearPin() {
        setText("");
    }

    public void setOnCodeChangeListener(OnCodeChangeListener onCodeChangeListener) {
        this.mOnCodeChangeListener = onCodeChangeListener;
    }
}
