package com.xiaomi.zxing;

import java.util.EnumMap;
import java.util.Map;

public final class Result {

    /* renamed from: a  reason: collision with root package name */
    private final String f1631a;
    private final byte[] b;
    private ResultPoint[] c;
    private final BarcodeFormat d;
    private Map<ResultMetadataType, Object> e;
    private final long f;

    public Result(String str, byte[] bArr, ResultPoint[] resultPointArr, BarcodeFormat barcodeFormat) {
        this(str, bArr, resultPointArr, barcodeFormat, System.currentTimeMillis());
    }

    public Result(String str, byte[] bArr, ResultPoint[] resultPointArr, BarcodeFormat barcodeFormat, long j) {
        this.f1631a = str;
        this.b = bArr;
        this.c = resultPointArr;
        this.d = barcodeFormat;
        this.e = null;
        this.f = j;
    }

    public String a() {
        return this.f1631a;
    }

    public byte[] b() {
        return this.b;
    }

    public ResultPoint[] c() {
        return this.c;
    }

    public BarcodeFormat d() {
        return this.d;
    }

    public Map<ResultMetadataType, Object> e() {
        return this.e;
    }

    public void a(ResultMetadataType resultMetadataType, Object obj) {
        if (this.e == null) {
            this.e = new EnumMap(ResultMetadataType.class);
        }
        this.e.put(resultMetadataType, obj);
    }

    public void a(Map<ResultMetadataType, Object> map) {
        if (map == null) {
            return;
        }
        if (this.e == null) {
            this.e = map;
        } else {
            this.e.putAll(map);
        }
    }

    public void a(ResultPoint[] resultPointArr) {
        ResultPoint[] resultPointArr2 = this.c;
        if (resultPointArr2 == null) {
            this.c = resultPointArr;
        } else if (resultPointArr != null && resultPointArr.length > 0) {
            ResultPoint[] resultPointArr3 = new ResultPoint[(resultPointArr2.length + resultPointArr.length)];
            System.arraycopy(resultPointArr2, 0, resultPointArr3, 0, resultPointArr2.length);
            System.arraycopy(resultPointArr, 0, resultPointArr3, resultPointArr2.length, resultPointArr.length);
            this.c = resultPointArr3;
        }
    }

    public long f() {
        return this.f;
    }

    public String toString() {
        return this.f1631a;
    }
}
