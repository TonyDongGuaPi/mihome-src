package com.bumptech.glide.load.engine;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.util.Pools;
import android.util.Log;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.GlideTrace;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DecodeJob<R> implements DataFetcherGenerator.FetcherReadyCallback, FactoryPools.Poolable, Comparable<DecodeJob<?>>, Runnable {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4859a = "DecodeJob";
    private Object A;
    private DataSource B;
    private DataFetcher<?> C;
    private volatile DataFetcherGenerator D;
    private volatile boolean E;
    private volatile boolean F;
    private final DecodeHelper<R> b = new DecodeHelper<>();
    private final List<Throwable> c = new ArrayList();
    private final StateVerifier d = StateVerifier.a();
    private final DiskCacheProvider e;
    private final Pools.Pool<DecodeJob<?>> f;
    private final DeferredEncodeManager<?> g = new DeferredEncodeManager<>();
    private final ReleaseManager h = new ReleaseManager();
    private GlideContext i;
    private Key j;
    private Priority k;
    private EngineKey l;
    private int m;
    private int n;
    private DiskCacheStrategy o;
    private Options p;
    private Callback<R> q;
    private int r;
    private Stage s;
    private RunReason t;
    private long u;
    private boolean v;
    private Object w;
    private Thread x;
    private Key y;
    private Key z;

    interface Callback<R> {
        void a(DecodeJob<?> decodeJob);

        void a(GlideException glideException);

        void a(Resource<R> resource, DataSource dataSource);
    }

    interface DiskCacheProvider {
        DiskCache a();
    }

    private enum RunReason {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    private enum Stage {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    DecodeJob(DiskCacheProvider diskCacheProvider, Pools.Pool<DecodeJob<?>> pool) {
        this.e = diskCacheProvider;
        this.f = pool;
    }

    /* access modifiers changed from: package-private */
    public DecodeJob<R> a(GlideContext glideContext, Object obj, EngineKey engineKey, Key key, int i2, int i3, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z2, boolean z3, boolean z4, Options options, Callback<R> callback, int i4) {
        this.b.a(glideContext, obj, key, i2, i3, diskCacheStrategy, cls, cls2, priority, options, map, z2, z3, this.e);
        this.i = glideContext;
        this.j = key;
        this.k = priority;
        this.l = engineKey;
        this.m = i2;
        this.n = i3;
        this.o = diskCacheStrategy;
        this.v = z4;
        this.p = options;
        this.q = callback;
        this.r = i4;
        this.t = RunReason.INITIALIZE;
        this.w = obj;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        Stage a2 = a(Stage.INITIALIZE);
        return a2 == Stage.RESOURCE_CACHE || a2 == Stage.DATA_CACHE;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2) {
        if (this.h.a(z2)) {
            g();
        }
    }

    private void e() {
        if (this.h.a()) {
            g();
        }
    }

    private void f() {
        if (this.h.b()) {
            g();
        }
    }

    private void g() {
        this.h.c();
        this.g.b();
        this.b.a();
        this.E = false;
        this.i = null;
        this.j = null;
        this.p = null;
        this.k = null;
        this.l = null;
        this.q = null;
        this.s = null;
        this.D = null;
        this.x = null;
        this.y = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.u = 0;
        this.F = false;
        this.w = null;
        this.c.clear();
        this.f.release(this);
    }

    /* renamed from: a */
    public int compareTo(@NonNull DecodeJob<?> decodeJob) {
        int h2 = h() - decodeJob.h();
        return h2 == 0 ? this.r - decodeJob.r : h2;
    }

    private int h() {
        return this.k.ordinal();
    }

    public void b() {
        this.F = true;
        DataFetcherGenerator dataFetcherGenerator = this.D;
        if (dataFetcherGenerator != null) {
            dataFetcherGenerator.b();
        }
    }

    public void run() {
        GlideTrace.a("DecodeJob#run(model=%s)", this.w);
        DataFetcher<?> dataFetcher = this.C;
        try {
            if (this.F) {
                l();
                if (dataFetcher != null) {
                    dataFetcher.b();
                }
                GlideTrace.a();
                return;
            }
            i();
            if (dataFetcher != null) {
                dataFetcher.b();
            }
            GlideTrace.a();
        } catch (CallbackException e2) {
            throw e2;
        } catch (Throwable th) {
            if (dataFetcher != null) {
                dataFetcher.b();
            }
            GlideTrace.a();
            throw th;
        }
    }

    private void i() {
        switch (this.t) {
            case INITIALIZE:
                this.s = a(Stage.INITIALIZE);
                this.D = j();
                k();
                return;
            case SWITCH_TO_SOURCE_SERVICE:
                k();
                return;
            case DECODE_DATA:
                n();
                return;
            default:
                throw new IllegalStateException("Unrecognized run reason: " + this.t);
        }
    }

    private DataFetcherGenerator j() {
        switch (this.s) {
            case RESOURCE_CACHE:
                return new ResourceCacheGenerator(this.b, this);
            case DATA_CACHE:
                return new DataCacheGenerator(this.b, this);
            case SOURCE:
                return new SourceGenerator(this.b, this);
            case FINISHED:
                return null;
            default:
                throw new IllegalStateException("Unrecognized stage: " + this.s);
        }
    }

    private void k() {
        this.x = Thread.currentThread();
        this.u = LogTime.a();
        boolean z2 = false;
        while (!this.F && this.D != null && !(z2 = this.D.a())) {
            this.s = a(this.s);
            this.D = j();
            if (this.s == Stage.SOURCE) {
                c();
                return;
            }
        }
        if ((this.s == Stage.FINISHED || this.F) && !z2) {
            l();
        }
    }

    private void l() {
        m();
        this.q.a(new GlideException("Failed to load resource", (List<Throwable>) new ArrayList(this.c)));
        f();
    }

    private void a(Resource<R> resource, DataSource dataSource) {
        m();
        this.q.a(resource, dataSource);
    }

    private void m() {
        this.d.b();
        if (this.E) {
            throw new IllegalStateException("Already notified", this.c.isEmpty() ? null : this.c.get(this.c.size() - 1));
        }
        this.E = true;
    }

    private Stage a(Stage stage) {
        switch (stage) {
            case RESOURCE_CACHE:
                return this.o.b() ? Stage.DATA_CACHE : a(Stage.DATA_CACHE);
            case DATA_CACHE:
                return this.v ? Stage.FINISHED : Stage.SOURCE;
            case SOURCE:
            case FINISHED:
                return Stage.FINISHED;
            case INITIALIZE:
                return this.o.a() ? Stage.RESOURCE_CACHE : a(Stage.RESOURCE_CACHE);
            default:
                throw new IllegalArgumentException("Unrecognized stage: " + stage);
        }
    }

    public void c() {
        this.t = RunReason.SWITCH_TO_SOURCE_SERVICE;
        this.q.a((DecodeJob<?>) this);
    }

    public void a(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2) {
        this.y = key;
        this.A = obj;
        this.C = dataFetcher;
        this.B = dataSource;
        this.z = key2;
        if (Thread.currentThread() != this.x) {
            this.t = RunReason.DECODE_DATA;
            this.q.a((DecodeJob<?>) this);
            return;
        }
        GlideTrace.a("DecodeJob.decodeFromRetrievedData");
        try {
            n();
        } finally {
            GlideTrace.a();
        }
    }

    public void a(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource) {
        dataFetcher.b();
        GlideException glideException = new GlideException("Fetching data failed", (Throwable) exc);
        glideException.setLoggingDetails(key, dataSource, dataFetcher.a());
        this.c.add(glideException);
        if (Thread.currentThread() != this.x) {
            this.t = RunReason.SWITCH_TO_SOURCE_SERVICE;
            this.q.a((DecodeJob<?>) this);
            return;
        }
        k();
    }

    private void n() {
        if (Log.isLoggable(f4859a, 2)) {
            long j2 = this.u;
            a("Retrieved data", j2, "data: " + this.A + ", cache key: " + this.y + ", fetcher: " + this.C);
        }
        Resource<R> resource = null;
        try {
            resource = a(this.C, this.A, this.B);
        } catch (GlideException e2) {
            e2.setLoggingDetails(this.z, this.B);
            this.c.add(e2);
        }
        if (resource != null) {
            b(resource, this.B);
        } else {
            k();
        }
    }

    private void b(Resource<R> resource, DataSource dataSource) {
        if (resource instanceof Initializable) {
            ((Initializable) resource).a();
        }
        LockedResource<R> lockedResource = null;
        LockedResource<R> lockedResource2 = resource;
        if (this.g.a()) {
            LockedResource<R> a2 = LockedResource.a(resource);
            lockedResource = a2;
            lockedResource2 = a2;
        }
        a(lockedResource2, dataSource);
        this.s = Stage.ENCODE;
        try {
            if (this.g.a()) {
                this.g.a(this.e, this.p);
            }
            e();
        } finally {
            if (lockedResource != null) {
                lockedResource.a();
            }
        }
    }

    private <Data> Resource<R> a(DataFetcher<?> dataFetcher, Data data, DataSource dataSource) throws GlideException {
        if (data == null) {
            dataFetcher.b();
            return null;
        }
        try {
            long a2 = LogTime.a();
            Resource<R> a3 = a(data, dataSource);
            if (Log.isLoggable(f4859a, 2)) {
                a("Decoded result " + a3, a2);
            }
            return a3;
        } finally {
            dataFetcher.b();
        }
    }

    private <Data> Resource<R> a(Data data, DataSource dataSource) throws GlideException {
        return a(data, dataSource, this.b.b(data.getClass()));
    }

    @NonNull
    private Options a(DataSource dataSource) {
        Options options = this.p;
        if (Build.VERSION.SDK_INT < 26) {
            return options;
        }
        boolean z2 = dataSource == DataSource.RESOURCE_DISK_CACHE || this.b.m();
        Boolean bool = (Boolean) options.a(Downsampler.e);
        if (bool != null && (!bool.booleanValue() || z2)) {
            return options;
        }
        Options options2 = new Options();
        options2.a(this.p);
        options2.a(Downsampler.e, Boolean.valueOf(z2));
        return options2;
    }

    private <Data, ResourceType> Resource<R> a(Data data, DataSource dataSource, LoadPath<Data, ResourceType, R> loadPath) throws GlideException {
        Options a2 = a(dataSource);
        DataRewinder b2 = this.i.getRegistry().b(data);
        try {
            return loadPath.a(b2, a2, this.m, this.n, new DecodeCallback(dataSource));
        } finally {
            b2.b();
        }
    }

    private void a(String str, long j2) {
        a(str, j2, (String) null);
    }

    private void a(String str, long j2, String str2) {
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" in ");
        sb.append(LogTime.a(j2));
        sb.append(", load key: ");
        sb.append(this.l);
        if (str2 != null) {
            str3 = ", " + str2;
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append(", thread: ");
        sb.append(Thread.currentThread().getName());
        Log.v(f4859a, sb.toString());
    }

    @NonNull
    public StateVerifier d_() {
        return this.d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: com.bumptech.glide.load.engine.DataCacheKey} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: com.bumptech.glide.load.engine.ResourceCacheKey} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: com.bumptech.glide.load.engine.ResourceCacheKey} */
    /* JADX WARNING: type inference failed for: r12v5, types: [com.bumptech.glide.load.Key] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Z> com.bumptech.glide.load.engine.Resource<Z> a(com.bumptech.glide.load.DataSource r12, @android.support.annotation.NonNull com.bumptech.glide.load.engine.Resource<Z> r13) {
        /*
            r11 = this;
            java.lang.Object r0 = r13.d()
            java.lang.Class r8 = r0.getClass()
            com.bumptech.glide.load.DataSource r0 = com.bumptech.glide.load.DataSource.RESOURCE_DISK_CACHE
            r1 = 0
            if (r12 == r0) goto L_0x0020
            com.bumptech.glide.load.engine.DecodeHelper<R> r0 = r11.b
            com.bumptech.glide.load.Transformation r0 = r0.c(r8)
            com.bumptech.glide.GlideContext r2 = r11.i
            int r3 = r11.m
            int r4 = r11.n
            com.bumptech.glide.load.engine.Resource r2 = r0.a(r2, r13, r3, r4)
            r7 = r0
            r0 = r2
            goto L_0x0022
        L_0x0020:
            r0 = r13
            r7 = r1
        L_0x0022:
            boolean r2 = r13.equals(r0)
            if (r2 != 0) goto L_0x002b
            r13.f()
        L_0x002b:
            com.bumptech.glide.load.engine.DecodeHelper<R> r13 = r11.b
            boolean r13 = r13.a((com.bumptech.glide.load.engine.Resource<?>) r0)
            if (r13 == 0) goto L_0x0041
            com.bumptech.glide.load.engine.DecodeHelper<R> r13 = r11.b
            com.bumptech.glide.load.ResourceEncoder r1 = r13.b(r0)
            com.bumptech.glide.load.Options r13 = r11.p
            com.bumptech.glide.load.EncodeStrategy r13 = r1.a(r13)
        L_0x003f:
            r10 = r1
            goto L_0x0044
        L_0x0041:
            com.bumptech.glide.load.EncodeStrategy r13 = com.bumptech.glide.load.EncodeStrategy.NONE
            goto L_0x003f
        L_0x0044:
            com.bumptech.glide.load.engine.DecodeHelper<R> r1 = r11.b
            com.bumptech.glide.load.Key r2 = r11.y
            boolean r1 = r1.a((com.bumptech.glide.load.Key) r2)
            r1 = r1 ^ 1
            com.bumptech.glide.load.engine.DiskCacheStrategy r2 = r11.o
            boolean r12 = r2.a(r1, r12, r13)
            if (r12 == 0) goto L_0x00b2
            if (r10 == 0) goto L_0x00a4
            int[] r12 = com.bumptech.glide.load.engine.DecodeJob.AnonymousClass1.c
            int r1 = r13.ordinal()
            r12 = r12[r1]
            switch(r12) {
                case 1: goto L_0x0091;
                case 2: goto L_0x007a;
                default: goto L_0x0063;
            }
        L_0x0063:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unknown strategy: "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r13 = r0.toString()
            r12.<init>(r13)
            throw r12
        L_0x007a:
            com.bumptech.glide.load.engine.ResourceCacheKey r12 = new com.bumptech.glide.load.engine.ResourceCacheKey
            com.bumptech.glide.load.engine.DecodeHelper<R> r13 = r11.b
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r2 = r13.i()
            com.bumptech.glide.load.Key r3 = r11.y
            com.bumptech.glide.load.Key r4 = r11.j
            int r5 = r11.m
            int r6 = r11.n
            com.bumptech.glide.load.Options r9 = r11.p
            r1 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x009a
        L_0x0091:
            com.bumptech.glide.load.engine.DataCacheKey r12 = new com.bumptech.glide.load.engine.DataCacheKey
            com.bumptech.glide.load.Key r13 = r11.y
            com.bumptech.glide.load.Key r1 = r11.j
            r12.<init>(r13, r1)
        L_0x009a:
            com.bumptech.glide.load.engine.LockedResource r0 = com.bumptech.glide.load.engine.LockedResource.a(r0)
            com.bumptech.glide.load.engine.DecodeJob$DeferredEncodeManager<?> r13 = r11.g
            r13.a(r12, r10, r0)
            goto L_0x00b2
        L_0x00a4:
            com.bumptech.glide.Registry$NoResultEncoderAvailableException r12 = new com.bumptech.glide.Registry$NoResultEncoderAvailableException
            java.lang.Object r13 = r0.d()
            java.lang.Class r13 = r13.getClass()
            r12.<init>(r13)
            throw r12
        L_0x00b2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.DecodeJob.a(com.bumptech.glide.load.DataSource, com.bumptech.glide.load.engine.Resource):com.bumptech.glide.load.engine.Resource");
    }

    private final class DecodeCallback<Z> implements DecodePath.DecodeCallback<Z> {
        private final DataSource b;

        DecodeCallback(DataSource dataSource) {
            this.b = dataSource;
        }

        @NonNull
        public Resource<Z> a(@NonNull Resource<Z> resource) {
            return DecodeJob.this.a(this.b, resource);
        }
    }

    private static class ReleaseManager {

        /* renamed from: a  reason: collision with root package name */
        private boolean f4863a;
        private boolean b;
        private boolean c;

        ReleaseManager() {
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean a(boolean z) {
            this.f4863a = true;
            return b(z);
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean a() {
            this.b = true;
            return b(false);
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean b() {
            this.c = true;
            return b(false);
        }

        /* access modifiers changed from: package-private */
        public synchronized void c() {
            this.b = false;
            this.f4863a = false;
            this.c = false;
        }

        private boolean b(boolean z) {
            return (this.c || z || this.b) && this.f4863a;
        }
    }

    private static class DeferredEncodeManager<Z> {

        /* renamed from: a  reason: collision with root package name */
        private Key f4862a;
        private ResourceEncoder<Z> b;
        private LockedResource<Z> c;

        DeferredEncodeManager() {
        }

        /* access modifiers changed from: package-private */
        public <X> void a(Key key, ResourceEncoder<X> resourceEncoder, LockedResource<X> lockedResource) {
            this.f4862a = key;
            this.b = resourceEncoder;
            this.c = lockedResource;
        }

        /* access modifiers changed from: package-private */
        public void a(DiskCacheProvider diskCacheProvider, Options options) {
            GlideTrace.a("DecodeJob.encode");
            try {
                diskCacheProvider.a().a(this.f4862a, new DataCacheWriter(this.b, this.c, options));
            } finally {
                this.c.a();
                GlideTrace.a();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.c != null;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.f4862a = null;
            this.b = null;
            this.c = null;
        }
    }
}
