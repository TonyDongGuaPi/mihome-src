package com.bumptech.glide;

import android.support.annotation.NonNull;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.NoTransition;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.request.transition.ViewAnimationFactory;
import com.bumptech.glide.request.transition.ViewPropertyAnimationFactory;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.bumptech.glide.util.Preconditions;

public abstract class TransitionOptions<CHILD extends TransitionOptions<CHILD, TranscodeType>, TranscodeType> implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private TransitionFactory<? super TranscodeType> f4819a = NoTransition.a();

    private CHILD a() {
        return this;
    }

    @NonNull
    public final CHILD b() {
        return b(NoTransition.a());
    }

    @NonNull
    public final CHILD b(int i) {
        return b(new ViewAnimationFactory(i));
    }

    @NonNull
    public final CHILD b(@NonNull ViewPropertyTransition.Animator animator) {
        return b(new ViewPropertyAnimationFactory(animator));
    }

    @NonNull
    public final CHILD b(@NonNull TransitionFactory<? super TranscodeType> transitionFactory) {
        this.f4819a = (TransitionFactory) Preconditions.a(transitionFactory);
        return a();
    }

    /* renamed from: c */
    public final CHILD clone() {
        try {
            return (TransitionOptions) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final TransitionFactory<? super TranscodeType> d() {
        return this.f4819a;
    }
}
