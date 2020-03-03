package miuipub.preference;

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
import miuipub.app.ActionBar;

public class PreferenceFragment extends android.preference.PreferenceFragment implements IFragment {

    /* renamed from: a  reason: collision with root package name */
    private FragmentDelegate f3008a;
    private boolean b = true;
    private boolean c = true;

    public final void a(ActionMode actionMode) {
    }

    public void a(boolean z) {
    }

    public boolean a(Menu menu) {
        return true;
    }

    public final void b(ActionMode actionMode) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3008a = new FragmentDelegate(this);
        this.f3008a.a(bundle);
    }

    public void onStop() {
        super.onStop();
        this.f3008a.b();
    }

    public void onResume() {
        super.onResume();
        this.f3008a.c();
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.f3008a.a(viewGroup, bundle);
    }

    public View getView() {
        return this.f3008a.n();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f3008a.a(configuration);
    }

    public ActionBar a() {
        return this.f3008a.e();
    }

    public MenuInflater b() {
        return this.f3008a.f();
    }

    public ActionMode a(ActionMode.Callback callback) {
        return this.f3008a.a(callback);
    }

    public void d(int i) {
        this.f3008a.e(i);
    }

    public Context P() {
        return this.f3008a.o();
    }

    public void setHasOptionsMenu(boolean z) {
        super.setHasOptionsMenu(z);
        if (this.b != z) {
            this.b = z;
            if (!isHidden() && isAdded() && this.f3008a != null) {
                this.f3008a.d();
            }
        }
    }

    public void setMenuVisibility(boolean z) {
        super.setMenuVisibility(z);
        if (this.c != z) {
            this.c = z;
            if (!isHidden() && isAdded() && this.f3008a != null) {
                this.f3008a.d();
            }
        }
    }

    public boolean a(int i) {
        return this.f3008a.a(i);
    }

    public void c() {
        if (this.f3008a != null) {
            this.f3008a.d(1);
            if (!isHidden() && this.b && this.c && isAdded()) {
                this.f3008a.d();
            }
        }
    }

    public void d() {
        if (this.f3008a != null && !isHidden() && this.b && this.c && isAdded()) {
            this.f3008a.d();
        }
    }

    public boolean a(int i, Menu menu) {
        if (i != 0 || !this.b || !this.c || isHidden() || !isAdded()) {
            return false;
        }
        return a(menu);
    }

    public void a(int i, View view, Menu menu) {
        if (i == 0 && this.b && this.c && !isHidden() && isAdded()) {
            onPrepareOptionsMenu(menu);
        }
    }

    public View b(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public final void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z && this.f3008a != null) {
            this.f3008a.d();
        }
        a(!z);
    }

    public void setImmersionMenuEnabled(boolean z) {
        this.f3008a.a(z);
    }

    public void showImmersionMenu() {
        this.f3008a.m();
    }

    public void showImmersionMenu(View view, ViewGroup viewGroup) {
        this.f3008a.a(view, viewGroup);
    }

    public void dismissImmersionMenu(boolean z) {
        this.f3008a.b(z);
    }

    public void onDestroy() {
        super.onDestroy();
        this.f3008a.b(false);
    }
}
