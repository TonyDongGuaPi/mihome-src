package com.miui.supportlite.app;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.miui.supportlite.R;
import com.xiaomi.jr.common.utils.MifiLog;

public class ActionBar extends android.app.ActionBar {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8194a = "ActionBar";
    private Activity b;
    private ViewGroup c;
    private ViewGroup d;
    private ImageView e;
    private ImageView f;
    private TextView g;
    private View h;
    private ViewGroup i;
    private View j;
    private ViewGroup k;
    private View l;

    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
    }

    public void addTab(ActionBar.Tab tab) {
    }

    public void addTab(ActionBar.Tab tab, int i2) {
    }

    public void addTab(ActionBar.Tab tab, int i2, boolean z) {
    }

    public void addTab(ActionBar.Tab tab, boolean z) {
    }

    public View getCustomView() {
        return null;
    }

    public int getDisplayOptions() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public int getNavigationItemCount() {
        return 0;
    }

    public int getNavigationMode() {
        return 0;
    }

    public int getSelectedNavigationIndex() {
        return 0;
    }

    public ActionBar.Tab getSelectedTab() {
        return null;
    }

    public CharSequence getSubtitle() {
        return null;
    }

    public ActionBar.Tab getTabAt(int i2) {
        return null;
    }

    public int getTabCount() {
        return 0;
    }

    public CharSequence getTitle() {
        return null;
    }

    public void hide() {
    }

    public boolean isShowing() {
        return false;
    }

    public ActionBar.Tab newTab() {
        return null;
    }

    public void removeAllTabs() {
    }

    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
    }

    public void removeTab(ActionBar.Tab tab) {
    }

    public void removeTabAt(int i2) {
    }

    public void selectTab(ActionBar.Tab tab) {
    }

    public void setBackgroundDrawable(Drawable drawable) {
    }

    public void setCustomView(int i2) {
    }

    public void setCustomView(View view) {
    }

    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
    }

    public void setDisplayHomeAsUpEnabled(boolean z) {
    }

    public void setDisplayOptions(int i2, int i3) {
    }

    public void setDisplayShowCustomEnabled(boolean z) {
    }

    public void setDisplayShowHomeEnabled(boolean z) {
    }

    public void setDisplayShowTitleEnabled(boolean z) {
    }

    public void setDisplayUseLogoEnabled(boolean z) {
    }

    public void setIcon(int i2) {
    }

    public void setIcon(Drawable drawable) {
    }

    public void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, ActionBar.OnNavigationListener onNavigationListener) {
    }

    public void setLogo(int i2) {
    }

    public void setLogo(Drawable drawable) {
    }

    public void setNavigationMode(int i2) {
    }

    public void setSelectedNavigationItem(int i2) {
    }

    public void setSubtitle(int i2) {
    }

    public void setSubtitle(CharSequence charSequence) {
    }

    public void show() {
    }

    public ActionBar(Activity activity, ViewGroup viewGroup) {
        this.b = activity;
        this.c = viewGroup;
        this.i = (ViewGroup) viewGroup.findViewById(R.id.supportlite_start_container);
        this.e = (ImageView) viewGroup.findViewById(R.id.supportlite_home);
        this.e.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                Activity.this.onHomeSelected();
            }
        });
        this.f = (ImageView) viewGroup.findViewById(R.id.supportlite_close);
        this.f.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                Activity.this.finish();
            }
        });
        this.k = (ViewGroup) viewGroup.findViewById(R.id.supportlite_end_container);
        this.d = (ViewGroup) viewGroup.findViewById(R.id.supportlite_title1_container);
        this.g = (TextView) viewGroup.findViewById(R.id.supportlite_title1);
    }

    public void setDisplayOptions(int i2) {
        ViewGroup viewGroup = (ViewGroup) this.c.findViewById(R.id.supportlite_title1_container);
        ViewGroup viewGroup2 = (ViewGroup) this.c.findViewById(R.id.supportlite_title2_container);
        if ((i2 & 8) != 0) {
            viewGroup.setVisibility(0);
            viewGroup2.setVisibility(8);
            this.d = viewGroup;
            return;
        }
        viewGroup.setVisibility(8);
        viewGroup2.setVisibility(0);
        this.d = viewGroup2;
    }

    public void a(View view) {
        this.i.removeAllViews();
        if (view != null) {
            this.i.addView(view);
        }
        this.j = view;
    }

    public ImageView a() {
        return this.e;
    }

    public ImageView b() {
        return this.f;
    }

    public void b(View view) {
        this.k.removeAllViews();
        if (view != null) {
            this.k.addView(view);
        }
        this.l = view;
    }

    public View c() {
        return this.l;
    }

    public void c(View view) {
        this.d.removeAllViews();
        if (view != null) {
            this.d.addView(view);
        }
        this.g = null;
        this.h = view;
    }

    public View d() {
        return this.h != null ? this.h : this.g;
    }

    public void setTitle(CharSequence charSequence) {
        this.b.setTitle(charSequence);
    }

    public void a(CharSequence charSequence) {
        if (this.g != null) {
            this.g.setText(charSequence);
        } else {
            MifiLog.d(f8194a, "The title view might be customized, try the customized ways like ActionBarCustomizer.setTwoLineTitle");
        }
    }

    public void a(int i2) {
        this.c.setBackgroundColor(i2);
    }

    public void b(int i2) {
        this.c.setVisibility(i2);
    }

    public void a(float f2) {
        this.c.setAlpha(f2);
    }

    public void setTitle(int i2) {
        setTitle((CharSequence) this.c.getContext().getString(i2));
    }
}
