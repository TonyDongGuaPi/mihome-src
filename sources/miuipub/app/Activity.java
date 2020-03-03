package miuipub.app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.miuipub.internal.app.ActivityDelegate;
import com.miuipub.internal.app.IActivity;

public class Activity extends android.app.Activity implements IActivity {
    private ActivityDelegate mDelegate = new ActivityDelegate(this, android.app.Activity.class);

    public ActionBar getMiuiActionBar() {
        return this.mDelegate.e();
    }

    public MenuInflater getMenuInflater() {
        return this.mDelegate.f();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDelegate.a(bundle);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDelegate.a(configuration);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mDelegate.b(bundle);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mDelegate.c(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.mDelegate.b();
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        this.mDelegate.c();
    }

    public View onCreatePanelView(int i) {
        if (i == 0) {
            return this.mDelegate.b(i);
        }
        return super.onCreatePanelView(i);
    }

    /* access modifiers changed from: protected */
    public void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        this.mDelegate.a(charSequence);
    }

    public void invalidateOptionsMenu() {
        this.mDelegate.d();
    }

    public boolean requestExtraWindowFeature(int i) {
        return this.mDelegate.a(i);
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        return this.mDelegate.a(i, menu);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        return this.mDelegate.a(i, view, menu);
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return super.onMenuItemSelected(i, menuItem);
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        return this.mDelegate.a(callback);
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return this.mDelegate.b(callback);
    }

    public void onActionModeStarted(ActionMode actionMode) {
        this.mDelegate.a(actionMode);
    }

    public void onActionModeFinished(ActionMode actionMode) {
        this.mDelegate.b(actionMode);
    }

    public void onBackPressed() {
        if (!this.mDelegate.o()) {
            super.onBackPressed();
        }
    }

    public void setImmersionMenuEnabled(boolean z) {
        this.mDelegate.a(z);
    }

    public void showImmersionMenu() {
        this.mDelegate.m();
    }

    public void showImmersionMenu(View view, ViewGroup viewGroup) {
        this.mDelegate.a(view, viewGroup);
    }

    public void dismissImmersionMenu(boolean z) {
        this.mDelegate.b(z);
    }

    public void setTranslucentStatus(int i) {
        this.mDelegate.c(i);
    }

    public int getTranslucentStatus() {
        return this.mDelegate.k();
    }

    public void setOnStatusBarChangeListener(OnStatusBarChangeListener onStatusBarChangeListener) {
        this.mDelegate.a(onStatusBarChangeListener);
    }
}
