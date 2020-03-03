package com.yanzhenjie.yp_permission.runtime;

import com.yanzhenjie.yp_permission.runtime.Runtime;
import com.yanzhenjie.yp_permission.source.Source;

public class LRequestFactory implements Runtime.PermissionRequestFactory {
    public PermissionRequest a(Source source) {
        return new LRequest(source);
    }
}
