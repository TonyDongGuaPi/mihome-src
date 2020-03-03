package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class HolderFragment extends Fragment implements ViewModelStoreOwner {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})

    /* renamed from: a  reason: collision with root package name */
    public static final String f440a = "android.arch.lifecycle.state.StateProviderHolderFragment";
    private static final String b = "ViewModelStores";
    private static final HolderFragmentManager c = new HolderFragmentManager();
    private ViewModelStore d = new ViewModelStore();

    public HolderFragment() {
        setRetainInstance(true);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        c.a((Fragment) this);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        this.d.a();
    }

    @NonNull
    public ViewModelStore getViewModelStore() {
        return this.d;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static HolderFragment a(FragmentActivity fragmentActivity) {
        return c.a(fragmentActivity);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static HolderFragment a(Fragment fragment) {
        return c.b(fragment);
    }

    static class HolderFragmentManager {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public Map<Activity, HolderFragment> f441a = new HashMap();
        /* access modifiers changed from: private */
        public Map<Fragment, HolderFragment> b = new HashMap();
        private Application.ActivityLifecycleCallbacks c = new EmptyActivityLifecycleCallbacks() {
            public void onActivityDestroyed(Activity activity) {
                if (((HolderFragment) HolderFragmentManager.this.f441a.remove(activity)) != null) {
                    Log.e(HolderFragment.b, "Failed to save a ViewModel for " + activity);
                }
            }
        };
        private boolean d = false;
        private FragmentManager.FragmentLifecycleCallbacks e = new FragmentManager.FragmentLifecycleCallbacks() {
            public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
                super.onFragmentDestroyed(fragmentManager, fragment);
                if (((HolderFragment) HolderFragmentManager.this.b.remove(fragment)) != null) {
                    Log.e(HolderFragment.b, "Failed to save a ViewModel for " + fragment);
                }
            }
        };

        HolderFragmentManager() {
        }

        /* access modifiers changed from: package-private */
        public void a(Fragment fragment) {
            Fragment parentFragment = fragment.getParentFragment();
            if (parentFragment != null) {
                this.b.remove(parentFragment);
                parentFragment.getFragmentManager().unregisterFragmentLifecycleCallbacks(this.e);
                return;
            }
            this.f441a.remove(fragment.getActivity());
        }

        private static HolderFragment a(FragmentManager fragmentManager) {
            if (!fragmentManager.isDestroyed()) {
                Fragment findFragmentByTag = fragmentManager.findFragmentByTag(HolderFragment.f440a);
                if (findFragmentByTag == null || (findFragmentByTag instanceof HolderFragment)) {
                    return (HolderFragment) findFragmentByTag;
                }
                throw new IllegalStateException("Unexpected fragment instance was returned by HOLDER_TAG");
            }
            throw new IllegalStateException("Can't access ViewModels from onDestroy");
        }

        private static HolderFragment b(FragmentManager fragmentManager) {
            HolderFragment holderFragment = new HolderFragment();
            fragmentManager.beginTransaction().add((Fragment) holderFragment, HolderFragment.f440a).commitAllowingStateLoss();
            return holderFragment;
        }

        /* access modifiers changed from: package-private */
        public HolderFragment a(FragmentActivity fragmentActivity) {
            FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
            HolderFragment a2 = a(supportFragmentManager);
            if (a2 != null) {
                return a2;
            }
            HolderFragment holderFragment = this.f441a.get(fragmentActivity);
            if (holderFragment != null) {
                return holderFragment;
            }
            if (!this.d) {
                this.d = true;
                fragmentActivity.getApplication().registerActivityLifecycleCallbacks(this.c);
            }
            HolderFragment b2 = b(supportFragmentManager);
            this.f441a.put(fragmentActivity, b2);
            return b2;
        }

        /* access modifiers changed from: package-private */
        public HolderFragment b(Fragment fragment) {
            FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            HolderFragment a2 = a(childFragmentManager);
            if (a2 != null) {
                return a2;
            }
            HolderFragment holderFragment = this.b.get(fragment);
            if (holderFragment != null) {
                return holderFragment;
            }
            fragment.getFragmentManager().registerFragmentLifecycleCallbacks(this.e, false);
            HolderFragment b2 = b(childFragmentManager);
            this.b.put(fragment, b2);
            return b2;
        }
    }
}
