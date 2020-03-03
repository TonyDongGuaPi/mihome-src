package com.yanzhenjie.permission;

import android.support.annotation.NonNull;

public interface Request {
    @NonNull
    Request a(Action action);

    @NonNull
    Request a(Rationale rationale);

    @NonNull
    Request a(String... strArr);

    @NonNull
    Request a(String[]... strArr);

    void a();

    @NonNull
    Request b(Action action);
}
