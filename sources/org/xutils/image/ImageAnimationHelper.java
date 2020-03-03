package org.xutils.image;

import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import java.lang.reflect.Method;
import org.xutils.common.util.LogUtil;

public final class ImageAnimationHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final Method f11925a;

    static {
        Method method;
        try {
            method = Animation.class.getDeclaredMethod("clone", new Class[0]);
            method.setAccessible(true);
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
            method = null;
        }
        f11925a = method;
    }

    private ImageAnimationHelper() {
    }

    public static void a(ImageView imageView, Drawable drawable) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        imageView.setImageDrawable(drawable);
        imageView.startAnimation(alphaAnimation);
    }

    public static void a(ImageView imageView, Drawable drawable, Animation animation) {
        imageView.setImageDrawable(drawable);
        if (f11925a == null || animation == null) {
            imageView.startAnimation(animation);
            return;
        }
        try {
            imageView.startAnimation((Animation) f11925a.invoke(animation, new Object[0]));
        } catch (Throwable unused) {
            imageView.startAnimation(animation);
        }
    }
}
