package com.xiaomi.smarthome.core.server.internal.bluetooth.mesh;

public abstract class AbsDfuEvent {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14183a = 1;
    public static final int b = 2;
    public static final int c = 0;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 3;
    public static final int g = 4;
    public static final int h = 5;
    public static final int i = 6;
    public static final int j = 255;
    protected byte[] k = new byte[0];
    private int l;

    public abstract int a();

    public AbsDfuEvent(byte[] bArr) {
        int min;
        if (bArr != null && bArr.length > 0) {
            this.l = bArr[0];
            if (bArr.length > 1 && (min = Math.min(bArr[1], bArr.length - 2)) > 0) {
                this.k = new byte[min];
                System.arraycopy(bArr, 2, this.k, 0, min);
            }
        }
    }

    public int b() {
        return this.l;
    }
}
