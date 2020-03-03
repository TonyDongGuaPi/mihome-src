package org.sufficientlysecure.htmltextview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.text.Html;
import android.util.AttributeSet;
import java.io.InputStream;
import java.util.Scanner;

public class HtmlTextView extends JellyBeanSpanFixTextView {
    public static final boolean DEBUG = false;
    public static final String TAG = "HtmlTextView";
    @Nullable
    private ClickableTableSpan clickableTableSpan;
    @Nullable
    private DrawTableLinkSpan drawTableLinkSpan;
    private boolean removeFromHtmlSpace = true;

    public HtmlTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public HtmlTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HtmlTextView(Context context) {
        super(context);
    }

    public void setHtml(@RawRes int i) {
        setHtml(i, (Html.ImageGetter) null);
    }

    public void setHtml(@NonNull String str) {
        setHtml(str, (Html.ImageGetter) null);
    }

    public void setHtml(@RawRes int i, @Nullable Html.ImageGetter imageGetter) {
        setHtml(convertStreamToString(getContext().getResources().openRawResource(i)), imageGetter);
    }

    public void setHtml(@NonNull String str, @Nullable Html.ImageGetter imageGetter) {
        HtmlTagHandler htmlTagHandler = new HtmlTagHandler(getPaint());
        htmlTagHandler.a(this.clickableTableSpan);
        htmlTagHandler.a(this.drawTableLinkSpan);
        String a2 = htmlTagHandler.a(str);
        if (this.removeFromHtmlSpace) {
            setText(removeHtmlBottomPadding(Html.fromHtml(a2, imageGetter, htmlTagHandler)));
        } else {
            setText(Html.fromHtml(a2, imageGetter, htmlTagHandler));
        }
        setMovementMethod(LocalLinkMovementMethod.a());
    }

    public void setRemoveFromHtmlSpace(boolean z) {
        this.removeFromHtmlSpace = z;
    }

    public void setClickableTableSpan(@Nullable ClickableTableSpan clickableTableSpan2) {
        this.clickableTableSpan = clickableTableSpan2;
    }

    public void setDrawTableLinkSpan(@Nullable DrawTableLinkSpan drawTableLinkSpan2) {
        this.drawTableLinkSpan = drawTableLinkSpan2;
    }

    @NonNull
    private static String convertStreamToString(@NonNull InputStream inputStream) {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    @Nullable
    private static CharSequence removeHtmlBottomPadding(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        while (charSequence.length() > 0 && charSequence.charAt(charSequence.length() - 1) == 10) {
            charSequence = charSequence.subSequence(0, charSequence.length() - 1);
        }
        return charSequence;
    }
}
