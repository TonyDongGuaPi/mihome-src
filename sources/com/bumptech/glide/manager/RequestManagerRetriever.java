package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RequestManagerRetriever implements Handler.Callback {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final String f5042a = "com.bumptech.glide.manager";
    private static final String d = "RMRetriever";
    private static final int e = 1;
    private static final int f = 2;
    private static final String g = "key";
    private static final RequestManagerFactory n = new RequestManagerFactory() {
        @NonNull
        public RequestManager a(@NonNull Glide glide, @NonNull Lifecycle lifecycle, @NonNull RequestManagerTreeNode requestManagerTreeNode, @NonNull Context context) {
            return new RequestManager(glide, lifecycle, requestManagerTreeNode, context);
        }
    };
    @VisibleForTesting
    final Map<FragmentManager, RequestManagerFragment> b = new HashMap();
    @VisibleForTesting
    final Map<android.support.v4.app.FragmentManager, SupportRequestManagerFragment> c = new HashMap();
    private volatile RequestManager h;
    private final Handler i;
    private final RequestManagerFactory j;
    private final ArrayMap<View, Fragment> k = new ArrayMap<>();
    private final ArrayMap<View, android.app.Fragment> l = new ArrayMap<>();
    private final Bundle m = new Bundle();

    public interface RequestManagerFactory {
        @NonNull
        RequestManager a(@NonNull Glide glide, @NonNull Lifecycle lifecycle, @NonNull RequestManagerTreeNode requestManagerTreeNode, @NonNull Context context);
    }

    public RequestManagerRetriever(@Nullable RequestManagerFactory requestManagerFactory) {
        this.j = requestManagerFactory == null ? n : requestManagerFactory;
        this.i = new Handler(Looper.getMainLooper(), this);
    }

    @NonNull
    private RequestManager b(@NonNull Context context) {
        if (this.h == null) {
            synchronized (this) {
                if (this.h == null) {
                    this.h = this.j.a(Glide.b(context.getApplicationContext()), new ApplicationLifecycle(), new EmptyRequestManagerTreeNode(), context.getApplicationContext());
                }
            }
        }
        return this.h;
    }

    @NonNull
    public RequestManager a(@NonNull Context context) {
        if (context != null) {
            if (Util.c() && !(context instanceof Application)) {
                if (context instanceof FragmentActivity) {
                    return a((FragmentActivity) context);
                }
                if (context instanceof Activity) {
                    return a((Activity) context);
                }
                if (context instanceof ContextWrapper) {
                    return a(((ContextWrapper) context).getBaseContext());
                }
            }
            return b(context);
        }
        throw new IllegalArgumentException("You cannot start a load on a null Context");
    }

    @NonNull
    public RequestManager a(@NonNull FragmentActivity fragmentActivity) {
        if (Util.d()) {
            return a(fragmentActivity.getApplicationContext());
        }
        c((Activity) fragmentActivity);
        return a((Context) fragmentActivity, fragmentActivity.getSupportFragmentManager(), (Fragment) null, d(fragmentActivity));
    }

    @NonNull
    public RequestManager a(@NonNull Fragment fragment) {
        Preconditions.a(fragment.getActivity(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (Util.d()) {
            return a(fragment.getActivity().getApplicationContext());
        }
        return a((Context) fragment.getActivity(), fragment.getChildFragmentManager(), fragment, fragment.isVisible());
    }

    @NonNull
    public RequestManager a(@NonNull Activity activity) {
        if (Util.d()) {
            return a(activity.getApplicationContext());
        }
        c(activity);
        return a((Context) activity, activity.getFragmentManager(), (android.app.Fragment) null, d(activity));
    }

    @NonNull
    public RequestManager a(@NonNull View view) {
        if (Util.d()) {
            return a(view.getContext().getApplicationContext());
        }
        Preconditions.a(view);
        Preconditions.a(view.getContext(), "Unable to obtain a request manager for a view without a Context");
        Activity c2 = c(view.getContext());
        if (c2 == null) {
            return a(view.getContext().getApplicationContext());
        }
        if (c2 instanceof FragmentActivity) {
            Fragment a2 = a(view, (FragmentActivity) c2);
            return a2 != null ? a(a2) : a(c2);
        }
        android.app.Fragment a3 = a(view, c2);
        if (a3 == null) {
            return a(c2);
        }
        return a(a3);
    }

    private static void a(@Nullable Collection<Fragment> collection, @NonNull Map<View, Fragment> map) {
        if (collection != null) {
            for (Fragment next : collection) {
                if (!(next == null || next.getView() == null)) {
                    map.put(next.getView(), next);
                    a((Collection<Fragment>) next.getChildFragmentManager().getFragments(), map);
                }
            }
        }
    }

    @Nullable
    private Fragment a(@NonNull View view, @NonNull FragmentActivity fragmentActivity) {
        this.k.clear();
        a((Collection<Fragment>) fragmentActivity.getSupportFragmentManager().getFragments(), (Map<View, Fragment>) this.k);
        View findViewById = fragmentActivity.findViewById(16908290);
        Fragment fragment = null;
        while (!view.equals(findViewById) && (fragment = this.k.get(view)) == null && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        this.k.clear();
        return fragment;
    }

    @Nullable
    @Deprecated
    private android.app.Fragment a(@NonNull View view, @NonNull Activity activity) {
        this.l.clear();
        a(activity.getFragmentManager(), this.l);
        View findViewById = activity.findViewById(16908290);
        android.app.Fragment fragment = null;
        while (!view.equals(findViewById) && (fragment = this.l.get(view)) == null && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        this.l.clear();
        return fragment;
    }

    @TargetApi(26)
    @Deprecated
    private void a(@NonNull FragmentManager fragmentManager, @NonNull ArrayMap<View, android.app.Fragment> arrayMap) {
        if (Build.VERSION.SDK_INT >= 26) {
            for (android.app.Fragment next : fragmentManager.getFragments()) {
                if (next.getView() != null) {
                    arrayMap.put(next.getView(), next);
                    a(next.getChildFragmentManager(), arrayMap);
                }
            }
            return;
        }
        b(fragmentManager, arrayMap);
    }

    @Deprecated
    private void b(@NonNull FragmentManager fragmentManager, @NonNull ArrayMap<View, android.app.Fragment> arrayMap) {
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            this.m.putInt("key", i2);
            android.app.Fragment fragment = null;
            try {
                fragment = fragmentManager.getFragment(this.m, "key");
            } catch (Exception unused) {
            }
            if (fragment != null) {
                if (fragment.getView() != null) {
                    arrayMap.put(fragment.getView(), fragment);
                    if (Build.VERSION.SDK_INT >= 17) {
                        a(fragment.getChildFragmentManager(), arrayMap);
                    }
                }
                i2 = i3;
            } else {
                return;
            }
        }
    }

    @Nullable
    private Activity c(@NonNull Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return c(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    @TargetApi(17)
    private static void c(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @TargetApi(17)
    @Deprecated
    @NonNull
    public RequestManager a(@NonNull android.app.Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        } else if (Util.d() || Build.VERSION.SDK_INT < 17) {
            return a(fragment.getActivity().getApplicationContext());
        } else {
            return a((Context) fragment.getActivity(), fragment.getChildFragmentManager(), fragment, fragment.isVisible());
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    @NonNull
    public RequestManagerFragment b(Activity activity) {
        return a(activity.getFragmentManager(), (android.app.Fragment) null, d(activity));
    }

    @NonNull
    private RequestManagerFragment a(@NonNull FragmentManager fragmentManager, @Nullable android.app.Fragment fragment, boolean z) {
        RequestManagerFragment requestManagerFragment = (RequestManagerFragment) fragmentManager.findFragmentByTag(f5042a);
        if (requestManagerFragment == null && (requestManagerFragment = this.b.get(fragmentManager)) == null) {
            requestManagerFragment = new RequestManagerFragment();
            requestManagerFragment.a(fragment);
            if (z) {
                requestManagerFragment.a().a();
            }
            this.b.put(fragmentManager, requestManagerFragment);
            fragmentManager.beginTransaction().add(requestManagerFragment, f5042a).commitAllowingStateLoss();
            this.i.obtainMessage(1, fragmentManager).sendToTarget();
        }
        return requestManagerFragment;
    }

    @Deprecated
    @NonNull
    private RequestManager a(@NonNull Context context, @NonNull FragmentManager fragmentManager, @Nullable android.app.Fragment fragment, boolean z) {
        RequestManagerFragment a2 = a(fragmentManager, fragment, z);
        RequestManager b2 = a2.b();
        if (b2 != null) {
            return b2;
        }
        RequestManager a3 = this.j.a(Glide.b(context), a2.a(), a2.c(), context);
        a2.a(a3);
        return a3;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public SupportRequestManagerFragment b(FragmentActivity fragmentActivity) {
        return a(fragmentActivity.getSupportFragmentManager(), (Fragment) null, d(fragmentActivity));
    }

    private static boolean d(Activity activity) {
        return !activity.isFinishing();
    }

    @NonNull
    private SupportRequestManagerFragment a(@NonNull android.support.v4.app.FragmentManager fragmentManager, @Nullable Fragment fragment, boolean z) {
        SupportRequestManagerFragment supportRequestManagerFragment = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag(f5042a);
        if (supportRequestManagerFragment == null && (supportRequestManagerFragment = this.c.get(fragmentManager)) == null) {
            supportRequestManagerFragment = new SupportRequestManagerFragment();
            supportRequestManagerFragment.a(fragment);
            if (z) {
                supportRequestManagerFragment.a().a();
            }
            this.c.put(fragmentManager, supportRequestManagerFragment);
            fragmentManager.beginTransaction().add((Fragment) supportRequestManagerFragment, f5042a).commitAllowingStateLoss();
            this.i.obtainMessage(2, fragmentManager).sendToTarget();
        }
        return supportRequestManagerFragment;
    }

    @NonNull
    private RequestManager a(@NonNull Context context, @NonNull android.support.v4.app.FragmentManager fragmentManager, @Nullable Fragment fragment, boolean z) {
        SupportRequestManagerFragment a2 = a(fragmentManager, fragment, z);
        RequestManager b2 = a2.b();
        if (b2 != null) {
            return b2;
        }
        RequestManager a3 = this.j.a(Glide.b(context), a2.a(), a2.c(), context);
        a2.a(a3);
        return a3;
    }

    public boolean handleMessage(Message message) {
        Object obj;
        Object obj2 = null;
        boolean z = true;
        switch (message.what) {
            case 1:
                obj2 = (FragmentManager) message.obj;
                obj = this.b.remove(obj2);
                break;
            case 2:
                obj2 = (android.support.v4.app.FragmentManager) message.obj;
                obj = this.c.remove(obj2);
                break;
            default:
                z = false;
                obj = null;
                break;
        }
        if (z && obj == null && Log.isLoggable(d, 5)) {
            Log.w(d, "Failed to remove expected request manager fragment, manager: " + obj2);
        }
        return z;
    }
}
