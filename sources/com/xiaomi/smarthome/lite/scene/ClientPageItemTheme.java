package com.xiaomi.smarthome.lite.scene;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.facebook.react.uimanager.ViewProps;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;

public class ClientPageItemTheme implements BaseLiteSceneItemTheme {

    /* renamed from: a  reason: collision with root package name */
    private long f19375a;

    public void a(ImageView imageView, int i) {
        imageView.setBackgroundResource(SmartHomeSceneUtility.d(i));
        imageView.setImageResource(SmartHomeSceneUtility.e(i));
    }

    public void b(ImageView imageView, int i) {
        if (i != -1) {
            imageView.setBackgroundResource(SmartHomeSceneUtility.d(i));
            imageView.setImageResource(SmartHomeSceneUtility.e(i));
            return;
        }
        imageView.setBackgroundResource(R.drawable.client_page_scene_user_define_bg);
        imageView.setImageResource(R.drawable.client_page_scene_user_define);
    }

    public void a(ImageView imageView, ImageView imageView2) {
        Drawable drawable = imageView.getDrawable();
        imageView.setImageResource(R.drawable.client_page_scene_loading);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, ViewProps.ROTATION, new float[]{0.0f, 360.0f});
        ofFloat.setDuration(500);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.setRepeatCount(-1);
        ofFloat.start();
        imageView.setTag(R.id.tag_first, drawable);
        imageView.setTag(R.id.tag_second, ofFloat);
        this.f19375a = System.currentTimeMillis();
    }

    public void a(boolean z, final ImageView imageView, ImageView imageView2) {
        ObjectAnimator objectAnimator;
        Drawable drawable;
        if (imageView == null) {
            return;
        }
        if (z || System.currentTimeMillis() - this.f19375a > 1000) {
            if ((imageView.getTag(R.id.tag_first) instanceof Drawable) && (drawable = (Drawable) imageView.getTag(R.id.tag_first)) != null) {
                Miio.b("ClientPageItemTheme", "drawable----" + drawable.hashCode());
                imageView.setImageDrawable(drawable);
            }
            if ((imageView.getTag(R.id.tag_second) instanceof ObjectAnimator) && (objectAnimator = (ObjectAnimator) imageView.getTag(R.id.tag_second)) != null) {
                Miio.b("ClientPageItemTheme", "rotate cnacle----" + objectAnimator.hashCode());
                objectAnimator.cancel();
                objectAnimator.end();
            }
            imageView.clearAnimation();
            return;
        }
        imageView.postDelayed(new Runnable() {
            public void run() {
                ObjectAnimator objectAnimator;
                Drawable drawable;
                if ((imageView.getTag(R.id.tag_first) instanceof Drawable) && (drawable = (Drawable) imageView.getTag(R.id.tag_first)) != null) {
                    Miio.b("ClientPageItemTheme postDelayed", "drawable----" + drawable.hashCode());
                    imageView.setImageDrawable(drawable);
                }
                if ((imageView.getTag(R.id.tag_second) instanceof ObjectAnimator) && (objectAnimator = (ObjectAnimator) imageView.getTag(R.id.tag_second)) != null) {
                    Miio.b("ClientPageItemTheme postDelayed", "rotate cnacle----" + objectAnimator.hashCode());
                    objectAnimator.cancel();
                    objectAnimator.end();
                }
                imageView.clearAnimation();
            }
        }, (1000 - System.currentTimeMillis()) + this.f19375a);
    }
}
