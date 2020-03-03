package com.miuipub.internal.app;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miuipub.internal.variable.Android_View_Window_class;
import com.miuipub.internal.view.menu.ImmersionMenuPopupWindow;
import com.miuipub.internal.view.menu.MenuBuilder;
import com.miuipub.internal.view.menu.MenuPresenter;
import com.miuipub.internal.widget.ActionBarView;
import miuipub.app.ActionBar;
import miuipub.v6.R;

public abstract class ActionBarDelegateImpl implements ActionBarDelegate, MenuBuilder.Callback, MenuPresenter.Callback {

    /* renamed from: a  reason: collision with root package name */
    static final String f8220a = "android.support.UI_OPTIONS";
    static final String b = "splitActionBarWhenNarrow";
    private static final String m = "ActionBarDelegate";
    final Activity c;
    protected ActionBarView d;
    protected MenuBuilder e;
    protected ActionMode f;
    protected boolean g;
    protected boolean h;
    protected boolean i;
    boolean j;
    boolean k;
    protected int l;
    private ActionBar n;
    private MenuInflater o;
    private int p = 0;
    private ImmersionMenuPopupWindow q;
    private boolean r;

    public ActionMode a(ActionMode.Callback callback) {
        return null;
    }

    public void a(Bundle bundle) {
    }

    public void a(ActionMode actionMode) {
    }

    public void a(View view, ViewGroup viewGroup) {
    }

    public ActionMode b(ActionMode.Callback callback) {
        return null;
    }

    public void b(ActionMode actionMode) {
    }

    public boolean b(MenuBuilder menuBuilder) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract boolean d(MenuBuilder menuBuilder);

    ActionBarDelegateImpl(Activity activity) {
        this.c = activity;
    }

    public final ActionBar e() {
        if (!this.j && !this.k) {
            this.n = null;
        } else if (this.n == null) {
            this.n = a();
        }
        return this.n;
    }

    public MenuInflater f() {
        if (this.o == null) {
            ActionBar e2 = e();
            if (e2 != null) {
                this.o = new MenuInflater(e2.getThemedContext());
            } else {
                this.o = new MenuInflater(this.c);
            }
        }
        return this.o;
    }

    /* access modifiers changed from: protected */
    public final String g() {
        try {
            ActivityInfo activityInfo = this.c.getPackageManager().getActivityInfo(this.c.getComponentName(), 128);
            if (activityInfo.metaData != null) {
                return activityInfo.metaData.getString(f8220a);
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(m, "getUiOptionsFromMetadata: Activity '" + this.c.getClass().getSimpleName() + "' not in manifest");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final Context h() {
        Activity activity = this.c;
        ActionBar e2 = e();
        return e2 != null ? e2.getThemedContext() : activity;
    }

    public Activity i() {
        return this.c;
    }

    public void a(Configuration configuration) {
        if (this.j && this.g) {
            ((ActionBarImpl) e()).a(configuration);
        }
    }

    public void b() {
        ActionBarImpl actionBarImpl;
        b(false);
        if (this.j && this.g && (actionBarImpl = (ActionBarImpl) e()) != null) {
            actionBarImpl.i(false);
        }
    }

    public void c() {
        ActionBarImpl actionBarImpl;
        if (this.j && this.g && (actionBarImpl = (ActionBarImpl) e()) != null) {
            actionBarImpl.i(true);
        }
    }

    public boolean a(int i2) {
        if (i2 == 2) {
            this.h = true;
            return true;
        } else if (i2 != 5) {
            switch (i2) {
                case 8:
                    this.j = true;
                    return true;
                case 9:
                    this.k = true;
                    return true;
                default:
                    return this.c.requestWindowFeature(i2);
            }
        } else {
            this.i = true;
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void a(MenuBuilder menuBuilder, boolean z) {
        if (this.d == null || !this.d.isOverflowReserved()) {
            menuBuilder.close();
        } else if (this.d.isOverflowMenuShowing() && z) {
            this.d.hideOverflowMenu();
        } else if (this.d.getVisibility() == 0) {
            this.d.showOverflowMenu();
        }
    }

    /* access modifiers changed from: protected */
    public void a(MenuBuilder menuBuilder) {
        if (menuBuilder != this.e) {
            this.e = menuBuilder;
            if (this.d != null) {
                this.d.setMenu(menuBuilder, this);
            }
        }
    }

    /* access modifiers changed from: protected */
    public MenuBuilder j() {
        MenuBuilder menuBuilder = new MenuBuilder(h());
        menuBuilder.a((MenuBuilder.Callback) this);
        return menuBuilder;
    }

    public void b(MenuBuilder menuBuilder, boolean z) {
        this.c.closeOptionsMenu();
    }

    public void c(MenuBuilder menuBuilder) {
        a(menuBuilder, true);
    }

    public void c(int i2) {
        int integer = this.c.getResources().getInteger(R.integer.v6_window_translucent_status);
        if (integer >= 0 && integer <= 2) {
            i2 = integer;
        }
        if (this.p != i2 && Android_View_Window_class.Factory.getInstance().get().setTranslucentStatus(this.c.getWindow(), i2)) {
            this.p = i2;
        }
    }

    public int k() {
        return this.p;
    }

    public void a(boolean z) {
        this.r = z;
        if (this.g && this.j) {
            if (!z) {
                this.d.hideImmersionMore();
            } else if (!this.d.showImmersionMore()) {
                this.d.initImmersionMore(this.l, this);
            }
            d();
        }
    }

    public boolean l() {
        return this.r;
    }

    public void m() {
        View findViewById;
        if (this.d == null || (findViewById = this.d.findViewById(R.id.more)) == null) {
            throw new IllegalStateException("Can't find anchor view in actionbar. Do you use default actionbar and immersion menu is enabled?");
        }
        a(findViewById, (ViewGroup) this.d);
    }

    public void b(boolean z) {
        if (this.q != null) {
            this.q.a(z);
        }
    }
}
