package com.miui.supportlite.app;

import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.miui.supportlite.R;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.MiuiClient;
import com.xiaomi.jr.common.utils.StatusBarHelper;
import com.xiaomi.jr.common.utils.Utils;

public class Activity extends FragmentActivity {
    public static final int STYLE_FIXED = 0;
    public static final int STYLE_FLOATING = 2;
    public static final int STYLE_NONE = 1;

    /* renamed from: a  reason: collision with root package name */
    private static final String f8195a = "MiuiSupportActivity";
    private CharSequence b;
    private int c;
    private ActionBar d;
    private ActionBar e;
    private ActionBar f;
    private View g;
    private LifecycleCallback h;

    public interface LifecycleCallback {
        void onContentViewInit();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        boolean z = true;
        TypedArray obtainStyledAttributes = getTheme().obtainStyledAttributes(new int[]{R.attr.miuisupport_actionBarStyle});
        this.c = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
        super.onCreate(bundle);
        if (this.c != 0) {
            z = false;
        }
        StatusBarHelper.a((android.app.Activity) this, z);
        if (Build.VERSION.SDK_INT >= 21 && MiuiClient.a()) {
            getWindow().setNavigationBarColor(-1);
        }
    }

    public void setLifecycleCallback(LifecycleCallback lifecycleCallback) {
        this.h = lifecycleCallback;
    }

    public void setActionBarStyle(int i) {
        if (this.g == null) {
            this.c = i;
        } else {
            MifiLog.e(f8195a, "setActionBarStyle must be called before setContentView");
        }
    }

    public int getActionBarStyle() {
        return this.c;
    }

    public void setContentView(int i) {
        setContentView(getLayoutInflater().inflate(i, (ViewGroup) null));
    }

    public void setContentView(View view) {
        if (this.c == 0) {
            super.setContentView(R.layout.miuisupport_fixed_actionbar_activity);
        } else if (this.c == 1) {
            super.setContentView(R.layout.miuisupport_no_actionbar_activity);
        } else if (this.c == 2) {
            super.setContentView(R.layout.miuisupport_floating_actionbar_activity);
        }
        ((ViewGroup) super.findViewById(R.id.supportlite_content)).addView(view, new LinearLayout.LayoutParams(-1, -1));
        this.g = view;
        b();
        if (this.h != null) {
            this.h.onContentViewInit();
        }
    }

    private void b() {
        if (this.c == 0) {
            this.d = a(R.id.actionbar_wrapper);
        } else if (this.c == 2) {
            this.e = a(R.id.actionbar1_wrapper);
            if (this.e.a() != null) {
                this.e.a().setImageResource(R.drawable.miuisupport_action_bar_back_dark);
            }
            if (this.e.b() != null) {
                this.e.b().setImageResource(R.drawable.miuisupport_action_bar_close_dark);
            }
            this.e.b(0);
            this.f = a(R.id.actionbar2_wrapper);
            this.f.b(8);
            setActionBar(this.e);
        }
        if (this.b == null) {
            this.b = getTitle();
        }
        setTitle(this.b);
    }

    private ActionBar a(int i) {
        ViewGroup viewGroup = (ViewGroup) super.findViewById(i);
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.supportlite_action_bar);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewGroup2.getLayoutParams();
        int b2 = Utils.b(getApplicationContext());
        if (!(b2 == 0 || b2 == layoutParams.topMargin)) {
            layoutParams.topMargin = b2;
            viewGroup2.setLayoutParams(layoutParams);
        }
        ActionBar actionBar = new ActionBar(this, viewGroup);
        actionBar.setDisplayOptions(8);
        return actionBar;
    }

    public void onHomeSelected() {
        finish();
    }

    public View getContentView() {
        return this.g;
    }

    public ActionBar getActionBar() {
        return this.d;
    }

    public ActionBar getActionBar1() {
        return this.e;
    }

    public ActionBar getActionBar2() {
        return this.f;
    }

    public void setActionBar(ActionBar actionBar) {
        this.d = actionBar;
    }

    public void setTitle(CharSequence charSequence) {
        this.b = charSequence;
        if (this.c == 0) {
            if (this.d != null) {
                this.d.a(charSequence);
            }
        } else if (this.c == 2) {
            if (this.e != null) {
                this.e.a(charSequence);
            }
            if (this.f != null) {
                this.f.a(charSequence);
            }
        }
    }
}
