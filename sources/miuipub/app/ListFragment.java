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

public class ListFragment extends android.app.ListFragment implements IFragment {

    /* renamed from: a  reason: collision with root package name */
    private FragmentDelegate f2924a;
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
        this.f2924a = new FragmentDelegate(this);
        this.f2924a.a(bundle);
    }

    public void onStop() {
        super.onStop();
        this.f2924a.b();
    }

    public void onResume() {
        super.onResume();
        this.f2924a.c();
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.f2924a.a(viewGroup, bundle);
    }

    public View getView() {
        return this.f2924a.n();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f2924a.a(configuration);
    }

    public ActionBar a() {
        return this.f2924a.e();
    }

    public MenuInflater b() {
        return this.f2924a.f();
    }

    public ActionMode a(ActionMode.Callback callback) {
        return this.f2924a.a(callback);
    }

    public void d(int i) {
        this.f2924a.e(i);
    }

    public Context P() {
        return this.f2924a.o();
    }

    public void setHasOptionsMenu(boolean z) {
        super.setHasOptionsMenu(z);
        if (this.b != z) {
            this.b = z;
            if (!isHidden() && isAdded() && this.f2924a != null) {
                this.f2924a.d();
            }
        }
    }

    public void setMenuVisibility(boolean z) {
        super.setMenuVisibility(z);
        if (this.c != z) {
            this.c = z;
            if (!isHidden() && isAdded() && this.f2924a != null) {
                this.f2924a.d();
            }
        }
    }

    public boolean a(int i) {
        return this.f2924a.a(i);
    }

    public void c() {
        if (this.f2924a != null) {
            this.f2924a.d(1);
            if (!isHidden() && this.b && this.c && isAdded()) {
                this.f2924a.d();
            }
        }
    }

    public void d() {
        if (this.f2924a != null && !isHidden() && this.b && this.c && isAdded()) {
            this.f2924a.d();
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
        if (!z && this.f2924a != null) {
            this.f2924a.d();
        }
        a(!z);
    }

    public void setImmersionMenuEnabled(boolean z) {
        this.f2924a.a(z);
    }

    public void showImmersionMenu() {
        this.f2924a.m();
    }

    public void showImmersionMenu(View view, ViewGroup viewGroup) {
        this.f2924a.a(view, viewGroup);
    }

    public void dismissImmersionMenu(boolean z) {
        this.f2924a.b(z);
    }

    public void onDestroy() {
        super.onDestroy();
        this.f2924a.b(false);
    }
}
