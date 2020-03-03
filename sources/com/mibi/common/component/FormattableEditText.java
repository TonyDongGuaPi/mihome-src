package com.mibi.common.component;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.EditText;
import com.mibi.common.data.FormatterUtils;
import com.mibi.common.data.MaxLengthFilter;

public class FormattableEditText extends EditText {
    private FormatterUtils.FormatterType mFormatType = FormatterUtils.FormatterType.TYPE_NORMAL;

    public FormattableEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public FormattableEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FormattableEditText(Context context) {
        super(context);
    }

    public void setFormatType(FormatterUtils.FormatterType formatterType) {
        FormatterUtils.a((EditText) this, formatterType);
        this.mFormatType = formatterType;
    }

    public void setMaxLength(int i) {
        if (i > 0 && this.mFormatType != null) {
            setFilters(new InputFilter[]{new MaxLengthFilter(i, this.mFormatType.getFormatter())});
        }
    }
}
