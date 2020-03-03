package com.xiaomi.smarthome.framework.page.verify.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.xiaomi.smarthome.R;

public class PinSoftKeyboard extends GridLayout {

    /* renamed from: a  reason: collision with root package name */
    private OnPinSoftKeyboardClickListener f17088a;

    public interface OnPinSoftKeyboardClickListener {
        void onBackClick();

        void onDeleteClick();

        void onNumberClick(int i);
    }

    public PinSoftKeyboard(Context context) {
        this(context, (AttributeSet) null);
    }

    public PinSoftKeyboard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.pin_softkeyboard, this, true);
        ButterKnife.bind((View) this);
    }

    @OnClick({2131434028, 2131434029, 2131434030, 2131434031, 2131434032, 2131434033, 2131434034, 2131434035, 2131434036, 2131434027})
    public void onClickNumber(View view) {
        if (this.f17088a != null) {
            TextView textView = (TextView) view;
            if (!TextUtils.isEmpty(textView.getText())) {
                try {
                    this.f17088a.onNumberClick(Integer.valueOf(textView.getText().toString()).intValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClick({2131434037})
    public void onClickPinBack(View view) {
        if (this.f17088a != null) {
            this.f17088a.onBackClick();
        }
    }

    @OnClick({2131434038})
    public void onClickPinDelete(View view) {
        if (this.f17088a != null) {
            this.f17088a.onDeleteClick();
        }
    }

    public void setClickListener(OnPinSoftKeyboardClickListener onPinSoftKeyboardClickListener) {
        this.f17088a = onPinSoftKeyboardClickListener;
    }
}
