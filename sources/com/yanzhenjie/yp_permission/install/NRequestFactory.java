package com.yanzhenjie.yp_permission.install;

import com.yanzhenjie.yp_permission.Options;
import com.yanzhenjie.yp_permission.source.Source;

public class NRequestFactory implements Options.InstallRequestFactory {
    public InstallRequest a(Source source) {
        return new NRequest(source);
    }
}
