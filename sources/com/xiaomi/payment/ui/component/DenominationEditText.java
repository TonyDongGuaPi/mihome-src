package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import com.mibi.common.data.Utils;

public class DenominationEditText extends EditText {
    public static final int FULL_NUM_STYLE = 1;
    public static final int SIMPLE_NUM_STYLE = 0;
    /* access modifiers changed from: private */
    public OnDenominationEditChangedListener mDenominationEditTextListener;
    private InputFilter mInputFilter = new InputFilter() {
        /* JADX WARNING: Removed duplicated region for block: B:26:0x007c A[RETURN] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.CharSequence filter(java.lang.CharSequence r9, int r10, int r11, android.text.Spanned r12, int r13, int r14) {
            /*
                r8 = this;
                com.xiaomi.payment.ui.component.DenominationEditText r0 = com.xiaomi.payment.ui.component.DenominationEditText.this
                boolean r0 = r0.isEnabled()
                r1 = 0
                if (r0 != 0) goto L_0x000a
                return r1
            L_0x000a:
                android.text.SpannableStringBuilder r2 = new android.text.SpannableStringBuilder
                r2.<init>(r12)
                r3 = r13
                r4 = r14
                r5 = r9
                r6 = r10
                r7 = r11
                android.text.SpannableStringBuilder r9 = r2.replace(r3, r4, r5, r6, r7)
                java.lang.String r9 = r9.toString()
                boolean r10 = android.text.TextUtils.isEmpty(r9)
                if (r10 == 0) goto L_0x0023
                return r1
            L_0x0023:
                com.xiaomi.payment.ui.component.DenominationEditText r10 = com.xiaomi.payment.ui.component.DenominationEditText.this
                long r10 = r10.checkAndGetDenomination(r9)
                com.xiaomi.payment.ui.component.DenominationEditText r12 = com.xiaomi.payment.ui.component.DenominationEditText.this
                long r12 = r12.mMaxDenomination
                int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
                if (r14 <= 0) goto L_0x0047
                com.xiaomi.payment.ui.component.DenominationEditText r9 = com.xiaomi.payment.ui.component.DenominationEditText.this
                com.xiaomi.payment.ui.component.DenominationEditText$OnDenominationEditChangedListener r9 = r9.mDenominationEditTextListener
                if (r9 == 0) goto L_0x0044
                com.xiaomi.payment.ui.component.DenominationEditText r9 = com.xiaomi.payment.ui.component.DenominationEditText.this
                com.xiaomi.payment.ui.component.DenominationEditText$OnDenominationEditChangedListener r9 = r9.mDenominationEditTextListener
                r9.b(r10)
            L_0x0044:
                java.lang.String r9 = ""
                return r9
            L_0x0047:
                java.lang.String r12 = "\\."
                java.lang.String[] r9 = r9.split(r12)
                int r12 = r9.length
                r13 = 0
                r0 = 1
                if (r0 >= r12) goto L_0x0069
                r9 = r9[r0]
                int r12 = r9.length()
                if (r12 <= r0) goto L_0x005f
                int r12 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
                if (r12 == 0) goto L_0x0066
            L_0x005f:
                int r9 = r9.length()
                r10 = 2
                if (r9 <= r10) goto L_0x007c
            L_0x0066:
                java.lang.String r9 = ""
                return r9
            L_0x0069:
                int r12 = r9.length
                if (r0 != r12) goto L_0x007c
                r12 = 0
                r9 = r9[r12]
                int r9 = r9.length()
                if (r9 <= r0) goto L_0x007c
                int r9 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
                if (r9 != 0) goto L_0x007c
                java.lang.String r9 = ""
                return r9
            L_0x007c:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.payment.ui.component.DenominationEditText.AnonymousClass1.filter(java.lang.CharSequence, int, int, android.text.Spanned, int, int):java.lang.CharSequence");
        }
    };
    /* access modifiers changed from: private */
    public long mMaxDenomination = 90000;
    /* access modifiers changed from: private */
    public int mMaxLength = 6;
    /* access modifiers changed from: private */
    public long mMinDenomination = 1;
    private TextWatcher mTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (DenominationEditText.this.isEnabled()) {
                if (editable.length() > 1 && editable.charAt(0) == '0' && editable.charAt(1) != '.') {
                    editable.replace(0, 1, "");
                }
                if (editable.length() > DenominationEditText.this.mMaxLength) {
                    editable.delete(editable.length() - 1, editable.length());
                }
                long access$000 = DenominationEditText.this.checkAndGetDenomination(editable.toString());
                long access$400 = DenominationEditText.this.mMinDenomination;
                long access$100 = DenominationEditText.this.mMaxDenomination;
                if (access$000 < access$400 || access$000 > access$100) {
                    access$000 = 0;
                }
                if (DenominationEditText.this.mDenominationEditTextListener != null) {
                    DenominationEditText.this.mDenominationEditTextListener.a(access$000);
                }
            }
        }
    };

    public interface OnDenominationEditChangedListener {
        void a(long j);

        void b(long j);
    }

    public DenominationEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setFilters(new InputFilter[]{this.mInputFilter});
        addTextChangedListener(this.mTextWatcher);
    }

    public DenominationEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFilters(new InputFilter[]{this.mInputFilter});
        addTextChangedListener(this.mTextWatcher);
    }

    public DenominationEditText(Context context) {
        super(context);
        setFilters(new InputFilter[]{this.mInputFilter});
        addTextChangedListener(this.mTextWatcher);
    }

    public void setDenomination(long j) {
        setDenomination(j, 0);
    }

    public void setDenomination(long j, int i) {
        if (i == 0) {
            setText(Utils.a(j));
        } else {
            setText(Utils.b(j));
        }
    }

    public long getDenomination() {
        long checkAndGetDenomination = checkAndGetDenomination();
        long j = this.mMinDenomination;
        long j2 = this.mMaxDenomination;
        if (checkAndGetDenomination < j || checkAndGetDenomination > j2) {
            return 0;
        }
        return checkAndGetDenomination;
    }

    private long checkAndGetDenomination() {
        return checkAndGetDenomination(getText().toString());
    }

    /* access modifiers changed from: private */
    public long checkAndGetDenomination(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Math.round(Double.parseDouble(str) * 100.0d);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public void setDenominationEditChangedListener(OnDenominationEditChangedListener onDenominationEditChangedListener) {
        this.mDenominationEditTextListener = onDenominationEditChangedListener;
    }

    public void setMinDenomination(long j) {
        this.mMinDenomination = j;
    }

    public void setMaxDenomination(long j) {
        this.mMaxDenomination = j;
    }

    public void setMaxLength(int i) {
        this.mMaxLength = i;
    }

    public void clearContent() {
        setText("");
        if (this.mDenominationEditTextListener != null) {
            this.mDenominationEditTextListener.a(0);
        }
    }
}
