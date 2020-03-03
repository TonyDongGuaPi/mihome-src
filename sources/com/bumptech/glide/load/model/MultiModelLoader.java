package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.Preconditions;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MultiModelLoader<Model, Data> implements ModelLoader<Model, Data> {

    /* renamed from: a  reason: collision with root package name */
    private final List<ModelLoader<Model, Data>> f4962a;
    private final Pools.Pool<List<Throwable>> b;

    MultiModelLoader(@NonNull List<ModelLoader<Model, Data>> list, @NonNull Pools.Pool<List<Throwable>> pool) {
        this.f4962a = list;
        this.b = pool;
    }

    public ModelLoader.LoadData<Data> a(@NonNull Model model, int i, int i2, @NonNull Options options) {
        ModelLoader.LoadData a2;
        int size = this.f4962a.size();
        ArrayList arrayList = new ArrayList(size);
        Key key = null;
        for (int i3 = 0; i3 < size; i3++) {
            ModelLoader modelLoader = this.f4962a.get(i3);
            if (modelLoader.a(model) && (a2 = modelLoader.a(model, i, i2, options)) != null) {
                key = a2.f4958a;
                arrayList.add(a2.c);
            }
        }
        if (arrayList.isEmpty() || key == null) {
            return null;
        }
        return new ModelLoader.LoadData<>(key, new MultiFetcher(arrayList, this.b));
    }

    public boolean a(@NonNull Model model) {
        for (ModelLoader<Model, Data> a2 : this.f4962a) {
            if (a2.a(model)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "MultiModelLoader{modelLoaders=" + Arrays.toString(this.f4962a.toArray()) + Operators.BLOCK_END;
    }

    static class MultiFetcher<Data> implements DataFetcher<Data>, DataFetcher.DataCallback<Data> {

        /* renamed from: a  reason: collision with root package name */
        private final List<DataFetcher<Data>> f4963a;
        private final Pools.Pool<List<Throwable>> b;
        private int c = 0;
        private Priority d;
        private DataFetcher.DataCallback<? super Data> e;
        @Nullable
        private List<Throwable> f;
        private boolean g;

        MultiFetcher(@NonNull List<DataFetcher<Data>> list, @NonNull Pools.Pool<List<Throwable>> pool) {
            this.b = pool;
            Preconditions.a(list);
            this.f4963a = list;
        }

        public void a(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super Data> dataCallback) {
            this.d = priority;
            this.e = dataCallback;
            this.f = this.b.acquire();
            this.f4963a.get(this.c).a(priority, this);
            if (this.g) {
                c();
            }
        }

        public void b() {
            if (this.f != null) {
                this.b.release(this.f);
            }
            this.f = null;
            for (DataFetcher<Data> b2 : this.f4963a) {
                b2.b();
            }
        }

        public void c() {
            this.g = true;
            for (DataFetcher<Data> c2 : this.f4963a) {
                c2.c();
            }
        }

        @NonNull
        public Class<Data> a() {
            return this.f4963a.get(0).a();
        }

        @NonNull
        public DataSource d() {
            return this.f4963a.get(0).d();
        }

        public void a(@Nullable Data data) {
            if (data != null) {
                this.e.a(data);
            } else {
                e();
            }
        }

        public void a(@NonNull Exception exc) {
            ((List) Preconditions.a(this.f)).add(exc);
            e();
        }

        private void e() {
            if (!this.g) {
                if (this.c < this.f4963a.size() - 1) {
                    this.c++;
                    a(this.d, this.e);
                    return;
                }
                Preconditions.a(this.f);
                this.e.a((Exception) new GlideException("Fetch failed", (List<Throwable>) new ArrayList(this.f)));
            }
        }
    }
}
