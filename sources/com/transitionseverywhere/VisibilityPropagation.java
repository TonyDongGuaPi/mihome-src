package com.transitionseverywhere;

import android.annotation.TargetApi;
import android.view.View;

@TargetApi(14)
public abstract class VisibilityPropagation extends TransitionPropagation {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9490a = "android:visibilityPropagation:visibility";
    private static final String b = "android:visibilityPropagation:center";
    private static final String[] c = {f9490a, b};

    public void a(TransitionValues transitionValues) {
        View view = transitionValues.f9482a;
        Integer num = (Integer) transitionValues.b.get("android:visibility:visibility");
        if (num == null) {
            num = Integer.valueOf(view.getVisibility());
        }
        transitionValues.b.put(f9490a, num);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        iArr[0] = iArr[0] + Math.round(view.getTranslationX());
        iArr[0] = iArr[0] + (view.getWidth() / 2);
        iArr[1] = iArr[1] + Math.round(view.getTranslationY());
        iArr[1] = iArr[1] + (view.getHeight() / 2);
        transitionValues.b.put(b, iArr);
    }

    public String[] a() {
        return c;
    }

    public int b(TransitionValues transitionValues) {
        Integer num;
        if (transitionValues == null || (num = (Integer) transitionValues.b.get(f9490a)) == null) {
            return 8;
        }
        return num.intValue();
    }

    public int c(TransitionValues transitionValues) {
        return a(transitionValues, 0);
    }

    public int d(TransitionValues transitionValues) {
        return a(transitionValues, 1);
    }

    private static int a(TransitionValues transitionValues, int i) {
        int[] iArr;
        if (transitionValues == null || (iArr = (int[]) transitionValues.b.get(b)) == null) {
            return -1;
        }
        return iArr[i];
    }
}
