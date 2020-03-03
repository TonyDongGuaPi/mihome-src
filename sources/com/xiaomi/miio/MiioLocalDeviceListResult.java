package com.xiaomi.miio;

import java.util.List;

public class MiioLocalDeviceListResult {

    /* renamed from: a  reason: collision with root package name */
    public MiioLocalErrorCode f11148a;
    public List<MiioLocalRpcResult> b;

    public MiioLocalDeviceListResult(MiioLocalErrorCode miioLocalErrorCode) {
        this.f11148a = miioLocalErrorCode;
    }

    public MiioLocalDeviceListResult(MiioLocalErrorCode miioLocalErrorCode, List<MiioLocalRpcResult> list) {
        this.f11148a = miioLocalErrorCode;
        this.b = list;
    }
}
