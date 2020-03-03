package com.bumptech.glide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.AbsListView;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.util.List;
import java.util.Queue;

public class ListPreloader<T> implements AbsListView.OnScrollListener {

    /* renamed from: a  reason: collision with root package name */
    private final int f4810a;
    private final PreloadTargetQueue b;
    private final RequestManager c;
    private final PreloadModelProvider<T> d;
    private final PreloadSizeProvider<T> e;
    private int f;
    private int g;
    private int h = -1;
    private int i;
    private boolean j = true;

    public interface PreloadModelProvider<U> {
        @Nullable
        RequestBuilder<?> a(@NonNull U u);

        @NonNull
        List<U> a(int i);
    }

    public interface PreloadSizeProvider<T> {
        @Nullable
        int[] a(@NonNull T t, int i, int i2);
    }

    public void onScrollStateChanged(AbsListView absListView, int i2) {
    }

    public ListPreloader(@NonNull RequestManager requestManager, @NonNull PreloadModelProvider<T> preloadModelProvider, @NonNull PreloadSizeProvider<T> preloadSizeProvider, int i2) {
        this.c = requestManager;
        this.d = preloadModelProvider;
        this.e = preloadSizeProvider;
        this.f4810a = i2;
        this.b = new PreloadTargetQueue(i2 + 1);
    }

    public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        this.i = i4;
        if (i2 > this.h) {
            a(i3 + i2, true);
        } else if (i2 < this.h) {
            a(i2, false);
        }
        this.h = i2;
    }

    private void a(int i2, boolean z) {
        if (this.j != z) {
            this.j = z;
            a();
        }
        a(i2, (z ? this.f4810a : -this.f4810a) + i2);
    }

    private void a(int i2, int i3) {
        int i4;
        int i5;
        if (i2 < i3) {
            i4 = Math.max(this.f, i2);
            i5 = i3;
        } else {
            i5 = Math.min(this.g, i2);
            i4 = i3;
        }
        int min = Math.min(this.i, i5);
        int min2 = Math.min(this.i, Math.max(0, i4));
        if (i2 < i3) {
            for (int i6 = min2; i6 < min; i6++) {
                a(this.d.a(i6), i6, true);
            }
        } else {
            for (int i7 = min - 1; i7 >= min2; i7--) {
                a(this.d.a(i7), i7, false);
            }
        }
        this.g = min2;
        this.f = min;
    }

    private void a(List<T> list, int i2, boolean z) {
        int size = list.size();
        if (z) {
            for (int i3 = 0; i3 < size; i3++) {
                a(list.get(i3), i2, i3);
            }
            return;
        }
        for (int i4 = size - 1; i4 >= 0; i4--) {
            a(list.get(i4), i2, i4);
        }
    }

    private void a(@Nullable T t, int i2, int i3) {
        int[] a2;
        RequestBuilder<?> a3;
        if (t != null && (a2 = this.e.a(t, i2, i3)) != null && (a3 = this.d.a(t)) != null) {
            a3.a(this.b.a(a2[0], a2[1]));
        }
    }

    private void a() {
        for (int i2 = 0; i2 < this.f4810a; i2++) {
            this.c.a((Target<?>) this.b.a(0, 0));
        }
    }

    private static final class PreloadTargetQueue {

        /* renamed from: a  reason: collision with root package name */
        private final Queue<PreloadTarget> f4812a;

        PreloadTargetQueue(int i) {
            this.f4812a = Util.a(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.f4812a.offer(new PreloadTarget());
            }
        }

        public PreloadTarget a(int i, int i2) {
            PreloadTarget poll = this.f4812a.poll();
            this.f4812a.offer(poll);
            poll.b = i;
            poll.f4811a = i2;
            return poll;
        }
    }

    private static final class PreloadTarget extends BaseTarget<Object> {

        /* renamed from: a  reason: collision with root package name */
        int f4811a;
        int b;

        public void a(@NonNull Object obj, @Nullable Transition<? super Object> transition) {
        }

        public void b(@NonNull SizeReadyCallback sizeReadyCallback) {
        }

        PreloadTarget() {
        }

        public void a(@NonNull SizeReadyCallback sizeReadyCallback) {
            sizeReadyCallback.a(this.b, this.f4811a);
        }
    }
}
