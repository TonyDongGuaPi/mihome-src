package com.mibi.common.data;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class Formatter {
    public abstract void a(Editable editable);

    public abstract boolean a(char c);

    public abstract boolean a(String str);

    public abstract void b(Editable editable);

    public abstract boolean b(char c);

    class FormattingTextWatcher implements TextWatcher {
        private boolean b = false;
        private boolean c;

        FormattingTextWatcher() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!this.b && !this.c && i2 > 0 && a(charSequence, i, i2)) {
                a();
            }
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!this.b && !this.c && i3 > 0 && a(charSequence, i, i3)) {
                a();
            }
        }

        public void afterTextChanged(Editable editable) {
            boolean z = false;
            if (this.c) {
                if (editable.length() != 0) {
                    z = true;
                }
                this.c = z;
            } else if (!this.b) {
                this.b = true;
                Formatter.this.a(editable);
                this.b = false;
            }
        }

        private boolean a(CharSequence charSequence, int i, int i2) {
            for (int i3 = i; i3 < i + i2; i3++) {
                if (Formatter.this.a(charSequence.charAt(i3))) {
                    return true;
                }
            }
            return false;
        }

        private void a() {
            this.c = true;
        }
    }

    public void a(EditText editText) {
        editText.addTextChangedListener(new FormattingTextWatcher());
    }

    public String b(String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        c((Editable) spannableStringBuilder);
        return spannableStringBuilder.toString();
    }

    public void c(Editable editable) {
        int i = 0;
        while (i < editable.length()) {
            if (!b(editable.charAt(i))) {
                editable.delete(i, i + 1);
            } else {
                i++;
            }
        }
    }

    public int c(String str) {
        return d((Editable) new SpannableStringBuilder(str));
    }

    public int d(Editable editable) {
        int i = 0;
        for (int i2 = 0; i2 < editable.length(); i2++) {
            if (b(editable.charAt(i2))) {
                i++;
            }
        }
        return i;
    }

    public String d(String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        a((Editable) spannableStringBuilder);
        return spannableStringBuilder.toString();
    }

    /* access modifiers changed from: protected */
    public void a(Editable editable, int i, int i2) {
        if (i >= 0 && i <= i2) {
            int min = Math.min(i2, editable.length());
            StringBuilder sb = new StringBuilder();
            int i3 = 0;
            while (true) {
                int i4 = min - i;
                if (i3 < i4) {
                    sb.append("*");
                    i3++;
                } else {
                    editable.replace(i, min, sb, 0, i4);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(Editable editable, int i, int i2, int i3) {
        a(editable, i, Math.min(i3 + i, i2));
    }

    public String e(String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        b((Editable) spannableStringBuilder);
        return spannableStringBuilder.toString();
    }
}
