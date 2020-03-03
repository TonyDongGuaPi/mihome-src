package com.xiaomi.zxing.datamatrix.encoder;

import com.ximalaya.ting.android.xmpayordersdk.IXmPayOrderListener;

final class DataMatrixSymbolInfo144 extends SymbolInfo {
    public int a() {
        return 10;
    }

    public int a(int i) {
        return i <= 8 ? 156 : 155;
    }

    DataMatrixSymbolInfo144() {
        super(false, 1558, IXmPayOrderListener.t, 22, 22, 36, -1, 62);
    }
}
