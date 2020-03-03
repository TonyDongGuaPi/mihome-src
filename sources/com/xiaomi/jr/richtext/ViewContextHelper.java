package com.xiaomi.jr.richtext;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

public class ViewContextHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11024a = R.id.view_host;

    public static void a(Fragment fragment) {
        a(fragment.getActivity().getWindow().getDecorView().getRootView(), fragment);
    }

    public static void a(View view, Fragment fragment) {
        view.setTag(f11024a, fragment);
    }

    public static Activity a(View view) {
        if (view.getContext() instanceof Activity) {
            return (Activity) view.getContext();
        }
        Activity b = b(view);
        return b == null ? b(view.getRootView()) : b;
    }

    private static Activity b(View view) {
        Object tag = view.getTag(f11024a);
        if (tag instanceof Fragment) {
            return ((Fragment) tag).getActivity();
        }
        if (tag instanceof Activity) {
            return (Activity) tag;
        }
        return null;
    }
}
