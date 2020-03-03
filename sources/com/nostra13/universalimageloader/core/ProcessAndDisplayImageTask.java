package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.utils.L;

final class ProcessAndDisplayImageTask implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8472a = "PostProcess image before displaying [%s]";
    private final ImageLoaderEngine b;
    private final Bitmap c;
    private final ImageLoadingInfo d;
    private final Handler e;

    public ProcessAndDisplayImageTask(ImageLoaderEngine imageLoaderEngine, Bitmap bitmap, ImageLoadingInfo imageLoadingInfo, Handler handler) {
        this.b = imageLoaderEngine;
        this.c = bitmap;
        this.d = imageLoadingInfo;
        this.e = handler;
    }

    public void run() {
        L.a(f8472a, this.d.b);
        LoadAndDisplayImageTask.a(new DisplayBitmapTask(this.d.e.p().a(this.c), this.d, this.b, LoadedFrom.MEMORY_CACHE), this.d.e.s(), this.e, this.b);
    }
}
