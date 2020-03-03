package org.sufficientlysecure.htmltextview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import java.io.IOException;

public class HtmlAssetsImageGetter implements Html.ImageGetter {

    /* renamed from: a  reason: collision with root package name */
    private final Context f4184a;

    public HtmlAssetsImageGetter(Context context) {
        this.f4184a = context;
    }

    public HtmlAssetsImageGetter(TextView textView) {
        this.f4184a = textView.getContext();
    }

    public Drawable getDrawable(String str) {
        try {
            Drawable createFromStream = Drawable.createFromStream(this.f4184a.getAssets().open(str), (String) null);
            createFromStream.setBounds(0, 0, createFromStream.getIntrinsicWidth(), createFromStream.getIntrinsicHeight());
            return createFromStream;
        } catch (IOException unused) {
            Log.e(HtmlTextView.TAG, "source could not be found: " + str);
            return null;
        }
    }
}
