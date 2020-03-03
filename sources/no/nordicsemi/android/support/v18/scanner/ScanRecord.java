package no.nordicsemi.android.support.v18.scanner;

import android.os.ParcelUuid;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ScanRecord {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3184a = "ScanRecord";
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final int g = 6;
    private static final int h = 7;
    private static final int i = 8;
    private static final int j = 9;
    private static final int k = 10;
    private static final int l = 22;
    private static final int m = 32;
    private static final int n = 33;
    private static final int o = 255;
    private final int p;
    @Nullable
    private final List<ParcelUuid> q;
    @Nullable
    private final SparseArray<byte[]> r;
    @Nullable
    private final Map<ParcelUuid, byte[]> s;
    private final int t;
    private final String u;
    private final byte[] v;

    public int a() {
        return this.p;
    }

    @Nullable
    public List<ParcelUuid> b() {
        return this.q;
    }

    @Nullable
    public SparseArray<byte[]> c() {
        return this.r;
    }

    @Nullable
    public byte[] a(int i2) {
        if (this.r == null) {
            return null;
        }
        return this.r.get(i2);
    }

    @Nullable
    public Map<ParcelUuid, byte[]> d() {
        return this.s;
    }

    @Nullable
    public byte[] a(@NonNull ParcelUuid parcelUuid) {
        if (parcelUuid == null || this.s == null) {
            return null;
        }
        return this.s.get(parcelUuid);
    }

    public int e() {
        return this.t;
    }

    @Nullable
    public String f() {
        return this.u;
    }

    @Nullable
    public byte[] g() {
        return this.v;
    }

    private ScanRecord(@Nullable List<ParcelUuid> list, @Nullable SparseArray<byte[]> sparseArray, @Nullable Map<ParcelUuid, byte[]> map, int i2, int i3, String str, byte[] bArr) {
        this.q = list;
        this.r = sparseArray;
        this.s = map;
        this.u = str;
        this.p = i2;
        this.t = i3;
        this.v = bArr;
    }

    @Nullable
    static ScanRecord a(@Nullable byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int i2 = 0;
        ArrayList arrayList = null;
        SparseArray sparseArray = null;
        HashMap hashMap = null;
        String str = null;
        byte b2 = -1;
        byte b3 = -2147483648;
        while (true) {
            try {
                if (i2 < bArr.length) {
                    int i3 = i2 + 1;
                    byte b4 = bArr[i2] & 255;
                    if (b4 != 0) {
                        int i4 = b4 - 1;
                        int i5 = i3 + 1;
                        byte b5 = bArr[i3] & 255;
                        int i6 = 16;
                        if (b5 != 22) {
                            if (b5 != 255) {
                                switch (b5) {
                                    case 1:
                                        b2 = bArr[i5] & 255;
                                        continue;
                                    case 2:
                                    case 3:
                                        if (arrayList == null) {
                                            arrayList = new ArrayList();
                                        }
                                        a(bArr, i5, i4, 2, arrayList);
                                        continue;
                                    case 4:
                                    case 5:
                                        if (arrayList == null) {
                                            arrayList = new ArrayList();
                                        }
                                        a(bArr, i5, i4, 4, arrayList);
                                        continue;
                                    case 6:
                                    case 7:
                                        if (arrayList == null) {
                                            arrayList = new ArrayList();
                                        }
                                        a(bArr, i5, i4, 16, arrayList);
                                        continue;
                                    case 8:
                                    case 9:
                                        str = new String(a(bArr, i5, i4));
                                        continue;
                                    case 10:
                                        b3 = bArr[i5];
                                        continue;
                                    default:
                                        switch (b5) {
                                            case 32:
                                            case 33:
                                                break;
                                            default:
                                                continue;
                                                continue;
                                        }
                                }
                            } else {
                                int i7 = ((bArr[i5 + 1] & 255) << 8) + (255 & bArr[i5]);
                                byte[] a2 = a(bArr, i5 + 2, i4 - 2);
                                if (sparseArray == null) {
                                    sparseArray = new SparseArray();
                                }
                                sparseArray.put(i7, a2);
                            }
                            i2 = i4 + i5;
                        }
                        if (b5 == 32) {
                            i6 = 4;
                        } else if (b5 != 33) {
                            i6 = 2;
                        }
                        ParcelUuid a3 = BluetoothUuid.a(a(bArr, i5, i6));
                        byte[] a4 = a(bArr, i5 + i6, i4 - i6);
                        if (hashMap == null) {
                            hashMap = new HashMap();
                        }
                        hashMap.put(a3, a4);
                        i2 = i4 + i5;
                    }
                }
            } catch (Exception unused) {
                Log.e(f3184a, "unable to parse scan record: " + Arrays.toString(bArr));
                return new ScanRecord((List<ParcelUuid>) null, (SparseArray<byte[]>) null, (Map<ParcelUuid, byte[]>) null, -1, Integer.MIN_VALUE, (String) null, bArr);
            }
        }
        return new ScanRecord(arrayList, sparseArray, hashMap, b2, b3, str, bArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.v, ((ScanRecord) obj).v);
    }

    public String toString() {
        return "ScanRecord [advertiseFlags=" + this.p + ", serviceUuids=" + this.q + ", manufacturerSpecificData=" + BluetoothLeUtils.a(this.r) + ", serviceData=" + BluetoothLeUtils.a(this.s) + ", txPowerLevel=" + this.t + ", deviceName=" + this.u + Operators.ARRAY_END_STR;
    }

    private static int a(@NonNull byte[] bArr, int i2, int i3, int i4, @NonNull List<ParcelUuid> list) {
        while (i3 > 0) {
            list.add(BluetoothUuid.a(a(bArr, i2, i4)));
            i3 -= i4;
            i2 += i4;
        }
        return i2;
    }

    private static byte[] a(@NonNull byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return bArr2;
    }
}
