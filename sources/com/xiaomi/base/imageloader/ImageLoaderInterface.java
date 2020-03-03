package com.xiaomi.base.imageloader;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.util.concurrent.Future;

public interface ImageLoaderInterface {
    Future<Drawable> a(Activity activity, String str, Option option);

    Future<Drawable> a(Fragment fragment, String str, Option option);

    Future<Drawable> a(Context context, int i);

    Future<Drawable> a(Context context, int i, Option option);

    Future<Drawable> a(Context context, String str);

    Future<Drawable> a(Context context, String str, Option option);

    void a(int i, ImageView imageView);

    void a(int i, ImageView imageView, Option option);

    void a(String str, ImageView imageView);

    void a(String str, ImageView imageView, Activity activity, Option option);

    void a(String str, ImageView imageView, Fragment fragment, Option option);

    void a(String str, ImageView imageView, Option option);
}
