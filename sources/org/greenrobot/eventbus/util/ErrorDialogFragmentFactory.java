package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import org.greenrobot.eventbus.util.ErrorDialogFragments;

public abstract class ErrorDialogFragmentFactory<T> {

    /* renamed from: a  reason: collision with root package name */
    protected final ErrorDialogConfig f3505a;

    /* access modifiers changed from: protected */
    public abstract T a(ThrowableFailureEvent throwableFailureEvent, Bundle bundle);

    protected ErrorDialogFragmentFactory(ErrorDialogConfig errorDialogConfig) {
        this.f3505a = errorDialogConfig;
    }

    /* access modifiers changed from: protected */
    public T a(ThrowableFailureEvent throwableFailureEvent, boolean z, Bundle bundle) {
        Bundle bundle2;
        if (throwableFailureEvent.c()) {
            return null;
        }
        if (bundle != null) {
            bundle2 = (Bundle) bundle.clone();
        } else {
            bundle2 = new Bundle();
        }
        if (!bundle2.containsKey("de.greenrobot.eventbus.errordialog.title")) {
            bundle2.putString("de.greenrobot.eventbus.errordialog.title", b(throwableFailureEvent, bundle2));
        }
        if (!bundle2.containsKey("de.greenrobot.eventbus.errordialog.message")) {
            bundle2.putString("de.greenrobot.eventbus.errordialog.message", c(throwableFailureEvent, bundle2));
        }
        if (!bundle2.containsKey("de.greenrobot.eventbus.errordialog.finish_after_dialog")) {
            bundle2.putBoolean("de.greenrobot.eventbus.errordialog.finish_after_dialog", z);
        }
        if (!bundle2.containsKey("de.greenrobot.eventbus.errordialog.event_type_on_close") && this.f3505a.i != null) {
            bundle2.putSerializable("de.greenrobot.eventbus.errordialog.event_type_on_close", this.f3505a.i);
        }
        if (!bundle2.containsKey("de.greenrobot.eventbus.errordialog.icon_id") && this.f3505a.h != 0) {
            bundle2.putInt("de.greenrobot.eventbus.errordialog.icon_id", this.f3505a.h);
        }
        return a(throwableFailureEvent, bundle2);
    }

    /* access modifiers changed from: protected */
    public String b(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
        return this.f3505a.f3504a.getString(this.f3505a.b);
    }

    /* access modifiers changed from: protected */
    public String c(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
        return this.f3505a.f3504a.getString(this.f3505a.a(throwableFailureEvent.f3511a));
    }

    public static class Support extends ErrorDialogFragmentFactory<Fragment> {
        public Support(ErrorDialogConfig errorDialogConfig) {
            super(errorDialogConfig);
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public Fragment a(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
            ErrorDialogFragments.Support support = new ErrorDialogFragments.Support();
            support.setArguments(bundle);
            return support;
        }
    }

    @TargetApi(11)
    public static class Honeycomb extends ErrorDialogFragmentFactory<android.app.Fragment> {
        public Honeycomb(ErrorDialogConfig errorDialogConfig) {
            super(errorDialogConfig);
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public android.app.Fragment a(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
            ErrorDialogFragments.Honeycomb honeycomb = new ErrorDialogFragments.Honeycomb();
            honeycomb.setArguments(bundle);
            return honeycomb;
        }
    }
}
