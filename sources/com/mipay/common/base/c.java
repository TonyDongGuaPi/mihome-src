package com.mipay.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import com.mipay.common.data.b;

public abstract class c extends l {
    private static final String j = "fragment_save_instance";

    /* renamed from: a  reason: collision with root package name */
    protected BaseActivity f8113a;
    private final p g = new p();
    private boolean h;
    private boolean i;

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        return this.i;
    }

    /* access modifiers changed from: protected */
    public String b() {
        return "";
    }

    /* access modifiers changed from: protected */
    public String c() {
        return "";
    }

    public void doActivityCreated(Bundle bundle) {
        super.doActivityCreated(bundle);
        this.g.c();
    }

    public void doAttach(Activity activity) {
        super.doAttach(activity);
        try {
            this.f8113a = (BaseActivity) getActivity();
        } catch (ClassCastException unused) {
            throw new ClassCastException(getActivity().toString() + " must implement FragmentListener");
        }
    }

    public void doCreate(Bundle bundle) {
        a(getArguments());
        super.doCreate(bundle);
    }

    public void doDestroy() {
        super.doDestroy();
        this.g.g();
    }

    public boolean doOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        doBackPressed();
        return true;
    }

    public void doPause() {
        super.doPause();
        this.i = true;
    }

    public void doResume() {
        super.doResume();
        this.i = false;
    }

    public void doSaveInstanceState(Bundle bundle) {
        super.doSaveInstanceState(bundle);
        bundle.putBoolean(j, true);
    }

    public void doStart() {
        super.doStart();
        if (!this.h) {
            this.h = true;
            this.g.d();
        }
    }

    public void doStop() {
        super.doStop();
        if (this.h) {
            this.h = false;
            Activity activity = getActivity();
            if (!b.c()) {
                this.g.e();
            } else if (activity != null && activity.isChangingConfigurations()) {
                this.g.f();
            }
        }
    }

    public final s getTaskManager() {
        return this.g;
    }
}
