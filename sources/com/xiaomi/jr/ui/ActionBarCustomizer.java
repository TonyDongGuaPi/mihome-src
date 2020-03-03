package com.xiaomi.jr.ui;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.miui.supportlite.app.ActionBar;
import com.miui.supportlite.app.Activity;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.StatusBarHelper;
import com.xiaomi.jr.ui.view.ActionBarEndView;
import com.xiaomi.jr.ui.view.ActionBarTwoLineTitleView;

public class ActionBarCustomizer {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11031a = "ActionBarCustomizer";

    public static void a(Activity activity, int i, View.OnClickListener onClickListener) {
        a(activity, (String) null, (View.OnClickListener) null, i, onClickListener, 0, (View.OnClickListener) null);
    }

    public static void a(Activity activity, String str, View.OnClickListener onClickListener, int i, View.OnClickListener onClickListener2, int i2, View.OnClickListener onClickListener3) {
        Activity activity2 = activity;
        int i3 = i;
        int i4 = i2;
        if (!b(activity)) {
            MifiLog.d(f11031a, "setRightIcon: action bar not installed");
            return;
        }
        int actionBarStyle = activity.getActionBarStyle();
        if (actionBarStyle == 0) {
            a(activity, activity.getActionBar(), str, onClickListener, i, onClickListener2, i2, onClickListener3);
        } else if (actionBarStyle == 2) {
            int a2 = a((Context) activity, i);
            int a3 = a((Context) activity, i4);
            if ((i3 != 0 && a2 == 0) || (i4 != 0 && a3 == 0)) {
                MifiLog.e(f11031a, "setRightIcon: no dark theme drawable");
            }
            Activity activity3 = activity;
            String str2 = str;
            View.OnClickListener onClickListener4 = onClickListener;
            View.OnClickListener onClickListener5 = onClickListener2;
            View.OnClickListener onClickListener6 = onClickListener3;
            a(activity3, activity.getActionBar1(), str2, onClickListener4, a2, onClickListener5, a3, onClickListener6);
            a(activity3, activity.getActionBar2(), str2, onClickListener4, i, onClickListener5, i2, onClickListener6);
        }
    }

    private static void a(Activity activity, ActionBar actionBar, String str, View.OnClickListener onClickListener, int i, View.OnClickListener onClickListener2, int i2, View.OnClickListener onClickListener3) {
        ActionBarEndView actionBarEndView;
        if (actionBar.c() instanceof ActionBarEndView) {
            actionBarEndView = (ActionBarEndView) actionBar.c();
        } else {
            actionBarEndView = (ActionBarEndView) activity.getLayoutInflater().inflate(R.layout.actionbar_end_view, (ViewGroup) null);
            actionBar.b((View) actionBarEndView);
        }
        if (!TextUtils.isEmpty(str)) {
            actionBarEndView.setText(str, onClickListener);
        } else {
            actionBarEndView.setIcon(i, onClickListener2, i2, onClickListener3);
        }
    }

    public static void a(Activity activity, CharSequence charSequence, CharSequence charSequence2) {
        if (!b(activity)) {
            MifiLog.d(f11031a, "setTwoLineTitle: action bar not installed");
            return;
        }
        int actionBarStyle = activity.getActionBarStyle();
        if (actionBarStyle == 0) {
            a(activity, activity.getActionBar(), charSequence, charSequence2);
        } else if (actionBarStyle == 2) {
            a(activity, activity.getActionBar2(), charSequence, charSequence2);
        }
    }

    private static void a(Activity activity, ActionBar actionBar, CharSequence charSequence, CharSequence charSequence2) {
        ActionBarTwoLineTitleView actionBarTwoLineTitleView;
        if (actionBar.d() instanceof ActionBarTwoLineTitleView) {
            actionBarTwoLineTitleView = (ActionBarTwoLineTitleView) actionBar.d();
        } else {
            actionBarTwoLineTitleView = (ActionBarTwoLineTitleView) activity.getLayoutInflater().inflate(R.layout.actionbar_two_line_title_view, (ViewGroup) null);
            actionBar.c(actionBarTwoLineTitleView);
        }
        actionBarTwoLineTitleView.setTitle(charSequence, charSequence2);
    }

    public static void a(Activity activity) {
        activity.setActionBarStyle(2);
        activity.setLifecycleCallback(new Activity.LifecycleCallback() {
            public final void onContentViewInit() {
                ActionBarCustomizer.c(Activity.this);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void c(Activity activity) {
        ActionBar actionBar1 = activity.getActionBar1();
        actionBar1.a(17170445);
        actionBar1.c((View) null);
    }

    public static void a(Activity activity, float f) {
        if (activity.getActionBarStyle() != 2) {
            MifiLog.d(f11031a, "setFullscreenPageActionBarTransition: action bar is not in floating style");
            return;
        }
        ActionBar actionBar1 = activity.getActionBar1();
        ActionBar actionBar2 = activity.getActionBar2();
        if (actionBar1 == null || actionBar2 == null) {
            MifiLog.d(f11031a, "setFullscreenPageActionBarTransition: neither of action bars can be empty.");
            return;
        }
        actionBar1.a(1.0f - f);
        actionBar2.a(f);
        boolean z = false;
        if (f <= 0.0f) {
            actionBar1.b(0);
            actionBar2.b(8);
            activity.setActionBar(actionBar1);
        } else if (f >= 1.0f) {
            actionBar1.b(8);
            actionBar2.b(0);
            activity.setActionBar(actionBar2);
        } else {
            actionBar1.b(0);
            actionBar2.b(0);
            activity.setActionBar(actionBar2);
        }
        if (((double) f) > 0.5d) {
            z = true;
        }
        StatusBarHelper.a((android.app.Activity) activity, z);
    }

    private static boolean b(Activity activity) {
        int actionBarStyle = activity.getActionBarStyle();
        if (actionBarStyle == 0) {
            if (activity.getActionBar() != null) {
                return true;
            }
            return false;
        } else if (actionBarStyle != 2 || activity.getActionBar1() == null || activity.getActionBar2() == null) {
            return false;
        } else {
            return true;
        }
    }

    private static int a(Context context, int i) {
        try {
            Resources resources = context.getResources();
            if (!TextUtils.equals(resources.getResourceTypeName(i), "drawable")) {
                return 0;
            }
            String resourceEntryName = resources.getResourceEntryName(i);
            return resources.getIdentifier(resourceEntryName + "_dark", "drawable", context.getPackageName());
        } catch (Exception unused) {
            MifiLog.e(f11031a, "get dark theme drawable failed for " + i);
            return 0;
        }
    }
}
