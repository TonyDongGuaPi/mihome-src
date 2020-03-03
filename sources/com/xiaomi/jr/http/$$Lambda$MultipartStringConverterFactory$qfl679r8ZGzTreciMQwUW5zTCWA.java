package com.xiaomi.jr.http;

import okhttp3.RequestBody;
import retrofit2.Converter;

/* renamed from: com.xiaomi.jr.http.-$$Lambda$MultipartStringConverterFactory$qfl679r8ZGzTreciMQwUW5zTCWA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MultipartStringConverterFactory$qfl679r8ZGzTreciMQwUW5zTCWA implements Converter {
    public static final /* synthetic */ $$Lambda$MultipartStringConverterFactory$qfl679r8ZGzTreciMQwUW5zTCWA INSTANCE = new $$Lambda$MultipartStringConverterFactory$qfl679r8ZGzTreciMQwUW5zTCWA();

    private /* synthetic */ $$Lambda$MultipartStringConverterFactory$qfl679r8ZGzTreciMQwUW5zTCWA() {
    }

    public final Object convert(Object obj) {
        return RequestBody.create(MultipartStringConverterFactory.f10817a, (String) obj);
    }
}
