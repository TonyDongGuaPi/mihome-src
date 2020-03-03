package org.xutils.http;

import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT(HttpPut.METHOD_NAME),
    PATCH("PATCH"),
    HEAD("HEAD"),
    MOVE("MOVE"),
    COPY("COPY"),
    DELETE("DELETE"),
    OPTIONS(HttpOptions.METHOD_NAME),
    TRACE(HttpTrace.METHOD_NAME),
    CONNECT("CONNECT");
    
    private final String value;

    private HttpMethod(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }

    public static boolean permitsRetry(HttpMethod httpMethod) {
        return httpMethod == GET;
    }

    public static boolean permitsCache(HttpMethod httpMethod) {
        return httpMethod == GET || httpMethod == POST;
    }

    public static boolean permitsRequestBody(HttpMethod httpMethod) {
        return httpMethod == POST || httpMethod == PUT || httpMethod == PATCH || httpMethod == DELETE;
    }
}
