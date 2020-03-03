package com.xiaomi.jr.mipay.pay.verifier.component;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.xiaomi.jr.common.utils.HashUtils;
import com.xiaomi.jr.mipay.pay.verifier.component.ShortNumberEditText;

public class PasswordEditText extends ShortNumberEditText {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10967a = 6;
    /* access modifiers changed from: private */
    public PasswordInputListener b;

    public interface PasswordInputListener {
        void onPassInputFinish(boolean z);
    }

    public PasswordEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    public PasswordEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PasswordEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setNumberLength(6);
        setNumberVisibility(false);
    }

    public String getPassword() {
        String numbers = getNumbers();
        if (TextUtils.isEmpty(numbers) || numbers.length() != 6) {
            return null;
        }
        for (int i = 0; i < numbers.length(); i++) {
            if (!Character.isDigit(numbers.charAt(i))) {
                return null;
            }
        }
        return HashUtils.a(HashUtils.a(numbers));
    }

    public boolean isPassInputFinish() {
        String numbers = getNumbers();
        return !TextUtils.isEmpty(numbers) && numbers.length() == 6;
    }

    public void clearPassword() {
        clearAll();
    }

    public void setPassInputListener(PasswordInputListener passwordInputListener) {
        this.b = passwordInputListener;
        setInputListener(new ShortNumberEditText.InputListener() {
            public void a(String str, int i) {
                if (PasswordEditText.this.b != null) {
                    PasswordEditText.this.b.onPassInputFinish(i == 6);
                }
            }
        });
    }
}
