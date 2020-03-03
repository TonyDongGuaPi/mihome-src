package com.yanzhenjie.yp_permission.overlay;

import com.yanzhenjie.yp_permission.Action;
import com.yanzhenjie.yp_permission.Rationale;

public interface OverlayRequest {
    OverlayRequest a(Action<Void> action);

    OverlayRequest a(Rationale<Void> rationale);

    OverlayRequest b(Action<Void> action);

    void f();
}
