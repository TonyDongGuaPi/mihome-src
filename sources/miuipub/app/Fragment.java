package miuipub.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miuipub.internal.app.FragmentDelegate;
import com.miuipub.internal.app.IFragment;

public class Fragment extends android.app.Fragment implements IFragment {

    /* renamed from: a  reason: collision with root package name */
    private FragmentDelegate f2923a;
    private boolean b = true;
    private boolean c = true;

    public final void a(ActionMode actionMode) {
    }

    public boolean a(Menu menu) {
        return true;
    }

    public View b(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public final void b(ActionMode actionMode) {
    }

    public void e(boolean z) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f2923a = new FragmentDelegate(this);
        this.f2923a.a(bundle);
    }

    public void onStop() {
        super.onStop();
        this.f2923a.b();
    }

    public void onResume() {
        super.onResume();
        this.f2923a.c();
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.f2923a.a(viewGroup, bundle);
    }

    public View getView() {
        return this.f2923a.n();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f2923a.a(configuration);
    }

    public ActionBar T() {
        return this.f2923a.e();
    }

    public MenuInflater U() {
        return this.f2923a.f();
    }

    public ActionMode a(ActionMode.Callback callback) {
        return this.f2923a.a(callback);
    }

    public void d(int i) {
        this.f2923a.e(i);
    }

    public Context P() {
        return this.f2923a.o();
    }

    public void setHasOptionsMenu(boolean z) {
        super.setHasOptionsMenu(z);
        if (this.b != z) {
            this.b = z;
            if (this.b && !this.f2923a.l() && !isHidden() && isAdded() && this.f2923a != null) {
                this.f2923a.d();
            }
        }
    }

    public void setMenuVisibility(boolean z) {
        super.setMenuVisibility(z);
        if (this.c != z) {
            this.c = z;
            if (!isHidden() && isAdded() && this.f2923a != null) {
                this.f2923a.d();
            }
        }
    }

    public boolean f(int i) {
        return this.f2923a.a(i);
    }

    public void V() {
        if (this.f2923a != null) {
            this.f2923a.d(1);
            if (!isHidden() && this.b && !this.f2923a.l() && this.c && isAdded()) {
                this.f2923a.d();
            }
        }
    }

    public void W() {
        if (this.f2923a != null && !isHidden() && this.b && !this.f2923a.l() && this.c && isAdded()) {
            this.f2923a.d();
        }
    }

    public boolean a(int i, Menu menu) {
        if (i != 0 || !this.b || this.f2923a.l() || !this.c || isHidden() || !isAdded()) {
            return false;
        }
        return a(menu);
    }

    public void a(int i, View view, Menu menu) {
        if (i == 0 && this.b && !this.f2923a.l() && this.c && !isHidden() && isAdded()) {
            onPrepareOptionsMenu(menu);
        }
    }

    public final void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z && this.f2923a != null) {
            this.f2923a.d();
        }
        e(!z);
    }

    public void setImmersionMenuEnabled(boolean z) {
        this.f2923a.a(z);
    }

    public void showImmersionMenu() {
        this.f2923a.m();
    }

    public void showImmersionMenu(View view, ViewGroup viewGroup) {
        this.f2923a.a(view, viewGroup);
    }

    public void dismissImmersionMenu(boolean z) {
        this.f2923a.b(z);
    }

    public void onDestroy() {
        super.onDestroy();
        this.f2923a.b(false);
    }

    public void a(OnStatusBarChangeListener onStatusBarChangeListener) {
        this.f2923a.a(onStatusBarChangeListener);
    }
}
