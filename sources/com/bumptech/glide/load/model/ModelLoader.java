package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.util.Preconditions;
import java.util.Collections;
import java.util.List;

public interface ModelLoader<Model, Data> {
    @Nullable
    LoadData<Data> a(@NonNull Model model, int i, int i2, @NonNull Options options);

    boolean a(@NonNull Model model);

    public static class LoadData<Data> {

        /* renamed from: a  reason: collision with root package name */
        public final Key f4958a;
        public final List<Key> b;
        public final DataFetcher<Data> c;

        public LoadData(@NonNull Key key, @NonNull DataFetcher<Data> dataFetcher) {
            this(key, Collections.emptyList(), dataFetcher);
        }

        public LoadData(@NonNull Key key, @NonNull List<Key> list, @NonNull DataFetcher<Data> dataFetcher) {
            this.f4958a = (Key) Preconditions.a(key);
            this.b = (List) Preconditions.a(list);
            this.c = (DataFetcher) Preconditions.a(dataFetcher);
        }
    }
}
