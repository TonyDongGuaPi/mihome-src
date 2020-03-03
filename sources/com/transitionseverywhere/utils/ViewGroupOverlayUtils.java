package com.transitionseverywhere.utils;

import android.annotation.TargetApi;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;

public class ViewGroupOverlayUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final BaseViewGroupOverlayUtils f9503a;

    static class BaseViewGroupOverlayUtils {
        BaseViewGroupOverlayUtils() {
        }

        public void a(ViewGroup viewGroup, View view, int i, int i2) {
            ViewOverlayPreJellybean a2 = ViewOverlayPreJellybean.a(viewGroup);
            if (a2 != null) {
                a2.addView(view, i, i2);
            }
        }

        public void a(ViewGroup viewGroup, View view) {
            ViewOverlayPreJellybean a2 = ViewOverlayPreJellybean.a(viewGroup);
            if (a2 != null) {
                a2.removeView(view);
            }
        }

        public void b(ViewGroup viewGroup, View view, int i, int i2) {
            ViewOverlayPreJellybean a2 = ViewOverlayPreJellybean.a(viewGroup);
            if (a2 != null) {
                a2.a(view, i, i2);
            }
        }

        public void a(ViewGroup viewGroup) {
            ViewOverlayPreJellybean.a(viewGroup);
        }

        public int[] b(ViewGroup viewGroup, View view) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            return iArr;
        }

        public void a(boolean z, View view, int i, BitmapDrawable bitmapDrawable, BitmapDrawable bitmapDrawable2) {
            if (view.getParent() != null && (view.getParent() instanceof ViewGroup)) {
                ViewOverlayPreJellybean a2 = ViewOverlayPreJellybean.a((ViewGroup) view.getParent());
                if (i == 1) {
                    a2.a((Drawable) bitmapDrawable2);
                }
                a2.a((Drawable) bitmapDrawable);
            }
        }

        public void b(boolean z, View view, int i, BitmapDrawable bitmapDrawable, BitmapDrawable bitmapDrawable2) {
            if (view.getParent() != null && (view.getParent() instanceof ViewGroup)) {
                ViewOverlayPreJellybean a2 = ViewOverlayPreJellybean.a((ViewGroup) view.getParent());
                a2.b(bitmapDrawable);
                if (i == 1) {
                    a2.b(bitmapDrawable2);
                }
            }
        }
    }

    @TargetApi(18)
    static class JellyBeanMR2ViewGroupUtils extends BaseViewGroupOverlayUtils {
        public void a(ViewGroup viewGroup) {
        }

        JellyBeanMR2ViewGroupUtils() {
        }

        public void a(ViewGroup viewGroup, View view, int i, int i2) {
            b(viewGroup, view, i, i2);
            viewGroup.getOverlay().add(view);
        }

        public void a(ViewGroup viewGroup, View view) {
            viewGroup.getOverlay().remove(view);
        }

        public void b(ViewGroup viewGroup, View view, int i, int i2) {
            if (i != 0 || i2 != 0) {
                int[] iArr = new int[2];
                viewGroup.getLocationOnScreen(iArr);
                view.offsetLeftAndRight((i - iArr[0]) - view.getLeft());
                view.offsetTopAndBottom((i2 - iArr[1]) - view.getTop());
            }
        }

        public void a(boolean z, View view, int i, BitmapDrawable bitmapDrawable, BitmapDrawable bitmapDrawable2) {
            ViewOverlay a2 = a(z, view);
            if (i == 1) {
                a2.add(bitmapDrawable2);
            }
            a2.add(bitmapDrawable);
        }

        public int[] b(ViewGroup viewGroup, View view) {
            int[] iArr = new int[2];
            viewGroup.getLocationOnScreen(iArr);
            iArr[0] = iArr[0] + view.getLeft();
            iArr[1] = iArr[1] + view.getTop();
            return iArr;
        }

        public void b(boolean z, View view, int i, BitmapDrawable bitmapDrawable, BitmapDrawable bitmapDrawable2) {
            ViewOverlay a2 = a(z, view);
            a2.remove(bitmapDrawable);
            if (i == 1) {
                a2.remove(bitmapDrawable2);
            }
        }

        private static ViewOverlay a(boolean z, View view) {
            return z ? ((ViewGroup) view.getParent()).getOverlay() : view.getOverlay();
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            f9503a = new JellyBeanMR2ViewGroupUtils();
        } else {
            f9503a = new BaseViewGroupOverlayUtils();
        }
    }

    public static void a(ViewGroup viewGroup, View view, int i, int i2) {
        if (view != null) {
            f9503a.a(viewGroup, view, i, i2);
        }
    }

    public static void a(ViewGroup viewGroup) {
        f9503a.a(viewGroup);
    }

    public static void a(ViewGroup viewGroup, View view) {
        if (view != null) {
            f9503a.a(viewGroup, view);
        }
    }

    public static void b(ViewGroup viewGroup, View view, int i, int i2) {
        if (view != null) {
            f9503a.b(viewGroup, view, i, i2);
        }
    }

    public static int[] b(ViewGroup viewGroup, View view) {
        if (view != null) {
            return f9503a.b(viewGroup, view);
        }
        return new int[2];
    }

    public static void a(boolean z, View view, int i, BitmapDrawable bitmapDrawable, BitmapDrawable bitmapDrawable2) {
        if (view != null) {
            f9503a.a(z, view, i, bitmapDrawable, bitmapDrawable2);
        }
    }

    public static void b(boolean z, View view, int i, BitmapDrawable bitmapDrawable, BitmapDrawable bitmapDrawable2) {
        if (view != null) {
            f9503a.b(z, view, i, bitmapDrawable, bitmapDrawable2);
        }
    }
}
