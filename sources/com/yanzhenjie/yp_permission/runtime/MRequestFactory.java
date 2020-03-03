package com.yanzhenjie.yp_permission.runtime;

import com.yanzhenjie.yp_permission.runtime.Runtime;
import com.yanzhenjie.yp_permission.source.Source;

public class MRequestFactory implements Runtime.PermissionRequestFactory {
    public PermissionRequest a(Source source) {
        return new MRequest(source);
    }
}
