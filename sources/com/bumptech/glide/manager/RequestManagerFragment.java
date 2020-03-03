package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Deprecated
public class RequestManagerFragment extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5040a = "RMFragment";
    private final ActivityFragmentLifecycle b;
    private final RequestManagerTreeNode c;
    private final Set<RequestManagerFragment> d;
    @Nullable
    private RequestManager e;
    @Nullable
    private RequestManagerFragment f;
    @Nullable
    private Fragment g;

    public RequestManagerFragment() {
        this(new ActivityFragmentLifecycle());
    }

    @VisibleForTesting
    @SuppressLint({"ValidFragment"})
    RequestManagerFragment(@NonNull ActivityFragmentLifecycle activityFragmentLifecycle) {
        this.c = new FragmentRequestManagerTreeNode();
        this.d = new HashSet();
        this.b = activityFragmentLifecycle;
    }

    public void a(@Nullable RequestManager requestManager) {
        this.e = requestManager;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ActivityFragmentLifecycle a() {
        return this.b;
    }

    @Nullable
    public RequestManager b() {
        return this.e;
    }

    @NonNull
    public RequestManagerTreeNode c() {
        return this.c;
    }

    private void a(RequestManagerFragment requestManagerFragment) {
        this.d.add(requestManagerFragment);
    }

    private void b(RequestManagerFragment requestManagerFragment) {
        this.d.remove(requestManagerFragment);
    }

    /* access modifiers changed from: package-private */
    @TargetApi(17)
    @NonNull
    public Set<RequestManagerFragment> d() {
        if (equals(this.f)) {
            return Collections.unmodifiableSet(this.d);
        }
        if (this.f == null || Build.VERSION.SDK_INT < 17) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (RequestManagerFragment next : this.f.d()) {
            if (b(next.getParentFragment())) {
                hashSet.add(next);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    /* access modifiers changed from: package-private */
    public void a(@Nullable Fragment fragment) {
        this.g = fragment;
        if (fragment != null && fragment.getActivity() != null) {
            a(fragment.getActivity());
        }
    }

    @Nullable
    @TargetApi(17)
    private Fragment e() {
        Fragment parentFragment = Build.VERSION.SDK_INT >= 17 ? getParentFragment() : null;
        return parentFragment != null ? parentFragment : this.g;
    }

    @TargetApi(17)
    private boolean b(@NonNull Fragment fragment) {
        Fragment parentFragment = getParentFragment();
        while (true) {
            Fragment parentFragment2 = fragment.getParentFragment();
            if (parentFragment2 == null) {
                return false;
            }
            if (parentFragment2.equals(parentFragment)) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
    }

    private void a(@NonNull Activity activity) {
        f();
        this.f = Glide.b((Context) activity).i().b(activity);
        if (!equals(this.f)) {
            this.f.a(this);
        }
    }

    private void f() {
        if (this.f != null) {
            this.f.b(this);
            this.f = null;
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            a(activity);
        } catch (IllegalStateException e2) {
            if (Log.isLoggable(f5040a, 5)) {
                Log.w(f5040a, "Unable to register fragment with root", e2);
            }
        }
    }

    public void onDetach() {
        super.onDetach();
        f();
    }

    public void onStart() {
        super.onStart();
        this.b.a();
    }

    public void onStop() {
        super.onStop();
        this.b.b();
    }

    public void onDestroy() {
        super.onDestroy();
        this.b.c();
        f();
    }

    public String toString() {
        return super.toString() + "{parent=" + e() + "}";
    }

    private class FragmentRequestManagerTreeNode implements RequestManagerTreeNode {
        FragmentRequestManagerTreeNode() {
        }

        @NonNull
        public Set<RequestManager> a() {
            Set<RequestManagerFragment> d = RequestManagerFragment.this.d();
            HashSet hashSet = new HashSet(d.size());
            for (RequestManagerFragment next : d) {
                if (next.b() != null) {
                    hashSet.add(next.b());
                }
            }
            return hashSet;
        }

        public String toString() {
            return super.toString() + "{fragment=" + RequestManagerFragment.this + "}";
        }
    }
}
