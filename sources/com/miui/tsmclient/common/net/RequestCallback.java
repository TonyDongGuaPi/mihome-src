package com.miui.tsmclient.common.net;

import android.support.annotation.NonNull;

public interface RequestCallback<T> {
    void onFailed(ErrorInfo errorInfo);

    void onResponse(@NonNull T t);
}
