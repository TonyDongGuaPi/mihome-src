package com.amap.openapi;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.amap.location.common.model.AmapLoc;
import com.amap.location.common.model.CellState;
import com.amap.location.common.model.FPS;
import com.amap.location.common.model.WiFi;
import java.util.ArrayList;
import java.util.List;

public class co {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f4664a = {"ENABLE", "LAT,", "LNG", "RADIUS", "TYPE"};

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public boolean f4665a;
        public AmapLoc b;
    }

    public static Cursor a(boolean z, AmapLoc amapLoc) {
        MatrixCursor matrixCursor = new MatrixCursor(f4664a);
        Object[] objArr = new Object[f4664a.length];
        objArr[matrixCursor.getColumnIndex(f4664a[0])] = Integer.valueOf(z ? 1 : 0);
        if (z && amapLoc != null) {
            objArr[matrixCursor.getColumnIndex(f4664a[1])] = Double.valueOf(amapLoc.d());
            objArr[matrixCursor.getColumnIndex(f4664a[2])] = Double.valueOf(amapLoc.c());
            objArr[matrixCursor.getColumnIndex(f4664a[3])] = Integer.valueOf((int) amapLoc.g());
            objArr[matrixCursor.getColumnIndex(f4664a[4])] = amapLoc.k();
        }
        matrixCursor.addRow(objArr);
        return matrixCursor;
    }

    public static AmapLoc a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String[] split = str.split(",");
            double parseDouble = Double.parseDouble(split[0]);
            double parseDouble2 = Double.parseDouble(split[1]);
            float parseFloat = Float.parseFloat(split[2]);
            String str2 = split[3];
            AmapLoc amapLoc = new AmapLoc();
            try {
                amapLoc.f(str2);
                amapLoc.b(parseDouble);
                amapLoc.a(parseDouble2);
                amapLoc.b(parseFloat);
            } catch (Exception unused) {
            }
            return amapLoc;
        } catch (Exception unused2) {
            return null;
        }
    }

    public static FPS a(String str, String str2) {
        FPS fps = new FPS();
        int i = 0;
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(":");
            CellState cellState = null;
            try {
                if (split.length == 3) {
                    cellState = new CellState(2, true);
                    cellState.k = Integer.parseInt(split[0]);
                    cellState.l = Integer.parseInt(split[1]);
                    cellState.m = Integer.parseInt(split[2]);
                } else if (split.length == 4) {
                    cellState = new CellState(1, true);
                    cellState.g = Integer.parseInt(split[0]);
                    cellState.h = Integer.parseInt(split[1]);
                    cellState.i = Integer.parseInt(split[2]);
                    cellState.j = Integer.parseInt(split[3]);
                }
                fps.f4587a.h = cellState;
            } catch (Exception unused) {
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            ArrayList arrayList = new ArrayList();
            try {
                int length = str2.length();
                do {
                    int indexOf = str2.indexOf(44, i);
                    if (indexOf == -1) {
                        break;
                    }
                    long parseLong = Long.parseLong(str2.substring(i, indexOf));
                    int i2 = indexOf + 1;
                    if (i2 < length) {
                        int indexOf2 = str2.indexOf(59, i2);
                        if (indexOf2 == -1) {
                            indexOf2 = length;
                        }
                        arrayList.add(new WiFi(parseLong, (String) null, Integer.parseInt(str2.substring(i2, indexOf2)), 0, 0));
                        i = indexOf2 + 1;
                        continue;
                    } else {
                        i = i2;
                        continue;
                    }
                } while (i < length);
                fps.b.a((List<WiFi>) arrayList);
            } catch (Exception unused2) {
            }
        }
        return fps;
    }

    public static a a(Cursor cursor) {
        a aVar = new a();
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(f4664a[0]);
            if (columnIndex != -1) {
                aVar.f4665a = cursor.getInt(columnIndex) == 1;
            }
            if (aVar.f4665a) {
                String str = "";
                int columnIndex2 = cursor.getColumnIndex(f4664a[1]);
                double d = 0.0d;
                double d2 = columnIndex2 != -1 ? cursor.getDouble(columnIndex2) : 0.0d;
                int columnIndex3 = cursor.getColumnIndex(f4664a[2]);
                if (columnIndex3 != -1) {
                    d = cursor.getDouble(columnIndex3);
                }
                int columnIndex4 = cursor.getColumnIndex(f4664a[3]);
                int i = columnIndex4 != -1 ? cursor.getInt(columnIndex4) : 0;
                int columnIndex5 = cursor.getColumnIndex(f4664a[4]);
                if (columnIndex5 != -1) {
                    str = cursor.getString(columnIndex5);
                }
                AmapLoc amapLoc = new AmapLoc();
                amapLoc.a(System.currentTimeMillis());
                amapLoc.b(0);
                amapLoc.d(str);
                amapLoc.b(d2);
                amapLoc.a(d);
                amapLoc.b((float) i);
                if (amapLoc.R()) {
                    aVar.b = amapLoc;
                }
            }
        }
        return aVar;
    }

    public static String[] a(String str, FPS fps, AmapLoc amapLoc, int i) {
        String[] strArr = new String[5];
        strArr[0] = str;
        if (fps != null) {
            strArr[1] = cn.a(fps.f4587a);
            if (fps.b != null && fps.b.a() > 0) {
                StringBuilder sb = new StringBuilder();
                int a2 = fps.b.a();
                for (int i2 = 0; i2 < a2; i2++) {
                    WiFi a3 = fps.b.a(i2);
                    sb.append(a3.f4589a);
                    sb.append(",");
                    sb.append(a3.c);
                    sb.append(i.b);
                }
                sb.deleteCharAt(sb.length() - 1);
                strArr[2] = sb.toString();
            }
        }
        if (amapLoc != null && amapLoc.R()) {
            strArr[3] = amapLoc.d() + "," + amapLoc.c() + "," + amapLoc.g() + "," + amapLoc.o();
        }
        strArr[4] = String.valueOf(i);
        return strArr;
    }
}
