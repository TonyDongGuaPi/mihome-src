package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.L;

final class DisplayBitmapTask implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8455a = "Display image in ImageAware (loaded from %1$s) [%2$s]";
    private static final String b = "ImageAware is reused for another image. Task is cancelled. [%s]";
    private static final String c = "ImageAware was collected by GC. Task is cancelled. [%s]";
    private final Bitmap d;
    private final String e;
    private final ImageAware f;
    private final String g;
    private final BitmapDisplayer h;
    private final ImageLoadingListener i;
    private final ImageLoaderEngine j;
    private final LoadedFrom k;

    public DisplayBitmapTask(Bitmap bitmap, ImageLoadingInfo imageLoadingInfo, ImageLoaderEngine imageLoaderEngine, LoadedFrom loadedFrom) {
        this.d = bitmap;
        this.e = imageLoadingInfo.f8467a;
        this.f = imageLoadingInfo.c;
        this.g = imageLoadingInfo.b;
        this.h = imageLoadingInfo.e.q();
        this.i = imageLoadingInfo.f;
        this.j = imageLoaderEngine;
        this.k = loadedFrom;
    }

    public void run() {
        if (this.f.e()) {
            L.a(c, this.g);
            this.i.onLoadingCancelled(this.e, this.f.d());
        } else if (a()) {
            L.a(b, this.g);
            this.i.onLoadingCancelled(this.e, this.f.d());
        } else {
            L.a(f8455a, this.k, this.g);
            this.h.a(this.d, this.f, this.k);
            this.j.b(this.f);
            this.i.onLoadingComplete(this.e, this.f.d(), this.d);
        }
    }

    private boolean a() {
        return !this.g.equals(this.j.a(this.f));
    }
}
