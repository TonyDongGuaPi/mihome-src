package com.xiaomi.qrcode2;

import com.xiaomi.zxing.ResultPoint;
import com.xiaomi.zxing.ResultPointCallback;

final class ViewfinderResultPointCallback implements ResultPointCallback {

    /* renamed from: a  reason: collision with root package name */
    private final ViewfinderView f13026a;

    ViewfinderResultPointCallback(ViewfinderView viewfinderView) {
        this.f13026a = viewfinderView;
    }

    public void a(ResultPoint resultPoint) {
        this.f13026a.addPossibleResultPoint(resultPoint);
    }
}
