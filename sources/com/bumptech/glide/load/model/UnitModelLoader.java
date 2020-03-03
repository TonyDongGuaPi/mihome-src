package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

public class UnitModelLoader<Model> implements ModelLoader<Model, Model> {

    /* renamed from: a  reason: collision with root package name */
    private static final UnitModelLoader<?> f4973a = new UnitModelLoader<>();

    public boolean a(@NonNull Model model) {
        return true;
    }

    public static <T> UnitModelLoader<T> a() {
        return f4973a;
    }

    public ModelLoader.LoadData<Model> a(@NonNull Model model, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(model), new UnitFetcher(model));
    }

    private static class UnitFetcher<Model> implements DataFetcher<Model> {

        /* renamed from: a  reason: collision with root package name */
        private final Model f4975a;

        public void b() {
        }

        public void c() {
        }

        UnitFetcher(Model model) {
            this.f4975a = model;
        }

        public void a(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super Model> dataCallback) {
            dataCallback.a(this.f4975a);
        }

        @NonNull
        public Class<Model> a() {
            return this.f4975a.getClass();
        }

        @NonNull
        public DataSource d() {
            return DataSource.LOCAL;
        }
    }

    public static class Factory<Model> implements ModelLoaderFactory<Model, Model> {

        /* renamed from: a  reason: collision with root package name */
        private static final Factory<?> f4974a = new Factory<>();

        public void a() {
        }

        public static <T> Factory<T> b() {
            return f4974a;
        }

        @NonNull
        public ModelLoader<Model, Model> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return UnitModelLoader.a();
        }
    }
}
