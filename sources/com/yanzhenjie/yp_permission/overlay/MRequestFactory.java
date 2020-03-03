package com.yanzhenjie.yp_permission.overlay;

import com.yanzhenjie.yp_permission.Options;
import com.yanzhenjie.yp_permission.source.Source;

public class MRequestFactory implements Options.OverlayRequestFactory {
    public OverlayRequest a(Source source) {
        return new MRequest(source);
    }
}
