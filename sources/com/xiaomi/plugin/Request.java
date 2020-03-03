package com.xiaomi.plugin;

public class Request<T> {
    public Callback<T> callback;
    public Parser<T> parser;
    public RequestParams requestParams;
    public T result;

    public Request(RequestParams requestParams2, Callback<T> callback2, Parser<T> parser2) {
        this.requestParams = requestParams2;
        this.callback = callback2;
        this.parser = parser2;
    }

    public static class Buider<T> {
        public Callback<T> callback;
        public Parser<T> parser;
        public RequestParams requestParams;

        public Buider<T> setRequestParams(RequestParams requestParams2) {
            this.requestParams = requestParams2;
            return this;
        }

        public Buider<T> setCallback(Callback<T> callback2) {
            this.callback = callback2;
            return this;
        }

        public Buider<T> setParser(Parser<T> parser2) {
            this.parser = parser2;
            return this;
        }

        public Request<T> buider() {
            return new Request<>(this.requestParams, this.callback, this.parser);
        }
    }
}
