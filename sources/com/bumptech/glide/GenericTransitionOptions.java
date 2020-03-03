package com.bumptech.glide;

import android.support.annotation.NonNull;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.request.transition.ViewPropertyTransition;

public final class GenericTransitionOptions<TranscodeType> extends TransitionOptions<GenericTransitionOptions<TranscodeType>, TranscodeType> {
    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> a() {
        return (GenericTransitionOptions) new GenericTransitionOptions().b();
    }

    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> a(int i) {
        return (GenericTransitionOptions) new GenericTransitionOptions().b(i);
    }

    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> a(@NonNull ViewPropertyTransition.Animator animator) {
        return (GenericTransitionOptions) new GenericTransitionOptions().b(animator);
    }

    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> a(@NonNull TransitionFactory<? super TranscodeType> transitionFactory) {
        return (GenericTransitionOptions) new GenericTransitionOptions().b(transitionFactory);
    }
}
