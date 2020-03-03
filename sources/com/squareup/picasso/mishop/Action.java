package com.squareup.picasso.mishop;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.mishop.Picasso;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

abstract class Action<T> {
    boolean cancelled;
    final Drawable errorDrawable;
    final int errorResId;
    final float fade;
    final String key;
    final int memoryPolicy;
    final int networkPolicy;
    final boolean noFade;
    final Picasso picasso;
    final Request request;
    final Object tag;
    final WeakReference<T> target;
    boolean willReplay;

    /* access modifiers changed from: package-private */
    public abstract void complete(Bitmap bitmap, Picasso.LoadedFrom loadedFrom);

    /* access modifiers changed from: package-private */
    public abstract void error();

    static class RequestWeakReference<M> extends WeakReference<M> {
        final Action action;

        public RequestWeakReference(Action action2, M m, ReferenceQueue<? super M> referenceQueue) {
            super(m, referenceQueue);
            this.action = action2;
        }
    }

    Action(Picasso picasso2, T t, Request request2, int i, int i2, int i3, Drawable drawable, String str, Object obj, boolean z, float f) {
        RequestWeakReference requestWeakReference;
        this.picasso = picasso2;
        this.request = request2;
        if (t == null) {
            requestWeakReference = null;
        } else {
            requestWeakReference = new RequestWeakReference(this, t, picasso2.referenceQueue);
        }
        this.target = requestWeakReference;
        this.memoryPolicy = i;
        this.networkPolicy = i2;
        this.noFade = z;
        this.fade = f;
        this.errorResId = i3;
        this.errorDrawable = drawable;
        this.key = str;
        this.tag = obj == null ? this : obj;
    }

    Action(Picasso picasso2, T t, Request request2, int i, int i2, int i3, Drawable drawable, String str, Object obj, boolean z) {
        this(picasso2, t, request2, i, i2, i3, drawable, str, obj, z, -1.0f);
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        this.cancelled = true;
    }

    /* access modifiers changed from: package-private */
    public Request getRequest() {
        return this.request;
    }

    /* access modifiers changed from: package-private */
    public T getTarget() {
        if (this.target == null) {
            return null;
        }
        return this.target.get();
    }

    /* access modifiers changed from: package-private */
    public String getKey() {
        return this.key;
    }

    /* access modifiers changed from: package-private */
    public boolean isCancelled() {
        return this.cancelled;
    }

    /* access modifiers changed from: package-private */
    public boolean willReplay() {
        return this.willReplay;
    }

    /* access modifiers changed from: package-private */
    public int getMemoryPolicy() {
        return this.memoryPolicy;
    }

    /* access modifiers changed from: package-private */
    public int getNetworkPolicy() {
        return this.networkPolicy;
    }

    /* access modifiers changed from: package-private */
    public Picasso getPicasso() {
        return this.picasso;
    }

    /* access modifiers changed from: package-private */
    public Picasso.Priority getPriority() {
        return this.request.priority;
    }

    /* access modifiers changed from: package-private */
    public Object getTag() {
        return this.tag;
    }
}