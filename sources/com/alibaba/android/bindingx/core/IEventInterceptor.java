package com.alibaba.android.bindingx.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.alibaba.android.bindingx.core.internal.ExpressionPair;
import java.util.Map;

public interface IEventInterceptor {
    void a(@NonNull String str, @NonNull ExpressionPair expressionPair, @NonNull Map<String, Object> map);

    void a(@Nullable Map<String, ExpressionPair> map);
}
