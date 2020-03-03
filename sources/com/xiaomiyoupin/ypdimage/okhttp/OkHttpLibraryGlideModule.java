package com.xiaomiyoupin.ypdimage.okhttp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.LibraryGlideModule;
import com.xiaomiyoupin.ypdimage.okhttp.OkHttpUrlLoader;
import java.io.InputStream;

@GlideModule
public final class OkHttpLibraryGlideModule extends LibraryGlideModule {
    public void a(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        Log.i(getClass().getSimpleName(), "registerComponents!!!");
        registry.c(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
}
