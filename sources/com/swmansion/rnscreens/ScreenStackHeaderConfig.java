package com.swmansion.rnscreens;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import com.facebook.react.views.text.ReactFontManager;
import com.swmansion.rnscreens.ScreenStackHeaderSubview;
import java.util.ArrayList;

public class ScreenStackHeaderConfig extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<ScreenStackHeaderSubview> f8957a = new ArrayList<>(3);
    private String b;
    private int c;
    private String d;
    private float e;
    private int f;
    private boolean g;
    private boolean h = true;
    private boolean i;
    private boolean j;
    private int k;
    private final Toolbar l;
    private boolean m = false;
    private View.OnClickListener n = new View.OnClickListener() {
        public void onClick(View view) {
            ScreenStackHeaderConfig.this.getScreenStack().dismiss(ScreenStackHeaderConfig.this.getScreenFragment());
        }
    };

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }

    public ScreenStackHeaderConfig(Context context) {
        super(context);
        setVisibility(8);
        this.l = new Toolbar(context);
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(16843827, typedValue, true)) {
            this.l.setBackgroundColor(typedValue.data);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.m = true;
        onUpdate();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.m = false;
    }

    private Screen getScreen() {
        ViewParent parent = getParent();
        if (parent instanceof Screen) {
            return (Screen) parent;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public ScreenStack getScreenStack() {
        Screen screen = getScreen();
        if (screen == null) {
            return null;
        }
        ScreenContainer container = screen.getContainer();
        if (container instanceof ScreenStack) {
            return (ScreenStack) container;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public ScreenStackFragment getScreenFragment() {
        ViewParent parent = getParent();
        if (!(parent instanceof Screen)) {
            return null;
        }
        Fragment fragment = ((Screen) parent).getFragment();
        if (fragment instanceof ScreenStackFragment) {
            return (ScreenStackFragment) fragment;
        }
        return null;
    }

    public boolean isDismissable() {
        return this.h;
    }

    public void onUpdate() {
        boolean z;
        boolean z2;
        boolean z3;
        Drawable navigationIcon;
        Screen screen = (Screen) getParent();
        ScreenStack screenStack = getScreenStack();
        if (screenStack == null || screenStack.getRootScreen() == screen) {
            z = true;
        } else {
            z = false;
        }
        if (screenStack == null || screenStack.getTopScreen() == screen) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.m && z2) {
            if (!this.g) {
                if (this.l.getParent() == null) {
                    getScreenFragment().a(this.l);
                }
                AppCompatActivity appCompatActivity = (AppCompatActivity) getScreenFragment().getActivity();
                appCompatActivity.setSupportActionBar(this.l);
                ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
                if (!z && !this.i) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                supportActionBar.setDisplayHomeAsUpEnabled(z3);
                this.l.setNavigationOnClickListener(this.n);
                getScreenFragment().a(this.j);
                supportActionBar.setTitle((CharSequence) this.b);
                TextView titleTextView = getTitleTextView();
                if (this.c != 0) {
                    this.l.setTitleTextColor(this.c);
                }
                if (titleTextView != null) {
                    if (this.d != null) {
                        titleTextView.setTypeface(ReactFontManager.getInstance().getTypeface(this.d, 0, getContext().getAssets()));
                    }
                    if (this.e > 0.0f) {
                        titleTextView.setTextSize(this.e);
                    }
                }
                if (this.f != 0) {
                    this.l.setBackgroundColor(this.f);
                }
                if (!(this.k == 0 || (navigationIcon = this.l.getNavigationIcon()) == null)) {
                    navigationIcon.setColorFilter(this.k, PorterDuff.Mode.SRC_ATOP);
                }
                for (int childCount = this.l.getChildCount() - 1; childCount >= 0; childCount--) {
                    if (this.l.getChildAt(childCount) instanceof ScreenStackHeaderSubview) {
                        this.l.removeViewAt(childCount);
                    }
                }
                int size = this.f8957a.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ScreenStackHeaderSubview screenStackHeaderSubview = this.f8957a.get(i2);
                    ScreenStackHeaderSubview.Type type = screenStackHeaderSubview.getType();
                    Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(-2, -1);
                    switch (type) {
                        case LEFT:
                            this.l.setNavigationIcon((Drawable) null);
                            this.l.setTitle((CharSequence) null);
                            layoutParams.gravity = 3;
                            continue;
                        case RIGHT:
                            layoutParams.gravity = 5;
                            continue;
                        case TITLE:
                            layoutParams.width = -1;
                            this.l.setTitle((CharSequence) null);
                            break;
                        case CENTER:
                            break;
                    }
                    layoutParams.gravity = 1;
                    screenStackHeaderSubview.setLayoutParams(layoutParams);
                    this.l.addView(screenStackHeaderSubview);
                }
            } else if (this.l.getParent() != null) {
                getScreenFragment().b();
            }
        }
    }

    private void a() {
        if (getParent() != null) {
            onUpdate();
        }
    }

    public ScreenStackHeaderSubview getConfigSubview(int i2) {
        return this.f8957a.get(i2);
    }

    public int getConfigSubviewsCount() {
        return this.f8957a.size();
    }

    public void removeConfigSubview(int i2) {
        this.f8957a.remove(i2);
        a();
    }

    public void addConfigSubview(ScreenStackHeaderSubview screenStackHeaderSubview, int i2) {
        this.f8957a.add(i2, screenStackHeaderSubview);
        a();
    }

    private TextView getTitleTextView() {
        int childCount = this.l.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.l.getChildAt(i2);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (textView.getText().equals(this.l.getTitle())) {
                    return textView;
                }
            }
        }
        return null;
    }

    public void setTitle(String str) {
        this.b = str;
    }

    public void setTitleFontFamily(String str) {
        this.d = str;
    }

    public void setTitleFontSize(float f2) {
        this.e = f2;
    }

    public void setTitleColor(int i2) {
        this.c = i2;
    }

    public void setTintColor(int i2) {
        this.k = i2;
    }

    public void setBackgroundColor(int i2) {
        this.f = i2;
    }

    public void setHideShadow(boolean z) {
        this.j = z;
    }

    public void setGestureEnabled(boolean z) {
        this.h = z;
    }

    public void setHideBackButton(boolean z) {
        this.i = z;
    }

    public void setHidden(boolean z) {
        this.g = z;
    }
}
