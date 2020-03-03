package net.qiujuer.genius.ui.compat;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;
import net.qiujuer.genius.ui.drawable.BalloonMarkerDrawable;

@TargetApi(21)
class UiCompatNotCrash {
    UiCompatNotCrash() {
    }

    static void a(View view, final BalloonMarkerDrawable balloonMarkerDrawable) {
        view.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                outline.setConvexPath(balloonMarkerDrawable.a());
            }
        });
    }

    static void a(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    static void a(TextView textView, int i) {
        textView.setTextDirection(i);
    }
}
