package android.arch.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ReportFragment extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    private static final String f462a = "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag";
    private ActivityInitializationListener b;

    interface ActivityInitializationListener {
        void a();

        void b();

        void c();
    }

    public static void a(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag(f462a) == null) {
            fragmentManager.beginTransaction().add(new ReportFragment(), f462a).commit();
            fragmentManager.executePendingTransactions();
        }
    }

    static ReportFragment b(Activity activity) {
        return (ReportFragment) activity.getFragmentManager().findFragmentByTag(f462a);
    }

    private void b(ActivityInitializationListener activityInitializationListener) {
        if (activityInitializationListener != null) {
            activityInitializationListener.a();
        }
    }

    private void c(ActivityInitializationListener activityInitializationListener) {
        if (activityInitializationListener != null) {
            activityInitializationListener.b();
        }
    }

    private void d(ActivityInitializationListener activityInitializationListener) {
        if (activityInitializationListener != null) {
            activityInitializationListener.c();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        b(this.b);
        a(Lifecycle.Event.ON_CREATE);
    }

    public void onStart() {
        super.onStart();
        c(this.b);
        a(Lifecycle.Event.ON_START);
    }

    public void onResume() {
        super.onResume();
        d(this.b);
        a(Lifecycle.Event.ON_RESUME);
    }

    public void onPause() {
        super.onPause();
        a(Lifecycle.Event.ON_PAUSE);
    }

    public void onStop() {
        super.onStop();
        a(Lifecycle.Event.ON_STOP);
    }

    public void onDestroy() {
        super.onDestroy();
        a(Lifecycle.Event.ON_DESTROY);
        this.b = null;
    }

    private void a(Lifecycle.Event event) {
        Activity activity = getActivity();
        if (activity instanceof LifecycleRegistryOwner) {
            ((LifecycleRegistryOwner) activity).a().a(event);
        } else if (activity instanceof LifecycleOwner) {
            Lifecycle lifecycle = ((LifecycleOwner) activity).getLifecycle();
            if (lifecycle instanceof LifecycleRegistry) {
                ((LifecycleRegistry) lifecycle).a(event);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ActivityInitializationListener activityInitializationListener) {
        this.b = activityInitializationListener;
    }
}
