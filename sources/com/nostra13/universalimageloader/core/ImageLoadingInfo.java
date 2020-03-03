package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import java.util.concurrent.locks.ReentrantLock;

final class ImageLoadingInfo {

    /* renamed from: a  reason: collision with root package name */
    final String f8467a;
    final String b;
    final ImageAware c;
    final ImageSize d;
    final DisplayImageOptions e;
    final ImageLoadingListener f;
    final ImageLoadingProgressListener g;
    final ReentrantLock h;

    public ImageLoadingInfo(String str, ImageAware imageAware, ImageSize imageSize, String str2, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener, ReentrantLock reentrantLock) {
        this.f8467a = str;
        this.c = imageAware;
        this.d = imageSize;
        this.e = displayImageOptions;
        this.f = imageLoadingListener;
        this.g = imageLoadingProgressListener;
        this.h = reentrantLock;
        this.b = str2;
    }
}
