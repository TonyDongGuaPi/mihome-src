package com.bumptech.glide;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.data.InputStreamRewinder;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.BitmapPreFiller;
import com.bumptech.glide.load.engine.prefill.PreFillType;
import com.bumptech.glide.load.model.AssetUriLoader;
import com.bumptech.glide.load.model.ByteArrayLoader;
import com.bumptech.glide.load.model.ByteBufferEncoder;
import com.bumptech.glide.load.model.ByteBufferFileLoader;
import com.bumptech.glide.load.model.DataUrlLoader;
import com.bumptech.glide.load.model.FileLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.MediaStoreFileLoader;
import com.bumptech.glide.load.model.ResourceLoader;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.model.StringLoader;
import com.bumptech.glide.load.model.UnitModelLoader;
import com.bumptech.glide.load.model.UriLoader;
import com.bumptech.glide.load.model.UrlUriLoader;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.model.stream.HttpUriLoader;
import com.bumptech.glide.load.model.stream.MediaStoreImageThumbLoader;
import com.bumptech.glide.load.model.stream.MediaStoreVideoThumbLoader;
import com.bumptech.glide.load.model.stream.UrlLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableEncoder;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.ExifInterfaceImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.ResourceBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.UnitBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;
import com.bumptech.glide.load.resource.bytes.ByteBufferRewinder;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
import com.bumptech.glide.load.resource.drawable.UnitDrawableDecoder;
import com.bumptech.glide.load.resource.file.FileDecoder;
import com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableEncoder;
import com.bumptech.glide.load.resource.gif.GifFrameResourceDecoder;
import com.bumptech.glide.load.resource.gif.StreamGifDecoder;
import com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.BitmapDrawableTranscoder;
import com.bumptech.glide.load.resource.transcode.DrawableBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.module.ManifestParser;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Glide implements ComponentCallbacks2 {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4807a = "image_manager_disk_cache";
    private static final String b = "Glide";
    private static volatile Glide c;
    private static volatile boolean d;
    private final Engine e;
    private final BitmapPool f;
    private final MemoryCache g;
    private final BitmapPreFiller h;
    private final GlideContext i;
    private final Registry j;
    private final ArrayPool k;
    private final RequestManagerRetriever l;
    private final ConnectivityMonitorFactory m;
    private final List<RequestManager> n = new ArrayList();
    private MemoryCategory o = MemoryCategory.NORMAL;

    public void onConfigurationChanged(Configuration configuration) {
    }

    @Nullable
    public static File a(@NonNull Context context) {
        return a(context, "image_manager_disk_cache");
    }

    @Nullable
    public static File a(@NonNull Context context, @NonNull String str) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File file = new File(cacheDir, str);
            if (file.mkdirs() || (file.exists() && file.isDirectory())) {
                return file;
            }
            return null;
        }
        if (Log.isLoggable(b, 6)) {
            Log.e(b, "default disk cache dir is null");
        }
        return null;
    }

    @NonNull
    public static Glide b(@NonNull Context context) {
        if (c == null) {
            synchronized (Glide.class) {
                if (c == null) {
                    d(context);
                }
            }
        }
        return c;
    }

    private static void d(@NonNull Context context) {
        if (!d) {
            d = true;
            e(context);
            d = false;
            return;
        }
        throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
    }

    @VisibleForTesting
    @Deprecated
    public static synchronized void a(Glide glide) {
        synchronized (Glide.class) {
            if (c != null) {
                a();
            }
            c = glide;
        }
    }

    @VisibleForTesting
    public static synchronized void a(@NonNull Context context, @NonNull GlideBuilder glideBuilder) {
        synchronized (Glide.class) {
            if (c != null) {
                a();
            }
            b(context, glideBuilder);
        }
    }

    @VisibleForTesting
    public static synchronized void a() {
        synchronized (Glide.class) {
            if (c != null) {
                c.d().getApplicationContext().unregisterComponentCallbacks(c);
                c.e.b();
            }
            c = null;
        }
    }

    private static void e(@NonNull Context context) {
        b(context, new GlideBuilder());
    }

    private static void b(@NonNull Context context, @NonNull GlideBuilder glideBuilder) {
        Context applicationContext = context.getApplicationContext();
        GeneratedAppGlideModule k2 = k();
        List<GlideModule> emptyList = Collections.emptyList();
        if (k2 == null || k2.c()) {
            emptyList = new ManifestParser(applicationContext).a();
        }
        if (k2 != null && !k2.a().isEmpty()) {
            Set<Class<?>> a2 = k2.a();
            Iterator<GlideModule> it = emptyList.iterator();
            while (it.hasNext()) {
                GlideModule next = it.next();
                if (a2.contains(next.getClass())) {
                    if (Log.isLoggable(b, 3)) {
                        Log.d(b, "AppGlideModule excludes manifest GlideModule: " + next);
                    }
                    it.remove();
                }
            }
        }
        if (Log.isLoggable(b, 3)) {
            for (GlideModule glideModule : emptyList) {
                Log.d(b, "Discovered GlideModule from manifest: " + glideModule.getClass());
            }
        }
        glideBuilder.a(k2 != null ? k2.b() : null);
        for (GlideModule a3 : emptyList) {
            a3.a(applicationContext, glideBuilder);
        }
        if (k2 != null) {
            k2.a(applicationContext, glideBuilder);
        }
        Glide a4 = glideBuilder.a(applicationContext);
        for (GlideModule a5 : emptyList) {
            a5.a(applicationContext, a4, a4.j);
        }
        if (k2 != null) {
            k2.a(applicationContext, a4, a4.j);
        }
        applicationContext.registerComponentCallbacks(a4);
        c = a4;
    }

    @Nullable
    private static GeneratedAppGlideModule k() {
        try {
            return (GeneratedAppGlideModule) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable(b, 5)) {
                Log.w(b, "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
        } catch (InstantiationException e2) {
            a((Exception) e2);
        } catch (IllegalAccessException e3) {
            a((Exception) e3);
        } catch (NoSuchMethodException e4) {
            a((Exception) e4);
        } catch (InvocationTargetException e5) {
            a((Exception) e5);
        }
        return null;
    }

    private static void a(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    Glide(@NonNull Context context, @NonNull Engine engine, @NonNull MemoryCache memoryCache, @NonNull BitmapPool bitmapPool, @NonNull ArrayPool arrayPool, @NonNull RequestManagerRetriever requestManagerRetriever, @NonNull ConnectivityMonitorFactory connectivityMonitorFactory, int i2, @NonNull RequestOptions requestOptions, @NonNull Map<Class<?>, TransitionOptions<?, ?>> map, @NonNull List<RequestListener<Object>> list, boolean z) {
        Context context2 = context;
        MemoryCache memoryCache2 = memoryCache;
        BitmapPool bitmapPool2 = bitmapPool;
        ArrayPool arrayPool2 = arrayPool;
        this.e = engine;
        this.f = bitmapPool2;
        this.k = arrayPool2;
        this.g = memoryCache2;
        this.l = requestManagerRetriever;
        this.m = connectivityMonitorFactory;
        this.h = new BitmapPreFiller(memoryCache2, bitmapPool2, (DecodeFormat) requestOptions.A().a(Downsampler.b));
        Resources resources = context.getResources();
        this.j = new Registry();
        this.j.a((ImageHeaderParser) new DefaultImageHeaderParser());
        if (Build.VERSION.SDK_INT >= 27) {
            this.j.a((ImageHeaderParser) new ExifInterfaceImageHeaderParser());
        }
        List<ImageHeaderParser> a2 = this.j.a();
        Downsampler downsampler = new Downsampler(a2, resources.getDisplayMetrics(), bitmapPool2, arrayPool2);
        ByteBufferGifDecoder byteBufferGifDecoder = new ByteBufferGifDecoder(context2, a2, bitmapPool2, arrayPool2);
        ResourceDecoder<ParcelFileDescriptor, Bitmap> b2 = VideoDecoder.b(bitmapPool);
        ByteBufferBitmapDecoder byteBufferBitmapDecoder = new ByteBufferBitmapDecoder(downsampler);
        StreamBitmapDecoder streamBitmapDecoder = new StreamBitmapDecoder(downsampler, arrayPool2);
        ResourceDrawableDecoder resourceDrawableDecoder = new ResourceDrawableDecoder(context2);
        ResourceLoader.StreamFactory streamFactory = new ResourceLoader.StreamFactory(resources);
        ResourceLoader.UriFactory uriFactory = new ResourceLoader.UriFactory(resources);
        ResourceLoader.FileDescriptorFactory fileDescriptorFactory = new ResourceLoader.FileDescriptorFactory(resources);
        ResourceLoader.AssetFileDescriptorFactory assetFileDescriptorFactory = new ResourceLoader.AssetFileDescriptorFactory(resources);
        BitmapEncoder bitmapEncoder = new BitmapEncoder(arrayPool2);
        BitmapBytesTranscoder bitmapBytesTranscoder = new BitmapBytesTranscoder();
        GifDrawableBytesTranscoder gifDrawableBytesTranscoder = new GifDrawableBytesTranscoder();
        ResourceLoader.FileDescriptorFactory fileDescriptorFactory2 = fileDescriptorFactory;
        ResourceLoader.UriFactory uriFactory2 = uriFactory;
        ResourceLoader.AssetFileDescriptorFactory assetFileDescriptorFactory2 = assetFileDescriptorFactory;
        BitmapBytesTranscoder bitmapBytesTranscoder2 = bitmapBytesTranscoder;
        Context context3 = context;
        ContentResolver contentResolver = context.getContentResolver();
        GifDrawableBytesTranscoder gifDrawableBytesTranscoder2 = gifDrawableBytesTranscoder;
        this.j.b(ByteBuffer.class, new ByteBufferEncoder()).b(InputStream.class, new StreamEncoder(arrayPool2)).a(Registry.b, ByteBuffer.class, Bitmap.class, byteBufferBitmapDecoder).a(Registry.b, InputStream.class, Bitmap.class, streamBitmapDecoder).a(Registry.b, ParcelFileDescriptor.class, Bitmap.class, b2).a(Registry.b, AssetFileDescriptor.class, Bitmap.class, VideoDecoder.a(bitmapPool)).a(Bitmap.class, Bitmap.class, UnitModelLoader.Factory.b()).a(Registry.b, Bitmap.class, Bitmap.class, new UnitBitmapDecoder()).b(Bitmap.class, bitmapEncoder).a(Registry.c, ByteBuffer.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, byteBufferBitmapDecoder)).a(Registry.c, InputStream.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, streamBitmapDecoder)).a(Registry.c, ParcelFileDescriptor.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, b2)).b(BitmapDrawable.class, new BitmapDrawableEncoder(bitmapPool2, bitmapEncoder)).a(Registry.f4813a, InputStream.class, GifDrawable.class, new StreamGifDecoder(a2, byteBufferGifDecoder, arrayPool2)).a(Registry.f4813a, ByteBuffer.class, GifDrawable.class, byteBufferGifDecoder).b(GifDrawable.class, new GifDrawableEncoder()).a(GifDecoder.class, GifDecoder.class, UnitModelLoader.Factory.b()).a(Registry.b, GifDecoder.class, Bitmap.class, new GifFrameResourceDecoder(bitmapPool2)).a(Uri.class, Drawable.class, resourceDrawableDecoder).a(Uri.class, Bitmap.class, new ResourceBitmapDecoder(resourceDrawableDecoder, bitmapPool2)).a((DataRewinder.Factory<?>) new ByteBufferRewinder.Factory()).a(File.class, ByteBuffer.class, new ByteBufferFileLoader.Factory()).a(File.class, InputStream.class, new FileLoader.StreamFactory()).a(File.class, File.class, new FileDecoder()).a(File.class, ParcelFileDescriptor.class, new FileLoader.FileDescriptorFactory()).a(File.class, File.class, UnitModelLoader.Factory.b()).a((DataRewinder.Factory<?>) new InputStreamRewinder.Factory(arrayPool2)).a(Integer.TYPE, InputStream.class, streamFactory).a(Integer.TYPE, ParcelFileDescriptor.class, fileDescriptorFactory2).a(Integer.class, InputStream.class, streamFactory).a(Integer.class, ParcelFileDescriptor.class, fileDescriptorFactory2).a(Integer.class, Uri.class, uriFactory2).a(Integer.TYPE, AssetFileDescriptor.class, assetFileDescriptorFactory2).a(Integer.class, AssetFileDescriptor.class, assetFileDescriptorFactory2).a(Integer.TYPE, Uri.class, uriFactory2).a(String.class, InputStream.class, new DataUrlLoader.StreamFactory()).a(Uri.class, InputStream.class, new DataUrlLoader.StreamFactory()).a(String.class, InputStream.class, new StringLoader.StreamFactory()).a(String.class, ParcelFileDescriptor.class, new StringLoader.FileDescriptorFactory()).a(String.class, AssetFileDescriptor.class, new StringLoader.AssetFileDescriptorFactory()).a(Uri.class, InputStream.class, new HttpUriLoader.Factory()).a(Uri.class, InputStream.class, new AssetUriLoader.StreamFactory(context.getAssets())).a(Uri.class, ParcelFileDescriptor.class, new AssetUriLoader.FileDescriptorFactory(context.getAssets())).a(Uri.class, InputStream.class, new MediaStoreImageThumbLoader.Factory(context3)).a(Uri.class, InputStream.class, new MediaStoreVideoThumbLoader.Factory(context3)).a(Uri.class, InputStream.class, new UriLoader.StreamFactory(contentResolver)).a(Uri.class, ParcelFileDescriptor.class, new UriLoader.FileDescriptorFactory(contentResolver)).a(Uri.class, AssetFileDescriptor.class, new UriLoader.AssetFileDescriptorFactory(contentResolver)).a(Uri.class, InputStream.class, new UrlUriLoader.StreamFactory()).a(URL.class, InputStream.class, new UrlLoader.StreamFactory()).a(Uri.class, File.class, new MediaStoreFileLoader.Factory(context3)).a(GlideUrl.class, InputStream.class, new HttpGlideUrlLoader.Factory()).a(byte[].class, ByteBuffer.class, new ByteArrayLoader.ByteBufferFactory()).a(byte[].class, InputStream.class, new ByteArrayLoader.StreamFactory()).a(Uri.class, Uri.class, UnitModelLoader.Factory.b()).a(Drawable.class, Drawable.class, UnitModelLoader.Factory.b()).a(Drawable.class, Drawable.class, new UnitDrawableDecoder()).a(Bitmap.class, BitmapDrawable.class, new BitmapDrawableTranscoder(resources)).a(Bitmap.class, byte[].class, bitmapBytesTranscoder2).a(Drawable.class, byte[].class, new DrawableBytesTranscoder(bitmapPool2, bitmapBytesTranscoder2, gifDrawableBytesTranscoder2)).a(GifDrawable.class, byte[].class, gifDrawableBytesTranscoder2);
        this.i = new GlideContext(context, arrayPool, this.j, new ImageViewTargetFactory(), requestOptions, map, list, engine, z, i2);
    }

    @NonNull
    public BitmapPool b() {
        return this.f;
    }

    @NonNull
    public ArrayPool c() {
        return this.k;
    }

    @NonNull
    public Context d() {
        return this.i.getBaseContext();
    }

    /* access modifiers changed from: package-private */
    public ConnectivityMonitorFactory e() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public GlideContext f() {
        return this.i;
    }

    public void a(@NonNull PreFillType.Builder... builderArr) {
        this.h.a(builderArr);
    }

    public void g() {
        Util.a();
        this.g.c();
        this.f.b();
        this.k.a();
    }

    public void a(int i2) {
        Util.a();
        this.g.a(i2);
        this.f.a(i2);
        this.k.a(i2);
    }

    public void h() {
        Util.b();
        this.e.a();
    }

    @NonNull
    public RequestManagerRetriever i() {
        return this.l;
    }

    @NonNull
    public MemoryCategory a(@NonNull MemoryCategory memoryCategory) {
        Util.a();
        this.g.a(memoryCategory.getMultiplier());
        this.f.a(memoryCategory.getMultiplier());
        MemoryCategory memoryCategory2 = this.o;
        this.o = memoryCategory;
        return memoryCategory2;
    }

    @NonNull
    private static RequestManagerRetriever f(@Nullable Context context) {
        Preconditions.a(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return b(context).i();
    }

    @NonNull
    public static RequestManager c(@NonNull Context context) {
        return f(context).a(context);
    }

    @NonNull
    public static RequestManager a(@NonNull Activity activity) {
        return f(activity).a(activity);
    }

    @NonNull
    public static RequestManager a(@NonNull FragmentActivity fragmentActivity) {
        return f(fragmentActivity).a(fragmentActivity);
    }

    @NonNull
    public static RequestManager a(@NonNull Fragment fragment) {
        return f(fragment.getActivity()).a(fragment);
    }

    @Deprecated
    @NonNull
    public static RequestManager a(@NonNull android.app.Fragment fragment) {
        return f(fragment.getActivity()).a(fragment);
    }

    @NonNull
    public static RequestManager a(@NonNull View view) {
        return f(view.getContext()).a(view);
    }

    @NonNull
    public Registry j() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public boolean a(@NonNull Target<?> target) {
        synchronized (this.n) {
            for (RequestManager b2 : this.n) {
                if (b2.b(target)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(RequestManager requestManager) {
        synchronized (this.n) {
            if (!this.n.contains(requestManager)) {
                this.n.add(requestManager);
            } else {
                throw new IllegalStateException("Cannot register already registered manager");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(RequestManager requestManager) {
        synchronized (this.n) {
            if (this.n.contains(requestManager)) {
                this.n.remove(requestManager);
            } else {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
        }
    }

    public void onTrimMemory(int i2) {
        a(i2);
    }

    public void onLowMemory() {
        g();
    }
}
