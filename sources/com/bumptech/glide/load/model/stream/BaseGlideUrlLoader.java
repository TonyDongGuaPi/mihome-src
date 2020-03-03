package com.bumptech.glide.load.model.stream;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class BaseGlideUrlLoader<Model> implements ModelLoader<Model, InputStream> {

    /* renamed from: a  reason: collision with root package name */
    private final ModelLoader<GlideUrl, InputStream> f4981a;
    @Nullable
    private final ModelCache<Model, GlideUrl> b;

    /* access modifiers changed from: protected */
    public abstract String b(Model model, int i, int i2, Options options);

    protected BaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> modelLoader) {
        this(modelLoader, (ModelCache) null);
    }

    protected BaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> modelLoader, @Nullable ModelCache<Model, GlideUrl> modelCache) {
        this.f4981a = modelLoader;
        this.b = modelCache;
    }

    @Nullable
    public ModelLoader.LoadData<InputStream> a(@NonNull Model model, int i, int i2, @NonNull Options options) {
        GlideUrl a2 = this.b != null ? this.b.a(model, i, i2) : null;
        if (a2 == null) {
            String b2 = b(model, i, i2, options);
            if (TextUtils.isEmpty(b2)) {
                return null;
            }
            GlideUrl glideUrl = new GlideUrl(b2, d(model, i, i2, options));
            if (this.b != null) {
                this.b.a(model, i, i2, glideUrl);
            }
            a2 = glideUrl;
        }
        List<String> c = c(model, i, i2, options);
        ModelLoader.LoadData<InputStream> a3 = this.f4981a.a(a2, i, i2, options);
        return (a3 == null || c.isEmpty()) ? a3 : new ModelLoader.LoadData<>(a3.f4958a, a(c), a3.c);
    }

    private static List<Key> a(Collection<String> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (String glideUrl : collection) {
            arrayList.add(new GlideUrl(glideUrl));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public List<String> c(Model model, int i, int i2, Options options) {
        return Collections.emptyList();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Headers d(Model model, int i, int i2, Options options) {
        return Headers.b;
    }
}
