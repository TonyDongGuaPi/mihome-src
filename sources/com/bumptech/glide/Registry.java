package com.bumptech.glide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.data.DataRewinderRegistry;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.load.engine.LoadPath;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.ModelLoaderRegistry;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.load.resource.transcode.TranscoderRegistry;
import com.bumptech.glide.provider.EncoderRegistry;
import com.bumptech.glide.provider.ImageHeaderParserRegistry;
import com.bumptech.glide.provider.LoadPathCache;
import com.bumptech.glide.provider.ModelToResourceClassCache;
import com.bumptech.glide.provider.ResourceDecoderRegistry;
import com.bumptech.glide.provider.ResourceEncoderRegistry;
import com.bumptech.glide.util.pool.FactoryPools;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Registry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4813a = "Gif";
    public static final String b = "Bitmap";
    public static final String c = "BitmapDrawable";
    private static final String d = "legacy_prepend_all";
    private static final String e = "legacy_append";
    private final ModelLoaderRegistry f = new ModelLoaderRegistry(this.o);
    private final EncoderRegistry g = new EncoderRegistry();
    private final ResourceDecoderRegistry h = new ResourceDecoderRegistry();
    private final ResourceEncoderRegistry i = new ResourceEncoderRegistry();
    private final DataRewinderRegistry j = new DataRewinderRegistry();
    private final TranscoderRegistry k = new TranscoderRegistry();
    private final ImageHeaderParserRegistry l = new ImageHeaderParserRegistry();
    private final ModelToResourceClassCache m = new ModelToResourceClassCache();
    private final LoadPathCache n = new LoadPathCache();
    private final Pools.Pool<List<Throwable>> o = FactoryPools.a();

    public Registry() {
        a((List<String>) Arrays.asList(new String[]{f4813a, b, c}));
    }

    @Deprecated
    @NonNull
    public <Data> Registry a(@NonNull Class<Data> cls, @NonNull Encoder<Data> encoder) {
        return b(cls, encoder);
    }

    @NonNull
    public <Data> Registry b(@NonNull Class<Data> cls, @NonNull Encoder<Data> encoder) {
        this.g.a(cls, encoder);
        return this;
    }

    @NonNull
    public <Data> Registry c(@NonNull Class<Data> cls, @NonNull Encoder<Data> encoder) {
        this.g.b(cls, encoder);
        return this;
    }

    @NonNull
    public <Data, TResource> Registry a(@NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull ResourceDecoder<Data, TResource> resourceDecoder) {
        a(e, cls, cls2, resourceDecoder);
        return this;
    }

    @NonNull
    public <Data, TResource> Registry a(@NonNull String str, @NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull ResourceDecoder<Data, TResource> resourceDecoder) {
        this.h.a(str, resourceDecoder, cls, cls2);
        return this;
    }

    @NonNull
    public <Data, TResource> Registry b(@NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull ResourceDecoder<Data, TResource> resourceDecoder) {
        b(d, cls, cls2, resourceDecoder);
        return this;
    }

    @NonNull
    public <Data, TResource> Registry b(@NonNull String str, @NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull ResourceDecoder<Data, TResource> resourceDecoder) {
        this.h.b(str, resourceDecoder, cls, cls2);
        return this;
    }

    @NonNull
    public final Registry a(@NonNull List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        arrayList.add(0, d);
        arrayList.add(e);
        this.h.a((List<String>) arrayList);
        return this;
    }

    @Deprecated
    @NonNull
    public <TResource> Registry a(@NonNull Class<TResource> cls, @NonNull ResourceEncoder<TResource> resourceEncoder) {
        return b(cls, resourceEncoder);
    }

    @NonNull
    public <TResource> Registry b(@NonNull Class<TResource> cls, @NonNull ResourceEncoder<TResource> resourceEncoder) {
        this.i.a(cls, resourceEncoder);
        return this;
    }

    @NonNull
    public <TResource> Registry c(@NonNull Class<TResource> cls, @NonNull ResourceEncoder<TResource> resourceEncoder) {
        this.i.b(cls, resourceEncoder);
        return this;
    }

    @NonNull
    public Registry a(@NonNull DataRewinder.Factory<?> factory) {
        this.j.a(factory);
        return this;
    }

    @NonNull
    public <TResource, Transcode> Registry a(@NonNull Class<TResource> cls, @NonNull Class<Transcode> cls2, @NonNull ResourceTranscoder<TResource, Transcode> resourceTranscoder) {
        this.k.a(cls, cls2, resourceTranscoder);
        return this;
    }

    @NonNull
    public Registry a(@NonNull ImageHeaderParser imageHeaderParser) {
        this.l.a(imageHeaderParser);
        return this;
    }

    @NonNull
    public <Model, Data> Registry a(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<Model, Data> modelLoaderFactory) {
        this.f.a(cls, cls2, modelLoaderFactory);
        return this;
    }

    @NonNull
    public <Model, Data> Registry b(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<Model, Data> modelLoaderFactory) {
        this.f.b(cls, cls2, modelLoaderFactory);
        return this;
    }

    @NonNull
    public <Model, Data> Registry c(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        this.f.c(cls, cls2, modelLoaderFactory);
        return this;
    }

    @Nullable
    public <Data, TResource, Transcode> LoadPath<Data, TResource, Transcode> a(@NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull Class<Transcode> cls3) {
        LoadPath<Data, TResource, Transcode> a2 = this.n.a(cls, cls2, cls3);
        if (this.n.a(a2)) {
            return null;
        }
        if (a2 == null) {
            List<DecodePath<Data, TResource, Transcode>> c2 = c(cls, cls2, cls3);
            if (c2.isEmpty()) {
                a2 = null;
            } else {
                a2 = new LoadPath<>(cls, cls2, cls3, c2, this.o);
            }
            this.n.a(cls, cls2, cls3, a2);
        }
        return a2;
    }

    @NonNull
    private <Data, TResource, Transcode> List<DecodePath<Data, TResource, Transcode>> c(@NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull Class<Transcode> cls3) {
        ArrayList arrayList = new ArrayList();
        for (Class next : this.h.b(cls, cls2)) {
            for (Class next2 : this.k.b(next, cls3)) {
                arrayList.add(new DecodePath(cls, next, next2, this.h.a(cls, next), this.k.a(next, next2), this.o));
            }
        }
        return arrayList;
    }

    @NonNull
    public <Model, TResource, Transcode> List<Class<?>> b(@NonNull Class<Model> cls, @NonNull Class<TResource> cls2, @NonNull Class<Transcode> cls3) {
        List<Class<?>> a2 = this.m.a(cls, cls2, cls3);
        if (a2 == null) {
            a2 = new ArrayList<>();
            for (Class<?> b2 : this.f.a((Class<?>) cls)) {
                for (Class next : this.h.b(b2, cls2)) {
                    if (!this.k.b(next, cls3).isEmpty() && !a2.contains(next)) {
                        a2.add(next);
                    }
                }
            }
            this.m.a(cls, cls2, cls3, Collections.unmodifiableList(a2));
        }
        return a2;
    }

    public boolean a(@NonNull Resource<?> resource) {
        return this.i.a(resource.c()) != null;
    }

    @NonNull
    public <X> ResourceEncoder<X> b(@NonNull Resource<X> resource) throws NoResultEncoderAvailableException {
        ResourceEncoder<X> a2 = this.i.a(resource.c());
        if (a2 != null) {
            return a2;
        }
        throw new NoResultEncoderAvailableException(resource.c());
    }

    @NonNull
    public <X> Encoder<X> a(@NonNull X x) throws NoSourceEncoderAvailableException {
        Encoder<X> a2 = this.g.a(x.getClass());
        if (a2 != null) {
            return a2;
        }
        throw new NoSourceEncoderAvailableException(x.getClass());
    }

    @NonNull
    public <X> DataRewinder<X> b(@NonNull X x) {
        return this.j.a(x);
    }

    @NonNull
    public <Model> List<ModelLoader<Model, ?>> c(@NonNull Model model) {
        List<ModelLoader<Model, ?>> a2 = this.f.a(model);
        if (!a2.isEmpty()) {
            return a2;
        }
        throw new NoModelLoaderAvailableException(model);
    }

    @NonNull
    public List<ImageHeaderParser> a() {
        List<ImageHeaderParser> a2 = this.l.a();
        if (!a2.isEmpty()) {
            return a2;
        }
        throw new NoImageHeaderParserException();
    }

    public static class NoModelLoaderAvailableException extends MissingComponentException {
        public NoModelLoaderAvailableException(@NonNull Object obj) {
            super("Failed to find any ModelLoaders for model: " + obj);
        }

        public NoModelLoaderAvailableException(@NonNull Class<?> cls, @NonNull Class<?> cls2) {
            super("Failed to find any ModelLoaders for model: " + cls + " and data: " + cls2);
        }
    }

    public static class NoResultEncoderAvailableException extends MissingComponentException {
        public NoResultEncoderAvailableException(@NonNull Class<?> cls) {
            super("Failed to find result encoder for resource class: " + cls + ", you may need to consider registering a new Encoder for the requested type or DiskCacheStrategy.DATA/DiskCacheStrategy.NONE if caching your transformed resource is unnecessary.");
        }
    }

    public static class NoSourceEncoderAvailableException extends MissingComponentException {
        public NoSourceEncoderAvailableException(@NonNull Class<?> cls) {
            super("Failed to find source encoder for data class: " + cls);
        }
    }

    public static class MissingComponentException extends RuntimeException {
        public MissingComponentException(@NonNull String str) {
            super(str);
        }
    }

    public static final class NoImageHeaderParserException extends MissingComponentException {
        public NoImageHeaderParserException() {
            super("Failed to find image header parser.");
        }
    }
}
