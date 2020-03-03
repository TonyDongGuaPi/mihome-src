package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import java.lang.ref.WeakReference;

public class EmojiFilterEditText extends EditText {
    /* access modifiers changed from: private */
    public int cursorPos;
    /* access modifiers changed from: private */
    public String inputAfterText;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public boolean resetText = false;

    public EmojiFilterEditText(Context context) {
        super(context);
        this.mContext = context;
        initEditTextWatcher();
    }

    public EmojiFilterEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initEditTextWatcher();
    }

    public EmojiFilterEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        initEditTextWatcher();
    }

    private static class CustomeTextWatcher implements TextWatcher {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<EmojiFilterEditText> f18834a;

        public void afterTextChanged(Editable editable) {
        }

        public CustomeTextWatcher(EmojiFilterEditText emojiFilterEditText) {
            this.f18834a = new WeakReference<>(emojiFilterEditText);
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            EmojiFilterEditText emojiFilterEditText = (EmojiFilterEditText) this.f18834a.get();
            if (emojiFilterEditText != null && !emojiFilterEditText.resetText) {
                String unused = emojiFilterEditText.inputAfterText = charSequence.toString();
                int unused2 = emojiFilterEditText.cursorPos = emojiFilterEditText.getSelectionEnd();
            }
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            EmojiFilterEditText emojiFilterEditText = (EmojiFilterEditText) this.f18834a.get();
            if (emojiFilterEditText != null) {
                if (emojiFilterEditText.resetText) {
                    boolean unused = emojiFilterEditText.resetText = false;
                } else if (i3 >= 1 && StringUtil.t(charSequence.subSequence(i, i3 + i).toString())) {
                    boolean unused2 = emojiFilterEditText.resetText = true;
                    Toast.makeText(emojiFilterEditText.mContext, R.string.input_contains_emoji, 0).show();
                    emojiFilterEditText.setText(emojiFilterEditText.inputAfterText);
                    emojiFilterEditText.setSelection(emojiFilterEditText.cursorPos);
                }
            }
        }
    }

    private void initEditTextWatcher() {
        addTextChangedListener(new CustomeTextWatcher(this));
    }
}
