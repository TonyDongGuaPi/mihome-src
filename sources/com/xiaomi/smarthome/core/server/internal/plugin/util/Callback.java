package com.xiaomi.smarthome.core.server.internal.plugin.util;

public interface Callback<T, E> {
    E call(T t) throws Exception;
}
