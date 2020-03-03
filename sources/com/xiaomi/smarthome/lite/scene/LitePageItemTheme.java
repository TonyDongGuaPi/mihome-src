package com.xiaomi.smarthome.lite.scene;

import android.animation.ObjectAnimator;
import android.widget.ImageView;
import com.facebook.react.uimanager.ViewProps;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;

public class LitePageItemTheme implements BaseLiteSceneItemTheme {

    /* renamed from: a  reason: collision with root package name */
    private long f19381a;

    public void a(ImageView imageView, int i) {
        imageView.setImageResource(SmartHomeSceneUtility.c(i));
    }

    public void b(ImageView imageView, int i) {
        if (i > 0) {
            imageView.setImageResource(SmartHomeSceneUtility.c(i));
        } else {
            imageView.setImageResource(R.drawable.lite_scene_user_config);
        }
    }

    public void a(ImageView imageView, ImageView imageView2) {
        if (imageView2 != null) {
            imageView2.setVisibility(0);
            imageView2.clearAnimation();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView2, ViewProps.ROTATION, new float[]{0.0f, 360.0f});
            ofFloat.setDuration(500);
            ofFloat.setRepeatCount(-1);
            ofFloat.start();
            this.f19381a = System.currentTimeMillis();
        }
    }

    public void a(boolean z, ImageView imageView, final ImageView imageView2) {
        if (imageView2 != null) {
            if (z || System.currentTimeMillis() - this.f19381a > 1000) {
                imageView2.clearAnimation();
                imageView2.setVisibility(8);
                return;
            }
            imageView.postDelayed(new Runnable() {
                public void run() {
                    imageView2.clearAnimation();
                    imageView2.setVisibility(8);
                }
            }, (1000 - System.currentTimeMillis()) + this.f19381a);
        }
    }
}
