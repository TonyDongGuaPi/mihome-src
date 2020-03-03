package com.xiaomi.jr.richtext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;
import com.xiaomi.jr.deeplink.DeeplinkUtils;

@SuppressLint({"ParcelCreator"})
public class NoUnderlineURLSpan extends URLSpan {
    public NoUnderlineURLSpan(String str) {
        super(str);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(textPaint.linkColor);
        textPaint.setUnderlineText(false);
    }

    public void onClick(View view) {
        Activity a2 = ViewContextHelper.a(view);
        if (a2 != null) {
            DeeplinkUtils.openDeeplink(a2, (String) null, getURL());
        }
    }
}
