package com.alibaba.android.bindingx.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.alibaba.android.bindingx.core.BindingXCore;
import com.alibaba.android.bindingx.core.internal.ExpressionPair;
import java.util.List;
import java.util.Map;

public interface IEventHandler extends IEventInterceptor {
    void a();

    void a(String str);

    void a(@NonNull String str, @Nullable Map<String, Object> map, @Nullable ExpressionPair expressionPair, @NonNull List<Map<String, Object>> list, @Nullable BindingXCore.JavaScriptCallback javaScriptCallback);

    boolean a(@NonNull String str, @NonNull String str2);

    void b();

    void b(String str);

    void b(@NonNull String str, @NonNull String str2);

    void c();

    boolean c(@NonNull String str, @NonNull String str2);
}
