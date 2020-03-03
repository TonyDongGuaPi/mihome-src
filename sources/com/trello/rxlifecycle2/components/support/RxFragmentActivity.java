package com.trello.rxlifecycle2.components.support;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public abstract class RxFragmentActivity extends FragmentActivity implements LifecycleProvider<ActivityEvent> {

    /* renamed from: a  reason: collision with root package name */
    private final BehaviorSubject<ActivityEvent> f9530a = BehaviorSubject.create();

    @CheckResult
    @NonNull
    public final Observable<ActivityEvent> lifecycle() {
        return this.f9530a.hide();
    }

    @CheckResult
    @NonNull
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent activityEvent) {
        return RxLifecycle.a(this.f9530a, activityEvent);
    }

    @CheckResult
    @NonNull
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.a((Observable<ActivityEvent>) this.f9530a);
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f9530a.onNext(ActivityEvent.CREATE);
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onStart() {
        super.onStart();
        this.f9530a.onNext(ActivityEvent.START);
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onResume() {
        super.onResume();
        this.f9530a.onNext(ActivityEvent.RESUME);
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onPause() {
        this.f9530a.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onStop() {
        this.f9530a.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onDestroy() {
        this.f9530a.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }
}
