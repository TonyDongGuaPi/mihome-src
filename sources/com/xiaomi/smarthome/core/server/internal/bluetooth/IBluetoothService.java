package com.xiaomi.smarthome.core.server.internal.bluetooth;

import android.os.Bundle;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.server.bluetooth.IBleMeshUpgradeResponse;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.bluetooth.SearchResponse;

public interface IBluetoothService {
    public static final int A = 42;
    public static final int B = 43;
    public static final int C = 44;
    public static final int D = 45;
    public static final int E = 46;
    public static final int F = 47;
    public static final int G = 48;
    public static final int H = 49;
    public static final int I = 50;
    public static final int J = 51;
    public static final int K = 52;
    public static final int L = 53;
    public static final int M = 54;
    public static final String N = "extra.service.uuid";
    public static final String O = "extra.character.uuid";
    public static final String P = "extra.byte.array";
    public static final String Q = "extra.int.array";
    public static final String R = "extra.rssi";
    public static final String S = "extra.mtu";
    public static final String T = "extra.key";
    public static final String U = "extra.result";
    public static final String V = "extra.value";
    public static final String W = "extra.device.status";
    public static final String X = "extra.duration";
    public static final String Y = "extra.mac";
    public static final String Z = "extra.submac";

    /* renamed from: a  reason: collision with root package name */
    public static final int f14145a = 1;
    public static final String aa = "extra.scannable";
    public static final String ab = "extra.delay";
    public static final String ac = "extra.devices";
    public static final String ad = "extra.device";
    public static final String ae = "extra.interval";
    public static final String af = "extra.digits";
    public static final String ag = "extra.utcdelaytime";
    public static final String ah = "extra.message";
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = 7;
    public static final int h = 8;
    public static final int i = 9;
    public static final int j = 10;
    public static final int k = 11;
    public static final int l = 12;
    public static final int m = 13;
    public static final int n = 14;
    public static final int o = 15;
    public static final int p = 16;
    public static final int q = 30;
    public static final int r = 31;
    public static final int s = 32;
    public static final int t = 33;
    public static final int u = 34;
    public static final int v = 35;
    public static final int w = 36;
    public static final int x = 37;
    public static final int y = 40;
    public static final int z = 41;

    void a(SearchRequest searchRequest, IBleResponse iBleResponse);

    void a(SearchRequest searchRequest, SearchResponse searchResponse);

    void a(String str);

    void a(String str, int i2, Bundle bundle);

    void a(String str, int i2, Bundle bundle, IBleResponse iBleResponse);

    void a(String str, String str2, String str3, String str4, IBleMeshUpgradeResponse iBleMeshUpgradeResponse);

    byte[] a(String str, byte[] bArr);

    void b();

    void b(String str, int i2, Bundle bundle);

    byte[] b(String str, byte[] bArr);
}
