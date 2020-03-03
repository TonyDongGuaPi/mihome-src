package com.amap.openapi;

import android.text.TextUtils;
import com.amap.location.common.model.CellState;
import com.amap.location.common.model.CellStatus;
import com.amap.location.common.util.f;
import com.amap.location.security.Core;

public class cn {
    public static long a(long j) {
        try {
            return Core.encMac(f.a(j));
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static long a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Core.encMac(str);
            } catch (Throwable unused) {
            }
        }
        return -1;
    }

    public static String a(CellStatus cellStatus) {
        StringBuilder sb;
        int i;
        if (cellStatus == null || cellStatus.h == null) {
            return "";
        }
        CellState cellState = cellStatus.h;
        if (cellState.f == 2) {
            sb = new StringBuilder();
            sb.append(cellState.k);
            sb.append(":");
            sb.append(cellState.l);
            sb.append(":");
            i = cellState.m;
        } else if (cellState.f == 0 || cellState.g == 0 || cellState.g == 65535) {
            return "";
        } else {
            sb = new StringBuilder();
            sb.append(cellState.g);
            sb.append(":");
            sb.append(cellState.h);
            sb.append(":");
            sb.append(cellState.i);
            sb.append(":");
            i = cellState.j;
        }
        sb.append(i);
        return sb.toString();
    }
}
