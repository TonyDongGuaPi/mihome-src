package org.sufficientlysecure.htmltextview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

public class HtmlResImageGetter implements Html.ImageGetter {

    /* renamed from: a  reason: collision with root package name */
    TextView f4188a;

    public HtmlResImageGetter(TextView textView) {
        this.f4188a = textView;
    }

    public Drawable getDrawable(String str) {
        Context context = this.f4188a.getContext();
        int identifier = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        if (identifier == 0) {
            identifier = context.getResources().getIdentifier(str, "drawable", "android");
        }
        if (identifier == 0) {
            Log.e(HtmlTextView.TAG, "source could not be found: " + str);
            return null;
        }
        Drawable drawable = context.getResources().getDrawable(identifier);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }
}
