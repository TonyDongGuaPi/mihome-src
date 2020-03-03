package com.xiaomi.smarthome;

import android.os.Build;
import android.os.ParcelUuid;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class BLEScanRecord {

    /* renamed from: a  reason: collision with root package name */
    public static final ParcelUuid f13362a = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
    public static final int b = 2;
    public static final int c = 4;
    public static final int d = 16;
    private static final String e = "BLEScanRecord";
    private static final int f = 1;
    private static final int g = 2;
    private static final int h = 3;
    private static final int i = 4;
    private static final int j = 5;
    private static final int k = 6;
    private static final int l = 7;
    private static final int m = 8;
    private static final int n = 9;
    private static final int o = 10;
    private static final int p = 22;
    private static final int q = 255;
    private final int r;
    private final List<ParcelUuid> s;
    private final SparseArray<byte[]> t;
    private final Map<ParcelUuid, byte[]> u;
    private final int v;
    private final String w;
    private final byte[] x;

    public int a() {
        return this.r;
    }

    public List<ParcelUuid> b() {
        return this.s;
    }

    public SparseArray<byte[]> c() {
        return this.t;
    }

    public byte[] a(int i2) {
        return this.t.get(i2);
    }

    public Map<ParcelUuid, byte[]> d() {
        return this.u;
    }

    public byte[] a(ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            return null;
        }
        return this.u.get(parcelUuid);
    }

    public int e() {
        return this.v;
    }

    public String f() {
        return this.w;
    }

    public byte[] g() {
        return this.x;
    }

    private BLEScanRecord(List<ParcelUuid> list, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int i2, int i3, String str, byte[] bArr) {
        this.s = list;
        this.t = sparseArray;
        this.u = map;
        this.w = str;
        this.r = i2;
        this.v = i3;
        this.x = bArr;
    }

    public static BLEScanRecord a(byte[] bArr) {
        Map hashMap;
        if (bArr == null) {
            return null;
        }
        int i2 = 0;
        ArrayList arrayList = new ArrayList();
        SparseArray sparseArray = new SparseArray();
        if (Build.VERSION.SDK_INT >= 19) {
            hashMap = new ArrayMap();
        } else {
            hashMap = new HashMap();
        }
        String str = null;
        Map map = hashMap;
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
                        if (b5 == 22) {
                            map.put(b(a(bArr, i5, 2)), a(bArr, i5 + 2, i4 - 2));
                        } else if (b5 != 255) {
                            switch (b5) {
                                case 1:
                                    b2 = bArr[i5] & 255;
                                    break;
                                case 2:
                                case 3:
                                    a(bArr, i5, i4, 2, arrayList);
                                    break;
                                case 4:
                                case 5:
                                    a(bArr, i5, i4, 4, arrayList);
                                    break;
                                case 6:
                                case 7:
                                    a(bArr, i5, i4, 16, arrayList);
                                    break;
                                case 8:
                                case 9:
                                    str = new String(a(bArr, i5, i4));
                                    break;
                                case 10:
                                    b3 = bArr[i5];
                                    break;
                            }
                        } else {
                            sparseArray.put(((bArr[i5 + 1] & 255) << 8) + (255 & bArr[i5]), a(bArr, i5 + 2, i4 - 2));
                        }
                        i2 = i4 + i5;
                    }
                }
            } catch (Exception unused) {
                Log.e(e, "unable to parse scan record: " + Arrays.toString(bArr));
                return new BLEScanRecord((List<ParcelUuid>) null, (SparseArray<byte[]>) null, (Map<ParcelUuid, byte[]>) null, -1, Integer.MIN_VALUE, (String) null, bArr);
            }
        }
        return new BLEScanRecord(arrayList.isEmpty() ? null : arrayList, sparseArray, map, b2, b3, str, bArr);
    }

    public String toString() {
        return "ScanRecord [mAdvertiseFlags=" + this.r + ", mServiceUuids=" + this.s + ", mManufacturerSpecificData=" + a(this.t) + ", mServiceData=" + a(this.u) + ", mTxPowerLevel=" + this.v + ", mDeviceName=" + this.w + Operators.ARRAY_END_STR;
    }

    public static ParcelUuid b(byte[] bArr) {
        long j2;
        if (bArr != null) {
            int length = bArr.length;
            if (length != 2 && length != 4 && length != 16) {
                throw new IllegalArgumentException("uuidBytes length invalid - " + length);
            } else if (length == 16) {
                ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
                return new ParcelUuid(new UUID(order.getLong(8), order.getLong(0)));
            } else {
                if (length == 2) {
                    j2 = ((long) (bArr[0] & 255)) + ((long) ((bArr[1] & 255) << 8));
                } else {
                    j2 = ((long) ((bArr[3] & 255) << 24)) + ((long) (bArr[0] & 255)) + ((long) ((bArr[1] & 255) << 8)) + ((long) ((bArr[2] & 255) << 16));
                }
                return new ParcelUuid(new UUID(f13362a.getUuid().getMostSignificantBits() + (j2 << 32), f13362a.getUuid().getLeastSignificantBits()));
            }
        } else {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
    }

    private static int a(byte[] bArr, int i2, int i3, int i4, List<ParcelUuid> list) {
        while (i3 > 0) {
            list.add(b(a(bArr, i2, i4)));
            i3 -= i4;
            i2 += i4;
        }
        return i2;
    }

    private static byte[] a(byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return bArr2;
    }

    static String a(SparseArray<byte[]> sparseArray) {
        if (sparseArray == null) {
            return "null";
        }
        if (sparseArray.size() == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.BLOCK_START);
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            sb.append(sparseArray.keyAt(i2));
            sb.append("=");
            sb.append(Arrays.toString(sparseArray.valueAt(i2)));
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    static <T> String a(Map<T, byte[]> map) {
        if (map == null) {
            return "null";
        }
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.BLOCK_START);
        Iterator<Map.Entry<T, byte[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Object key = it.next().getKey();
            sb.append(key);
            sb.append("=");
            sb.append(Arrays.toString(map.get(key)));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
