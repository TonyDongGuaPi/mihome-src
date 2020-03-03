package com.ximalaya.ting.android.sdkdownloader.downloadutil;

import com.ximalaya.ting.android.sdkdownloader.exception.BaseRuntimeException;

public interface IDoSomethingProgress<T extends BaseRuntimeException> {
    void a();

    void a(T t);

    void b();
}
