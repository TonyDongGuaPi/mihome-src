package com.amap.location.common.model;

import android.os.SystemClock;
import com.alipay.sdk.util.i;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CellStatus {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4585a = 1;
    public static final int b = 2;
    public static final int c = 4;
    public static final int d = 8;
    private static final int l = 3;
    public long e;
    public int f = 0;
    public String g;
    public CellState h;
    public List<CellState> i = Collections.emptyList();
    public CellState j;
    public List<CellState> k = Collections.emptyList();
    private final List<HistoryCell> m = new ArrayList(3);

    public static class HistoryCell {

        /* renamed from: a  reason: collision with root package name */
        public int f4586a = 0;
        public int b = 0;
        public int c = 0;
        public int d = 0;
        public int e = 0;
        public int f = 0;
        public int g = 0;
        public long h = 0;

        public HistoryCell() {
        }

        public HistoryCell(CellState cellState) {
            this.f4586a = cellState.f;
            this.b = cellState.n;
            this.c = cellState.i;
            this.d = cellState.j;
            this.e = cellState.k;
            this.f = cellState.l;
            this.g = cellState.m;
            this.h = cellState.r <= 0 ? SystemClock.elapsedRealtime() : cellState.r;
        }

        /* renamed from: a */
        public HistoryCell clone() {
            HistoryCell historyCell = new HistoryCell();
            historyCell.f4586a = this.f4586a;
            historyCell.b = this.b;
            historyCell.c = this.c;
            historyCell.d = this.d;
            historyCell.e = this.e;
            historyCell.f = this.f;
            historyCell.g = this.g;
            historyCell.h = this.h;
            return historyCell;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof HistoryCell)) {
                HistoryCell historyCell = (HistoryCell) obj;
                return this.f4586a == historyCell.f4586a && this.c == historyCell.c && this.d == historyCell.d && this.f == historyCell.f && this.g == historyCell.g && this.e == historyCell.e;
            }
        }

        public String toString() {
            return String.format(Locale.CHINA, "[type=%d,rssi=%d,lac=%d, cid=%d,sid=%d,nid=%d, bid=%d, time=%d]", new Object[]{Integer.valueOf(this.f4586a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f), Integer.valueOf(this.g), Long.valueOf(this.h)});
        }
    }

    private String a(boolean z) {
        String str;
        String str2;
        String str3;
        String str4;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("CellStatus:[");
        sb3.append("updateTime=" + this.e + ",");
        sb3.append("cellType=" + this.f + ",");
        sb3.append("networkOperator=" + this.g + ",");
        if (this.h != null) {
            str = "mainCell=" + this.h.toString() + ",";
        } else {
            str = "mainCell=null ,";
        }
        sb3.append(str);
        if (this.j != null) {
            str2 = "mainCell2=" + this.j.toString() + ",";
        } else {
            str2 = "mainCell2=null ,";
        }
        sb3.append(str2);
        if (this.i == null || this.i.size() <= 0) {
            str3 = "neighbors=null";
        } else {
            ArrayList arrayList = new ArrayList();
            if (this.i.size() <= 5) {
                arrayList.addAll(this.i);
                sb2 = new StringBuilder("neighbors=");
            } else if (z) {
                arrayList.addAll(this.i.subList(0, 5));
                sb2 = new StringBuilder("neighbors=");
            } else {
                arrayList.addAll(this.i);
                sb2 = new StringBuilder("neighbors=");
            }
            sb2.append(arrayList.toString());
            str3 = sb2.toString();
        }
        sb3.append(str3);
        sb3.append(i.b);
        if (this.k == null || this.k.size() <= 0) {
            str4 = "cellStateList2=null";
        } else {
            ArrayList arrayList2 = new ArrayList();
            if (this.k.size() <= 5) {
                arrayList2.addAll(this.k);
                sb = new StringBuilder("cellStateList2=");
            } else if (z) {
                arrayList2.addAll(this.k.subList(0, 5));
                sb = new StringBuilder("cellStateList2=");
            } else {
                arrayList2.addAll(this.k);
                sb = new StringBuilder("cellStateList2=");
            }
            sb.append(arrayList2.toString());
            str4 = sb.toString();
        }
        sb3.append(str4);
        sb3.append(Operators.ARRAY_END_STR);
        StringBuilder sb4 = new StringBuilder(" [HistoryCell:");
        int size = this.m.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb4.append(i2);
            sb4.append(":");
            sb4.append(this.m.get(i2).toString());
            sb4.append(" ");
        }
        sb4.append(Operators.ARRAY_END_STR);
        return sb3.toString() + sb4.toString();
    }

    public List<HistoryCell> a() {
        return this.m;
    }

    public void a(List<HistoryCell> list) {
        if (list != null && list.size() > 0) {
            this.m.clear();
            this.m.addAll(list);
        }
    }

    /* renamed from: b */
    public CellStatus clone() {
        CellStatus cellStatus = new CellStatus();
        cellStatus.e = this.e;
        cellStatus.f = this.f;
        cellStatus.g = this.g;
        if (this.h != null) {
            cellStatus.h = this.h.clone();
        }
        if (this.j != null) {
            cellStatus.j = this.j.clone();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.i);
        cellStatus.i = arrayList;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(this.k);
        cellStatus.k = arrayList2;
        for (HistoryCell a2 : this.m) {
            cellStatus.m.add(a2.clone());
        }
        return cellStatus;
    }

    public String c() {
        return a(true);
    }

    public String toString() {
        return a(false);
    }
}
