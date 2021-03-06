package com.trello.rxlifecycle2.components;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public abstract class RxDialogFragment extends DialogFragment implements LifecycleProvider<FragmentEvent> {

    /* renamed from: a  reason: collision with root package name */
    private final BehaviorSubject<FragmentEvent> f9524a = BehaviorSubject.create();

    @CheckResult
    @NonNull
    public final Observable<FragmentEvent> lifecycle() {
        return this.f9524a.hide();
    }

    @CheckResult
    @NonNull
    /* renamed from: a */
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent fragmentEvent) {
        return RxLifecycle.a(this.f9524a, fragmentEvent);
    }

    @CheckResult
    @NonNull
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.b(this.f9524a);
    }

    @CallSuper
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f9524a.onNext(FragmentEvent.ATTACH);
    }

    @CallSuper
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9524a.onNext(FragmentEvent.CREATE);
    }

    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f9524a.onNext(FragmentEvent.CREATE_VIEW);
    }

    @CallSuper
    public void onStart() {
        super.onStart();
        this.f9524a.onNext(FragmentEvent.START);
    }

    @CallSuper
    public void onResume() {
        super.onResume();
        this.f9524a.onNext(FragmentEvent.RESUME);
    }

    @CallSuper
    public void onPause() {
        this.f9524a.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @CallSuper
    public void onStop() {
        this.f9524a.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @CallSuper
    public void onDestroyView() {
        this.f9524a.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @CallSuper
    public void onDestroy() {
        this.f9524a.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }

    @CallSuper
    public void onDetach() {
        this.f9524a.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }
}
