package com.xiaomi.qrcode;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

final class ViewfinderResultPointCallback implements ResultPointCallback {

    /* renamed from: a  reason: collision with root package name */
    private final ViewfinderView f12984a;

    ViewfinderResultPointCallback(ViewfinderView viewfinderView) {
        this.f12984a = viewfinderView;
    }

    public void foundPossibleResultPoint(ResultPoint resultPoint) {
        this.f12984a.addPossibleResultPoint(resultPoint);
    }
}
