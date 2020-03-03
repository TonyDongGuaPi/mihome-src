package com.bumptech.glide.provider;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.List;

public final class ImageHeaderParserRegistry {

    /* renamed from: a  reason: collision with root package name */
    private final List<ImageHeaderParser> f5050a = new ArrayList();

    @NonNull
    public synchronized List<ImageHeaderParser> a() {
        return this.f5050a;
    }

    public synchronized void a(@NonNull ImageHeaderParser imageHeaderParser) {
        this.f5050a.add(imageHeaderParser);
    }
}
