package com.bumptech.glide.load.engine;

import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.UnitTransformation;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class DecodeHelper<Transcode> {

    /* renamed from: a  reason: collision with root package name */
    private final List<ModelLoader.LoadData<?>> f4858a = new ArrayList();
    private final List<Key> b = new ArrayList();
    private GlideContext c;
    private Object d;
    private int e;
    private int f;
    private Class<?> g;
    private DecodeJob.DiskCacheProvider h;
    private Options i;
    private Map<Class<?>, Transformation<?>> j;
    private Class<Transcode> k;
    private boolean l;
    private boolean m;
    private Key n;
    private Priority o;
    private DiskCacheStrategy p;
    private boolean q;
    private boolean r;

    DecodeHelper() {
    }

    /* access modifiers changed from: package-private */
    public <R> void a(GlideContext glideContext, Object obj, Key key, int i2, int i3, DiskCacheStrategy diskCacheStrategy, Class<?> cls, Class<R> cls2, Priority priority, Options options, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, DecodeJob.DiskCacheProvider diskCacheProvider) {
        this.c = glideContext;
        this.d = obj;
        this.n = key;
        this.e = i2;
        this.f = i3;
        this.p = diskCacheStrategy;
        this.g = cls;
        this.h = diskCacheProvider;
        this.k = cls2;
        this.o = priority;
        this.i = options;
        this.j = map;
        this.q = z;
        this.r = z2;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.c = null;
        this.d = null;
        this.n = null;
        this.g = null;
        this.k = null;
        this.i = null;
        this.o = null;
        this.j = null;
        this.p = null;
        this.f4858a.clear();
        this.l = false;
        this.b.clear();
        this.m = false;
    }

    /* access modifiers changed from: package-private */
    public DiskCache b() {
        return this.h.a();
    }

    /* access modifiers changed from: package-private */
    public DiskCacheStrategy c() {
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public Priority d() {
        return this.o;
    }

    /* access modifiers changed from: package-private */
    public Options e() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public Key f() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public int g() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public int h() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public ArrayPool i() {
        return this.c.getArrayPool();
    }

    /* access modifiers changed from: package-private */
    public Class<?> j() {
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public Class<?> k() {
        return this.d.getClass();
    }

    /* access modifiers changed from: package-private */
    public List<Class<?>> l() {
        return this.c.getRegistry().b(this.d.getClass(), this.g, this.k);
    }

    /* access modifiers changed from: package-private */
    public boolean a(Class<?> cls) {
        return b(cls) != null;
    }

    /* access modifiers changed from: package-private */
    public <Data> LoadPath<Data, ?, Transcode> b(Class<Data> cls) {
        return this.c.getRegistry().a(cls, this.g, this.k);
    }

    /* access modifiers changed from: package-private */
    public boolean m() {
        return this.r;
    }

    /* access modifiers changed from: package-private */
    public <Z> Transformation<Z> c(Class<Z> cls) {
        Transformation<Z> transformation = this.j.get(cls);
        if (transformation == null) {
            Iterator<Map.Entry<Class<?>, Transformation<?>>> it = this.j.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                if (((Class) next.getKey()).isAssignableFrom(cls)) {
                    transformation = (Transformation) next.getValue();
                    break;
                }
            }
        }
        if (transformation != null) {
            return transformation;
        }
        if (!this.j.isEmpty() || !this.q) {
            return UnitTransformation.a();
        }
        throw new IllegalArgumentException("Missing transformation for " + cls + ". If you wish to ignore unknown resource types, use the optional transformation methods.");
    }

    /* access modifiers changed from: package-private */
    public boolean a(Resource<?> resource) {
        return this.c.getRegistry().a(resource);
    }

    /* access modifiers changed from: package-private */
    public <Z> ResourceEncoder<Z> b(Resource<Z> resource) {
        return this.c.getRegistry().b(resource);
    }

    /* access modifiers changed from: package-private */
    public List<ModelLoader<File, ?>> a(File file) throws Registry.NoModelLoaderAvailableException {
        return this.c.getRegistry().c(file);
    }

    /* access modifiers changed from: package-private */
    public boolean a(Key key) {
        List<ModelLoader.LoadData<?>> n2 = n();
        int size = n2.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (n2.get(i2).f4958a.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public List<ModelLoader.LoadData<?>> n() {
        if (!this.l) {
            this.l = true;
            this.f4858a.clear();
            List c2 = this.c.getRegistry().c(this.d);
            int size = c2.size();
            for (int i2 = 0; i2 < size; i2++) {
                ModelLoader.LoadData a2 = ((ModelLoader) c2.get(i2)).a(this.d, this.e, this.f, this.i);
                if (a2 != null) {
                    this.f4858a.add(a2);
                }
            }
        }
        return this.f4858a;
    }

    /* access modifiers changed from: package-private */
    public List<Key> o() {
        if (!this.m) {
            this.m = true;
            this.b.clear();
            List<ModelLoader.LoadData<?>> n2 = n();
            int size = n2.size();
            for (int i2 = 0; i2 < size; i2++) {
                ModelLoader.LoadData loadData = n2.get(i2);
                if (!this.b.contains(loadData.f4958a)) {
                    this.b.add(loadData.f4958a);
                }
                for (int i3 = 0; i3 < loadData.b.size(); i3++) {
                    if (!this.b.contains(loadData.b.get(i3))) {
                        this.b.add(loadData.b.get(i3));
                    }
                }
            }
        }
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public <X> Encoder<X> a(X x) throws Registry.NoSourceEncoderAvailableException {
        return this.c.getRegistry().a(x);
    }
}
