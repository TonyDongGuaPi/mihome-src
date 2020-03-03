package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import android.support.v4.util.Pools;
import android.util.Log;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.util.Preconditions;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DecodePath<DataType, ResourceType, Transcode> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4864a = "DecodePath";
    private final Class<DataType> b;
    private final List<? extends ResourceDecoder<DataType, ResourceType>> c;
    private final ResourceTranscoder<ResourceType, Transcode> d;
    private final Pools.Pool<List<Throwable>> e;
    private final String f;

    interface DecodeCallback<ResourceType> {
        @NonNull
        Resource<ResourceType> a(@NonNull Resource<ResourceType> resource);
    }

    public DecodePath(Class<DataType> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<? extends ResourceDecoder<DataType, ResourceType>> list, ResourceTranscoder<ResourceType, Transcode> resourceTranscoder, Pools.Pool<List<Throwable>> pool) {
        this.b = cls;
        this.c = list;
        this.d = resourceTranscoder;
        this.e = pool;
        this.f = "Failed DecodePath{" + cls.getSimpleName() + "->" + cls2.getSimpleName() + "->" + cls3.getSimpleName() + "}";
    }

    public Resource<Transcode> a(DataRewinder<DataType> dataRewinder, int i, int i2, @NonNull Options options, DecodeCallback<ResourceType> decodeCallback) throws GlideException {
        return this.d.a(decodeCallback.a(a(dataRewinder, i, i2, options)), options);
    }

    @NonNull
    private Resource<ResourceType> a(DataRewinder<DataType> dataRewinder, int i, int i2, @NonNull Options options) throws GlideException {
        List list = (List) Preconditions.a(this.e.acquire());
        try {
            return a(dataRewinder, i, i2, options, (List<Throwable>) list);
        } finally {
            this.e.release(list);
        }
    }

    @NonNull
    private Resource<ResourceType> a(DataRewinder<DataType> dataRewinder, int i, int i2, @NonNull Options options, List<Throwable> list) throws GlideException {
        int size = this.c.size();
        Resource<ResourceType> resource = null;
        for (int i3 = 0; i3 < size; i3++) {
            ResourceDecoder resourceDecoder = (ResourceDecoder) this.c.get(i3);
            try {
                if (resourceDecoder.a(dataRewinder.a(), options)) {
                    resource = resourceDecoder.a(dataRewinder.a(), i, i2, options);
                }
            } catch (IOException | OutOfMemoryError | RuntimeException e2) {
                if (Log.isLoggable(f4864a, 2)) {
                    Log.v(f4864a, "Failed to decode data for " + resourceDecoder, e2);
                }
                list.add(e2);
            }
            if (resource != null) {
                break;
            }
        }
        if (resource != null) {
            return resource;
        }
        throw new GlideException(this.f, (List<Throwable>) new ArrayList(list));
    }

    public String toString() {
        return "DecodePath{ dataClass=" + this.b + ", decoders=" + this.c + ", transcoder=" + this.d + Operators.BLOCK_END;
    }
}
