package net.qiujuer.genius.ui.compat;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import net.qiujuer.genius.ui.drawable.BalloonMarkerDrawable;

public class UiCompat {
    public static void a(View view, BalloonMarkerDrawable balloonMarkerDrawable) {
        if (Build.VERSION.SDK_INT >= 21) {
            UiCompatNotCrash.a(view, balloonMarkerDrawable);
        }
    }

    public static void a(Drawable drawable, int i, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 21) {
            int i5 = (i3 - i) / 8;
            drawable.setHotspotBounds(i + i5, i2 + i5, i3 - i5, i4 - i5);
            return;
        }
        drawable.setBounds(i, i2, i3, i4);
    }

    public static void a(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            UiCompatNotCrash.a(view, drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void a(TextView textView, int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            UiCompatNotCrash.a(textView, i);
        }
    }

    public static int a(Resources resources, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return resources.getColor(i, (Resources.Theme) null);
        }
        return resources.getColor(i);
    }

    public static ColorStateList b(Resources resources, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return resources.getColorStateList(i, (Resources.Theme) null);
        }
        return resources.getColorStateList(i);
    }
}
