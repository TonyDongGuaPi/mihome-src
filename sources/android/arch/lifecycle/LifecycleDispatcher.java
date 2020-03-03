package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

class LifecycleDispatcher {

    /* renamed from: a  reason: collision with root package name */
    private static final String f444a = "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag";
    private static AtomicBoolean b = new AtomicBoolean(false);

    LifecycleDispatcher() {
    }

    static void a(Context context) {
        if (!b.getAndSet(true)) {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new DispatcherActivityCallback());
        }
    }

    @VisibleForTesting
    static class DispatcherActivityCallback extends EmptyActivityLifecycleCallbacks {

        /* renamed from: a  reason: collision with root package name */
        private final FragmentCallback f445a = new FragmentCallback();

        DispatcherActivityCallback() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (activity instanceof FragmentActivity) {
                ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.f445a, true);
            }
            ReportFragment.a(activity);
        }

        public void onActivityStopped(Activity activity) {
            if (activity instanceof FragmentActivity) {
                LifecycleDispatcher.b((FragmentActivity) activity, Lifecycle.State.CREATED);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            if (activity instanceof FragmentActivity) {
                LifecycleDispatcher.b((FragmentActivity) activity, Lifecycle.State.CREATED);
            }
        }
    }

    public static class DestructionReportFragment extends Fragment {
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
        }

        /* access modifiers changed from: protected */
        public void a(Lifecycle.Event event) {
            LifecycleDispatcher.b(getParentFragment(), event);
        }
    }

    private static void a(FragmentManager fragmentManager, Lifecycle.State state) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment next : fragments) {
                if (next != null) {
                    a((Object) next, state);
                    if (next.isAdded()) {
                        a(next.getChildFragmentManager(), state);
                    }
                }
            }
        }
    }

    private static void a(Object obj, Lifecycle.State state) {
        if (obj instanceof LifecycleRegistryOwner) {
            ((LifecycleRegistryOwner) obj).a().a(state);
        }
    }

    /* access modifiers changed from: private */
    public static void b(FragmentActivity fragmentActivity, Lifecycle.State state) {
        a((Object) fragmentActivity, state);
        a(fragmentActivity.getSupportFragmentManager(), state);
    }

    /* access modifiers changed from: private */
    public static void b(Fragment fragment, Lifecycle.Event event) {
        if (fragment instanceof LifecycleRegistryOwner) {
            ((LifecycleRegistryOwner) fragment).a().a(event);
        }
    }

    @VisibleForTesting
    static class FragmentCallback extends FragmentManager.FragmentLifecycleCallbacks {
        FragmentCallback() {
        }

        public void onFragmentCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
            LifecycleDispatcher.b(fragment, Lifecycle.Event.ON_CREATE);
            if ((fragment instanceof LifecycleRegistryOwner) && fragment.getChildFragmentManager().findFragmentByTag(LifecycleDispatcher.f444a) == null) {
                fragment.getChildFragmentManager().beginTransaction().add((Fragment) new DestructionReportFragment(), LifecycleDispatcher.f444a).commit();
            }
        }

        public void onFragmentStarted(FragmentManager fragmentManager, Fragment fragment) {
            LifecycleDispatcher.b(fragment, Lifecycle.Event.ON_START);
        }

        public void onFragmentResumed(FragmentManager fragmentManager, Fragment fragment) {
            LifecycleDispatcher.b(fragment, Lifecycle.Event.ON_RESUME);
        }
    }
}
