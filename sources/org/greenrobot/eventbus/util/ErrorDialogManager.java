package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import org.greenrobot.eventbus.EventBus;

public class ErrorDialogManager {

    /* renamed from: a  reason: collision with root package name */
    public static ErrorDialogFragmentFactory<?> f3507a = null;
    protected static final String b = "de.greenrobot.eventbus.error_dialog";
    protected static final String c = "de.greenrobot.eventbus.error_dialog_manager";
    public static final String d = "de.greenrobot.eventbus.errordialog.title";
    public static final String e = "de.greenrobot.eventbus.errordialog.message";
    public static final String f = "de.greenrobot.eventbus.errordialog.finish_after_dialog";
    public static final String g = "de.greenrobot.eventbus.errordialog.icon_id";
    public static final String h = "de.greenrobot.eventbus.errordialog.event_type_on_close";

    public static class SupportManagerFragment extends Fragment {

        /* renamed from: a  reason: collision with root package name */
        protected boolean f3509a;
        protected Bundle b;
        private EventBus c;
        private boolean d;
        private Object e;

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.c = ErrorDialogManager.f3507a.f3505a.b();
            this.c.a((Object) this);
            this.d = true;
        }

        public void onResume() {
            super.onResume();
            if (this.d) {
                this.d = false;
                return;
            }
            this.c = ErrorDialogManager.f3507a.f3505a.b();
            this.c.a((Object) this);
        }

        public void onPause() {
            this.c.c((Object) this);
            super.onPause();
        }

        public void a(ThrowableFailureEvent throwableFailureEvent) {
            if (ErrorDialogManager.b(this.e, throwableFailureEvent)) {
                ErrorDialogManager.a(throwableFailureEvent);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.executePendingTransactions();
                DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag(ErrorDialogManager.b);
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                DialogFragment dialogFragment2 = (DialogFragment) ErrorDialogManager.f3507a.a(throwableFailureEvent, this.f3509a, this.b);
                if (dialogFragment2 != null) {
                    dialogFragment2.show(fragmentManager, ErrorDialogManager.b);
                }
            }
        }

        public static void a(Activity activity, Object obj, boolean z, Bundle bundle) {
            FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            SupportManagerFragment supportManagerFragment = (SupportManagerFragment) supportFragmentManager.findFragmentByTag(ErrorDialogManager.c);
            if (supportManagerFragment == null) {
                supportManagerFragment = new SupportManagerFragment();
                supportFragmentManager.beginTransaction().add((Fragment) supportManagerFragment, ErrorDialogManager.c).commit();
                supportFragmentManager.executePendingTransactions();
            }
            supportManagerFragment.f3509a = z;
            supportManagerFragment.b = bundle;
            supportManagerFragment.e = obj;
        }
    }

    @TargetApi(11)
    public static class HoneycombManagerFragment extends android.app.Fragment {

        /* renamed from: a  reason: collision with root package name */
        protected boolean f3508a;
        protected Bundle b;
        private EventBus c;
        private Object d;

        public void onResume() {
            super.onResume();
            this.c = ErrorDialogManager.f3507a.f3505a.b();
            this.c.a((Object) this);
        }

        public void onPause() {
            this.c.c((Object) this);
            super.onPause();
        }

        public void a(ThrowableFailureEvent throwableFailureEvent) {
            if (ErrorDialogManager.b(this.d, throwableFailureEvent)) {
                ErrorDialogManager.a(throwableFailureEvent);
                android.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.executePendingTransactions();
                android.app.DialogFragment dialogFragment = (android.app.DialogFragment) fragmentManager.findFragmentByTag(ErrorDialogManager.b);
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                android.app.DialogFragment dialogFragment2 = (android.app.DialogFragment) ErrorDialogManager.f3507a.a(throwableFailureEvent, this.f3508a, this.b);
                if (dialogFragment2 != null) {
                    dialogFragment2.show(fragmentManager, ErrorDialogManager.b);
                }
            }
        }

        public static void a(Activity activity, Object obj, boolean z, Bundle bundle) {
            android.app.FragmentManager fragmentManager = activity.getFragmentManager();
            HoneycombManagerFragment honeycombManagerFragment = (HoneycombManagerFragment) fragmentManager.findFragmentByTag(ErrorDialogManager.c);
            if (honeycombManagerFragment == null) {
                honeycombManagerFragment = new HoneycombManagerFragment();
                fragmentManager.beginTransaction().add(honeycombManagerFragment, ErrorDialogManager.c).commit();
                fragmentManager.executePendingTransactions();
            }
            honeycombManagerFragment.f3508a = z;
            honeycombManagerFragment.b = bundle;
            honeycombManagerFragment.d = obj;
        }
    }

    public static void a(Activity activity) {
        a(activity, false, (Bundle) null);
    }

    public static void a(Activity activity, boolean z) {
        a(activity, z, (Bundle) null);
    }

    public static void a(Activity activity, boolean z, Bundle bundle) {
        a(activity, activity.getClass(), z, bundle);
    }

    public static void a(Activity activity, Object obj, boolean z, Bundle bundle) {
        if (f3507a == null) {
            throw new RuntimeException("You must set the static factory field to configure error dialogs for your app.");
        } else if (b(activity)) {
            SupportManagerFragment.a(activity, obj, z, bundle);
        } else {
            HoneycombManagerFragment.a(activity, obj, z, bundle);
        }
    }

    private static boolean b(Activity activity) {
        String name;
        Class cls = activity.getClass();
        do {
            cls = cls.getSuperclass();
            if (cls != null) {
                name = cls.getName();
                if (name.equals("android.support.v4.app.FragmentActivity")) {
                    return true;
                }
                if (name.startsWith("com.actionbarsherlock.app") && (name.endsWith(".SherlockActivity") || name.endsWith(".SherlockListActivity") || name.endsWith(".SherlockPreferenceActivity"))) {
                    throw new RuntimeException("Please use SherlockFragmentActivity. Illegal activity: " + name);
                }
            } else {
                throw new RuntimeException("Illegal activity type: " + activity.getClass());
            }
        } while (!name.equals("android.app.Activity"));
        if (Build.VERSION.SDK_INT >= 11) {
            return false;
        }
        throw new RuntimeException("Illegal activity without fragment support. Either use Android 3.0+ or android.support.v4.app.FragmentActivity.");
    }

    protected static void a(ThrowableFailureEvent throwableFailureEvent) {
        if (f3507a.f3505a.f) {
            String str = f3507a.f3505a.g;
            if (str == null) {
                str = EventBus.f3481a;
            }
            Log.i(str, "Error dialog manager received exception", throwableFailureEvent.f3511a);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r1 = r1.a();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.lang.Object r0, org.greenrobot.eventbus.util.ThrowableFailureEvent r1) {
        /*
            if (r1 == 0) goto L_0x0010
            java.lang.Object r1 = r1.a()
            if (r1 == 0) goto L_0x0010
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0010
            r0 = 0
            return r0
        L_0x0010:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.eventbus.util.ErrorDialogManager.b(java.lang.Object, org.greenrobot.eventbus.util.ThrowableFailureEvent):boolean");
    }
}
