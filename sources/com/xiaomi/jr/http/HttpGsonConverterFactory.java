package com.xiaomi.jr.http;

import com.google.gson.Gson;
import com.xiaomi.jr.http.model.RawResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpGsonConverterFactory extends Converter.Factory {

    /* renamed from: a  reason: collision with root package name */
    private Gson f10813a;
    private GsonConverterFactory b;

    public static HttpGsonConverterFactory a(Gson gson) {
        return new HttpGsonConverterFactory(gson);
    }

    private HttpGsonConverterFactory(Gson gson) {
        this.f10813a = gson;
        this.b = GsonConverterFactory.create(gson);
    }

    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        return this.b.requestBodyConverter(type, annotationArr, annotationArr2, retrofit);
    }

    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        Converter<ResponseBody, ?> responseBodyConverter = this.b.responseBodyConverter(type, annotationArr, retrofit);
        boolean z = false;
        if (annotationArr != null && annotationArr.length > 0) {
            int length = annotationArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (annotationArr[i] instanceof RetainRawResponse) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
        }
        if (z) {
            Class cls = null;
            if (type instanceof ParameterizedType) {
                cls = (Class) ((ParameterizedType) type).getRawType();
            } else if (type instanceof Class) {
                cls = (Class) type;
            }
            if (cls != null && RawResponse.class.isAssignableFrom(cls)) {
                return new Converter(type) {
                    private final /* synthetic */ Type f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final Object convert(Object obj) {
                        return HttpGsonConverterFactory.this.a(this.f$1, (ResponseBody) obj);
                    }
                };
            }
        }
        return responseBodyConverter;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ RawResponse a(Type type, ResponseBody responseBody) throws IOException {
        String string = responseBody.string();
        RawResponse rawResponse = (RawResponse) this.f10813a.fromJson(string, type);
        if (rawResponse != null) {
            rawResponse.b(string);
        }
        return rawResponse;
    }
}
