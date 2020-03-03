package com.mibi.common.component;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class IntentSpan extends ClickableSpan {

    /* renamed from: a  reason: collision with root package name */
    private Context f7488a;
    private String b;
    private String c;
    private int d;
    private OnClickListener e;

    public interface OnClickListener {
        void onClick(String str, String str2);
    }

    public IntentSpan(Context context, String str, String str2) {
        this(context, str, str2, 17170435);
    }

    public IntentSpan(Context context, String str, String str2, int i) {
        this.f7488a = context;
        this.b = str;
        this.c = str2;
        this.d = i;
    }

    public void a(OnClickListener onClickListener) {
        this.e = onClickListener;
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(true);
        textPaint.setColor(this.f7488a.getResources().getColor(this.d));
    }

    public void onClick(View view) {
        if (this.e != null) {
            this.e.onClick(this.c, this.b);
        }
    }
}
