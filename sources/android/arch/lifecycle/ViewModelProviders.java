package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class ViewModelProviders {
    private static Application a(Activity activity) {
        Application application = activity.getApplication();
        if (application != null) {
            return application;
        }
        throw new IllegalStateException("Your activity/fragment is not yet attached to Application. You can't request ViewModel before onCreate call.");
    }

    private static Activity b(Fragment fragment) {
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
    }

    @MainThread
    @NonNull
    public static ViewModelProvider a(@NonNull Fragment fragment) {
        return a(fragment, (ViewModelProvider.Factory) null);
    }

    @MainThread
    @NonNull
    public static ViewModelProvider a(@NonNull FragmentActivity fragmentActivity) {
        return a(fragmentActivity, (ViewModelProvider.Factory) null);
    }

    @MainThread
    @NonNull
    public static ViewModelProvider a(@NonNull Fragment fragment, @Nullable ViewModelProvider.Factory factory) {
        Application a2 = a(b(fragment));
        if (factory == null) {
            factory = ViewModelProvider.AndroidViewModelFactory.a(a2);
        }
        return new ViewModelProvider(ViewModelStores.a(fragment), factory);
    }

    @MainThread
    @NonNull
    public static ViewModelProvider a(@NonNull FragmentActivity fragmentActivity, @Nullable ViewModelProvider.Factory factory) {
        Application a2 = a((Activity) fragmentActivity);
        if (factory == null) {
            factory = ViewModelProvider.AndroidViewModelFactory.a(a2);
        }
        return new ViewModelProvider(ViewModelStores.a(fragmentActivity), factory);
    }

    @Deprecated
    public static class DefaultFactory extends ViewModelProvider.AndroidViewModelFactory {
        @Deprecated
        public DefaultFactory(@NonNull Application application) {
            super(application);
        }
    }
}
