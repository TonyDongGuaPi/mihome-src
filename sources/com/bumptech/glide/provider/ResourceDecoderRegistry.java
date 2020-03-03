package com.bumptech.glide.provider;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.ResourceDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceDecoderRegistry {

    /* renamed from: a  reason: collision with root package name */
    private final List<String> f5053a = new ArrayList();
    private final Map<String, List<Entry<?, ?>>> b = new HashMap();

    public synchronized void a(@NonNull List<String> list) {
        ArrayList<String> arrayList = new ArrayList<>(this.f5053a);
        this.f5053a.clear();
        this.f5053a.addAll(list);
        for (String str : arrayList) {
            if (!list.contains(str)) {
                this.f5053a.add(str);
            }
        }
    }

    @NonNull
    public synchronized <T, R> List<ResourceDecoder<T, R>> a(@NonNull Class<T> cls, @NonNull Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (String str : this.f5053a) {
            List<Entry> list = this.b.get(str);
            if (list != null) {
                for (Entry entry : list) {
                    if (entry.a(cls, cls2)) {
                        arrayList.add(entry.b);
                    }
                }
            }
        }
        return arrayList;
    }

    @NonNull
    public synchronized <T, R> List<Class<R>> b(@NonNull Class<T> cls, @NonNull Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (String str : this.f5053a) {
            List<Entry> list = this.b.get(str);
            if (list != null) {
                for (Entry entry : list) {
                    if (entry.a(cls, cls2) && !arrayList.contains(entry.f5054a)) {
                        arrayList.add(entry.f5054a);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized <T, R> void a(@NonNull String str, @NonNull ResourceDecoder<T, R> resourceDecoder, @NonNull Class<T> cls, @NonNull Class<R> cls2) {
        a(str).add(new Entry(cls, cls2, resourceDecoder));
    }

    public synchronized <T, R> void b(@NonNull String str, @NonNull ResourceDecoder<T, R> resourceDecoder, @NonNull Class<T> cls, @NonNull Class<R> cls2) {
        a(str).add(0, new Entry(cls, cls2, resourceDecoder));
    }

    @NonNull
    private synchronized List<Entry<?, ?>> a(@NonNull String str) {
        List<Entry<?, ?>> list;
        if (!this.f5053a.contains(str)) {
            this.f5053a.add(str);
        }
        list = this.b.get(str);
        if (list == null) {
            list = new ArrayList<>();
            this.b.put(str, list);
        }
        return list;
    }

    private static class Entry<T, R> {

        /* renamed from: a  reason: collision with root package name */
        final Class<R> f5054a;
        final ResourceDecoder<T, R> b;
        private final Class<T> c;

        public Entry(@NonNull Class<T> cls, @NonNull Class<R> cls2, ResourceDecoder<T, R> resourceDecoder) {
            this.c = cls;
            this.f5054a = cls2;
            this.b = resourceDecoder;
        }

        public boolean a(@NonNull Class<?> cls, @NonNull Class<?> cls2) {
            return this.c.isAssignableFrom(cls) && cls2.isAssignableFrom(this.f5054a);
        }
    }
}
