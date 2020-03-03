package com.xiaomi.base.imageloader;

public class ImageLoader {

    /* renamed from: a  reason: collision with root package name */
    private static ImageLoaderInterface f10011a = new GlideImageLoader();

    public static void a(ImageLoaderInterface imageLoaderInterface) {
        f10011a = imageLoaderInterface;
    }

    public static ImageLoaderInterface a() {
        return f10011a;
    }

    public static void a(Option option) {
        Option.f10012a = option;
    }
}
