package com.xiaomi.jr.http;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.Part;

public class MultipartStringConverterFactory extends Converter.Factory {

    /* renamed from: a  reason: collision with root package name */
    private static final MediaType f10817a = MediaType.parse("text/plain");

    public static MultipartStringConverterFactory a() {
        return new MultipartStringConverterFactory();
    }

    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        if (!String.class.equals(type) || annotationArr == null || annotationArr.length <= 0) {
            return null;
        }
        for (Annotation annotation : annotationArr) {
            if (annotation instanceof Part) {
                return $$Lambda$MultipartStringConverterFactory$qfl679r8ZGzTreciMQwUW5zTCWA.INSTANCE;
            }
        }
        return null;
    }
}
