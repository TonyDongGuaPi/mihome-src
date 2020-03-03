package com.bumptech.glide.request.transition;

import com.bumptech.glide.load.DataSource;

public interface TransitionFactory<R> {
    Transition<R> a(DataSource dataSource, boolean z);
}
