package com.loc;

public final class am {

    /* renamed from: a  reason: collision with root package name */
    private final int f6479a;
    private int b;

    public am() {
        this.b = 0;
        this.f6479a = 37;
        this.b = 17;
    }

    private am a(long j) {
        this.b = (this.b * this.f6479a) + ((int) (j ^ (j >> 32)));
        return this;
    }

    public final int a() {
        return this.b;
    }

    public final am a(Object obj) {
        if (obj != null) {
            if (obj.getClass().isArray()) {
                int i = 0;
                if (obj instanceof long[]) {
                    long[] jArr = (long[]) obj;
                    if (jArr != null) {
                        while (i < jArr.length) {
                            a(jArr[i]);
                            i++;
                        }
                    }
                } else if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    if (iArr != null) {
                        while (i < iArr.length) {
                            this.b = (this.b * this.f6479a) + iArr[i];
                            i++;
                        }
                    }
                } else if (obj instanceof short[]) {
                    short[] sArr = (short[]) obj;
                    if (sArr != null) {
                        while (i < sArr.length) {
                            this.b = (this.b * this.f6479a) + sArr[i];
                            i++;
                        }
                    }
                } else if (obj instanceof char[]) {
                    char[] cArr = (char[]) obj;
                    if (cArr != null) {
                        while (i < cArr.length) {
                            this.b = (this.b * this.f6479a) + cArr[i];
                            i++;
                        }
                    }
                } else if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    if (bArr != null) {
                        while (i < bArr.length) {
                            this.b = (this.b * this.f6479a) + bArr[i];
                            i++;
                        }
                    }
                } else if (obj instanceof double[]) {
                    double[] dArr = (double[]) obj;
                    if (dArr != null) {
                        while (i < dArr.length) {
                            a(Double.doubleToLongBits(dArr[i]));
                            i++;
                        }
                    }
                } else if (obj instanceof float[]) {
                    float[] fArr = (float[]) obj;
                    if (fArr != null) {
                        while (i < fArr.length) {
                            this.b = (this.b * this.f6479a) + Float.floatToIntBits(fArr[i]);
                            i++;
                        }
                    }
                } else if (obj instanceof boolean[]) {
                    boolean[] zArr = (boolean[]) obj;
                    if (zArr != null) {
                        while (i < zArr.length) {
                            this.b = (this.b * this.f6479a) + (zArr[i] ^ true ? 1 : 0);
                            i++;
                        }
                    }
                } else {
                    a((Object[]) obj);
                }
            } else {
                this.b = (this.b * this.f6479a) + obj.hashCode();
            }
            return this;
        }
        this.b *= this.f6479a;
        return this;
    }

    public final am a(Object[] objArr) {
        if (objArr == null) {
            this.b *= this.f6479a;
        } else {
            for (Object a2 : objArr) {
                a(a2);
            }
        }
        return this;
    }

    public final int hashCode() {
        return this.b;
    }
}
