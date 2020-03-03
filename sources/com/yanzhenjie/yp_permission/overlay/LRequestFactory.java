package com.yanzhenjie.yp_permission.overlay;

import com.yanzhenjie.yp_permission.Options;
import com.yanzhenjie.yp_permission.source.Source;

public class LRequestFactory implements Options.OverlayRequestFactory {
    public OverlayRequest a(Source source) {
        return new LRequest(source);
    }
}
