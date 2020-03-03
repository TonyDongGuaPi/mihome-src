package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SupportRequestManagerFragment extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5044a = "SupportRMFragment";
    private final ActivityFragmentLifecycle b;
    private final RequestManagerTreeNode c;
    private final Set<SupportRequestManagerFragment> d;
    @Nullable
    private SupportRequestManagerFragment e;
    @Nullable
    private RequestManager f;
    @Nullable
    private Fragment g;

    public SupportRequestManagerFragment() {
        this(new ActivityFragmentLifecycle());
    }

    @VisibleForTesting
    @SuppressLint({"ValidFragment"})
    public SupportRequestManagerFragment(@NonNull ActivityFragmentLifecycle activityFragmentLifecycle) {
        this.c = new SupportFragmentRequestManagerTreeNode();
        this.d = new HashSet();
        this.b = activityFragmentLifecycle;
    }

    public void a(@Nullable RequestManager requestManager) {
        this.f = requestManager;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ActivityFragmentLifecycle a() {
        return this.b;
    }

    @Nullable
    public RequestManager b() {
        return this.f;
    }

    @NonNull
    public RequestManagerTreeNode c() {
        return this.c;
    }

    private void a(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.d.add(supportRequestManagerFragment);
    }

    private void b(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.d.remove(supportRequestManagerFragment);
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public Set<SupportRequestManagerFragment> d() {
        if (this.e == null) {
            return Collections.emptySet();
        }
        if (equals(this.e)) {
            return Collections.unmodifiableSet(this.d);
        }
        HashSet hashSet = new HashSet();
        for (SupportRequestManagerFragment next : this.e.d()) {
            if (b(next.e())) {
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
    private Fragment e() {
        Fragment parentFragment = getParentFragment();
        return parentFragment != null ? parentFragment : this.g;
    }

    private boolean b(@NonNull Fragment fragment) {
        Fragment e2 = e();
        while (true) {
            Fragment parentFragment = fragment.getParentFragment();
            if (parentFragment == null) {
                return false;
            }
            if (parentFragment.equals(e2)) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
    }

    private void a(@NonNull FragmentActivity fragmentActivity) {
        f();
        this.e = Glide.b((Context) fragmentActivity).i().b(fragmentActivity);
        if (!equals(this.e)) {
            this.e.a(this);
        }
    }

    private void f() {
        if (this.e != null) {
            this.e.b(this);
            this.e = null;
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            a(getActivity());
        } catch (IllegalStateException e2) {
            if (Log.isLoggable(f5044a, 5)) {
                Log.w(f5044a, "Unable to register fragment with root", e2);
            }
        }
    }

    public void onDetach() {
        super.onDetach();
        this.g = null;
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

    private class SupportFragmentRequestManagerTreeNode implements RequestManagerTreeNode {
        SupportFragmentRequestManagerTreeNode() {
        }

        @NonNull
        public Set<RequestManager> a() {
            Set<SupportRequestManagerFragment> d = SupportRequestManagerFragment.this.d();
            HashSet hashSet = new HashSet(d.size());
            for (SupportRequestManagerFragment next : d) {
                if (next.b() != null) {
                    hashSet.add(next.b());
                }
            }
            return hashSet;
        }

        public String toString() {
            return super.toString() + "{fragment=" + SupportRequestManagerFragment.this + "}";
        }
    }
}
