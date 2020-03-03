package com.mics.widget.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import com.mics.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmileyUtils {

    /* renamed from: a  reason: collision with root package name */
    private static String[] f7854a;

    public static void a(Context context, Spannable spannable) {
        Matcher matcher = Pattern.compile("(\\[.*?\\])").matcher(spannable.toString());
        while (matcher.find()) {
            String group = matcher.group(1);
            int a2 = a(context, group);
            if (a2 != 0) {
                int start = matcher.start();
                Drawable drawable = context.getResources().getDrawable(a2);
                int applyDimension = (int) TypedValue.applyDimension(1, 16.0f, context.getResources().getDisplayMetrics());
                spannable.setSpan(new ImageSpan(a(drawable, applyDimension, applyDimension), 0), start, group.length() + start, 17);
            }
        }
    }

    private static Drawable a(Drawable drawable, int i, int i2) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        int i3 = (int) (((float) intrinsicWidth) / 12.0f);
        drawable.setBounds(i3, 0, intrinsicWidth - i3, intrinsicHeight - ((int) (((float) intrinsicHeight) / 6.0f)));
        drawable.draw(canvas);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(Bitmap.createScaledBitmap(createBitmap, i, i2, true));
        bitmapDrawable.setBounds(0, 0, i, i2);
        return bitmapDrawable;
    }

    public static int a(Context context, String str) {
        Resources resources = context.getResources();
        if (f7854a == null) {
            f7854a = resources.getStringArray(R.array.smiley_values);
        }
        int i = 0;
        for (String equalsIgnoreCase : f7854a) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return resources.getIdentifier("mics_smiley_" + i, "drawable", resources.getResourcePackageName(R.drawable.mics_smiley_0));
            }
            i++;
        }
        return 0;
    }
}
