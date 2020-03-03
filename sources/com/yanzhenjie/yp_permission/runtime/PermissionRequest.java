package com.yanzhenjie.yp_permission.runtime;

import com.yanzhenjie.yp_permission.Action;
import com.yanzhenjie.yp_permission.Rationale;
import java.util.List;

public interface PermissionRequest {
    PermissionRequest a(Action<List<String>> action);

    PermissionRequest a(Rationale<List<String>> rationale);

    PermissionRequest a(String... strArr);

    void ac_();

    PermissionRequest b(Action<List<String>> action);
}
