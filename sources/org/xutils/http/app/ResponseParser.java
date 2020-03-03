package org.xutils.http.app;

import java.lang.reflect.Type;
import org.xutils.http.request.UriRequest;

public interface ResponseParser {
    Object a(Type type, Class<?> cls, String str) throws Throwable;

    void a(UriRequest uriRequest) throws Throwable;
}
