package com.xiaomi.smarthome.core.server.internal.plugin.util;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import com.google.android.gms.measurement.AppMeasurement;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class ParcelUtils {
    private static final int A = 24;
    private static final int B = 25;
    private static final int C = 26;
    private static final int D = 27;
    private static final int E = 28;
    private static final int F = 29;
    private static final int G = 30;

    /* renamed from: a  reason: collision with root package name */
    private static final String f14699a = "ParcelUtils";
    private static final int b = -1;
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 2;
    private static final int f = 3;
    private static final int g = 4;
    private static final int h = 5;
    private static final int i = 6;
    private static final int j = 7;
    private static final int k = 8;
    private static final int l = 9;
    private static final int m = 10;
    private static final int n = 11;
    private static final int o = 12;
    private static final int p = 13;
    private static final int q = 14;
    private static final int r = 15;
    private static final int s = 16;
    private static final int t = 17;
    private static final int u = 18;
    private static final int v = 19;
    private static final int w = 20;
    private static final int x = 21;
    private static final int y = 22;
    private static final int z = 23;

    public static final void a(Parcel parcel, Map map) {
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        Set<Map.Entry> entrySet = map.entrySet();
        parcel.writeInt(entrySet.size());
        for (Map.Entry entry : entrySet) {
            a(parcel, entry.getKey());
            a(parcel, entry.getValue());
        }
    }

    public static final void a(Parcel parcel, Object obj) {
        if (obj == null) {
            parcel.writeInt(-1);
        } else if (obj instanceof String) {
            parcel.writeInt(0);
            parcel.writeString((String) obj);
        } else if (obj instanceof Integer) {
            parcel.writeInt(1);
            parcel.writeInt(((Integer) obj).intValue());
        } else if (obj instanceof Map) {
            parcel.writeInt(2);
            a(parcel, (Map) obj);
        } else if (obj instanceof Bundle) {
            parcel.writeInt(3);
            parcel.writeBundle((Bundle) obj);
        } else if (Build.VERSION.SDK_INT >= 21 && (obj instanceof PersistableBundle)) {
            parcel.writeInt(25);
            parcel.writePersistableBundle((PersistableBundle) obj);
        } else if (obj instanceof Parcelable) {
            parcel.writeInt(4);
            parcel.writeParcelable((Parcelable) obj, 0);
        } else if (obj instanceof Short) {
            parcel.writeInt(5);
            parcel.writeInt(((Short) obj).intValue());
        } else if (obj instanceof Long) {
            parcel.writeInt(6);
            parcel.writeLong(((Long) obj).longValue());
        } else if (obj instanceof Float) {
            parcel.writeInt(7);
            parcel.writeFloat(((Float) obj).floatValue());
        } else if (obj instanceof Double) {
            parcel.writeInt(8);
            parcel.writeDouble(((Double) obj).doubleValue());
        } else if (obj instanceof Boolean) {
            parcel.writeInt(9);
            parcel.writeInt(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof CharSequence) {
            parcel.writeInt(10);
            a(parcel, (CharSequence) obj);
        } else if (obj instanceof List) {
            parcel.writeInt(11);
            parcel.writeList((List) obj);
        } else if (obj instanceof SparseArray) {
            parcel.writeInt(12);
            parcel.writeSparseArray((SparseArray) obj);
        } else if (obj instanceof boolean[]) {
            parcel.writeInt(23);
            parcel.writeBooleanArray((boolean[]) obj);
        } else if (obj instanceof byte[]) {
            parcel.writeInt(13);
            parcel.writeByteArray((byte[]) obj);
        } else if (obj instanceof String[]) {
            parcel.writeInt(14);
            parcel.writeStringArray((String[]) obj);
        } else if (obj instanceof CharSequence[]) {
            parcel.writeInt(24);
            a(parcel, (CharSequence[]) obj);
        } else if (obj instanceof IBinder) {
            parcel.writeInt(15);
            parcel.writeStrongBinder((IBinder) obj);
        } else if (obj instanceof Parcelable[]) {
            parcel.writeInt(16);
            parcel.writeParcelableArray((Parcelable[]) obj, 0);
        } else if (obj instanceof int[]) {
            parcel.writeInt(18);
            parcel.writeIntArray((int[]) obj);
        } else if (obj instanceof long[]) {
            parcel.writeInt(19);
            parcel.writeLongArray((long[]) obj);
        } else if (obj instanceof Byte) {
            parcel.writeInt(20);
            parcel.writeInt(((Byte) obj).byteValue());
        } else if (Build.VERSION.SDK_INT >= 21 && (obj instanceof Size)) {
            parcel.writeInt(26);
            parcel.writeSize((Size) obj);
        } else if (Build.VERSION.SDK_INT >= 21 && (obj instanceof SizeF)) {
            parcel.writeInt(27);
            parcel.writeSizeF((SizeF) obj);
        } else if (obj instanceof double[]) {
            parcel.writeInt(28);
            parcel.writeDoubleArray((double[]) obj);
        } else if (obj instanceof JSONArray) {
            parcel.writeInt(29);
            parcel.writeString(((JSONArray) obj).toString());
        } else if (obj instanceof JSONObject) {
            parcel.writeInt(30);
            parcel.writeString(((JSONObject) obj).toString());
        } else {
            Class<?> cls = obj.getClass();
            if (cls.isArray() && cls.getComponentType() == Object.class) {
                parcel.writeInt(17);
                parcel.writeArray((Object[]) obj);
            } else if (obj instanceof Serializable) {
                parcel.writeInt(21);
                parcel.writeSerializable((Serializable) obj);
            } else {
                Log.e(f14699a, AppMeasurement.Param.FATAL, new RuntimeException("Parcel: unable to marshal value " + obj + obj.getClass()));
            }
        }
    }

    private static void a(Parcel parcel, CharSequence[] charSequenceArr) {
        if (charSequenceArr != null) {
            parcel.writeInt(r0);
            for (CharSequence a2 : charSequenceArr) {
                a(parcel, a2);
            }
            return;
        }
        parcel.writeInt(-1);
    }

    private static void a(Parcel parcel, CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, parcel, 0);
    }

    public static <E extends Map> E a(Parcel parcel, ClassLoader classLoader, Callback<Void, E> callback) throws Exception {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        E e2 = (Map) callback.call(null);
        while (readInt > 0) {
            e2.put(b(parcel, classLoader, callback), b(parcel, classLoader, callback));
            readInt--;
        }
        return e2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        if (android.os.Build.VERSION.SDK_INT < 21) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0040, code lost:
        return r4.readSize();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0043, code lost:
        if (android.os.Build.VERSION.SDK_INT < 21) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0049, code lost:
        return r4.readSizeF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004e, code lost:
        return r4.createDoubleArray();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <E extends java.util.Map> java.lang.Object b(android.os.Parcel r4, java.lang.ClassLoader r5, com.xiaomi.smarthome.core.server.internal.plugin.util.Callback<java.lang.Void, E> r6) throws java.lang.Exception {
        /*
            int r0 = r4.readInt()
            r1 = 0
            r2 = 0
            r3 = 21
            switch(r0) {
                case -1: goto L_0x00fd;
                case 0: goto L_0x00f8;
                case 1: goto L_0x00ef;
                case 2: goto L_0x00ea;
                case 3: goto L_0x00e5;
                case 4: goto L_0x00e0;
                case 5: goto L_0x00d6;
                case 6: goto L_0x00cd;
                case 7: goto L_0x00c4;
                case 8: goto L_0x00bb;
                case 9: goto L_0x00ad;
                case 10: goto L_0x00a8;
                case 11: goto L_0x00a3;
                case 12: goto L_0x009e;
                case 13: goto L_0x0099;
                case 14: goto L_0x0085;
                case 15: goto L_0x0080;
                case 16: goto L_0x007b;
                case 17: goto L_0x0076;
                case 18: goto L_0x0071;
                case 19: goto L_0x006c;
                case 20: goto L_0x0063;
                case 21: goto L_0x005e;
                case 22: goto L_0x0059;
                case 23: goto L_0x0054;
                case 24: goto L_0x004f;
                case 25: goto L_0x002f;
                case 26: goto L_0x0038;
                case 27: goto L_0x0041;
                case 28: goto L_0x004a;
                case 29: goto L_0x000d;
                case 30: goto L_0x001d;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x00fe
        L_0x000d:
            java.lang.String r5 = r4.readString()
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0017 }
            r6.<init>(r5)     // Catch:{ JSONException -> 0x0017 }
            return r6
        L_0x0017:
            r6 = move-exception
            java.lang.String r1 = "ParcelUtils"
            android.util.Log.e(r1, r5, r6)
        L_0x001d:
            java.lang.String r5 = r4.readString()
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0027 }
            r6.<init>(r5)     // Catch:{ JSONException -> 0x0027 }
            return r6
        L_0x0027:
            r6 = move-exception
            java.lang.String r1 = "ParcelUtils"
            android.util.Log.e(r1, r5, r6)
            goto L_0x00fe
        L_0x002f:
            int r6 = android.os.Build.VERSION.SDK_INT
            if (r6 < r3) goto L_0x0038
            android.os.PersistableBundle r4 = r4.readPersistableBundle(r5)
            return r4
        L_0x0038:
            int r5 = android.os.Build.VERSION.SDK_INT
            if (r5 < r3) goto L_0x0041
            android.util.Size r4 = r4.readSize()
            return r4
        L_0x0041:
            int r5 = android.os.Build.VERSION.SDK_INT
            if (r5 < r3) goto L_0x004a
            android.util.SizeF r4 = r4.readSizeF()
            return r4
        L_0x004a:
            double[] r4 = r4.createDoubleArray()
            return r4
        L_0x004f:
            java.lang.Object r4 = a(r4)
            return r4
        L_0x0054:
            boolean[] r4 = r4.createBooleanArray()
            return r4
        L_0x0059:
            android.util.SparseBooleanArray r4 = r4.readSparseBooleanArray()
            return r4
        L_0x005e:
            java.io.Serializable r4 = r4.readSerializable()
            return r4
        L_0x0063:
            byte r4 = r4.readByte()
            java.lang.Byte r4 = java.lang.Byte.valueOf(r4)
            return r4
        L_0x006c:
            long[] r4 = r4.createLongArray()
            return r4
        L_0x0071:
            int[] r4 = r4.createIntArray()
            return r4
        L_0x0076:
            java.lang.Object[] r4 = r4.readArray(r5)
            return r4
        L_0x007b:
            android.os.Parcelable[] r4 = r4.readParcelableArray(r5)
            return r4
        L_0x0080:
            android.os.IBinder r4 = r4.readStrongBinder()
            return r4
        L_0x0085:
            int r5 = r4.readInt()
            if (r5 < 0) goto L_0x0098
            java.lang.String[] r2 = new java.lang.String[r5]
        L_0x008d:
            if (r1 >= r5) goto L_0x0098
            java.lang.String r6 = r4.readString()
            r2[r1] = r6
            int r1 = r1 + 1
            goto L_0x008d
        L_0x0098:
            return r2
        L_0x0099:
            byte[] r4 = r4.createByteArray()
            return r4
        L_0x009e:
            android.util.SparseArray r4 = r4.readSparseArray(r5)
            return r4
        L_0x00a3:
            java.util.ArrayList r4 = r4.readArrayList(r5)
            return r4
        L_0x00a8:
            java.lang.CharSequence r4 = b(r4)
            return r4
        L_0x00ad:
            int r4 = r4.readInt()
            r5 = 1
            if (r4 != r5) goto L_0x00b5
            goto L_0x00b6
        L_0x00b5:
            r5 = 0
        L_0x00b6:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r5)
            return r4
        L_0x00bb:
            double r4 = r4.readDouble()
            java.lang.Double r4 = java.lang.Double.valueOf(r4)
            return r4
        L_0x00c4:
            float r4 = r4.readFloat()
            java.lang.Float r4 = java.lang.Float.valueOf(r4)
            return r4
        L_0x00cd:
            long r4 = r4.readLong()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            return r4
        L_0x00d6:
            int r4 = r4.readInt()
            short r4 = (short) r4
            java.lang.Short r4 = java.lang.Short.valueOf(r4)
            return r4
        L_0x00e0:
            android.os.Parcelable r4 = r4.readParcelable(r5)
            return r4
        L_0x00e5:
            android.os.Bundle r4 = r4.readBundle(r5)
            return r4
        L_0x00ea:
            java.util.Map r4 = a(r4, r5, r6)
            return r4
        L_0x00ef:
            int r4 = r4.readInt()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            return r4
        L_0x00f8:
            java.lang.String r4 = r4.readString()
            return r4
        L_0x00fd:
            return r2
        L_0x00fe:
            int r5 = r4.dataPosition()
            int r5 = r5 + -4
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Parcel "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = ": Unmarshalling unknown type code "
            r1.append(r4)
            r1.append(r0)
            java.lang.String r4 = " at offset "
            r1.append(r4)
            r1.append(r5)
            java.lang.String r4 = r1.toString()
            r6.<init>(r4)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.util.ParcelUtils.b(android.os.Parcel, java.lang.ClassLoader, com.xiaomi.smarthome.core.server.internal.plugin.util.Callback):java.lang.Object");
    }

    @Nullable
    private static Object a(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        CharSequence[] charSequenceArr = new CharSequence[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            charSequenceArr[i2] = b(parcel);
        }
        return charSequenceArr;
    }

    private static CharSequence b(Parcel parcel) {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }
}
