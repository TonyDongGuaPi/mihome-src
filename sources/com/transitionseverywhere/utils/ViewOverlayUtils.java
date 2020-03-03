package com.transitionseverywhere.utils;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ViewGroup;

public class ViewOverlayUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final BaseViewOverlayUtils f9509a;

    static class BaseViewOverlayUtils {
        BaseViewOverlayUtils() {
        }

        public void a(ViewGroup viewGroup, Drawable drawable) {
            ViewOverlayPreJellybean.a(viewGroup).a(drawable);
        }

        public void b(ViewGroup viewGroup, Drawable drawable) {
            ViewOverlayPreJellybean.a(viewGroup).b(drawable);
        }
    }

    @TargetApi(18)
    static class JellyBeanMR2ViewUtils extends BaseViewOverlayUtils {
        JellyBeanMR2ViewUtils() {
        }

        public void a(ViewGroup viewGroup, Drawable drawable) {
            viewGroup.getOverlay().add(drawable);
        }

        public void b(ViewGroup viewGroup, Drawable drawable) {
            viewGroup.getOverlay().remove(drawable);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            f9509a = new JellyBeanMR2ViewUtils();
        } else {
            f9509a = new BaseViewOverlayUtils();
        }
    }

    public static void a(ViewGroup viewGroup, Drawable drawable) {
        f9509a.a(viewGroup, drawable);
    }

    public static void b(ViewGroup viewGroup, Drawable drawable) {
        f9509a.b(viewGroup, drawable);
    }
}
