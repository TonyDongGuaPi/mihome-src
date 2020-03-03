package com.nostra13.universalimageloader.core.decode;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.os.Build;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

public class ImageDecodingInfo {

    /* renamed from: a  reason: collision with root package name */
    private final String f8483a;
    private final String b;
    private final String c;
    private final ImageSize d;
    private final ImageScaleType e;
    private final ViewScaleType f;
    private final ImageDownloader g;
    private final Object h;
    private final boolean i;
    private final BitmapFactory.Options j = new BitmapFactory.Options();

    public ImageDecodingInfo(String str, String str2, String str3, ImageSize imageSize, ViewScaleType viewScaleType, ImageDownloader imageDownloader, DisplayImageOptions displayImageOptions) {
        this.f8483a = str;
        this.b = str2;
        this.c = str3;
        this.d = imageSize;
        this.e = displayImageOptions.j();
        this.f = viewScaleType;
        this.g = imageDownloader;
        this.h = displayImageOptions.n();
        this.i = displayImageOptions.m();
        a(displayImageOptions.k(), this.j);
    }

    private void a(BitmapFactory.Options options, BitmapFactory.Options options2) {
        options2.inDensity = options.inDensity;
        options2.inDither = options.inDither;
        options2.inInputShareable = options.inInputShareable;
        options2.inJustDecodeBounds = options.inJustDecodeBounds;
        options2.inPreferredConfig = options.inPreferredConfig;
        options2.inPurgeable = options.inPurgeable;
        options2.inSampleSize = options.inSampleSize;
        options2.inScaled = options.inScaled;
        options2.inScreenDensity = options.inScreenDensity;
        options2.inTargetDensity = options.inTargetDensity;
        options2.inTempStorage = options.inTempStorage;
        if (Build.VERSION.SDK_INT >= 10) {
            b(options, options2);
        }
        if (Build.VERSION.SDK_INT >= 11) {
            c(options, options2);
        }
    }

    @TargetApi(10)
    private void b(BitmapFactory.Options options, BitmapFactory.Options options2) {
        options2.inPreferQualityOverSpeed = options.inPreferQualityOverSpeed;
    }

    @TargetApi(11)
    private void c(BitmapFactory.Options options, BitmapFactory.Options options2) {
        options2.inBitmap = options.inBitmap;
        options2.inMutable = options.inMutable;
    }

    public String a() {
        return this.f8483a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public ImageSize d() {
        return this.d;
    }

    public ImageScaleType e() {
        return this.e;
    }

    public ViewScaleType f() {
        return this.f;
    }

    public ImageDownloader g() {
        return this.g;
    }

    public Object h() {
        return this.h;
    }

    public boolean i() {
        return this.i;
    }

    public BitmapFactory.Options j() {
        return this.j;
    }
}
