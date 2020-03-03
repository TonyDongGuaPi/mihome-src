package com.transitionseverywhere.utils;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.ViewGroup;
import com.transitionseverywhere.R;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@TargetApi(14)
public class ViewGroupUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final BaseViewGroupUtils f9504a;

    @TargetApi(16)
    static class BaseViewGroupUtils {

        /* renamed from: a  reason: collision with root package name */
        private static final int f9505a = 4;
        private static Field b;
        private static LayoutTransition c;
        private static Method d;

        BaseViewGroupUtils() {
        }

        public void a(ViewGroup viewGroup, boolean z) {
            if (c == null) {
                c = new LayoutTransition() {
                    public boolean isChangingLayout() {
                        return true;
                    }
                };
                c.setAnimator(2, (Animator) null);
                c.setAnimator(0, (Animator) null);
                c.setAnimator(1, (Animator) null);
                c.setAnimator(3, (Animator) null);
                c.setAnimator(4, (Animator) null);
            }
            if (z) {
                a(viewGroup);
                LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
                if (!(layoutTransition == null || layoutTransition == c)) {
                    viewGroup.setTag(R.id.group_layouttransition_backup, viewGroup.getLayoutTransition());
                }
                viewGroup.setLayoutTransition(c);
                return;
            }
            viewGroup.setLayoutTransition((LayoutTransition) null);
            if (b == null) {
                b = ReflectionUtils.a(ViewGroup.class, "mLayoutSuppressed");
            }
            if (!Boolean.FALSE.equals((Boolean) ReflectionUtils.a((Object) viewGroup, (Object) Boolean.FALSE, b))) {
                ReflectionUtils.a((Object) viewGroup, b, (Object) false);
                viewGroup.requestLayout();
            }
            LayoutTransition layoutTransition2 = (LayoutTransition) viewGroup.getTag(R.id.group_layouttransition_backup);
            if (layoutTransition2 != null) {
                viewGroup.setTag(R.id.group_layouttransition_backup, (Object) null);
                viewGroup.setLayoutTransition(layoutTransition2);
            }
        }

        public boolean a(ViewGroup viewGroup) {
            LayoutTransition layoutTransition;
            if (viewGroup == null || (layoutTransition = viewGroup.getLayoutTransition()) == null || !layoutTransition.isRunning()) {
                return false;
            }
            if (d == null) {
                d = ReflectionUtils.b(LayoutTransition.class, "cancel", new Class[0]);
            }
            ReflectionUtils.a((Object) viewGroup.getLayoutTransition(), (Object) null, d);
            return true;
        }
    }

    @TargetApi(18)
    static class JellyBeanMr2ViewGroupUtils extends BaseViewGroupUtils {

        /* renamed from: a  reason: collision with root package name */
        private static Method f9507a;

        JellyBeanMr2ViewGroupUtils() {
        }

        public void a(ViewGroup viewGroup, boolean z) {
            if (f9507a == null) {
                f9507a = ReflectionUtils.a((Class<?>) ViewGroup.class, "suppressLayout", (Class<?>[]) new Class[]{Boolean.TYPE});
            }
            ReflectionUtils.a((Object) viewGroup, (Object) null, f9507a, (Object) Boolean.valueOf(z));
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            f9504a = new JellyBeanMr2ViewGroupUtils();
        } else {
            f9504a = new BaseViewGroupUtils();
        }
    }

    public static void a(ViewGroup viewGroup, boolean z) {
        if (viewGroup != null) {
            f9504a.a(viewGroup, z);
        }
    }

    public static boolean a(ViewGroup viewGroup) {
        return f9504a.a(viewGroup);
    }
}
